package kr.spring.library.main.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import kr.spring.library.board_announce.vo.BoardAnnounceVO;
import kr.spring.library.lib_lost_item.vo.LibLostItemVO;
import kr.spring.library.main.vo.LibraryMainVO;

@Mapper
public interface LibraryMainMapper {
	
	
	@Select("select * FROM lib_product_manage")
	public List<LibraryMainVO> selectLibraryAllPorducts();
	
	@Select("select COUNT (lib_product_class_no) AS search_count, lib_product_class_no "
			+ "FROM LIB_PRODUCT_MANAGE GROUP BY lib_product_class_no ORDER BY lib_product_class_no")
	public List<LibraryMainVO> selectLibraryCategoryNav();
	
	
	public List<LibraryMainVO> selectLibraryByCategoryAndOrderNum(Map<String,Object> map);
	public int selectLibraryByCategoryAndOrderNumCount(Map<String,Object> map);
	
	@Select("SELECT * FROM (SELECT a.*, rownum rnum FROM ("
			+ "SELECT notice_num, notice_title, notice_hit, notice_reg_date FROM board_announce ORDER BY notice_reg_date DESC"
			+ ")a) WHERE rnum <= #{end}")
	public List<BoardAnnounceVO> selectAnnounceList(int end);
	
	@Select("SELECT * FROM (SELECT a.*, rownum rnum FROM (SELECT * FROM lib_lost_item )a) WHERE rnum <= #{end}")
	public List<LibLostItemVO> selectLostList(int end);
	
	
	@Select("SELECT SYSDATE FROM DUAL")
	public String selectCurrentTime();
	
	@Select("SELECT * FROM (SELECT a.*, rownum rnum FROM (SELECT * FROM lib_product_manage "
			+ "ORDER BY lib_product_loancnt DESC )a) WHERE rnum <= #{end}")
	public List<LibraryMainVO> selectLibraryAjaxTop5(int end);
	
	@Select("SELECT * FROM (SELECT a.*, rownum rnum FROM (SELECT * FROM lib_product_manage "
			+ "ORDER BY DBMS_RANDOM.RANDOM )a) WHERE rnum <= #{end}")
	public List<LibraryMainVO> selectLibraryAjaxRecommend(int end);
	
	public List<LibraryMainVO> selectLibraryAjaxReviewBest(int end);
	
	@Select("SELECT * FROM (SELECT a.*, rownum rnum FROM (SELECT * FROM lib_product_manage "
			+ "ORDER BY lib_product_publication_year DESC)a) WHERE rnum <= #{end}")
	public List<LibraryMainVO> selectLibraryAjaxNew(int end);
	
	
	
}

package kr.spring.library.main.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

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
	
}

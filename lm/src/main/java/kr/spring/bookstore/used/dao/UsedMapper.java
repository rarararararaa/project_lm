package kr.spring.bookstore.used.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import kr.spring.bookstore.used.vo.UsedVO;

@Mapper
public interface UsedMapper {
	//중고 책 등록 하기
		public void insertUsedManage(UsedVO usedVO);
		public void insertUsedDetail(UsedVO usedVO);
		//중고 책 List 로 반환
		public List<UsedVO> selectUsedList(Map<String,Object> map);
		//product_title을 넣어서 number를 찾아오자
		public int searchProductNum(String store_product_title);
		
		//popup 창에서 찾는 select 창
		public List<UsedVO> selectProductNameByUsed(Map<String,Object> map);
		
		
		//RowCount
		public int selectUsedRowCount(Map<String,Object>map);
		
		
		//중고 책 목록 반환
		public UsedVO selectUsed(Integer user_num);
		
		//중고 책 수정하기
		public void updateUsed(Integer used_product_num);
		
		//중고 책 지우기
		public void deleteUsed(Integer used_product_num);
		
		//중고 책 조회 수
		public int updateHit(Integer used_product_num);
		
}

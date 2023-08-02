package kr.spring.bookstore.used.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import kr.spring.bookstore.used.vo.UsedVO;

@Mapper
public interface UsedMapper {
	//중고 책 등록 하기
		@Select("SELECT store_used_product_manage_seq.nextval FROM dual")
		public int getUsed_product_num();
		@Insert("INSERT INTO STORE_USED_PRODUCT_MANAGE (used_product_num, store_product_num, mem_num, used_product_status, used_product_condition, used_product_approve, used_product_reg_date, used_product_hit) "
				+ " VALUES (#{used_product_num},#{store_product_num},#{mem_num},#{used_product_status},#{used_product_condition},#{used_product_approve},SYSDATE, 0)")
		public void insertUsedManage(UsedVO usedVO);
		@Insert("INSERT INTO STORE_USED_PRODUCT_DETAIL (used_product_num, used_product_info, used_product_photo1, used_product_photo2, used_product_price)"
				+ " VALUES (#{used_product_num}, #{used_product_info}, #{used_product_photo1}, #{used_product_photo2}, #{used_product_price})")
		public void insertUsedDetail(UsedVO usedVO);
		
		
		//중고 책 List 로 반환
		@Select("SELECT * FROM store_used_product_manage m LEFT JOIN store_used_product_detail d ON m.used_product_num = d.used_product_num")
		public List<UsedVO> selectAllUsed();
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

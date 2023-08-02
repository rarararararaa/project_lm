package kr.spring.bookstore.used.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import kr.spring.bookstore.used.vo.UsedVO;

@Mapper
public interface UsedMapper {
		
		//중고 책 List 로 반환
		@Select("select COUNT(store_product_num) used_product_match_count, store_product_num, store_product_title, store_product_author,"
				+ "store_product_pubdate, store_product_pricesales, store_product_cover, store_product_publisher, store_product_description "
				+ "FROM store_product_detail RIGHT INNER JOIN (select * FROM store_used_product_detail d RIGHT JOIN store_used_product_manage m ON m.used_product_num = d.used_product_num) USING (store_product_num) GROUP BY store_product_num, store_product_title, "
				+ "store_product_author, store_product_pubdate, store_product_pricesales, store_product_cover, store_product_publisher, store_product_description")
		public List<UsedVO> selectAllUsed();
		
		
		@Select("SELECT rs.* , sd.*, 100-(floor((used_product_price/store_product_pricestandard)*100)) AS devide_product_by_used "
				+ " FROM store_product_detail sd JOIN "
				+ "(SELECT * FROM store_used_product_manage m JOIN store_used_product_detail d ON m.used_product_num = d.used_product_num WHERE mem_num = #{mem_num} ) "
				+ " rs ON rs.store_product_num = sd.store_product_num")
		public List<UsedVO> selectUsedProductByMem(int mem_num);
		
		
		//Submit Start
		@Select("SELECT store_used_product_manage_seq.nextval FROM dual")
		public int getUsed_product_num();
		@Insert("INSERT INTO STORE_USED_PRODUCT_MANAGE (used_product_num, store_product_num, mem_num, used_product_status, used_product_condition, used_product_approve, used_product_reg_date, used_product_hit) "
				+ " VALUES (#{used_product_num},#{store_product_num},#{mem_num},#{used_product_status},#{used_product_condition},#{used_product_approve},SYSDATE, 0)")
		public void insertUsedManage(UsedVO usedVO);
		@Insert("INSERT INTO STORE_USED_PRODUCT_DETAIL (used_product_num, used_product_info, used_product_photo1, used_product_photo2, used_product_price)"
				+ " VALUES (#{used_product_num}, #{used_product_info}, #{used_product_photo1}, #{used_product_photo2}, #{used_product_price})")
		public void insertUsedDetail(UsedVO usedVO);
		//Popup Start
		public int searchProductNum(String store_product_title);
		//Popup 창에서 찾는 select 창
		public List<UsedVO> selectProductNameByUsed(Map<String,Object> map);
		//Popup Result Count 창
		public int selectProductNameByUsedCount(Map<String,Object> map);
		//End of Popup
		
		//End of Submit
		
		
		
		
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

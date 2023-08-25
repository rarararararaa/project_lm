package kr.spring.bookstore.used.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.spring.bookstore.used.vo.UsedVO;

@Mapper
public interface UsedMapper {
		
		//중고 책 List 로 반환
		@Select("select * FROM store_product_manage RIGHT JOIN ("
				+ "select COUNT(store_product_num) used_product_match_count, store_product_num, store_product_title, store_product_author, store_product_ratingcount, store_product_ratingscore,"
				+ "store_product_pubdate, store_product_pricesales, store_product_cover, store_product_publisher, store_product_description "
				+ "FROM store_product_detail RIGHT INNER JOIN (select * FROM store_used_product_detail d RIGHT JOIN store_used_product_manage m ON m.used_product_num = d.used_product_num) USING (store_product_num) "
				+ "GROUP BY store_product_num, store_product_title, store_product_author, store_product_pubdate, store_product_pricesales, store_product_cover, store_product_publisher, store_product_description, store_product_ratingcount, store_product_ratingscore"
				+ ") USING (store_product_num)")
		public List<UsedVO> selectAllUsed();
		
		@Select("select COUNT(*) count FROM store_product_manage RIGHT JOIN ("
				+ "select COUNT(store_product_num) used_product_match_count, store_product_num, store_product_title, store_product_author,"
				+ "store_product_pubdate, store_product_pricesales, store_product_cover, store_product_publisher, store_product_description "
				+ "FROM store_product_detail RIGHT INNER JOIN (select * FROM store_used_product_detail d RIGHT JOIN store_used_product_manage m ON m.used_product_num = d.used_product_num) USING (store_product_num) GROUP BY store_product_num, store_product_title, "
				+ "store_product_author, store_product_pubdate, store_product_pricesales, store_product_cover, store_product_publisher, store_product_description"
				+ ") USING (store_product_num)")
		public int selectAllUsedCount();
		
		/////////////////////////////////수정 필요...
		@Select("SELECT * FROM (SELECT a.*, rownum rnum FROM("
				+ "SELECT sm.store_product_ISBN13, sd.store_product_num, sd.store_product_title, um.used_product_num, um.used_product_approve, sd.store_product_cover, "
				+ "sd.store_product_pricesales, um.used_product_condition, ud.used_product_price, 100 - (floor((used_product_price / store_product_pricestandard) * 100)) AS devide_product_by_used, ud.used_product_info "
				+ "FROM store_used_product_manage um JOIN store_used_product_detail ud ON ud.used_product_num = um.used_product_num "
				+ "JOIN store_product_detail sd ON sd.store_product_num = um.store_product_num "
				+ "JOIN store_product_manage sm ON um.store_product_num = sm.store_product_num WHERE um.mem_num = #{mem_num} )a) WHERE rnum >= #{start} AND rnum <= #{end}")
		public List<UsedVO> selectUsedProductByMem(Map<String,Object> map);
		
		@Select("SELECT COUNT(*) count FROM (SELECT sm.store_product_ISBN13 ,ud.*, sd.*, um.*, 100-(floor((used_product_price/store_product_pricestandard)*100)) AS devide_product_by_used FROM store_used_product_manage um "
				+ "JOIN store_used_product_detail ud ON ud.used_product_num = um.used_product_num "
				+ "JOIN store_product_detail sd ON sd.store_product_num = um.store_product_num "
				+ "JOIN store_product_manage sm ON sd.store_product_num = sm.store_product_num WHERE um.mem_num = #{mem_num})")
		public int selectUsedProductByMemCount(int mem_num);
		
		
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
		@Select("SELECT ud.*, sd.*, um.*, 100-(floor((used_product_price/store_product_pricestandard)*100)) AS devide_product_by_used FROM store_used_product_manage um "
				+ "JOIN store_used_product_detail ud ON ud.used_product_num = um.used_product_num "
				+ "JOIN store_product_detail sd ON sd.store_product_num = um.store_product_num WHERE um.used_product_num = #{used_product_num} AND um.mem_num = #{mem_num}")
		public UsedVO selectUsedProductNumAndMemNum(int used_product_num, int mem_num);
		
		@Update("UPDATE store_used_product_manage SET store_product_num = #{store_product_num}, "
				+ "used_product_condition = #{used_product_condition},"
				+ " used_product_reg_date = SYSDATE WHERE used_product_num = #{used_product_num}")
		public void usedUpdateManage(UsedVO usedVO);
		@Update("UPDATE store_used_product_detail SET used_product_info = #{used_product_info}, "
				+ "used_product_photo1 = #{used_product_photo1}, used_product_photo2 = #{used_product_photo2}, "
				+ "used_product_price = #{used_product_price} WHERE used_product_num = #{used_product_num}")
		public void usedUpdateDetail(UsedVO usedVO);
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
		
		@Select("select om.*, od.used_product_num , pd.store_product_cover, pd.store_product_title, om.order_date "
				+ "from store_order_manage om RIGHT JOIN store_order_detail od "
				+ "ON om.order_num = od.order_num LEFT JOIN store_product_detail pd ON pd.store_product_num = od.store_product_num "
				+ "WHERE od.used_product_num != 0 AND om.mem_num = #{mem_num}")
		public List<UsedVO> selectUsedSalesStatus(int mem_num);
}

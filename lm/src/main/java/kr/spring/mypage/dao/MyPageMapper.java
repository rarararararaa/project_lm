package kr.spring.mypage.dao;

import java.sql.Blob;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.spring.mypage.vo.MyPageVO;

@Mapper
public interface MyPageMapper {
	
	//lm_member_manage의 mem_reg_date
	@Select("SELECT mem_reg_date FROM lm_member_manage WHERE mem_num=#{mem_num}")
	public String getMemRegDate(int mem_num);
	//lm_member_detail의 mem_grade,mem_point,mem_modify_date
	@Select("SELECT mem_point,mem_grade,mem_modify_date FROM lm_member_detail WHERE mem_num=#{mem_num}")
	public List<MyPageVO> getDetailData(int mem_num);
	//lib_member_zzim의 zzim_num
	@Select("SELECT COUNT(zzim_num) FROM lib_member_zzim WHERE mem_num=#{mem_num}")
	public int getZzimNum(int mem_num);
	//lm_review의 review_num
	@Select("SELECT COUNT(review_num) FROM lm_review WHERE mem_num=#{mem_num}")
	public int getReviewNum(int mem_num);
	//lib_book_report의 rep_num
	@Select("SELECT COUNT(rep_num) FROM lib_book_report WHERE mem_num=#{mem_num}")
	public int getRepNum(int mem_num);
	//lm_coupon_detail의 coupon_num
	@Select("SELECT COUNT(coupon_num) FROM lm_coupon_detail WHERE mem_num=#{mem_num}")
	public int getCouponNum(int mem_num);
	//store_order_manage order_total_price
	@Select("SELECT SUM(order_total_price) FROM store_order_manage WHERE mem_num = #{mem_num}")
	public String getOrderPrice(int mem_num);
	@Select("SELECT mem_name,mem_email,mem_cell FROM lm_member_detail WHERE mem_num=#{mem_num}")
	public MyPageVO getMyEdit(int mem_num);
	@Update("UPDATE lm_member_detail SET mem_passwd = #{mem_new_passwd} WHERE mem_num = #{mem_num}")
	public void updatePasswd(MyPageVO mypageVO);
	@Update("UPDATE lm_member_detail SET mem_email = #{mem_new_email} WHERE mem_num = #{mem_num}")
	public void updateEmail(MyPageVO mypageVO);
	@Update("UPDATE lm_member_detail SET mem_cell = #{mem_new_cell} WHERE mem_num = #{mem_num}")
	public void updateCell(MyPageVO mypageVO);
	@Update("UPDATE lm_member_manage SET mem_auth = 4 WHERE mem_num = #{mem_num}")
	public void updateAuth(MyPageVO mypageVO);
	@Select("SELECT mem_salt FROM lm_member_detail WHERE mem_num =#{mem_num}")
	public String getSalt(int mem_num);
	//db의 mem_num과 session의 mem_num 비교
	@Select("SELECT m.mem_num FROM lm_member_detail d INNER JOIN lm_member_manage m ON d.mem_num = m.mem_num WHERE m.mem_id = #{mem_id} AND d.mem_passwd = #{mem_passwd}")
	public int memberOutCheck(MyPageVO mypageVO);
	//탈퇴회원 manage에 auth,탈퇴일 업데이트
	@Update("UPDATE lm_member_manage SET mem_auth = 0, mem_exp_date = SYSDATE WHERE mem_num = #{mem_num}")
	public void memberOut(int mem_num);
	//탈퇴회원 home 테이블 정보 삭제
	@Delete("DELETE FROM store_member_home WHERE mem_num=#{mem_num}")
	public void memberOut_Home(int mem_num);
	//탈퇴회원 detail 테이블 정보 삭제
	@Delete("DELETE FROM lm_member_detail WHERE mem_num=#{mem_num}")
	public void memberOut_Detail(int mem_num);
	@Update("UPDATE lm_member_detail SET mem_photo = #{mem_photo} WHERE mem_num=#{mem_num}")
	public void updatePhoto(MyPageVO mypageVO);
	//photo 유무 확인
	@Select("SELECT * FROM lm_member_detail WHERE mem_num = #{mem_num}")
	public MyPageVO getPhoto(int mem_num);
	//회원정보 수정일 수정
	@Update("UPDATE lm_member_detail SET mem_modify_date = SYSDATE WHERE mem_num = #{mem_num}")
	public void updateModifyDate(MyPageVO mypageVO);
	//배송지, 결제 정보
	@Select("SELECT * FROM store_order_manage a INNER JOIN store_member_home b ON a.home_num = b.home_num WHERE order_num = #{order_num} AND a.mem_num = #{mem_num}")
	public MyPageVO getHomeOrderList(int order_num,int mem_num);
	//주문내역 가져오기 store_order_manage, store_order_detail, store_product_detail
	@Select("SELECT * FROM (SELECT a.*, rownum rnum FROM (SELECT c.store_product_title,d.order_total_price, d.order_status, d.order_date, d.order_num, d.order_pay_status FROM"
			+ "			(SELECT LISTAGG(a.store_product_title, ', ') AS store_product_title, b.order_num FROM (SELECT m.mem_num,d.store_product_num,d.order_num FROM store_order_manage m INNER JOIN store_order_detail d ON m.order_num = d.order_num) b"
			+ "			INNER JOIN store_product_detail a ON b.store_product_num = a.store_product_num GROUP BY order_num) c INNER JOIN store_order_manage d ON c.order_num = d.order_num"
			+ "			WHERE mem_num = #{mem_num}  ORDER BY order_num DESC)a) WHERE rnum >= #{start} AND rnum <= #{end}")
	public List<MyPageVO> getOrderList(Map<String,Object> map);
	@Select("SELECT COUNT(*) FROM store_order_manage")
	public int selectRowCountOrderList(Map<String,Object> map);
	
	//주문내역 상세 가져오기
	@Select("SELECT * FROM (SELECT a.*, rownum rnum FROM ("
			+ "SELECT c.*,d.store_product_isbn13 FROM (SELECT a.*,b.store_product_title,b.store_product_author,b.store_product_cover,b.store_product_publisher FROM store_order_detail a INNER JOIN store_product_detail b ON a.store_product_num = b.store_product_num) c INNER JOIN store_product_manage d ON c.store_product_num = d.store_product_num WHERE order_num=#{order_num} order by order_num DESC "
			+ ")a) WHERE rnum >= #{start} AND rnum <= #{end}")
	public List<MyPageVO> getOrderListDetail(Map<String,Object> map);
	@Select("SELECT COUNT(*) FROM store_order_detail WHERE order_num=#{order_num}")
	public int selectRowCountOrderListDetail(Map<String,Object> map);
	
	//문의내역 가져오기
	@Select("SELECT * FROM (SELECT a.*, rownum rnum FROM (SELECT ask_num,ask_title,ask_content,ask_reg_date,ask_image,ask_image_name FROM lib_board_ask WHERE mem_num=#{mem_num} ORDER BY ask_num DESC)a) WHERE rnum >= #{start} AND rnum <= #{end}")
	public List<MyPageVO> getAskList(Map<String,Object> map);
	@Select("SELECT COUNT(*) FROM lib_board_ask WHERE mem_num = #{mem_num}")
	public int selectRowCountAskList(Map<String,Object> map);

	//대출/반납내역 가져오기
	@Select("SELECT * FROM (SELECT a.*, rownum rnum FROM ("
			+ "SELECT a.*,b.lib_product_bookname,b.lib_product_isbn FROM lib_history a INNER JOIN lib_product_manage b ON a.callNumber = b.callNumber WHERE a.mem_num = #{mem_num}"
			+ ")a) WHERE rnum >= #{start} AND rnum <= #{end}")
	public List<MyPageVO> getCheckList(Map<String,Object> map);
	@Select("SELECT COUNT(*) FROM lib_history WHERE mem_num = #{mem_num}")
	public int selectRowCountCheckList(Map<String,Object> map);
	
	//희망도서신청내역 가져오기
	@Select("SELECT * FROM (SELECT a.*, rownum rnum FROM ("
			+ "SELECT * FROM lib_book_apply WHERE mem_num = #{mem_num}"
			+ ")a) WHERE rnum >= #{start} AND rnum <= #{end}")
	public List<MyPageVO> getWantBookList(Map<String,Object> map);
	@Select("SELECT COUNT(*) FROM lib_book_apply WHERE mem_num=#{mem_num}")
	public int selectRowCountWantBookList(Map<String,Object> map);
	
	//프로그램신청내역 가져오기
	@Select("SELECT * FROM (SELECT a.*, rownum rnum FROM ("
			+ "SELECT * FROM lib_program_apply_user a INNER JOIN lib_program_admin b ON a.program_num = b.program_num WHERE mem_num=#{mem_num}"
			+ ")a) WHERE rnum >= #{start} AND rnum <= #{end}")
	public List<MyPageVO> getProgramList(Map<String,Object> map);
	@Select("SELECT COUNT(*) FROM lib_program_apply_user WHERE mem_num=#{mem_num}")
	public int selectRowCountProgramList(Map<String,Object> map);
}



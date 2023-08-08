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
}





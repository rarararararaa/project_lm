package kr.spring.mypage.dao;

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
	
}





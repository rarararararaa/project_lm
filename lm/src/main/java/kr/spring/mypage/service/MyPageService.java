package kr.spring.mypage.service;

import java.util.List;
import kr.spring.mypage.vo.MyPageVO;

public interface MyPageService {
	//마이페이지 정보 가져오기
	//mem_grade,mem_point,zzim_num,review_num,rep_num,coupon_num
	public String getMemRegDate(int mem_num);
	public List<MyPageVO> getDetailData(int mem_num);
	public int getZzimNumCount(int mem_num);
	public int getReviewNumCount(int mem_num);
	public int getRepNumCount(int mem_num);
	public int getCouponNumCount(int mem_num);
	public String getOrderPrice(int mem_num);
}

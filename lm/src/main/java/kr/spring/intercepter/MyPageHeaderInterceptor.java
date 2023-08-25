package kr.spring.intercepter;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import kr.spring.mypage.service.MyPageService;
import kr.spring.mypage.vo.MyPageVO;

public class MyPageHeaderInterceptor implements HandlerInterceptor{
	
	@Autowired
	private MyPageService mypageService;
	
	private static final Logger logger = 
			LoggerFactory.getLogger(MyPageHeaderInterceptor.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			                 HttpServletResponse response,
			                 Object handler)
                                 throws Exception{
		logger.debug("<<MyPageHeaderInterceptor 진입>>");
		
		HttpSession session = request.getSession();
		Integer mem_num = (Integer)session.getAttribute("mem_num");
		if(mem_num == null) {
			return true;
		}else {
			logger.debug("<<MyPageHeaderInterceptor else 진입>>");
		List<MyPageVO> list = null;
		
		//grade, point, modify_date list에 저장
		list = mypageService.getDetailData(mem_num);
		int mem_grade = list.get(0).mem_grade;
		String mem_point = list.get(0).mem_point+"";
		//YYYY-MM-DD
		String mem_modify_date = "";
		if(list.get(0).mem_modify_date != null) {
			mem_modify_date = list.get(0).mem_modify_date.substring(0,10);
		}else {
			mem_modify_date = "수정기록 없음";
		}
		String mem_reg_date = mypageService.getMemRegDate(mem_num).substring(0,10);
		int zzim_num_count = mypageService.getZzimNumCount(mem_num);
		int review_num_count = mypageService.getReviewNumCount(mem_num);
		int rep_num_count = mypageService.getRepNumCount(mem_num);
		int coupon_num_count = mypageService.getCouponNumCount(mem_num);
		
		//int형 등급을 string형 등급으로 처리
		String mem_grade_name = "";
		if(mem_grade == 0) {
			mem_grade_name = "Bronze";
		}else if(mem_grade == 1) {
			mem_grade_name = "Silver";
		}else if(mem_grade == 2) {
			mem_grade_name = "Gold";
		}else if(mem_grade == 3) {
			mem_grade_name = "Platinum";
		}else if(mem_grade == 4) {
			mem_grade_name = "Diamond";
		}
		
		//회원의 총 주문 금액 (주문금액/100 처리)
		int mem_order_price=0;
		if(mypageService.getOrderPrice(mem_num) != null) { //int형은 null이 불가능하여 String으로 받은 후 int로 저장
			mem_order_price = Integer.parseInt(mypageService.getOrderPrice(mem_num))/100;	
		}

		//다음 등급까지 남은 금액
		String mem_grade_remain = "";
		if(mem_order_price<1000) {
			mem_grade_remain = (1000 - mem_order_price) + " 점"; //int to String
		}else if(mem_order_price>=1000 && mem_order_price<5000) {
			mem_grade_remain = (5000 - mem_order_price) + " 점"; 
		}else if(mem_order_price>=5000 && mem_order_price<15000) {
			mem_grade_remain = (15000 - mem_order_price) + " 점";
		}else if(mem_order_price>=10000 && mem_order_price<3000) {
			mem_grade_remain = (30000 - mem_order_price) + " 점";
		}else if(mem_order_price>=3000) {
			mem_grade_remain = "최고 등급입니다.";
		}
		//천단위 , 처리
		mem_grade_remain = mem_grade_remain.replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",");
		mem_point = mem_point.replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",");
		//데이터 등록
		request.setAttribute("mem_grade_name",mem_grade_name);
		request.setAttribute("mem_grade_remain",mem_grade_remain);
		request.setAttribute("mem_point",mem_point);
		request.setAttribute("mem_modify_date",mem_modify_date);
		request.setAttribute("mem_reg_date",mem_reg_date);
		request.setAttribute("zzim_num_count",zzim_num_count);
		request.setAttribute("review_num_count",review_num_count);
		request.setAttribute("rep_num_count",rep_num_count);
		request.setAttribute("coupon_num_count",coupon_num_count);
		
		return true;
		}
	}
}

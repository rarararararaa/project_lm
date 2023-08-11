package kr.spring.bookstore.review.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.spring.bookstore.payment.vo.BookStorePaymentOrderVO;
import kr.spring.bookstore.review.service.ReviewService;
import kr.spring.bookstore.review.vo.ReviewVO;
import kr.spring.member.vo.MemberVO;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class ReviewController {
	@Autowired
	private ReviewService reviewService;

	//리뷰 폼 초기화
	@ModelAttribute
	public ReviewVO initCommand() {
		return new ReviewVO();
	}
	
	//리뷰 버튼 눌렀을 때 구매한 내역이 있는지&이미 작성한 사항인지 체크 후 폼 호출
	@RequestMapping("/bookstore/review/reviewForm.do")
	@ResponseBody
	public Map<String, Object> reviewForm(@RequestParam int store_product_num,HttpSession session,
							 HttpServletRequest request) {
		
		log.debug("<<item_num>> : "+store_product_num);
		Map<String, Object> mapJson=new HashMap<String, Object>();
		mapJson.put("mem_num", session.getAttribute("mem_num"));
		mapJson.put("store_product_num", store_product_num);
		List<BookStorePaymentOrderVO> list=reviewService.selectOrderVO(mapJson);
		if(list.size()!=0) {
			for(BookStorePaymentOrderVO i:list) {
				//if(reviewService.selectOrderDetail(i)==null) {
					mapJson.put("result", "noOrder");
					return mapJson;
				}
				if(reviewService.selectReviewCheck(mapJson)) {
					mapJson.put("result", "reviewAlready");
					return mapJson;
				}
				log.debug("<<mapJson>> : "+mapJson);
			}
			mapJson.put("result", "success");
			log.debug("<<mapJson>> : "+mapJson);
			return mapJson;
		/*
		 * mapJson.put("result", "noOrder"); return mapJson;
		 */
	}
	
	@RequestMapping("/bookstore/review/reviewWrite.do")
	@ResponseBody
	public Map<String, Object> reviewSubmit(@Valid ReviewVO reviewVO,
			             BindingResult result,
			             HttpServletRequest request,
			             HttpSession session
			             ) {
		Map<String, Object> mapJson=new HashMap<String, Object>();
		//유효성 체크 결과 오류가 있으며 폼 호출
		if(result.hasErrors()) {
		}
		
		reviewVO.setMem_num((Integer)session.getAttribute("mem_num"));
		log.debug("<<reviewVO : >>"+reviewVO);
		//글쓰기
		reviewService.insertReview(reviewVO);
		mapJson.put("result","success");
		return mapJson; 
	}
}
























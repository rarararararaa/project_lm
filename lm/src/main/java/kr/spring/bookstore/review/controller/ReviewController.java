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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.spring.bookstore.payment.vo.BookStorePaymentOrderVO;
import kr.spring.bookstore.review.service.ReviewService;
import kr.spring.bookstore.review.vo.ReviewVO;
import kr.spring.bookstore.service.vo.OrderDetailVO;
import kr.spring.member.service.MemberService;
import kr.spring.member.vo.MemberVO;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class ReviewController {
	@Autowired
	private ReviewService reviewService;
	@Autowired
	private MemberService memberService;

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
		List<OrderDetailVO> list=reviewService.selectOrderDetail(mapJson);
		if(list.size()==0) {
			mapJson.put("result", "reviewAlready");
			return mapJson;
		}else {
			mapJson.put("result", "success");
			return mapJson;
			
		}
	}	
	
	// 리뷰 작성
	@RequestMapping("/bookstore/review/reviewWrite.do")
	public String reviewWrite(@Valid ReviewVO reviewVO, BindingResult result, Model model,
			HttpServletRequest request, HttpSession session,
						@RequestParam(value="store_product_isbn13", required=false) String store_product_isbn13,
						@RequestParam int store_product_num) {
		
		//상품 이미지 유효성 체크 //MultipartFile -> byte[]로 변환한 경우 파일을 업로드 하지 않으면 byte[]는 생성되고 length는 0이다. 
		if(reviewVO.getReview_image().length == 0) {
			result.rejectValue("review_image", "required"); 
		}

		Map<String, Object> map=new HashMap<String, Object>();
		//용량체크 - byte[] 이므로 용량 체크 가능 
		if(reviewVO.getReview_image().length >= 5*1024*1024) { //5 MB // 자바빈의 필드명, 에러 코드, 에러문구에 전달할 값, 기본 오류(에러) 문구
			result.rejectValue("review_image", "limitIploadSize", new Object[] {"5MB"}, null); 
		}
	    
	    Integer mem_num=(Integer)session.getAttribute("mem_num");
	    MemberVO member=memberService.selectMember(mem_num);
	    log.debug("<<memberVO : >>"+member);
	    reviewVO.setMem_num(mem_num);
	    map.put("mem_num", mem_num);
	    reviewVO.setMem_id(member.getMem_id());
		map.put("store_product_num", store_product_num);
		List<OrderDetailVO> list=reviewService.selectOrderDetail(map);
		for(OrderDetailVO i:list) {
				reviewVO.setOrderdetailVO(i);
				reviewVO.setOrder_detail_num(reviewVO.getOrderdetailVO().getOrder_detail_num());
				reviewService.insertReview(reviewVO);
				break;
		}	 
	    model.addAttribute("message", "댓글 등록 완료");
	    model.addAttribute("url",request.getContextPath()+"/bookstore/product/productDetail.do?store_product_isbn13="+store_product_isbn13);
	 
	    return "common/resultView";
	}

}
























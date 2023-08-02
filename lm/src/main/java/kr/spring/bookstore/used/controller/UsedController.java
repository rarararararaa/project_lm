package kr.spring.bookstore.used.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.spring.bookstore.used.service.UsedService;
import kr.spring.bookstore.used.vo.UsedVO;
import kr.spring.member.vo.MemberVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class UsedController {

	// 서비스에 autowired 걸어주기

	@Autowired
	private UsedService usedService;

	// VO 초기화
	@ModelAttribute
	public UsedVO initCommand() {
		return new UsedVO();
	}


	@RequestMapping("/bookstore/used/usedMain.do")
	public ModelAndView getUsedMainClick() {
		List<UsedVO> list = usedService.selectAllUsed();
		log.debug("<<중고 책 List 목록>> : " + list);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("usedMain");
		mav.addObject("list",list);
		return mav; // 타일스 식별자
	}

	@RequestMapping("/bookstore/used/usedNoticeForm.do")
	public ModelAndView getUsedNoticeForm(HttpSession session) { // 중고 등록 알림
		if(session.getAttribute("mem_num") == null) {
			ModelAndView mav = new ModelAndView();
			mav.addObject("lo",1);
			mav.setViewName("redirect:/lm/login/template/loginMain.do");
			return mav;
		}
		ModelAndView mav = new ModelAndView();
		mav.setViewName("usedNoticeForm");
		return mav;
	}

	@RequestMapping("/bookstore/used/usedBooksByUser.do")
	public ModelAndView getUsedBooksByUser(HttpSession session) { // 등록한 중고 상품
		
		if(session.getAttribute("mem_num") == null) {
			ModelAndView mav = new ModelAndView();
			mav.addObject("lo",1);
			mav.setViewName("redirect:/lm/login/template/loginMain.do");
			return mav;
		}
		int mem_num = (Integer)session.getAttribute("mem_num");
		List<UsedVO> list = usedService.selectUsedProductByMem(mem_num);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("usedBooksByUser");
		
		mav.addObject("list",list);
		return mav;
	}

	@RequestMapping("/bookstore/used/usedSalesStatus.do")
	public String getUsedSalesStatus(Model model) { // 중고 판매 상태
		return "usedSalesStatus";
	}

	@GetMapping("/bookstore/used/usedForm.do")
	public ModelAndView getUsedForm(HttpSession session) {
		if(session.getAttribute("mem_num") == null) {
			ModelAndView mav = new ModelAndView();
			mav.addObject("lo",1);
			mav.setViewName("redirect:/lm/login/template/loginMain.do");
			return mav;
		}
		ModelAndView mav = new ModelAndView();
		mav.setViewName("usedForm");
		return mav;
	}
	// 팝업창 영역 (팝업 띄우기)/////////
	@GetMapping("/bookstore/used/usedSearchProductPopup.do")
	public String popup() {
		return "/bookstore/used/usedSearchProductPopup"; // pop업 띄우기
	}

	// 팝업 서치 출력
	@GetMapping("/bookstore/used/selectProductNameByUsed.do")
	public ModelAndView selectProducts(@RequestParam(value = "keyword") String keyword, HttpSession session) {
		
		
		Map<String, Object> map = new HashMap<String, Object>();
		// 맵 선언 후 keyword의 스트링은 keyword이다...
		map.put("keyword", keyword);
		log.debug("<<검색 목록>> : " + keyword);
		int count = usedService.selectProductNameByUsedCount(map);
		log.debug("<<검색 결과 갯수>> : " + count);
		// list에 담자...
		List<UsedVO> list = null;

		list = usedService.selectProductNameByUsed(map);

		// 뿌리는거 (Model And View로 뿌리자)
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/bookstore/used/usedSearchProductPopup");
		log.debug("<<검색 결과>> : " + list);
		mav.addObject("success", 1);
		mav.addObject("list", list);
		mav.addObject("count",count);
		
		return mav;
	}

	// 팝업 끝//////////////////////
	/* 나중 Ajax 처리
	@RequestMapping("/bookstore/used/confirmProductNum.do")
	@ResponseBody
	public Map<String, String> confirmProductNum(@RequestParam String store_product_title) {
		// Map 형태로 받아서 key value 값 주기
		Map<String, String> mapAjax = new HashMap<String, String>();

		// product 받아야함...
		int product_num = usedService.searchProductNum(store_product_title);
		if (product_num == 0) {
			// 아이디 중복
			mapAjax.put("result", "idDuplicated");
		} else {

		}
		return mapAjax;
	}
	*/
	
	@PostMapping("/bookstore/used/usedForm.do")
	public String submitUsedForm(@Valid UsedVO usedVO, BindingResult result, HttpServletRequest request, HttpSession session, Model model,  
			@RequestParam("store_product_num") int store_product_num) {
		
		usedVO.setStore_product_num(store_product_num);
		log.debug("<<등록한 중고책 데이터>> : " + usedVO);
		usedVO.setMem_num((Integer) session.getAttribute("mem_num"));
		
		usedService.insertUsed(usedVO);
	
		
		model.addAttribute("message", "글 수정 완료!");
		model.addAttribute("url", request.getContextPath()+"/bookstore/used/usedNoticeForm.do");
		

		return "resultView";
	}
	
}

package kr.spring.bookstore.used.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	
	//서비스에 autowired 걸어주기
	
	@Autowired private UsedService usedService;
	
	//VO 초기화
	@ModelAttribute
	public UsedVO initCommand() {
		return new UsedVO();
	}
	
	//jsp에서 RequestMapping 값 들어오면 걸어주기
	@RequestMapping("/bookstore/template/bsUsedMain.do")
	public String getUsedMain(Model model) { //중고 판매 책 목록
		return "usedMain"; //타일스 식별자
	}
	
	@RequestMapping("/bookstore/used/usedMain.do")
	public String getUsedMainClick(Model model) {
		return "usedMain";
	}
	
	@RequestMapping("/bookstore/used/usedNoticeForm.do")
	public String getUsedNoticeForm(Model model) { //중고 등록 알림
		return "usedNoticeForm";
	}
	
	@RequestMapping("/bookstore/used/usedBooksByUser.do")
	public String getUsedBooksByUser(Model model) { //등록한 중고 상품
		return "usedBooksByUser";
	}
	
	@RequestMapping("/bookstore/used/usedSalesStatus.do")
	public String getUsedSalesStatus(Model model) { //중고 판매 상태
		return "usedSalesStatus";
	}
	
	@GetMapping("/bookstore/used/usedForm.do")
	public String getUsedForm(Model model) {
		UsedVO usedVO = new UsedVO();
		//model을 넣어줘야 jsp에서 form:form을 받을 수 있다...
		model.addAttribute(usedVO);
		return "usedForm";
	}
	
	@PostMapping("/bookstore/used/usedForm.do")
	public String submit() {
		return "usedForm";
	}
	
	//팝업창 영역 (팝업 띄우기)/////////
	@RequestMapping("/bookstore/used/usedSearchProductPopup.do")
	public String popup() {
		return "/bookstore/used/usedSearchProductPopup"; //pop업 띄우기
	}
	
	//팝업 서치 출력
	@GetMapping("/bookstore/used/selectProductNameByUsed.do")
	public ModelAndView selectProducts(@RequestParam(value="keyword") String keyword, HttpSession session) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("keyword", keyword);
		
		
		
		//list에 담자...
		List<UsedVO> list = null;
		list = usedService.selectProductNameByUsed(keyword);
		
		//뿌리는거 (Model And View로 뿌리자)
		ModelAndView mav = new ModelAndView();
		mav.setViewName("selectProductNameByUsed");
		mav.addObject("success",1);
		mav.addObject("list",list);
		return mav; 
	}
	
	//팝업 끝//////////////////////
	
	
	@RequestMapping("/bookstore/used/confirmProductNum.do")
	@ResponseBody
	public Map<String,String> confirmProductNum(@RequestParam String store_product_title){
		//Map 형태로 받아서 key value 값 주기
		Map<String,String> mapAjax = new HashMap<String,String>();
		
		//product 받아야함...
		int product_num = usedService.searchProductNum(store_product_title);
		if(product_num == 0) {
			//아이디 중복
			mapAjax.put("result", "idDuplicated");
		}else {
			
		}		
		return mapAjax;
	}
	
	
}

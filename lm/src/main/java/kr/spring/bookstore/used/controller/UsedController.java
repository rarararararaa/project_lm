package kr.spring.bookstore.used.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.spring.bookstore.used.service.UsedService;
import kr.spring.bookstore.used.vo.UsedVO;

@Controller
public class UsedController {
	
	//서비스에 autowired 걸어주기
	
	/*
	 * @Autowired private UsedService usedService;
	 */
	 
	
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
	
	
}

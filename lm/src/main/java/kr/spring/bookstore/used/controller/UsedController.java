package kr.spring.bookstore.used.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.spring.bookstore.used.vo.UsedVO;

@Controller
public class UsedController {
	
	//autowired 걸어주기
	/*
	 * @Autowired private UsedVO usedvo;
	 */
	
	//VO 초기화
	@ModelAttribute
	public UsedVO initCommand() {
		return new UsedVO();
	}
	
	
	
	//jsp에서 RequestMapping 값 들어오면 걸어주기
	@RequestMapping("/bookstoreUsed/usedMain.do")
	public String getUsedMain() {
		return "usedMain"; //타일스 식별자
	}
	
	
	@RequestMapping("/bookstoreUsed/usedNoticeForm.do")
	public String getUsedNoticeForm() {
		return "usedNoticeForm";
	}
	
	@PostMapping("/bookstoreUsed/usedForm.do")
	public String getUsedForm() {
		return "usedForm";
	}
	
	@RequestMapping("/bookstoreUsed/usedSalesStatus.do")
	public String getUsedSalesStatus() {
		return "usedSalesStatus";
	}
	
	
}

package kr.spring.bookstore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class bookstoreMainController {

	@RequestMapping("/")
	public String getbsMain() {
		return "redirect:/bookstore/template/bsMain.do";
	}
	
	@RequestMapping("/bookstore/template/bsMain.do")
	public String bsMain(Model model) {
		return "bsMain"; //타일스 설정의 식별자
	}
	
	
	
}

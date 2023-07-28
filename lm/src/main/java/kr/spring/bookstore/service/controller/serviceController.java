package kr.spring.bookstore.service.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class serviceController {
	@RequestMapping("/bookstore/service/main.do")
	public String serviceMain() {
		return "main";
	}
}

package kr.spring.bookstore.service.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class serviceController {
	@RequestMapping("/bookstore/service/main.do")
	public String serviceMain() {
		return "service";
	}
	@RequestMapping("/bookstore/service/announceList.do")
	public String selectAnnounce() {
		return "announceList";
	}
	@GetMapping("/bookstore/service/announceWrite.do")
	public String writeAnnounce() {
		return "announceWrite";
	}
}

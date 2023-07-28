package kr.spring.library.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LibMainController {

	
	@RequestMapping("/library/template/libMain.do")
	public String libMain(Model model) {
		return "libMain"; //타일스 설정의 식별자
	}
}

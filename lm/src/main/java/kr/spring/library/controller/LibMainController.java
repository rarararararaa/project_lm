package kr.spring.library.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import kr.spring.library.main.service.LibraryMainService;
import kr.spring.library.main.vo.LibraryMainVO;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class LibMainController {
	
	@Autowired
	LibraryMainService libraryMainService;
	
	@ModelAttribute
	public LibraryMainVO initCommand() {
		return new LibraryMainVO();
	}
	
	
	@GetMapping("/library/template/libMain.do")
	public ModelAndView libMain(LibraryMainVO libraryMainVO, HttpServletRequest request, HttpSession session) {
		
		
		List<LibraryMainVO> list = null;
		
		list = libraryMainService.selectLibraryAllPorducts();
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("libMain");
		mav.addObject("list",list);
		return mav; //타일스 설정의 식별자
	}
	
	@RequestMapping("/library/template/libAdmin.do")
	public String adminMain(Model model) {
		
		return "libAdmin";//타일스 설정의 식별자
	}
}

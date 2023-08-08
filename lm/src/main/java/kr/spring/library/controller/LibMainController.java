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
import org.springframework.web.bind.annotation.RequestParam;
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
		List<LibraryMainVO> navs = null;
		list = libraryMainService.selectLibraryAllPorducts();
		navs = libraryMainService.selectLibraryCategoryNav();
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("libMain");
		mav.addObject("list",list);
		mav.addObject("navs",navs);
		
		return mav; //타일스 설정의 식별자
	}
	
	@RequestMapping("/library/template/libAdmin.do")
	public String adminMain(Model model) {
		
		return "libAdmin";//타일스 설정의 식별자
	}
	
	@GetMapping("/library/template/libSearchMain.do") 
	public ModelAndView searchMain(@RequestParam(name="categoryNum", defaultValue="10") int categoryNum,
			@RequestParam(name="orderByNum", defaultValue="1") int orderByNum,
			@RequestParam(name="keyword", defaultValue="") String keyword,
			LibraryMainVO libraryMainVO, HttpServletRequest request, HttpSession session) {
		Map<String,Object> map = new HashMap<>();
		//Search가자..
		//List<LibraryMainVO> list = null;
		List<LibraryMainVO> navs = null;
		List<LibraryMainVO> result = null;
		
		
		//list = libraryMainService.selectLibraryAllPorducts();
		navs = libraryMainService.selectLibraryCategoryNav();
		map.put("keyword",keyword);
		map.put("categoryNum",categoryNum);
		map.put("orderByNum",orderByNum);
		result = libraryMainService.selectLibraryByCategoryAndOrderNum(map);
		//300자 넘으면 ... 처리...
		for(LibraryMainVO VO : result) {
			if(VO.getLib_product_detail().length() >= 300) {
				VO.setLib_product_detail(VO.getLib_product_detail().substring(0,300)+"...");
			}
		}
		int totalCount = libraryMainService.selectLibraryByCategoryAndOrderNumCount(map);
		int selectedCategoryNum = categoryNum;
		ModelAndView mav = new ModelAndView();
		mav.setViewName("libSearchMain");
		mav.addObject("list",result);
		mav.addObject("navs",navs);
		mav.addObject("totalCount",totalCount);
		mav.addObject("selectedCategoryNum",selectedCategoryNum);
		//mav.addObject("orderByNum",orderByNum);
		log.debug("<<navs를 까보자>> : "+navs);
		return mav;
	}
	
}

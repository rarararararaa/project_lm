package kr.spring.bookstore.service.controller;

import java.util.List;

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
import org.springframework.web.servlet.ModelAndView;

import kr.spring.bookstore.service.service.ServiceService;
import kr.spring.bookstore.service.vo.AnnounceVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class ServiceController {
	
	@Autowired
	private ServiceService serviceService;
	
	@ModelAttribute
	public AnnounceVO initCommand() {
		return new AnnounceVO();
	}
	
	
	@RequestMapping("/bookstore/service/main.do")
	public String serviceMain() {
		return "service";
	}
	@RequestMapping("/bookstore/service/announceList.do")
	public ModelAndView selectAnnounce() {
		
		List<AnnounceVO> list = null;
		list = serviceService.selectAnnounceList();
		ModelAndView mav = new ModelAndView();
		mav.setViewName("announceList");
		mav.addObject("list",list);
		return mav;
	}
	@GetMapping("/bookstore/service/announceWrite.do")
	public String formAnnounce() {
		return "announceWrite";
	}
	@PostMapping("/bookstore/service/announceWrite.do")
	public String writeAnnounce(@Valid AnnounceVO announceVO,
								BindingResult result,
								HttpSession session,
								HttpServletRequest request,
								Model model) {
		log.debug("<<글 작성>> : " + announceVO);
		
		//유효성 체크 결과 오류가 있으면 폼 호출
		if(result.hasErrors()) {
			return formAnnounce();
		}
		//글 작성
		if(announceVO.getBoard_filename() == null) {
			announceVO.setBoard_filename("");
		}
		serviceService.insertAnnounce(announceVO);
		
		return "announceList";
	}
}

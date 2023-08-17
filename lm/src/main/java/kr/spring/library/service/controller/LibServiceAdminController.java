package kr.spring.library.service.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kr.spring.library.memberadmin.service.MemberAdminService;
import kr.spring.library.service.service.LibServiceService;
import kr.spring.library.service.vo.BoardAnswerVO;
import kr.spring.library.service.vo.BoardAskVO;
import kr.spring.member.vo.MemberVO;
import kr.spring.util.PagingUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class LibServiceAdminController {
	@Autowired
	private LibServiceService libServiceService;
	
	@Autowired
	private MemberAdminService memberAdminService;
	
	@ModelAttribute
	public MemberVO initCommand() {
		return new MemberVO();
	}
	
	@ModelAttribute
	public BoardAskVO initCommand2() {
		return new BoardAskVO();
	}
	@ModelAttribute
	public BoardAnswerVO initCommand3() {
		return new BoardAnswerVO();
	}
	
	//1:1 문의하기 목록
	@RequestMapping("/library/service/admin_boardAskList.do")
	public ModelAndView adminDesk(HttpSession session) {

		Integer mem_num = (Integer)session.getAttribute("mem_num");
		Integer mem_auth = (Integer)session.getAttribute("mem_auth");

		List<BoardAskVO> list = null;
	
		if(mem_num!=null) {
			if(mem_auth==9) {
				list = libServiceService.selectBoardAskList();
			}else {
				list = libServiceService.selectBoardAskListByMem_num(mem_num);
			}
		}
		return new ModelAndView("admin_boardAskList","list",list);
	}
	
	//1:1 문의하기 - 관리자 답변 작성
	@GetMapping("/library/service/admin_boardAnswerWrite.do")
	public String answerWrite(@RequestParam int ask_num,Model model) {
		BoardAskVO ask = libServiceService.selectBoardAsk(ask_num);

		log.debug("<<ask>> : " + ask);

		model.addAttribute("ask", ask);

		return "admin_boardAnswerWrite";
	}
	//이미지 출력
	@RequestMapping("/library/service/admin_boardAskImageView.do")
	public ModelAndView viewImageAsk(@RequestParam int ask_num) {

		BoardAskVO vo = 
				libServiceService.selectBoardAsk(ask_num);

		ModelAndView mav = new ModelAndView();
		mav.setViewName("admin_boardAskImageView");
		mav.addObject("imageFile", vo.getAsk_image());
		mav.addObject("filename", vo.getAsk_imagename());
		return mav;
	}
	@PostMapping("/library/service/admin_boardAnswerWrite.do")
	public String answerInsert(BoardAnswerVO boardAnswer, HttpSession session,Model model) {
		log.debug("<<BoardAnswerVO>> : " + boardAnswer);

		boardAnswer.setMem_num((Integer)session.getAttribute("mem_num"));

		libServiceService.insertBoardAnswer(boardAnswer);

		model.addAttribute("message", "답변 작성이 완료되었습니다.");
		model.addAttribute("url", "admin_boardAskList.do");

		return "common/resultView";
	}
}

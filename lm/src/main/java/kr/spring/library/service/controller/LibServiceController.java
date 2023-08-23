package kr.spring.library.service.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kr.spring.library.memberadmin.service.MemberAdminService;
import kr.spring.library.service.service.LibServiceService;
import kr.spring.library.service.vo.BoardAnswerVO;
import kr.spring.library.service.vo.BoardAskVO;
import kr.spring.member.vo.MemberVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class LibServiceController {
	
	@Autowired
	private LibServiceService libServiceService;
	
	@Autowired
	private MemberAdminService memberAdminService;
	
	/*========================
	 * 자바빈(VO) 초기화
	 *========================*/
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
	
	/*========================
	 * 고객센터(메인페이지)
	 *========================*/
	@RequestMapping("/library/service/main.do")
	public String serviceMain() {
		return "libService";
	}
	
	/*========================
	 * 고객센터(1:1 문의하기) - 1:1문의글 등록
	 *========================*/
	//등록 폼
	@GetMapping("/library/service/boardAskWrite.do")
	public String askWrite() {
		return "boardAskWrite";
	}
	//전송된 데이터 처리
	@PostMapping("/library/service/boardAskWrite.do")
	public String askInsert(BoardAskVO boardAskVO, Model model,HttpServletRequest request,HttpSession session) {
		log.debug("<<boardAskVO>> : " + boardAskVO);

		boardAskVO.setMem_num((Integer)session.getAttribute("mem_num"));

		libServiceService.insertBoardAsk(boardAskVO);

		model.addAttribute("message", "1:1 문의가 접수되었습니다.");
		model.addAttribute("url", "user_boardAskList.do");

		return "common/resultView";
	}
	
	/*========================
	 * 고객센터(1:1 문의하기) - 1:1문의글 목록
	 *========================*/
	@RequestMapping("/library/service/user_boardAskList.do")
	public ModelAndView mainDesk(HttpSession session) {
		Integer mem_num = (Integer)session.getAttribute("mem_num");
		Integer mem_auth = (Integer)session.getAttribute("mem_auth");

		Map<String, Object> map = new HashMap<String, Object>();
		// 전체/검색 레코드수
		int count = libServiceService.selectRowCount(map);

		log.debug("<<ALL-boardAsk-count>> : " + count);
		
		List<BoardAskVO> list = null;
		if (count > 0) {
			if(mem_num!=null) {
				if(mem_auth==9) {
					list = libServiceService.selectBoardAskList();
				}else {
					list = libServiceService.selectBoardAskListByMem_num(mem_num);
				}
			}
		}
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("user_boardAskList");
		mav.addObject("count", count);
		mav.addObject("list", list);
		return mav;
		//return new ModelAndView("user_boardAskList","list",list);
	}
	
	/*========================
	 * 고객센터(1:1 문의하기) - 내가 쓴 1:1문의글 상세
	 *========================*/
	@RequestMapping("/library/service/boardAskDetail.do")
	public ModelAndView askDetail(@RequestParam int ask_num,HttpSession session) {
		BoardAnswerVO boardAnswerVO = libServiceService.selectBoardAnswer(ask_num);
		BoardAskVO boardAskVO = libServiceService.selectBoardAsk(ask_num);

		Integer mem_num = (Integer)session.getAttribute("mem_num");
		
		ModelAndView mav = new ModelAndView();

		if(mem_num!=boardAskVO.getMem_num()) {
			mav.setViewName("common/resultView");
			mav.addObject("message","회원정보가 일치 하지 않습니다.");
			mav.addObject("url", "user_boardAskList.do");

			return mav;
		}

		mav.setViewName("boardAskView");
		mav.addObject("boardAnswerVO",boardAnswerVO);
		mav.addObject("boardAskVO",boardAskVO);

		return mav;
	}
	
	/*========================
	 * 고객센터(1:1 문의하기) - 내가 쓴 1:1문의글 수정
	 *========================*/
	//수정 폼 호출
	@GetMapping("/library/service/boardAskUpdate.do")
	public String formUpdate(@RequestParam int ask_num,
			                  Model model) {
		BoardAskVO boardAskVO = 
				libServiceService.selectBoardAsk(ask_num);
		model.addAttribute("boardAskVO", boardAskVO);
		
		return "boardAskModify";
	}
	//전송된 데이터 처리
	@PostMapping("/library/service/boardAskUpdate.do")
	public String submitUpdate(
			    @Valid BoardAskVO boardAskVO,
			    BindingResult result,
			    HttpServletRequest request,
			    Model model) {
		log.debug("<<글 수정 - BoardAskVO>> : " + boardAskVO);
		
		//유효성 체크 결과 오류가 있으면 폼 호출
		if(result.hasErrors()) {
			return "boardAskModify";
		}

		//글 수정
		libServiceService.updateBoardAsk(boardAskVO);
		
		//View에 표시할 메시지
		model.addAttribute("message", "1:1문의글 수정 완료!");
		model.addAttribute("url", 
				request.getContextPath()
				+"/library/service/boardAskDetail.do?ask_num="
						+boardAskVO.getAsk_num());

		return "common/resultView";
	}
	
	/*========================
	 * 고객센터(1:1 문의하기) - 내가 쓴 1:1문의글 삭제
	 *========================*/
	@RequestMapping("/library/service/boardAskDelete.do")
	public String submitDelete(
			     @RequestParam int ask_num) {
		log.debug("<<글삭제 - ask_num>> : " + ask_num);
		
		//글삭제
		libServiceService.deleteBoardAsk(ask_num);
		
		return "redirect:/library/service/user_boardAskList.do";
	}

	/*========================
	 * 고객센터(1:1 문의하기) - 1:1문의답변 작성(관리자)
	 *========================*/
	//등록폼
	@GetMapping("/library/service/boardAnswerWrite.do")
	public String answerWrite(@RequestParam int ask_num,Model model) {
		BoardAskVO boardAsk = libServiceService.selectBoardAsk(ask_num);

		log.debug("<<boardAsk>> : " + boardAsk);
		
		model.addAttribute("boardAsk", boardAsk);

		return "boardAnswerWrite";
	}
	@PostMapping("/library/service/boardAnswerWrite.do")
	public String answerInsert(BoardAnswerVO boardAnswer, HttpSession session,Model model) {
		log.debug("<<BoardAnswerVO>> : " + boardAnswer);

		boardAnswer.setMem_num((Integer)session.getAttribute("mem_num"));
		
		libServiceService.insertBoardAnswer(boardAnswer);

		model.addAttribute("message", "답변 작성이 완료되었습니다.");
		model.addAttribute("url", "user_boardAskList.do");

		return "common/resultView";
	}
	
}

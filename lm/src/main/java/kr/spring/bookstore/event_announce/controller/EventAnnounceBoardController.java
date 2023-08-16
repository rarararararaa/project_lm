package kr.spring.bookstore.event_announce.controller;

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

import kr.spring.bookstore.event.vo.BsEventVO;
import kr.spring.bookstore.event_announce.service.EventAnnounceBoardService;
import kr.spring.bookstore.event_announce.vo.EventAnnounceBoardVO;
import kr.spring.util.PagingUtil;
import kr.spring.util.StringUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class EventAnnounceBoardController {
	
	@Autowired
	private EventAnnounceBoardService eventAnnounceService;
	
	/*========================
	 * 자바빈(VO) 초기화
	 *========================*/
	@ModelAttribute
	public EventAnnounceBoardVO initCommand() {
		return new EventAnnounceBoardVO();
	}
	
	/*========================
	 * 게시판 글 등록
	 *========================*/
	//등록 폼
	@GetMapping("/bookstore/event/eventAnnounceWrite.do")
	public String form() {
		return "eventAnnounceWrite";
	}
	//전송된 데이터 처리
	@PostMapping("/bookstore/event/eventAnnounceWrite.do")
	public String submit(@Valid EventAnnounceBoardVO eventboardVO,
						 BindingResult result, Model model,
						 HttpServletRequest request, HttpSession session) {
		
		log.debug("<<게시판 글쓰기 >> : " + eventboardVO);
		
		//유효성 체크 결과 오류가 있으며 폼 호출
		if(result.hasErrors()) {
			return form();
		}
		
		//글쓰기
		eventAnnounceService.insertEventAnnounceBoard(eventboardVO);
		
		model.addAttribute("message", "글쓰기가 완료되었습니다.");
		model.addAttribute("url", request.getContextPath()+"/bookstore/event/announcelist.do");
		
		return "common/resultView";
	}
	
	/*========================
	 * 게시판 목록
	 *========================*/
	@RequestMapping("/bookstore/event/eventAnnounceList.do")
	public ModelAndView getList(@RequestParam(value="pageNum", defaultValue="1") int currentPage,
								String keyfield, String keyword) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("keyfield", keyfield);
		map.put("keyword", keyword);
		
		//전체/검색 레코드수
		int count = eventAnnounceService.selectCount(map);

		log.debug("<<count>> : " + count);

		//페이지 처리
		PagingUtil page = new PagingUtil(keyfield,keyword,currentPage,count,20,10,"announceList.do");

		List<EventAnnounceBoardVO> list = null;
		
		if(count > 0) {
			map.put("start", page.getStartRow());
			map.put("end", page.getEndRow());
			
			list = eventAnnounceService.selectList(map);
		}
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("eventAnnounceList");
		mav.addObject("count", count);
		mav.addObject("list", list);
		mav.addObject("page", page.getPage());
		
		return mav;
	}
	
	/*========================
	 * 게시판 글 상세
	 *========================*/
	@RequestMapping("/bookstore/event/eventAnnounceDetail.do")
	public ModelAndView getDetail(@RequestParam int event_announce_board_num) {
		log.debug("<<글 상세 - board_num>> : " + event_announce_board_num);

		//해당 글의 조회수 증가
		eventAnnounceService.updateHit(event_announce_board_num);

		//글 상세
		EventAnnounceBoardVO announceBoard = eventAnnounceService.selectEventAnnounceBoard(event_announce_board_num);

		//제목에 태그를 허용하지 않음
		announceBoard.setTitle(StringUtil.useNoHtml(announceBoard.getTitle()));

		//CKEditor를 사용하지 않을 경우 내용에 태그 불허
		//board.setContent(StringUtil.useBrNoHtml(board.getContent()));
		// -> 에디터를 사용하는경우 태그를 허용해줘야 한다.

		//뷰 이름		  속성명	  속성값
		return new ModelAndView("eventAnnounceView", "announceBoard", announceBoard);
	}
	
	/*========================
	 * 게시판 글 수정
	 *========================*/
	//수정 폼 호출
	@GetMapping("/bookstore/event/eventAnnounceUpdate.do")
	public String formUpdate(@RequestParam int event_announce_board_num, Model model) {
		EventAnnounceBoardVO eventAnnounceVO = eventAnnounceService.selectEventAnnounceBoard(event_announce_board_num);
		log.debug("<<글 수정 - eventAnnounceVO>> : " + eventAnnounceVO);
		model.addAttribute("eventAnnounceBoardVO", eventAnnounceVO);
		return "eventAnnounceModify";
	}
	@RequestMapping("/bookstore/event/eventAnnounceUpdate.do")
	public String submitUpdate(@Valid EventAnnounceBoardVO eventAnnounceVO, BindingResult result, HttpServletRequest request, Model model) {
		log.debug("<<글 수정버튼 클릭 - bsEventVO>> : " + eventAnnounceVO);
		
		//유효성 체크 결과 오류가 있으면 폼 호출
		if(result.hasErrors()) {
			return "eventAnnounceModify";
		}
		//글 수정
		eventAnnounceService.updateEventAnnouceBoard(eventAnnounceVO);		
		//View에 표시할 메시지
		model.addAttribute("message", "글 수정 완료!");
		model.addAttribute("url", request.getContextPath()+"/bookstore/event/eventAnnounceList.do");
		
		return "common/resultView";
	}
	
	/*========================
	 * 게시판 글 삭제
	 *========================*/
	
	//전달된 데이터 처리
	@RequestMapping("/bookstore/event/eventAnnounceDelete.do")
	public String submitDelete(@RequestParam int event_announce_board_num) {
		log.debug("<<글삭제 - event_announce_board_num>> : " + event_announce_board_num);
		
		//삭제
		eventAnnounceService.deleteEventAnnounceBoard(event_announce_board_num);
		
		return "redirect:/bookstore/event/eventAnnounceList.do";
	}
	
	
}

package kr.spring.library.board_announce.controller;

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

import kr.spring.library.board_announce.service.BoardAnnounceService;
import kr.spring.library.board_announce.vo.BoardAnnounceVO;
import kr.spring.util.PagingUtil;
import kr.spring.util.StringUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class BoardAnnounceController {
	@Autowired
	private BoardAnnounceService boardAnnounceService;

	/* 
	 * ============================== 자바빈(VO) 초기화 ==============================
	 */
	@ModelAttribute
	public BoardAnnounceVO initCommand() {
		return new BoardAnnounceVO();
	}

	/*
	 * ============================== 게시판 목록 ==============================
	 */
	@RequestMapping("/library/boardannounce/list.do")
	public ModelAndView getList(@RequestParam(value = "pageNum", defaultValue = "1") int currentPage,
			@RequestParam(value = "order", defaultValue = "1") int order, String keyfield, String keyword) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("keyfield", keyfield);
		map.put("keyword", keyword);

		// 전체/검색 레코드수
		int count = boardAnnounceService.selectRowCount(map);

		log.debug("<<ALL-board-count>> : " + count);

		// 페이지처리 부가적인 파라미터
		PagingUtil page = new PagingUtil(keyfield, keyword, currentPage, count, 15, 10, "list.do", "&order=" + order);

		List<BoardAnnounceVO> list = null;
		if (count > 0) {
			map.put("order", order);
			map.put("start", page.getStartRow());
			map.put("end", page.getEndRow());

			list = boardAnnounceService.selectList(map);
		}

		ModelAndView mav = new ModelAndView();
		mav.setViewName("boardAnnounceList");
		mav.addObject("count", count);
		mav.addObject("list", list);
		mav.addObject("page", page.getPage());

		return mav;
	}

	/*
	 * ============================== 게시판 글 등록 ==============================
	 */
	// 등록 폼
	@GetMapping("/library/boardannounce/write.do")
	public String form() {
		return "boardAnnounceWrite";
	}
	
	// 게시판 테이블에 데이터 삽입
	@PostMapping("/library/boardannounce/write.do")
	public String submit(@Valid BoardAnnounceVO boardAnnounceVO, BindingResult result, HttpServletRequest request, HttpSession session,
			Model model) {

		log.debug("<< 게시판 글쓰기 >> : " + boardAnnounceVO);
		// 유효성 체크결과 오류가 있으면 폼 호출
		if (result.hasErrors()) {
			return form();
		}
		// IP 셋팅
		boardAnnounceVO.setNotice_ip(request.getRemoteAddr());
		// 글쓰기
		boardAnnounceService.insertBoardAnnounce(boardAnnounceVO);

		model.addAttribute("message", "공지사항 등록이 완료되었습니다.");
		model.addAttribute("url", request.getContextPath() + "/library/boardannounce/list.do");

		return "common/resultView";
	}

	/*
	 * ============================== 게시판 글 상세 ==============================
	 */
	@RequestMapping("/library/boardannounce/detail.do")
	public ModelAndView getDetail(@RequestParam int notice_num) {
		log.debug("<<글상세 - notice_num>> : " + notice_num);

		// 해당 글의 조회수 증가
		boardAnnounceService.updateHit(notice_num);
		// 글상세
		
		BoardAnnounceVO boardAnnounce = boardAnnounceService.selectBoardAnnounce(notice_num);

		// 제목에 태그를 허용하지 않음
		boardAnnounce.setNotice_title(StringUtil.useNoHtml(boardAnnounce.getNotice_title()));

		// 뷰 이름 속성명 속성값
		return new ModelAndView("boardAnnounceView", "boardAnnounce", boardAnnounce);
	}

	/*
	 * ============================== 게시판 글수정 ==============================
	 */
	// 수정 폼 호출
	@GetMapping("/library/boardannounce/update.do")
	public String formUpdate(@RequestParam int notice_num, Model model) {

		BoardAnnounceVO boardAnnounceVO = boardAnnounceService.selectBoardAnnounce(notice_num);
		model.addAttribute("boardAnnounceVO", boardAnnounceVO);

		return "boardAnnounceModify";
	}

	// 전송된 데이터 처리
	@PostMapping("/library/boardannounce/update.do")
	public String submitUpdate(@Valid BoardAnnounceVO boardAnnounceVO, BindingResult result, HttpServletRequest request, Model model) {
		log.debug("<<글 수정 - BoardAnnounceVO>> : " + boardAnnounceVO);

		// 유효성 체크 결과 오류가 있으면 폼 호출
		if (result.hasErrors()) {
			return "boardAnnounceModify";
		}

		//ip 셋팅
		boardAnnounceVO.setNotice_ip(request.getRemoteAddr());
				
		//글 수정
		boardAnnounceService.updateBoardAnnounce(boardAnnounceVO);

		//View에 표시할 메시지
		model.addAttribute("message","공지사항 수정 완료!");
		model.addAttribute("url", 
				request.getContextPath() 
				+ "/library/boardannounce/detail.do?notice_num="
						+boardAnnounceVO.getNotice_num());
		
		return "common/resultView";
	}
	
	/*
	 * ============================== 게시판 글삭제 ==============================
	 */
	@RequestMapping("/library/boardannounce/delete.do")
	public String submitDelete(@RequestParam int notice_num) {
		log.debug("<<글삭제 - notice_num>> : " + notice_num);
		
		//글삭제
		boardAnnounceService.deleteBoardAnnounce(notice_num);
		
		return "redirect:/library/boardannounce/list.do";
	}
	
	
	/*
	 * ============================== 게시판 목록(사용자) ==============================
	 */
	@RequestMapping("/library/boardannounce/Userlist.do")
	public ModelAndView getUserList(@RequestParam(value = "pageNum", defaultValue = "1") int currentPage,
			@RequestParam(value = "order", defaultValue = "1") int order, String keyfield, String keyword) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("keyfield", keyfield);
		map.put("keyword", keyword);

		// 전체/검색 레코드수
		int count = boardAnnounceService.selectRowCount(map);

		log.debug("<<ALL-board-count>> : " + count);

		// 페이지처리 부가적인 파라미터
		PagingUtil page = new PagingUtil(keyfield, keyword, currentPage, count, 20, 10, "list.do", "&order=" + order);

		List<BoardAnnounceVO> list = null;
		if (count > 0) {
			map.put("order", order);
			map.put("start", page.getStartRow());
			map.put("end", page.getEndRow());

			list = boardAnnounceService.selectList(map);
		}

		ModelAndView mav = new ModelAndView();
		mav.setViewName("boardAnnounceUserList");
		mav.addObject("count", count);
		mav.addObject("list", list);
		mav.addObject("page", page.getPage());

		return mav;
	}
	
	/*
	 * ============================== 게시판 글 상세 ==============================
	 */
	@RequestMapping("/library/boardannounce/detailUser.do")
	public ModelAndView getUserDetail(@RequestParam int notice_num) {
		log.debug("<<글상세 - notice_num>> : " + notice_num);

		// 해당 글의 조회수 증가
		boardAnnounceService.updateHit(notice_num);
		// 글상세
		
		BoardAnnounceVO boardAnnounce = boardAnnounceService.selectBoardAnnounce(notice_num);

		// 제목에 태그를 허용하지 않음
		boardAnnounce.setNotice_title(StringUtil.useNoHtml(boardAnnounce.getNotice_title()));

		// 뷰 이름 속성명 속성값
		return new ModelAndView("boardAnnounceUserView", "boardAnnounce", boardAnnounce);
	}
}













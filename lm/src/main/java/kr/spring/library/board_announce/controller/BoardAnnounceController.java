package kr.spring.library.board_announce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import kr.spring.library.board_announce.service.BoardAnnounceService;
import kr.spring.library.board_announce.vo.BoardAnnounceVO;

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
	

	/*
	 * ============================== 게시판 글 등록 ==============================
	 */
	// 등록 폼
	@GetMapping("/library/boardannounce/write.do")
	public String form() {
		return "boardAnnounceWrite";
	}

	/*
	 * ============================== 게시판 글 상세 ==============================
	 */

	/*
	 * ============================== 게시판 글수정 ==============================
	 */
	
	
	/*
	 * ============================== 게시판 글삭제 ==============================
	 */
	
	
}













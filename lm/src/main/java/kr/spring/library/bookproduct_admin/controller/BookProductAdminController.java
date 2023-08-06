package kr.spring.library.bookproduct_admin.controller;

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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kr.spring.library.bookproduct_admin.service.BookProductAdminService;
import kr.spring.library.bookproduct_admin.vo.BookProductAdminVO;
import kr.spring.library.product.vo.BookProductVO;
import kr.spring.util.PagingUtil;
import kr.spring.util.StringUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class BookProductAdminController {
	
	/*=================================
	 * 자바빈(VO) 초기화 
	 *=================================*/
	@Autowired
	private BookProductAdminService bookProuctAdminService;
	
	/*=================================
	 * 도서관리 - 도서목록
	 *=================================*/
	@RequestMapping("/library/bookproductadmin/admin_booklist.do")
	public ModelAndView getList(
			@RequestParam(value="pageNum",
			defaultValue="1") int currentPage,
			String keyfield,String keyword) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("keyfield", keyfield);
		map.put("keyword", keyword);

		//전체/검색 레코드수
		int count = bookProuctAdminService.selectRowCount(map);

		log.debug("<<count>> : " + count);

		//페이지 처리
		PagingUtil page = 
				new PagingUtil(keyfield,keyword,currentPage,
						count,20,10,"admin_booklist.do");

		List<BookProductAdminVO> list = null;
		if(count > 0) {
			map.put("start", page.getStartRow());
			map.put("end", page.getEndRow());

			list = bookProuctAdminService.selectBookProductList(map);
		}

		ModelAndView mav = new ModelAndView();
		mav.setViewName("admin_bookProductList");
		mav.addObject("count", count);
		mav.addObject("list", list);
		mav.addObject("page", page.getPage());

		return mav;
	}
	
	/*=================================
	 * 도서관리 - 대출목록
	 *=================================*/
	@RequestMapping("/library/bookproductadmin/admin_bookloanlist.do")
	public ModelAndView getloanList(
			@RequestParam(value="pageNum",
			defaultValue="1") int currentPage,
			String keyfield,String keyword) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("keyfield", keyfield);
		map.put("keyword", keyword);

		//전체/검색 레코드수
		int count = bookProuctAdminService.selectRowCount(map);

		log.debug("<<count>> : " + count);

		//페이지 처리
		PagingUtil page = 
				new PagingUtil(keyfield,keyword,currentPage,
						count,20,10,"admin_bookloanlist.do");

		List<BookProductAdminVO> list = null;
		if(count > 0) {
			map.put("start", page.getStartRow());
			map.put("end", page.getEndRow());

			list = bookProuctAdminService.selectLoanList(map);
		}

		ModelAndView mav = new ModelAndView();
		mav.setViewName("admin_bookProductLoanList");
		mav.addObject("count", count);
		mav.addObject("list", list);
		mav.addObject("page", page.getPage());

		return mav;
	}
	
	/*=================================
	 * 도서관리 - 신규도서등록
	 *=================================*/
	// 등록 폼
	@GetMapping("/library/bookproductadmin/admin_write.do")
	public String form() {
		return "admin_bookProductWrite";
	}
	
	// 도서 테이블에 데이터 삽입
	@PostMapping("/library/bookproductadmin/admin_write.do")
	public String submit(@Valid BookProductAdminVO bookProductAdminVO,
			BindingResult result, HttpServletRequest request, 
			HttpSession session, Model model) {

		log.debug("<< 신규도서등록 >> : " + bookProductAdminVO);
		// 유효성 체크결과 오류가 있으면 폼 호출
		if (result.hasErrors()) {
			return form();
		}
		// 글쓰기
		bookProuctAdminService.insertBookProduct(bookProductAdminVO);

		model.addAttribute("message", "신규도서 등록이 완료되었습니다.");
		model.addAttribute("url", request.getContextPath() + "/library/bookproductadmin/admin_booklist.do");

		return "common/resultView";
	}

	/*=================================
	 * 도서관리 - 도서 상세
	 *=================================*/
	@RequestMapping("/library/bookproductadmin/admin_detail.do")
	public ModelAndView getDetail(@RequestParam String callNumber) {
		log.debug("<<도서상세 - callNumber>> : " + callNumber);
		
		// 글상세
		BookProductAdminVO bookProductAdmin = bookProuctAdminService.selectDetailBookProduct(callNumber);

		// 제목에 태그를 허용하지 않음
		bookProductAdmin.setLib_product_bookName(StringUtil.useNoHtml(bookProductAdmin.getLib_product_bookName()));

		// CKEditor를 사용하지 않을 경우 내용에 태그 불허
		// board.setContent(StringUtil.useBrNoHtml(board.getContent()));
		// 뷰 이름 속성명 속성값
		return new ModelAndView("admin_bookProductView", "bookProductAdmin", bookProductAdmin);
	}
	/*=================================
	 * 도서관리 - 도서 수정
	 *=================================*/
	// 수정 폼 호출
	@GetMapping("/library/bookproductadmin/admin_update.do")
	public String formUpdate(@RequestParam String callNumber, Model model) {

		BookProductAdminVO bookProductAdminVO = bookProuctAdminService.selectDetailBookProduct(callNumber);
		model.addAttribute("bookProductAdminVO", bookProductAdminVO);

		return "admin_bookProductModify";
	}

	// 전송된 데이터 처리
	@PostMapping("/library/bookproductadmin/admin_update.do")
	public String submitUpdate(@Valid BookProductAdminVO bookProductAdminVO, 
			BindingResult result, HttpServletRequest request, Model model) {
		log.debug("<<도서정보 수정 - BookProductAdminVO>> : " + bookProductAdminVO);

		// 유효성 체크 결과 오류가 있으면 폼 호출
		if (result.hasErrors()) {
			return "admin_bookProductModify";
		}

		//글 수정
		bookProuctAdminService.updateBookProduct(bookProductAdminVO);

		//View에 표시할 메시지
		model.addAttribute("message","도서 정보 수정 완료!");
		model.addAttribute("url", 
				request.getContextPath() 
				+ "/library/bookproductadmin/admin_detail.do?callNumber="
				+bookProductAdminVO.getCallNumber());

		return "common/resultView";
	}

	/*=================================
	 * 도서관리 - 도서 삭제
	 *=================================*/
	@RequestMapping("/library/bookproductadmin/admin_delete.do")
	public String submitDelete(@RequestParam String callNumber) {
		log.debug("<<도서삭제 - callNumber>> : " + callNumber);
		
		//글삭제
		bookProuctAdminService.deleteBookProduct(callNumber);
		
		return "redirect:/library/bookproductadmin/admin_booklist.do";
	}
	
	
	
	
/*
	/*=================================
	 * 회원관한변경 - 관리자
	 *=================================
	//수정 폼
	@GetMapping("/library/bookproductadmin/admin_bookupdate.do")
	public String form(@RequestParam String callNumber,Model model) {
		BookProductVO bookProudctVO = bookProuctAdminService.selectMember(mem_num);

		model.addAttribute("bookProudctVO", bookProudctVO);

		return "admin_bookProductModify";
	}
	//전송된 데이터 처리
	@PostMapping("/library/bookproductadmin/admin_bookupdate.do")
	public String submit(BookProductVO bookProudctVO, Model model,
			HttpServletRequest request) {
		log.debug("<<관리자 대출상태 변경>> : " + bookProudctVO);

		//회원권한 수정
		bookProuctAdminService.updateByBookProductAdmin(bookProudctVO);

		//View에 표시할 메시지
		model.addAttribute("message", "회원권한 수정 완료");
		model.addAttribute("url", 
				request.getContextPath()
				+"/library/bookproductadmin/admin_bookupdate.do?mem_num="+BookProductVO.getMem_num());

		return "common/resultView";
	}
*/
}

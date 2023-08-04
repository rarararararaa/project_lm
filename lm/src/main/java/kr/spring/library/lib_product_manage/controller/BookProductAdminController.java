package kr.spring.library.lib_product_manage.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kr.spring.library.lib_product_manage.service.BookProductAdminService;
import kr.spring.library.product.vo.BookProductVO;
import kr.spring.util.PagingUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class BookProductAdminController {
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

		List<BookProductVO> list = null;
		if(count > 0) {
			map.put("start", page.getStartRow());
			map.put("end", page.getEndRow());

			list = bookProuctAdminService.selectList(map);
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

		List<BookProductVO> list = null;
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

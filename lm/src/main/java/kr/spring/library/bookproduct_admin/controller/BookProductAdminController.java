package kr.spring.library.bookproduct_admin.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kr.spring.library.bookproduct_admin.service.BookProductAdminService;
import kr.spring.library.product.BookProductService;
import kr.spring.library.product.vo.BookProductVO;
import kr.spring.library.rent.vo.RentVO;
import kr.spring.member.vo.MemberVO;
import kr.spring.util.PagingUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class BookProductAdminController {

	/*=================================
	 * 자바빈(VO) 초기화 
	 *=================================*/
	@Autowired
	private BookProductAdminService bookProuctAdminService;
	@Autowired
	private BookProductService bookProductService;

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
						count,15,10,"admin_booklist.do");

		List<BookProductVO> list = null;
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
	 * 도서관리 - 도서상세페이지
	 *=================================*/
	//도서 상세
	@GetMapping("/library/lib_book/admin_bookDetail.do")
	public String cartForm(@RequestParam String callNumber, Model model, HttpSession session) {
		BookProductVO book = bookProductService.selectDetailLIB_P(callNumber);
		log.debug("<<도서 상세>> : "+book);
		//대출 도서 리스트
		List<BookProductVO> list = bookProductService.selectListLIB_P(book.getLib_product_isbn());
		//도서분류
		for(BookProductVO vo : list) {
			RentVO rent = bookProductService.selectDate(vo.getCallNumber());
			if(rent != null) {
				vo.setReturn_reg_deadline(rent.getReturn_reg_deadline());
			}
		}
		String class_name = getClassName(book.getLib_product_class_no());

		model.addAttribute("book", book);
		model.addAttribute("className", class_name);
		model.addAttribute("list", list);

		log.debug("<<도서 상세>> : "+list);

		return "admin_bookDetail";
	}
	//도서 분류
	public String getClassName(int class_no) {
		String class_name = "";
		if(class_no == 0 ) class_name ="총류";
		if(class_no == 1 ) class_name ="철학";
		if(class_no == 2 ) class_name ="종교";
		if(class_no == 3 ) class_name ="사회과학";
		if(class_no == 4 ) class_name ="자연과학";
		if(class_no == 5 ) class_name ="기술과학";
		if(class_no == 6 ) class_name ="예술";
		if(class_no == 7 ) class_name ="언어";
		if(class_no == 8 ) class_name ="문학";
		if(class_no == 9 ) class_name ="역사";
		return class_name;
	}
}

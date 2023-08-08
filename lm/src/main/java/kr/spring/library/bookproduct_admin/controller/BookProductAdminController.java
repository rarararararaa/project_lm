package kr.spring.library.bookproduct_admin.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kr.spring.library.bookproduct_admin.service.BookProductAdminService;
import kr.spring.library.product.vo.BookProductVO;
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

			list = bookProuctAdminService.selectBookProductList(map);
		}

		ModelAndView mav = new ModelAndView();
		mav.setViewName("admin_bookProductList");
		mav.addObject("count", count);
		mav.addObject("list", list);
		mav.addObject("page", page.getPage());

		return mav;
	}
}

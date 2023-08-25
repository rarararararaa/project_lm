package kr.spring.bookstore.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.spring.bookstore.event.service.BsEventService;
import kr.spring.bookstore.event.vo.BsEventVO;
import kr.spring.bookstore.product.service.ProductService;
import kr.spring.bookstore.product.vo.ProductVO;
import kr.spring.bookstore.search.service.SearchService;
import kr.spring.util.PagingUtil;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class bookstoreMainController {
	@Autowired
	private BsEventService bsEventService;
	@Autowired
	private ProductService productService;
	@Autowired
	private SearchService searchService;
	@RequestMapping("/")
	public String getbsMain() {
		return "redirect:/library/template/testpage.do";
	}
	
	
	@RequestMapping("/bookstore/template/bsMain.do")
	public String main(Model model) {
		//인기 도서 읽어오기
		List<ProductVO> product = productService.selectBestBook();
		log.debug("<<메인 페이지 - 베스트 도서>> : "+product);
		//이벤트 정보 가져오기
		//status가 1보다 큰 이벤트만 조회 | 1:미표시, 2:진행중,  3:종료
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("order", 2);
		
		//전체/검색 레코드 수
		int count = bsEventService.selectEventCount(map);
		//페이지 처리
		PagingUtil page = new PagingUtil(null, null, 1, count, 20, 10, "list.do");
				
		List<BsEventVO> list = null;
		if(count > 0) {
			map.put("start", page.getStartRow());
			map.put("end", page.getEndRow());
			
			list = bsEventService.selectEventList(map);
		}
		
		//신간 도서 읽어오기
		List<ProductVO> newBook = productService.selectNewBook();
		log.debug("<<메인 페이지 - 신간>> : "+newBook);
		//출판 예쩡 도서 읽어오기
		List<ProductVO> FutureBook = productService.selectFuture();
		log.debug("<<메인 페이지 - 출판예정>> : "+FutureBook);
		log.debug("<<메인 페이지 - 이벤트>> : "+list);
		model.addAttribute("list", list);
		model.addAttribute("count", count);
		model.addAttribute("best", product);
		model.addAttribute("newBook", newBook);
		model.addAttribute("futureBook", FutureBook);

		return "bsMain";//타일스 설정의 식별자
	}

	@RequestMapping("/bookstore/search/searchMain.do")
	public String searchMain(@RequestParam int categoryNum, 
			@RequestParam int orderByNum,
			@RequestParam(value="keyword", defaultValue="") String keyword, Model model) {
		Map<String,Object> map = new HashMap<>();
		//categoryNum=1&orderByNum=1&keyword=ㄹㄹ
		map.put("categoryNum", categoryNum);
		map.put("orderByNum", orderByNum);
		map.put("keyword", keyword);
		int totalCount = searchService.searchProductAllCount(map); //고치기...
		model.addAttribute("categoryNum",categoryNum);
		model.addAttribute("orderByNum",orderByNum);
		model.addAttribute("keyword",keyword);
		model.addAttribute("totalCount",totalCount);
		return "/bookstore/search/searchMain";
	}

}

package kr.spring.bookstore.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.spring.bookstore.event.service.BsEventService;
import kr.spring.bookstore.event.vo.BsEventVO;
import kr.spring.bookstore.product.service.ProductService;
import kr.spring.bookstore.product.vo.ProductVO;
import kr.spring.util.PagingUtil;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class bookstoreMainController {
	@Autowired
	private BsEventService bsEventService;
	@Autowired
	private ProductService productService;

	@RequestMapping("/")
	public String getbsMain() {
		return "redirect:/library/template/testpage.do";
	}
	
	
	@RequestMapping("/bookstore/template/bsMain.do")
	public String main(Model model) {
		//최신 등록 상품 읽어오기
		List<ProductVO> product = productService.selectBestBook();
		log.debug("<<메인 페이지 - 베스트 도서>> : "+product);
		//이벤트 정보 가져오기
		//status가 1보다 큰 이벤트만 조회 | 1:미표시, 2:진행중,  3:종료
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("event_board_status", 2);
		
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
		log.debug("<<메인 페이지 - 이벤트>> : "+list);
		model.addAttribute("list", list);
		model.addAttribute("count", count);
		model.addAttribute("best", product);

		return "bsMain";//타일스 설정의 식별자
	}


}

package kr.spring.library.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class LIB_BookProductController {
	@Autowired
	private BookProductService bookProductService;
	
	@RequestMapping("/lm/bookproduct.do")
	public void insertBook() {
		bookProductService.getData();
		
		log.debug("<<도서 API 등록>> : ");
	}
}

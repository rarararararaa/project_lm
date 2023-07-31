package kr.spring.bookstore.product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.spring.bookstore.product.service.ProductService;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j 
public class ProductController {
	@Autowired
	private ProductService productService;
	
	@RequestMapping("/lm/product.do")
	public void insertBook() {
		productService.fetchDataFromApi();
		
		log.debug("<<도서 API 등록>> : ");
	}
}

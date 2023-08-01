package kr.spring.bookstore.product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kr.spring.bookstore.product.service.ProductService;
import kr.spring.bookstore.product.service.ProductServiceapi;
import kr.spring.bookstore.product.vo.ProductVO;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j 
public class ProductController {
	@Autowired
	private ProductServiceapi productServiceapi;
	@Autowired
	private ProductService productService;
	
	
	@RequestMapping("/lm/product.do")
	public void insertBook() {
		productServiceapi.fetchData1FromApi();
	}
	
	@RequestMapping("/lm/productDetail.do")
	public ModelAndView getDetail(@RequestParam(value="store_product_isbn13", required=false) String store_product_isbn13) {
		log.debug("<<글상세 - board_num>> : " + store_product_isbn13);
		
		//글상세 
		ProductVO product= productService.selectProduct(store_product_isbn13);
		
		return new ModelAndView("productDetail","product",product);
	}
}

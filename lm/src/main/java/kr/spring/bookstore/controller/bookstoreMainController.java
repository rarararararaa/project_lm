package kr.spring.bookstore.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.spring.bookstore.product.service.ProductService;
import kr.spring.bookstore.product.vo.ProductVO;

@Controller
public class bookstoreMainController {

	@Autowired
	private ProductService productService;

	@RequestMapping("/")
	public String getbsMain() {
		return "redirect:/library/template/testpage.do";
	}
	
	
	@RequestMapping("/bookstore/template/bsMain.do")
	public String main(Model model) {
		//최신 등록 상품 읽어오기
		Map<String, Object> mapProduct = new HashMap<String, Object>();
		mapProduct.put("start", 1);
		mapProduct.put("end", 12);
		mapProduct.put("status",1);//표시 상품만 처리 (어떤 카테고리인지 이걸로 구분할수 있을듯)
		List<ProductVO> product_list = productService.selectList(mapProduct);

		model.addAttribute("product_list",product_list);

		return "bsMain";//타일스 설정의 식별자
	}


}

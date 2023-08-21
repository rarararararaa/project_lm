package kr.spring.bookstore.product.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.spring.bookstore.product.service.ProductService;
import kr.spring.bookstore.product.vo.ProductVO;
import kr.spring.util.PagingUtil;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class ProductViewController {
	@Autowired
	ProductService 	productService; 
	
	@RequestMapping("/bookstore/product/productCeteList.do")
	public String listProduct(HttpSession session, Model model, 
			@RequestParam(value="pageNum",defaultValue="1") int currentPage,
			@RequestParam(value="order",defaultValue="1") int order,
			@RequestParam(defaultValue="국내도서")String cate,
			@RequestParam(defaultValue="소설")String detail) {//1:국내도서
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("store_product_searchcategoryName", cate);
		map.put("store_product_categoryname", detail);
		int count = productService.selectCategoryCount(map);
		PagingUtil page = 
				new PagingUtil(null,null,
						currentPage,count,10,10,
						"/bookstore/product/productCeteList.do","&order="+order);
		map.put("start", page.getStartRow());
		map.put("end", page.getEndRow());
		List<ProductVO> list = productService.selectCategoryBook(map);
		String[] category = category();
		model.addAttribute("category", category);
		model.addAttribute("list", list);
		model.addAttribute("page", page.getPage());
		model.addAttribute("count", count);
		model.addAttribute("categoryTitle", detail);
		return "productListAll";
	}

	public String[] category(){
		String[] list = {"소설/시/희곡","인문학","컴퓨터","소년","종교/역학","자기계발","유아",
		                 "외국어","예술/대중문화","역사", "에세이","어린이",
		                 "수험서/자격증","잡지","사회과학","만화","대학교재",
		                 "달력/기타", "과학","참고서","경제경영","건강/취미"};
		return list;
	}
}

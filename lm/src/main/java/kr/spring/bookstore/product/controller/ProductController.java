package kr.spring.bookstore.product.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kr.spring.bookstore.product.service.ProductService;
import kr.spring.bookstore.product.service.ProductServiceapi;
import kr.spring.bookstore.product.vo.ProductVO;
import kr.spring.util.PagingUtil;
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

	/*==========================
	 * 상품 리스트
	 *==========================*/
	@RequestMapping("/lm/productList.do")
	public ModelAndView getList(@RequestParam(value="pageNum",defaultValue="1") int currentPage,
								@RequestParam(value="order",defaultValue="1") int order,
								String keyfield,String keyword,@RequestParam int store_product_searchcategoryid){
		
		log.debug("category : "+store_product_searchcategoryid);
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("keyfield", keyfield);
		map.put("keyword", keyword);
		map.put("store_product_searchcategoryid", store_product_searchcategoryid);
		
		//전체/검색 레코드수
		int count = productService.selectRowCount(map);
		
		log.debug("<<count>> : " + count);
		
		//페이지 처리
		PagingUtil page = 
				new PagingUtil(keyfield,keyword,
						currentPage,count,20,10,
						"productList.do","&order="+order);
		
		List<ProductVO> product= null;
		if(count > 0) {
			map.put("order",order);
			map.put("start", page.getStartRow());
			map.put("end", page.getEndRow());
			
			product = productService.selectList(map);			
		}
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("productList");
		mav.addObject("count", count);
		mav.addObject("product", product);
		mav.addObject("page", page.getPage());
		
		return mav;
	}
	
	/*==========================
	 * 상품 상세
	 *==========================*/	
	@RequestMapping("/lm/productDetail.do")
	public ModelAndView getDetail(@RequestParam(value="store_product_isbn13", required=false) String store_product_isbn13) {
		log.debug("<<글상세 - isbn13>> : " + store_product_isbn13);
		
		//상품 상세 
		ProductVO product= productService.selectProduct(store_product_isbn13);
		log.debug("<<product>> : " + product);
		
		
		return new ModelAndView("productDetail","product",product);
	}
	
	
}

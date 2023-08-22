package kr.spring.bookstore.product.controller;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

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
			@RequestParam(defaultValue="4") int order,
			@RequestParam(defaultValue="국내도서")String cate, //1:국내도서
			@RequestParam(defaultValue="소설")String detail,
			@RequestParam(defaultValue="10") int count_num) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("store_product_searchcategoryName", cate);
		map.put("store_product_categoryname", detail);
		int count = productService.selectCategoryCount(map);
		PagingUtil page = 
				new PagingUtil(null,null,
						currentPage,count,count_num,10,
						"/bookstore/product/productCeteList.do","&detail="+detail+"&order="+order+"&count_num="+count_num+"&cate="+cate);
		map.put("start", page.getStartRow());
		map.put("end", page.getEndRow());
		map.put("order", order);
		List<ProductVO> list = productService.selectCategoryBook(map);
		String[] category = category();
		model.addAttribute("category", category);
		model.addAttribute("list", list);
		model.addAttribute("page", page.getPage());
		model.addAttribute("count", count);
		model.addAttribute("categoryTitle", detail);
		model.addAttribute("order", order);
		model.addAttribute("count_num", count_num);
		model.addAttribute("cate", cate);
		
		LocalDate now = LocalDate.now();
		model.addAttribute("now", now); 
		return "productListAll";
	}

	public String[] category(){
		String[] list = {"소설/시/희곡","인문학","컴퓨터","종교/역학","예술/대중문화","자기계발","외국어",
		                 "역사","경제경영","사회과학","과학","유아","어린이","소년",
		                 "수험서/자격증","참고서","만화","대학교재",
		                 "달력/기타","잡지","에세이","건강/취미"};
		return list;
	}
	@RequestMapping("/bookstore/product/productBestList.do")
	public ModelAndView BestListProduct() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("productListBest");
		List<ProductVO> best = productService.selectBestBookList(0,1);
		for(ProductVO vo : best) {
			if(vo.getStore_product_title().length()>=10) {
				String title = vo.getStore_product_title().substring(0, 10)+"...";
				vo.setStore_product_title(title);
			}
		}
		List<ProductVO> best_y = productService.selectBestBookList(1,12);
		for(ProductVO vo : best_y) {
			if(vo.getStore_product_title().length()>=10) {
				String title = vo.getStore_product_title().substring(0, 10)+"...";
				log.debug("<<자름>> : "+title);
				vo.setStore_product_title(title);
			}
		}
		
		mav.addObject("bestList", best);
		mav.addObject("bestYear", best_y);
		log.debug("<<베스트 셀러>> : "+best);
		return mav;
	}
}

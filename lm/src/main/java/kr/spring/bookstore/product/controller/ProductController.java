package kr.spring.bookstore.product.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.spring.bookstore.product.service.ProductService;
import kr.spring.bookstore.product.service.ProductServiceapi;
import kr.spring.bookstore.product.vo.ProductFavVO;
import kr.spring.bookstore.product.vo.ProductVO;
import kr.spring.bookstore.review.service.ReviewService;
import kr.spring.bookstore.review.vo.ReviewVO;
import kr.spring.member.vo.MemberVO;
import kr.spring.util.FileUtil;
import kr.spring.util.PagingUtil;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j 
public class ProductController {
	@Autowired
	private ProductServiceapi productServiceapi;
	@Autowired
	private ProductService productService;
	@Autowired
	private ReviewService reviewService;
	
	
	@RequestMapping("/lm/product.do")
	public void insertBook() {
		productServiceapi.fetchData1FromApi();
	}

	/*==========================
	 * 상품 리스트
	 *==========================*/
	@RequestMapping("/bookstore/product/productList.do")
	public ModelAndView getList(@RequestParam(value="pageNum",defaultValue="1") int currentPage,
								@RequestParam(value="order",defaultValue="1") int order,
								String keyfield,String keyword){
		
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("keyfield", keyfield);
		map.put("keyword", keyword);
		
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
	@GetMapping("/bookstore/product/productDetail.do")
	public ModelAndView getDetail(@RequestParam(value="store_product_isbn13", required=false) String store_product_isbn13,
			@RequestParam(value="pageNum",defaultValue="1") int currentPage,
			@RequestParam(value="order",defaultValue="1") int order) {
		log.debug("<<글상세 - isbn13>> : " + store_product_isbn13);
		
		//상품 상세 
		ProductVO product= productService.selectProduct(store_product_isbn13);
		log.debug("<<product>> : " + product);
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("store_product_num", product.getStore_product_num());
		
		//전체/검색 레코드수
		int count = reviewService.selectReviewCount(map);
		
		log.debug("<<count>> : " + count);
		
		//페이지 처리
		PagingUtil page = 
		new PagingUtil(null,null,
			currentPage,count,20,10,
			"productDetail.do","&order="+order);
		
		List<ReviewVO> review= null;
		if(count > 0) {
			map.put("order",order);
			map.put("start", page.getStartRow());
			map.put("end", page.getEndRow());
			
			review = reviewService.selectReviewList(map);			
		}
		ModelAndView mav = new ModelAndView();
		mav.setViewName("productDetail");
		mav.addObject("product", product);
		mav.addObject("count", count);
		mav.addObject("review", review);
		mav.addObject("page", page.getPage());
		log.debug("<<mav>> : "+mav);
		return mav;
	}
	
	//이미지 출력
	@RequestMapping("/bookstore/product/imageView.do")
	public String viewImage(@RequestParam int review_num, HttpServletRequest request, Model model) {
		ReviewVO reviewVO = reviewService.selectReview(review_num);
		
		log.debug("<<reviewVO>>" + reviewVO);
		ModelAndView mav = new ModelAndView();
		viewReview(reviewVO, request, model);
		return "imageView";
	}

	//프로필 사진 처리를 위한 공통 코드
	public void viewReview(ReviewVO reviewVO,
			                HttpServletRequest request,
			                Model model) {
		if(reviewVO==null || reviewVO.getReview_image()==null) {
			//기본 이미지 읽기
			byte[] readbyte = null;
			model.addAttribute("imageFile", null);
			model.addAttribute("filename", " ");
		}else {//업로드한 프로필 사진이 있는 경우
			model.addAttribute("imageFile", reviewVO.getReview_image());
			model.addAttribute("filename", "review_image.jpg");
		}
	}	
	/*==========================
	 * 상품 찜하기
	 *==========================*/
	//상품 찜 읽기
	@RequestMapping("/bookstore/product/getZzim.do")
	@ResponseBody
	public Map<String,Object> getFav(ProductFavVO fav, HttpSession session){
		log.debug("<<게시판 좋아요 읽기 - ProductFavVO>> : " + fav);
		
		Map<String,Object> mapJson = new HashMap<String,Object>();
		
		int user=0;
		if(session.getAttribute("mem_num")!=null) {
			user = (Integer)session.getAttribute("mem_num");
		}
		if(user==0) {//로그인이 되지 않은 상태
			mapJson.put("status", "noFav");
		}else {
			//로그인된 회원번호 셋팅
			fav.setMem_num(user);
			
			ProductFavVO productFav = productService.selectFav(fav);
			if(productFav!=null) {
				mapJson.put("status", "yesFav");
			}else {
				mapJson.put("status","noFav");
			}			
		}
		log.debug("<<게시판 좋아요 읽기 - status>> : " + mapJson.get("status"));
		mapJson.put("count", productService.selectFavCount(
				                   fav.getStore_product_num()));
		return mapJson;
	}
	
	//부모글 좋아요 등록/삭제
	@RequestMapping("/bookstore/product/writeZzim.do")
	@ResponseBody
	public Map<String,Object> writeFav(ProductFavVO fav,HttpSession session,@RequestParam int store_product_num){
		log.debug("<<게시판 좋아요 등록/삭제 - ProductFavVO>> : " + fav);
		
		Map<String,Object> mapJson = new HashMap<String,Object>();
		
		int user=0;
		if(session.getAttribute("mem_num")!=null) {
			user = (Integer)session.getAttribute("mem_num");
		}
		if(user==0) {
			mapJson.put("result", "logout");
		}else {
			//로그인된 회원번호 셋팅
			fav.setMem_num(user);
			fav.setStore_product_num(store_product_num);
			
			ProductFavVO productFav = productService.selectFav(fav);
			if(productFav!=null) {//등록한 좋아요가 있으면 삭제
				productService.deleteFav(productFav.getZzim_num());
				
				mapJson.put("result", "success");
				mapJson.put("status", "noFav");
			}else {//등록한 좋아요가 없으면 등록
				productService.insertFav(fav);
				
				mapJson.put("result", "success");
				mapJson.put("status", "yesFav");
			}
			mapJson.put("count", productService.selectFavCount(
					                          fav.getStore_product_num()));
		}
		return mapJson;
	}
		
	
	
	
}



















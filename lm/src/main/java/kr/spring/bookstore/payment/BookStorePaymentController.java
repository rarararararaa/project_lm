package kr.spring.bookstore.payment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.spring.bookstore.payment.vo.BookStorePaymentCartVO;
import kr.spring.bookstore.product.vo.ProductVO;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class BookStorePaymentController {
	
	public static final String IMPORT_TOKEN_URL = "https://api.iamport.kr/users/getToken";
	public static final String IMPORT_CANCEL_URL = "https://api.iamport.kr/payments/cancel";
	public static final String KEY = "";  
	public static final String SECRET = "";
	
	@Autowired
	BookStorePaymentService bookStorePaymentService;
	@PostMapping("/bookstore/payment/cart.do")
	@ResponseBody
	public Map<String,String> insertcartForm(HttpSession session, Model model, 
			@RequestParam(value = "order_quantity")int quantity, @RequestParam(value = "product_num")int product_num
			,@RequestParam(value = "store_product_status")int store_product_status) {
		int mem_num = (Integer)session.getAttribute("mem_num");
		BookStorePaymentCartVO vo = new BookStorePaymentCartVO();
		vo.setMem_num(mem_num);
		vo.setCart_quantity(quantity);
		vo.setStore_product_num(product_num);
		log.debug("<<cartInsert>> : "+vo);
		bookStorePaymentService.insertCart(vo);
		
		Map<String, String> mapJson = new HashMap<String, String>();
		mapJson.put("result", "success");
		
		return mapJson;
	}
	//장바구니  
	@GetMapping("/bookstore/payment/cart.do")
	public String cartForm(HttpSession session, Model model) {
		int mem_num = (Integer)session.getAttribute("mem_num");
		int grade = (Integer)session.getAttribute("mem_grade");
		double point = 0.01;
		List<BookStorePaymentCartVO> list = bookStorePaymentService.selectCartList(mem_num);
		List<ProductVO> book_list = new ArrayList<ProductVO>();
		for(BookStorePaymentCartVO vo : list) {
			ProductVO product = bookStorePaymentService.selectDetailBook(vo.getStore_product_num());
			log.debug("<<도서 상세 정보>> : "+product);
			book_list.add(bookStorePaymentService.selectDetailBook(vo.getStore_product_num()));
		}
		log.debug("<<cart>> : "+list);
		log.debug("<<cart_book>> : "+book_list);
		model.addAttribute("list", list);
		model.addAttribute("book_list", book_list);
		point = getPoint(grade);
		model.addAttribute("point", point);
		log.debug("<<grade>>"+point);
		return "cart";
	}
	//멤버 등급에 따른 포인드 % 가져오기
	public double getPoint(int grade) {
		double point = 0.01;
		if(grade == 1 ) point = 0.1;
		else if(grade == 2 ) point = 0.15;
		else if(grade == 3 ) point = 0.2;
		else if(grade == 4 ) point = 0.3;
		
		return point;
	}

}

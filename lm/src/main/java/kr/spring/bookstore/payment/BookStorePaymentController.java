package kr.spring.bookstore.payment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.spring.bookstore.payment.vo.BookStorePaymentCartVO;
import kr.spring.bookstore.product.vo.ProductVO;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

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
	public Map<String,String> insertcartForm(HttpSession session, Model model, ProductVO product) {
		int mem_num = (Integer)session.getAttribute("mem_num");
		
		List<BookStorePaymentCartVO> cart_db = bookStorePaymentService.selectCartList(mem_num);
		boolean check = false;
		int cart_quantity = 0;
		for(BookStorePaymentCartVO vo : cart_db) {
			log.debug("<<quantity>> : "+vo.getCart_quantity());
			log.debug("<<product_q>> : "+product.getCart_quantity());
			if(vo.getStore_product_num() == product.getStore_product_num()) {
				check = true;
				cart_quantity = vo.getCart_quantity();
				break;
			}
		}
		//중복 책 검사
		if(check) {
			int total = cart_quantity + product.getCart_quantity();
			bookStorePaymentService.updateBookQuantity(total, product.getStore_product_num(),mem_num);
		}else {
			BookStorePaymentCartVO vo = new BookStorePaymentCartVO();
			vo.setMem_num(mem_num);
			vo.setCart_quantity(product.getCart_quantity());
			vo.setStore_product_num(product.getStore_product_num());
			log.debug("<<cartInsert>> : "+vo);
			bookStorePaymentService.insertCart(vo);
		}
		
		Map<String, String> mapJson = new HashMap<String, String>();
		mapJson.put("result", "success");
		
		return mapJson;
	}
	//======================장바구니FORM===========================//  
	@GetMapping("/bookstore/payment/cart.do")
	public String cartForm(HttpSession session, Model model, HttpServletRequest request) {
		String mem_id = (String)session.getAttribute("mem_id");
		log.debug("<<로그인 체크>> : "+mem_id);
		if(mem_id == null) {
			return "redirect:/lm/login/template/loginMain.do?lo=1";
		}
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
		model.addAttribute("point", point);
		log.debug("<<grade>>"+point);
		return "cart";
	}
	//책 삭제
	@RequestMapping("/bookstore/payment/Cartdelete.do")
	@ResponseBody
	public Map<String, Object> deleteCartBook(int store_product_num, HttpSession session){
		Map<String, Object> mapJson = new HashMap<String, Object>();
		String mem_id = (String)session.getAttribute("mem_id");
		log.debug("<<로그인 체크>> : "+mem_id);
		if(mem_id == null) {
			mapJson.put("result", "logout");
		}
		int mem_num = (Integer)session.getAttribute("mem_num");
		bookStorePaymentService.deleteCart(store_product_num, mem_num);
		mapJson.put("result", "success");
		return mapJson;
	}
	//======================장바구니 > 결제 페이지===========================//  
	@PostMapping("/bookstore/payment/cartAction.do")
	@ResponseBody 
	public Map<String, Object> actionCart(@RequestParam Map<String, Object> data, HttpSession session, int total){
		log.debug("<<total>>"+total);
		int mem_num = (Integer)session.getAttribute("mem_num");
		String mem_id = (String)session.getAttribute("mem_id");
		Map<String, Object> mapJson = new HashMap<String, Object>();
		if(mem_id == null) {
			mapJson.put("result", "logout");
		}
		log.debug("<<ACTION!>> : "+data.get("data"));
		String test = (String)data.get("data");
		try {
			
			JSONArray array = JSONArray.fromObject(test);
			List<Map<String, String>> list = new ArrayList<Map<String,String>>();
			for(int i=0;i<array.size();i++) {
				JSONObject obj = (JSONObject)array.get(i);
				Map<String, String> re = new HashMap<String, String>();
				
				re.put("cart_quantity",(String)obj.get("cart_quantity"));
				re.put("store_product_num", (String)obj.get("store_product_num"));
				re.put("store_product_pricestandard", (String)obj.get("store_product_pricestandard"));
				
				list.add(re);
			}
			log.debug("<<fsjkdlkfjkals>> : "+list);
			List<BookStorePaymentCartVO> cartList = new ArrayList<BookStorePaymentCartVO>();
			
			for(Map<String, String> map : list) {
				BookStorePaymentCartVO vo = new BookStorePaymentCartVO();
				vo.setCart_quantity(Integer.parseInt(map.get("cart_quantity")));
				vo.setStore_product_num(Integer.parseInt(map.get("store_product_num")));
				vo.setMem_num(mem_num);
				cartList.add(vo);
				log.debug("<<tlqkf>> : "+vo.getCart_quantity());
				log.debug("<<orderList>> : "+cartList);
				bookStorePaymentService.updateCart(vo);
			}
			mapJson.put("result", "success");
			orderForm(cartList, total);
			//mapJson.put("cartList", cartList);
			
			//log.debug("<<되냐???????>>"+list);
			
			//List<ProductVO> list = data.get("data"); 
		//log.debug("<<ACTION-list>> : "+list);
				
		} catch (Exception e) {  
			e.printStackTrace(); 
		}
		
		return mapJson;
	}
	
	
	@RequestMapping("/bookstore/payment/order.do")
	public String orderForm(@RequestParam List<BookStorePaymentCartVO> cart, int total) {
		log.debug("<<됐으면 좋겠다>>"+cart);
		
		return "order";
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

package kr.spring.bookstore.payment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.spring.bookstore.payment.service.BookStorePaymentOrderService;
import kr.spring.bookstore.payment.service.BookStorePaymentService;
import kr.spring.bookstore.payment.vo.BookStorePaymentCartVO;
import kr.spring.bookstore.payment.vo.BookStorePaymentOrderVO;
import kr.spring.bookstore.product.vo.ProductVO;
import kr.spring.member.service.MemberService;
import kr.spring.member.vo.MemberVO;
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
	private BookStorePaymentService bookStorePaymentService;
	@Autowired
	private BookStorePaymentOrderService bookStorePaymentOrderService; 
	@Autowired
	private MemberService memberService;
	
	//초기화
	@ModelAttribute
	public MemberVO initCommand() {
		return new MemberVO();
	}
	
	//API 책 추가
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
		log.debug("<<세션 확인 >> : "+session.getAttribute("cartList"));
		String mem_id = (String)session.getAttribute("mem_id");
		if(mem_id == null) {
			return "redirect:/lm/login/template/loginMain.do?lo=1";
		}
		int mem_num = (Integer)session.getAttribute("mem_num");
		int grade = (Integer)session.getAttribute("mem_grade");
		
		double point = getPoint(grade);
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
				int price = Integer.parseInt(map.get("cart_quantity"))*Integer.parseInt(map.get("store_product_pricestandard").substring(0, map.get("store_product_pricestandard").length()-1));
				vo.setOrder_product_price(price);
				vo.setMem_num(mem_num);
				cartList.add(vo);
				log.debug("<<orderList>> : "+cartList);
				bookStorePaymentService.updateCart(vo);
			}
			mapJson.put("result", "success");
			session.setAttribute("cartList", cartList);
			session.setAttribute("total", total);
		} catch (Exception e) {  
			e.printStackTrace(); 
		}
		
		return mapJson;
	}
	
	//주문 페이지
	@GetMapping("/bookstore/payment/order.do")
	public String orderForm(HttpSession session, Model model) {
		log.debug("<<세션 확인 order >> : "+session.getAttribute("cartList"));
		String mem_id = (String)session.getAttribute("mem_id");
		if(mem_id == null) {
			return "redirect:/lm/login/template/loginMain.do?lo=1";
		}		
		int mem_num = (Integer)session.getAttribute("mem_num");
		int count = 0;
		List<BookStorePaymentCartVO> list = (ArrayList)session.getAttribute("cartList");
		if(list == null) {//장바구니로 돌아가기
			return "redirect:/bookstore/payment/cart.do";
		}
		List<ProductVO> book_list = new ArrayList<ProductVO>();
		//회원 배송지 정보
		List<MemberVO> home_list = new ArrayList<MemberVO>();
		home_list = bookStorePaymentOrderService.selectMemHome(mem_num);
		//총 구매액
		int total = (int)session.getAttribute("total");
		for(BookStorePaymentCartVO vo : list) {
			//log.debug("<<도서 상세 정보>> : "+product);
			book_list.add(bookStorePaymentService.selectDetailBook(vo.getStore_product_num()));
			count += vo.getCart_quantity();
		}
		log.debug("<<listCart 확인>> : "+book_list);
		
		MemberVO member = memberService.selectMember(mem_num);
		log.debug("<<member 확인>>"+member);
		//배송정보
		MemberVO home = bookStorePaymentOrderService.selectDefaultHome(mem_num);
		//등급에 따른 포인트 정보
		double point = getPoint(member.getMem_grade());
		//상품 정보
		model.addAttribute("list", list);
		model.addAttribute("book_list", book_list);
		model.addAttribute("count", count);
		//금액 정보&회원 할인 정보
		model.addAttribute("total", total);
		model.addAttribute("mem", member);
		model.addAttribute("point", point);
		//회원 배송 정보
		model.addAttribute("home", home);
		log.debug("<<기본 배송지>> : "+home);	
		model.addAttribute("home_list", home_list);
		//log.debug("<<배송지 리스트>> : "+home_list);
		return "order";
	}
	//주문 성공시 주문 내역 저장
	@RequestMapping("/bookstore/payment/orderAction.do")
	@ResponseBody
	public Map<String, String> payBook(String orderInfo, HttpSession session) {
		log.debug("<<결제 데이터 확인>> : "+orderInfo);
		int mem_num = (Integer)session.getAttribute("mem_num");		
		org.json.JSONObject jObject = new org.json.JSONObject(orderInfo);
		//boolean result = jObject.getBoolean("success");
		List<BookStorePaymentCartVO> list = (ArrayList)session.getAttribute("cartList");
		int total = (Integer)session.getAttribute("total");
		BookStorePaymentOrderVO order = new BookStorePaymentOrderVO();
		//데이터 뽑기
		MemberVO mem_home = bookStorePaymentOrderService.selectDefaultHome(mem_num);
		order.setHome_num(mem_home.getHome_num());
		order.setMem_num(mem_home.getMem_num());
		order.setOrder_total_price(total);
		String type = jObject.getString("pg_provider");
		int payment_type = 1;
		if(type.equals("kakaopay")) {
			payment_type = 2;
		}
		order.setPayment_type(payment_type);
		order.setIMP_UID(jObject.getString("imp_uid"));
		log.debug("<<result-order>> : "+order);
		log.debug("<<result-cartList>> : "+list);
		bookStorePaymentOrderService.insertOrder(order, list);
		
		session.removeAttribute("cartList");
		session.removeAttribute("total");
		Map<String, String> mapJson = new HashMap<String, String>();
		mapJson.put("result", "success");
		return mapJson;
	}
	//배송지 수정
	//폼
	@GetMapping("/bookstore/payment/homeModify.do")
	@ResponseBody
	public Map<String, Object> modifyForm(int home_num, HttpSession session){
		Map<String, Object> mapJson = new HashMap<String, Object>();
		String mem_id = (String)session.getAttribute("mem_id");
		if(mem_id == null) {
			mapJson.put("result", "logout");
			return mapJson;
		}
		MemberVO homeInfo = bookStorePaymentOrderService.selectHome(home_num);
		log.debug("<<배송 정보 수정>> : "+homeInfo);
		mapJson.put("homeInfo", homeInfo);
		mapJson.put("result", "success");
		return mapJson;
	}
	//동작
	@PostMapping("/bookstore/payment/homeModify.do")
	@ResponseBody
	public Map<String, Object> modifyAction(MemberVO memberVO, HttpSession session){
		Map<String, Object> mapJson = new HashMap<String, Object>();
		String mem_id = (String)session.getAttribute("mem_id");
		if(mem_id == null) {
			mapJson.put("result", "logout");
			return mapJson;
		}
		int mem_num = (Integer)session.getAttribute("mem_num");
		log.debug("<<수정 Action>> : "+memberVO);
		//기본 배송지로 설정한 주소 등록 시
		memberVO.setMem_num(mem_num);
		bookStorePaymentOrderService.updateHome(memberVO);
		if(memberVO.getHome_default() == 0) {
			mapJson.put("default", memberVO);
		}
		//회원 배송지 정보
		List<MemberVO> home_list = new ArrayList<MemberVO>();
		home_list = bookStorePaymentOrderService.selectMemHome(mem_num);
		log.debug("<<수정 ACTION - home_list>> : "+home_list);
		mapJson.put("result", "success");
		mapJson.put("home_list", home_list);
		return mapJson;
	}
	//배송지 등록
	@PostMapping("/bookstore/payment/homeInsert.do")
	@ResponseBody
	public Map<String, Object> insertHome(MemberVO memberVO, HttpSession session){
		Map<String, Object> mapJson = new HashMap<String, Object>();
		String mem_id = (String)session.getAttribute("mem_id");
		if(mem_id == null) {
			mapJson.put("result", "logout");
			return mapJson;
		}
		int mem_num = (Integer)session.getAttribute("mem_num");
		memberVO.setMem_num(mem_num);
		log.debug("<<배송 정보>> : "+memberVO);
		bookStorePaymentOrderService.insertHome(memberVO);
		//회원 배송지 정보
		List<MemberVO> home_list = new ArrayList<MemberVO>();
		home_list = bookStorePaymentOrderService.selectMemHome(mem_num);
		//기본 배송지로 설정한 주소 등록 시
		if(memberVO.getHome_default() == 0) {
			mapJson.put("default", memberVO);
		}
			
		mapJson.put("result", "success");
		mapJson.put("home_list", home_list);
		return mapJson;
	}
	//배송지 삭제
	@RequestMapping("/bookstore/payment/homeDelete.do")
	@ResponseBody
	public Map<String, Object> deleteHome(int home_num, HttpSession session, Model model) {
		Map<String, Object> mapJson = new HashMap<String, Object>();
		String mem_id = (String)session.getAttribute("mem_id");
		if(mem_id == null) {
			mapJson.put("result", "logout");
			return mapJson;
		}		
		int mem_num = (Integer)session.getAttribute("mem_num");
		bookStorePaymentOrderService.deleteHome(home_num);
		//회원 배송지 정보
		List<MemberVO> home_list = new ArrayList<MemberVO>();
		home_list = bookStorePaymentOrderService.selectMemHome(mem_num);
		mapJson.put("result", "success");
		mapJson.put("home_list", home_list);
		return mapJson;
	}
	//기본 배송지 업데이트
	@RequestMapping("/bookstore/payment/defaultChange.do")
	@ResponseBody
	public Map<String, Object> ChangeDefault(Integer home_num, HttpSession session){
		Map<String, Object> mapJson = new HashMap<String, Object>();
		log.debug("<<>> : "+home_num);
		int mem_num = (Integer)session.getAttribute("mem_num");
		MemberVO memberVO = memberService.selectMember(mem_num);
		memberVO.setMem_num(mem_num);
		bookStorePaymentOrderService.updateNormal(home_num, memberVO);
		//회원 배송지 정보
		List<MemberVO> home_list = new ArrayList<MemberVO>();
		home_list = bookStorePaymentOrderService.selectMemHome(mem_num);	
		log.debug("<<기본 배송지 업데이트>> : "+home_list);
		mapJson.put("result", "success");
		mapJson.put("home_list", home_list);
		return mapJson;
	}
	//멤버 등급에 따른 포인드 % 가져오기
	public double getPoint(int grade) {
		double point = 0.005;
		if(grade == 1 ) point = 0.01;
		else if(grade == 2 ) point = 0.015;
		else if(grade == 3 ) point = 0.02;
		else if(grade == 4 ) point = 0.03;
		
		return point;
	}
}

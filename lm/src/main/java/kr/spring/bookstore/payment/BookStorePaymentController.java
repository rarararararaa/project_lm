package kr.spring.bookstore.payment;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.exception.IamportResponseException;
import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;

import kr.spring.bookstore.payment.service.BookStorePaymentOrderService;
import kr.spring.bookstore.payment.service.BookStorePaymentService;
import kr.spring.bookstore.payment.vo.BookStorePaymentCartVO;
import kr.spring.bookstore.payment.vo.BookStorePaymentOrderVO;
import kr.spring.bookstore.product.vo.ProductVO;
import kr.spring.bookstore.used.vo.UsedVO;
import kr.spring.member.service.MemberService;
import kr.spring.member.vo.MemberVO;
import lombok.RequiredArgsConstructor;
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
	@Autowired
	
	//초기화
	@ModelAttribute
	public MemberVO initCommand() {
		return new MemberVO();
	}
	//장바구니 추가
	@PostMapping("/bookstore/payment/cart.do")
	@ResponseBody
	public Map<String,String> insertcartForm(HttpSession session, Model model, BookStorePaymentCartVO product) {
		String mem_id = (String)session.getAttribute("mem_id");
		Map<String, String> mapJson = new HashMap<String, String>();
		if(mem_id == null) {
			mapJson.put("result", "logout");
			return mapJson;
		}
		int mem_num = (Integer)session.getAttribute("mem_num");
		log.debug("<<도서 정보>> : "+product);
		product.setMem_num(mem_num);
		Boolean check = true;
		if(product.getUsed_product_num() != 0) {//중고 확인
			//중고 중복 상품 확인
			BookStorePaymentCartVO used = bookStorePaymentService.selectEmptyUsed(product.getUsed_product_num(),mem_num);
			if(used != null) {
				mapJson.put("result", "existBook");
				return mapJson;
			}
			product.setCart_quantity(1);
			bookStorePaymentService.insertCart(product);
			check = false;
		}else {//신상
			List<BookStorePaymentCartVO> db_cartVO = bookStorePaymentService.selectCartList(mem_num);
			for(BookStorePaymentCartVO vo : db_cartVO) {//중복 상품 확인
				log.debug("<<신상 중복 확인>> : "+vo.getStore_product_num()+','+product.getStore_product_num()+','+vo.getStore_product_status());
				if(vo.getStore_product_num() == product.getStore_product_num() && vo.getStore_product_status() == 0) {
					int total = vo.getCart_quantity() + product.getCart_quantity();
					product.setCart_quantity(total);
					product.setMem_cart_num(vo.getMem_cart_num());
					bookStorePaymentService.updateCart(product);
					check = false;
					break;
				}
			}
			if(check) {//새상품 추가
				log.debug("<<카트 도서 추가>>"+product);
				bookStorePaymentService.insertCart(product);
			}
		}
		mapJson.put("result", "success");
		
		return mapJson;
	}
	//======================API 관련===========================//  
	//Token 값 발급
	public String tokenTest() {
		String token = "";
		try {
			token = bookStorePaymentService.getToken();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return token;//결제 취소로 보내줘야 함
	}
	//주문 취소
	@RequestMapping("/bookstore/payment/cancelPay.do")
	@ResponseBody
	public Map<String, Object> cancelPay(int order_num){
		Map<String, Object> mapJson = new HashMap<String, Object>();
		try {
			String token = tokenTest();
			if(token == null) {
				throw new Exception();
			}
			//주문 정보 가져오기
			String IMP_UID = bookStorePaymentOrderService.selectImp_uid(order_num);
			String reason = "단순 변심";
			int amount = bookStorePaymentService.paymentInfo(token, IMP_UID);
			bookStorePaymentService.cancelPay(token, IMP_UID, amount,reason);
			//주문 취소 정보 추가
			bookStorePaymentOrderService.updateCancelInfo(order_num, reason);
			//재고 변경
			List<BookStorePaymentOrderVO> list = bookStorePaymentOrderService.listOrder(order_num);
			bookStorePaymentOrderService.updateQuantity(list);
			//주문 취소 포인트 전환
			String point_reason = String.valueOf(order_num);
			//해당 주문 건의 포인트 조회
			bookStorePaymentOrderService.selectPoint(point_reason);
			mapJson.put("result", "success");
		} catch (Exception e) {
			e.printStackTrace();
			mapJson.put("result", "errorCancel");
		}
		log.debug("<<주문 취소-map>> : "+mapJson);
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
			log.debug("<<중고 확인!!!!!!!!>> : "+vo);
			ProductVO product = bookStorePaymentService.selectDetailBook(vo.getStore_product_num());
			if(vo.getStore_product_status() == 1) {
				UsedVO used = bookStorePaymentService.selectUsedBook(vo.getUsed_product_num());
				log.debug("<<도서 중고 정보>> : "+used);
				product.setUsed_product_price(used.getUsed_product_price());
				product.setUsed_product_status(used.getUsed_product_status());
				product.setUsed_product_num(used.getUsed_product_num());
			}
			book_list.add(product);
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
	public Map<String, Object> deleteCartBook(Integer mem_cart_num, HttpSession session){
		String mem_id = (String)session.getAttribute("mem_id");
		Map<String, Object> mapJson = new HashMap<String, Object>();
		if(mem_id == null) {
			mapJson.put("result", "logout");
		}
		log.debug("<<카드 삭제 번호>> : "+mem_cart_num);
		if(mem_id == null) {
			mapJson.put("result", "logout");
		}
		int mem_num = (Integer)session.getAttribute("mem_num");
		bookStorePaymentService.deleteCart(mem_cart_num);
		mapJson.put("result", "success");
		return mapJson;
	}
	//======================장바구니 > 결제 페이지===========================//  
	@PostMapping("/bookstore/payment/cartAction.do")
	@ResponseBody 
	public Map<String, Object> actionCart(@RequestParam Map<String, Object> data, HttpSession session, int total){
		log.debug("<<total>>"+total);
		Map<String, Object> mapJson = new HashMap<String, Object>();
		String mem_id = (String)session.getAttribute("mem_id");
		if(mem_id == null) {
			mapJson.put("result", "logout");
			return mapJson;
		}
		int mem_num = (Integer)session.getAttribute("mem_num");
		log.debug("<<ACTION!>> : "+data.get("data"));
		String test = (String)data.get("data");
		try {
			
			JSONArray array = JSONArray.fromObject(test);
			List<Map<String, String>> list = new ArrayList<Map<String,String>>();
			for(int i=0;i<array.size();i++) {
				JSONObject obj = (JSONObject)array.get(i);
				log.debug("<<fsjkdlkfjkals>> : "+obj);
				Map<String, String> re = new HashMap<String, String>();
				
				re.put("mem_cart_num", (String)obj.get("mem_cart_num"));
				re.put("used_product_num", (String)obj.get("used_product_num"));
				re.put("cart_quantity",(String)obj.get("cart_quantity"));
				re.put("store_product_num", (String)obj.get("store_product_num"));
				re.put("store_product_pricestandard", (String)obj.get("store_product_pricestandard"));
				
				list.add(re);
			}
			List<BookStorePaymentCartVO> cartList = new ArrayList<BookStorePaymentCartVO>();
			
			for(Map<String, String> map : list) {
				BookStorePaymentCartVO vo = new BookStorePaymentCartVO();
				if(map.get("mem_cart_num")!=null) {
				vo.setMem_cart_num(Integer.parseInt(map.get("mem_cart_num")));
				vo.setUsed_product_num(Integer.parseInt(map.get("used_product_num")));
				}
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
			ProductVO product = bookStorePaymentService.selectDetailBook(vo.getStore_product_num());
			log.debug("<<fjidslkjf>> : "+vo.getUsed_product_num());
			if(vo.getUsed_product_num() != 0) {//중고 상품 추가 정보
				UsedVO used = bookStorePaymentService.selectUsedBook(vo.getUsed_product_num());
				log.debug("<<도서 중고 정보>> : "+used);
				product.setUsed_product_price(used.getUsed_product_price());
				product.setUsed_product_status(used.getUsed_product_status());
				product.setUsed_product_num(used.getUsed_product_num());
			}
			book_list.add(product);
			count += vo.getCart_quantity();
		}
		
		MemberVO member = memberService.selectMember(mem_num);
		//배송정보
		MemberVO home = bookStorePaymentOrderService.selectDefaultHome(mem_num);
		//등급에 따른 포인트 정보
		double point = getPoint(member.getMem_grade());
		//상품 정보
		model.addAttribute("list", list);
		model.addAttribute("book_list", book_list);
		log.debug("<<주문페이지 확인-list>>"+list);
		log.debug("<<주문페이지 확인-book_list>>"+book_list);
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
	//결제 검증
	@Value("${imp_key}")
	private String imp_key;
	@Value("${imp_secret}")
	private String imp_secret;

	@RestController
	@RequiredArgsConstructor
	@RequestMapping("/verifyIamport")
	private class VeriyController{
		private IamportClient api = new IamportClient(imp_key, imp_secret);
		
		@RequestMapping("/{imp_uid}")
		@ResponseBody
		public IamportResponse<Payment> paymentByUid(@PathVariable String imp_uid) throws IOException, IamportResponseException{
			log.debug("<<결제 검증>> : "+imp_uid);
			return api.paymentByImpUid(imp_uid);	
		}
	}
	
	//주문 성공시 주문 내역 저장
	@RequestMapping("/bookstore/payment/orderAction.do")
	@ResponseBody
	public Map<String, Object> payBook(String orderInfo, HttpSession session, String notice, int point) {
		log.debug("<<결제 데이터 확인>> : "+orderInfo);
		log.debug("<<배송시 요청사항>> : "+notice);
		int mem_num = (Integer)session.getAttribute("mem_num");		
		org.json.JSONObject jObject = new org.json.JSONObject(orderInfo);
		//boolean result = jObject.getBoolean("success");
		List<BookStorePaymentCartVO> list = (ArrayList)session.getAttribute("cartList");
		log.debug("<<주문완료 - cartList>> : "+list);
		BookStorePaymentOrderVO order = new BookStorePaymentOrderVO();
		//데이터 뽑기
		int total = (int)session.getAttribute("total");
		MemberVO mem_home = bookStorePaymentOrderService.selectDefaultHome(mem_num);
		order.setHome_num(mem_home.getHome_num());
		order.setMem_num(mem_home.getMem_num());
		order.setOrder_total_price(jObject.getInt("paid_amount"));
		order.setOrder_notice(notice);
		
		String type = jObject.getString("pg_provider");
		int payment_type = 1;
		if(type.equals("kakaopay")) {
			payment_type = 2;
		}
		order.setPayment_type(payment_type);
		order.setIMP_UID(jObject.getString("imp_uid"));
		log.debug("<<result-order>> : "+order);
		log.debug("<<result-cartList>> : "+list);
		//order 테이블 insert + 카트 정보 작세 + 재고 변경
		bookStorePaymentOrderService.insertOrder(order, list);
		
		//포인트 증감 - 회원 정보와 총금액
		Map<String, Object> map = new HashMap<String, Object>();
		MemberVO member = memberService.selectMember(mem_num);
		double grade = getPoint(member.getMem_grade());
		int addPoint = (int) Math.round(total*grade);//적립되는 포인트
		map.put("mem_num", mem_num);
		map.put("addPoint", (point*-1));//사용한 포인트
		map.put("order_num", order.getOrder_num());
		log.debug("<<포인트 - controller>> : "+map+','+addPoint);
		bookStorePaymentOrderService.updatePoint(map, addPoint);
		
		//세션 정보 삭제
		session.removeAttribute("cartList");
		session.removeAttribute("total");
		Map<String, Object> mapJson = new HashMap<String, Object>();
		
		mapJson.put("result", "success");
		mapJson.put("order", order);
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
		String mem_id = (String)session.getAttribute("mem_id");
		if(mem_id == null) {
			mapJson.put("result", "logout");
		}
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
	//영수증 페이지 보여주기
	@RequestMapping("/bookstore/payment/receipt.do")
	public String showReceipt(int order_num, HttpSession session, Model model) {
		log.debug("<<영수증 페이지>> : "+order_num);
		BookStorePaymentOrderVO order = bookStorePaymentOrderService.selectOrder(order_num);
		//책 정보
		List<BookStorePaymentOrderVO> list = bookStorePaymentOrderService.listOrder(order_num);
		String book_name = "";
		int product_num = list.get(0).getStore_product_num();
		ProductVO vo = bookStorePaymentOrderService.selectProductNum(product_num);
		if(list.size()>1) {
			int length = vo.getStore_product_title().length();
			int cut = 5;
			if( length <= 5) {
				cut = length-1;
			}
			book_name = vo.getStore_product_title().substring(0, cut)+"외 "+(list.size()-1)+"권";
		}else {
			book_name = vo.getStore_product_title();
		}
		MemberVO homeInfo = bookStorePaymentOrderService.selectHome(order.getHome_num());
		model.addAttribute("order", order);
		model.addAttribute("book_name", book_name);
		model.addAttribute("homeInfo", homeInfo);
		return "receipt";
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

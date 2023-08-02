package kr.spring.bookstore.payment;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class BookStorePaymentController {
	
	public static final String IMPORT_TOKEN_URL = "https://api.iamport.kr/users/getToken";
	public static final String IMPORT_CANCEL_URL = "https://api.iamport.kr/payments/cancel";
	public static final String KEY = "";
	public static final String SECRET = "";
	
	//장바구니
	@RequestMapping("/bookstore/payment/cart.do")
	public String cartForm() {
		
		return "cart";
	}

}

package kr.spring.bookstore.payment.service;

import java.io.IOException;
import java.util.List;

import kr.spring.bookstore.payment.vo.BookStorePaymentCartVO;
import kr.spring.bookstore.product.vo.ProductVO;
import kr.spring.bookstore.used.vo.UsedVO;

public interface BookStorePaymentService {
	public void insertCart(BookStorePaymentCartVO cartVO);
	
	public List<BookStorePaymentCartVO> selectCartList(int mem_num);
	
	//도서 상세 정보
	public ProductVO selectDetailBook(int store_product_num);
	public UsedVO selectUsedBook(int used_product_num);
	//카트 중고도서 중복확인
	public BookStorePaymentCartVO selectEmptyUsed(int used_product_num, int mem_num);
	//중복 도서 수량 합치기
	public void updateBookQuantity(int total, int mem_cart_num);
	//장바구니 정보 업데이트
	public void updateCart(BookStorePaymentCartVO cartVO);
	
	public void deleteCart(int mem_cart_num);
	
	//토큰 값 가져오기
	public String getToken() throws IOException;
	//결제 정보 불러오기
	public int paymentInfo(String token, String IMP_UID) throws IOException;
	//결제 취소하기
	public void cancelPay(String token, String IMP_UID, int amount, String reason) throws IOException;
}

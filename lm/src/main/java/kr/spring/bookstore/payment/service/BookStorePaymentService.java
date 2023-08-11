package kr.spring.bookstore.payment.service;

import java.util.List;

import kr.spring.bookstore.payment.vo.BookStorePaymentCartVO;
import kr.spring.bookstore.product.vo.ProductVO;

public interface BookStorePaymentService {
	public void insertCart(BookStorePaymentCartVO cartVO);
	
	public List<BookStorePaymentCartVO> selectCartList(int mem_num);
	
	//도서 상세 정보
	public ProductVO selectDetailBook(int store_product_num);
	//중복 도서 수량 합치기
	public void updateBookQuantity(int total, int store_product_num, int mem_num);
	//장바구니 정보 업데이트
	public void updateCart(BookStorePaymentCartVO cartVO);
	
	public void deleteCart(int store_product_num, int mem_num);
}

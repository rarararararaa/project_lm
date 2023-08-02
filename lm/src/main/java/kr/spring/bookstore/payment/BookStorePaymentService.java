package kr.spring.bookstore.payment;

import java.util.List;

import kr.spring.bookstore.payment.vo.BookStorePaymentCartVO;
import kr.spring.bookstore.product.vo.ProductVO;

public interface BookStorePaymentService {
	public void insertCart(BookStorePaymentCartVO cartVO);
	
	public List<BookStorePaymentCartVO> selectCartList(int mem_num);
	
	//도서 상세 정보
	public ProductVO selectDetailBook(int store_product_num);
}

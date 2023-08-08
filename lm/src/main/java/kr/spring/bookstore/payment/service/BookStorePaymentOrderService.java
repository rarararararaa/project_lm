package kr.spring.bookstore.payment.service;

import java.util.List;

import kr.spring.bookstore.payment.vo.BookStorePaymentCartVO;
import kr.spring.bookstore.payment.vo.BookStorePaymentOrderVO;

public interface BookStorePaymentOrderService {
	//주문 테이블 추가
	public void insertOrder(BookStorePaymentOrderVO bookStorePaymentOrderVO, List<BookStorePaymentCartVO> cartInfo);
}

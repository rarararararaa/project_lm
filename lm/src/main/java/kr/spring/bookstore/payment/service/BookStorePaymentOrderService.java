package kr.spring.bookstore.payment.service;

import java.util.List;


import kr.spring.bookstore.payment.vo.BookStorePaymentCartVO;
import kr.spring.bookstore.payment.vo.BookStorePaymentOrderVO;
import kr.spring.member.vo.MemberVO;

public interface BookStorePaymentOrderService {
	//주문 테이블 추가
	public void insertOrder(BookStorePaymentOrderVO bookStorePaymentOrderVO, List<BookStorePaymentCartVO> cartInfo);
	
	//배송 정보
	//우편번호로 주소 찾기
	public MemberVO selectHome(int home_zipcode);
}

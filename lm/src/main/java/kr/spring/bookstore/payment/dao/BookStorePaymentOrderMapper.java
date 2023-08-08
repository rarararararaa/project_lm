package kr.spring.bookstore.payment.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import kr.spring.bookstore.payment.vo.BookStorePaymentCartVO;
import kr.spring.bookstore.payment.vo.BookStorePaymentOrderVO;

@Mapper
public interface BookStorePaymentOrderMapper {
	//주문번호 생성
	@Select("SELECT store_order_manage_seq.nextval FROM dual")
	public int selectOrder_num();
	//주문 테이블 추가
	public void insertOrder(BookStorePaymentOrderVO bookStorePaymentOrderVO);
	public void insertDetailOrder(BookStorePaymentCartVO bookStorePaymentCartVO);
}

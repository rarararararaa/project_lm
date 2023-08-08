package kr.spring.bookstore.payment.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import kr.spring.bookstore.payment.vo.BookStorePaymentCartVO;
import kr.spring.bookstore.payment.vo.BookStorePaymentOrderVO;
import kr.spring.member.vo.MemberVO;

@Mapper
public interface BookStorePaymentOrderMapper {
	//주문번호 생성
	@Select("SELECT store_order_manage_seq.nextval FROM dual")
	public int selectOrder_num();
	//주문 테이블 추가
	public void insertOrder(BookStorePaymentOrderVO bookStorePaymentOrderVO);
	public void insertDetailOrder(BookStorePaymentCartVO bookStorePaymentCartVO);
	
	//배송 정보
	//우편번호로 주소 찾기
	@Select("SELECT * FROM store_member_home WHERE home_zipcode = #{home_zipcode}")
	public MemberVO selectHome(int home_zipcode);
	
}

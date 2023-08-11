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
	public MemberVO selectHome(int home_num);
	//회원 번호로 주소 찾기
	public List<MemberVO> selectMemHome(int mem_num);
	//회원의 기본 배송지 찾기
	public MemberVO selectDefaultHome(int mem_num);
	//배송지 등록
	public void insertHome(MemberVO memberVo);
	//배송지 수정
	public void updateHome(MemberVO memberVO);
	//배송지 삭제
	public void deleteHome(int home_num);
	//기본배송지 바꾸기
	public void updateNormal(int home_num, MemberVO mem);
}

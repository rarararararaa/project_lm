package kr.spring.bookstore.payment.service;

import java.util.List;
import java.util.Map;

import kr.spring.bookstore.payment.vo.BookStorePaymentCartVO;
import kr.spring.bookstore.payment.vo.BookStorePaymentOrderVO;
import kr.spring.bookstore.product.vo.ProductVO;
import kr.spring.lm.point.vo.PointVO;
import kr.spring.member.vo.MemberVO;

public interface BookStorePaymentOrderService {
	//주문 테이블 추가
	public void insertOrder(BookStorePaymentOrderVO bookStorePaymentOrderVO, List<BookStorePaymentCartVO> cartInfo);
	
	//주문 정보
	public BookStorePaymentOrderVO selectOrder(int order_num);
	//주문 상세 정보
	public List<BookStorePaymentOrderVO> listOrder(int order_num);
	//주문 취소 후 재고 변경
	public void updateQuantity(List<BookStorePaymentOrderVO> order);
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
	
	//도서 번호로 검색
	public ProductVO selectProductNum(int product_num);
	
	//주문 번호로 imp_uid를 가져오기
	public String selectImp_uid(int order_num);
	//주문 취소후 주문상태와 취소 메시지 넣기
	public void updateCancelInfo(int order_num, String cancel_notice);
	//주문 후 포인트 차감&적립
	public void updatePoint(Map<String, Object> map, int addPoint);
	//주문 포인트 조회 후 차감
	public void selectPoint(String point_reason);
}

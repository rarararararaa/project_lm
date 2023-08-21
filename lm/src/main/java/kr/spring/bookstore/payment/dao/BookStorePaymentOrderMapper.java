package kr.spring.bookstore.payment.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.spring.bookstore.payment.vo.BookStorePaymentCartVO;
import kr.spring.bookstore.payment.vo.BookStorePaymentOrderVO;
import kr.spring.bookstore.product.vo.ProductVO;
import kr.spring.member.vo.MemberVO;

@Mapper
public interface BookStorePaymentOrderMapper {
	//주문번호 생성
	@Select("SELECT store_order_manage_seq.nextval FROM dual")
	public int selectOrder_num();
	//주문 테이블 추가
	public void insertOrder(BookStorePaymentOrderVO bookStorePaymentOrderVO);
	public void insertDetailOrder(BookStorePaymentCartVO bookStorePaymentCartVO);
	
	//주문 정보
	@Select("SELECT * FROM store_order_manage WHERE order_num = #{order_num}")
	public BookStorePaymentOrderVO selectOrder(int order_num);
	//주문 상세 정보
	@Select("SELECT * FROM store_order_detail WHERE order_num = #{order_num}")
	public List<BookStorePaymentOrderVO> listOrder(int order_num);
	
	//배송 정보
	//home_num로 주소 찾기
	@Select("SELECT * FROM store_member_home WHERE home_num = #{home_num}")
	public MemberVO selectHome(int home_num);
	//회원 번호로 주소 찾기
	@Select("SELECT * FROM store_member_home WHERE mem_num = #{mem_num}")
	public List<MemberVO> selectMemHome(int mem_num);
	//회원의 기본 배송지 찾기
	@Select("SELECT * FROM store_member_home WHERE mem_num = #{mem_num} AND home_default = 0")
	public MemberVO selectDefaultHome(int mem_num);
	
	//주소 등록 하기
	@Insert("INSERT INTO store_member_home (home_num,mem_num,home_title,home_zipcode,home_address,home_address_detail,home_cell,home_name,home_default) VALUES (lm_member_manage_seq.nextval,#{mem_num},#{home_title},#{home_zipcode},#{home_address},#{home_address_detail},#{mem_cell},#{mem_name},#{home_default})")
	public void insertHome(MemberVO member);
	//기본 배송지 > 일반 변경
	@Update("UPDATE store_member_home SET home_default = 1 WHERE home_default = 0 AND mem_num = #{mem_num}")
	public void updateDefault(MemberVO memberVO);
	//일반 > 기본 배송지
	@Update("UPDATE store_member_home SET home_default = 0 WHERE home_num = #{home_num}")
	public void updateNormal(int home_num);
	//배송지 수정
	public void updateHome(MemberVO memberVO);
	
	//배송지 삭제
	@Delete("DELETE FROM store_member_home WHERE home_num = #{home_num}")
	public void deleteHome(int home_num);

	//도서 번호 검색
	@Select("SELECT * FROM store_product_detail WHERE store_product_num = #{product_num}")
	public ProductVO selectProductNum(int product_num);
	
	//주문 후 중고 status 값 바꾸기
	@Update("UPDATE store_used_product_manage SET used_product_status = #{num} WHERE used_product_num = #{used_product_num}")
	public void updateUsed(int num, int used_product_num);
	//구매 후 수량 변경
	@Update("UPDATE store_product_detail SET store_product_stock = store_product_stock+#{quantity} WHERE store_product_num = #{store_product_num}")
	public void updateProduct(int store_product_num, int quantity);
	
	
	
	//주문 번호로 imp_uid를 가져오기
	@Select("SELECT imp_uid FROM store_order_manage WHERE order_num = #{order_num}")
	public String selectImp_uid(int order_num);
	//주문 취소후 주문상태와 취소 메시지 넣기
	@Update("UPDATE store_order_manage SET order_pay_status = 0,cancel_notice = #{cancel_notice} WHERE order_num = #{order_num}")
	public void updateCancelInfo(int order_num, String cancel_notice);
	
}

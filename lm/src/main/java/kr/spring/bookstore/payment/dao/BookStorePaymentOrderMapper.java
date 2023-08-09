package kr.spring.bookstore.payment.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

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
	//회원 번호로 주소 찾기
	@Select("SELECT * FROM store_member_home WHERE mem_num = #{mem_num}")
	public List<MemberVO> selectMemHome(int mem_num);
	//주소 등록 하기
	@Insert("INSERT INTO store_member_home (home_num,mem_num,home_title,home_zipcode,home_address,home_address_detail,home_cell,home_name,home_default) VALUES (lm_member_manage_seq.nextval,#{mem_num},#{home_title},#{home_zipcode},#{home_address},#{home_address_detail},#{mem_cell},#{mem_name},#{home_default})")
	public void insertHome(MemberVO member);
	//기본 배송지 > 일반 변경
	@Update("UPDATE store_member_home SET home_default = 1 WHERE home_default = 0 AND mem_num = #{mem_num}")
	public void updateDefault(MemberVO memberVO);
	
}

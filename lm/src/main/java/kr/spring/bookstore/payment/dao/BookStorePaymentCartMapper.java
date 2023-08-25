package kr.spring.bookstore.payment.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.spring.bookstore.payment.vo.BookStorePaymentCartVO;
import kr.spring.bookstore.product.vo.ProductVO;
import kr.spring.bookstore.used.vo.UsedVO;

@Mapper
public interface BookStorePaymentCartMapper {
	@Insert("insert into store_cart (mem_cart_num, mem_num, store_product_num, store_product_status, cart_quantity, used_product_num) values (em_cart_seq.nextval, #{mem_num}, #{store_product_num}, #{store_product_status},#{cart_quantity}, #{used_product_num})")
	public void insertCart(BookStorePaymentCartVO cartVO);
	
	@Select("select * from store_cart where mem_num = #{mem_num}")
	public List<BookStorePaymentCartVO> selectCartList(int mem_num);
	
	//도서 상세 정보
	public ProductVO selectDetailBook(int store_product_num);
	//중고 도서 정보
	@Select("SELECT * FROM store_used_product_detail d JOIN store_used_product_manage m USING(used_product_num) WHERE used_product_num = #{used_product_num}")
	public UsedVO selectUsedBook(int used_product_num);
	//카트 중고도서 중복확인
	@Select("SELECT * FROM store_cart WHERE used_product_num = #{used_product_num} AND mem_num = #{mem_num}")
	public BookStorePaymentCartVO selectEmptyUsed(int used_product_num,int mem_num);
	//중복 도서 합치기
	@Update("UPDATE store_cart SET cart_quantity = #{total} WHERE mem_cart_num = #{mem_cart_num}")
	public void updateBookQuantity(int total, int mem_cart_num);
	
	//주문 시 카트 변경사항 저장
	@Update("UPDATE store_cart SET cart_quantity = #{cart_quantity} WHERE mem_cart_num = #{mem_cart_num}")
	public void updateCart(BookStorePaymentCartVO cartVO);
	
	
	//장바구니에서 도서 삭제
	@Delete("DELETE FROM store_cart WHERE mem_cart_num = #{mem_cart_num}")
	public void deleteCart(int mem_cart_num);
	

}
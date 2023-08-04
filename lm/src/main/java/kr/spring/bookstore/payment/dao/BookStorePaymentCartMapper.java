package kr.spring.bookstore.payment.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.spring.bookstore.payment.vo.BookStorePaymentCartVO;
import kr.spring.bookstore.product.vo.ProductVO;

@Mapper
public interface BookStorePaymentCartMapper {
	@Insert("insert into store_cart (mem_cart_num, mem_num, store_product_num, store_product_status, cart_quantity) values (em_cart_seq.nextval, #{mem_num}, #{store_product_num}, #{store_product_status},#{cart_quantity})")
	public void insertCart(BookStorePaymentCartVO cartVO);
	
	@Select("select * from store_cart where mem_num = #{mem_num}")
	public List<BookStorePaymentCartVO> selectCartList(int mem_num);
	
	//도서 상세 정보
	public ProductVO selectDetailBook(int store_product_num);
	
	//중복 도서 합치기
	@Update("UPDATE store_cart SET cart_quantity = #{total} WHERE store_product_num = #{store_product_num} AND mem_num = #{mem_num}")
	public void updateBookQuantity(int total, int store_product_num, int mem_num);
	
	//주문 시 카트 변경사항 저장
	@Update("UPDATE store_cart SET cart_quantity = #{cart_quantity} WHERE store_product_num = #{store_product_num} AND mem_num = #{mem_num}")
	public void updateCart(BookStorePaymentCartVO cartVO);
	
	//장바구니에서 도서 삭제
	@Delete("DELETE FROM store_cart WHERE mem_num = #{mem_num} AND store_product_num = #{store_product_num}")
	public void deleteCart(int store_product_num, int mem_num );
	}

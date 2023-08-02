package kr.spring.bookstore.payment.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import kr.spring.bookstore.payment.vo.BookStorePaymentCartVO;
import kr.spring.bookstore.product.vo.ProductVO;

@Mapper
public interface BookStorePaymentCartMapper {
	@Insert("insert into store_cart (mem_cart_num, mem_num, store_product_num, cart_quantity) values (em_cart_seq.nextval, #{mem_num}, #{store_product_num},#{cart_quantity})")
	public void insertCart(BookStorePaymentCartVO cartVO);
	
	@Select("select * from store_cart where mem_num = #{mem_num}")
	public List<BookStorePaymentCartVO> selectCartList(int mem_num);
	
	//도서 상세 정보
	public ProductVO selectDetailBook(int store_product_num);
}

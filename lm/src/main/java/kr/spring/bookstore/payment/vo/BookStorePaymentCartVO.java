package kr.spring.bookstore.payment.vo;

import kr.spring.bookstore.product.vo.ProductVO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BookStorePaymentCartVO {
	private int mem_cart_num;
	private int mem_num;
	private int store_product_num;
	private int cart_quantity;
	
	private ProductVO productVO;
}

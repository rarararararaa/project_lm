package kr.spring.bookstore.payment.vo;

import kr.spring.bookstore.product.vo.ProductVO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class BookStorePaymentCartVO {
	private int mem_cart_num;
	private int mem_num;
	private int store_product_num;//상품 번호
	private int cart_quantity;
	private int store_product_status;
	private int used_product_num;
	
	
	private int order_product_price;//구매 금액
	private int order_num;
	private ProductVO productVO;
}

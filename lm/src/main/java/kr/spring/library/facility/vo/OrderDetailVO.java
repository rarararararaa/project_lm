package kr.spring.library.facility.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OrderDetailVO {
	private int order_detail_num;
	private int order_num;
	private int store_product_num;
	private int order_product_price;
	private int cart_quantity;
}

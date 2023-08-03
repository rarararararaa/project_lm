package kr.spring.bookstore.payment.vo;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookStorePaymentOrderVO {
	//order_manage
	private int order_num;//주문번호
	private int home_num;//집주소
	private int mem_num;//회원번호
	private int order_total_price;//총금액
	private int order_status;//배송 상태
	private Date order_date;//주문 날짜
	private String notice;//배송지 요청 사함
	private int order_pay_status;//결제 상채 확인
	private int payment_type;//결제 방식
	private int IMP_UID;//결제 번호
	
	//order_detail-구매한 상품 각각의 정보
	private int ordre_detail_num;
	private int store_product_num;//상품 번호
	private int order_product_price;
	private int order_product_quantity;
}

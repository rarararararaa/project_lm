package kr.spring.library.book_lost_report.vo;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class LostBookReportVO {
	private int lost_report_num;
	private int callNumber;
	private int mem_num;
	private int rent_num;
	private int store_product_pricesales;
	private Date lost_reg_date;
	
	private int lost_payment_status;
	private int lost_payment_type;
	private String IMP_UID;//결제 번호
	
	//테이블에 없는 내용
	private String product_bookName;	//도서 이름
}

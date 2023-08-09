package kr.spring.library.rent.vo;

import java.sql.Date;

import kr.spring.library.product.vo.BookProductVO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ReservationVO {
	private int reservation_num;
	private String callNumber;
	private int mem_num;
	private int reservation_status;
	private Date reservation_submit_date;
	
	private String mem_id;
	private BookProductVO bookVO;
	private String lib_product_isbn;
}

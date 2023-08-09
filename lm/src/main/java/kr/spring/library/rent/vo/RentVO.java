package kr.spring.library.rent.vo;

import java.sql.Date;

import kr.spring.library.product.vo.BookProductVO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class RentVO {
	private int rent_num;
	private String callNumber;
	private int mem_num;
	private int lib_product_status; 
	private Date rent_reg_date;
	private Date return_reg_deadline;
	private Date return_reg_date;
	
	private BookProductVO bookVO;
	private String mem_id;
}

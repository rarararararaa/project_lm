package kr.spring.library.bookapply.vo;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BookApplyVO {
	private int book_apply_num;
	private int mem_num;
	private String book_apply_content;
	private int book_apply_status;
	private Date book_apply_reg_date;
	private String book_apply_title;
	
	
	/*BOOK_APPLY_NUM	NUMBER
	MEM_NUM	NUMBER(5,0)
	BOOK_APPLY_CONTENT	VARCHAR2(2000 BYTE)
	BOOK_APPLY_STATUS	NUMBER(1,0)
	BOOK_APPLY_REG_DATE	DATE
	BOOK_APPLY_TITLE	VARCHAR2(200 BYTE)*/
}

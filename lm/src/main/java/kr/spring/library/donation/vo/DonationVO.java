package kr.spring.library.donation.vo;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class DonationVO {
	
	private int donation_num;
	private String donation_book_info;
	private String donation_title;
	private String donation_content;
	private Date donation_reg_date;
	private String donation_name;
	private String donation_info;
	private int donation_status;
	
	/*DONATION_NUM	NUMBER(5,0)
	DONATION_BOOK_INFO	VARCHAR2(300 BYTE)
	DONATION_TITLE	VARCHAR2(200 BYTE)
	DONATION_CONTENT	VARCHAR2(1000 BYTE)
	DONATION_REG_DATE	DATE
	DONATION_NAME	VARCHAR2(300 BYTE)
	DONATION_INFO	VARCHAR2(300 BYTE)
	DONATION_STATUS	NUMBER(1,0)*/
}

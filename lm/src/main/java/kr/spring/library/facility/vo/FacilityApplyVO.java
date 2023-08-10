package kr.spring.library.facility.vo;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class FacilityApplyVO {
	private int facility_apply_num;
	private int facility_num;
	private int mem_num;
	private String reg_date;
	private Date facility_apply_start;
	private Date facility_apply_end;
	
	private String start;
	private String end;
	private String year;
	private String month;
	private String date;
}

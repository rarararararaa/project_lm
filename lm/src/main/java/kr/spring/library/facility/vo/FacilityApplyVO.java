package kr.spring.library.facility.vo;

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
	private String start;
	private String end;
	
	private String year;
	private String month;
	private String date;
}

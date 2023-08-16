package kr.spring.lm.point.vo;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PointVO {
	private int point_num;
	private int mem_num;
	private int point_value;
	private Date point_date;
	private String point_reason;
	private int point_status;
}

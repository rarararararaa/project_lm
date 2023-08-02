package kr.spring.bookstore.event.vo;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BsAttendancePointVO {
	private int event_attendance_point_num;
	private int mem_num;
	private int event_month;
	private int point_get1;
	private int point_get2;
	private int point_get3;
	
	private int attendance_count ;
}

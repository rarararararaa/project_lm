package kr.spring.bookstore.event.vo;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BsAttendanceVO {
	private int event_attendance_num;
	private int mem_num;
	private int event_attendance_point_num;
	private LocalDate event_attendance_date;
}

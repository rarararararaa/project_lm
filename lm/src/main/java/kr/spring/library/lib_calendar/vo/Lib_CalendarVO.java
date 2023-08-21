package kr.spring.library.lib_calendar.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Lib_CalendarVO {
	private int calendar_num;
	private String title;
	private String start_date;
	private String start_time;
	private String end_date;
	private String end_time;
	private int allday;
	private int event_status;
}

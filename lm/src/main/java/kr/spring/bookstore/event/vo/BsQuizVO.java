package kr.spring.bookstore.event.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BsQuizVO {
	private int event_quiz_status_num;
	private int event_board_num;
	private int mem_num;
}

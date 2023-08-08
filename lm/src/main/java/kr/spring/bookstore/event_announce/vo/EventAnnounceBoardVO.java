package kr.spring.bookstore.event_announce.vo;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class EventAnnounceBoardVO {
	private int event_announce_board_num;	//게시판 번호
	private String title;					//제목
	private String content;					//내용
	private int hit;						//조회수
	private Date reg_date;					//작성일
	private Date modify_date;				//수정일
	 
}

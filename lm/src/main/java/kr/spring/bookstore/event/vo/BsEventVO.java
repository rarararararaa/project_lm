package kr.spring.bookstore.event.vo;

import java.sql.Date;
import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BsEventVO {
	private int event_board_num;		//이벤트 게시판 글 번호
	private int store_product_num;		//연관 상품 번호
	private int event_board_status;		//이벤트 상태(진행중,종료,시작전(출력X)
	private int event_board_category;	//이벤트 종류(댓글,퀴즈)
	private String event_title;			//이벤트 글 제목
	private String event_short_content;	//짧은 소개(목록에서 출력)
	private String event_content;		//이벤트 소개(상세페이지 출력)
	private Date event_reg_date;			//이벤트 글 등록 날짜
	private Date event_date_start;		//이벤트 시작날짜
	private Date event_date_end;			//이벤트 종료날짜
	private int event_hit;					//글 조회수
	private String event_quiz_sel1;		//퀴즈 선택지1
	private String event_quiz_sel2;		//퀴즈 선택지2
	private String event_quiz_an;		//퀴즈 정답
	
}

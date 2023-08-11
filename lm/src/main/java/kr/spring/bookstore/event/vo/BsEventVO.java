package kr.spring.bookstore.event.vo;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;

import javax.validation.constraints.NotEmpty;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(exclude = {"event_img_small", "event_img_big"})
public class BsEventVO {
	private int event_board_num;		//이벤트 게시판 글 번호
	private int store_product_num;		//연관 상품 번호
	private int event_board_status;		//이벤트 상태(진행중,종료,시작전(출력X)
	private int event_board_category;	//이벤트 종류(댓글,퀴즈)
	@NotEmpty
	private String event_title;			//이벤트 글 제목
	private String event_short_content;	//짧은 소개(목록에서 출력)
	private String event_content;		//이벤트 소개(상세페이지 출력)
	private byte[] event_img_small;
	private byte[] event_img_big;
	private Date event_reg_date;			//이벤트 글 등록 날짜-
	private Date event_modify_date;			//이벤트 글 수정 날짜
	private String event_date_start;		//이벤트 시작날짜
	private String event_date_end;			//이벤트 종료날짜
	private int event_hit;					//글 조회수
	private String event_quiz_sel1;		//퀴즈 선택지1
	private String event_quiz_sel2;		//퀴즈 선택지2
	private int event_quiz_an;		//퀴즈 정답
	
	//업로드 파일 처리
	public void setUploadSmall(MultipartFile uploadSmall) throws IOException{
		//MultipartFile -> byte[] 변환 | 변환해야 Mybatis가 blob 처리를 해준다.
		setEvent_img_small(uploadSmall.getBytes());
	}
	public void setUploadBig(MultipartFile uploadBig) throws IOException{
		//MultipartFile -> byte[] 변환 | 변환해야 Mybatis가 blob 처리를 해준다.
		setEvent_img_big(uploadBig.getBytes());
	}
}

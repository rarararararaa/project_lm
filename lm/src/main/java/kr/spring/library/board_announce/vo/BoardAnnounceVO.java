package kr.spring.library.board_announce.vo;

import java.sql.Date;

import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BoardAnnounceVO {//공지사항 게시판
	private int notice_num; //글번호
	@NotEmpty
	private String notice_title; //제목
	@NotEmpty
	private String notice_content; //내용
	private int notice_category; //카테고리
	private Date notice_reg_date; //작성일
	private Date notice_modify_date; //수정일
	private int notice_hit; //조회수
	private String notice_ip; //ip주소
}

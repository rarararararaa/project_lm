package kr.spring.library.lib_lost_item.vo;

import java.sql.Date;

import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class LibLostItemVO {//공지사항 게시판
	private int item_num; //글번호
	@NotEmpty
	private String item_title; //제목
	@NotEmpty
	private String item_content; //내용
	private int item_status; //카테고리
	private Date item_reg_date; //작성일
	private Date item_modify_date; //수정일
	private int item_hit; //조회수
}

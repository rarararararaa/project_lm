package kr.spring.bookstore.service.vo;

import java.sql.Date;

import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AnnounceVO {
	private int board_num;
	@NotEmpty
	private String board_title;
	@NotEmpty
	private String board_content;
	private Date board_reg_date;
	private Date board_modify_date;
	private String board_filename;
}

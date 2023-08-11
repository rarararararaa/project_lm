package kr.spring.library.program.vo;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(exclude = {"program_content"})
public class ProgramVO {
	private int program_num;
	private String program_title;
	private String program_content;
	private Date program_reg_date;
	private Date program_date;
	private int program_admit;//정원
	private int program_hit;//조회수
	
	private String start;
	private String end;
	private String startYear;
	private String startMonth;
	private String startDate;
	private String endYear;
	private String endMonth;
	private String endDate;
}

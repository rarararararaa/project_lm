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
	private int program_admit;//정원
	private int program_hit;//조회수
	
	
	private Date program_start;
	private Date program_end;
	private int status;
	private Date program_date;
	private int start;
	private int end;
	private int startYear;
	private int startMonth;
	private int startDate;
	private int endYear;
	private int endMonth;
	private int endDate;
}

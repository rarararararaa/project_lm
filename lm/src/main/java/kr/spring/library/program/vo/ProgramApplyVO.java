package kr.spring.library.program.vo;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProgramApplyVO {

	private int program_apply_num;
	private int mem_num;
	private int program_times_num;
	private Date program_reg_date;
	
	private String program_title;
	private String date;
	private String start;
	private String end;
	/*PROGRAM_APPLY_NUM	NUMBER
	MEM_NUM	NUMBER(5,0)
	PROGRAM_TIMES_NUM	NUMBER
	PROGRAM_REG_DATE	DATE*/
}

package kr.spring.library.program.vo;

import java.sql.Date;
import java.text.SimpleDateFormat;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class ProgramTimesVO {
	private int program_num;
	private int program_times_num;
	private Date program_start;
	private Date program_end;
	private int program_admit;
	
	public String getStart() {
		SimpleDateFormat fmt = new SimpleDateFormat("hh");
		return fmt.format(program_start);
	}
	public String getEnd() {
		SimpleDateFormat fmt = new SimpleDateFormat("hh");
		return fmt.format(program_end);
	}
	/*PROGRAM_NUM	NUMBER
	PROGRAM_TIMES_NUM	NUMBER
	PROGRAM_START	DATE
	PROGRAM_END	DATE*/
}

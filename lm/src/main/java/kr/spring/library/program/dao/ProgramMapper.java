package kr.spring.library.program.dao;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.spring.library.program.vo.ProgramApplyVO;
import kr.spring.library.program.vo.ProgramTimesVO;
import kr.spring.library.program.vo.ProgramVO;

@Mapper
public interface ProgramMapper {
	//프로그램 추가
	@Select("SELECT lib_program_seq.nextval FROM dual")
	public Integer selectProgramNum();
	public void insertProgram(ProgramVO programVO);
	public void insertProgramTimes(ProgramTimesVO programTimesVO);
	//프로그램 목록
	public List<ProgramVO> selectProgramList(Map<String, Object> map);
	public Date selectProgramStart(int program_num);
	public Date selectProgramEnd(int program_num);
	public Integer selectRowCount();
	//프로그램 상세
	@Select("SELECT * FROM lib_program_admin WHERE program_num=#{program_num}")
	public ProgramVO selectProgram(int program_num);
	@Select("SELECT * FROM lib_program_times WHERE program_num=#{program_num}")
	public List<ProgramTimesVO> selectProgramTimes(int program_num);
	//프로그램 신청
	public void insertProgramApply(ProgramApplyVO programApplyVO);
	@Select("SELECT program_admit FROM lib_program_times WHERE program_times_num=#{program_times_num}")
	public Integer selectAdmit(int program_times_num); 
	@Update("UPDATE lib_program_times SET program_admit=#{program_admit} WHERE program_times_num=#{program_times_num}")
	public void updateAdmit(ProgramTimesVO times);
	
}

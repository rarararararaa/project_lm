package kr.spring.library.program.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

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
	public Integer selectProgramStatus(int program_num);
	public Integer selectRowCount();
	//프로그램 상세
	public ProgramVO selectProgram(int program_num);
	public List<ProgramTimesVO> selectProgramTimes(int program_num);
	public Integer CountApply(int program_times_num);
	//프로그램 신청
}

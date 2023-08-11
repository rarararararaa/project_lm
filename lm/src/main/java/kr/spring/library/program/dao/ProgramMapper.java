package kr.spring.library.program.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import kr.spring.library.program.vo.ProgramTimesVO;
import kr.spring.library.program.vo.ProgramVO;

@Mapper
public interface ProgramMapper {
	//프로그램 추가
	public void insertProgram(ProgramVO program);
	public void insertProgramTimes(ProgramTimesVO times);
	//프로그램 목록
	public List<ProgramVO> selectProgramList(Map<String, Object> map);
	//프로그램 상세
	public ProgramVO selectProgram(int program_num);
	public List<ProgramTimesVO> selectProgramTimes(int program_num);
	public Integer CountApply(int program_times_num);
	//프로그램 신청
}

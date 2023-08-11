package kr.spring.library.program.service;

import java.util.List;
import java.util.Map;

import kr.spring.library.program.vo.ProgramTimesVO;
import kr.spring.library.program.vo.ProgramVO;

public interface ProgramService {
	//프로그램 추가
	public void insertProgram(ProgramVO program);
	public void insertProgramTimes(ProgramTimesVO times);
	//프로그램 목록
	public List<ProgramVO> selectProgramList(Map<String, Object> map);
	//프로그램 상세
	public ProgramVO selectProgram(int program_num);
	public List<ProgramTimesVO> selectProgramTimes(int program_num);
	public Integer CountApply(int program_times_num);
}

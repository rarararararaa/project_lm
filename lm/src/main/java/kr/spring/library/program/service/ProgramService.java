package kr.spring.library.program.service;

import java.util.List;
import java.util.Map;

import kr.spring.library.program.vo.ProgramApplyVO;
import kr.spring.library.program.vo.ProgramTimesVO;
import kr.spring.library.program.vo.ProgramVO;

public interface ProgramService {
	//프로그램 추가
	public void insertProgram(ProgramVO program, List<ProgramTimesVO> timeList);
	//프로그램 목록
	public List<ProgramVO> selectProgramList(Map<String, Object> map);

	public Integer selectRowCount();
	//프로그램 상세
	public ProgramVO selectProgram(int program_num);
	public List<ProgramTimesVO> selectProgramTimes(int program_num);
	
	//프로그램 신청
	public void insertProgramApply(ProgramApplyVO programApplyVO);
}

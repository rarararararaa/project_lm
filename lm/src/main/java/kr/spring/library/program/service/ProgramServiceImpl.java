package kr.spring.library.program.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import kr.spring.library.program.vo.ProgramTimesVO;
import kr.spring.library.program.vo.ProgramVO;

@Service
public class ProgramServiceImpl implements ProgramService{

	@Override
	public void insertProgram(ProgramVO program) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insertProgramTimes(ProgramTimesVO times) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<ProgramVO> selectProgramList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProgramVO selectProgram(int program_num) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProgramTimesVO> selectProgramTimes(int program_num) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer CountApply(int program_times_num) {
		// TODO Auto-generated method stub
		return null;
	}

}

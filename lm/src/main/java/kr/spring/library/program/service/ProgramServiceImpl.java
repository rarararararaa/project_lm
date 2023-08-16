package kr.spring.library.program.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.spring.library.program.dao.ProgramMapper;
import kr.spring.library.program.vo.ProgramTimesVO;
import kr.spring.library.program.vo.ProgramVO;

@Service
@Transactional
public class ProgramServiceImpl implements ProgramService{
	
	@Autowired
	private ProgramMapper programMapper;

	@Override
	public void insertProgram(ProgramVO program, List<ProgramTimesVO> timeList) {
		int program_num = programMapper.selectProgramNum();
		program.setProgram_num(program_num);
		
		programMapper.insertProgram(program);
		
		for(ProgramTimesVO time : timeList) {
			time.setProgram_num(program_num);
			programMapper.insertProgramTimes(time);
		}
	}
	
	@Override
	public List<ProgramVO> selectProgramList(Map<String, Object> map) {
		
		List<ProgramVO> list = programMapper.selectProgramList(map);
		for(ProgramVO program : list) {
			program.setStatus(programMapper.selectProgramStatus(program.getProgram_num()));
		}
		return list;
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

	@Override
	public Integer selectRowCount() {
		return programMapper.selectRowCount();
	}


}

package kr.spring.library.program.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.spring.library.program.dao.ProgramMapper;
import kr.spring.library.program.vo.ProgramApplyVO;
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
			program.setProgram_start(programMapper.selectProgramStart(program.getProgram_num()));
			program.setProgram_end(programMapper.selectProgramEnd(program.getProgram_num()));
			
			/*Calendar cal = Calendar.getInstance();
			Calendar start = Calendar.getInstance();
			Calendar end = Calendar.getInstance();
			
			Date ustart = new java.util.Date(program.getProgram_start().getTime());
			Date uend = new java.util.Date(program.getProgram_end().getTime());
			
			
			start.setTime(ustart);
			end.setTime(uend);
			
			if(cal.before(start)){
				program.setStatus(0);
			}else if(cal.after(end)) {
				program.setStatus(1);
			}else {
				program.setStatus(2);
			}*/
				
			
		}
		return list;
	}

	@Override
	public ProgramVO selectProgram(int program_num) {
		return programMapper.selectProgram(program_num);
	}

	@Override
	public List<ProgramTimesVO> selectProgramTimes(int program_num) {
		return programMapper.selectProgramTimes(program_num);
	}


	@Override
	public Integer selectRowCount() {
		return programMapper.selectRowCount();
	}

	@Override
	public void insertProgramApply(ProgramApplyVO programApplyVO) {
		int admit = programMapper.selectAdmit(programApplyVO.getProgram_times_num());
		ProgramTimesVO time = new ProgramTimesVO();
		time.setProgram_admit(admit - 1);
		time.setProgram_times_num(programApplyVO.getProgram_times_num());
		programMapper.updateAdmit(time);
		programMapper.insertProgramApply(programApplyVO);
	}


}

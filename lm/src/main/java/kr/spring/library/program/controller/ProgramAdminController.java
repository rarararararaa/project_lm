package kr.spring.library.program.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kr.spring.library.program.service.ProgramService;
import kr.spring.library.program.vo.ProgramApplyVO;
import kr.spring.library.program.vo.ProgramTimesVO;
import kr.spring.library.program.vo.ProgramVO;
import kr.spring.util.PagingUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class ProgramAdminController {
	
	@Autowired
	private ProgramService programService;
	
	@ModelAttribute
	public ProgramVO initCommand() {
		return new ProgramVO();
	}
	@ModelAttribute
	public ProgramTimesVO initCommand2() {
		return new ProgramTimesVO();
	}
	
	@RequestMapping("/library/apply/programAdminList.do")
	public String programList(@RequestParam(value="pageNum",
										defaultValue="1") int currentPage,
									@RequestParam(value="order",
										defaultValue="1") int order, Model model) {
		Map<String,Object> map = 
				new HashMap<String,Object>();
		
		int count = programService.selectRowCount();
		
		log.debug("<<count>> : " + count);
		PagingUtil page = 
				new PagingUtil(
						currentPage,count,20,10,
						"announceList.do","&order="+order);
		
		List<ProgramVO> list = null;
		if(count > 0) {
			map.put("order",order);
			map.put("start", page.getStartRow());
			map.put("end", page.getEndRow());
			
			list = programService.selectProgramList(map);			
		}
		
		
		model.addAttribute("list", list);
		model.addAttribute("count", count);
		model.addAttribute("page", page.getPage());
		return "admin_programList";
	}
	@GetMapping("/library/apply/programAdminWrite.do")
	public String insertProgram(HttpSession session) {
		
		return "admin_programWrite";
	}
	@PostMapping("/library/apply/insertProgram.do")
	public String programWrite(@Valid ProgramVO programVO,
								BindingResult result,
								HttpSession session,
								HttpServletRequest request,
								Model model) {
		log.debug("<<programVO>> : " + programVO);
		
		if(result.hasErrors()) {
			
			return "insertProgram";
		}
		
		Calendar start = Calendar.getInstance();
		Calendar end = Calendar.getInstance();
		start.set(Calendar.YEAR, programVO.getStartYear());
		start.set(Calendar.MONTH, programVO.getStartMonth());
		start.set(Calendar.DATE, programVO.getStartDate());
		end.set(Calendar.YEAR,programVO.getEndYear());
		end.set(Calendar.MONTH,programVO.getEndMonth());
		end.set(Calendar.DATE,programVO.getEndDate());
		if(start.after(end)) {
			return "insertProgram";
		}
		List<ProgramTimesVO> timeList = new ArrayList<ProgramTimesVO>();
		end.set(Calendar.HOUR, programVO.getEnd());
		while(!start.after(end)) {
			ProgramTimesVO times = new ProgramTimesVO();
			times.setProgram_admit(programVO.getProgram_admit());
			start.set(Calendar.HOUR, programVO.getStart());
			times.setProgram_start(new Date(start.getTimeInMillis()));
			start.set(Calendar.HOUR, programVO.getEnd());
			times.setProgram_end(new Date(start.getTimeInMillis()));
			start.add(Calendar.DATE, 1);
			timeList.add(times);
		}
		programService.insertProgram(programVO, timeList);
		
		model.addAttribute("message", "프로그램을 추가하였습니다");
		model.addAttribute("url", request.getContextPath()+"/library/apply/programAdminList.do");
		
		
		return "common/resultView";
	}

	@GetMapping("/library/apply/admin_programDetail.do")
	public String programDetail(@RequestParam(value="program_num") int program_num, Model model,HttpServletRequest request) {
		ProgramVO program = programService.selectProgram(program_num);
		List<ProgramTimesVO> times = programService.selectProgramTimes(program_num);
		model.addAttribute("program", program);
		model.addAttribute("times", times);
		return "admin_programDetail";
	}
	@PostMapping("/library/apply/admin_programDetail.do")
	public String programApply(@Valid ProgramTimesVO time, BindingResult result, Model model, HttpSession session) {
		int mem_num = (Integer)session.getAttribute("mem_num");
		ProgramApplyVO apply = new ProgramApplyVO();
		
		log.debug("<<time>> : " + time);
		
		apply.setMem_num(mem_num);
		apply.setProgram_times_num(time.getProgram_times_num());
		
		log.debug("<<apply>> : " + apply);
		programService.insertProgramApply(apply);
		
		model.addAttribute("message", "프로그램 신청이 완료되었습니다.");
		model.addAttribute("url", "programAdminList.do");
		
		return "common/resultView";
	}
}

package kr.spring.library.program.controller;

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

import kr.spring.library.program.service.ProgramService;
import kr.spring.library.program.vo.ProgramTimesVO;
import kr.spring.library.program.vo.ProgramVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class ProgramController {
	
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
	
	@GetMapping("/library/insertProgram.do")
	public String programForm(){
		return "insertProgram";
	}
	@PostMapping("/library/insertProgram.do")
	public String programWrite(@Valid ProgramVO programVO,
								BindingResult result,
								HttpSession session,
								HttpServletRequest request,
								Model model) {
		log.debug("<<programVO>> : " + programVO);
		
		if(result.hasErrors()) {
			return programForm();
		}
		
		if(programVO.getStartMonth().length()== 1) {
			programVO.setStartMonth("0" + programVO.getStartMonth());
		}
		if(programVO.getStartDate().length()== 1) {
			programVO.setStartDate("0" + programVO.getStartDate());
		}
		if(programVO.getEndMonth().length()== 1) {
			programVO.setEndMonth("0" + programVO.getEndMonth());
		}
		if(programVO.getEndDate().length()== 1) {
			programVO.setEndDate("0" + programVO.getEndDate());
		}
		
		return "";
	}
}

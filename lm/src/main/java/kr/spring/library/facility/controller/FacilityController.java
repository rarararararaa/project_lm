package kr.spring.library.facility.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.spring.library.facility.service.FacilityService;
import kr.spring.library.facility.vo.FacilityVO;

@Controller
public class FacilityController {
	
	@Autowired
	FacilityService facilityService;
	
	@ModelAttribute
	public FacilityVO initCommand() {
		return new FacilityVO();
	}
	
	
	@RequestMapping("/insertFacility.do")
	public String insertFacility(){
		
		return "";
	}
}

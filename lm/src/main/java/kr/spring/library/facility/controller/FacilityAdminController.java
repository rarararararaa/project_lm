package kr.spring.library.facility.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.spring.library.facility.service.FacilityService;
import kr.spring.library.facility.vo.FacilityApplyVO;
import kr.spring.library.facility.vo.FacilityVO;
import kr.spring.util.PagingUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class FacilityAdminController {
	
	@Autowired
	private FacilityService facilityService;
	
	@ModelAttribute
	public FacilityVO initCommand() {
		return new FacilityVO();
	}
	@ModelAttribute
	public FacilityApplyVO initCommand2() {
		return new FacilityApplyVO();
	}
	
	@GetMapping("/library/apply/admin_insertAdminFacility.do")
	public String insertForm(){
		
		return "admin_insertFacility";
	}
	@PostMapping("/library/apply/admin_insertFacility.do")
	public String insertFacility(@Valid FacilityVO facilityVO,
								BindingResult result,
								HttpSession session,
								HttpServletRequest request,
								Model model) {
		log.debug("<<facilityVO>> : "+facilityVO);
		
		
		if(facilityVO.getFacility_image().length == 0) {
			result.rejectValue("facility_image", "required");
		}
		
		if(facilityVO.getFacility_image().length>=5*1024*1024) {//5MB
			                  //필드명,에러코드,에러문구에 전달할 값,기본 에러 문구
			result.rejectValue("Facility_image","limitUploadSize",new Object[] {"5MB"},null);
		}
		if(result.hasErrors()) {
			return insertForm();
		}
		
		facilityService.insertFacility(facilityVO);
		
		//View에 표시할 메시지
		model.addAttribute("message", "시설 등록이 완료되었습니다.");
		model.addAttribute("url", 
				request.getContextPath()+"/library/apply/facilityAdminList.do");

		return "common/resultView";
	}
	
	@RequestMapping("/library/apply/facilityAdminList.do")
	public ModelAndView process(
			@RequestParam(value="pageNum"
			,defaultValue="1") int currentPage,
			String keyfield,String keyword) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("keyfield", keyfield);
		map.put("keyword", keyword);

		//전체/검색 레코드수
		int count = facilityService.selectFacilityCount(map);
		
		log.debug("<<count>> : "+count);

		//페이지 처리
		PagingUtil page = new PagingUtil(keyfield,keyword,currentPage,
				count,20,10,"facilityAdminList.do");

		List<FacilityVO> list = null;
		if(count > 0) {
			map.put("start",page.getStartRow());
			map.put("end", page.getEndRow());

			list = facilityService.selectFacilityList(map);
		}

		ModelAndView mav = new ModelAndView();
		mav.setViewName("admin_facilityList");
		mav.addObject("count", count);
		mav.addObject("list", list);
		mav.addObject("page", page.getPage());

		return mav;
	}
	@RequestMapping("/library/admin_facApplyWrite.do")
	public String adminDetailFacility(int facility_num, Model model) {
		model.addAttribute("facility_num",facility_num);
		return "admin_deTailFacility";
	}
}

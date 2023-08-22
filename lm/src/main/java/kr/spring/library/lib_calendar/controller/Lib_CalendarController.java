package kr.spring.library.lib_calendar.controller;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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

import kr.spring.bookstore.event.vo.BsAttendancePointVO;
import kr.spring.bookstore.event_announce.vo.EventAnnounceBoardVO;
import kr.spring.library.lib_calendar.service.Lib_CalendarService;
import kr.spring.library.lib_calendar.vo.Lib_CalendarVO;
import kr.spring.util.PagingUtil;
import kr.spring.util.StringUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class Lib_CalendarController {
	@Autowired
	private Lib_CalendarService calendarService;

	/*
	 * ======================== 자바빈(VO) 초기화 ========================
	 */
	@ModelAttribute
	public Lib_CalendarVO initCommand() {
		return new Lib_CalendarVO();
	}

	@GetMapping("/library/basic/lib_SchedulePopup.do")
	public String openPopup() {
		return "/library/basic/lib_SchedulePopup";
	}

	// 일정 추가
	@PostMapping("/library/basic/lib_SchedulePopup.do")
	@ResponseBody
	public Map<String, Object> submit(Lib_CalendarVO calendarVO, BindingResult result, Model model, HttpServletRequest request,
			HttpSession session) {
		Map<String, Object> mapJson = new HashMap<String, Object>();
		log.debug("<<일정 추가 >> : " + calendarVO);

		/* 유효성 체크 결과 오류가 있으며 폼 호출
		if (result.hasErrors()) {
			//return openPopup();
		}*/
		
		//일정 추가
		calendarService.insertCalendarEvent(calendarVO);
		mapJson.put("result", "success");
		return mapJson;
		
	}
	
	//달력페이지 조회
	@GetMapping("/library/basic/libCalendar.do")
	public String libCalendar() {
		
		return "libCalendar";
	}
	
	//일정 불러오기
	@RequestMapping("/library/basic/getSchedule.do")
	@ResponseBody
	public Map<String, Object> getSchedule(Lib_CalendarVO calendar, HttpSession session){
		Map<String, Object> mapJson = new HashMap<String, Object>();
		
		Integer mem_auth = (Integer) session.getAttribute("mem_auth");
		if(mem_auth == null) {
			mapJson.put("mem_auth", "no-admin");
		}else if(mem_auth == 9) {
			//관리자
			mapJson.put("mem_auth", "admin");
		}else {
			//로그인된 회원번호 셋팅
			mapJson.put("mem_auth", "no-admin");
		}
		log.debug("mem_auth: "+ mem_auth);
		List<Lib_CalendarVO> list = calendarService.selectCalendarEvent();
		
		mapJson.put("list", list);		
		return mapJson;
	}
	
	//일정 수정 폼 호출
	@GetMapping("/library/basic/lib_ScheduleModify.do")
	public String formUpdate(@RequestParam int calendar_num, Model model) {
		log.debug("<<수정 일정 - calendar_num>> : " + calendar_num);

		//글 상세
		Lib_CalendarVO calendar = calendarService.selectScheduleDetail(calendar_num);
		log.debug("<<수정 일정 - calendar_vo>> : " + calendar);
		
		model.addAttribute("lib_CalendarVO", calendar);

		return "/library/basic/lib_ScheduleModify";
	}
	
	//수정버튼 클릭 - 수정 처리
	@RequestMapping("/library/basic/lib_ScheduleModify.do")
	@ResponseBody
	public Map<String, Object> formUpdate(Lib_CalendarVO calendarVO, BindingResult result, HttpServletRequest request, Model model) {
		Map<String, Object> mapJson = new HashMap<String, Object>();
		log.debug("<<수정 버튼 처리 - calendar_num>> : " + calendarVO);

		//글 수정
		calendarService.updateCalendarEvent(calendarVO);
		
		mapJson.put("result", "success");
		return mapJson;
		//return "/library/basic/lib_ScheduleModify";
	}
	
	//일정 삭제 - 삭제처리
	@RequestMapping("/library/basic/lib_ScheduleDelete.do")
	@ResponseBody
	public Map<String, Object> submitDelte(@RequestParam int calendar_num) {
		Map<String, Object> mapJson = new HashMap<String, Object>();
		log.debug("<<삭제 버튼 클릭 - calendar_num>> : " + calendar_num);

		//일정 삭제
		calendarService.deleteCalendarEvent(calendar_num);
		
		mapJson.put("result", "success");
		return mapJson;
		//return "/library/basic/lib_ScheduleModify";
	}
	
	//관리자 페이지 - 일정 리스트
	@RequestMapping("/library/basic/lib_ScheduleAdminList.do")
	public ModelAndView getList(@RequestParam(value="pageNum", defaultValue="1") int currentPage,
								String keyfield, String keyword) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("keyfield", keyfield);
		map.put("keyword", keyword);
		
		//전체 일정 수
		int count = calendarService.selectCount(map);
		log.debug("<<count>> : "+count);
		
		//페이지처리
		PagingUtil page = new PagingUtil(keyfield,keyword,currentPage,count,20,10,"lib_ScheduleAdminList.do");
		
		List<Lib_CalendarVO> list = null;
		
		if(count > 0) {
			map.put("start", page.getStartRow());
			map.put("end", page.getEndRow());
			
			list = calendarService.selectList(map);
		}

		ModelAndView mav = new ModelAndView();
		mav.setViewName("libScehduleAdminList");
		mav.addObject("count", count);
		mav.addObject("list", list);
		mav.addObject("page", page.getPage());
		
		return mav;
	}
	
}

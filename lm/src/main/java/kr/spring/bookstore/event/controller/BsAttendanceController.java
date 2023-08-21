package kr.spring.bookstore.event.controller;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.spring.bookstore.event.service.BsEventService;
import kr.spring.bookstore.event.vo.BsAttendancePointVO;
import kr.spring.bookstore.event.vo.BsAttendanceVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class BsAttendanceController {
	
	@Autowired
	private BsEventService bsEventService;
	

	/*========================
	 * 출석 이벤트
	 *========================*/
	//기본 상태 출력
	@RequestMapping("/bookstore/event/attendanceEvent.do")
	public String atten(HttpSession session,Model model, BsAttendancePointVO vo) {
		//MemberVO member = (MemberVO)session.getAttribute("member");
		Integer mem_num = (Integer) session.getAttribute("mem_num");
		log.debug("<<mem_num>> : " + mem_num);
		LocalDate now = LocalDate.now();
		int month = now.getMonthValue();
		
		//Map<String, Object> map = new HashMap<String, Object>();
		//map.put("event_moth", month);
		
		if(mem_num == null) {
			//로그인 X
			BsAttendancePointVO status = new BsAttendancePointVO();
			status.setEvent_month(month);
			status.setEvent_attendance_point_num(-1);
			status.setAttendance_count(0);
			status.setPoint_get1(0);
			status.setPoint_get2(0);
			status.setPoint_get3(0);
			status.setMem_num(-1);
			model.addAttribute("status", status);
		}else {
			//로그인O
			//map.put("mem_num", user.getMem_num());
			
			BsAttendancePointVO attendanceStatus = bsEventService.selectAttendanceDetail(mem_num, month);
			log.debug("<<attendanceStatus1: >>"+attendanceStatus);
			if(attendanceStatus == null) {
				vo.setMem_num(mem_num);
				vo.setEvent_month(month);
				bsEventService.insertAttendancePoint(vo);
				attendanceStatus = bsEventService.selectAttendanceDetail(mem_num, month);
				log.debug("<<attendanceStatus널: >>"+attendanceStatus);
			}
			BsAttendancePointVO status = attendanceStatus;
			model.addAttribute("status", status);
			
			
		}
		
		return "attendanceEvent";
	}
	@RequestMapping("/bookstore/event/getStatus.do")
	@ResponseBody
	public Map<String, Object> getStatus(BsAttendancePointVO attendance, HttpSession session){
		Map<String, Object> mapJson = new HashMap<String, Object>();
		LocalDate now = LocalDate.now();
		int month = now.getMonthValue();
		//MemberVO member = (MemberVO)session.getAttribute("member");
		//int mem_num = (int)session.getAttribute("mem_num");
		Integer mem_num = (Integer) session.getAttribute("mem_num");
		if(mem_num == null) {
			//로그인 X
			mapJson.put("status", "noCount");
		}else {
			//로그인된 회원번호 셋팅
			attendance = bsEventService.selectAttendanceDetail(mem_num, month);
			if(attendance != null) {
				mapJson.put("status", "yesCount");
			}else {
				mapJson.put("status", "noCount");
			}
		}
		mapJson.put("attendance", attendance);		
		return mapJson;
	}
	
	
	//출석체크 버튼 클릭
	@RequestMapping("/bookstore/event/writeAttendance.do")
	@ResponseBody
	public Map<String, Object> writeAttendance( HttpSession session, HttpServletRequest request, 
												BsAttendanceVO attendanceVO, BsAttendancePointVO attPointVO){
		Map<String, Object> mapJson = new HashMap<String, Object>();
		Map<String, Object> mapCheck = new HashMap<String, Object>();
		Map<String, Object> mapPointStatus = new HashMap<String, Object>();
		
		LocalDate now = LocalDate.now();
		log.debug("<<now>> : " + now);
		int month = now.getMonthValue();
		LocalDate lastDate = now.withDayOfMonth(now.lengthOfMonth());
		int lastDate2 = lastDate.getDayOfMonth();
		
		//MemberVO member = (MemberVO)session.getAttribute("member");
		//int mem_num = (int)session.getAttribute("mem_num");
		Integer mem_num = (Integer) session.getAttribute("mem_num");
		if(mem_num == null) {
			//로그인 X
			mapJson.put("result", "logout");
		}else {
			//로그인 O
			//중복체크
			mapCheck.put("mem_num", mem_num);
			mapCheck.put("event_attendance_date", now);
			attendanceVO = bsEventService.selectAttendanceCheck(mapCheck);
			if(attendanceVO != null) {
				//이미 오늘 출석 정보가 있다.
				mapJson.put("result", "Check");
			}else {
				//Event_attendance_point_num을 알기 위한 호출
				attPointVO = bsEventService.selectAttendanceDetail(mem_num, month);
				
				BsAttendanceVO attendance = new BsAttendanceVO();
				attendance.setMem_num(mem_num);
				attendance.setEvent_attendance_point_num(attPointVO.getEvent_attendance_point_num());
				log.debug("<<attendanceVO>> : " + attendance);
				
				bsEventService.insertAttendance(attendance);
				//출석체크 수행후  - 추가된 부분 조회
				attPointVO  = bsEventService.selectAttendanceDetail(mem_num, month);
				
				//mem 총 이벤트 상태 조회(이벤트 페이지 들어오면 없는 경우 자동으로 만들지만 혹시 없는 경우)
				if(attPointVO != null) {
					mapJson.put("status", "yesCount");
				}else {
					mapJson.put("status", "noCount");
				}
				
				//int count = bsEventService.selectAttendanceCount(attPo);
				//도장개수
				if(attPointVO .getAttendance_count() == 10) {
					mapJson.put("mem_num", mem_num);
					mapJson.put("addPoint", 100);
					bsEventService.updateMemberPoint(mapJson);
					
					mapPointStatus.put("get1", "add");
					mapJson.put("alertStatus", "get1");
					mapPointStatus.put("event_attendance_point_num", attPointVO.getEvent_attendance_point_num());
					bsEventService.updateAttendancePointGet(mapPointStatus);
					
					//포인트 발급여부 수정 후 조회
					attPointVO  = bsEventService.selectAttendanceDetail(mem_num, month);
				}else if(attPointVO .getAttendance_count() == 20) {
					mapJson.put("mem_num", mem_num);
					mapJson.put("addPoint", 200);
					bsEventService.updateMemberPoint(mapJson);
					
					mapPointStatus.put("get2", "add");
					mapJson.put("alertStatus", "get2");
					mapPointStatus.put("event_attendance_point_num", attPointVO.getEvent_attendance_point_num());
					bsEventService.updateAttendancePointGet(mapPointStatus);
					
					//포인트 발급여부 수정 후 조회
					attPointVO  = bsEventService.selectAttendanceDetail(mem_num, month);
				}else if(attPointVO .getAttendance_count() == lastDate2) {
					mapJson.put("mem_num", mem_num);
					mapJson.put("addPoint", 300);
					bsEventService.updateMemberPoint(mapJson);
					
					mapPointStatus.put("get3", "add");
					mapJson.put("alertStatus", "get");
					mapPointStatus.put("event_attendance_point_num", attPointVO.getEvent_attendance_point_num());
					bsEventService.updateAttendancePointGet(mapPointStatus);
					
					//포인트 발급여부 수정 후 조회
					attPointVO  = bsEventService.selectAttendanceDetail(mem_num, month);
				}
				mapJson.put("result", "success");
				mapJson.put("attendance", attPointVO);
			}
			
		}
		return mapJson;
	}
	
	@RequestMapping("/bookstore/event/getStamp.do")
	@ResponseBody
	public Map<String, Object> drawStamp(HttpSession session){
		Map<String, Object> mapJson = new HashMap<String, Object>();

		LocalDate now = LocalDate.now();
		int month = now.getMonthValue();
		
		//MemberVO member = (MemberVO)session.getAttribute("member");
		Integer mem_num = (Integer) session.getAttribute("mem_num");
		if(mem_num == null) {	
			//로그인 X
			mapJson.put("status", "noStamp");
		}else {
			BsAttendancePointVO attendancePointVO = bsEventService.selectAttendanceDetail(mem_num, month);
			List<BsAttendanceVO> list = bsEventService.selectListAtendance(attendancePointVO);
			if(list == null) {
				mapJson.put("status", "noStamp");
			}else {
				mapJson.put("status", "yesStamp");
				mapJson.put("list", list);
			}
		}
		return mapJson;
	}


}

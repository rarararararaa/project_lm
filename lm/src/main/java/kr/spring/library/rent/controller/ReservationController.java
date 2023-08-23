package kr.spring.library.rent.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.spring.library.product.BookProductService;
import kr.spring.library.rent.service.RentService;
import kr.spring.library.rent.service.ReservationService;
import kr.spring.library.rent.vo.RentVO;
import kr.spring.library.rent.vo.ReservationVO;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class ReservationController {
	@Autowired
	private ReservationService reservationService;
	@Autowired
	private RentService rentService;
	@Autowired
	private BookProductService bookProductService;
	
	//대출 예약
	@RequestMapping("/library/rent/reservationWrite.do")
	@ResponseBody
	public Map<String,Object> reservationBook(ReservationVO vo, HttpSession session){
		
		Map<String, Object> mapJson = new HashMap<String, Object>();
		if(session.getAttribute("mem_num") ==null ) {
			//로그인 X인 경우
			mapJson.put("result", "logout");
		}else {
			//로그인 O인 경우
			vo.setMem_num((Integer)session.getAttribute("mem_num"));
			Map<String, Object> map=new HashMap<String, Object>();
			log.debug("<<mem_num>> : "+vo.getMem_num());
			map.put("mem_num", vo.getMem_num());
			log.debug("<<lib_product_isbn>> : "+vo.getLib_product_isbn());
			map.put("lib_product_isbn", vo.getLib_product_isbn());
			
			//ISBN당 예약인수 확인
			int countbybook=reservationService.selectReservationCountByISBN(map);
			if(countbybook>=1) {
				mapJson.put("result", "over1st");
				return mapJson;
			}
			//회원당 대출권수 확인
			int count=rentService.selectRentCountByMem_num(map);
			log.debug("<<rent_count>> : "+count);
			if(count>=3) {
				mapJson.put("result", "overValue");
				return mapJson;
			}
			//회원당 대출예약 확인
			int reser_count=reservationService.selectReservationCountByMem_num(map);
			if(reser_count>=3) {
				mapJson.put("result", "reservationLimit");
				return mapJson;
			}
			
			
			List<String> list=null;
			list=reservationService.selectCallNumberToReservation(vo.getLib_product_isbn());
			for(String c: list) {
				//반납되었는지
				if(!reservationService.selectCheckRentStatus(c)) {
					mapJson.put("result", "rentBook");
					return mapJson;
				}
				vo.setCallNumber(c);
				//대출중인 책
				reservationService.insertReservation(vo);
				mapJson.put("result", "success");
				return mapJson;
			}
		} 
		log.debug("<<mapJson>> : "+mapJson);
		return mapJson;
	}
	
	//예약 중인 책 반납 시 대출 버튼 누름
	@RequestMapping("/library/rent/rentReservationBook.do")
	public String rentReservationBook(@RequestParam int reservation_num,@RequestParam int lo,
										HttpSession session,
										HttpServletRequest request,Model model) {
		Map<String, Object> map=new HashMap<String, Object>();
		int mem_num=(Integer)session.getAttribute("mem_num");
		//예약자 본인인지 확인
		ReservationVO reservation=reservationService.selectReservationDetailtoRent(reservation_num);
		if(reservation.getMem_num()!=mem_num) {
			model.addAttribute("message", "예약한 아이디로만 대출 가능합니다.");
			model.addAttribute("url", request.getContextPath()+"/lm/mypage/bookreservationlist/bookReservationListMain.do?lo="+lo);
		}else {
			//대출
			RentVO rentVO=new RentVO();
			String callNumber=reservationService.selectCallNumbertoRent(reservation);
			map.put("mem_num",mem_num);
			int count=rentService.selectRentCountByMem_num(map);
			if(count>3) {
				model.addAttribute("message", "최대 세권까지 대출 가능합니다.");
				model.addAttribute("url", request.getContextPath()+"/lm/mypage/bookreservationlist/bookReservationListMain.do?lo="+lo);
			}else {
				rentVO.setCallNumber(callNumber);
				rentVO.setMem_num(mem_num);
				rentService.insertRentHistory(rentVO);
				reservationService.updateReservationtoRent(reservation);
				model.addAttribute("message", "대출 성공");
				model.addAttribute("url", request.getContextPath()+"/lm/mypage/bookreservationlist/bookReservationListMain.do?lo="+lo);
			} 
			
		}
		
		return "common/resultView";
		
	}
	
	//취소 버튼 누름
	@RequestMapping("/library/rent/cancelReservationBook.do")
	public String cancelReservationBook(@RequestParam int reservation_num,@RequestParam int lo,
										HttpSession session,
										HttpServletRequest request,Model model) {
		Map<String, Object> map=new HashMap<String, Object>();
		int mem_num=(Integer)session.getAttribute("mem_num");
		//예약자 본인인지 확인
		ReservationVO reservation=reservationService.selectReservationDetailtoRent(reservation_num);
		if(reservation.getMem_num()!=mem_num) {
			model.addAttribute("message", "예약한 아이디로만 취소 가능합니다.");
			model.addAttribute("url", request.getContextPath()+"/lm/mypage/bookreservationlist/bookReservationListMain.do?lo="+lo);
		}else {
			//취소
			reservationService.updateCancelReservation(reservation);
			model.addAttribute("message", "취소 성공");
			model.addAttribute("url", request.getContextPath()+"/lm/mypage/bookreservationlist/bookReservationListMain.do?lo="+lo);
		}
		
		return "common/resultView";
		
	}	
	
	
}

















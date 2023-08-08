package kr.spring.library.rent.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.spring.library.product.BookProductService;
import kr.spring.library.rent.service.RentService;
import kr.spring.library.rent.service.ReservationService;
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
			//callNumber추출위한 vo
			vo.setCallNumber(reservationService.selectCallNumberToReservation(vo.getLib_product_isbn()));
			//회원당 대출권수 확인
			int count=rentService.selectRentCountByMem_num(map);
			log.debug("<<rent_count>> : "+count);
			if(count>=3) {
				mapJson.put("result", "overValue");
				return mapJson;
			}
			int reser_count=reservationService.selectReservationCountByMem_num(map);
			if(reser_count>=3) {
				mapJson.put("result", "reservationLimit");
				return mapJson;
			}
			if(!reservationService.selectCheckRentStatus(vo.getCallNumber())) {
				mapJson.put("result", "rentBook");
				return mapJson;
			}
			reservationService.insertReservation(vo);
			mapJson.put("result", "success");
		} 
		log.debug("<<mapJson>> : "+mapJson);
		return mapJson;
	}	
}

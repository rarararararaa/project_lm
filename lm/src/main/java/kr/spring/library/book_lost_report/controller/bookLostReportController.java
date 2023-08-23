package kr.spring.library.book_lost_report.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.spring.library.book_lost_report.service.LostBookReportService;
import kr.spring.library.book_lost_report.vo.LostBookReportVO;
import kr.spring.library.rent.vo.RentVO;
import kr.spring.member.service.MemberService;
import kr.spring.member.vo.MemberVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class bookLostReportController {
	@Autowired
	private LostBookReportService lostBookReportService;
	
	@Autowired
	private MemberService memberService;
	
	@GetMapping("/library/lostReport/bookLostInfo.do")
	public String lostInfo() {
		return "bookLostInfo";
	}
	
	@GetMapping("/library/lostReport/bookLostReport.do")
	public String bookLostReport(HttpSession session, Model model, HttpServletRequest request) {
		Integer mem_num = (Integer) session.getAttribute("mem_num");
		log.debug("<<mem_num>> : " + mem_num);
		
		if(mem_num == null) {
			//로그인 X
			model.addAttribute("message","로그인이 필요합니다.");
			model.addAttribute("url",request.getContextPath()+"/lm/login/template/loginMain.do?lo=2");
			return "common/resultView";
		}else {
			//로그인 O
			int count = lostBookReportService.selectRentCount(mem_num);
			if(count > 0) {
				//String mem_id = (String)session.getAttribute("mem_id");
				//MemberVO memberVO = memberService.selectCheckMember(mem_id);
				MemberVO memberVO = lostBookReportService.selectMemberInfo(mem_num);
				log.debug("<<memberVO>> : " +memberVO);
				model.addAttribute("memberVO", memberVO);
				List<RentVO> list = null;
				list = lostBookReportService.selectlistRentInfo(mem_num);
				model.addAttribute("list", list);
				return "bookLostReport";
				
			}else {
				model.addAttribute("message","현재 대출중인 도서가 없습니다.");
				model.addAttribute("url","bookLostInfo.do");
				return "common/resultView";
			}
		}
		
	}
	
	@RequestMapping("/library/lostReport/bookLostReportAction.do")
	@ResponseBody
	public Map<String, Object> lostBookPay(HttpSession session, String orderInfo,
											int callNumber, int rent_num, int amount){
		Map<String, Object> mapJson = new HashMap<String, Object>();
		int mem_num = (Integer)session.getAttribute("mem_num");	
		org.json.JSONObject jObject = new org.json.JSONObject(orderInfo);
		
		LostBookReportVO lostBook = new LostBookReportVO();
		
		int lostBook_num = lostBookReportService.selectLostReport_num();
		
		lostBook.setLost_report_num(lostBook_num);
		lostBook.setMem_num(mem_num);
		lostBook.setCallNumber(callNumber);
		lostBook.setRent_num(rent_num);
		lostBook.setStore_product_pricesales(amount);
		
		String type = jObject.getString("pg_provider");
		int payment_type = 1;
		if(type.equals("kakaopay")) {
			payment_type = 2;
		}
		lostBook.setLost_payment_type(payment_type);
		lostBook.setIMP_UID(jObject.getString("imp_uid"));
		
		//도서분실 insert
		lostBookReportService.insertBookLostReport(lostBook);
		
		//분실도서 상태 update
		lostBookReportService.updateBookHistory_Lost(callNumber);
		lostBookReportService.updateBookManage_Lost(callNumber);
		
		mapJson.put("result", "success");
		mapJson.put("lostBook", lostBook);
		
		return mapJson;
	}
	
	@RequestMapping("/library/lostReport/bookLostCheck.do")
	public String showBookLostCheck(int lost_report_num, HttpSession session, Model model) {
		LostBookReportVO lostBook = lostBookReportService.selectLostBookInfo(lost_report_num);
		log.debug("lostBookVO: "+lostBook);
		if(lostBook == null) {
			
		}
		
		model.addAttribute("lostBook", lostBook);
		return "bookLostCheck";
	}
	
	@GetMapping("/library/basic/informationUse1.do")
	public String informationUSE1() {
		return "informationUse1";
	}
	@GetMapping("/library/basic/informationUse2.do")
	public String informationUSE2() {
		return "informationUse2";
	}
	@GetMapping("/library/basic/informationUse3.do")
	public String informationUSE3() {
		return "informationUse3";
	}
	
}

package kr.spring.library.rent.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.spring.library.product.BookProductService;
import kr.spring.library.product.vo.BookProductVO;
import kr.spring.library.rent.service.RentService;
import kr.spring.library.rent.vo.RentVO;
import kr.spring.member.vo.MemberVO;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class RentController {
	@Autowired
	private RentService rentService;
	@Autowired
	private BookProductService bookProductService;
	
	//대출 리스트
	@RequestMapping("/library/rent/rentHistoryList.do")
	public ModelAndView getList() {
		Map<String, Object> map=new HashMap<String,Object>();
		List<RentVO> list=null;
		list=rentService.selectRentHistory(map);
		
		ModelAndView mav=new ModelAndView();
		mav.setViewName("rentList");
		mav.addObject("list",list);
		
		return mav;
	}
	
	/* ===============
	 *  대출 회원 검색
	 * =============== */
	@RequestMapping("/libarary/rent/memberSearchAjax.do")
	@ResponseBody
	public Map<String, Object> memberSearchAjax(@RequestParam String mem_id, HttpSession session){
		Map<String, Object> mapJson = new HashMap<String, Object>();
		
		int user=0;
		user=(Integer)session.getAttribute("mem_num");
		if(user == 0) {
			//로그인 X
			mapJson.put("result", "logout");
		}else {
			//로그인 O
			List<MemberVO> member = rentService.selectSearchMember(mem_id);
			mapJson.put("result", "success");
			mapJson.put("member", member);
		}
		return mapJson;
	}
	
	/* ===============
	 *  대출 도서 검색
	 * =============== */
	@RequestMapping("/libarary/rent/bookSearchAjax.do")
	@ResponseBody
	public Map<String, Object> bookSearchAjax(@RequestParam String lib_product_bookname, HttpSession session){
		Map<String, Object> mapJson = new HashMap<String, Object>();
		int user=0;
		user=(Integer)session.getAttribute("mem_num");
		if(user == 0) {
			//로그인 X
			mapJson.put("result", "logout");
		}else {
			//로그인 O
			List<BookProductVO> book= bookProductService.searchDetailLIB_P(lib_product_bookname);
			mapJson.put("result", "success");
			mapJson.put("book", book);
		}
		return mapJson;
	}	
	
	/* ===============
	 *  대출 등록
	 * =============== */
	@RequestMapping("/library/rent/rentHistoryWrite.do")
	@ResponseBody
	public Map<String,Object> rentBook(RentVO vo, HttpSession session){
		
		Map<String, Object> mapJson = new HashMap<String, Object>();
		
		int user =0;
		user=(Integer)session.getAttribute("mem_num");
		if(user == 0) {
			//로그인 X인 경우
			mapJson.put("result", "logout");
		}else {
			//로그인 O인 경우
			vo.setMem_num(user);
			
			rentService.insertRentHistory(vo);
			
			mapJson.put("result", "success");
		}
		
		return mapJson;
	}	
	
	
}

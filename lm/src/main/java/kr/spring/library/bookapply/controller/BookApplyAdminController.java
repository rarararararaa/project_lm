package kr.spring.library.bookapply.controller;

import java.sql.Date;
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

import kr.spring.library.bookapply.service.BookApplyService;
import kr.spring.library.bookapply.vo.BookApplyVO;
import kr.spring.util.PagingUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class BookApplyAdminController {
	
	@Autowired
	private BookApplyService bookApplyService;
	
	@ModelAttribute
	public BookApplyVO initCommand() {
		return new BookApplyVO();
	}
	@GetMapping("/library/apply/admin_bookApplyWrite.do")
	public String applyForm(Model model,HttpSession session){
		
		int mem_num = (Integer)session.getAttribute("mem_num");
		BookApplyVO search = new BookApplyVO();
		search.setMem_num(mem_num);
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -1);
		search.setBook_apply_reg_date(new Date(cal.getTimeInMillis()));
		
		int month = bookApplyService.countBookApply(search);
		
		cal.add(Calendar.MONTH, 1);
		cal.add(Calendar.YEAR, -1);
		search.setBook_apply_reg_date(new Date(cal.getTimeInMillis()));
		
		int year = bookApplyService.countBookApply(search);
		
		model.addAttribute("month", month);
		model.addAttribute("year", year);
		
		return "admin_bookApplyWrite";
	}
	@PostMapping("/library/apply/admin_bookApplyWrite.do")
	public String bookApply(@Valid BookApplyVO apply, BindingResult result,
							HttpSession session, Model model,HttpServletRequest request) {
		log.debug("<<apply>> : " + apply);
		
		int mem_num = (Integer)session.getAttribute("mem_num");
		
		apply.setMem_num(mem_num);
		
		BookApplyVO search = new BookApplyVO();
		search.setMem_num(mem_num);
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -1);
		search.setBook_apply_reg_date(new Date(cal.getTimeInMillis()));
		
		bookApplyService.countBookApply(search);
		
		bookApplyService.insertBookApply(apply);
		
		model.addAttribute("message", "희망도서 신청이 접수되었습니다.");
		model.addAttribute("url", request.getContextPath()+"/library/apply/admin_bookApplyList.do");
		return "common/resultView";
	}
	@RequestMapping("/library/apply/admin_bookApplyList.do")
	public ModelAndView userList(HttpSession session,@RequestParam(value="pageNum"
									,defaultValue="1") int currentPage,
									String keyfield,String keyword) {
		int mem_num = (Integer)session.getAttribute("mem_num");
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("keyfield", keyfield);
		map.put("keyword", keyword);
		map.put("mem_num", mem_num);

		//전체/검색 레코드수
		int count = bookApplyService.countBookApplyListByMem_num(map);
		
		log.debug("<<count>> : "+count);

		//페이지 처리
		PagingUtil page = new PagingUtil(keyfield,keyword,currentPage,
				count,20,10,"admin_bookApplyList.do");

		List<BookApplyVO> list = null;
		if(count > 0) {
			map.put("start",page.getStartRow());
			map.put("end", page.getEndRow());

			list = bookApplyService.selectBookApplyListByMem_num(map);
		}
		BookApplyVO search = new BookApplyVO();
		search.setMem_num(mem_num);
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -1);
		search.setBook_apply_reg_date(new Date(cal.getTimeInMillis()));
		
		int month = bookApplyService.countBookApply(search);
		
		cal.add(Calendar.MONTH, 1);
		cal.add(Calendar.YEAR, -1);
		search.setBook_apply_reg_date(new Date(cal.getTimeInMillis()));
		
		int year = bookApplyService.countBookApply(search);

		ModelAndView mav = new ModelAndView();
		mav.setViewName("admin_bookApplyList");
		mav.addObject("count", count);
		mav.addObject("list", list);
		mav.addObject("page", page.getPage());
		mav.addObject("month", month);
		mav.addObject("year", year);
		return mav;
	}
}

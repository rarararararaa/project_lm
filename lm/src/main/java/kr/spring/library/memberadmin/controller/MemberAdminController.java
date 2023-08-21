package kr.spring.library.memberadmin.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kr.spring.library.memberadmin.service.MemberAdminService;
import kr.spring.member.vo.MemberVO;
import kr.spring.util.PagingUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class MemberAdminController {
	@Autowired
	private MemberAdminService memberAdminService;
	
	/*=================================
	 * 회원목록 - 관리자
	 *=================================*/
	@RequestMapping("/library/member/admin_list.do")
	public ModelAndView getList(
			@RequestParam(value="pageNum",
			            defaultValue="1") int currentPage,
			             String keyfield,String keyword) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("keyfield", keyfield);
		map.put("keyword", keyword);
		
		//전체/검색 레코드수
		int count = memberAdminService.selectRowCount(map);
		
		log.debug("<<count>> : " + count);
		
		//페이지 처리
		PagingUtil page = 
				new PagingUtil(keyfield,keyword,currentPage,
						count,15,10,"admin_list.do");
		
		List<MemberVO> list = null;
		if(count > 0) {
			map.put("start", page.getStartRow());
			map.put("end", page.getEndRow());
			
			list = memberAdminService.selectList(map);
		}
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("admin_memberList");
		mav.addObject("count", count);
		mav.addObject("list", list);
		mav.addObject("page", page.getPage());
		
		return mav;
	}
	
	/*=================================
	 * 회원관한변경 - 관리자
	 *=================================*/
	//수정 폼
	@GetMapping("/library/member/admin_update.do")
	public String form(@RequestParam int mem_num,Model model) {
		MemberVO memberVO = memberAdminService.selectMember(mem_num);
		
		model.addAttribute("memberVO", memberVO);
		
		return "admin_memberModify";
	}
	//전송된 데이터 처리
	@PostMapping("/library/member/admin_update.do")
	public String submit(MemberVO memberVO, Model model,
			         HttpServletRequest request) {
		log.debug("<<관리자 회원권한 수정>> : " + memberVO);
		
		//회원권한 수정
		memberAdminService.updateByAdmin(memberVO);
		
		//View에 표시할 메시지
		model.addAttribute("message", "회원권한 수정 완료");
		model.addAttribute("url", 
				request.getContextPath()
				+"/library/member/admin_update.do?mem_num="+memberVO.getMem_num());
		
		return "common/resultView";
	}
}

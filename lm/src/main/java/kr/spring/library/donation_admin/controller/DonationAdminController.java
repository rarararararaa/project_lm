package kr.spring.library.donation_admin.controller;

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

import kr.spring.library.donation.vo.DonationVO;
import kr.spring.library.donation_admin.service.DonationAdminService;
import kr.spring.util.PagingUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class DonationAdminController {
	/*=================================
	 * 자바빈(VO) 초기화 
	 *=================================*/
	@Autowired
	private DonationAdminService donationAdminService;
	
	/*=================================
	 * 기증신청글 목록
	 *=================================*/
	@RequestMapping("/library/donationadmin/admin_donationlist.do")
	public ModelAndView getList(
			@RequestParam(value="pageNum",
			defaultValue="1") int currentPage,
			String keyfield,String keyword) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("keyfield", keyfield);
		map.put("keyword", keyword);

		//전체/검색 레코드수
		int count = donationAdminService.selectRowCount(map);

		log.debug("<<count>> : " + count);

		//페이지 처리
		PagingUtil page = 
				new PagingUtil(keyfield,keyword,currentPage,
						count,15,10,"admin_donationlist.do");

		List<DonationVO> list = null;
		if(count > 0) {
			map.put("start", page.getStartRow());
			map.put("end", page.getEndRow());

			list = donationAdminService.selectDonationAdminList(map);
		}

		ModelAndView mav = new ModelAndView();
		mav.setViewName("admin_donationList");
		mav.addObject("count", count);
		mav.addObject("list", list);
		mav.addObject("page", page.getPage());

		return mav;
	}
	
	/*=================================
	 * 기증품 상태변경 - 관리자
	 *=================================*/
	//수정 폼
	@GetMapping("/library/donationadmin/admin_donationupdate.do")
	public String form(@RequestParam int donation_num,Model model) {
		DonationVO donationVO = donationAdminService.selectDonationAdmin(donation_num);
		
		model.addAttribute("donationVO", donationVO);
		
		return "admin_donationModify";
	}
	//전송된 데이터 처리
	@PostMapping("/library/donationadmin/admin_donationupdate.do")
	public String submit(DonationVO donationVO, Model model,
			         HttpServletRequest request) {
		log.debug("<<관리자 기증상태 수정>> : " + donationVO);
		
		//기증상태 수정
		donationAdminService.updateByAdmin(donationVO);
		
		//View에 표시할 메시지
		model.addAttribute("message", "기증상태 수정 완료");
		model.addAttribute("url", 
				request.getContextPath()
				+"/library/donationadmin/admin_donationupdate.do?donation_num="+donationVO.getDonation_num());
		
		return "common/resultView";
	}
	
	/*=================================
	 * 기증신청 글 삭제
	 *=================================*/
	@RequestMapping("/library/donationadmin/admin_donationdelete.do")
	public String submitDelete(@RequestParam int donation_num) {
		log.debug("<<기증신청글삭제 - donation_num>> : " + donation_num);
		
		//기증신청글삭제
		donationAdminService.deleteDonationAdmin(donation_num);
		
		return "redirect:/library/donationadmin/admin_donationlist.do";
	}
	
	
	
	
	
}

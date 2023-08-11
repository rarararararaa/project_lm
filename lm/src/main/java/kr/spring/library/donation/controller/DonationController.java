package kr.spring.library.donation.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.spring.library.donation.service.DonationService;
import kr.spring.library.donation.vo.DonationVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class DonationController {
	
	@Autowired
	private DonationService donationService;
	
	@ModelAttribute
	public DonationVO initCommand() {
		return new DonationVO();
	}
	
	@RequestMapping("/library/donationMain.do")
	public String main() {
		return "bookDonationMain";
	}
	@GetMapping("/library/donationApply.do")
	public String form() {
		return "bookDonationApply";
	}
	@PostMapping("/library/donationApply.do")
	public String apply(@Valid DonationVO donationVO,BindingResult result, HttpServletRequest request, Model model) {
		log.debug("<<donationVO>> : " + donationVO);
		
		donationService.insertDonation(donationVO);
		
		model.addAttribute("message", "도서 기증 신청이 완료되었습니다.");
		model.addAttribute("url", request.getContextPath() + "/library/donationMain.do");

		return "common/resultView";
	}
}

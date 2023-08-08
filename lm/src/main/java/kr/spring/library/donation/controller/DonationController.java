package kr.spring.library.donation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DonationController {
	@RequestMapping("/library/donationMain.do")
	public String main() {
		return "bookDonationMain";
	}
}

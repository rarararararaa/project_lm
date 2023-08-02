package kr.spring.mypage.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Pattern;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.ibatis.annotations.Insert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.spring.member.service.MemberService;
import kr.spring.mypage.service.MyPageService;
import kr.spring.mypage.vo.MyPageVO;
import kr.spring.util.AuthCheckException;
import kr.spring.util.EncryptionPasswd;
import kr.spring.util.SaltGenerate;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class MyPageController {
	
	@Autowired
	private MyPageService mypageService;
	
	/*=======================
	 * 자바빈 초기화
	 *=======================*/
	@ModelAttribute
	public MyPageVO initCommand() {
		return new MyPageVO();
	}
	
	/*=======================
	 * 마이페이지
	 *=======================*/
	@RequestMapping("/lm/mypage/template/myPageMain.do")
	public String formMyPage() {
		return "myPageMain"; //타일스 설정의 식별자
	}
}

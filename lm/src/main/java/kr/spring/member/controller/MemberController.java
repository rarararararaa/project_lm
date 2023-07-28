package kr.spring.member.controller;

import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.spring.member.service.MemberService;
import kr.spring.member.vo.MemberVO;
import kr.spring.util.AuthCheckException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	/*=======================
	 * 자바빈 초기화
	 *=======================*/
	@ModelAttribute
	public MemberVO initCommand() {
		return new MemberVO();
	}
	
	//로그인 폼
	@RequestMapping("/lm/login/template/loginMain.do")
	public String formLogin() {
		return "loginMain"; //타일스 설정의 식별자
	}
	
	//로그인 데이터 처리
	@PostMapping("/lm/login/template/loginMain.do")
	public String submitLogin(@Valid MemberVO memberVO,
			                  BindingResult result,
			                  HttpSession session,
			                  Model model,
			                  HttpServletResponse response) {
		log.debug("<<회원로그인>> : " + memberVO);
		
		//id와 passwd 필드만 유효성 체크 결과 오류가 있으면 폼 호출
		if(result.hasFieldErrors("mem_id") || 
				result.hasFieldErrors("mem_passwd")) {
			return formLogin();
		}
		
		//로그인 체크(id,비밀번호 일치 여부 체크)
		MemberVO member = null;
		try {
			member = memberService.selectCheckMember(
					                    memberVO.getMem_id());
			boolean check = false;
			
			if(member!=null) {
				//비밀번호 일치 여부 체크
				check = member.isCheckedPassword(
						                memberVO.getMem_passwd());
			}
			if(check) {//인증 성공
				//===자동 로그인 체크 시작===
				boolean autoLogin = 
						memberVO.getAuto() != null 
						      && memberVO.getAuto().equals("on");
				if(autoLogin) {
					//자동로그인 체크를 한 경우
					String au_id = member.getMem_au_id();
					if(au_id==null) {
						//자동로그인 체크 식별값 생성
						au_id = UUID.randomUUID().toString();
						log.debug("<<au_id>> : " + au_id);
						memberService.updateAu_id(
								au_id, member.getMem_num());
					}
					
					//쿠키 생성
					Cookie auto_cookie = new Cookie("au-log",au_id);
					auto_cookie.setMaxAge(60*60*24*7);//쿠키 유효시간 1주일
					auto_cookie.setPath("/");
					
					response.addCookie(auto_cookie);
				}
				//===자동 로그인 체크 끝===
				
				//인증 성공, 로그인 처리
				session.setAttribute("user", member);
				
				log.debug("<<인증 성공>>");
				log.debug("<<id>> : " + member.getMem_id());
				log.debug("<<auth>> : " + member.getMem_auth());
				log.debug("<<au_id>> : " + member.getMem_au_id());
				
				if(member.getMem_auth() == 0) {
					//탈퇴회원
					return "redirect:/bookstore/template/bsMain.do";
				}else if(member.getMem_auth() == 1) { //정지회원
					return "redirect:/bookstore/template/bsMain.do";
				}else if(member.getMem_auth() == 2){ //휴면회원
					//아이디, 비밀번호 변경하게
					return "redirect:/bookstore/template/bsMain.do";
				}else if(member.getMem_auth() == 3){ //일반회원
					return "redirect:/bookstore/template/bsMain.do";
				}else if(member.getMem_auth() == 4){ //미인증 일반회원
					//미인증 시 인증 알림창 띄우고 인증 페이지
					return "redirect:/bookstore/template/bsMain.do";
				}else if(member.getMem_auth() == 9){ //관리자
					return "redirect:/bookstore/template/bsMain.do";
				}
			}
			//인증 실패
			throw new AuthCheckException();
		}catch(AuthCheckException e) {
			//인증 실패로 로그인폼 호출
			if(member!=null && member.getMem_auth()==1) {
				//정지회원 메시지 표시
				//나중에 정지 이유 추가
				result.reject("noAuthority");
			}else {
				//아이디,비밀번호 불일치
				result.reject("invalidIdOrPassword");
			}
			
			log.debug("<<인증 실패>>");
			
			return formLogin();
		}
	}
}

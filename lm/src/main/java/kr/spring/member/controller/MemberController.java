package kr.spring.member.controller;

import java.io.File;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
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
import org.springframework.web.servlet.ModelAndView;

import kr.spring.member.service.MemberService;
import kr.spring.member.vo.MemberVO;
import kr.spring.mypage.controller.MyPageController;
import kr.spring.mypage.email.Email;
import kr.spring.mypage.email.EmailSender;
import kr.spring.mypage.service.MyPageService;
import kr.spring.mypage.vo.MyPageVO;
import kr.spring.util.AuthCheckException;
import kr.spring.util.EmailUtil;
import kr.spring.util.EncryptionPasswd;
import kr.spring.util.FileUtil;
import kr.spring.util.SaltGenerate;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class MemberController {

	@Autowired
	private MemberService memberService;
	@Autowired
	private MyPageService mypageService;
	@Autowired
	private Email email;
	@Autowired
	private EmailSender emailSender;
	
	/*=======================
	 * 자바빈 초기화
	 *=======================*/
	@ModelAttribute
	public MemberVO initCommand() {
		return new MemberVO();
	}

	/*=======================
	 * 로그인
	 *=======================*/
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
			HttpServletRequest request,
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
			// 1.mem_id를 이용해 mem_salt 추출
			String salt = memberService.selectSalt(memberVO.getMem_id());
			// 2.mem_salt와 입력받은 passswd를 이용해 암호화된 mem_passwd를 구함
			String mem_passwd = EncryptionPasswd.encryptionPasswd(salt,memberVO.getMem_passwd());
			log.debug("mem_passwd"+mem_passwd);
			memberVO.setMem_passwd(mem_passwd);

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
					String mem_au_id = member.getMem_au_id();
					if(mem_au_id==null) {
						//자동로그인 체크 식별값 생성
						mem_au_id = UUID.randomUUID().toString();
						log.debug("<<mem_au_id>> : " + mem_au_id);
						memberService.updateMem_au_id(
								mem_au_id, member.getMem_num());
					}

					//쿠키 생성
					Cookie auto_cookie = new Cookie("mem_au-log",mem_au_id);
					auto_cookie.setMaxAge(60*60*24*7);//쿠키 유효시간 1주일
					auto_cookie.setPath("/");

					response.addCookie(auto_cookie);
				}
				//===자동 로그인 체크 끝===

				//인증 성공, 로그인 처리

				//로그인 날짜 입력
				memberService.updateLoginDate(member.getMem_num());

				//세션에 id, auth, num 적재
				session.setAttribute("mem_id", member.getMem_id());
				session.setAttribute("mem_auth", member.getMem_auth());
				session.setAttribute("mem_num", member.getMem_num());
				session.setAttribute("mem_grade", member.getMem_grade());//회원 등급

				log.debug("<<인증 성공>>");
				log.debug("<<mem_id>> : " + member.getMem_id());
				log.debug("<<mem_auth>> : " + member.getMem_auth());
				log.debug("<<mem_au_id>> : " + member.getMem_au_id());

				//hidden 값으로 받아온 로그인 홈페이지 데이터
				int lo = Integer.parseInt(request.getParameter("lo"));
				if(lo == 1) { //bs인 경우
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
				}else { //lib인 경우
					if(member.getMem_auth() == 0) {
						//탈퇴회원
						return "redirect:/library/template/libMain.do";
					}else if(member.getMem_auth() == 1) { //정지회원
						return "redirect:/library/template/libMain.do";
					}else if(member.getMem_auth() == 2){ //휴면회원
						//아이디, 비밀번호 변경하게
						return "redirect:/library/template/libMain.do";
					}else if(member.getMem_auth() == 3){ //일반회원
						return "redirect:/library/template/libMain.do";
					}else if(member.getMem_auth() == 4){ //미인증 일반회원
						//미인증 시 인증 알림창 띄우고 인증 페이지
						return "redirect:/library/template/libMain.do";
					}else if(member.getMem_auth() == 9){ //관리자
						return "redirect:/library/template/libMain.do";
					}
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
	/*=======================
	 * 로그아웃
	 *=======================*/
	@RequestMapping("/lm/logout/template/logoutMain.do")
	public String logout(HttpSession session,
			@RequestParam int lo,
			HttpServletResponse response) {
		//로그아웃
		session.invalidate();

		//===자동로그인 해제 시작===//
		//클라이언트 쿠키 처리
		Cookie auto_cookie = new Cookie("mem-au-log","");
		auto_cookie.setMaxAge(0); //쿠키의 삭제
		auto_cookie.setPath("/");

		response.addCookie(auto_cookie);
		//===자동로그인 해제 끝===//

		//hidden 값으로 받아온 로그아웃 홈페이지 데이터
		if(lo == 1) { //bs인 경우
			return "redirect:/bookstore/template/bsMain.do";
		}else { //lib인 경우
			return "redirect:/library/template/libMain.do";
		}
	}

	/*=======================
	 * 회원가입
	 *=======================*/
	//아이디 중복 체크
	@RequestMapping("/lm/register/template/confirmId.do")
	@ResponseBody
	public Map<String,String> confimId(@RequestParam String mem_id){
		log.debug("<<아이디 중복 체크>> : " + mem_id);
		Map<String,String> mapAjax = new HashMap<String,String>();
		MemberVO member = memberService.selectCheckMember(mem_id);
		if(member!=null) {
			//아이디 중복
			mapAjax.put("result", "idDuplicated");
		}else {
			if(!Pattern.matches("^[a-zA-Z0-9!@#$%^&*()_+=\\-{}\\[\\]:;\"'<>,.?/~`]{6,15}$", mem_id)) {
				//패턴 불일치
				mapAjax.put("result", "notMatchPattern");
			}else {
				//패턴 일치하면서 아이디 미중복
				mapAjax.put("result", "idNotFound");
			}
		}		
		return mapAjax;
	}
	//회원가입 폼 호출
	@GetMapping("/lm/register/template/registerMain.do")
	public String registerForm() {
		return "registerMain";
	}

	//회원가입 처리
	@PostMapping("/lm/register/template/registerMain.do")
	public String submit(@Valid MemberVO memberVO,@RequestParam int lo,BindingResult result,HttpServletRequest request,Model model) {
		log.debug("<<회원가입>> : " + memberVO);
		//유효성 체크 결과 오류가 있으면 폼 호출
		if(result.hasErrors()) {
			return registerForm();
		}
		String passwd = memberVO.getMem_passwd();
		//비밀번호 암호화 salt 생성
		String salt = SaltGenerate.getSalt();
		//입력받은 비밀번호 암호화 (salt + mem_passwd)
		String mem_passwd = EncryptionPasswd.encryptionPasswd(salt,passwd);
		//VO에 salt와 SHA-256 passwd 적재
		memberVO.setMem_salt(salt);
		memberVO.setMem_passwd(mem_passwd);

		//회원가입시 기본 프로필 사진 지정
		int rand = (int)Math.random()*7+1;//1~7까지의 난수
		byte[] readbyte = FileUtil.getBytes(
				request.getServletContext().getRealPath(
						"/image_basic/basic"+rand+".png"));
		memberVO.setMem_photo(readbyte);

		//회원가입 manage, detail, home에 데이터 insert
		memberService.insertMember(memberVO);
		memberService.insertHome(memberVO);
		model.addAttribute("accessMsg", "회원가입이 완료되었습니다.");
		if(lo==1) {
			return "common/notice_bs";
		}else {
			return "common/notice_lib";
		}
	}

	/*=======================
	 * ID찾기
	 *=======================*/

	//ID찾기 폼 호출
	@GetMapping("/lm/findId/template/findIdMain.do")
	public String findIdForm() {
		return "findIdMain";
	}

	//ID찾기 처리
	@PostMapping("/lm/findId/template/findIdMain.do")
	public String findId(@Valid MemberVO memberVO,@RequestParam int lo,BindingResult result, Model model) {
		log.debug("<<ID찾기>> : " + memberVO);

		String mem_id = memberService.findId(memberVO);

		//존재하는 아이디 판별
		if(mem_id != null) {
			model.addAttribute("accessMsg", "아이디는 " +mem_id+" 입니다.");
		}else {
			model.addAttribute("accessMsg", "존재하는 아이디가 없습니다.");
		}

		//리턴 헤더 지정
		if(lo==1) {
			return "common/notice_bs";
		}else {
			return "common/notice_lib";
		}
	}

	//passwd찾기 폼 호출
	@GetMapping("/lm/findPasswd/template/findPasswdMain.do")
	public String findPasswdForm() {
		return "findPasswdMain";
	}

	//passwd찾기 처리
	@PostMapping("/lm/findPasswd/template/findPasswdMain.do")
	public void emailCheckApply(@Valid MemberVO memberVO,@RequestParam int lo,Model model,HttpServletRequest request) throws Exception{

	}
	//비밀번호 찾기 처리
	@ResponseBody
	@PostMapping("/findPasswdCheck.do")
	public Map<String,String> emailCheck(@Valid MemberVO memberVO) throws Exception{
		log.debug("<<임시 비밀번호 발급>>");

		Map<String,String> mapJson = new HashMap<String,String>();
		//입력받은 데이터 검증
		MemberVO vo = memberService.changePasswdCheck(memberVO);
		log.debug("<<vo>> : " + vo);
		
		//mem_num 존재유무 판별
		if(vo!=null) {
			log.debug("<<임시 비밀번호 발급 조건 충족>>");
			//db에 저장된 salt값 가져오기
			String salt = mypageService.getSalt(vo.getMem_num());
			log.debug("<<salt : >>" + salt);
			//임시 비밀번호 생성
			HashMap<String,String> passmap = EmailUtil.createPasswd(salt);
			//VO에 적재
			vo.setMem_passwd_sha(passmap.get("mem_passwd_sha"));
			//암호화된 임시 비밀번호 db에 저장
			memberService.changePasswd(vo);
			//이메일 제목,수신자,내용 입력
			email.setSubject("lm그룹 통합 홈페이지 임시 비밀번호 발급");
			email.setContent("다음 임시 비밀번호로 로그인 해주세요. " + passmap.get("mem_passwd"));
			email.setReceiver(memberVO.getMem_email());
			//이메일 발송
			emailSender.sendEmail(email);
			mapJson.put("result", "임시 비밀번호가 이메일로 전송되었습니다.");
			return mapJson;
		}else {
			log.debug("<<입력 데이터 불일치>>");
			mapJson.put("result", "입력한 정보로 가입되어있는 아이디가 없습니다.");
			return mapJson;
		}
	}
}

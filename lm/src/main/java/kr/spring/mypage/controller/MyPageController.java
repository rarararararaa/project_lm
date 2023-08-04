package kr.spring.mypage.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
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
import org.springframework.web.servlet.ModelAndView;

import kr.spring.library.board_announce.vo.BoardAnnounceVO;
import kr.spring.member.service.MemberService;
import kr.spring.member.vo.MemberVO;
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
	@RequestMapping("/lm/mypage/main/myPageMain.do")
	public String formMyPage() {
		return "myPageMain"; //타일스 설정의 식별자
	}
	@GetMapping("/lm/mypage/main/myPageMain.do")
	public String myPage(@Valid MyPageVO mypage,
			@RequestParam int lo,BindingResult result,
			Model model,HttpSession session) {
		
		//intercepter에서 마이페이지 공용 데이터 처리하여 SELECT SQL 실행 후 반환. AppConfig.java,MyPageHeaderInterceptor.java
		//이후 주문내역 데이터 SELECT 하여 model에 addAttribute
		//페이지 추가 시 appconfig에 추가 해줘야 함
		
		//model을 이용한 파라미터 세팅
		//model.addAttribute("",null);
		

		return "myPageMain";
	}
	
	/*=======================
	 * 문의내역
	 *=======================*/
	@RequestMapping("/lm/mypage/asklist/askListMain.do")
	public String askList() {
		return "askListMain"; //타일스 설정의 식별자
	}
	@GetMapping("/lm/mypage/asklist/askListMain.do")
	public String askListHandle(@RequestParam int lo) {

		return "myPageMain";
	}
	/*=======================
	 * 대출/반납내역
	 *=======================*/
	@RequestMapping("/lm/mypage/checkoutreturnlist/checkOutReturnListMain.do")
	public String checkOutReturnList() {
		return "checkOutReturnListMain"; //타일스 설정의 식별자
	}
	@GetMapping("/lm/mypage/checkoutreturnlist/checkOutReturnListMain.do")
	public String checkOutReturnListHandle(@RequestParam int lo) {

		return "myPageMain";
	}
	/*=======================
	 * 희망도서신청내역
	 *=======================*/
	@RequestMapping("/lm/mypage/wantbooklist/wantBookListMain.do")
	public String wantBookList() {
		return "wantBookListMain"; //타일스 설정의 식별자
	}
	@GetMapping("/lm/mypage/wantbooklist/wantBookListMain.do")
	public String wantBookListHandle(@RequestParam int lo) {

		return "myPageMain";
	}
	/*=======================
	 * 프로그램신청내역
	 *=======================*/
	@RequestMapping("/lm/mypage/programapplylist/programApplyListMain.do")
	public String programApplyList() {
		return "programApplyListMain"; //타일스 설정의 식별자
	}
	@GetMapping("/lm/mypage/programapplylist/programApplyListMain.do")
	public String programApplyListHandle(@RequestParam int lo) {

		return "myPageMain";
	}
	/*=======================
	 * 책기증신청내역
	 *=======================*/
	@RequestMapping("/lm/mypage/donatebooklist/donateBookListMain.do")
	public String donateBookList() {
		return "donateBookListMain"; //타일스 설정의 식별자
	}
	@GetMapping("/lm/mypage/donatebooklist/donateBookListMain.do")
	public String donateBookListHandle(@RequestParam int lo) {

		return "myPageMain";
	}
	/*=======================
	 * 시설이용신청내역
	 *=======================*/
	@RequestMapping("/lm/mypage/facilityapplylist/facilityApplyListMain.do")
	public String facilityApplyList() {
		return "facilityApplyListMain"; //타일스 설정의 식별자
	}
	@GetMapping("/lm/mypage/facilityapplylist/facilityApplyListMain.do")
	public String facilityApplyListHandle(@RequestParam int lo) {

		return "myPageMain";
	}
	/*=======================
	 * 책예약내역
	 *=======================*/
	@RequestMapping("/lm/mypage/bookreservationlist/bookReservationListMain.do")
	public String bookReservationList() {
		return "bookReservationListMain"; //타일스 설정의 식별자
	}
	@GetMapping("/lm/mypage/bookreservationlist/bookReservationListMain.do")
	public String bookReservationListHandle(@RequestParam int lo) {

		return "myPageMain";
	}
	/*=======================
	 * 분실도서신고내역
	 *=======================*/
	@RequestMapping("/lm/mypage/booklostlist/bookLostListMain.do")
	public String bookLostList() {
		return "bookLostListMain"; //타일스 설정의 식별자
	}
	@GetMapping("/lm/mypage/booklostlist/bookLostListMain.do")
	public String bookLostListHandle(@RequestParam int lo) {

		return "myPageMain";
	}
	/*=======================
	 * 이벤트참여내역
	 *=======================*/
	@RequestMapping("/lm/mypage/eventparticipatelist/eventParticipateListMain.do")
	public String eventParticipateList() {
		return "eventParticipateListMain"; //타일스 설정의 식별자
	}
	@GetMapping("/lm/mypage/eventparticipatelist/eventParticipateListMain.do")
	public String eventParticipateListHandle(@RequestParam int lo) {

		return "myPageMain";
	}
	/*=======================
	 * 중고서적등록내역
	 *=======================*/
	@RequestMapping("/lm/mypage/usedbookapplylist/usedBookApplyListMain.do")
	public String usedBookApplyList() {
		return "usedBookApplyListMain"; //타일스 설정의 식별자
	}
	@GetMapping("/lm/mypage/usedbookapplylist/usedBookApplyListMain.do")
	public String usedBookApplyListHandle(@RequestParam int lo) {

		return "myPageMain";
	}
	/*=======================
	 * 찜한도서내역
	 *=======================*/
	@RequestMapping("/lm/mypage/zzimbooklist/zzimBookListMain.do")
	public String zzimBookList() {
		return "zzimBookListMain"; //타일스 설정의 식별자
	}
	@GetMapping("/lm/mypage/zzimbooklist/zzimBookListMain.do")
	public String zzimBookListHandle(@RequestParam int lo) {

		return "myPageMain";
	}
	/*=======================
	 * 도서후기
	 *=======================*/
	@RequestMapping("/lm/mypage/bookwritelist/bookWriteListMain.do")
	public String bookWriteList() {
		return "bookWriteListMain"; //타일스 설정의 식별자
	}
	@GetMapping("/lm/mypage/bookwritelist/bookWriteListMain.do")
	public String bookWriteListHandle(@RequestParam int lo) {

		return "myPageMain";
	}
	/*=======================
	 * 독후감작성
	 *=======================*/
	@RequestMapping("/lm/mypage/bookreportlist/bookReportListMain.do")
	public String bookReportList() {
		return "bookReportListMain"; //타일스 설정의 식별자
	}
	@GetMapping("/lm/mypage/bookreportlist/bookReportListMain.do")
	public String bookReportListHandle(@RequestParam int lo) {

		return "myPageMain";
	}
	/*=======================
	 * 보유쿠폰
	 *=======================*/
	@RequestMapping("/lm/mypage/couponlist/couponListMain.do")
	public String couponList() {
		return "couponListMain"; //타일스 설정의 식별자
	}
	@GetMapping("/lm/mypage/couponlist/couponListMain.do")
	public String couponListHandle(@RequestParam int lo) {

		return "myPageMain";
	}
	/*=======================
	 * 회원정보수정
	 *=======================*/
	@RequestMapping("/lm/mypage/myedit/myEditMain.do")
	public String myEdit() {
		return "myEditMain"; //타일스 설정의 식별자
	}
	@GetMapping("/lm/mypage/myedit/myEditMain.do")
	public String myEditHandle(@RequestParam int lo) {

		return "myPageMain";
	}
	/*=======================
	 * 사용가능포인트정보
	 *=======================*/
	@RequestMapping("/lm/mypage/pointinfo/pointInfoMain.do")
	public String pointInfo() {
		return "pointInfoMain"; //타일스 설정의 식별자
	}
	@GetMapping("/lm/mypage/pointinfo/pointInfoMain.do")
	public String pointInfoHandle(@RequestParam int lo) {

		return "myPageMain";
	}
	/*=======================
	 * 등급정보
	 *=======================*/
	@RequestMapping("/lm/mypage/gradeinfo/gradeInfoMain.do")
	public String gradeInfo() {
		return "gradeInfoMain"; //타일스 설정의 식별자
	}
	@GetMapping("/lm/mypage/gradeinfo/gradeInfoMain.do")
	public String gradeInfoHandle(@RequestParam int lo) {

		return "myPageMain";
	}
}

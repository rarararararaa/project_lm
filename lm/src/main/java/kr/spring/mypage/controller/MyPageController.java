package kr.spring.mypage.controller;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.spring.mypage.email.Email;
import kr.spring.mypage.email.EmailSender;
import kr.spring.mypage.service.MyPageService;
import kr.spring.mypage.vo.MyPageVO;
import kr.spring.util.EmailUtil;
import kr.spring.util.EncryptionPasswd;
import kr.spring.util.FileUtil;
import kr.spring.util.PagingUtil;
import kr.spring.util.SaltGenerate;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class MyPageController {

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
	public MyPageVO initCommand() {
		return new MyPageVO();
	}

	//intercepter에서 마이페이지 공용 데이터 처리하여 SELECT SQL 실행 후 반환. AppConfig.java,MyPageHeaderInterceptor.java
	//페이지 추가 시 appconfig에 추가 해줘야 함

	/*=======================
	 * 마이페이지 + 주문내역
	 *=======================*/
	@GetMapping("/lm/mypage/main/myPageMain.do")
	public String myPage(@RequestParam(value = "pageNum", defaultValue = "1") int currentPage, 
			HttpServletRequest request,Model model,@RequestParam int lo,String keyfield, String keyword,
			@RequestParam(value = "order", defaultValue = "1") int order) {
		HttpSession session = request.getSession();
		if(session.getAttribute("mem_num") == null) {
			return "redirect:/lm/login/template/loginMain.do?lo="+lo;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("keyfield", keyfield);
		map.put("keyword", keyword);
		map.put("mem_num", session.getAttribute("mem_num"));

		// 전체/검색 레코드수
		int count = mypageService.selectRowCountOrderList(map);

		log.debug("<<ALL-board-count>> : " + count);

		// 페이지처리 부가적인 파라미터
		PagingUtil page = new PagingUtil(currentPage, count, 10, 10, "myPageMain.do", "&order=" + order+"&lo="+lo);

		List<MyPageVO> list = null;
		if (count > 0) {
			map.put("order", order);
			map.put("start", page.getStartRow());
			map.put("end", page.getEndRow());
			//주문내역 가져오기
			list = mypageService.getOrderList(map);
		}
		//제목 길이가 길면 잘라내고 .. 추가
		if(list != null) {
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).getStore_product_title().length() >= 90) {
					String new_title = list.get(i).getStore_product_title().substring(0, 85)+"..";
					list.get(i).setStore_product_title(new_title);
				}
			}
			//금액 천단위 , 처리
			for (int i = 0; i < list.size(); i++) {
				String price = list.get(i).getOrder_total_price();
				price = price.replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",");
				list.get(i).setOrder_total_price(price);
			}
		}
		model.addAttribute("myPageMain");
		model.addAttribute("count", count);
		model.addAttribute("list", list);
		model.addAttribute("page", page.getPage());

		return "myPageMain"; //타일스 설정의 식별자
	}
	@PostMapping("/lm/mypage/main/myPageMain.do")
	public String myPageHandle(@Valid MyPageVO mypageVO,@RequestParam int lo,BindingResult result, Model model,HttpServletRequest request) {
		log.debug("<<order-status-update2>>");
		HttpSession session = request.getSession();
		//주문번호의 배송상태를 구매확정으로 수정
		mypageService.setOrderStatus(mypageVO.getOrder_status_confirm(),(Integer)session.getAttribute("mem_num"));

		model.addAttribute("lo",lo);
		return "common/notice_edit";
	}
	/*=======================
	 * 주문 내역 상세
	 *=======================*/

	@GetMapping("/lm/mypage/orderlist/orderListMain.do")
	public ModelAndView formMyPage(@RequestParam(value = "pageNum", defaultValue = "1") int currentPage, 
			HttpServletRequest request,Model model,@RequestParam int lo,@RequestParam int order_num,String keyfield, String keyword,
			@RequestParam(value = "order", defaultValue = "1") int order, @Valid MyPageVO mypageVO) {

		HttpSession session = request.getSession();
		if(session.getAttribute("mem_num") == null) {
			ModelAndView mav = new ModelAndView();
			mav.setViewName("redirect:/lm/login/template/loginMain.do?lo="+lo);
			return mav;
		}
		Integer mem_num = (Integer)session.getAttribute("mem_num");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("keyfield", keyfield);
		map.put("keyword", keyword);
		map.put("order_num",order_num);
		map.put("mem_num",mem_num);

		// 전체/검색 레코드수
		int count = mypageService.selectRowCountOrderListDetail(map);

		log.debug("<<ALL-board-count>> : " + count);

		// 페이지처리 부가적인 파라미터
		PagingUtil page = new PagingUtil(currentPage, count, 10, 10, "orderListMain.do", "&order=" + order+"&lo="+lo);

		List<MyPageVO> list = null;
		if (count > 0) {
			map.put("order", order);
			map.put("start", page.getStartRow());
			map.put("end", page.getEndRow());
			//주문내역상세 가져오기
			list = mypageService.getOrderListDetail(map);
		}
		if(list != null) {
			//금액 천단위 , 처리
			for (int i = 0; i < list.size(); i++) {
				String price = list.get(i).getOrder_product_price();
				price = price.replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",");
				list.get(i).setOrder_product_price(price);
			}
		}

		if(list==null) {

			ModelAndView mav = new ModelAndView();
			mav.setViewName("myPageMain");
			mav.addObject("error", "일치하는 주문 내역이 없습니다.");
			return mav;
		}
		//배송지, 결제 정보
		mypageVO = mypageService.getHomeOrderList(order_num,mem_num);
		if(mypageVO.getOrder_total_price()!=null) {
			String price2 = mypageVO.getOrder_total_price();
			price2 = price2.replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",");
			mypageVO.setOrder_total_price(price2);
		}
		ModelAndView mav = new ModelAndView();
		mav.setViewName("orderListMain");
		mav.addObject("count", count);
		mav.addObject("list", list);
		mav.addObject("mypageVO", mypageVO);
		mav.addObject("page", page.getPage());
		return mav;
	}
	/*=======================
	 * 문의 내역
	 *=======================*/
	@GetMapping("/lm/mypage/asklist/askListMain.do")
	public ModelAndView askList(@RequestParam(value = "pageNum", defaultValue = "1") int currentPage, 
			HttpServletRequest request,Model model,@RequestParam int lo,String keyfield, String keyword,
			@RequestParam(value = "order", defaultValue = "1") int order) {
		HttpSession session = request.getSession();
		if(session.getAttribute("mem_num") == null) {
			ModelAndView mav = new ModelAndView();
			mav.setViewName("redirect:/lm/login/template/loginMain.do?lo="+lo);
			return mav;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("keyfield", keyfield);
		map.put("keyword", keyword);
		map.put("mem_num", session.getAttribute("mem_num"));

		// 전체/검색 레코드수
		int count = mypageService.selectRowCountAskList(map);

		log.debug("<<ALL-board-count>> : " + count);

		// 페이지처리 부가적인 파라미터
		PagingUtil page = new PagingUtil(currentPage, count, 10, 10, "askListMain.do", "&order=" + order+"&lo="+lo);

		List<MyPageVO> list = null;
		if (count > 0) {
			map.put("order", order);
			map.put("start", page.getStartRow());
			map.put("end", page.getEndRow());
			//문의내역 가져오기
			list = mypageService.getAskList(map);
		}

		ModelAndView mav = new ModelAndView();
		mav.setViewName("askListMain");
		mav.addObject("count", count);
		mav.addObject("list", list);
		mav.addObject("page", page.getPage());
		return mav;
	}
	/*=======================
	 * 대출/반납내역
	 *=======================*/
	@GetMapping("/lm/mypage/checkoutreturnlist/checkOutReturnListMain.do")
	public ModelAndView checkOutReturnList(@RequestParam(value = "pageNum", defaultValue = "1") int currentPage, 
			HttpServletRequest request,Model model,@RequestParam int lo,String keyfield, String keyword,
			@RequestParam(value = "order", defaultValue = "1") int order) {
		HttpSession session = request.getSession();
		if(session.getAttribute("mem_num") == null) {
			ModelAndView mav = new ModelAndView();
			mav.setViewName("redirect:/lm/login/template/loginMain.do?lo="+lo);
			return mav;
		}

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("keyfield", keyfield);
		map.put("keyword", keyword);
		map.put("mem_num", session.getAttribute("mem_num"));

		// 전체/검색 레코드수
		int count = mypageService.selectRowCountCheckList(map);

		log.debug("<<ALL-board-count>> : " + count);

		// 페이지처리 부가적인 파라미터
		PagingUtil page = new PagingUtil(currentPage, count, 10, 10, "checkOutReturnListMain.do", "&order=" + order+"&lo="+lo);

		List<MyPageVO> list = null;
		if (count > 0) {
			map.put("order", order);
			map.put("start", page.getStartRow());
			map.put("end", page.getEndRow());
			//대출/반납내역 가져오기
			list = mypageService.getCheckList(map);
		}
		ModelAndView mav = new ModelAndView();
		mav.setViewName("checkOutReturnListMain");
		mav.addObject("count", count);
		mav.addObject("list", list);
		mav.addObject("page", page.getPage());
		return mav;
	}
	/*=======================
	 * 희망도서신청내역
	 *=======================*/
	@GetMapping("/lm/mypage/wantbooklist/wantBookListMain.do")
	public ModelAndView wantBookList(@RequestParam(value = "pageNum", defaultValue = "1") int currentPage, 
			HttpServletRequest request,Model model,@RequestParam int lo,String keyfield, String keyword,
			@RequestParam(value = "order", defaultValue = "1") int order) {
		HttpSession session = request.getSession();

		if(session.getAttribute("mem_num") == null) {
			ModelAndView mav = new ModelAndView();
			mav.setViewName("redirect:/lm/login/template/loginMain.do?lo="+lo);
			return mav;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("keyfield", keyfield);
		map.put("keyword", keyword);
		map.put("mem_num", session.getAttribute("mem_num"));

		// 전체/검색 레코드수
		int count = mypageService.selectRowCountWantBookList(map);

		log.debug("<<ALL-board-count>> : " + count);

		// 페이지처리 부가적인 파라미터
		PagingUtil page = new PagingUtil(currentPage, count, 10, 10, "wantBookListMain.do", "&order=" + order+"&lo="+lo);

		List<MyPageVO> list = null;
		if (count > 0) {
			map.put("order", order);
			map.put("start", page.getStartRow());
			map.put("end", page.getEndRow());
			//희망도서신청내역 가져오기
			list = mypageService.getWantBookList(map);
		}
		ModelAndView mav = new ModelAndView();
		mav.setViewName("wantBookListMain");
		mav.addObject("count", count);
		mav.addObject("list", list);
		mav.addObject("page", page.getPage());
		return mav;
	}
	/*=======================
	 * 프로그램신청내역
	 *=======================*/
	@GetMapping("/lm/mypage/programapplylist/programApplyListMain.do")
	public ModelAndView programApplyList(@RequestParam(value = "pageNum", defaultValue = "1") int currentPage, 
			HttpServletRequest request,Model model,@RequestParam int lo,String keyfield, String keyword,
			@RequestParam(value = "order", defaultValue = "1") int order) {
		HttpSession session = request.getSession();

		if(session.getAttribute("mem_num") == null) {
			ModelAndView mav = new ModelAndView();
			mav.setViewName("redirect:/lm/login/template/loginMain.do?lo="+lo);
			return mav;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("keyfield", keyfield);
		map.put("keyword", keyword);
		map.put("mem_num", session.getAttribute("mem_num"));

		// 전체/검색 레코드수
		int count = mypageService.selectRowCountProgramList(map);

		log.debug("<<ALL-board-count>> : " + count);

		// 페이지처리 부가적인 파라미터
		PagingUtil page = new PagingUtil(currentPage, count, 10, 10, "programApplyListMain.do", "&order=" + order+"&lo="+lo);

		List<MyPageVO> list = null;
		if (count > 0) {
			map.put("order", order);
			map.put("start", page.getStartRow());
			map.put("end", page.getEndRow());
			//프로그램신청내역 가져오기
			list = mypageService.getProgramList(map);
		}
		ModelAndView mav = new ModelAndView();
		mav.setViewName("programApplyListMain");
		mav.addObject("count", count);
		mav.addObject("list", list);
		mav.addObject("page", page.getPage());
		return mav;
	}
	/*=======================
	 * 시설이용신청내역
	 *=======================*/
	@GetMapping("/lm/mypage/facilityapplylist/facilityApplyListMain.do")
	public ModelAndView facilityApplyList(@RequestParam(value = "pageNum", defaultValue = "1") int currentPage, 
			HttpServletRequest request,Model model,@RequestParam int lo,String keyfield, String keyword,
			@RequestParam(value = "order", defaultValue = "1") int order) {
		HttpSession session = request.getSession();

		if(session.getAttribute("mem_num") == null) {
			ModelAndView mav = new ModelAndView();
			mav.setViewName("redirect:/lm/login/template/loginMain.do?lo="+lo);
			return mav;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("keyfield", keyfield);
		map.put("keyword", keyword);
		map.put("mem_num", session.getAttribute("mem_num"));

		// 전체/검색 레코드수
		int count = mypageService.selectRowCountFacilityList(map);

		log.debug("<<ALL-board-count>> : " + count);

		// 페이지처리 부가적인 파라미터
		PagingUtil page = new PagingUtil(currentPage, count, 10, 10, "facilityApplyListMain.do", "&order=" + order+"&lo="+lo);

		List<MyPageVO> list = null;
		if (count > 0) {
			map.put("order", order);
			map.put("start", page.getStartRow());
			map.put("end", page.getEndRow());
			//시설이용신청내역 가져오기
			list = mypageService.getFacilityList(map);
		}
		ModelAndView mav = new ModelAndView();
		mav.setViewName("facilityApplyListMain");
		mav.addObject("count", count);
		mav.addObject("list", list);
		mav.addObject("page", page.getPage());
		return mav;
	}
	/*=======================
	 * 도서예약내역
	 *=======================*/
	@GetMapping("/lm/mypage/bookreservationlist/bookReservationListMain.do")
	public ModelAndView bookReservationList(@RequestParam(value = "pageNum", defaultValue = "1") int currentPage, 
			HttpServletRequest request,Model model,@RequestParam int lo,String keyfield, String keyword,
			@RequestParam(value = "order", defaultValue = "1") int order) {
		HttpSession session = request.getSession();
		if(session.getAttribute("mem_num") == null) {
			ModelAndView mav = new ModelAndView();
			mav.setViewName("redirect:/lm/login/template/loginMain.do?lo="+lo);
			return mav;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("keyfield", keyfield);
		map.put("keyword", keyword);
		map.put("mem_num", session.getAttribute("mem_num"));

		// 전체/검색 레코드수
		int count = mypageService.selectRowCountBookReservationList(map);

		log.debug("<<ALL-board-count>> : " + count);

		// 페이지처리 부가적인 파라미터
		PagingUtil page = new PagingUtil(currentPage, count, 10, 10, "bookReservationListMain.do", "&order=" + order+"&lo="+lo);

		List<MyPageVO> list = null;
		if (count > 0) {
			map.put("order", order);
			map.put("start", page.getStartRow());
			map.put("end", page.getEndRow());
			//도서예약신청내역 가져오기
			list = mypageService.getBookReservationList(map);
		}
		ModelAndView mav = new ModelAndView();
		mav.setViewName("bookReservationListMain");
		mav.addObject("count", count);
		mav.addObject("list", list);
		mav.addObject("page", page.getPage());
		return mav;
	}
	/*=======================
	 * 분실도서신고내역
	 *=======================*/
	@GetMapping("/lm/mypage/booklostlist/bookLostListMain.do")
	public ModelAndView bookLostList(@RequestParam(value = "pageNum", defaultValue = "1") int currentPage, 
			HttpServletRequest request,Model model,@RequestParam int lo,String keyfield, String keyword,
			@RequestParam(value = "order", defaultValue = "1") int order) {
		HttpSession session = request.getSession();
		if(session.getAttribute("mem_num") == null) {
			ModelAndView mav = new ModelAndView();
			mav.setViewName("redirect:/lm/login/template/loginMain.do?lo="+lo);
			return mav;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("keyfield", keyfield);
		map.put("keyword", keyword);
		map.put("mem_num", session.getAttribute("mem_num"));

		// 전체/검색 레코드수
		int count = mypageService.selectRowCountBookLostList(map);

		log.debug("<<ALL-board-count>> : " + count);

		// 페이지처리 부가적인 파라미터
		PagingUtil page = new PagingUtil(currentPage, count, 10, 10, "bookLostListMain.do", "&order=" + order+"&lo="+lo);

		List<MyPageVO> list = null;
		if (count > 0) {
			map.put("order", order);
			map.put("start", page.getStartRow());
			map.put("end", page.getEndRow());
			//도서예약신청내역 가져오기
			list = mypageService.getBookLostList(map);
		}
		log.debug("list : " + list);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("bookLostListMain");
		mav.addObject("count", count);
		mav.addObject("list", list);
		mav.addObject("page", page.getPage());
		return mav;
	}
	/*=======================
	 * 포인트 로그
	 *=======================*/
	@GetMapping("/lm/mypage/pointinfo/pointInfoMain.do")
	public ModelAndView pointInfoList(@RequestParam(value = "pageNum", defaultValue = "1") int currentPage, 
			HttpServletRequest request,Model model,@RequestParam int lo,String keyfield, String keyword,
			@RequestParam(value = "order", defaultValue = "1") int order) {
		HttpSession session = request.getSession();
		if(session.getAttribute("mem_num") == null) {
			ModelAndView mav = new ModelAndView();
			mav.setViewName("redirect:/lm/login/template/loginMain.do?lo="+lo);
			return mav;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("keyfield", keyfield);
		map.put("keyword", keyword);
		map.put("mem_num", session.getAttribute("mem_num"));

		// 전체/검색 레코드수
		int count = mypageService.selectRowCountPointInfo(map);

		log.debug("<<ALL-board-count>> : " + count);

		// 페이지처리 부가적인 파라미터
		PagingUtil page = new PagingUtil(currentPage, count, 10, 10, "pointInfoMain.do", "&order=" + order+"&lo="+lo);

		List<MyPageVO> list = null;
		if (count > 0) {
			map.put("order", order);
			map.put("start", page.getStartRow());
			map.put("end", page.getEndRow());
			//도서예약신청내역 가져오기
			list = mypageService.getPointInfo(map);
		}
		log.debug("list : " + list);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("pointInfoMain");
		mav.addObject("count", count);
		mav.addObject("list", list);
		mav.addObject("page", page.getPage());
		return mav;
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

		return "eventParticipateListMain";
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

		return "usedBookApplyListMain";
	}
	/*=======================
	 * 찜한도서내역
	 *=======================*/
	@GetMapping("/lm/mypage/zzimbooklist/zzimBookListMain.do")
	public ModelAndView zzimBookList(@RequestParam(value = "pageNum", defaultValue = "1") int currentPage, 
			HttpServletRequest request,Model model,@RequestParam int lo,String keyfield, String keyword,
			@RequestParam(value = "order", defaultValue = "1") int order) {
		HttpSession session = request.getSession();
		if(session.getAttribute("mem_num") == null) {
			ModelAndView mav = new ModelAndView();
			mav.setViewName("redirect:/lm/login/template/loginMain.do?lo="+lo);
			return mav;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("keyfield", keyfield);
		map.put("keyword", keyword);
		map.put("mem_num", session.getAttribute("mem_num"));

		// 전체/검색 레코드수
		int count = mypageService.selectRowCountZzimBookList(map);

		log.debug("<<ALL-board-count>> : " + count);

		// 페이지처리 부가적인 파라미터
		PagingUtil page = new PagingUtil(currentPage, count, 10, 10, "zzimBookListMain.do", "&order=" + order+"&lo="+lo);

		List<MyPageVO> list = null;
		if (count > 0) {
			map.put("order", order);
			map.put("start", page.getStartRow());
			map.put("end", page.getEndRow());
			//도서예약신청내역 가져오기
			list = mypageService.getZzimBookList(map);
		}
		if(list != null) {
			//금액 천단위 , 처리
			for (int i = 0; i < list.size(); i++) {
				String price = list.get(i).getStore_product_pricesales();
				price = price.replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",");
				list.get(i).setStore_product_pricesales(price);
			}
		}
		log.debug("list : " + list);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("zzimBookListMain");
		mav.addObject("count", count);
		mav.addObject("list", list);
		mav.addObject("page", page.getPage());
		return mav;
	}
	/*=======================
	 * 도서후기
	 *=======================*/
	@GetMapping("/lm/mypage/bookwritelist/bookWriteListMain.do")
	public ModelAndView bookWriteList(@RequestParam(value = "pageNum", defaultValue = "1") int currentPage, 
			HttpServletRequest request,Model model,@RequestParam int lo,String keyfield, String keyword,
			@RequestParam(value = "order", defaultValue = "1") int order) {
		HttpSession session = request.getSession();
		if(session.getAttribute("mem_num") == null) {
			ModelAndView mav = new ModelAndView();
			mav.setViewName("redirect:/lm/login/template/loginMain.do?lo="+lo);
			return mav;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("keyfield", keyfield);
		map.put("keyword", keyword);
		map.put("mem_num", session.getAttribute("mem_num"));

		// 전체/검색 레코드수
		int count = mypageService.selectRowCountBookWriteList(map);

		log.debug("<<ALL-board-count>> : " + count);

		// 페이지처리 부가적인 파라미터
		PagingUtil page = new PagingUtil(currentPage, count, 10, 10, "bookWriteListMain.do", "&order=" + order+"&lo="+lo);

		List<MyPageVO> list = null;
		if (count > 0) {
			map.put("order", order);
			map.put("start", page.getStartRow());
			map.put("end", page.getEndRow());
			//도서예약신청내역 가져오기
			list = mypageService.getBookWriteList(map);
		}
		log.debug("list : " + list);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("bookWriteListMain");
		mav.addObject("count", count);
		mav.addObject("list", list);
		mav.addObject("page", page.getPage());
		return mav;
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

		return "bookReportListMain";
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


		return "couponListMain";
	}
	/*=======================
	 * 회원정보수정
	 *=======================*/
	//수정 폼 호출
	@RequestMapping("/lm/mypage/myedit/myEditMain.do")
	public String myEdit(@Valid MyPageVO mypageVO,HttpServletRequest request,@RequestParam int lo,Model model) {
		//로그인 여부 체크
		HttpSession session = request.getSession();
		Integer mem_num = (Integer)session.getAttribute("mem_num");
		if(session.getAttribute("mem_num") == null) {
			ModelAndView mav = new ModelAndView();
			return "redirect:/lm/login/template/loginMain.do?lo="+lo;
		}
		//name,email,cell 가져오기
		mypageVO = mypageService.getMyEdit(mem_num);
		//store_member_home 가져오기
		List<MyPageVO> list = null;
		list = mypageService.getMyHome(mem_num);
		log.debug(list.size()+"");
		model.addAttribute("mypageVO", mypageVO);
		model.addAttribute("list",list);
		model.addAttribute("length",list.size());
		return "myEditMain"; //타일스 설정의 식별자
	}
	@PostMapping("/lm/mypage/myedit/myEditMain.do")
	public String myEditHandle(@Valid MyPageVO mypageVO,@RequestParam int lo,BindingResult result, Model model,HttpServletRequest request) {
		log.debug("<<회원정보수정>> : " + mypageVO);
		//유효성 체크 결과 오류가 있으면 폼 호출
		if(result.hasErrors()) {
			return "myEditMain";
		}
		//mem_num 가져오기
		HttpSession session = request.getSession();
		Integer mem_num = (Integer)session.getAttribute("mem_num");
		//VO에 mem_num 등록
		mypageVO.setMem_num(mem_num);
		//비밀번호가 변경 시도 여부 체크
		if(!mypageVO.getMem_new_passwd().equals("")) {
			//저장되어있는 salt 값 가져오기
			String salt = mypageService.getSalt(mem_num);
			//입력받은 비밀번호 암호화 (salt + mem_passwd)
			String mem_new_passwd = EncryptionPasswd.encryptionPasswd(salt,mypageVO.getMem_new_passwd());
			//VO에 SHA-256 passwd로 업데이트
			mypageVO.setMem_new_passwd(mem_new_passwd);
		}
		boolean check = false;
		//입력된 회원정보만 수정
		if(mypageVO.getMem_photo().length!=0) {
			mypageService.updatePhoto(mypageVO);
			model.addAttribute("accessMsg", "이미지 등록 완료.");
			check = true;
		}
		if(!mypageVO.getMem_new_passwd().equals("")) {
			mypageService.updatePasswd(mypageVO);
			model.addAttribute("accessMsg", "비밀번호 수정 완료.");
			check = true;
		}
		if(!mypageVO.getMem_new_email().equals("")) {
			mypageService.updateEmail(mypageVO);
			//이메일 변경 시 미인증이므로 auth=4 변경
			mypageVO.setMem_auth(4);
			mypageService.updateAuth(mypageVO);
			//세션에 auth 적용;
			session.setAttribute("mem_auth", 4);
			model.addAttribute("accessMsg", "이메일 수정 완료.");
			check = true;
		}
		if(!mypageVO.getMem_new_cell().equals("")) {
			mypageService.updateCell(mypageVO);

			model.addAttribute("accessMsg", "전화번호 수정 완료.");
			check = true;
		}
		if(!mypageVO.getHome_title().equals("")) {
			mypageService.insertHome(mypageVO);

			model.addAttribute("accessMsg", "주소지 추가 완료.");
			check = true;
		}
		//개인정보가 수정되었으면 modify_date 수정
		if(check) {
			mypageService.updateModifyDate(mypageVO);
		}
		model.addAttribute("lo",lo);
		return "redirect:/lm/mypage/myedit/myEditMain.do?lo="+lo;
	}
	/*=======================
	 * 이메일 인증 처리
	 *=======================*/
	@PostMapping("/emailCheck.do")
	@ResponseBody
	public Map<String,String> emailCheck(@Valid MyPageVO mypageVO) throws Exception{
		log.debug("<<이메일 인증>> : " + mypageVO.getUserEmail());

		//8자리 인증코드 생성
		String data = EmailUtil.createKey();
		log.debug("<<임시 비밀번호>> : " + data);
		//이메일 제목,수신자,내용 입력
		email.setSubject("lm그룹 통합 홈페이지 이메일 인증 요청.");
		email.setContent("다음 이메일 인증 코드를 입력해 주세요. " + data);
		email.setReceiver(mypageVO.getUserEmail());
		//이메일 발송
		emailSender.sendEmail(email);

		Map<String,String> mapJson = new HashMap<String,String>();
		mapJson.put("result",data);
		return mapJson;
	}
	/*=======================
	 * 기본 배송지 변경
	 *=======================*/
	@PostMapping("/addDefaultHome.do")
	@ResponseBody
	public Map<String,String> addDefaultHome(@Valid MyPageVO mypageVO, HttpServletRequest request) throws Exception{
		log.debug("<<기본 배송지 변경 : >>"+mypageVO.getHome_num());
		//mem_num 가져오기
		HttpSession session = request.getSession();
		Integer mem_num = (Integer)session.getAttribute("mem_num");
		int home_num = mypageVO.getHome_num();
		mypageService.addDefaultHome(home_num,mem_num);
		Map<String,String> mapJson = new HashMap<String,String>();
		mapJson.put("result", "기본 배송지 변경 완료");
		return mapJson;
	}

	/*=======================
	 * 이메일 인증 적용(auth 변경) 처리
	 *=======================*/
	@PostMapping("/emailCheckApply.do")
	@ResponseBody
	public Map<String,String> emailCheckApply(@Valid MyPageVO mypageVO, HttpServletRequest request) throws Exception{
		log.debug("<<이메일 인증 적용>> : " + mypageVO.getUserEmailApply());
		if(mypageVO.getUserEmailApply().equals("true")) {
			//mem_num 가져오기
			HttpSession session = request.getSession();
			Integer mem_num = (Integer)session.getAttribute("mem_num");
			//VO에 mem_num 등록
			mypageVO.setMem_num(mem_num);
			//이메일 인증 시 auth=3
			mypageVO.setMem_auth(3);
			mypageService.updateAuth(mypageVO);
			//세션에 auth 적용
			session.setAttribute("mem_auth", 3);
			Map<String,String> mapJson = new HashMap<String,String>();
			mapJson.put("result", "이메일 인증 완료");
			return mapJson;
		}
		Map<String,String> mapJson = new HashMap<String,String>();
		mapJson.put("result", "네트워크 오류발생");
		return mapJson;
	}
	/*=======================
	 * 비밀번호 변경 처리
	 *=======================*/
	@PostMapping("/passwdChangeApply.do")
	@ResponseBody
	public Map<String,String> passwdChangeApply(@Valid MyPageVO mypageVO, HttpServletRequest request) throws Exception{
		log.debug("<<비밀번호 찾기>> : ");
		//mem_num 가져오기
		HttpSession session = request.getSession();
		Integer mem_num = (Integer)session.getAttribute("mem_num");
		//VO에 mem_num 등록
		mypageVO.setMem_num(mem_num);

		//입력받은 비밀번호 sha-256+salt 암호화 처리 시작
		String passwd = mypageVO.getMem_passwd();
		//db에 저장된 salt값 가져오기
		String salt = mypageService.getSalt(mem_num);
		//입력받은 비밀번호 암호화 (salt + mem_passwd)
		String mem_passwd = EncryptionPasswd.encryptionPasswd(salt,passwd);
		//VO에 SHA-256 passwd 적재
		mypageVO.setMem_passwd(mem_passwd);

		mypageService.passwdChangeApply(mypageVO);

		Map<String,String> mapJson = new HashMap<String,String>();
		mapJson.put("result", "비밀번호 변경 완료");
		return mapJson;

	}
	/*=======================
	 * 사진 관련
	 *=======================*/
	@RequestMapping("/lm/mypage/myedit/photoView.do")
	public String getProfile(HttpSession session,
			HttpServletRequest request,
			Model model,@RequestParam int mem_num) {
		log.debug("<<photoView>> : " + mem_num);
		MyPageVO mypageVO = mypageService.getPhoto(mem_num);
		viewProfile(mypageVO,request,model);
		return "imageView";
		//<img src="${pageContext.request.contextPath}/lm/mypage/myedit/photoView.do?mem_num=${mem_num}" class="view-photo" width="500" height="500">
		//형태로 이미지 사용 가능
	}
	@RequestMapping("/lm/mypage/facilityapplylist/facilityView.do")
	public String getFacility(HttpSession session,
			HttpServletRequest request,
			Model model,@RequestParam int facility_num) {
		log.debug("<<facilityView>> : " + facility_num);
		MyPageVO mypageVO = mypageService.getFacility(facility_num);
		viewFacility(mypageVO,request,model);
		return "imageView";
	}
	//프로필 사진 처리를 위한 공통 코드
	public void viewProfile(MyPageVO mypageVO,
			HttpServletRequest request,
			Model model) {
		if(mypageVO.getMem_photo()==null 
				//|| memberVO.getPhoto_name()==null
				) {
			//기본 이미지 읽기
			byte[] readbyte = FileUtil.getBytes(
					request.getServletContext().getRealPath(
							"/image_basic/basic3.png"));
			model.addAttribute("imageFile", readbyte);
			model.addAttribute("filename", "lm_photo.png");
		}else {//업로드한 프로필 사진이 있는 경우
			model.addAttribute("imageFile", mypageVO.getMem_photo());
			model.addAttribute("filename", "lm_photo.png");
		}
	}
	//시설 사진 처리
	public void viewFacility(MyPageVO mypageVO,
			HttpServletRequest request,
			Model model) {
		model.addAttribute("imageFile", mypageVO.getFacility_image());
		model.addAttribute("filename", mypageVO.getFacility_imagename());
	}
	/*=======================
	 * 등급정보
	 *=======================*/
	@RequestMapping("/lm/mypage/gradeinfo/gradeInfoMain.do")
	public String gradeInfo(@RequestParam int lo,Model model,HttpSession session,MyPageVO mypageVO) {
		log.debug("<<등급정보>> : " + mypageVO);
		Integer mem_num = (Integer)session.getAttribute("mem_num");
		//로그인 여부 체크
		if(session.getAttribute("mem_num") == null) {
			ModelAndView mav = new ModelAndView();
			return "redirect:/lm/login/template/loginMain.do?lo="+lo;
		}
		//회원의 총 주문 금액
		int mem_order_price=0;
		//회원의 등급 점수
		int mem_grade_point=0;
		if(mypageService.getOrderPrice(mem_num) != null) { //int형은 null이 불가능하여 String으로 받은 후 int로 저장
			mem_order_price = Integer.parseInt(mypageService.getOrderPrice(mem_num));
			mem_grade_point = mem_order_price/100;
		}
		//천단위 , 처리
		String mem_order_price_str = (mem_order_price+"").replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",")+" 원";
		if(mem_order_price_str.equals("0 원")) {
			mem_order_price_str = "구매 내역이 없습니다.";
		}
		model.addAttribute("mem_order_price_str",mem_order_price_str);
		model.addAttribute("mem_grade_point",mem_grade_point);

		return "gradeInfoMain";
	}
	/*=======================
	 * 회원 탈퇴
	 *=======================*/
	@GetMapping("/lm/mypage/memberout/memberOutMain.do")
	public String memberOut(MyPageVO mypageVO,HttpServletRequest request,Model model,@RequestParam int lo) {
		HttpSession session = request.getSession();
		//로그인 여부 체크
		if(session.getAttribute("mem_num") == null) {
			return "redirect:/lm/login/template/loginMain.do?lo="+lo;
		}
		return "memberOutMain";
	}
	@PostMapping("/lm/mypage/memberout/memberOutMain.do")
	@ResponseBody
	public String memberOutHandle(@RequestParam int lo,MyPageVO mypageVO,HttpServletRequest request,Model model,BindingResult result) {
		log.debug("<<회원탈퇴>> : " + mypageVO);

		HttpSession session = request.getSession();
		Integer mem_num = (Integer)session.getAttribute("mem_num");

		//저장되어있는 salt 값 가져오기
		String salt = mypageService.getSalt(mem_num);
		//입력받은 비밀번호 암호화 (salt + mem_passwd)
		String mem_passwd = EncryptionPasswd.encryptionPasswd(salt,mypageVO.getMem_passwd());
		//VO에 SHA-256 passwd로 업데이트
		mypageVO.setMem_passwd(mem_passwd);
		//입력받은 비밀번호, 아이디 체크 후 탈퇴
		if(mypageService.memberOutCheck(mypageVO) == mem_num) {
			//회원정보 삭제
			mypageService.memberOut(mem_num);
			//로그아웃
			session.invalidate();
		}
		model.addAttribute("accessMsg", "회원 탈퇴 완료.");
		return "bsMain";
	}
}


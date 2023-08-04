package kr.spring.bookstore.event.controller;

import java.sql.Date;
import java.time.LocalDate;
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

//import kr.spring.board.vo.BoardFavVO;
import kr.spring.bookstore.event.service.BsEventService;
import kr.spring.bookstore.event.vo.BsAttendancePointVO;
import kr.spring.bookstore.event.vo.BsAttendanceVO;
import kr.spring.bookstore.event.vo.BsEventVO;
import kr.spring.member.vo.MemberVO;
import kr.spring.util.PagingUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class BsEventController {
	@Autowired
	private BsEventService bsEventService;

	/*========================
	 * 자바빈(VO) 초기화
	 *========================*/
	@ModelAttribute
	public BsEventVO intitCommand() {
		return new BsEventVO();
	}

	/*========================
	 * 게시판 글 등록
	 *========================*/
	//등록 폼
	@GetMapping("/bookstore/event/write.do")
	public String form() {
		return "bsEventWrite";
	}
	//전송된 데이터 처리
	@PostMapping("/bookstore/event/write.do")
	public String submit(@Valid BsEventVO vo,
			BindingResult result, Model model,
			HttpServletRequest request, HttpSession session) {
		log.debug("<<게시판 글쓰기 >> : " + vo);

	
		//상품 이미지 유효성 체크 //MultipartFile -> byte[]로 변환한 경우 파일을 업로드 하지 않으면 byte[]는 생성되고 length는 0이다. 
		if(vo.getEvent_img_small().length == 0) {
			result.rejectValue("img_Small", "required"); 
		}

		//용량체크 - byte[] 이므로 용량 체크 가능 
		if(vo.getEvent_img_small().length >= 5*1024*1024) { //5 MB // 자바빈의 필드명, 에러 코드, 에러문구에 전달할 값, 기본 오류(에러) 문구
			result.rejectValue("img_Small", "limitIploadSize", new Object[] {"5MB"}, null); 
		}


		//글쓰기
		bsEventService.insertEvent(vo);

		model.addAttribute("message", "글쓰기가 완료되었습니다.");
		model.addAttribute("url", 
				request.getContextPath()+"/bookstore/event/adminlist.do");

		return "common/resultView";
	}
	
	//글 수정
	//수정 폼 호출
	@GetMapping("/bookstore/event/update.do")
	public String formUpdate(@RequestParam int event_board_num, Model model) {
		BsEventVO bsEventVO = bsEventService.selectEvent(event_board_num);
		log.debug("<<글 수정 - bsEventVO>> : " + bsEventVO);
		model.addAttribute("bsEventVO", bsEventVO);
		return "bsEventModify";
	}
	//전달된 데이터 처리
	@PostMapping("/bookstore/event/update.do")
	public String submitUpdate(@Valid BsEventVO bsEventVO, BindingResult result, HttpServletRequest request, Model model) {
		log.debug("<<글 수정버튼 클릭 - bsEventVO>> : " + bsEventVO);
		
		//유효성 체크 결과 오류가 있으면 폼 호출
		if(result.hasErrors()) {
			return "bsEventModify";
		}
		
		//글 수정
		bsEventService.updateEvent(bsEventVO);
		
		//View에 표시할 메시지
		model.addAttribute("message", "글 수정 완료!");
		model.addAttribute("url", request.getContextPath()+"/bookstore/event/adminlist.do");
		
		return "common/resultView";
	}
	
	/*========================
	 * 이벤트 목록
	 *========================*/
	//이벤트 리스트 - 일반 사용자 조회용
	@RequestMapping("/bookstore/event/list.do")
	public ModelAndView EventList(@RequestParam(value="order",defaultValue="1") int order,
									@RequestParam(value="pageNum", defaultValue="1") int CurrentPage,
									String keyfield, String keyword) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("keyfield", keyfield);
		map.put("keyword", keyword);
		//status가 1보다 큰 이벤트만 조회 | 1:미표시, 2:진행중,  3:종료
		map.put("event_board_status", 1);
		
		//전체/검색 레코드 수
		int count = bsEventService.selectEventCount(map);
		//페이지 처리
		PagingUtil page = new PagingUtil(keyfield, keyword, CurrentPage, count, 20, 10, "admin_list.do");
				
		List<BsEventVO> list = null;
		if(count > 0) {
			map.put("order",order);
			map.put("start", page.getStartRow());
			map.put("end", page.getEndRow());
			
			list = bsEventService.selectEventList(map);
		}
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("bsEventList");
		mav.addObject("count", count);
		mav.addObject("list", list);
		mav.addObject("page", page);
		
		return mav;
	}
	
	//이미지 출력
	@RequestMapping("/bookstore/event/imageView.do")
	public ModelAndView viewImage(@RequestParam int event_board_num,
			                      @RequestParam int event_board_type) {
		BsEventVO eventVO = bsEventService.selectEvent(event_board_num);
		
		log.debug("<<eventVO>>" + eventVO);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("imageView");
		if(event_board_type==1) {
			mav.addObject("imageFile", eventVO.getEvent_img_small());
			mav.addObject("filename", "bannerSmall.jpg");
		}else if(event_board_type==2) {
			mav.addObject("imageFile", eventVO.getEvent_img_big());
			mav.addObject("filename", "bannerBig.jpg");
		}
		return mav;
	}



}

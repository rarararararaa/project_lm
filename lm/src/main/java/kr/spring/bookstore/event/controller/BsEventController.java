package kr.spring.bookstore.event.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Collections;
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

import kr.spring.bookstore.event.service.BsEventService;
import kr.spring.bookstore.event.vo.BsEventReplyVO;
import kr.spring.bookstore.event.vo.BsEventVO;
import kr.spring.bookstore.event.vo.BsQuizVO;
import kr.spring.bookstore.product.service.ProductService;
import kr.spring.bookstore.product.vo.ProductVO;
import kr.spring.util.PagingUtil;
import kr.spring.util.StringUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class BsEventController {
	@Autowired
	private BsEventService bsEventService;
	@Autowired
	private ProductService productService;

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
			result.rejectValue("event_img_small", "required"); 
		}

		//용량체크 - byte[] 이므로 용량 체크 가능 
		if(vo.getEvent_img_small().length >= 5*1024*1024) { //5 MB // 자바빈의 필드명, 에러 코드, 에러문구에 전달할 값, 기본 오류(에러) 문구
			result.rejectValue("img_Small", "limitIploadSize", new Object[] {"5MB"}, null); 
		}
		
		//유효성 체크 결과 오류가 있으면 폼 호출
		if(result.hasErrors()) {
			return form();
		}

		//글쓰기
		bsEventService.insertEvent(vo);

		model.addAttribute("message", "글쓰기가 완료되었습니다.");
		model.addAttribute("url", 
				request.getContextPath()+"/bookstore/event/list.do");

		return "common/resultView";
	}
	/*========================
	 * 게시판 글 수정
	 *========================*/
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
		log.debug("date"+bsEventVO.getEvent_date_end());
		//글 수정
		bsEventService.updateEvent(bsEventVO);
		
		//View에 표시할 메시지
		model.addAttribute("message", "글 수정 완료!");
		model.addAttribute("url", request.getContextPath()+"/bookstore/event/list.do");
		
		return "common/resultView";
	}
	
	/*========================
	 * 게시판 글 상세
	 *========================*/
	@RequestMapping("/bookstore/event/detail.do")
	public ModelAndView getDetail(@RequestParam int event_board_num) {
		log.debug("<<글 상세 - event_board_num>> : " + event_board_num);
		
		//글 조회수 증가
		bsEventService.updateEventHit(event_board_num);
		
		//글 상세
		BsEventVO event = bsEventService.selectEvent(event_board_num);
		
		//제목에 태그 불허용
		event.setEvent_title(StringUtil.useNoHtml(event.getEvent_title()));
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("bsEventDetail");
		
		int store_product_num = event.getStore_product_num();
		if(store_product_num != 0) {
			String isbn = bsEventService.selectEventItemIsbn(store_product_num);
			
			ProductVO product = productService.selectProduct(isbn);
			mav.addObject("product", product);
			
		}
		mav.addObject("event", event);
		return mav;
	}
	
	/*========================
	 * 게시판 글 삭제
	 *========================*/
	@RequestMapping("/bookstore/event/delete.do")
	public String submitDelete(@RequestParam int event_board_num) {
		log.debug("<<글삭제 - event_board_num>> : " + event_board_num);
		BsEventVO event = bsEventService.selectEvent(event_board_num);
	
		//삭제
		bsEventService.deleteEventBoard(event_board_num);
		
		return "redirect:/bookstore/event/list.do";
	}
	
	
	/*========================
	 * 이벤트 목록
	 *========================*/
	//이벤트 리스트 - 일반 사용자 조회용
	@RequestMapping("/bookstore/event/list.do")
	public ModelAndView EventList(@RequestParam(value="order",defaultValue="1") int order,
									@RequestParam(value="pageNum", defaultValue="1") int CurrentPage,
									String keyfield, String keyword) {
		bsEventService.updateEndList();
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("order",order);
		map.put("keyfield", keyfield);
		map.put("keyword", keyword);
		//status가 1보다 큰 이벤트만 조회! | 1:미표시, 2:진행중,  3:종료
		map.put("event_board_status", 1);
		
		//전체/검색 레코드 수
		int count = bsEventService.selectEventCount(map);
		//페이지 처리
		PagingUtil page = new PagingUtil(keyfield, keyword, CurrentPage, count, 20, 10, "list.do","&order="+order);
				
		List<BsEventVO> list = null;
		if(count > 0) {
			map.put("start", page.getStartRow());
			map.put("end", page.getEndRow());
			
			list = bsEventService.selectEventList(map);
		}
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("bsEventList");
		mav.addObject("count", count);
		mav.addObject("list", list);
		mav.addObject("page", page.getPage());
		
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
	
	//퀴즈 클릭
	@RequestMapping("/bookstore/event/writeQuiz.do")
	@ResponseBody
	public Map<String, Object> writeQuiz(HttpSession session, HttpServletRequest request,
										BsQuizVO quizVO, @RequestParam int event_board_num, @RequestParam int answer){
		Map<String, Object> mapJson = new HashMap<String, Object>();
		Integer mem_num = (Integer) session.getAttribute("mem_num");
		log.debug("<<mem_num>> : " + mem_num);
		log.debug("<<event_board_num>> : " + event_board_num);

		// 종료된 이벤트 확인을 위해
		BsEventVO event = bsEventService.selectEvent(event_board_num);
		int event_status = event.getEvent_board_status();

		if (mem_num == null) {
			// 로그인X
			mapJson.put("status", "logOut");
		} else {
			// 로그인 O
			// 퀴즈 status 확인
			if (event_status == 3) {
				mapJson.put("status", "fin");
			} else if (event_status == 2) {
				quizVO.setEvent_board_num(event_board_num);
				quizVO.setMem_num(mem_num);

				BsQuizVO checkQuizVO = new BsQuizVO();
				checkQuizVO = bsEventService.selectQuizStatus(quizVO);
				if (checkQuizVO != null) {
					mapJson.put("status", "already");
				} else {
					// 위에서 사용
					// BsEventVO event = bsEventService.selectEvent(event_board_num);

					if (event.getEvent_quiz_an() == answer) {
						Map<String, Object> mapPoint = new HashMap<String, Object>();
						mapPoint.put("mem_num", mem_num);
						mapPoint.put("addPoint", 50);
						bsEventService.updateMemberPoint(mapPoint);

						bsEventService.insetQuizStatus(quizVO);

						mapJson.put("status", "success");
					} else {
						mapJson.put("status", "wrongAnswer");

					}
				}
			}

		}
		return mapJson;
	}
	
	/*========================
	 * 댓글 등록
	 *========================*/
	@RequestMapping("/bookstore/event/writeReply.do")
	@ResponseBody
	public Map<String, String> writeReply(BsEventReplyVO eventReplyVO,
										  HttpSession session, HttpServletRequest request){
		log.debug("<<댓글 등록>> : " + eventReplyVO);

		Map<String,String> mapJson = new HashMap<String,String>();
		Integer mem_num = (Integer) session.getAttribute("mem_num");
		
		if(mem_num == null) {//로그인이 되지 않은 경우
			mapJson.put("result", "logout");
		}else {
			//회원번호 등록
			eventReplyVO.setMem_num(mem_num);
			//댓글 등록
			bsEventService.insertReply(eventReplyVO);
			mapJson.put("result", "success");
		}
		return mapJson;
	}
	
	/*========================
	 * 댓글 목록
	 *========================*/
	@RequestMapping("/bookstore/event/listReply.do")
	@ResponseBody
	public Map<String,Object> getList(@RequestParam(value="pageNum", defaultValue="1") int currentPage,
									  @RequestParam(value="rowCount", defaultValue="10") int rowCount,
									  @RequestParam int event_board_num,
									  HttpSession session){
		log.debug("<<currentPage>> : " + currentPage);
		log.debug("<<event_board_num>> : " + event_board_num);
		
		Map<String,Object> map =  new HashMap<String,Object>();
		map.put("event_board_num", event_board_num);
		
		Integer mem_num = (Integer) session.getAttribute("mem_num");
		
		//전체 레코드수
		int count = bsEventService.selectRowCountReply(map);
		
		//페이지 처리
		PagingUtil page = new PagingUtil(currentPage, count,rowCount,1,null);
		
		List<BsEventReplyVO> list = null;
		if(count > 0) {
			map.put("start", page.getStartRow());
			map.put("end", page.getEndRow());
			list = bsEventService.selecListReply(map);
		}else {
			list = Collections.emptyList();
		}
		
		Map<String,Object> mapJson = new HashMap<String,Object>();
		mapJson.put("count",count);
		mapJson.put("list", list);
		
		//====로그인 한 회원정보 셋팅====
		if(mem_num != null) {
			mapJson.put("mem_num", mem_num);
		}
		
		return mapJson;
	}
	
	/*========================
	 * 댓글 삭제
	 *========================*/
	@RequestMapping("/bookstore/event/deleteReply.do")
	@ResponseBody
	public Map<String,String> deleteReply(@RequestParam int reply_num, HttpSession session){
		log.debug("<<re_num>> : " + reply_num);
		
		Map<String,String> mapJson = new HashMap<String,String>();
		Integer mem_num = (Integer) session.getAttribute("mem_num");
		
		BsEventReplyVO db_reply = bsEventService.selectReply(reply_num);
		if(mem_num == null) {
			//로그인이 되지 않은 경우
			mapJson.put("result", "logout");
		}else if(mem_num!=null && mem_num == db_reply.getMem_num()) {
			//로그인한 회원번호와 작성자 회원번호 일치
			bsEventService.deleteReply(reply_num);
			mapJson.put("result", "success");
		}else {
			//로그인한 회원번호와 작성자 회원번호 불일치
			mapJson.put("result", "wrongAccess");
		}
		return mapJson;
	}
	
	/*========================
	 * 댓글 수정
	 *========================*/
	@RequestMapping("/bookstore/event/updateReply.do")
	@ResponseBody
	public Map<String,String> modifyReply(BsEventReplyVO eventReplyVO,
										  HttpSession session,HttpServletRequest request){
		log.debug("<<BsEventReplyVO>> : " + eventReplyVO);
		
		Map<String,String> mapJson = new HashMap<String,String>();
		Integer mem_num = (Integer) session.getAttribute("mem_num");
		
		BsEventReplyVO db_reply = bsEventService.selectReply(eventReplyVO.getReply_num());
		if(mem_num==null) {//로그인이 되지 않은 경우
			mapJson.put("result", "logout");
		}else if(mem_num != null && mem_num ==db_reply.getMem_num()) {
			//로그인 회원번호와 작성자 회원번호가 일치
		
			//댓글 수정
			bsEventService.updateReply(eventReplyVO);
			mapJson.put("result", "success");
		}else {
			//로그인 회원번호와 작성자 회원번호가 불일치
			mapJson.put("result", "wrongAccess");
		}
		
		return mapJson;
	}
}

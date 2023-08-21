package kr.spring.bookstore.service.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
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
import org.springframework.web.servlet.ModelAndView;

import kr.spring.bookstore.payment.vo.BookStorePaymentOrderVO;
import kr.spring.bookstore.product.service.ProductService;
import kr.spring.bookstore.product.vo.ProductVO;
import kr.spring.bookstore.service.service.ServiceService;
import kr.spring.bookstore.service.vo.AnnounceVO;
import kr.spring.bookstore.service.vo.AnswerVO;
import kr.spring.bookstore.service.vo.FaqVO;
import kr.spring.bookstore.service.vo.OrderDetailVO;
import kr.spring.bookstore.service.vo.AskVO;
import kr.spring.library.memberadmin.service.MemberAdminService;
import kr.spring.member.vo.MemberVO;
import kr.spring.util.PagingUtil;
import kr.spring.util.StringUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class ServiceController {

	@Autowired
	private ServiceService serviceService;
	
	@Autowired
	private MemberAdminService memberAdminService;
	
	@Autowired
	private ProductService productService;

	@ModelAttribute
	public AnnounceVO initCommand() {
		return new AnnounceVO();
	}
	@ModelAttribute
	public FaqVO initCommand2() {
		return new FaqVO();
	}
	@ModelAttribute
	public MemberVO initCommand3() {
		return new MemberVO();
	}
	@ModelAttribute
	public ProductVO initCommand4() {
		return new ProductVO();
	}
	@ModelAttribute
	public AskVO initCommand5() {
		return new AskVO();
	}
	@ModelAttribute
	public AnswerVO initCommand6() {
		return new AnswerVO();
	}
	@ModelAttribute
	public BookStorePaymentOrderVO initCommand7() {
		return new BookStorePaymentOrderVO();
	}
	@ModelAttribute
	public OrderDetailVO initCommand8() {
		return new OrderDetailVO();
	}
	
	@RequestMapping("/bookstore/service/main.do")
	public String serviceMain() {
		return "service";
	}
	@RequestMapping("/bookstore/service/announceList.do")
	public ModelAndView selectAnnounce(@RequestParam(value="pageNum",
		      defaultValue="1") int currentPage,
		@RequestParam(value="order",
		      defaultValue="1") int order) {

		Map<String,Object> map = 
				new HashMap<String,Object>();
		
		int count = serviceService.selectRowCount(map);
		
		log.debug("<<count>> : " + count);
		PagingUtil page = 
				new PagingUtil(
						currentPage,count,20,10,
						"announceList.do","&order="+order);
		
		List<AnnounceVO> list = null;
		if(count > 0) {
			map.put("order",order);
			map.put("start", page.getStartRow());
			map.put("end", page.getEndRow());
			
			list = serviceService.selectAnnounceList(map);			
		}
		ModelAndView mav = new ModelAndView();
		mav.setViewName("announceList");
		mav.addObject("count", count);
		mav.addObject("list", list);
		mav.addObject("page", page.getPage());
		return mav;
	}
	@GetMapping("/bookstore/service/announceWrite.do")
	public String formAnnounce() {
		return "announceWrite";
	}
	
	/*========================
	 * 게시판 글상세
	 *========================*/
	@RequestMapping("/bookstore/service/announceDetail.do")
	public ModelAndView getAnnounceDetail(@RequestParam int board_num) {
		log.debug("<<글상세 - board_num>> : " + board_num);
		
		//해당 글의 조회수 증가
		//serviceService.updateHit(board_num);
		
		//글상세 
		AnnounceVO board = serviceService.selectAnnounce(board_num);
		
		//제목에 태그를 허용하지 않음
		board.setBoard_title(StringUtil.useNoHtml(board.getBoard_title()));
		

		                         //뷰 이름    속성명   속성값
		return new ModelAndView("announceView","board",board);
	}
	@PostMapping("/bookstore/service/announceWrite.do")
	public String writeAnnounce(@Valid AnnounceVO announceVO,
								BindingResult result,
								HttpSession session,
								HttpServletRequest request,
								Model model) {
		log.debug("<<글 작성>> : " + announceVO);

		//유효성 체크 결과 오류가 있으면 폼 호출
		if(result.hasErrors()) {
			return formAnnounce();
		}
		//글 작성
		if(announceVO.getBoard_filename() == null) {
			announceVO.setBoard_filename("");
		}
		serviceService.insertAnnounce(announceVO);
		model.addAttribute("message", "글쓰기가 완료되었습니다.");
		model.addAttribute("url", 
				request.getContextPath()+"/bookstore/service/announceList.do");
		
		return "common/resultView";
	}
	/*========================
	 * 자주묻는질문
	 *========================*/
	@GetMapping("/bookstore/service/faqWrite.do")
	public String formFaq() {
		return "faqWrite";
	}
	@PostMapping("/bookstore/service/faqWrite.do")
	public String writeFaq(@Valid FaqVO faqVO,
								BindingResult result,
								HttpSession session,
								HttpServletRequest request,
								Model model) {
		log.debug("<<글 작성>> : " + faqVO);
		
		faqVO.setFaq_content(StringUtil.useBrNoHtml(faqVO.getFaq_content()));

		//유효성 체크 결과 오류가 있으면 폼 호출.
		if(result.hasErrors()) {
			return formFaq();
		}
		//글 작성
		
		serviceService.insertFaq(faqVO);
		model.addAttribute("message", "글쓰기가 완료되었습니다.");
		model.addAttribute("url", 
				request.getContextPath()+"/bookstore/service/faqList.do");
		
		return "common/resultView";
	}
	@RequestMapping("/bookstore/service/faqList.do")
	public ModelAndView selectFaq(@RequestParam(value="pageNum",defaultValue="1") int currentPage,
								  @RequestParam(value="order",defaultValue="1") int order,
								  String faq_category){

		Map<String,Object> map = 
				new HashMap<String,Object>();
		if(faq_category==null) {
			faq_category="1";
		}
		map.put("faq_category", Integer.parseInt(faq_category));
		log.debug("<<faq_category>> : " + Integer.parseInt(faq_category));
		//전체/검색 레코드수
		int count = serviceService.selectRowCountFaq(map);
		log.debug("<<count>> : " + count);
		count = 1;
		PagingUtil page = 
				new PagingUtil(
						currentPage,count,20,10,
						"faqList.do","&order="+order);
		List<FaqVO> list = null;
		if(count > 0) {
			map.put("order",order);
			map.put("start", page.getStartRow());
			map.put("end", page.getEndRow());
			
			list = serviceService.selectFaqList(map);			
		}
		ModelAndView mav = new ModelAndView();
		mav.setViewName("faqList");
		mav.addObject("count", count);
		mav.addObject("list", list);
		log.debug("<<list>> : " + list);
		mav.addObject("page", page.getPage());
		return mav;
	}
	@RequestMapping("/bookstore/adminMain.do")
	public String main() {
		return "bsadmin";
	}
	@RequestMapping("/bookstore/admin_list.do")
	public ModelAndView memberList(
			@RequestParam(value="pageNum",
			defaultValue="1") int currentPage,
			String keyfield,String keyword) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("keyfield", keyfield);
		map.put("keyword", keyword);

		//전체/검색 레코드수
		int count = memberAdminService.selectRowCount(map);

		log.debug("<<count>> : " + count);

		//페이지 처리
		PagingUtil page = 
				new PagingUtil(keyfield,keyword,currentPage,
						count,20,10,"admin_list.do");

		List<MemberVO> list = null;
		if(count > 0) {
			map.put("start", page.getStartRow());
			map.put("end", page.getEndRow());

			list = memberAdminService.selectList(map);
		}

		ModelAndView mav = new ModelAndView();
		mav.setViewName("adminMemberList");
		mav.addObject("count", count);
		mav.addObject("list", list);
		mav.addObject("page", page.getPage());

		return mav;
	}
	
	@GetMapping("/bookstore/admin_update.do")
	public String form(@RequestParam int mem_num,Model model) {
		MemberVO memberVO = memberAdminService.selectMember(mem_num);
		
		log.debug("<<memberVO>> : " + memberVO);
		model.addAttribute("memberVO", memberVO);
		
		return "adminMemberModify";
	}
	//전송된 데이터 처리
	@PostMapping("/bookstore/admin_update.do")
	public String submit(MemberVO memberVO, Model model,
			         HttpServletRequest request) {
		log.debug("<<관리자 회원권한 수정>> : " + memberVO);
		
		//회원권한 수정
		memberAdminService.updateByAdmin(memberVO);
		
		//View에 표시할 메시지
		model.addAttribute("message", "회원권한 수정 완료");
		model.addAttribute("url", 
				request.getContextPath()
				+"/bookstore/member/admin_update.do?mem_num="+memberVO.getMem_num());
		
		return "common/resultView";
	}
	
	@RequestMapping("/bookstore/adminProductList.do")
	public ModelAndView productList(
			@RequestParam(value="pageNum",
			defaultValue="1") int currentPage,
			String keyfield,String keyword) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("keyfield", keyfield);
		map.put("keyword", keyword);

		//전체/검색 레코드수
		int count = productService.selectRowCount(map);

		log.debug("<<count>> : " + count);

		//페이지 처리
		PagingUtil page = 
				new PagingUtil(keyfield,keyword,currentPage,
						count,20,10,"adminProductList.do");

		List<ProductVO> list = null;
		if(count > 0) {
			map.put("start", page.getStartRow());
			map.put("end", page.getEndRow());

			list = productService.selectList(map);
		}

		ModelAndView mav = new ModelAndView();
		mav.setViewName("bsAdminProductList");
		mav.addObject("count", count);
		mav.addObject("list", list);
		mav.addObject("page", page.getPage());

		return mav;
	}
	//문의하기 메인
	@RequestMapping("/bookstore/service/mainDesk.do")
	public ModelAndView mainDesk(HttpSession session) {
		Integer mem_num = (Integer)session.getAttribute("mem_num");
		Integer mem_auth = (Integer)session.getAttribute("mem_auth");
		
		List<AskVO> list = null;
		
		if(mem_num!=null) {
			if(mem_auth==9) {
				list = serviceService.selectAskList();
			}else {
				list = serviceService.selectAskListByMem_num(mem_num);
			}
		}
		return new ModelAndView("mainDesk","list",list);
	}
	@GetMapping("/bookstore/service/askWrite.do")
	public String askWrite() {
		return "askWrite";
	}
	@PostMapping("/bookstore/service/askWrite.do")
	public String askInsert(AskVO askVO, Model model,HttpServletRequest request,HttpSession session) {
		log.debug("<<askVO>> : " + askVO);
		
		askVO.setMem_num((Integer)session.getAttribute("mem_num"));
		
		serviceService.insertAsk(askVO);
		
		model.addAttribute("message", "1:1 문의가 접수되었습니다.");
		model.addAttribute("url", "mainDesk.do");
		
		return "common/resultView";
	}
	@GetMapping("/bookstore/service/answerWrite.do")
	public String answerWrite(@RequestParam int ask_num,Model model) {
		AskVO ask = serviceService.selectAsk(ask_num);
		
		log.debug("<<ask>> : " + ask);
		
		model.addAttribute("ask", ask);
		
		return "answerWrite";
	}
	//이미지 출력
	@RequestMapping("/bookstore/service/askImageView.do")
	public ModelAndView viewImageAsk(@RequestParam int ask_num) {

		AskVO vo = 
				serviceService.selectAsk(ask_num);

		ModelAndView mav = new ModelAndView();
		mav.setViewName("imageView");
		mav.addObject("imageFile", vo.getAsk_image());
		mav.addObject("filename", vo.getAsk_imagename());
		return mav;
	}
	@PostMapping("/bookstore/service/answerWrite.do")
	public String answerInsert(AnswerVO answer, HttpSession session,Model model) {
		log.debug("<<AnswerVO>> : " + answer);
		
		answer.setMem_num((Integer)session.getAttribute("mem_num"));
		
		serviceService.insertAnswer(answer);
		
		model.addAttribute("message", "답변 작성이 완료되었습니다.");
		model.addAttribute("url", "mainDesk.do");
		
		return "common/resultView";
	}
	
	@RequestMapping("/bookstore/service/answerDetail.do")
	public ModelAndView answerDetail(@RequestParam int ask_num,HttpSession session) {
		AnswerVO answerVO = serviceService.selectAnswer(ask_num);
		AskVO askVO = serviceService.selectAsk(ask_num);
		
		Integer mem_num = (Integer)session.getAttribute("mem_num");
		
		ModelAndView mav = new ModelAndView();
		
		if(mem_num!=askVO.getMem_num()) {
			mav.setViewName("common/resultView");
			mav.addObject("message","회원정보가 일치 하지 않습니다.");
			mav.addObject("url", "mainDesk.do");
			
			return mav;
		}
		
		mav.setViewName("answerDetail");
		mav.addObject("answerVO",answerVO);
		mav.addObject("askVO",askVO);
		
		return mav;
	}
	
	@GetMapping("/bookstore/adminProductModify.do")
	public String productModifyForm(@RequestParam String store_product_isbn13,Model model) {
		ProductVO productVO = productService.selectProduct(store_product_isbn13);
		model.addAttribute("productVO",productVO);
		return "adminProductModify";
	}
	
	@PostMapping("/bookstore/adminProductModify.do")
	public String productModify(@Valid ProductVO productVO, Model model,HttpServletRequest request) {
		log.debug("<<productVO>> : " + productVO);
		serviceService.updateProduct(productVO);
		
		model.addAttribute("message", "상품 수정이 완료되었습니다.");
		model.addAttribute("url", request.getContextPath()+"/bookstore/adminProductList.do");
		return "common/resultView";
	}
	
	@RequestMapping("/bookstore/adminOrderList.do")
	public String adminOrderList(Model model) {
		List<BookStorePaymentOrderVO> list = new ArrayList<BookStorePaymentOrderVO>();
				
		list = serviceService.adminOrderList();
		model.addAttribute("list", list);
		return "adminOrderList";
	}
	@GetMapping("/bookstore/adminOrderDetail.do")
	public String orderDetail(@RequestParam("order_num") int order_num,Model model) {
		BookStorePaymentOrderVO order = serviceService.adminSelectOrder(order_num);
		log.debug("<<order>> : " + order);
		List<OrderDetailVO> detailList = serviceService.adminOrderDetailList(order_num);
		
		for(OrderDetailVO detail : detailList) {
			log.debug("<<detail>> : "+detail);
			String product_name = serviceService.selectProductByNum(detail.getStore_product_num()).getStore_product_title();
			
			detail.setProduct_name(product_name);
		}
		
		model.addAttribute("order", order);
		model.addAttribute("detailList", detailList);
		
		return "adminOrderDetail";
	}
	@PostMapping("/bookstore/adminOrderDetail.do")
	public String statusChange(BookStorePaymentOrderVO orderVO, Model model, HttpServletRequest request) {
		log.debug("<<orderVO>> : " + orderVO);
		serviceService.updateOrderStatus(orderVO);
		
		model.addAttribute("message","주문 상태 수정이 완료되었습니다.");
		model.addAttribute("url", request.getContextPath()+"/bookstore/adminOrderList.do");
		
		return "common/resultView";
	}
}
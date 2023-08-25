package kr.spring.bookstore.used.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import kr.spring.bookstore.used.service.UsedService;
import kr.spring.bookstore.used.vo.UsedVO;
import kr.spring.util.FileUtil;
import kr.spring.util.PagingUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class UsedController {

	// 서비스에 autowired 걸어주기

	@Autowired
	private UsedService usedService;

	// VO 초기화
	@ModelAttribute
	public UsedVO initCommand() {
		return new UsedVO();
	}

	
	@RequestMapping("/bookstore/used/usedMain.do")
	public ModelAndView getUsedMainClick(@RequestParam(value = "pageNum", defaultValue = "1") int currentPage) {
		List<UsedVO> list = usedService.selectAllUsed();
		int count = usedService.selectAllUsedCount();
		PagingUtil page = new PagingUtil(currentPage, count, 10, 20, "usedMain.do");
		
		log.debug("<<중고 책 List 목록>> : " + list);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("usedMain");
		mav.addObject("list",list);
		mav.addObject("page", page.getPage());
		return mav; // 타일스 식별자
	}

	@RequestMapping("/bookstore/used/usedNoticeForm.do")
	public ModelAndView getUsedNoticeForm(HttpSession session) { // 중고 등록 알림
		if(session.getAttribute("mem_num") == null) {
			ModelAndView mav = new ModelAndView();
			mav.addObject("lo",1);
			mav.setViewName("redirect:/lm/login/template/loginMain.do");
			return mav;
		}
		ModelAndView mav = new ModelAndView();
		mav.setViewName("usedNoticeForm");
		return mav;
	}

	@RequestMapping("/bookstore/used/usedBooksByUser.do")
	public ModelAndView getUsedBooksByUser(@RequestParam(value = "pageNum", defaultValue = "1") int currentPage, 
			HttpSession session) { // 등록한 중고 상품
		Map<String,Object> map = new HashMap<>();
		if(session.getAttribute("mem_num") == null) {
			ModelAndView mav = new ModelAndView();
			mav.addObject("lo",1);
			mav.setViewName("redirect:/lm/login/template/loginMain.do");
			return mav;
		}
		
		int mem_num = (Integer)session.getAttribute("mem_num");
		int count = usedService.selectUsedProductByMemCount(mem_num);
		
		PagingUtil page = new PagingUtil(currentPage, count, 10, 20, "usedBooksByUser.do");
		map.put("mem_num",mem_num);
		map.put("start", page.getStartRow());
		map.put("end", page.getEndRow());
		List<UsedVO> list = usedService.selectUsedProductByMem(map);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("usedBooksByUser");
		mav.addObject("page",page.getPage());
		mav.addObject("list",list);
		mav.addObject("page",page.getPage());
		return mav;
	}

	@RequestMapping("/bookstore/used/usedSalesStatus.do")
	public ModelAndView getUsedSalesStatus(HttpSession session) { // 중고 판매 상태
		ModelAndView mav = new ModelAndView();
		List<UsedVO> list = null;
		if(session.getAttribute("mem_num") == null) {
			mav.addObject("lo",1);
			mav.setViewName("redirect:/lm/login/template/loginMain.do");
			return mav;
		}
		int mem_num = (Integer)session.getAttribute("mem_num");
		mav.setViewName("usedSalesStatus");
		list = usedService.selectUsedSalesStatus(mem_num);
		mav.addObject("list",list);
		return mav;
	}

	
	// 팝업창 영역 (팝업 띄우기)/////////
	@GetMapping("/bookstore/used/usedSearchProductPopup.do")
	public String popup() {
		return "/bookstore/used/usedSearchProductPopup"; // pop업 띄우기
	}

	// 팝업 서치 출력
	@GetMapping("/bookstore/used/selectProductNameByUsed.do")
	public ModelAndView selectProducts(@RequestParam(value="pageNum", defaultValue="1") int currentPage,
			@RequestParam(value = "keyword") String keyword, HttpSession session) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("keyword", keyword);
		
		log.debug("<<검색 목록>> : " + keyword);
		int count = usedService.selectProductNameByUsedCount(map);
		PagingUtil page = new PagingUtil(currentPage, count, 10, 20,"selectProductNameByUsed.do", "&keyword=" + keyword);
		map.put("start", page.getStartRow());
		map.put("end", page.getEndRow());
		log.debug("<<검색 결과 갯수>> : " + count);
		// list에 담자...
		List<UsedVO> list = null;
		list = usedService.selectProductNameByUsed(map);
		// 뿌리는거 (Model And View로 뿌리자)
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/bookstore/used/usedSearchProductPopup");
		log.debug("<<검색 결과>> : " + list);
		mav.addObject("success", 1);
		mav.addObject("list", list);
		mav.addObject("count",count);
		mav.addObject("page",page.getPage()); 
		return mav;
	}

	// 팝업 끝//////////////////////
	/* 나중 Ajax 처리
	@RequestMapping("/bookstore/used/confirmProductNum.do")
	@ResponseBody
	public Map<String, String> confirmProductNum(@RequestParam String store_product_title) {
		// Map 형태로 받아서 key value 값 주기
		Map<String, String> mapAjax = new HashMap<String, String>();

		// product 받아야함...
		int product_num = usedService.searchProductNum(store_product_title);
		if (product_num == 0) {
			// 아이디 중복
			mapAjax.put("result", "idDuplicated");
		} else {

		}
		return mapAjax;
	}
	*/
	@GetMapping("/bookstore/used/usedForm.do")
	public ModelAndView getUsedForm(HttpSession session) {
		if(session.getAttribute("mem_num") == null) {
			ModelAndView mav = new ModelAndView();
			mav.addObject("lo",1);
			mav.setViewName("redirect:/lm/login/template/loginMain.do");
			return mav;
		}
		ModelAndView mav = new ModelAndView();
		mav.setViewName("usedForm");
		return mav;
	}
	
	@PostMapping("/bookstore/used/usedForm.do")
	public String submitUsedForm(@Valid UsedVO usedVO, BindingResult result, HttpServletRequest request, HttpSession session, Model model,  
			@RequestParam("store_product_num") int store_product_num) {
		if(result.hasErrors()) {
			return "usedForm";
		}
		usedVO.setStore_product_num(store_product_num);
		log.debug("<<등록한 중고책 데이터>> : " + usedVO);
		usedVO.setMem_num((Integer) session.getAttribute("mem_num"));
		usedVO.setUsed_product_status(1);
		usedService.insertUsed(usedVO);
	
		
		model.addAttribute("message", "글 등록 완료!");
		model.addAttribute("url", request.getContextPath()+"/bookstore/used/usedBooksByUser.do");
		

		return "common/resultView";
	}
	
	public void viewProfile01(UsedVO usedVO, HttpServletRequest request, Model model) {
		if(usedVO==null) {
			
			//기본 이미지 읽기
			byte[] readbyte = FileUtil.getBytes(request.getServletContext().getRealPath("/image_bundle/default_photo_used.png"));
			model.addAttribute("imageFile", readbyte);
			model.addAttribute("filename","none-image01.jpg");
		}else {//업로드한 프로필 사진이 있는 경우
			model.addAttribute("imageFile", usedVO.getUsed_product_photo1());
			model.addAttribute("filename","ok-image01.jpg");
		}
	}
	
	public void viewProfile02(UsedVO usedVO, HttpServletRequest request, Model model) {
		if(usedVO==null) {
			
			//기본 이미지 읽기
			byte[] readbyte = FileUtil.getBytes(request.getServletContext().getRealPath("/image_bundle/default_photo_used.png"));
			model.addAttribute("imageFile", readbyte);
			model.addAttribute("filename","none-image02.jpg");
		}else {//업로드한 프로필 사진이 있는 경우
			model.addAttribute("imageFile", usedVO.getUsed_product_photo2());
			model.addAttribute("filename","ok-image02.jpg");
		}
	}
	
	//photo 출력하기
	@RequestMapping("/bookstore/used/photoView01.do")
	public String usedPhoto01(@RequestParam int used_product_num, HttpSession session, HttpServletRequest request, Model model) {
		
		if(session.getAttribute("mem_num") == null) {
			model.addAttribute("lo",1);
			return "redirect:/lm/login/template/loginMain.do";
		}
		
		if(used_product_num == 0 ) { //이미지가 없다
			byte[] readbyte = FileUtil.getBytes(request.getServletContext().getRealPath("/image_bundle/default_photo_used.png"));
			model.addAttribute("imageFile", readbyte);
			model.addAttribute("filename", "default_photo_used.png");
		} else { //이미지가 있다
			int mem_num = (Integer)session.getAttribute("mem_num");
			UsedVO usedVO = usedService.selectUsedProductNumAndMemNum(used_product_num, mem_num);
			viewProfile01(usedVO,request,model);
		}
		
		return "imageView";
	}
	
	@RequestMapping("/bookstore/used/photoView02.do")
	public String usedPhoto02(@RequestParam int used_product_num, HttpSession session, HttpServletRequest request, Model model) {
		
		if(session.getAttribute("mem_num") == null) {
			model.addAttribute("lo",1);
			return "redirect:/lm/login/template/loginMain.do";
		}
		
		if(used_product_num == 0 ) { //이미지가 없다
			byte[] readbyte = FileUtil.getBytes(request.getServletContext().getRealPath("/image_bundle/default_photo_used.png"));
			model.addAttribute("imageFile", readbyte);
			model.addAttribute("filename", "default_photo_used.png");
		} else { //이미지가 있다
			int mem_num = (Integer)session.getAttribute("mem_num");
			UsedVO usedVO = usedService.selectUsedProductNumAndMemNum(used_product_num, mem_num);
			viewProfile02(usedVO,request,model);
		}
		
		return "imageView";
	}
	//업데이트 보기
	@GetMapping("/bookstore/used/usedUpdate.do")
	public ModelAndView getUsedUpdate(@Valid UsedVO usedVO, BindingResult result, HttpServletRequest request, HttpSession session,  
			@RequestParam("used_product_num") int used_product_num) {
		
		if(session.getAttribute("mem_num") == null) {
			ModelAndView mav = new ModelAndView();
			mav.addObject("lo",1);
			mav.setViewName("redirect:/lm/login/template/loginMain.do");
			return mav;
		}
		int mem_num = (Integer)session.getAttribute("mem_num");
		//vo에 들어가서 하나를 받아옴 >> Service > ServiceImpl > Mapper > Oracle
		UsedVO vo = usedService.selectUsedProductNumAndMemNum(used_product_num, mem_num);
		//이미지 처리
		
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("usedUpdate");
		mav.addObject("usedVO",vo);
		return mav;
		
	}
	
	
	//제출하기
	@PostMapping("/bookstore/used/usedUpdateSubmit.do")
	public ModelAndView getUsedModify(@ModelAttribute UsedVO usedVO, HttpServletRequest reqeust, HttpSession session, HttpServletRequest request) {
		
		if(session.getAttribute("mem_num") == null) {
			ModelAndView mav = new ModelAndView();
			mav.addObject("lo",1);
			mav.setViewName("redirect:/lm/login/template/loginMain.do");
			return mav;
		}
		int mem_num = (Integer)session.getAttribute("mem_num");
		usedVO.setMem_num(mem_num);
		
		
		
		// 기존 이미지 데이터 가져오기
	    
	    if(usedVO.getUsed_product_photo1() == null || usedVO.getUsed_product_photo1().length == 0) {
	    	UsedVO FindImage01 = usedService.selectUsedProductNumAndMemNum(usedVO.getUsed_product_num(), mem_num);
	    	
	    	usedVO.setUsed_product_photo1(FindImage01.getUsed_product_photo1());
	    }
	    if(usedVO.getUsed_product_photo2() == null || usedVO.getUsed_product_photo2().length == 0) {
	    	UsedVO FindImage02 = usedService.selectUsedProductNumAndMemNum(usedVO.getUsed_product_num(), mem_num);
	    	
	    	usedVO.setUsed_product_photo2(FindImage02.getUsed_product_photo2());
	    }
	    
		usedService.updateUsed(usedVO);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("common/resultView");
		

		mav.addObject("message", "글 수정 완료!");
		mav.addObject("url", request.getContextPath()+"/bookstore/used/usedBooksByUser.do");
		
		return mav;
	}
}

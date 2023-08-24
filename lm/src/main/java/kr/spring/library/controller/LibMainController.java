package kr.spring.library.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.spring.library.board_announce.vo.BoardAnnounceVO;
import kr.spring.library.facility.service.FacilityService;
import kr.spring.library.facility.vo.FacilityVO;
import kr.spring.library.lib_lost_item.vo.LibLostItemVO;
import kr.spring.library.main.service.LibraryMainService;
import kr.spring.library.main.vo.LibraryMainVO;
import kr.spring.util.PagingUtil;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class LibMainController {
	
	@Autowired
	LibraryMainService libraryMainService;
	
	@Autowired
	FacilityService facilityService;
	
	@ModelAttribute
	public LibraryMainVO initCommand() {
		return new LibraryMainVO();
	}
	
	
	@GetMapping("/library/template/libMain.do")
	public ModelAndView libMain(LibraryMainVO libraryMainVO,BoardAnnounceVO boardAnnounceVO, 
			HttpServletRequest request, HttpSession session) {
		Map<String,Object> map = new HashMap<>();
		
		int end = 5;
		
		List<LibraryMainVO> list = null;
		List<LibraryMainVO> navs = null;
		List<BoardAnnounceVO> ann = null;
		List<LibLostItemVO> lost = null;
		List<FacilityVO> faciList = null;
		map.put("start", 2);
		map.put("end", 3);
		faciList = facilityService.selectFacilityList(map);
		list = libraryMainService.selectLibraryAllPorducts();
		navs = libraryMainService.selectLibraryCategoryNav();
		ann = libraryMainService.selectAnnounceList(end);
		lost = libraryMainService.selectLostList(end);
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("libMain");
		mav.addObject("list",list);
		mav.addObject("navs",navs);
		mav.addObject("ann",ann);
		mav.addObject("lost",lost);
		mav.addObject("faciList",faciList);
		return mav; //타일스 설정의 식별자
	}
	
	@RequestMapping("/library/template/giveMeOneSec.do")
	@ResponseBody
	public String giveYouOneSec() {//시간 실시간 가져오기
		String currentTime = libraryMainService.selectCurrentTime();
		return currentTime;
	}
	
	@RequestMapping("/library/template/libAdmin.do")
	public String adminMain(Model model) {
		
		return "libAdmin";//타일스 설정의 식별자
	}
	
	@GetMapping("/library/template/libSearchMain.do") 
	public ModelAndView searchMain(@RequestParam(name="categoryNum", defaultValue="10") int categoryNum,
			@RequestParam(name="orderByNum", defaultValue="1") int orderByNum,
			@RequestParam(name="keyword", defaultValue="") String keyword,
			@RequestParam(name="pageNum", defaultValue="1") int currentPage,
			@RequestParam(name="rnumNum", defaultValue="10") int rnumNum,
			LibraryMainVO libraryMainVO, HttpServletRequest request, HttpSession session) {
		Map<String,Object> map = new HashMap<>();
		//Search가자..
		//List<LibraryMainVO> list = null;
		List<LibraryMainVO> navs = null;
		List<LibraryMainVO> result = null;
		map.put("keyword",keyword);
		map.put("categoryNum",categoryNum);
		int totalCount = libraryMainService.selectLibraryByCategoryAndOrderNumCount(map);
		int selectedCategoryNum = categoryNum;
		int rnum = rnumNum;
		PagingUtil page = new PagingUtil(currentPage, totalCount, rnum, 20, "libSearchMain.do", "&keyword="+keyword+"&orderByNum="+orderByNum+
				"&categoryNum="+categoryNum+"&rnumNum="+rnum);
		//list = libraryMainService.selectLibraryAllPorducts();
		navs = libraryMainService.selectLibraryCategoryNav();
		map.put("orderByNum",orderByNum);
		map.put("start", page.getStartRow());
		map.put("end", page.getEndRow());
		result = libraryMainService.selectLibraryByCategoryAndOrderNum(map);
		//300자 넘으면 ... 처리...
		for(LibraryMainVO VO : result) {
			if(VO.getLib_product_detail().length() >= 300) {
				VO.setLib_product_detail(VO.getLib_product_detail().substring(0,300)+"...");
			}
		}
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("libSearchMain");
		mav.addObject("list",result);
		mav.addObject("navs",navs);
		mav.addObject("totalCount",totalCount);
		mav.addObject("selectedCategoryNum",selectedCategoryNum);
		mav.addObject("resultSearch",keyword);
		mav.addObject("page",page.getPage());
		mav.addObject("rnumNum",rnum);
		//mav.addObject("orderByNum",orderByNum);
		log.debug("<<navs를 까보자>> : "+navs);
		return mav;
	}
	
	@RequestMapping("/library/template/top5container.do")
	@ResponseBody
	public Map<String,Object> top5(LibraryMainVO libraryMainVO){
		Map<String,Object> mapAjax = new HashMap<>();
		int end = 5;
		List<LibraryMainVO> list = null;
		list = libraryMainService.selectLibraryAjaxTop5(end);
		for(LibraryMainVO VO : list) {
			if(VO.getLib_product_bookname().length() >= 15) {
				VO.setLib_product_bookname(VO.getLib_product_bookname().substring(0,15)+"...");
			}
		}
		for(LibraryMainVO VO : list) {
			if(VO.getLib_product_authors().length() >= 15) {
				VO.setLib_product_authors(VO.getLib_product_authors().substring(0,15)+"...");
			}
		}
		mapAjax.put("top",list);
		return mapAjax;
	}
	
	@RequestMapping("/library/template/recommendbook.do")
	@ResponseBody
	public Map<String,Object> recommend(LibraryMainVO libraryMainVO){
		Map<String,Object> mapAjax = new HashMap<>();
		int end = 5;
		List<LibraryMainVO> list = null;
		list = libraryMainService.selectLibraryAjaxRecommend(end);
		for(LibraryMainVO VO : list) {
			if(VO.getLib_product_bookname().length() >= 15) {
				VO.setLib_product_bookname(VO.getLib_product_bookname().substring(0,15)+"...");
			}
		}
		for(LibraryMainVO VO : list) {
			if(VO.getLib_product_authors().length() >= 15) {
				VO.setLib_product_authors(VO.getLib_product_authors().substring(0,15)+"...");
			}
		}
		mapAjax.put("recommend",list);
		return mapAjax;
	}
	/* 개발 중...
	@RequestMapping("/library/template/reviewbest.do")
	@ResponseBody
	public Map<String,Object> reviewbest(LibraryMainVO libraryMainVO){
		Map<String,Object> mapAjax = new HashMap<>();
		int end = 5;
		List<LibraryMainVO> list = null;
		
		mapAjax.put("review",list);
		return mapAjax;
	}
	*/
	@RequestMapping("/library/template/newbook.do")
	@ResponseBody
	public Map<String,Object> newbook(LibraryMainVO libraryMainVO){
		Map<String,Object> mapAjax = new HashMap<>();
		int end = 5;
		List<LibraryMainVO> list = null;
		list = libraryMainService.selectLibraryAjaxNew(end);
		for(LibraryMainVO VO : list) {
			if(VO.getLib_product_bookname().length() >= 15) {
				VO.setLib_product_bookname(VO.getLib_product_bookname().substring(0,15)+"...");
			}
		}
		for(LibraryMainVO VO : list) {
			if(VO.getLib_product_authors().length() >= 15) {
				VO.setLib_product_authors(VO.getLib_product_authors().substring(0,15)+"...");
			}
		}
		mapAjax.put("news",list);
		
		return mapAjax;
	}
	
	@RequestMapping("/library/template/testpage.do")
	public String getTestPage() {
		return "/library/template/testPage";
	}
}

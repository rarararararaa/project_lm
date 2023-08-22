package kr.spring.library.lib_lost_item.controller;

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
import org.springframework.web.servlet.ModelAndView;

import kr.spring.library.lib_lost_item.service.LibLostItemService;
import kr.spring.library.lib_lost_item.vo.LibLostItemVO;
import kr.spring.util.PagingUtil;
import kr.spring.util.StringUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class LibLostItemUserController {
	@Autowired
	private LibLostItemService libLostItemService;
	
	/*
	 * ============================== 분실물 목록 ==============================
	 */
	@RequestMapping("/library/liblostitem/listUser.do")
	public ModelAndView getList(@RequestParam(value = "pageNum", defaultValue = "1") int currentPage,
			@RequestParam(value = "order", defaultValue = "1") int order, String keyfield, String keyword) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("keyfield", keyfield);
		map.put("keyword", keyword);

		// 전체/검색 레코드수
		int count = libLostItemService.selectRowCount(map);

		log.debug("<<ALL-board-count>> : " + count);

		// 페이지처리 부가적인 파라미터
		PagingUtil page = new PagingUtil(keyfield, keyword, currentPage, count, 20, 10, "list.do", "&order=" + order);

		List<LibLostItemVO> list = null;
		if (count > 0) {
			map.put("order", order);
			map.put("start", page.getStartRow());
			map.put("end", page.getEndRow());

			list = libLostItemService.selectList(map);
		}

		ModelAndView mav = new ModelAndView();
		mav.setViewName("libLostItemUserList");
		mav.addObject("count", count);
		mav.addObject("list", list);
		mav.addObject("page", page.getPage());

		return mav;
	}



	/*
	 * ============================== 분실물 글 상세 ==============================
	 */
	@RequestMapping("/library/liblostitem/detailUser.do")
	public ModelAndView getDetail(@RequestParam int item_num) {
		log.debug("<<글상세 - item_num>> : " + item_num);

		// 해당 글의 조회수 증가
		libLostItemService.updateHit(item_num);
		
		// 글상세
		LibLostItemVO libLostItem = libLostItemService.selectLibLostItem(item_num);

		// 제목에 태그를 허용하지 않음
		libLostItem.setItem_title(StringUtil.useNoHtml(libLostItem.getItem_title()));

		// 뷰 이름 속성명 속성값
		return new ModelAndView("libLostItemUserView", "libLostItem", libLostItem);
	}

	
	
}

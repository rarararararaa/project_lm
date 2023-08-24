package kr.spring.bookstore.search.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


import kr.spring.bookstore.search.service.SearchService;
import kr.spring.bookstore.search.vo.SearchVO;

@RestController
public class SearchRestController {
	private static final Logger logger = LoggerFactory.getLogger(SearchRestController.class);
	@Resource
	private SearchService searchService;
	
	@GetMapping("/searchProducts/{categoryNum}/{orderByNum}/{keyword}/{startRow}/{endRow}")
	public Map<String, Object> getAllProducts(@PathVariable int categoryNum, 
			@PathVariable int orderByNum, @PathVariable String keyword, @PathVariable int startRow, @PathVariable int endRow) {
		Map<String,Object> map = new HashMap<>();
		logger.debug("<<categoryNum, orderByNum, keyword, startRow, endRow>>"+categoryNum+"/"+orderByNum+"/"+keyword+"/"+startRow+"/"+endRow);
		map.put("categoryNum", categoryNum);
		map.put("orderByNum", orderByNum);
		map.put("keyword", keyword);
		int count = searchService.searchProductAllCount(map);
		List<SearchVO> list = null;
		map.put("startRow", startRow);
		map.put("endRow", endRow);
		list = searchService.searchProductAll(map);
		Map<String,Object> mapJson = new HashMap<>();
		mapJson.put("result", "success");
		mapJson.put("list", list);
		mapJson.put("count", count);
		mapJson.put("keywordResult", keyword);
		return mapJson;
	}
}
package kr.spring.bookstore.search.service;

import java.util.List;
import java.util.Map;

import kr.spring.bookstore.search.vo.SearchVO;

public interface SearchService {
	public List<SearchVO> searchProductAll(Map<String, Object> mapJson);
	
	public int searchProductAllCount(Map<String,Object> mapJson);
}

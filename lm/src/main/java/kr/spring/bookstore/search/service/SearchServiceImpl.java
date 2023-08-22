package kr.spring.bookstore.search.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.spring.bookstore.search.dao.SearchMapper;
import kr.spring.bookstore.search.vo.SearchVO;

@Service
@Transactional
public class SearchServiceImpl implements SearchService{
	
	@Autowired
	private SearchMapper searchMapper;
	
	@Override
	public List<SearchVO> searchProductAll(Map<String, Object> mapJson) {
		// TODO Auto-generated method stub
		return searchMapper.searchProductAll(mapJson);
	}

	@Override
	public int searchProductAllCount(Map<String, Object> mapJson) {
		// TODO Auto-generated method stub
		return searchMapper.searchProductAllCount(mapJson);
	}

}

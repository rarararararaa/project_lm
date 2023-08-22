package kr.spring.bookstore.search.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import kr.spring.bookstore.search.vo.SearchVO;

@Mapper
public interface SearchMapper {
	//List
	public List<SearchVO> searchProductAll(Map<String, Object> mapJson);
	
	//count
	public int searchProductAllCount(Map<String,Object> mapJson);
}

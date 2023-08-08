package kr.spring.library.main.service;

import java.util.List;
import java.util.Map;

import kr.spring.library.main.vo.LibraryMainVO;


public interface LibraryMainService {
	public List<LibraryMainVO> selectLibraryAllPorducts();
	
	public List<LibraryMainVO> selectLibraryCategoryNav();
	
	public List<LibraryMainVO> selectLibraryByCategoryAndOrderNum(Map<String,Object> map);
	public int selectLibraryByCategoryAndOrderNumCount(Map<String,Object> map);
}

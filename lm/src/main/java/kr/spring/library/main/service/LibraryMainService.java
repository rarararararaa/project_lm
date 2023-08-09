package kr.spring.library.main.service;

import java.util.List;
import java.util.Map;

import kr.spring.library.board_announce.vo.BoardAnnounceVO;
import kr.spring.library.lib_lost_item.vo.LibLostItemVO;
import kr.spring.library.main.vo.LibraryMainVO;


public interface LibraryMainService {
	public List<LibraryMainVO> selectLibraryAllPorducts();
	
	public List<LibraryMainVO> selectLibraryCategoryNav();
	
	public List<LibraryMainVO> selectLibraryByCategoryAndOrderNum(Map<String,Object> map);
	public int selectLibraryByCategoryAndOrderNumCount(Map<String,Object> map);
	
	public List<BoardAnnounceVO> selectAnnounceList(int end);
	
	public List<LibLostItemVO> selectLostList(int end);
	
	public String selectCurrentTime();
	
	public List<LibraryMainVO> selectLibraryAjaxTop5(int end);
	public List<LibraryMainVO> selectLibraryAjaxRecommend(int end);
	public List<LibraryMainVO> selectLibraryAjaxNew(int end);
}

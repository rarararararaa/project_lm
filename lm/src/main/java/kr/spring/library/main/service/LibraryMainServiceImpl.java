package kr.spring.library.main.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.spring.library.board_announce.vo.BoardAnnounceVO;
import kr.spring.library.lib_lost_item.vo.LibLostItemVO;
import kr.spring.library.main.dao.LibraryMainMapper;
import kr.spring.library.main.vo.LibraryMainVO;

@Service
@Transactional
public class LibraryMainServiceImpl implements LibraryMainService {
	
	@Autowired
	LibraryMainMapper libraryMainMapper;
	
	@Override
	public List<LibraryMainVO> selectLibraryAllPorducts() {
		return libraryMainMapper.selectLibraryAllPorducts();
	}

	@Override
	public List<LibraryMainVO> selectLibraryCategoryNav() {
		return libraryMainMapper.selectLibraryCategoryNav();
	}

	@Override
	public List<LibraryMainVO> selectLibraryByCategoryAndOrderNum(Map<String,Object> map) {
		
		return libraryMainMapper.selectLibraryByCategoryAndOrderNum(map);
	}

	@Override
	public int selectLibraryByCategoryAndOrderNumCount(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return libraryMainMapper.selectLibraryByCategoryAndOrderNumCount(map);
	}

	@Override
	public List<BoardAnnounceVO> selectAnnounceList(int end) {
		
		return libraryMainMapper.selectAnnounceList(end);
	}

	@Override
	public List<LibLostItemVO> selectLostList(int end) {
		// TODO Auto-generated method stub
		return libraryMainMapper.selectLostList(end);
	}

	@Override
	public String selectCurrentTime() {
		// TODO Auto-generated method stub
		return libraryMainMapper.selectCurrentTime();
	}

	@Override
	public List<LibraryMainVO> selectLibraryAjaxNew(int end) {
		// TODO Auto-generated method stub
		return libraryMainMapper.selectLibraryAjaxNew(end);
	}

	@Override
	public List<LibraryMainVO> selectLibraryAjaxRecommend(int end) {
		// TODO Auto-generated method stub
		return libraryMainMapper.selectLibraryAjaxRecommend(end);
	}

	@Override
	public List<LibraryMainVO> selectLibraryAjaxTop5(int end) {
		// TODO Auto-generated method stub
		return libraryMainMapper.selectLibraryAjaxTop5(end);
	}
	
	
	
	

}

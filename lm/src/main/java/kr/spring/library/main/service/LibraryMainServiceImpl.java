package kr.spring.library.main.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

}

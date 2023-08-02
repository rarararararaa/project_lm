package kr.spring.library.lib_lost_item.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.spring.library.lib_lost_item.dao.LibLostItemMapper;
import kr.spring.library.lib_lost_item.vo.LibLostItemVO;

@Service
@Transactional
public class LibLostItemServiceImpl implements LibLostItemService{

	@Autowired
	LibLostItemMapper libLostItemMapper;
	
	@Override
	public List<LibLostItemVO> selectList(Map<String, Object> map) {
		return libLostItemMapper.selectList(map);
	}

	@Override
	public int selectRowCount(Map<String, Object> map) {
		return libLostItemMapper.selectRowCount(map);
	}

	@Override
	public void insertLibLostItem(LibLostItemVO libLostItem) {
		libLostItemMapper.insertLibLostItem(libLostItem);
	}

	@Override
	public LibLostItemVO selectLibLostItem(Integer item_num) {
		return libLostItemMapper.selectLibLostItem(item_num);
	}

	@Override
	public void updateHit(Integer item_num) {
		libLostItemMapper.updateHit(item_num);
	}

	@Override
	public void updateLibLostItem(LibLostItemVO libLostItem) {
		libLostItemMapper.updateLibLostItem(libLostItem);
	}

	@Override
	public void deleteLibLostItem(Integer item_num) {
		libLostItemMapper.deleteLibLostItem(item_num);
	}

}

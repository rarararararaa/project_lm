package kr.spring.library.bookapply.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.spring.library.bookapply.dao.BookApplyMapper;
import kr.spring.library.bookapply.vo.BookApplyVO;

@Service
@Transactional
public class BookApplyServiceImpl implements BookApplyService{

	@Autowired
	private BookApplyMapper bookApplyMapper;
	
	@Override
	public void insertBookApply(BookApplyVO bookApplyVO) {
		bookApplyMapper.insertBookApply(bookApplyVO);
	}

	@Override
	public List<BookApplyVO> selectBookApplyListByMem_num(Map<String, Object> map) {
		return bookApplyMapper.selectBookApplyListByMem_num(map);
	}

	@Override
	public List<BookApplyVO> selectBookApplyList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BookApplyVO selectBookApply(Integer book_apply_num) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateBookApply(Integer book_apply_num) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteBookApply(Integer book_apply_num) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int countBookApply(BookApplyVO vo) {
		return bookApplyMapper.countBookApply(vo);
	}

	@Override
	public int countBookApplyListByMem_num(Map<String, Object> map) {
		return bookApplyMapper.countBookApplyListByMem_num(map);
	}

}

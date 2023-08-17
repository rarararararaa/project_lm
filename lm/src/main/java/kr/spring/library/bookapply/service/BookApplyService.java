package kr.spring.library.bookapply.service;

import java.util.List;
import java.util.Map;

import kr.spring.library.bookapply.vo.BookApplyVO;

public interface BookApplyService {
	//희망도서 신청
	public void insertBookApply(BookApplyVO bookApplyVO);
	//희망도서 목록(사용자)
	public List<BookApplyVO> selectBookApplyListByMem_num(Map<String, Object> map);
	public int countBookApplyListByMem_num(Map<String, Object> map);
	//희망도서 목록(관리자)
	public List<BookApplyVO> selectBookApplyList();
	//희망도서 상세
	public BookApplyVO selectBookApply(Integer book_apply_num);
	//희망도서 승인
	public void updateBookApply(Integer book_apply_num);
	//희망도서 삭제
	public void deleteBookApply(Integer book_apply_num);
	
	public int countBookApply(BookApplyVO vo); 
}

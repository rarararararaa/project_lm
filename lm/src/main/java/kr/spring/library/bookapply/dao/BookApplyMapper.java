package kr.spring.library.bookapply.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import kr.spring.library.bookapply.vo.BookApplyVO;

@Mapper
public interface BookApplyMapper {
	
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
	//특정 기간동안 희망도서 신청 갯수
	@Select("SELECT COUNT(*) FROM lib_book_apply WHERE book_apply_reg_date>#{book_apply_reg_date} AND mem_num=#{mem_num}")
	public int countBookApply(BookApplyVO vo); 
}

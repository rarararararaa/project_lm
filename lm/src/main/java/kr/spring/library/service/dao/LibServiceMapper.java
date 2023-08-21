package kr.spring.library.service.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.spring.library.service.vo.BoardAnswerVO;
import kr.spring.library.service.vo.BoardAskVO;


@Mapper
public interface LibServiceMapper {
	//1:1문의 게시글 개수
	public int selectRowCount(Map<String,Object> map);
	//1:1문의 작성
	public void insertBoardAsk(BoardAskVO boardAskVO);
	//1:1문의 목록 - 회원번호 별
	@Select("SELECT * FROM lib_board_ask WHERE mem_num=#{mem_num} ORDER BY ask_status DESC, ask_reg_date DESC")
	public List<BoardAskVO> selectBoardAskListByMem_num(Integer mem_num);
	//1:1문의 목록-전체
	@Select("SELECT * FROM lib_board_ask ORDER BY ask_status DESC, ask_reg_date DESC")
	public List<BoardAskVO> selectBoardAskList();
	//내가 쓴 1:1문의글 상세페이지 
	@Select("SELECT * FROM lib_board_ask WHERE ask_num=#{ask_num}")
	public BoardAskVO selectBoardAsk(Integer ask_num);
	//내가 쓴 1:1문의글 수정페이지 
	@Update("UPDATE lib_board_ask SET ask_title=#{ask_title},"
			+ "ask_content=#{ask_content},"
			+ "ask_modify_date=SYSDATE "
			+ "WHERE ask_num=#{ask_num}")
	public void updateBoardAsk(BoardAskVO boardAsk);
	//내가 쓴 1:1문의글 삭제 
	@Delete("DELETE FROM lib_board_ask WHERE ask_num=#{ask_num}")
	public void deleteBoardAsk(Integer ask_num);
	
	//관리자
	//1:1문의 답변 작성
	public void insertBoardAnswer(BoardAnswerVO boardAnswerVO);
	//1:1문의 답변 작성시 질문글의 status값 변경
	@Update("UPDATE lib_board_ask SET ask_status=1 WHERE ask_num=#{ask_num}")
	public void updateBoardAskStatus(Integer ask_num);
	//1:1문의 답변 내용
	@Select("SELECT * FROM lib_board_answer WHERE ask_num=#{ask_num}")
	public BoardAnswerVO selectBoardAnswer(Integer ask_num);
	//부모글 삭제시 댓글이 존재하면 부모글 삭제전 댓글 삭제
	@Delete("DELETE FROM lib_board_answer WHERE ask_num=#{ask_num}")
	public void deleteAnswerByAskNum(Integer ask_num);

}
package kr.spring.library.service.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.spring.library.service.vo.BoardAnswerVO;
import kr.spring.library.service.vo.BoardAskVO;


@Mapper
public interface LibServiceMapper {
	//1:1문의 작성
	public void insertBoardAsk(BoardAskVO boardAskVO);
	//목록-회원별
	@Select("SELECT * FROM lib_board_ask WHERE mem_num=#{mem_num} ORDER BY ask_status DESC, ask_reg_date DESC")
	public List<BoardAskVO> selectBoardAskListByMem_num(Integer mem_num);
	//목록-전체
	@Select("SELECT * FROM lib_board_ask ORDER BY ask_status DESC, ask_reg_date DESC")
	public List<BoardAskVO> selectBoardAskList();
	//상세
	@Select("SELECT * FROM lib_board_ask WHERE ask_num=#{ask_num}")
	public BoardAskVO selectBoardAsk(Integer ask_num);
	
	//1:1문의 답변 작성
	public void insertBoardAnswer(BoardAnswerVO boardAnswerVO);
	@Update("UPDATE lib_board_ask SET ask_status=1 WHERE ask_num=#{ask_num}")
	public void updateBoardAskStatus(Integer ask_num);
	
	//답변 보기
	@Select("SELECT * FROM lib_board_answer WHERE ask_num=#{ask_num}")
	public BoardAnswerVO selectBoardAnswer(Integer ask_num);

}
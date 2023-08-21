package kr.spring.library.service.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.spring.library.service.vo.BoardAnswerVO;
import kr.spring.library.service.vo.BoardAskVO;


public interface LibServiceService {
	//1:1문의 게시글 개수
	public int selectRowCount(Map<String,Object> map);
	//1:1문의 작성
	public void insertBoardAsk(BoardAskVO boardAskVO);
	//1:1문의 목록 - 회원번호 별
	public List<BoardAskVO> selectBoardAskListByMem_num(Integer mem_num);
	//1:1문의 목록-전체
	public List<BoardAskVO> selectBoardAskList();
	//내가 쓴 1:1문의글 상세페이지
	public BoardAskVO selectBoardAsk(Integer ask_num);
	//내가 쓴 1:1문의글 수정
	public void updateBoardAsk(BoardAskVO boardAsk);
	//내가 쓴 1:1문의글 삭제
	public void deleteBoardAsk(Integer ask_num);

	//관리자
	//1:1문의 답변 작성
	public void insertBoardAnswer(BoardAnswerVO boardAnswerVO);
	//1:1문의 답변 내용
	public BoardAnswerVO selectBoardAnswer(Integer ask_num);
	
}

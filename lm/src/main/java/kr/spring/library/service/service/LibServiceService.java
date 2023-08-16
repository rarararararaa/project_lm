package kr.spring.library.service.service;

import java.util.List;
import kr.spring.library.service.vo.BoardAnswerVO;
import kr.spring.library.service.vo.BoardAskVO;


public interface LibServiceService {
	//1:1문의 작성
	public void insertBoardAsk(BoardAskVO boardAskVO);
	//목록-회원별
	public List<BoardAskVO> selectBoardAskListByMem_num(Integer mem_num);
	//목록-전체
	public List<BoardAskVO> selectBoardAskList();
	//상세
	public BoardAskVO selectBoardAsk(Integer ask_num);

	//1:1문의 답변 작성
	public void insertBoardAnswer(BoardAnswerVO boardAnswerVO);
	public void updateBoardAskStatus(Integer ask_num);

	//답변 보기
	public BoardAnswerVO selectBoardAnswer(Integer ask_num);
}

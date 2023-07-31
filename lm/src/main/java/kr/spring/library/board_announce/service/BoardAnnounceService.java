package kr.spring.library.board_announce.service;

import java.util.List;
import java.util.Map;

import kr.spring.library.board_announce.vo.BoardAnnounceVO;

public interface BoardAnnounceService {
	//부모글
	public List<BoardAnnounceVO> selectList(Map<String,Object> map);
	public int selectRowCount(Map<String,Object> map);
	public void insertBoardAnnounce(BoardAnnounceVO boardAnnounce);
	public BoardAnnounceVO selectBoard(Integer notice_num);
	public void updateBoard(BoardAnnounceVO boardAnnounce);
	public void deleteBoard(Integer notice_num);
	
}

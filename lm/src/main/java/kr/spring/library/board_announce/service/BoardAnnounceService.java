package kr.spring.library.board_announce.service;

import java.util.List;
import java.util.Map;

import kr.spring.library.board_announce.vo.BoardAnnounceVO;

public interface BoardAnnounceService {
	//부모글
	public List<BoardAnnounceVO> selectList(Map<String,Object> map);
	public int selectRowCount(Map<String,Object> map);
	public void insertBoardAnnounce(BoardAnnounceVO boardAnnounce);
	public BoardAnnounceVO selectBoardAnnounce(Integer notice_num);
	public void updateHit(Integer notice_num);
	public void updateBoardAnnounce(BoardAnnounceVO boardAnnounce);
	public void deleteBoardAnnounce(Integer notice_num);
	
}

package kr.spring.library.board_announce.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import kr.spring.library.board_announce.vo.BoardAnnounceVO;


@Mapper
public interface BoardAnnounceMapper {
	//부모글
	public List<BoardAnnounceVO> selectList(Map<String,Object> map);
	public int selectRowCount(Map<String,Object> map);
	public void insertBoardAnnounce(BoardAnnounceVO boardAnnounce);
	public BoardAnnounceVO selectBoard(Integer notice_num);
	public void updateBoard(BoardAnnounceVO boardAnnounce);
	public void deleteBoard(Integer notice_num);
	
}







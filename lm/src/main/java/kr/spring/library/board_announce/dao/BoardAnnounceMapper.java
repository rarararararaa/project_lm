package kr.spring.library.board_announce.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

import kr.spring.library.board_announce.vo.BoardAnnounceVO;


@Mapper
public interface BoardAnnounceMapper {
	//부모글
	public List<BoardAnnounceVO> selectList(Map<String,Object> map);
	public int selectRowCount(Map<String,Object> map);
	public void insertBoardAnnounce(BoardAnnounceVO boardAnnounce);
	public BoardAnnounceVO selectBoardAnnounce(Integer notice_num);
	@Update("UPDATE board_announce SET hit=hit+1 WHERE notice_num=#{notice_num}")
	public void updateHit(Integer notice_num);
	@Update("UPDATE board_announce SET notice_title=#{notice_title},"
			  + "notice_content=#{notice_content},"
			  + "notice_modify_date=SYSDATE "
			  + "WHERE notice_num=#{notice_num}")
	public void updateBoardAnnounce(BoardAnnounceVO boardAnnounce);
	@Delete("DELETE FROM board_announce WHERE notice_num=#{notice_num}")
	public void deleteBoardAnnounce(Integer notice_num);
	
}







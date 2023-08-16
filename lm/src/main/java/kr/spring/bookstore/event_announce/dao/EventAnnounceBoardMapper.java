package kr.spring.bookstore.event_announce.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.spring.bookstore.event_announce.vo.EventAnnounceBoardVO;

@Mapper
public interface EventAnnounceBoardMapper {
	//게시판 글 작성 - o 
	public void insertEventAnnounceBoard(EventAnnounceBoardVO board);
	//게시판 글 수정 - o
	@Update("UPDATE store_event_announce_board SET title=#{title}, content=#{content}, modify_date=SYSDATE WHERE event_announce_board_num=#{event_announce_board_num}")
	public void updateEventAnnouceBoard(EventAnnounceBoardVO board);
	//게시판 글 삭제 - o
	@Delete("DELETE FROM store_event_announce_board WHERE event_announce_board_num=#{event_announce_board_num}")
	public void deleteEventAnnounceBoard(Integer event_announce_board_num);
	
	//게시판 글 개수 세기 - o
	public int selectCount(Map<String,Object> map);
	//게시판 글 목록 - o
	public List<EventAnnounceBoardVO> selectList(Map<String,Object> map);
	
	//게시판 글 자세히 보기 - o
	@Select("SELECT * FROM store_event_announce_board WHERE event_announce_board_num=#{event_announce_board_num}")
	public EventAnnounceBoardVO selectEventAnnounceBoard(Integer event_announce_board_num);
	
	//게시판 글 조회수 - o
	@Update("UPDATE store_event_announce_board SET hit=hit+1 WHERE event_announce_board_num=#{event_announce_board_num}")
	public void updateHit(Integer event_announce_board_num);
}


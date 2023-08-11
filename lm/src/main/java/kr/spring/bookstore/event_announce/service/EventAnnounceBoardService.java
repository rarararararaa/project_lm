package kr.spring.bookstore.event_announce.service;

import java.util.List;
import java.util.Map;


import kr.spring.bookstore.event_announce.vo.EventAnnounceBoardVO;

public interface EventAnnounceBoardService {
		//게시판 글 작성 - o 
		public void insertEventAnnounceBoard(EventAnnounceBoardVO board);
		//게시판 글 수정 - o
		public void updateEventAnnouceBoard(EventAnnounceBoardVO board);
		//게시판 글 삭제
		public void deleteEventAnnounceBoard(Integer event_announce_board_num);
		
		//게시판 글 개수 세기 - o
		public int selectCount(Map<String,Object> map);
		//게시판 글 목록 - o
		public List<EventAnnounceBoardVO> selectList(Map<String,Object> map);
		
		//게시판 글 자세히 보기
		public EventAnnounceBoardVO selectEventAnnounceBoard(Integer event_announce_board_num);
		
		//게시판 글 조회수 - o
		public void updateHit(Integer event_announce_board_num);
}

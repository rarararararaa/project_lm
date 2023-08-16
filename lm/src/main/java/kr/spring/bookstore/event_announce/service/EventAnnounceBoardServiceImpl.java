package kr.spring.bookstore.event_announce.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.spring.bookstore.event_announce.dao.EventAnnounceBoardMapper;
import kr.spring.bookstore.event_announce.vo.EventAnnounceBoardVO;

@Service
@Transactional
public class EventAnnounceBoardServiceImpl implements EventAnnounceBoardService{
	@Autowired
	EventAnnounceBoardMapper announceBoardMapper;
	
	@Override
	public void insertEventAnnounceBoard(EventAnnounceBoardVO board) {
		announceBoardMapper.insertEventAnnounceBoard(board);
	}

	@Override
	public void updateEventAnnouceBoard(EventAnnounceBoardVO board) {
		announceBoardMapper.updateEventAnnouceBoard(board);
	}

	@Override
	public void deleteEventAnnounceBoard(Integer event_announce_board_num) {
		announceBoardMapper.deleteEventAnnounceBoard(event_announce_board_num);
	}

	@Override
	public int selectCount(Map<String, Object> map) {
		return announceBoardMapper.selectCount(map);
	}

	@Override
	public List<EventAnnounceBoardVO> selectList(Map<String, Object> map) {
		return announceBoardMapper.selectList(map);
	}

	@Override
	public EventAnnounceBoardVO selectEventAnnounceBoard(Integer event_announce_board_num) {
		return announceBoardMapper.selectEventAnnounceBoard(event_announce_board_num);
	}

	@Override
	public void updateHit(Integer event_announce_board_num) {
		announceBoardMapper.updateHit(event_announce_board_num);
	}

}

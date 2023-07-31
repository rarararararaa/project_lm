package kr.spring.library.board_announce.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.spring.library.board_announce.dao.BoardAnnounceMapper;
import kr.spring.library.board_announce.vo.BoardAnnounceVO;

@Service
@Transactional
public class BoardAnnounceServiceImpl implements BoardAnnounceService{

	@Autowired
	BoardAnnounceMapper boardAnnounceMapper;
	
	@Override
	public List<BoardAnnounceVO> selectList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int selectRowCount(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void insertBoardAnnounce(BoardAnnounceVO boardAnnounce) {
		boardAnnounceMapper.insertBoardAnnounce(boardAnnounce);
	}

	@Override
	public BoardAnnounceVO selectBoard(Integer notice_num) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateBoard(BoardAnnounceVO boardAnnounce) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteBoard(Integer notice_num) {
		// TODO Auto-generated method stub
		
	}

}

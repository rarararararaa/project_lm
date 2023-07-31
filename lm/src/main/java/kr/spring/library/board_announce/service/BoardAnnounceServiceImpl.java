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
		return boardAnnounceMapper.selectList(map);
	}

	@Override
	public int selectRowCount(Map<String, Object> map) {
		return boardAnnounceMapper.selectRowCount(map);
	}

	@Override
	public void insertBoardAnnounce(BoardAnnounceVO boardAnnounce) {
		boardAnnounceMapper.insertBoardAnnounce(boardAnnounce);
	}

	@Override
	public BoardAnnounceVO selectBoardAnnounce(Integer notice_num) {
		return boardAnnounceMapper.selectBoardAnnounce(notice_num);
	}

	@Override
	public void updateBoardAnnounce(BoardAnnounceVO boardAnnounce) {
		boardAnnounceMapper.updateBoardAnnounce(boardAnnounce);
	}

	@Override
	public void deleteBoardAnnounce(Integer notice_num) {
		boardAnnounceMapper.deleteBoardAnnounce(notice_num);
	}

	@Override
	public void updateHit(Integer notice_num) {
		boardAnnounceMapper.updateHit(notice_num);
	}
	
	

}

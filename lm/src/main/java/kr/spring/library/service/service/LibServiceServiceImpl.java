package kr.spring.library.service.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.spring.library.service.dao.LibServiceMapper;
import kr.spring.library.service.vo.BoardAnswerVO;
import kr.spring.library.service.vo.BoardAskVO;


@Service
@Transactional
public class LibServiceServiceImpl implements LibServiceService{

	@Autowired
	private LibServiceMapper libServiceMapper;
	
	@Override
	public void insertBoardAsk(BoardAskVO boardAskVO) {
		libServiceMapper.insertBoardAsk(boardAskVO);
	}

	@Override
	public List<BoardAskVO> selectBoardAskListByMem_num(Integer mem_num) {
		return libServiceMapper.selectBoardAskListByMem_num(mem_num);
	}

	@Override
	public List<BoardAskVO> selectBoardAskList() {
		return libServiceMapper.selectBoardAskList();
	}

	@Override
	public BoardAskVO selectBoardAsk(Integer ask_num) {
		return libServiceMapper.selectBoardAsk(ask_num);
	}

	@Override
	public void insertBoardAnswer(BoardAnswerVO boardAnswerVO) {
		libServiceMapper.insertBoardAnswer(boardAnswerVO);
	}

	@Override
	public void updateBoardAskStatus(Integer ask_num) {
		libServiceMapper.updateBoardAskStatus(ask_num);
	}

	@Override
	public BoardAnswerVO selectBoardAnswer(Integer ask_num) {
		return libServiceMapper.selectBoardAnswer(ask_num);
	}

	
}

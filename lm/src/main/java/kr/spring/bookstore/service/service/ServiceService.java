package kr.spring.bookstore.service.service;

import java.util.List;

import kr.spring.bookstore.service.vo.AnnounceVO;


public interface ServiceService {
	//공지사항 글 목록 가져오기
	public List<AnnounceVO> selectAnnounceList();
	//공지사항 작성
	public void insertAnnounce(AnnounceVO announceVO);
}

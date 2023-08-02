package kr.spring.bookstore.service.service;

import java.util.List;
import java.util.Map;

import kr.spring.bookstore.service.vo.AnnounceVO;
import kr.spring.bookstore.service.vo.FaqVO;


public interface ServiceService {
	//공지사항 글 목록 가져오기
	public List<AnnounceVO> selectAnnounceList(Map<String, Object> map);
	//공지사항 작성
	public void insertAnnounce(AnnounceVO announceVO);
	//공지사항 글 상세
	public AnnounceVO selectAnnounce(Integer board_num);
	//공지사항 글 갯수
	public int selectRowCount(Map<String,Object> map);
	
	//자주묻는질문
	public void insertFaq(FaqVO faqVO);
	//목록불러오기/검색
	public List<FaqVO> selectFaqList(Map<String, Object> map);
	public int selectRowCountFaq(Map<String,Object> map);
}

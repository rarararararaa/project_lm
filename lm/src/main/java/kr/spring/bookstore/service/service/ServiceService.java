package kr.spring.bookstore.service.service;

import java.util.List;
import java.util.Map;

import kr.spring.bookstore.service.vo.AnnounceVO;
import kr.spring.bookstore.service.vo.AnswerVO;
import kr.spring.bookstore.service.vo.AskVO;
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
	public void updateFaq(FaqVO faqVO);
	//목록불러오기/검색
	public List<FaqVO> selectFaqList(Map<String, Object> map);
	public int selectRowCountFaq(Map<String,Object> map);
	
	//1:1문의 작성
	public void insertAsk(AskVO askVO);
	//목록-회원별
	public List<AskVO> selectAskListByMem_num(Integer mem_num);
	//목록-전체
	public List<AskVO> selectAskList();
	//상세
	public AskVO selectAsk(Integer ask_num);
	
	//1:1문의 답변 작성
	public void insertAnswer(AnswerVO answerVO);
	
	//답변 보기
	public AnswerVO selectAnswer(Integer ask_num);
}

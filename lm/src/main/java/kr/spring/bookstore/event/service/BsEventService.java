package kr.spring.bookstore.event.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.spring.bookstore.event.vo.BsAttendancePointVO;
import kr.spring.bookstore.event.vo.BsAttendanceVO;
import kr.spring.bookstore.event.vo.BsEventReplyVO;
import kr.spring.bookstore.event.vo.BsEventVO;
import kr.spring.bookstore.event.vo.BsQuizVO;

public interface BsEventService {
	//이벤트 글 목록
	public List<BsEventVO> selectEventList(Map<String, Object> map);
	public int selectEventCount(Map<String, Object> map);
	//이벤트 글 등록
	public void insertEvent(BsEventVO event);
	//이벤트 글 상세페이지
	public BsEventVO selectEvent(Integer event_board_num);
	//글 조회수
	public void updateEventHit (Integer event_board_num);
	//이벤트 글 수정
	public void updateEvent(BsEventVO event);
	//이벤트 글 삭제
	public void deleteEventBoard(Integer event_board_num);
	public String selectEventItemIsbn(Integer store_product_num);
	//이벤트 종료기간 업데이트
	public void updateEndList();


	/* ---------------
	 *  출석 이벤트
	 * ---------------- */
	//출석 이벤트 - 출석 처리
	public void insertAttendancePoint(BsAttendancePointVO attendancePoint);
	public void insertAttendance(BsAttendanceVO attendance);
	//출석 상태 상세 - xml
	public BsAttendancePointVO selectAttendanceDetail(@Param(value="mem_num")Integer mem_num,
			@Param(value="event_month")Integer event_month);
	//출석 이벤트 - 출석 개수
	public int selectAttendanceCount(BsAttendancePointVO attendancePoint); 

	//출석 이벤트 - 포인트 부여 상태 update
	public void updateAttendancePointGet(Map<String, Object> map); 

	/*
	//회원이 가지고 있는 현재 포인트 조회
	@Select("SELECT mem_num, mem_point FROM lm_member_detail WHERE mem_num=#{mem_num}")
	public int selectMemberPoint(Integer mem_num);
	*/
	//회원 포인트 추가(update)
	@Update("UPDATE lm_member_detail SET mem_point=#{addPoint} WHERE mem_num=#{mem_num}")
	public void updateMemberPoint(Map<String, Object> map);

	public BsAttendancePointVO selectCountAttendanceStatus(Map<String, Object> map);

	//출석 개수 list
	public List<BsAttendanceVO> selectListAtendance(BsAttendancePointVO attendancePoint);

	//출석 중복 검사
	public BsAttendanceVO selectAttendanceCheck(Map<String, Object> map);
	
	/* ---------------
	 *  퀴즈 이벤트
	 * ---------------- */
	//퀴즈 이벤트 검색
	public BsQuizVO selectQuizStatus(BsQuizVO quiz);
	//퀴즈 이벤트 insert
	public void insetQuizStatus(BsQuizVO quiz);
	
	
	/* ---------------
	 *  댓글
	 * --------------- */
	//댓글
	//댓글 목록 - o
	public List<BsEventReplyVO> selecListReply(Map<String, Object> map);
	//댓글 개수 - o
	public int selectRowCountReply(Map<String,Object> map);
	//특정 댓글 선택 - o
	public BsEventReplyVO selectReply(Integer reply_num);
	//댓글 작성 - o
	public void insertReply(BsEventReplyVO eventReply);
	//댓글 수정
	public void updateReply(BsEventReplyVO eventReply);
	//댓글 삭제
	public void deleteReply(Integer reply_num);
	//부모글 삭제시 댓글이 존재하면 부모글 삭제 전 댓글 삭제

}

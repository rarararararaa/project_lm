package kr.spring.bookstore.event.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.junit.runners.Parameterized.Parameters;

import kr.spring.bookstore.event.vo.BsAttendancePointVO;
import kr.spring.bookstore.event.vo.BsAttendanceVO;
import kr.spring.bookstore.event.vo.BsEventReplyVO;
import kr.spring.bookstore.event.vo.BsEventVO;
import kr.spring.bookstore.event.vo.BsQuizVO;
import kr.spring.bookstore.product.vo.ProductVO;
import kr.spring.member.vo.MemberVO;

@Mapper
public interface BsEventMapper {
	//이벤트 글 목록
	public List<BsEventVO> selectEventList(Map<String, Object> map);
	public int selectEventCount(Map<String, Object> map);
	//이벤트 글 등록
	public void insertEvent(BsEventVO event);
	//이벤트 글 상세페이지
	@Select("SELECT * FROM store_event_board WHERE event_board_num=#{event_board_num} ")
	public BsEventVO selectEvent(Integer event_board_num);
	//글 조회수
	@Update("UPDATE store_event_board SET event_hit=event_hit+1 WHERE event_board_num=#{event_board_num}")
	public void updateEventHit (Integer event_board_num);
	//이벤트 글 수정
	public void updateEvent(BsEventVO event);
	//이벤트 글 삭제
	@Delete("DELETE FROM store_event_board WHERE event_board_num=#{event_board_num}")
	public void deleteEventBoard(Integer event_board_num);
	//이벤트 글에달린 댓글 삭제
	@Delete("DELETE FROM store_event_board_reply WHERE event_board_num=#{event_board_num}")
	public void deleteReplyByBoardNum(Integer event_board_num);
	//이벤트 연관상품 - isbn 불러오기
	@Select("SELECT store_product_isbn13 FROM store_product_manage WHERE store_product_num=#{store_product_num}")
	public String selectEventItemIsbn(Integer store_product_num);
	
	
	
	//종료 기간 지난 이벤트 수정
	@Update("UPDATE store_event_board SET event_board_status=3 WHERE event_board_status=2 AND TO_CHAR(SYSDATE,'YYYY-MM-DD') > event_date_end")
	public void updateEndList();
	
	
	
	//출석 이벤트 - 출석 처리
	@Insert("INSERT INTO store_event_attendance_point (event_attendance_point_num, mem_num, event_month) VALUES(store_event_attendance_point_seq.nextval, #{mem_num}, #{event_month})")
	public void insertAttendancePoint(BsAttendancePointVO attendancePoint);
	@Insert ("INSERT INTO store_event_attendance (event_attendance_num, mem_num, event_attendance_point_num, event_attendance_date) VALUES (store_event_attendance_seq.nextval, #{mem_num}, #{event_attendance_point_num}, SYSDATE)")
	public void insertAttendance(BsAttendanceVO attendance);
	
	//출석 상태 상세 - xml
	public BsAttendancePointVO selectAttendanceDetail(@Param(value="mem_num")Integer mem_num,
													  @Param(value="event_month")Integer event_month);
	//출석 상태 테이블 존재 여부
	@Select("SELECT * FROM store_event_attendance_poin WHERE mem_num=#{mem_num} AND event_month=#{event_month}")
	public BsAttendancePointVO selectCountAttendanceStatus(Map<String, Object> map);
	
	/* - 사용X 위에 수정코드
	//출석 이벤트 - 포인트부여 상세정보
	public BsAttendancePointVO selectAttendencePoint(@Param(value="mem_num") int mem_num, @Param(value="event_month") int event_month);
	ㅎ*/
	
	//출석 이벤트 - 출석 개수
	@Select("SELECT COUNT(*) FROM store_event_attendance WHERE event_attendance_point_num=#{event_attendance_point_num}")
	public int selectAttendanceCount(BsAttendancePointVO attendancePoint); 
	
	//출석 개수 list
	public List<BsAttendanceVO> selectListAtendance(BsAttendancePointVO vo );
	
	//출석 이벤트 - 포인트 부여 상태 update
	@Update("UPDATE store_event_attendance_point SET point_get1=1 WHERE event_attendance_point_num=#{event_attendance_point_num}")
	public void updateAttendancePointGet1(int event_attendance_point_num); 
	@Update("UPDATE store_event_attendance_point SET point_get2=1 WHERE event_attendance_point_num=#{event_attendance_point_num}")
	public void updateAttendancePointGet2(int event_attendance_point_num); 
	@Update("UPDATE store_event_attendance_point SET point_get3=1 WHERE event_attendance_point_num=#{event_attendance_point_num}")
	public void updateAttendancePointGet3(int event_attendance_point_num); 
	
	//회원이 가지고 있는 현재 포인트 조회
	@Select("SELECT mem_num, mem_point FROM lm_member_detail WHERE mem_num=#{mem_num}")
	public MemberVO selectMemberPoint(Integer mem_num);
	//회원 포인트 추가(update)
	//@Update("UPDATE lm_member_detail SET mem_point=#{addPoint} WHERE mem_num=#{mem_num}")
	@Update("UPDATE lm_member_detail SET mem_point=mem_point+#{addPoint} WHERE mem_num=#{mem_num}")
	public void updateMemberPoint(Map<String, Object> map);
	
	//출석 중복 검사
	@Select("SELECT * FROM store_event_attendance WHERE mem_num=#{mem_num} AND TO_CHAR(event_attendance_date,'YYYY-MM-DD')=#{event_attendance_date}")
	public BsAttendanceVO selectAttendanceCheck(Map<String, Object> map);
	
	
	//퀴즈 이벤트
	//퀴즈 이벤트 검색
	@Select("SELECT * FROM store_event_quiz_status WHERE mem_num=#{mem_num} AND event_board_num=#{event_board_num}")
	public BsQuizVO selectQuizStatus(BsQuizVO quiz);
	//퀴즈 이벤트 insert
	@Insert("INSERT INTO store_event_quiz_status (event_quiz_status_num, event_board_num, mem_num) VALUES (store_event_quiz_status_seq.nextval, #{event_board_num}, #{mem_num})")
	public void insetQuizStatus(BsQuizVO quiz);
	
	//댓글
	//댓글 목록 - o
	public List<BsEventReplyVO> selecListReply(Map<String, Object> map);
	//댓글 개수 - o
	@Select("SELECT COUNT(*) FROM store_event_board_reply WHERE event_board_num=#{event_board_num}")
	public int selectRowCountReply(Map<String,Object> map);
	//특정 댓글 선택 - o
	@Select("SELECT * FROM store_event_board_reply WHERE reply_num=#{reply_num}")
	public BsEventReplyVO selectReply(Integer reply_num);
	//댓글 작성 - o
	public void insertReply(BsEventReplyVO eventReply);
	//댓글 수정
	@Update("UPDATE store_event_board_reply SET reply_content=#{reply_content}, reply_modify_date=SYSDATE WHERE reply_num=#{reply_num}")
	public void updateReply(BsEventReplyVO eventReply);
	//댓글 삭제
	@Delete("DELETE FROM store_event_board_reply WHERE reply_num=#{reply_num}")
	public void deleteReply(Integer reply_num);
	//부모글 삭제시 댓글이 존재하면 부모글 삭제 전 댓글 삭제
	
	
}

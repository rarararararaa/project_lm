package kr.spring.library.lib_calendar.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.spring.library.lib_calendar.vo.Lib_CalendarVO;

@Mapper
public interface Lib_CalendarMapper {
	//일정 가져오기
	@Select("SELECT * FROM lib_calendar")
	public List<Lib_CalendarVO> selectCalendarEvent();
	
	//일정 개수
	public int selectCount (Map<String, Object> map);
	//일정 목록 - admin 관리용
	public List<Lib_CalendarVO> selectList(Map<String,Object> map);
		
	
	//일정 추가하기
	public void insertCalendarEvent(Lib_CalendarVO calendarVO);
	
	//일정 상세 불러오기
	@Select("SELECT * FROM lib_calendar WHERE calendar_num=#{calendar_num}")
	public Lib_CalendarVO selectScheduleDetail(Integer calendar_num);
	
	//일정 수정
	@Update("UPDATE lib_calendar SET title=#{title}, start_date=#{start_date}, start_time=#{start_time}, end_date=#{end_date}, end_time=#{end_time}, allday=#{allday} WHERE calendar_num=#{calendar_num}")
	public void updateCalendarEvent(Lib_CalendarVO calendarVO);
	//일정 삭제
	@Delete("DELETE FROM lib_calendar WHERE calendar_num=#{calendar_num}")
	public void deleteCalendarEvent(Integer calendar_num);
}

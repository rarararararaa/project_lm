package kr.spring.library.lib_calendar.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.spring.library.lib_calendar.dao.Lib_CalendarMapper;
import kr.spring.library.lib_calendar.vo.Lib_CalendarVO;

@Service
public class Lib_CalendarServiceImpl implements Lib_CalendarService{
	
	@Autowired
	Lib_CalendarMapper calendarMapper;

	@Override
	public void insertCalendarEvent(Lib_CalendarVO calendarVO) {
		calendarMapper.insertCalendarEvent(calendarVO);
	}

	@Override
	public List<Lib_CalendarVO> selectCalendarEvent() {
		// TODO Auto-generated method stub
		return calendarMapper.selectCalendarEvent();
	}

	@Override
	public Lib_CalendarVO selectScheduleDetail(Integer calendar_num) {
		// TODO Auto-generated method stub
		return calendarMapper.selectScheduleDetail(calendar_num);
	}

	@Override
	public void updateCalendarEvent(Lib_CalendarVO calendarVO) {
		calendarMapper.updateCalendarEvent(calendarVO);
	}

	@Override
	public void deleteCalendarEvent(Integer calendar_num) {
		calendarMapper.deleteCalendarEvent(calendar_num);
	}

	@Override
	public int selectCount(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return calendarMapper.selectCount(map);
	}

	@Override
	public List<Lib_CalendarVO> selectList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return calendarMapper.selectList(map);
	}
}

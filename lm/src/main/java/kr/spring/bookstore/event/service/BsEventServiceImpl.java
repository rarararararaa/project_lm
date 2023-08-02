package kr.spring.bookstore.event.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.spring.bookstore.event.dao.BsEventMapper;
import kr.spring.bookstore.event.vo.BsAttendancePointVO;
import kr.spring.bookstore.event.vo.BsAttendanceVO;
import kr.spring.bookstore.event.vo.BsEventVO;
import kr.spring.member.vo.MemberVO;

@Service
@Transactional
public class BsEventServiceImpl implements BsEventService{
	@Autowired
	BsEventMapper bsEventMapper;
	
	@Override
	public List<BsEventVO> selectList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int selectRowCount(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void insertEvent(BsEventVO event) {
		bsEventMapper.insertEvent(event);
	}

	@Override
	public BsEventVO selectEvent(Integer event_board_num) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateEvent(BsEventVO event) {
		// TODO Auto-generated method stub
		
	}
	
	//출석!!!!

	@Override
	public void insertAttendancePoint(BsAttendancePointVO attendancePoint) {
		bsEventMapper.insertAttendancePoint(attendancePoint);
	}

	@Override
	public void insertAttendance(BsAttendanceVO attendance) {
		bsEventMapper.insertAttendance(attendance);
	}

	@Override
	public BsAttendancePointVO selectAttendanceDetail(@Param(value="mem_num")Integer mem_num,
			  @Param(value="event_month")Integer event_month) {
		return bsEventMapper.selectAttendanceDetail(mem_num, event_month);
	}

	@Override
	public int selectAttendanceCount(BsAttendancePointVO attendancePoint) {
		// TODO Auto-generated method stub
		return bsEventMapper.selectAttendanceCount(attendancePoint);
	}

	
	
	//포인트 부여
	@Override
	public void updateMemberPoint(Map<String, Object> map) {
		// TODO Auto-generated method stub
		int mem_num = (Integer)map.get("mem_num");
		int addPoint = (Integer)map.get("addPoint");
		//포인트 조회
		MemberVO memVO = bsEventMapper.selectMemberPoint(mem_num);
		int beforePoint = memVO.getMem_point();
		int afterPoint = beforePoint + addPoint;
		map.put("addPoint", afterPoint);
		
		//포인트 업데이트
		bsEventMapper.updateMemberPoint(map);
		
	}

	@Override
	public BsAttendancePointVO selectCountAttendanceStatus(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return bsEventMapper.selectCountAttendanceStatus(map);
	}

	@Override
	public List<BsAttendanceVO> selectListAtendance(BsAttendancePointVO attendancePoint) {
		// TODO Auto-generated method stub
		return bsEventMapper.selectListAtendance(attendancePoint);
	}

	@Override
	public BsAttendanceVO selectAttendanceCheck(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return bsEventMapper.selectAttendanceCheck(map);
	}

	@Override
	public void updateAttendancePointGet(Map<String, Object> map) {
		// TODO Auto-generated method stub
		int event_attendance_point_num = (Integer)map.get("event_attendance_point_num");
		if(map.get("get1") != null) {
			bsEventMapper.updateAttendancePointGet1(event_attendance_point_num);
		}else if(map.get("get2") != null) {
			bsEventMapper.updateAttendancePointGet2(event_attendance_point_num);
		}else if(map.get("get3") != null) {
			bsEventMapper.updateAttendancePointGet3(event_attendance_point_num);
		}
		
	}

	


}

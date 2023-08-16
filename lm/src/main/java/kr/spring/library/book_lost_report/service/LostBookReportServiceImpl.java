package kr.spring.library.book_lost_report.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.spring.library.book_lost_report.dao.LostBookReportMapper;
import kr.spring.library.book_lost_report.vo.LostBookReportVO;
import kr.spring.library.rent.vo.RentVO;
import kr.spring.member.vo.MemberVO;

@Service
@Transactional
public class LostBookReportServiceImpl implements LostBookReportService{

	@Autowired
	LostBookReportMapper lostBookReportMapper;

	@Override
	public int selectRentCount(int mem_num) {
		// TODO Auto-generated method stub
		return lostBookReportMapper.selectRentCount(mem_num);
	}

	@Override
	public List<RentVO> selectlistRentInfo(int mem_num) {
		// TODO Auto-generated method stub
		return lostBookReportMapper.selectlistRentInfo(mem_num);
	}

	@Override
	public MemberVO selectMemberInfo(int mem_num) {
		// TODO Auto-generated method stub
		return lostBookReportMapper.selectMemberInfo(mem_num);
	}

	@Override
	public int selectLostReport_num() {
		// TODO Auto-generated method stub
		return lostBookReportMapper.selectLostReport_num();
	}

	@Override
	public void insertBookLostReport(LostBookReportVO lostBookReportVO) {
		lostBookReportMapper.insertBookLostReport(lostBookReportVO);
	}

	@Override
	public void updateBookManage_Lost(int callNumber) {
		lostBookReportMapper.updateBookManage_Lost(callNumber);
	}

	@Override
	public void updateBookHistory_Lost(int callNumber) {
		lostBookReportMapper.updateBookHistory_Lost(callNumber);
	}

	@Override
	public LostBookReportVO selectLostBookInfo(int lost_report_num) {
		// TODO Auto-generated method stub
		return lostBookReportMapper.selectLostBookInfo(lost_report_num);
	}

	
	
}

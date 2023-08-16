package kr.spring.library.book_lost_report.service;

import java.util.List;

import org.apache.ibatis.annotations.Update;

import kr.spring.library.book_lost_report.vo.LostBookReportVO;
import kr.spring.library.product.vo.BookProductVO;
import kr.spring.library.rent.vo.RentVO;
import kr.spring.member.vo.MemberVO;

public interface LostBookReportService {
	public int selectRentCount(int mem_num);

	public List<RentVO> selectlistRentInfo(int mem_num);

	public MemberVO selectMemberInfo(int mem_num);

	public int selectLostReport_num();

	public void insertBookLostReport(LostBookReportVO lostBookReportVO);

	// 대출 테이블 - 도서 상태 분실로 update
	public void updateBookManage_Lost(int callNumber);

	// 도서 테이블 - 도서 대출 상태, 대출 불가능으로 update
	public void updateBookHistory_Lost(int callNumber);

	// 분실 도서 결제완료 확인
	public LostBookReportVO selectLostBookInfo(int lost_report_num);

}

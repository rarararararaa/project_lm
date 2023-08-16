package kr.spring.library.book_lost_report.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.spring.library.book_lost_report.vo.LostBookReportVO;
import kr.spring.library.product.vo.BookProductVO;
import kr.spring.library.rent.vo.RentVO;
import kr.spring.member.vo.MemberVO;

@Mapper
public interface LostBookReportMapper {
	//대출 도서 조회
	@Select("SELECT COUNT(*) FROM lib_history WHERE mem_num=#{mem_num} AND lib_product_status=2")
	public int selectRentCount(int mem_num);
	public List<RentVO> selectlistRentInfo(int mem_num);
	
	//결제 회원 정보
	public MemberVO selectMemberInfo (int mem_num);
	
	//분실 번호 생성
	@Select("SELECT lib_book_lost_report_seq.nextval FROM DUAL")
	public int selectLostReport_num();
	//분실 테이블 결제정보 insert
	public void insertBookLostReport(LostBookReportVO lostBookReportVO);
	
	//대출 테이블 - 도서 상태 분실로 update
	@Update("UPDATE lib_product_manage SET lib_product_product_status=9 WHERE callNumber=#{callNumber}")
	public void updateBookManage_Lost(int callNumber);
	//도서 테이블 - 도서 대출 상태, 대출 불가능으로 update
	@Update("UPDATE lib_history SET lib_product_status=0 WHERE callNumber=#{callNumber}")
	public void updateBookHistory_Lost(int callNumber);
	
	//분실 도서 결제완료 확인
	public LostBookReportVO selectLostBookInfo(int lost_report_num);
	
	
}

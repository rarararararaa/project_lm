package kr.spring.library.rent.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;

import kr.spring.library.product.vo.BookProductVO;
import kr.spring.library.rent.vo.RentVO;
import kr.spring.library.rent.vo.ReservationVO;
import kr.spring.member.vo.MemberVO;

public interface RentService {
	//대출 등록
	public void insertRentHistory(RentVO rentVO);
	//대출 반납
	public void updateRentHistory(RentVO rentVO);
	//대출 상세
	public RentVO selectRent(Integer rent_num);	
	public BookProductVO selectBook(String callNumber);	
	//대여 상세
	public ReservationVO selectReservationByMemnum(Map<String, Object> map);	
	//대출 기록
	public List<RentVO> selectRentHistory(Map<String, Object> map);
	//페이징 처리 위한 횟수
	public int selectRentRowCount(Map<String, Object> map);
	//대출 권수 체크
	public int selectRentCountByMem_num(Map<String, Object> map);
	//대출 연장 -> 예약하면 대출 연장 불가->reservationService 연동
	public void updateRentDeadline(RentVO rentVO);
	//대출 연체
	//연체일 상세
	public List<RentVO> selectOverdue(int mem_num);	
	//회원 검색
	public List<MemberVO> selectSearchMember(String mem_id);
	//반납 자동 검색
	public List<RentVO> selectAllRent();	

	
}

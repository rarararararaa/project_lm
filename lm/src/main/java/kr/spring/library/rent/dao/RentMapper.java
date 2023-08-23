package kr.spring.library.rent.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.spring.library.product.vo.BookProductVO;
import kr.spring.library.rent.vo.RentVO;
import kr.spring.library.rent.vo.ReservationVO;
import kr.spring.member.vo.MemberVO;

@Mapper
public interface RentMapper {
	//대출 등록
	public void insertRentHistory(RentVO rentVO);
	public void updateBookStatus(BookProductVO bookVO); 
	//대출 반납
	public void updateRentHistory(RentVO rentVO);
	public void updateBookStatus2(BookProductVO bookVO);
	//대출 상세
	@Select("SELECT * FROM lib_history where rent_num=#{rent_num}")
	public RentVO selectRent(Integer rent_num);
	@Select("SELECT * FROM lib_product_manage WHERE callNumber=#{callNumber}")
	public BookProductVO selectBook(String callNumber);
	//대여 상세
	@Select("SELECT * FROM lib_reservation WHERE reservation_status=1 AND lib_product_isbn=#{lib_product_isbn}")
	public ReservationVO selectReservationByMemnum(Map<String, Object> map);
	//대출 기록
	public List<RentVO> selectRentHistory(Map<String, Object> map);
	//페이징 처리 위한 횟수
	public int selectRentRowCount(Map<String, Object> map);
	//대출 권수 체크
	public int selectRentCountByMem_num(Map<String, Object> map);
	//대출 연장 -> 예약하면 대출 연장 불가->reservationService 연계
	@Update("UPDATE lib_history SET return_reg_deadline=#{return_reg_deadline}+7,lib_product_status=3 WHERE rent_num=#{rent_num}")
	public void updateRentDeadline(RentVO rentVO);
	//대출 연체
	//자동화 -> 반납일 지난거 연체일수에 업데이트처리
	public void updateOverdue();
	//연체일 상세
	@Select("SELECT * FROM lib_history WHERE overdue_value IS NOT NULL AND mem_num=#{mem_num} AND lib_product_status=2")
	public List<RentVO> selectOverdue(int mem_num);
	//반납 자동 검색
	@Select("SELECT * FROM lib_history WHERE LIB_PRODUCT_STATUS=1")
	public List<RentVO> selectAllRent();
	//회원 검색
	@Select("SELECT * FROM lm_member_manage WHERE mem_id LIKE '%'||#{mem_id}||'%'")
	public List<MemberVO> selectSearchMember(String mem_id);		
}

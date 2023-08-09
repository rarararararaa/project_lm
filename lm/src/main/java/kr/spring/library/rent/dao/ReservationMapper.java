package kr.spring.library.rent.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import kr.spring.library.product.vo.BookProductVO;
import kr.spring.library.rent.vo.ReservationVO;

@Mapper
public interface ReservationMapper {
	//isbn을 통해 책 추출
	public String selectCallNumberToReservation(String lib_product_isbn);	
	//대출 예약 신청 -> 대출 가능한 도서일 경우 예약 불가
	public void insertReservation(ReservationVO reservationVO);
	//대출 중인지 확인
	public boolean selectCheckRentStatus(String callNumber);
	//1순위 예약자 상태 변경
	public void updateConfirmReservation(ReservationVO reservationVO);
	//기한 내 대출 시 대출 완료로 업데이트
	public void updateReservation(ReservationVO reservationVO);	
	//기한 내 미대출 시 예약 만료 -> 3:취소 처리 -> 2번째 대기자에게 순번
	public void updateReservation3(ReservationVO reservationVO);	
	
	//예약 권수 확인 (최대 3권)
	public int selectReservationCountByMem_num(Map<String, Object> map);	
	//예약 인원수 확인 -> 최대 3인
	public int selectReservationCountBycallNum(Map<String, Object> map);	
	
	//예약리스트
	public List<ReservationVO> selectReservationList(Map<String, Object> map);	
}

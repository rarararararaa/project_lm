package kr.spring.library.rent.service;

import java.util.List;
import java.util.Map;

import kr.spring.library.product.vo.BookProductVO;
import kr.spring.library.rent.vo.ReservationVO;

public interface ReservationService {
	//isbn을 통해 책 추출
	public List<String> selectCallNumberToReservation(String lib_product_isbn);		
	//대출 예약 신청 -> 대출 가능한 도서일 경우 예약 불가
	public void insertReservation(ReservationVO reservationVO);
	//기한 내 대출 시 대출 완료로 업데이트
	public void updateReservation(ReservationVO reservationVO);	
	//기한 내 미대출 시 예약 만료
	public void updateReservation3(ReservationVO reservationVO);		
	//예약 권수 확인 (최대 3권)
	public int selectReservationCountByMem_num(Map<String, Object> map);	
	//예약 인원수 확인 -> 최대 1인
	public int selectReservationCountByISBN(Map<String, Object> map);	
	//대출 중인지 확인
	public boolean selectCheckRentStatus(String callNumber);
	public boolean selectCheckRentStatus2(String lib_product_isbn);
	//예약 대기인 예약 상세 불러오기
	public ReservationVO selectReservationDetail(Map<String, Object> map);	
	
	//예약리스트
	public List<ReservationVO> selectReservationList(Map<String, Object> map);
	//자동화 -> 전체 예약 체크
	public List<ReservationVO> selectAllReservation();
	
}

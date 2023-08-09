package kr.spring.library.rent.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.spring.library.rent.dao.RentMapper;
import kr.spring.library.rent.dao.ReservationMapper;
import kr.spring.library.rent.vo.RentVO;
import kr.spring.library.rent.vo.ReservationVO;

@Service
public class ReservationServiceImpl implements ReservationService{
	@Autowired
	private ReservationMapper reservationMapper;
	@Autowired
	private RentMapper rentMapper;
	
	@Override
	public void insertReservation(ReservationVO reservationVO) {
		reservationMapper.insertReservation(reservationVO);
		//reservationMapper.updateConfirmReservation(reservationVO);
	}

	@Override
	public void updateReservation(ReservationVO reservationVO) {
		RentVO rentVO=new RentVO();
		rentVO.setMem_num(reservationVO.getMem_num());
		rentVO.setCallNumber(reservationVO.getCallNumber());
		//대출테이블
		rentMapper.insertRentHistory(rentVO);
		//예약테이블
		reservationMapper.updateReservation(reservationVO);
		//예약1순위
		//reservationMapper.updateConfirmReservation(reservationVO);
	}

	@Override
	public int selectReservationCountByMem_num(Map<String, Object> map) {
		return reservationMapper.selectReservationCountByMem_num(map);
	}

	@Override
	public int selectReservationCountByISBN(Map<String, Object> map) {
		return reservationMapper.selectReservationCountByISBN(map);
	}

	@Override
	public List<ReservationVO> selectReservationList(Map<String, Object> map) {
		return null;
	}

	@Override
	public void updateReservation3(ReservationVO reservationVO) {
		
	}

	@Override
	public boolean selectCheckRentStatus(String callNumber) {
		return reservationMapper.selectCheckRentStatus(callNumber);
	}

	@Override
	public List<String> selectCallNumberToReservation(String lib_product_isbn) {
		return reservationMapper.selectCallNumberToReservation(lib_product_isbn);
	}


}

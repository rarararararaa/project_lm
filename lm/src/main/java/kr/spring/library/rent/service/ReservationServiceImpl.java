package kr.spring.library.rent.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.spring.library.rent.dao.RentMapper;
import kr.spring.library.rent.dao.ReservationMapper;
import kr.spring.library.rent.vo.RentVO;
import kr.spring.library.rent.vo.ReservationVO;

@Service
@Transactional
public class ReservationServiceImpl implements ReservationService{
	@Autowired
	private ReservationMapper reservationMapper;
	@Autowired
	private RentMapper rentMapper;
	
	@Override
	public void insertReservation(ReservationVO reservationVO) {
		reservationMapper.insertReservation(reservationVO);
	}

	@Override
	public void updateReservation(ReservationVO reservationVO) {
		reservationMapper.updateReservation(reservationVO);

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
	@Scheduled(cron = "0 0/5 * * * *")
	public void updateReservationCancel() {
		reservationMapper.updateReservationCancel();
	}

	@Override
	public boolean selectCheckRentStatus(String callNumber) {
		return reservationMapper.selectCheckRentStatus(callNumber);
	}

	@Override
	public List<String> selectCallNumberToReservation(String lib_product_isbn) {
		return reservationMapper.selectCallNumberToReservation(lib_product_isbn);
	}

	@Override
	public List<ReservationVO> selectAllReservation() {
		return reservationMapper.selectAllReservation();
	}

	@Override
	public boolean selectCheckRentStatus2(String lib_product_isbn) {
		return reservationMapper.selectCheckRentStatus2(lib_product_isbn);
	}

	@Override
	public ReservationVO selectReservationDetail(Map<String, Object> map) {
		return reservationMapper.selectReservationDetail(map);
	}

	@Override
	public ReservationVO selectReservationDetailtoRent(int reservation_num) {
		return reservationMapper.selectReservationDetailtoRent(reservation_num);
	}

	@Override
	public String selectCallNumbertoRent(ReservationVO reservationVO) {
		return reservationMapper.selectCallNumbertoRent(reservationVO);
	}

	@Override
	public void updateReservationtoRent(ReservationVO reservationVO) {
		reservationMapper.updateReservationtoRent(reservationVO);
	}

	@Override
	public void updateCancelReservation(ReservationVO reservationVO) {
		reservationMapper.updateCancelReservaiton(reservationVO);
	}



}

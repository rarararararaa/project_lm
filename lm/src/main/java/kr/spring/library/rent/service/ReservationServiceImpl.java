package kr.spring.library.rent.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.spring.library.product.vo.BookProductVO;
import kr.spring.library.rent.dao.ReservationMapper;
import kr.spring.library.rent.vo.ReservationVO;

@Service
public class ReservationServiceImpl implements ReservationService{
	@Autowired
	private ReservationMapper reservationMapper;
	
	@Override
	public void insertReservation(ReservationVO reservationVO) {
		reservationMapper.insertReservation(reservationVO);
	}

	@Override
	public void updateReservation(ReservationVO reservationVO) {
		
	}

	@Override
	public int selectReservationCountByMem_num(Map<String, Object> map) {
		return reservationMapper.selectReservationCountByMem_num(map);
	}

	@Override
	public int selectReservationCountBycallNum(Map<String, Object> map) {
		return reservationMapper.selectReservationCountBycallNum(map);
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
	public String selectCallNumberToReservation(String lib_product_isbn) {
		return reservationMapper.selectCallNumberToReservation(lib_product_isbn);
	}

}

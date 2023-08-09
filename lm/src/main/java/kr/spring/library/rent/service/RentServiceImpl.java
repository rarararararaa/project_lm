package kr.spring.library.rent.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.spring.library.product.dao.BookProductMapper;
import kr.spring.library.rent.dao.RentMapper;
import kr.spring.library.rent.vo.RentVO;
import kr.spring.member.vo.MemberVO;

@Service
@Transactional
public class RentServiceImpl implements RentService{
	@Autowired
	private RentMapper rentMapper; 
	@Autowired
	private BookProductMapper bookMapper;
	
	@Override
	public void insertRentHistory(RentVO rentVO) {
		rentMapper.insertRentHistory(rentVO);
		rentMapper.updateBookStatus(bookMapper.selectDetailLIB_P(rentVO.getCallNumber()));
	}

	@Override
	public void updateRentHistory(RentVO rentVO) {
		rentMapper.updateRentHistory(rentVO);
		rentMapper.updateBookStatus2(bookMapper.selectDetailLIB_P(rentVO.getCallNumber()));
	}

	@Override
	public List<RentVO> selectRentHistory(Map<String, Object> map) {
		return rentMapper.selectRentHistory(map);
	}

	@Override
	public int selectRentRowCount(Map<String, Object> map) {
		return rentMapper.selectRentRowCount(map);
	}

	@Override
	public int selectRentCountByMem_num(Map<String, Object> map) {
		return rentMapper.selectRentCountByMem_num(map);
	}

	@Override
	public List<MemberVO> selectSearchMember(String mem_id) {
		return rentMapper.selectSearchMember(mem_id);
	}

	@Override
	public RentVO selectRent(Integer rent_num) {
		return rentMapper.selectRent(rent_num); 
	}

	@Override
	public void updateRentDeadline(RentVO rentVO) {
		rentMapper.updateRentDeadline(rentVO);
	}

}

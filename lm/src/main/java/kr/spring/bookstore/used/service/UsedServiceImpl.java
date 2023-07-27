package kr.spring.bookstore.used.service;

import java.util.List;
import java.util.Map;

import kr.spring.bookstore.used.vo.UsedVO;

public class UsedServiceImpl implements UsedService {

	@Override
	public List<UsedVO> selectUsedList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int selectUsedRowCount(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void insertUsed(UsedVO usedvo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public UsedVO selectUsed(Integer user_num) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateUsed(Integer used_product_num) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteUsed(Integer used_product_num) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int updateHit(Integer used_product_num) {
		// TODO Auto-generated method stub
		return 0;
	}

}

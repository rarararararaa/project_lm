package kr.spring.mypage.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.spring.mypage.dao.MyPageMapper;
import kr.spring.mypage.vo.MyPageVO;
import java.util.List;

@Service
@Transactional
public class MyPageServiceImpl implements MyPageService{

	@Autowired
	private MyPageMapper mypageMapper;

	@Override
	public String getMemRegDate(int mem_num) {
		return mypageMapper.getMemRegDate(mem_num);
	}
	
	@Override
	public List<MyPageVO> getDetailData(int mem_num) {
		return mypageMapper.getDetailData(mem_num);
	}

	@Override
	public int getZzimNumCount(int mem_num) {
		return 0;
	}

	@Override
	public int getReviewNumCount(int mem_num) {
		return 0;
	}

	@Override
	public int getRepNumCount(int mem_num) {
		return 0;
	}

	@Override
	public int getCouponNumCount(int mem_num) {
		return 0;
	}

	@Override
	public String getOrderPrice(int mem_num) {
		return mypageMapper.getOrderPrice(mem_num);
	}
}

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

	@Override
	public MyPageVO getMyEdit(int mem_num) {
		
		return mypageMapper.getMyEdit(mem_num);
	}

	@Override
	public void updatePasswd(MyPageVO mypageVO) {
		mypageMapper.updatePasswd(mypageVO);
	}
	
	@Override
	public void updateEmail(MyPageVO mypageVO) {
		mypageMapper.updateEmail(mypageVO);
	}

	@Override
	public void updateCell(MyPageVO mypageVO) {
		mypageMapper.updateCell(mypageVO);
	}
	
	@Override
	public void updateAuth(MyPageVO mypageVO) {
		mypageMapper.updateAuth(mypageVO);
	}
	@Override
	public String getSalt(int mem_num) {
		return mypageMapper.getSalt(mem_num);
	}

	@Override
	public int memberOutCheck(MyPageVO mypageVO) {
		return mypageMapper.memberOutCheck(mypageVO);
	}
	
	@Override
	public void memberOut(int mem_num) {
		
		//manage에 탈퇴날쨔, 상태 업데이트
		mypageMapper.memberOut(mem_num);
		//회원 상세정보, 집주소 삭제
		mypageMapper.memberOut_Home(mem_num);
		mypageMapper.memberOut_Detail(mem_num);
	}
}

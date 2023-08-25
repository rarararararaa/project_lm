package kr.spring.mypage.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.spring.member.vo.MemberVO;
import kr.spring.mypage.dao.MyPageMapper;
import kr.spring.mypage.vo.MyPageVO;

import java.sql.Blob;
import java.util.List;
import java.util.Map;

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
		return mypageMapper.getZzimNumCount(mem_num);
	}

	@Override
	public int getReviewNumCount(int mem_num) {
		return mypageMapper.getReviewNumCount(mem_num);
	}

	@Override
	public int getRepNumCount(int mem_num) {
		return mypageMapper.getRepNumCount(mem_num);
	}

	@Override
	public int getCouponNumCount(int mem_num) {
		return mypageMapper.getCouponNumCount(mem_num);
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
	public Integer memberOutCheck(MyPageVO mypageVO) {
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

	@Override
	public void updatePhoto(MyPageVO mypageVO) {
		mypageMapper.updatePhoto(mypageVO);
	}

	@Override
	public MyPageVO getPhoto(int mem_num) {
		return mypageMapper.getPhoto(mem_num);
	}
	
	@Override
	public MyPageVO getFacility(int facility_num) {
		return mypageMapper.getFacility(facility_num);
	}
	
	@Override
	public void updateModifyDate(MyPageVO mypageVO) {
		mypageMapper.updateModifyDate(mypageVO);
	}
	
	@Override
	public List<MyPageVO> getOrderList(Map<String,Object> map) {
		return mypageMapper.getOrderList(map);
	}

	@Override
	public int selectRowCountOrderList(Map<String, Object> map) {
		return mypageMapper.selectRowCountOrderList(map);
	}
	
	@Override
	public List<MyPageVO> getOrderListDetail(Map<String,Object> map) {
		return mypageMapper.getOrderListDetail(map);
	}

	@Override
	public int selectRowCountOrderListDetail(Map<String, Object> map) {
		return mypageMapper.selectRowCountOrderListDetail(map);
	}
	
	@Override
	public List<MyPageVO> getAskList(Map<String,Object> map) {
		return mypageMapper.getAskList(map);
	}

	@Override
	public int selectRowCountAskList(Map<String, Object> map) {
		return mypageMapper.selectRowCountAskList(map);
	}
	
	@Override
	public List<MyPageVO> getCheckList(Map<String,Object> map) {
		return mypageMapper.getCheckList(map);
	}

	@Override
	public int selectRowCountCheckList(Map<String, Object> map) {
		return mypageMapper.selectRowCountCheckList(map);
	}
	
	@Override
	public List<MyPageVO> getWantBookList(Map<String,Object> map) {
		return mypageMapper.getWantBookList(map);
	}

	@Override
	public int selectRowCountWantBookList(Map<String, Object> map) {
		return mypageMapper.selectRowCountWantBookList(map);
	}

	@Override
	public List<MyPageVO> getProgramList(Map<String,Object> map) {
		return mypageMapper.getProgramList(map);
	}

	@Override
	public int selectRowCountProgramList(Map<String, Object> map) {
		return mypageMapper.selectRowCountProgramList(map);
	}

	@Override
	public MyPageVO getHomeOrderList(int order_num,int mem_num) {
		return mypageMapper.getHomeOrderList(order_num,mem_num);
	}
	
	@Override
	public List<MyPageVO> getFacilityList(Map<String,Object> map) {
		return mypageMapper.getFacilityList(map);
	}

	@Override
	public int selectRowCountFacilityList(Map<String, Object> map) {
		return mypageMapper.selectRowCountFacilityList(map);
	}
	
	@Override
	public List<MyPageVO> getBookReservationList(Map<String,Object> map) {
		return mypageMapper.getBookReservationList(map);
	}

	@Override
	public int selectRowCountBookReservationList(Map<String, Object> map) {
		return mypageMapper.selectRowCountBookReservationList(map);
	}

	@Override
	public void setOrderStatus(int order_num, int mem_num) {
		mypageMapper.setOrderStatus(order_num,mem_num);
	}

	@Override
	public void passwdChangeApply(MyPageVO mypageVO) {
		mypageMapper.passwdChangeApply(mypageVO);
	}

	@Override
	public void insertHome(MyPageVO mypageVO) {
		mypageVO.setHome_num(mypageMapper.selectHome_num()); //nextval
		mypageMapper.insertHome(mypageVO);
	}
	
	@Override
	public List<MyPageVO> getMyHome(int mem_num) {
		return mypageMapper.getMyHome(mem_num);
	}
	
	@Override
	public void addDefaultHome(int home_num,int mem_num) {
		mypageMapper.deleteDefaultHome(mem_num);
		mypageMapper.addDefaultHome(home_num);
	}

	@Override
	public List<MyPageVO> getBookLostList(Map<String, Object> map) {
		return mypageMapper.getBookLostList(map);
	}

	@Override
	public int selectRowCountBookLostList(Map<String, Object> map) {
		return mypageMapper.selectRowCountBookLostList(map);
	}

	@Override
	public List<MyPageVO> getPointInfo(Map<String, Object> map) {
		return mypageMapper.getPointInfo(map);
	}

	@Override
	public int selectRowCountPointInfo(Map<String, Object> map) {
		return mypageMapper.selectRowCountPointInfo(map);
	}

	@Override
	public List<MyPageVO> getZzimBookList(Map<String, Object> map) {
		return mypageMapper.getZzimBookList(map);
	}

	@Override
	public int selectRowCountZzimBookList(Map<String, Object> map) {
		return mypageMapper.selectRowCountZzimBookList(map);
	}
	
	@Override
	public List<MyPageVO> getBookWriteList(Map<String, Object> map) {
		return mypageMapper.getBookWriteList(map);
	}

	@Override
	public int selectRowCountBookWriteList(Map<String, Object> map) {
		return mypageMapper.selectRowCountBookWriteList(map);
	}
}

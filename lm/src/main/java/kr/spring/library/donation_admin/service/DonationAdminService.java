package kr.spring.library.donation_admin.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;

import kr.spring.library.donation.vo.DonationVO;

public interface DonationAdminService {
	
	//전체기증 신청 목록
	public List<DonationVO> selectDonationAdminList(Map<String, Object> map);
	//글 개수
	public int selectRowCount(Map<String,Object> map);
	//기증신청글 삭제 - 0:확인전 1: 기증수락 2: 기증거부 (default값 0)
	public void updateByAdmin(DonationVO donationVO); 
	//글 삭제
	public void deleteDonationAdmin(Integer donation_num);
	//기증신청번호를 이용한 글정보 구하기
	public DonationVO selectDonationAdmin(Integer donation_num);

}

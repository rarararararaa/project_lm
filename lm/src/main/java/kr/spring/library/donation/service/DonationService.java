package kr.spring.library.donation.service;

import java.util.List;

import kr.spring.library.donation.vo.DonationVO;

public interface DonationService {
	public List<DonationVO> selectDonationList();
	//기증 신청 입력
	public void insertDonation(DonationVO donationVO);
	public DonationVO selectDonation(Integer donation_num);
}

package kr.spring.library.donation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.spring.library.donation.dao.DonationMapper;
import kr.spring.library.donation.vo.DonationVO;

@Service
public class DonationServiceImpl implements DonationService{

	@Autowired
	DonationMapper donationMapper;
	
	
	
	@Override
	public List<DonationVO> selectDonationList() {
		return donationMapper.selectDonationList();
	}

	@Override
	public void insertDonation(DonationVO donationVO) {
		donationMapper.insertDonation(donationVO);
	}

	@Override
	public DonationVO selectDonation(Integer donation_num) {
		// TODO Auto-generated method stub
		return null;
	}
	
}

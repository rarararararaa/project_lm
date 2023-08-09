package kr.spring.library.donation_admin.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.spring.library.donation.vo.DonationVO;
import kr.spring.library.donation_admin.dao.DonationAdminMapper;

@Service
@Transactional
public class DonationAdminServiceImpl implements DonationAdminService{
	
	@Autowired
	private DonationAdminMapper donationAdminMapper;

	@Override
	public List<DonationVO> selectDonationAdminList(Map<String, Object> map) {
		return donationAdminMapper.selectDonationAdminList(map);
	}

	@Override
	public int selectRowCount(Map<String, Object> map) {
		return donationAdminMapper.selectRowCount(map);
	}

	@Override
	public void updateByAdmin(DonationVO donationVO) {
		donationAdminMapper.updateByAdmin(donationVO);
	}

	@Override
	public void deleteDonationAdmin(Integer donation_num) {
		donationAdminMapper.deleteDonationAdmin(donation_num);
	}

	@Override
	public DonationVO selectDonationAdmin(Integer donation_num) {
		return donationAdminMapper.selectDonationAdmin(donation_num);
	}
	

}

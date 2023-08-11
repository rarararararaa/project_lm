package kr.spring.library.donation.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import kr.spring.library.donation.vo.DonationVO;

@Mapper
public interface DonationMapper {
	//전체 기증 신청 목록
	@Select("SELECT * FROM lib_book_donation")
	public List<DonationVO> selectDonationList();
	//기증 신청 입력
	@Insert("INSERT INTO lib_book_donation VALUES (lib_book_donation_seq.nextval,#{donation_book_info},#{donation_title},#{donation_content},SYSDATE,#{donation_name},#{donation_info},0)")
	public void insertDonation(DonationVO donationVO);
	public DonationVO selectDonation(Integer donation_num);
}

package kr.spring.library.donation_admin.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.spring.library.donation.vo.DonationVO;

@Mapper
public interface DonationAdminMapper {
	//전체기증 신청 목록
	public List<DonationVO> selectDonationAdminList(Map<String, Object> map);
	//글 개수
	public int selectRowCount(Map<String,Object> map); 
	@Update("UPDATE lib_book_donation SET donation_status=#{donation_status} WHERE donation_num=#{donation_num}")
	//(관리자)기증품 상태 변경 - 0:확인전 1: 기증수락 2: 기증거부 (default값 0)
	public void updateByAdmin(DonationVO donationVO);
	//기증신청글 삭제
	@Delete("DELETE FROM lib_book_donation WHERE donation_num=#{donation_num}")
	public void deleteDonationAdmin(Integer donation_num);
	//기증신청번호를 이용한 글정보 구하기
	@Select("SELECT * FROM lib_book_donation WHERE donation_num=#{donation_num}")
	public DonationVO selectDonationAdmin(Integer donation_num);
}

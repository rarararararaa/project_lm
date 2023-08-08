package kr.spring.library.rent.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import kr.spring.library.rent.vo.RentVO;
import kr.spring.member.vo.MemberVO;

@Mapper
public interface RentMapper {
	//대출 등록
	public void insertRentHistory(RentVO rentVO);
	//대출 반납
	public void updateRentHistory(RentVO rentVO);
	//대출 기록
	@Select("SELECT * FROM lib_history")
	public List<RentVO> selectRentHistory(Map<String, Object> map);
	//페이징 처리 위한 횟수
	public int selectRentRowCount(Map<String, Object> map);
	//대출 권수 체크
	public int selectRentCountByMem_num(Map<String, Object> map);
	//대출 연장 -> 예약 후
	
	//회원 검색
	@Select("SELECT * FROM lm_member_manage WHERE mem_id LIKE '%'||#{mem_id}||'%'")
	public List<MemberVO> selectSearchMember(String mem_id);		
}
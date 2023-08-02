package kr.spring.library.memberadmin.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.spring.member.vo.MemberVO;

@Mapper
public interface MemberAdminMapper {
	
//	//ID를 이용한 회원정보 체크
//	public MemberVO selectCheckMember(String mem_id);
//	//salt 가져오기
//	@Select("SELECT mem_salt FROM lm_member_detail WHERE mem_num = (SELECT mem_num FROM lm_member_manage WHERE mem_id = #{mem_id})")
//	public String selectSalt(String mem_id);
	//회원번호를 이용한 회원정보 구하기
	@Select("SELECT * FROM lm_member_manage m JOIN lm_member_detail d "
			+ "ON m.mem_num=d.mem_num WHERE m.mem_num=#{mem_num}")
	public MemberVO selectMember(Integer mem_num);
//	//회원정보 수정
//	@Update("UPDATE spmember SET nick_name=#{nick_name} WHERE mem_num=#{mem_num}")
//	public void updateMember(MemberVO member);
//	public void updateMember_detail(MemberVO member);
		
	//회원관리 - 관리자
	public int selectRowCount(Map<String,Object> map);
	public List<MemberVO> selectList(Map<String,Object> map);
	@Update("UPDATE lm_member_manage SET mem_auth=#{mem_auth} WHERE mem_num=#{mem_num}")
	public void updateByAdmin(MemberVO memberVO);
}

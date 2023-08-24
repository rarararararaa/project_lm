package kr.spring.member.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.spring.member.vo.MemberVO;

@Mapper
public interface MemberMapper {
	
	//회원관리 - 일반회원
	//시퀀스를 이용한 unique속성 mem_num 자동 생성
	@Select("SELECT lm_member_manage_seq.nextval FROM dual")
	public int selectMem_num();
	@Select("SELECT store_member_home_seq.nextval FROM dual")
	public int selectHome_num();
	//로그인 시 최근 로그인 날짜 입력
	@Update("UPDATE lm_member_detail SET mem_login_date = SYSDATE WHERE mem_num = #{mem_num}")
	public void updateLoginDate(Integer mem_num);
	//회원가입 manage 입력
	@Insert("INSERT INTO lm_member_manage (mem_num,mem_id,mem_auth,mem_reg_date) VALUES (#{mem_num},#{mem_id},4,SYSDATE)")
	public void insertMember(MemberVO member);
	public void insertMember_detail(MemberVO member);
	@Insert("INSERT INTO store_member_home (home_num,mem_num,home_title,home_zipcode,home_address,home_address_detail,home_cell,home_name,home_default) VALUES (#{home_num},#{mem_num},#{home_title},#{home_zipcode},#{home_address},#{home_address_detail},#{mem_cell},#{mem_name},0)")
	public void insertHome(MemberVO member);
	//ID를 이용한 회원정보 체크
	public MemberVO selectCheckMember(String mem_id); 
	//salt 가져오기
	@Select("SELECT mem_salt FROM lm_member_detail WHERE mem_num = (SELECT mem_num FROM lm_member_manage WHERE mem_id = #{mem_id})")
	public String selectSalt(String mem_id);
	//회원번호를 이용한 회원정보 구하기
	@Select("SELECT * FROM lm_member_manage m JOIN lm_member_detail d "
			+ "ON m.mem_num=d.mem_num WHERE m.mem_num=#{mem_num}")
	public MemberVO selectMember(Integer mem_num);
	//cell,name을 이용한 아이디 찾기
	@Select("SELECT mem_id FROM lm_member_manage WHERE mem_num = (SELECT mem_num FROM lm_member_detail WHERE mem_cell = #{mem_cell} AND mem_name = #{mem_name})")
	public String findId(MemberVO member);
	//회원정보 수정
	@Update("UPDATE spmember SET nick_name=#{nick_name} WHERE mem_num=#{mem_num}")
	public void updateMember(MemberVO member);
	public void updateMember_detail(MemberVO member);
	//비밀번호 변경
	@Select("SELECT m.mem_num FROM lm_member_detail d INNER JOIN lm_member_manage m ON d.mem_num = m.mem_num WHERE m.mem_id=#{mem_id} AND d.mem_name=#{mem_name} AND d.mem_email=#{mem_email}")
	public MemberVO changePasswdCheck(MemberVO memberVO);
	@Update("UPDATE lm_member_detail SET mem_passwd = #{mem_passwd_sha} WHERE mem_num = #{mem_num}")
	public void changePasswd(MemberVO memberVO);
	//회원탈퇴
	@Update("UPDATE spmember SET auth=0 WHERE mem_num=#{mem_num}")
	public void deleteMember(Integer mem_num);
	@Delete("DELETE FROM spmember_detail WHERE mem_num=#{mem_num}")
	public void deleteMember_detail(Integer mem_num);
	
	//자동 로그인
	@Update("UPDATE lm_member_detail SET mem_au_id=#{mem_au_id} WHERE mem_num=#{mem_num}")
	public void updateMem_au_id(
	   @Param("mem_au_id") String mem_au_id, @Param("mem_num") int mem_num);
	@Select("SELECT m.mem_num, m.mem_id, m.mem_auth, d.mem_au_id, d.mem_passwd, d.mem_email FROM lm_member_manage m JOIN lm_member_detail d ON m.mem_num=d.mem_num WHERE d.mem_au_id=#{mem_au_id}")
	public MemberVO selectMem_au_id(String mem_au_id);
	@Update("UPDATE lm_member_detail SET mem_au_id='' WHERE mem_num=#{mem_num}")
	public void deleteMem_au_id(int mem_num);
	/*
	//프로필 이미지 업데이트
	@Update("UPDATE spmember_detail SET photo=#{photo},photo_name=#{photo_name} WHERE mem_num=#{mem_num}")
	public void updateProfile(MemberVO member);
	
	//회원관리 - 관리자
	public int selectRowCount(Map<String,Object> map);
	public List<MemberVO> selectList(Map<String,Object> map);
	@Update("UPDATE spmember SET auth=#{auth} WHERE mem_num=#{mem_num}")
	public void updateByAdmin(MemberVO memberVO);
	*/
	//배송 관련
	//회원번호와 기본 배송지 확인 번호로 기본 배공지 정보 가져오기
	@Select("SELECT * FROM store_member_home WHERE mem_num=#{mem_num} AND home_default=0")
	public MemberVO homeDefault(int mem_num);
}





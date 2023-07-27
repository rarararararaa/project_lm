package kr.spring.member.service;

import java.util.List;
import java.util.Map;

import kr.spring.member.vo.MemberVO;

public interface MemberService {
	//회원관리 - 일반회원
	//회원가입
	public void insertMember(MemberVO member);
	//ID를 이용한 회원정보 체크
	public MemberVO selectCheckMember(String mem_id);
	//회원번호를 이용한 회원정보 구하기
	public MemberVO selectMember(Integer mem_num);
	//회원정보 수정
	public void updateMember(MemberVO member);
	//비밀번호 수정
	public void updatePassword(MemberVO member);
	//회원탈퇴
	public void deleteMember(Integer mem_num);
	//자동 로그인
	public void updateAu_id(String mem_au_id, int mem_num);
	public MemberVO selectAu_id(String mem_au_id);
	public void deleteAu_id(int mem_num);
	//프로필 이미지 업데이트
	public void updateProfile(MemberVO member);
	
	//회원관리 - 관리자
	public int selectRowCount(Map<String,Object> map);
	public List<MemberVO> selectList(Map<String,Object> map);
	public void updateByAdmin(MemberVO memberVO);
}




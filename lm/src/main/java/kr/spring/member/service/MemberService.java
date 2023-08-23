package kr.spring.member.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import kr.spring.member.vo.MemberVO;

public interface MemberService {
	//회원가입
	public void insertMember(MemberVO member);
	//회원가입 - 주소지
	public void insertHome(MemberVO member);
	//로그인 시 최근 로그인 날짜 입력
	public void updateLoginDate(Integer mem_num);
	//ID를 이용한 회원정보 체크
	public MemberVO selectCheckMember(String mem_id);
	//SHA-256 비밀번호 구하기
	public String selectSalt(String mem_id);
	//회원번호를 이용한 회원정보 구하기
	public MemberVO selectMember(Integer mem_num);
	//cell,name을 이용한 아이디 찾기
	public String findId(MemberVO member);
	
	
	
	//회원정보 수정
	public void updateMember(MemberVO member);
	//비밀번호 변경
	public MemberVO changePasswdCheck(MemberVO member);
	public void changePasswd(MemberVO member);
	//회원탈퇴
	public void deleteMember(Integer mem_num);
	//자동 로그인
	public void updateMem_au_id(String mem_au_id, int mem_num);
	public MemberVO selectMem_au_id(String mem_au_id);
	public void deleteMem_au_id(int mem_num);
	//프로필 이미지 업데이트
	public void updateProfile(MemberVO member);
	
	//회원관리 - 관리자
	public int selectRowCount(Map<String,Object> map);
	public List<MemberVO> selectList(Map<String,Object> map);
	public void updateByAdmin(MemberVO memberVO);
	
	//배송 관련
	//회원번호와 기본 배송지 확인 번호로 기본 배공지 정보 가져오기
	public MemberVO homeDefault(int mem_num);
}




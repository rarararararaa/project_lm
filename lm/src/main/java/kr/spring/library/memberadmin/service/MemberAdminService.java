package kr.spring.library.memberadmin.service;

import java.util.List;
import java.util.Map;

import kr.spring.member.vo.MemberVO;

public interface MemberAdminService {
	//회원번호를 이용한 회원정보 구하기
	public MemberVO selectMember(Integer mem_num);
	//회원관리 - 관리자
	public int selectRowCount(Map<String,Object> map);
	public List<MemberVO> selectList(Map<String,Object> map);
	public void updateByAdmin(MemberVO memberVO);
}

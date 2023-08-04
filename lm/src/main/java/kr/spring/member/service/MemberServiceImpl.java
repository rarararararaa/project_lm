package kr.spring.member.service;

import java.util.List;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.spring.member.dao.MemberMapper;
import kr.spring.member.vo.MemberVO;

@Service
@Transactional
public class MemberServiceImpl implements MemberService{

	@Autowired
	private MemberMapper memberMapper;
	
	@Override
	public void insertMember(MemberVO member) {
		member.setMem_num(memberMapper.selectMem_num()); //nextval
		memberMapper.insertMember(member);
		//member_detail에 넣기 전 주민등록번호 합치기
		member.setMem_identify(member.getMem_identify()+"-"+member.getMem_identify2());
		memberMapper.insertMember_detail(member);
		//선택사항인 주소 정보 입력 유무 판별
		if(member.getHome_zipcode() != null) {
			member.setHome_num(memberMapper.selectHome_num()); //nextval
			memberMapper.insertHome(member);
		}
	}

	@Override
	public MemberVO selectCheckMember(String mem_id) {
		return memberMapper.selectCheckMember(mem_id);
	}
	
	@Override
	public String selectSalt(String mem_id) {
		return memberMapper.selectSalt(mem_id);
	}

	@Override
	public void insertHome(MemberVO member) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String findId(MemberVO member) {
		return memberMapper.findId(member);
	}
	
	@Override
	public MemberVO selectMember(Integer mem_num) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void deleteMember(Integer mem_num) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateMem_au_id(String mem_au_id, int mem_num) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public MemberVO selectMem_au_id(String mem_au_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteMem_au_id(int mem_num) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateProfile(MemberVO member) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int selectRowCount(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<MemberVO> selectList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateByAdmin(MemberVO memberVO) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void updateLoginDate(Integer mem_num) {
		//로그인 날짜 업데이트
		memberMapper.updateLoginDate(mem_num);
	}

	@Override
	public void updateMember(MemberVO member) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateMem_password(MemberVO member) {
		// TODO Auto-generated method stub
		
	}

}






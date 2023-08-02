package kr.spring.library.memberadmin.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.spring.library.memberadmin.dao.MemberAdminMapper;
import kr.spring.member.vo.MemberVO;

@Service
@Transactional
public class MemberAdminServiceImpl implements MemberAdminService{

	@Autowired
	private MemberAdminMapper memberAdminMapper;
	
	@Override
	public MemberVO selectMember(Integer mem_num) {
		return memberAdminMapper.selectMember(mem_num);
	}
	
	@Override
	public int selectRowCount(Map<String, Object> map) {
		return memberAdminMapper.selectRowCount(map);
	}

	@Override
	public List<MemberVO> selectList(Map<String, Object> map) {
		return memberAdminMapper.selectList(map);
	}

	@Override
	public void updateByAdmin(MemberVO memberVO) {
		memberAdminMapper.updateByAdmin(memberVO);
	}

}

package kr.spring.mypage.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.spring.mypage.dao.MyPageMapper;

@Service
@Transactional
public class MyPageServiceImpl implements MyPageService{

	@Autowired
	private MyPageMapper mypageMapper;
}

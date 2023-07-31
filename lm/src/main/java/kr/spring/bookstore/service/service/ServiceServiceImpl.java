package kr.spring.bookstore.service.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.spring.bookstore.service.dao.ServiceMapper;
import kr.spring.bookstore.service.vo.AnnounceVO;

@Service
@Transactional
public class ServiceServiceImpl implements ServiceService{

	@Autowired
	ServiceMapper serviceMapper;
	
	@Override
	public List<AnnounceVO> selectAnnounceList() {
		return serviceMapper.selectAnnounceList();
	}

	@Override
	public void insertAnnounce(AnnounceVO announceVO) {
		serviceMapper.insertAnnounce(announceVO);
	}

}

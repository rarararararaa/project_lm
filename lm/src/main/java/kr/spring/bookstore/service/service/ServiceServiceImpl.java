package kr.spring.bookstore.service.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.spring.bookstore.service.dao.ServiceMapper;
import kr.spring.bookstore.service.vo.AnnounceVO;
import kr.spring.bookstore.service.vo.FaqVO;

@Service
@Transactional
public class ServiceServiceImpl implements ServiceService{

	@Autowired
	ServiceMapper serviceMapper;
	
	@Override
	public List<AnnounceVO> selectAnnounceList(Map<String, Object> map) {
		return serviceMapper.selectAnnounceList(map);
	}

	@Override
	public void insertAnnounce(AnnounceVO announceVO) {
		serviceMapper.insertAnnounce(announceVO);
	}

	@Override
	public int selectRowCount(Map<String, Object> map) {
		return serviceMapper.selectRowCount(map);
	}

	@Override
	public AnnounceVO selectAnnounce(Integer board_num) {
		return serviceMapper.selectAnnounce(board_num);
	}

	@Override
	public void insertFaq(FaqVO faqVO) {
		serviceMapper.insertFaq(faqVO);
	}

	@Override
	public List<FaqVO> selectFaqList(Map<String, Object> map) {
		return serviceMapper.selectFaqList(map);
	}

	@Override
	public int selectRowCountFaq(Map<String, Object> map) {
		return serviceMapper.selectRowCountFaq(map);
	}

	@Override
	public void updateFaq(FaqVO faqVO) {
		serviceMapper.updateFaq(faqVO);
	}

}

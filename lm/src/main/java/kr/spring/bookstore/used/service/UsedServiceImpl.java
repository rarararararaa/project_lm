package kr.spring.bookstore.used.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.spring.bookstore.used.dao.UsedMapper;
import kr.spring.bookstore.used.vo.UsedVO;

@Service
@Transactional
public class UsedServiceImpl implements UsedService {

	@Autowired
	UsedMapper usedMapper;
	
	@Override
	public List<UsedVO> selectUsedList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int selectUsedRowCount(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	//Insert 시작
	@Override
	public void insertUsedManage(UsedVO usedVO) {
		usedMapper.insertUsedManage(usedVO);
	}

	@Override
	public void insertUsedDetail(UsedVO usedVO) {
		usedMapper.insertUsedDetail(usedVO);
	}
	//Insert 끝
	@Override
	public List<UsedVO> selectAllUsed() {
		return usedMapper.selectAllUsed();
	}
	@Override
	public int selectAllUsedCount() {
		return usedMapper.selectAllUsedCount();
	}
	
	@Override
	public void updateUsed(UsedVO usedVO) {
		usedMapper.usedUpdateManage(usedVO);
		usedMapper.usedUpdateDetail(usedVO);
	}

	@Override
	public void deleteUsed(Integer used_product_num) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int updateHit(Integer used_product_num) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int searchProductNum(String store_product_title) {
		
		return 0;
	}

	//ProductNum을 뱉어낸다.
	@Override
	public List<UsedVO> selectProductNameByUsed(Map<String,Object> map) {
		//list 형태로 검색된 책들을 뱉어내자
		return usedMapper.selectProductNameByUsed(map);
	}

	@Override
	public void insertUsed(UsedVO usedVO) {
		usedVO.setUsed_product_num(usedMapper.getUsed_product_num());
		usedMapper.insertUsedManage(usedVO);
		usedMapper.insertUsedDetail(usedVO);
	}

	@Override
	public List<UsedVO> selectUsedProductByMem(Map<String,Object> map) {
		return usedMapper.selectUsedProductByMem(map);
	}

	@Override
	public int selectProductNameByUsedCount(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return usedMapper.selectProductNameByUsedCount(map);
	}

	@Override
	public UsedVO selectUsedProductNumAndMemNum(int used_product_num, int mem_num) {
		// 가져오자...
		return usedMapper.selectUsedProductNumAndMemNum(used_product_num, mem_num);
	}

	@Override
	public int selectUsedProductByMemCount(int mem_num) {
		
		return usedMapper.selectUsedProductByMemCount(mem_num);
	}

	@Override
	public List<UsedVO> selectUsedSalesStatus(int mem_num) {
		// TODO Auto-generated method stub
		return usedMapper.selectUsedSalesStatus(mem_num);
	}

	


}

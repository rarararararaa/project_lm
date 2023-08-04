package kr.spring.library.lib_product_manage.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.spring.library.lib_product_manage.dao.BookProductAdminMapper;
import kr.spring.library.product.vo.BookProductVO;

@Service
@Transactional
public class BookProductAdminServiceImpl implements BookProductAdminService{

	@Autowired
	private BookProductAdminMapper bookProductAdminMapper;

	@Override
	public void insertBookProduct(BookProductVO bookProductVO) {
		bookProductAdminMapper.insertBookProduct(bookProductVO);
	}

	@Override
	public BookProductVO selectDetailBookProduct(String callNumber) {
		return bookProductAdminMapper.selectDetailBookProduct(callNumber);
	}

	@Override
	public List<BookProductVO> selectList(Map<String, Object> map) {
		return bookProductAdminMapper.selectList(map);
	}
	
	@Override
	public List<BookProductVO> selectLoanList(Map<String, Object> map) {
		return bookProductAdminMapper.selectLoanList(map);
	}

	@Override
	public void updateLoanCnt(Integer lib_product_loanCnt) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int selectRowCount(Map<String, Object> map) {
		return bookProductAdminMapper.selectRowCount(map);
	}

	@Override
	public void updateByBookProductAdmin(BookProductVO bookProductVO) {
		bookProductAdminMapper.updateByBookProductAdmin(bookProductVO);
	}

	@Override
	public List<BookProductVO> selectListLoan(int lib_product_product_status) {
		return bookProductAdminMapper.selectListLoan(lib_product_product_status);
	}



}

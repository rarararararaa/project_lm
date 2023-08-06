package kr.spring.library.bookproduct_admin.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.spring.library.bookproduct_admin.dao.BookProductAdminMapper;
import kr.spring.library.bookproduct_admin.vo.BookProductAdminVO;

@Service
@Transactional
public class BookProductAdminServiceImpl implements BookProductAdminService{

	@Autowired
	private BookProductAdminMapper bookProductAdminMapper;

	@Override
	public void insertBookProduct(BookProductAdminVO bookProductAdminVO) {
		bookProductAdminMapper.insertBookProduct(bookProductAdminVO);
	}

	@Override
	public BookProductAdminVO selectDetailBookProduct(String callNumber) {
		return bookProductAdminMapper.selectDetailBookProduct(callNumber);
	}

	@Override
	public List<BookProductAdminVO> selectBookProductList(Map<String, Object> map) {
		return bookProductAdminMapper.selectBookProductList(map);
	}

	@Override
	public int selectRowCount(Map<String, Object> map) {
		return bookProductAdminMapper.selectRowCount(map);
	}

	@Override
	public void updateBookProduct(BookProductAdminVO bookProductAdmin) {
		bookProductAdminMapper.updateBookProduct(bookProductAdmin);
	}

	@Override
	public void deleteBookProduct(String callNumber) {
		bookProductAdminMapper.deleteBookProduct(callNumber);
	}

	@Override
	public void updateByBookProductAdmin(BookProductAdminVO bookProductAdminVO) {
		bookProductAdminMapper.updateByBookProductAdmin(bookProductAdminVO);
	}

	@Override
	public List<BookProductAdminVO> selectListLoan(int lib_product_product_status) {
		return bookProductAdminMapper.selectListLoan(lib_product_product_status);
	}

	@Override
	public List<BookProductAdminVO> selectLoanList(Map<String, Object> map) {
		return bookProductAdminMapper.selectLoanList(map);
	}

	@Override
	public void updateLoanCnt(Integer lib_product_loanCnt) {
		bookProductAdminMapper.updateLoanCnt(lib_product_loanCnt);
	}
}

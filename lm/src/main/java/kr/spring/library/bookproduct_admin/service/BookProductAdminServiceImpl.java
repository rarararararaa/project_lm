package kr.spring.library.bookproduct_admin.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.spring.library.bookproduct_admin.dao.BookProductAdminMapper;
import kr.spring.library.product.vo.BookProductVO;

@Service
@Transactional
public class BookProductAdminServiceImpl implements BookProductAdminService{

	@Autowired
	private BookProductAdminMapper bookProductAdminMapper;

	@Override
	public BookProductVO selectDetailBookProduct(String callNumber) {
		return bookProductAdminMapper.selectDetailBookProduct(callNumber);
	}

	@Override
	public List<BookProductVO> selectBookProductList(Map<String, Object> map) {
		return bookProductAdminMapper.selectBookProductList(map);
	}

	@Override
	public int selectRowCount(Map<String, Object> map) {
		return bookProductAdminMapper.selectRowCount(map);
	}

}

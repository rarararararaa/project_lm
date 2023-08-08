package kr.spring.library.bookproduct_admin.service;

import java.util.List;
import java.util.Map;

import kr.spring.library.product.vo.BookProductVO;

public interface BookProductAdminService {
	// 도서 상세 정보
	public BookProductVO selectDetailBookProduct(String callNumber);
	// 전체 도서 목록
	public List<BookProductVO> selectBookProductList(Map<String, Object> map);
	// 도서 개수 (xml)
	public int selectRowCount(Map<String, Object> map);
}

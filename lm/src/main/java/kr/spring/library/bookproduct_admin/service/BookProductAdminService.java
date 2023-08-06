package kr.spring.library.bookproduct_admin.service;

import java.util.List;
import java.util.Map;

import kr.spring.library.bookproduct_admin.vo.BookProductAdminVO;

public interface BookProductAdminService {
	// 신규 도서 등록
	public void insertBookProduct(BookProductAdminVO bookProductAdminVO);
	// 도서 상세 정보
	public BookProductAdminVO selectDetailBookProduct(String callNumber);
	// 전체 도서 목록
	public List<BookProductAdminVO> selectBookProductList(Map<String, Object> map);
	// 도서 개수 (xml)
	public int selectRowCount(Map<String, Object> map);
	//도서정보 수정
	public void updateBookProduct(BookProductAdminVO bookProductAdmin);
	//도서 삭제
	public void deleteBookProduct(String callNumber);

	// 대출상태 변경(0:대출가능 1:대출중) == 권한수정
	public void updateByBookProductAdmin(BookProductAdminVO bookProductAdminVO);
	// 대출중인 도서 리스트
	public List<BookProductAdminVO> selectListLoan (int lib_product_product_status);
	// 대출 도서 목록
	public List<BookProductAdminVO> selectLoanList(Map<String, Object> map);
	// 대출횟수 증가(누적) status값이 바뀌면 누적되게 해야함..
	public void updateLoanCnt(Integer lib_product_loanCnt);
}

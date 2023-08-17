package kr.spring.library.product;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import kr.spring.library.product.vo.BookProductVO;
import kr.spring.library.rent.vo.RentVO;

public interface BookProductService {
	//도서 API 읽기
	public void getData();
	//도서 상세 읽기
	public BookProductVO selectDetailLIB_P(String callNumber);
	public List<BookProductVO> selectListLIB_P(String isbn);
	public RentVO selectDate(String callNumber);
	//상세 정보 수정
	public void updateLIB_P_description();
	
	//도서 상세 API 읽기
	public String getDetailData(String isbn);
	
	//도서 검색
	public List<BookProductVO> searchDetailLIB_P(String lib_product_bookname);	
}

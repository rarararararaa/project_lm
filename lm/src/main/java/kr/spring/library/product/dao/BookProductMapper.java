package kr.spring.library.product.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.spring.library.product.vo.BookProductVO;

@Mapper
public interface BookProductMapper {
	//도서 추가
	public void insertLIB_P(BookProductVO bookProductVO);
	//도서 상세 읽기
	@Select("SELECT * FROM lib_product_manage WHERE callNumber = #{callNumber}")
	public BookProductVO selectDetailLIB_P(String callNumber);
	//대출 도서 리스트
	@Select("SELECT * FROM lib_product_manage WHERE lib_product_isbn = #{lib_product_isbn}")
	public List<BookProductVO> selectListLIB_P(String isbn);
	//전체 데이터 가져오기 (isbn 뽑기)
	@Select("SELECT * FROM lib_product_manage")
	public List<BookProductVO> getIsbn();
	//상세 정보 수정
	@Update("UPDATE lib_product_manage SET lib_product_description = #{lib_product_description} WHERE lib_product_isbn = #{lib_product_isbn}")
	public void updateLIB_P_description(String lib_product_description, String lib_product_isbn);
	
}

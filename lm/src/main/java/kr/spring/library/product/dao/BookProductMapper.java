package kr.spring.library.product.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.spring.library.product.vo.BookProductVO;
import kr.spring.library.rent.vo.RentVO;
import kr.spring.library.rent.vo.ReservationVO;

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
	//대출 반납 예정일 가져오기
	
	@Select("SELECT * FROM lib_history WHERE callNumber = #{callNumber} AND lib_product_status = 2")
	public RentVO selectDate(String callNumber);
	
	//전체 데이터 가져오기 (isbn 뽑기)
	@Select("SELECT * FROM lib_product_manage")
	public List<BookProductVO> getIsbn();
	//상세 정보 수정
	@Update("UPDATE lib_product_manage SET lib_product_detail = #{lib_product_detail} WHERE lib_product_isbn = #{lib_product_isbn}")
	public void updateLIB_P_description(String lib_product_detail, String lib_product_isbn);
	//등록 번호로 예약 현황 가져오기
	@Update("SELECT * FROM lib_reservation WHERE callnumber = #{callNumber}")
	public ReservationVO selectBook(int callNumber);
	
	//도서 검색
	@Select("SELECT * FROM lib_product_manage WHERE LIB_PRODUCT_BOOKNAME LIKE '%'||#{lib_product_bookname}||'%'")
	public List<BookProductVO> searchDetailLIB_P(String lib_product_bookname);	
}

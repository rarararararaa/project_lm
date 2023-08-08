package kr.spring.library.bookproduct_admin.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.spring.library.product.vo.BookProductVO;


@Mapper
public interface BookProductAdminMapper {
	// 도서 상세 정보
	@Select("SELECT * FROM lib_product_manage WHERE callNumber = #{callNumber}")
	public BookProductVO selectDetailBookProduct(String callNumber);
	// 전체 도서 목록
	public List<BookProductVO> selectBookProductList(Map<String, Object> map);
	// 도서 개수 (xml)
	public int selectRowCount(Map<String, Object> map);
	
}

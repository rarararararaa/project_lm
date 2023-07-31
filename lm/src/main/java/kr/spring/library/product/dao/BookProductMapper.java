package kr.spring.library.product.dao;

import org.apache.ibatis.annotations.Mapper;

import kr.spring.library.product.vo.BookProductVO;

@Mapper
public interface BookProductMapper {
	public void insertLIB_P(BookProductVO bookProductVO);
}

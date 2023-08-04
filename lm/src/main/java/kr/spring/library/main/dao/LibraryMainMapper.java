package kr.spring.library.main.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import kr.spring.library.main.vo.LibraryMainVO;

@Mapper
public interface LibraryMainMapper {
	
	
	@Select("select * FROM lib_product_manage")
	public List<LibraryMainVO> selectLibraryAllPorducts();
}

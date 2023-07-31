package kr.spring.bookstore.product.dao;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProductMapper { 
	//알라딘 api에서 데이터 추출 후 db연동
	public void fetchDataFromApi();
}

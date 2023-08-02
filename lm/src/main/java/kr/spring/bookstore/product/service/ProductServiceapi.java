package kr.spring.bookstore.product.service;

import kr.spring.bookstore.product.vo.ProductVO;

public interface ProductServiceapi {
	//알라딘 api에서 데이터 추출 후 db연동
	public void fetchData1FromApi();
	public ProductVO fetchData2FromApi(String isbn13);
}

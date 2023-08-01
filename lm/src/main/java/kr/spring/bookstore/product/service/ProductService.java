package kr.spring.bookstore.product.service;

import kr.spring.bookstore.product.vo.ProductVO;

public interface ProductService {
	//상품 상세
	public ProductVO selectProduct(String store_product_isbn13);	
}

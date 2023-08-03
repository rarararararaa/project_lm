package kr.spring.bookstore.product.service;

import java.util.List;
import java.util.Map;

import kr.spring.bookstore.product.vo.ProductVO;

public interface ProductService {
	//상품리스트
	public List<ProductVO> selectList(Map<String,Object> map);
	public int selectRowCount(Map<String,Object> map);	
	//상품 상세
	public ProductVO selectProduct(String store_product_isbn13);	
}

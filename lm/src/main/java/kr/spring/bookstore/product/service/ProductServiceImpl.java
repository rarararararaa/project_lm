package kr.spring.bookstore.product.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.spring.bookstore.product.dao.ProductMapper;
import kr.spring.bookstore.product.vo.ProductVO;

@Service
public class ProductServiceImpl implements ProductService{
	@Autowired
	ProductMapper productMapper;
	
	@Override
	public ProductVO selectProduct(String store_product_isbn13) {
		return productMapper.selectProduct(store_product_isbn13);
	}

	@Override
	public List<ProductVO> selectList(Map<String, Object> map) {
		return productMapper.selectList(map);
	}

	@Override
	public int selectRowCount(Map<String, Object> map) {
		return productMapper.selectRowCount(map);
	}

}

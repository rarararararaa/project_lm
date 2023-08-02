package kr.spring.bookstore.product.service;

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

}

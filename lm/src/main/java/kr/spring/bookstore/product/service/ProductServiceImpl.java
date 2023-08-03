package kr.spring.bookstore.product.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.spring.bookstore.product.dao.ProductMapper;
import kr.spring.bookstore.product.vo.ProductFavVO;
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

	@Override
	public ProductFavVO selectFav(ProductFavVO fav) {
		return productMapper.selectFav(fav);
	}

	@Override
	public int selectFavCount(Integer store_product_num) {
		return productMapper.selectFavCount(store_product_num);
	}

	@Override
	public void insertFav(ProductFavVO fav) {
		productMapper.insertFav(fav);
	}

	@Override
	public void deleteFav(Integer ZZIM_NUM) {
		productMapper.deleteFav(ZZIM_NUM);
	}


}

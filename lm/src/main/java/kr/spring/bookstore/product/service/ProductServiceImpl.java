package kr.spring.bookstore.product.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.spring.bookstore.product.dao.ProductMapper;
import kr.spring.bookstore.product.vo.ProductFavVO;
import kr.spring.bookstore.product.vo.ProductVO;
import kr.spring.bookstore.used.vo.UsedVO;

@Service
@Transactional
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

	@Override
	public List<UsedVO> selectUsedNum(int store_product_num) {
		return productMapper.selectUsedNum(store_product_num);
	}
	
	@Override
	public List<ProductVO> selectBestBook() {
		return productMapper.selectBestBook();
	}

	@Override
	public List<ProductVO> selectNewBook() {
		return productMapper.selectNewBook();
	}

	@Override
	public List<ProductVO> selectFuture() {
		return productMapper.selectFuture();
	}

	@Override
	public List<ProductVO> selectCategoryBook(Map<String, Object> map) {
		return productMapper.selectCategoryBook(map);
	}

	@Override
	public int selectCategoryCount(Map<String, Object> map) {
		return productMapper.selectCategoryCount(map);
	}

	@Override
	public List<ProductVO> selectBestBookList(int start, int end) {
		return productMapper.selectBestBookList(start, end);
	}


}

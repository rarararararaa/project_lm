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
		List<ProductVO> list = productMapper.selectBestBookList(start, end);
		for(ProductVO vo : list) {
			if(vo.getStore_product_title().length()>=10) {
				String title = vo.getStore_product_title().substring(0, 10)+"...";
				vo.setStore_product_title(title);
			}
		}
		return productMapper.selectBestBookList(start, end);
	}

	@Override
	public List<ProductVO> selectCateTop3(String cate) {
		List<ProductVO> list = productMapper.selectCateTop3(cate);
		for(ProductVO vo : list) {
			if(vo.getStore_product_author().length()>=10) {
				String author = vo.getStore_product_author().substring(0, 10)+"...";
				vo.setStore_product_author(author);
			}
		}
		return list;
	}

	@Override
	public List<ProductVO> selectCateRandom(String cate) {
		return productMapper.selectCateRandom(cate);
	}

	@Override
	public List<ProductVO> selectCateNew(String cate) {
		List<ProductVO> list =  productMapper.selectCateNew(cate);
		for(ProductVO vo : list) {
			if(vo.getStore_product_author().length()>=10) {
				String author = vo.getStore_product_author().substring(0, 10)+"...";
				vo.setStore_product_author(author);
			}
		}
		return list;
	}

	@Override
	public List<ProductVO> selectNew(String cate) {
		List<ProductVO> list =  productMapper.selectNew(cate);
		for(ProductVO vo : list) {
			if(vo.getStore_product_title().length()>=10) {
				String title = vo.getStore_product_title().substring(0, 10)+"...";
				vo.setStore_product_title(title);
			}
		}
		return list;
	}


}

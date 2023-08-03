package kr.spring.bookstore.product.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import kr.spring.bookstore.product.vo.ProductVO;


@Mapper
public interface ProductMapper { 
	//알라딘 api에서 데이터 추출 후 db연동
	public void fetchData1FromApi();
	public void fetchData2FromApi(String isbn13);
	//책 번호 생성
	@Select("SELECT STORE_PRODUCT_MANAGE_seq.nextval FROM dual")
	public int selectStoreProductNum();	
	public void insertStore_P(ProductVO ProductVO);
	public void insertStore_Pdetail(ProductVO ProductVO);
	
	//상품리스트
	public List<ProductVO> selectList(Map<String,Object> map);
	public int selectRowCount(Map<String,Object> map);
	
	//상품 상세
	public ProductVO selectProduct(String store_product_isbn13);	
}

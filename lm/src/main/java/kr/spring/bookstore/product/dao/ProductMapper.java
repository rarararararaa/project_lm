package kr.spring.bookstore.product.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import kr.spring.bookstore.product.vo.ProductFavVO;
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
	
	//좋아요
	@Select("SELECT * FROM STORE_MEMBER_ZZIM WHERE store_product_num=#{store_product_num} AND mem_num=#{mem_num}")
	public ProductFavVO selectFav(ProductFavVO fav);
	@Select("SELECT COUNT(*) FROM STORE_MEMBER_ZZIM WHERE store_product_num=#{store_product_num}")
	public int selectFavCount(Integer store_product_num);
	@Insert("INSERT INTO STORE_MEMBER_ZZIM (ZZIM_NUM,store_product_num,mem_num) VALUES (STORE_MEMBER_ZZIM_SEQ.nextval,#{store_product_num},#{mem_num})")
	public void insertFav(ProductFavVO fav);
	@Delete("DELETE FROM STORE_MEMBER_ZZIM WHERE ZZIM_NUM=#{ZZIM_NUM}")
	public void deleteFav(Integer ZZIM_NUM);
	@Delete("DELETE FROM STORE_MEMBER_ZZIM WHERE store_product_num=#{store_product_num}")
	public void deleteFavByProductNum(Integer store_product_num);	
}

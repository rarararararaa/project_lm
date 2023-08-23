package kr.spring.bookstore.product.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import kr.spring.bookstore.product.vo.ProductFavVO;
import kr.spring.bookstore.product.vo.ProductVO;
import kr.spring.bookstore.used.vo.UsedVO;


@Mapper
public interface ProductMapper { 
	//알라딘 api에서 데이터 추출 후 db연동
	public void fetchData1FromApi();
	public void fetchData2FromApi(String isbn13);
	//api 상품 수정
	public void updateStore_P(ProductVO productVO);
	public void updateStore_Pdetail(ProductVO productVO);
	//책 번호 생성
	@Select("SELECT STORE_PRODUCT_MANAGE_seq.nextval FROM dual")
	public int selectStoreProductNum();	
	public void insertStore_P(ProductVO ProductVO);
	public void insertStore_Pdetail(ProductVO ProductVO);
	
	//상품리스트
	public List<ProductVO> selectList(Map<String,Object> map);
	public int selectRowCount(Map<String,Object> map);
	
	//상품 번호로 중고 상품 번호 리스트
	public List<UsedVO> selectUsedNum(int store_product_num);
	
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
	
	//메인 페이지
	//상품 댓글 수 & 평점 수 상위 5개 도서 뽑기
	@Select("SELECT * FROM( SELECT * FROM store_product_manage m JOIN store_product_detail USING(store_product_num) ORDER BY store_product_ratingCount DESC)  WHERE rownum >= 1 AND rownum <=5 ORDER BY store_product_ratingscore DESC")
	public List<ProductVO> selectBestBook();
	//신간 도서
	@Select("SELECT * FROM store_product_manage m JOIN store_product_detail USING(store_product_num) WHERE store_product_pubdate <= SYSDATE  AND rownum <=5 ORDER BY store_product_pubdate DESC")
	public List<ProductVO> selectNewBook();
	//출판 예정 도서
	@Select("SELECT * FROM (SELECT * FROM store_product_manage m JOIN store_product_detail USING(store_product_num) ORDER BY store_product_pubdate DESC) WHERE rownum <= 5")
	public List<ProductVO> selectFuture();
	
	//카테고리 별 도서 검색
	public List<ProductVO> selectCategoryBook(Map<String, Object> map);
	//카데고리 별 도서 개수
	public int selectCategoryCount(Map<String, Object> map);
	//Best 도서
	public List<ProductVO> selectBestBookList(int start, int end);
	//분야별 TOP3
	public List<ProductVO> selectCateTop3(String cate);
	
}

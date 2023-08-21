package kr.spring.bookstore.review.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.spring.bookstore.payment.vo.BookStorePaymentOrderVO;
import kr.spring.bookstore.product.vo.ProductVO;
import kr.spring.bookstore.review.vo.ReviewVO;
import kr.spring.bookstore.service.vo.OrderDetailVO;

@Mapper
public interface ReviewMapper {
	//구매한 사람인지 체크
	public List<OrderDetailVO> selectOrderDetail(Map<String, Object> map);
	//주문내역 불러오기
	public List<BookStorePaymentOrderVO> selectOrderVO(Map<String, Object> map);
	//삭제한 리뷰인지 체크
	public int selectReviewDeleteCheck(Map<String, Object> map);
	//리뷰 리스트
	public List<ReviewVO> selectReviewList(Map<String, Object> map);
	//리뷰 작성
	public void insertReview(ReviewVO reviewVO);
	//포인트 입금
	@Update("UPDATE lm_member_detail SET mem_point=mem_point+150 WHERE mem_num=#{mem_num}")
	public void updateMemberPoint(int mem_num);
	//별점 상품에 업데이트
	public float selectUpdateProductRating(Map<String,Object> map);
	public void updateProductRating(ProductVO productVO);
	//상품당 리뷰 개수 -> 상품에도 업데이트
	public int selectReviewCount(Map<String,Object> map);
	@Update("UPDATE store_product_detail SET store_product_ratingcount=1+store_product_ratingcount WHERE store_product_num=#{store_product_num}")
	public void updateProductReviewCount(ProductVO productVO);
	//별점당 리뷰 개수
	public int selectReviewCountBetween(Map<String, Object> map);
	//리뷰
	@Select("SELECT * FROM lm_review WHERE review_num=#{review_num}")
	public ReviewVO selectReview(int review_num);
	//리뷰 수정
	public void updateReview(ReviewVO reviewVO);
	public float selectModifyRating(Map<String, Object> map);
	//리뷰 삭제->카운트에도 처리,평균 별점
	@Update("UPDATE lm_review SET review_deleted=1 WHERE review_num=#{review_num}")
	public void UpdateDeleteReview(ReviewVO reviewVO);
	@Update("UPDATE store_product_detail SET store_product_ratingcount=store_product_ratingcount-1 WHERE store_product_num=#{store_product_num}")
	public void updateProductReviewCount2(ProductVO productVO);
	public float selectModifyRating2(Map<String, Object> map);
	//상품 VO
	public ProductVO selectProductVO(int store_product_num);
	
}

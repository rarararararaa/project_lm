package kr.spring.bookstore.review.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.spring.bookstore.payment.vo.BookStorePaymentOrderVO;
import kr.spring.bookstore.review.vo.ReviewVO;
import kr.spring.bookstore.service.vo.OrderDetailVO;

@Mapper
public interface ReviewMapper {
	//구매한 사람인지 체크
	public List<OrderDetailVO> selectOrderDetail(Map<String, Object> map);
	//주문내역 불러오기
	public List<BookStorePaymentOrderVO> selectOrderVO(Map<String, Object> map);
	//이미 작성한 구매 내역건인지 체크
	public List<ReviewVO> selectReviewCheck(Map<String, Object> map);
	//리뷰 리스트
	public List<ReviewVO> selectReviewList(Map<String, Object> map);
	//리뷰 작성
	public void insertReview(ReviewVO reviewVO);
	//별점 상품에 업데이트
	public float selectUpdateProductRating(Map<String,Object> map);
	public void updateProductRating(Map<String,Object> map);
	//상품당 리뷰 개수 -> 상품에도 업데이트
	public int selectReviewCount(Map<String,Object> map);
	@Update("UPDATE store_product_detail SET store_product_ratingcount=#{count}+store_product_ratingcount WHERE store_product_num=#{store_product_num}")
	public void updateProductReviewCount(Map<String,Object> map);
	//별점당 리뷰 개수
	public int selectReviewCountBetween(Map<String, Object> map);
	//리뷰
	@Select("SELECT * FROM lm_review WHERE review_num=#{review_num}")
	public ReviewVO selectReview(int review_num);
	//리뷰 수정
	public void updateReview(ReviewVO reviewVO);
	//리뷰 삭제
	public void deleteReview(Integer review_num);
	
}

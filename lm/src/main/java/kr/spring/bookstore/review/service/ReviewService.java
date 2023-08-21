package kr.spring.bookstore.review.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;

import kr.spring.bookstore.payment.vo.BookStorePaymentOrderVO;
import kr.spring.bookstore.product.vo.ProductVO;
import kr.spring.bookstore.review.vo.ReviewVO;
import kr.spring.bookstore.service.vo.OrderDetailVO;

public interface ReviewService {
	//구매한 사람인지 체크
	public List<OrderDetailVO> selectOrderDetail(Map<String, Object> map);
	//주문내역 불러오기
	public List<BookStorePaymentOrderVO> selectOrderVO(Map<String, Object> map);
	public int selectReviewDeleteCheck(Map<String, Object> map);
	//리뷰 리스트
	public List<ReviewVO> selectReviewList(Map<String, Object> map);
	//리뷰 작성
	public void insertReview(ReviewVO reviewVO);
	//포인트 부여
	
	//별점당 리뷰 개수
	public int selectReviewCountBetween(Map<String, Object> map);
	//상품당 리뷰 개수 -> 상품에도 업데이트
	public int selectReviewCount(Map<String,Object> map);
	//리뷰 수정
	public void updateReview(ReviewVO reviewVO);
	//리뷰 삭제
	public void deleteReview(ReviewVO reviewVO);
	//리뷰
	public ReviewVO selectReview(int review_num);	
	//상품 VO
	public ProductVO selectProductVO(int store_product_num);	
}

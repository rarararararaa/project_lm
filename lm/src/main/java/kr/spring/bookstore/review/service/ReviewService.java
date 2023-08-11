package kr.spring.bookstore.review.service;

import java.util.List;
import java.util.Map;

import kr.spring.bookstore.payment.vo.BookStorePaymentOrderVO;
import kr.spring.bookstore.review.vo.ReviewVO;
import kr.spring.bookstore.service.vo.OrderDetailVO;

public interface ReviewService {
	//구매한 사람인지 체크
	public OrderDetailVO selectOrderDetail(BookStorePaymentOrderVO orderVO);
	//주문내역 불러오기
	public List<BookStorePaymentOrderVO> selectOrderVO(Map<String, Object> map);
	//이미 작성한 구매 내역건인지 체크
	public boolean selectReviewCheck(Map<String, Object> map);
	//리뷰 리스트
	public List<ReviewVO> selectReviewList(Map<String, Object> map);
	//리뷰 작성
	public void insertReview(ReviewVO reviewVO);
	//포인트 부여
	
	//별점 상품에 업데이트
	public void updateProductRating();
	//상품당 리뷰 개수 -> 상품에도 업데이트
	public int selectReviewCount(Map<String,Object> map);
	//리뷰 수정
	public void updateReview(ReviewVO reviewVO);
	//리뷰 삭제
	public void deleteReview(Integer review_num);
}

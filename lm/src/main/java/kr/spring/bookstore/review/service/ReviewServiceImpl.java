package kr.spring.bookstore.review.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.spring.bookstore.payment.vo.BookStorePaymentOrderVO;
import kr.spring.bookstore.review.dao.ReviewMapper;
import kr.spring.bookstore.review.vo.ReviewVO;
import kr.spring.bookstore.service.vo.OrderDetailVO;

@Service
public class ReviewServiceImpl implements ReviewService{
	@Autowired
	private ReviewMapper reviewMapper;
	

	@Override
	public List<ReviewVO> selectReviewCheck(Map<String, Object> map) {
		return reviewMapper.selectReviewCheck(map);
	}

	@Override
	public List<ReviewVO> selectReviewList(Map<String, Object> map) {
		return reviewMapper.selectReviewList(map);
	}

	@Override
	public void insertReview(ReviewVO reviewVO) {
		reviewMapper.insertReview(reviewVO);
	}

	@Override
	public void updateProductRating() {
		
	}

	@Override
	public int selectReviewCount(Map<String, Object> map) {
		return reviewMapper.selectReviewCount(map);
	}

	@Override
	public void updateReview(ReviewVO reviewVO) {
		
	}

	@Override
	public void deleteReview(Integer review_num) {
		
	}

	@Override
	public List<OrderDetailVO> selectOrderDetail(Map<String, Object> map) {
		return reviewMapper.selectOrderDetail(map);
	}

	@Override
	public List<BookStorePaymentOrderVO> selectOrderVO(Map<String, Object> map) {
		return reviewMapper.selectOrderVO(map);
	}

	@Override
	public ReviewVO selectReview(int review_num) {
		return reviewMapper.selectReview(review_num);
	}

}

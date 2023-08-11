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
	public boolean selectReviewCheck(Map<String, Object> map) {
		return reviewMapper.selectReviewCheck(map);
	}

	@Override
	public List<ReviewVO> selectReviewList(Map<String, Object> map) {
		return null;
	}

	@Override
	public void insertReview(ReviewVO reviewVO) {
		
	}

	@Override
	public void updateProductRating() {
		
	}

	@Override
	public int selectReviewCount(Map<String, Object> map) {
		return 0;
	}

	@Override
	public void updateReview(ReviewVO reviewVO) {
		
	}

	@Override
	public void deleteReview(Integer review_num) {
		
	}

	@Override
	public OrderDetailVO selectOrderDetail(BookStorePaymentOrderVO orderVO) {
		return reviewMapper.selectOrderDetail(orderVO);
	}

	@Override
	public List<BookStorePaymentOrderVO> selectOrderVO(Map<String, Object> map) {
		return reviewMapper.selectOrderVO(map);
	}

}

package kr.spring.bookstore.review.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.spring.bookstore.payment.vo.BookStorePaymentOrderVO;
import kr.spring.bookstore.product.vo.ProductVO;
import kr.spring.bookstore.review.dao.ReviewMapper;
import kr.spring.bookstore.review.vo.ReviewVO;
import kr.spring.bookstore.service.vo.OrderDetailVO;
import kr.spring.lm.point.dao.PointMapper;

@Service
public class ReviewServiceImpl implements ReviewService{
	@Autowired
	private ReviewMapper reviewMapper;
	@Autowired
	private PointMapper pointMapper;
	

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
		reviewMapper.updateMemberPoint(reviewVO.getMem_num());
		reviewMapper.updateProductReviewCount(reviewVO.getProductVO());
		pointMapper.insertReviewPoint(reviewVO.getPointVO());
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

	@Override
	public int selectReviewCountBetween(Map<String, Object> map) {
		return reviewMapper.selectReviewCountBetween(map);
	}

	@Override
	public void updateProductRating(Map<String, Object> map) {
		Map<String, Object> map2=new HashMap<String, Object>();
		map2.put("store_product_num", map.get("store_product_num"));
		map2.put("store_product_ratingscore", reviewMapper.selectUpdateProductRating(map));
		
		reviewMapper.updateProductRating(map2);
	}

	@Override
	public ProductVO selectProductVO(int store_product_num) {
		return reviewMapper.selectProductVO(store_product_num);
	}


}

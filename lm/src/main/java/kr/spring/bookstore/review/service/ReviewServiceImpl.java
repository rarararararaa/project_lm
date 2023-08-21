package kr.spring.bookstore.review.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.spring.bookstore.payment.vo.BookStorePaymentOrderVO;
import kr.spring.bookstore.product.vo.ProductVO;
import kr.spring.bookstore.review.dao.ReviewMapper;
import kr.spring.bookstore.review.vo.ReviewVO;
import kr.spring.bookstore.service.vo.OrderDetailVO;
import kr.spring.lm.point.dao.PointMapper;

@Service
@Transactional
public class ReviewServiceImpl implements ReviewService{
	@Autowired
	private ReviewMapper reviewMapper;
	@Autowired
	private PointMapper pointMapper;
	

	@Override
	public int selectReviewDeleteCheck(Map<String, Object> map) {
		return reviewMapper.selectReviewDeleteCheck(map);
	}

	@Override
	public List<ReviewVO> selectReviewList(Map<String, Object> map) {
		return reviewMapper.selectReviewList(map);
	}

	@Override
	public void insertReview(ReviewVO reviewVO) {
		reviewMapper.insertReview(reviewVO);
		reviewMapper.updateMemberPoint(reviewVO.getMem_num());
		pointMapper.insertReviewPoint(reviewVO.getPointVO());
		ProductVO product=reviewVO.getProductVO();
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("store_product_num", product.getStore_product_num());
		map.put("review_rating", reviewVO.getReview_rating());
		product.setStore_product_ratingScore(reviewMapper.selectUpdateProductRating(map));
		reviewMapper.updateProductReviewCount(reviewVO.getProductVO());
		reviewMapper.updateProductRating(product);
	}


	@Override
	public int selectReviewCount(Map<String, Object> map) {
		return reviewMapper.selectReviewCount(map);
	}

	@Override
	public void updateReview(ReviewVO reviewVO) {
		ReviewVO db_review=reviewMapper.selectReview(reviewVO.getReview_num());
		int old_review_rating=db_review.getReview_rating();
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("old_review_rating", old_review_rating);
		reviewMapper.updateReview(reviewVO);
		ProductVO product=reviewVO.getProductVO();
		map.put("review_rating", reviewVO.getReview_rating());
		map.put("store_product_num", product.getStore_product_num());
		product.setStore_product_ratingScore(reviewMapper.selectModifyRating(map));
		reviewMapper.updateProductRating(product);
	}

	@Override
	public void deleteReview(ReviewVO reviewVO) {
		ReviewVO db_review=reviewMapper.selectReview(reviewVO.getReview_num());
		int review_rating=db_review.getReview_rating();
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("review_rating", review_rating);
		ProductVO product=reviewVO.getProductVO();
		map.put("store_product_num", product.getStore_product_num());
		float store_product_ratingScore=reviewMapper.selectModifyRating2(map);
		if(store_product_ratingScore>10) {
			store_product_ratingScore=10;
		}
		product.setStore_product_ratingScore(store_product_ratingScore);
		reviewMapper.updateProductRating(product);		
		reviewMapper.updateProductReviewCount2(product);
		reviewMapper.UpdateDeleteReview(reviewVO);
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
	public ProductVO selectProductVO(int store_product_num) {
		return reviewMapper.selectProductVO(store_product_num);
	}



}

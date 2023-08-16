package kr.spring.lm.point.dao;

import org.apache.ibatis.annotations.Mapper;

import kr.spring.lm.point.vo.PointVO;

@Mapper
public interface PointMapper {
	//댓글 달때 포인트 적립 로그 남기기
	public void insertReviewPoint(PointVO pointVO);
}

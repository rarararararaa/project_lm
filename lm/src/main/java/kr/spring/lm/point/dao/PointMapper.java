package kr.spring.lm.point.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.spring.lm.point.vo.PointVO;

@Mapper
public interface PointMapper {
	//댓글 달때 포인트 적립 로그 남기기
	public void insertReviewPoint(PointVO pointVO);
	public void insertOrderPoint(PointVO pointVO);
	
	//주문 취소 할 때 해당 주문의 point 조회
	@Select("SELECT * FROM lm_point WHERE point_reason = #{point_reason}")
	public List<PointVO> selectPoint(String point_reason);
	//주문 취소 상태로 변환
	@Update("UPDATE lm_point SET point_status = 4,point_reason='주문취소' WHERE point_reason = #{point_reason}")
	public void updatePointStatus(String point_reason);
}

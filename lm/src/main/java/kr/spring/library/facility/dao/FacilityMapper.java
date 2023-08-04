package kr.spring.library.facility.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import kr.spring.library.facility.vo.FacilityVO;

@Mapper
public interface FacilityMapper {
	//시설 추가
	public void insertFacility(FacilityVO facility);
	//시설 목록(검색)
	public List<FacilityVO> selectFacilityList(Map<String, Object> map);
	//시설 번호로 시설 검색
	public FacilityVO selectFacility(Integer facility_num);
	
}

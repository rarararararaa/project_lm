package kr.spring.library.facility.service;

import java.util.List;
import java.util.Map;

import kr.spring.library.facility.vo.FacilityVO;

public interface FacilityService {
	//시설 추가
	public void insertFacility(FacilityVO facility);
	//시설 목록(검색)
	public List<FacilityVO> selectFacilityList(Map<String, Object> map);
	public int selectFacilityCount(Map<String, Object> map);
	//시설 번호로 시설 검색
	public FacilityVO selectFacility(Integer facility_num);
}

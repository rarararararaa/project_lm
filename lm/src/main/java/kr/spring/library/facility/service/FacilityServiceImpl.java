package kr.spring.library.facility.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.spring.library.facility.dao.FacilityMapper;
import kr.spring.library.facility.vo.FacilityVO;

@Service
public class FacilityServiceImpl implements FacilityService{

	@Autowired
	FacilityMapper facilityMapper;
	
	@Override
	public void insertFacility(FacilityVO facility) {
		facilityMapper.insertFacility(facility);
	}

	@Override
	public List<FacilityVO> selectFacilityList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FacilityVO selectFacility(Integer facility_num) {
		// TODO Auto-generated method stub
		return null;
	}

}
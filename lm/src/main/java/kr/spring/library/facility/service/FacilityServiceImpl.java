package kr.spring.library.facility.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.spring.library.facility.dao.FacilityMapper;
import kr.spring.library.facility.vo.FacilityApplyVO;
import kr.spring.library.facility.vo.FacilityVO;

@Service
@Transactional
public class FacilityServiceImpl implements FacilityService{

	@Autowired
	FacilityMapper facilityMapper;
	
	@Override
	public void insertFacility(FacilityVO facility) {
		facilityMapper.insertFacility(facility);
	}

	@Override
	public List<FacilityVO> selectFacilityList(Map<String, Object> map) {
		return facilityMapper.selectFacilityList(map);
	}

	@Override
	public FacilityVO selectFacility(Integer facility_num) {
		return facilityMapper.selectFacility(facility_num);
	}

	@Override
	public int selectFacilityCount(Map<String, Object> map) {
		return facilityMapper.selectFacilityCount(map);
	}

	@Override
	public void insertFacilityApply(FacilityApplyVO fac_apply) {
		facilityMapper.insertFacilityApply(fac_apply);
	}

	@Override
	public List<FacilityApplyVO> selectFacilityApplyList(Integer facility_num) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<FacilityApplyVO> selectFacilityApplyListByMem_num(Integer mem_num) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<FacilityApplyVO> selectFacilityApplyListByDate(String date, Integer facility_num) {
		return facilityMapper.selectFacilityApplyListByDate(date,facility_num);
	}

}

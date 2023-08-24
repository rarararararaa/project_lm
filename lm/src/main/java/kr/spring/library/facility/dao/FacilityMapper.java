package kr.spring.library.facility.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import kr.spring.library.facility.vo.FacilityApplyVO;
import kr.spring.library.facility.vo.FacilityVO;

@Mapper
public interface FacilityMapper {
	//시설 추가
	public void insertFacility(FacilityVO facility);
	//시설 목록(검색)
	public List<FacilityVO> selectFacilityList(Map<String, Object> map);
	public int selectFacilityCount(Map<String, Object> map);
	//시설 번호로 시설 검색
	@Select("SELECT * FROM lib_facility_admin WHERE facility_num=#{facility_num}")
	public FacilityVO selectFacility(Integer facility_num);
	
	//시설 이용 신청 등록
	public void insertFacilityApply(FacilityApplyVO fac_apply);
	//시설별 신청 목록
	public List<FacilityApplyVO> selectFacilityApplyList(Integer facility_num);
	//사용자별 신청 목록
	public List<FacilityApplyVO> selectFacilityApplyListByMem_num(Integer mem_num);
	
	//당일 시설 사용 시간 체크
	@Select("select facility_apply_start, facility_apply_end FROM lib_facility_apply WHERE facility_apply_start LIKE to_date(#{date},'YYYYMMDD') AND facility_num=#{facility_num}")
	public List<FacilityApplyVO> selectFacilityApplyListByDate(String date,Integer facility_num);
}

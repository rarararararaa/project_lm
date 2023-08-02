package kr.spring.bookstore.used.service;

import java.util.List;
import java.util.Map;

import kr.spring.bookstore.used.vo.UsedVO;

public interface UsedService {
	//Used Service
	//중고 책 등록 하기
	public void insertUsed(UsedVO usedVO);
	//매니지 등록
	public void insertUsedManage(UsedVO usedVO);
	//디테일 등록
	public void insertUsedDetail(UsedVO usedVO);
	//기존 책 번호 (product_num) 가져오기 
	public int searchProductNum(String store_product_title);
	
	//팝업 영역!!
	//pop업창에서 찾는 PorductNum
	public List<UsedVO> selectProductNameByUsed(Map<String,Object> map);
	//중고 책 List 로 반환
	public List<UsedVO> selectUsedList(Map<String,Object> map);
	
	
	//RowCount
	public int selectUsedRowCount(Map<String,Object>map);
	
	
	//중고 책 목록 전부 반환
	public List<UsedVO> selectAllUsed();
	
	//중고 책 수정하기
	public void updateUsed(Integer used_product_num);
	
	//중고 책 지우기
	public void deleteUsed(Integer used_product_num);
	
	//중고 책 조회 수
	public int updateHit(Integer used_product_num);
	
	
}

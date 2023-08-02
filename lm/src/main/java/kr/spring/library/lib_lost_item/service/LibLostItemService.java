package kr.spring.library.lib_lost_item.service;

import java.util.List;
import java.util.Map;

import kr.spring.library.lib_lost_item.vo.LibLostItemVO;

public interface LibLostItemService {
	//부모글
		public List<LibLostItemVO> selectList(Map<String,Object> map);//게시글 목록
		public int selectRowCount(Map<String,Object> map);//게시글 개수
		public void insertLibLostItem(LibLostItemVO libLostItem);//글 등록
		public LibLostItemVO selectLibLostItem(Integer item_num);//게시글 상세
		public void updateHit(Integer item_num);//조회수 증가
		public void updateLibLostItem(LibLostItemVO libLostItem);//게시글 수정
		public void deleteLibLostItem(Integer item_num);//게시글 삭제
}

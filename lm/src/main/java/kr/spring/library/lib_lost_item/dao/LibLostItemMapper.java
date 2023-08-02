package kr.spring.library.lib_lost_item.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

import kr.spring.library.lib_lost_item.vo.LibLostItemVO;


@Mapper
public interface LibLostItemMapper {
	//부모글
	public List<LibLostItemVO> selectList(Map<String,Object> map);//게시글 목록
	public int selectRowCount(Map<String,Object> map);//게시글 개수
	public void insertLibLostItem(LibLostItemVO libLostItem);//글 등록
	public LibLostItemVO selectLibLostItem(Integer item_num);//게시글 상세
	@Update("UPDATE lib_lost_item SET item_hit=item_hit+1 WHERE item_num=#{item_num}")
	public void updateHit(Integer item_num);//조회수 증가
	@Update("UPDATE lib_lost_item SET item_title=#{item_title},"
			  + "item_content=#{item_content},"
			  + "item_modify_date=SYSDATE "
			  + "WHERE item_num=#{item_num}")
	public void updateLibLostItem(LibLostItemVO libLostItem);//게시글 수정
	@Delete("DELETE FROM lib_lost_item WHERE item_num=#{item_num}")
	public void deleteLibLostItem(Integer item_num);//게시글 삭제
	
}







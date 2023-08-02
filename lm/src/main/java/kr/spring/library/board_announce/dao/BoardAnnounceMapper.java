package kr.spring.library.board_announce.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

import kr.spring.library.board_announce.vo.BoardAnnounceVO;


@Mapper
public interface BoardAnnounceMapper {
	//부모글
	public List<BoardAnnounceVO> selectList(Map<String,Object> map);//게시글 목록
	public int selectRowCount(Map<String,Object> map);//게시글 개수
	public void insertBoardAnnounce(BoardAnnounceVO boardAnnounce);//글 등록
	public BoardAnnounceVO selectBoardAnnounce(Integer notice_num);//게시글 상세
	@Update("UPDATE board_announce SET notice_hit=notice_hit+1 WHERE notice_num=#{notice_num}")
	public void updateHit(Integer notice_num);//조회수 증가
	@Update("UPDATE board_announce SET notice_title=#{notice_title},"
			  + "notice_content=#{notice_content},"
			  + "notice_modify_date=SYSDATE "
			  + "WHERE notice_num=#{notice_num}")
	public void updateBoardAnnounce(BoardAnnounceVO boardAnnounce);//게시글 수정
	@Delete("DELETE FROM board_announce WHERE notice_num=#{notice_num}")
	public void deleteBoardAnnounce(Integer notice_num);//게시글 삭제
	
}







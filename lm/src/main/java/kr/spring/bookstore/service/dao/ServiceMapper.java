package kr.spring.bookstore.service.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import kr.spring.bookstore.service.vo.AnnounceVO;

@Mapper
public interface ServiceMapper {
	@Select("SELECT * FROM lm_board_announce")
	public List<AnnounceVO> selectAnnounceList();
	@Insert("INSERT INTO lm_board_announce (board_num,board_title,board_content,board_reg_date,board_filename)VALUES (board_announce_seq.nextval,#{board_title},#{board_content},SYSDATE,#{board_filename})")
	public void insertAnnounce(AnnounceVO announceVO);
}

package kr.spring.bookstore.event.vo;

import kr.spring.util.DurationFromNow;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BsEventReplyVO {
	private int reply_num;
	private int event_board_num;
	private int mem_num;
	private String reply_date;
	private String reply_modify_date;
	private String reply_content;
	
	private String mem_id;
	
	public void setReply_date(String reply_date) {
		this.reply_date = DurationFromNow.getTimeDiffLabel(reply_date);
	}
	public void setReply_modify_date(String reply_modify_date) {
		this.reply_modify_date = DurationFromNow.getTimeDiffLabel(reply_modify_date);
	}
}

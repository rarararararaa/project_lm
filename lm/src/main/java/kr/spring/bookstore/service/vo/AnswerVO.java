package kr.spring.bookstore.service.vo;

import java.io.IOException;
import java.sql.Date;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AnswerVO {
	private int answer_num;
	private int ask_num;
	private int mem_num;
	private String answer_content;
	private Date answer_reg_date;
	private byte[] answer_image;
	private String answer_imagename;
	
	public void setUpload1(MultipartFile upload1)
			throws IOException{
		//MultipartFile -> byte[] 변환
		setAnswer_image(upload1.getBytes());
		setAnswer_imagename(upload1.getOriginalFilename());
	}
	
	
	/*ANSWER_NUM	NUMBER
	ASK_NUM	NUMBER
	MEM_NUM	NUMBER
	ANSWER_CONTENT	VARCHAR2(1000 BYTE)
	ANSWER_REG_DATE	DATE
	ANSWER_IMAGE	BLOB
	ANSWER_IMAGENAME	VARCHAR2(30 BYTE)*/
}

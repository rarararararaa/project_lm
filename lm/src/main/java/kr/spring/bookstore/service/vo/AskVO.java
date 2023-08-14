package kr.spring.bookstore.service.vo;

import java.io.IOException;
import java.sql.Date;

import javax.validation.constraints.NotEmpty;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(exclude= {"ask_image","ask_content"})
public class AskVO {
	private int ask_num;
	private int mem_num;
	@NotEmpty
	private String ask_title;
	@NotEmpty
	private String ask_content;
	private int ask_category;
	private Date ask_reg_date;
	private byte[] ask_image;
	private String ask_imagename;
	private int ask_status;
	
	public void setUpload1(MultipartFile upload1)
			throws IOException{
		//MultipartFile -> byte[] 변환
		setAsk_image(upload1.getBytes());
		setAsk_imagename(upload1.getOriginalFilename());
	}
	
	/*ASK_NUM	NUMBER
	MEM_NUM	NUMBER
	ASK_TITLE	VARCHAR2(300 BYTE)
	ASK_CONTENT	VARCHAR2(1000 BYTE)
	ASK_CATEGORY	NUMBER
	ASK_REG_DATE	DATE
	ASK_IMAGE	BLOB
	ASK_IMAGENAME	VARCHAR2(30 BYTE)*/
}

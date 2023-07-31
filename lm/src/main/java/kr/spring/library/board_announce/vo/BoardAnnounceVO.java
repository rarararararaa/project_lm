package kr.spring.library.board_announce.vo;

import java.io.IOException;
import java.sql.Date;

import javax.validation.constraints.NotEmpty;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BoardAnnounceVO {//공지사항 게시판
	private int notice_num; //글번호
	@NotEmpty
	private String notice_title; //제목
	@NotEmpty
	private String notice_content; //내용
	private byte[] notice_img; //이미지
	private String notice_filename; //첨부파일 이름
	@NotEmpty
	private int notice_category; //카테고리
	@NotEmpty
	private Date notice_reg_date; //작성일
	private Date notice_modify_date; //수정일
	
	
	//=======이미지를 byte[]로 변환(BLOB 처리)=======//
	//(주의) 폼에서 파일 업로드 파라미터네임은 반드시 upload로 지정해야 함
	public void setUpload(MultipartFile upload)throws IOException{
		//MultipartFile -> byte[]
		setNotice_img(upload.getBytes());
		//파일 이름
		setNotice_filename(upload.getOriginalFilename());
	}
}

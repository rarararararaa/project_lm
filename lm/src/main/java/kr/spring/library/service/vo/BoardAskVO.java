package kr.spring.library.service.vo;

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
public class BoardAskVO {
	private int ask_num;//질문번호-seq
	private int mem_num;//회원번호
	@NotEmpty
	private String ask_title;//제목
	@NotEmpty
	private String ask_content;//내용
	private Date ask_reg_date;//등록날짜
	private Date ask_modify_date;//수정날짜
	private byte[] ask_image;
	private String ask_imagename;
	private int ask_status;//답변확인여부(dafault 0:확인전, 1:확인)
	
}

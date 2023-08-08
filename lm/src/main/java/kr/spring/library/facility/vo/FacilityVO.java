package kr.spring.library.facility.vo;

import java.io.IOException;

import javax.validation.constraints.NotEmpty;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(exclude = {"facility_image"})
public class FacilityVO {
	
	private int facility_num;
	@NotEmpty
	private String facility_name;
	private String facility_content;
	private byte[] facility_image;
	private String facility_imagename;
	
	//업로드 파일 처리
	public void setUpload1(MultipartFile upload1)
			throws IOException{
		//MultipartFile -> byte[] 변환
		setFacility_image(upload1.getBytes());
		setFacility_imagename(upload1.getOriginalFilename());
	}
	
	
}

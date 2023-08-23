package kr.spring.member.vo;

import java.io.IOException;

import java.sql.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
//byte[]로 데이터를 반환하는 프로퍼티를 출력하게 되면 
//데이터양이 많아서 느려지는 현상이 발생하기 때문에 
//출력하지 않도록 제외시킴
@ToString(exclude = {"mem_photo"})
public class MemberVO {
	//lm_member_manage, lm_member_detail
	private int mem_num;
	private String mem_id;
	private String mem_au_id;
	private int mem_auth;
	private Date mem_reg_date;
	private Date mem_exp_date;
	private String mem_stop_reason;
	private String mem_name;
	private String mem_passwd;
	private String mem_passwd_sha;
	private String mem_cell;
	@Email
	private String mem_email;
	private String mem_identify;
	private String mem_identify2;
	private int mem_point;
	private byte[] mem_photo;
	//사진 파일명
	private String mem_photo_name;
	private Date mem_login_date;
	private Date mem_modify_date;
	private String mem_salt;
	private int mem_grade;
	private String auto;
	private int home_num;
	private String home_title;
	private String home_zipcode;
	private String home_address;
	private String home_address_detail;
	private String home_cell;
	private String home_name;
	private int home_default;
	/*
	//store_member_home
	@NotEmpty
	private int home_num;
	@NotEmpty
	private String home_title;
	@Size(min=5,max=6)
	@NotEmpty
	private String home_zipcode;
	@NotEmpty
	private String home_address;
	@NotEmpty
	private String home_address_detail;
	@Size(min=9,max=15)
	@NotEmpty
	private String home_cell;
	@NotEmpty
	private int home_status;
	*/
	
	//@Pattern(regexp="^[A-Za-z0-9]{4,12}$")
	
	//=======비밀번호 일치 여부 체크=======//
	public boolean isCheckedPassword(String userPasswd) {
		if(mem_auth > 1 && mem_passwd.equals(userPasswd)) {
			return true;
		}
		return false;
	}
	
	//=======이미지를 byte[]로 변환(BLOB 처리)=======//
	//(주의) 폼에서 파일 업로드 파라미터네임은 반드시 upload로 지정해야 함
	
	public void setUpload(MultipartFile upload)throws IOException{
		//MultipartFile -> byte[]
		setMem_photo(upload.getBytes());
		//파일 이름
		setMem_photo_name(upload.getOriginalFilename());
	}
	
}








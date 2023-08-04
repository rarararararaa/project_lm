package kr.spring.mypage.vo;

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


//byte[]로 데이터를 반환하는 프로퍼티를 출력하게 되면 
//데이터양이 많아서 느려지는 현상이 발생하기 때문에 
//출력하지 않도록 제외시킴
@Getter
@Setter
public class MyPageVO {
	public int mem_num;
	public int mem_grade;
	public int mem_point;
	public int zzim_num;
	public int review_num;
	public int rep_num;
	public int coupon_num;
	public String mem_id;
	public String mem_reg_date;
	public String mem_modify_date;
	public String mem_old_passwd;
	public String mem_new_passwd;
	public String mem_new_passwd2;
	public String mem_new_email;
	public String mem_confirm_email;
	public String mem_old_cell;
	public String mem_new_cell;
}








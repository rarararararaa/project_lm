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
@ToString(exclude = {"mem_photo","facility_image"})
public class MyPageVO {
	public int mem_num;
	public int mem_auth;
	public int mem_grade;
	public int mem_point;
	public int zzim_num; 
	public int review_num;
	public int rep_num;
	public int coupon_num;
	public int home_num;
	public String mem_id;
	public String mem_reg_date;
	public String mem_modify_date;
	public String mem_passwd;
	public String mem_old_passwd;
	public String mem_new_passwd;
	public String mem_old_email;
	public String mem_new_email;
	public String mem_confirm_email;
	public String mem_old_cell;
	public String mem_new_cell;
	public String mem_email;
	public String mem_cell;
	public String mem_name;
	public String store_product_title;
	public String order_total_price;
	public int order_status;
	public String order_date;
	public int order_num;
	public int rent_num;
	public String callNumber;
	public int lib_product_status;
	public String rent_reg_date;
	public String return_reg_deadline;
	public String return_reg_date;
	public String lib_product_bookname;
	public int book_apply_num;
	public String book_apply_title;
	public String book_apply_content;
	public String book_apply_reg_date;
	public int book_apply_status;
	public int order_detail_num;
	public int store_product_num;
	public int order_product_quantity;
	public int cart_quantity;
	public String order_product_price;
	public String store_product_author;
	public String store_product_publisher;
	public String store_product_cover;
	public String store_product_isbn13;
	public String home_title;
	public int home_default;
	public String home_zipcode;
	public String home_address;
	public String home_address_detail;
	public String home_cell;
	public String home_name;
	public String order_notice;
	public int payment_type;
	public int order_pay_status;
	public int program_num;
	public String program_title;
	public String program_content;
	public String program_reg_date;
	public String program_date;
	public int facility_apply_num;
	public int facility_num;
	public String facility_apply_reg_date;
	public String facility_apply_start;
	public String facility_apply_end;
	public String facility_name;
	public String facility_content;
	public String facility_imagename;
	public int reservation_num;
	public int reservation_status;
	public String reservation_submit_date;
	public String lib_product_isbn;
	public String lib_product_authors;
	public String lib_product_publisher;
	public int lib_product_product_status;
	public String lib_product_bookimageurl;
	public int order_status_confirm;
	//이메일 인증
	public String userEmail;
	public int ask_num;
	public int ask_category;
	public int ask_status;
	public String ask_title;
	public String ask_content;
	public String ask_reg_date;
	public String ask_imagename;
	public String userEmailApply;
	public int lost_report_num;
	public String store_product_pricesales;
	public String lost_reg_date;
	public int lost_payment_status;
	public int lost_payment_type;
	public int point_num;
	public String point_value;
	public String point_date;
	public String point_reason;
	public int point_status;
	public String review_content;
	public String review_reg_date;
	public int review_rating;
	public int review_deleted;
	
	private byte[] review_image;
	private byte[] ask_image;
	private byte[] facility_image;
	private byte[] mem_photo; //myEdit.jsp에서 입력받은 이미지 파일
	//MultipartFile to byte 이미지 처리 
	public void setUpload(MultipartFile upload) throws IOException {
		setMem_photo(upload.getBytes());
	}
}
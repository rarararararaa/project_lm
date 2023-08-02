package kr.spring.bookstore.used.vo;

import java.io.IOException;
import java.sql.Date;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UsedVO {
	private int used_product_num;
	private int store_product_num;
	private int mem_num;
	private int used_product_status;
	private int used_product_condition;
	private int used_product_approve;
	private String used_product_title;
	private Date used_product_reg_date;
	private Date used_product_sales_date;
	
	
	private String used_product_info;
	private byte[] used_product_photo1;
	private byte[] used_product_photo2;
	private int used_product_price;
	
	
	//private String store_product_cover;
	private String store_product_title;
	private String store_product_categoryname;
	private String store_product_author;
	private String store_product_publisher;
	
	
	//Blob 타입은 MultipartFile 로 바꿔주고 byte로 변환 시켜야 한다...
	public void setUpload1(MultipartFile upload1) throws IOException {
		used_product_photo1 = upload1.getBytes();
	}
	public void setUpload2(MultipartFile upload2) throws IOException {
		used_product_photo2 = upload2.getBytes();
	}
}

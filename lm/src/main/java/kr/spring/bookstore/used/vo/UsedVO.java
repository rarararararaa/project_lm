package kr.spring.bookstore.used.vo;

import java.io.IOException;
import java.sql.Date;

import javax.validation.constraints.NotEmpty;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(exclude = {"used_product_photo1","used_product_photo2"})
public class UsedVO {
	private int used_product_num;
	private int store_product_num;
	private int mem_num;
	private int used_product_status;
	private int used_product_condition;
	private int used_product_approve;
	private int used_product_hit;
	private int used_product_price;
	
	private Date used_product_reg_date;
	private Date used_product_sales_date;
	private String used_product_info;
	
	private byte[] used_product_photo1;
	private byte[] used_product_photo2;
	
	//store product manage 영역
	private String store_product_categoryname;
	private String store_product_searchcategoryname;
	
	//store product detail 영역
	private String store_product_title;    //책 제목
	private String store_product_author;   //저자 
	private String store_product_pubdate; //등록 날짜
	private int store_product_status;   //판매 상태??
	private int store_product_discount; //할인률
	private double store_product_ratingscore; //점수
	private int store_product_ratingcount; //리뷰 갯수...
	private int store_product_pricesales; //판매 가격
	private String store_product_cover; //이미지 경로
	private String store_product_publisher; //출판사
	private int used_product_match_count;
	private String store_product_description;
	private String store_product_isbn13;
	
	//연산 작업
	private int devide_product_by_used;
	
	//order 관련 영역...
	private int order_num;
	private Date order_date;
	private int order_pay_status;
	
	
	//파일 처리 작업
	private String store_product_photo1_name; //파일 1 처리
	private String store_product_photo2_name; //파일 2 처리
	//Blob 타입은 MultipartFile 로 바꿔주고 byte로 변환 시켜야 한다...
	public void setUpload1(MultipartFile upload1) throws IOException {
		used_product_photo1 = upload1.getBytes();
	}
	public void setUpload2(MultipartFile upload2) throws IOException {
		used_product_photo2 = upload2.getBytes();
	}
}

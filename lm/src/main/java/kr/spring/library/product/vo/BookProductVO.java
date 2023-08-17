package kr.spring.library.product.vo;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class BookProductVO {
	
	private String lib_product_isbn; 
	private String callNumber;//도서 청구 번호 primary key
	private int lib_product_class_no;//주제 분류
	private String lib_product_bookName;//도서 이름
	private String lib_product_authors;//작가
	private String lib_product_publisher;//출판사 LIB_PRODUCT_PUBLISHER
	private String lib_product_publication_year;//출판년도
	private String lib_product_description;//도서소개 ->이거 ur던데
	private int lib_product_loanCnt;
	private int lib_product_product_status;//대출 상태
	private String lib_product_bookImageUrl;//이미지	
	private String lib_product_bookImageData;//null값 안쓰는 거임
	private String lib_product_detail;
	
	private Date return_reg_deadline;

}

package kr.spring.library.main.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class LibraryMainVO {
	private String lib_product_isbn; 
	private String callNumber;//도서 청구 번호 primary key
	private int lib_product_class_no;//주제 분류
	private String lib_product_bookname;//도서 이름
	private String lib_product_authors;//작가
	private String lib_product_publisher;//출판사 LIB_PRODUCT_PUBLISHER
	private String lib_product_publication_year;//출판년도
	private String lib_product_description;//도서소개 ->이거 ur던데
	private int lib_product_loanCnt;
	private int lib_product_product_status;//대출 상태
	private String lib_product_bookimageurl;//이미지	
	private String lib_product_bookimagedata;
	private String lib_product_detail;
	
	private String product_category_name; //함수를 통한 카테고리 네임
	private int search_count; //searchCount 입니다.
	private int orderByNum;
	private int categoryNum;
	private String keyword;
}

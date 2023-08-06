package kr.spring.library.bookproduct_admin.vo;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BookProductAdminVO {
	
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
	private byte[] lib_product_bookImageUrl_ar;//이미지 배열
	private String lib_product_bookImageUrl;//이미지 이름
	private String lib_product_bookImageData;//null값 안쓰는 거임
	private String lib_product_detail;
	
	//업로드 파일 처리
	public void setUpload1(MultipartFile upload1) throws IOException{
		//MultipartFile -> byte[] 변환
		setLib_product_bookImageUrl_ar(upload1.getBytes());
		//파일명 구하기
		setLib_product_bookImageUrl(upload1.getOriginalFilename());
	}

}

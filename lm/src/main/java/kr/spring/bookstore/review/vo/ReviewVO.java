package kr.spring.bookstore.review.vo;

import java.io.IOException;
import java.sql.Date;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Range;
import org.springframework.web.multipart.MultipartFile;

import kr.spring.bookstore.product.vo.ProductVO;
import kr.spring.bookstore.service.vo.OrderDetailVO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(exclude= {"review_image"})
public class ReviewVO {
	private int review_num;
	private int order_detail_num;
	private int mem_num;
	@NotEmpty
	private String review_content;
	private Date review_reg_date;
	private byte[] review_image;
	@Range(min=1,max=10)
	private int review_rating;
	
	private OrderDetailVO orderdetailVO;
	private ProductVO productVO;
	
	//업로드 파일 처리
	public void setReview_image2(MultipartFile review_image2) throws IOException{
		//MultipartFile -> byte[] 변환 | 변환해야 Mybatis가 blob 처리를 해준다.
		setReview_image(review_image2.getBytes());
	}
}

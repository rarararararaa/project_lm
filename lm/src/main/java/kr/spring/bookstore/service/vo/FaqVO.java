package kr.spring.bookstore.service.vo;

import java.sql.Date;

import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class FaqVO {
	private int faq_num;
	@NotEmpty
	private String faq_title;
	@NotEmpty
	private String faq_content;
	private int faq_category;
	private Date faq_reg_date;
}

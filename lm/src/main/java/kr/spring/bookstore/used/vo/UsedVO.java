package kr.spring.bookstore.used.vo;

import java.sql.Date;

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
	private Date used_product_reg_date;
	private Date used_product_sales_date;
	
	private String used_product_info;
	private String used_product_photo1;
	private String used_product_photo2;
	private int used_product_price;
	private String store_product_name;
	
	private String store_product_cover;
	private String store_product_title;
	private String store_product_categoryname;
	private String store_product_author;
	private String store_product_publisher;
}

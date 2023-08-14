package kr.spring.bookstore.product.vo;

import kr.spring.bookstore.used.vo.UsedVO;
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
public class ProductVO {
	
	private int store_product_num;
	private int store_product_searchcategoryId;
	private String store_product_searchcategoryName;
	private String store_product_isbn13;
	private int store_product_stockstatus;
	
	private String store_product_title;
	private String store_product_author;
	private String store_product_pubdate;
	private String store_product_cover;
	private String store_product_description;
	private int store_product_categoryid;
	private String store_product_categoryname;
	private int store_product_pricesales;
	private int store_product_pricestandard;
	private String store_product_publisher;
	private int store_product_stock;
	private int store_product_seriesId;
	private String store_product_seriesName;
	private int store_product_customerReviewRank;
	private int store_product_ratingCount;
	private int store_product_discount;
	private double store_product_ratingScore;
	private int store_product_status;
	
	private int cart_quantity; 
	private int used_product_num;
	private int used_product_price;
	private int used_product_status;
}

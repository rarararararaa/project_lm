package kr.spring.bookstore.search.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SearchVO {
	private int store_product_num;
	private int store_product_searchcategoryid;
	private String store_product_searchcategoryname;
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
	private int store_product_ratingcount;
	private int store_product_discount;
	private double store_product_ratingscore;
	private int store_product_status;
	
	private int cart_quantity; 
	private int used_product_num;
	private int used_product_price;
	private int used_product_status;
}

package kr.spring.bookstore.product.service;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.spring.bookstore.product.dao.ProductMapper;
import kr.spring.bookstore.product.vo.ProductVO;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class ProductServiceapiImpl implements ProductServiceapi{
	
	@Autowired
	ProductMapper productMapper;
	
	//인증키
	final String ttbkey="ttbhoyo18941539001";
	
	@Override
	public void fetchData1FromApi() {
	    String result="";
	    BufferedReader br=null;
	    HttpURLConnection conn=null;
	    List<String> list=null;
	    try {
	    		//알라딘 상품조회 url
	    		URL apiUrl=new URL("http://www.aladin.co.kr/ttb/api/ItemList.aspx?"
	    				+ "ttbkey="+ttbkey+"&QueryType=bestseller&MaxResults=100&start=1&"
	    				+ "SearchTarget=book&output=js&Version=20131101");
	    		conn=(HttpURLConnection)apiUrl.openConnection();
	    		conn.setRequestMethod("GET");
	    		conn.setRequestProperty("Content-type", "application/json; charset=utf-8");
	    		
	    		br=new BufferedReader(new InputStreamReader(conn.getInputStream()));
	    		
	    		result=br.readLine();
	    		JSONParser jsonParser=new JSONParser();
	    		JSONObject jsonObject=(JSONObject)jsonParser.parse(result);
				JSONArray docs = (JSONArray)jsonObject.get("item");
	    		
				int jsonSize = docs.size();	  
				for (int i = 0; i < jsonSize; i++) {
					JSONObject j = (JSONObject)docs.get(i);
					list=new ArrayList<String>();
					if(((String)j.get("isbn13")).trim().equals("")) continue;
		    		String isbn13=(String)j.get("isbn13");
					int searchcategoryId=Integer.parseInt(String.valueOf(jsonObject.get("searchCategoryId")));
					int product_num=productMapper.selectStoreProductNum();
					ProductVO product2=fetchData2FromApi(isbn13);
					ProductVO db_product=productMapper.selectProduct(isbn13);
					if(product2==null) continue;
					if(productMapper.selectProduct(isbn13)==null) {
						ProductVO product=new ProductVO(
								product_num,
								searchcategoryId,
								(String)jsonObject.get("searchCategoryName"), 
								isbn13,
								1,product2.getStore_product_title(),
								product2.getStore_product_author(), 
								product2.getStore_product_pubdate(),
								product2.getStore_product_cover(), 
								product2.getStore_product_description(), 
								product2.getStore_product_categoryid(),
								product2.getStore_product_categoryname(),
								product2.getStore_product_pricesales(),
								product2.getStore_product_pricestandard(),
								product2.getStore_product_publisher(),
								100, product2.getStore_product_seriesId(),
								product2.getStore_product_seriesName(),
								product2.getStore_product_customerReviewRank(),
								product2.getStore_product_ratingCount(),
								0,
								product2.getStore_product_ratingScore(),
								1,0,0,0,0);
						productMapper.insertStore_P(product);
						productMapper.insertStore_Pdetail(product);
						log.debug("product:"+product);
					}else {
						ProductVO product=new ProductVO(
								db_product.getStore_product_num(),
								searchcategoryId,
								(String)jsonObject.get("searchCategoryName"), 
								isbn13,
								1,product2.getStore_product_title(),
								product2.getStore_product_author(), 
								product2.getStore_product_pubdate(),
								product2.getStore_product_cover(), 
								product2.getStore_product_description(), 
								product2.getStore_product_categoryid(),
								product2.getStore_product_categoryname(),
								product2.getStore_product_pricesales(),
								product2.getStore_product_pricestandard(),
								product2.getStore_product_publisher(),
								100, product2.getStore_product_seriesId(),
								product2.getStore_product_seriesName(),
								product2.getStore_product_customerReviewRank(),
								product2.getStore_product_ratingCount(),
								0,
								product2.getStore_product_ratingScore(),
								1,0,0,0,0);
						
						productMapper.updateStore_P(product);
						productMapper.updateStore_Pdetail(product);
						log.debug("product:"+product);
					}
				}
	    }catch(Exception e) {
		    	e.printStackTrace();
		}finally {
		    	if(br!=null)try {br.close();}catch(IOException e) {}
		}
	}

	@Override
	public ProductVO fetchData2FromApi(String isbn13) {
	    String result="";
	    BufferedReader br=null;
	    HttpURLConnection conn=null;		
	    ProductVO product2=null;
	    try {
			URL apiUrl2=new URL("http://www.aladin.co.kr/ttb/api/ItemLookUp.aspx?ttbkey="+ttbkey
					+ "&itemIdType=ISBN13&ItemId="+isbn13
					+"&output=js&Version=20131101&OptResult=cardReviewImgList,bestSellerRank,ratingInfo"
					+ "&Cover=Big");
			conn=(HttpURLConnection)apiUrl2.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Content-type", "application/json; charset=utf-8");
			
			br=new BufferedReader(new InputStreamReader(conn.getInputStream()));
    		result=br.readLine();
    		JSONParser jsonParser=new JSONParser();
    		JSONObject jsonObject=(JSONObject)jsonParser.parse(result);
    		log.debug("jsonObject : "+jsonObject);
			JSONArray docs = (JSONArray)jsonObject.get("item");
    		log.debug("JSONArray : "+docs);
			int jsonSize = docs.size();
				for (int i = 0; i < jsonSize; i++) {
					if(!((JSONObject)docs.get(i)).get("isbn13").equals(isbn13)) {
						continue;
					}
					JSONObject j = (JSONObject)docs.get(i);
					JSONObject subInfo=(JSONObject)j.get("subInfo");
					log.debug("subInfo : "+subInfo);
					for(Object o : subInfo.keySet()) {
						log.debug("<<key 값>> : "+o);
					}
					JSONObject ratingInfo=(JSONObject)subInfo.get("ratingInfo");
					log.debug("ratingInfo : "+ratingInfo);
					
					String description=" ";
					if(!((String)j.get("description")).trim().equals("")) {
						description=(String)j.get("description");
					}
					String author=" ";
					if(!((String)j.get("author")).trim().equals("")) {
						author=(String)j.get("author");
					}
					int seriesId=0;
					String seriesName="";
					int ratingCount=0;
					double ratingScore=0;
					
					if(j.get("seriesInfo")!=null) { 
						JSONObject docs2 =(JSONObject)j.get("seriesInfo");
				
						seriesId=Integer.parseInt(String.valueOf(docs2.get("seriesId")));
						seriesName=(String)docs2.get("seriesName");
					}
					if(subInfo.get("ratingInfo")!=null) { 
						ratingCount=Integer.parseInt(String.valueOf(ratingInfo.get("ratingCount")));
						log.debug("ratingCount : "+ratingCount);
						ratingScore=Double.parseDouble(String.valueOf(ratingInfo.get("ratingScore")));
						log.debug("ratingScore : "+ratingScore);
					}
					
					
					
					String cover=" ";
					if((String)j.get("cover")!=null) {
						cover=(String)j.get("cover");
					}
					
					String categoryName=" ";
					if(!((String)j.get("categoryName")).trim().equals("")) {
						categoryName=(String)j.get("categoryName");
					}
					
					int customerReviewRank=0;
					if((String)jsonObject.get("customerReviewRank")!=null) {
						customerReviewRank=(int)j.get("customerReviewRank");
					}
					int discount=0;
					int searchcategoryId=Integer.parseInt(String.valueOf(jsonObject.get("searchCategoryId")));
					int categoryId=Integer.parseInt(String.valueOf(j.get("categoryId")));
					int priceSales=Integer.parseInt(String.valueOf(j.get("priceSales")));
					int priceStandard=Integer.parseInt(String.valueOf(j.get("priceStandard")));
					
					product2=new ProductVO(0,
							 searchcategoryId,
							 (String)jsonObject.get("searchCategoryName"), 
							 (String)j.get("isbn13"),
							 1, (String)j.get("title"),
							 author, 
							 (String)j.get("pubDate"),
							 cover, 
							 description, 
							 categoryId,
							 categoryName,
							 priceSales,
							 priceStandard,
							 (String)j.get("publisher"),
							 100, seriesId,
							 seriesName,
							 customerReviewRank,
							 ratingCount,
							 discount,
							 ratingScore,
							 0,0,0,0,0);
				}	 				
				log.debug("product2 : "+product2);
	    }catch(Exception e) {
	    	e.printStackTrace();
	    }finally {
	    	if(br!=null)try {br.close();}catch(IOException e) {}
	    }
	    return product2;
	}
}





















package kr.spring.bookstore.product.service;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.spring.bookstore.product.dao.ProductMapper;
import kr.spring.bookstore.product.vo.ProductVO;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	ProductMapper productMapper;
	
	//인증키
	final String ttbkey="ttbhoyo18941539001";
	
	@Override
	public void fetchDataFromApi() {
	    String result="";
	    BufferedReader br=null;
	    HttpURLConnection conn=null;
	    
	    try {
	    		//알라딘 상품조회 url
	    		URL apiUrl=new URL("http://www.aladin.co.kr/ttb/api/ItemList.aspx?"
	    				+ "ttbkey="+ttbkey+"&QueryType=ItemNewAll&MaxResults=10&start=1&"
	    				+ "SearchTarget=Book&output=js&Version=20131101");
	    		conn=(HttpURLConnection)apiUrl.openConnection();
	    		conn.setRequestMethod("GET");
	    		conn.setRequestProperty("Content-type", "application/json; charset=utf-8");
	    		
	    		br=new BufferedReader(new InputStreamReader(conn.getInputStream()));
	    		
	    		result=br.readLine();
	    		JSONParser jsonParser=new JSONParser();
	    		JSONObject jsonObject=(JSONObject)jsonParser.parse(result);
	    		log.debug("jsonObject : "+jsonObject);
				JSONArray docs = (JSONArray)jsonObject.get("item");
				JSONArray docs2 = (JSONArray)jsonObject.get("seriesInfo");
	    		log.debug("JSONArray : "+docs);
				int jsonSize = docs.size();	    		
				for (int i = 0; i < 1; i++) {
					JSONObject j = (JSONObject)docs.get(i);
					for(Object o : j.keySet()) {
						log.debug("<<key 값>> : "+o);
					}
					
					
					byte[]cover=null;
					if((String)jsonObject.get("cover")!=null) {
						cover=((String)jsonObject.get("cover")).getBytes("utf-8");
					}
					
					int priceSales=0;
					if((String)jsonObject.get("priceSales")!=null) {
						priceSales=(int)j.get("priceSales");
					} 
					int priceStandard=0;
					if((String)jsonObject.get("priceStandard")!=null) {
						priceStandard=(int)j.get("priceStandard");
					}
					int seriesId=0;
					if((String)jsonObject.get("seriesId")!=null) {
						seriesId=(int)j.get("seriesId");
					}
					String seriesName="";
					if((String)jsonObject.get("seriesName")!=null) {
						seriesName=(String)j.get("seriesName");
					}
					int customerReviewRank=0;
					if((String)jsonObject.get("customerReviewRank")!=null) {
						customerReviewRank=(int)j.get("customerReviewRank");
					}
					int ratingCount=0;
					if((String)jsonObject.get("ratingCount")!=null) {
						ratingCount=(int)j.get("ratingCount");
					}
					int discount=0;
					if((String)jsonObject.get("discount")!=null) {
						discount=(int)j.get("discount");
					}
					int ratingScore=0;
					if((String)jsonObject.get("ratingScore")!=null) {
						ratingScore=(int)j.get("ratingScore");
					}
					
					 ProductVO product=new ProductVO(0,
							 (Long)jsonObject.get("searchCategoryId"),
							 (String)jsonObject.get("searchCategoryName"), 
							 (String)j.get("isbn13"),
							 1, (String)j.get("title"),
							 (String)j.get("author"), 
							 (String)j.get("pubDate"),
							 cover, 
							 (String)j.get("description"), 
							 (Long)j.get("categoryId"),
							 (String)j.get("categoryName"),
							 priceSales,
							 priceStandard,
							 (String)j.get("publisher"),
							 100, seriesId,
							 seriesName,
							 customerReviewRank,
							 ratingCount,
							 discount,
							 ratingScore,
							 1);
					log.debug("product"+product);
				}	    		
	    }catch(Exception e) {
		    	e.printStackTrace();
		}finally {
		    	if(br!=null)try {br.close();}catch(IOException e) {}
		}

	}
}





















package kr.spring.bookstore.product.service;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

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
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	ProductMapper productMapper;
	
	//인증키
	final String ttbkey="ttbhoyo18941539001";
	
	@Override
	public void fetchDataFromApi() {
	    String result="";
	    BufferedReader br=null;
	    BufferedReader br2=null;
	    HttpURLConnection conn=null;
	    HttpURLConnection conn2=null;
	    
	    try {
	    		//알라딘 상품조회 url
	    		URL apiUrl=new URL("http://www.aladin.co.kr/ttb/api/ItemList.aspx?"
	    				+ "ttbkey="+ttbkey+"&QueryType=ItemNewAll&MaxResults=50&start=1&"
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
	    		log.debug("JSONArray : "+docs);
				int jsonSize = docs.size();	    		
				for (int i = 0; i < jsonSize; i++) {
					JSONObject j = (JSONObject)docs.get(i);
					
					int seriesId=0;
					String seriesName="";
					if(j.get("seriesInfo")!=null) { 
						JSONObject docs2 =(JSONObject)j.get("seriesInfo");
						for(Object o : docs2.keySet()) {
							log.debug("<<시리즈 key 값>> : "+o);
						}						
						seriesId=Integer.parseInt(String.valueOf(docs2.get("seriesId")));
						seriesName=(String)docs2.get("seriesName");
					}
					
					
					for(Object o : j.keySet()) {
						log.debug("<<key 값>> : "+o);
					}
					
					if(((String)j.get("isbn13")).trim().equals("")) continue;
					
					byte[]cover=null;
					if((String)jsonObject.get("cover")!=null) {
						cover=((String)jsonObject.get("cover")).getBytes("utf-8");
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
		    		
					URL apiUrl2=new URL("http://www.aladin.co.kr/ttb/api/ItemLookUp.aspx?ttbkey="+ttbkey
		    				+ "&itemIdType=ISBN13&ItemId="+(String)j.get("isbn13")
		    				+"&output=js&Version=20131101&OptResult=cardReviewImgList,bestSellerRank,reviewInfo");
		    		conn2=(HttpURLConnection)apiUrl2.openConnection();
		    		conn2.setRequestMethod("GET");
		    		conn2.setRequestProperty("Content-type", "application/json; charset=utf-8");
		    		br2=new BufferedReader(new InputStreamReader(conn.getInputStream()));
		    		
		    		result=br2.readLine();
		    		JSONParser jsonParser2=new JSONParser();
		    		JSONObject jsonObject2=(JSONObject)jsonParser2.parse(result);
		    		log.debug("jsonObject : "+jsonObject2);
		    		JSONObject docs3 =(JSONObject)j.get("ratingInfo");
		    		int ratingScore=Integer.parseInt(String.valueOf(docs3.get("ratingScore")));
		    		int ratingCount=Integer.parseInt(String.valueOf(docs3.get("ratingCount")));
		    		
		    					
					 ProductVO product=new ProductVO(0,
							 searchcategoryId,
							 (String)jsonObject.get("searchCategoryName"), 
							 (String)j.get("isbn13"),
							 1, (String)j.get("title"),
							 (String)j.get("author"), 
							 (String)j.get("pubDate"),
							 cover, 
							 (String)j.get("description"), 
							 categoryId,
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
				if(br2!=null)try {br.close();}catch(IOException e) {}
		    	if(br!=null)try {br.close();}catch(IOException e) {}
		}

	}
}





















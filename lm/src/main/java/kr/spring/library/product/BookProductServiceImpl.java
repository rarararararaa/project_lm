package kr.spring.library.product;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.spring.library.product.dao.BookProductMapper;
import kr.spring.library.product.vo.BookProductVO;
import kr.spring.library.rent.vo.RentVO;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class BookProductServiceImpl implements BookProductService {
	@Autowired
	BookProductMapper bookProductMapper;
	
	//인증키
	final String AUTH_KEY = "1bed712dd9366b514c817d63f1af130792bf81b15715ac096d87c2d524c7ac25";
	//도서관 번호
	final String LIBCODE = "111314";
	

	@Override
	public void getData() {
		String result = "";
		BufferedReader br = null;
		HttpURLConnection conn = null;
		try {
			URL url = new URL("http://data4library.kr/api/itemSrch?authKey="+AUTH_KEY+"&libCode="+LIBCODE+"&pageSize=300&type=ALL&format=json");//type : all [전체]
			//log.debug("<<도서API URL확인>> : "+url);
			conn = (HttpURLConnection)url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Content-type", "application/json");
			
			br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			
			result = br.readLine();
			JSONParser jsonParser = new JSONParser();
			JSONObject jsonObject = (JSONObject)jsonParser.parse(result);
			JSONObject response = (JSONObject)jsonObject.get("response");//구조 확인 후 최상위 객체 이름 명시
			for(Object o : response.keySet()) {
				log.debug("<<key 값>> : "+o);
			}
			//log.debug("<<test>> : "+response);
			
			//데이터의 상위 객체 [배열]
			JSONArray docs = (JSONArray)response.get("docs");
			
			for(int i=0;i<docs.size();i++) {
				//해당 데이터 객체
				JSONObject getDoc = (JSONObject)docs.get(i);
				JSONObject doc = (JSONObject)getDoc.get("doc");
				//log.debug("<<도서 분류번호확인>> : "+doc.get("class_no"));

				//주제 분류
				String getCN = (String)doc.get("class_no");
				int classno = 0;
				//book_code가 없을시 고유 넘버 만들기
				int random = (int)(Math.random()*800);
				String bookCode = String.valueOf(random)+String.valueOf(i);
				//log.debug("<<번호>> : "+getCN);
				//작가가 null일 경우
				String author = (String)doc.get("authors");
				if(author.equals("")) {
					author = "작가미상";
				}
				//출판사가 null일 경우
				String publisher = (String)doc.get("publisher");
				if(publisher.equals("")) {
					 publisher = "출판정보없음";
				}
				//사진이 없는 경우
				String photo = (String)doc.get("bookImageURL");
				if(photo.equals("")) {
					photo = "/images/noImage.png";
				}
				//출판년도가 없는 경우
				String year = (String)doc.get("publication_year");
				if(year.equals("")) {
					year = "0000";
				}
				if(!getCN.equals("")) {
					//주제 분류
					classno = Integer.parseInt(getCN.substring(0, 1));
				}
				/*
				 * if(callNumbers != null) { callNum = (JSONObject)callNumbers.get(1); bookCode
				 * = (String)callNum.get("book_code")+String.valueOf(i); }
				 */
				BookProductVO book = new BookProductVO((String)doc.get("isbn13"),bookCode,
						classno, (String)doc.get("bookname"), 
						author, publisher,
						 year,
						(String)doc.get("class_nm"), 0, 1, photo, null, null,null);
				log.debug("<<API 등록 확인>> : "+book);
				bookProductMapper.insertLIB_P(book);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(br != null)try {br.close();} catch(IOException e) {}
		}
	}


	@Override
	public BookProductVO selectDetailLIB_P(String callNumber) {
		return bookProductMapper.selectDetailLIB_P(callNumber);
	}


	@Override
	public List<BookProductVO> selectListLIB_P(String isbn) {
		return bookProductMapper.selectListLIB_P(isbn);
	}


	@Override
	public void updateLIB_P_description() {
		//isbn 뽑기
		String result = "";  
		List<BookProductVO> list = bookProductMapper.getIsbn();
		for(BookProductVO vo : list) {
			log.debug("<<DetailData API>> : "+vo.getLib_product_isbn());
			result = getDetailData(vo.getLib_product_isbn());
			if(result != null) {
				bookProductMapper.updateLIB_P_description(result, vo.getLib_product_isbn());
			}
			//log.debug("<<DetailData API>> : "+result);
		}
	}
	//API detail 업데이트
	@Override
	public String getDetailData(String isbn) {
		String result = "";
		String result_detail = "도서 상세 정보가 없습니다.";
		BufferedReader br = null; 
		HttpURLConnection conn = null;
		try {
			URL url = new URL("http://data4library.kr/api/srchDtlList?authKey="+AUTH_KEY+"&isbn13="+isbn+"&format=json");//type : all [전체]
			//log.debug("<<도서API URL확인>> : "+url);
			conn = (HttpURLConnection)url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Content-type", "application/json");
			
			br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			
			result = br.readLine();
			JSONParser jsonParser = new JSONParser();
			JSONObject jsonObject = (JSONObject)jsonParser.parse(result);
			JSONObject response = (JSONObject)jsonObject.get("response");//구조 확인 후 최상위 객체 이름 명시
			//log.debug("<<test>> : "+response);
			
			//데이터의 상위 객체 [배열]
			JSONArray detail = (JSONArray)response.get("detail");
			
			for(int i=0;i<detail.size();i++) {
				//해당 데이터 객체
				JSONObject getBook = (JSONObject)detail.get(i);
				JSONObject book = (JSONObject)getBook.get("book");
				//log.debug("<<도서 분류번호확인>> : "+doc.get("class_no"));
				result_detail = (String)book.get("description");
				if(result_detail.equals("")) {
					result_detail = "도서 상세 정보가 없습니다.";
				}
				log.debug("<<detail 값>> : "+result_detail);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(br != null)try {br.close();} catch(IOException e) {}
		}
		return result_detail;
	}


	@Override
	public List<BookProductVO> searchDetailLIB_P(String lib_product_bookname) {
		return bookProductMapper.searchDetailLIB_P(lib_product_bookname);
	}


	@Override
	public RentVO selectDate(String callNumber) {
		return bookProductMapper.selectDate(callNumber);
	}
}

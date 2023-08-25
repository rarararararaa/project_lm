package kr.spring.bookstore.payment.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import kr.spring.bookstore.payment.dao.BookStorePaymentCartMapper;
import kr.spring.bookstore.payment.vo.BookStorePaymentCartVO;
import kr.spring.bookstore.product.vo.ProductVO;
import kr.spring.bookstore.used.vo.UsedVO;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class BookStorePaymentServiceImpl implements BookStorePaymentService{
	//api 값 가져오기
	@Value("${imp_key}")
	private String imp_key;
	@Value("${imp_secret}")
	private String imp_secret;
	
	@Data
	private class Response{
		private PaymentInfo response;
	}
	@Data
	private class PaymentInfo{
		private int amount;
	}
	
	@Autowired
	BookStorePaymentCartMapper bookStorePaymentCartMapper;
	//카트 추가
	@Override
	public void insertCart(BookStorePaymentCartVO cartVO) {
		bookStorePaymentCartMapper.insertCart(cartVO);
	}
	//카트 정보
	@Override
	public List<BookStorePaymentCartVO> selectCartList(int mem_num) {
		return bookStorePaymentCartMapper.selectCartList(mem_num);
	}
	//중고도서 중복 확인
	@Override
	public BookStorePaymentCartVO selectEmptyUsed(int used_product_num, int mem_num) {
		return bookStorePaymentCartMapper.selectEmptyUsed(used_product_num, mem_num);
	}
	//도서 상세
	@Override
	public ProductVO selectDetailBook(int store_product_num) {
		return bookStorePaymentCartMapper.selectDetailBook(store_product_num);
	}
	//재고 변경
	@Override
	public void updateBookQuantity(int total, int mem_cart_num) {
		bookStorePaymentCartMapper.updateBookQuantity(total, mem_cart_num);
	}

	@Override
	public void updateCart(BookStorePaymentCartVO cartVO) {
		bookStorePaymentCartMapper.updateCart(cartVO);
	}

	
//토큰 값 받아오기
	@Override
	public String getToken() throws IOException {
		log.debug("<<KEY 값 : >>"+imp_key);
		log.debug("<<Secret 값 : >>"+imp_secret);
		
		BufferedWriter bw = null;
		BufferedReader br = null;
		String token = null;
		//REST API 호출에 사용
		HttpsURLConnection conn = null;

		try {
			//IOException 발생
			URL url = new URL("https://api.iamport.kr/users/getToken");//토큰 값을 받아오는 주소
			conn = (HttpsURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-type", "application/json");
			conn.setRequestProperty("Accept", "application/json");
			conn.setDoOutput(true);
			
			JsonObject json = new JsonObject(); //String 객체를 json 객체로 바꿔줄 때 사용한다 google.gson ~
			json.addProperty("imp_key", imp_key);
			json.addProperty("imp_secret", imp_secret);
			
			bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
			bw.write(json.toString());
			bw.flush();
			bw.close();
			br = new BufferedReader(new InputStreamReader(conn.getInputStream(),"utf-8"));
			
			Gson gson = new Gson();//json 구조의 직렬화 된 데이터를 java 객체로 역직렬화 해주는 라이브러리 (직렬화도 해줌)
			//이게 뭐야============추가로 설명 작성=====================//
			String response = gson.fromJson(br.readLine(), Map.class).get("response").toString();
			log.debug("<<getToken Method - response>> : "+response);//로그 확인
			//토큰 값 받기
			token = gson.fromJson(response, Map.class).get("access_token").toString();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(bw != null)try {bw.close();} catch(IOException e) {}
			if(br != null)try {br.close();} catch(IOException e) {}
		}
		return token;
	}
	//결제 정보 가져오기
	@Override
	public int paymentInfo(String token, String IMP_UID) throws IOException {
		HttpURLConnection conn = null;
		URL url = new URL("https://api.iamport.kr/payments/"+IMP_UID);
		
		conn = (HttpURLConnection)url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Authorization", token);
		conn.setDoOutput(true);// OutputStream으로 POST 데이터를 넘겨주겠다는 옵션.뭔소이려
		
		BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(),"utf-8"));
		Gson gson = new Gson();
		
		Response response = gson.fromJson(br.readLine(), Response.class);
		log.debug("<<response>> : "+response);
		
		br.close();
		conn.disconnect();
		
		return response.getResponse().getAmount();
	}

	//결제 취소하기
	@Override
	public void cancelPay(String token, String IMP_UID, int amount,String reason)throws IOException {
		log.debug("<<토큰 받기 성공!!>> : "+token);
		log.debug("<<주문 정보 가져오기 성공 !!>> : "+amount);
		
		HttpsURLConnection conn = null;
		URL url = new URL("https://api.iamport.kr/payments/cancel");//주문 취소 API주소 > io예외처리 
		conn = (HttpsURLConnection)url.openConnection(); //주소 연결
		
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Content-type", "application/json");
		conn.setRequestProperty("Authorization", token);
		
		conn.setDoOutput(true);
		
		JsonObject json = new JsonObject();
		json.addProperty("reason", reason);
		json.addProperty("imp_uid", IMP_UID);
		json.addProperty("amount", amount);
		json.addProperty("checksum", amount);//가격 체크
		// Request Body에 Data를 담기위해 OutputStream 객체를 생성. // 데이터를 reqestbody에 담아서 서버로 보냄
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
		bw.write(json.toString());//버퍼에 있는 값 출력
		bw.flush();//남아있는 값 전부 출력
		bw.close();//스트림을 닫음
		//서버로 부터의 응답을 읽음
		BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(),"utf-8"));
		Gson gson = new Gson();//json 구조의 직렬화 된 데이터를 java 객체로 역직렬화 해주는 라이브러리 (직렬화도 해줌)
		//이게 뭐야============추가로 설명 작성=====================//
		String response = gson.fromJson(br.readLine(), Map.class).get("response").toString();
		log.debug("<<결제 취소 응답>> : "+response);
		br.close();
		conn.disconnect();
	}

	@Override
	public UsedVO selectUsedBook(int used_product_num) {
		return bookStorePaymentCartMapper.selectUsedBook(used_product_num);
	}
	@Override
	public void deleteCart(int mem_cart_num) {
		bookStorePaymentCartMapper.deleteCart(mem_cart_num);
	}
	



}

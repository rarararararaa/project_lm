package kr.spring.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncryptionPasswd {
	
	//비밀번호를 암호화 (salt + mem_passwd)
	public static String encryptionPasswd(String salt,String passwd) {
		/*=======================
		 * SHA-256 방식으로 암호화하는 유틸리티 메서드 
		 *=======================*/
		String mem_passwd = "";
		try {
			//SHA256 알고리즘 객체체 생성
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			
			//pwd + salt에 SHA256 적용
			md.update((passwd + salt).getBytes());
			byte[] salt_passwd = md.digest();
			
			//byte to String (10진수 문자열)
			StringBuffer sb = new StringBuffer();
			for(byte b : salt_passwd) {
				sb.append(String.format("%02x",b));
			}
			
			mem_passwd = sb.toString();

		}catch(NoSuchAlgorithmException e){
			e.printStackTrace();
		}
		
		return mem_passwd;
	}
}

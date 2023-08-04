package kr.spring.util;

import java.security.SecureRandom;

public class SaltGenerate {
	public static String getSalt() {
		/*=======================
		 * 회원의 고유 salt값을 생성하는 유틸리티 메서드
		 *=======================*/
		//RAndom 객체 생성
		SecureRandom rand = new SecureRandom();
		byte[] salt = new byte[20];
		
		//난수 생성
		rand.nextBytes(salt);
		
		//byte -> String (10진수 문자열)
		StringBuffer sb = new StringBuffer();
		for(byte b : salt) {
			sb.append(String.format("%02x",b));
		};
		return sb.toString();
	}
}

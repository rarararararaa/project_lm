package kr.spring.util;

import java.util.HashMap;
import java.util.Random;

import kr.spring.member.controller.MemberController;
import kr.spring.mypage.service.MyPageService;
import lombok.extern.slf4j.Slf4j;
@Slf4j
public class EmailUtil {

	//이메일 인증 인증코드 8자리 생성
    public static String createKey() {
        StringBuffer key = new StringBuffer();
        Random rnd = new Random();
 
        for (int i = 0; i < 8; i++) { // 인증코드 8자리
            int index = rnd.nextInt(3); // 0~2 까지 랜덤
 
            switch (index) {
                case 0:
                    key.append((char) ((int) (rnd.nextInt(26)) + 97));
                    //  a~z  (ex. 1+97=98 => (char)98 = 'b')
                    break;
                case 1:
                    key.append((char) ((int) (rnd.nextInt(26)) + 65));
                    //  A~Z
                    break;
                case 2:
                    key.append((rnd.nextInt(10)));
                    // 0~9
                    break;
            }
        }
        return key.toString();
    }
    
    //이메일 임시 비밀번호 발급
    public static HashMap<String,String> createPasswd(String salt) {
    	
    	//랜덤 비밀번호 생성
    	Random rand =new Random();
    	StringBuffer sb =new StringBuffer();
    	for(int i=0;i<10;i++){
    	    if(rand.nextBoolean()){
    	    	sb.append((char)((int)(rand.nextInt(26))+97));
    	    }else{
    	    	sb.append((rand.nextInt(10)));
    	    }
    	}
    	String mem_passwd = sb.toString();
		//랜덤 비밀번호 sha-256+salt 암호화 처리 시작
		String mem_passwd_sha = EncryptionPasswd.encryptionPasswd(salt,mem_passwd);
		HashMap<String,String> map = new HashMap<String, String>();
		log.debug("<<mem_passwd : >>"+mem_passwd);
		log.debug("<<mem_passwd_sha : >>" + mem_passwd_sha);
		map.put("mem_passwd", mem_passwd);
		map.put("mem_passwd_sha", mem_passwd_sha);
		//랜덤 비밀번호(암호화 전)와 암호화된 비밀번호 리턴
		return map;

    }
}

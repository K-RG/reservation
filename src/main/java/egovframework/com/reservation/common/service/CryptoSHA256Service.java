package egovframework.com.reservation.common.service;

import java.security.MessageDigest;

import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Service;

@Service("cryptoSHA256Service")
public class CryptoSHA256Service {
	
	/**
	 * 비밀번호를 암호화하는 기능(복호화가 안됨 SHA-256 인코딩 방식)
	 * @param password 암호화될 패스워드
	 * @param id salt로 사용될 사용자 ID 
	 * @return
	 * @throws Exception
	 */
	public static String encryptData(String password, String id) throws Exception {
		
		if(password == null || id == null) return "";
		
		byte[] hashValue = null;
		
		MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
		messageDigest.reset();
		messageDigest.update(id.getBytes());
		
		hashValue = messageDigest.digest(password.getBytes());
		
		return new String(Base64.encodeBase64(hashValue));
	}
}

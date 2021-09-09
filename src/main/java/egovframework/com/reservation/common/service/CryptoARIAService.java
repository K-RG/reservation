package egovframework.com.reservation.common.service;

import java.io.UnsupportedEncodingException;

import javax.annotation.Resource;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cryptography.EgovCryptoService;
import egovframework.rte.fdl.cryptography.EgovPasswordEncoder;

@Service("cryptoService")
@PropertySource("classpath:egovframework/properties/crypto_config.properties")
public class CryptoARIAService {

	@Resource(name="ARIACryptoService")
	EgovCryptoService cryptoService;
	
	@Resource(name="passwordEncoder")
	private EgovPasswordEncoder passwordEncoder;
	
	public static String plainPassword = "egovframe";
	
	@Value("${crypto.password.algorithm}")
	public String passwordAlgorithm;
	
	public String encryptData(String plainText) {
		String encodeText = null;
		
		try {
			
			byte[] encrypted = cryptoService.encrypt(plainText.getBytes("UTF-8"), plainPassword);
			encodeText = Base64.encodeBase64String(encrypted);
		
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return encodeText;
	}
	
	public String decryptData(String encodeText) {
		String plainText = null;
		
		try {
			
			byte[] base64dec = Base64.decodeBase64(encodeText);
			byte[] decrypted = cryptoService.decrypt(base64dec, plainPassword);
			
			plainText = new String(decrypted, "UTF-8");
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return plainText;
	}
}

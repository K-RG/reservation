package egovframework.com.reservation.custom.helper;

import javax.servlet.ServletContext;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import egovframework.com.cms.common.service.CryptoSHA256Service;

public class CryptoSHA256Helper {

	private static final String CRYPTO_SHA256 = "cryptoSHA256Service";
	
	public static  CryptoSHA256Service getCryptoSHA256Service(ServletContext ctx) {
		WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(ctx);
		
		return (CryptoSHA256Service) wac.getBean(CRYPTO_SHA256);
	}
}

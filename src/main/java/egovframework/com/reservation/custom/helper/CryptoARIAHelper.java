package egovframework.com.reservation.custom.helper;

import javax.servlet.ServletContext;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import egovframework.com.cms.common.service.CryptoARIAService;

public class CryptoARIAHelper {
	
	private static final String CRYPTO_ARIA = "cryptoService";
	
	public static  CryptoARIAService getCryptoARIAService(ServletContext ctx) {
		WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(ctx);
		
		return (CryptoARIAService) wac.getBean(CRYPTO_ARIA);
	}
	
}

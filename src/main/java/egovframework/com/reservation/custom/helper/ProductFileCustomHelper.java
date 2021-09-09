package egovframework.com.reservation.custom.helper;

import javax.servlet.ServletContext;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import egovframework.com.cms.product.service.ProductFileService;

public class ProductFileCustomHelper {
	
	private static final String PRODUCT_FILE = "productFileService";
	
	public static ProductFileService getProductFileService(ServletContext ctx) {
		WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(ctx);
		
		return (ProductFileService) wac.getBean(PRODUCT_FILE);
	}
}

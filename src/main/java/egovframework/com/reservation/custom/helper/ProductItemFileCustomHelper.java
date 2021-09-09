package egovframework.com.reservation.custom.helper;

import javax.servlet.ServletContext;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import egovframework.com.cms.product.service.ProductItemFileService;

public class ProductItemFileCustomHelper {
	
	private static final String PRODUCT_ITEM_FILE = "productItemFileService";
	
	public static ProductItemFileService getProductItemFileService(ServletContext ctx) {
		WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(ctx);
		
		return (ProductItemFileService) wac.getBean(PRODUCT_ITEM_FILE);
	}
}

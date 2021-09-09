package egovframework.com.reservation.custom.helper;

import javax.servlet.ServletContext;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import egovframework.com.cms.product.service.ProductItemService;

public class ProductItemCustomHelper {
	
	private static final String PRODUCT_ITEM = "productItemService";
	
	public static ProductItemService getProductItemService(ServletContext ctx) {
		WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(ctx);
		
		return (ProductItemService) wac.getBean(PRODUCT_ITEM);
	}
}

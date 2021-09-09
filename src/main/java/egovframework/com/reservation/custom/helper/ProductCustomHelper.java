package egovframework.com.reservation.custom.helper;

import javax.servlet.ServletContext;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import egovframework.com.cms.product.service.ProductService;

public class ProductCustomHelper {
	
	private static final String PRODUCT = "productService";
	
	public static ProductService getProductService(ServletContext ctx) {
		WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(ctx);
		
		return (ProductService) wac.getBean(PRODUCT);
	}
}

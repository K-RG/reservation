package egovframework.com.reservation.custom.helper;

import javax.servlet.ServletContext;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import egovframework.com.cms.category.service.CategoryService;

public class CategoryCustomHelper {
	
	private static final String CATEGORY = "categoryService";
	
	public static  CategoryService getCategoryService(ServletContext ctx) {
		WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(ctx);
		
		return (CategoryService) wac.getBean(CATEGORY);
	}
}

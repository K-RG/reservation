package egovframework.com.reservation.custom.helper;

import javax.servlet.ServletContext;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import egovframework.com.cms.menu.service.MenuService;

public class MenuCustomHelper {
	
	private static final String MENU = "menuService";
	
	public static MenuService getMenuService(ServletContext ctx) {
		WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(ctx);
		
		return (MenuService) wac.getBean(MENU);
	}
}

package egovframework.com.reservation.custom.helper;

import javax.servlet.ServletContext;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import egovframework.com.cms.user.service.UserFileService;

public class UserFileCustomHelper {
	
	private static final String USER_FILE = "userFileService";
	
	public static UserFileService getUserFileService(ServletContext ctx) {
		WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(ctx);
		
		return (UserFileService) wac.getBean(USER_FILE);
	}
}

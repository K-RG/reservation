package egovframework.com.reservation.custom.helper;

import javax.servlet.ServletContext;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import egovframework.com.cms.user.service.UserExtraInfoService;

public class UserExtraInfoCustomHelper {
	
	private static final String USER_EXTRA_INFO = "userExtraInfoService";
	
	public static UserExtraInfoService getUserExtraInfoService(ServletContext ctx) {
		WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(ctx);
		
		return (UserExtraInfoService) wac.getBean(USER_EXTRA_INFO);
	}
}

package egovframework.com.reservation.custom.helper;

import javax.servlet.ServletContext;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import egovframework.com.reservation.company.service.CompanyService;

public class CompanyCustomHelper {
	
	private static final String AUTHORITY = "companyService";
	
	public static CompanyService getCompanyService(ServletContext ctx) {
		WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(ctx);
		
		return (CompanyService) wac.getBean(AUTHORITY);
	}
}

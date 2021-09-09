package egovframework.com.reservation.custom.helper;

import javax.servlet.ServletContext;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import egovframework.com.cms.bannerpopup.service.BannerPopupService;

public class BannerPopupCustomHelper {
	
	private static final String BANNER_POPUP = "bannerPopupService";
	
	public static BannerPopupService getBannerPopupService(ServletContext ctx) {
		WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(ctx);
		
		return (BannerPopupService) wac.getBean(BANNER_POPUP);
	}
}

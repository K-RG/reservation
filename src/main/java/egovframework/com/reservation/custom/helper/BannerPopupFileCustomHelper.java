package egovframework.com.reservation.custom.helper;

import javax.servlet.ServletContext;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import egovframework.com.cms.bannerpopup.service.BannerPopupFileService;

public class BannerPopupFileCustomHelper {
	
	private static final String BANNER_POPUP_FILE = "bannerPopupFileService";
	
	public static BannerPopupFileService getBannerPopupFileService(ServletContext ctx) {
		WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(ctx);
		
		return (BannerPopupFileService) wac.getBean(BANNER_POPUP_FILE);
	}
}

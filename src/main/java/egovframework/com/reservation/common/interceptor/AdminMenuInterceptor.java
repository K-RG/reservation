package egovframework.com.reservation.common.interceptor;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import egovframework.com.reservation.common.utils.RequestInformation;

public class AdminMenuInterceptor  extends HandlerInterceptorAdapter{
	
	private static final Logger logger = LoggerFactory.getLogger(AdminMenuInterceptor.class);

	@SuppressWarnings("unchecked")
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		
		RequestInformation requestInfo = new RequestInformation(request);
		logger.info("===============AdminMenuInterceptor===============" + requestInfo.getUri());
		
		HttpSession session = request.getSession();
		List<Map<String, String>> listMap = (List<Map<String, String>>) session.getAttribute("accessAdmMenu");
		
		if(listMap == null || listMap.size() == 0) {
			response.sendRedirect("/mngr/index.do");
			return false;
		}
		
		boolean exists = false;
		
		for(Map<String, String> map : listMap) {
			String accessUrl = map.get("adminPageAccessUrl");
			if(accessUrl.equals(requestInfo.getUri())) {
				exists = true;
				break;
			}
		}
		
		if(!exists) {
			response.sendRedirect("/mngr/index.do");
			return false;
		}
		
		return true;
	}
}

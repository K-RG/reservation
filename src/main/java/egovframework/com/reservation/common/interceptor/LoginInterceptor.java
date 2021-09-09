package egovframework.com.reservation.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import egovframework.com.reservation.common.utils.RequestInformation;

public class LoginInterceptor extends HandlerInterceptorAdapter {

	private static final Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		
		RequestInformation requestInfo = new RequestInformation(request);
		String prevUrl = requestInfo.getPrevUrl();
		String returnUrl = "/login.do";
		
		if(prevUrl == null || "".equals(prevUrl)) {
			prevUrl = requestInfo.getUrL();
		}
		if(prevUrl.indexOf("/mngr/") > -1) {
			returnUrl = "/mngr/login.do";	
		}
		if(request.getQueryString() != null) {
			returnUrl = returnUrl + "?" + request.getQueryString();
		}
		
		logger.info("===============> returnUrl : "+returnUrl);
				
		HttpSession session = request.getSession(false);
		if(session == null) {
			response.sendRedirect(returnUrl);
			return false;
		}
		
		if(session.getAttribute("loginUser") == null) {
			response.sendRedirect(returnUrl);
			return false;
		}

		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		super.postHandle(request, response, handler, modelAndView);
	}
}

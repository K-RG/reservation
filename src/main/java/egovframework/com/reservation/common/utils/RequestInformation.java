package egovframework.com.reservation.common.utils;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

public class RequestInformation {
	
	private HttpServletRequest request;
	
	public RequestInformation (HttpServletRequest request) {
		this.request = request;
	}
	
	public boolean patternMacher(String strPattern) {
		Pattern pattern = Pattern.compile(strPattern);
		Matcher matcher = pattern.matcher(request.getRequestURI());
		return matcher.find();
	}
	
	public List<Map<String, String>> getParamMapList(){
		
		List<Map<String, String>> list = new ArrayList<>();
		
		Enumeration<String> paramNames = request.getParameterNames();
		if(paramNames.hasMoreElements()) {
			
			while(paramNames.hasMoreElements()) {
				
				Map<String, String> map = new HashMap<String, String>();
				
				String key = (String) paramNames.nextElement();
				String value = request.getParameter(key);
				
				map.put(key, value);
				
				list.add(map);
			}
		}
		
		return list;
	}
	
	public String getIpAddress() {
		String uri = request.getRequestURI();
		String ipAddress = "";
		if(uri != null) {
			ipAddress = ((HttpServletRequest)request).getHeader("X-FORWARDED-FOR");
			if(ipAddress != null && !"".contentEquals(ipAddress)) {
				ipAddress = request.getRemoteAddr();
			}
		}
		
		return ipAddress;
	}
	
	public String getPrevUrl() {
		return ((HttpServletRequest)request).getHeader("REFERER");
	}
	
	public String getUrL() {
		return request.getRequestURL().toString();
	}
	
	public String getUri() {
		return request.getRequestURI().toString();
	}
	
	public String getQueryString() {
		return request.getQueryString();
	}
	
	public boolean getIsAdmin() {
		return request.getSession().getAttribute("isAdmin") == null ? false : (Boolean) request.getSession().getAttribute("isAdmin");
	}
	
	public Object getSessionAttribute(String attributeName) {
		return request.getSession().getAttribute(attributeName);
	}
}

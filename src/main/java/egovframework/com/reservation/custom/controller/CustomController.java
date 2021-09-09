package egovframework.com.reservation.custom.controller;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/custom")
@Controller
public class CustomController {

	@RequestMapping("/{viewName}.do")
	public String customView(@PathVariable String viewName, HttpServletRequest request, HttpServletResponse response, Model model) {
		
		String action = request.getParameter("action");
		if(action != null && !"".equals(action)) {
			viewName = action;
		}
		
		Enumeration<String> paramNames = request.getParameterNames();
		if(paramNames.hasMoreElements()) {
			
			while(paramNames.hasMoreElements()) {
				
				String key = (String) paramNames.nextElement();
				String value = request.getParameter(key);
				
				model.addAttribute(key, value);
			}
		}
		
		return "/custom/"+viewName;
	}
}

package egovframework.com.reservation;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class indexController {

	@RequestMapping(value="/index.do")
	public String index() {
		return "/index";
	}
	
	@RequestMapping(value="/mngr/index.do")
	public String mngrIndex() {
		return "/mngr/index";
	}
	
	@RequestMapping(value="/user/index.do")
	public String userIndex() {
		return "/user/index";
	}
}

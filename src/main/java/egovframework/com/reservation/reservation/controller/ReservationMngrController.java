package egovframework.com.reservation.reservation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mngr/reservation")
public class ReservationMngrController {

	@RequestMapping("/list.do")
	public String list() {
		return "/mngr/reservation/list";
	}
}

package egovframework.com.reservation.reservation.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import egovframework.com.reservation.common.model.SearchVO;
import egovframework.com.reservation.common.utils.CheckParam;
import egovframework.com.reservation.common.utils.RequestInformation;
import egovframework.com.reservation.common.utils.ReturnErrorView;
import egovframework.com.reservation.company.model.CompanyVO;
import egovframework.com.reservation.company.service.CompanyService;
import egovframework.com.reservation.reservation.model.RoomReservationVO;
import egovframework.com.reservation.reservation.repository.RoomReservationRepository;
import egovframework.com.reservation.reservation.service.CheckService;
import egovframework.com.reservation.reservation.service.RoomReservationService;
import egovframework.com.reservation.room.controller.RoomMngrController;
import egovframework.com.reservation.room.model.RoomVO;
import egovframework.com.reservation.room.service.RoomService;

@Controller
@RequestMapping("/user/reservation")
public class ReservationUserController {
	
	private static final Logger logger = LoggerFactory.getLogger(RoomMngrController.class);

	@Autowired
	CheckService checkService;
	
	@Autowired
	CompanyService companyService;
	
	@Autowired
	RoomService roomService;
	
	@Autowired
	RoomReservationService roomReservationService;
	
	@RequestMapping("/select.do")
	public String select(@RequestParam Map<String,String> paramMap, Model model) {
		int gubun = paramMap.get("gubun") == null ? 1 : Integer.parseInt(paramMap.get("gubun"));
		
		String roomReservationUUID = paramMap.get("roomReservationUUID") == null ? "" : paramMap.get("roomReservationUUID");
		String companyBank = paramMap.get("companyBank");
		String depositor = paramMap.get("depositor");
		
		model.addAttribute("roomReservationUUID",roomReservationUUID);
		model.addAttribute("companyBank",companyBank);
		model.addAttribute("depositor",depositor);
		model.addAttribute("gubun",gubun);
		model.addAttribute("mode","select");
		
		return "/user/reservation/select";
	}
	@RequestMapping("/view.do")
	public String view(@RequestParam Map<String,String> paramMap, Model model, RoomReservationVO roomReservationVO, SearchVO searchVO) {
		int gubun = paramMap.get("gubun") == null ? 1 : Integer.parseInt(paramMap.get("gubun"));
		
		if(paramMap.get("roomReservationUUID") != null) {
			String roomReservationUUID = paramMap.get("roomReservationUUID");
			roomReservationVO = roomReservationService.findbyRoomReservationUUID(roomReservationUUID);
			if(roomReservationVO == null) {
				model.addAttribute("returnUrl","/user/reservation/select.do")
				.addAttribute("mode","select")
				.addAttribute("message","예약번호를 확인해주세요.");
			
				return "/common/reservationError";
			}
		}
		addAttribute(model, searchVO);
		model.addAttribute("roomReservationVO",roomReservationVO);		
		model.addAttribute("gubun",gubun);
		
		return "/user/reservation/view";
	}
	
	@RequestMapping("/register.do")
	public String write(@RequestParam Map<String,String> paramMap,HttpServletRequest request, Model model, SearchVO searchVO) {
		
		int gubun = paramMap.get("gubun") == null ? 1 : Integer.parseInt(paramMap.get("gubun"));
		String roomUUID = paramMap.get("roomUUID") == null ? "" : paramMap.get("roomUUID");
		if(request.getParameter("companyUUID")!=null) {
			CompanyVO companyVO = companyService.findOneCompany(request.getParameter("companyUUID"));
			model.addAttribute("companyVO",companyVO);
		}else {
			List<CompanyVO> companyList = companyService.findAllCompany();
			model.addAttribute("companyList",companyList);
		}
		if(roomUUID != null || "".equals(roomUUID)) {
			RoomVO roomVO = roomService.findOneRoom(roomUUID);
			model.addAttribute("roomVO",roomVO);
		}
		addAttribute(model, searchVO);

		model.addAttribute("mode","reservation")
			.addAttribute("gubun",gubun); 
		return "/user/reservation/write";
	}
	
	@RequestMapping("/save.do")
	public String save(@RequestParam Map<String,String> paramMap, RoomReservationVO roomReservationVO, RoomVO roomVO, Model model) {
		logger.info("=======================roomSelect End"+roomVO.getRoomUUID());
		roomVO = roomService.findOneRoom(roomVO.getRoomUUID());
		roomVO.setUseAt(1);
		String phone[] = paramMap.get("roomReservationCellPhone").split("-");
		roomReservationVO.setRoomVO(roomVO);
		roomReservationVO.setRoomReservationCellPhoneFst(Integer.parseInt(phone[0]));
		roomReservationVO.setRoomReservationCellPhoneSnd(Integer.parseInt(phone[1]));
		roomReservationVO.setRoomReservationCellPhoneTrd(Integer.parseInt(phone[2]));
		
		logger.info("=======================reservation get roomVO"+roomVO.getRoomUUID());
		roomReservationVO.setRegistId("user");
		roomReservationVO.setRegistDt(new Date());
		roomReservationService.saveReservation(roomReservationVO);
		roomService.saveRoom(roomVO);
		//roomVO.addToRservatrion(roomReservationVO);
		
		
		RoomReservationVO roomReservationVO2 = roomReservationService.findOneReservation(((roomReservationService.saveReservation(roomReservationVO)).getRoomReservationUUID()));
		
		if(roomReservationVO2.getRoomReservationPaymentType() == 1) {
			model.addAttribute("companyBank",roomReservationVO2.getRoomVO().getCompanyVO().getStrCompanyBank()); 
			model.addAttribute("depositor",roomReservationVO2.getRoomReservationName()); 
		}
		
		model.addAttribute("roomReservationUUID", roomReservationVO2.getRoomReservationUUID());
		
		
		return "redirect:/user/reservation/select.do";
	}
	
	@RequestMapping("/check.do")
	public String check(String phoneCheck, Model model) {
		return "/user/reservation/check";
	}
	
	
	@RequestMapping("/checkPhone.do")
	@ResponseBody 
	public Object checkPhone(String phoneCheck, Model model) {
	
		Random random = new Random();
		String numStr = "";
		
		for(int i=0; i<6; i++) {
			String ran = Integer.toString(random.nextInt(10));
			numStr += ran; 
		}
		
		System.out.println("수신자 번호"+phoneCheck); 
		System.out.println("인증 번호"+numStr);
		checkService.certifiedPhoneNumber(phoneCheck, numStr);
		
		return numStr; 
	}

	/*
	@RequestMapping("/companySearch.do")
	@ResponseBody
	public Map<String,Object> searchCompanyList(String company){
		Map<String,Object> map = new HashMap<String, Object>();
		
		if(company != null || !"".equals(company)) {
			CompanyVO companyVO = companyVO.setCompanyUUID(company);
			CompanyVO company1 = companyService.findOneCompany(companyVO);
			List<RoomVO> childRoomList = companyService.findChildList(companyVO);
			System.out.println("-------------check1-------------");
			map.put("company", company1);
			map.put("childRoomList", childRoomList);
			
			return map;
		}

		return map;
	}
	
	@RequestMapping("/roomSearch.do")
	@ResponseBody
	public RoomVO searchRoom(String roomUUID){
		
		RoomVO roomInfo = new RoomVO();
		if(roomUUID != null || !"".equals(roomUUID)) {
			roomInfo = roomService.findOneRoom(roomUUID);
		}
		
		return roomInfo;
	}
	*/
	private void addAttribute(Model model, SearchVO searchVO) {
			
		model.addAttribute("mode", searchVO.getMode())
			.addAttribute("page", searchVO.getPage())
			.addAttribute("rowCount", searchVO.getRowCount())
			.addAttribute("searchType", searchVO.getSearchType())
			.addAttribute("searchContent", searchVO.getSearchContent());
	}

}

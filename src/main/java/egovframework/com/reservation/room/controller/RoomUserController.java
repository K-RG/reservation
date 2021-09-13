package egovframework.com.reservation.room.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import egovframework.com.reservation.common.model.SearchVO;
import egovframework.com.reservation.common.utils.CheckParam;
import egovframework.com.reservation.common.utils.RequestInformation;
import egovframework.com.reservation.common.utils.ReturnErrorView;
import egovframework.com.reservation.company.model.CompanyVO;
import egovframework.com.reservation.company.service.CompanyService;
import egovframework.com.reservation.room.model.RoomVO;
import egovframework.com.reservation.room.service.RoomService;

@Controller
@RequestMapping("/user/room")
public class RoomUserController {
	private static final Logger logger = LoggerFactory.getLogger(RoomMngrController.class);
	
	@Autowired
	private CompanyService companyService;

	@Autowired
	private RoomService roomService;
	
	@RequestMapping("/list.do")
	public String firstFloorList(@RequestParam Map<String, String> paramMap,CompanyVO companyVO, Model model, SearchVO searchVO) {
		addAttribute(model, searchVO);
		//메뉴 구분
		int gubun = paramMap.get("gubun") == null ? 1 : Integer.parseInt(paramMap.get("gubun").toString());
		
		int maxRoomFloor = roomService.findbyMaxRoomFloor();
		logger.info("객실 최고 층수 ============="+maxRoomFloor);
		logger.info("객실 검색 날짜 ============="+paramMap.get("startDay")+"-"+ paramMap.get("endDay"));
		
		//층별 객실 조회
		List<RoomVO> roomList = roomService.findRoomList(null,gubun,0);
		
		model.addAttribute("gubun",gubun)
				.addAttribute("maxRoomFloor",maxRoomFloor)
				.addAttribute("roomList",roomList);
	
		
		/*
		String companyVO2 = "";
		
		if(paramMap.get("companyUUID") != null) {
			companyVO2 = paramMap.get("companyUUID");
		}else if(companyService.findAllCompany().size()>0) {
			companyVO2 = companyService.findAllCompany().get(0).getCompanyUUID();
		}else {
			model.addAttribute("returnUrl","/mngr/company/register.do")
				.addAttribute("mode","write")
				.addAttribute("message","사업장을 등록해주세요.");
			return "/common/reservationError";
		}							
		
		//사업장 전체 조회
		List<CompanyVO> companyList = companyService.findAllCompany();
		//사업장 조회
		companyVO = companyService.findOneCompany(companyVO2);
		//사업장 별 객실 조회
		
		
		System.out.println("통합리스트 구분"+gubun);
		System.out.println("통합리스트 유유아이디"+companyVO2);
		System.out.println("룸리스트"+roomList.size());
		System.out.println("룸 층수"+companyVO.getCompanyRoomFloor());
		
		
			.addAttribute("companyVO",companyVO)
			.addAttribute("companyUUID",companyVO2)
			.addAttribute("companyList",companyList)
			.addAttribute("roomList",roomList)
			.addAttribute("mode","list");
		*/
		return "/user/room/list";
	}
	
	@RequestMapping("/view.do")
	public String view(@RequestParam Map<String, String> paramMap, RoomVO roomVO,CompanyVO companyVO, SearchVO searchVO, Model model,HttpServletRequest request) {
		//메뉴 구분
		int gubun = paramMap.get("gubun") == null ? 1 : Integer.parseInt(paramMap.get("gubun").toString());
		
		RequestInformation requestInfo = new RequestInformation(request);
		
		CheckParam checkParam = new CheckParam(paramMap, new String[]{"roomUUID"});
		if(!checkParam.getExists()){
			return ReturnErrorView.returnErrorView(new String[]{"roomUUID", "객실 정보가 없습니다."}, requestInfo, model);
		}
		/*
		//받아온 사업장 uuid
		String companyUUID =  paramMap.get("companyUUID") == null ? 
				companyService.findAllCompany().get(0).getCompanyUUID() :  paramMap.get("companyUUID");
		
		System.out.println("등록 페이지 유유아이디"+companyUUID);
		
		companyVO = companyService.findOneCompany(companyUUID);
		*/
		roomVO = roomService.findOneRoom(roomVO.getRoomUUID());
			
		addAttribute(model, searchVO);
		model.addAttribute("gubun",gubun)
			.addAttribute("companyVO",companyVO)
			.addAttribute("roomVO",roomVO);
		return "/user/room/view";
	}
	
	private void addAttribute(Model model, SearchVO searchVO) {
		
		model.addAttribute("mode", searchVO.getMode())
			.addAttribute("page", searchVO.getPage())
			.addAttribute("rowCount", searchVO.getRowCount())
			.addAttribute("searchType", searchVO.getSearchType())
			.addAttribute("searchContent", searchVO.getSearchContent());
	}
}

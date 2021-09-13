package egovframework.com.reservation.room.controller;

import java.util.ArrayList;
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
@RequestMapping("/mngr/room")
public class RoomMngrController {
	
	private static final Logger logger = LoggerFactory.getLogger(RoomMngrController.class);
	
	@Autowired
	private CompanyService companyService;

	@Autowired
	private RoomService roomService;
	
	@RequestMapping("/list.do")
	public String firstFloorList(@RequestParam Map<String, String> paramMap,CompanyVO companyVO,SearchVO searchVO, Model model) {
		logger.info("====================RoomMngrList====================");
		//메뉴 구분
		int gubun = paramMap.get("gubun") == null ? 1 : Integer.parseInt(paramMap.get("gubun").toString());
		String companyUUID = "";
		if(paramMap.get("companyUUID") != null) {
			companyUUID = paramMap.get("companyUUID");
		}else if(companyService.findAllCompany().size()>0) {
			companyUUID = companyService.findAllCompany().get(0).getCompanyUUID();
		}else {
			model.addAttribute("returnUrl","/mngr/company/register.do")
				.addAttribute("mode","write")
				.addAttribute("message","사업장을 등록해주세요.");
			return "/common/reservationError";
		}					
		companyVO = companyService.findOneCompany(companyUUID);
		//사업장 전체 조회
		List<CompanyVO> companyList = companyService.findAllCompany();
		//사업장 별 객실 조회
		List<RoomVO> roomList = roomService.findRoomList(companyUUID, gubun,2);
		
		
		
		System.out.println("통합리스트 유유아이디"+companyUUID);
		System.out.println("룸리스트"+roomList.size());
		System.out.println("룸 층수"+companyVO.getCompanyRoomFloor());
		
		model.addAttribute("gubun",gubun)
			.addAttribute("companyVO",companyVO)
			.addAttribute("companyList",companyList)
			.addAttribute("roomList",roomList)
			.addAttribute("mode","list");
		
		return "/mngr/room/list";
	}
	
	@RequestMapping("/register.do")
	public String write(@RequestParam Map<String, String> paramMap, Model model, RoomVO roomVO,CompanyVO companyVO, SearchVO searchVO) {
		//메뉴 구분
		int gubun = paramMap.get("gubun") == null ? 1 : Integer.parseInt(paramMap.get("gubun").toString());
		System.out.println("등록컨트롤러 입장");
		//받아온 사업장 uuid
		String companyUUID =  paramMap.get("companyUUID") == null ? 
				companyService.findAllCompany().get(0).getCompanyUUID() :  paramMap.get("companyUUID");
		
		System.out.println("등록 페이지 유유아이디"+companyUUID);
		
		companyVO = companyService.findOneCompany(companyUUID);
		
		if("update".equals(searchVO.getMode())) {
			roomVO = roomService.findOneRoom(roomVO.getRoomUUID());
			companyVO = companyService.findOneCompany(roomVO.getCompanyVO().getCompanyUUID()); 
		}
		addAttribute(model, searchVO);
		model.addAttribute("gubun",gubun)
			.addAttribute("companyUUID",companyUUID)
			.addAttribute("companyVO",companyVO)
			.addAttribute("roomVO",roomVO);
		return "/mngr/room/write";
	}
	
	@RequestMapping("/save.do")
	public String save(@RequestParam Map<String,String> paramMap, RoomVO roomVO,CompanyVO companyVO, Model model) {
		logger.info("====================Room====================");
		companyVO = companyService.findOneCompany(companyVO.getCompanyUUID());
		
		roomVO.setCompanyVO(companyVO);
		roomVO.setRegistDt(new Date());
		roomVO.setRegistId("admin");
		roomService.saveRoom(roomVO);
		//companyVO.addToRoom(roomVO);
		logger.info("====================RoomSave===================="+roomVO.getCompanyVO());
		
		model.addAttribute("company",roomVO.getCompanyVO());
		
		return "redirect:/mngr/room/list.do";
	}
	
	@RequestMapping("/update.do")
	public String update(@RequestParam Map<String,String> paramMap, RoomVO roomVO, Model model) {
		//메뉴 구분
		int gubun = paramMap.get("gubun") == null ? 1 : Integer.parseInt(paramMap.get("gubun").toString());
		
		roomVO.setModifyDt(new Date());
		roomVO.setModifyId("admin");
		roomService.saveRoom(roomVO);
		
		model.addAttribute("gubun", gubun);
		
		return "redirect:/mngr/room/list.do";
	}
	
	@RequestMapping("/delete.do")
	public String delete(@RequestParam Map<String,String> paramMap, HttpServletRequest request, Model model) {
		//메뉴 구분
		int gubun = paramMap.get("gubun") == null ? 1 : Integer.parseInt(paramMap.get("gubun").toString());
				
		RequestInformation requestInfo = new RequestInformation(request);
		
		CheckParam checkParam = new CheckParam(paramMap, new String[]{"roomUUID"});
		if(!checkParam.getExists()){
			return ReturnErrorView.returnErrorView(new String[]{"roomUUID", "객실 정보가 없습니다."}, requestInfo, model);
		}
		
		String roomUUID = paramMap.get("roomUUID");
		roomService.deleteRoom(roomUUID);
		
		model.addAttribute("gubun", gubun);
		
		return "redirect:/mngr/room/list.do";
	}
	private void addAttribute(Model model, SearchVO searchVO) {
		
		model.addAttribute("mode", searchVO.getMode())
			.addAttribute("page", searchVO.getPage())
			.addAttribute("rowCount", searchVO.getRowCount())
			.addAttribute("searchType", searchVO.getSearchType())
			.addAttribute("searchContent", searchVO.getSearchContent());
	}
}

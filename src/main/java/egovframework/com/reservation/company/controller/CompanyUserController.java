package egovframework.com.reservation.company.controller;

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
@RequestMapping("/user/company")
public class CompanyUserController {
	private static final Logger logger = LoggerFactory.getLogger(CompanyUserController.class);
	@Autowired
	private CompanyService companyService;
	
	@Autowired
	private RoomService roomService;
	
	@RequestMapping("/list.do")
	public String list(@RequestParam Map<String,String> paramMap, SearchVO searchVO, Model model, HttpServletRequest request) {
		//메뉴 구분
		int gubun = paramMap.get("gubun") == null ? 1 : Integer.parseInt(paramMap.get("gubun").toString());
				
		List<CompanyVO> list = companyService.findAllCompany();
		
		model.addAttribute("list",list);
		model.addAttribute("mode","list");
		model.addAttribute("gubun",gubun);
	
		return "/user/company/list";
	}
	
	@RequestMapping("/view.do")
	public String view(@RequestParam Map<String, String> paramMap, CompanyVO companyVO, SearchVO searchVO, Model model, HttpServletRequest request) {
		
		RequestInformation requestInfo = new RequestInformation(request);
		addModel(searchVO, model);
		
		CheckParam checkParam1 = new CheckParam(paramMap, new String[]{"companyUUID"});
		
		if(!checkParam1.getExists()){
			return ReturnErrorView.returnErrorView(new String[]{"companyUUID", "카테고리 정보가 없습니다."}, requestInfo, model);
		}
		
		companyVO = companyService.findOneCompany(companyVO.getCompanyUUID());
		
		model.addAttribute("companyVO",companyVO);
	
		return "/user/company/view";
	}
	
	// model 기본 리턴값 설정
	private void addModel(SearchVO searchVO, Model model) {
		model.addAttribute("page", searchVO.getPage())
			.addAttribute("mode", searchVO.getMode())
			.addAttribute("rowCount", searchVO.getRowCount())
			.addAttribute("searchType", searchVO.getSearchType())
			.addAttribute("searchContent", searchVO.getSearchContent());
	}
}

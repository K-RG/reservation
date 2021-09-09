package egovframework.com.reservation.company.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

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
@RequestMapping("/mngr/company")
public class CompanyMngrController {

	@Autowired
	private CompanyService companyService;
	
	@Autowired
	private RoomService roomService;
	
	@Transactional
	@RequestMapping("/list.do")
	public String list(@RequestParam Map<String,String> paramMap, SearchVO searchVO, Model model, HttpServletRequest request) {
		//메뉴 구분
		int gubun = paramMap.get("gubun") == null ? 1 : Integer.parseInt(paramMap.get("gubun").toString());
		
		List<CompanyVO> list = companyService.findAllCompany();
		
		
		model.addAttribute("list",list);
		model.addAttribute("mode","LIST");
		model.addAttribute("gubun",gubun);
	
		return "/mngr/company/list";
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
		
		return "/mngr/company/view";
	}
	
	@RequestMapping("/register.do")
	public String write(SearchVO searchVO, Model model, CompanyVO companyVO) {
		
		if("update".equals(searchVO.getMode())){
			companyVO = companyService.findOneCompany(companyVO.getCompanyUUID());
		}
		
		addModel(searchVO, model);
		model.addAttribute("companyVO", companyVO);
		
		return "/mngr/company/write";
	}
	
	@RequestMapping("/save.do")
	public String save(CompanyVO companyVO,HttpServletRequest request, Model model) {
		companyVO.setRegistId("admin");
		companyVO.setRegistDt(new Date());
		companyService.saveCompany(companyVO);
		
		return "redirect:/mngr/company/list.do";
	}
	
	@RequestMapping("/update.do")
	public String update(CompanyVO companyVO, Model model) {
		System.out.println("ddddd"+companyVO.getCompanyUUID());
		companyVO.setModifyId("admin");
		companyVO.setModifyDt(new Date());
		companyService.saveCompany(companyVO);
		
		model.addAttribute("companyUUID",companyVO.getCompanyUUID());
		
		return "redirect:/mngr/company/list.do";
	}
	
	@RequestMapping("/delete.do")
	public String delete(@RequestParam Map<String,String> paramMap, HttpServletRequest request, Model model) {
		
		RequestInformation requestInfo = new RequestInformation(request);
		
		CheckParam checkParam1 = new CheckParam(paramMap, new String[]{"companyUUID"});
		
		if(!checkParam1.getExists()){
			return ReturnErrorView.returnErrorView(new String[]{"companyUUID", "사업장 정보가 없습니다."}, requestInfo, model);
		}
		String companyUUID = paramMap.get("companyUUID");
		System.out.println("삭제 유유아이디"+companyUUID);
		List<RoomVO> childRoomList = companyService.findChildList(companyUUID);
		if(childRoomList != null && childRoomList.size()>0) {
			int cnt = 0;
			for(RoomVO r : childRoomList) {
				if(companyUUID.equals(r.getCompanyVO().getCompanyUUID())) {
					cnt++;
				}
				if(cnt > 0) {
					return ReturnErrorView.returnErrorView(new String[]{"companyUUID", "사업장의 객실 정보가 존재합니다."}, requestInfo, model);
				}
			}
		}
		
		companyService.deleteCompany(companyUUID);
		return "redirect:/mngr/company/list.do";
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

package egovframework.com.reservation.common.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.ui.Model;

public class ReturnErrorView {
	
	public static String returnErrorView(CheckParam checkParam, RequestInformation requestInfo, Model model) {
		
		model.addAttribute("checkMap", checkParam.getNotExsistsValues());
		model.addAttribute("paramMapList", requestInfo.getParamMapList());
		model.addAttribute("prevUrl", requestInfo.getPrevUrl());
		model.addAttribute("url", requestInfo.getUrL());
		model.addAttribute("queryString", requestInfo.getQueryString());
		
		return "/common/error";
	}
	
	public static String returnErrorView(String[] checkInfo, RequestInformation requestInfo, Model model) {
		
		List<Map<String, String>> mapList = new ArrayList<>();
		Map<String, String> map = new HashMap<>();
		map.put(checkInfo[0],checkInfo[1]);
		mapList.add(map);
		
		model.addAttribute("checkMap", mapList);
		model.addAttribute("paramMapList", requestInfo.getParamMapList());
		model.addAttribute("prevUrl", requestInfo.getPrevUrl());
		model.addAttribute("url", requestInfo.getUrL());
		model.addAttribute("queryString", requestInfo.getQueryString());
		
		return "/common/error";
	}
}

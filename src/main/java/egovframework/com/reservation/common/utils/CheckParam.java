package egovframework.com.reservation.common.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 컨트롤러의 Map<String, String> paramMap 키,값 존재 유무를 체크한다.
 * @author user
 *
 */
public class CheckParam {
	
	private Map<String, String> paramMap;
	
	private String[] checkKeys;
	
	private boolean exists;
	
	private List<Map<String, String>> notExistsValues = new ArrayList<>();;
	
	public CheckParam(Map<String, String> paramMap, String[] checkKeys) {
		this.paramMap = paramMap;
		this.checkKeys = checkKeys;
		
		this.exists = paramMapExists();
	}
	
	private boolean paramMapExists() {
		
		exists = (paramMap == null || paramMap.size() == 0) ? false : true;
		if(exists) valueExists();
		
		return exists; 
	}
	
	private boolean valueExists() {
		for(String key : checkKeys) {
			String value = paramMap.get(key);
			if(value == null || "".equals(value)) { 
				exists = false;
				
				Map<String, String> map = new HashMap<>();
				map.put(key, key + " Is Not Exist.");
				notExistsValues.add(map);
			}
		}
		
		return exists;
	}
	
	public boolean getExists() {
		return exists;
	}
	
	public List<Map<String,String>> getNotExsistsValues(){
		return notExistsValues;
	}
	
	public String getValue(String key) {
		return paramMap.get(key);
	}
}

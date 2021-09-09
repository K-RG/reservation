package egovframework.com.reservation.common.utils;

import java.lang.reflect.Field;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * VO 객체 데이터를 json형태로 변환
 * @author user
 *
 * @param <T>
 */
public class ObjectToJson<T> {
	
	private T type;
	private List<T> list;
	
	public ObjectToJson(T type) {
		this.type = type;
		
	}
	
	public ObjectToJson(List<T> list) {
		this.list = list;
	}
	
	/**
	 * 제너럴 타입 T 객체를 JSONObject로 변환
	 * @return JSONObject
	 * @throws Exception
	 */
	public JSONObject toJson() throws Exception{
		
		JSONObject jsonObj = new JSONObject();
		
		for(Field field : type.getClass().getDeclaredFields()) {
			field.setAccessible(true);
			
			String fieldName = field.getName();
			Object obj = field.get(type);
			if(obj != null) {
				jsonObj.put(fieldName, obj.toString());
			}
		}
		
		return jsonObj;
	}
	
	/**
	 * 리스트형 제너럴 타입 T 객체를 JSONObject로 변환
	 * @return JSONObject list 와 count 로 저장
	 * @throws Exception
	 */
	public JSONObject fromListToJsonObject() throws Exception {
		
		JSONObject jsonObject = new JSONObject();
		JSONArray jArray = new JSONArray();
		int totalCount = 0;
		if(list != null && list.size() > 0) {
			totalCount = list.size();
			for(T t : list) {
				
				JSONObject jsonObj = new JSONObject();
				
				for(Field field : t.getClass().getDeclaredFields()) {
					field.setAccessible(true);
					
					String fieldName = field.getName();
					Object obj = field.get(t);
					if(obj != null) {
						jsonObj.put(fieldName, obj.toString());
					}
				}
				jArray.add(jsonObj);
			}
		}
		
		jsonObject.put("list", jArray);
		jsonObject.put("count", totalCount);
		
		return jsonObject;
	}
}

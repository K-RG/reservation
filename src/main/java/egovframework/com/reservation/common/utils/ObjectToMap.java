package egovframework.com.reservation.common.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ObjectToMap<T> {
	
	private T type;
	
	private List<T> list;
	
	public ObjectToMap(T type) {
		this.type = type;
	}
	
	public ObjectToMap(List<T> list) {
		this.list = list;
	}
	
	public Map<String, String> toMap() throws IllegalArgumentException, IllegalAccessException{
		
		Map<String, String> map = new HashMap<String, String>();
		for(Field field : type.getClass().getDeclaredFields()) {
			field.setAccessible(true);
			
			String fieldName = field.getName();
			Object obj = field.get(type);
			if(obj != null) {
				map.put(fieldName, obj.toString());
			}
		}
		
		return map;
	}
	
	public List<Map<String, String>> toListMap() throws IllegalArgumentException, IllegalAccessException{
		
		List<Map<String, String>> listMap = new ArrayList<Map<String, String>>();
		
		if(list != null && list.size() > 0) {
			for(T t : list) {
				
				Map<String, String> map = new HashMap<String, String>();
				
				for(Field field : t.getClass().getDeclaredFields()) {
					field.setAccessible(true);
					
					String fieldName = field.getName();
					Object obj = field.get(t);
					if(obj != null) {
						map.put(fieldName, obj.toString());
					}
				}
				listMap.add(map);
			}
		}
		
		return listMap;
	}
}

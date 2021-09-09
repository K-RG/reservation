package egovframework.com.reservation.common.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import egovframework.com.reservation.common.service.CryptoARIAService;

public class CryptoProcess<T> {

	private T type;
	
	private List<T> list;
	
	private CryptoARIAService cryptoService;
	
	public CryptoProcess(T type, CryptoARIAService cryptoService) {
		this.type = type;
		this.cryptoService = cryptoService;
	}
	
	public CryptoProcess(List<T> list, CryptoARIAService cryptoService) {
		this.list = list;
		this.cryptoService = cryptoService;
	}
	
	public T encryptData() throws Exception{
	
		for(Field field : type.getClass().getDeclaredFields()) {
			
			field.setAccessible(true);
			CryptoColumn columnAnnotation = field.getAnnotation(CryptoColumn.class);
			
			if(columnAnnotation.cryptoField()) {
				Object obj = field.get(type);
				if(obj != null) {
					if(!"".equals(obj.toString())) {
						Object object = cryptoService.encryptData(obj.toString());
						field.set(type, object);
					}
				}
			}
		}
		
		return type;
	}
	
	public T decryptData() throws Exception{
		
		for(Field field : type.getClass().getDeclaredFields()) {
			
			field.setAccessible(true);
			CryptoColumn columnAnnotation = field.getAnnotation(CryptoColumn.class);
			
			if(columnAnnotation.cryptoField()) {
				Object obj = field.get(type);
				if(obj != null) {
					if(!"".equals(obj.toString())) {
						Object object = cryptoService.decryptData(obj.toString());
						field.set(type, object);
					}
				}
			}
		}
		
		return type;
	}
	
	public List<T> encryptDataList() throws Exception{
		
		List<T> dataList = new ArrayList<T>();
		
		if(list != null && list.size() > 0) {
			for(T t : list) {
				
				T data = t;
				
				for(Field field : data.getClass().getDeclaredFields()) {
					
					field.setAccessible(true);
					CryptoColumn columnAnnotation = field.getAnnotation(CryptoColumn.class);
					
					if(columnAnnotation.cryptoField()) {
						Object obj = field.get(data);
						if(obj != null) {
							if(!"".equals(obj.toString())) {
								Object object = cryptoService.encryptData(obj.toString());
								field.set(data, object);
							}
						}
					}
				}
				
				dataList.add(data);
			}
		}
		
		return dataList;
	}
	
	public List<T> decryptDataList() throws Exception{
		
		List<T> dataList = new ArrayList<T>();
		
		if(list != null && list.size() > 0) {
			for(T t : list) {
				T data = t;
				
				for(Field field : data.getClass().getDeclaredFields()) {
					
					field.setAccessible(true);
					CryptoColumn columnAnnotation = field.getAnnotation(CryptoColumn.class);
					
					if(columnAnnotation.cryptoField()) {
						Object obj = field.get(data);
						if(obj != null) {
							if(!"".equals(obj.toString())) {								
								Object object = cryptoService.decryptData(obj.toString());
								field.set(data, object);
							}
						}
					}
				}
				
				dataList.add(data);
			}
		}
		
		return dataList;
	}
}

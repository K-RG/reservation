package egovframework.com.reservation.common.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil {
	
	private int rowNum = 0;
	
	/**
	 * 엑셀파일을 읽어서 Workbook 객체를 리턴한다.
	 * xls와 xlsx 확장자 비교
	 * @param filePath
	 * @return
	 */
	public static Workbook getWorkbook(String filePath) {
		
		FileInputStream fis = null;
		Workbook workbook = null;
		
		try {
			
			fis = new FileInputStream(filePath);
						
			if(filePath.toUpperCase().endsWith(".XLS")) {
				
				workbook = new HSSFWorkbook(fis);
				
			} else if(filePath.toUpperCase().endsWith(".XLSX")) {
				
				workbook = new XSSFWorkbook(fis);
				
			}
			
		} catch (FileNotFoundException e) {
			
			throw new RuntimeException(e.getMessage(), e);
			
		} catch (IOException e) {
			
			throw new RuntimeException(e.getMessage(), e);
			
		}
		
		return workbook;
	}
	
	/**
	 * Cell에 해당하는 Column Name을 가져온다.
	 * @param cell
	 * @param cellIdx
	 * @return
	 */
	public static String getName(Cell cell, int cellIdx) {
		int cellNum = 0;
		
		if(cell != null) cellNum = cell.getColumnIndex();
		else cellNum = cellIdx;
		
		return CellReference.convertNumToColString(cellNum);
	}
	
	/**
	 * Cell의 값을 가져온다.
	 * @param cell
	 * @return
	 */
	public static String getValue(Cell cell) {
		String value = "";
		
		CellType ct = cell.getCellTypeEnum();
		
		if(ct != null) {
			switch(cell.getCellTypeEnum()) {
				case FORMULA : 
					value = cell.getCellFormula();
					break;
				case NUMERIC :
					value = String.valueOf(cell.getNumericCellValue()) ;
					break;
				case STRING : 
					value = cell.getStringCellValue();
					break;
				case BOOLEAN : 
					value = String.valueOf(cell.getBooleanCellValue());
					break;
				case ERROR :
					value = String.valueOf(cell.getErrorCellValue());
					break;
				case BLANK:
					value = "";
					break;
				case _NONE:
					value = "";
					break;
				default:
					value = cell.getStringCellValue();
					break;
			}
		}
		
		return value;
	}
	
	/**
	 * 엑셀 내용 읽기
	 * @param excelReadOption
	 * @return
	 */
	public static List<Map<String, String>> excelRead(ExcelReadOption excelReadOption){
		
		// 엑셀파일 읽기
		Workbook workbook = getWorkbook(excelReadOption.getFilePath());
		// 엑셀파일에서 첫번째 시트를 가져온다.
		Sheet sheet = workbook.getSheetAt(0);
		
		// 시트에서 유효한 행의 개수를 가져온다.
		int numOfRows = sheet.getPhysicalNumberOfRows();
		int numOfCells = 0;
		
		Row row = null;
		Cell cell = null;
		
		String cellName = "";
		
		// 데이터를 담을 객체
		Map<String, String> map = null;
		
		// 각 row를 담을 리스트 객체
		List<Map<String, String>> result = new ArrayList<Map<String, String>>();
		
		// 행 반복
		for(int rowIdx = excelReadOption.getStartRow() -1; rowIdx < numOfRows; rowIdx++) {
			row = sheet.getRow(rowIdx);
			
			if(row != null) {
				
				// 마지막 셀의 숫자
				numOfCells = row.getLastCellNum();
				
				map = new HashMap<String, String>();
				
				// 셀 반복
				for(int cellIdx = 0; cellIdx < numOfCells; cellIdx++) {
					
					cell = row.getCell(cellIdx);
					cellName = getName(cell, cellIdx);
					
					// 추출대상 컬럼 확인 - 아니면 다음셀로 이동
					if(!excelReadOption.getOutputColumns().contains(cellName)) {
						continue;
					}
					
					map.put(cellName, getValue(cell));
				}
				
				map.put("rowNum", String.valueOf(rowIdx+1));
				
				result.add(map);
			}
		}
		
		return result;
	}
	
	/**
	 * 엑셀을 File로 생성
	 * @param headers     
	 * @param dataList   시트 body에 넣을 데이터
	 * @param filePath   파일 저장위치
	 * @param sheetName  시트 이름
	 * @param fileName   파일 이름
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void createExcelToFile(String[] headers, List<Map<String, String>> dataList, String filePath, String sheetName, String fileName) throws FileNotFoundException, IOException{
		Workbook workbook = new SXSSFWorkbook();
		Sheet sheet = workbook.createSheet(sheetName);
		
		rowNum = 0;
		
		createExcelHeader(headers, sheet);
		createExcelBody(dataList, sheet);
		
		FileOutputStream fos = new FileOutputStream(new File(filePath + fileName));
		workbook.write(fos);
		workbook.close();
	}
	
	/**
	 * 엑셀 생성후 response로 전달하여 다운로드
	 * @param headers
	 * @param dataList
	 * @param sheetName
	 * @param fileName
	 * @param response
	 * @throws IOException
	 */
	public void createExcelToResponse(String[] headers, List<Map<String, String>> dataList, String sheetName, String fileName, HttpServletResponse response) throws IOException{
		Workbook workbook = new SXSSFWorkbook();
		Sheet sheet = workbook.createSheet(sheetName);
		
		rowNum = 0;
		
		createExcelHeader(headers, sheet);
		createExcelBody(dataList, sheet);
		
		response.setContentType("application/vns.ms-excel");
		response.setHeader("Content-Disposition", String.format("attachment;filename=%s.xlsx", fileName));
		
		workbook.write(response.getOutputStream());
		workbook.close();
	}
	
	/**
	 * 엑셀 헤더 제목 설정
	 * @param headers 시트 header
	 * @param sheet
	 */
	private void createExcelHeader(String[] headers, Sheet sheet) {
		
		Row row = sheet.createRow(rowNum);
		int cellNum = 0;
		for(String header : headers) {
			Cell cell = row.createCell(cellNum++);
			
			cell.setCellValue(header);
		}
		
		rowNum = 1;
	}
	
	/**
	 * 엑셀 바디 데이터 설정
	 * @param dataList 시트 body에 넣을 데이터
	 * @param sheet
	 */
	private void createExcelBody(List<Map<String, String>> dataList, Sheet sheet) {
		for(Map<String, String> data : dataList) {
			Row row = sheet.createRow(rowNum++);
			
			int cellNum = 0;
			
			for(String key : data.keySet()) {
				
				Cell cell = row.createCell(cellNum++);
				
				cell.setCellValue(data.get(key));
			}
		}
	}
	
}

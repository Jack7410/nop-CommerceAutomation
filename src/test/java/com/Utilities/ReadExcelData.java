package com.Utilities;

import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcelData {
	
	
	FileInputStream input;
	XSSFWorkbook workbook; 
	XSSFSheet sheet; 
	XSSFRow row; 
	XSSFCell cell; 
	
	public ReadExcelData() {
		
		try {
			input=new FileInputStream(".//TestData/UsersData.xlsx");
			workbook=new XSSFWorkbook(input); 
			sheet=workbook.getSheetAt(0);
		}catch (Exception e) {
			e.printStackTrace(); 
		}
 
	}
	
	
	
	
	public int rowCount() {
		return sheet.getLastRowNum(); 
	}
	
	public int cellCount() {
		return sheet.getRow(1).getLastCellNum();
	}
	
	public String readExcelData(int rowNumber, int cellNumber){

		row=sheet.getRow(rowNumber);
		cell=row.getCell(cellNumber);
		
		String data;
		try {
			DataFormatter formatter=new DataFormatter(); 
			String cellData=formatter.formatCellValue(cell); 
			return cellData; 
			
		}catch (Exception e) {
			data=" "; 
		}
		
		try {
			input.close();
			workbook.close();
		}catch (Exception e) {
			e.printStackTrace();
		}

		
		return data;

		
		
		
	}
}

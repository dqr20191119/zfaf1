/*package com.cesgroup.prison.yjct;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class PoiExcelImportHandler {

	public static void main(String[] args) {
		
		*//**
		 * 门禁导入
		 * @param filePath
		 *//*
		String filePath = "D:\\门禁.xls";
		importExeclDoor(filePath);
		
		String filePath = "D:\\区域.xls";
		importExeclArea(filePath);
		
		String filePath = "D:\\摄像头.xls";
		importExeclCamera(filePath);
	}
		
	public static void importExeclDoor(String filePath) {
		
        List<List<String>> dataList = new ArrayList<List<String>>();
        File file = new File(filePath);
              
        InputStream is = null;
        Workbook wb = null;
        
        try {
         
        	is = new FileInputStream(file);
            wb = new HSSFWorkbook(is);
            // wb=new XSSFWorkbook(is);
                
            Sheet sheet = wb.getSheetAt(0);
            //得到所有行数
            int totalRows = sheet.getPhysicalNumberOfRows();
            // 得到Excel的列数
            int totalCells=0;
            if (totalRows >= 1 && sheet.getRow(0) != null){ 
                totalCells = sheet.getRow(0).getPhysicalNumberOfCells(); 
            }
            
            //循环行
            for(int rowNum = 1; rowNum < totalRows; rowNum++) {
                Row row = sheet.getRow(rowNum);
                if(row==null){
                    continue;
                }
                
                List<String> rowList = new ArrayList<String>();
                System.out.print("insert into dvc_door_control_base_dtls(DCB_NAME, DCB_AREA_ID, DCB_DPRTMNT, DCB_IDNTY, DCB_BRAND_INDC, 型号) values(");
                for(int column = 0; column < totalCells; column++) {
                    Cell cell = row.getCell(column);
                    String cellValue = "";
                    if(null != cell) {
                    	
                    	if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                    		cellValue = ((int) cell.getNumericCellValue()) + "";
                    	} else {
                    		cellValue = cell.getStringCellValue();     
                    	}                                              
                    }
                    rowList.add(cellValue);  
                    
                    if(column == totalCells - 1) {
                    	System.out.print("'" + cellValue + "'");
                    } else {
                    	if(!"门".equals(cellValue)) {
                    		System.out.print("'" + cellValue + "', ");
                    	}                   	
                    }                  
                }
                
                dataList.add(rowList);               
                System.out.println(");");
            }
            
            is.close();
        } catch(Exception e) {
        	e.printStackTrace();
        }          
    }
	
	
	public static void importExeclArea(String filePath) {
		
        File file = new File(filePath);
              
        InputStream is = null;
        Workbook wb = null;
        
        try {
         
        	is = new FileInputStream(file);
            wb = new HSSFWorkbook(is);
            // wb=new XSSFWorkbook(is);
                
            Sheet sheet = wb.getSheetAt(0);
            //得到所有行数
            int totalRows = sheet.getPhysicalNumberOfRows();
            // 得到Excel的列数
            int totalCells=0;
            if (totalRows >= 1 && sheet.getRow(0) != null){ 
                totalCells = sheet.getRow(0).getPhysicalNumberOfCells(); 
            }
   
            for(int rowNum = 1; rowNum < totalRows; rowNum++) {
            	
            	System.out.print("insert into cds_area_base_dtls(");
                Row colRow = sheet.getRow(0);
                for(int column = 0; column < totalCells; column++) {
                	Cell cell = colRow.getCell(column);
                 	if(column == totalCells - 1) {
                     	System.out.print("" + cell.getStringCellValue() + "");
                    } else {
                      	System.out.print(cell.getStringCellValue() + ", ");                 	
                    }   
                }
                System.out.print(") values(");
                 
                
                Row row = sheet.getRow(rowNum);
                if(row==null){
                    continue;
                }
                
                for(int column = 0; column < totalCells; column++) {
                    Cell cell = row.getCell(column);
                    String cellValue = "";
                    if(null != cell) {
                    	
                    	if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                    		cellValue = ((int) cell.getNumericCellValue()) + "";
                    	} else {
                    		cellValue = cell.getStringCellValue();     
                    	}                                              
                    }
                    
                    if(column == totalCells - 1) {
                    	System.out.print("'" + cellValue + "'");
                    } else {
                    	System.out.print("'" + cellValue + "', ");               	
                    }                  
                }
                
                System.out.println(");");
            }
            
            is.close();
        } catch(Exception e) {
        	e.printStackTrace();
        }          
    }
	
	public static void importExeclCamera(String filePath) {
		
        File file = new File(filePath);
              
        InputStream is = null;
        Workbook wb = null;
        
        try {
         
        	is = new FileInputStream(file);
            wb = new HSSFWorkbook(is);
            // wb=new XSSFWorkbook(is);
                
            Sheet sheet = wb.getSheetAt(0);
            //得到所有行数
            int totalRows = sheet.getPhysicalNumberOfRows();
            // 得到Excel的列数
            int totalCells=0;
            if (totalRows >= 1 && sheet.getRow(0) != null){ 
                totalCells = sheet.getRow(0).getPhysicalNumberOfCells(); 
            }
            
            StringBuffer sb = new StringBuffer();           
            for(int rowNum = 1; rowNum < totalRows; rowNum++) {
            	
            	System.out.print("insert into dvc_camera_base_dtls(");
            	sb.append("insert into dvc_camera_base_dtls(");
            	
                Row colRow = sheet.getRow(0);
                for(int column = 0; column < totalCells; column++) {
                	Cell cell = colRow.getCell(column);
                 	if(column == totalCells - 1) {
                     	System.out.print("" + cell.getStringCellValue() + "");
                     	sb.append("" + cell.getStringCellValue() + "");
                    } else {
                      	System.out.print(cell.getStringCellValue() + ", "); 
                      	sb.append(cell.getStringCellValue() + ", ");
                    }   
                }
                System.out.print(") values(");
                sb.append(") values(");
                                
                Row row = sheet.getRow(rowNum);
                if(row==null){
                    continue;
                }
                
                for(int column = 0; column < totalCells; column++) {
                    Cell cell = row.getCell(column);
                    String cellValue = "";
                    if(null != cell) {
                    	
                    	if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                    		cellValue = ((int) cell.getNumericCellValue()) + "";
                    	} else {
                    		cellValue = cell.getStringCellValue();     
                    	}                                              
                    }
                    
                    if(column == totalCells - 1) {
                    	System.out.print("'" + cellValue + "'");
                    	sb.append("'" + cellValue + "'");
                    } else {
                    	System.out.print("'" + cellValue + "', ");     
                    	sb.append("'" + cellValue + "', ");
                    }                  
                }
                
                System.out.println(");");
                sb.append(");\r\n");
            }
            
            is.close();
            
            OutputStream os = new FileOutputStream(new File("D:\\a.txt"));
            os.write(sb.toString().getBytes());
        } catch(Exception e) {
        	e.printStackTrace();
        }          
    }
	
}*/
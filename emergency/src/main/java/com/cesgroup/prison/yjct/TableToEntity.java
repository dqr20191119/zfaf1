package com.cesgroup.prison.yjct;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSetMetaData;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TableToEntity {

	private static final String URL = "jdbc:dm://33.167.18.102:5236?comOra=true";
	private static final String NAME = "PRISON";
	private static final String PASS = "PRISON"; 
	private static final String DRIVER ="dm.jdbc.driver.DmDriver";  
	
	public static void main(String[] args) {
		
		try {
			String sql = "select * from CDS_EVIDENCE_USE";  
			
			Class.forName(DRIVER);
			Connection con = DriverManager.getConnection(URL, NAME, PASS);  
			PreparedStatement pStemt = con.prepareStatement(sql);
			
			ResultSetMetaData rsmd = pStemt.getMetaData();
			int size = rsmd.getColumnCount();   //统计列
			
			for (int i = 0; i < size; i++) {
				
				String colName = rsmd.getColumnName(i + 1); 
				String colType = rsmd.getColumnTypeName(i + 1); 
				
				if("timestamp".equals(colType.toLowerCase())) {
					System.out.println("private Date " + underlineToCame(colName) + ";");
				} else {
					System.out.println("private String " + underlineToCame(colName) + ";");
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		/*String key = "ehr_cus_number," +
	            	   "ehr_handle_id," +
	            	   "ehr_type," +
	            	   "ehr_time," +
	            	   "ehr_address," +
	            	   "ehr_em_plan_id," +
	            	   "ehr_alarm_plan_id," +
	            	   "ehr_status," +
	            	   "ehr_crte_time," +
	            	   "ehr_crte_user_id," +
	            	   "ehr_updt_time," +
	            	   "ehr_updt_user_id";
		
		
		String[] arr = key.split(",");
		
		for(String a : arr) {
			System.out.println("private String " + underlineToCame(a) + ";");
		}*/
	}
	
	private static String underlineToCame(String key) {
		
		Pattern linePattern = Pattern.compile("_(\\w)");
        Matcher matcher = linePattern.matcher(key.toLowerCase()); 	        
        StringBuffer sb = new StringBuffer();  
        while(matcher.find()){  
            matcher.appendReplacement(sb, matcher.group(1).toUpperCase());  
        }  
        matcher.appendTail(sb); 
        
        return sb.toString();
	}
}

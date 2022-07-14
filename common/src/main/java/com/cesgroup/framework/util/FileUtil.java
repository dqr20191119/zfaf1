package com.cesgroup.framework.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class FileUtil {
	
	
	/**
	 * 写入文件，不存在则创建文件
	 */
	public static void writeToFile(String filePath,String fileName, String content, String charset){    
	    try {
	    	//设置默认编码
	        if(charset == null)
	            charset = "UTF-8";
	    	//String content = "视频巡查通报";
	        //File file = new File("C:\\Users\\ziyang\\Desktop\\a.txt");
	    	File fileDir = new File(filePath);
	    	if(!fileDir.exists()) {
	    		fileDir.mkdirs();
	    	}
	    	File file = new File(filePath+File.separator+fileName);
	        
	        if(!file.exists()){//文件不存在主动创建
	            file.createNewFile();
	        }
	       /* FileWriter fw = new FileWriter(file,false);//非追加，直接覆盖
	        BufferedWriter bw = new BufferedWriter(fw);
	        bw.write(content);
	        bw.close(); fw.close();	*/   
	        
	        //FileWriter不能设置编码，用下面的方式可以add by zk
	        FileOutputStream fileOutputStream = new FileOutputStream(file,false);
	        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream,charset);
	        BufferedWriter bw = new BufferedWriter(outputStreamWriter);
	        bw.write(content);
	        bw.close();
	        
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	/**
	 * 读取文件，charset ==null  ,默认UTF-8
	 * @param filePath
	 * @param charset
	 * @return
	 */
	public static String readFile(String filePath, String charset){
        //设置默认编码
        if(charset == null)
            charset = "UTF-8";
        File file = new File(filePath);
        if(file.isFile() && file.exists()){
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, charset);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                StringBuffer sb = new StringBuffer();
                String text = null;
                while((text = bufferedReader.readLine()) != null){
                    sb.append(text);
                }
                return sb.toString();
            } catch (Exception e) {
                // TODO: handle exception
            }
        }
        return null;
    }
	
	



	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//String content = "视频巡查通报";
		String filePath  =  "C:\\Users\\ziyang\\Desktop\\a.txt";
		//FileUtil.writeToFile(filePath, content);
		
		String content_tmp  = FileUtil.readFile(filePath, null);
		System.out.println(content_tmp);
	}

}

package com.cesgroup.rmi.listener;
import java.io.PrintWriter;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.cesgroup.framework.commons.SpringContextUtils;
import com.cesgroup.prison.authsystem.service.XarchInvocationSecurityMetadataSource;


public class RMIServerCustomListener implements ServletContextListener {
    public void contextDestroyed(ServletContextEvent event) {
		ServletContext sc = event.getServletContext();
		String scName = sc.getServletContextName();
		sc.log("destroyed systemParameter of '" + scName + "'");
    }

    public void contextInitialized(ServletContextEvent event) {
		ServletContext sc = event.getServletContext();
		String scName = sc.getServletContextName();
    	initCommon(scName);
		sc.log("Initializing systemParameter of '" + scName + "' complete");
    }
    /**
     * 初始化common.properties
     */
    public void initCommon(String systemName){
    	try{
    		/* ResourceBundle bundle = null;
    		 bundle = ResourceBundle.getBundle("com/cesgroup/rmi/rmi");
    			if (bundle == null) {
    				throw new IllegalArgumentException("[rmi.properties] is not found!");
    			}
    			String hostname =bundle.getString("rmi.host");
    			int port =Integer.valueOf(bundle.getString("rmi.port"));
    			RMIEndpoint.setHostName(hostname);
    		RMIEndpoint.addService(AuthServer.class, new AuthServerImpl());
    		try {
    			//RMIEndpoint.publish(8888);
    			RMIEndpoint.publish(port);
    			System.out.println("\n=======================\nRMI service start success , hostname:"+hostname+"\t"+"port:"+port);
    		} catch (Exception e) {
    			e.printStackTrace();
    		}*/
    		
    		MyThing my = new MyThing(systemName);
    		my.start();
    		
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    }

}


//业务逻辑类
class MyThing
{
	private String systemName =null;
	private Timer timer =null;
	private Date startTime = null;
	private int count=0;
	public MyThing(String systemName){
		this.systemName = systemName;
		timer = new Timer();
	}
	private TimerTask timerTask = new  TimerTask(){
		public void run(){
			//if(count++==0)return;//系统启动时不更新文章
			/*count++;
			System.out.println("\n====================================================================" +
					"\n===== System AutoUpdate AuthResources start" +
					"\n==============execute number:"+(count-1)+
					"");*/
			
			try{
				XarchInvocationSecurityMetadataSource  xarchInvocationSecurityMetadataSource = (XarchInvocationSecurityMetadataSource)SpringContextUtils.getBean("XarchInvocationSecurityMetadataSource");
				if(count==0 || xarchInvocationSecurityMetadataSource.task){
					count=1;
					System.out.println("\n"+systemName+" ===> RMIServerListener 开始执行更新资源操作");
					xarchInvocationSecurityMetadataSource.reflushResourceMapClone();
					
				}else{
					//System.out.println("SUCCESS : XarchInvocationSecurityMetadataSource  Task is false, don't need to update AuthResources");
					System.out.println("\n"+systemName +"==>[SUCCESS :任务消息：无需更新系统资源]");
				}
				
				
			}catch(Exception e){
				e.printStackTrace();
				System.out.println("ERROR : RMIServerListener  Task run  exception");
			}finally{
				
			}
			/*System.out.println("\n===== System AutoUpdate AuthResources end");
			System.out.println("======================================================================\n" );*/
		}
	};
	
	public void start(){
		startTime = new Date();//现在时间
		startTime.setTime((startTime.getTime()/1000)*1000);//执行任务的起始时间
		timer.schedule(timerTask, startTime, 60*2*1000L);//执行任务
		
	}
}

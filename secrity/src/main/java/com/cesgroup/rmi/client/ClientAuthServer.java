package com.cesgroup.rmi.client;

import java.util.Collection;
import java.util.Map;
import java.util.ResourceBundle;

import org.springframework.security.access.ConfigAttribute;

import com.cesgroup.rmi.AuthServer;
import com.cesgroup.rmi.RMIProxyFactory;


public class ClientAuthServer {
	
	private long version=0;//版本
	private long status=0;//状态0：没有加载 1：正在加载 2：加载完毕
	Map<String, Collection<ConfigAttribute>> loadResourceMap = null;
	Map<String,Map<String, Collection<ConfigAttribute>>> loadOrgDiffResourceMap =null;
	
	public static void main(String[] args) {
		while(true){
			ClientAuthServer clientAuthServer = new ClientAuthServer();
			clientAuthServer.loadAuthResource(0l);
			
			
			Map<String, Collection<ConfigAttribute>> x = clientAuthServer.loadOrgDiffResourceMap.get("103");
			if(x != null){
				Collection n = x.get("/jggz/rj/xfsj/xsjd");
				System.out.println("\n"+n);
				
			}
			
			//System.out.println(clientAuthServer.loadOrgDiffResourceMap);
			//System.out.print(clientAuthServer.version +"\t"+ clientAuthServer.status);
			try {
				Thread.sleep(100000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	
	public long getVersion() {
		return version;
	}


	public long getStatus() {
		return status;
	}


	public Map<String, Collection<ConfigAttribute>> getLoadResourceMap() {
		return loadResourceMap;
	}


	public Map<String, Map<String, Collection<ConfigAttribute>>> getLoadOrgDiffResourceMap() {
		return loadOrgDiffResourceMap;
	}


	public  void loadAuthResource(long clientVersion) {
		String hostname ="";
		int port =0;
		try {
			ResourceBundle bundle = null;
   		 bundle = ResourceBundle.getBundle("rmi/rmi");
   			if (bundle == null) {
   				throw new IllegalArgumentException("[rmi.properties] is not found!");
   			}
   			 hostname =bundle.getString("rmi.host");
   			 port =Integer.valueOf(bundle.getString("rmi.port"));
   			
			AuthServer authServer = RMIProxyFactory.getProxy(AuthServer.class, hostname, port);
			//long[] prep = authServer.getPrepareStatus();
			Object[] rmiObject = authServer.loadResource();
			if(rmiObject != null &&rmiObject.length==3){
				long[] prep = (long[])rmiObject[0];
				status = prep[0];
				version = prep[1];
				System.out.print("\nRMI server [status:"+prep[0]+",version:"+prep[1]+"]==>>>>");
				if(prep[0]==0){
					System.out.print("没有正常加载资源权限，将执行本机主动更新机制");
				}else if(prep[0]==1){
					System.out.print("正在重新加载资源权限，将在下次轮询时执行更新操作");
				}else {
					if(prep[1]==clientVersion){
						System.out.print("当前节点的权限资源与RMI server 资源权限相同，无需更新");
					}else if(prep[1]>clientVersion){
						System.out.print("资源权限准备就绪，将从RMI server服务端获取资源权限");
						//loadResourceMap =   authServer.loadResourceMap();
						//loadOrgDiffResourceMap =  authServer.loadOrgDiffResourceMap();
							loadResourceMap  = (Map<String, Collection<ConfigAttribute>>)rmiObject[1];
							loadOrgDiffResourceMap  = (Map<String, Map<String, Collection<ConfigAttribute>>>)rmiObject[2];
							//System.out.println(loadResourceMap);
							//System.out.println(loadOrgDiffResourceMap);	
							System.out.print("===>>>>>获取资源权限结束,当前版本为：V"+prep[1]+",上一版本为：v"+clientVersion);
							
							
							System.out.println("=========================开始找103权限=============");
							/*Map<String, Collection<ConfigAttribute>> x = loadOrgDiffResourceMap.get("103");
							if(x != null){
								Collection n = x.get("/jggz/rj/xfsj/xsjd");
								System.out.println(n);
								
							}
							System.out.println("103权限判断完毕");*/
					}
				}
				
			}
		} catch (Exception e) {
			//e.printStackTrace();
			System.out.println("未发现远程RMI server hostname:"+hostname+"\tport:"+port+"\t请及时打开远程RMI server");
			System.out.println("======> Exception error Message : "+e.getMessage());
		}finally{
			
		}

	}
	

	
}

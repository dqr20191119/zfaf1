package com.cesgroup.rmi;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.rmi.Naming;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class RMIProxyFactory {

	private static final ConcurrentMap<String, Object> proxyCache = new ConcurrentHashMap<String, Object>();
	
	public static void clearProxyCache(){
		proxyCache.clear();
	}
	
	public static <T> T getProxy(Class<T> iface, String host, int port) throws Exception{
		final String serviceName = getServiceName(iface);
		if(proxyCache.containsKey(serviceName)){
			return (T)proxyCache.get(serviceName);
		}
		
		final GenericRmiService rmiService = (GenericRmiService)Naming.lookup("rmi://"+host+":"+port+"/GenericRmiService");
		
		InvocationHandler h = new InvocationHandler() {
			public Object invoke(Object proxy, Method method, Object[] args)throws Throwable {
				try {
					Object r = rmiService.doService(serviceName, method.getName(), args);
					return r;
				} catch (Exception e) {
					throw new RuntimeException(e.getMessage(), e);
				}
			}
		};
		
		Object proxy = Proxy.newProxyInstance(iface.getClassLoader(), new Class<?>[]{iface}, h);
		proxyCache.put(serviceName, proxy);
		return (T) proxy;
	}
	
	private static <T> String getServiceName(Class<T> iface) throws IllegalArgumentException, IllegalAccessException{
		Field[] fs = iface.getFields();
		for(Field f : fs){
			if(f.getName().equals("SERVICENAME")){
				return (String)f.get(null);
			}
		}
		
		return iface.getName();
	}
}

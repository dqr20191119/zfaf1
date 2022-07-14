package com.cesgroup.prison.webservice;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;
import javax.xml.namespace.QName;
import javax.xml.rpc.ParameterMode;
import javax.xml.rpc.ServiceException;
import org.apache.axis.AxisFault;
import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.encoding.XMLType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbsWebServiceClient {
	private static final Logger logger = LoggerFactory.getLogger(WebServiceClient.class);
		
	protected String address;// 地址
	protected String nameSpace;// 命名空间
	
	/**
	 * 调用WebService接口
	 * @param method 接口方法
	 * @param params 接口方法参数:{"参数名":"参数值", ...}
	 * @return
	 */
	public Object callServer(String method, LinkedHashMap<String, Object> params) {
		Call call;
		List<Object> list;
		Iterator<Entry<String, Object>> iterator;
		try {
			call = (Call)new Service().createCall();
			call.setTargetEndpointAddress(address);
			call.setOperationName(new QName(nameSpace, method));
			call.setUseSOAPAction(true);
			call.setReturnType(XMLType.SOAP_STRING);
			call.setSOAPActionURI(nameSpace + method);
			
			// 设置请求参数
			list = new ArrayList<Object>();
			if (params != null) {
				iterator = params.entrySet().iterator();
				while(iterator.hasNext()){
					Entry<String, Object> entry = iterator.next();
					entry.getKey();
					list.add(entry.getValue());
					call.addParameter(new QName(nameSpace, entry.getKey()), XMLType.XSD_STRING, ParameterMode.IN);
				}
			}
			// 请求服务并返回结果
			return call.invoke(list.toArray());
		    
		} catch (ServiceException e) {
			logger.error("", e);
			e.printStackTrace();
		} catch (AxisFault e) {
			logger.error("", e);
		} catch (Exception e) {
			logger.error("", e);
		}
	    return null;
	}
	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getNameSpace() {
		return nameSpace;
	}

	public void setNameSpace(String nameSpace) {
		this.nameSpace = nameSpace;
	}
}

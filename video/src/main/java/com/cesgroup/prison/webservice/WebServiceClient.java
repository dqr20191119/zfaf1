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

public class WebServiceClient {
	private static final Logger logger = LoggerFactory.getLogger(WebServiceClient.class);
	
	/**
	 * 调用WebService
	 * @param req 参数对象
	 * @return Object
	 */
	public Object callServer(ReqParams req) {
		Call call;
		List<Object> list;
		Iterator<Entry<String, Object>> iterator;
		try {
			call = (Call)new Service().createCall();
			call.setTargetEndpointAddress(req.getAddress());
			call.setOperationName(new QName(req.getNameSpace(), req.getMethod()));
			call.setUseSOAPAction(true);
			call.setReturnType(XMLType.SOAP_STRING);
			call.setSOAPActionURI(req.getNameSpace() + req.getMethod());
			
			// 设置请求参数
			list = new ArrayList<Object>();
			if (req.getParams() != null) {
				iterator = req.getParams().entrySet().iterator();
				Entry<String, Object> entry = null;
				while(iterator.hasNext()){
					entry = iterator.next();
					list.add(entry.getValue());
					call.addParameter(new QName(req.getNameSpace(), entry.getKey()), XMLType.XSD_STRING, ParameterMode.IN);
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
	
	public ReqParams createReqParams(){
		return new WebServiceClient.ReqParams();
	}
	
	public class ReqParams{
		private String address;// 地址
		private String nameSpace;// 命名空间
		private String method;// 调用方法
		private LinkedHashMap<String, Object> params = new LinkedHashMap<String, Object>();// 参数键值对
		
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
		public String getMethod() {
			return method;
		}
		public void setMethod(String method) {
			this.method = method;
		}
		public LinkedHashMap<String, Object> getParams() {
			return params;
		}
		
		/**
		 * 设置请求参数
		 * @param params {"参数名":"参数值",...}
		 */
		public void setParams(LinkedHashMap<String, Object> params) {
			this.params = params;
		}
		
	}
}

package com.cesgroup.prison.webservice;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.rpc.ParameterMode;
import javax.xml.rpc.ServiceException;
import javax.xml.rpc.encoding.XMLType;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.soap.SOAPConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * WebService请求抽象类
 * @author xie.yh 2016.04.21
 */
public abstract class AbsWebService {
	// 静态对象定义
	private static final Logger log = LoggerFactory.getLogger(AbsWebService.class);
	private static final Service service = new Service();		// WS服务对象
	private static final JSONObject xmlType = new JSONObject();	// XML类型映射
	private static final String XMLSCHEMA = "http://www.w3.org/2001/XMLSchema";

	// 静态初始化
	static {
		xmlType.put("boolean", XMLType.SOAP_BOOLEAN);
		xmlType.put("string", XMLType.SOAP_STRING);
		xmlType.put("int", XMLType.SOAP_INT);
	}

	/*
	 * 抽象方法定义
	 */
	protected abstract String getAddress ();	// 获取请求地址
	protected abstract String getNameSpace ();	// 获取命名空间
	protected abstract String getSoapAction ();	// 获取SoapAction



	/**
	 * 获取输入参数格式
	 * @param name	参数名称
	 * @param type	参数类型
	 * @return {"name":"参数名称", "type":"参数类型"}
	 */
	protected static JSONObject getParameter (String name, String type) {
		JSONObject tempJSON = new JSONObject();
		tempJSON.put("name", name);
		tempJSON.put("type", type);
		return tempJSON;
	}

	/**
	 * 调用方法(无请求参数，无返回参数)
	 * @param method	方法名称
	 * @return
	 */
	protected Object call (String method) throws Exception {
		return call(method, null, "string", new Object[]{});
	}
	
	/**
	 * 调用方法(无请求参数，有返回参数)
	 * @param method	方法名称
	 * @param outType	输出类型(返回类型)
	 * @return
	 */
	protected Object call (String method, String outType) throws Exception {
		return call(method, null, outType, new Object[]{});
	}

	/**
	 * 调用方法
	 * @param method	方法名称
	 * @param inParams	输入类型集合：[{"name":"参数名", "type":"参数类型"}]
	 * @param outType	输出类型(返回类型)
	 * @param params	输入参数集合：[arg0,arg1,...,argn]
	 * @return
	 */
	protected Object call (String method, JSONArray inParams, String outType, Object... params) throws Exception {
		Call call = null;
		JSONObject inParam = null;

		try {
			// 创建调用对象并设置基础参数
			call = (Call) service.createCall();
			call.setTargetEndpointAddress(new URL(this.getAddress()));		// 服务地址
			call.setOperationName(new QName(this.getNameSpace(), method));	// 操作方法
			call.setSOAPActionURI(this.getSoapAction() + method);			// 方法路径
			call.setSOAPVersion(SOAPConstants.SOAP11_CONSTANTS);			// 版本
			call.setUseSOAPAction(true);
			call.setEncodingStyle(null);
			call.setReturnType(new QName(XMLSCHEMA, outType));				// 设置输出参数类型

			if (inParams != null) {
				// 设置输入参数名称及类型
				for (int i = 0, I = inParams.size(); i < I; i++) {
					inParam = inParams.getJSONObject(i);
					call.addParameter(getParamName(inParam), getXmlType(inParam), ParameterMode.IN);
				}
			}
			log.info("视频webservice信息==>服务地址[" + call.getTargetEndpointAddress() + "],"
					+ "操作方法["+call.getOperationName()+"],方法路径["+call.getSOAPActionURI()+"]");
			// 请求并返回结果
			return call.invoke(params);
		} catch (ServiceException ex) {
			log.error("创建Call服务对象异常：" + ex.getMessage());
		} catch (MalformedURLException ex) {
			log.error("创建URL地址对象异常：" + ex.getMessage());
		}
		return null;
	}

	/**
	 * 获取参数名
	 * @param inParam
	 * @return
	 */
	private QName getParamName (JSONObject inParam) {
		return new QName(this.getNameSpace(), inParam.getString("name"));
	}

	/**
	 * 获取参数类型
	 * @param inParam
	 * @return
	 */
	private QName getXmlType (JSONObject inParam) {
		return (QName) xmlType.get(inParam.getString("type"));
	}
}
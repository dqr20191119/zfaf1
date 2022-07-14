package com.cesgroup.prison.callNamesAl.bean;

import java.util.List;
import java.util.Map;

/**      
* @projectName：baseframework   
* @ClassName：CallNamesBean   
* @Description：   阿里接口
* @author：Tao   
* @date：2018年8月13日 下午3:18:31   
* @version        
*/
public class CallNamesAlBean {

	private String code;
	private String message;
	private String requestId;
	private boolean success = false;

	public CallNamesAlBean() {
	}

	/**
	* @Fields faces : 人脸注册成功返回
	*/
	private Map<String, List<CallNamesPrisonerAlBean>> faces;
	/**
	* @Fields failRegisterFaces : 人脸注册失败返回
	*/
	private Map<String, CallNamesAlBean> failRegisterFaces;

	/**
	* @Fields request : 人脸删除 人脸组删除返回
	*/
	private CallNamesRequestAlBean request;

	public CallNamesRequestAlBean getRequest() {
		return request;
	}

	public void setRequest(CallNamesRequestAlBean request) {
		this.request = request;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public Map<String, List<CallNamesPrisonerAlBean>> getFaces() {
		return faces;
	}

	public void setFaces(Map<String, List<CallNamesPrisonerAlBean>> faces) {
		this.faces = faces;
	}

	public Map<String, CallNamesAlBean> getFailRegisterFaces() {
		return failRegisterFaces;
	}

	public void setFailRegisterFaces(Map<String, CallNamesAlBean> failRegisterFaces) {
		this.failRegisterFaces = failRegisterFaces;
	}

}

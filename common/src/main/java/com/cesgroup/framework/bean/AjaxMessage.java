package com.cesgroup.framework.bean;

public class AjaxMessage {
	
	private boolean success = false;	// 处理结果
	private String code = null;			// 消息码
	private String msg = null;			// 消息提示
	private Object obj = null;			// 返回的结果

	public AjaxMessage(){
		
	}

	public AjaxMessage(boolean success){
		this.success = success;
		this.obj = null;
		this.msg = "";
	}

	public AjaxMessage(boolean success, Object obj){
		this.success = success;
		this.obj = obj;
		this.msg = "";
	}

	public AjaxMessage(boolean success, Object obj, String msg){
		this.success = success;
		this.obj = obj;
		this.msg = msg;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}
}

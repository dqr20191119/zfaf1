<%@page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<style>
.form-control{
    width: 100%;
}
</style>

<center>
<cui:form id="formId_videoDeviceAdd">
<cui:input  type="hidden" required="true" id="vdiCusNumber" name="vdiCusNumber"  componentCls="form-control"></cui:input>
<cui:input  type="hidden" required="true" id="vdiCrteUserId" name="vdiCrteUserId"  componentCls="form-control"></cui:input>
<div style="display:none;" ><cui:datepicker  required="true" id="vdiCrteTime"  name="vdiCrteTime"  dateFormat="yyyy-MM-dd HH:mm:ss" componentCls="form-control"></cui:datepicker>
</div>
	<table class="table table-fixed" 
		style="width: 90%;">
		<tr>
			<td width="30%" ><label>名称：</label></td>
			<td width="60%" ><cui:input name="vdiDeviceName"  required="true" componentCls="form-control"></cui:input></td>
		</tr>
		<tr>
			<td><label>类型：</label></td>
			<td><cui:combobox  required="true" componentCls="form-control" name="vdiTypeIndc"  emptyText="请选择"  data="VDI_TYPE_INDC_json"></cui:combobox></td>
		</tr>
		<tr>
			<td><label>品牌：</label></td>
			<td><cui:combobox  required="true" componentCls="form-control" name="vdiBrand" emptyText="请选择"  data="VDI_BRAND_json"></cui:combobox></td>
		</tr>
		<tr>
			<td><label>型号：</label></td>
			<td><cui:input name="vdiMode" componentCls="form-control"></cui:input></td>
		</tr>
		<tr>
			<td><label>用户名：</label></td>
			<td><cui:input required="true" name="vdiUserName" componentCls="form-control"></cui:input></td>
		</tr>
		<tr>
			<td><label>密码：</label></td>
			<td><cui:input type="password" required="true" name="vdiUserPassword" componentCls="form-control"></cui:input></td>
		</tr>
		<tr>
			<td><label>IP地址：</label></td>
			<td><cui:input required="true" name="vdiIpAddrs" validType = "ip" componentCls="form-control"></cui:input></td>
		</tr>
		<tr>
			<td><label>IP地址2：</label></td>
			<td><cui:input name="vdiIp2Addrs" validType = "ip" componentCls="form-control"></cui:input></td>
		</tr>
		<tr>
			<td><label>端口：</label></td>
			<td><cui:input required="true" name="vdiPort" validType = "port" componentCls="form-control"></cui:input></td>
		</tr>
	</table>
</cui:form>
</center>
<div class="dialog-buttons" >
	<cui:button label="重置"  onClick="reset"></cui:button>
	<cui:button label="保存"  onClick="save"></cui:button>
</div>
<script>
var jsConst=window.top.jsConst;
var cusNumber=jsConst.ORG_CODE;							//监狱编号
var userId=jsConst.USER_ID;					//登录人

$.parseDone(function(){
	initFormData();
});


var VDI_TYPE_INDC_json=<%=CodeFacade.loadCode2Json("4.20.15")%>;
var VDI_BRAND_json=<%=CodeFacade.loadCode2Json("4.20.16")%>;
function initFormData(){
	$("#vdiCusNumber").textbox("setValue",cusNumber);
	$("#vdiCrteUserId").textbox("setValue",userId);
	$('#vdiCrteTime').datepicker('setDate',new Date());
}
function reset(){
	$("#formId_videoDeviceAdd").form("reset");
	initFormData();
}
function save(){
	if ($("#formId_videoDeviceAdd").form("valid")) {
		var formData = $("#formId_videoDeviceAdd").form("formData");
		var ur = '${ctx}/sppz/videoDevice/create.json';
		$.ajax({
			type : 'post',
			url : ur,
			data : formData,
			dataType : 'json',
			success : function(data) {
				if(data.exception==undefined){
					$.message({
						message : "保存成功",
						cls : "success",
						iframePanel:true
					});
					$("#gridId_videoDevice").grid("reload");
					$("#dialogId_videoDeviceManage").dialog("close");
				}else{
					$.message( {
						iframePanel:true,
				        message:data.exception.cause.message,
				        type:"danger"
				    });
				}
				
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$.alert({
					message:textStatus,
					title:"信息提示",
					iframePanel:true
				});
			}
		});

	} else {
		$.alert({
			message:"请确认输入是否正确！",
			title:"信息提示",
			iframePanel:true
		});
	}
}
</script>
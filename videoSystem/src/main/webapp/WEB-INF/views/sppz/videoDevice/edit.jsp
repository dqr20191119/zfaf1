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
<cui:form id="formId_videoDeviceEdit">
<cui:input  type="hidden" required="true" name="id" value="${model.id}" componentCls="form-control"></cui:input>
<cui:input  type="hidden" required="true" name="vdiCusNumber" value="${model.vdiCusNumber}" componentCls="form-control"></cui:input>
<cui:input  type="hidden" required="true" name="vdiCrteUserId" value="${model.vdiCrteUserId}" componentCls="form-control"></cui:input>
<div style="display:none;" ><cui:datepicker  required="true" id="vdiCrteTime"  name="vdiCrteTime" value="${model.vdiCrteTime}" dateFormat="yyyy-MM-dd HH:mm:ss" componentCls="form-control"></cui:datepicker>
	</div>
	<table class="table table-fixed" 
		style="width: 90%;">
		<tr>
			<td width="30%" ><label>名称：</label></td>
			<td width="60%" ><cui:input required="true" componentCls="form-control" name="vdiDeviceName" value="${model.vdiDeviceName}"></cui:input></td>
		</tr>
		<tr>
			<td><label>类型：</label></td>
			<td><cui:combobox required="true" name="vdiTypeIndc" value="${model.vdiTypeIndc}" componentCls="form-control"  emptyText="请选择"  data="VDI_TYPE_INDC_json"></cui:combobox></td>
		</tr>
		<tr>
			<td><label>品牌：</label></td>
			<td><cui:combobox required="true" name="vdiBrand" value="${model.vdiBrand}"  componentCls="form-control" emptyText="请选择"  data="VDI_BRAND_json"></cui:combobox></td>
		</tr>
		<tr>
			<td><label>型号：</label></td>
			<td><cui:input name="vdiMode" value="${model.vdiMode}" componentCls="form-control"></cui:input></td>
		</tr>
		<tr>
			<td><label>用户名：</label></td>
			<td><cui:input required="true" name="vdiUserName" value="${model.vdiUserName}" componentCls="form-control"></cui:input></td>
		</tr>
		<tr>
			<td><label>密码：</label></td>
			<td><cui:input required="true" type="password" name="vdiUserPassword" value="${model.vdiUserPassword}" componentCls="form-control"></cui:input></td>
		</tr>
		<tr>
			<td><label>IP地址：</label></td>
			<td><cui:input required="true" name="vdiIpAddrs" validType = "ip" value="${model.vdiIpAddrs}" componentCls="form-control"></cui:input></td>
		</tr>
		<tr>
			<td><label>IP地址2：</label></td>
			<td><cui:input name="vdiIp2Addrs" validType = "ip" value="${model.vdiIp2Addrs}" componentCls="form-control"></cui:input></td>
		</tr>
		<tr>
			<td><label>端口：</label></td>
			<td><cui:input required="true" name="vdiPort" validType = "port" value="${model.vdiPort}" componentCls="form-control"></cui:input></td>
		</tr>
	</table>
</cui:form>
</center>
<div class="dialog-buttons" >
	<cui:button label="重置"  onClick="reset"></cui:button>
	<cui:button label="保存"  onClick="save_edit"></cui:button>
</div>
<script>
var VDI_TYPE_INDC_json=<%=CodeFacade.loadCode2Json("4.20.15")%>;
//var VDI_BRAND_json=[{'value':'8','text':'HIK'},{'value':'20','text':'EdnnsEV8000'},{'value':'106','text':'海康平台'},{'value':'1','text':'大华'},{'value':'400','text':'宇视平台'},{'value':'900','text':'威科平台'}]
var VDI_BRAND_json=<%=CodeFacade.loadCode2Json("4.20.16")%>;
function reset(){
	$("#formId_videoDeviceEdit").form("reset");
}
function save_edit(){
	if ($("#formId_videoDeviceEdit").form("valid")) {
		var formData = $("#formId_videoDeviceEdit").form("formData");
		var ur = '${ctx}/sppz/videoDevice/update';
		$.ajax({
			type : 'post',
			url : ur,
			data : formData,
			dataType : 'json',
			success : function(data) {
				console.log(data);
				if(data.exception==undefined){
					$.message({
						message : "修改成功",
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
				//console.log(data.exception);
				//console.log(data.exception.cause.message);
				
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
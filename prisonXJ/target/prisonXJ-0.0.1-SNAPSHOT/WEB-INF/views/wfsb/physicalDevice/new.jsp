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
<cui:form id="formId_physicalDeviceAdd">
<cui:input  type="hidden" required="true" id="pdeCusNumber" name="pdeCusNumber" value="" componentCls="form-control"></cui:input>
<cui:input  type="hidden" required="true" id="pdeCrteUserId" name="pdeCrteUserId" value="" componentCls="form-control"></cui:input>
<cui:input  type="hidden" required="true" id="pdeUpdtUserId" name="pdeUpdtUserId" value="" componentCls="form-control"></cui:input>
<div style="display:none;" ><cui:datepicker  required="true" id="pdeCrteTime"  name="pdeCrteTime"  dateFormat="yyyy-MM-dd HH:mm:ss" componentCls="form-control"></cui:datepicker>
<cui:datepicker  required="true" id="pdeUpdtTime"  name="pdeUpdtTime"  dateFormat="yyyy-MM-dd HH:mm:ss" componentCls="form-control"></cui:datepicker>
	</div>
	<table class="table table-fixed" 
		style="width: 100%;">
		<tr>
			<td width="40%"><label>设施类型：</label></td>
			<td width="60%"><cui:combobox required="true" name="pdeTypeIndc"  emptyText="请选择"  data="pdeTypeIndc_json" componentCls="form-control"></cui:combobox></td>
		</tr>
		<tr>
			<td><label>设施名称：</label></td>
			<td><cui:combobox required="true" name="pdeDeviceIdnty"  emptyText="请选择"  url="${ctx }/wfsb/physicalDeviceName/simplePhysicalDeviceName.json" componentCls="form-control" onChange="onChangeDeviceName"></cui:combobox></td>
			<cui:input  id="pdeDeviceName" name="pdeDeviceName" type="hidden" value="" componentCls="form-control"></cui:input></td>
		</tr>
		<tr>
			<td><label>安装位置：</label></td>
			<td><cui:input name="pdeLocation" componentCls="form-control"></cui:input></td>
		</tr>
		<tr>
			<td><label>正常总数：</label></td>
			<td><cui:input validType = "integer" name="pdeNormalCount" componentCls="form-control"></cui:input></td>
		</tr>
		<tr>
			<td><label>异常总数：</label></td>
			<td><cui:input validType = "integer" name="pdeAbnormalCount" componentCls="form-control"></cui:input></td>
		</tr>
		<tr>
			<td><label>数量单位：</label></td>
			<td><cui:input name="pdeUnit" componentCls="form-control"></cui:input></td>
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
var cusNumber=jsConst.ORG_CODE							//监狱编号
var userId=jsConst.USER_ID					//登录人
var pdeTypeIndc_json=<%=CodeFacade.loadCode2Json("4.20.34")%>;
$.parseDone(function(){
	initFormData();
});
function initFormData(){
	$("#pdeCusNumber").textbox("setValue",cusNumber);
	$("#pdeCrteUserId").textbox("setValue",userId);
	$("#pdeUpdtUserId").textbox("setValue",userId);
	$('#pdeCrteTime').datepicker('setDate',new Date());
	$('#pdeUpdtTime').datepicker('setDate',new Date());
}

function reset(){
	$("#formId_physicalDeviceAdd").form("reset");
	initFormData();
}
function onChangeDeviceName(e,ui){
	$("#pdeDeviceName").textbox('setValue',ui.text);
}
function save(){
	if ($("#formId_physicalDeviceAdd").form("valid")) {
		var formData = $("#formId_physicalDeviceAdd").form("formData");
		var ur = '${ctx}/wfsb/physicalDevice/create.json';
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
					$("#gridId_physicalDevice").grid("reload","${ctx}/wfsb/physicalDevice/searchPhysicalDevice.json?pdeCusNumber="+cusNumber);
					$("#dialogId_physicalDeviceManage").dialog("close");
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
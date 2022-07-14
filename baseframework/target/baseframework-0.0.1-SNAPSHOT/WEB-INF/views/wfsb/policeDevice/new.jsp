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
<cui:form id="formId_policeDeviceAdd">
<cui:input  type="hidden" required="true" id="poeCusNumber" name="poeCusNumber" value="" componentCls="form-control"></cui:input>
<cui:input  type="hidden" required="true" id="poeCrteUserId" name="poeCrteUserId" value="" componentCls="form-control"></cui:input>
<cui:input  type="hidden" required="true" id="poeUpdtUserId" name="poeUpdtUserId" value="" componentCls="form-control"></cui:input>
<div style="display:none;" ><cui:datepicker  required="true" id="poeCrteTime"  name="poeCrteTime"  dateFormat="yyyy-MM-dd HH:mm:ss" componentCls="form-control"></cui:datepicker>
<cui:datepicker  required="true" id="poeUpdtTime"  name="poeUpdtTime"  dateFormat="yyyy-MM-dd HH:mm:ss" componentCls="form-control"></cui:datepicker>
	</div>
	<table class="table table-fixed" 
		style="width: 100%;">
		
		<tr>
			<td width="20%"><label>器材名称：</label></td>
			<td width="30%"><cui:input required="true" name="poeDeviceName" componentCls="form-control"></cui:input></td>
			<td width="20%"><label>责任人：</label></td>
			<td width="30%"><cui:input name="poeAdministrator" componentCls="form-control"></cui:input></td>
		</tr>
		<tr>
			<td><label>器材类型：</label></td>
			<td><cui:combobox required="true" name="poeTypeIndc" emptyText="请选择"  data="poeTypeIndc_json" componentCls="form-control"></cui:combobox></td>
			<td><label>装备功能描述：</label></td>
			<td><cui:input name="poeFunctionDesc" componentCls="form-control"></cui:input></td>
		</tr>
		<tr>
			<td><label>所属部门：</label></td>
			<td><cui:combobox id="poeDprtmntId" name="poeDprtmntId" emptyText="请选择"  url="${ctx}" componentCls="form-control" onChange="onChangeDprtmnt"></cui:combobox>
			<cui:input  id="poeDprtmntName" name="poeDprtmntName" type="hidden" value="" componentCls="form-control"></cui:input></td>
			<td><label>保管措施：</label></td>
			<td><cui:input name="poeSaveMehtod" componentCls="form-control"></cui:input></td>
		</tr>
		<tr>
			<td><label>型号：</label></td>
			<td><cui:input  name="poeModel" componentCls="form-control"></cui:input></td>
			<td><label>状态：</label></td>
			<td><cui:combobox name="poeSttsIndc"  emptyText="请选择"  data="poeSttsIndc_json" componentCls="form-control"></cui:combobox></td>
		</tr>
		<tr>
			<td><label>计量单位：</label></td>
			<td><cui:input name="poeUnit" componentCls="form-control"></cui:input></td>
			<td row="2"><label>状态描述：</label></td>
			<td row="2"><cui:input name="poeSttsDesc" componentCls="form-control"></cui:input></td>
		</tr>
		<tr>
			<td><label>数量：</label></td>
			<td><cui:input  validType = "integer" name="poeCount" componentCls="form-control"></cui:input></td>	
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

$.parseDone(function(){
	
	$("#poeDprtmntId").combobox("reload","${ctx}/common/authsystem/findAllChildrenOrgByJyKeyForCombobox.json?cusNumber="+cusNumber);
	initFormData();
});
var poeTypeIndc_json=<%=CodeFacade.loadCode2Json("4.20.34")%>;
var poeSttsIndc_json=<%=CodeFacade.loadCode2Json("4.20.55")%>;
function initFormData(){
	$("#poeCusNumber").textbox("setValue",cusNumber);
	$("#poeCrteUserId").textbox("setValue",userId);
	$("#poeUpdtUserId").textbox("setValue",userId);
	$('#poeCrteTime').datepicker('setDate',new Date());
	$('#poeUpdtTime').datepicker('setDate',new Date());
}

function reset(){
	$("#formId_policeDeviceAdd").form("reset");
	initFormData();
}

function onChangeDprtmnt(e,ui){
	console.log(ui.text);
	$("#poeDprtmntName").textbox('setValue',ui.text);
}

function save(){
	if ($("#formId_policeDeviceAdd").form("valid")) {
		var formData = $("#formId_policeDeviceAdd").form("formData");
		var ur = '${ctx}/wfsb/policeDevice/create.json';
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
					$("#gridId_policeDevice").grid("reload");
					$("#dialogId_policeDeviceManage").dialog("close");
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
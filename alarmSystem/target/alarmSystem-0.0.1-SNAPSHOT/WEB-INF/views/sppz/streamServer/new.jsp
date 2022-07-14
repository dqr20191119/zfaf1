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
<cui:form id="formId_streamServerAdd">
<cui:input  type="hidden" required="true" id="ssiCusNumber" name="ssiCusNumber" value="" componentCls="form-control"></cui:input>
<cui:input  type="hidden" required="true" id="ssiCrteUserId" name="ssiCrteUserId" value="" componentCls="form-control"></cui:input>
<div style="display:none;" ><cui:datepicker  required="true" id="ssiCrteTime"  name="ssiCrteTime"  dateFormat="yyyy-MM-dd HH:mm:ss" componentCls="form-control"></cui:datepicker>
	</div>
	<table class="table table-fixed" 
		style="width: 90%;">
		<tr>
			<td width="30%" ><label>名称：</label></td>
			<td width="60%" ><cui:input required="true" name="ssiServerName" componentCls="form-control"></cui:input></td>
		</tr>
		<tr>
			<td><label>类型：</label></td>
			<td><cui:combobox required="true" name="ssiTypeIndc" componentCls="form-control"  emptyText="请选择"  data="ssiTypeIndc_json"></cui:combobox></td>
		</tr>
		<tr>
			<td><label>IP地址：</label></td>
			<td><cui:input required="true" validType = "ip" name="ssiIpAddrs" componentCls="form-control"></cui:input></td>
		</tr>
		<tr>
			<td><label>端口：</label></td>
			<td><cui:input required="true" name="ssiPort" validType = "port" componentCls="form-control"></cui:input></td>
		</tr>
		<tr>
			<td><label>IP地址2：</label></td>
			<td><cui:input name="ssiIp2Addrs" validType = "ip" componentCls="form-control"></cui:input></td>
		</tr>
		<tr>
			<td><label>端口2：</label></td>
			<td><cui:input name="ssiPort2" validType = "port" componentCls="form-control"></cui:input></td>
		</tr>
		<tr>
			<td><label>区域：</label></td>
			<td><cui:combotree id="ssiAreaId" name="ssiAreaId"  url="${ctx}" simpleDataEnable="true"  componentCls="form-control"/></td>
		</tr>
		<tr>
			<td><label>描述：</label></td>
			<td><cui:input name="ssiRemark"  componentCls="form-control"></cui:input></td>
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
	debugger;
	$("#ssiAreaId").combotree("tree").tree("reload","${ctx}/common/areadevice/findForCombotree.json?cusNumber="+cusNumber+"&deviceType=0");
	initFormData();
});
var ssiTypeIndc_json=<%=CodeFacade.loadCode2Json("4.20.15")%>;
function initFormData(){
	$("#ssiCusNumber").textbox("setValue",cusNumber);
	$("#ssiCrteUserId").textbox("setValue",userId);
	$('#ssiCrteTime').datepicker('setDate',new Date());
}
function reset(){
	$("#formId_streamServerAdd").form("reset");
	initFormData();
}
function save(){
	if ($("#formId_streamServerAdd").form("valid")) {
		var formData = $("#formId_streamServerAdd").form("formData");
		var ur = '${ctx}/sppz/streamServer/create.json';
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
					$("#gridId_streamServer").grid("reload");
					$("#dialogId_streamServerManage").dialog("close");
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
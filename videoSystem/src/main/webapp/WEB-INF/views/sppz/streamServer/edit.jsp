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
<cui:form id="formId_streamServerEdit">
<cui:input  type="hidden" required="true" name="id" value="${model.id}" componentCls="form-control"></cui:input>
<cui:input  type="hidden" required="true" name="ssiCusNumber" value="${model.ssiCusNumber}" componentCls="form-control"></cui:input>
<cui:input  type="hidden" required="true" name="ssiCrteUserId" value="${model.ssiCrteUserId}" componentCls="form-control"></cui:input>
<div style="display:none;" ><cui:datepicker  required="true" id="ssiCrteTime"  name="ssiCrteTime" value="${model.ssiCrteTime}"  dateFormat="yyyy-MM-dd HH:mm:ss" componentCls="form-control"></cui:datepicker>
	</div>
	<table class="table table-fixed" 
		style="width: 90%;">
		<tr>
			<td width="30%" ><label>名称：</label></td>
			<td width="60%" ><cui:input componentCls="form-control" required="true" name="ssiServerName" value="${model.ssiServerName }"></cui:input></td>
		</tr>
		<tr>
			<td><label>类型：</label></td>
			<td><cui:combobox required="true" name="ssiTypeIndc" value="${model.ssiTypeIndc }" componentCls="form-control"  emptyText="请选择"  data="ssiTypeIndc_json"></cui:combobox></td>
		</tr>
		<tr>
			<td><label>IP地址：</label></td>
			<td><cui:input required="true" name="ssiIpAddrs" validType = "ip" value="${model.ssiIpAddrs}" componentCls="form-control"></cui:input></td>
		</tr>
		<tr>
			<td><label>端口：</label></td>
			<td><cui:input required="true" name="ssiPort" validType = "port" value="${model.ssiPort }" componentCls="form-control"></cui:input></td>
		</tr>
		<tr>
			<td><label>IP地址2：</label></td>
			<td><cui:input name="ssiIp2Addrs" validType = "ip" value="${model.ssiIp2Addrs }" componentCls="form-control"></cui:input></td>
		</tr>
		<tr>
			<td><label>端口2：</label></td>
			<td><cui:input name="ssiPort2" validType = "port" value="${model.ssiPort2 }" componentCls="form-control"></cui:input></td>
		</tr>
		<tr>
			<td><label>区域：</label></td>
			<td><cui:combotree id="ssiAreaId" name="ssiAreaId" value="${model.ssiAreaId }"  url="${ctx}" simpleDataEnable="true"  componentCls="form-control"/></td>
		</tr>
		<tr>
			<td><label>描述：</label></td>
			<td><cui:input name="ssiRemark" value="${model.ssiRemark }" componentCls="form-control"></cui:input></td>
		</tr>
	</table>
</cui:form>
</center>
<div class="dialog-buttons" >
	<cui:button label="重置"  onClick="reset"></cui:button>
	<cui:button label="保存"  onClick="f_edit"></cui:button>
</div>
<script>
var ssiTypeIndc_json=<%=CodeFacade.loadCode2Json("4.20.15")%>;
var jsConst=window.top.jsConst;
var cusNumber=jsConst.ORG_CODE;							//监狱编号
var userId=jsConst.USER_ID;					//登录人

$.parseDone(function(){
	$("#ssiAreaId").combotree("tree").tree("reload","${ctx}/common/areadevice/findForCombotree.json?cusNumber="+cusNumber+"&deviceType=0");
});

function reset(){
	$("#formId_streamServerEdit").form("reset");
}
function f_edit(){
	if ($("#formId_streamServerEdit").form("valid")) {
		var formData = $("#formId_streamServerEdit").form("formData");
		var ur = '${ctx}/sppz/streamServer/update';
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
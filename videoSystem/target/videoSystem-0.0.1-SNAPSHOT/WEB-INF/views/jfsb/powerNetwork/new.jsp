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
<cui:form id="formId_powerNetworkAdd">
<cui:input  type="hidden" required="true" id="pnbCusNumber" name="pnbCusNumber" value="" componentCls="form-control"></cui:input>
<cui:input  type="hidden" required="true" id="pnbCrteUserId" name="pnbCrteUserId" value="" componentCls="form-control"></cui:input>
<cui:input  type="hidden" required="true" id="pnbUpdtUserId" name="pnbUpdtUserId" value="" componentCls="form-control"></cui:input>
<div style="display:none;" ><cui:datepicker  required="true" id="pnbCrteTime"  name="pnbCrteTime"  dateFormat="yyyy-MM-dd HH:mm:ss" componentCls="form-control"></cui:datepicker>
<cui:datepicker  required="true" id="pnbUpdtTime"  name="pnbUpdtTime"  dateFormat="yyyy-MM-dd HH:mm:ss" componentCls="form-control"></cui:datepicker>
	</div>
	<table class="table table-fixed" 
		style="width: 90%;">
		<tr>
			<td width="15%"><label>电网名称：</label></td>
			<td width="35%"><cui:input required="true" name="pnbName" componentCls="form-control"></cui:input></td>
			<td width="15%"><label>电网品牌：</label></td>
			<td width="35%"><cui:combobox name="pnbBrand" emptyText="请选择"  data="pnbBrand_json" componentCls="form-control"></cui:combobox></td>
		</tr>
		<tr>
			<td><label>电网IP：</label></td>
			<td><cui:input required="false" name="pnbIp" validType = "ip" componentCls="form-control"></cui:input></td>
			<td><label>端口：</label></td>
			<td><cui:input required="false" name="pnbPort" validType = "port" componentCls="form-control"></cui:input></td>
		</tr>
		<tr>
			<td><label>所属区域：</label></td>
			<td><cui:combotree id="pnbArea_new" name="pnbArea"  url="${ctx}" simpleDataEnable="true"  componentCls="form-control"  onChange="onChangeArea"></cui:combotree>
			<cui:input  id="pnbAreaName" name="pnbAreaName" type="hidden" value="" componentCls="form-control"></cui:input></td>
			<td><label>电源电压(V)：</label></td>
			<td><cui:input name="pnbPowerSourceVoltage" validType = "number" componentCls="form-control"></cui:input></td>
		</tr>
		<tr>
			<td><label>A相电压：</label></td>
			<td><cui:input name="pnbABoxVoltage" validType = "number" componentCls="form-control"></cui:input></td>
			<td><label>B相电压：</label></td>
			<td><cui:input name="pnbBBoxVoltage" validType = "number" componentCls="form-control"></cui:input></td>
		</tr>
		<tr>
			<td><label>A相电流：</label></td>
			<td><cui:input name="pnbABoxPowerFlow"  validType = "number" componentCls="form-control"></cui:input></td>
			<td><label>B相电流：</label></td>
			<td><cui:input name="pnbBBoxPowerFlow" validType = "number" componentCls="form-control"></cui:input></td>
		</tr>
		<tr>
			<td><label>电源电流(A)：</label></td>
			<td><cui:input name="pnbPowerSourceFlow" validType = "number" componentCls="form-control"></cui:input></td>
			<td><label>排序序号：</label></td>
			<td><cui:input name="pnbOrderId" validType = "number" componentCls="form-control"></cui:input></td>
		</tr>
		<tr>
			<td><label>设备安装地址：</label></td>
			<td><cui:textarea name="pnbAddrs" componentCls="form-control"></cui:textarea></td>
			<td><label>备注：</label></td>
			<td><cui:textarea name="pnbRemark" componentCls="form-control"></cui:textarea></td>
		</tr>
		<tr>
			<td><label>状态：</label></td>
			<td><cui:combobox name="pnbSttsIndc" emptyText="请选择"  data="pnbSttsIndc_json" componentCls="form-control"></cui:combobox></td>
		</tr>
	</table>
</cui:form>
</center>
<div class="dialog-buttons" >
	<cui:button label="重置"  onClick="reset"></cui:button>
	<cui:button label="保存"  onClick="save"></cui:button>
</div>
<script>
	var pnbBrand_json =<%=CodeFacade.loadCode2Json("4.20.40")%>;
	var pnbSttsIndc_json=[{"value":'0',"text":"正常"},{"value":'1',"text":"故障"}];
	var jsConst=window.top.jsConst;
	var cusNumber=jsConst.ORG_CODE							//监狱编号
	var userId=jsConst.USER_ID					//登录人
	
	$.parseDone(function(){
		initFormData();
		
	});

	function initFormData(){
		$("#pnbArea_new").combotree("tree").tree("reload","${ctx}/common/areadevice/findForCombotree.json?cusNumber="+cusNumber+"&deviceType=0");
		
		$("#pnbCusNumber").textbox("setValue",cusNumber);
		$("#pnbCrteUserId").textbox("setValue",userId);
		$("#pnbUpdtUserId").textbox("setValue",userId);
	
		$('#pnbCrteTime').datepicker('setDate',new Date());
		$('#pnbUpdtTime').datepicker('setDate',new Date());
	}
	function reset(){
		$("#formId_powerNetworkAdd").form("reset");
		initFormData();
	}
	function onChangeArea(e,ui){
		console.log(ui.text);
		$("#pnbAreaName").textbox('setValue',ui.text);
	}
	function save(){
		if ($("#formId_powerNetworkAdd").form("valid")) {
			var formData = $("#formId_powerNetworkAdd").form("formData");
			var ur = '${ctx}/jfsb/powerNetwork/create.json';
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
						$("#gridId_powerNetwork").grid("reload","${ctx}/jfsb/powerNetwork/searchPowerNetwork.json?pnbCusNumber="+cusNumber);
						$("#dialogId_powerNetworkManage").dialog("close");
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
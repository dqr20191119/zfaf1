<%@page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<div style="width: 100%; height: 100%;">
	<div style="width: 100%; height: 100%;">
		<cui:form id="formId_jswh_edit">
		<cui:input type='hidden' id="id" name="id" />
		<cui:input type='hidden' id="cpjLch" name="cpjLch" />
			<table class="table">
				<tr>
					<th>区域:</th>
					<td><cui:input id="cpjLc" name="cpjLc"  width="250" readonly="true"></cui:input>
				</tr>
				<tr>
					<th>监舍名称:</th>
					<td><cui:input id="cpjJs" name="cpjJs" width="250" required="true"></cui:input>
				</tr>
				<tr>
					<th>主摄像头:</th>
					<td><cui:combobox id="edit_cpjCameraId" name="cpjCameraId" width="250" enablePinyin="true"  readonlyInput="false" required="true"></cui:combobox>
				</tr>
				<tr>
					<th>辅摄像头:</th>
					<td><cui:combobox id="edit_cpjFCameraId" name="cpjFCameraId" width="250" multiple="true" readonlyInput="false" enablePinyin="true"></cui:combobox>
				</tr>
			</table>
		</cui:form>
	</div>
</div>
<script>
	var jsConst = window.top.jsConst;
	var cusNumber = jsConst.ORG_CODE; //监狱编号
	var userId = jsConst.USER_ID; //登录人

	$.parseDone(function() {
		
		var id = '${id}';
		var areaId = '${areaId}';
		loadForm("formId_jswh_edit", "${ctx}/xxhj/jswh/getById?id="+id, function(data) {
		});
		$("#edit_cpjCameraId").combobox( "reload", "${ctx}/common/areadevice/findDeviceList.json?deviceType=1&cusNumber=" + cusNumber+ "&areaId=" + areaId);  
		$("#edit_cpjFCameraId").combobox( "reload", "${ctx}/common/areadevice/findDeviceList.json?deviceType=1&cusNumber=" + cusNumber+ "&areaId=" + areaId);  
	})
	
	function save() {
		
		var id = '${id}';
		var validFlag = $("#formId_jswh_edit").form("valid");
		if(!validFlag) {
			return;
		}
		var list = new Array();
		var new_JS = $("#cpjJs").textbox("getText");
		var cpjLch = $("#cpjLch").textbox("getText");
		var cpjLc = $("#cpjLc").textbox("getText");
		var nowData = [];
		nowData.push(
				{key : cpjLch, value : cpjLc},{key : "id", value : id});
		var cameraId = $("#edit_cpjCameraId").combobox("getValue");
		var cameraName = $("#edit_cpjCameraId").combobox("getText");
		var fCameraId = $("#edit_cpjFCameraId").combobox("getValues");
		var fCameraName = $("#edit_cpjFCameraId").combobox("getText");
		var addData = [];
		addData.push(
				{key : "jsTab", value : new_JS},
				{key : cameraName, value : cameraId},
				{key : fCameraName, value : fCameraId});
		nowData.push({key : "edit", value : addData});
		list.push(nowData);
		var url = "${ctx}/xxhj/jswh/searchAllData.json";
		var flag = list;
		
		$.ajax({
			type : "post",
			data : {"cpjLch" : cpjLch, "id" : id},
			url : url,
			dataType : "json",
			async  : false,
			success : function(data) {
				if(data.length > 0) {
					
					for(var key in data) {
						if (new_JS == data[key].cpjJs) {
							flag = false;
							$.message({message:"注意！该监舍已存在！", cls:"waring"});
							break;
						}
					}
				}
			},
		});
		return flag;
	}
</script>
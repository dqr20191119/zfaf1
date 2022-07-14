<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<style>
	.table td {
		font-size: 15px;
		font-weight: bold;
		text-align: center;
	}	
</style>

<div>
    <cui:form id="deviceName_query">
	    <table class="table">
		   <tr>
				<td>设备名称：</td>
				<td><cui:input id="deviceName" name="deviceName"></cui:input></td>
				<td>设备编号：</td>
				<td><cui:input id="deviceId" name="deviceId"></cui:input></td>
				<td><cui:button cls="cyanbtn" id="s_userSearchButton" label="查询" name="se" onClick="search" componentCls="coral-btn-blue" /></td>
				<td><cui:button id="resetBtn" label="重置" onClick="resetHandler"></cui:button></td> 
		  </tr>
	   </table>
    </cui:form> 
</div>

<cui:grid id="gridId_technicalDeviceList" singleselect="true" colModel="technicalDeviceListData" fitStyle="fill" pager="true">
</cui:grid>

<script type="text/javascript">

	var cusNumber ='${cusNumber}';
	var sttsIndc = '${sttsIndc}';
	var sql = '${sql}';
	var useLimit ='${useLimit}';
	
	$.parseDone(function() {
		
		var url = "${ctx}/xxhj/jfsb/" + sql;
		var data = {
			cusNumber : cusNumber,
			sttsIndc : sttsIndc,
			useLimit : useLimit,
		};
		
		$("#gridId_technicalDeviceList").grid("option", "postData", data);
		$("#gridId_technicalDeviceList").grid("reload", url);
	});
	
	var DeviceStts = [{
	  'value' : '0',
	  'text' : '正常'
    }, {
	  'value' : '1',
	  'text' : '故障'
    }];
	
	var technicalDeviceListData = [{
		label : "监所编号",
		name : "PBD_CUS_NUMBER",
		hidden : true
	}, {
		label : "监所名称",
		name : "OBD_ORGA_NAME",
		align : "center"
	}, {
		label : "设备编号",
		name : "DEVICEID",
		align : "center"
	}, {
		label : "设备名称",
		name : "DEVICENAME",
		align : "center"
	}, {
		label : "设备状态",
		name : "DEVICESTTS",
		formatter : 'convertCode',
		revertCode : true,
		align : "center",
		formatoptions : {
			'dataStructure' : 'list',
			'data' : DeviceStts
		}
	}]
	
	function search() {

		var formData = $("#deviceName_query").form("formData");		
		formData["cusNumber"]=cusNumber;
		formData["sttsIndc"]=sttsIndc;
		formData["useLimit"]=useLimit;
		
		$("#gridId_technicalDeviceList").grid("option", "postData", formData);
		$("#gridId_technicalDeviceList").grid("reload");
	}
	
	function resetHandler() {
			
		$("#deviceName_query").form("clear");
	}		
</script>
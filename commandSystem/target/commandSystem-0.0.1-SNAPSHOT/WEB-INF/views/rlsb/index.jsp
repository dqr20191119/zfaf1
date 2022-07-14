<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.cesgroup.prison.common.facade.AuthSystemFacade"%>
<%@ page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<%@ page import="javax.servlet.http.HttpServletResponse"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<div style="height: 100%; margin: 0px 10px;">
	<cui:form id="formId_rlsb_List">
		<table class="table">
			<tr>
				<th>名称 ：</th>
				<td><cui:input id="namelistName" name="namelistName"></cui:input></td>
				<th>事件时间 ：</th>
				<td><cui:datepicker  id="st" name="st" dateFormat="yyyy-MM-dd" width="190"></cui:datepicker ></td>
				<td>
	 				<cui:button label="查询" componentCls="btn-primary" onClick="searchrlsb"></cui:button>
				</td>
			</tr>
			<tr>
				<th>卡号 ：</th>
				<td><cui:input id="cardNo" name="cardNo"></cui:input></td>
				<th>所属区域 ：</th>
				<td><cui:input id="locationName" name="locationName"></cui:input></td>
				<td>
					<cui:button label="重置" onClick="clear"></cui:button>
				</td>
			</tr>
		</table>
	</cui:form>
	
	
		<cui:toolbar id="toolbarId_jhrc" data="toolbar_rlsb_List"></cui:toolbar>	
	
		
	<cui:grid id="gridId_rlsb_List" rownumbers="true" multiselect="true" width="auto" fitStyle="fill" rowNum="15">
		<cui:gridCols>
			<cui:gridCol name="id" hidden="true">id</cui:gridCol>
			<cui:gridCol name="facepicUrl" hidden="true">罪犯照片</cui:gridCol>
			<cui:gridCol name="capSmallpicUrl" hidden="true">抓拍照片</cui:gridCol>
			<cui:gridCol name="namelistName" align="center">名称</cui:gridCol>
			<cui:gridCol name="cardNo" align="center">卡号</cui:gridCol>
			<cui:gridCol name="gender" align="center">性别</cui:gridCol>
			<cui:gridCol name="st" align="center">事件时间</cui:gridCol>
			<cui:gridCol name="similarity" align="center">相似度</cui:gridCol>
			<cui:gridCol name="channelName" align="center">抓拍地点</cui:gridCol>
			<cui:gridCol name="locationName" align="center">所属区域</cui:gridCol>
		</cui:gridCols>
		<cui:gridPager gridId="gridId_rlsb_List" />
	</cui:grid>
	<cui:dialog id="dialogId_rlsb_view" autoOpen="false" resizable="false" iframePanel="true" reLoadOnOpen="true" modal="true" autoDestroy="true">
	</cui:dialog>
</div>
<script>
	var cusNumber = jsConst.CUS_NUMBER;
	var USER_LEVEL = jsConst.USER_LEVEL;
	var drpmntId = jsConst.DEPARTMENT_ID;
	
	$.parseDone(function() {
		searchrlsb();
	});
	
	function searchrlsb() {
		var formData = $("#formId_rlsb_List").form("formData");
		formData["cusNumber"] = cusNumber;
		$("#gridId_rlsb_List").grid("option", "postData", formData);
		$("#gridId_rlsb_List").grid("reload");
		$("#gridId_rlsb_List").grid("reload","${ctx}/rlsb/searchRlsbList"); 
	}
	
	function clear() {
		$("#formId_rlsb_List").form("reset");
	}
	
	var toolbar_rlsb_List = [{
		"type" : "button",
		"id" : "btnId_view",
		"label" : "查看",
		"onClick" : "openDailog",
		"componentCls" : "btn-primary"
	}];
	
	
	function openDailog(event, ui) {
	
		var url;
		var dialog_w;
		var dialog_h;
		
		if(ui.id == "btnId_view") {
			var selarrrow = $("#gridId_rlsb_List").grid("option", "selarrrow");	
			var rowData = $("#gridId_rlsb_List").grid("getRowData", selarrrow[0]);
			if(selarrrow && selarrrow.length == 1) {
				url="${ctx}/rlsb/toView?facepic=1&capSmallpic=2&id="+selarrrow[0]
				dialog_w = "800";
				dialog_h = "450";
			} else {
				$.message({message:"请选择一条记录！", cls:"waring"});
				return;
			}			
		}
		$("#dialogId_rlsb_view").dialog({
			width : dialog_w,
			height : dialog_h,
			title : ui.label,
			url : url				 
		});
		$("#dialogId_rlsb_view").dialog("open");
		 
	}
	
	
	
	</script>
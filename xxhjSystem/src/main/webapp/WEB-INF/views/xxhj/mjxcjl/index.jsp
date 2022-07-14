<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.cesgroup.prison.common.facade.AuthSystemFacade"%>
<%@ page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<script src="${ctx}/static/js/scripts/common.js"></script>

<div style="height: 100%; margin: 0px 10px;">
	<cui:form id="formId_mjxcjl_query">
		<table class="table">
			<tr>
			
				<th>开始时间：</th>
				<td><cui:datepicker  id="startTime" name="startTime" dateFormat="yyyy-MM-dd HH:mm:ss" width="190"></cui:datepicker ></td>
				<th>结束时间：</th>
				<td><cui:datepicker  id="endTime" name="endTime" dateFormat="yyyy-MM-dd HH:mm:ss" width="190"></cui:datepicker ></td>
				
		
			</tr>

			</tr>
			<tr>
				<th>民警名称：</th>
				<td><cui:input name="policeName"></cui:input></td>
				<td><cui:button label="查询" componentCls="btn-primary"
						onClick="search" /></td>
				<td><cui:button label="重置" onClick="reset"></cui:button></td>
			</tr>
		</table>
	</cui:form>


	<cui:grid id="gridId_mjxcjl" rownumbers="true" multiselect="true"
		width="auto" fitStyle="fill" url="${ctx}/xxhj/patrol/mjxcjl/searchData"
		rowNum="15">
		<cui:gridCols>
			<cui:gridCol name="id" hidden="true">id</cui:gridCol>
			<cui:gridCol name="policeName" align="center">民警姓名</cui:gridCol>
			<cui:gridCol name="startTime" align="center">开始时间</cui:gridCol>
			<cui:gridCol name="endTime" align="center">结束时间</cui:gridCol>
			<cui:gridCol name="patrolRoute" align="center">路线</cui:gridCol>
			<cui:gridCol name="remark" align="center">备注</cui:gridCol>
		</cui:gridCols>
		<cui:gridPager gridId="gridId_mjxcjl" />
	</cui:grid>

	<cui:dialog id="dialogId_mjxcjl" autoOpen="false" resizable="false"
		iframePanel="true" reLoadOnOpen="true" modal="true" autoDestroy="true"
		buttons="buttons_mjxcjl">
	</cui:dialog>
</div>

<script>
var comboboxData_statue=[{value:'0',text:'未还'},{value:'1',text:'已还'}];

	$.parseDone(function() {

	});

	var toolbar_mjxcjl = [ {
		"type" : "button",
		"id" : "btnId_add",
		"label" : "新增",
		"onClick" : "openDailog",
		"componentCls" : "btn-primary"
	}, {
		"type" : "button",
		"id" : "btnId_edit",
		"label" : "修改",
		"onClick" : "openDailog",
		"componentCls" : "btn-primary"
	}, {
		"type" : "button",
		"id" : "btnId_remove",
		"label" : "删除",
		"onClick" : "deleteByIds",
		"componentCls" : "btn-primary"
	} ];

	var buttons_mjxcjl = [ {
		text : "保存",
		id : "btnId_save",
		click : function() {
			saveOrUpdate();
		}
	}, {
		text : "取消",
		id : "btnId_cancel",
		click : function() {
			$("#dialogId_mjxcjl").dialog("close");
		}
	} ];

	function openDailog(event, ui) {
		var url;
		if (ui.id == "btnId_add") {
			url = "${ctx}/wwjg/mjxcjl/toEdit";
		} else if (ui.id == "btnId_edit") {
			var selarrrow = $("#gridId_mjxcjl").grid("option", "selarrrow");
			if (selarrrow && selarrrow.length == 1) {
				url = "${ctx}/xxhj/mjxcjl/toEdit?id=" + selarrrow[0];
			} else {
				$.message({
					message : "请选择一条记录！",
					cls : "waring"
				});
				return;
			}
		}
		$("#dialogId_mjxcjl").dialog({
			width : 360,
			height : 600,
			title : ui.label,
			url : url
		});
		$("#dialogId_mjxcjl").dialog("open");
	}

	function search() {
		var formData = $("#formId_mjxcjl_query").form("formData");
		$("#gridId_mjxcjl").grid("option", "postData", formData);
		$("#gridId_mjxcjl").grid("reload");
		$("#gridId_mjxcjl").grid("reload","${ctx}/xxhj/patrol/mjxcjl/searchSwdbPage");
	}

	function reset() {
		$("#formId_mjxcjl_query").form("reset");
	}

	
</script>
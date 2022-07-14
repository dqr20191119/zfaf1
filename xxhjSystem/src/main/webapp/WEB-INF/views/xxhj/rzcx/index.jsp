<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.cesgroup.prison.common.facade.AuthSystemFacade"%>
<%@ page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<script src="${ctx}/static/js/scripts/common.js"></script>

<div style="height: 100%; margin: 0px 10px;">
<cui:form id="formId_rzcx_query">
		<table class="table">
			<tr>
			
				<th>从：</th>
				<td><cui:datepicker  id="StartTime" name="StartTime" dateFormat="yyyy-MM-dd HH:mm:ss" width="190"></cui:datepicker ></td>
				<th>到：</th>
				<td><cui:datepicker  id="EndTime" name="EndTime" dateFormat="yyyy-MM-dd HH:mm:ss" width="190"></cui:datepicker ></td>
				<th>查询：</th>
				<td><cui:input id="SearchDate" type="text"  placeholder="请输入操作,IP或URL" name="SearchDate"  ></cui:input></td>
				<td><cui:button label="查询" componentCls="btn-primary"
						onClick="search" /></td>
				<td><cui:button label="重置" onClick="reset"></cui:button></td>
			</tr>

			
		</table>
	</cui:form>


	<cui:grid id="gridId_rzcx" rownumbers="true" multiselect="true"
		width="auto" fitStyle="fill" url="${ctx}/xxhj/rzcx/searchData"
		rowNum="15">
		<cui:gridCols>
			<cui:gridCol name="id" hidden="true">id</cui:gridCol>
			<cui:gridCol name="time" align="center">时间</cui:gridCol>
			<cui:gridCol name="opTarget" align="center">操作</cui:gridCol>
			<cui:gridCol name="result" align="center">结果</cui:gridCol>
			<cui:gridCol name="ip" align="center">ip</cui:gridCol>
			<cui:gridCol name="url" align="center">url</cui:gridCol>
		</cui:gridCols>
		<cui:gridPager gridId="gridId_rzcx" />
	</cui:grid>

	<cui:dialog id="dialogId_rzcx" autoOpen="false" resizable="false"
		iframePanel="true" reLoadOnOpen="true" modal="true" autoDestroy="true"
		buttons="buttons_rzcx">
	</cui:dialog>
</div>

<script>
	$.parseDone(function() {

	});

	var toolbar_rzcx = [ {
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

	var buttons_rzcx = [ {
		text : "保存",
		id : "btnId_save",
		click : function() {
			saveOrUpdate();
		}
	}, {
		text : "取消",
		id : "btnId_cancel",
		click : function() {
			$("#dialogId_rzcx").dialog("close");
		}
	} ];

	function openDailog(event, ui) {
		var url;
		if (ui.id == "btnId_add") {
			url = "${ctx}/rzcx/toEdit";
		} else if (ui.id == "btnId_edit") {
			var selarrrow = $("#gridId_rzcx").grid("option", "selarrrow");
			if (selarrrow && selarrrow.length == 1) {
				url = "${ctx}/xxhj/rzcx/toEdit?id=" + selarrrow[0];
			} else {
				$.message({
					message : "请选择一条记录！",
					cls : "waring"
				});
				return;
			}
		}
		$("#dialogId_rzcx").dialog({
			width : 360,
			height : 600,
			title : ui.label,
			url : url
		});
		$("#dialogId_rzcx").dialog("open");
	}

	function search() {
		var formData = $("#formId_rzcx_query").form("formData");
		var STime =$("#StartTime").datepicker("getValue");
		var ETime =$("#EndTime").datepicker("getValue");
		var SearchDate=$("#SearchDate").textbox("getValue");
		$("#gridId_rzcx").grid("option", "postData", formData);
		$("#gridId_rzcx").grid("reload");
		$("#gridId_rzcx").grid("reload","${ctx}/xxhj/rzcx/searchSwdbPage?STime="+STime+"&ETime="+ETime+"&SearchDate="+SearchDate);
	}

	function reset() {
		$("#formId_rzcx_query").form("reset");
	}

	
</script>
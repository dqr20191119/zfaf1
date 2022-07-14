<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.cesgroup.prison.common.facade.AuthSystemFacade"%>
<%@ page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<script src="${ctx}/static/js/scripts/common.js"></script>

<div style="height: 100%; margin: 0px 10px;">
	<cui:form id="formId_znys_query">
		<table class="table">
			<tr>
				<th>终端机名称：</th>
				<td><cui:input name="name"></cui:input></td>
			<%-- 	<th>进出状态：</th>
				<td><cui:combobox name="inOut" id="inOut" data="comboboxData_inOut"></cui:combobox></td> --%>
				<th>钥匙名称：</th>
				<td><cui:input name="keyName" id="keyName" ></cui:input></td>
				<th>钥匙状态：</th>
				<td><cui:combobox name="statue" id="statue" data="comboboxData_statue"></cui:combobox></td>
			</tr>

			</tr>
			<tr>
				
				<th>取钥匙时间：</th>
				<td><cui:datepicker  id="getKeyTime" name="getKeyTime" dateFormat="yyyy-MM-dd HH:mm:ss" width="190"></cui:datepicker ></td>
				<th>还钥匙时间：</th>
				<td><cui:datepicker  id="returnKeyTime" name="returnKeyTime" dateFormat="yyyy-MM-dd HH:mm:ss" width="190"></cui:datepicker ></td>
				<td><cui:button label="查询" componentCls="btn-primary"
						onClick="search" /></td>
				<td><cui:button label="重置" onClick="reset"></cui:button></td>
			</tr>
		</table>
	</cui:form>


	<cui:grid id="gridId_znys" rownumbers="true" multiselect="true"
		width="auto" fitStyle="fill" url="${ctx}/xxhj/znys/searchData"
		rowNum="15">
		<cui:gridCols>
			<cui:gridCol name="id" hidden="true">id</cui:gridCol>
			<cui:gridCol name="name" align="center">终端机名称</cui:gridCol>
			<cui:gridCol name="keyName" align="center">钥匙名称</cui:gridCol>
			<cui:gridCol name="getKeyTime" align="center">取钥匙时间</cui:gridCol>
			<cui:gridCol name="getKeyPerson" align="center">取钥匙人员</cui:gridCol>
			<cui:gridCol name="returnKeyTime" align="center">还钥匙时间</cui:gridCol>
			<cui:gridCol name="statue" align="center">钥匙状态</cui:gridCol>
		</cui:gridCols>
		<cui:gridPager gridId="gridId_znys" />
	</cui:grid>

	<cui:dialog id="dialogId_znys" autoOpen="false" resizable="false"
		iframePanel="true" reLoadOnOpen="true" modal="true" autoDestroy="true"
		buttons="buttons_znys">
	</cui:dialog>
</div>

<script>
var comboboxData_statue=[{value:'0',text:'未还'},{value:'1',text:'已还'}];

	$.parseDone(function() {

	});

	var toolbar_znys = [ {
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

	var buttons_znys = [ {
		text : "保存",
		id : "btnId_save",
		click : function() {
			saveOrUpdate();
		}
	}, {
		text : "取消",
		id : "btnId_cancel",
		click : function() {
			$("#dialogId_znys").dialog("close");
		}
	} ];

	function openDailog(event, ui) {
		var url;
		if (ui.id == "btnId_add") {
			url = "${ctx}/wwjg/znys/toEdit";
		} else if (ui.id == "btnId_edit") {
			var selarrrow = $("#gridId_znys").grid("option", "selarrrow");
			if (selarrrow && selarrrow.length == 1) {
				url = "${ctx}/wwjg/znys/toEdit?id=" + selarrrow[0];
			} else {
				$.message({
					message : "请选择一条记录！",
					cls : "waring"
				});
				return;
			}
		}
		$("#dialogId_znys").dialog({
			width : 360,
			height : 600,
			title : ui.label,
			url : url
		});
		$("#dialogId_znys").dialog("open");
	}

	function search() {
		var formData = $("#formId_znys_query").form("formData");
		$("#gridId_znys").grid("option", "postData", formData);
		$("#gridId_znys").grid("reload");
		$("#gridId_znys").grid("reload","${ctx}/xxhj/znys/searchSwdbPage");
	}

	function reset() {
		$("#formId_znys_query").form("reset");
	}

	
</script>
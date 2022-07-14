<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.cesgroup.prison.common.facade.AuthSystemFacade"%>
<%@ page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<script src="${ctx}/static/js/scripts/common.js"></script>

<div style="height: 100%; margin: 0px 10px;">
	<cui:form id="formId_mjkgjl_query">
		<table class="table">
			<tr>
				<th>门禁编号：</th>
				<td><cui:input name="pirDoorIdnty"></cui:input></td>
			<%-- 	<th>进出状态：</th>
				<td><cui:combobox name="inOut" id="inOut" data="comboboxData_inOut"></cui:combobox></td> --%>
				<th>进出人员：</th>
				<td><cui:combobox name="pirPeopleTypeIndc" id="people" data="comboboxData_people"></cui:combobox></td>
			</tr>

			</tr>
			<tr>
				
				<th>开始时间：</th>
				<td><cui:datepicker  id="pirEnterTime" name="pirEnterTime" dateFormat="yyyy-MM-dd HH:mm:ss" width="190"></cui:datepicker ></td>
				<th>结束时间：</th>
				<td><cui:datepicker  id="pirLeaveTime" name="pirLeaveTime" dateFormat="yyyy-MM-dd HH:mm:ss" width="190"></cui:datepicker ></td>
				<td><cui:button label="查询" componentCls="btn-primary"
						onClick="search" /></td>
				<td><cui:button label="重置" onClick="reset"></cui:button></td>
			</tr>
		</table>
	</cui:form>


	<cui:grid id="gridId_mjkgjl" rownumbers="true" multiselect="true"
		width="auto" fitStyle="fill"
		rowNum="15">
		<cui:gridCols>
			<cui:gridCol name="id" hidden="true">id</cui:gridCol>
			<cui:gridCol name="pirDoorIdnty" align="center">门禁编号</cui:gridCol>
			<cui:gridCol name="pirDoorName" align="center">门禁名称</cui:gridCol>
			<cui:gridCol name="pirPeopleTypeIndc" align="center">人员</cui:gridCol>
			<cui:gridCol name="pirPoliceName" align="center">姓名</cui:gridCol>
			<cui:gridCol name="pirEnterTime" align="center">进入时间</cui:gridCol>
			<cui:gridCol name="pirLeaveTime" align="center">离开时间</cui:gridCol>
		</cui:gridCols>
		<cui:gridPager gridId="gridId_mjkgjl" />
	</cui:grid>

	<cui:dialog id="dialogId_mjkgjl" autoOpen="false" resizable="false"
		iframePanel="true" reLoadOnOpen="true" modal="true" autoDestroy="true"
		buttons="buttons_mjkgjl">
	</cui:dialog>
</div>

<script>
var comboboxData_inOut=[{value:'0',text:'出'},{value:'1',text:'进'}];
var comboboxData_people=[{value:'1',text:'民警'},{value:'2',text:'犯人'},{value:'3',text:'外来人员'},{value:'4',text:'职工'}];

var jsConst = window.top.jsConst;
var cusNumber = jsConst.CUS_NUMBER;//监狱号

$.parseDone(function() {
	var url = "${ctx}/xxhj/mjkgjl/searchSwdbPage.json?pirCusNumber=" + cusNumber;
	if(jsConst.USER_LEVEL == 3){
		url = url + "&pirDprtmntId=" + jsConst.DEPARTMENT_ID;
	}
	$("#gridId_mjkgjl").grid("reload", url);
});

	var toolbar_mjkgjl = [ {
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

	var buttons_mjkgjl = [ {
		text : "保存",
		id : "btnId_save",
		click : function() {
			saveOrUpdate();
		}
	}, {
		text : "取消",
		id : "btnId_cancel",
		click : function() {
			$("#dialogId_mjkgjl").dialog("close");
		}
	} ];

	function openDailog(event, ui) {
		var url;
		if (ui.id == "btnId_add") {
			url = "${ctx}/wwjg/mjkgjl/toEdit";
		} else if (ui.id == "btnId_edit") {
			var selarrrow = $("#gridId_mjkgjl").grid("option", "selarrrow");
			if (selarrrow && selarrrow.length == 1) {
				url = "${ctx}/wwjg/mjkgjl/toEdit?id=" + selarrrow[0];
			} else {
				$.message({
					message : "请选择一条记录！",
					cls : "waring"
				});
				return;
			}
		}
		$("#dialogId_mjkgjl").dialog({
			width : 360,
			height : 600,
			title : ui.label,
			url : url
		});
		$("#dialogId_mjkgjl").dialog("open");
	}

	function search() {
		var formData = $("#formId_mjkgjl_query").form("formData");
		$("#gridId_mjkgjl").grid("option", "postData", formData);
		$("#gridId_mjkgjl").grid("reload");
	}

	function reset() {
		$("#formId_mjkgjl_query").form("reset");
	}

	
</script>
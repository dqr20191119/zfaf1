<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<%@page import="com.ces.prison.common.constants.GroupKeyConst"%>
<%@page import="com.ces.prison.common.constants.SystemConst"%>
<%@taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<style>
.form-control {
	width: 100%;
}
</style>
<div style="height: 100%;">
	<cui:form id="formId_query">
		<table class="table">
			<tr>
				<th>人员姓名：</th>
				<td>
					<cui:input id="cName" name="cName" componentCls="form-control"></cui:input>
				</td>
				<th>登记事由：</th>
				<td>
					<cui:input id="cSy" name="cSy" componentCls="form-control"></cui:input>
				</td>
				<td>
					<cui:input id="onlyToday" name="onlyToday" type="hidden"></cui:input>
					<cui:button label="查询" onClick="query"></cui:button>
					<cui:button label="重置" onClick="reset"></cui:button>
				</td>
			</tr>
		</table>
	</cui:form>
	
	<cui:grid id="gridId_foreignerPeos" rownumbers="true" singleselect="false" fitStyle="fill" width="auto" colModel="gridId_foreignerPeos_colModelDate">
		<cui:gridPager gridId="gridId_foreignerPeos" />
	</cui:grid>
</div>
 
<script>
var cusNumberType =<%=CodeFacade.loadCode2Json("4.13.2")%>; 
var jsConst = window.top.jsConst;

var cusNumber = jsConst.CUS_NUMBER;//监狱号

var userId = jsConst.USER_ID;//当前登陆者

$.parseDone(function() {
	var url = "${ctx}/xxhj/foreignerPeos/findList";
	var params = {};
	
	// 是否只显示当天（0：否；1：是。）
	var onlyToday = "${onlyToday}";
	
	// 初始化查询条件
	if(onlyToday) {
		params["onlyToday"] = onlyToday;
		$("form[id='formId_query']").find("#onlyToday").textbox("setValue", onlyToday);
	}
	
	$('#gridId_foreignerPeos').grid('option', 'postData', params);
	$("#gridId_foreignerPeos").grid("reload", url);
});

var gridId_foreignerPeos_colModelDate = [{
		label : "id",
		name : "id",
		width : "70",
		hidden : true
	}, {
		label : "监狱名称",
		name : "cusNumber",
		width : "95", 
		align : "center",
		formatter : 'convertCode',
		revertCode : true,
		formatoptions : {
			'dataStructure' : 'list',
			'data' : cusNumberType
		}
	}, {
		label : "人员姓名",
		name : "cName",
		width : "75",
		align : "center"
	}, {
		label : "身份证号",
		align : "center",
		name : "cSfzbh",
		width : "85"
	}, {
		label : "登记日期",
		align : "center",
		name : "dDjrq",
		width : "85"
	}, {
		label : "登记事由",
		align : "center",
		name : "cSy",
		width : "85"
	}, /* {
		label : "照片",
		name : "cZp",
		align : "center",
		width : "85",
		formatter : "photoDetil"
	},  {
		label : "审批领导",
		align : "center",
		name : "cSpld",
		width : "85"
	}*/];


/**
 * 查询按钮时间
 */
function query() {
	var formData = $("#formId_query").form("formData");
	$('#gridId_foreignerPeos').grid('option', 'postData', formData);
	$("#gridId_foreignerPeos").grid("reload");
}

/**
 * 重置按钮事件
 */
var reset = function() {
	$("form[id='formId_query']").find("#cName").textbox("setValue", "");
	$("form[id='formId_query']").find("#cSy").textbox("setValue", "");
}

/**
 * 查看照片
 */
 function photoDetil(cellvalue, options, rawObject) {
	var html = "<a href='" + cellvalue + "'; target='_blank' style='color: #4692f0; margin-left: 5px; text-decoration:none'>点击查看</a>";
	
	return html;	
}
</script>

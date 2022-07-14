<%@page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!-- 自定义查询开始 -->

<div style="background-color: #EDEEEE; width: 100%; height: 100%;">
	<cui:form id="queryForm">

			<table class="table">
				<tr>
					<th>民警姓名:</th>
					<td>
						<cui:input id="userName" name="userName" type="text"
							placeholder=""></cui:input>
					</td>
					<td>
						<cui:button cls="cyanbtn" id="s_searchButton" label="查询"
									onClick="search" componentCls="coral-btn-blue" />
						<cui:button id="resetBtn" label="重置" onClick="resetHandler"></cui:button>
					</td>
				</tr>

			</table>

		</cui:form>
		<!-- 自定义查询结束 -->

		<cui:grid id="gridId_camera" singleselect="false" shrinkToFit="false"
				  colModel="gridColModel_camera" fitStyle="fill" datatype="json" url=""
				  pager="true"></cui:grid>

</div>
<cui:dialog id="dialogId_cameraManage" autoOpen="false" iframePanel="true"
	reLoadOnOpen="true" modal="true" resizable="false"></cui:dialog>
	<div id="dailog"></div>
<script>
	var cusNumberType =<%=CodeFacade.loadCode2Json("4.13.2")%>; 
	var jsConst = window.top.jsConst;
	var cusNumber = jsConst.ORG_CODE; //监狱编号
	var userId = jsConst.USER_ID; //登录人

	$.parseDone(function() {
		$("#gridId_camera").grid("reload","${ctx}/rcs/searchJl?cusNumber="+cusNumber);
	});

	function search() {
		var postData = {};
		postData['cusNumber'] = cusNumber;
		var organizeCode = '';
		var userName = $('#userName').val();
		if (userName != "") {
			postData['userName'] = userName;
		}
		$('#gridId_camera').grid('option', 'postData', postData);
		$("#gridId_camera").grid("reload","${ctx}/rcs/searchJl");
	}
	var cmdType = [
		{
			value : "2",
			text : "语音通知"
		},{
			value : "3",
			text : "发送短信"
		}]
	
	var gridColModel_camera = [  {
		label : "id",
		name : "TER_FLAG",
		hidden : true,
		key : true
	},{
		label : "监狱名称",
		name : "CUS_NUMBER",
		align : "center",
		width : 200,
		formatter : 'convertCode',
		revertCode : true,
		formatoptions : {
			'dataStructure' : 'list',
			'data' : cusNumberType
		}
	},{
		label : "警号",
		name : "JOB_NO",
		align : "center",
		width : 200
	}, {
        label : "民警姓名",
        width : 200,
        align : "center",
        name : "USER_NAME"
    },{
        label : "通讯号码",
        width : 200,
        align : "center",
        name : "CELLVALUE"
    }, {
		label : "指令类型",
		width : 200,
		align : "center",
		name : "CMD",
		formatter : 'convertCode',
		revertCode : true,
		formatoptions : {
			'dataStructure' : 'list',
			'data' : cmdType
		}
	}, {
		label : "状态",
		width : 200,
		align : "center",
		name : "TYPE"
	},{
		label : "消息内容",
		width : 200,
		align : "center",
		name : "CONTENT"
	}, {
		label : "发送人",
		width : 200,
		align : "center",
		name : "SEND_USER_NAME"
	}, {
		label : "发送时间",
		width : 200,
		align : "center",
		name : "SEND_TIME"
	}];
</script>
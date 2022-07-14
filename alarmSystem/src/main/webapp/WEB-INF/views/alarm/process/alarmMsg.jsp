<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="sec" tagdir="/WEB-INF/tags/security"%>
<%@ page import="com.cesgroup.prison.common.facade.AuthSystemFacade"%>
<%@ page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<link href="${ctx}/static/cui/cui.css" rel="stylesheet" />
<script src="${ctx}/static/jquery-cui/js/jquery-1.11.3.min.js"></script>
<script src="${ctx}/static/cui/cui.js"></script>

<style>
body, html {
	width: 100%;
	height: 100%;
	background: url("${ctx}/static/images/index-bg.jpg") 0 0 no-repeat;
	background-size: 100% 100%;
	overflow: hidden;
}

.content_msgShow {
	width: 100%;
	height: 90%;
	position: relative;
	top: 50%;
	left: 50%;
	transform: translate(-50%, -50%);
	color: #fff;
	overflow: hidden;
}

.content_msgShow h1 {
	width: 100%;
	text-align: center;
	font-size: 60px;
}

.content_msgShow table {
	width: 100%;
	transform: translate(-50%, -50%);
	top: 40%;
	left: 50%;
	position: relative;
}

.content_msgShow table td {
	font-size: 57px;
	padding: 15px 10px;
	vertical-align: bottom;
}

.content_msgShow table td:first-child {
	padding: 15px 0px 10px 12px;
}

.form_msgShow {
	width: 97%;
	margin: 0 auto;
	height: 100%;
	padding-top: 0;
}

.form_msgShow td .coral-textbox {
	width: 100%;
	height: 65px;
}

.form_msgShow td .coral-isLabel .coral-textbox-default, .form_msgShow td .coral-isLabel .coral-combo-text
	{
	color: #fff;
	font-size: 58px;
	height: 65px;
	line-height: 65px;
}

.form_msgShow table, table tr th, table tr td {
	border: 1px solid #175daf;
	box-shadow: 0 0 10px rgb(35, 53, 144);
}
</style>
<div class="content_msgShow">
	<!-- 报警信息 -->
	<h1>报警信息</h1>
	<cui:form id="formId_msgShow" componentCls="form_msgShow">
		<table>
			<tr>
				<td>地点:</td>
				<td colspan="3">
					<cui:input name="ALARM_ADDRESS"></cui:input>
				</td>
			</tr>
			<tr>
				<td>报警器:</td>
				<td colspan="3">
					<cui:input name="ALERTOR_NAME"></cui:input>
				</td>
			</tr>
			<tr>
				<td>时间:</td>
				<td colspan="3">
					<cui:input name="ARD_ALERT_TIME"></cui:input>
				</td>
			</tr>
			<tr>
				<td>类型:</td>
				<td>
					<cui:combobox name="ARD_TYPE_INDC" data="bjlx_data"></cui:combobox>
				</td>
				<td>等级:</td>
				<td>
					<cui:combobox name="ARD_ALERT_LEVEL_INDC" data="bjdj_data"></cui:combobox>
				</td>
			</tr>
			<tr>
				<td>状态:</td>
				<td>
					<cui:combobox id="alarmOprtnStatus" name="ARD_OPRTN_STTS_INDC" data="czzt_data"></cui:combobox>
				</td>
				<td>接警人:</td>
				<td>
					<cui:input id="alarmReceive" name="ARD_RECEIVE_ALARM_POLICE"></cui:input>
				</td>
			</tr>

		</table>
	</cui:form>
</div>

<script>
	var czzt_data = <%=CodeFacade.loadCode2Json("4.20.26")%>; 
	var bjdj_data = <%=CodeFacade.loadCode2Json("4.20.25")%>; 
	var bjlx_data = <%=CodeFacade.loadCode2Json("4.20.27")%>; 
	$.parseDone(function() {
		$('#formId_msgShow').form("setIsLabel", true);
		getAlarmMsg();
	});
	
	function getAlarmMsg() {
		var url = "${ctx}/alarm/guest/findById.json?id=${id}";
		$.ajax({
			type : "post",
			url : url,
			dataType : "json",
			success : function(data) {
				if (data.success) {
					$('#formId_msgShow').form("load", data.obj);//刷新报警信息数据
					if(data.obj.ARD_OPRTN_STTS_INDC != "3"){
						setTimeout(function(){
							getAlarmMsg();
						}, 5 * 1000);
					} else {
						window.location.href = "${ctx}/xxhj/jndt/toShowScreen?cusNumber=" + data.obj.ARD_CUS_NUMBER;
					}
				} else {
					$.messageQueue({
						message : data.msg,
						cls : "warning",
						iframePanel : true,
						type : "info"
					});
				}
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$.messageQueue({
					message : textStatus,
					cls : "warning",
					iframePanel : true,
					type : "info"
				});
			}
		});
	}
</script>
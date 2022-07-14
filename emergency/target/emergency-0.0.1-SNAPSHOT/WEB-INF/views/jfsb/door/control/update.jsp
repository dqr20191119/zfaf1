<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<%@taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<style>
.form-control {
	width: 100%;
}
</style>

<div style="text-align: center; height: 100%; width: 100%">
	<cui:form id="formId_mjkzq_save" heightStyle="fill">
		<table class="table" style="width: 98%">
			<tr>
				<th>编号：</th>
				<td>
					<cui:input name="dcdIdnty" type="text" required="true"></cui:input></td>
			
				<th>名称：</th>
				<td>
					<cui:input name="dcdName" type="text" required="true"></cui:input></td>
			</tr>
			<tr>
				<th>IP：</th>
				<td>
					<cui:input validType="ip" name="dcdIpAddrs" type="text" required="true"></cui:input></td>
			
				<th>端口：</th>
				<td>
					<cui:input validType="port" name="dcdPort" type="text" required="true"></cui:input></td>
			</tr>
			<tr>
				<th>序列号：</th>
				<td>
					<cui:input name="dcdSn" type="text" required="true"></cui:input></td>
			
				<th>用户名：</th>
				<td>
					<cui:input name="dcdUserName" type="text" required="true"></cui:input></td>
			</tr>
			<tr>
				<th>密码：</th>
				<td>
					<cui:input name="dcdUserPassword" type="text" required="true"></cui:input></td>
				<th>备注：</th>
				<td>
					<cui:input name="dcdRemark" type="text"></cui:input></td>
			</tr>
		</table>
	</cui:form>
	<div class="dialog-buttons" style="margin-bottom: 0px; text-align: center;">
		<cui:button label="提交" text="false" onClick="update"></cui:button>
		<cui:button label="重置" text="false" onClick="clear"></cui:button>
	</div>
</div>



<script>
	var info;
	$.parseDone(function() {
		var url = "${ctx}/doorControl/findById.json?id=${ID}";
		$.ajax({
			type : "post",
			url : url,
			dataType : "json",
			success : function(data) {
				if (data.success) {
					info = data.obj;
					$("#formId_mjkzq_save").form("load", info);
				} else {
					$.messageQueue({ message : data.msg, cls : "warning", iframePanel : true, type : "info" });
				}
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$.messageQueue({ message : textStatus, cls : "warning", iframePanel : true, type : "info" });
			}
		});

	});

	//发送修改请求
	function update() {
		if ($("#formId_mjkzq_save").form("valid")) {
			var formData = $("#formId_mjkzq_save").form("formData");
			var url = '${ctx}/doorControl/update/info.json?id=${ID}';
			$.ajax({
				type : 'post',
				url : url,
				data : formData,
				dataType : 'json',
				success : function(data) {
					if (data.success) {
						$.messageQueue({ message : data.msg, cls : "success", iframePanel : true, type : "info" });
						$("#gridId_doorControl").grid("reload");
						$("#dialogId_doorControl").dialog("close");
					} else {
						$.messageQueue({ message : data.msg, cls : "warning", iframePanel : true, type : "info" });
					}
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					$.messageQueue({ message : textStatus, cls : "warning", iframePanel : true, type : "info" });
				}
			});
		} else {
			alert("请确认输入是否正确！");
		}

	}

	function clear() {
		$("#formId_mjkzq_save").form("load", info);
	}
</script>
</html>
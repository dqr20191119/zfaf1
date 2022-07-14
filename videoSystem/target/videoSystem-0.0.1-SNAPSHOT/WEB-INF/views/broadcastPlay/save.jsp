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
	<cui:form id="formId_gb_save" heightStyle="fill">
		<table class="table" style="width: 98%">
			<tr>
				<th>voice：</th>
				<td>
					<cui:input name="voice" componentCls="form-control"></cui:input>
				</td>
				<th>voiceName：</th>
				<td>
					<cui:input name="voiceName" componentCls="form-control"></cui:input>
				</td>
			</tr>
			<tr>
				<th>speed：</th>
				<td>
					<cui:input name="speed" componentCls="form-control" required="true"></cui:input>
				</td>
				<th>volume：</th>
				<td>
					<cui:input name="volume" componentCls="form-control"></cui:input>
				</td>
			</tr>
			<tr>
				<th>playMode：</th>
				<td>
					<cui:input name="playMode" componentCls="form-control"></cui:input>
				</td>
				<th>vol：</th>
				<td>
					<cui:input name="vol" componentCls="form-control"></cui:input>
				</td>
			</tr>
			<tr>
				<th>dialNos：</th>
				<td>
					<cui:input name="dialNos" componentCls="form-control"></cui:input>
				</td>
				<th>text：</th>
				<td>
					<cui:input name="text" componentCls="form-control"></cui:input>
				</td>
			</tr>
		</table>

	</cui:form>
	<div class="dialog-buttons" style="margin-bottom: 0px; text-align: center;">
		<cui:button label="提交" text="false" onClick="add"></cui:button>
		<cui:button label="重置" text="false" onClick="clear"></cui:button>
	</div>
</div>



<script>
	$.parseDone(function() {
		//部门数据请求
		
	});

	//发送添加请求
	function add() {
		if ($("#formId_gb_save").form("valid")) {
			var formData = $("#formId_gb_save").form("formData");
			$.ajax({
				type : 'post',
				url : '${ctx}/broadcastPlay/save.json',
				data : formData,
				dataType : 'json',
				success : function(data) {
					if (data.success) {
						$.messageQueue({ message : data.msg, cls : "success", iframePanel : true, type : "info" });
						$("#gridId_broadcastPlay").grid("reload");
						$("#dialogId_broadcastPlay").dialog("close");
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
		$("#formId_gb_save").form("reset");
	}
</script>
</html>
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
	<cui:form id="formId_updateInfo" heightStyle="fill">
		<table class="table" style="width: 98%">
			<tr>
				<th>报警类型：</th>
				<td>
					<cui:combobox  name="altTypeId" componentCls="form-control" data="typeData" readonly="true"></cui:combobox>
				</td>
			</tr>
			<tr>
				<th>报警等级：</th>
				<td>
					<cui:combobox name="altLevel" componentCls="form-control" data="levelData" required="true"></cui:combobox>
				</td>
			</tr>
		</table>
	</cui:form>
	<div class="dialog-buttons" style="margin-bottom: 0px; text-align: center;">
		<cui:button label="提交" text="false" onClick="update"></cui:button>
		<cui:button label="重置" text="false" onClick="reset"></cui:button>
	</div>
</div>



<script>
	var info;
	$.parseDone(function() {
		$.ajax({
			type : "post",
			url : "${ctx}/alarmTypeAndLev/findById.json?id=${ID}",
			dataType : "json",
			success : function(data) {
				if (data.success) {
					info = data.obj;
					$("#formId_updateInfo").form("load", info);
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
		if ($("#formId_updateInfo").form("valid")) {
			var formData = $("#formId_updateInfo").form("formData");
			$.ajax({
				type : 'post',
				url : '${ctx}/alarmTypeAndLev/updateInfo.json?id=${ID}',
				data : formData,
				dataType : 'json',
				success : function(data) {
					if (data.success) {
						$.messageQueue({ message : "修改成功", cls : "success", iframePanel : true, type : "info" });
						$("#gridId_alarmTypeAndLev").grid("reload");
						$("#dialogId_alarmTypeAndLev").dialog("close");
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

	function reset() {
		$("#formId_updateInfo").form("load", info);
	}
</script>
</html>
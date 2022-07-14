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
	<cui:form id="formId_dpya_update" heightStyle="fill">
		<table class="table" style="width: 98%">
			<tr>
				<th>厂家：</th>
				<td>
					<cui:combobox  name="splManufacturersId" componentCls="form-control" data="dpya_changjiaData" required="true"></cui:combobox>
				</td>
			</tr>
			<tr>
				<th>预案编号：</th>
				<td>
					<cui:input name="splPlanIndc" componentCls="form-control" readonly="true"></cui:input>
				</td>
			</tr>
			<tr>
				<th>预案名称：</th>
				<td>
					<cui:input name="splPlanName" componentCls="form-control" required="true"></cui:input>
				</td>

			</tr>
			<tr>
				<th>状态：</th>
				<td>
					<cui:combobox name="splStatusIndc" componentCls="form-control" data="dpya_sttsData" required="true"></cui:combobox>
				</td>
			</tr>
			<tr>
				<th>信号源动态：</th>
				<td>
					<cui:combobox name="splIsDynamic" componentCls="form-control" data="dpya_isDynamic" required="true"></cui:combobox>
				</td>
			</tr>
			<tr>
				<th>备注：</th>
				<td>
					<cui:input name="splRemark" componentCls="form-control"></cui:input>
				</td>
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
		var url = "${ctx}/screenPlan/findById.json?id=${ID}";
		$.ajax({
			type : "post",
			url : url,
			dataType : "json",
			success : function(data) {
				if (data.success) {
					info = data.obj;
					$("#formId_dpya_update").form("load", info);
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
		if ($("#formId_dpya_update").form("valid")) {
			var formData = $("#formId_dpya_update").form("formData");
			var url = '${ctx}/screenPlan/update/info.json?id=${ID}';
			$.ajax({
				type : 'post',
				url : url,
				data : formData,
				dataType : 'json',
				success : function(data) {
					if (data.success) {
						$.messageQueue({ message : data.msg, cls : "success", iframePanel : true, type : "info" });
						$("#gridId_screenPlan").grid("reload");
						$("#dialogId_screenPlan").dialog("close");
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
		$("#formId_dpya_update").form("load", info);
	}
</script>
</html>
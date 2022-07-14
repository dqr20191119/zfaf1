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
	<cui:form id="formId_broadcastFile_update" heightStyle="fill">
		<!-- 主键ID -->
		<cui:input name="id" type="hidden"></cui:input>
		<!-- 其它属性 -->
		<table class="table" style="width: 98%">
			<tr>
				<th>编号：</th>
				<td>
					<cui:input name="bfdIdnty" componentCls="form-control" readonly="true"></cui:input>
				</td>
			</tr>
			<tr>
				<th>名称：</th>
				<td>
					<cui:input name="bfdName" componentCls="form-control" required="true"></cui:input>
				</td>
			</tr>
			<tr>
				<th>备注：</th>
				<td>
					<cui:textarea name="bfdRemark" componentCls="form-control"></cui:textarea>
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
		setTimeout(function () {
			var url = "${ctx}/broadcastFile/queryById.json?id=${id}";
			$.ajax({
				type : "post",
				url : url,
				dataType : "json",
				success : function(data) {
					debugger;
					if (data.success) {
						info = data.obj;
						$("#formId_broadcastFile_update").form("load", info);
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
		}, 500);
	});

	//发送修改请求
	function update() {
		if ($("#formId_broadcastFile_update").form("valid")) {
			var formData = $("#formId_broadcastFile_update").form("formData");
			var url = '${ctx}/broadcastFile/saveOrUpdate.json';
			$.ajax({
				type : 'post',
				url : url,
				data : formData,
				dataType : 'json',
				success : function(data) {
					if (data.success) {
						$.messageQueue({
							message : data.msg,
							cls : "success",
							iframePanel : true,
							type : "info"
						});
						$("#gridId_broadcastFile").grid("reload");
						$("#dialogId_broadcastFile").dialog("close");
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
						message : XMLHttpRequest,
						cls : "warning",
						iframePanel : true,
						type : "info"
					});
				}
			});
		} else {
			alert("请确认输入是否正确！");
		}

	}

	function reset() {
		$("#formId_broadcastFile_update").form("load", info);
	}
</script>
</html>
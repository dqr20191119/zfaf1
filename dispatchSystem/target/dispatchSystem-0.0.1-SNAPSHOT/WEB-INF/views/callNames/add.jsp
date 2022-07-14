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
	<cui:form id="formId_fqdm_save" heightStyle="fill">
		<table class="table" style="width: 98%">
			<tr>
				<th>点名时长：</th>
				<td>
					<cui:input validType="naturalnumber" id="cnrTimeLag" name="cnrTimeLag" required="true" componentCls="form-control"></cui:input>
				</td>
			</tr>
		</table>
	</cui:form>
	<div class="dialog-buttons" style="margin-bottom: 0px; text-align: center;">
		<cui:button label="提交" text="false" onClick="add_fqdm"></cui:button>
		<cui:button label="重置" text="false" onClick="clear_fqdm"></cui:button>
	</div>
</div>



<script>
	//发送添加请求
	function add_fqdm() {
		if ($("#formId_fqdm_save").form("valid")) {

			var formData = $("#formId_fqdm_save").form("formData");

			if (formData['cnrTimeLag'] == '0') {
				$.messageQueue({
					message : "点名时长不能为0",
					cls : "warning",
					iframePanel : true,
					type : "info"
				});
				return;
			}
			$.ajax({
				type : 'post',
				url : '${ctx}/callNames/beginRollcall.json',
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
						$("#gridId_callName").grid("reload");
						$("#dialogId_callName").dialog("close");
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
		} else {
			alert("请确认输入是否正确！");
		}

	}

	function clear_fqdm() {
		$("#formId_fqdm_save").form("reset");
	}
</script>
</html>
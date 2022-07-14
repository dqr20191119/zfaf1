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
	<cui:form id="formId_gzlx" heightStyle="fill">
		<table class="table" style="width: 98%">
			<tr>
				<th>故障类型：</th>
				<td>
					<cui:input componentCls="form-control" name="dftFaultName" required="true"></cui:input>
				</td>
			</tr>
			<tr>
				<td>
					<cui:radio componentCls="radio-md" name="dftSttsIndc" value="1" label="启用" checked="true"></cui:radio>
				</td>
				<td>
					<cui:radio componentCls="radio-md" name="dftSttsIndc" value="0" label="停用"></cui:radio>
				</td>
			</tr>
		</table>
	</cui:form>
	<div class="dialog-buttons" style="margin-bottom: 0px; text-align: center;">
		<cui:button label="提交" text="false" onClick="submit"></cui:button>
		<cui:button label="取消" text="false" onClick="cancel"></cui:button>
	</div>
</div>



<script>
	//发送添加请求
	function submit() {
		if ($("#formId_gzlx").form("valid")) {
			var formData = $("#formId_gzlx").form("formData");
			formData["dftTypeClassify"] = "1";
			var url = "${ctx}/deviceFaultType/inster.json?type=1";
			$.ajax({
				type : "post",
				url : url,
				data : formData,
				dataType : "json",
				success : function(data) {
					if (data.success) {
						$.messageQueue({ message : "保存成功", cls : "success", iframePanel : true, type : "info" });
						$("#gridId_gzlx").grid("reload");
						$("#dialogId_gzlx").dialog("close");
						$("#combId_gzlx_query").combobox( "reload");
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

	function cancel() {
		$("#dialogId_gzlx").dialog("close");
	}
</script>
</html>
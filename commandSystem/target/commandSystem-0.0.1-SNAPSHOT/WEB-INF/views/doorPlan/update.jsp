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
	<cui:form id="formId_mjya_update" heightStyle="fill">
		<table class="table" style="width: 98%">
			<tr>
				<th>名称：</th>
				<td>
					<cui:input name="pdoPlanName" componentCls="form-control" required="true"></cui:input>
				</td>
			</tr>
			<tr>
				<th>启用时间：</th>
				<td>
					<cui:datepicker id="off_timepicker" name="pdoOffTime" model="timepicker" componentCls="form-control"></cui:datepicker>
				</td>
			</tr>
			<tr>
				<th>有效时限：</th>
				<td>
					 <cui:input validType="naturalnumber" id="pdoTimeLimit" name="pdoTimeLimit" required="true" componentCls="form-control"></cui:input>
				</td>
			</tr>
			<tr>
				<th>备注：</th>
				<td>
					<cui:input name="pdoRemark" componentCls="form-control"></cui:input>
				</td>
			</tr>
		</table>
	</cui:form>
	<div class="dialog-buttons" style="margin-bottom: 0px; text-align: center;">
		<cui:button label="提交" text="false" onClick="update_mjya"></cui:button>
		<cui:button label="重置" text="false" onClick="clear_mjya"></cui:button>
	</div>
</div>

<script>
	var info;
	$.parseDone(function() {
		var url = "${ctx}/door/plan/findById.json?id=${ID}";
		$.ajax({
			type : "post",
			url : url,
			dataType : "json",
			success : function(data) {
				if (data.success) {
					info = data.obj;
					$("#formId_mjya_update").form("load", info);
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
	function update_mjya() {
		if ($("#formId_mjya_update").form("valid")) {
			var formData = $("#formId_mjya_update").form("formData");
			if(formData['pdoTimeLimit'] == '0'){
				$.messageQueue({ message : "有效时间不能为0", cls : "warning", iframePanel : true, type : "info" }); 
				return;
			}
			var url = '${ctx}/door/plan/updateInfo.json?id=${ID}';
			$.ajax({
				type : 'post',
				url : url,
				data : formData,
				dataType : 'json',
				success : function(data) {
					if (data.success) {
						$.messageQueue({ message : data.msg, cls : "success", iframePanel : true, type : "info" });
						$("#gridId_doorPlan").grid("reload");
						$("#dialogId_doorPlan").dialog("close");
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

	function clear_mjya() {
		$("#formId_mjya_update").form("load", info);
	}
</script>
</html>
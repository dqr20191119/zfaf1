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
	<cui:form id="formId_wxjl_update" heightStyle="fill">
		<table class="table" style="width: 98%">

			<tr>
				<th>设备类型：</th>
				<td>
					<cui:combobox name="dmrDeviceType" componentCls="form-control" data="deviceTypeDate" readonly="true"></cui:combobox>
				</td>
				<th>设备名称：</th>
				<td>
					<cui:input name="dmrDeviceName" componentCls="form-control" readonly="true"></cui:input>
				</td>
			</tr>
			<tr>
				<th>维修人：</th>
				<td>
					<cui:input name="dmrFaultMaintainer" componentCls="form-control" required="true"></cui:input>
				</td>
				<th>维修时间：</th>
				<td>
				  	<cui:datepicker name="dmrFaultMaintainTime" componentCls="form-control" required="true"></cui:datepicker>  
				</td>
			</tr>
			<tr>
				<th>维修情况：</th>
				<td colspan="3">
					<cui:textarea name="dmrFaultContent" componentCls="form-control" required="true"></cui:textarea>
				</td>
			</tr>
		</table>
	</cui:form>
	<div class="dialog-buttons" style="margin-bottom: 0px; text-align: center;">
		<cui:button   label="提交" onClick="submit_wxjl"></cui:button>
		<cui:button   label="重置" onClick="reset_wxjl_"></cui:button>
	</div>
</div>
<script>
	var info = null;
	$.parseDone(function() {
		var url = "${ctx}/deviceMaintain/record/findById.json?id=${ID}";
		$.ajax({
			type : "post",
			url : url,
			dataType : "json",
			success : function(data) {
				if (data.success) {
					info = data.obj;
					$("#formId_wxjl_update").form("load", info);
				} else {
					$.messageQueue({ message : data.msg, cls : "warning", iframePanel : true, type : "info" });
				}
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$.messageQueue({ message : textStatus, cls : "warning", iframePanel : true, type : "info" });
			}
		});

	});
	function reset_wxjl_() {
		$("#formId_wxjl_update").form("load", info);
	}	
	
	//发送修改请求
	/* function submit_wxjl() {
		if ($("#formId_wxjl_update").form("valid")) {
			var formData = $("#formId_wxjl_update").form("formData");
			debugger;
			$.ajax({
				type : "post",
				url :  "${ctx}/deviceMaintain/record/update/info.json",
				data : formData,
				dataType : 'json',
			 	contentType : 'application/json',
				success : function(data) {
					if (data.success) {
						$.messageQueue({ message : "修改成功", cls : "success", iframePanel : true, type : "info" });
						$("#gridId_record").grid("reload");
						$("#dialogId_record").dialog("close");
					} else {
						$.messageQueue({ message : data.msg, cls : "warning", iframePanel : true, type : "info" });
					}
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					$.messageQueue({ message : textStatus, cls : "warning", iframePanel : true, type : "info" });
				}
			});
		} else {
			$.messageQueue({ message : "请确认输入是否正确！", cls : "warning", iframePanel : true, type : "info" });
		}

	} */
	
	//发送修改请求
	function submit_wxjl() {
		if ($("#formId_wxjl_update").form("valid")) {
			var formData = $("#formId_wxjl_update").form("formData");
			$.ajax({
				type : 'post',
				url :  "${ctx}/deviceMaintain/record/update/info.json?id=${ID}",
				data : formData,
				dataType : 'json',
				success : function(data) {
					if (data.success) {
						$.messageQueue({ message : "修改成功", cls : "success", iframePanel : true, type : "info" });
						$("#gridId_record").grid("reload");
						$("#dialogId_record").dialog("close");
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
</script>
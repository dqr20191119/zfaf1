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
	<cui:form id="formId_gznr_udp" heightStyle="fill">
		<table class="table" style="width: 98%">
			<tr>
				<td>故障类型：</td>
				<td>
					<cui:combobox id="combId_type" name="dftParentId" componentCls="form-control" required="true"></cui:combobox>
				</td>
			</tr>
			<tr>
				<td>故障内容：</td>
				<td>
					<cui:input id="combId_name" componentCls="form-control" name="dftFaultName" required="true"></cui:input>
				</td>
			</tr>
			<tr>
				<td>维修部门：</td>
				<td>
					<cui:combobox id="combId_maintainDprtmnt" name="fdrMaintainDprtmntId" componentCls="form-control" onSelect="onComboSelect" required="true"></cui:combobox>
				</td>
			</tr>
			<tr>
				<td>协作部门：</td>
				<td>
					<cui:combobox id="combId_helpDprtmnt" name="fdrHelpDprtmntId" componentCls="form-control"></cui:combobox>
				</td>
			</tr>
			<tr>
				<td>
					<cui:radio id="radioId_1" componentCls="radio-md" name="dftSttsIndc" value="1" label="启用"></cui:radio>
				</td>
				<td>
					<cui:radio id="radioId_2" componentCls="radio-md" name="dftSttsIndc" value="0" label="停用"></cui:radio>
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
	var info = null;

	$.parseDone(function() {
		$("#combId_type").combobox( "reload", "${ctx}/deviceFaultType/seachComboData.json?cusNumber=" + cusNumber + "&typeClassify=1&sttsIndc=1");
		//维修部门数据请求
		$("#combId_maintainDprtmnt").combobox( "reload", "${ctx}/common/authsystem/findAllChildrenOrgByJyKeyForCombobox.json?cusNumber=" + cusNumber);
		//维修协助部门请求
		$("#combId_helpDprtmnt").combobox( "reload", "${ctx}/common/authsystem/findAllChildrenOrgByJyKeyForCombobox.json?cusNumber=" + cusNumber);

		$.ajax({
			type : "post",
			url : "${ctx}/deviceFaultType/findById.json?id=${ID}&type=2",
			dataType : "json",
			success : function(data) {
				if (data.success) {
					info = data.obj;
					setData(info);
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
		if ($("#formId_gznr_udp").form("valid")) {
			var formData = $("#formId_gznr_udp").form("formData");
			formData["fdrMaintainDprtmnt"] = $("#combId_maintainDprtmnt") .combobox("getText");
			if (formData.fdrHelpDprtmntId != undefined && formData.fdrHelpDprtmntId != "" && formData.fdrHelpDprtmntId != 'undefined') {
				formData["fdrHelpDprtmnt"] = $("#combId_helpDprtmnt").combobox( "getText");
			}
			var url = "${ctx}/deviceFaultType/updateDeviceFaultType.json?id=${ID}&type=2";
			$.ajax({
				type : "post",
				url : url,
				data : formData,
				dataType : "json",
				success : function(data) {
					if (data.success) {
						$.messageQueue({ message : "修改成功", cls : "success", iframePanel : true, type : "info" });
						$("#gridId_gznr").grid("reload");
						$("#dialogId_gzlx").dialog("close");
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
		setData(info);
	}

	function setData(data) {
		$("#combId_type").combobox("setValue", data.dftParentId);
		$("#combId_maintainDprtmnt").combobox("setValue", data.fdrMaintainDprtmntId);
		$("#combId_helpDprtmnt").combobox("setValue", data.fdrHelpDprtmntId);
		$("#combId_maintainDprtmnt").combobox("setText", data.fdrMaintainDprtmnt);
		$("#combId_helpDprtmnt").combobox("setText", data.fdrHelpDprtmnt);
		$("#combId_name").textbox("setText", data.dftFaultName);
		if (data.dftSttsIndc == "1") {
			$("#radioId_1").radio("check");
		} else {
			$("#radioId_2").radio("check");
		}
	}
</script>
</html>
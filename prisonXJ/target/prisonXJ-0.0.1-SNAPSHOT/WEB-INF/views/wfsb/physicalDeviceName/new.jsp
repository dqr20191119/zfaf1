<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<style>
.form-control {
	width: 100%;
}
</style>

<center>
	<cui:form id="formId_physicalDeviceNameAdd">
		<cui:input type="hidden" required="true" id="pdnCusNumber"
			name="pdnCusNumber" value="" componentCls="form-control"></cui:input>
		<cui:input type="hidden" required="true" id="pdnCrteUserId"
			name="pdnCrteUserId" value="" componentCls="form-control"></cui:input>
		<cui:input type="hidden" required="true" id="pdnUpdtUserId"
			name="pdnUpdtUserId" value="" componentCls="form-control"></cui:input>
		<div style="display: none;">
			<cui:datepicker required="true" id="pdnCrteTime" name="pdnCrteTime"
				dateFormat="yyyy-MM-dd HH:mm:ss" componentCls="form-control"></cui:datepicker>
			<cui:datepicker required="true" id="pdnUpdtTime" name="pdnUpdtTime"
				dateFormat="yyyy-MM-dd HH:mm:ss" componentCls="form-control"></cui:datepicker>
		</div>
		<table class="table table-fixed" style="width: 100%;">
			<tr>
				<td width="40%"><label>设施编号：</label></td>
				<td width="60%"><cui:input required="true"
						name="pdnDeviceIdnty" componentCls="form-control"></cui:input></td>
			</tr>
			<tr>
				<td width="40%"><label>设施名称：</label></td>
				<td width="60%"><cui:input required="true" name="pdnDeviceName"
						componentCls="form-control"></cui:input></td>
			</tr>
			<tr>
				<td width="40%"><label>备注：</label></td>
				<td width="60%"><cui:input name="pdnRemark"
						componentCls="form-control"></cui:input></td>
			</tr>
		</table>
	</cui:form>
</center>
<div class="dialog-buttons">
	<cui:button label="重置" onClick="reset"></cui:button>
	<cui:button label="保存" onClick="save"></cui:button>
</div>
<script>
	var jsConst = window.top.jsConst;
	var cusNumber = jsConst.ORG_CODE; //监狱编号
	var userId = jsConst.USER_ID; //登录人
	var userLevel = jsConst.USER_LEVEL; //用户等级

	$.parseDone(function() {
		initFormData();
	});

	function initFormData() {
		$("#pdnCrteUserId").textbox("setValue", userId);
		$("#pdnUpdtUserId").textbox("setValue", userId);
		$('#pdnCrteTime').datepicker('setDate', new Date());
		$('#pdnUpdtTime').datepicker('setDate', new Date());
		if (userLevel == 1) {//省局
			$("#pdnCusNumber").textbox("setValue", '0'); //0 代表通用
		} else {
			$("#pdnCusNumber").textbox("setValue", cusNumber);
		}
	}

	function reset() {
		$("#formId_physicalDeviceNameAdd").form("reset");
		initFormData();
	}

	function save() {
		if ($("#formId_physicalDeviceNameAdd").form("valid")) {
			var formData = $("#formId_physicalDeviceNameAdd").form("formData");
			var ur = '${ctx}/wfsb/physicalDeviceName/create.json';
			
			$.ajax({
				type : 'post',
				url : ur,
				data : formData,
				dataType : 'json',
				success : function(data) {
					if (data.exception == undefined) {
						$.message({
							message : "保存成功",
							cls : "success",
							iframePanel : true
						});
						$("#gridId_physicalDeviceName").grid("reload");
						$("#dialogId_physicalDeviceNameManage").dialog(
								"close");
					} else {
						$.message({
							iframePanel : true,
							message : data.exception.cause.message,
							type : "danger"
						});
					}

				},
				error : function(XMLHttpRequest, textStatus,
						errorThrown) {
					$.alert({
						message : textStatus,
						title : "信息提示",
						iframePanel : true
					});
				}
			});

		} else {
			$.alert({
				message : "请确认输入是否正确！",
				title : "信息提示",
				iframePanel : true
			});
		}
	}
</script>
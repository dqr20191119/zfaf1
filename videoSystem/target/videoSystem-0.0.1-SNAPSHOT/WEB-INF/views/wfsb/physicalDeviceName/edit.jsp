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
	<cui:form id="formId_physicalDeviceNameEdit">
		<cui:input type="hidden" required="true" name="id"
			value="${model.id}" componentCls="form-control"></cui:input>
		<cui:input type="hidden" required="true" id="pdnUpdtUserId" name="pdnUpdtUserId"
			value="" componentCls="form-control"></cui:input>
		<div style="display: none;">
			<cui:datepicker required="true" id="pdnUpdtTime" name="pdnUpdtTime"
				dateFormat="yyyy-MM-dd HH:mm:ss" componentCls="form-control"></cui:datepicker>
		</div>
		<table class="table table-fixed" style="width: 100%;">
			<tr>
				<td width="40%"><label>设施编号：</label></td>
				<td width="60%"><cui:input required="true" name="pdnDeviceIdnty" value="${model.pdnDeviceIdnty }" componentCls="form-control"></cui:input></td>
			</tr>
			<tr>
				<td width="40%"><label>设施名称：</label></td>
				<td width="60%"><cui:input required="true" name="pdnDeviceName" value="${model.pdnDeviceName }"  componentCls="form-control"></cui:input></td>
			</tr>
			<tr>
				<td width="40%"><label>备注：</label></td>
				<td width="60%"><cui:input name="pdnRemark"  value="${model.pdnRemark }" componentCls="form-control"></cui:input></td>
			</tr>
		</table>
	</cui:form>
</center>
<div class="dialog-buttons">
	<cui:button label="重置" onClick="reset"></cui:button>
	<cui:button label="修改" onClick="f_edit"></cui:button>
</div>
<script>
	var jsConst=window.top.jsConst;
	var cusNumber=jsConst.ORG_CODE							//监狱编号
	var userId=jsConst.USER_ID					//登录人
	$.parseDone(function() {
		initFormData();
	});
	
	function initFormData(){
		$("#pdnUpdtuserId").textbox("setValue",userId);
		$('#pdnUpdtTime').datepicker('setDate', new Date());
	}
	
	function reset() {
		$("#formId_physicalDeviceNameEdit").form("reset");
		initFormData();
	}
	
	function f_edit() {
		if ($("#formId_physicalDeviceNameEdit").form("valid")) {
			var formData = $("#formId_physicalDeviceNameEdit").form("formData");
			var ur = '${ctx}/wfsb/physicalDeviceName/updatePart.json';
			$.ajax({
				type : 'post',
				url : ur,
				data : formData,
				dataType : 'json',
				success : function(data) {
					if (data.success) {
						$.message({
							message : "修改成功",
							cls : "success",
							iframePanel:true
						});
						$("#gridId_physicalDeviceName").grid("reload");
						$("#dialogId_physicalDeviceNameManage").dialog("close");
					} else {
						$.message({
							iframePanel:true,
							message : data.msg,
							type : "danger"
						});
					}

				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					$.alert({
						message:textStatus,
						title:"信息提示",
						iframePanel:true
					});
				}
			});

		} else {
			$.alert({
				message:"请确认输入是否正确！",
				title:"信息提示",
				iframePanel:true
			});
		}
	}
</script>
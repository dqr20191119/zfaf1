<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<%@page import="com.cesgroup.prison.broadcastPlan.common.BroadcastPlanCommom"%>
<%@taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<style>
.form-control {
	width: 100%;
}
</style>

<div style="text-align: center; height: 100%; width: 100%">
	<cui:form id="formId_gb_save" heightStyle="fill">
		<table class="table" style="width: 98%">
			<tr>
				<th>预案：</th>
				<td>
					<cui:input id="planName_" readonly="true"></cui:input>
				</td>
			</tr>
			<tr>
				<th>关联的广播预案：</th>
				<td>
					<cui:combobox id="bprBroadcastPlanId" name="bprBroadcastPlanId" componentCls="form-control" data="gb_plan" ></cui:combobox>
				</td>
			</tr>
		</table>
	</cui:form>
	<div class="dialog-buttons" style="margin-bottom: 0px; text-align: center;">
		<cui:button label="提交" text="false" onClick="add"></cui:button>
		<cui:button label="重置" text="false" onClick="clear"></cui:button>
	</div>
</div>

<script>
	var planId = "${planId}";
	var planName = "${planName}";
	var gb_plan =<%=BroadcastPlanCommom.broadcastPlanNameCommbox()%>
	$.parseDone(function() {
		$("#planName_").textbox("setText", planName);
		// 部门数据请求
		// $("#combId_dprtmnt").combobox( "reload", "${ctx}/common/authsystem/findAllChildrenOrgByJyKeyForCombobox.json?cusNumber=" + cusNumber);
		// 区域数据请求
		//$("#combId_area").combotree("tree").tree("reload", "${ctx}/common/areadevice/findForCombotree.json?cusNumber=" + cusNumber +"&deviceType=0");
		setTimeout(function () {
			var url = "${ctx}/plan/findByPlanId.json?bprPlanId=${planId}";
			$.ajax({
				type : "post",
				url : url,
				dataType : "json",
				success : function(data) {
					if (data.success) {
						info = data.obj;
						$("#formId_gb_save").form("load", info);
                        $("form[id='formId_gb_save']").find("#planName_").textbox({disabled: true});
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

	
	//发送添加请求
	function add() {
			var formData = $("#formId_gb_save").form("formData");
           formData["bprPlanId"] =planId;
			$.ajax({
				type : 'post',
				url : '${ctx}/plan/saveBroadcastPlan.json',
				data : formData,
				dataType : 'json',
				success : function(data) {
					if (data.success) {
                        $("#dialogId_plan").dialog("close");
						$.messageQueue({ message : data.msg, cls : "success", iframePanel : true, type : "info" });
					} else {
						$.messageQueue({ message : data.msg, cls : "warning", iframePanel : true, type : "info" });
					}
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					$.messageQueue({ message : textStatus, cls : "warning", iframePanel : true, type : "info" });
				}
			});
	}

	function clear() {
        $("form[id='formId_gb_save']").find("#bprBroadcastPlanId").combobox("setValue", "");
	}
</script>
</html>
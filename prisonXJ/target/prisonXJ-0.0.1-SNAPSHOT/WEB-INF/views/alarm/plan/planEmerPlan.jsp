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
	<cui:form id="formId_emerPlan_edit" heightStyle="fill">
		<table class="table" style="width: 98%">
			<tr>
				<th>报警预案：</th>
				<td>
					<cui:input id="alarmPlanName" name="alarmPlanName" type="text" componentCls="form-control"></cui:input>
					<cui:input id="alarmPlanId" name="alarmPlanId" type="hidden"></cui:input>
				</td>
			</tr>
			<tr>
				<th>应急预案：</th>
				<td>
					<cui:combobox id="emerPlanId" name="emerPlanId" componentCls="form-control"></cui:combobox>
				</td>
			</tr>
		</table>
	</cui:form>
	<div class="dialog-buttons" style="margin-bottom: 0px; text-align: center;">
		<cui:button label="提交" text="false" onClick="saveOrUpdate"></cui:button>
		<cui:button label="重置" text="false" onClick="reset"></cui:button>
	</div>
</div>

<script>
	$.parseDone(function() {
		// 初始化报警预案及应急预案关联关系数据
		initAlarmEmerPlan();
	});

	/**
	 * 初始化报警预案及应急预案关联关系数据
	 */
	function initAlarmEmerPlan() {
		var alarmPlanId = '${alarmPlanId}';// 报警预案编号
		var url = "";
		var params = {};

		// 应急预案下拉框数据加载
		$("form[id='formId_emerPlan_edit']").find("#emerPlanId").combobox("clear");
		$("form[id='formId_emerPlan_edit']").find("#emerPlanId").combobox("reload", "${ctx}/emergency/preplanManage/queryAllForCombobox.json");

		// 报警预案数据初始化
		url = "${ctx}/plan/findPlanById.json";
		params['id'] = alarmPlanId;
		var callBack = function(data) {
			if (data.success) {
				if(data.obj) {
					$("form[id='formId_emerPlan_edit']").find("#alarmPlanId").textbox("setValue", data.obj.id);
					$("form[id='formId_emerPlan_edit']").find("#alarmPlanName").textbox("setValue", data.obj.pmaPlanName);
					$("form[id='formId_emerPlan_edit']").find("#alarmPlanName").textbox({disabled: true});
				}
			} else {
				$.messageQueue({ message : data.msg, cls : "warning", iframePanel : true, type : "info" });
			}
		};
		ajaxTodo(url, params, callBack);

		// 报警预案关联的应急预案赋值
		url = "${ctx}/plan/queryAlarmEmerPlanRltnByAlarmPlanId.json";
		delete params['id'];// 删除params中的key为id的元素
		params['alarmPlanId'] = alarmPlanId;
		var callBack = function(data) {
			if (data.success) {
				if(data.obj) {
					$("form[id='formId_emerPlan_edit']").find("#emerPlanId").combobox("setValue", data.obj.emerPlanId);
				}
			} else {
				$.messageQueue({ message : data.msg, cls : "warning", iframePanel : true, type : "info" });
			}
		};
		ajaxTodo(url, params, callBack);
	}

	/**
	 * 保存或更新
	 */
	function saveOrUpdate() {
		var validFlag = $("#formId_emerPlan_edit").form("valid");
		if (!validFlag) {
			return;
		}

		var url = "${ctx}/plan/saveOrUpdateAlarmEmerPlanRltn.json";
		var formData = $("#formId_emerPlan_edit").form("formData");

		$.loading({text: "正在处理中，请稍后..."});

		var callBack = function (data) {
			if (data.success) {
				$.loading("hide");
				$("#dialogId_plan").dialog("close");
				$.message({message: "操作成功！", cls: "success"});
			} else {
				$.loading("hide");
				$.messageQueue({message: data.msg, cls: "warning", iframePanel: true, type: "info"});
			}
		};
		ajaxTodo(url, formData, callBack);
	}

	/**
	 * 重置
	 */
	function reset() {
		$("form[id='formId_emerPlan_edit']").find("#emerPlanId").combobox("setValue", "");
	}
</script>
</html>
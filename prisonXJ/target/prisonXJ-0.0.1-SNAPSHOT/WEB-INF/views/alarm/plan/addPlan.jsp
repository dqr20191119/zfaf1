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

<cui:form id="formId_addPlan" style="text-align: center;">
	<table class="table" style="width: 98%;">
		<tr>
			<th>预案名称：</th>
			<td>
				<cui:input id="inpId_planName" name="pmaPlanName" componentCls="form-control" required="true"></cui:input>
			</td>
		</tr>
		<tr>
			<th>备注：</th>
			<td>
				<cui:textarea name="pmaRemark" componentCls="form-control"></cui:textarea>
			</td>
		</tr>
	</table>

	<table class="table" style="width: 80%;">
		<tr>
			<td>
				<cui:checkbox id="checkId_1" label="关联门禁" value="1" onChange="checkChange(1)"></cui:checkbox>
			</td>
			<td colspan="2">
				<cui:radiolist id="radiolist1" repeatLayout="flow" disabled="true" column="2" data="radiolistdata"></cui:radiolist>
			</td>

		</tr>
		<tr>
			<td>
				<cui:checkbox id="checkId_2" label="关联摄像" value="2" onChange="checkChange(2)"></cui:checkbox>
			</td>
			<td colspan="2">
				<cui:radiolist id="radiolist2" repeatLayout="flow" disabled="true" column="2" data="radiolistdata"></cui:radiolist>
			</td>
		</tr>
		<tr>
			<td>
				<cui:checkbox id="checkId_3" label="关联广播" value="3" onChange="checkChange(3)"></cui:checkbox>
			</td>
			<td colspan="2">
				<cui:radiolist id="radiolist3" repeatLayout="flow" disabled="true" column="2" data="radiolistdata"></cui:radiolist>
			</td>
		<tr>
		<tr>
			<td>
				<cui:checkbox id="checkId_4" label="关联大屏" value="4" onChange="checkChange(4)"></cui:checkbox>
			</td>
			<td colspan="2">
				<cui:radiolist id="radiolist4" repeatLayout="flow" disabled="true" column="2" data="radiolistdata"></cui:radiolist>
			</td>
		</tr>
		<tr>
			<td>
				<cui:checkbox id="checkId_5" label="关联对讲" value="5" onChange="checkChange(5)"></cui:checkbox>
			</td>
			<td colspan="2">
				<cui:radiolist id="radiolist5" repeatLayout="flow" disabled="true" column="2" data="radiolistdata"></cui:radiolist>
			</td>
		</tr>
	</table>

</cui:form>

<div class="dialog-buttons" style="margin-bottom: 0px; text-align: center;">
	<cui:button label="提交" width="70" onClick="add"></cui:button>
	<cui:button label="重置" width="70" onClick="clear"></cui:button>
</div>
<script>
	function checkChange(index) {
		var isChk = $("#checkId_" + index).checkbox("isChecked");
		var radio = $("#radiolist" + index);

		if (isChk == true) {
			radio.radiolist("setValue", "1");
		} else {
			radio.radiolist("setValue", "");
		}
		radio.radiolist("option", "disabled", !isChk);
	}

	//发送添加请求
	function add() {
		if ($("#formId_addPlan").form("valid")) {
			// console.log("add 1");
			var formData = $("#formId_addPlan").form("formData");
			// console.log("add formData = " + JSON.stringify(formData));
			var items = [];
			for (var i = 0; i < 5; i++) {
				var index = i + 1;
				if ($("#checkId_" + index).checkbox( "isChecked")) {
					var itemData = {};
					itemData["pidItemId"] = index + "";
					itemData["pidSttsIndc"] = $("#radiolist" + index).radiolist("getValue");
					items.push(itemData);  
				}
			}
			// console.log("add 2");
			// console.log("add items = " + JSON.stringify(items));
			formData.items = items;
			// console.log("add formData = " + JSON.stringify(formData));
			$.ajax({
				type : "post",
				url : "${ctx}/plan/inster/master.json",
				data : JSON.stringify(formData),
				dataType : "json",
				contentType : 'application/json', 
				success : function(data) {
					if (data.success) {
						// console.log("add 3");
						$.messageQueue({ message : data.msg, cls : "success", type : "info" });
						$("#gridId_plan").grid("reload");
						$("#dialogId_plan").dialog("close");
					} else {
						// console.log("add 4");
						$.messageQueue({ message : data.msg, cls : "warning", iframePanel : true, type : "info" });
					}
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					// console.log("add 5");
					// console.log("add XMLHttpRequest = " + JSON.stringify(XMLHttpRequest));
					// console.log("add textStatus = " + JSON.stringify(textStatus));
					// console.log("add errorThrown = " + JSON.stringify(errorThrown));
					$.messageQueue({ message : textStatus, cls : "warning", iframePanel : true, type : "info" });
				}
			});
		} else {
			// console.log("add 6");
			$.messageQueue({ message : "请输入预案名称", cls : "warning", iframePanel : true, type : "info" });
		}
	}

	function clear() {
		$("#formId_addPlan").form("reset");
	}
</script>
</html>
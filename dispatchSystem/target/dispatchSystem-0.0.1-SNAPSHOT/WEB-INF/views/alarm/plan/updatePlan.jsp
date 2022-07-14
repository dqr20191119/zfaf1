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

<div>
	<cui:form id="formId_updateInfo" style="text-align: center;">
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
</div>

<div class="dialog-buttons" style="margin-bottom: 0px; text-align: center;">
	<cui:button label="提交" width="70"   onClick="submit"></cui:button>
	<cui:button label="重置" width="70"   onClick="clear"></cui:button>
</div>

<script>
	var info;//存放预案信息
	var itemIds =  new Array(5);//存放itemid

	$.parseDone(function() {
		var url = "${ctx}/plan/findPlanById.json?id=${ID}";
		$.ajax({
			type : "post",
			url : url,
			dataType : "json",
			success : function(data) {
				if (data.success) {
					info = data.obj;
					$("#formId_updateInfo").form("load", info);
					var arr = info.items;
					initRadioData(arr);
				} else {
					$.messageQueue({ message : data.msg, cls : "warning", iframePanel : true, type : "info" });
				}
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$.messageQueue({ message : textStatus, cls : "warning", iframePanel : true, type : "info" });
			}
		});

	});

	//初始化radio 单选数据
	function initRadioData(arr) {
		for (var i = 0; i < arr.length; i++) {
			var item = arr[i];
			itemIds[parseInt(item.pidItemId) - 1] = item.id;
			$("#checkId_" + item.pidItemId).checkbox("check");
			$("#radiolist" + item.pidItemId).radiolist("option", "disabled", false);
			$("#radiolist" + item.pidItemId).radiolist("setValue", item.pidSttsIndc);
		}
	}
	//复选框点击事件
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

	function submit() {
		$.confirm("是否修改选中的记录？", "信息确认", function(confirm) {
			if (confirm) {
				update();
			}
		});
	}

	//发送修改请求
	function update() {
		if ($("#formId_updateInfo").form("valid")) {
			var formData = $("#formId_updateInfo").form("formData");
			formData['id'] = "${ID}";
			var items = [];
			for (var i = 0; i < 5; i++) {
				var index = i + 1;
				var itemData = {};
				itemData["id"] = itemIds[i];
				itemData["pidPlanId"] = "${ID}";
				itemData["pidItemId"] = index + "";
				if ($("#checkId_" + index).checkbox("isChecked")) {
					itemData["pidSttsIndc"] = $( "#radiolist" + index).radiolist( "getValue");
				} 
				items.push(itemData);
			}
			formData.items = items;
			var url = "${ctx}/plan/update/master.json?";
			$.ajax({
				type : "post",
				url : url,
				data : JSON.stringify(formData),
				dataType : "json",
				contentType : 'application/json', 
				success : function(data) {
					if (data.success) {
						$.messageQueue({ message : data.msg, cls : "success", iframePanel : true, type : "info" });
						$("#gridId_plan").grid("reload");
						$("#dialogId_plan").dialog("close");
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
		$("#formId_updateInfo").form("load", info);
		initRadioData(info.items);
	}
</script>
</html>
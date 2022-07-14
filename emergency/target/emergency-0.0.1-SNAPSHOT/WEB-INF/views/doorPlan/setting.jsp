<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<%@taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<style>
.div_content {
	float: left;
	width: 30%;
	height: 100%;
	text-align: center;
}

.div_content_1 {
	float: left;
	width: 70%;
	height: 100%;
	text-align: center;
}

fieldset {
	height: 97%;
}
</style>
<div style="text-align: center; height: 100%; width: 100%">
	<div style="height: 93.5%; width: 100%">
		<div class="div_content">
			<fieldset>
				<legend>待关联</legend>
				<div style="width: 100%; text-align: left;">
					<table style="width: 98%; padding: 2px;">
						<tr>
							<th>门禁：</th>
							<td>
								<cui:input id="input_doorName"></cui:input>
							</td>
						</tr>
						<tr style="margin-top: 4px">
							<th>区域：</th>
							<td>
								<cui:combotree id="combotree_door" allowPushParent="false" url="${ctx}" simpleDataEnable="true" simpleDataIdKey="id" simpleDataPIdKey="pid" keyName="name">
								</cui:combotree>
							</td>
						</tr>
						<tr style="margin-top: 4px">
							<td colspan="2" style="text-align: center;">
								<cui:button onClick="querydoor" label="查询"></cui:button>
								<cui:button onClick="resetdoor" label="重置"></cui:button>
								<cui:button onClick="togridDoorPlan" label="关联"></cui:button>
							</td>
						</tr>
					</table>
				</div>

				<cui:grid id="gridDoor" colModel="gridDoor" fitStyle="fill" multiselect="true" rowNum="999">
				</cui:grid>
			</fieldset>
		</div>

		<div class="div_content_1">
			<fieldset>
				<legend>已关联</legend>
				<div style="width: 100%; text-align: left;">
					<cui:button onClick="cancelRltn" label="取消关联"></cui:button>
				</div>
				<cui:grid id="gridDoorPlan" colModel="gridDoorPlan" fitStyle="fill" multiselect="true">
					<cui:gridPager gridId="gridDoorPlan" />
				</cui:grid>
			</fieldset>
		</div>
	</div>
	<div class="dialog-buttons" style="margin-bottom: 0px; text-align: center;">
		<cui:button label="提交" text="false" onClick="savemjya"></cui:button>
		<cui:button label="重置" text="false" onClick="resetmjya"></cui:button>
	</div>
</div>
<script>
	$.parseDone(function() {
		$("#combotree_door").combotree("tree").tree( "reload", "${ctx}/common/areadevice/findForCombotree.json?cusNumber=" + cusNumber + "&deviceType=0");
		var url = "${ctx}/door/plan/listAll/rltn.json?dorCusNumber=" + cusNumber + "&dorPlanId=${ID}";
		$("#gridDoorPlan").grid("reload", url);
	});

	//重置
	function resetmjya() {
		$("#combotree_door").combotree("setText", "");
		$("#input_doorName").textbox("setText", "");
		$("#gridDoor").grid("clearGridData");
		$("#gridDoorPlan").grid("reload");
	}

	function querydoor() {
		var areaId = $("#combotree_door").combotree("getValue");
		var doorName = $("#input_doorName").textbox("getText");
		var formData = {};
		formData['cusNumber'] = cusNumber;
		formData['planId'] = '${ID}';
		if (areaId != "") {
			formData['areaId'] = areaId;
		}
		if (doorName != "") {
			formData['doorName'] = doorName;
		}
		$("#gridDoor").grid("option", "postData", formData);
		var url = "${ctx}/door/plan/listAllForMj";
		$("#gridDoor").grid("reload", url);
	}

	function resetdoor() {
		$("#input_doorName").textbox("setText", "");
		$("#combotree_door").combotree("setText", "");
		$("#gridDoor").grid("clearGridData");
	}

	function togridDoorPlan() {
		var selected = $("#gridDoor").grid("option", "selarrrow");
		if (selected.length < 0) {
			$.messageQueue({ message : "请选择需要关联的门禁", cls : "warning", iframePanel : true, type : "info" });
			return;
		}
		for (var i = 0; i < selected.length; i++) {
			var doorData = $("#gridDoor").grid("getRowData", selected[i]);
			var doorPlanDate = $("#gridDoorPlan").grid("getRowData");
			var len = doorPlanDate.length;
			for (var j = 0; j < doorPlanDate.length; j++) {
				if (doorPlanDate[j].DOR_DOOR_ID == doorData.ID) {
					return;
				}
			}
			var obj = new Object();
			obj.DOR_PLAN_ID = "${ID}";
			obj.DOR_EXEC_ORDER = len + 1;
			obj.DOR_DOOR_ID = doorData.ID;
			obj.DOR_DOOR_NAME = doorData.DVC_NAME;
			$("#gridDoorPlan").grid("addRowData", len + 1, obj);
		}
		for (var i = selected.length; i > 0; i--) {
			$("#gridDoor").grid("delRowData", selected[i - 1]);
		}
	}

	//取消关联，已经存库的删除操作，否则退回待关联列表
	function cancelRltn() {
		var selected = $("#gridDoorPlan").grid("option", "selarrrow");
		var ids = [];
		for (var i = selected.length; i > 0; i--) {
			var doorPlanDate = $("#gridDoorPlan").grid("getRowData", selected[i - 1]);
			if (doorPlanDate.ID == "") {
				$("#gridDoorPlan").grid("delRowData", selected[i - 1]);
				var doorData = $("#gridDoor").grid("getRowData");
				var len = doorData.length;
				var obj = new Object();
				obj.ID = doorPlanDate.DOR_DOOR_ID;
				obj.DVC_NAME = doorPlanDate.DOR_DOOR_NAME;
				$("#gridDoor").grid("addRowData", len + 1, obj);
			} else {
				ids.push(doorPlanDate.ID);
			}
		}
		if (ids.length > 0) {
			removemjya(ids);
		}
	}

	function savemjya() {
		var doorDate = $("#gridDoorPlan").grid("getRowData");
		if (doorDate.length == 0) {
			alert("门禁未选择！");
			return;
		}
		$.ajax({
			type : "post",
			url : "${ctx}/door/plan/save/rltn.json",
			data : JSON.stringify(doorDate),
			dataType : "json",
			contentType : 'application/json; charset=UTF-8',
			success : function(data) {
				if (data.success) {
					$.messageQueue({ message : data.msg, cls : "success", iframePanel : true, type : "info" });
					resetmjya();//重置
				} else {
					$.messageQueue({ message : data.msg, cls : "warning", iframePanel : true, type : "info" });
				}
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$.messageQueue({ message : textStatus, cls : "warning", iframePanel : true, type : "info" });
			}
		});

	}

	function removemjya(data_) {
		if (data_.length > 0) {
			$.confirm("是否删除选中的记录", "信息确认", function(confirm) {
				if (confirm) {
					$.ajax({
						url : "${ctx}/door/plan/delete/rltn.json",
						dataType : "json",
						type : "post",
						data : JSON.stringify(data_),
						contentType : "application/json",
						success : function(data) {
							if (data.success) {
								$.messageQueue({ message : data.msg, cls : "success", iframePanel : true, type : "info" });
								$("#gridDoorPlan").grid("reload");
							} else {
								$.messageQueue({ message : data.msg, cls : "warning", iframePanel : true, type : "info" });
							}
						}
					});
				}
			});
		} else {
			$.messageQueue({ message : "请选择要删除的记录", cls : "warning", iframePanel : true, type : "info" });
		}

	}

	var gridDoorPlan = [ {
		label : "id",
		name : "ID",
		key : true,
		hidden : true
	}, {
		name : "DOR_PLAN_ID",
		label : "预案id",
		hidden : true
	}, {
		name : "DOR_EXEC_ORDER",
		label : "排序",
		width : 40,
		formatter : "text"
	}, {
		name : "DOR_DOOR_ID",
		label : "门禁id",
		hidden : true
	}, {
		name : "DOR_DOOR_NAME",
		label : "门禁"
	}, {
		name : "DOR_REMARK",
		label : "备注",
		width : 200,
		formatter : "text"
	}];

	var gridDoor = [ {
		name : "ID",
		hidden : true
	}, {
		name : "DVC_NAME",
		label : "门禁",
	}];
</script>
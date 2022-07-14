<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<%@taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<style>
.div_body {
	height: 100%;
	width: 100%;
}

.div_content {
	margin-left:3px;
	float: left;
	width: 33%;
	height: 100%;
}

fieldset {
	height: 96.5%;
}
</style>

<div class="div_body">
	<div class="div_content">
		<fieldset>
			<legend>预案信息</legend>
			<table class="table">
				<tr>
					<th>预案：</th>
					<td>
						<cui:input id="planName_" readonlyInput="true"></cui:input>
					</td>
				</tr>
				<tr>
					<th>关联项：</th>
					<td>
						<cui:combobox id="comId_items" onSelect="comboSelect"></cui:combobox>
					</td>
				</tr>
				<tr>
					<th>区域：</th>
					<td>
						<cui:combotree id="combotree_area" allowPushParent="false" url="${ctx}" simpleDataEnable="true" simpleDataIdKey="id" simpleDataPIdKey="pid" keyName="name" required="true" onNodeClick="treeSelect">
						</cui:combotree>
					</td>
				</tr>
				<tr>
					<td colspan="2" style="text-align: center;">
						<cui:button   onClick="saveDevice" label="提交保存"></cui:button>
						<cui:button   onClick="reset" label="重置"></cui:button>
					</td>
				</tr>
			</table>
		</fieldset>
	</div>
	<div class="div_content">
		<fieldset>
			<legend>待关联的设备</legend>
			<cui:grid id="gridLeftData" colModel="gridColModel_left" fitStyle="fill" onDblClickRow="toRight" rowNum="200">
			</cui:grid>
		</fieldset>
	</div>
	<div class="div_content">
		<fieldset>
			<legend>已关联的设备</legend>
			<cui:grid id="gridRightData" colModel="gridColModel_right" fitStyle="fill" onDblClickRow="removeDevice" rowNum="200">
			</cui:grid>
		</fieldset>
	</div>
</div>

<script>
	var planId = "${planId}";
	$.parseDone(function() {
		$("#combotree_area").combotree("tree").tree( "reload", "${ctx}/common/areadevice/findForCombotree.json?cusNumber=" + cusNumber + "&deviceType=0");
		$("#combotree_area").combotree("option", "disabled", true);
		$.ajax({
			type : "post",
			url : "${ctx}/plan/findPlanAndItemsById.json?id=" + planId,
			dataType : "json",
			success : function(data) {
				if (data.success) {
					$("#comId_items").combobox("reload", data.obj.items);
					$("#planName_").textbox("setText", data.obj.planName);
				} else {
					$.messageQueue({ message : data.msg, cls : "warning", iframePanel : true, type : "info" });
				}
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$.messageQueue({ message : textStatus, cls : "warning", iframePanel : true, type : "info" });
			}
		});

	});
	//待关联列表双击列加入关联表
	function toRight(e, ui) {
		var $gridLeft = $("#gridLeftData"), $gridRight = $("#gridRightData"), sel = $gridLeft.grid("option", "selrow");
		var rowData = $gridLeft.grid("getRowData", ui.rowId);
		var rightData = $gridRight.grid("getRowData");
		for (var i = 0; i < rightData.length; i++) {
			console.info(rightData[i]);
			if (rightData[i].PDR_DVC_IDNTY == rowData.ID) {
				$.messageQueue({ message : $("#comId_items").combobox("getText") + "设备【 " + rowData.DVC_NAME + " 】不可重复添加", cls : "warning", iframePanel : true, type : "info" });
				return;
			}
		}
		var obj = new Object();
		obj.PDR_DVC_IDNTY = rowData.ID;
		obj.PDR_DVC_NAME = rowData.DVC_NAME;
		obj.PDR_ITEM_ID = $("#comId_items").combobox("getValue");
		obj.PDR_PLAN_ID = planId;
		$gridRight.grid("addRowData", rowData.id, obj);
		//$gridLeft.grid("delRowData", ui.rowId);
	}

	//已关联列表的双击删除事件
	function removeDevice(e, ui) {
		var $gridRight = $("#gridRightData");
		var rowData = $gridRight.grid("getRowData", ui.rowId);
		console.info(rowData);
		if (rowData.ID == "") {
			$gridRight.grid("delRowData", ui.rowId);
		} else {
			$.ajax({
				url : "${ctx}/plan/delete/device.json?id=" + rowData.ID + "&pdrCusNumber=" + cusNumber + "&pdrPlanId=" + planId,
				dataType : "json",
				type : "post",
				contentType : "application/json",
				success : function(data) {
					if (data.success) {
						$.messageQueue({ message : "删除记录成功", cls : "success", iframePanel : true, type : "info" });
						$gridRight.grid("reload");
					} else {
						$.messageQueue({ message : data.msg, cls : "warning", iframePanel : true, type : "info" });
					}
				}
			});
		}
	}

	//重置
	function reset() {
		$("#combotree_area").combotree("setValue", "");
		$("#comId_items").combobox("setValue", "");
	}

	//item下拉控件的点击事件
	function comboSelect(event, ui) {
		var url = "${ctx}/plan/listAllForDeviceByItem.json?pdrCusNumber=" + cusNumber + "&pdrPlanId=" + planId + "&pdrItemId=" + ui.value;
		$("#combotree_area").combotree("setValue", "");
		//此处控制 门禁 对讲 自动的选项不可勾选 wuzl
        if(ui.value == '1' || ui.value == '5') {
            $("#gridRightData").grid("hideCol", ["PDR_OUTO_INDC"]);
        }
        $("#gridRightData").grid("reload", url);//请求已关联设备信息


		$("#gridLeftData").grid("clearGridData");//清空当前待关联设备数据

		if (ui.value == "4") {//大屏预案不和区域关联
			$("#combotree_area").combotree("option", "disabled", true);
			var item = $("#comId_items").combobox("getValue");
			queryDevice(item);//请求待关联设备数据
		} else {
			$("#combotree_area").combotree("option", "disabled", false);
		}
	}

	//区域控件下拉树的点击事件
	function treeSelect(event, ui) {
		var item = $("#comId_items").combobox("getValue");
		var node = ui.node;
		if (node.isParent == false) {
			//alert(node.id);
			queryDevice(item, node.id);//请求待关联设备数据
		}
	}

	//请求待关联设备数据
	function queryDevice(itemId, areaId) {
		var url = "${ctx}/plan/listAll/device.json?cusNumber=" + cusNumber + "&itemId=" + itemId + "&planId=" + planId;
		if (areaId != undefined || areaId != "") {
			url = url + "&areaId=" + areaId;
		}
		$("#gridLeftData").grid("reload", url);
	}

	var gridColModel_left = [ {
		name : "ID",
		hidden : true
	}, {
		name : "DVC_NAME",
		label : "待关联设备",
	}/* , {
		name : "STTS_INDC",
		label : "状态",
		width : 82,
		fixed : true
	}  */];

	var gridColModel_right = [ {
		label : "id",
		name : "ID",
		key : true,
		hidden : true
	}, {
		name : "PDR_PLAN_ID",
		label : "预案id",
		hidden : true
	}, {
		name : "PDR_ITEM_ID",
		label : "关联项id",
		hidden : true
	}, {
		name : "PDR_DVC_IDNTY",
		label : "关联设备 id",
		hidden : true
	}, {
		name : "PDR_DVC_NAME",
		label : "关联设备",
	}, {
		label : "自动",
		name : "PDR_OUTO_INDC",
		formatter : "checkbox",
		formatoptions : {
			value : '1:0'
		},
		align : "center",
		width : 50,
		fixed : true
	}, {
		label : "执行顺序",
		name : "PDR_EXEC_ORDER",
		formatter : "text",
		width : 70,
		fixed : true
	} ];

	//保存关联设备信息
	function saveDevice() {
		var deviceData = $("#gridRightData").grid("getRowData");
		if (validation(deviceData)) {
			return;
		}
		$.ajax({
			type : "post",
			url : "${ctx}/plan/inster/device.json",
			data : JSON.stringify(deviceData),
			dataType : "json",
			contentType : 'application/json; charset=UTF-8',
			success : function(data) {
				if (data.success) {
					$.messageQueue({ message : "保存成功", cls : "success", iframePanel : true, type : "info" });
					$("#gridRightData").grid("reload");
				} else {
					$.messageQueue({ message : data.msg, cls : "warning", iframePanel : true, type : "info" });
				}
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$.messageQueue({ message : textStatus, cls : "warning", iframePanel : true, type : "info" });
			}
		});
	}
	
	//将保存的数据验证
	function validation(deviceData) {
		if (deviceData.length == 0) {
			$.messageQueue({ message : "已关联设备列表不能为空", cls : "warning", iframePanel : true, type : "info" });
			return true;
		} else if(deviceData[0].PDR_ITEM_ID == "4"){//大屏
			var index = 0;
			for (var i = 0; i < deviceData.length; i++) {
				if(deviceData[i].PDR_OUTO_INDC == "1"){
					index = index + 1;
				}
			}
			if(index > 1){
				$.messageQueue({ message : "大屏预案自动触发只能设置一个预案", cls : "warning", iframePanel : true, type : "info" });
				return true;
			}
		}

		return false;
	}
</script>
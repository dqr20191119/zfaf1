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
					<th>区域：</th>
					<td>
						<cui:combotree id="combotree_area_planAlertor" allowPushParent="false" url="${ctx}" simpleDataEnable="true" simpleDataIdKey="id" simpleDataPIdKey="pid" keyName="name" required="true" onNodeClick="treeSelect">
						</cui:combotree>
					</td>
				</tr>
				<tr>
					<td colspan="2" style="text-align: center;">
						<cui:button onClick="saveDevice" label="提交保存"></cui:button>
						<cui:button onClick="reset" label="重置"></cui:button>
					</td>
				</tr>
			</table>
		</fieldset>
	</div>
	<div class="div_content">
		<fieldset>
			<legend>待关联</legend>
			<cui:grid id="gridLeftData" colModel="gridColModel_left" fitStyle="fill" onDblClickRow="toRight" rowNum="200">
			</cui:grid>
		</fieldset>
	</div>
	<div class="div_content">
		<fieldset>
			<legend>已关联</legend>
			<cui:grid id="gridRightData" colModel="gridColModel_right" fitStyle="fill" onDblClickRow="removeDevice" rowNum="200">
			</cui:grid>
		</fieldset>
	</div>
</div>

<script>
	var planId = "${planId}";
	var planName = "${planName}";
	//报警器类型
	var typeData = <%=CodeFacade.loadCode2Json("4.20.27")%>;
	//报警器品牌
	<%-- var brandData = <%=CodeFacade.loadCode2Json("4.20.36")%>; --%>
	$.parseDone(function() {
		$("#combotree_area_planAlertor").combotree("tree").tree( "reload", "${ctx}/common/areadevice/findForCombotree.json?cusNumber=" + cusNumber + "&deviceType=0");
		$("#planName_").textbox("setText", planName);
		var url = "${ctx}/plan/listAllForAlertorByPlan.json?aprCusNumber=" + cusNumber + "&aprPlanId=" + planId;
		$("#gridRightData").grid("reload", url);//请求已关联设备信息
	});
	//待关联列表双击列加入关联表
	function toRight(e, ui) {
		var $gridLeft = $("#gridLeftData"), $gridRight = $("#gridRightData"), sel = $gridLeft.grid("option", "selrow");
		var rowData = $gridLeft.grid("getRowData", ui.rowId);
		var rightData = $gridRight.grid("getRowData");
		for (var i = 0; i < rightData.length; i++) {
			console.info(rightData[i]);
			if (rightData[i].APR_DVC_IDNTY == rowData.ID) {
				$.messageQueue({ message : "报警器【 " + rowData.ABD_NAME + " 】不可重复添加", cls : "warning", iframePanel : true, type : "info" });
				return;
			}
		}
		var obj = new Object();
		obj.APR_DVC_IDNTY = rowData.ID;
		obj.APR_DVC_NAME = rowData.ABD_NAME;
		obj.APR_DVC_TYPE_INDC = rowData.ABD_TYPE_INDC;
		obj.APR_PLAN_ID = planId;
		obj.APR_BRAND_INDC = rowData.ABD_BRAND_INDC;
		$gridRight.grid("addRowData", rowData.id, obj);
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
				url : "${ctx}/plan/delete/alert.json?id=" + rowData.ID + "&aprCusNumber=" + cusNumber + "&aprPlanId=" + planId,
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
		$("#combotree_area_planAlertor").combotree("setValue", "");
	}

	//区域控件下拉树的点击事件
	function treeSelect(event, ui) {
		var node = ui.node;
		if (node.isParent == false) {
			// queryDevice(item, node.id);//请求待关联设备数据
			queryDevice(node.id);//请求待关联设备数据
		}
	}

	//请求待关联设备数据
	function queryDevice(areaId) {
		var url = "${ctx}/plan/listAll/alertor.json?cusNumber=" + cusNumber + "&planId=" + planId + "&areaId=" + areaId;
		$("#gridLeftData").grid("reload", url);
	}

	//请求待关联设备数据
	/* function queryDevice(itemId, areaId) {
		var url = "${ctx}/plan/listAll/alertor.json?cusNumber=" + cusNumber + "&planId=" + planId + "&areaId=" + areaId;
		$("#gridLeftData").grid("reload", url);
	} */
	
	var gridColModel_left = [ {
		name : "ID",
		label : "主键编号",
		hidden : true
	},{
		name : "ABD_IDNTY",
		label : "报警器在平台的索引编号",
		hidden : true
	}, {
		name : "ABD_NAME",
		label : "报警器",
	}, {
		name : "ABD_BRAND_INDC",
		label : "品牌",
		hidden : true
		/* formatter : "convertCode",
        revertCode : true,
        formatoptions : { 'data':brandData }  */
	}, {
		name : "ABD_TYPE_INDC",
		label : "类型",
	 	formatter : "convertCode",
        revertCode : true,
        formatoptions : { 'data':typeData } 
	} ];

	var gridColModel_right = [ {
		label : "id",
		name : "ID",
		key : true,
		hidden : true
	}, {
		name : "APR_PLAN_ID",
		label : "预案id",
		hidden : true
	}, {
		name : "APR_DVC_IDNTY",
		label : "编号",
		hidden : true
	}, {
		name : "APR_DVC_NAME",
		label : "报警器",
	}, {
		name : "APR_BRAND_INDC",
		label : "品牌",
		hidden : true
		/* formatter : "convertCode",
        revertCode : true,
        formatoptions : { 'data':brandData }  */
	}, {
		name : "APR_DVC_TYPE_INDC",
		label : "类型",
	 	formatter : "convertCode",
        revertCode : true,
        formatoptions : { 'data':typeData } 
	}, {
		label : "备注",
		name : "APR_REMARK",
		formatter : "text",
	} ];

	//保存关联设备信息
	function saveDevice() {
		var alertorData = $("#gridRightData").grid("getRowData");
		if (alertorData.length == 0) {
			return;
		}
		$.ajax({
			type : "post",
			url : "${ctx}/plan/inster/alertor.json",
			data : JSON.stringify(alertorData),
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
</script>
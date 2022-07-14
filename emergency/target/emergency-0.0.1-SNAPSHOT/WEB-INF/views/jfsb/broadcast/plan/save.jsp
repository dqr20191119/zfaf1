<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<%@taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<style>
.div_body {
	height: 90%;
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
	<cui:form id="formId_gb_save" heightStyle="fill">
		<table class="table" style="width: 98%">
			<tr>
				<th>广播预案名称：</th>
				<td>
					<cui:input name="dbpBroadcastPlanName" componentCls="form-control" required="true"></cui:input>
				</td>
				
			</tr>
			<tr>
			<th>广播曲目：</th>
					<td>
						<cui:combobox name="dbpBroadcastFileDtlsId" componentCls="form-control" data="gb_qm" required="true"></cui:combobox>
					</td>
			</tr>
			<tr>
					<th>区域：</th>
					<td>
						<cui:combotree id="combotree_area_planBroadcast" url="${ctx}" simpleDataEnable="true" simpleDataIdKey="id" simpleDataPIdKey="pid" keyName="name"  onNodeClick="treeSelect">
						</cui:combotree>
					</td>
				</tr>
		</table>
	</cui:form>
	</div>
	<div class="div_content">
		<fieldset>
			<legend>待关联的广播</legend>
			 <cui:grid id="gridLeftData" colModel="gridColModel_left" fitStyle="fill" onDblClickRow="toRight" rowNum="200">
			</cui:grid> 
		</fieldset>
	</div>
	<div class="div_content">
		<fieldset>
			<legend>已关联的广播</legend>
			 <cui:grid id="gridRightData" colModel="gridColModel_right" fitStyle="fill"  onDblClickRow="removeDevice" rowNum="200">
			</cui:grid> 
		</fieldset>
	</div>
</div>
 <div class="dialog-buttons" style="margin-bottom: 0px; text-align: center;">
		<cui:button label="提交" text="false" onClick="add"></cui:button>
		<cui:button label="重置" text="false" onClick="clear"></cui:button>
</div>
<script>
	$.parseDone(function() {
		// 部门数据请求
		// $("#combId_dprtmnt").combobox( "reload", "${ctx}/common/authsystem/findAllChildrenOrgByJyKeyForCombobox.json?cusNumber=" + cusNumber);
		// 区域数据请求
		$("#combotree_area_planBroadcast").combotree("tree").tree("reload", "${ctx}/common/areadevice/findForCombotree.json?cusNumber=" + cusNumber +"&deviceType=0");
	});

	var gridColModel_left = [ {
		name : "ID",
		label : "主键编号",
		hidden : true
	},{
		name : "BBD_NAME",
		label : "广播名称",
		align: "center"
	},{
		name : "BBD_AREA",
		label : "所属区域",
		align: "center"
	}];

	var gridColModel_right = [ {
		label : "id",
		name : "ID",
		key : true,
		hidden : true
	}, {
		name : "BBD_NAME",
		label : "广播名称",
		align: "center"
	},{
		name : "BBD_AREA",
		label : "所属区域",
		align: "center"
	}];

	//区域控件下拉树的点击事件
	function treeSelect(event, ui) {
		var node = ui.node;
			// queryDevice(item, node.id);//请求待关联设备数据
			queryDevice(node.id);//请求待关联设备数据
	}

	//请求待关联设备数据
	function queryDevice(areaId) {
		var url = "${ctx}/broadcast/listAll.json?bbdCusNumber="+ cusNumber+"&bbdAreaId="+areaId;
		$("#gridLeftData").grid("reload", url);
	}
	
	//待关联列表双击列加入关联表
	function toRight(e, ui) {
		var $gridLeft = $("#gridLeftData"), $gridRight = $("#gridRightData"), sel = $gridLeft.grid("option", "selrow");
		var rowData = $gridLeft.grid("getRowData", ui.rowId);
		var rightData = $gridRight.grid("getRowData");
		for (var i = 0; i < rightData.length; i++) {
			console.info(rightData[i]);
			if (rightData[i].ID == rowData.ID) {
				$.messageQueue({ message : "广播【 " + rowData.BBD_NAME + " 】不可重复添加", cls : "warning", iframePanel : true, type : "info" });
				return;
			}
		}
		var obj = new Object();
		obj.ID = rowData.ID;
		obj.BBD_NAME = rowData.BBD_NAME;
		obj.BBD_AREA = rowData.BBD_AREA;
		$gridRight.grid("addRowData", rowData.id, obj);
	}

	//已关联列表的双击删除事件
	function removeDevice(e, ui) {
		var $gridRight = $("#gridRightData");
		var rowData = $gridRight.grid("getRowData", ui.rowId);
		console.info(rowData);
			$gridRight.grid("delRowData", ui.rowId);
	}
	
	
	
	//发送添加请求
	function add() {
		if ($("#formId_gb_save").form("valid")) {
			var formData = $("#formId_gb_save").form("formData");
			// formData['bbdDprtmnt'] = $("#combId_dprtmnt").combobox("getText");
			//formData['bbdArea'] = 	$("#combId_area").combotree("getText");
          //  formData["bbdCameraName"] = $("#comboId_glsxt").autocomplete("getText");
           var braodcasts= $("#gridRightData").grid("getRowData");
           //dbpBroadcastId
         //  console.log("广播id为:"+);
           var dbpBroadcastId ="";
           if(braodcasts.length!=0){
	           for (var i = 0; i < braodcasts.length; i++) {
	        	   if(i==braodcasts.length-1){
	        		   dbpBroadcastId = dbpBroadcastId +  braodcasts[i].ID;
	        	   }else {
	        		   dbpBroadcastId = dbpBroadcastId +  braodcasts[i].ID+",";
					}
				}
           }
           console.log("拼接后的广播id为:"+dbpBroadcastId);
           formData["dbpBroadcastId"] =dbpBroadcastId;
			$.ajax({
				type : 'post',
				url : '${ctx}/broadcastPlan/save.json',
				data : formData,
				dataType : 'json',
				success : function(data) {
					if (data.success) {
						$.messageQueue({ message : data.msg, cls : "success", iframePanel : true, type : "info" });
						$("#gridId_broadcast").grid("reload");
						$("#dialogId_broadcast").dialog("close");
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
		$("#formId_gb_save").form("reset");
	}
	

</script>
</html>
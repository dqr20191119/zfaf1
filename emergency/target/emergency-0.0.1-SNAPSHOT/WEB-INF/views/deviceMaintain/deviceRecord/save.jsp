<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<%@taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<style>
.div_body {
	height: 99%;
	width: 99%;
	padding-left: 10px;
}

.div_content {
	float: left;
	width: 32%;
	height: 100%;
	margin: 5px;
}

fieldset {
	height: 530px;
}
</style>

<div class="div_body">
	<div class="div_content">
		<fieldset>
			<legend>维修信息</legend>
			<cui:form id="formId_wxxx">
				<table class="table">
					<tr>
						<th>维修人：</th>
						<td width="110px">
							<cui:input id="wxr" name="dmrFaultMaintainer" componentCls="form-control" required="true"></cui:input>
						</td>
					</tr>
					<tr>	
						<th>维修时间：</th>
						<td>
							<cui:datepicker id="wxsj" name="dmrFaultMaintainTime" componentCls="form-control" required="true"></cui:datepicker>
						</td>
					</tr>
					<tr>
						<th>维修情况：</th>
						<td colspan="3">
							<cui:textarea id="wxqk" name="dmrFaultContent" componentCls="form-control" required="true"></cui:textarea>
						</td>
					</tr>
					<tr>
						<th>设备类型：</th>
						<td>
							<cui:combobox id="deviceType" name="dmrDeviceType" componentCls="form-control" data="deviceTypeDate" required="true" onSelect="comboSelect"></cui:combobox>
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
							<cui:button   onClick="saveDevice" label="保存"></cui:button>
							<cui:button   onClick="reset_" label="重置"></cui:button>
						</td>
					</tr>
				</table>
			</cui:form>
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
			<legend>关联的设备</legend>
			<cui:grid id="gridRightData" colModel="gridColModel_right" fitStyle="fill" onDblClickRow="removeDevice" rowNum="200">
			</cui:grid>
		</fieldset>
	</div>
</div>

<script>

	$.parseDone(function() {
		$("#combotree_area").combotree("tree").tree( "reload", "${ctx}/common/areadevice/findForCombotree.json?cusNumber=" + cusNumber + "&deviceType=0");
		$("#combotree_area").combotree("option", "disabled", true);
	});
	
	//待关联列表双击列加入关联表
	function toRight(e, ui) {
		if ($("#formId_wxxx").form("valid")) {
			var $gridLeft = $("#gridLeftData"), $gridRight = $("#gridRightData"), sel = $gridLeft.grid("option", "selrow");
			var rowData = $gridLeft.grid("getRowData", ui.rowId);
			var rightData = $gridRight.grid("getRowData");
			for (var i = 0; i < rightData.length; i++) {
				console.info(rightData[i]);
				if (rightData[i].DMR_DEVICE_NAME == rowData.DEVICE_NAME) {
					$.messageQueue({ message : "设备【 " + rowData.DEVICE_NAME + " 】不可重复添加", cls : "warning", iframePanel : true, type : "info" });
					return;
				}
			}
			var obj = new Object();
			obj.DMR_DEVICE_IDNTY = rowData.DEVICE_ID;
			obj.DMR_DEVICE_NAME = rowData.DEVICE_NAME;
			obj.DMR_DEVICE_TYPE = $("#deviceType").combobox("getValue");
			obj.DMR_FAULT_MAINTAINER = $('#wxr').textbox("getText");
			obj.DMR_FAULT_MAINTAIN_TIME = $('#wxsj').datepicker("getDateValue");
			obj.DMR_FAULT_CONTENT = $('#wxqk').textbox("getText");
			$gridRight.grid("addRowData", rowData.id, obj);
		}else{
			$.messageQueue({ message : "请检查维修人、时间、内容是否填写！", cls : "warning", iframePanel : true, type : "info" });
		}
	}

	//已关联列表的双击删除事件
	function removeDevice(e, ui) {
		var $gridRight = $("#gridRightData");
		var rowData = $gridRight.grid("getRowData", ui.rowId);
		console.info(rowData);
	 	$gridRight.grid("delRowData", ui.rowId);
	}

	//重置
	function reset_() {
		$("#formId_wxxx").form("clear");
		$("#combotree_area").combotree("option", "disabled", true);
		$("#gridLeftData").grid("clearGridData");//清空设备数据
		$("#gridRightData").grid("clearGridData");//清空设备数据
	}

	//type下拉控件的点击事件
	function comboSelect(event, ui) {
		$("#gridLeftData").grid("clearGridData");//清空当前待关联设备数据
 		$("#combotree_area").combotree("option", "disabled", false);
		if($("#combotree_area").combotree("getValue") != ""){
			queryDevice(ui.item.value, $("#combotree_area").combotree("getValue"));//请求待关联设备数据
		}
	}

	//区域控件下拉树的点击事件
	function treeSelect(event, ui) {
		var type = $("#deviceType").combobox("getValue");
		if (type == "") {
			$.messageQueue({ message : "请选择设备类型", cls : "warning", iframePanel : true, type : "info" });
			return;
		}
		queryDevice(type, ui.node.id);//请求待关联设备数据
	}

	//请求待关联设备数据
	function queryDevice(type, areaId) {
		var url = "${ctx}/deviceMaintain/record/findDeviceList.json?cusNumber=" + cusNumber + "&type=" + type;
		if (areaId != undefined || areaId != "") {
			url = url + "&areaId=" + areaId;
		}
		$("#gridLeftData").grid("reload", url);
	}

	var gridColModel_left = [ {
		name : "DEVICE_ID",
		hidden : true
	}, {
		name : "DEVICE_NAME",
		label : "待关联设备"
	}];

	var gridColModel_right = [ {
		name : "DMR_DEVICE_IDNTY",
		hidden : true
	}, {
		name : "DMR_DEVICE_TYPE",
		label : "设备类型",
		align : "center",
		width : 80,
		formatter : "convertCode",
        revertCode : true,
        formatoptions : { 'data':deviceTypeDate } 
	}, {
		name : "DMR_DEVICE_NAME",
		label : "设备名称"
	}, {
		name : "DMR_FAULT_CONTENT",
		label : "维修情况",
		hidden : true
	}, {
		name : "DMR_FAULT_MAINTAINER",
		label : "维修人",
		hidden : true
	}, {
		name : "DMR_FAULT_MAINTAIN_TIME",
		label : "维修时间",
		hidden : true
	} ];
 
	//保存关联设备信息
	function saveDevice() {
		var deviceData = $("#gridRightData").grid("getRowData");
		if (deviceData.length == 0) {
			$.messageQueue({ message : "请选择要关联的设备！", cls : "warning", iframePanel : true, type : "info" });
			return;
		}
	 
		$.ajax({
			type : "post",
			url : "${ctx}/deviceMaintain/record/save.json",
			data : JSON.stringify(deviceData),
			dataType : "json",
			contentType : 'application/json; charset=UTF-8',
			success : function(data) {
				if (data.success) {
					$.messageQueue({ message : "保存成功", cls : "success", iframePanel : true, type : "info" });
					$("#gridId_record").grid("reload");
					$("#dialogId_record").dialog("close");
					addRw();
				} else {
					$.messageQueue({ message : data.msg, cls : "warning", iframePanel : true, type : "info" });
				}
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$.messageQueue({ message : textStatus, cls : "warning", iframePanel : true, type : "info" });
			}
		});
	}
	
	/**
	* Modified By Yuan.ihong@20190422
	* 故障维修需要和事物督办关联起来，保存后，打开任务下发新增页面
	*/
	function addRw() {
		var url = "${ctx}/rwgl/rwxf/toEdit";
		$("#dialogId_rwxf").dialog({
			width : 1000,
			height : 600,
			title : "新增下发任务",
			url : url				 
		});
		$("#dialogId_rwxf").dialog("open");
		
	}
	
	function updateStatus() {
		var validFlag = $("#formId_rwxf_edit").form("valid");
		if(!validFlag) {
			return;
		}
		var formData = $("#formId_rwxf_edit").form("formData");
		formData.rwStatus = '1';
		$.loading({text:"正在处理中，请稍后..."});
		$.ajax({
			type : 'post',
			url : '${ctx}/rwgl/rwxf/saveOrUpdate',
			data: formData,
			dataType : 'json',
			success : function(data) {
				$.loading("hide");
				if(data.code == "1") {
					$.message({message:"操作成功！", cls:"success"});
					$("#dialogId_rwxf").dialog("close");
				} else {
					$.message({message:"操作失败！", cls:"error"});
				}				
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$.loading("hide");
				$.message({message:"操作失败！", cls:"error"});
			}
		});
	}
</script>
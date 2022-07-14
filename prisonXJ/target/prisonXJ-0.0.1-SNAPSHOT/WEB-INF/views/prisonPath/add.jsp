<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<%@taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<style>
.div_body_xhfz {
	height: 100%;
	width: 100%;
	text-align: center;
}

.div_content {
	float: left;
	width: 33%;
	height: 100%;
	text-align: center;
}

fieldset {
	height: 97%;
}
</style>
<div style="text-align: center; height: 100%; width: 100%">
	<div class="div_body_xhfz">
		<div class="div_content">
			<fieldset>
				<legend>路线</legend>
				<cui:form id="formId_addInfo" heightStyle="fill">
					<table class="table" style="width: 98%">
						<tr>
							<th>所属部门：</th>
							<td>
								<cui:combobox id="ppiDprtmntId" name="ppiDprtmntId" componentCls="form-control" required="true"></cui:combobox>
							</td>
						</tr>
						<tr>
							<th>路线名称：</th>
							<td>
								<cui:input name="ppiPathName" componentCls="form-control" required="true"></cui:input>
							</td>
						</tr>
						<tr>
							<th>起始区域：</th>
							<td>
								<cui:combotree id="comboId_areaId" name="ppiStartAreaId" componentCls="form-control" url="${ctx}" simpleDataEnable="true" simpleDataIdKey="id" simpleDataPIdKey="pid" keyName="name" required="true"></cui:combotree>
							</td>
						</tr>
						<tr>
							<th>结束区域：</th>
							<td>
								<cui:combotree id="comboId_areaId_" name="ppiEndAreaId" componentCls="form-control" url="${ctx}" simpleDataEnable="true" simpleDataIdKey="id" simpleDataPIdKey="pid" keyName="name" required="true"></cui:combotree>
							</td>
						</tr>
						<tr>
							<th>备注：</th>
							<td>
								<cui:textarea name="ppiRemark" componentCls="form-control"></cui:textarea>
							</td>
						</tr>

						<tr>
							<td colspan="2">
								<cui:button label="提交" text="false" onClick="add"></cui:button>
								<cui:button label="重置" text="false" onClick="reset"></cui:button>
							</td>
						</tr>
					</table>
				</cui:form>
			</fieldset>
		</div>
		<div class="div_content">
			<fieldset>
				<legend>待关联摄像头</legend>
				<div style="width: 100%; text-align: left;">
					<table style="width: 98%; padding: 2px;">
						<tr>
							<th>摄像头：</th>
							<td>
								<cui:input id="input_cameraName"></cui:input>
							</td>
						</tr>
						<tr style="margin-top: 4px">
							<th>区域：</th>
							<td>
								<cui:combotree id="combotree_camera" allowPushParent="false" url="${ctx}" simpleDataEnable="true" simpleDataIdKey="id" simpleDataPIdKey="pid" keyName="name">
								</cui:combotree>
							</td>
						</tr>
						<tr style="margin-top: 4px">
							<td colspan="2" style="text-align: center;">
								<cui:button onClick="queryCamera" label="查询"></cui:button>
								<cui:button onClick="resetCamera" label="重置"></cui:button>
								<cui:button onClick="togridPath" label="关联"></cui:button>
							</td>
						</tr>
					</table>
				</div>

				<cui:grid id="gridCamera" colModel="gridCamera" fitStyle="fill" multiselect="true" rowNum="999">
				</cui:grid>
			</fieldset>
		</div>

		<div class="div_content">
			<fieldset>
				<legend>已关联摄像头</legend>
				<div style="width: 100%; text-align: left;">
					<cui:button onClick="cancelRltn" label="取消关联"></cui:button>
				</div>
				<cui:grid id="gridPath" colModel="gridPath" fitStyle="fill" multiselect="true" rowNum="999">
				</cui:grid>
			</fieldset>
		</div>

	</div>

</div>

<script>
	$.parseDone(function() {
		//部门数据请求
		$("#ppiDprtmntId").combobox(
				"reload",
				"${ctx}/common/authsystem/findAllChildrenOrgByJyKeyForCombobox.json?cusNumber="
						+ cusNumber);
		var url = "${ctx}/common/areadevice/findForCombotree.json?cusNumber="
				+ cusNumber + "&deviceType=0";
		$("#comboId_areaId").combotree("tree").tree("reload", url);
		$("#comboId_areaId_").combotree("tree").tree("reload", url);
		$("#combotree_camera").combotree("tree").tree("reload", url);
	});

	//发送添加请求
	function add() {
		if ($("#formId_addInfo").form("valid")) {
			var pathData = {};
			var camera = $("#gridPath").grid("getRowData");
			if (camera.length == 0) {
				alert("带关联摄像头未选择！");
				return;
			}
			pathData['camera'] = JSON.stringify(camera);
			var path = $("#formId_addInfo").form("formData");
			path['ppiDprtmnt'] = $("#ppiDprtmntId").combobox("getText");
			path['ppiStartArea'] = $("#comboId_areaId").combotree("getText");
			path['ppiEndArea'] = $("#comboId_areaId_").combotree("getText");
			pathData['path'] = JSON.stringify(path);
			$.ajax({
				type : 'post',
				url : '${ctx}/prisonPath/saveInfo.json',
				data : JSON.stringify(pathData),
				dataType : 'json',
				contentType : 'application/json; charset=UTF-8',
				success : function(data) {
					if (data.success) {
						$.messageQueue({
							message : "保存成功",
							cls : "success",
							iframePanel : true,
							type : "info"
						});
						$("#gridId_prisonPath").grid("reload");
						$("#dialogId_prisonPath").dialog("close");
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
		} else {
			alert("请确认输入是否正确！");
		}
	}
	function reset() {
		$("#formId_addInfo").form("reset");
		$("#gridPath").grid("clearGridData");
	}

	function queryCamera() {
		var areaId = $("#combotree_camera").combotree("getValue");
		var cameraName = $("#input_cameraName").textbox("getText");
		var formData = {};
		formData['cusNumber'] = cusNumber;
		if (areaId != "") {
			formData['areaId'] = areaId;
		}
		if (cameraName != "") {
			formData['cameraName'] = cameraName;
		}
		$("#gridCamera").grid("option", "postData", formData);
		var url = "${ctx}/prisonPath/listAllForSx";
		$("#gridCamera").grid("reload", url);
	}

	function resetCamera() {
		$("#input_cameraName").textbox("setText", "");
		$("#combotree_camera").combotree("setText", "");
		$("#gridCamera").grid("clearGridData");
	}

	function togridPath() {
		var selected = $("#gridCamera").grid("option", "selarrrow");
		if (selected.length < 0) {
			$.messageQueue({
				message : "请选择需要关联的摄像头",
				cls : "warning",
				iframePanel : true,
				type : "info"
			});
			return;
		}
		for (var i = 0; i < selected.length; i++) {
			var cameraData = $("#gridCamera").grid("getRowData", selected[i]);
			var patnCameraDate = $("#gridPath").grid("getRowData");
			var len = patnCameraDate.length;
			for (var j = 0; j < patnCameraDate.length; j++) {
				if (patnCameraDate[j].PCR_CAMERA_ID == cameraData.ID) {
					return;
				}
			}
			var obj = new Object();
			obj.PCR_CAMERA_ID = cameraData.ID;
			obj.PCR_CAMERA = cameraData.DVC_NAME;
			$("#gridPath").grid("addRowData", len + 1, obj);
		}
		for (var i = selected.length; i > 0; i--) {
			$("#gridCamera").grid("delRowData", selected[i - 1]);
		}
	}

	//信号源取消关联，已经存库的删除操作，否则退回待关联列表
	function cancelRltn() {
		var selected = $("#gridPath").grid("option", "selarrrow");
		var ids = [];
		for (var i = selected.length; i > 0; i--) {
			var patnCameraDate = $("#gridPath").grid("getRowData",
					selected[i - 1]);
			if (patnCameraDate.ID == "") {
				$("#gridPath").grid("delRowData", selected[i - 1]);
				var cameraData = $("#gridCamera").grid("getRowData");
				var len = cameraData.length;
				var obj = new Object();
				obj.ID = patnCameraDate.PCR_CAMERA_ID;
				obj.DVC_NAME = patnCameraDate.PCR_CAMERA;
				$("#gridCamera").grid("addRowData", len + 1, obj);
			} else {
				ids.push(patnCameraDate.ID);
			}
		}
		if (ids.length > 0) {
			
		}
	}

	var pathType=[{text: "零星流动", value: "0"}, {text: "外来人车", value: "1"}];

	var gridPath = [ {
		label : "id",
		name : "ID",
		key : true,
		hidden : true
	}, {
		name : "PCR_CAMERA_ID",
		label : "摄像头id",
		hidden : true
	}, {
		name : "PCR_CAMERA",
		label : "摄像头",
	}, {
		name : "PCR_PATH_ID",
		label : "路线id",
		hidden : true
	}, {
		name : "PCR_REMARK",
		label : "备注",
		formatter : "text"
	} ];

	var gridCamera = [ {
		name : "ID",
		hidden : true
	}, {
		name : "DVC_NAME",
		label : "摄像头",
	} ];
</script>
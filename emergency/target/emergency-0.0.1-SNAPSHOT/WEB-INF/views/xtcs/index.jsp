<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<style>
	.coral-dialog .coral-dialog-buttonpane .coral-dialog-buttonset {
		background: #fff;
	}
</style>
<div style="height: 99%; margin: 0px 10px;">
	<!-- 查询条件 -->
	<cui:form id="formId_xtcs_query">
		<table class="table">
			<tr>
				<th>参数编码：</th>
				<td>
					<cui:input id="csbm" name="csbm"></cui:input>
				</td>
				<th>参数名称：</th>
				<td>
					<cui:input id="csmc" name="csmc"></cui:input>
				</td>
				<td>
					<cui:button label="查询" componentCls="btn-primary" onClick="search"/>
					<cui:button label="重置" onClick="reset"></cui:button>
				</td>
			</tr>
		</table>
	</cui:form>
	<!-- 工具栏 -->
	<div style="height: 40px;">
		<cui:toolbar id="toolbarId_xtcs" data="toolbar_xtcs"></cui:toolbar>
	</div>

	<!-- 数据列表 -->
	<cui:grid id="gridId_xtcs" rownumbers="true" multiselect="true" width="auto" fitStyle="fill" colModel="gridId_xtcs_colModelData" rownumWidth="80" rownumName="序号">
		<cui:gridPager gridId="gridId_xtcs"/>
	</cui:grid>

	<!-- 对话框 -->
	<cui:dialog id="dialogId_xtcs" autoOpen="false" resizable="false" iframePanel="true" reLoadOnOpen="true" modal="true" autoDestroy="true" >
	</cui:dialog>
</div>

<script>
	/**
	 * 页面加载完成后执行
	 */
	$.parseDone(function () {
		// 页面数据加载
		loadPage();
	});


    var combox_csz =[{"value":"0","text":"视频客户端"},{"value":"1","text":"视频插件"}];
	/**
	 * 工具栏
	 * @type {*[]}
	 */
	var toolbar_xtcs = [{
		"type": "button",
		"id": "btnId_add",
		"label": "增加",
		"onClick": "openDailog",
		"componentCls": "btn-primary"
	}, {
		"type": "button",
		"id": "btnId_edit",
		"label": "修改",
		"onClick": "openDailog",
		"componentCls": "btn-primary"
	}, {
		"type": "button",
		"id": "btnId_remove",
		"label": "删除",
		"onClick": "deleteByIds",
		"componentCls": "btn-primary"
	}];

	/**
	 * 数据列表
	 */
	var gridId_xtcs_colModelData = [{
		label: "id",
		name: "id",
		width: "70",
		hidden: true
	}, {
		label: "参数编码",
		name: "csbm",
		width: "70",
		align: "center"
	}, {
		label: "参数名称",
		name: "csmc",
		width: "85",
		align: "center"
	}, {
		label: "视屏播放类型",
		name: "csz",
		width: "100",
		align: "center",
        formatter:"convertCode",
        revertCode:true,
        formatoptions:{
            'data':combox_csz
        }
	}];

	/**
	 * 新增、更新对话框按钮
	 */
	var buttons_xtcs = [{
		text: "保存",
		id: "btnId_save",
		type: "button",
		componentCls: "btn-primary",
		click: function () {
			saveOrUpdate();
		}
	}, {
		text: "关闭",
		id: "btnId_cancel",
		click: function () {
			$("#dialogId_xtcs").dialog("close");
		}
	}];

	/**
	 * 打开对话框
	 * @param event
	 * @param ui
	 */
	function openDailog(event, ui) {
		var parentId = $("#formId_xtcs_query").find("#parentId").textbox("getValue");//上级指令编号

		var url = "";
		if (ui.id == "btnId_add") {
			url = "${ctx}/xtcs/toEdit";
		} else if (ui.id == "btnId_edit") {
			var selarrrow = $("#gridId_xtcs").grid("option", "selarrrow");
			if (selarrrow && selarrrow.length == 1) {
				url = "${ctx}/xtcs/toEdit?id=" + selarrrow[0];
			} else {
				$.message({message: "请选择一条记录！", cls: "waring"});
				return;
			}
		}
		$("#dialogId_xtcs").dialog({
			width: 350,
			height: 393,
			title: ui.label,
			url: url,
			buttons: buttons_xtcs
		});
		$("#dialogId_xtcs").dialog("open");
	}

	/**
	 * 页面数据加载
	 */
	function loadPage() {
		var url = "${ctx}/xtcs/queryWithPage";
		var params = {};

		// 初始化查询条件

		$('#gridId_xtcs').grid('option', 'postData', params);
		$("#gridId_xtcs").grid("reload", url);
	}

	/**
	 * 条件查询
	 */
	function search() {
		var formObj = $("#formId_xtcs_query");

		var csbm = formObj.find("#csbm").textbox("getValue");// 参数编码
		var csmc = formObj.find("#csmc").textbox("getValue");// 参数名称

		var params = {};
		if (csbm) {
			params["csbm"] = csbm;
		}
		if (csmc) {
			params["csmc"] = csmc;
		}
		$("#gridId_xtcs").grid("option", "postData", params);
		$("#gridId_xtcs").grid("reload");
	}

	/**
	 * 重置查询条件
	 */
	function reset() {
		var formObj = $("#formId_xtcs_query");
		formObj.find("#csbm").textbox("setValue", "");
		formObj.find("#csmc").textbox("setValue", "");
	}

	/**
	 * 保存或更新参数
	 */
	function saveOrUpdate() {
		var validFlag = $("#formId_xtcs_edit").form("valid");
		if (!validFlag) {
			return;
		}

		var url = "${ctx}/xtcs/saveOrUpdate.json";
		var formData = $("#formId_xtcs_edit").form("formData");

		$.loading({text: "正在处理中，请稍后..."});

		var callBack = function (data) {
			if (data.success) {
				$.loading("hide");
				$("#dialogId_xtcs").dialog("close");
				$.message({message: "操作成功！", cls: "success"});

				// 刷新页面
				search();

				// 初始化视频播放器类型
				initVideoPlayerType();
			} else {
				$.loading("hide");
				$.messageQueue({message: data.msg, cls: "warning", iframePanel: true, type: "info"});
			}
		};
		ajaxTodo(url, formData, callBack);
	}

	/**
	 * 删除参数
	 */
	function deleteByIds() {
		var selectedIds = "";
		var selectedIdArray = $("#gridId_xtcs").grid("option", "selarrrow");
		if (selectedIdArray != null && selectedIdArray.length > 0) {
			for(var i=0; i < selectedIdArray.length; i++) {
				selectedIds += selectedIdArray[i] + ",";
			}
			if(selectedIds != null && selectedIds != '') {
				selectedIds = selectedIds.substr(0, selectedIds.lastIndexOf(","));
			}
			$.confirm("确认删除？", function(r) {
				if(r) {
					var url = "${ctx}/xtcs/deleteByIds.json";
					var params = {};
					if(selectedIds) {
						params["ids"] = selectedIds;
					}

					$.loading({text: "正在处理中，请稍后..."});

					var callBack = function (data) {
						if (data.success) {
							$.loading("hide");
							$.message({message: "删除成功！", cls: "success"});

							// 刷新页面
							search();
						} else {
							$.loading("hide");
							$.messageQueue({message: data.msg, cls: "warning", iframePanel: true, type: "info"});
						}
					};
					ajaxTodo(url, params, callBack);
				}
			});
		} else {
			$.message({message: "请选择待删除的参数！", cls: "waring"});
		}
	}
</script>
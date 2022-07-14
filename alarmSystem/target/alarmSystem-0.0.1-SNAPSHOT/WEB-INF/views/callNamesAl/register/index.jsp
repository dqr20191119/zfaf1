<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.cesgroup.prison.common.facade.AuthSystemFacade"%>
<%@ page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<body style="height: 100%">
	<div id= "rlzc_con" style="height: 98%; margin: 5px">
		<cui:form id="formId_query">
			<table class="table">
				<tr>
					<th>监区：</th>
					<td>
						<cui:combobox  name="demptId" componentCls="form-control" data="combobox_data"></cui:combobox>
					</td>

					<th>楼层：</th>
					<td>
						<cui:combotree id="comboId_lch" name="lch" componentCls="form-control" url="${ctx}" simpleDataEnable="true" simpleDataIdKey="id" simpleDataPIdKey="pid" keyName="name" allowPushParent="false">
					    </cui:combotree>
					</td>

					<th>罪犯：</th>
					<td>
						<cui:input name="prisonerName" componentCls="form-control"></cui:input>
					</td>

					<td>
						<cui:button label="查询" onClick="query"></cui:button>
						<cui:button label="重置" onClick="reset"></cui:button>
					</td>
				</tr>
			</table>
		</cui:form>
		<cui:toolbar data="toolbar_rlzcDate"></cui:toolbar>
		<cui:grid id="gridId_rlzc" fitStyle="fill" multiselect="true" colModel="gridId_rlzc_colModelDate">
			<cui:gridPager gridId="gridId_rlzc" />
		</cui:grid>

	</div>
	<cui:dialog id="dialogId_rlzc" iframePanel="true" autoOpen="false" resizable="false" reLoadOnOpen="true" modal="true"></cui:dialog>
	<cui:dialog id="dialogId_ckzp" iframePanel="true" autoOpen="false" resizable="false" reLoadOnOpen="true" autoDestroy="true" modal="true">
		<div id="div_zfzp" style="height: 100%;width: 100%"></div>
	</cui:dialog>

	<cui:dialog id="dialogId_zpzc" iframePanel="true" autoOpen="false" resizable="false" reLoadOnOpen="true" autoDestroy="true" modal="true">
	</cui:dialog>
</body>
<script>
	var jsConst = window.top.jsConst;
	var userLev = jsConst.USER_LEVEL;
	var cusNumber = jsConst.CUS_NUMBER;//监狱号
	var combobox_data = <%=AuthSystemFacade.getAllChildrenOrgJsonInfoByOrgKey(null)%>;
	$.parseDone(function() {
		var url = "${ctx}/callNames/register/listAll.json?cnrCusNumber=" + cusNumber;
		$("#gridId_rlzc").grid("reload", url);
		//加载区域信息
		$("#comboId_lch").combotree("tree").tree( "reload", "${ctx}/common/areadevice/findForCombotree.json?cusNumber=" + jsConst.CUS_NUMBER + "&deviceType=0");
	});

	var gridId_rlzc_colModelDate = [
			{
				name : "ID",
				label : "ID",
				key : true,
				hidden : true
			},
			{
				name : "PDB_DEMPT_ID",
				label : "部门",
				formatter : "convertCode",
		        revertCode : true,
		        formatoptions : { 'data':combobox_data} 
			},
			{
				name : "PBD_AREA_ID",
				label : "楼层编号",
				hidden : true
			},
			{
				name : "ABD_AREA_NAME",
				label : "楼层"
			},
			{
				name : "PBD_CELL_INDC",
				label : "监舍"
			},
			{
				name : "CNR_PRISONER_INDC",
				label : "罪犯编号",
				hidden : true
			},
			{
				name : "PBD_PRISONER",
				label : "罪犯"
			},
			{
				name : "CNR_IMG_ID",
				label : "注册状态",
				align : "center",
				formatter : function(cellvalue, options, rawObject) {
					if (cellvalue && rawObject.CNR_IMG_URL) {
						return "注册成功";
					} else {
						return "<button class=\"ctrl-init ctrl-init-button coral-button coral-component coral-state-default coral-corner-all coral-button-text-only\" onClick= \"zpzc('"
								+ rawObject.ID + "')\">点击注册</button>";
					}
				}
			}, {
				name : "CNR_IMG_URL",
				label : "照片",
				align : "center",
				formatter : "formatter_ckzp"
			}, {
				name : "CNR_IMG_NAME",
				label : "照片name",
				hidden : true
			}, {
				name : "CNR_IMG_SIZE",
				label : "照片大小",
				hidden : true
			} ];

	function formatter_ckzp(cellValue, options, rowObject) {
		var param1 = rowObject.ID;
		var param2 = rowObject.CNR_IMG_URL;
		var param3 = rowObject.CNR_IMG_NAME;
		var param4 = rowObject.CNR_IMG_SIZE;
		var result = null;
		if (param2) {
			result = "<button class=\"ctrl-init ctrl-init-button coral-button coral-component coral-state-default coral-corner-all coral-button-text-only\" onClick= \"ckzp('"
					+ param2 + "','" + param3 + "','" + param4 + "')\">查看</button>";
		} else {
			result = "<span style=\"color: red;\"><strong>未注册，请注册</strong></span>"
		}
		return result;
	}
	
	function hideDiv_zfzp() {
		$("#dialogId_ckzp").loading("hide");
	}

	//查看照片
	function ckzp(imgUrl, imgName, imgSize) {
		$("#dialogId_ckzp").dialog({
			width : 450,
			height : 450,
			title : '罪犯正面照',
		});
		$("#div_zfzp").empty();
		$("#dialogId_ckzp").loading({ text : "正在请求罪犯照片数据，请稍后..."});
		var $img = $('<img id="img_zfzp" border="0" align="middle" src="" onload="hideDiv_zfzp();"  width="100%" height="100%" alt="罪犯照片遗失，请注销后重新注册上传！"></img>')
		$("#div_zfzp").append($img);
		$("#img_zfzp").attr( "src", "${ctx}/callNames/register/getZfPicInfo?imgUrl=" + imgUrl + "&imgName=" + imgName + "&imgSize=" + imgSize);
		$("#dialogId_ckzp").dialog("open");
	}
	 
	
	
	function zpzc(recordId) {
		$("#dialogId_zpzc").dialog({
			url : "${ctx}/callNames/register/openDialog/uploadAndRegister?id=" + recordId,
			width : 450,
			height : 450,
			title : '注册',
		});
		$("#dialogId_zpzc").dialog("open");
	}

	toolbar_rlzcDate = [ {
		"type" : "button",
		"id" : "btnId_add",
		"label" : "批量注册",
		"onClick" : "openDailog"
	},{
		"type" : "button",
		"id" : "btnId_cancel",
		"label" : "注销",
		"onClick" : "logoutRlzc"
	},{
		"type" : "button",
		"id" : "btnId_del",
		"label" : "删除",
		"onClick" : "remove"
	},{
		"type" : "button",
		"id" : "btnId_sx",
		"label" : "刷新缓存",
		"onClick" : "refresh_hc"
	}];
	
	function refresh_hc() {
		$("#rlzc_con").loading({ text : "正在刷新，请稍后..."});
		$.ajax({
			url : "${ctx}/callNames/register/refresh.json",
			dataType : "json",
			type : "post",
			data : {'cusNumber' : cusNumber },
			success : function(data) {
				$("#rlzc_con").loading("hide");
				if (data.success) {
					$.messageQueue({ message : data.msg, cls : "success", iframePanel : true, type : "info" });
				} else {
					$.messageQueue({ message : data.msg, cls : "warning", iframePanel : true, type : "info" });
				}
			}
		});
	}
	

	function query() {
		var formData = $("#formId_query").form("formData");
		$('#gridId_rlzc').grid('option', 'postData', formData);
		$("#gridId_rlzc").grid("reload");
	}

	//重置按钮事件
	var reset = function() {
		$("#formId_query").form("reset");
	}

	function openDailog(event, ui) {
		var dialog_width = 0;
		var dialog_height = 0;
		var dialog_name;
		var url;
		switch (ui.id) {
		case "btnId_add":
			dialog_width = "1100";
			dialog_height = "600";
			url = "${ctx}/callNames/register/openDialog/add";
			break;
		case "btnId_update":
			var selected = $("#gridId_rlzc").grid("option", "selarrrow");
			if (selected.length == 1) {
				var rowData = $("#gridId_rlzc").grid("getRowData", selected[0])
				dialog_width = "360";
				dialog_height = "210";
				url = "${ctx}/callNames/register/openDialog/update?id=" + rowData.ID;
			} else {
				$.messageQueue({ message : "请选择一条要修改的记录", cls : "warning", iframePanel : true, type : "info" });
			}
			break;
		default:
			break;
		}

		if (dialog_width != 0 && dialog_height != 0) {
			$("#dialogId_rlzc").dialog({
				width : dialog_width,
				height : dialog_height,
				url : url,
				title : ui.label,
			});
			$("#dialogId_rlzc").dialog("open");
		}
	}

	function remove() {
		var selected = $("#gridId_rlzc").grid("option", "selarrrow");
		if (selected.length > 0) {
			var ids = []; //数据库删除
			var zfbhs = []; //缓存删除
			for (var i = 0; i < selected.length; i++) {
				if($("#gridId_rlzc").grid("getRowData", selected[i]).CNR_IMG_ID != '注册成功'){
					ids.push(selected[i]);
					zfbhs.push($("#gridId_rlzc").grid("getRowData", selected[i]).CNR_PRISONER_INDC);
				}
			}
			if(ids.length == 0){
				$.messageQueue({ message : "请选择未注册的记录", cls : "warning", iframePanel : true, type : "info" });	
				return;
			}
			var data ={};
			data['ids'] = ids;
			data['zfbhs'] = zfbhs;
			$.confirm("是否删除选中的记录,已经注册成功的用户请注销后删除", "信息确认", function(confirm) {
				if (confirm) {
					$.ajax({
						url : "${ctx}/callNames/register/delete.json",
						dataType : "json",
						type : "post",
						data : JSON.stringify(data),
						contentType : "application/json",
						success : function(data) {
							if (data.success) {
								$("#gridId_rlzc").grid("reload");
								$.messageQueue({ message : data.msg, cls : "success", iframePanel : true, type : "info" });
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
	
	//注销
	function logoutRlzc() {
		var selected = $("#gridId_rlzc").grid("option", "selarrrow");
		if (selected.length > 0) {
			var ids = [];
			for (var i = 0; i < selected.length; i++) {
				if($("#gridId_rlzc").grid("getRowData", selected[i]).CNR_IMG_ID == '注册成功'){
					ids.push(selected[i]);
				}
			}
			if(ids.length == 0){
				$.messageQueue({ message : "请选择注册成功的记录", cls : "warning", iframePanel : true, type : "info" });	
				return;
			}
			$.confirm("是否注销选中的记录,该操作自动过滤未注册罪犯", "信息确认", function(confirm) {
				if (confirm) {
					$("#rlzc_con").loading({ text : "正在注销，请稍后..."});
					$.ajax({
						url : "${ctx}/callNames/register/logout.json",
						dataType : "json",
						type : "post",
						data : JSON.stringify(ids),
						contentType : "application/json",
						success : function(data) {
							$("#rlzc_con").loading("hide");
							if (data.success) {
								$("#gridId_rlzc").grid("reload");
								$.messageQueue({ message : data.msg, cls : "success", iframePanel : true, type : "info" });
							} else {
								$.messageQueue({ message : data.msg, cls : "warning", iframePanel : true, type : "info" });
							}
						}
					});
				}
			});
		} else {
			$.messageQueue({ message : "请选择要注销的记录", cls : "warning", iframePanel : true, type : "info" });
		}
	}

	function isEmpty(_v) {
		if (_v == undefined)
			return true;
		if (_v == null)
			return true;
		if (_v == "undefined")
			return true;
		if (_v == "")
			return true;
		return false;
	}
</script>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<%@taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<body style="height: 100%">
	<div style="height: 98%; margin: 5px">
		<cui:form id="formId_gb_query">
			<table class="table">
				<tr>
					<th>广播内容：</th>
					<td>
						<cui:input name="text" componentCls="form-control"></cui:input>
					</td>
					<td>
						<cui:button label="查询" onClick="gb_query"></cui:button>
						<cui:button label="重置" onClick="gb_clear"></cui:button>
					</td>
				</tr>
			</table>
		</cui:form>
		<cui:toolbar id="toolbarId_broadcastPlay" data="toolbar_broadcastPlayDate"></cui:toolbar>
		<cui:grid id="gridId_broadcastPlay" fitStyle="fill" multiselect="true" colModel="gridId_broadcastPlay_colModelDate">
			<cui:gridPager gridId="gridId_broadcastPlay" />
		</cui:grid>
	</div>
	<cui:dialog id="dialogId_broadcastPlay" autoOpen="false" resizable="false" reLoadOnOpen="true" modal="true"></cui:dialog>
</body>
<script>
	var jsConst = window.top.jsConst;
	var cusNumber = jsConst.CUS_NUMBER;//监狱号
	var gb_sttsData = <%=CodeFacade.loadCode2Json("4.20.12")%>;//状态
	var gb_brandData = <%=CodeFacade.loadCode2Json("4.20.35")%>;//品牌
	
	$.parseDone(function() {
		var url = "${ctx}/broadcastPlay/listAll.json";
		$("#gridId_broadcastPlay").grid("reload", url);
	});
	
	var gridId_broadcastPlay_colModelDate = [{
		label : "id",
		name : "id",
		key : true,
		hidden : true
	}, {
		label : "ttsId",
		name : "ttsId",
		key : true,
		hidden : true
	}, {
		name : "voice",
		label : "语言类型",
		align : "center",
	}, {
		name : "voiceName",
		label : "名称",
		align : "center",
		width : 250
	}, {
		name : "text",
		label : "广播内容",
		align : "center",
		width : 250
	}, {
		label : "操作",
		name : "operate",
		align : "center",
		width : "85",
		formatter : "operateFormatter"
	}];

	toolbar_broadcastPlayDate = [ {
		"type" : "button",
		"id" : "btnId_add",
		"label" : "增加",
		"onClick" : "openDailog",
		//"componentCls" : "btn-primary"
	}/* , {
		"type" : "button",
		"id" : "btnId_update",
		"label" : "修改",
		"onClick" : "openDailog",
		//"componentCls" : "btn-primary"
	}, {
		"type" : "button",
		"id" : "btnId_del",
		"label" : "删除",
		"onClick" : "remove",
		//"componentCls" : "btn-primary"
	} */];

	function openDailog(event, ui) {
		var dialog_width = 0;
		var dialog_height = 0;
		var url;
		switch (ui.id) {
		case "btnId_add":
			dialog_width = "640";
			dialog_height = "360";
			url = "${ctx}/broadcastPlay/openDialog/save";
			break;

		case "btnId_update":
			var selected = $("#gridId_broadcastPlay").grid("option", "selarrrow");
			if (selected.length == 1) {
				var rowData = $("#gridId_broadcastPlay").grid("getRowData", selected[0])
				dialog_width = "640";
				dialog_height = "360";
				url = "${ctx}/broadcastPlay/openDialog/update?id=" + rowData.id;
			} else {
				$.messageQueue({ message : "请选择一条要修改的记录", cls : "warning", iframePanel : true, type : "info" });
			}
			break;

		default:
			break;
		}

		if (dialog_width != 0 && dialog_height != 0) {
			$("#dialogId_broadcastPlay").dialog({
				width : dialog_width,
				height : dialog_height,
				url : url,
				title : ui.label,
			});
			$("#dialogId_broadcastPlay").dialog("open");
		}
	}

	function remove() {
		var selected = $("#gridId_broadcastPlay").grid("option", "selarrrow");
		console.info(JSON.stringify(selected));
		if (selected.length > 0) {
			$.confirm("是否删除选中的记录", "信息确认", function(confirm) {
				if (confirm) {
					$.ajax({
						url : "${ctx}/broadcastPlay/delete.json",
						dataType : "json",
						type : "post",
						data : JSON.stringify(selected),
						contentType : "application/json",
						success : function(data) {
							if (data.success) {
								$("#gridId_broadcastPlay").grid("reload");
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
	function gb_query() {
		var formData = $("#formId_gb_query").form("formData");
		$("#gridId_broadcastPlay").grid("option", "postData", formData);
		$("#gridId_broadcastPlay").grid("reload");
		//关闭当前弹窗		
		$("#dialogId_broadcastPlay").dialog("close");
	}

	function gb_clear() {
		$("#formId_gb_query").form("clear");
	}
	/**
	 * 操作栏初始化
	 */
	function operateFormatter(cellValue, options, rowObject) {
		//var result = '<a href="" style="color: #4692f0;" onclick="getAlarmInfo(\''+param1.toString()+'\');return false;">查看</a>';
		var result = "<button class='ctrl-init ctrl-init-button coral-button coral-component coral-state-default coral-corner-all coral-button-text-only' onClick='startPlay(\"" + rowObject.id + "\")'>播放</button>";
		
		return result;
	}
	/**
	 * 播放广播
	 */
	function startPlay(playId) {
		
		var ur = '${ctx}/broadcastPlay/startPlay';
		$.ajax({
			type : 'post',
			url : ur,
			data : {
				playId:playId
			},
			dataType : 'json',
			success : function(data) {
	            if(data.code == 200){
	                alert("播放成功");
	            }else if(data.code == 500){
	                alert("播放失败");
	            }
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				alert(textStatus);
			}
		});
	}
</script>
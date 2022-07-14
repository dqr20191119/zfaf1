<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<%@taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<body style="height: 100%">

	<div style="height: 98%; margin: 5px">
		<cui:form id="formId_swjd_query">
			<table class="table">
				<tr>
					<th>填报日期：</th>
					<td colspan="3">
						<cui:datepicker id="startTime" name ="startTime"  dateFormat="yyyy-MM-dd HH:mm:ss" showOn="button"></cui:datepicker>
				 			~ 
						<cui:datepicker id="endTime" name ="endTime" startDateId="startTime" dateFormat="yyyy-MM-dd HH:mm:ss" showOn="button"></cui:datepicker>
					</td>
					<th>填报时限：</th>
					<td>
						<cui:combobox name="dmaMaintainTerm" data="maintainTerm"></cui:combobox>
					</td>
				</tr>
	 			<tr>
					<th>故障类型:</th>
					<td>
						<cui:combobox id="gzlx" name="dmaFaultType" ></cui:combobox>
					</td>
					<td>
						<cui:button label="查询" onClick="swjd_query"></cui:button>
						<cui:button label="重置" onClick="swjd_reset"></cui:button>
	
					</td>
				</tr>
			</table>
		</cui:form>
		<cui:toolbar id="toolbarId" data="toolbar_localDate"></cui:toolbar>
		<cui:grid id="gridId_affairsOversee" rownumbers="false" singleselect="true" fitStyle="fill" width="auto" colModel="gridId_affairsOversee_colModelDate">
			<cui:gridPager gridId="gridId_affairsOversee" />
		</cui:grid>
	</div>
	<cui:dialog id="dialogId_swjd" autoOpen="false" resizable="false" reLoadOnOpen="true" modal="true"></cui:dialog>
</body>
<script>
	var jsConst = window.top.jsConst;
	var cusNumber = jsConst.CUS_NUMBER;//监狱号
	var sttsIndc = <%=CodeFacade.loadCode2Json("4.20.28")%>;//填报状态
	var maintainTerm = <%=CodeFacade.loadCode2Json("4.20.29")%>;//维修时限

	$.parseDone(function() {
		$("#gzlx").combobox( "reload", "${ctx}/deviceFaultType/seachComboData.json?cusNumber=" + cusNumber + "&typeClassify=1&sttsIndc=1");
		var url = "${ctx}/deviceMaintain/listAll.json?dmaCusNumber=" + cusNumber + "&actionType=oversee&P_orders=dma_fault_submit_time,desc";
		$("#gridId_affairsOversee").grid("reload", url);

	});

	var gridId_affairsOversee_colModelDate = [ {
		label : "id",
		name : "ID",
		hidden : true
	}, {
		label : "填报单位",
		name : "DMA_DPRMNT_NAME"
	}, {
		label : "故障类型",
		name : "DMA_FAULT_TYPE_CH"
	}, {
		label : "故障内容",
		name : "DMA_FAULT_CONTENT_CH"
	}, {
		label : "详细描述",
		name : "DMA_FAULT_DESC"
	}, {
		label : "填报状态",
		name : "DMA_STTS_INDC",
		formatter : "convertCode",
		revertCode : true,
		formatoptions : {
			'data' : sttsIndc
		}
	}, {
		label : "填报时间",
		name : "DMA_FAULT_SUBMIT_TIME"
	}, {
		label : "填报人",
		name : "DMA_FAULT_SUBMITTER"
	}, {
		label : "维修时限",
		name : "DMA_MAINTAIN_TERM",
		formatter : "convertCode",
		revertCode : true,
		formatoptions : {
			'data' : maintainTerm
		}
	}, {
		label : "监督提醒状态",
		name : "DMA_REMIND_STTS",
		formatter : function(cellvalue, options, rawObject) {
			if (cellvalue == 0) {
				return '未提醒';
			} else if (cellvalue == 1) {
				return '已提醒';
			} else {
				return cellvalue;
			}
		}
	}, {
		label : "维修负责人",
		name : "DMA_MAINTAIN_PERSON"
	} ];

	toolbar_localDate = [ {
		"type" : "button",
		"id" : "btnId_tx",
		"label" : "提醒",
		"onClick" : "openDailog",
		//"componentCls" : "btn-primary"
	} ];

	function openDailog(event, ui) {
		var dialog_width;
		var path;
		var url = 0;
		var dialog_name;
		switch (ui.id) {
		case "btnId_tx":
			var selrow = $("#gridId_affairsOversee").grid("option", "selrow");
			if (selrow != null) {
				var rowData = $("#gridId_affairsOversee").grid("getRowData", selrow.toString())
				dialog_width = "800";
				dialog_height = "450";
				url = "${ctx}/deviceMaintain/openDialog/remind?id=" + rowData.ID;
			} else {
				$.messageQueue({ message : "请先勾选需要处理记录！", cls : "warning", iframePanel : true, type : "info" });
			}
			break;
		default:
			break;
		}
		if (url != 0) {
			$("#dialogId_swjd").dialog({
				width : dialog_width,
				height : dialog_height,
				title : ui.name,
				url : url,
				title : ui.label,
				onClose : function() { //回调事件
					//alert(ui.id);
				}
			});

			$("#dialogId_swjd").dialog("open");
		}
	}
	function swjd_query() {
		var formData = $("#formId_swjd_query").form("formData");
		$("#gridId_affairsOversee").grid("option", "postData", formData);
		$("#gridId_affairsOversee").grid("reload");
	}

	function swjd_reset() {
		$("#formId_swjd_query").form("clear");
	}
</script>
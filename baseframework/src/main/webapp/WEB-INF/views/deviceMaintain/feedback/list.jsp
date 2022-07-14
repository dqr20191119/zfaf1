<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<%@taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<body style="height: 100%">
	<div style="height: 98%; margin: 5px">
		<cui:form id="formId_swfk_query">
			<table class="table">
				<tr>
					<th>填报日期：</th>
					<td colspan="3">
						<cui:datepicker id="startTime" name ="startTime"  dateFormat="yyyy-MM-dd HH:mm:ss" showOn="button"></cui:datepicker>
				 			~ 
						<cui:datepicker id="endTime" name ="endTime" startDateId="startTime" dateFormat="yyyy-MM-dd HH:mm:ss" showOn="button"></cui:datepicker>
					</td>
					<th>故障类型:</th>
					<td>
						<cui:combobox id="gzlx" name="dmaFaultType" ></cui:combobox>
					</td>
					<td>
						<cui:button label="查询" onClick="swfk_query"></cui:button>
						<cui:button label="重置" onClick="swfk_reset"></cui:button>
	
					</td>
				</tr>
			</table>
		</cui:form>
		<cui:toolbar data="toolbar_localDate"></cui:toolbar>
		<cui:grid id="gridId_affairsFeedBack" rownumbers="false" singleselect="true" fitStyle="fill" width="auto" colModel="gridId_affairsFeedBack_colModelDate">
			<cui:gridPager gridId="gridId_affairsFeedBack" />
		</cui:grid>
	</div>
	<cui:dialog id="dialogId_feedBack" autoOpen="false" resizable="false" reLoadOnOpen="true" modal="true"></cui:dialog>
</body>
<script>
	var jsConst = window.top.jsConst;
	var cusNumber = jsConst.CUS_NUMBER;//监狱号
	var userId = jsConst.USER_ID;//当前登陆者
	var sttsIndc = <%=CodeFacade.loadCode2Json("4.20.28")%>;//填报状态
	
	$.parseDone(function() {
		$("#gzlx").combobox( "reload", "${ctx}/deviceFaultType/seachComboData.json?cusNumber=" + cusNumber + "&typeClassify=1&sttsIndc=1");
		//var url = "${ctx}/deviceMaintain/listAll.json?dmaCusNumber=" + cusNumber + "&dmaSttsIndc=3&dmaFaultSubmitter=" + userId + "&P_orders=dma_fault_submit_time,desc";
		var url = "${ctx}/deviceMaintain/listAll.json?dmaCusNumber=" + cusNumber + "&actionType=feedBack&P_orders=dma_fault_submit_time,desc";
		$("#gridId_affairsFeedBack").grid("reload", url);

	});

	var gridId_affairsFeedBack_colModelDate = [ {
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
		label : "维修负责人",
		name : "DMA_MAINTAIN_PERSON"
	}, {
		label : "维修处理情况",
		name : "DMA_MAINTAIN_DESC"
	}, {
		label : "维修结果",
		name : "DMA_MAINTAIN_RESULT"
	} ];

	toolbar_localDate = [ {
		"type" : "button",
		"id" : "btnId_fk",
		"label" : "反馈",
		"onClick" : "openDailog",
		//"componentCls" : "btn-primary"
	} ];

	function openDailog(event, ui) {
		var dialog_width = 0;
		var url;
		var dialog_name;
		switch (ui.id) {

		case "btnId_fk":
			var selrow = $("#gridId_affairsFeedBack").grid("option", "selrow");
			if (selrow != null) {
				var rowData = $("#gridId_affairsFeedBack").grid("getRowData", selrow.toString());
				var stts = $("#gridId_affairsFeedBack").grid("getCell", selrow.toString(), "DMA_STTS_INDC");
				if (stts == "3") {
					dialog_width = "800";
					dialog_height = "620";
					url = "${ctx}/deviceMaintain/openDialog/feedback/handle?id=" + rowData.ID;
				} else {
					$.messageQueue({ message : "请选择已完成的处理记录！", cls : "warning", iframePanel : true, type : "info" });
				}
			} else {
				$.messageQueue({ message : "请先勾选需要处理记录！", cls : "warning", iframePanel : true, type : "info" });
			}
			break;

		default:
			dialog_width = 0;
			break;
		}

		if (dialog_width != 0) {
			$("#dialogId_feedBack").dialog({
				width : dialog_width,
				height : dialog_height,
				title : ui.name,
				url : url,
				title : ui.label,
			});
			$("#dialogId_feedBack").dialog("open");
		}
	}
	function swfk_query() {
		var formData = $("#formId_swfk_query").form("formData");
		$("#gridId_affairsFeedBack").grid("option", "postData", formData);
		$("#gridId_affairsFeedBack").grid("reload");
	}

	function swfk_reset() {
		$("#formId_swfk_query").form("clear");
	}
</script>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<%@taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!-- 事务处理 -->
<div style="height: 100%;">
	<cui:form id="formId_swcl_query">
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
					<cui:button label="查询" onClick="swcl_query"></cui:button>
					<cui:button label="重置" onClick="swcl_reset"></cui:button>
				</td>
			</tr>
		</table>
	</cui:form>
	<cui:toolbar data="toolbar_swclDate"></cui:toolbar>
	<cui:grid id="gridId_swcl" rownumbers="false" singleselect="true" fitStyle="fill" width="auto" colModel="gridId_swcl_colModelDate">
		<cui:gridPager gridId="gridId_swcl" />
	</cui:grid>
</div>
<cui:dialog id="dialogId_swcl" autoOpen="false" resizable="false" reLoadOnOpen="true" modal="true"></cui:dialog>

<script>
	var jsConst = window.top.jsConst;
	var cusNumber = jsConst.CUS_NUMBER;//监狱号
	var userId = jsConst.USER_ID;//当前登陆者
	var dprmntId = jsConst.DEPARTMENT_ID;//部门ID
	//var policeCode = jsConst.POLICE_CODE;//警察编号
	//var policeCode = "3236279";//警察编号
	var sttsIndc = <%=CodeFacade.loadCode2Json("4.20.28")%>;//填报状态
	var maintainTerm = <%=CodeFacade.loadCode2Json("4.20.29")%>;//维修时限

	$.parseDone(function() {
		$("#gzlx").combobox( "reload", "${ctx}/deviceFaultType/seachComboData.json?cusNumber=" + cusNumber + "&typeClassify=1&sttsIndc=1");
		/* 	var url = "${ctx}/deviceMaintain/listAll.json?dmaCusNumber="
					+ cusNumber + "&dmaSttsIndc=1&damSttsIndcDone=2&policeIdnty="
					+ policeCode + "&P_orders=dma_fault_submit_time,desc"; */
		var url = "${ctx}/deviceMaintain/listAll.json?dmaCusNumber=" + cusNumber + "&actionType=handle&P_orders=dma_fault_submit_time,desc";
		$("#gridId_swcl").grid("reload", url);

	});

	var gridId_swcl_colModelDate = [ {
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
		label : "故障内容id",
		name : "DMA_FAULT_CONTENT",
	 	hidden : true
	},{
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
		label : "维修负责人",
		name : "DMA_MAINTAIN_PERSON"
	} ];

	toolbar_swclDate = [ {
		"type" : "button",
		"id" : "btnId_qsbl",
		"label" : "签收办理",
		"onClick" : "openDailog",
		//"componentCls" : "btn-primary"
	}, {
		"type" : "button",
		"id" : "btnId_blwc",
		"label" : "办理完成",
		"onClick" : "openDailog",
		//"componentCls" : "btn-primary"
	} ];

	function openDailog(event, ui) {
		var dialog_width = 0;
		var dialog_height = 0;
		var dialog_name;
		var url;

		switch (ui.id) {

		case "btnId_qsbl":
			var selrow = $("#gridId_swcl").grid("option", "selrow");
			if (selrow != null) {
				var rowData = $("#gridId_swcl").grid("getRowData", selrow.toString())
				if (!checkDprtmntByFaultContent(rowData.DMA_FAULT_CONTENT)){
					return;
				}
				if (rowData.DMA_STTS_INDC == "1") {
					dialog_width = "800";
					dialog_height = "370";
					url = "${ctx}/deviceMaintain/openDialog/signIn?id=" + rowData.ID;
				} else {
					$.messageQueue({ message : "请选择待签收的处理记录！", cls : "warning", iframePanel : true, type : "info" });
				}
			} else {
				$.messageQueue({ message : "请先勾选需要处理记录！", cls : "warning", iframePanel : true, type : "info" });
			}
			break;

		case "btnId_blwc":
			var selrow = $("#gridId_swcl").grid("option", "selrow");
			if (selrow != null) {
				var rowData = $("#gridId_swcl").grid("getRowData", selrow.toString())
				if (rowData.DMA_STTS_INDC == "2") {
					if (!checkDprtmntByFaultContent(rowData.DMA_FAULT_CONTENT)){
						return;
					}
					/* if(rowData.DMA_MAINTAIN_PERSON != policeCode){
						$.message({
							message : "请选择您本人签收的处理记录！",
							cls : "warning"
						});
						return;
					} */
					dialog_width = "800";
					dialog_height = "500";
					url = "${ctx}/deviceMaintain/openDialog/affairsDone?id=" + rowData.ID;
				} else {
					$.messageQueue({ message : "请选择已签收的处理记录！", cls : "warning", iframePanel : true, type : "info" });
				}
			} else {
				$.messageQueue({ message : "请先勾选需要处理记录！", cls : "warning", iframePanel : true, type : "info" });
			}
			break;

		default: 
			break;
		}

		if (dialog_width != 0 && dialog_height != 0) {
			$("#dialogId_swcl").dialog({
				width : dialog_width,
				height : dialog_height,
				title : ui.name,
				url : url,
				title : ui.label,
				onClose : function() { //回调事件
				}
			});

			$("#dialogId_swcl").dialog("open");
		}
	}
	function swcl_query() {
		var formData = $("#formId_swcl_query").form("formData");
		$("#gridId_swcl").grid("option", "postData", formData);
		$("#gridId_swcl").grid("reload");
	}

	function swcl_reset() {
		$("#formId_swcl_query").form("clear");
	}
	
	//根据维修内容id验证当前登录者是否属于维修部门
	function checkDprtmntByFaultContent(faultId) {
		var falg = false;
		
		var url = "${ctx}/deviceFaultType/findDprtmnt.json?cusNumber=" + cusNumber + "&faultId=" + faultId;
		$.ajax({
			type : "post",
			url : url,
			dataType : "json",
			async: false ,
			success : function(resData) {
				if(resData.success){
					 if (dprmntId == resData.obj.FDR_MAINTAIN_DPRTMNT_ID) {
						 falg = true;
					 } else {
						 $.messageQueue({ message : "非"+resData.obj.FDR_MAINTAIN_DPRTMNT+"人员无权处理，没有权限", cls : "warning", iframePanel : true, type : "info" });
					 }
				}  
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$.messageQueue({ message : textStatus, cls : "warning", iframePanel : true, type : "info" });
			}
		});
		
		return falg;
	}
</script>
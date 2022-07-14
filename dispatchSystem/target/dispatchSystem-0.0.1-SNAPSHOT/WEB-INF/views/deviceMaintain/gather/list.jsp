<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<body style="height: 100%">
	
	<div style="height: 98%; margin: 5px">
		<cui:form id="formId_swhz_query">
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
					<th>填报状态：</th>
					<td>
						<cui:combobox componentCls="form-control" name="dmaSttsIndc" data="sttsIndc"></cui:combobox>
					</td>
				</tr>
	 			<tr>
					<th>故障类型:</th>
					<td>
						<cui:combobox id="gzlx" name="dmaFaultType" ></cui:combobox>
					</td>
					<td>
						<cui:button label="查询" onClick="swhz_query"></cui:button>
						<cui:button label="重置" onClick="swhz_reset"></cui:button>
					</td>
				</tr>
			</table>
		</cui:form>
		<cui:grid id="gridId_affairsGather" rownumbers="false" singleselect="true" fitStyle="fill" width="auto" onDblClickRow="getRecordInfo" colModel="gridId_affairsGather_colModelDate">
			<cui:gridPager gridId="gridId_affairsGather" />
		</cui:grid>
	</div>
	<cui:dialog id="dialogId_swhz" autoOpen="false" resizable="false" reLoadOnOpen="true" modal="true" buttons="dia_button">
		<cui:form id="formId_info" heightStyle="fill">
			<table class="table table-bordered" style="width: 98%">
				<tr>
					<th>填报单位：</th>
					<td>
						<cui:input componentCls="form-control" readonly="true" name="DMA_DPRMNT_NAME"></cui:input>
					</td>
					<th>填报人：</th>
					<td>
						<cui:input componentCls="form-control" readonly="true" name="DMA_FAULT_SUBMITTER"></cui:input>
					</td>
					<th>填报时间：</th>
					<td>
						<cui:datepicker componentCls="form-control" readonly="true" dateFormat="yyyy-MM-dd HH:mm:ss" name="DMA_FAULT_SUBMIT_TIME"></cui:datepicker>
					</td>

				</tr>
				<tr>
					<th>填报状态：</th>
					<td>
						<cui:combobox componentCls="form-control" readonly="true" name="DMA_STTS_INDC" data="sttsIndc"></cui:combobox>
					</td>
					<th>故障类型：</th>
					<td>
						<cui:input componentCls="form-control" readonly="true" name="DMA_FAULT_TYPE_CH"></cui:input>
					</td>
					<th>故障内容：</th>
					<td>
						<cui:input componentCls="form-control" readonly="true" name="DMA_FAULT_CONTENT_CH"></cui:input>
					</td>
				</tr>
				<tr>
					<th>维修时限：</th>
					<td>
						<cui:combobox componentCls="form-control" readonly="true" name="DMA_MAINTAIN_TERM" data="maintainTerm"></cui:combobox>
					</td>
					<th>故障地点：</th>
					<td colspan="3">
						<cui:input componentCls="form-control" readonly="true" name="DMA_FAULT_ADDRS"></cui:input>
					</td>
				</tr>
				<tr>
					<th>详细描述：</th>
					<td colspan="5">
						<cui:textarea componentCls="form-control" readonly="true" name="DMA_FAULT_DESC"></cui:textarea>
					</td>
				</tr>
				<tr>
					<th>维修负责人</th>
					<td>
						<cui:input componentCls="form-control" readonly="true" name="DMA_MAINTAIN_PERSON"></cui:input>
					</td>
					<th>维修结果：</th>
					<td colspan="2">
						<cui:input componentCls="form-control" readonly="true" name="DMA_MAINTAIN_DESC"></cui:input>
					</td>
				</tr>
				<tr>
					<th>维修情况：</th>
					<td colspan="5">
						<cui:textarea componentCls="form-control" readonly="true" name="DMA_MAINTAIN_RESULT">
						</cui:textarea>
					</td>
				</tr>
				<tr>
					<th>反馈意见：</th>
					<td colspan="5">
						<cui:textarea id="textId_idea" name="DMA_DPRTMNT_IDEA" componentCls="form-control" readonly="true">
						</cui:textarea>
					</td>
				</tr>
			</table>
		</cui:form>
	</cui:dialog>
</body>
<script>
	var jsConst = window.top.jsConst;
	var cusNumber = jsConst.CUS_NUMBER;//监狱号
	var sttsIndc = <%=CodeFacade.loadCode2Json("4.20.28")%>;//填报状态
	var maintainTerm = <%=CodeFacade.loadCode2Json("4.20.29")%>;//维修时限

	$.parseDone(function() {
		$("#gzlx").combobox( "reload", "${ctx}/deviceFaultType/seachComboData.json?cusNumber=" + cusNumber + "&typeClassify=1&sttsIndc=1");
		var url = "${ctx}/deviceMaintain/listAll.json?dmaCusNumber=" + cusNumber + "&P_orders=dma_fault_submit_time,desc";
		$("#gridId_affairsGather").grid("reload", url);
	});
	var gridId_affairsGather_colModelDate = [ {
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
		label : "处理状态",
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
	}/* , {
		label : "维修部门",
		name : "MAINTAINDRPTMNT"
	} */, {
		label : "维修处理情况",
		name : "DMA_MAINTAIN_DESC"
	}, {
		label : "维修结果",
		name : "DMA_MAINTAIN_RESULT"
	}, {
		label : "反馈人",
		name : "DMA_DPRTMNT_LEADER"
	}, {
		label : "反馈意见",
		name : "DMA_DPRTMNT_IDEA"
	} ];

	var dia_button = [ {
		text : "关闭",
		width : 80,
		click : function() {
			$("#dialogId_swhz").dialog("close");
		}
	} ];

	//双击列表，后台请求数据，根据id获取报警记录信息
	function getRecordInfo(e, ui) {
		$('#formId_info').form("clear");
		$("#dialogId_swhz").dialog({
			width : 750,
			height : 600,
			title : '记录详情',
		});
		$("#dialogId_swhz").dialog("open");
		var rowData = $("#gridId_affairsGather").grid("getRowData", ui.rowId);
		var url = "${ctx}/deviceMaintain/findById.json?id=" + rowData.ID;
		$.ajax({
			type : 'post', //方法类型  
			url : url,//请求地址     
			dataType : 'json', //数据类型  
			success : callback
		//请求成功处理函数  
		});
		function callback(res) { //返回函数
			if (res.success) {
				$('#formId_info').form("load", res.obj);//刷新数据
			} else {
				$.messageQueue({ message : res.msg, cls : "warning", iframePanel : true, type : "info" });
			}
		}
	}
	function swhz_query() {
		var formData = $("#formId_swhz_query").form("formData");
		$("#gridId_affairsGather").grid("option", "postData", formData);
		$("#gridId_affairsGather").grid("reload");
	}

	function swhz_reset() {
		$("#formId_swhz_query").form("clear");
	}
</script>
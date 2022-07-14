<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<style>
.form-control {
	width: 100%;
}
.td_title{
	text-align: right;
	font-weight: bold;
}
.td_context{
}
</style>

<center>
		<table class="table table-fixed" style="width: 90%;">
			<tr>
				<td width="15%" class="td_title"><label>督察名称：</label></td>
				<td colspan="3" >
					<cui:input readonly="true"   componentCls="form-control" value="${inspect.inoInspectName}"  ></cui:input>
				</td>
			</tr>
			<tr>
				<td width="15%" class="td_title"><label>编校：</label></td>
				<td width="35%" class="td_context">
					<cui:input  readonly="true"  name="inoInspectBj" value="${inspect.inoInspectBj}" componentCls="form-control"></cui:input>
				</td>
				<td width="15%" class="td_title"><label>通报期数：</label></td>
				<td width="35%" class="td_context">
					<cui:input  readonly="true"  name="inoInspectPhase" value="${inspect.inoInspectPhase}" validType="integer" componentCls="form-control"></cui:input>
				</td>
			</tr>
			<tr>
				<td class="td_title"><label>督察时间：</label></td>
				<td class="td_context">
					<cui:datepicker readonly="true"  value="${inspect.inoInspectTime}" dateFormat="yyyy-MM-dd HH:mm:ss" componentCls="form-control"></cui:datepicker>
				</td>
				<td class="td_title"><label>通报监狱：</label></td>
				<td class="td_context">
					<cui:combobox url="${ctx}/common/authsystem/findAllJyForCombobox.json" componentCls="form-control" value="${inspect.inoNoticeCusNumber}" readonly="true"></cui:combobox>
				</td>
			</tr>
			<tr>
				<td class="td_title"><label>制度执行：</label></td>
				<td class="td_context" colspan="3">
					<cui:textarea readonly="true"  componentCls="form-control" >${inspect.inoRuleExecute}</cui:textarea>
				</td>
			</tr>
			<tr>
				<td class="td_title"><label>现场管理：</label></td>
				<td class="td_context" height="300px" colspan="3" style="border: 1px solid #3789EA;background-color: #F0F0F0">
					<cui:grid id="gridId_monitor_already" colModel="gridColModel_monitor"
					 width="auto" fitStyle="fill" pager="true">
					</cui:grid>	
				</td>
			</tr>
			<tr>
				<td class="td_title"><label>凌晨检查：</label></td>
				<td class="td_context"  colspan="3">
					<cui:textarea readonly="true" componentCls="form-control" >${inspect.inoMorningCheck}</cui:textarea>
				</td>
			</tr>
			<tr>
				<td class="td_title"><label>工作要求：</label></td>
				<td class="td_context" colspan="3">
					<cui:textarea  componentCls="form-control" readonly="true">${inspect.inoSuggesition}</cui:textarea>
				</td>
			</tr>
			<tr>
				<td class="td_title"><label>移交问题：</label></td>
				<td class="td_context" colspan="3">
					<cui:textarea readonly="true" componentCls="form-control" >${inspect.inoProblem}</cui:textarea>
				</td>
			</tr>
			<tr>
				<td class="td_title"><label>督察人员：</label></td>
				<td class="td_context">
					<cui:input readonly="true" componentCls="form-control" value="${inspect.iprPoliceNames}" ></cui:input>
				</td>
				<td class="td_title"><label>上报人员：</label></td>
				<td class="td_context">
					<cui:input readonly="true" componentCls="form-control" value="${inspect.inoCrteUserName}" ></cui:input>
				</td>
			</tr>
			<tr>
				<td class="td_title"><label>审核状态：</label></td>
				<td class="td_context">
					<cui:radiolist  value="${inspect.inoApprovalSttsIndc}" data="radiolistApprovalSttsIndc"  readonly="true"></cui:radiolist>
					<%-- <cui:input  componentCls="form-control" value="${inspect.inoApprovalSttsIndcName}"></cui:input> --%>
				</td>
				<td class="td_title"><label>审批领导：</label></td>
				<td class="td_context">
					<cui:input readonly="true" componentCls="form-control" value="${inspect.checkPoliceName}"  ></cui:input>
				</td>
			</tr>    
			<tr>
				<td class="td_title"><label>备注：</label></td>
				<td class="td_context" colspan="3">
					<cui:textarea readonly="true"  componentCls="form-control">${inspect.inoRemark}</cui:textarea>
				</td>
			</tr>
		</table>
</center>
<cui:dialog id="monitorDetailDialog" autoOpen="false" iframePanel="true" reLoadOnOpen="true" modal="true" resizable="false">
</cui:dialog>
<script type="text/javascript"
	src="${ctx}/static/module/evidence/evidence.js"></script>
<script type="text/javascript"
	src="${ctx}/static/module/evidence/messenger.js"></script>
<script>
	var radiolistApprovalSttsIndc=[{
		'value' : '0',
		'text' : '<font color="#00aeef">未提交</font>'
	},{
		'value' : '1',
		'text' : '<font color="#e69138">待审核</font>'
	},{
		'value' : '2',
		'text' : '<font color="#FF0000">不同意</font>'
	}, {
		'value' : '3',
		'text' : '<font color="green">同意</font>'
	}];
	
	var gridColModel_monitor=[ {
		label : "id",
		name : "ID",
		hidden : true,
		key:true
	}, {
		label : "监督单名称",
		width : 250,
		align : "center",
		name : "MDO_MONITOR_NAME"
	}, {
		label : "创建时间",
		align : "center",
		hidden : true,
		name : "MDO_CRTE_TIME"
	},{
		label : "操作",
		align:"center",
		width : 80,
		formatter:"formatterDetail",
	} ];
	
	$.parseDone(function(){
		$("#gridId_monitor_already").grid("reload","${ctx }/monitor/searchMonitorByInspectId.json?inspectId=${inspect.id}");
	});
	
	function detailedMonitor(selrow){
		if (selrow != null) {
			$("#monitorDetailDialog").dialog({
				width : 700,
				height : "auto",
				subTitle : '监督单详细',
				url : '${ctx}/monitor/edit?id='+selrow.toString(),
			});
			//messenger.js
			messenger.isDetailShow=true;
			$("#monitorDetailDialog").dialog("open");
		} else {
			$.message({
				iframePanel:true,
				message : "请先勾选需要处理记录！",
				cls : "warning"
			});
		}
	}
	function formatterDetail(cellValue,options,rowObject){
		var result="<span><a href='#' style='color: #4692f0;' onclick='detailedMonitor(\""+rowObject.ID+"\");return false;'>详细</a></span>";
		return result;
	}
</script>
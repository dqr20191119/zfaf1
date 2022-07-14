<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />


<style>
.form-control {
	width: 100%;
}
</style>


<div>
	<cui:form id="formId_inspect_localrecord">
		<table class="table">
			<tr>
				<td>开始日期：</td>
				<td><cui:datepicker id="startTime" componentCls="form-control" dateFormat="yyyy-MM-dd HH:mm:ss" showOn="button"></cui:datepicker></td>
				<td>~</td>
				<td>结束日期：</td>
				<td><cui:datepicker id="endTime" componentCls="form-control" startDateId="startTime" dateFormat="yyyy-MM-dd HH:mm:ss" showOn="button"></cui:datepicker></td>
				<td><cui:button cls="cyanbtn" label="查询"  onClick="localrecord.search" componentCls="coral-btn-blue" /></td>
				<td><cui:button id="resetBtn" label="重置" onClick="localrecord.resetHandler"></cui:button></td>
			</tr>
		</table>
	</cui:form>
</div>
<cui:button label="详细" onClick="localrecord.inspectDetail" cls="greenbtn"></cui:button>
<!-- 自定义查询结束 -->

<cui:grid id="gridId_inspect_localrecord"  singleselect="true" colModel="gridColModel_inspect_localrecord"
shrinkToFit="true" fitStyle="fill" datatype="json" url=""
	pager="true"></cui:grid>	

<cui:dialog id="inspectdetaildialog" autoOpen="false" iframePanel="true" reLoadOnOpen="true" modal="true" resizable="false">
</cui:dialog>		

<script type="text/javascript">
    
    var approvalSttsIndc_json=[{
		'value' : '0',
		'text' : '待审核'
	}, {
		'value' : '1',
		'text' : '不同意'
	}, {
		'value' : '2',
		'text' : '同意'
	}];
    
    var gridColModel_inspect_localrecord=[ {
		label : "id",
		name : "ID",
		width : 100,
		hidden : true,
		key : true
	}, {
		label : "督察时间",
		name : "INL_INSPECT_TIME",
		width : 200
	}, {
		label : "督察名称",
		width : 200,
		name : "INL_INSPECT_NAME"
	}, {
		label : "编辑人",
		width : 150,
		name : "INL_CRTE_USER_NAME"
	}, {
		label : "编辑时间",
		width : 150,
		name : "INL_CRTE_TIME",
	}, {
		label : "审批人",
		width : 150,
		name : "ICP_POLICE_NAME"
	}, {
		label : "审批状态",
		name : "INL_APPROVAL_STTS_INDC",
		width : 100,
		formatter : 'convertCode',
		revertCode : true,
		formatoptions : {
			'dataStructure' : 'list',
			'data' : approvalSttsIndc_json
		}
	}];
    
	$.parseDone(function(){
		var jsConst = window.top.jsConst;
		var userId = jsConst.USER_ID;
		var cusNumber = jsConst.CUS_NUMBER;
		queryNoticeCheckList(userId,cusNumber);
		
		localrecord = {
			search:function(){
				if(localrecord.checkForm()){
					var postData = {};
					var startTime = $('#startTime').datepicker("getDateValue");
					var endTime = $('#endTime').datepicker("getDateValue");
					if (startTime != "") {
						postData['startTime'] = startTime;
					}
					if (endTime != "") {
						postData['endTime'] = endTime;
					}
					postData['inlCrteUserId']=userId;
					postData['inlCusNumber']=cusNumber;
					$('#gridId_inspect_localrecord').grid('option', 'postData', postData);
					$('#gridId_inspect_localrecord').grid('reload');
				}
			},
			checkForm:function(){
				var flag = true;
				
				return flag;
			},
			inspectDetail:function(){
					var selrow = $('#gridId_inspect_localrecord').grid("option", "selrow");//获取选中行的id
					var rowData = $('#gridId_inspect_localrecord').grid("getRowData", selrow);
					if(selrow != null){
						var url= '${ctx}/inspectlocal/inspectdetailDialog?id='+selrow;
						/* if(rowData.inlApprovalSttsIndc=='0'){
							url= '${ctx}/inspect/inspectdetailUpdateDialog?id='+selrow;
						}else{
							url= '${ctx}/inspect/inspectdetailDialog?id='+selrow;
						} */
						$("#inspectdetaildialog").dialog({
							width : 800,
							height : 580,
							subTitle : '督察通报详情',
							url : url
						});
						$("#inspectdetaildialog").dialog("open");
					}else{
						$.alert({
							message:"请选择一条记录",
							title:"信息提示",
							iframePanel:true
						});
					}
			},
			resetHandler:function(){
				$('#formId_inspect_localrecord').form("clear");
			}
			
		}
	});
	
	function queryNoticeCheckList(userId,cusNumber){
		var url = "${ctx}/inspectlocal/inspectlocalHzListPage.json";
		var data = {"inlCrteUserId":userId,"inlCusNumber":cusNumber};
		$("#gridId_inspect_localrecord").grid("option", "postData", data);
		$("#gridId_inspect_localrecord").grid("reload",url);
	}
	
</script>









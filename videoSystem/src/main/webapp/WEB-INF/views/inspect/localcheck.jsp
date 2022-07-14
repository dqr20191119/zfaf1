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
	<cui:form id="formId_inspect_localcheck">
		<table class="table">
			<tr>
				<td>督查开始日期：</td>
				<td><cui:datepicker id="startTime" componentCls="form-control" dateFormat="yyyy-MM-dd HH:mm:ss" showOn="button"></cui:datepicker></td>
				<td>督查结束日期：</td>
				<td><cui:datepicker id="endTime" componentCls="form-control" startDateId="startTime" dateFormat="yyyy-MM-dd HH:mm:ss" showOn="button"></cui:datepicker></td>
				<td><cui:button cls="cyanbtn" id="s_userSearchButton" label="查询" name="se" onClick="localcheck.search" componentCls="coral-btn-blue" /></td>
				<td><cui:button id="resetBtn" label="重置" onClick="resetHandler"></cui:button></td>
			</tr>
		</table>
	</cui:form>
</div>
<div style="padding-left: 10px;padding-bottom: 10px">
	<cui:button label="审核" onClick="localcheck.checkDetail" cls="greenbtn" 	></cui:button>
</div>

<!-- 自定义查询结束 -->

<cui:grid id="gridId_inspect_notice_record" singleselect="true"  colModel="gridColModel_inspect_notice"
shrinkToFit="true" fitStyle="fill" datatype="json" url=""
	pager="true"></cui:grid>	

<cui:dialog id="localcheckdetaildialog" autoOpen="false" iframePanel="true" reLoadOnOpen="true" modal="true" resizable="false">
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
    
    var gridColModel_inspect_notice=[ {
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
		queryNoticeCheckList(userId);
		
		localcheck = {
			search:function(){
				if(localcheck.checkForm()){
					var postData = {};
					var startTime = $('#startTime').datepicker("getDateValue");
					var endTime = $('#endTime').datepicker("getDateValue");
			
					if (startTime != "") {
						postData['startTime'] = startTime;
					}
					if (endTime != "") {
						postData['endTime'] = endTime;
					}
					postData['checkPoliceIdnty']=userId;
					postData['inlApprovalSttsIndc']='0';	//待审核
					$('#gridId_inspect_notice_record').grid('option', 'postData', postData);
					$('#gridId_inspect_notice_record').grid('reload');
				}
			},
			checkForm:function(){
				var flag = true;
				
				return flag;
			},
			checkDetail:function(){
				var selrow = $('#gridId_inspect_notice_record').grid("option", "selrow");//获取选中行的id
				if(selrow != null){
					$("#localcheckdetaildialog").dialog({
						width : 800,
						height : 580,
						subTitle : '现场督察通报审批',
						url : '${ctx}/inspectlocal/checkdetailDialog?id='+selrow+'&inlApprovalSttsIndc=0',
					});
					$("#localcheckdetaildialog").dialog("open");
				}else{
					$.alert({
						message:"请选择一条记录",
						title:"信息提示",
						iframePanel:true
					});
				}
			}
			
		}
	});
	
	function queryNoticeCheckList(userId){
		var url = "${ctx}/inspectlocal/inspectlocallistPage.json";
		var data = {"checkPoliceIdnty" : userId,"inlApprovalSttsIndc":0};
		$("#gridId_inspect_notice_record").grid("option", "postData", data);
		$("#gridId_inspect_notice_record").grid("reload",url);
	}
	
	function resetHandler() {
		$('#formId_inspect_check').form("clear");
	}
</script>









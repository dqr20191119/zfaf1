<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.cesgroup.prison.common.facade.AuthSystemFacade"%>	
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<style>
.form-control {
	width: 100%;
}
</style>


<div>
	<cui:form id="formId_inspect_check">
		<table class="table">
			<tr>
				<td>督查开始日期：</td>
				<td><cui:datepicker id="startTime" componentCls="form-control" dateFormat="yyyy-MM-dd HH:mm:ss" showOn="button"></cui:datepicker></td>
				<td>督查结束日期：</td>
				<td><cui:datepicker id="endTime" componentCls="form-control" startDateId="startTime" dateFormat="yyyy-MM-dd HH:mm:ss" showOn="button"></cui:datepicker></td>
				<td><cui:button cls="cyanbtn" id="s_userSearchButton" label="查询" name="se" onClick="check.search" componentCls="coral-btn-blue" /></td>
				<td><cui:button id="resetBtn" label="重置" onClick="resetHandler"></cui:button></td>
			</tr>
		</table>
	</cui:form>
</div>
<div style="padding-left: 10px;padding-bottom: 10px">
	<cui:button label="审核" onClick="check.checkDetail" cls="greenbtn" 	></cui:button>
</div>

<!-- 自定义查询结束 -->

<cui:grid id="gridId_inspect_notice_record" singleselect="true"  colModel="gridColModel_inspect_notice"
shrinkToFit="true" fitStyle="fill" datatype="json" url=""
	pager="true"></cui:grid>	

<cui:dialog id="checkdetaildialog" autoOpen="false" iframePanel="true" reLoadOnOpen="true" modal="true" resizable="false">
</cui:dialog>		

<script type="text/javascript">

	var combobox_jy = <%=AuthSystemFacade.getAllJyJsonInfo()%>;

	//0未提交 ，1已提交、待审核 ， 2不同意  ， 3 同意
    var approvalSttsIndc_json=[{
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
    
    var gridColModel_inspect_notice=[ {
		label : "id",
		name : "ID",
		width : 100,
		hidden : true,
		key : true
	}, {
		label : "督察时间",
		name : "INO_INSPECT_TIME",
		align:"center",
		width : 200
	}, {
		label : "通报监狱",
		width : 200,
		align:"center",
		name : "INO_NOTICE_CUS_NUMBER",
		formatter : 'convertCode',
		revertCode : true,
		formatoptions : {
			'dataStructure' : 'list',
			'data' : combobox_jy
		}
	}, {
		label : "督察名称",
		width : 200,
		align:"center",
		name : "INO_INSPECT_NAME"
	}, {
		label : "期数",
		align:"center",
		width : 75,
		name : "INO_INSPECT_PHASE"
	}, {
		label : "编辑人",
		align:"center",
		width : 125,
		name : "INO_CRTE_USER_NAME"
	}, {
		label : "审批状态",
		name : "INO_APPROVAL_STTS_INDC",
		width : 100,
		align:"center",
		formatter : 'convertCode',
		revertCode : true,
		formatoptions : {
			'dataStructure' : 'list',
			'data' : approvalSttsIndc_json
		}
	},{
		label : "操作",
		align:"center",
		width : 80,
		formatter:"formatterShow",
	}];
	function formatterShow(cellValue,options,rowObject){
		var result="<span><a href='#' style='color: #4692f0;' onclick='check.showDocument(\""+rowObject.ID+"\");return false;'>查看</a></span>";
		return result;
	}
	$.parseDone(function(){
		var jsConst = window.top.jsConst;
		var userId = jsConst.USER_ID;
		queryNoticeCheckList(userId);
		
		check = {
				showDocument:function(inspectId){
					window.open('${ctx}/xctb/index?inspectId='+inspectId);
				},
			search:function(){
				if(check.checkForm()){
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
					postData['inoApprovalSttsIndc']='1';	//0未提交 ，1已提交、待审核 ， 2不同意  ， 3 同意
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
					$("#checkdetaildialog").dialog({
						width : 800,
						height : 580,
						subTitle : '督察通报审批',
						url : '${ctx}/inspect/checkdetailDialog?id='+selrow+'&inoApprovalSttsIndc=1',
					});
					$("#checkdetaildialog").dialog("open");
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
		var url = "${ctx}/inspect/inspectSpListPage.json";
		var data = {"checkPoliceIdnty" : userId,"inoApprovalSttsIndc":1};
		$("#gridId_inspect_notice_record").grid("option", "postData", data);
		$("#gridId_inspect_notice_record").grid("reload",url);
	}
	
	function resetHandler() {
		$('#formId_inspect_check').form("clear");
	}
</script>









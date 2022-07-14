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
	<cui:form id="formId_inspect_record">
		<table class="table">
			<tr>
				<td>开始日期：</td>
				<td><cui:datepicker id="startTime" componentCls="form-control" dateFormat="yyyy-MM-dd HH:mm:ss" showOn="button"></cui:datepicker></td>
				<td>~</td>
				<td>结束日期：</td>
				<td><cui:datepicker id="endTime" componentCls="form-control" startDateId="startTime" dateFormat="yyyy-MM-dd HH:mm:ss" showOn="button"></cui:datepicker></td>
				<td><cui:button cls="cyanbtn" label="查询"  onClick="record.search" componentCls="coral-btn-blue" /></td>
				<td><cui:button id="resetBtn" label="重置" onClick="record.resetHandler"></cui:button></td>
			</tr>
		</table>
	</cui:form>
</div>

<!-- 自定义查询结束 -->

<cui:grid id="gridId_inspect_record"  singleselect="true" colModel="gridColModel_inspect_record"
shrinkToFit="true" fitStyle="fill" datatype="json" url=""
	pager="true"></cui:grid>	

<cui:dialog id="inspectdetaildialog" autoOpen="false" iframePanel="true" reLoadOnOpen="true" modal="true" resizable="false">
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
    
    var gridColModel_inspect_record=[ {
		label : "id",
		name : "ID",
		width : 100,
		hidden : true,
		key : true
	}, {
		label : "通报监狱",
		align:"center",
		width : 200,
		name : "INO_NOTICE_CUS_NUMBER",
		formatter : 'convertCode',
		revertCode : true,
		formatoptions : {
			'dataStructure' : 'list',
			'data' : combobox_jy
		}
	}, {
		label : "通报名称",
		align:"center",
		width : 200,
		name : "INO_INSPECT_NAME"
	}, {
		label : "通报时间",
		name : "INO_INSPECT_TIME",
		align:"center",
		width : 200
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
		align:"center",
		width : 100,
		formatter : 'convertCode',
		revertCode : true,
		formatoptions : {
			'dataStructure' : 'list',
			'data' : approvalSttsIndc_json
		}
	},{
		label : "操作",
		align:"center",
		width : 200,
		formatter:"formatterShow",
	}];
	function formatterShow(cellValue,options,rowObject){
		var result="<span><a href='#' style='color: #4692f0;' onclick='record.inspectDetail(\""+rowObject.ID+"\");return false;'>查看</a></span>"+
					"&nbsp;&nbsp;&nbsp;<span><a href='#' style='color: #4692f0;' onclick='record.showDocument(\""+rowObject.ID+"\");return false;'>阅览</a></span>";
		return result;
	}
	$.parseDone(function(){
		var jsConst = window.top.jsConst;
		var userId = jsConst.USER_ID;
		var cusNumber = jsConst.CUS_NUMBER;
		queryNoticeCheckList(cusNumber);
		
		record = {
			showDocument:function(inspectId){
				window.open('${ctx}/xctb/show?inspectId='+inspectId);
			},
			search:function(){
				if(record.checkForm()){
					var postData = {};
					var startTime = $('#startTime').datepicker("getDateValue");
					var endTime = $('#endTime').datepicker("getDateValue");
					if (startTime != "") {
						postData['startTime'] = startTime;
					}
					if (endTime != "") {
						postData['endTime'] = endTime;
					}
					postData['inoNoticeCusNumber']=cusNumber;
					$('#gridId_inspect_record').grid('option', 'postData', postData);
					$('#gridId_inspect_record').grid('reload');
				}
			},
			checkForm:function(){
				var flag = true;
				
				return flag;
			},
			inspectDetail:function(inspectId){
					if(inspectId){
						var selrow = inspectId;
					}else{
						var selrow = $('#gridId_inspect_record').grid("option", "selrow");//获取选中行的id
					}
					
					var rowData = $('#gridId_inspect_record').grid("getRowData", selrow);
					if(selrow != null){
						var url= '${ctx}/inspect/inspectdetailDialog?id='+selrow;
						/* if(rowData.inoApprovalSttsIndc=='0'){
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
				$('#formId_inspect_record').form("clear");
			}
			
		}
	});
	
	function queryNoticeCheckList(cusNumber){
		var url = "${ctx}/inspect/inspectHzListPage.json";
		var data = {"inoNoticeCusNumber":cusNumber};
		$("#gridId_inspect_record").grid("option", "postData", data);
		$("#gridId_inspect_record").grid("reload",url);
	}
	
</script>









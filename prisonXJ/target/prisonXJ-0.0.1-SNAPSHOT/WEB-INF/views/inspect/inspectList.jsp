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
	<cui:form id="formId_inspect_list">
		<table class="table">
			<tr>
				<td>开始日期：</td>
				<td><cui:datepicker id="startTime" componentCls="form-control" dateFormat="yyyy-MM-dd HH:mm:ss" showOn="button"></cui:datepicker></td>
				<td>~</td>
				<td>结束日期：</td>
				<td><cui:datepicker id="endTime" componentCls="form-control" startDateId="startTime" dateFormat="yyyy-MM-dd HH:mm:ss" showOn="button"></cui:datepicker></td>
				<td><cui:button cls="cyanbtn" label="查询"  onClick="inspectList.search" componentCls="coral-btn-blue" /></td>
				<td><cui:button id="resetBtn" label="重置" onClick="inspectList.resetHandler"></cui:button></td>
			</tr>
		</table>
	</cui:form>
</div>
<div><cui:button label="新增" onClick="inspectList.inspectCreate" cls="btn-primary"></cui:button>
<cui:button label="修改" onClick="inspectList.inspectUpdate" cls="btn-primary"></cui:button>
<cui:button label="删除" onClick="inspectList.inspectDelete" cls="btn-primary"></cui:button>
<cui:button label="详细" onClick="inspectList.inspectDetail()" cls="btn-primary"></cui:button>
<cui:button label="提交审批" onClick="inspectList.submitCheck" cls="btn-primary"></cui:button>
</div> 
<!-- 自定义查询结束 -->

<cui:grid id="gridId_inspect_list"  singleselect="true" colModel="gridColModel_inspect_list"
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
    
    var gridColModel_inspect_list=[ {
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
		width : 100,
		name : "INO_CRTE_USER_NAME"
	}, {
		label : "审批人",
		align:"center",
		width : 100,
		name : "ICP_POLICE_NAME"
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
		var result="<span><a href='#' style='color: #4692f0;' onclick='inspectList.inspectDetail(\""+rowObject.ID+"\");return false;'>查看</a></span>"+
					"&nbsp;&nbsp;&nbsp;<span><a href='#' style='color: #4692f0;' onclick='inspectList.showDocument(\""+rowObject.ID+"\");return false;'>阅览</a></span>";
		return result;
	}
	$.parseDone(function(){
		var jsConst = window.top.jsConst;
		var userId = jsConst.USER_ID;
		var cusNumber = jsConst.CUS_NUMBER;
		queryNoticeCheckList(cusNumber);
		
		inspectList = {
			showDocument:function(inspectId){
				window.open('${ctx}/xctb/show?inspectId='+inspectId);
			},
			search:function(){
				if(inspectList.checkForm()){
					var postData = {};
					var startTime = $('#startTime').datepicker("getDateValue");
					var endTime = $('#endTime').datepicker("getDateValue");
					if (startTime != "") {
						postData['startTime'] = startTime;
					}
					if (endTime != "") {
						postData['endTime'] = endTime;
					}
					postData['inoCrteUserId']=userId;
					postData['inoCusNumber']=cusNumber;
					$('#gridId_inspect_list').grid('option', 'postData', postData);
					$('#gridId_inspect_list').grid('reload');
				}
			},
			checkForm:function(){
				var flag = true;
				
				return flag;
			},
			inspectCreate:function(){
				var url= '${ctx}/inspect/editDialog';
				$("#inspectdetaildialog").dialog({
					width : 800,
					height : 600,
					subTitle : '新增巡查通报',
					url : url
				});
				$("#inspectdetaildialog").dialog("open");
			},
			inspectUpdate:function(){
				var selrow = $('#gridId_inspect_list').grid("option", "selrow");//获取选中行的id
				var rowData = $('#gridId_inspect_list').grid("getRowData", selrow);
				if(selrow != null){
					if(rowData.INO_APPROVAL_STTS_INDC!='0' && rowData.INO_APPROVAL_STTS_INDC!='1'){
						$.alert({
							message:"该记录已被审核不可修改！",
							title:"信息提示",
							iframePanel:true
						});
						return;
					}
					
					var url= '${ctx}/inspect/editDialog?id='+selrow;
					$("#inspectdetaildialog").dialog({
						width : 800,
						height : 600,
						subTitle : '修改巡查通报',
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
			inspectDelete:function(){
				var selrow = $('#gridId_inspect_list').grid("option", "selrow");//获取选中行的id
				var rowData = $('#gridId_inspect_list').grid("getRowData", selrow);
				if(selrow != null){
					if(rowData.INO_APPROVAL_STTS_INDC!='0' && rowData.INO_APPROVAL_STTS_INDC!='1'){
						$.alert({
							message:"该记录已被审核不可删除！",
							title:"信息提示",
							iframePanel:true
						});
						return;
					}
					$.confirm( {
						message:"确认删除？",
						iframePanel:true,
						callback: function(sure) {
							if (sure == true) {
								$.ajax({
									type : 'post',
									url : '${ctx}/inspect/inspectDelete?id='+selrow,
									dataType : 'json',
									success : function(data) {
										if (data.success) {
											$.message({
												message : "操作成功",
												cls : "success",
												iframePanel:true
											});
											$('#gridId_inspect_list').grid('reload');
										} else {
											$.message({
												iframePanel:true,
												message : data.msg,
												type : "danger"
											});
										}
									},
									error : function(XMLHttpRequest, textStatus, errorThrown) {
										$.alert({
											message:textStatus,
											title:"信息提示",
											iframePanel:true
										});
									}
								});
							}
							if (sure == false) {
								console.log('cancel');
							}
						}
					});
					
				}else{
					$.alert({
						message:"请选择一条记录",
						title:"信息提示",
						iframePanel:true
					});
				}
			},
			submitCheck:function(){
				var selrow = $('#gridId_inspect_list').grid("option", "selrow");//获取选中行的id
				var rowData = $('#gridId_inspect_list').grid("getRowData", selrow);
				if(selrow != null){
					if(rowData.INO_APPROVAL_STTS_INDC!='0'){
						$.alert({
							message:"该记录已提交审核，不可重复提交",
							title:"信息提示",
							iframePanel:true
						});
						return;
					}
					$.confirm( {
						message:"确认提交审核？",
						iframePanel:true,
						callback: function(sure) {
							if (sure == true) {
								$.ajax({
									type : 'post',
									url : '${ctx}/inspect/inspectUpdate.json',
									data : {
										id:selrow,
										inoApprovalSttsIndc:'1'				//0未提交 ，1已提交-待审核 ， 2不同意  ， 3 同意
									},
									dataType : 'json',
									success : function(data) {
										if (data.success) {
											$.message({
												message : "操作成功",
												cls : "success",
												iframePanel:true
											});
											$('#gridId_inspect_list').grid('reload');
										} else {
											$.message({
												iframePanel:true,
												message : data.msg,
												type : "danger"
											});
										}
									},
									error : function(XMLHttpRequest, textStatus, errorThrown) {
										$.alert({
											message:textStatus,
											title:"信息提示",
											iframePanel:true
										});
									}
								});
							}
							if (sure == false) {
								console.log('cancel');
							}
						}
					});
					
				}else{
					$.alert({
						message:"请选择一条记录",
						title:"信息提示",
						iframePanel:true
					});
				}
			},
			inspectDetail:function(inspectId){
					console.log(inspectId);
					if(inspectId){
						var selrow = inspectId;
					}else{
						var selrow = $('#gridId_inspect_list').grid("option", "selrow");//获取选中行的id
					}
					
					var rowData = $('#gridId_inspect_list').grid("getRowData", selrow);
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
				$('#formId_inspect_list').form("clear");
			}
			
		}
	});
	
	function queryNoticeCheckList(cusNumber){
		var url = "${ctx}/inspect/inspectListPage.json";
		var data = {"inoCusNumber":cusNumber};
		$("#gridId_inspect_list").grid("option", "postData", data);
		$("#gridId_inspect_list").grid("reload",url);
	}
	
</script>









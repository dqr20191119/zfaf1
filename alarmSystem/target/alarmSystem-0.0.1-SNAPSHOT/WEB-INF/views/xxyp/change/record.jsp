<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<style type="text/css">
.form-control {
	width: 100%;
}
.title{
 	font-weight: bold;
 	font-size: 14px;
 	font-family: "新宋体";
}
.text_center{
vertical-align: middle!important;
text-align:center;
}
</style>
<div>
	<cui:form id="formId_change_record">
		<input type="hidden" id="cusNumber" value="${cusNumber}"/>
		<table class="table">
			<tr>
				<td>通报时间：</td>
				<td width="160px"><cui:datepicker id="startTime" componentCls="form-control" dateFormat="yyyy-MM-dd HH:mm:ss" showOn="button"></cui:datepicker></td>
				<td>~</td>
				<td width="160px"><cui:datepicker id="endTime" componentCls="form-control" startDateId="startTime" dateFormat="yyyy-MM-dd HH:mm:ss" showOn="button"></cui:datepicker></td>
				<td>状态：</td>
				<td><cui:combobox id="comboboxId_status" data="ccdStatus_json" 
					emptyText="请选择" ></cui:combobox></td>
				<td><cui:button cls="cyanbtn" label="查询"  onClick="record.search" componentCls="coral-btn-blue" /></td>
				<td><cui:button id="resetBtn" label="重置" onClick="record.resetHandler"></cui:button></td>
			</tr>
		</table>
	</cui:form>
</div>
<cui:toolbar id="toolbarId_record" data="toolbarData_record"
	onClick="toolbarOnClick_record"></cui:toolbar>
<cui:grid id="gridId_record" singleselect="true" shrinkToFit="true" 
colModel="gridColModel_record" fitStyle="fill" datatype="json" url=""
	pager="true"></cui:grid>	

<cui:dialog id="recorddetaildialog" autoOpen="false"
 iframePanel="true" reLoadOnOpen="true" modal="true" resizable="false">
  	<center style="margin-top:15px;">
		<table class="table table-fixed" border="1" style="width: 90%;">
			<tr>
				<td colspan="4" align="center" class="title" id="inspectName"></td>
			</tr>
			<tr>
				<td width="20%" class="text_center">整改内容</td>
				<td width="80%" colspan="3">	
					<textarea id="ccdChangedContent" readonly="readonly" style="height: 170px;width:100%"></textarea>
				</td>
			</tr>
			<tr>
				<td colspan="4" align="center" class="title">整改信息</td>
			</tr>
			<tr>
				<td width="20%" class="text_center">整改部门</td>
				<td width="80%" colspan="3">	
					<cui:input  readonly="true" id="ccdDprtmntName"  componentCls="form-control"></cui:input>
				</td>
			</tr>
			<tr>
				<td width="20%" class="text_center">整改情况</td>
				<td width="80%" colspan="3">	
					<textarea id="ccpChangedMatters" readonly="readonly" style="height: 170px;width:100%"></textarea>
				</td>
			</tr>
			<tr>
				<td width="20%" class="text_center">分管领导</td>
				<td width="80%" colspan="3">	
					<cui:input type="hidden"  required="true" id="ccdBranchLeaders"></cui:input>
					<cui:input required="true" readonly="true" id="ccdBranchLeadersName"></cui:input>
				</td>
			</tr>
			<tr>
				<td width="20%" class="text_center">审批意见</td>
				<td width="80%" colspan="3">	
					<cui:radiolist id="branchLeader_ccaSuggestion"  required="true" repeatLayout="flow" readonly="true"
											column="4"  data="ccaSuggestion_json" componentCls="form-control"></cui:radiolist>
				</td>
			</tr>
			<tr>
				<td width="20%" class="text_center">意见内容</td>
				<td width="80%" colspan="3">	
					<textarea id="branchLeader_ccaContent" readonly="readonly" style="height: 170px;width:100%"></textarea>
				</td>
			</tr>
			<tr>
				<td width="20%" class="text_center">主要领导</td>
				<td width="80%" colspan="3">	
					<cui:input  type="hidden" required="true" id="ccdManagerLeaders"></cui:input>
					<cui:input required="true" readonly="true" id="ccdManagerLeadersName"></cui:input>
				</td>
			</tr>
			<tr>
				<td width="20%" class="text_center">审批意见</td>
				<td width="80%" colspan="3">	
					<cui:radiolist id="managerLeader_ccaSuggestion"  required="true" repeatLayout="flow" readonly="true"
											column="4"  data="ccaSuggestion_json" componentCls="form-control"></cui:radiolist>
				</td>
			</tr>
			<tr>
				<td width="20%" class="text_center">意见内容</td>
				<td width="80%" colspan="3">	
					<textarea id="managerLeader_ccaContent" readonly="readonly" style="height: 170px;width:100%"></textarea>
				</td>
			</tr>
		</table>
	</center>
</cui:dialog>		
<cui:dialog id="inspectdetaildialog" autoOpen="false" iframePanel="true" reLoadOnOpen="true" modal="true" resizable="false">
</cui:dialog>	
<script type="text/javascript">
	var jsConst = window.top.jsConst;
	var cusNumber;//监狱号
	
	var ccaSuggestion_json = [{
		value : "0",
		text : '<font color="#FF0000">不同意</font>'
	}, {
		value : "1",
		text : '<font color="green">同意</font>'
	}];
	var ccdStatus_json=[{
		'value' : '1',
		'text' : '<font color="#999900">待整改</font>'
	}, {
		'value' : '2',
		'text' : '<font color="#888800">分管领导待审批</font>'
	}, {
		'value' : '3',
		'text' : '<font color="#777700">主管领导待审批</font>'
	}, {
		'value' : '4',
		'text' : '<font color="green">已完成</font>'
	}];
	
	var gridColModel_record = [ {
		label : "id",
		name : "CCH_CHANGED_ID",
		width : 100,
		hidden : true,
		key : true
	}, {
		label : "通报id",
		name : "INO_INSPECT_ID",
		hidden : true,
	}, {
		label : "分管领导name",
		name : "CCD_BRANCH_LEADERS_NAME",
		hidden : true,
	}, {
		label : "主管领导name",
		name : "CCD_MANAGER_LEADERS_NAME",
		hidden : true,
	}, {
		label : "通报日期",
		name : "INO_INSPECT_TIME",
		align:"center",
		width : 300
	}, {
		label : "通报名称",
		name : "INO_INSPECT_NAME",
		align:"center",
		width : 300
	}, {
		label : "发起整改人",
		width : 150,
		align:"center",
		name : "CCH_CRTE_USER_NAME"
	}, {
		label : "发起整改时间",
		width : 150,
		align:"center",
		name : "CCH_CRTE_TIME"
	}, {
		label : "整改内容",
		width : 150,
		align:"center",
		name : "CCD_CHANGED_CONTENT"
	}, {
		label : "整改部门",
		width : 150,
		align:"center",
		name : "CCD_DPRTMNT_NAME"
	}, {
		label : "状态",
		width : 150,
		align:"center",
		name : "CCD_STATUS",
		formatter : 'convertCode',
		revertCode : true,
		formatoptions : {
			'dataStructure' : 'list',
			'data' : ccdStatus_json
		}
	}];
	
	var toolbarData_record = [ {
		"id" : "recorddetail",
		"label" : "详细",
		"disabled" : "false",
		"type" : "button",
		"cls" : "greenbtn",
		"icon" : ""
	}];
	
	var toolbarOnClick_record = function(event, ui) {
		var id = ui.id;
		if (id == 'recorddetail') {
			record.f_recorddetail();
		}
	}
	
	function queryRecordList(){
		var url = "${ctx}/xxyp/change/recordListPage.json";
		var data = {"inoNoticeCusNumber" : cusNumber};
		$("#gridId_record").grid("option", "postData", data);
		$("#gridId_record").grid("reload",url);
	}
	
	function showInspect(inspectId){
		window.open('${ctx}/xctb/show?inspectId='+inspectId);
	}
	
	$.parseDone(function(){
		cusNumber=$("#cusNumber").val();
		if(!cusNumber){
			cusNumber = jsConst.CUS_NUMBER
		}
		
		queryRecordList();
		
		record={
			f_recorddetail:function(){
				var selrow = $('#gridId_record').grid("option", "selrow");//获取选中行的id
				if(selrow != null){
					var rowData=$('#gridId_record').grid("getRowData", selrow.toString());
					//初始化信息
					var dom_a="<a href='#' style='color: #4692f0;' onclick='showInspect(\""+rowData.INO_INSPECT_ID+"\");return false;'>"+rowData.INO_INSPECT_NAME+"</a>"
					
					$("#inspectName").html(dom_a);
					$("#ccdChangedContent").val(rowData.CCD_CHANGED_CONTENT);
					$('#ccdDprtmntName').textbox("setValue",rowData.CCD_DPRTMNT_NAME);
					$('#ccdBranchLeadersName').textbox("setValue",rowData.CCD_BRANCH_LEADERS_NAME);
					$('#ccdManagerLeadersName').textbox("setValue",rowData.CCD_MANAGER_LEADERS_NAME);
					//获取整改情况
					var changePeople_param={
						"ccpCusNumber":cusNumber,
						"ccpChangedIdnty":rowData.CCH_CHANGED_ID,
						"ccpDprtmntIdnty":rowData.CCD_DPRTMNT_IDNTY,
					}
					$.ajax({
						type : 'post',
						url : '${ctx}/xxyp/change/searchChangePeople.json',
						data : changePeople_param,
						dataType : 'json',
						success : function(data) {
							if (data.success) {
								if(data.obj.length>0){
									var ccpChangedMatters=data.obj[0].ccpChangedMatters;
									$("#ccpChangedMatters").val(ccpChangedMatters);
								}
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
					//获取分管领导审批意见
					if(rowData.CCD_STATUS>='3'){
						var approval_param={
							"ccaCusNumber":cusNumber,
							"ccaChangedIdnty":rowData.CCH_CHANGED_ID,
							"ccaDprtmntIdnty":rowData.CCD_DPRTMNT_IDNTY,
							"ccaApprovalType":"1"					//分管领导审批
							
						}
						$.ajax({
							type : 'post',
							url : '${ctx}/xxyp/change/searchChangeApproval.json',
							data : approval_param,
							dataType : 'json',
							success : function(data) {
								if (data.success) {
									if(data.obj.length>0){
										var ccaSuggestion=data.obj[0].ccaSuggestion;
										var ccaContent=data.obj[0].ccaContent;
										$("#branchLeader_ccaSuggestion").radiolist("setValue",ccaSuggestion);
										$("#branchLeader_ccaContent").val(ccaContent);
									}
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
					//获取主管领导审批意见
					if(rowData.CCD_STATUS=='4'){
						var approval_param={
							"ccaCusNumber":cusNumber,
							"ccaChangedIdnty":rowData.CCH_CHANGED_ID,
							"ccaDprtmntIdnty":rowData.CCD_DPRTMNT_IDNTY,
							"ccaApprovalType":"2"					//主管领导审批
						}
						$.ajax({
							type : 'post',
							url : '${ctx}/xxyp/change/searchChangeApproval.json',
							data : approval_param,
							dataType : 'json',
							success : function(data) {
								if (data.success) {
									if(data.obj.length>0){
										var ccaSuggestion=data.obj[0].ccaSuggestion;
										var ccaContent=data.obj[0].ccaContent;									
										$("#managerLeader_ccaSuggestion").radiolist("setValue",ccaSuggestion);
										$("#managerLeader_ccaContent").val(ccaContent);

									}
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
					
					
					$("#recorddetaildialog").dialog({
						width : 800,
						height : 580,
						subTitle : '汇总',
					});
					$("#recorddetaildialog").dialog("open");
				}else{
					$.alert({
						message:"请选择一条记录",
						title:"信息提示",
						iframePanel:true
					});
				}
			},	
			search:function(){
				var postData = {};
				var startTime = $('#startTime').datepicker("getDateValue");
				var endTime = $('#endTime').datepicker("getDateValue");
				var ccdStatus=$("#comboboxId_status").combobox("getValue");
				if (startTime != "") {
					postData['startTime'] = startTime;
				}
				if (endTime != "") {
					postData['endTime'] = endTime;
				}
				if (ccdStatus != "") {
					postData['ccdStatus']=ccdStatus;
				}
				postData['inoNoticeCusNumber']=cusNumber;
				$('#gridId_record').grid('option', 'postData', postData);
				$('#gridId_record').grid('reload');	
			},
			resetHandler:function(){
				$('#formId_change_record').form("clear");
			}
		}
	});
</script>
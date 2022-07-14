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
.managerLeader{
display:none;
}
</style>

<cui:toolbar id="toolbarId_check" data="toolbarData_check"
	onClick="toolbarOnClick_check"></cui:toolbar>
<cui:grid id="gridId_check" singleselect="true" shrinkToFit="true" 
colModel="gridColModel_check" fitStyle="fill" datatype="json" url=""
	pager="true"></cui:grid>	

<cui:dialog id="checkdetaildialog" autoOpen="false"
 iframePanel="true" reLoadOnOpen="true" modal="true" resizable="false">
 	<center style="margin-top:15px;">
 		<cui:input type="hidden" required="true" id="changeId"></cui:input>
 		<cui:input type="hidden" required="true" id="ccdDprtmntIdnty"></cui:input>
 		<cui:input type="hidden" required="true" id="ccdStatus"></cui:input>
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
					<cui:radiolist id="branchLeader_ccaSuggestion"  required="true" repeatLayout="flow" readonly="false"
											column="4"  data="ccaSuggestion_json" componentCls="form-control"></cui:radiolist>
				</td>
			</tr>
			<tr>
				<td width="20%" class="text_center">意见内容</td>
				<td width="80%" colspan="3">	
					<textarea id="branchLeader_ccaContent" style="height: 170px;width:100%"></textarea>
				</td>
			</tr>
			<tr class="managerLeader">
				<td width="20%" class="text_center">主要领导</td>
				<td width="80%" colspan="3">	
					<cui:input  type="hidden" required="true" id="ccdManagerLeaders"></cui:input>
					<cui:input required="true" readonly="true" id="ccdManagerLeadersName"></cui:input>
				</td>
			</tr>
			<tr class="managerLeader">
				<td width="20%" class="text_center">审批意见</td>
				<td width="80%" colspan="3">	
					<cui:radiolist id="managerLeader_ccaSuggestion"  required="true" repeatLayout="flow" readonly="false"
											column="4"  data="ccaSuggestion_json" componentCls="form-control"></cui:radiolist>
				</td>
			</tr>
			<tr class="managerLeader">
				<td width="20%" class="text_center">意见内容</td>
				<td width="80%" colspan="3">	
					<textarea id="managerLeader_ccaContent" style="height: 170px;width:100%"></textarea>
				</td>
			</tr>
		</table>
	</center>
	<div class="dialog-buttons">
		<cui:button label="提交" componentCls="btn-primary" onClick="check.submit"></cui:button>
	</div>
</cui:dialog>		
<cui:dialog id="inspectdetaildialog" autoOpen="false" iframePanel="true" reLoadOnOpen="true" modal="true" resizable="false">
</cui:dialog>	
<script type="text/javascript">
	var jsConst = window.top.jsConst;
	var cusNumber = jsConst.CUS_NUMBER;//监狱号
	var userId = jsConst.USER_ID;
	var userId=jsConst.USER_ID;
	
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
	var ccaSuggestion_json = [{
		value : "0",
		text : '<font color="#FF0000">不同意</font>'
	}, {
		value : "1",
		text : '<font color="green">同意</font>'
	}];
	
	var gridColModel_check = [ {
		label : "id",
		name : "ID",
		width : 100,
		hidden : true,
		key : true
	},{
		label : "通报id",
		name : "INO_INSPECT_ID",
		hidden : true,
	}, {
		label : "整改部门id",
		name : "CCD_DPRTMNT_IDNTY",
		hidden : true,
	}, {
		label : "分管领导警号",
		name : "CCD_BRANCH_LEADERS",
		hidden : true,
	}, {
		label : "分管领导name",
		name : "CCD_BRANCH_LEADERS_NAME",
		hidden : true,
	}, {
		label : "主管领导警号",
		name : "CCD_MANAGER_LEADERS",
		hidden : true,
	}, {
		label : "主管领导name",
		name : "CCD_MANAGER_LEADERS_NAME",
		hidden : true,
	}, {
		label : "名称",
		name : "INO_INSPECT_NAME",
		align:"center",
		width : 300
	}, {
		label : "发起整改时间",
		width : 150,
		align:"center",
		name : "CCH_CRTE_TIME"
	}, {
		label : "发起整改人",
		width : 150,
		align:"center",
		name : "CCH_CRTE_USER_NAME"
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
	
	var toolbarData_check = [ {
		"id" : "checkdetail",
		"label" : "审批",
		"disabled" : "false",
		"type" : "button",
		"cls" : "greenbtn",
		"icon" : ""
	}];
	
	var toolbarOnClick_check = function(event, ui) {
		var id = ui.id;
		if (id == 'checkdetail') {
			check.f_checkdetail();
		}
	}
	
	function queryCheckList(){
		var url = "${ctx}/xxyp/change/checkListPage.json";
		var data = {"cchCusNumber" : cusNumber,"ccdBranchLeaders":userId,"ccdManagerLeaders":userId};
		$("#gridId_check").grid("option", "postData", data);
		$("#gridId_check").grid("reload",url);
	}
	
	function showInspect(inspectId){
		window.open('${ctx}/xctb/show?inspectId='+inspectId);
	}
	
	$.parseDone(function(){
		
		queryCheckList();
		
		check={
			f_checkdetail:function(){
				var selrow = $('#gridId_check').grid("option", "selrow");//获取选中行的id
				if(selrow != null){
					var rowData=$('#gridId_check').grid("getRowData", selrow.toString());
					//初始化信息
					//获取整改情况
					var changePeople_param={
						"ccpCusNumber":cusNumber,
						"ccpChangedIdnty":rowData.ID,
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
					//主管领导待审批
					if(rowData.CCD_STATUS=='3'){
						$(".managerLeader").show();
						
						var approval_param={
							"ccaCusNumber":cusNumber,
							"ccaChangedIdnty":rowData.ID,
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
										$("#branchLeader_ccaSuggestion").radiolist("option","readonly",true);
										$("#branchLeader_ccaContent").val(ccaContent);
										$("#branchLeader_ccaContent").attr("readonly","readonly");
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
					$('#ccdStatus').textbox("setValue",rowData.CCD_STATUS);
					
					var dom_a="<a href='#' style='color: #4692f0;' onclick='showInspect(\""+rowData.INO_INSPECT_ID+"\");return false;'>"+rowData.INO_INSPECT_NAME+"</a>"
					$("#inspectName").html(dom_a);
					$("#ccdChangedContent").val(rowData.CCD_CHANGED_CONTENT);
					$('#ccdDprtmntName').textbox("setValue",rowData.CCD_DPRTMNT_NAME);
					$('#changeId').textbox("setValue",rowData.ID);
					$('#ccdDprtmntIdnty').textbox("setValue",rowData.CCD_DPRTMNT_IDNTY);
					$('#ccdBranchLeaders').textbox("setValue",rowData.CCD_BRANCH_LEADERS);
					$('#ccdBranchLeadersName').textbox("setValue",rowData.CCD_BRANCH_LEADERS_NAME);
					$('#ccdManagerLeaders').textbox("setValue",rowData.CCD_MANAGER_LEADERS);
					$('#ccdManagerLeadersName').textbox("setValue",rowData.CCD_MANAGER_LEADERS_NAME);
					
					$("#checkdetaildialog").dialog({
						width : 800,
						height : 580,
						subTitle : '审批',
					});
					$("#checkdetaildialog").dialog("open");
				}else{
					$.alert({
						message:"请选择一条记录",
						title:"信息提示",
						iframePanel:true
					});
				}
			},
			
			submit:function(){
				var ccdStatus=$('#ccdStatus').textbox("getValue");
				var paramData={
					"ccdDprtmntIdnty":$('#ccdDprtmntIdnty').textbox("getValue"),
					"changeId":$('#changeId').textbox("getValue"),	
					"ccdStatus":ccdStatus,	
				}
				//分管领导待审批
				if(ccdStatus=="2"){
					paramData["ccaPoliceIdnty"]=$('#ccdBranchLeaders').textbox("getValue");
					paramData["ccaPoliceName"]=$('#ccdBranchLeadersName').textbox("getValue");
					paramData["ccaSuggestion"]=$("#branchLeader_ccaSuggestion").radiolist("getValue");
					paramData["ccaContent"]=$("#branchLeader_ccaContent").val();
					
				}
				//主管领导待审批
				else if(ccdStatus=="3"){
					paramData["ccaPoliceIdnty"]=$('#ccdManagerLeaders').textbox("getValue");
					paramData["ccaPoliceName"]=$('#ccdBranchLeadersName').textbox("getValue");
					paramData["ccaSuggestion"]=$("#managerLeader_ccaSuggestion").radiolist("getValue");
					paramData["ccaContent"]=$("#managerLeader_ccaContent").val();
				}
				debugger;
				//验证
				if(!check.valida(paramData)){
					return;
				}
				
				//不同意
				if(paramData["ccaSuggestion"]=="0"){
					paramData["ccdStatus"]=parseInt(ccdStatus)-1;
					paramData["ccaApprovalType"]=parseInt(ccdStatus)-1;
					
				}
				//同意
				else if(paramData["ccaSuggestion"]=="1"){
					paramData["ccdStatus"]=parseInt(ccdStatus)+1;
					paramData["ccaApprovalType"]=parseInt(ccdStatus)-1;
				}
				
				$.ajax({
					type : 'post',
					url : '${ctx}/xxyp/change/checkSubmit.json',
					data : paramData,
					dataType : 'json',
					success : function(data) {
						if (data.success) {
							$.message({
								message : "操作成功",
								cls : "success",
								iframePanel:true
							});
							queryCheckList();
							$("#checkdetaildialog").dialog("close");
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
			},
			valida:function(paramData){
				for(var key in paramData){
					if(paramData[key]==""){
						$.alert({
							message:"请将信息填写完整",
							title:"信息提示",
							iframePanel:true
						});
						return false;
					}
				}
				return true;
			}
		}
	});
</script>
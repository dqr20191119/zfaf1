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
.text_height{
	height:170px;
}
</style>

<cui:toolbar id="toolbarId_change" data="toolbarData_change"
	onClick="toolbarOnClick_change"></cui:toolbar>
<cui:grid id="gridId_change" singleselect="true" shrinkToFit="true" 
colModel="gridColModel_change" fitStyle="fill" datatype="json" url=""
	pager="true"></cui:grid>	

<cui:dialog id="changedetaildialog" autoOpen="false"
 iframePanel="true" reLoadOnOpen="true" modal="true" resizable="false">
 	<center style="margin-top:15px;">
 		<cui:input type="hidden" required="true" id="changeId"></cui:input>
 		<cui:input type="hidden" required="true" id="changeDepartmentId"></cui:input>
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
					<cui:input  readonly="true" id="ccdDprtmntName" componentCls="form-control"></cui:input>
				</td>
			</tr>
			<tr>
				<td width="20%" class="text_center">整改情况</td>
				<td width="80%" colspan="3">	
					<textarea id="ccpChangedMatters" style="height: 170px;width:100%"></textarea>
				</td>
			</tr>
			<tr>
				<td width="20%" class="text_center">分管领导</td>
				<td width="30%">	
					<cui:combotree required="true" id="change_combotree_ccdBranchLeaders" name="ccdBranchLeaders" simpleDataEnable="true"
							url="${ctx}/common/areadevice/nullJson.json" allowPushParent="false" onChange="change.ccdBranchLeadersOnChange" componentCls="form-control"></cui:combotree>
					<cui:input type="hidden" required="true" id="change_ccdBranchLeadersName" name="ccdBranchLeadersName"></cui:input>
				</td>
				<td width="20%" class="text_center">主要领导</td>
				<td width="30%">	
					<cui:combotree required="true" id="change_combotree_ccdManagerLeaders" name="ccdManagerLeaders" simpleDataEnable="true"
							url="${ctx}/common/areadevice/nullJson.json" allowPushParent="false" onChange="change.ccdManagerLeadersOnChange" componentCls="form-control"></cui:combotree>
					<cui:input type="hidden" required="true" id="change_ccdManagerLeadersName" name="ccdManagerLeadersName"></cui:input>
				</td>
			</tr>
		</table>
	</center>
	<div class="dialog-buttons">
		<cui:button label="提交" componentCls="btn-primary" onClick="change.submit"></cui:button>
	</div>
</cui:dialog>		
<cui:dialog id="inspectdetaildialog" autoOpen="false" iframePanel="true" reLoadOnOpen="true" modal="true" resizable="false">
</cui:dialog>	
<script type="text/javascript">
	var jsConst = window.top.jsConst;
	var cusNumber = jsConst.CUS_NUMBER;//监狱号
	var departmentId = jsConst.DEPARTMENT_ID;
	
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
	
	var gridColModel_change = [ {
		label : "id",
		name : "ID",
		width : 100,
		hidden : true,
		key : true
	},  {
		label : "通报id",
		name : "INO_INSPECT_ID",
		hidden : true,
	},{
		label : "整改部门表id",
		name : "CHANGE_DEPARTMENT_ID",
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

	
	var toolbarData_change = [ {
		"id" : "changedetail",
		"label" : "整改",
		"disabled" : "false",
		"type" : "button",
		"cls" : "greenbtn",
		"icon" : ""
	}];
	
	var toolbarOnClick_change = function(event, ui) {
		var id = ui.id;
		if (id == 'changedetail') {
			change.f_changedetail();
		}
	}
	
	function queryChangeList(){
		var url = "${ctx}/xxyp/change/changeListPage.json";
		var data = {"cchCusNumber" : cusNumber,"ccdDprtmntIdnty":departmentId};
		$("#gridId_change").grid("option", "postData", data);
		$("#gridId_change").grid("reload",url);
	}
	
	function showInspect(inspectId){
		window.open('${ctx}/xctb/show?inspectId='+inspectId);
	}
	
	$.parseDone(function(){
		
		queryChangeList();
		
		$("#change_combotree_ccdBranchLeaders").combotree("tree").tree("reload",{
			asyncUrl: "${ctx}/common/authsystem/findDeptPoliceForCombotree.json?cusNumber="+cusNumber
		} );
		
		$("#change_combotree_ccdManagerLeaders").combotree("tree").tree("reload",{
			asyncUrl: "${ctx}/common/authsystem/findDeptPoliceForCombotree.json?cusNumber="+cusNumber
		} );
		
		change={
			f_changedetail:function(){
				var selrow = $('#gridId_change').grid("option", "selrow");//获取选中行的id
				if(selrow != null){
					var rowData=$('#gridId_change').grid("getRowData", selrow.toString());
					//初始化信息
					var dom_a="<a href='#' style='color: #4692f0;' onclick='showInspect(\""+rowData.INO_INSPECT_ID+"\");return false;'>"+rowData.INO_INSPECT_NAME+"</a>"
					
					$("#inspectName").html(dom_a);
					$("#ccdChangedContent").val(rowData.CCD_CHANGED_CONTENT);
					$('#ccdDprtmntName').textbox("setValue",rowData.CCD_DPRTMNT_NAME);
					$('#changeId').textbox("setValue",rowData.ID);
					$('#changeDepartmentId').textbox("setValue",rowData.CHANGE_DEPARTMENT_ID);
					
					$("#changedetaildialog").dialog({
						width : 800,
						height : 580,
						subTitle : '整改',
					});
					$("#changedetaildialog").dialog("open");
				}else{
					$.alert({
						message:"请选择一条记录",
						title:"信息提示",
						iframePanel:true
					});
				}
			},	
			ccdBranchLeadersOnChange:function(e,ui){
				$('#change_ccdBranchLeadersName').textbox('setValue',ui.text);
			},
			ccdManagerLeadersOnChange:function(e,ui){
				$('#change_ccdManagerLeadersName').textbox('setValue',ui.text);
			},
			submit:function(){
				
				var paramData={
					"changeDepartmentId":$('#changeDepartmentId').textbox("getValue"),
					"changeId":$('#changeId').textbox("getValue"),	
					"ccpChangedMatters":$("#ccpChangedMatters").val(),
					"ccdBranchLeaders":$("#change_combotree_ccdBranchLeaders").combotree("getValue"),
					"ccdBranchLeadersName":$('#change_ccdBranchLeadersName').textbox("getValue"),
					"ccdManagerLeaders":$("#change_combotree_ccdManagerLeaders").combotree("getValue"),
					"ccdManagerLeadersName":$('#change_ccdManagerLeadersName').textbox("getValue")
				}
				
				//验证
				if(!change.valida(paramData)){
					return;
				}
				
				$.ajax({
					type : 'post',
					url : '${ctx}/xxyp/change/changeSubmit.json',
					data : paramData,
					dataType : 'json',
					success : function(data) {
						if (data.success) {
							$.message({
								message : "操作成功",
								cls : "success",
								iframePanel:true
							});
							queryChangeList();
							$("#changedetaildialog").dialog("close");
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
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
	<cui:form id="formId_inspectEdit">
		<cui:input type='hidden' name="id" />
		<cui:input type="hidden" required="true" id="inspect_edit_inoCrteUserId" name="inoCrteUserId"></cui:input>
		<cui:input type="hidden" required="true" id="inspect_edit_inoCrteUserName" name="inoCrteUserName"></cui:input>
		<table class="table table-fixed" style="width: 90%;">
			<tr>
				<td width="15%" class="td_title"><label>通报名称：</label></td>
				<td colspan ="3">
					<cui:input  required="true" id="inspect_edit_inoInspectName" name="inoInspectName" componentCls="form-control"></cui:input>
				</td>
			</tr>
			<tr>
				<td width="15%" class="td_title"><label>编校：</label></td>
				<td width="35%" class="td_context">
					<cui:input  required="true" name="inoInspectBj" componentCls="form-control"></cui:input>
				</td>
				<td width="15%" class="td_title"><label>通报期数：</label></td>
				<td width="35%" class="td_context">
					<cui:input  required="true" name="inoInspectPhase" validType="integer" componentCls="form-control"></cui:input>
				</td>
			</tr>
			<tr>
				<td class="td_title"><label>督察时间：</label></td>
				<td width="35%" class="td_context">
					<cui:datepicker required="true" id="inspect_edit_inoInspectTime" name="inoInspectTime" dateFormat="yyyy-MM-dd" componentCls="form-control"></cui:datepicker>
				</td>
				<td width="15%" class="td_title"><label>通报监狱：</label></td>
				<td width="35%" class="td_context">
					<cui:combobox required="true" id="inspect_edit_inoNoticeCusNumber" name="inoNoticeCusNumber" url="${ctx}/common/authsystem/findAllJyForCombobox.json" componentCls="form-control"></cui:combobox>
				</td>
			</tr>
			<tr>
				<td class="td_title"><label>制度执行：</label></td>
				<td class="td_context" colspan="3">
					<cui:textarea id="inspect_edit_inoRuleExecute" name="inoRuleExecute" placeholder="民警在岗履职、门卫会见室管理、外来人员管理、重要时段管理等制度执行情况"  componentCls="form-control"></cui:textarea>
				</td>
			</tr>
			<tr>
				<td class="td_title"><label>现场管理：</label></td>
				<td class="td_context" height="300px" colspan="3" style="border: 1px solid #3789EA;background-color: #F0F0F0">
					<cui:toolbar data="toolbar_monitor"></cui:toolbar>
					<cui:grid id="gridId_monitor_already" colModel="gridColModel_monitor"
					 width="auto" fitStyle="fill" multiselect="true" pager="true">
					</cui:grid>	
				</td>
			</tr>
			<tr>
				<td class="td_title"><label>凌晨检查：</label></td>
				<td class="td_context" colspan="3">
					<cui:textarea id="inspect_edit_inoMorningCheck" name="inoMorningCheck" placeholder="监控中心、门卫、分监控在班民警履职情况，罪犯夜间监督岗值岗情况，伙房监区出早工罪犯管理情况等" componentCls="form-control"></cui:textarea>
				</td>
			</tr>
			<tr>
				<td class="td_title"><label>工作要求：</label></td>
				<td class="td_context" colspan="3">
					<cui:textarea id="inspect_edit_inoSuggesition" name="inoSuggesition" placeholder="工作要求"  componentCls="form-control"></cui:textarea>
				</td>
			</tr>
			<tr>
				<td class="td_title"><label>移交问题：</label></td>
				<td class="td_context" colspan="3">
					<cui:textarea id="inspect_edit_inoProblem" name="inoProblem" placeholder="移交问题" componentCls="form-control"></cui:textarea>
				</td>
			</tr>
			<tr>
				<td class="td_title"><label>审核领导：</label></td>
				<td class="td_context">
					<cui:combotree iframePanel="true" required="true" id="inspect_combotree_checkPoliceIdnty" name="checkPoliceIdnty" simpleDataEnable="true"
						url="${ctx}/common/areadevice/nullJson.json" allowPushParent="false" onChange="inspect_edit.checkPoliceIdntyOnChange" componentCls="form-control"></cui:combotree>
					<cui:input type="hidden" required="true" id="inspect_edit_checkPoliceName" name="checkPoliceName"></cui:input>
				</td>
				<td class="td_title"><label>督察人员：</label></td>
				<td class="td_context">
					<cui:combotree iframePanel="true" id="inspect_combotree_iprPoliceIdntys" name="iprPoliceIdntys" value="" simpleDataEnable="true"
						url="${ctx}/common/areadevice/nullJson.json" multiple="true" allowPushParent="false" onChange="inspect_edit.iprPoliceIdntysOnChange" componentCls="form-control"></cui:combotree>
					<cui:input type="hidden" required="true" id="inspect_edit_iprPoliceNames" name="iprPoliceNames" value="" ></cui:input>
				</td>
			</tr> 
			
		</table>
	</cui:form>
</center>

<cui:dialog id="monitorDialog" autoOpen="false" iframePanel="true" reLoadOnOpen="true" modal="true" resizable="false">
	<cui:form id="queryForm_monitor">
		<table class="table">
			<tr>
				<td align="right"><label>创建时间:</label></td>
				<td><cui:datepicker id="start_monitor_cjsj"
						componentCls="form-control" dateFormat="yyyy-MM-dd HH:mm:ss"></cui:datepicker></td>
				<td width="5px">至</td>
				<td><cui:datepicker id="end_monitor_cjsj" componentCls="form-control"
						startDateId="start_monitor_cjsj" dateFormat="yyyy-MM-dd HH:mm:ss"></cui:datepicker></td>
					
				<td><cui:button cls="cyanbtn"  label="查询"
					 onClick="inspect_edit.search_monitor" componentCls="coral-btn-blue" /></td>
				<td><cui:button id="resetBtn" label="重置" onClick="inspect_edit.resetHandler_monitor" /></td>
			</tr>
			</table></cui:form>
		<cui:grid id="gridId_monitor_toBe" singleselect="true"  shrinkToFit="true" multiselect="true"
			colModel="gridColModel_all_monitor" fitStyle="fill" datatype="json"
			url="" pager="true"></cui:grid>
</cui:dialog>
<cui:dialog id="monitorDetailDialog" autoOpen="false" iframePanel="true" reLoadOnOpen="true" modal="true" resizable="false">
</cui:dialog>
<div class="dialog-buttons">
	<cui:button label="生成巡查通报" componentCls="btn-primary" onClick="inspect_edit.submit()"></cui:button>
	<%-- <cui:button label="提交" componentCls="btn-primary" onClick="inspect_edit.submit()"></cui:button>
	<cui:button label="重置" onClick="inspect_edit.reset()"></cui:button> --%>
</div>
<script type="text/javascript"
	src="${ctx}/static/module/evidence/evidence.js"></script>
<script type="text/javascript"
	src="${ctx}/static/module/evidence/messenger.js"></script>
<script>
	var jsConst = window.top.jsConst;
	var cusNumber = jsConst.CUS_NUMBER;//监狱号
	var userName = jsConst.REAL_NAME;//当前登录人真实姓名
	var userId = jsConst.USER_ID;//当前登录人id
	var MonitorEditor = null;
	var gridColModel_monitor=[ {
		label : "id",
		name : "ID",
		hidden : true,
		key:true
	}, {
		label : "记录地点",
		width : 200,
		align : "center",
		name : "MDO_ADDR",
		hidden : true
	}, {
		label : "存在问题",
		width : 280,
		align : "center",
		name : "MDO_PROBLEM",
		hidden : true
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
	
	var gridColModel_all_monitor=[ {
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
		label : "记录地点",
		width : 200,
		align : "center",
		hidden : true,
		name : "MDO_ADDR"
	}, {
		label : "存在问题",
		width : 280,
		align : "center",
		name : "MDO_PROBLEM"
	}, {
		label : "创建时间",
		align : "center",
		name : "MDO_CRTE_TIME"
	}, {
		label : "创建人",
		width : 100,
		align : "center",
		name : "CRTE_USER_NAME"
	},{
		label : "操作",
		align:"center",
		width : 80,
		formatter:"formatterDetail",
	} ];
	function formatterDetail(cellValue,options,rowObject){
		var result="<span><a href='#' style='color: #4692f0;' onclick='inspect_edit.detailedMonitor(\""+rowObject.ID+"\");return false;'>详细</a></span>";
		return result;
	}
	var toolbar_monitor = [{
		"type" : "button",
		"id" : "btnId_Monitor",
		"label" : "增加",
		"onClick" : "inspect_edit.addMonitor",
		"componentCls" : "btn-primary"
	},{
		"type" : "button",
		"id" : "btnId_deleteMonitor",
		"label" : "删除",
		"onClick" : "inspect_edit.delMonitor",
		"componentCls" : "btn-primary"
	}];
	
	$.parseDone(function(){ 
		
		//若非省局登录则设置为本监狱
		if(jsConst.USER_LEVEL!=1){
			$("#inspect_edit_inoNoticeCusNumber").combobox("setValue",cusNumber);
			$("#inspect_edit_inoNoticeCusNumber").combobox("option","readonly",true);
		}
		
		$('#inspect_edit_inoInspectTime').datepicker('setDate', new Date());
		var currentDate = $('#inspect_edit_inoInspectTime').datepicker('getValue');
		$("#inspect_edit_inoInspectName").textbox("setValue","指挥中心每日巡查通报" + currentDate);
		$("#inspect_combotree_checkPoliceIdnty").combotree("tree").tree("reload",{
			asyncUrl: "${ctx}/common/authsystem/findDeptPoliceForCombotree.json?cusNumber="+cusNumber
		} );
		
		$("#inspect_combotree_iprPoliceIdntys").combotree("tree").tree("reload",{
			asyncUrl: "${ctx}/common/authsystem/findDeptPoliceForCombotree.json?cusNumber="+cusNumber
		} );
		
		var id='${id}';
		if(id) {
			loadForm("formId_inspectEdit", "${ctx}/inspect/findById?id=" + id, function(data) {
				$("#inspect_combotree_checkPoliceIdnty").combotree("setValue",data.checkPoliceIdnty);
				$("#inspect_combotree_checkPoliceIdnty").combotree("setText",data.checkPoliceName);
				$("#inspect_combotree_iprPoliceIdntys").combotree("setValue",data.iprPoliceIdntys);
				$("#inspect_combotree_iprPoliceIdntys").combotree("setText",data.iprPoliceNames);
			});
			$("#gridId_monitor_already").grid("reload","${ctx }/monitor/searchMonitorByInspectId.json?inspectId=" + id);
		}
		
		inspect_edit = {
			
			checkPoliceIdntyOnChange: function(e,ui){
				$('#inspect_edit_checkPoliceName').textbox('setValue',ui.text);
			},
			iprPoliceIdntysOnChange: function(e,ui){
				$('#inspect_edit_iprPoliceNames').textbox('setValue',ui.text);
			},
			
			reset:function(){
				$("#formId_inspectEdit").form("reset");
			},
			submit:function(){
				if ($("#formId_inspectEdit").form("valid")) {
					var monitorIds=$("#gridId_monitor_already").grid("getDataIDs");
					if(monitorIds && monitorIds.length>0){
					}else{
						$.alert({
							message:"请先添加监督单，再生成巡查通报！",
							title:"信息提示",
							iframePanel:true
						});
						return;
					}
					$('#inspect_edit_inoCrteUserId').textbox('setValue',userId);
					$('#inspect_edit_inoCrteUserName').textbox('setValue',userName);
					var formData = $("#formId_inspectEdit").form("formData");
					if (cusNumber != "") {
						formData["inoCusNumber"] = cusNumber;
					}
					$.ajax({
						type : 'post',
						url : '${ctx}/inspect/saveOrUpdate.json',
						data : formData,
						dataType : 'json',
						success : function(data) {
							if (data.success) {
								inspect_edit.addMonitorInspect(data.obj);
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
		
				} else {
					$.alert({
						message:"请确认输入是否正确！",
						title:"信息提示",
						iframePanel:true
					});
				}
			},
			addMonitorInspect:function(inspectId){
				var monitorAndInspectList=new Array();
				var monitorIds=$("#gridId_monitor_already").grid("getDataIDs");
				if(monitorIds && monitorIds.length>0){
					$.each(monitorIds,function(i,v){
						monitorAndInspectList.push({
							"IMR_INSPECT_ID":inspectId,						//网络督查通报id
							"IMR_MONITOR_SQNO":v,							//监督单号
						});
					});
				}else{
					$.message({
						message : "上报成功",
						cls : "success",
						iframePanel:true
					});
					$("#dialog").dialog("close");
					return;
				}
				
				
				$.ajax({
					type : 'post',
					url : '${ctx}/monitor/batchInsertMonitorAndInspect.json',
					contentType: "application/json; charset=utf-8",
					data : JSON.stringify(monitorAndInspectList),
					dataType : 'json',
					success : function(data) {
						if (data.success) {
							//生成电子巡查通报单
							window.open('${ctx}/xctb/index?inspectId='+inspectId);
							$.message({
								message : "上报成功",
								cls : "success",
								iframePanel:true
							});
							$('#gridId_inspect_list').grid('reload');
							$("#inspectdetaildialog").dialog("close");
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
			addMonitorRecord:function(){
				var ToBeAddeds=$("#gridId_monitor_toBe").grid("option", "selarrrow");
				if (ToBeAddeds == null || ToBeAddeds.length<=0) {
					$.message({
						iframePanel:true,
						message : "请先勾选需要处理记录！",
						cls : "warning"
					});
					return;
				}
				var AlreadyAddeds=$("#gridId_monitor_already").grid("getDataIDs");
				
				$.each(ToBeAddeds,function(index,value){
					if($.inArray(value,AlreadyAddeds)!=-1){
						var monitorName = $("#gridId_monitor_toBe").grid("getCell",ToBeAddeds[index],"MDO_MONITOR_NAME");
						 $.messageQueue( {
						        message:monitorName+"已存在，不可重复添加",
						        iframePanel : true,
						        type:"info"
						    });  
					}else{
						 var rowData = $("#gridId_monitor_toBe").grid("getRowData",ToBeAddeds[index]);
						 $("#gridId_monitor_already").grid("addRowData",rowData.id,rowData);
					}
				})
				$("#monitorDialog").dialog( "close" ); 
			},
			//添加监督单
			addMonitor:function(){
				$("#gridId_monitor_toBe").grid("reload","${ctx }/monitor/searchMonitor.json?mdoCusNumber="+jsConst.ORG_CODE);
				$("#monitorDialog").dialog({
					width : 900,
					height : 500,
					subTitle : '监督单列表',
					buttons: [ { text: "确认添加",
								//cls:"greenbtn", 
								click: function(){
									inspect_edit.addMonitorRecord()
								}
						}]
				});
				$("#monitorDialog").dialog("open");
			},
			//删除监督单
			delMonitor:function(){
				var checkIds = $('#gridId_monitor_already').grid("option", "selarrrow");
				if (checkIds != null && checkIds.length>0) {
					$.confirm( {
						message:"确认删除？",
						iframePanel:true,
						callback: function(sure) {
							if (sure == true) {
								debugger;
								for(var i=checkIds.length-1;i>=0;i--){
									$('#gridId_monitor_already').grid("delRowData",checkIds[i].toString());
								}
							}
							if (sure == false) {
								console.log('cancel');
							}
						}
					});
				} else {
					$.message({
						iframePanel:true,
						message : "请先勾选需要处理记录！",
						cls : "warning"
					});
				}
			},
			search_monitor:function(){
				var postData={};
				var startTime=$("#start_monitor_cjsj").datepicker("getValue");
				var endTime=$("#end_monitor_cjsj").datepicker("getDateValue");
				
				if (startTime != "") {
					postData['startTime'] = startTime;
				}
				if (endTime != "") {
					postData['endTime'] = endTime;
				}

				$('#gridId_monitor_toBe').grid('option', 'postData', postData);
				$("#gridId_monitor_toBe").grid("reload","${ctx }/monitor/searchMonitor.json?mdoCusNumber="+jsConst.ORG_CODE);
			},
			detailedMonitor:function(selrow){
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
			},
			resetHandler_monitor:function(){
				$('#queryForm_monitor').form("reset");
			}
		}
	});
	
</script>
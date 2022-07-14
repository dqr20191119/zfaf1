<%@page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<link rel="stylesheet" type="text/css"
	href="${ctx}/static/module/evidence/evidence.css" />

<cui:tabs heightStyle="fill">
	<ul>
		<!-- <li><a href="#fragment_ysjdd">已收监督单</a></li> -->
		<li><a href="#fragment_deptysjdd">已收监督单</a></li>
		<li><a href="#fragment_zjxx">证据信息</a></li>
		<li><a href="#fragment_yjjdd">监督单</a></li>
		<!-- <li><a href="#fragment_yfjdd">已发监督单</a></li> -->
	</ul>
	<div id="fragment_zjxx" style="padding-top: 5px;">
	<cui:form id="queryForm_zjxx">
		<table class="table">
			<tr>
				<%-- <td align="right"><label>信息状态:</label></td>
				<td ><cui:combobox id="zjxxzt" name="zjxxzt"
						componentCls="form-control" data="comboboxData_zjxxzt"></cui:combobox></td> --%>
				<td align="right"><label>文件类型:</label></td>
				<td ><cui:combobox id="wjlx" name="wjlx"
						componentCls="form-control" data="zjwjlxJson"></cui:combobox></td>
			<!-- </tr>
			<tr> -->
				<td align="right"><label>记录时间:</label></td>
				<td><cui:datepicker id="startingDate"
						componentCls="form-control" dateFormat="yyyy-MM-dd HH:mm:ss"></cui:datepicker></td>
				<td width="5px">至</td>
				<td><cui:datepicker id="endingDate" componentCls="form-control"
						startDateId="startingDate" dateFormat="yyyy-MM-dd HH:mm:ss"></cui:datepicker></td>
						
				<td><cui:button cls="cyanbtn" id="s_searchButton" label="查询"
					 onClick="searchZjxx" componentCls="coral-btn-blue" /></td>
				<td><cui:button id="resetBtn" label="重置" onClick="resetHandler_zjxx" /></td>
			</tr>
			</table></cui:form>
		<cui:toolbar id="toolbarId_zjxx" data="toolbarData_zjxx"
			onClick="toolbarOnClick_zjxx"></cui:toolbar>
		<cui:grid id="gridId_zjxx" multiselect="true"  
			colModel="gridColModel_zjxx" shrinkToFit="true" fitStyle="fill" datatype="json"
			url="" pager="true"></cui:grid>
	</div>
	<div id="fragment_yjjdd" style="padding-top: 5px;">
		<cui:form id="queryForm_yjjdd">
		<table class="table">
			<tr>
				<td align="right"><label>创建时间:</label></td>
				<td><cui:datepicker id="start_cjsj"
						componentCls="form-control" dateFormat="yyyy-MM-dd HH:mm:ss"></cui:datepicker></td>
				<td width="5px">至</td>
				<td><cui:datepicker id="end_cjsj" componentCls="form-control"
						startDateId="start_cjsj" dateFormat="yyyy-MM-dd HH:mm:ss"></cui:datepicker></td>
					
				<td><cui:button cls="cyanbtn" id="s_searchButton" label="查询"
					 onClick="f_search_yjjdd" componentCls="coral-btn-blue" /></td>
				<td><cui:button id="resetBtn" label="重置" onClick="resetHandler_yjjdd" /></td>
			</tr>
			</table></cui:form>
		<cui:toolbar id="toolbarId_yjjdd" data="toolBarData_yjjdd"
			onClick="toolbarOnClick_yjjdd"></cui:toolbar>
		<cui:grid id="gridId_yjjdd" singleselect="true"  shrinkToFit="true"
			colModel="gridColModel_yjjdd" fitStyle="fill" datatype="json"
			url="" pager="true"></cui:grid>
	</div>
	<%-- <div id="fragment_yfjdd" style="padding-top: 5px;">
		<cui:form id="queryForm_yfjdd">
		<table class="table">
			<tr>
				<td align="right"><label>推送时间:</label></td>
				<td><cui:datepicker id="start_tssj"
						componentCls="form-control" dateFormat="yyyy-MM-dd HH:mm:ss"></cui:datepicker></td>
				<td width="5px">至</td>
				<td><cui:datepicker id="end_tssj" componentCls="form-control"
						startDateId="start_tssj" dateFormat="yyyy-MM-dd HH:mm:ss"></cui:datepicker></td>
						
				<td><cui:button cls="cyanbtn" id="s_searchButton" label="查询"
					 onClick="f_search_yfjdd" componentCls="coral-btn-blue" /></td>
				<td><cui:button id="resetBtn" label="重置" onClick="resetHandler_yfjdd" /></td>
			</tr>
			</table></cui:form>
		
		<cui:grid id="gridId_yfjdd" shrinkToFit="true"
			colModel="gridColModel_yfjdd" fitStyle="fill" datatype="json"
			url="" pager="true"></cui:grid>
	</div>
	<div id="fragment_ysjdd" style="padding-top: 5px;">
		<cui:form id="queryForm_ysjdd">
		<table class="table">
			<tr>
				<td align="right"><label>接收时间:</label></td>
				<td><cui:datepicker id="start_jssj"
						componentCls="form-control" dateFormat="yyyy-MM-dd HH:mm:ss"></cui:datepicker></td>
				<td width="5px">至</td>
				<td><cui:datepicker id="end_jssj" componentCls="form-control"
						startDateId="start_jssj" dateFormat="yyyy-MM-dd HH:mm:ss"></cui:datepicker></td>
						
				<td><cui:button cls="cyanbtn" id="s_searchButton" label="查询"
					 onClick="f_search_ysjdd" componentCls="coral-btn-blue" /></td>
				<td><cui:button id="resetBtn" label="重置" onClick="resetHandler_ysjdd" /></td>
			</tr>
			</table></cui:form>
		<cui:toolbar id="toolbarId_ysjdd" data="toolbarData_ysjdd"
			onClick="toolbarOnClick_ysjdd"></cui:toolbar>
		<cui:grid id="gridId_ysjdd" singleselect="true"  shrinkToFit="true"
			colModel="gridColModel_ysjdd" fitStyle="fill" datatype="json"
			url="" pager="true"></cui:grid>
	</div> --%>
	
	<%--部门接收监督单列表，之前是推送到人，现在推送到部门 --%>
	<div id="fragment_deptysjdd" style="padding-top: 5px;">
		<cui:form id="queryForm_deptysjdd">
		<table class="table">
			<tr>
				<td align="right"><label>推送时间:</label></td>
				<td><cui:datepicker id="start_deptjssj"
						componentCls="form-control" dateFormat="yyyy-MM-dd HH:mm:ss"></cui:datepicker></td>
				<td width="5px">至</td>
				<td><cui:datepicker id="end_deptjssj" componentCls="form-control"
						startDateId="start_deptjssj" dateFormat="yyyy-MM-dd HH:mm:ss"></cui:datepicker></td>
						
				<td><cui:button cls="cyanbtn" label="查询"
					 onClick="f_search_deptysjdd" componentCls="coral-btn-blue" /></td>
				<td><cui:button label="重置" onClick="resetHandler_deptysjdd" /></td>
			</tr>
			</table></cui:form>
		<cui:toolbar id="toolbarId_deptysjdd" data="toolbarData_deptysjdd"
			onClick="toolbarOnClick_deptysjdd"></cui:toolbar>
		<cui:grid id="gridId_deptysjdd" singleselect="true"  shrinkToFit="true"
			colModel="gridColModel_deptysjdd" fitStyle="fill" datatype="json"
			url="" pager="true"></cui:grid>
	</div>
</cui:tabs>

<cui:dialog id="dialogId_jdjc" autoOpen="false" iframePanel="true"  reLoadOnOpen="true"  modal="true" resizable="false"></cui:dialog>

<cui:dialog id="ysjdd_sendToDept" title="选择推送部门" autoOpen="false" iframePanel="true"  reLoadOnOpen="true"  modal="true" resizable="false" width="300" height="auto">
	<cui:input  type="hidden"  id="deptysjdd_monitorId" ></cui:input>
	<center style="height:60px;overflow-y:scroll;margin-top:20px">
		<cui:combobox  id="sendToDept_DeptId" name="mdoNoticeDepartment" url="" placeholder="选择部门" componentCls="form-control" ></cui:combobox>
	</center>
	<div class="dialog-buttons" >
		<cui:button label="确定"  onClick="ysjdd_sendDept_save"></cui:button>
	</div>
</cui:dialog>

<cui:dialog id="ysjdd_sendToUser" title="选择推送对象" autoOpen="false" iframePanel="true"  reLoadOnOpen="true"  modal="true" resizable="false" width="300" height="auto">
	<cui:input  type="hidden"  id="ysjdd_monitorId" ></cui:input>
	<cui:input  texticons="" placeholder="Search" onKeyPress="ysjdd_userSearch"></cui:input>
	<div style="height:350px;overflow-y:scroll;">
		<cui:tree id="ysjdd_sendToUserTree" checkable="true" chkboxType="chkboxType" chkStyle="checkbox" >
		</cui:tree>
	</div>
	<div class="dialog-buttons" >
		<cui:button label="确定"  onClick="ysjdd_sendToUserTree_save"></cui:button>
	</div>
</cui:dialog>

<cui:dialog id="yfjdd_cyqk" title="查阅情况" autoOpen="false" iframePanel="true"  reLoadOnOpen="true"  modal="true" resizable="false" width="300" height="300">
	<cui:grid id="gridId_yfjdd_cyqk" 
			colModel="gridColModel_yfjdd_cyqk" fitStyle="fill" datatype="json"
			url=""></cui:grid>
</cui:dialog>


<!-- 单独显示图片或视频信息 -->
	<div id="imgView" class="video-file" style="display:none;overflow:hidden">
		<img id="imgEvi" src="" style="width:100%;height:100%;">
	</div>
	<div id="videoView" class="video-file" style="display:none;overflow:hidden">
		<div class="play" title="点击播放录像文件..."></div>
		<img id="videoEvi" src="">
	</div>
	
<script type="text/javascript"
	src="${ctx}/static/module/evidence/evidence.js"></script>
<script type="text/javascript"
	src="${ctx}/static/module/evidence/messenger.js"></script>
<script>
var chkboxType = {
		'Y' : 'ps',
		'N' : 'ps'
	}
var jsConst= window.top.jsConst;
var evidence=window.top.evidence;
var messenger=window.top.messenger;
$.parseDone(function(){
	//证据信息
	$("#gridId_zjxx").grid("reload","${ctx }/evidence/searchEvidence.json?einSttsIndc=0&einCusNumber="+jsConst.ORG_CODE);
	//监督单
	$("#gridId_yjjdd").grid("reload","${ctx }/monitor/searchMonitor.json?mdoCusNumber="+jsConst.ORG_CODE);
	//已发监督单
	//$("#gridId_yfjdd").grid("reload","${ctx }/monitor/searchYFMonitor.json");
	//已收监督单
	//$("#gridId_ysjdd").grid("reload","${ctx }/monitor/searchReceivedMonitor.json");
	
	//部门已收监督单
	$("#gridId_deptysjdd").grid("reload","${ctx }/monitor/searchDeptReceivedMonitor.json");
})

	var ckztJson  = [ {
		value : 0,
		text : "未查阅"
	}, {
		value : 1,
		text : "已查阅"
	} ];
	var comboboxData_zjxxzt = [ {
		value : 0,
		text : "未使用"
	}, {
		value : 1,
		text : "已使用"
	}];
	var zjwjlxJson =<%=CodeFacade.loadCode2Json("4.20.32")%>;
	var toolbarData_zjxx = [{
		"id" : "create_jdd",
		"label" : "创建监督单",
		"disabled" : "disable",
		"type" : "button",
		"cls" : "cyanbtn",
		"icon" : ""
	}, {
		"id" : "edit_zjxx",
		"label" : "编辑图片证据",
		"disabled" : "disable",
		"type" : "button",
		"cls" : "btn-primary",
		"icon" : ""
	}, {
		"id" : "batchDelete_zjxx",
		"label" : "批量删除证据",
		"disabled" : "disable",
		"type" : "button",
		"cls" : "deleteBtn",
		"icon" : ""
	} ];

	
	var toolBarData_yjjdd = [{
		"id" : "update_yjjdd",
		"label" : "编辑",
		"disabled" : "disable",
		"type" : "button",
		"cls" : "cyanbtn",
		"icon" : ""
	}, {
		"id" : "delete_yjjdd",
		"label" : "删除",
		"disabled" : "false",
		"type" : "button",
		"cls" : "deleteBtn",
		"icon" : ""

	}, {
		"id" : "pushToDepartment",
		"label" : "推送",
		"disabled" : "false",
		"type" : "button",
		"cls" : "greenbtn",
		"onClick" : "pushToDepartment",
		"icon" : ""
	}/* , {
		"id" : "push_yjjdd",
		"label" : "推送",
		"disabled" : "false",
		"type" : "button",
		"cls" : "greenbtn",
		"icon" : ""
	} */ ];
	var toolbarOnClick_yjjdd = function(event, ui) {
		if ("update_yjjdd" == ui.id) {
			f_updateYjjdd();
		}else if("delete_yjjdd" == ui.id){
			f_deleteYjjdd();
		}else if("push_yjjdd" == ui.id){
			f_pushYjjdd();
		}
	}
	//查询已建监督单
	function f_search_yjjdd(){
		var postData={};
		var startTime=$("#start_cjsj").datepicker("getValue");
		var endTime=$("#end_cjsj").datepicker("getDateValue");
		
		if (startTime != "") {
			postData['startTime'] = startTime;
		}
		if (endTime != "") {
			postData['endTime'] = endTime;
		}

		$('#gridId_yjjdd').grid('option', 'postData', postData);
		//$("#gridId_yjjdd").grid("reload","${ctx }/monitor/searchMonitor.json?modSttsIndc=0&mdoCusNumber="+jsConst.ORG_CODE);
        $("#gridId_yjjdd").grid("reload");
	}
	//编辑已建监督单
	function f_updateYjjdd(){
		var selrow = $("#gridId_yjjdd").grid("option", "selrow");
		if (selrow != null) {
			$.ajax({
				type : 'post',
				url : '${ctx}/monitor/monitorCanEdit.json?monitorSqno='+selrow.toString(),
				dataType : 'json',
				success : function(data) {
					if(data.success){
						$("#dialogId_jdjc").dialog({
							width : 700,
							height : "auto",
							subTitle : '创建监督单',
							url : '${ctx}/monitor/edit?id='+selrow.toString(),
						});
						//messenger.js
						messenger.isDetailShow=false;
						$("#dialogId_jdjc").dialog("open");
					}else{
						$.message( {
							iframePanel:true,
					        message:data.msg,
					        type:"alert"
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
			$.message({
				iframePanel:true,
				message : "请先勾选需要处理记录！",
				cls : "warning"
			});
		}
	}
	function f_deleteYjjdd(){
		var selrow = $("#gridId_yjjdd").grid("option", "selrow");
		if (selrow != null) {
			$.confirm( {
				message:"确认删除？",
				iframePanel:true,
				callback: function(sure) {
					if (sure == true) {
						$.ajax({
							type : 'post',
							url : '${ctx}/monitor/deleteYjjdd.json?cusNumber='+jsConst.ORG_CODE+'&monitorSqno='+selrow.toString(),
							dataType : 'json',
							success : function(data) {
								if(data.success){
									$.message({
										message : "操作成功",
										cls : "success",
										iframePanel:true
									});
									//刷新证据列表
									$("#gridId_zjxx").grid("reload",jsConst.basePath+"/evidence/searchEvidence.json?einSttsIndc=0&einCusNumber="+jsConst.ORG_CODE);
									//刷新监督单列表
									$("#gridId_yjjdd").grid("reload");
								}else{
									$.message( {
										iframePanel:true,
								        message:data.msg,
								        type:"alert"
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
			
		} else {
			$.message({
				iframePanel:true,
				message : "请先勾选需要处理记录！",
				cls : "warning"
			});
		}
	}
	function pushToDepartment(){
		var selrow = $("#gridId_yjjdd").grid("option", "selrow");
		var rowData=$("#gridId_yjjdd").grid("getRowData", selrow.toString());
		if(rowData.MOD_STTS_INDC && rowData.MOD_STTS_INDC != "0"){
			$.message({
				iframePanel:true,
				message : "该监督单已经推送，不可重复推送！",
				cls : "warning"
			});
			return;
		}
		//用户等级
		var USER_LEVEL = jsConst.USER_LEVEL;
		//监狱级用户
		if(USER_LEVEL=='2'){
			if(!rowData.MDO_NOTICE_DEPARTMENT){
				$.message({
					iframePanel:true,
					message : "未设置推送部门！",
					cls : "warning"
				});
				return;
			}
		}
		if (selrow != null) {
			$.confirm( {
				message:'是否确认推送名为['+rowData.MDO_MONITOR_NAME+']的监督单信息?',
				iframePanel:true,
				callback: function(sure) {
					if (sure == true) {
						 var list ={
					    			"modSttsIndc":"1",					//已推送
					    			"id":selrow.toString()
					    		 };
								 updateMdoStatus(list);
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
	}
	function f_pushYjjdd(){
		var selrow = $("#gridId_yjjdd").grid("option", "selrow");
		var rowData=$("#gridId_yjjdd").grid("getRowData", selrow.toString());
		if (selrow != null) {
			$.confirm( {
				message:'是否确认推送名为['+rowData.MDO_MONITOR_NAME+']的监督单信息?',
				iframePanel:true,
				callback: function(sure) {
					if (sure == true) {
						 var list ={
					    			"modSttsIndc":"1",					//已推送
					    			"id":selrow.toString()
					    		 };
								 updateMdoStatus(list);
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
	}
	var gridColModel_yjjdd = [ {
		label : "id",
		name : "ID",
		hidden : true,
		key:true
	},{
		label : "监督单状态",
		name : "MOD_STTS_INDC",
		hidden : true
	},{
		label : "推送部门",
		name : "MDO_NOTICE_DEPARTMENT",
		hidden : true
	}, {
		label : "监督单名称",
		width : 250,
		align : "center",
		name : "MDO_MONITOR_NAME"
	}, {
		label : "记录时间",
		align : "center",
		hidden : true,
		name : "MDO_TIME"
	}, {
		label : "记录地点",
		width : 200,
		hidden : true,
		align : "center",
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
		var result="<span><a href='#' style='color: #4692f0;' onclick='f_detailedYfjdd(\""+rowObject.ID+"\");return false;'>详细</a></span>";
		return result;
	}
	var gridColModel_zjxx = [ {
		label : "id",
		name : "ID",
		hidden : true,
		key:true
	}, {
		label : "证据标题",
		width : 320,
		align:"center",
		name : "EIN_TITLE"
	}, {
		label : "存在问题",
		hidden : true,
		name : "EIN_PROBLEM"
	}, {
		label : "详细描述",
		hidden : true,
		name : "EIN_CONTENT_DESC"
	}, {
		label : "监控地点",
		width : 200,
		align : "center",
		hidden : true,
		name : "EIN_ADDRS"
	}, {
		label : "状态",
		align:"center",
		hidden : true,
		name : "EIN_STTS_INDC",
		formatter : "convertCode",
		revertCode : true,
		formatoptions : {
			'dataStructure' : 'list',
			'data':comboboxData_zjxxzt
		} 
	}, {
		label : "记录人",
		align:"center",
		name : "CRTE_USER_NAME"
	}, {
		label : "记录时间",
		align:"center",
		name : "EIN_CRTE_TIME"
	},{
		label : "文件类型",
		name : "EIN_FILE_TYPE_INDC",
		align:"center",
		width : 80,
		formatter : "convertCode",
		revertCode : true,
		formatoptions : {
			'dataStructure' : 'list',
			'data':zjwjlxJson
		} 

	},{
		label : "操作",
		align:"center",
		width : 80,
		formatter:"formatterA",
	}];
	function formatterA(cellValue,options,rowObject){
		var result="<span><a href='#' style='color: #4692f0;' onclick='showEvidence(\""+rowObject.EIN_FILE_TYPE_INDC+"\",\""+rowObject.ID+"\");return false;'>查看</a></span>";
		return result;
	}
	
	//查询已发监督单
	function f_search_yfjdd(){
		var postData={};
		var startTime=$("#start_tssj").datepicker("getValue");
		var endTime=$("#end_tssj").datepicker("getDateValue");
		if (startTime != "") {
			postData['startTime'] = startTime;
		}
		if (endTime != "") {
			postData['endTime'] = endTime;
		}
		$('#gridId_yfjdd').grid('option', 'postData', postData);
		$("#gridId_yfjdd").grid("reload","${ctx }/monitor/searchYFMonitor.json");
	}
	//已发监督单详细
	function f_detailedYfjdd(selrow){
		if(!selrow){
			selrow = $("#gridId_yfjdd").grid("option", "selrow");
		}
		if (selrow != null) {
			$("#dialogId_jdjc").dialog({
				width : 700,
				height : "auto",
				subTitle : '监督单详细',
				url : '${ctx}/monitor/edit?id='+selrow.toString(),
			});
			//messenger.js
			messenger.isDetailShow=true;
			$("#dialogId_jdjc").dialog("open");
		} else {
			$.message({
				iframePanel:true,
				message : "请先勾选需要处理记录！",
				cls : "warning"
			});
		}
	}
	var gridColModel_yfjdd = [ {
		label : "id",
		name : "ID",
		hidden : true,
		key:true
	}, {
		label : "监督单名称",
		width : 240,
		align : "center",
		name : "MDO_MONITOR_NAME"
	}, {
		label : "记录时间",
		align : "center",
		hidden : true,
		name : "MDO_TIME"
	}, {
		label : "记录地点",
		width : 200,
		align : "center",
		name : "MDO_ADDR"
	}, {
		label : "存在问题",
		width : 280,
		align : "center",
		name : "MDO_PROBLEM"
	}, {
		label : "推送时间",
		name : "MDO_UPDT_TIME"
	}, {
		label : "推送人",
		width : 100,
		align : "center",
		hidden : true,
		name : "CRTE_USER_NAME"
	},{
		label : "接收人查阅情况",
		name : "MDO_CONSULT_STATUS",
		align:"center",
		formatter:"formatterCYQK",
	},{
		label : "操作",
		align:"center",
		width : 80,
		formatter:"formatterDetail",
	} ];
	var gridColModel_deptysjdd = [ {
		label : "id",
		name : "ID",
		hidden : true,
		key:true
	},{
		label : "监督单状态",
		name : "MOD_STTS_INDC",
		hidden : true
	},{
		label : "是否来自省局推送",
		name : "MDO_IS_FROM_PROV",
		hidden : true
	},{
		label : "查阅状态（针对省局发送的监督单，监狱级用户的查阅状态）",
		name : "MDO_CONSULT_STATUS",
		hidden : true
	}, {
		label : "监督单名称",
		width : 240,
		align : "center",
		name : "MDO_MONITOR_NAME"
	}, {
		label : "推送人",
		width : 100,
		align : "center",
		name : "UPDT_USER_NAME"
	}, {
		label : "推送时间",
		align : "center",
		name : "MDO_UPDT_TIME"
	}, {
		label : "查看状态",
		align : "center",
		name : "STATUS",
		formatter:"formatterMrrStatus"
	}, {
		label : "存在问题",
		width : 280,
		align : "center",
		hidden : true,
		name : "MDO_PROBLEM"
	}, {
		label : "记录时间",
		align : "center",
		hidden : true,
		name : "MDO_TIME"
	}, {
		label : "记录地点",
		width : 200,
		align : "center",
		hidden : true,
		name : "MDO_ADDR"
	},  {
		label : "接收时间",
		align : "center",
		hidden : true,
		name : "MDO_UPDT_TIME"
	}, {
		label : "接收人",
		width : 100,
		align : "center",
		hidden : true,
		name : "MRR_RCPNT_NAME"
	}];
	var gridColModel_ysjdd = [ {
		label : "id",
		name : "ID",
		hidden : true,
		key:true
	},{
		label : "监督单状态",
		name : "MOD_STTS_INDC",
		hidden : true
	},{
		label : "是否来自省局推送",
		name : "MDO_IS_FROM_PROV",
		hidden : true
	},{
		label : "查阅状态（针对省局发送的监督单，监狱级用户的查阅状态）",
		name : "MDO_CONSULT_STATUS",
		hidden : true
	}, {
		label : "监督单名称",
		width : 240,
		align : "center",
		name : "MDO_MONITOR_NAME"
	}, {
		label : "推送人",
		width : 100,
		align : "center",
		name : "UPDT_USER_NAME"
	}, {
		label : "推送时间",
		align : "center",
		name : "MDO_UPDT_TIME"
	}, {
		label : "查看状态",
		align : "center",
		name : "MRR_STATUS",
		formatter:"formatterMrrStatus"
	}, {
		label : "存在问题",
		width : 280,
		align : "center",
		hidden : true,
		name : "MDO_PROBLEM"
	}, {
		label : "记录时间",
		align : "center",
		hidden : true,
		name : "MDO_TIME"
	}, {
		label : "记录地点",
		width : 200,
		align : "center",
		hidden : true,
		name : "MDO_ADDR"
	},  {
		label : "接收时间",
		align : "center",
		hidden : true,
		name : "MDO_UPDT_TIME"
	}, {
		label : "接收人",
		width : 100,
		align : "center",
		hidden : true,
		name : "MRR_RCPNT_NAME"
	}];
	
	var gridColModel_yfjdd_cyqk = [{
		label : "接收人",
		align : "center",
		name : "MRR_RCPNT_NAME"
	}, {
		label : "查看状态",
		align : "center",
		name : "MRR_STATUS",
		formatter:"formatterMrrStatus"
	}];
	function formatterMrrStatus(cellValue,options,rowObject){
		var result="";
		if(cellValue=='0'){
			result='<font color="#FF9900">未查阅</font>';
		}else if(cellValue=='1'){
			result="已查阅";
		}else if(cellValue=='已下发'){
			result='<font color="green">已下发</font>';
		}else if(cellValue=='待下发'){
			result='<font color="#FF0000">待下发</font>';
		}
		return result;
	}
	function formatterCYQK(cellValue,options,rowObject){
		//用户等级
		var USER_LEVEL = jsConst.USER_LEVEL;
		//省局
		if(USER_LEVEL=='1'){
			var result;
			if(cellValue=='1'){
				result="已查阅";
			}else{
				result='<font color="#FF9900">未查阅</font>';
			}	
			return result;
		}else{
			var result="<span><a href='#' style='color: #4692f0;' onclick='show_ys_cyqk(\""+rowObject.ID+"\");return false;'>查看</a></span>";
			return result;
		}
	}
	var toolbarData_deptysjdd = [{
		"id" : "detailed_deptysjdd",
		"label" : "查阅",
		"disabled" : "disable",
		"type" : "button",
		"cls" : "cyanbtn",
		"icon" : ""
	}, {
		"id" : "push_deptysjdd",
		"label" : "下发",
		"disabled" : "false",
		"type" : "button",
		"cls" : "greenbtn",
		"icon" : ""
	} ];
	var toolbarOnClick_deptysjdd = function(event, ui) {
		if("detailed_deptysjdd"==ui.id){
			f_detailedDeptYsjdd();
		}else if("push_deptysjdd"==ui.id){
			f_pushDeptysjdd();
		}
	}
	var toolbarData_ysjdd = [{
		"id" : "detailed_ysjdd",
		"label" : "查阅",
		"disabled" : "disable",
		"type" : "button",
		"cls" : "cyanbtn",
		"icon" : ""
	}, {
		"id" : "push_ysjdd",
		"label" : "下发",
		"disabled" : "false",
		"type" : "button",
		"cls" : "greenbtn",
		"icon" : ""
	} ];
	var toolbarOnClick_ysjdd = function(event, ui) {
		if("detailed_ysjdd"==ui.id){
			f_detailedYsjdd();
		}else if("push_ysjdd"==ui.id){
			f_push_ysjdd();
		}
	}
	//查询部门已收监督单
	function f_search_deptysjdd(){
		var params;
		var startTime=$("#start_deptjssj").datepicker("getValue");
		var endTime=$("#end_deptjssj").datepicker("getDateValue");
		if (startTime != "") {
			params['startTime'] = startTime;
		}
		if (endTime != "") {
			params['endTime'] = endTime;
		}
		$("#gridId_deptysjdd").grid("reload","${ctx }/monitor/searchDeptReceivedMonitor.json?params="+JSON.stringify(params));
	}
	//查询已收监督单
	function f_search_ysjdd(){
		var params;
		var startTime=$("#start_jssj").datepicker("getValue");
		var endTime=$("#end_jssj").datepicker("getDateValue");
		if (startTime != "") {
			params['startTime'] = startTime;
		}
		if (endTime != "") {
			params['endTime'] = endTime;
		}
		
		$("#gridId_ysjdd").grid("reload","${ctx }/monitor/searchReceivedMonitor.json?params="+JSON.stringify(params));

	}
	function ysjdd_initSendToUserTree(){
		$.ajax({
			type : 'post',
			url : "${ctx }/common/authsystem/findSyncDeptPoliceForCombotree.json?cusNumber="+cusNumber,
			dataType : 'json',
			success : function(data) {
				
				if(data.exception==undefined){
					$("#ysjdd_sendToUserTree").tree("reload",data);

				}else{
					$.message( {
						iframePanel:true,
				        message:data.exception.cause.message,
				        type:"danger"
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
	
	//下发到部门，针对省局推送的监督单
	function f_pushDeptysjdd(){
		var selrow = $("#gridId_deptysjdd").grid("option", "selrow");
		if (selrow != null){
			var selrowData = $("#gridId_deptysjdd").grid("getRowData", selrow.toString());
			if(selrowData.MDO_IS_FROM_PROV!="1"){
				$.message({
					iframePanel:true,
					message : "下发只是针对于省局推送的监督单！",
					cls : "warning"
				});
				return;
			}
			if(selrowData.MOD_STTS_INDC=="2"){
				$.message({
					iframePanel:true,
					message : "该记录已下发！",
					cls : "warning"
				});
				return;
			}
			if(selrowData.MDO_CONSULT_STATUS!="1"){
				$.message({
					iframePanel:true,
					message : "请先查阅！",
					cls : "warning"
				});
				return;
			}
			$("#sendToDept_DeptId").combobox("clear");
			$("#sendToDept_DeptId").combobox("reload","${ctx}/common/authsystem/findAllChildrenOrgByJyKeyForCombobox.json?cusNumber="+cusNumber);
			$("#ysjdd_sendToDept").dialog("open");
			$("#deptysjdd_monitorId").textbox("setValue",selrowData.ID);
			
		} else{
			$.message({
				iframePanel:true,
				message : "请先勾选需要处理记录！",
				cls : "warning"
			});
		}
	}
	//下发
	function f_push_ysjdd(){
		var selrow = $("#gridId_ysjdd").grid("option", "selrow");
		if (selrow != null){
			var selrowData = $("#gridId_ysjdd").grid("getRowData", selrow.toString());
			if(selrowData.MDO_IS_FROM_PROV!="1"){
				$.message({
					iframePanel:true,
					message : "请选择待下发的记录！",
					cls : "warning"
				});
				return;
			}
			if(selrowData.MOD_STTS_INDC=="2"){
				$.message({
					iframePanel:true,
					message : "该记录已下发！",
					cls : "warning"
				});
				return;
			}
			if(selrowData.MDO_CONSULT_STATUS!="1"){
				$.message({
					iframePanel:true,
					message : "请先查阅！",
					cls : "warning"
				});
				return;
			}
			$("#ysjdd_sendToUser").dialog("open");
			//初始化接收人树（同步树）
			ysjdd_initSendToUserTree();
			$("#ysjdd_monitorId").textbox("setValue",selrowData.ID);
			
		} else{
			$.message({
				iframePanel:true,
				message : "请先勾选需要处理记录！",
				cls : "warning"
			});
		}
	}
	
	//部门已收监督单详细
	function f_detailedDeptYsjdd(){
		var selrow = $("#gridId_deptysjdd").grid("option", "selrow");
		if (selrow != null){
			$("#dialogId_jdjc").dialog({
				width : 700,
				height : "auto",
				subTitle : '监督单详细',
				url : '${ctx}/monitor/edit?id='+selrow.toString(),
			});
			//messenger.js
			messenger.isDetailShow=true;
			$("#dialogId_jdjc").dialog("open");
			
			var selrowData = $("#gridId_deptysjdd").grid("getRowData", selrow.toString());
			//用户等级
			var USER_LEVEL = jsConst.USER_LEVEL;
			//省局
			if(USER_LEVEL=='1'){
				console.log("省局不存在已收，只会下发给监狱");
				return;
			}
			//监狱
			else if(USER_LEVEL=='2'){
				//监督单来自省局
				if(selrowData.MDO_IS_FROM_PROV=="1"){
					if(selrowData.MDO_CONSULT_STATUS=="1"){
						console.log("已查阅");
						return;
					}else{
						var list ={
			    			"mdoConsultStatus":"1",				//已查阅
			    			"id":selrow.toString(),					 //监督单ID
			    		 };
						 updateMdoStatus(list);
					}
				}
				else{
					if(selrowData.STATUS=="1"){
						console.log("已查阅");
						return;
					}
				}
			}
			//监区
			else if(USER_LEVEL=='3'){
				if(selrowData.STATUS=="1"){
					console.log("已查阅");
					return;
				}
			}
			
			
			//修改查看状态
			var list ={
	    			"mdoConsultStatus":"1",				//已查阅
	    			"id":selrow.toString(),					 //监督单ID
	    		 };
				 updateMdoStatus(list);
		} else{
			$.message({
				iframePanel:true,
				message : "请先勾选需要处理记录！",
				cls : "warning"
			});
		}
	}
	//已收监督单详细
	function f_detailedYsjdd(){
		var selrow = $("#gridId_ysjdd").grid("option", "selrow");
		if (selrow != null){
			$("#dialogId_jdjc").dialog({
				width : 700,
				height : "auto",
				subTitle : '监督单详细',
				url : '${ctx}/monitor/edit?id='+selrow.toString(),
			});
			//messenger.js
			messenger.isDetailShow=true;
			$("#dialogId_jdjc").dialog("open");
			
			var selrowData = $("#gridId_ysjdd").grid("getRowData", selrow.toString());
			//用户等级
			var USER_LEVEL = jsConst.USER_LEVEL;
			//省局
			if(USER_LEVEL=='1'){
				console.log("省局不存在已收，只会下发给监狱");
				return;
			}
			//监狱
			else if(USER_LEVEL=='2'){
				//监督单来自省局
				if(selrowData.MDO_IS_FROM_PROV=="1"){
					if(selrowData.MDO_CONSULT_STATUS=="1"){
						console.log("已查阅");
						return;
					}else{
						var list ={
			    			"mdoConsultStatus":"1",				//已查阅
			    			"id":selrow.toString(),					 //监督单ID
			    		 };
						 updateMdoStatus(list);
					}
				}
				else{
					if(selrowData.MRR_STATUS=="1"){
						console.log("已查阅");
						return;
					}
				}
			}
			//监区
			else if(USER_LEVEL=='3'){
				if(selrowData.MRR_STATUS=="1"){
					console.log("已查阅");
					return;
				}
			}
			
			
			//修改查看状态
			 var seeStatusParam ={
				"mrrStatus":"1",					 			// 1 - 已查看状态
				"mrrMonitorSqno":selrow.toString(),					 //监督单ID
				"mrrRcpntIdnty":jsConst.USER_ID
			 };
			 $.ajax({
				type : 'post',
				url : '${ctx}/monitor/updateMrrStatus.json',
				contentType: "application/json; charset=utf-8",
				data : JSON.stringify(seeStatusParam),
				dataType : 'json',
				success : function(data) {
					if(data.success){
						console.log("success");
						//已收监督单
						$("#gridId_ysjdd").grid("reload","${ctx }/monitor/searchReceivedMonitor.json");
					}else{
						$.message( {
							iframePanel:true,
					        message:data.msg,
					        type:"danger"
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
		} else{
			$.message({
				iframePanel:true,
				message : "请先勾选需要处理记录！",
				cls : "warning"
			});
		}
	}

	
	//发送对象查询
	function ysjdd_userSearch(e, data) {
		//回车时触发
		if(e.keyCode == 13)      
	    {
			$("#ysjdd_sendToUserTree").tree("filterNodesByParam", {
				name : data.value
			});
	    }	
	}
	function ysjdd_sendDept_save(){
		var mdoNoticeDepartment = $("#sendToDept_DeptId").combobox("getValue");
		//监督单号
		var monitorDocId = $("#deptysjdd_monitorId").textbox("getValue");
		var list ={
				"mdoNoticeDepartment":mdoNoticeDepartment,
    			"modSttsIndc":"2",					//已下发
    			"id":monitorDocId
    		 };
		updateMdoStatus(list);
		$("#ysjdd_sendToDept").dialog("close");
	}
	function ysjdd_sendToUserTree_save(){
		var nodes=$("#ysjdd_sendToUserTree").tree("getCheckedNodes");
		
		for(var i=0;i<nodes.length;i++){
			if(!nodes[i].isParent){
				var number = nodes[i].id;
		    	var name = nodes[i].name;
		    	evidence.sendToUserList[number] = name;
			}
		}
		//监督单号
		var monitorDocId = $("#ysjdd_monitorId").textbox("getValue");
		var list ={
    			"modSttsIndc":"2",					//已下发
    			"id":monitorDocId
    		 };
		savaRelationMonRec(monitorDocId,updateMdoStatus(list));
		$("#ysjdd_sendToUser").dialog("close");
	}
	function show_ys_cyqk(monitorDocId){
		
		$.ajax({
			type : 'post',
			url : jsConst.basePath+'/monitor/searchRelationMonRec?cusNumber='+jsConst.ORG_CODE+'&monitorSqno='+monitorDocId,	
			dataType : 'json',
			success : function(data) {
				if(data.exception==undefined){
					$("#gridId_yfjdd_cyqk").grid("reload",data.mapList);
				}else{
					$.message( {
						iframePanel:true,
				        message:data.exception.cause.message,
				        type:"danger"
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
		$("#yfjdd_cyqk").dialog("open");
	}
	
	function resetHandler_zjxx(){
		$('#queryForm_zjxx').form("reset");
	}
	function resetHandler_yjjdd(){
		$('#queryForm_yjjdd').form("reset");
	}
	function resetHandler_yfjdd(){
		$('#queryForm_yfjdd').form("reset");
	}
	function resetHandler_ysjdd(){
		$('#queryForm_ysjdd').form("reset");
	}
	function resetHandler_deptysjdd(){
		$('#queryForm_deptysjdd').form("reset");
	}
</script>
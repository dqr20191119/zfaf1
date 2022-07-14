<%@page import="com.cesgroup.prison.common.facade.AuthSystemFacade"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%
//String jybm = AuthSystemFacade.getLoginUserInfo().getOrgCode(); 
//System.out.print(jybm);
%>
<div style="height: 100%; margin: 0px 10px;">
	<cui:form id="formId_rwjs_query">
		<table class="table">			
			<tr>
				<td>单位：</td>
				<td><cui:combobox name="jyId" url="${ctx}/common/authsystem/findAllJyForCombobox"></cui:combobox></td>
				<td>下发时间：</td>
				<td><cui:datepicker name="xfTimeS"></cui:datepicker></td>
				<td>至</td>
				<td><cui:datepicker name="xfTimeE"></cui:datepicker></td>
			</tr>
			<tr>
				<td id = "rwzt">任务状态：</td>
				<td id = "rwztxl"><cui:combobox name="jsStatus" data="combobox_jsStatus"></cui:combobox></td>
				<td>任务标题</td>
				<td><cui:input name="rwTitle"></cui:input></td>
				<td>
					<cui:button label="查询" componentCls="btn-primary" onClick="search"/>
				</td>
			</tr>
		</table>
	</cui:form>
	
	 <div style="height: 480px;">
		<cui:toolbar id="toolbarId_rwjs" data="toolbar_rwjs"></cui:toolbar>			
		<cui:grid id="gridId_rwjs" rownumbers="true" multiselect="true" width="auto" fitStyle="fill" 
			url="${ctx}/rwgl/rwjs/searchData?type=${type}&id=${id}"
			sortname="rwjsCrteTime, desc" rowNum="10">
			<cui:gridCols>
				<cui:gridCol name="id" hidden="true">id</cui:gridCol>
				<cui:gridCol name="fxcjId" hidden="true">fxcjId</cui:gridCol>
				<cui:gridCol name="xfPolice" width="100">下发干警</cui:gridCol>
				<cui:gridCol name="xfTime" width="100">下发时间</cui:gridCol>
				<cui:gridCol name="rwTitle" width="100">任务标题</cui:gridCol>
				<cui:gridCol name="rwContent" width="100">任务内容</cui:gridCol>
				<cui:gridCol name="clDeadline" width="100">处理期限</cui:gridCol>
				<cui:gridCol name="jsStatus" width="100" formatter="formatjsStatus">任务状态</cui:gridCol>
				<cui:gridCol name="clTime" width="100">处理时间</cui:gridCol>
				<cui:gridCol name="clContent" width="100">处理内容</cui:gridCol>
				<cui:gridCol name="rwjsCrteUserName" width="100">处理人</cui:gridCol>
			</cui:gridCols>
			<cui:gridPager gridId="gridId_rwjs" />
		</cui:grid>
	</div> 
	
	<cui:dialog id="dialogId_rwjs" autoOpen="false" resizable="false" iframePanel="true" reLoadOnOpen="true" modal="true" autoDestroy="true">
	</cui:dialog>
</div>

<script>

	var combobox_jsStatus = [{"text":"待接收","value":"0"},{"text":"待处理","value":"1"},{"text":"已处理","value":"2"},{"text":"待处理（退回修改）","value":"3"},{"text":"已完结","value":"4"},];
	
	var toolbar_rwjs = [{
		"type" : "button",
		"id" : "btnId_view",
		"label" : "查看",
		"onClick" : "openDailog",
		"componentCls" : "btn-primary"
	}, {
		"type" : "button",
		"id" : "btnId_sp",
		"label" : "接收",
		"onClick" : "updateYafbsp",
		"componentCls" : "btn-primary"
	},{
		"type" : "button",
		"id" : "btnId_edit",
		"label" : "处理",
		"onClick" : "openDailog",
		"componentCls" : "btn-primary"
	}, {
		"type" : "button",
		"id" : "btnId_sb",
		"label" : "上报",
		"onClick" : "reportByIds",
		"componentCls" : "btn-primary"
	}, ];
	
	
	$.parseDone(function() {
		var type = '${type}'
		if(type == 'swdb'){
			$("#rwzt").hide();
			$("#rwztxl").hide();
		}
	});
	
	
	function openDailog(event, ui) {
		 
		var url;		 
		if(ui.id == "btnId_edit") {	//处理
			
			var selarrrow = $("#gridId_rwjs").grid("option", "selarrrow");			
			if(selarrrow && selarrrow.length == 1) {
				
				var rowData = $("#gridId_rwjs").grid("getRowData", selarrrow[0]);
				if(rowData.jsStatus == "待处理") {
					url = "${ctx}/rwgl/rwjs/toEdit?id=" + selarrrow[0];
				}else if(rowData.jsStatus == "待处理（退回修改）") {
					url = "${ctx}/rwgl/rwjs/toEdit?id=" + selarrrow[0];
				}else {
					$.message({message:"该状态下的记录不能处理 ！", cls:"waring"});
					return;
				}
			} else {
				$.message({message:"请选择一条记录！", cls:"waring"});
				return;
			}			
		} else if(ui.id == "btnId_view") {
			
			var selarrrow = $("#gridId_rwjs").grid("option", "selarrrow");			
			if(selarrrow && selarrrow.length == 1) {
				
				url = "${ctx}/rwgl/rwjs/toView?id=" + selarrrow[0];
			} else {
				$.message({message:"请选择一条记录！", cls:"waring"});
				return;
			}
		}

		$("#dialogId_rwjs").dialog({
			width : 1000,
			height : 600,
			title : ui.label,
			url : url				 
		});
		$("#dialogId_rwjs").dialog("open");
	}
	
	function closeDialog() {
		
		$("#dialogId_rwjs").dialog("close");
	}
	
	function search() {
		
		var formData = $("#formId_rwjs_query").form("formData");
		$("#gridId_rwjs").grid("option", "postData", formData);
		$("#gridId_rwjs").grid("reload");
	}
	
	function reset() {
		
		$("#formId_rwjs_query").form("reset");
	}
		
	function formatjsStatus(cellvalue, options, rawObject) {
		
		return convertColVal(combobox_jsStatus, cellvalue);
	}
	 
function updateYafbsp() { //接收
		
		var selarrrow = $("#gridId_rwjs").grid("option", "selarrrow");
		if(selarrrow && selarrrow.length > 0) {
			var ids = "";
			for(var i = 0; i < selarrrow.length; i++) {
				ids += selarrrow[i] + ",";
				
				var rowData = $("#gridId_rwjs").grid("getRowData", selarrrow[i]);
// 				alert(rowData.jsStatus);
				if(rowData.fxcjId){
					$.message({message:"该督察是由风险采集生成需在一日执勤中完成！", cls:"waring"});
					return;
				}
				if(rowData.jsStatus != "待接收") {
					$.message({message:"请选择状态为待接收的记录！", cls:"waring"});
					return;
				}
			}
			$.loading({text:"正在处理中，请稍后..."});
			$.ajax({
				type : 'post',
				url : '${ctx}/rwgl/rwjs/updateStatus',
				data: {
					ids : ids
				},
				dataType : 'json',
				success : function(data) {
					$.loading("hide");
					
					if(data.code == "1") {
						$.message({message:"操作成功！", cls:"success"});
						$("#gridId_rwjs").grid("reload");
					} else {
						$.message({message:"操作失败！", cls:"error"});
					}				
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					$.loading("hide");
					$.message({message:"操作失败！", cls:"error"});
				}
			});
			
		}
	}
	
	function reportByIds() {
		
		var selarrrow = $("#gridId_rwjs").grid("option", "selarrrow");			
		if(selarrrow && selarrrow.length > 0) {
			$.confirm("确认上报？", function(r) {
				if(r) {
					
					var ids = "";
					for(var i = 0; i < selarrrow.length; i++) {
						ids += selarrrow[i] + ",";
						
						var rowData = $("#gridId_rwjs").grid("getRowData", selarrrow[i]);
						if(rowData.jsStatus != "待处理") {
							$.message({message:"请选择状态为待处理的记录！", cls:"waring"});
							return;
						}
					}
					
					$.loading({text:"正在处理中，请稍后..."});
					
					$.ajax({
						type : 'post',
						url : '${ctx}/rwgl/rwjs/updateStatus',
						data: {
							ids : ids
						},
						dataType : 'json',
						success : function(data) {
							$.loading("hide");
							
							if(data.code == "1") {
								$.message({message:"操作成功！", cls:"success"});
								$("#gridId_rwjs").grid("reload");
							} else {
								$.message({message:"操作失败！", cls:"error"});
							}				
						},
						error : function(XMLHttpRequest, textStatus, errorThrown) {
							$.loading("hide");
							$.message({message:"操作失败！", cls:"error"});
						}
					});
				}
			}); 		
		} else {
			$.message({message:"请选择记录！", cls:"waring"});
			return;
		}
	}
	
function saveOrUpdate() {
		var validFlag = $("#formId_rwjs_edit").form("valid");
		if(!validFlag) {
			return;
		}
		var formData = $("#formId_rwjs_edit").form("formData");
// 		formData.jsStatus = '0';
		$.loading({text:"正在处理中，请稍后..."});
		$.ajax({
			type : 'post',
			url : '${ctx}/rwgl/rwjs/saveOrUpdate',
			data: formData,
			dataType : 'json',
			success : function(data) {
				$.loading("hide");
				if(data.code == "1") {
					$.message({message:"操作成功！", cls:"success"});
					$("#gridId_rwjs").grid("reload");
					$("#dialogId_rwjs").dialog("close");
				} else {
					$.message({message:"操作失败！", cls:"error"});
				}				
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$.loading("hide");
				$.message({message:"操作失败！", cls:"error"});
			}
		});
	}
	
	
function updateStatus() {
	var validFlag = $("#formId_rwjs_edit").form("valid");
	if(!validFlag) {
		return;
	}
	var formData = $("#formId_rwjs_edit").form("formData");
// 	formData.jsStatus = '1';
	$.loading({text:"正在处理中，请稍后..."});
	$.ajax({
		type : 'post',
		url : '${ctx}/rwgl/rwjs/saveOrUpdate',
		data: formData,
		dataType : 'json',
		success : function(data) {
			$.loading("hide");
			if(data.code == "1") {
				$.message({message:"操作成功！", cls:"success"});
				$("#gridId_rwjs").grid("reload");
				$("#dialogId_rwjs").dialog("close");
			} else {
				$.message({message:"操作失败！", cls:"error"});
			}				
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			$.loading("hide");
			$.message({message:"操作失败！", cls:"error"});
		}
	});
}
	
</script>

<%@page import="com.cesgroup.prison.common.facade.AuthSystemFacade"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<%@ page import="com.cesgroup.prison.rwgl.web.RwxfController"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%
//String jybm = AuthSystemFacade.getLoginUserInfo().getOrgCode(); 
//System.out.print(jybm);
%>
<div style="height: 100%; margin: 0px 10px;">
	<cui:form id="formId_rwxf_query">
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
				<td>任务状态：</td>
				<td><cui:combobox name="rwStatus" data="combobox_rwStatus"></cui:combobox></td>
				<td>任务标题</td>
				<td><cui:input name="rwTitle"></cui:input></td>
				<td colspan="2">
					<cui:button label="查询" componentCls="btn-primary" onClick="search"/>
				</td>
			</tr>
			<tr>
				<td>
					<cui:button label="新增下发任务" onClick="addRw"></cui:button>
				</td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
		</table>
	</cui:form>
	
	 <div style="height: 480px;">
		<cui:toolbar id="toolbarId_rwxf" data="toolbar_rwxf"></cui:toolbar>			
		<cui:grid id="gridId_rwxf" rownumbers="true" multiselect="true" width="auto" fitStyle="fill" 
			url="${ctx}/rwgl/rwxf/searchData"
			sortname="rwxfCrteTime, desc" rowNum="10">
			<cui:gridCols>
				<cui:gridCol name="id" hidden="true">id</cui:gridCol>
				<cui:gridCol name="xfPolice" width="100">下发干警</cui:gridCol>
				<cui:gridCol name="xfTime" width="100">下发时间</cui:gridCol>
				<cui:gridCol name="rwTitle" width="100">任务标题</cui:gridCol>
				<cui:gridCol name="rwContent" width="100">任务内容</cui:gridCol>
<%-- 				<cui:gridCol name="jsDept" width="100" formatter="formatJsDept" >接收单位</cui:gridCol> --%>
				<cui:gridCol name="clDeadline" width="100">处理期限</cui:gridCol>
				<cui:gridCol name="rwStatus" width="100" formatter="formatRwStatus">任务状态</cui:gridCol>
				<cui:gridCol name="rwSituation" width="100" >任务情况（下发单位总数/接收单位总数/未反馈数）</cui:gridCol>
			</cui:gridCols>
			<cui:gridPager gridId="gridId_rwxf" />
		</cui:grid>
	</div> 
	
	<cui:dialog id="dialogId_rwxf" autoOpen="false" resizable="false" iframePanel="true" reLoadOnOpen="true" modal="true" autoDestroy="true">
	</cui:dialog>
</div>

<script>
	
	var combobox_rwStatus = [{"text":"待下发","value":"0"},{"text":"未反馈","value":"1"},{"text":"部分反馈","value":"2"},{"text":"全部反馈","value":"3"},{"text":"已完结","value":"4"},];
	
	var combobox_jsDept = <%=RwxfController.getJsDeptForCombobox()%>
	
	var toolbar_rwxf = [/*{
		"type" : "button",
		"id" : "btnId_edit",
		"label" : "修改",
		"onClick" : "openDailog",
		"componentCls" : "btn-primary"
	},  {
		"type" : "button",
		"id" : "btnId_remove",
		"label" : "删除",
		"onClick" : "deleteByIds",
		"componentCls" : "btn-primary"
	}, */ {
		"type" : "button",
		"id" : "btnId_view",
		"label" : "查看",
		"onClick" : "openDailog",
		"componentCls" : "btn-primary"
	} /* , {
		"type" : "button",
		"id" : "btnId_sp",
		"label" : "下发",
		"onClick" : "updateYafbsp",
		"componentCls" : "btn-primary"
	} */ ];
	
	
	$.parseDone(function() {
		
	});
	
	function addRw() {
		var url = "${ctx}/rwgl/rwxf/toEdit";
		$("#dialogId_rwxf").dialog({
			width : 1000,
			height : 600,
			title : "新增下发任务",
			url : url				 
		});
		$("#dialogId_rwxf").dialog("open");
		
	}
	
	function openDailog(event, ui) {
		 
		var url;		 
		if(ui.id == "btnId_edit") {	
			
			var selarrrow = $("#gridId_rwxf").grid("option", "selarrrow");			
			if(selarrrow && selarrrow.length == 1) {
				
				var rowData = $("#gridId_rwxf").grid("getRowData", selarrrow[0]);
				if(rowData.rwStatus != "待下发") {
					$.message({message:"请选择状态为未下发的记录！", cls:"waring"});
					return;
				}
				
				url = "${ctx}/rwgl/rwxf/toEdit?id=" + selarrrow[0];
			} else {
				$.message({message:"请选择一条记录！", cls:"waring"});
				return;
			}			
		} else if(ui.id == "btnId_view") {
			
			var selarrrow = $("#gridId_rwxf").grid("option", "selarrrow");			
			if(selarrrow && selarrrow.length == 1) {
				
				url = "${ctx}/rwgl/rwxf/toView?id=" + selarrrow[0];
			} else {
				$.message({message:"请选择一条记录！", cls:"waring"});
				return;
			}
		}

		$("#dialogId_rwxf").dialog({
			width : 1000,
			height : 600,
			title : ui.label,
			url : url				 
		});
		$("#dialogId_rwxf").dialog("open");
	}
	
	function closeDialog() {
		
		$("#dialogId_rwxf").dialog("close");
	}
	
	function search() {
		
		var formData = $("#formId_rwxf_query").form("formData");
		$("#gridId_rwxf").grid("option", "postData", formData);
		$("#gridId_rwxf").grid("reload");
	}
	
	function reset() {
		
		$("#formId_rwxf_query").form("reset");
	}
		
	function formatRwStatus(cellvalue, options, rawObject) {
		
		return convertColVal(combobox_rwStatus, cellvalue);
	}
	
	function formatJsDept(cellvalue, options, rawObject) {
		
		return convertColVal(combobox_jsDept, cellvalue);
	}
	 
function updateYafbsp() {
		
		var selarrrow = $("#gridId_rwxf").grid("option", "selarrrow");
		if(selarrrow && selarrrow.length > 0) {
			var ids = "";
			for(var i = 0; i < selarrrow.length; i++) {
				ids += selarrrow[i] + ",";
				
				var rowData = $("#gridId_rwxf").grid("getRowData", selarrrow[i]);
				if(rowData.rwStatus != "待下发") {
					$.message({message:"请选择状态为待下发的记录！", cls:"waring"});
					return;
				}
			}
			$.loading({text:"正在处理中，请稍后..."});
			$.ajax({
				type : 'post',
				url : '${ctx}/rwgl/rwxf/updateStatus',
				data: {
					ids : ids
				},
				dataType : 'json',
				success : function(data) {
					$.loading("hide");
					
					if(data.code == "1") {
						$.message({message:"操作成功！", cls:"success"});
						$("#gridId_rwxf").grid("reload");
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
	
	function deleteByIds() {
		
		var selarrrow = $("#gridId_rwxf").grid("option", "selarrrow");			
		if(selarrrow && selarrrow.length > 0) {
			$.confirm("确认删除？", function(r) {
				if(r) {
					
					var ids = "";
					for(var i = 0; i < selarrrow.length; i++) {
						ids += selarrrow[i] + ",";
						
						var rowData = $("#gridId_rwxf").grid("getRowData", selarrrow[i]);
						if(rowData.rwStatus != "待下发") {
							$.message({message:"请选择状态为待下发的记录！", cls:"waring"});
							return;
						}
					}
					
					$.loading({text:"正在处理中，请稍后..."});
					
					$.ajax({
						type : 'post',
						url : '${ctx}/rwgl/rwxf/deleteByIds',
						data: {
							ids : ids
						},
						dataType : 'json',
						success : function(data) {
							$.loading("hide");
							
							if(data.code == "1") {
								$.message({message:"操作成功！", cls:"success"});
								$("#gridId_rwxf").grid("reload");
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
		var validFlag = $("#formId_rwxf_edit").form("valid");
		if(!validFlag) {
			return;
		}
		var formData = $("#formId_rwxf_edit").form("formData");
		formData.rwStatus = '0';
		$.loading({text:"正在处理中，请稍后..."});
		$.ajax({
			type : 'post',
			url : '${ctx}/rwgl/rwxf/saveOrUpdate',
			data: formData,
			dataType : 'json',
			success : function(data) {
				$.loading("hide");
				if(data.code == "1") {
					$.message({message:"操作成功！", cls:"success"});
					$("#gridId_rwxf").grid("reload");
					$("#dialogId_rwxf").dialog("close");
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
	var validFlag = $("#formId_rwxf_edit").form("valid");
	if(!validFlag) {
		return;
	}
	var formData = $("#formId_rwxf_edit").form("formData");
	formData.rwStatus = '1';
	$.loading({text:"正在处理中，请稍后..."});
	$.ajax({
		type : 'post',
		url : '${ctx}/rwgl/rwxf/saveOrUpdate',
		data: formData,
		dataType : 'json',
		success : function(data) {
			$.loading("hide");
			if(data.code == "1") {
				$.message({message:"操作成功！", cls:"success"});
				$("#gridId_rwxf").grid("reload");
				$("#dialogId_rwxf").dialog("close");
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

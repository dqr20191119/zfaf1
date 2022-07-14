<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<div style="height: 100%; margin: 0px 10px;">
	<cui:form id="formId_yljh_query">
		<table class="table">			
			<tr>
				<td>预案名称：</td>
				<td><cui:input name="epiPlanName"></cui:input></td>		 
				<td>演练时间：<td>
				<td><cui:datepicker name="edpTime" dateFormat="yyyy-MM-dd HH:mm:ss"></cui:datepicker></td>
				<td>演练地点：<td>
				<td><cui:input name="edpAddress"></cui:input></td>
			</tr>
			<tr>
				<td>状态：</td>
				<td><cui:combobox name="edpStatus" data="combobox_yjct_edpStatus"></cui:combobox></td>
			 	<td colspan="4">
					<cui:button label="查询" componentCls="btn-primary" onClick="search"/>
					<cui:button label="重置" onClick="reset"></cui:button>
				</td>
			</tr>
		</table>
	</cui:form>
 	
	<div style="height: 450px;">
		<cui:toolbar id="toolbarId_yljh" data="toolbar_yljh"></cui:toolbar>			
		<cui:grid id="gridId_yljh" rownumbers="true" multiselect="true" width="auto" fitStyle="fill" 
			url="${ctx}/yjct/yljh/searchData" 
			sortname="edpCrteTime, desc" rowNum="10">
			<cui:gridCols>
				<cui:gridCol name="id" hidden="true">id</cui:gridCol>
				<cui:gridCol name="edpTitle" width="100">演练标题</cui:gridCol>
				<cui:gridCol name="edpTime" width="100">演练时间</cui:gridCol>
				<cui:gridCol name="edpAddress" width="100">演练地点</cui:gridCol>
				<cui:gridCol name="epiPlanName" width="100">演练预案</cui:gridCol>
				<cui:gridCol name="edpStatus" width="100" align="center" formatter="formatEdpStatus" revertCode="true">状态</cui:gridCol>
			</cui:gridCols>
			<cui:gridPager gridId="gridId_yljh" />
		</cui:grid>
	</div>
	
	<cui:dialog id="dialogId_yljh" autoOpen="false" resizable="false" iframePanel="true" reLoadOnOpen="true" modal="true" autoDestroy="true" buttons="buttons_yljh">
	</cui:dialog>
</div>

<script>
	 	
	var combobox_yjct_edpStatus = <%=CodeFacade.loadCode4ComboJson("4.20.2", 4, "1,3,4")%>;
	
	var toolbar_yljh = [{
		"type" : "button",
		"id" : "btnId_add",
		"label" : "新增",
		"onClick" : "openDailog",
		"componentCls" : "btn-primary"
	}, {
		"type" : "button",
		"id" : "btnId_edit",
		"label" : "修改",
		"onClick" : "openDailog",
		"componentCls" : "btn-primary"
	}, {
		"type" : "button",
		"id" : "btnId_remove",
		"label" : "删除",
		"onClick" : "deleteByIds",
		"componentCls" : "btn-primary"
	}];

	var buttons_yljh = [{
		text: "保存",
		id: "btnId_save",
		click: function () {
			
			saveOrUpdate();
		}        
	}, {
	    text: "取消",
	    id: "btnId_cancel",
	    click: function () {
	    	
	    	$("#dialogId_yljh").dialog("close");
	    }            
	}];

	$.parseDone(function() {
		
	});
	
	function openDailog(event, ui) {
		 
		var url;		 
		if(ui.id == "btnId_add") {
			
			url = "${ctx}/yjct/yljh/toEdit";
		} else if(ui.id == "btnId_edit") {	
			
			var selarrrow = $("#gridId_yljh").grid("option", "selarrrow");			
			if(selarrrow && selarrrow.length == 1) {
				
				var rowData = $("#gridId_yljh").grid("getRowData", selarrrow[0]);
				if(rowData.edpStatus != "1") {
					$.message({message:"请选择状态为待审批的记录！", cls:"waring"});
					return;
				}
				
				url = "${ctx}/yjct/yljh/toEdit?id=" + selarrrow[0];
			} else {
				$.message({message:"请选择一条记录！", cls:"waring"});
				return;
			}			
		}
		
		$("#dialogId_yljh").dialog({
			width : 800,
			height : 400,
			title : ui.label,
			url : url				 
		});
		$("#dialogId_yljh").dialog("open");
	}
	
	function search() {
		
		var formData = $("#formId_yljh_query").form("formData");
		$("#gridId_yljh").grid("option", "postData", formData);
		$("#gridId_yljh").grid("reload");
	}
	
	function reset() {
		
		$("#formId_yljh_query").form("reset");
	}
	
	function formatEdpStatus(cellvalue, options, rawObject) {
		
		return convertColVal(combobox_yjct_edpStatus, cellvalue);	
	}
		 
	function saveOrUpdate() {
		
		var validFlag = $("#formId_yljh_edit").form("valid");
		if(!validFlag) {
			return;
		}
		
		var formData = $("#formId_yljh_edit").form("formData");

		$.loading({text:"正在处理中，请稍后..."});
		
		$.ajax({
			type : 'post',
			url : '${ctx}/yjct/yljh/saveOrUpdate',
			data: formData,
			dataType : 'json',
			success : function(data) {
				$.loading("hide");
				
				if(data.code == "1") {
					$.message({message:"操作成功！", cls:"success"});
					$("#dialogId_yljh").dialog("close");
					$("#gridId_yljh").grid("reload");
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
	
	function deleteByIds() {
		
		var selarrrow = $("#gridId_yljh").grid("option", "selarrrow");			
		if(selarrrow && selarrrow.length > 0) {
			$.confirm("确认删除？", function(r) {
				if(r) {
					
					var ids = "";
					for(var i = 0; i < selarrrow.length; i++) {
						ids += selarrrow[i] + ",";
						
						var rowData = $("#gridId_yljh").grid("getRowData", selarrrow[i]);
						if(rowData.edpStatus != "1") {
							$.message({message:"请选择状态为待审批的记录！", cls:"waring"});
							return;
						}
					}
					
					$.ajax({
						type : 'post',
						url : '${ctx}/yjct/yljh/deleteByIds',
						data: {
							ids : ids
						},
						dataType : 'json',
						success : function(data) {
							if(data.code == "1") {
								$.message({message:"操作成功！", cls:"success"});
								$("#gridId_yljh").grid("reload");
							} else {
								$.message({message:"操作失败！", cls:"error"});
							}				
						},
						error : function(XMLHttpRequest, textStatus, errorThrown) {
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
</script>
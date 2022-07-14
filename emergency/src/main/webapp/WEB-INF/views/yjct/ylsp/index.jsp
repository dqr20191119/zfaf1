<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<div style="height: 100%; margin: 0px 10px;">
	<cui:form id="formId_ylsp_query">
		<table class="table">			
			<tr>
				<td nowrap="nowrap">预案名称：</td>
				<td><cui:input name="epiPlanName"></cui:input></td>		 
				<td nowrap="nowrap">演练时间：<td>
				<td><cui:datepicker name="edpTime" dateFormat="yyyy-MM-dd HH:mm:ss"></cui:datepicker></td>
				<td nowrap="nowrap">演练地点：<td>
				<td><cui:input name="edpAddress"></cui:input></td>				 
			 	<td nowrap="nowrap">
					<cui:button label="查询" componentCls="btn-primary" onClick="search"/>
					<cui:button label="重置" onClick="reset"></cui:button>
				</td>
			</tr>
		</table>
	</cui:form>
 	
	<div style="height: 480px;">
		<cui:toolbar id="toolbarId_ylsp" data="toolbar_ylsp"></cui:toolbar>			
		<cui:grid id="gridId_ylsp" rownumbers="true" multiselect="true" width="auto" fitStyle="fill" 
			sortname="edpCrteTime, desc" rowNum="10">
			<cui:gridCols>
				<cui:gridCol name="id" hidden="true">id</cui:gridCol>
				<cui:gridCol name="edpTitle" width="100">演练标题</cui:gridCol>
				<cui:gridCol name="edpTime" width="100">演练时间</cui:gridCol>
				<cui:gridCol name="edpAddress" width="100">演练地点</cui:gridCol>
				<cui:gridCol name="epiPlanName" width="100">演练预案</cui:gridCol>
				<cui:gridCol name="edpStatus" width="100" align="center" formatter="formatEdpStatus" revertCode="true">状态</cui:gridCol>
			</cui:gridCols>
			<cui:gridPager gridId="gridId_ylsp" />
		</cui:grid>
	</div>
	
	<cui:dialog id="dialogId_ylsp" autoOpen="false" resizable="false" iframePanel="true" reLoadOnOpen="true" modal="true" autoDestroy="true" buttons="buttons_ylsp">
	</cui:dialog>
</div>

<script>
	
	var combobox_yjct_edpStatus = <%=CodeFacade.loadCode4ComboJson("4.20.2", 4, "1,3,4")%>;
	var combobox_yjct_sfty = <%=CodeFacade.loadCode2Json("4.0.5")%>;
	
	var toolbar_ylsp = [{
		"type" : "button",
		"id" : "btnId_sp",
		"label" : "审批",
		"onClick" : "openDailog",
		"componentCls" : "btn-primary"
	}];

	var buttons_ylsp = [{
		text: "保存",
		id: "btnId_save",
		click: function () {
			
			saveOrUpdate();
		}        
	}, {
	    text: "取消",
	    id: "btnId_cancel",
	    click: function () {
	    	
	    	$("#dialogId_ylsp").dialog("close");
	    }            
	}];

	$.parseDone(function() {
		
		// 监狱领导
		var data = {};
		data.edpStatus = "1";					
		// $("#gridId_ylsp").grid("option", "url", "${ctx}/yjct/yljh/searchData");
		$("#gridId_ylsp").grid("option", "postData", data);
		$("#gridId_ylsp").grid("reload", "${ctx}/yjct/yljh/searchData");  
	});
	
	function openDailog(event, ui) {
		 
		var url;		 
		if(ui.id == "btnId_sp") {	
			
			var selarrrow = $("#gridId_ylsp").grid("option", "selarrrow");			
			if(selarrrow && selarrrow.length == 1) {
				url = "${ctx}/yjct/ylsp/toEdit?id=" + selarrrow[0];
			} else {
				$.message({message:"请选择一条记录！", cls:"waring"});
				return;
			}			
		}
		
		$("#dialogId_ylsp").dialog({
			width : 800,
			height : 600,
			title : ui.label,
			url : url				 
		});
		$("#dialogId_ylsp").dialog("open");
	}
	
	function search() {
		
		var formData = $("#formId_ylsp_query").form("formData");
		formData.edpStatus = 1;
		
		$("#gridId_ylsp").grid("option", "postData", formData);
		$("#gridId_ylsp").grid("reload");		
	}
	
	function reset() {
		
		$("#formId_ylsp_query").form("reset");
	}
	
	function formatEdpStatus(cellvalue, options, rawObject) {
		
		return convertColVal(combobox_yjct_edpStatus, cellvalue);	
	}
		 
	function saveOrUpdate() {
		
		var validFlag = $("#formId_ylsp_edit").form("valid");
		if(!validFlag) {
			return;
		}
		
		var formData = $("#formId_ylsp_edit").form("formData");
		formData.ehaPhFid = formData.id;
		formData.id = null;		
		formData.ehaAppRole = "1";
		formData.ehaAppType = "2";
		
		$.loading({text:"正在处理中，请稍后..."});
		
		$.ajax({
			type : 'post',
			url : '${ctx}/yjct/ylsp/saveOrUpdate',
			data: formData,
			dataType : 'json',
			success : function(data) {
				$.loading("hide");
				
				if(data.code == "1") {
					$.message({message:"操作成功！", cls:"success"});
					$("#dialogId_ylsp").dialog("close");
					$("#gridId_ylsp").grid("reload");
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
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<div style="height: 100%; margin: 0px 10px;">
	<cui:form id="formId_yasp_query">
		<table class="table">			
			<tr>
				<td>预案名称：</td>
				<td><cui:input name="epiPlanName"></cui:input></td>
				<td>预案类别：</td>
				<td><cui:combobox name="epiPlanType" data="combobox_yjct_planType"></cui:combobox></td>
				<td>
					<cui:button label="查询" componentCls="btn-primary" onClick="search"/>
					<cui:button label="重置" onClick="reset"></cui:button>
				</td>
			</tr>
		</table>
	</cui:form>
	
	<div style="height: 480px;">
		<cui:toolbar id="toolbarId_yasp" data="toolbar_yasp"></cui:toolbar>			
		<cui:grid id="gridId_yasp" rownumbers="true" multiselect="true" width="auto" fitStyle="fill" 			
			sortname="epiCrteTime, desc" rowNum="10">
			<cui:gridCols>
				<cui:gridCol name="id" hidden="true">id</cui:gridCol>
				<cui:gridCol name="epiPlanName" width="100">预案名称</cui:gridCol>
				<cui:gridCol name="epiPlanType" width="120" formatter="formatPlanType">预案类别</cui:gridCol>
			</cui:gridCols>
			<cui:gridPager gridId="gridId_yasp" />
		</cui:grid>
	</div>
	
	<cui:dialog id="dialogId_yasp" autoOpen="false" resizable="false" iframePanel="true" reLoadOnOpen="true" modal="true" autoDestroy="true" buttons="buttons_yasp">
	</cui:dialog>
</div>

<script>
	 
	var combobox_yjct_planType = <%=CodeFacade.loadCode2Json("4.20.4")%>;
	var combobox_yjct_sfty = <%=CodeFacade.loadCode2Json("4.0.5")%>;
		
	var planId = "";
	var toolbar_yasp = [{
		"type" : "button",
		"id" : "btnId_sp",
		"label" : "审批",
		"onClick" : "openDailog",
		"componentCls" : "btn-primary"
	}];
	
	var buttons_yasp = [{
		text: "保存",
		id: "btnId_save",
		click: function () {
			
			saveOrUpdate();
		}        
	}, {
	    text: "取消",
	    id: "btnId_cancel",
	    click: function () {
	    	
	    	$("#dialogId_yasp").dialog("close");
	    }            
	}];
	
	$.parseDone(function() {
		 
		// 监狱领导
		var data = {};
		data.epiPlanStatus = "1";	
		// $("#gridId_yasp").grid("option", "url", "${ctx}/yjct/yabz/searchData");
		$("#gridId_yasp").grid("option", "postData", data);
		$("#gridId_yasp").grid("reload", "${ctx}/yjct/yabz/searchData");		 		
	});
	
	function openDailog(event, ui) {
		 
		var url;		 
		if(ui.id == "btnId_sp") {	
			
			var selarrrow = $("#gridId_yasp").grid("option", "selarrrow");			
			if(selarrrow && selarrrow.length == 1) {
				
				url = "${ctx}/yjct/yabz/toView?id=" + selarrrow[0] + "&type=1";
			} else {
				$.message({message:"请选择一条记录！", cls:"waring"});
				return;
			}			
		}

		$("#dialogId_yasp").dialog({
			width : 1000,
			height : 600,
			title : ui.label,
			url : url				 
		});
		$("#dialogId_yasp").dialog("open");
	}
	
	function search() {
		
		var formData = $("#formId_yasp_query").form("formData");
		formData.epiPlanStatus = "1";
		$("#gridId_yasp").grid("option", "postData", formData);
		$("#gridId_yasp").grid("reload");		 	
	}
	
	function reset() {
		
		$("#formId_yasp_query").form("reset");
	}
		
	function formatPlanType(cellvalue, options, rawObject) {
		
		return convertColVal(combobox_yjct_planType, cellvalue);
	}
	 
 	function saveOrUpdate() {
		 	
		var validFlag = $("#formId_yasp_edit").form("valid");
		if(!validFlag) {
			return;
		}
		
		var formData = $("#formId_yasp_edit").form("formData");
		formData.ehaPhFid = planId;
		formData.ehaAppRole = "1";
		formData.ehaAppType = "1";
		
		$.loading({text:"正在处理中，请稍后..."});
		
		$.ajax({
			type : 'post',
			url : '${ctx}/yjct/yasp/saveOrUpdate',
			data: formData,
			dataType : 'json',
			success : function(data) {
				$.loading("hide");
				
				if(data.code == "1") {
					$.message({message:"操作成功！", cls:"success"});
					$("#gridId_yasp").grid("reload");
					$("#dialogId_yasp").dialog("close");					
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
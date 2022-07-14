<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<div style="height: 100%; margin: 0px 10px;">
	<cui:form id="formId_yafb_query">
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
		<cui:toolbar id="toolbarId_yafb" data="toolbar_yafb"></cui:toolbar>			
		<cui:grid id="gridId_yafb" rownumbers="true" multiselect="true" width="auto" fitStyle="fill" 
			url="${ctx}/yjct/yabz/searchData" postData="{epiPlanStatus: '3', ehaIsAgree: '1'}"
			sortname="epiCrteTime, desc" rowNum="10">
			<cui:gridCols>
				<cui:gridCol name="id" hidden="true">id</cui:gridCol>
				<cui:gridCol name="epiPlanName" width="100">预案名称</cui:gridCol>
				<cui:gridCol name="epiPlanType" width="120" formatter="formatPlanType">预案类别</cui:gridCol>
			</cui:gridCols>
			<cui:gridPager gridId="gridId_yafb" />
		</cui:grid>
	</div>
	
	<cui:dialog id="dialogId_yafb" autoOpen="false" resizable="false" iframePanel="true" reLoadOnOpen="true" modal="true" autoDestroy="true" buttons="buttons_yafb">
	</cui:dialog>
</div>

<script>
	
	var combobox_yjct_planType = <%=CodeFacade.loadCode2Json("4.20.4")%>;
	var combobox_yjct_sfty = <%=CodeFacade.loadCode2Json("4.0.5")%>;
	
	var planId = "";
	var toolbar_yafb = [{
		"type" : "button",
		"id" : "btnId_fb",
		"label" : "发布",
		"onClick" : "openDailog",
		"componentCls" : "btn-primary"
	}];
	
	var buttons_yafb = [{
		text: "发布",
		id: "btnId_add",
		click: function () {
			
			saveOrUpdate();
		}        
	}, {
	    text: "取消",
	    id: "btnId_cancel",
	    click: function () {
	    	
	    	$("#dialogId_yafb").dialog("close");
	    }            
	}];
	
	$.parseDone(function() {
		
	});
	
	function openDailog(event, ui) {
		 
		var url;		 
		if(ui.id == "btnId_fb") {	
			
			var selarrrow = $("#gridId_yafb").grid("option", "selarrrow");			
			if(selarrrow && selarrrow.length == 1) {
				
				url = "${ctx}/yjct/yabz/toView?id=" + selarrrow[0] + "&type=2";
			} else {
				$.message({message:"请选择一条记录！", cls:"waring"});
				return;
			}			
		}

		$("#dialogId_yafb").dialog({
			width : 1000,
			height : 600,
			title : ui.label,
			url : url				 
		});
		$("#dialogId_yafb").dialog("open");
	}
	
	function search() {
		
		var formData = $("#formId_yafb_query").form("formData");
		formData.epiPlanStatus = 3;
		
		$("#gridId_yafb").grid("option", "postData", formData);
		$("#gridId_yafb").grid("reload");
	}
	
	function reset() {
		
		$("#formId_yafb_query").form("reset");
	}
		
	function formatPlanType(cellvalue, options, rawObject) {
		
		return convertColVal(combobox_yjct_planType, cellvalue);
	}
	
 	function saveOrUpdate() {
		
 		$.loading({text:"正在处理中，请稍后..."});
 		
		$.ajax({
			type : 'post',
			url : '${ctx}/yjct/yafb/saveOrUpdate',
			data: {
				ehaPhFid : planId
			},
			dataType : 'json',
			success : function(data) {
				$.loading("hide");
				
				if(data.code == "1") {
					$.message({message:"操作成功！", cls:"success"}); 
					$("#gridId_yafb").grid("reload");
					$("#dialogId_yafb").dialog("close");			
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
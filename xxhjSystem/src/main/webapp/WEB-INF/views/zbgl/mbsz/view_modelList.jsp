<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.cesgroup.prison.common.facade.AuthSystemFacade"%>
<%@ page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<div style="height: auto;">
	<div style="height: 40px;margin:10px 10px 5px 15px">
		<cui:toolbar id="toolbarId_mbsz_view" data="toolbar_mbsz_view"></cui:toolbar>	
	</div>
	
	<div style="height: 670px">
		<cui:grid id="gridId_mbsz_view" rownumbers="true" multiselect="true" width="auto" fitStyle="fill"  
			url="${ctx}/zbgl/mbbm/searchData?dmdModeId=${dmdModeId}" rowNum="15">
			<cui:gridCols>
				<cui:gridCol name="id" hidden="true">id</cui:gridCol>
				<cui:gridCol name="dmdModeId" hidden="true">模板编号</cui:gridCol>
				<cui:gridCol name="dmdCategoryId" hidden="true">类别</cui:gridCol>
				<cui:gridCol name="cdmModeName" align="center">模板名称</cui:gridCol>
				<cui:gridCol name="dmdDprtmntId" align="center" formatter="convertCode" revertCode="true" formatoptions="{
				'data': combobox_bm
				}">部门名称</cui:gridCol>
				<cui:gridCol name="dmdStartDate" align="center">生效时间</cui:gridCol>
				<cui:gridCol name="dmdEndDate" align="center">截止时间</cui:gridCol>
			</cui:gridCols>
			<cui:gridPager gridId="gridId_mbsz_view" />
		</cui:grid>
		
		<cui:dialog id="dialogId_mbsz_view" autoDestroy="true" autoOpen="false" resizable="false" iframePanel="true" reLoadOnOpen="true" modal="true" buttons="buttons_mbsz_view">
		</cui:dialog>
	</div>
<script>

	var dmdModeId = '${dmdModeId}';
	var categoryId = '${categoryId}';
	var combobox_bm = <%=AuthSystemFacade.getAllChildrenOrgJsonInfoByOrgKey(null)%>;

	$.parseDone(function() {
		
	});
	
	var toolbar_mbsz_view = [{
		"type" : "button",
		"id" : "btnId_mbsz_view",
		"label" : "查看",
		"onClick" : "openSubDailog",
		"componentCls" : "btn-primary"
	}]
	
	var buttons_mbsz_view= [{
	    text: "取消",
	    id: "btnId_cancel",
	    click: function () {
	    	
	    	$("#dialogId_mbsz_view").dialog("close");
	    }            
	}];
	
	function openSubDailog(event, ui) {
		
		if(ui.id == "btnId_mbsz_view") {
			
			var selarrrow = $("#gridId_mbsz_view").grid("option", "selarrrow");	
			var rowData = $("#gridId_mbsz_view").grid("getRowData", selarrrow[0]);
			
			if(selarrrow && selarrrow.length == 1) {
				url = "${ctx}/zbgl/zbbp/toView?id=" +rowData.id+ "&categoryId=" +rowData.dmdCategoryId+ "&dmdDprtmntId=" +rowData.dmdDprtmntId+ "&dmdModeId="
					+rowData.dmdModeId +"&dmdStartDate=" +rowData.dmdStartDate+ "&dmdEndDate=" +rowData.dmdEndDate;	
				
				$("#dialogId_mbsz_view").dialog({
					width : 1200,
					height : 800,
					title : ui.label,
					url : url				 
				});
				$("#dialogId_mbsz_view").dialog("open");
				
			} else {
				$.message({message:"请选择单条记录！", cls:"waring"});
				return;
			}
		}
	} 
	
	
</script>
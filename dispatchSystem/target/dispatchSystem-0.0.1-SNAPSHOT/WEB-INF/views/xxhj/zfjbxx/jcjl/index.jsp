<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.cesgroup.prison.common.facade.AuthSystemFacade"%>
<%@ page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<script src="${ctx}/static/js/scripts/common.js"></script>
 
<div style="height: 100%; margin: 0px 10px;">
	 
	
	<div style="height: 40px;">
		<cui:toolbar id="toolbarId_jcjl" data="toolbar_jcjl"></cui:toolbar>	
	</div>	
		
	<cui:grid id="gridId_jcjl" rownumbers="true" multiselect="true" width="auto" fitStyle="fill" 
		url="${ctx}/xxhj/zfjbxx/searchDataJcjl?zfbh=${zfbh}" rowNum="15">
		<cui:gridCols>
			<cui:gridCol name="ID" hidden="true">id</cui:gridCol>
			<cui:gridCol name="DRD_BSNS_DATE" align="center">时间</cui:gridCol>
			<cui:gridCol name="DRD_IN_OUT_INDC"  align="center" sortable="true" formatter="convertCode" revertCode="true"  formatoptions="{
			'data':commbobox_jcbs
			}">进出标识</cui:gridCol>
			<cui:gridCol name="DRD_DOOR_NAME" align="center">区域</cui:gridCol>
		</cui:gridCols>
		<cui:gridPager gridId="gridId_jcjl" />
	</cui:grid>
	
	<cui:dialog id="dialogId_jcjl" autoOpen="false" resizable="false" iframePanel="true" reLoadOnOpen="true" modal="true" autoDestroy="true" buttons="buttons_jcjl">
	</cui:dialog>
</div>

<script>
var commbobox_jcbs=[{'value':'1','text':'进'},{'value':'0','text':'出'}];

	$.parseDone(function() {
		
	});
	
	var toolbar_jcjl = [ ];

	var buttons_jcjl = [ ];
 
</script>
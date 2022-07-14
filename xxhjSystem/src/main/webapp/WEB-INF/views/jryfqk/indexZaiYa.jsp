<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.cesgroup.prison.common.facade.AuthSystemFacade"%>
<%@ page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<script src="${ctx}/static/js/scripts/common.js"></script>

<div style="height: 100%; margin: 0px 10px;">
	
	
	<div style="height: 40px;">
		<cui:toolbar id="toolbarId_jryfqk" data="toolbar_jryfqk"></cui:toolbar>	
	</div>	
		
	<cui:grid id="gridId_ZaiYa" rownumbers="false" multiselect="true" width="auto" fitStyle="fill" 
		url="${ctx}/jyshouye/jryfqk/searchListZaiYa" rowNum="15">
		<cui:gridCols>
			<cui:gridCol name="XM" align="center">姓名</cui:gridCol>
			<cui:gridCol name="JH" align="center">警号</cui:gridCol>
			<cui:gridCol name="TEL" align="center">电话</cui:gridCol>
			<cui:gridCol name="DW" align="center">单位</cui:gridCol>
		</cui:gridCols>
		<cui:gridPager gridId="gridId_ZaiYa" />
	</cui:grid>
	

</div>

<script>


	$.parseDone(function() {
		
	});
	
	var toolbar_jryfqk = [];
 
	function search() {
		
		var formData = $("#formId_jryfqk_query").form("formData");
		$("#gridId_ZaiYa").grid("option", "postData", formData);
		$("#gridId_ZaiYa").grid("reload");
	}
	
	function reset() {
		
		$("#formId_jryfqk_query").form("reset");
	}
  
</script>
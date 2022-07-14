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
		
	<cui:grid id="gridId_jryfqk" rownumbers="true" multiselect="true" width="auto" fitStyle="fill" 
		url="${ctx}/jyshouye/jryfqk/searchList?type=${type }&time=${time }" rowNum="15">
		<cui:gridCols>
			<cui:gridCol name="ID" hidden="true">id</cui:gridCol>
			<cui:gridCol name="C_ZFBH" align="center">编号</cui:gridCol>
			<cui:gridCol name="C_XM" align="center">姓名</cui:gridCol>
			<cui:gridCol name="C_XB" align="center">性别</cui:gridCol>
			<cui:gridCol name="D_RJRQ" align="center">入监日期</cui:gridCol>
			<cui:gridCol name="C_XXQ_ZXXZ" align="center">执行刑种</cui:gridCol>
			<cui:gridCol name="D_XXQ_QR" align="center">刑期起日</cui:gridCol>
			<cui:gridCol name="D_XXQ_ZR" align="center">刑期止日</cui:gridCol>
			<cui:gridCol name="C_HJDZ" align="center">户籍地址</cui:gridCol>
			 
		</cui:gridCols>
		<cui:gridPager gridId="gridId_jryfqk" />
	</cui:grid>
	

</div>

<script>


	$.parseDone(function() {
		
	});
	
	var toolbar_jryfqk = [];
 
	function search() {
		
		var formData = $("#formId_jryfqk_query").form("formData");
		$("#gridId_jryfqk").grid("option", "postData", formData);
		$("#gridId_jryfqk").grid("reload");
	}
	
	function reset() {
		
		$("#formId_jryfqk_query").form("reset");
	}
  
</script>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />


<cui:dialog id="dialogId_jsxzzf" reLoadOnOpen="true" modal="true" iframePanel="true" resizable="false" autoOpen="false" width="1000" height="500" maximizable="false"></cui:dialog>

	<div class="jsxzzf">
		<cui:grid id="gridId_jsxzzf"  singleselect="true" colModel="gridColModel_jsxzzf" fitStyle="fill" rowNum="10" height="450"  
		url='${ctx}/xxhj/zfjbxx/queryPrisonerBedInfo?jsId=${jsId}&qyId=${qyId}'>
			<cui:gridPager gridId="gridId_jsxzzf" />
		</cui:grid>
			
	</div>
	
	
<script type="text/javascript"> 

 	var USER_LEVEL = jsConst.USER_LEVEL; 
	var cusNumber = jsConst.CUS_NUMBER;  
	var drpmntId = jsConst.DEPARTMENT_ID; 
	
	$.parseDone(function() {
	
	});
	var gridColModel_jsxzzf = [{
		
		label : "床号",
		name : "BED_NUMBER",
		align:"center"
	},{
		label : "罪犯编号",
		name : "PRSNR_IDNTY",
		align:"center"
	},{
		label : "姓名",
		name : "NAME",
		align:"center",
	},{
		label : "危险等级",
		name : "DANGER_LEVEL",
		align:"center"
	},{
		label : "详情",
		name : "check",
		align:"center",
		formatter : "Formatter"
	}]
	
	function Formatter(cellValue, options, rowObject) {
		
		var param1 = rowObject.PRSNR_IDNTY;
		var param2 = rowObject.SEX;
		var param3 = rowObject.NAME;
		var result = "<a href='' style='color: #4692f0;' onclick='toPrisonerInfo( "
				+ param1.toString()
				+");return false;'>操作</a>";
		return result;
	}
	
	function toPrisonerInfo(PRSNR_IDNTY) {
		
		$("#dialogId_jsxzzf").dialog({
				
				width : 1000,       
				height : 600, 
				title : '罪犯信息',
				modal : true, 
				autoOpen : false,
				url :"${ctx}/xxhj/zfjbxx/criminalArchivesFile?prisonerId=" + PRSNR_IDNTY,
			});
	
		$("#dialogId_jsxzzf").dialog("open");
	}
	
  
</script >



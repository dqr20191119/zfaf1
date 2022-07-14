<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<div>
	<cui:form id="formId_yljh_edit">
		<cui:input type='hidden' name="id" />
		<cui:input type='hidden' name="edpCusNumber" />
		<cui:input type='hidden' name="edpReleaseTime" />
		<cui:input type='hidden' name="edpStatus" />	
 		<table class="table">
			<tr>
				<th>演练标题：</th>
				<td><cui:input name="edpTitle" required="true"></cui:input></td>
				<th>演练预案：</th>
				<td><cui:combobox name="edpEmPlanFid" url="${ctx}/yjct/yabz/searchAllData?epiPlanStatus=4" required="true"></cui:combobox></td>				
			</tr>
			<tr>
				<th>演练时间：</th>
				<td><cui:datepicker name="edpTime" dateFormat="yyyy-MM-dd HH:mm:ss" required="true"></cui:datepicker></td>
				<th>演练地点：</th>
				<td><cui:input name="edpAddress" required="true"></cui:input></td>				
			</tr>			 
		</table>
	</cui:form>
</div>

<script>
	
	$.parseDone(function() {
		
		var id = '${id}';
		if(id) {
			loadForm("formId_yljh_edit", "${ctx}/yjct/yljh/getById?id=" + id, function(data) {
				
			});
		}		
	});
</script>
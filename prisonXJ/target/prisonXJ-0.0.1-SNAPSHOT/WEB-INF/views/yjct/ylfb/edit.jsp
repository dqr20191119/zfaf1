<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<div>
	<cui:form id="formId_ylfb_edit">
		<cui:input type='hidden' name="id" />
		<cui:input type='hidden' name="edpCusNumber" />
		<cui:input type='hidden' name="edpReleaseTime" />
		<cui:input type='hidden' name="edpStatus" />	
 		<table class="table">
			<tr>
				<th>演练标题：</th>
				<td><cui:input name="edpTitle" readonly="true"></cui:input></td>
				<th>演练预案：</th>
				<td><cui:combobox name="edpEmPlanFid" url="${ctx}/yjct/yabz/searchAllData?epiPlanStatus=4" readonly="true"></cui:combobox></td>				
			</tr>
			<tr>
				<th>演练时间：</th>
				<td><cui:datepicker name="edpTime" dateFormat="yyyy-MM-dd HH:mm:ss" readonly="true"></cui:datepicker></td>
				<th>演练地点：</th>
				<td><cui:input name="edpAddress" readonly="true"></cui:input></td>				
			</tr>
		</table>
		<table class="table">
			<tr>
				<th rowspan="2" style="vertical-align: middle; width: 100px; text-align: center;">监狱领导</th>
				<th>审批意见</th>
				<td><cui:radiolist name="ehaIsAgree" data="combobox_yjct_sfty" readonly="true"></cui:radiolist></td>
			</tr>
			<tr>
				<th>审批内容</th>
				<td><cui:textarea name="ehaAgreeContent" width="500" height="100" readonly="true"></cui:textarea></td>
	 		</tr>			 
		</table>
	</cui:form>
</div>

<script>
	
	$.parseDone(function() {
		
		var id = '${id}';
		if(id) {
			loadForm("formId_ylfb_edit", "${ctx}/yjct/yljh/getById?id=" + id + "&isHaveSp=1", function(data) {
				
			});
		}		
	});
</script>
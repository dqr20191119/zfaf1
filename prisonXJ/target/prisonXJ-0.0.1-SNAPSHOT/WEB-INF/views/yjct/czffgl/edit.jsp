<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<div>
	<cui:form id="formId_czffgl_edit">
		<cui:input type='hidden' name="id" />
		<cui:input type='hidden' name="dmiCusNumber" />
		<cui:input type='hidden' name="dmiStatus" />
 		<table class="table">
			<tr>
				<th>方法名称：</th>
				<td><cui:input name="dmiMethodName" required="true"></cui:input></td>		
				<th>预案类别：</th>
				<td><cui:combobox name="dmiPlanType" data="combobox_yjct_planType" required="true"></cui:combobox></td>				
			</tr>
			<tr>
				<th>关联操作项：</th>
				<td colspan="3"><cui:combobox name="methodRelAction" data="combobox_yjct_actionType" width="500" multiple="true" required="true"></cui:combobox></td>				
			</tr>
			<tr>
				<th>方法描述：</th>
				<td colspan="3"><cui:textarea name="dmiMethodDesc" width="500" required="true"></cui:textarea></td>
			</tr> 
		</table>
	</cui:form>
</div>

<script>
	
	$.parseDone(function() {
		
		var id = '${id}';
		if(id) {
			loadForm("formId_czffgl_edit", "${ctx}/yjct/czffgl/getById?id=" + id, function(data) {
				
			});
		}		
	});
</script>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<div>
	<cui:form id="formId_wzgl_edit">
		<cui:input type='hidden' name="id" />
		<cui:input type='hidden' name="mriCusNumber" />
		<cui:input type='hidden' name="mriStatus" />
 		<table class="table">
			<tr>
				<th>物资名称：</th>
				<td><cui:input name="mriMaterialName" required="true"></cui:input></td>
				<th>物资类型：</th>
				<td><cui:input name="mriMaterialType"></cui:input></td>				
			</tr>
			<tr>
				<th>存放位置：</th>
				<td><cui:input name="mriAddress" required="true"></cui:input></td>
				<th>用途：</th>
				<td><cui:input name="mriUse"></cui:input></td>				
			</tr>
			<tr>
				<th>数量：</th>
				<td><cui:input name="mriCount" required="true"></cui:input></td>
				<th>所属单位：</th>
				<td><cui:combobox name="mriMaterialCompany" url="${ctx}/common/authsystem/findAllJyForCombobox" required="true"></cui:combobox></td>				
			</tr>
			<tr>
				<th>负责人：</th>
				<td><cui:input name="mriCharger" required="true"></cui:input></td>
				<th>负责电话：</th>
				<td><cui:input name="mriChargePhone" required="true"></cui:input></td>				
			</tr>
			<tr>
				<th>签约单位：</th>
				<td><cui:input name="mriSignCompany"></cui:input></td>
				<th>规格说明：</th>
				<td><cui:input name="mriSpec"></cui:input></td>				
			</tr> 
		</table>
	</cui:form>
</div>

<script>
	
	$.parseDone(function() {
		
		var id = '${id}';
		if(id) {
			loadForm("formId_wzgl_edit", "${ctx}/yjct/wzgl/getById?id=" + id, function(data) {
				
			});
		}		
	});
</script>
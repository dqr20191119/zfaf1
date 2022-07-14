<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<div>
	<cui:form id="formId_zjgl_edit">
		<cui:input type='hidden' name="id" />
		<cui:input type='hidden' name="epiCusNumber" />
		<cui:input type='hidden' name="epiSttsIndc" />
 		<table class="table">
			<tr>
				<th>专家名称：</th>
				<td><cui:input name="epiExpertName" required="true"></cui:input></td>
				<th>性别：</th>
				<td><cui:combobox name="epiSex" data="combobox_yjct_sex" required="true"></cui:combobox></td>				
			</tr>
			<tr>
				<th>所属单位：</th>
				<td><cui:combobox name="epiCompany" url="${ctx}/common/authsystem/findAllJyForCombobox" required="true"></cui:combobox></td>
				<th>年龄：</th>
				<td><cui:input name="epiAge" validType="integer"></cui:input></td>				
			</tr>
			<tr>
				<th>职位：</th>
				<td><cui:input name="epiPost"></cui:input></td>
				<th>职能：</th>
				<td><cui:input name="epiFunction"></cui:input></td>		
			</tr>
			<tr>
				<th>应急特长：</th>
				<td><cui:input name="epiSpecialty" required="true"></cui:input></td>
				<th>类别：</th>
				<td><cui:input name="epiType"></cui:input></td>				
			</tr>
			<tr>
				<th>电话：</th>
				<td><cui:input name="epiPhone" required="true"></cui:input></td>
				<th>备注：</th>
				<td><cui:input name="epiRemark"></cui:input></td>				
			</tr> 
		</table>
	</cui:form>
</div>

<script>
	
	$.parseDone(function() {
		
		var id = '${id}';
		if(id) {
			loadForm("formId_zjgl_edit", "${ctx}/yjct/zjgl/getById?id=" + id, function(data) {
				
			});
		}		
	});
</script>
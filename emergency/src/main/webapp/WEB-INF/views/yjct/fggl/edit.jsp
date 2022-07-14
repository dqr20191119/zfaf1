<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<div>
	<cui:form id="formId_fggl_edit">
		<cui:input type='hidden' name="id" />
		<cui:input type='hidden' name="lriCusNumber" />
		<cui:input type='hidden' name="lpiSttsIndc" />
  		<table class="table">
			<tr>
				<th>法规名称：</th>
				<td><cui:input name="lriLawsName" required="true"></cui:input></td>		
				<th>法规类别：</th>
				<td><cui:combobox name="lriLawsType" data="combobox_yjct_fgType" required="true"></cui:combobox></td>					
			</tr>
			<tr>
				<th>法规内容：</th>
				<td colspan="3"><cui:textarea name="lriLawsContent" required="true" width="500"></cui:textarea></td>				 	
			</tr>			 
		</table>
	</cui:form>
</div>

<script>
	
	$.parseDone(function() {
		
		var id = '${id}';
		if(id) {
			loadForm("formId_fggl_edit", "${ctx}/yjct/fggl/getById?id=" + id, function(data) {
				
			});
		}		
	});
</script>
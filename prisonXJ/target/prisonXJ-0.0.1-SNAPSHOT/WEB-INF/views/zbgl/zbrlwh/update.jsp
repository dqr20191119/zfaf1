<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.cesgroup.prison.common.facade.AuthSystemFacade"%>
<%@ page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<div>
	<cui:form id="formId_rlwh_update">
		<cui:input type='hidden' name="id" />
 		<table class="table">
			<tr>
				<th>日期：</th>
				<td> <cui:datepicker name="dutyDate" readonly="true" dateFormat="yyyy-MM-dd"></cui:datepicker>
				</td>
			</tr>
			<tr>	
				<th>节假日标志：</th>
				<td>
				<cui:combobox  name="flag"  data="combobox_flag" required="true" width="250"></cui:combobox>
			    </td>
			</tr>
			<tr>
		</table>
	</cui:form>
</div>
<script>
var cusNumber = jsConst.CUS_NUMBER;
var USER_LEVEL = jsConst.USER_LEVEL;
	$.parseDone(function() {
 		 var id='${id}';
		if(id) {
			loadForm("formId_rlwh_update", "${ctx}/zbgl/zbrlwh/getById?id=" + id, function(data) {
			    
			});
		}
	});
	
	
	
</script>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.cesgroup.prison.common.facade.AuthSystemFacade"%>
<%@ page import="com.cesgroup.prison.common.bean.user.UserBean" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%
   UserBean user = AuthSystemFacade.getLoginUserInfo();
   request.setAttribute("cusNumber",user.getCusNumber());
%>
<div>
	<cui:form id="formId_gwgl_edit">
		<cui:input type='hidden' name="id" />
		<cui:input type='hidden' name="cdjCusNumber" />
		<cui:input type='hidden' name="cdjStatus" />
 		<table class="table">
			<tr>
				<th>岗位名称：</th>
				<td>
				<%-- <cui:input id="jobName" name="cdjJobName" required="true" width="250"></cui:input> --%>
                 <c:if test="${cusNumber==4300}">
				    <cui:combobox id="jobName" name="cdjJobName" data="combobox_cdjJobName" required="true" width="250"></cui:combobox>
                 </c:if>
                <c:if test="${cusNumber !=4300}">
                    <cui:input id="jobName" name="cdjJobName" required="true" width="250"></cui:input>
                </c:if>
				</td>	
			</tr>
			<tr>			
				<th>所属类别：</th>
				<td><cui:combobox id="cdjCategoryName" name="cdjCategoryId" url="${ctx}/zbgl/lbgl/searchAllData?dcaStatus=1" required="true" width="250"></cui:combobox></td>
			</tr>
			<%-- <tr>		
				<th>岗位编码：</th>
				<td><cui:combobox id="cdjJobCode" name="cdjJobCode" data="combobox_jbdJobCode" required="true" width="250"></cui:combobox></td>
			</tr> --%>
			<tr>
				<th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;备注：</th>
				<td><cui:textarea name="cdjRemark" width="250"></cui:textarea></td>				
			</tr>
		</table>
	</cui:form>
</div>

<script>
	
	$.parseDone(function() {
	
		var id = '${id}';
		if(id) {
			loadForm("formId_gwgl_edit", "${ctx}/zbgl/gwgl/getById?id="+id, function(data) {
				
				$("#cdjCategoryName").combobox("option","readonly",true);
				

			});
		}	
	});
	
</script>

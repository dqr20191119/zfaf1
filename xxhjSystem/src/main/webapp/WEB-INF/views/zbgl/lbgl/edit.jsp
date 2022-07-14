<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.cesgroup.prison.common.facade.AuthSystemFacade"%>
<%@ page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<div>
	<cui:form id="formId_lbgl_edit">
		<cui:input type='hidden' name="id" />
		<cui:input type='hidden' name="dcaCusNumber" />
		<cui:input type='hidden' name="dcaStatus" />
 		<table class="table">
			<tr>
				<th>类别名称：</th>
				<td>  <cui:input id="CategoryName" name="dcaCategoryName" required="true" width="250"></cui:input> 
					<%-- <cui:combobox id="CategoryName" name="dcaCategoryName" data="combobox_categoryName" width="250"> --%>
				</td>				
			</tr>
			<tr>	
				<th>关联部门：</th>
				<td>
				<cui:combobox id="comId_drpmnt" name="dcaDprtmntId" multiple="true" data="combobox_bm" width="250"></cui:combobox>
			    </td>
			</tr>
			<tr>
         	<th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;备注：</th>
				<td><cui:textarea name="dcaRemark" width="250"></cui:textarea></td>				
			</tr>
		</table>
	</cui:form>
</div>
<script>
var cusNumber = jsConst.CUS_NUMBER;
var USER_LEVEL = jsConst.USER_LEVEL;
	$.parseDone(function() {
 		 var id='${id}';
		if(id) {
			loadForm("formId_lbgl_edit", "${ctx}/zbgl/lbgl/getById?id=" + id, function(data) {
				if(USER_LEVEL==1){
					$.ajax({
				        type : 'post',
				        url : '${ctx}/zbgl/rygl/listSjzzxx',
				        dataType : 'json',
				        success : function(data) {
				            $("#comId_drpmnt").combobox("loadData", data);
				        },
				        error : function(XMLHttpRequest, textStatus, errorThrown) {
				            $.message({message:"操作失败！", cls:"error"});
				        }
				    });
				}
			});
		}else{
			if(USER_LEVEL==1){
				$.ajax({
			        type : 'post',
			        url : '${ctx}/zbgl/rygl/listSjzzxx',
			        dataType : 'json',
			        success : function(data) {
			            $("#comId_drpmnt").combobox("loadData", data);
			        },
			        error : function(XMLHttpRequest, textStatus, errorThrown) {
			            $.message({message:"操作失败！", cls:"error"});
			        }
			    });
			}
		}  
	});
	
	
	
</script>
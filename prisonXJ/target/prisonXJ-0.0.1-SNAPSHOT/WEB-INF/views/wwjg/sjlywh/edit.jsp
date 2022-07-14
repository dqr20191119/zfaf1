<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.cesgroup.prison.common.facade.AuthSystemFacade"%>
<%@ page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<div>
	<cui:form id="formId_sjlywh_edit">
		<cui:input type='hidden' name="id" />
		<cui:input name="scflg" value="1" type="hidden"/>
		 
 		<table class="table">
			<tr>
				<th>编码：</th>
				<td>
				<cui:input  name="code" id="code" required="true" width="250"  onChange="getCodes()"/>
				</td>				
			</tr>
			<tr>	
				<th>名称：</th>
				<td>
				<cui:input  name="name" required="true"  width="250"/>
			    </td>
			</tr>
			<tr>
         	<th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;备注：</th>
				<td><cui:textarea name="bz" width="250"></cui:textarea></td>				
			</tr>
		</table>
	</cui:form>
</div>

<script>
	
	$.parseDone(function() {
		
 		var id='${id}';
		if(id) {
			loadForm("formId_sjlywh_edit", "${ctx}/wwjg/sjlywh/getById?id=" + id, function(data) {
				$("#code").textbox("option", "readonly", true);
			});
		} 	
	});
	function getCodes(){
		var code = $("#code").textbox("getValue");
		 $.ajax({
				type: 'post',
				url: '${ctx}/wwjg/sjlywh/getByCode?code='+code,
				dataType: 'json',
				success: function(data) {
					if(data=="1"){
						$.alert("此编码已存在！");
						$("#code").textbox("setValue","");
						return;
					}
				},
				error: function(XMLHttpRequest, textStatus, errorThrown) {
					$.message("获取失败！");
				}
			});
	}
</script>
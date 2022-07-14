<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.cesgroup.prison.common.facade.AuthSystemFacade"%>
<%@ page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<div>
	<cui:form id="formId_sjfwgl_edit">
		<cui:input type='hidden' name="id" />
		<cui:input name="scflg" value="1" type="hidden"/>
		 
 		<table class="table">
 			<tr>
				<th>五维架构</th>
				<td>
					<cui:combobox  name="wwjgId" data="" id="wwjgId" required="true" onChange="huoquwujgmc()" width="250"/>
					<cui:input  name="wwjgName" id="wwjgName" type="hidden"/>
				</td>				
			</tr>
			<tr>
				<th>编码：</th>
				<td>
				<cui:input  name="code" id="code" required="true" width="250" onChange="getCodes()"/>
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
			loadForm("formId_sjfwgl_edit", "${ctx}/wwjg/sjfwgl/getById?id=" + id, function(data) {
				$("#code").textbox("option", "readonly", true);
			});
		} 
		loadWwjg();
	});
	//五维架构
	function loadWwjg() {
		var lcmlUrl =  "${ctx}/wwjg/wwjgwh/searchAllData";
	    $("#wwjgId").combobox("reload",lcmlUrl);
	}
	//选择五维架构后触发
	function huoquwujgmc(){
		var a  = $("#wwjgId").combobox("getText");
		$("#wwjgName").textbox("setValue",a);
	}
	function getCodes(){
		var code = $("#code").textbox("getValue");
		 $.ajax({
				type: 'post',
				url: '${ctx}/wwjg/sjfwgl/getByCode?code='+code,
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
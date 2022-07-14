<%@page import="com.cesgroup.prison.common.bean.user.UserBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.cesgroup.prison.common.facade.AuthSystemFacade"%>
<%@ page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%
UserBean user = AuthSystemFacade.getLoginUserInfo();
 String org1 = user.getOrgName();
 String orgcode1 = user.getOrgCode();
 
%>
<div>
	<cui:form id="formId_zzwh_edit">
		<cui:input type='hidden' name="id" />
		<cui:input name="scflg" value="1" type="hidden"/>
		 
 		<table class="table">
 			<tr>
				<th>所属监狱</th>
				<td>
					<cui:input  name="jyId" id="jyId" type="hidden"/>
					<cui:input  name="jyName" id="jyName" type="text" readonly="true"/>
				</td>				
			</tr>
			<tr>	
				<th>组织级别：</th>
				<td>
					<cui:combobox  name="zzJb" data="combobox_zzjb" id="zzJb" required="true" width="250" onChange="zzjb"/>
			    </td>
			</tr>
			<tr>
				<th>编码：</th>
				<td>
				<cui:input  name="zzCode" id="zzCode" required="true" width="250" onChange="getCodes()"/>
				</td>				
			</tr>
			<tr>	
				<th>名称：</th>
				<td>
				<cui:input  name="zzName" required="true"  width="250"/>
			    </td>
			</tr>
			
			<tr>
         	<th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;备注：</th>
				<td><cui:textarea name="bz" width="250"></cui:textarea></td>				
			</tr>
			<tr>
         	
		</table>
	</cui:form>
</div>

<script>
	
	$.parseDone(function() {
		
 		var id='${id}';
		if(id) {
			loadForm("formId_zzwh_edit", "${ctx}/djwg/zzwh/getById?id=" + id, function(data) {
				$("#zzCode").textbox("option", "readonly", true);
			});
		} else{
			var org1 = "<%=org1%>";
	 		var orgCode1 = "<%=orgcode1%>";
	 		$("#jyName").textbox("setValue",org1);
	 		$("#jyId").textbox("setValue",orgCode1);
		}
		
	});
	
	function zzjb(event, ui){
			var jb = ui.value;
			if(jb=="1"){
				 $("#zzCode").textbox("setValue","DWBZCY");
			}
			if(jb=="2"){
				 $("#zzCode").textbox("setValue","FGBMJCDJLXD");
			}
	}
	function getCodes(){
		var zzCode = $("#zzCode").textbox("getValue");
		 $.ajax({
				type: 'post',
				url: '${ctx}/djwg/zzwh/getByCode?zzCode='+zzCode,
				dataType: 'json',
				success: function(data) {
					if(data=="1"){
						$.alert("此编码已存在！");
						$("#zzCode").textbox("setValue","");
						return;
					}
				},
				error: function(XMLHttpRequest, textStatus, errorThrown) {
					$.message("获取失败！");
				}
			});
	}
</script>
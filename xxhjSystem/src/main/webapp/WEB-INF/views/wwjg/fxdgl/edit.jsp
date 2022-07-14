<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.cesgroup.prison.common.facade.AuthSystemFacade"%>
<%@ page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<div>
	<cui:form id="formId_fxdgl_edit">
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
				<th>数据范围</th>
				<td>
					<cui:combobox  name="sjfwId" data="" id="sjfwId" required="true" onChange="huoqusjfwmc()" width="250"/>
					<cui:input  name="sjfwName" id="sjfwName" type="hidden"/>
				</td>				
			</tr>
			 <tr>
				<th>权重等级</th>
				<td>
					<cui:combobox  name="qzdjId" data="" id="qzdjId" required="true" onChange="huoquQzdjmc()" width="250"/>
					<cui:input  name="qzdjName" id="qzdjName" type="hidden"/>
				</td>				
			</tr>
			 <tr>
				<th>数据来源</th>
				<td>
					<cui:combobox  name="sjlyId" data="" id="sjlyId" multiple="true" required="true" onChange="huoquSjlymc()" width="250"/>
					<cui:input  name="sjlyName" id="sjlyName" type="hidden"/>
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
         	<th>事项说明：</th>
				<td><cui:textarea name="sxsm" width="250"></cui:textarea></td>				
			</tr>
			<tr>
         	<th>备注：</th>
				<td><cui:textarea name="bz" width="250"></cui:textarea></td>				
			</tr>
		</table>
	</cui:form>
</div>

<script>
	
	$.parseDone(function() {
		
 		var id='${id}';
		if(id) {
			loadForm("formId_fxdgl_edit", "${ctx}/wwjg/fxdgl/getById?id=" + id, function(data) {
				$("#code").textbox("option", "readonly", true);
				loadsjfw(data.wwjgId);
			});
		} 	
		loadWwjg();
		loadQzdj();
		loadSjly();
	});
	//五维架构
	function loadWwjg() {
		var lcmlUrl =  "${ctx}/wwjg/wwjgwh/searchAllData";
	    $("#wwjgId").combobox("reload",lcmlUrl);
	}
	//选择五维架构后触发
	function huoquwujgmc(){
		var a  = $("#wwjgId").combobox("getText");
		var b  = $("#wwjgId").combobox("getValue");
		$("#wwjgName").textbox("setValue",a);
		$("#sjfwId").combobox("setValue","");
		$("#sjfwName").textbox("setValue","");
		loadsjfw(b);
	}
	
	
//权重等级
	function loadQzdj() {
		var lcmlUrl =  "${ctx}/wwjg/qzdjwh/searchAllData";
	    $("#qzdjId").combobox("reload",lcmlUrl);
	}
	
	function huoquQzdjmc(){
		var a  = $("#qzdjId").combobox("getText");
		$("#qzdjName").textbox("setValue",a);
	}
	
	//数据来源
	function loadSjly() {
		var lcmlUrl =  "${ctx}/wwjg/sjlywh/searchAllData";
	    $("#sjlyId").combobox("reload",lcmlUrl);
	}
	
	 function  huoquSjlymc(){
		 var a  = $("#sjlyId").combobox("getText");
		$("#sjlyName").textbox("setValue",a);
	 }
	
//数据范围
	function loadsjfw(b) {
	
		var lcmlUrl =  "${ctx}/wwjg/sjfwgl/searchAllData?wwjgId="+b;
	    $("#sjfwId").combobox("reload",lcmlUrl);
	}
	function huoqusjfwmc(){
		var a  = $("#sjfwId").combobox("getText");
		$("#sjfwName").textbox("setValue",a);
	}
	
	function getCodes(){
		var code = $("#code").textbox("getValue");
		 $.ajax({
				type: 'post',
				url: '${ctx}/wwjg/fxdgl/getByCode?code='+code,
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
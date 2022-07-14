<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.cesgroup.prison.common.facade.AuthSystemFacade"%>
<%@ page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<div>
	<cui:form id="formId_fxgkgl_edit">
		<cui:input type='hidden' name="id" />
		<cui:input name="scflg" value="1" type="hidden"/>
		 
 		<table class="table">
			
			 <tr>
				<th>五维架构：</th>
				<td>
					<cui:combobox name="wwjgId" data="" id="wwjgId" required="true" onChange="huoquwujgmc()" width="250"/>
					<cui:input name="wwjgName" id="wwjgName" type="hidden"/>
				</td>				
			</tr>
			 <tr>
				<th>数据范围：</th>
				<td>
					<cui:combobox name="sjfwId" data="" id="sjfwId" required="true" onChange="huoqusjfwmc()" width="250"/>
					<cui:input name="sjfwName" id="sjfwName" type="hidden"/>
				</td>				
			</tr>
			 
			 <tr>
				<th>风险点：</th>
				<td>
					<cui:combobox name="fxdId" data="" id="fxdId" required="true" onChange="huoquFxdmc()" width="250"/>
					<cui:input name="fxdName" id="fxdName" type="hidden"/>
				</td>				
			</tr>
			<%-- <tr>
				<th>事项说明：</th>
				<td >
					<cui:textarea height="200" name="sxsm" id="sxsm" readonly="true" width="250"></cui:textarea>
				</td>				
			</tr> --%>
			
			<tr>
				<th>风险等级：</th>
				<td>
					<cui:combobox name="fxdjId" data="" id="fxdjId" required="true" onChange="huoquFxdjmc()" width="250"/>
					<cui:input name="fxdjName" id="fxdjName" type="hidden"/>
				</td>				
			</tr> 
		  	<tr>
				<th>编码：</th>
				<td>
					<cui:input name="code"  id="code" required="true" width="250" onChange="getCodes()"/>
				</td>				
			</tr> 	
			<tr>
				<th>解决方案：</th>
				<td>
				<%-- <cui:combobox name="combobox" data="combo_data" id="combobox" required="true" onChange="huoqukfz()" width="250"/> --%>
					<cui:textarea  height="200" name="kfz"  id="kfz" required="true"  width="250"/>
				</td>				
			</tr>
			<tr>
         	<th>备注：</th>
				<td><cui:textarea name="bz" width="250" ></cui:textarea></td>				
			</tr>
		</table>
	</cui:form>
</div>

<script>
var combo_data = [
    {value:"0",text:"否","selected":true},
    {value:"1",text:"是"},
];
	
	
	$.parseDone(function() {
 		var id='${id}';
		if(id) {
			loadForm("formId_fxgkgl_edit", "${ctx}/wwjg/fxgkgl/getById?id=" + id, function(data) {
				$("#code").textbox("option", "readonly", true);
				loadsjfw(data.wwjgId);
				loadfxd(data.wwjgId,data.sjfwId);
			});
		} 	
		loadWwjg();
		loadFxdj();
	});

	function loadFxdj() {
		var lcmlUrl =  "${ctx}/wwjg/fxdjwh/searchAllData";
	    $("#fxdjId").combobox("reload",lcmlUrl);
	}
	 
  function huoquFxdjmc(){
 	 var a  = $("#fxdjId").combobox("getText");
		$("#fxdjName").textbox("setValue",a);
  }
  function huoqukfz(){
	 	 var a  = $("#combobox").combobox("getText");
			$("#kfz").textbox("setValue",a);
	  }
  
	function loadWwjg() {
		var lcmlUrl =  "${ctx}/wwjg/wwjgwh/searchAllData";
	    $("#wwjgId").combobox("reload",lcmlUrl);
	}
	function huoquwujgmc(){
		var a  = $("#wwjgId").combobox("getText");//获取五维架构combobox的Text值
		var b  = $("#wwjgId").combobox("getValue");
		$("#wwjgName").textbox("setValue",a);
		$("#sjfwId").combobox("setValue","");
		$("#sjfwName").textbox("setValue","");
		loadsjfw(b);//根据所选五维架构value值获取数据范围的combobox
	}
	
	
	function loadsjfw(b) {
		var lcmlUrl =  "${ctx}/wwjg/sjfwgl/searchAllData?wwjgId="+b;
	    $("#sjfwId").combobox("reload",lcmlUrl);
	}
	function huoqusjfwmc(){
		var a  = $("#sjfwId").combobox("getText");
		var b  = $("#sjfwId").combobox("getValue");
		var c  = $("#wwjgId").combobox("getValue");
		$("#sjfwName").textbox("setValue",a);
		
		$("#fxdId").combobox("setValue","");
		$("#fxdName").textbox("setValue","");
		loadfxd(c,b);
	}
	
	
	function loadfxd(a,b){//根据[五维架构]跟[数据范围]的value值来确定[风险点]的combobox
		var lcmlUrl =  "${ctx}/wwjg/fxdgl/searchAllData?wwjgId="+a+"&sjfwId="+b;
	    $("#fxdId").combobox("reload",lcmlUrl);
	}
	
	function huoquFxdmc(){
		var a  = $("#fxdId").combobox("getText");
		var a1  = $("#fxdId").combobox("getValue");
		$("#fxdName").textbox("setValue",a);
		// setsxsm(a1);
		 
	}
	
	function setsxsm(a){
		 $.ajax({
				type: 'post',
				url: '${ctx}/wwjg/fxdgl/getById?id='+a,
				dataType: 'json',
				success: function(data) {
					var sxsm = data.sxsm;
					$("#sxsm").textbox("setValue",sxsm);
				},
				error: function(XMLHttpRequest, textStatus, errorThrown) {
					$.message("获取失败！");
				}
			});
	}
	function getCodes(){
		var code = $("#code").textbox("getValue");
		 $.ajax({
				type: 'post',
				url: '${ctx}/wwjg/fxgkgl/getByCode?code='+code,
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
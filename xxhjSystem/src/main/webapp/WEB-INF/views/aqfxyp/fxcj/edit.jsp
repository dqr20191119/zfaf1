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
	<cui:form id="formId_fxcj_edit">
		<cui:input type='hidden' name="id" />
		<cui:input name="scflg" value="1" type="hidden"/>
 		<table class="table">
			
			<tr>
				<th>监狱</th>
				<td>
					<cui:input  name="jyId" id="jyId" type="hidden"/>
					<cui:input  name="jyName" id="jyName" type="text"/>
				</td>				
			</tr>
			
			<tr style="display:none;" >
				<th>网格一级</th>
				<td>
					<cui:combobox  name="wgOne" id="wgOne"   data="combobox_wgOne" onChange="getTwo()"></cui:combobox>
				</td>				
			</tr>
			<tr  style="display:none;" >
				<th>网格二级</th>
				<td>
					<cui:combobox  name="wgTwo" id="wgTwo"    data=""  onChange="getThree()"></cui:combobox>
				</td>				
			</tr>
			<tr  style="display:none;" >
				<th>网格三级</th>
				<td>
					<cui:combobox  name="wgThree" id="wgThree"   data=""  ></cui:combobox>
				</td>				
			</tr>
			
			<tr>
				<th>风险日期</th>
				<td>
					<cui:datepicker  id="fxDate" name="fxDate" dateFormat="yyyy-MM-dd"  required="true"></cui:datepicker>
				</td>				
			</tr>
			<tr>
				<th>计划解决时间</th>
				<td>
					<cui:datepicker  id="jhJjsj" name="jhJjsj" dateFormat="yyyy-MM-dd HH:mm:ss"></cui:datepicker>
				</td>				
			</tr>
			<tr>
				<th>五维架构</th>
				<td>
					<cui:combobox  name="wwjgId" id="wwjgId"  data="" required="true" width="250"  onChange="huoquwujgmc()"></cui:combobox>
					<cui:input  name="wwjgName" id="wwjgName" type="hidden" readonly="true"></cui:input>
				</td>				
			</tr>
			 <tr>
				<th>数据范围</th>
				<td>
					<cui:combobox  name="sjfwId" id="sjfwId" data="" required="true" width="250" onChange="huoqusjfwmc()"></cui:combobox>
					<cui:input  name="sjfwName" id="sjfwName" type="hidden"  readonly="true"></cui:input>
				</td>				
			</tr> 
			 <tr>
				<th>风险点</th>
				<td>
					<cui:combobox  name="fxdId" id="fxdId" data="" required="true" width="250" onChange="huoquFxdmc()" ></cui:combobox>
					<cui:input  name="fxdName" id="fxdName" type="hidden"></cui:input>
				</td>				
			</tr> 
			<tr>
				<th>评估条件</th>
				<td>
					<cui:combobox  name="pgtjId" id="pgtjId" data="" required="true" width="250"  onChange="huoqupgthmc()"></cui:combobox>
					<cui:input  name="pgtjName" id="pgtjName" type="hidden"></cui:input>
				</td>				
			</tr> 
			<tr>
				<th>单项扣分</th>
				<td>
					<cui:input  name="dxkf" id="dxkf" type="text" required="true"/>
				</td>				
			</tr> 
			<tr>
				<th>扣除数量</th>
				<td>
					<cui:input  name="kcsl" id="kcsl" type="text" required="true" onChange="jisuanzf()"/>
				</td>				
			</tr>
			<tr>
				<th>扣除总分</th>
				<td>
					<cui:input  name="kczf" id="kczf" type="text" required="true"/>
				</td>				
			</tr>
			<tr>
				<th>是否有效</th>
				<td>
					<cui:combobox  name="status" id="status"  data="commbobox_sfyx"  ></cui:combobox>
				</td>				
			</tr>
			<tr>
         	<th>明细：</th>
				<td><cui:textarea name="bz" width="250"></cui:textarea></td>				
			</tr>
		</table>
	</cui:form>
</div>

<script>
	
	$.parseDone(function() {
		
 		var id='${id}';
		if(id) {
			loadForm("formId_fxcj_edit", "${ctx}/aqfxyp/fxcj/getById?id=" + id, function(data) {
				loadWwjg();
				loadsjfw(data.wwjgId);
				loadfxd(data.wwjgId,data.sjfwId);
				loadpgtj(data.wwjgId,data.sjfwId,data.fxdId);
				if(data.wgOne){
					getTwo1(data.wgOne);
				}
				if(data.wgTwo){
					getThree1(data.wgTwo);
				}
				
			});
		}else{
			initwwjg();
			$("#status").combobox("setValue","1");
		}
		 
	});
	
	function getTwo(){
		var wgOne  = $("#wgOne").combobox("getValue");
		var lcmlUrl =  "${ctx}/aqfxyp/fxcj/searchWG?leve=2&parent="+wgOne;
	    $("#wgTwo").combobox("reload",lcmlUrl);
	}
	function getThree(){
		var wgTwo  = $("#wgTwo").combobox("getValue");
		var lcmlUrl =  "${ctx}/aqfxyp/fxcj/searchWG?leve=3&parent="+wgTwo;
	    $("#wgThree").combobox("reload",lcmlUrl);
	}
	function getTwo1(a){
		 
		var lcmlUrl =  "${ctx}/aqfxyp/fxcj/searchWG?leve=2&parent="+a;
	    $("#wgTwo").combobox("reload",lcmlUrl);
	}
	function getThree1(b){
		var lcmlUrl =  "${ctx}/aqfxyp/fxcj/searchWG?leve=3&parent="+b;
	    $("#wgThree").combobox("reload",lcmlUrl);
	}
	
	function initwwjg(){
		var wwjgId = "${wwjgId}";
 		var wwjgName = "${wwjgName}";
 		var sjfwId="${sjfwId}";
 		var sjfwName="${sjfwName}";
 		var org1 = "<%=org1%>";
 		var orgCode1 = "<%=orgcode1%>";
 		
 		$("#jyName").textbox("setValue",org1);
 		$("#jyId").textbox("setValue",orgCode1);
 		if(wwjgId){
 			loadWwjg();
 			loadsjfw(wwjgId);
 			$("#wwjgId").combobox("setValue",wwjgId);
 			$("#wwjgName").textbox("setValue",wwjgName);
 			if(sjfwId){
 				loadfxd(wwjgId,sjfwId);
 				$("#sjfwId").combobox("setValue",sjfwId);
 				$("#sjfwName").textbox("setValue",sjfwName);
 			} 
 		}
		
	}
	
	
	//五维架构
	function loadWwjg() {
		var lcmlUrl =  "${ctx}/wwjg/wwjgwh/searchAllData";
	    $("#wwjgId").combobox("reload",lcmlUrl);
	}
	//数据范围
	function loadsjfw(b) {
		var lcmlUrl =  "${ctx}/wwjg/sjfwgl/searchAllData?wwjgId="+b;
	    $("#sjfwId").combobox("reload",lcmlUrl);
	}
	/*风险点
	**/
	function loadfxd(a,b){
		var lcmlUrl =  "${ctx}/wwjg/fxdgl/searchAllData?wwjgId="+a+"&sjfwId="+b;
	    $("#fxdId").combobox("reload",lcmlUrl);
	}
	/**评估条件**/
	function loadpgtj(a,b,c){
		var lcmlUrl =  "${ctx}/wwjg/pgtjgl/searchAllData?wwjgId="+a+"&sjfwId="+b+"&fxdId="+c;
		 $("#pgtjId").combobox("reload",lcmlUrl);
	}
	
	function huoquwujgmc(){
		var a  = $("#wwjgId").combobox("getText");
		var b  = $("#wwjgId").combobox("getValue");
		$("#wwjgName").textbox("setValue",a);
		$("#sjfwId").combobox("setValue","");
		$("#sjfwName").textbox("setValue","");
		loadsjfw(b);
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
	function huoquFxdmc(){
		var a  = $("#fxdId").combobox("getText");
		$("#fxdName").textbox("setValue",a);
		var a  = $("#wwjgId").combobox("getValue");
		var b  = $("#sjfwId").combobox("getValue");
		var c  = $("#fxdId").combobox("getValue");
		loadpgtj(a,b,c);
	}
	function huoqupgthmc(){
		var a  = $("#pgtjId").combobox("getText");
		var pjid  = $("#pgtjId").combobox("getValue");
		$("#pgtjName").textbox("setValue",a);
		setfs(pjid);
	}
	function setfs(a){
		 $.ajax({
				type: 'post',
				url: '${ctx}/wwjg/pgtjgl/getById?id='+a,
				dataType: 'json',
				success: function(data) {
					var kfz = data.kfz;
					$("#dxkf").textbox("setValue",kfz);
				},
				error: function(XMLHttpRequest, textStatus, errorThrown) {
					$.message("获取失败！");
				}
			});
	}
	function jisuanzf(){
		var dxkf = $("#dxkf").textbox("getValue");
		var kcsl = $("#kcsl").textbox("getValue");
		if(dxkf){
			$("#kczf").textbox("setValue",  (parseFloat(dxkf)*parseFloat(kcsl)).toFixed(2));
		}else{
			$.alert("单项扣分值不存在！");
			$("#kcsl").textbox("setValue","");
		}
	}
	 
</script>
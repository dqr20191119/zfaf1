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
	<cui:form id="formId_rywh_edit">
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
				<th>组织：</th>
				<td>
				<cui:combobox  name="zzCode" data="" id="zzCode" required="true" width="250" onChange="zzkzxm"/>
				 <cui:input id="zzName" name="zzName"    type="hidden"></cui:input>
				</td>				
			</tr>
			<tr id="usercl">	
				<th>姓名：</th>
				<td>
				<cui:combotree id="userId" name="userId"    onChange="setUserName" allowPushParent="false" multiple="true"></cui:combotree>
                 <cui:input id="userName" name="userName"    type="hidden"></cui:input>
			    </td>
			</tr>
			<tr id="deptcl">	
				<th>部门：</th>
				<td>
                 <cui:input id="deptName"  name="deptName"></cui:input>
			    </td>
			</tr>
			
			<tr id="zwcl">
				<th >职务：</th>
				<td >
				<cui:input  name="zw" id="zw"  width="250"/>
				</td>				
			</tr>
			
			<tr id="bm">
				<th >编码：</th>
				<td >
				<cui:input  name="code" id="code"  width="250"/>
				</td>				
			</tr>
			
			<tr id="flbm">
				<th >父类编码：</th>
				<td >
				<cui:combobox  name="parentCode" data="" id="parentCode" required="true" width="250"/>
				</td>				
			</tr>
			
			 <tr>
				<th>排序：</th>
				<td>
				<cui:input  name="px" id="px" required="true" width="250"/>
				</td>				
			</tr>
			<tr>
         	<th>备注：</th>
				<td><cui:textarea name="bz" width="250"></cui:textarea></td>				
			</tr>
			<tr>
         	
		</table>
	</cui:form>
</div>

<script>
var cusNumber = jsConst.CUS_NUMBER;//监狱号
var org1 = "<%=org1%>";
	var orgCode1 = "<%=orgcode1%>";
	$.parseDone(function() {
		
 		var id='${id}';
		if(id) {
			loadForm("formId_rywh_edit", "${ctx}/djwg/rywh/getById?id=" + id, function(data) {
				//$("#userId").combotree("setValue",data.userId);
				$("#code").textbox("option", "readonly", true);
				pdzsjg(data.zzCode,data.userName);
			});
		}else{
			
	 		$("#jyName").textbox("setValue",org1);
	 		$("#jyId").textbox("setValue",orgCode1);
			$("#zwcl").hide();
			$("#usercl").hide();
			$("#deptcl").hide();
			$("#bm").hide();
			$("#flbm").hide();
		} 	
		loadWwjg();
		loadParentCode();
	});
	function loadWwjg() {
		var lcmlUrl =  "${ctx}/djwg/zzwh/searchAllData";
	    $("#zzCode").combobox("reload",lcmlUrl);
	}
	function loadParentCode() {
		var lcmlUrl =  "${ctx}/djwg/rywh/searchAllData";
	    $("#parentCode").combobox("reload",lcmlUrl);
	}
	function setUserName(event, ui){
		$("#userName").textbox("setValue",ui.newText);
	}
	// multiple="true"
	function pdzsjg(code,userName){
		$("#zwcl").hide();
		$("#usercl").hide();
		$("#deptcl").hide();
		$("#bm").hide();
		$("#flbm").hide();
		$("#code").textbox("option", "required", false);
		if(code == orgCode1+'_DWBZCY'){
			//如果为党委班子成员
			$("#zwcl").show();
			$("#usercl").show();
			$("#bm").show();
			$("#code").textbox("option", "required", true);
			$("#flbm").hide();
		}else if(code == orgCode1+'_FGBMJCDJLXD'){
			//如果为分管部门
			$("#zwcl").hide();
			$("#usercl").hide();
			$("#deptcl").show();
			$("#deptName").textbox("setValue",userName);
			$("#bm").hide();
			$("#flbm").show();
		}else{
			$("#deptcl").hide();
			$("#usercl").show();
			$("#bm").hide();
			$("#flbm").show();
		}
	}
	function zzkzxm(event, ui){
		$("#zzName").textbox("setValue",ui.text);
		$("#zwcl").hide();
		$("#usercl").hide();
		$("#deptcl").hide();
		
		$("#zw").textbox("setValue","");
		$("#userId").combotree("setValue","");
		$("#userName").textbox("setValue","");
		$("#deptName").textbox("setValue","");
		$("#code").textbox("setValue","");
		$("#parentCode").combobox("setValue","");
		$("#code").textbox("option", "required", false);
		if(ui.value == orgCode1+'_DWBZCY'){
			//如果为党委班子成员
			$("#zwcl").show();
			$("#usercl").show();
			$("#bm").show();
			$("#code").textbox("option", "required", true);
			$("#flbm").hide();
		}else if(ui.value == orgCode1+'_FGBMJCDJLXD'){
			//如果为分管部门
			$("#zwcl").hide();
			$("#usercl").hide();
			$("#deptcl").show();
			$("#bm").hide();
			$("#flbm").show();
		}else{
			$("#deptcl").hide();
			$("#usercl").show();
			$("#bm").hide();
			$("#flbm").show();
		}
	}
	
	$("#userId").combotree({url:"${ctx}/common/authsystem/findSyncDeptPoliceForCombotree?cusNumber="+cusNumber});
</script>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.cesgroup.prison.common.facade.AuthSystemFacade"%>
<%@page import="com.cesgroup.prison.common.bean.user.UserBean"%>
<%@ page import="com.cesgroup.prison.zbgl.rygl.util.RyCommon"%>
<%@ page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <%
UserBean userInfo = AuthSystemFacade.getLoginUserInfo();
request.setAttribute("cusNumber", userInfo.getCusNumber());
%> 
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<div style="height:100%;">
	<cui:form id="formId_zfcjfh_query">
		<table class="table">
			<tr>	
				<th>复核状态：</th>
				<td>
					<cui:combobox id ="combobox_zt" name ="zt" data="combobox_zt" ></cui:combobox>
				</td>
			</tr>
			<tr>	
				<td>
	 				<cui:button label="查询" componentCls="btn-primary" onClick="search"/>
					<cui:button label="重置" onClick="clear"></cui:button>
				</td>
			</tr>
		</table>
	</cui:form>
	<div style="height: 40px;">
		<cui:toolbar id="toolbarId_zfcjfh_query" data="toolbar_zfcjfh_query"></cui:toolbar>	
	</div>	
	
	<cui:grid id="gridId_zfcjfh_query" rownumbers="true" multiselect="true" width="auto" fitStyle="fill" rowNum="20">
		<cui:gridCols>
			<cui:gridCol name="id" hidden="true">id</cui:gridCol>
			<cui:gridCol name="cusNumber" align="center" formatter="convertCode" revertCode="true" formatoptions="{
			'data': combox_dw
			}">监狱</cui:gridCol>
			<cui:gridCol name="zfBh" hidden="true">值班时间</cui:gridCol>
			<cui:gridCol name="zm" align="center">罪名</cui:gridCol>
			<cui:gridCol name="sflb" align="center">释放类别</cui:gridCol>
			<cui:gridCol name="sfRq" align="center" >释放日期</cui:gridCol>
			<cui:gridCol name="zt" align="center" formatter="convertCode" revertCode="true" formatoptions="{
			'data': combobox_zt
			}">状态</cui:gridCol>
		</cui:gridCols>
		<cui:gridPager gridId="gridId_zfcjfh_query"/>
	</cui:grid>
</div>

<script>
	var combobox_bm = <%=AuthSystemFacade.getAllChildrenOrgJsonInfoByOrgKey(null)%>;
	var combox_dw =<%= AuthSystemFacade.getAllJyJsonInfo()%>;
	var commonbox_ry =<%= RyCommon.getCommonbox3(request.getAttribute("cusNumber").toString())%>;
	var cusNumber = jsConst.CUS_NUMBER;
	var drpmntId = jsConst.DEPARTMENT_ID;
	var USER_LEVEL = jsConst.USER_LEVEL;
	var combobox_zt = [{"value":"0","text":"未复核"},
					   {"value":"1","text":"已复核"},
						];
	
	
	$.parseDone(function() {
		inItDutyData();
		search(); 
	});
	
	
	
	var toolbar_zfcjfh_query = [{
		"type" : "button",
		"id" : "btnId_zb",
		"label" : "复核",
		"onClick" : "startFh",
		"componentCls" : "btn-primary"
	}]
	
	
	function inItDutyData() {
		$.ajax({
			type : 'post',
			url : '${ctx}/aqfh/zfcjfh/inItDutyData',
			dataType : 'json',
			async: false,
			success : function(data) {
					if(data.code==200){
						/* if(data.message!='true'){
							$.message({message:data.message, cls:"waring"});
						} */
					}
			},
		});
	}
	
	
	function search() {
		var formData = {};
		formData.cusNumber = cusNumber;
		formData.zt = $("#combobox_zt").combobox("getValue");
		$("#gridId_zfcjfh_query").grid("option", "postData", formData);
		$("#gridId_zfcjfh_query").grid("reload","${ctx}/aqfh/zfcjfh/searchData"); 
	}
	
	function clear() {
			$("#formId_zfcjfh_query").form("reset");
	}
	
	
	
	
	
	
	
	
	//开始值班
	function startFh() {
		var selarrrow = $("#gridId_zfcjfh_query").grid("option","selarrrow");
		var rowData = $("#gridId_zfcjfh_query").grid("getRowData", selarrrow[0]);
		var zt = rowData.zt;
		if(selarrrow && selarrrow.length == 1 ) {
			var params = {};
			params.id = rowData.id;
			params.zt = "1";
			$.ajax({
				type : 'post',
				url : '${ctx}/aqfh/zfcjfh/updateById',
				data: params,
				dataType : 'json',
				async: false,
				success : function(data) {
						if(data.code==200){
							$.message({message:"审核成功！", cls:"success"});
							$("#gridId_zfcjfh_query").grid("reload");
						}
				}
			});
			
		}else{
			$.message({message:"请选择一条记录！", cls:"waring"});
			return;
		}	
		
	}
	
		
	
	
</script>
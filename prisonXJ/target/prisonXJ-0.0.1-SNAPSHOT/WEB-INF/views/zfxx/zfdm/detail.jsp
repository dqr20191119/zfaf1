<%@page import="com.cesgroup.prison.common.bean.user.UserBean"%>
<%@page import="com.cesgroup.prison.common.facade.AuthSystemFacade"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<%@page import="com.ces.prison.common.constants.GroupKeyConst"%>
<%@page import="com.ces.prison.common.constants.SystemConst"%>
<%@taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%
UserBean user = AuthSystemFacade.getLoginUserInfo();
String jyId= user.getOrgCode();
String jqId= user.getDprtmntCode();
%>
<style>
.form-control {
	width: 100%;
}
</style>
<div style="height: 100%;">
	<cui:form id="formId_query">
		<table class="table">
			<tr>
				<td>监所名称:&nbsp;&nbsp;
					<cui:combobox id="zfdm_cusNumber" name="cusNumber" data="combobox_jy" required="true" width="130"  onSelect="onFaultTypeSelect">
					</cui:combobox>
				</td>
				<td>所在监区:&nbsp;&nbsp;
					<cui:combobox id="deptId" name="deptId" width="130"></cui:combobox>
				</td>
				 
				<td>
					<cui:button label="查询" onClick="query"></cui:button>
					<cui:button label="重置" onClick="reset"></cui:button>
				</td>
			</tr>
		</table>
	</cui:form>
	
	<cui:grid id="gridId_zfdmDetail" rownumbers="true" singleselect="false" fitStyle="fill" width="auto" 
			  colModel="gridId_zfdm_colModelDate">
		<cui:gridPager gridId="gridId_zfdmDetail" />
	</cui:grid>
</div>
<script>
//监狱
var combobox_jy = <%=AuthSystemFacade.getAllJyJsonInfo()%>;
var jyId= "<%=jyId%>";
var jqId= "<%=jqId%>";
var USER_LEVEL = jsConst.USER_LEVEL;
$.parseDone(function() {
	var url = "${ctx}/zfxx/zfdm/search";
	var params = {};
	
	$('#gridId_zfdmDetail').grid('option', 'postData', params);
	$("#gridId_zfdmDetail").grid("reload", url);
	
	if(USER_LEVEL == 1) {
		//省局用户
		/* $('#cusNumber').combobox("setValue",jyId);
		$("#deptId").combobox("reload", "${ctx}/common/authsystem/findAllJqByJyKeyForCombobox?cusNumber=" + jyId);	//加载监狱监区 */
	} else if(USER_LEVEL == 2) {
		//监狱用户
		$('#zfdm_cusNumber').combobox("setValue",jyId);
		$('#zfdm_cusNumber').combobox("option","readonly",true);
		$("#deptId").combobox("reload", "${ctx}/common/authsystem/findAllJqByJyKeyForCombobox?cusNumber=" + jyId);	//加载监狱监区
	} else {
		//监区用户
		$('#zfdm_cusNumber').combobox("setValue",jyId);
		$("#deptId").combobox("reload", "${ctx}/common/authsystem/findAllJqByJyKeyForCombobox?cusNumber=" + jyId);	//加载监狱监区
		$('#deptId').combobox("setValue",jqId);
		$('#zfdm_cusNumber').combobox("option","readonly",true);
		$('#deptId').combobox("option","readonly",true);
	}
	
	
	
});

//省局视角 监狱选中 根据 监狱加载监区
function onFaultTypeSelect(event, ui) {
	$("#deptId").combobox("reload",  "${ctx}/common/authsystem/findAllJqByJyKeyForCombobox?cusNumber=" + ui.item.value);
}


var gridId_zfdm_colModelDate = [/* {
	label : "监狱名称",
	name : "CUS_NUMBER",
	width : "95", 
	align : "left"
}, */  {
	label : "监狱名称",
	align : "center",
	name : "JY_NAME",
	width : "85"
}, {
	label : "监区名称",
	align : "center",
	name : "JQ_NAME",
	width : "85"
},{
	label : "罪犯编号",
	name : "BH",
	width : "95", 
	align : "center"
}, {
	label : "罪犯名称",
	name : "XM",
	width : "95", 
	align : "center"
}, {
	label : "监舍号",
	name : "JSH",
	width : "75",
	align : "center"
} , {
	label : "点名状态",
	align : "center",
	name : "DMZT",
	width : "85"
}];

/**
 * 查询按钮时间
 */
function query() {
	var formData = $("#formId_query").form("formData");
	$('#gridId_zfdmDetail').grid('option', 'postData', formData);
	$("#gridId_zfdmDetail").grid("reload");
}

/**
 * 重置按钮事件
 */
var reset = function() {
	$("#formId_query").form("reset");
}
</script>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.cesgroup.prison.common.facade.AuthSystemFacade"%>
<%@ page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<script src="${ctx}/static/js/scripts/common.js"></script>
<div>
	<cui:form id="formId_wlryclfh_edit">
		<cui:input type='hidden' name="id" />
 		<table class="table" style="width: 100%">
			<tr>
				<td><label>监狱：</label></td>
				<td ><cui:combobox  name="cusNumber" data="combox_dw" readonly="true"></cui:combobox></td>
				<td><label>带领民警：</label></td>
				<td><cui:input  name="dlmjName" readonly="true" ></cui:input></td>
			</tr>
			<tr>	
				<td><label>进监事由：</label></td>
				<td><cui:input  name="jjsy" readonly="true"></cui:input></td>	
				<td><label>进监时间：</label></td>
				<td><cui:input  name="jjsj" readonly="true"></cui:input></td>
			</tr>
		</table>
	</cui:form>

	<div style="width: 100%;height: 550px">
        <c:if test="${lx==1}">
		<cui:grid id="gridId_wlryclfh_view_query" rownumbers="true" multiselect="true" width="auto" fitStyle="fill" rowNum="20"  >
			<cui:gridCols>
				<cui:gridCol name="name" align="center" >姓名</cui:gridCol>
				<cui:gridCol name="xb" align="center" formatter="convertCode" revertCode="true" formatoptions="{
			'data': combobox_xb
			}" >性别</cui:gridCol>
                <cui:gridCol name="zjmc" align="center" >证件名称</cui:gridCol>
                <cui:gridCol name="zjhm" align="center" >证件号码</cui:gridCol>
                <cui:gridCol name="wlwpsm" align="center" >外来物品说明</cui:gridCol><%--zjhm  wlwpsm--%>
                <cui:gridCol name="gzdw" align="center" >工作单位</cui:gridCol>
			</cui:gridCols>
			<cui:gridPager gridId="gridId_wlryclfh_view_query" />
		</cui:grid>
        </c:if>
        <c:if test="${lx==2}">
            <cui:grid id="gridId_wlryclfh_view_query" rownumbers="true" multiselect="true" width="auto" fitStyle="fill" rowNum="20"  >
                <cui:gridCols>
                    <cui:gridCol name="sjname" align="center" >名称</cui:gridCol>
                    <cui:gridCol name="xb" align="center" formatter="convertCode" revertCode="true" formatoptions="{
			'data': combobox_xb
			}" >性别</cui:gridCol>
                    <cui:gridCol name="cph" align="center" >车牌号</cui:gridCol>
                    <cui:gridCol name="driverNo" align="center" >驾驶证号</cui:gridCol>
                    <cui:gridCol name="wlwpsm" align="center" >外来物品说明</cui:gridCol>
                    <cui:gridCol name="gzdw" align="center" >工作单位</cui:gridCol>
                </cui:gridCols>
                <cui:gridPager gridId="gridId_wlryclfh_view_query" />
            </cui:grid>
        </c:if>
	</div>
</div>
<script>
	var cusNumber = jsConst.CUS_NUMBER;
	var USER_LEVEL = jsConst.USER_LEVEL;
	var drpmntId = jsConst.DEPARTMENT_ID;
	var lx = '${lx}';
	var id = '${id}';                     
	$.parseDone(function() {
		if(id) {
			loadForm("formId_wlryclfh_edit", "${ctx}/aqfh/wlryclfh/getById?id="+id, function(data) {
               
			});
			$("#gridId_wlryclfh_view_query").grid("reload","${ctx}/aqfh/wlryclfh/getWlryOrWlclById?id="+id+"&lx="+lx);
		}
	});
	

	

</script>
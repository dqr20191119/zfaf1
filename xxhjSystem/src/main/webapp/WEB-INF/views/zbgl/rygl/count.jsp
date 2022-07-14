<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.cesgroup.prison.common.facade.AuthSystemFacade"%>
<%@ page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />


<div style="height: 100%; margin: 0px 10px;">
	<cui:form id="formId_rygl_count_query">
		<table class="table">
			<tr><%--<th>单位 ：</th>
				<td><cui:combobox  id="cusNumber"  name="cusNumber"  data="combox_dw"  componentCls="form-control" onSelect="changeDw" showClose="true" ></cui:combobox></td>--%>
                <th>年度 ：</th>
                <td><cui:datepicker id="year"  name="year"  required="true" dateFormat="yyyy"></cui:datepicker>
                    <cui:button label="查询" componentCls="btn-primary" onClick="search"/>
                    <cui:button label="重置" onClick="reset"></cui:button>
                </td>
                <td>
                    <cui:button label="模板下载"  componentCls="btn-primary" onClick="downloadexcel"></cui:button>
                     <cui:button label="导入" componentCls="btn-primary" onClick="importExcel"></cui:button>
                     <cui:button label="导出" componentCls="btn-primary" onClick="exportExcel"></cui:button>
                </td>
            </tr>
		</table>
	</cui:form>
	<div style="height: 40px;">
		<cui:toolbar id="toolbarId_rygl_count" data="toolbar_rygl_count"></cui:toolbar>
	</div>	
			
	<cui:grid id="gridId_rygl_count" rownumbers="true" multiselect="true" width="auto" fitStyle="fill">
		<cui:gridCols>
			<cui:gridCol name="id" hidden="true" >id</cui:gridCol>
			<cui:gridCol name="cusNumber" formatter="convertCode" revertCode="true" formatoptions="{
				'data': combox_dw
				}"  align="center" >单位</cui:gridCol>
            <cui:gridCol name="name" align="center">姓名</cui:gridCol>
			<cui:gridCol name="yearCount" align="center">全年排班数</cui:gridCol>
			<cui:gridCol name="middleCount" align="center">中班值班数</cui:gridCol>
            <cui:gridCol name="nightCount" align="center">晚班值班数</cui:gridCol>
			<cui:gridCol name="workDayCount" align="center">工作日值班数</cui:gridCol>
            <cui:gridCol name="HolidaysCount" align="center">节假日值班数</cui:gridCol>
		</cui:gridCols>
		<cui:gridPager gridId="gridId_rygl_count"/>
	</cui:grid>
	
</div>

<script>
    var cusNumber = jsConst.CUS_NUMBER;
    var USER_LEVEL = jsConst.USER_LEVEL;
    var combobox_bm = <%=AuthSystemFacade.getAllChildrenOrgJsonInfoByOrgKey(null)%>;
	$.parseDone(function() {
	    /*if(USER_LEVEL=="2"){
	        $("#cusNumber").combobox("option","readonly","true");
            $("#cusNumber").combobox("setValue", cusNumber);
        }*/
	    $("#year")..datepicker("setValue", new Date().getFullYear());
        $("#gridId_rygl_count").grid("reload","${ctx}/zbgl/rygl/searchData?cusNumber=" + cusNumber);
       /* $.ajax({
            type : 'post',
            url : '${ctx}/common/authsystem/findAllChildrenOrgByJyKeyForCombobox',
            data: {
                "cusNumber" : cusNumber
            },
            dataType : 'json',
            success : function(data) {
                $("#deptNumber").combobox( "loadData", data);
            },
            error : function(XMLHttpRequest, textStatus, errorThrown) {
                $.loading("hide");
                $.message({message:"操作失败！", cls:"error"});
            }
        });*/
	});

	var combox_dw =<%= AuthSystemFacade.getHnAllJyJsonInfo()%>
	function search() {
		var formData = $("#formId_rygl_count_query").form("formData");
		$("#gridId_rygl_count").grid("option", "postData", formData);
		$("#gridId_rygl_count").grid("reload","${ctx}/zbgl/rygl/dutyCount?cusNumber=" + cusNumber);
	}
	
	function reset() {
		$("#formId_rygl_count_query").form("reset");
	}

</script>
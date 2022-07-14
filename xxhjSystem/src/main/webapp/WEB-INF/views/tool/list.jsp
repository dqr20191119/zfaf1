<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@page import="com.cesgroup.prison.code.utils.CodeFacade" %>
<%@taglib prefix="cui" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<body style="height: 100%">
<div style="height: 98%; margin: 5px">
    <cui:form id="formId_gb_query">
        <table class="table">
            <tr>
                <th>名称：</th>
                <td>
                        <cui:input name="name" componentCls="form-control"></cui:input>
				</td>
                <td>
                    <cui:button label="查询" onClick="gb_query"></cui:button>
                    <cui:button label="重置" onClick="gb_clear"></cui:button>
                </td>
            </tr>
        </table>
    </cui:form>
    <cui:grid id="gridId_securityCheck" fitStyle="fill" multiselect="true" colModel="gridId_securityCheck_colModelDate" >
        <cui:gridPager gridId="gridId_securityCheck"/>
    </cui:grid>
</div>
</body>

<script>

    $.parseDone(function () {
        var url = "${ctx}/tool/all/listAll.json";
        $("#gridId_securityCheck").grid("reload", url);
    });
    
    var gridId_securityCheck_colModelDate = [
    	{
            label: "id",
            name: "id",
            key: true,
            hidden: true
        },{
	        label: "编码",
	        name: "code",
	        align: "center",
	        width: 250
	    }, {
	        name: "name",
	        label: "名称",
	        align: "center",
	        width: 250
	    }, {
	        name: "type",
	        label: "填表人",
	        align: "center",
	        width: 250
	    }, {
	        name: "typeName",
	        label: "填表日期",
	        align: "center",
	        width: 250
	    }];
    
    function gb_query() {
        var formData = $("#formId_gb_query").form("formData");
        $("#gridId_securityCheck").grid("option", "postData", formData);
        $("#gridId_securityCheck").grid("reload");
        //关闭当前弹窗
        $("#dialogId_securityCheck").dialog("close");
    }

    function gb_clear() {
        $("#formId_gb_query").form("clear");
    }
</script>
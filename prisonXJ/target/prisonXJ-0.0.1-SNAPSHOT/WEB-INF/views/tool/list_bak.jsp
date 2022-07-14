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
                <th>工具名称：</th>
                <td>
                        <cui:input name="name" componentCls="form-control"></cui:input>

                <th>监狱名：</th>
                <td>
                        <cui:input name="prisonName" componentCls="form-control"></cui:input>
                <th>工具状态 ：</th>
                <td><cui:combobox id="eventState" name="status" data="comboboxData_sjzt"></cui:combobox></td>
                </td>
                <td>
                    <cui:button label="查询" onClick="gb_query"></cui:button>
                    <cui:button label="重置" onClick="gb_clear"></cui:button>
                </td>
            </tr>
        </table>
    </cui:form>
    <%-- <cui:toolbar id="toolbarId_securityCheck" data="toolbar_securityCheckDate"></cui:toolbar> --%>
    <cui:grid id="gridId_securityCheck" fitStyle="fill" multiselect="true" colModel="gridId_securityCheck_colModelDate">
        <cui:gridPager gridId="gridId_securityCheck"/>
    </cui:grid>
</div>
</body>
<script>
    // var jsConst = window.top.jsConst;
    // var cusNumber = jsConst.CUS_NUMBER;//监狱号
    var comboboxData_sjzt = [{value:'801', text: '正常'}, {value:'802', text: '出库'}, {
        value:'807',
        text: '解绑'
    }, {value:'808', text: '绑定'}, {value:'all', text: '全部'}];
    $.parseDone(function () {
        $("#eventState").combobox("setValue", "绑定");
        var url = "${ctx}/tool/all/listAll.json";
        $("#gridId_securityCheck").grid("reload", url);
    });

    var gridId_securityCheck_colModelDate = [{
        label: "id",
        name: "id",
        key: true,
        hidden: true
    }, {
        name: "code",
        label: "工具编号",
        align: "center",
        width: 250
    }, {
        name: "name",
        label: "工具名称",
        align: "center",
        width: 250
    }, {
        name: "itemUnit",
        label: "工具单位",
        align: "center",
        width: 250
    }, {
        name: "quantity",
        label: "工具数量",
        align: "center",
        width: 250
    }, {
        name: "prisonName",
        label: "监狱名称",
        align: "center",
        width: 250
    }, {
        name: "typeName",
        label: "工具类型名称",
        align: "center",
        width: 250
    }, {
        name: "statusName",
        label: "工具状态",
        align: "center",
        width: 250
    }];

    toolbar_securityCheckDate = [{
        "type": "button",
        "id": "btnId_add",
        "label": "增加",
        "onClick": "openDailog"
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
<%@page import="com.ces.prison.common.constants.GroupKeyConst" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.cesgroup.prison.common.facade.AuthSystemFacade" %>
<%@ page import="com.cesgroup.prison.code.utils.CodeFacade" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<div style="height: 100%; margin: 0px 10px;">
    <!-- 查询条件 -->
    <cui:form id="formId_recordStatisticManage_query">
        <table class="table">
            <tr>
                <th>单位名称：</th>
                <td>
                    <cui:combobox id="cusNumber" name="cusNumber" componentCls="form-control" data="combobox_jy"></cui:combobox>
                </td>
                <th>应急预案名称：</th>
                <td>
                    <cui:input id="preplanName" name="preplanName"></cui:input>
                </td>
                <td>
                    <cui:button label="查询" componentCls="btn-primary" onClick="search"/>
                    <cui:button label="重置" onClick="reset"></cui:button>
                </td>
            </tr>
        </table>
    </cui:form>
    <!-- 数据列表 -->
    <cui:grid id="gridId_recordStatisticManage" rownumName="序号" rownumWidth="80" rownumbers="true" multiselect="true" width="auto" fitStyle="fill"
              colModel="gridId_recordStatisticManage_colModelData">
        <cui:gridPager gridId="gridId_recordStatisticManage"/>
    </cui:grid>
    <!-- 应急处置 对话框 -->
    <cui:dialog id="dialogId_record_list" autoOpen="false" resizable="false" iframePanel="true" reLoadOnOpen="true" modal="true"
                autoDestroy="true">
    </cui:dialog>
</div>

<script>
    // JS常量类
    var jsConst = window.top.jsConst;
    // 监狱编号
    var cusNumber = jsConst.CUS_NUMBER;
    //从编码表中取监狱
    var combobox_jy = <%=AuthSystemFacade.getSjFjJyJsonInfoByLoginUser()%>;

    /**
     * 页面加载完成后执行
     */
    $.parseDone(function () {
        // 页面数据加载
        loadPage();
    });

    /**
     * 数据列表
     */
    var gridId_recordStatisticManage_colModelData = [{
        label: "单位名称",
        name: "cusNumber",
        width: "70",
        align: "center",
        formatter : "convertCode",  revertCode : true, formatoptions : { 'data':combobox_jy }
    }, {
        label: "应急预案名称",
        name: "preplanName",
        width: "70",
        align: "center"
    }, {
        label: "处置记录数量",
        name: "counts",
        width: "70",
        align: "center",
        formatter: "operateFormatter"
    }];

    /**
     * 页面数据加载
     */
    function loadPage() {
        var url = "${ctx}/emergency/handle/recordStatisticManage/queryWithPage";
        var params = {};
        if(cusNumber) {
            params["cusNumber"] = cusNumber;
            $("#formId_recordStatisticManage_query").find("#cusNumber").combobox("setValue", cusNumber);// 单位/监狱编号
        }
        $('#gridId_recordStatisticManage').grid('option', 'postData', params);
        $("#gridId_recordStatisticManage").grid("reload", url);
    }

    /**
     * 操作栏初始化
     */
    function operateFormatter(cellValue, options, rowObject) {
        var result = "<a href=\"javascript:void(0);\" style=\"color: #0000ff\" onClick= \"openRecordDialog('"+rowObject.cusNumber+"','" + rowObject.preplanName + "')\">" + cellValue + "</a>" ;

        return result;
    }

    /**
     /**
     * 处置记录列表
     *
     * @param cusNumber
     * @param groupName
     */
    function openRecordDialog(cusNumber, preplanName) {
        console.log("==================== emergency/handle/recordStatistic/list.jsp openRecordDialog start ====================");
        var url = "${ctx}/emergency/handle/recordStatisticManage/openRecordDialog?cusNumber=" + cusNumber + "&preplanName=" + new Base64().multiEncode(preplanName, 2);
        $("#dialogId_record_list").dialog({
            width: 1000,
            height: 600,
            title: preplanName + "处置记录",
            url: url
        });
        $("#dialogId_record_list").dialog("open");
        console.log("==================== emergency/handle/recordStatistic/list.jsp openRecordDialog end ====================");
    }

    /**
     * 条件查询
     */
    function search() {
        var formObj = $("#formId_recordStatisticManage_query");
        var cusNumber = formObj.find("#cusNumber").combobox("getValue");// 单位/监狱编号
        var preplanName = formObj.find("#preplanName").textbox("getValue");// 应急预案名称

        var url = "${ctx}/emergency/handle/recordStatisticManage/queryWithPage";
        var params = {};
        if (cusNumber) {
            params["cusNumber"] = cusNumber;
        }
        if (preplanName) {
            params["preplanName"] = preplanName;
        }
        $("#gridId_recordStatisticManage").grid("option", "postData", params);
        $("#gridId_recordStatisticManage").grid("reload");
    }

    /**
     * 重置查询条件
     */
    function reset() {
        var formObj = $("#formId_recordStatisticManage_query");
        formObj.find("#cusNumber").combobox("setValue", cusNumber);// 单位/监狱编号
        formObj.find("#preplanName").textbox("setValue", "");//应急预案名称
    }
</script>
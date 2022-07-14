<%@page import="com.ces.prison.common.constants.GroupKeyConst" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.cesgroup.prison.common.facade.AuthSystemFacade" %>
<%@ page import="com.cesgroup.prison.code.utils.CodeFacade" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<div style="height: 100%; margin: 0px 10px;">
    <!-- 查询条件 -->
    <cui:form id="formId_recordManage_query">
        <table class="table">
            <tr>
                <th>处置状态：</th>
                <td>
                    <cui:combobox id="recordStatus" name="recordStatus" componentCls="form-control" data="combobox_yjct_recordStatus"></cui:combobox>
                </td>
                <th>处置开始时间：</th>
                <td>
                    <cui:datepicker id="startTimeStart" name="startTimeStart" dateFormat="yyyy-MM-dd"></cui:datepicker>
                    至
                    <cui:datepicker id="startTimeEnd" name="startTimeEnd" dateFormat="yyyy-MM-dd"></cui:datepicker>
                </td>
                <td>
                    <cui:button label="查询" componentCls="btn-primary" onClick="search"/>
                    <cui:button label="重置" onClick="reset"></cui:button>

                    <!-- 单位编号 -->
                    <cui:input id="cusNumber" name="cusNumber" type="hidden"></cui:input>
                    <!-- 应急预案编号 -->
                    <cui:input id="preplanName" name="preplanName" type="hidden"></cui:input>
                </td>
            </tr>
        </table>
    </cui:form>
    <!-- 工具栏 -->
    <div style="height: 40px;">
        <cui:toolbar id="toolbarId_recordManage" data="toolbar_recordManage"></cui:toolbar>
    </div>
    <!-- 数据列表 -->
    <cui:grid id="gridId_recordManage" rownumName="序号" rownumWidth="80" rownumbers="true" multiselect="true" width="auto" fitStyle="fill"
              colModel="gridId_recordManage_colModelData">
        <cui:gridPager gridId="gridId_recordManage"/>
    </cui:grid>
</div>

<script>
    var c = {
        value : '',
        text : '请选择',
        selected : 'true'
    };
    //从编码表中取监狱
    var combobox_jy = <%=AuthSystemFacade.getSjFjJyJsonInfoByLoginUser()%>;
    // 应急处置记录状态
    var combobox_yjct_recordStatus = <%=CodeFacade.loadCode2Json("4.20.6")%>;
    combobox_yjct_recordStatus.unshift(c);
    combobox_yjct_recordStatus.push( {hidden: false, isValid: "1", jianpinLower: "sjwc", jianpinUpper: "SJWC", text: "事件处置完成，该预案强制结束", value:"4"});

    /**
     * 页面加载完成后执行
     */
    $.parseDone(function () {
        // 页面数据加载
        loadPage();
    });

    /**
     * 工具栏
     * @type {*[]}
     */
    var toolbar_recordManage = [{
        "type": "button",
        "id": "btnId_handle",
        "label": "继续处置",
        "onClick": "restartEmerHandle",
        "componentCls": "btn-primary"
    }];

    /**
     * 数据列表
     */
    var gridId_recordManage_colModelData = [{
        label: "id",
        name: "id",
        width: "70",
        hidden: true
    }, {
        label: "alarmRecordId",
        name: "alarmRecordId",
        width: "70",
        hidden: true
    }, {
        label: "preplanId",
        name: "preplanId",
        width: "70",
        hidden: true
    }, {
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
        label: "报警预案名称",
        name: "alarmPlanName",
        width: "70",
        align: "center"
    }, {
        label: "地点",
        name: "alarmAreaName",
        width: "70",
        align: "center"
    }, {
        label: "处置状态",
        name: "recordStatus",
        width: "85",
        align: "center",
        formatter : "convertCode",
        revertCode : true,
        formatoptions : {'data': combobox_yjct_recordStatus}
    }, {
        label: "处置开始时间",
        align: "center",
        name: "startTime",
        width: "85"
    }];

    /**
     * 继续开始应急处置
     * @param event
     * @param ui
     */
    function restartEmerHandle(event, ui) {
        var url = "";
        if (ui.id == "btnId_handle") {
            var selarrrow = $("#gridId_recordManage").grid("option", "selarrrow");
            if (selarrrow && selarrrow.length == 1) {
                var rowData = $("#gridId_recordManage").grid("getRowData", selarrrow[0]);
                // recordStatus：1-处置中，2-处置完成，3-已归档
                if(rowData.recordStatus != "1") {
                    $.message({message:"请选择处置中的记录！", cls:"waring"});
                    return;
                }
                // handleType：1-事件处置，2-事件演练，3-报警处置，4-处置记录处置(现在使用中的只有3和4，1和2类型已停用)
                url = "${ctx}/emergency/handle/recordManage/handlePanel?emerHandleRecordId=" + rowData.id + "&handleType=4";
            } else {
                $.message({message: "请选择一条记录！", cls: "waring"});
                return;
            }
        }

        // 关闭窗口
        $("#dialogId_record_list").dialog("close");
        $("#dialog").dialog("close");

        // 刷新右侧处置面板
        var panel = $("#layout1").layout("panel", "east");
        panel.panel("refresh", url);
    }

    /**
     * 页面数据加载
     */
    function loadPage() {
        var url = "${ctx}/emergency/handle/recordStatisticManage/queryRecordWithPage";
        var params = {};
        var cusNumber = "${cusNumber}";
        var preplanName = "${preplanName}";
        if(cusNumber) {
            params["cusNumber"] = cusNumber;
            $("#formId_recordManage_query").find("#cusNumber").textbox("setValue", cusNumber);//单位编号
        }
        if(preplanName) {
            params["preplanName"] = preplanName;
            $("#formId_recordManage_query").find("#preplanName").textbox("setValue", preplanName);//应急预案名称
        }
        $('#gridId_recordManage').grid('option', 'postData', params);
        $("#gridId_recordManage").grid("reload", url);
    }

    /**
     * 条件查询
     */
    function search() {
        var formObj = $("#formId_recordManage_query");
        var cusNumber = formObj.find("#cusNumber").textbox("getValue");// 单位编号
        var preplanName = formObj.find("#preplanName").textbox("getValue");// 应急预案名称
        var recordStatus = formObj.find("#recordStatus").combobox("getValue");// 应急处置状态
        var startTimeStart = formObj.find("#startTimeStart").datepicker("getValue");// 处置时间-开始
        var startTimeEnd = formObj.find("#startTimeEnd").datepicker("getValue");// 处置时间-结束

        var url = "${ctx}/emergency/handle/recordManage/queryWithPage";
        var params = {};
        if (cusNumber) {
            params["cusNumber"] = cusNumber;
        }
        if (preplanName) {
            params["preplanName"] = preplanName;
        }
        if (recordStatus) {
            params["recordStatus"] = recordStatus;
        }
        if (startTimeStart) {
            params["startTimeStart"] = startTimeStart;
        }
        if (startTimeEnd) {
            params["startTimeEnd"] = startTimeEnd;
        }
        $("#gridId_recordManage").grid("option", "postData", params);
        $("#gridId_recordManage").grid("reload");
    }

    /**
     * 重置查询条件
     */
    function reset() {
        var formObj = $("#formId_recordManage_query");
        formObj.find("#recordStatus").combobox("setValue", "");// 处置状态
        formObj.find("#startTimeStart").datepicker("setValue", "");// 处置开始时间-开始
        formObj.find("#startTimeEnd").datepicker("setValue", "");// 处置开始时间-结束
    }
</script>
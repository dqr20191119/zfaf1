<%@page import="com.ces.prison.common.constants.GroupKeyConst" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.cesgroup.prison.common.facade.AuthSystemFacade" %>
<%@ page import="com.cesgroup.prison.code.utils.CodeFacade" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<div style="height: 100%; margin: 0px 10px;">
    <script src="${ctx}/static/js/alarm/alarm.js"></script>
    <!-- 查询条件 -->
    <cui:form id="formId_recordManage_query">
        <table class="table">
            <tr>
                <th>应急预案名称：</th>
                <td>
                    <cui:input id="preplanName" name="preplanName" width="150"></cui:input>
                </td>
                <th>处置状态：</th>
                <td>
                    <cui:combobox id="recordStatus" name="recordStatus" componentCls="form-control" data="combobox_yjct_recordStatus" width="150"></cui:combobox>
                </td>
                <th>单位名称：</th>
                <td>
                    <cui:combobox id="cusNumber" name="cusNumber" componentCls="form-control" data="combobox_jy" width="150"></cui:combobox>
                </td>
                <td>
                    <cui:button label="查询" componentCls="btn-primary" onClick="search"/>
                    <cui:button label="重置" onClick="reset"></cui:button>
                </td>
            </tr>
            <tr>
                <th>报警预案名称：</th>
                <td>
                    <cui:input id="alarmPlanName" name="alarmPlanName" width="150"></cui:input>
                </td>
                <th>处置开始时间：</th>
                <td>
                    <cui:datepicker id="startTimeStart" name="startTimeStart" dateFormat="yyyy-MM-dd" width="150"></cui:datepicker>
                </td>
                <th align="center">至</th>
                <td>
                    <cui:datepicker id="startTimeEnd" name="startTimeEnd" dateFormat="yyyy-MM-dd" width="150"></cui:datepicker>
                </td>

            </tr>
        </table>
    </cui:form>
    <!-- 工具栏 -->
    <div style="margin-bottom: 3px;">
        <cui:toolbar id="toolbarId_recordManage" data="toolbar_recordManage"></cui:toolbar>
    </div>
    <!-- 数据列表 -->
    <cui:grid id="gridId_recordManage" rownumName="序号" rownumWidth="80" rownumbers="true" multiselect="true" width="auto" fitStyle="fill"
              colModel="gridId_recordManage_colModelData">
        <cui:gridPager gridId="gridId_recordManage"/>
    </cui:grid>
    <!-- 应急处置 对话框 -->
    <cui:dialog id="dialogId_recordManage" autoOpen="false" resizable="false" iframePanel="true" reLoadOnOpen="true" modal="true"
                autoDestroy="true">
    </cui:dialog>
    <cui:dialog id="dialogId_recordManage_process" autoOpen="false" resizable="false" iframePanel="true" reLoadOnOpen="true" modal="true"
                autoDestroy="true">
    </cui:dialog>
</div>

<script>
    var c = {
        value : '',
        text : '请选择',
        selected : 'true'
    };
    // JS常量类
    var jsConst = window.top.jsConst;
    // 监狱编号
    var cusNumber = jsConst.CUS_NUMBER;
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
    },
    //     {
    //     "type": "button",
    //     "id": "btnId_eventReport",
    //     "label": "事件报告",
    //     "onClick": "openDialog",
    //     "componentCls": "btn-primary"
    // },
    //     {
    //     "type": "button",
    //     "id": "btnId_reviewHandleStep",
    //     "label": "查看处置过程",
    //     "onClick": "openDialog",
    //     "componentCls": "btn-primary"
    // },
        {
        "type": "button",
        "id": "btnId_reviewEmerReport",
        "label": "查看处置报告",
        "onClick": "openDialog",
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
        width: "100",
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
        $("#dialog").dialog("close");

        // 刷新右侧处置面板
        var panel = $("#layout1").layout("panel", "east");
        panel.panel("refresh", url);
    }

    /**
     * 打开对话框
     * @param event
     * @param ui
     */
    function openDialog(event, ui) {
        var w = null;
        var h = null;
        var url = "";
        var buttons = null;
        var selarrrow = $("#gridId_recordManage").grid("option", "selarrrow");
        if (!selarrrow || selarrrow.length != 1) {
            $.message({message: "请选择一条记录！", cls: "waring"});
            return;
        }
        var rowData = $("#gridId_recordManage").grid("getRowData", selarrrow[0]);
        switch (ui.id) {
            // 事件报告
            <%--case "btnId_eventReport" : {--%>
            <%--    // recordStatus：1-处置中，2-处置完成，3-已归档--%>
            <%--    if(rowData.recordStatus == "1") {--%>
            <%--        $.message({message:"请选择处置完成或已归档的记录！", cls:"waring"});--%>
            <%--        return;--%>
            <%--    }--%>
            <%--    // 窗口高度、宽度--%>
            <%--    w = 1050;--%>
            <%--    h = 600;--%>
            <%--    // 窗口URL--%>
            <%--    url = "${ctx}/emergency/handle/recordManage/eventReport?emerHandleRecordId=" + rowData.id;--%>
            <%--    // 窗口按钮组--%>
            <%--    buttons = [{--%>
            <%--        text: "保存",--%>
            <%--        id: "btnId_save",--%>
            <%--        width: 50,--%>
            <%--        componentCls:"btn-primary",--%>
            <%--        click: function () {--%>
            <%--            saveOrUpdateEventReport();--%>
            <%--        }--%>
            <%--    }, {--%>
            <%--        text: "关闭",--%>
            <%--        id: "btnId_cancel",--%>
            <%--        width: 50,--%>
            <%--        componentCls:"btn-primary",--%>
            <%--        click: function () {--%>
            <%--            $("#dialogId_recordManage").dialog("close");--%>
            <%--        }--%>
            <%--    }];--%>
            <%--    break;--%>
            <%--}--%>
            // 查看处置过程
            <%--case "btnId_reviewHandleStep" : {--%>
            <%--    // 窗口高度、宽度--%>
            <%--    w = 1065;--%>
            <%--    h = 600;--%>
            <%--    url = "${ctx}/emergency/handle/recordManage/handleStepReview?emerHandleRecordId=" + rowData.id + "&preplanId=" + rowData.preplanId + "&alarmRecordId=" + rowData.alarmRecordId;--%>
            <%--    break;--%>
            <%--}--%>
            // 查看预案报告
            case "btnId_reviewEmerReport" : {
                // recordStatus：1-处置中，2-处置完成，3-已归档
                if(rowData.recordStatus == "1") {
                    $.message({message:"请选择处置完成或已归档的记录！", cls:"waring"});
                    return;
                }
                // 窗口高度、宽度
                w = 1000;
                h = 600;
                // 窗口URL
                url = "${ctx}/emergency/handle/recordManage/reviewEmerReport?emerHandleRecordId=" + rowData.id + "&alarmRecordId=" + rowData.alarmRecordId;
                // 窗口按钮组
                buttons = [{
                    text: "保存",
                    id: "btnId_saveExp",
                    componentCls:"btn-primary",
                    width: 50,
                    click: function () {
                        var validFlag = $("#formId_review_report").form("valid");
                        if (!validFlag) {
                            return;
                        }
                        var url = "${ctx}/emergency/handle/recordManage/saveOrUpdateEventReport.json";
                        var formData = $("#formId_review_report").form("formData");
                        formData.emerHandleRecordId = rowData.id;
                        formData.rydjbAffixIds = "111111111111";// $("#uploaderId_eventReportRydjb_affixIds").val();
                        $.loading({text: "正在处理中，请稍后..."});

                        var callBack = function (data) {
                            if (data.success) {
                                $.loading("hide");
                                $("#dialogId_recordManage").dialog("close");
                                $.message({message: "操作成功！", cls: "success"});

                                // 刷新列表
                                search();
                            } else {
                                $.loading("hide");
                                $.messageQueue({message: data.msg, cls: "warning", iframePanel: true, type: "info"});
                            }
                        };
                        ajaxTodo(url, formData, callBack);
                    }
                },{
                    text: "查看附件",
                    id: "btnId_viewAffix",
                    componentCls:"btn-primary",
                    width: 100,
                    click: function () {
                        var wp = 1065;
                        var hp = 600;
                        var url_p = "${ctx}/emergency/handle/recordManage/eventReport?emerHandleRecordId=" + rowData.id;
                        $("#dialogId_recordManage_process").dialog({
                            width: wp,
                            height: hp,
                            title: "查看附件",
                            url: url_p
                        });
                        $("#dialogId_recordManage_process").dialog("open");
                    }
                },{
                    text: "查看录像",
                    id: "btnId_watchVideo",
                    componentCls:"btn-primary",
                    width: 100,
                    click: function () {
                        var alarmRecordId = $("#divId_hiddenAttr").find("input[id='alarmRecordId']").val();
                        var alarmTime = formatToDate($("#alarmTime").html());
                        var stime = new Date(alarmTime.getTime() - 1000 * 30).Format("yyyy-MM-dd hh:mm:ss");
                        var etime = new Date(alarmTime.getTime() + 1000 * 30).Format("yyyy-MM-dd hh:mm:ss");
                        var params = {};
                        params["id"] = alarmRecordId;
                        params["itemId"] = "2";//摄像机
                        var url = "${ctx}/alarm/queryPlanDevice.json";
                        var callBack = function(data) {
                            if(data.success) {
                                var cameraList = new Array();
                                for(var i=0; i<data.obj.planDeviceList.length; i++) {
                                    var camera = {
                                        cameraId: data.obj.planDeviceList[i].PDR_DVC_IDNTY,
                                        cameraName: data.obj.planDeviceList[i].PDR_DVC_NAME
                                    };
                                    cameraList.push(camera);
                                }
                                plackbackCameraBetweenTimes(cameraList, stime, etime);
                            }
                        };
                        ajaxTodo(url, params, callBack);
                    }
                },{
                    text: "详细过程",
                    id: "btnId_watchProcess",
                    componentCls:"btn-primary",
                    width: 100,
                    click: function () {
                        var wp = 1065;
                        var hp = 600;
                        var url_p = "${ctx}/emergency/handle/recordManage/handleStepReview?emerHandleRecordId=" + rowData.id + "&preplanId=" + rowData.preplanId + "&alarmRecordId=" + rowData.alarmRecordId;
                        $("#dialogId_recordManage_process").dialog({
                            width: wp,
                            height: hp,
                            title: "详细过程",
                            url: url_p
                        });
                        $("#dialogId_recordManage_process").dialog("open");
                    }
                },{
                    text: "导出",
                    id: "btnId_export",
                    componentCls:"btn-primary",
                    width: 50,
                    click: function () {
                        window.location.href = "${ctx}/emergency/handle/recordManage/exprotWord?recordId=" + rowData.id+"&alarmRecordId="+rowData.alarmRecordId;

                    }
                }, {
                    text: "关闭",
                    id: "btnId_cancel",
                    componentCls:"btn-primary",
                    width: 50,
                    click: function () {
                        $("#dialogId_recordManage").dialog("close");
                    }
                }];
                break;
            }
        }
        if(w == null || w == '' || w == 0) {
            w = 1000;
        }
        if(h == null || h == '' || h == 0) {
            h = 600;
        }
        $("#dialogId_recordManage").dialog({
            width: w,
            height: h,
            title: ui.label,
            url: url,
            buttons : buttons
        });
        $("#dialogId_recordManage").dialog("open");
    }

    /**
     * 页面数据加载
     */
    function loadPage() {
        var url = "${ctx}/emergency/handle/recordManage/queryWithPage";
        var params = {};
        if(cusNumber) {
            params["cusNumber"] = cusNumber;
            $("#formId_recordManage_query").find("#cusNumber").combobox("setValue", cusNumber);//单位编号
        }
        $('#gridId_recordManage').grid('option', 'postData', params);
        $("#gridId_recordManage").grid("reload", url);
    }

    /**
     * 条件查询
     */
    function search() {
        var formObj = $("#formId_recordManage_query");
        var preplanName = formObj.find("#preplanName").textbox("getValue");// 应急预案名称
        var recordStatus = formObj.find("#recordStatus").combobox("getValue");// 应急处置状态
        var cusNumber = formObj.find("#cusNumber").combobox("getValue");// 单位编号
        var alarmPlanName = formObj.find("#alarmPlanName").textbox("getValue");// 报警预案名称
        var startTimeStart = formObj.find("#startTimeStart").datepicker("getValue");// 处置时间-开始
        var startTimeEnd = formObj.find("#startTimeEnd").datepicker("getValue");// 处置时间-结束

        var url = "${ctx}/emergency/handle/recordManage/queryWithPage";
        var params = {};
        if (preplanName) {
            params["preplanName"] = preplanName;
        }
        if (recordStatus) {
            params["recordStatus"] = recordStatus;
        }
        if (cusNumber) {
            params["cusNumber"] = cusNumber;
        }
        if (alarmPlanName) {
            params["alarmPlanName"] = alarmPlanName;
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
        formObj.find("#preplanName").textbox("setValue", "");// 应急预案名称
        formObj.find("#recordStatus").combobox("setValue", "");// 处置状态
        formObj.find("#cusNumber").combobox("setValue", cusNumber);// 单位编号
        formObj.find("#alarmPlanName").textbox("setValue", "");// 报警预案名称
        formObj.find("#startTimeStart").datepicker("setValue", "");// 处置开始时间-开始
        formObj.find("#startTimeEnd").datepicker("setValue", "");// 处置开始时间-结束
    }

    /**
     * 保存或更新事件记录
     */
    function saveOrUpdateEventReport() {
        var validFlag = $("#formId_event_report").form("valid");
        if (!validFlag) {
            return;
        }

        var url = "${ctx}/emergency/handle/recordManage/saveOrUpdateEventReport.json";
        var formData = $("#formId_event_report").form("formData");
        formData.emerHandleRecordId = $("#formId_event_report").find("#emerHandleRecordId").textbox("getValue");
        formData.rydjbAffixIds = "111111111111";// $("#uploaderId_eventReportRydjb_affixIds").val();

        $.loading({text: "正在处理中，请稍后..."});

        var callBack = function (data) {
            if (data.success) {
                $.loading("hide");
                $("#dialogId_recordManage").dialog("close");
                $.message({message: "操作成功！", cls: "success"});

                // 刷新列表
                search();
            } else {
                $.loading("hide");
                $.messageQueue({message: data.msg, cls: "warning", iframePanel: true, type: "info"});
            }
        };
        ajaxTodo(url, formData, callBack);
    }


</script>
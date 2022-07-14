<%@page import="com.ces.prison.common.constants.GroupKeyConst" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.cesgroup.prison.common.facade.AuthSystemFacade" %>
<%@ page import="com.cesgroup.prison.code.utils.CodeFacade" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<link href="${ctx}/static/emergency/style/style.css" rel="stylesheet" type="text/css"/>

<body>
<div id="wrap">
    <div class="header">
        <cui:form id="formId_preplanStart_query">
            <table class="table">
                <tr>
                    <th>预案名称：</th>
                    <td>
                        <cui:input id="preplanName" name="preplanName"></cui:input>
                    </td>
                    <td>
                        <cui:button label="查询" componentCls="btn-primary" onClick="search"/>
                        <cui:button label="重置" onClick="reset"></cui:button>

                        <!-- 隐藏属性 -->
                        <cui:input id="alarmPlanId" name="alarmPlanId" type="hidden"></cui:input>
                        <cui:input id="alarmRecordId" name="alarmRecordId" type="hidden"></cui:input>
                    </td>
                </tr>
            </table>
        </cui:form>
    </div>
    <div class="main">
        <ul></ul>
    </div>
</div>

<!-- 应急预案详情对话框 -->
<cui:dialog id="dialogId_preplan_view" autoOpen="false" resizable="false" iframePanel="true" reLoadOnOpen="true" modal="true"
            autoDestroy="true" buttons="buttons_preplan_view">
</cui:dialog>

<script>
    /**
     * 页面加载完成后执行
     */
    $.parseDone(function () {
        // 加载应急预案数据
        loadPreplan();
    });

    /**
     * 新增、更新对话框按钮
     */
    var buttons_preplan_view = [{
        text: "开始处置",
        id: "btnId_save",
        width: 70,
        click: function () {
            beforeStartPreplan();
        }
    }, {
        text: "取消",
        id: "btnId_cancel",
        width: 70,
        click: function () {
            $("#dialogId_preplan_view").dialog("close");
        }
    }];

    /**
     * 页面数据加载
     */
    function loadPreplan() {
        var alarmPlanId = "${alarmPlanId}";
        var alarmRecordId = "${alarmRecordId}";
        if (alarmRecordId) {
            $("#formId_preplanStart_query").find("#alarmPlanId").textbox("setValue", alarmPlanId);
            $("#formId_preplanStart_query").find("#alarmRecordId").textbox("setValue", alarmRecordId);
        }
        search();
    }

    /**
     * 条件查询
     */
    function search() {
        var formObj = $("#formId_preplanStart_query");

        var alarmRecordId = formObj.find("#alarmRecordId").textbox("getValue");// 报警记录编号
        var preplanName = formObj.find("#preplanName").textbox("getValue");// 预案名称

        var params = {};
        if (alarmRecordId) {
            params["alarmRecordId"] = alarmRecordId;
        }
        if (preplanName) {
            params["preplanName"] = preplanName;
        }

        $.loading({text: "正在处理中，请稍后..."});
        var url = "${ctx}/emergency/preplanStart/queryByCondition";
        var callBack = function (data) {
            if (data.success) {
                $.loading("hide");

                // 填充预案数据
                fillPreplanInfo(data.obj);
            } else {
                $.loading("hide");
                $.messageQueue({message: data.msg, cls: "warning", iframePanel: true, type: "info"});
            }
        };
        ajaxTodo(url, params, callBack);
    }

    /**
     * 重置查询条件
     */
    function reset() {
        var formObj = $("#formId_preplanStart_query");
        formObj.find("#preplanName").textbox("setValue", "");
    }

    /**
     * 填充应急预案
     * @param preplanList
     */
    function fillPreplanInfo(preplanList) {
        // console.log("fillPreplanInfo preplanList = " + JSON.stringify(preplanList));
        // 应急预案容器
        var preplanContainer = $("div[id='wrap']").find("div[class='main']").find("ul:eq(0)");
        // 应急预案模板
        var preplanTemplate = '<li><div class="content-item"><span class="item-text"></span></div></li>';

        // 清空工作组
        preplanContainer.empty();

        // 填充新的待关联工作组
        if(preplanList != null && preplanList.length > 0) {
            var timers = [];
            $.each(preplanList, function (index, obj) {
                // 工作组填充
                var preplanTemplateClone = $(preplanTemplate).clone();
                preplanTemplateClone.attr({
                    id : obj.preplanId,
                    name : obj.preplanName
                }).unbind('dblclick').bind('dblclick', function() {
                    event.stopPropagation();
                    if(timers[index]) {
                        clearTimeout(timers[index]);
                    }

                    // 应急预案名称双击事件
                    preplanDblclick(this);
                }).unbind('click').bind('click', function() {
                    var liObj = this;
                    if(timers[index]) {
                        clearTimeout(timers[index]);
                    }

                    // 应急预案名称单击事件
                    timers[index] = setTimeout(function () {
                        preplanClick(liObj);
                    }, 500);
                });
                preplanTemplateClone.find("div[class='content-item']").find("span[class='item-text']").text(obj.preplanName);
                preplanContainer.append(preplanTemplateClone);
            });
        }
    }

    /**
     * 预案双击事件
     */
    function preplanDblclick(obj) {
        // 应急预案编号
        var preplanId = $(obj).attr("id");

        // 首先，判断预案是否存在
        var params = {};
        if (preplanId) {
            params["id"] = preplanId;
        }

        var url = "${ctx}/emergency/preplanManage/judgeById";
        var callBack = function (data) {
            if (data.success) {
                // 开始处置
                startPreplan(preplanId);
            } else {
                $.messageQueue({message: data.msg, cls: "warning", iframePanel: true, type: "info"});
            }
        };
        ajaxTodo(url, params, callBack);
    }

    /**
     * 预案单击事件
     */
    function preplanClick(obj) {
        // 应急预案编号
        var preplanId = $(obj).attr("id");

        // 首先，判断预案是否存在
        var params = {};
        if (preplanId) {
            params["id"] = preplanId;
        }

        var url = "${ctx}/emergency/preplanManage/judgeById";
        var callBack = function (data) {
            if (data.success) {
                // 展示预案基础信息
                openDailog(preplanId);
            } else {
                $.messageQueue({message: data.msg, cls: "warning", iframePanel: true, type: "info"});
            }
        };
        ajaxTodo(url, params, callBack);
    }

    /**
     * 打开对话框
     * @param event
     * @param ui
     */
    function openDailog(preplanId) {
        // 报警记录编号
        var alarmRecordId = $("#formId_preplanStart_query").find("#alarmRecordId").textbox("getValue");

        var url = "${ctx}/emergency/preplanStart/toView?preplanId=" + preplanId + "&alarmRecordId=" + alarmRecordId;
        $("#dialogId_preplan_view").dialog({
            width: 1065,
            height: 600,
            title: "预览",
            url: url
        });
        $("#dialogId_preplan_view").dialog("open");
    }

    /**
     * 启动预案前
     */
    function beforeStartPreplan() {
        // 应急预案编号
        var preplanId = $("#formId_preplan_view").find("input[id='preplanId']").val();
        // 首先，判断预案是否存在
        var params = {};
        if (preplanId) {
            params["id"] = preplanId;
        }

        var url = "${ctx}/emergency/preplanManage/judgeById";
        var callBack = function (data) {
            if (data.success) {
                // 展示预案基础信息
                startPreplan(preplanId);
            } else {
                $.messageQueue({message: data.msg, cls: "warning", iframePanel: true, type: "info"});
            }
        };
        ajaxTodo(url, params, callBack);
    }

    /**
     * 直接启动应急预案
     */
    function startPreplan(preplanId) {
        // 报警预案编号
        var alarmPlanId = $("#formId_preplanStart_query").find("#alarmPlanId").textbox("getValue");
        // 报警记录编号
        var alarmRecordId = $("#formId_preplanStart_query").find("#alarmRecordId").textbox("getValue");

        // 新增报警处置记录
        var url = "${ctx}/emergency/handle/recordManage/saveOrUpdate.json";
        var params = {};
        if(alarmPlanId) {
            params["alarmPlanId"] = alarmPlanId;
        }
        if(alarmRecordId) {
            params["alarmRecordId"] = alarmRecordId;
        }
        if(preplanId) {
            params["preplanId"] = preplanId;
        }
        // $.loading({text:"正在处理中，请稍后..."});
        var callBack = function (data) {
            console.log("preplanStart index.jsp startPreplan data = " + JSON.stringify(data));
            if (data.success) {
                // $.loading("hide");
                // 关闭预案预览窗口
                $("#dialogId_preplan_view").dialog("close");
                $("#dialogId_viewEmerPreplan").dialog("close");

                // 打开报警处置窗口
                startEmerHandle(data.obj);
            } else {
                console.log("preplanStart index.jsp startPreplan data failed");
                // $.loading("hide");
                $.messageQueue({message: data.msg, cls: "warning", iframePanel: true, type: "info"});
            }
        };
        ajaxTodo(url, params, callBack);
    }
</script>

<%@page import="com.cesgroup.prison.common.bean.user.UserBean" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.cesgroup.prison.common.facade.AuthSystemFacade" %>
<%@ page import="com.cesgroup.prison.code.utils.CodeFacade" %>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<link href="${ctx}/static/emergency/style/style.css" rel="stylesheet" type="text/css"/>

<cui:tabs id="tabsId_preplan_view" heightStyle="fill">
    <ul>
        <li><a href="#tabsId_preplan_view_1">基本信息</a></li>
        <li><a href="#tabsId_preplan_view_2">处置流程</a></li>
    </ul>
    <div id="tabsId_preplan_view_1">
        <cui:form id="formId_preplan_view" heightStyle="fill">
            <table class="table">
                <tr>
                    <th>预案名称：</th>
                    <td><span id="preplanName" name="preplanName"></span></td>
                </tr>
                <tr>
                    <th>报警地点：</th>
                    <td><span id="alarmAddress" name="alarmAddress"></span></td>
                </tr>
                <tr>
                    <th>报警设备：</th>
                    <td><span id="alarmDevice" name="alarmDevice"></span></td>
                </tr>
                <tr>
                    <th>报警时间：</th>
                    <td><span id="alarmTime" name="alarmTime"></span></td>
                </tr>
            </table>
            <!-- 应急预案编号 -->
            <input id="preplanId" type="hidden" value="${preplanId}" />
            <!-- 报警记录编号 -->
            <input id="alarmRecordId" type="hidden" value="${alarmRecordId}" />
        </cui:form>
    </div>
    <div id="tabsId_preplan_view_2">
        <div class="left_side">
            <div class="title"><span>处置流程</span></div>
            <div class="content">
                <ul class="leftnav"></ul>
            </div>
        </div>
        <div class="middle_side">
            <div class="title"><span>关联工作组</span></div>
            <div class="content">
                <ul class="middlenav"></ul>
            </div>
        </div>
        <div class="right_side">
            <div class="title1"><span>工作组任务</span></div>
            <div class="content1">
                <span></span>
            </div>
            <div class="title2"><span>工作组成员</span></div>
            <div class="content2">
                <%--<table class="member-list"></table>--%>
                <!-- 工具栏 -->
                <div id="div_toolbarId_groupMember" style="height: 40px; display: none;">
                    <%--<cui:toolbar id="toolbarId_groupMember" data="toolbar_groupMember"></cui:toolbar>--%>
                    <!-- 隐藏属性 -->
                    <cui:input id="groupId" name="groupId" type="hidden"></cui:input>
                </div>
                <!-- 数据列表 -->
                <cui:grid id="gridId_groupMember" rownumbers="true" multiselect="true" width="auto" fitStyle="fill"
                          colModel="gridId_groupMember_colModelData">
                    <cui:gridPager gridId="gridId_groupMember"/>
                </cui:grid>
            </div>
        </div>
    </div>
</cui:tabs>

<script>
    $.parseDone(function() {
        // tab页切换
        $("#tabsId_preplan_view").tabs({
            onActivate: function(event, ui) {
                var index = $("#tabsId_preplan_view").tabs("option", "active");
                switch (parseInt(index)) {
                    case 1 : {
                        initStep();
                        break;
                    }
                    default : {
                        break;
                    }
                }
            }
        });
        // 初始化预案信息
        initPreplan();

        // 初始化报警记录信息
        initAlarmRecord();

        // 展示处置流程切换页
        $("#tabsId_preplan_view").tabs("option", "active", "tabsId_preplan_view_2");
    });

    /**
     * 工具栏
     * @type {*[]}
     */
    /*var toolbar_groupMember = [{
        "type": "button",
        "id": "btnId_nearby",
        "label": "附近民警",
        "onClick": "openDailog",
        "componentCls": "btn-primary"
    }];*/

    /**
     * 数据列表
     */
    var gridId_groupMember_colModelData = [{
        label: "id",
        name: "id",
        width: "70",
        hidden: true
    }, {
        label: "姓名",
        name: "memberName",
        width: "85",
        align: "left"
    }, {
        label: "号码",
        name: "callNo",
        width: "85",
        align: "left"
    }, {
        label: "呼叫结果",
        name: "callResult",
        width: "100",
        align: "left"
    }, {
        label: "更新时间",
        align: "center",
        name: "updateTime",
        width: "85"
    }];

    /**
     * 初始化应急预案信息
     */
    function initPreplan() {
        // 应急预案编号
        var preplanId = $("#formId_preplan_view").find("input[id='preplanId']").val();

        var url = "${ctx}/emergency/preplanManage/queryById.json";
        var params = {};
        if(preplanId) {
            params["id"] = preplanId;
        }

        var callBack = function(data) {
            if (data.success) {
                // 填充应急预案信息
                fillPreplan(data.obj);
            } else {
                $.messageQueue({ message : data.msg, cls : "warning", iframePanel : true, type : "info" });
            }
        };
        ajaxTodo(url, params, callBack);
    }

    /**
     * 初始化报警记录信息
     */
    function initAlarmRecord() {
        // 报警记录编号
        var alarmRecordId = $("#formId_preplan_view").find("input[id='alarmRecordId']").val();

        var url = "${ctx}/alarm/findById.json";
        var params = {};
        if(alarmRecordId) {
            params["id"] = alarmRecordId;
        }

        var callBack = function(data) {
            if (data.success) {
                // 填充报警记录信息
                fillAlarmRecord(data.obj);
            } else {
                $.messageQueue({ message : data.msg, cls : "warning", iframePanel : true, type : "info" });
            }
        };
        ajaxTodo(url, params, callBack);
    }

    /**
     * 初始化应急预案处置步骤
     */
    function initStep() {
        // 应急预案编号
        var preplanId = $("#formId_preplan_view").find("input[id='preplanId']").val();

        var url = "${ctx}/emergency/stepManage/queryByPreplanId.json";
        var params = {};
        if(preplanId) {
            params["preplanId"] = preplanId;
        }

        var callBack = function(data) {
            if (data.success) {
                // 填充应急预案处置步骤
                fillStep(data.obj);
            } else {
                $.messageQueue({ message : data.msg, cls : "warning", iframePanel : true, type : "info" });
            }
        };
        ajaxTodo(url, params, callBack);
    }

    /**
     * 填充处置步骤
     */
    function fillStep(stepList) {
        // 处置步骤容器
        var stepContainer = $("div[id='tabsId_preplan_view_2']").find("div[class='left_side']").find("div[class='content']").find("ul:eq(0)");
        // 处置步骤模板
        var stepTemplate = '<li></li>'

        // 清空处置步骤
        stepContainer.empty();

        // 填充处置步骤
        if(stepList != null && stepList.length > 0) {
            var timers = [];
            $.each(stepList, function (index, obj) {
                var stepTemplateClone = $(stepTemplate).clone();
                stepTemplateClone.attr({
                    id : obj.id,
                    name : obj.id,
                    value : obj.id
                }).unbind('click').bind('click', function() {
                    $(this).addClass("li-select").siblings().removeClass("li-select");

                    var liObj = this;
                    if(timers[index]) {
                        clearTimeout(timers[index]);
                    }

                    // 应急工作组名称单击事件
                    timers[index] = setTimeout(function () {
                        initGroup(liObj);
                    }, 500);
                });

                // 显示名称赋值
                stepTemplateClone.html(obj.name);

                stepContainer.append(stepTemplateClone);
            });
            stepContainer.find("li:eq(0)").click();
        }
    }

    /**
     * 初始化应急工作组
     */
    function initGroup(obj) {
        // 应急预案编号
        var preplanId = $("#formId_preplan_view").find("input[id='preplanId']").val();
        // 处置步骤编号
        var stepId = $(obj).attr("value");

        var url = "${ctx}/emergency/stepGroupManage/queryCheckedGroup.json";
        var params = {};
        if(preplanId) {
            params["preplanId"] = preplanId;
        }
        if(stepId) {
            params["stepId"] = stepId;
        }

        var callBack = function(data) {
            if (data.success) {
                // 将待关联工作组数据存入uncheckedGroupList
                if(data.obj != null && data.obj.length > 0) {
                    // 填充待关联工作组
                    fillGroup(data.obj);
                }
            } else {
                $.messageQueue({ message : data.msg, cls : "warning", iframePanel : true, type : "info" });
            }
        };
        ajaxTodo(url, params, callBack);
    }

    /**
     * 填充处置步骤
     */
    function fillGroup(groupList) {
        // 工作组容器
        var groupContainer = $("div[id='tabsId_preplan_view_2']").find("div[class='middle_side']").find("div[class='content']").find("ul:eq(0)");
        // 工作组模板
        var groupTemplate = '<li></li>'

        // 清空工作组
        groupContainer.empty();

        // 填充新的待关联工作组
        if(groupList != null && groupList.length > 0) {
            var timers = [];
            $.each(groupList, function (index, obj) {
                // 工作组填充
                var groupTemplateClone = $(groupTemplate).clone();
                groupTemplateClone.attr({
                    id : obj.id,
                    name : obj.id,
                    value : obj.id,
                    notice : obj.notice
                }).unbind('click').bind('click', function() {
                    $(this).addClass("li-select").siblings().removeClass("li-select");
                    var liObj = this;
                    if(timers[index]) {
                        clearTimeout(timers[index]);
                    }

                    // 应急工作组名称单击事件
                    timers[index] = setTimeout(function () {
                        loadGroupMemberInfo(liObj);
                    }, 500);
                });

                // 显示名称赋值
                groupTemplateClone.html(obj.name);

                groupContainer.append(groupTemplateClone);
            });
            groupContainer.find("li:eq(0)").click();
        }
    }

    /**
     * 加载工作组成员数据
     * @param obj
     */
    function loadGroupMemberInfo(obj) {
        var groupId = $(obj).attr("value");
        var notice = $(obj).attr("notice");
        $("div[id='tabsId_preplan_view_2']").find("div[class='right_side']").find("div[class='content1']").find("span:eq(0)").empty();
        $("div[id='tabsId_preplan_view_2']").find("div[class='right_side']").find("div[class='content1']").find("span:eq(0)").html(notice);

        // 分页查询
        var url = "${ctx}/emergency/groupMemberManage/queryWithPage";
        var params = {};

        // 初始化查询条件
        if (groupId) {
            params["groupId"] = groupId;
            $("div[id='tabsId_preplan_view_2']").find("div[class='right_side']").find("div[id='div_toolbarId_groupMember']").find("#groupId").textbox("setValue", groupId);
        }

        $('#gridId_groupMember').grid('option', 'postData', params);
        $("#gridId_groupMember").grid("reload", url);
    }

    /**
     * 填充预案信息
     * @param preplan
     */
    function fillPreplan(preplan) {
        if(preplan) {
            $("#formId_preplan_view").find("span[id='preplanName']").html(preplan.name);
        }
    }

    /**
     * 填充报警记录信息
     */
    function fillAlarmRecord(alarmRecord) {
        if(alarmRecord) {
            $("#formId_preplan_view").find("span[id='alarmAddress']").html(alarmRecord.ALARM_ADDRESS);
            $("#formId_preplan_view").find("span[id='alarmDevice']").html(alarmRecord.ALERTOR_NAME);
            $("#formId_preplan_view").find("span[id='alarmTime']").html(alarmRecord.ARD_ALERT_TIME);
        }
    }
</script>
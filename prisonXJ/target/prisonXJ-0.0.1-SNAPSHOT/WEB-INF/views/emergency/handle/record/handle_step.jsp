<%@page import="com.cesgroup.prison.common.bean.user.UserBean" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.cesgroup.prison.common.facade.AuthSystemFacade" %>
<%@ page import="com.cesgroup.prison.code.utils.CodeFacade" %>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<link href="${ctx}/static/emergency/style/style.css" rel="stylesheet" type="text/css"/>
<style>
    .coral-dialog .coral-dialog-buttonpane .coral-dialog-buttonset {
        text-align: center;
        background: #F0F0F0;
        border-top: 1px solid #ccc;
    }
</style>
<cui:tabs id="tabsId_handle_process" heightStyle="fill">
    <ul>
        <li><a id="aId_handle_process_1" href="#tabsId_handle_process_1">基本信息</a></li>
        <li><a id="aId_handle_process_2" href="#tabsId_handle_process_2">处置流程</a></li>
    </ul>
    <div id="tabsId_handle_process_1">
        <cui:form id="formId_preplan_view" heightStyle="fill">
            <table class="table">
                <tr>
                    <!-- 应急预案 -->
                    <th>预案名称：</th>
                    <td>
                        <cui:input id="preplanName" name="preplanName" readonly="true"></cui:input>
                    </td>
                    <!-- 应急预案 -->
                    <th>预案来源：</th>
                    <td>
                        <cui:combobox id="preplanSource" name="preplanSource" data="preplanSourceData" readonly="true"></cui:combobox>
                    </td>
                </tr>
                <tr>
                    <th>预案描述：</th>
                    <td colspan="3"><cui:textarea id="preplanRemarks" name="preplanRemarks" width="500" readonly="true"></cui:textarea></td>
                </tr>
                <tr>
                    <!-- 报警预案 -->
                    <th>报警地点：</th>
                    <td>
                        <cui:input id="alarmAreaName" name="alarmAreaName" readonly="true"></cui:input>
                    </td>
                    <!-- 报警预案 -->
                    <th>报警设备：</th>
                    <td>
                        <cui:input id="alarmPlanName" name="alarmPlanName" readonly="true"></cui:input>
                    </td>
                </tr>
                <tr>
                    <th>报警发生时间：</th>
                    <td>
                        <cui:input id="alarmTime" name="alarmTime" readonly="true"></cui:input>
                    </td>
                    <th>应急处置时间：</th>
                    <td>
                        <cui:input id="startTime" name="startTime" readonly="true"></cui:input>
                    </td>
                </tr>
            </table>
            <!-- 应急预案编号 -->
            <input id="preplanId" type="hidden" value="${preplanId}" />
            <!-- 报警记录编号 -->
            <input id="alarmRecordId" type="hidden" value="${alarmRecordId}" />
            <!-- 应急处置记录编号 -->
            <input id="emerHandleRecordId" type="hidden" value="${emerHandleRecordId}" />
        </cui:form>
    </div>
    <div id="tabsId_handle_process_2">
        <div class="left_side">
            <div class="title"><span>处置流程</span></div>
            <div class="content">
                <ul class="leftnav"></ul>
            </div>
        </div>
        <div class="middle_side">
            <div class="title1"><span>关联工作组</span></div>
            <div class="content1">
                <ul class="middlenav"></ul>
            </div>
            <div class="title2"><span>工作组任务</span></div>
            <div class="content2">
                <cui:textarea id="groupNotice" height="220" componentCls="form-control"></cui:textarea>
            </div>
        </div>
        <div class="right_side">
            <div class="title1">
                <%--<span>工作组成员</span>--%>
                <cui:button id="restartCallMember" label="重新呼叫" style="width: 70px; display: none;"  onClick="restartCallMember()"></cui:button>
                <cui:button id="nearbyPolice" label="编辑工作组民警" style="width: 120px; display: none;" onClick="openNearbyDailog()"></cui:button>
            </div>
            <div class="content1">
                <!-- 数据列表 -->
                <cui:grid id="gridId_groupMember" rownumbers="true" multiselect="true" width="auto" fitStyle="fill"
                          colModel="gridId_groupMember_colModelData">
                    <cui:gridPager gridId="gridId_groupMember"/>
                </cui:grid>
            </div>
            <div class="title2"><span>过程记录</span></div>
            <div class="content2">
                <cui:textarea id="handleContent" height="100" componentCls="form-control"></cui:textarea>
            </div>
        </div>
    </div>
</cui:tabs>

<!-- 隐藏域 -->
<div id="divId_handle_process_hidden" style="display: none;">
    <!-- 应急处置记录的当前处置步骤编号 -->
    <cui:input id="currentRecordStepId" name="currentRecordStepId" type="hidden"></cui:input>
    <!-- 应急处置记录的当前工作组编号 -->
    <cui:input id="currentRecordGroupId" name="currentRecordGroupId" type="hidden"></cui:input>
    <!-- 应急处置记录的当前工作组通知状态 -->
    <cui:input id="currentRecordGroupNoticeStatus" name="currentRecordGroupNoticeStatus" type="hidden"></cui:input>
</div>

<!-- 附近的民警列表 -->
<cui:dialog id="dialogId_groupMember_nearby" autoOpen="false" resizable="false" iframePanel="true" reLoadOnOpen="true" modal="true"
            autoDestroy="true">
</cui:dialog>

<script>
    // 通知状态
    var callStatusData = [
        {value:"0", text:"未呼叫"},
        {value:"1", text:"已呼叫"}
    ];
    // 预案数据来源
    var preplanSourceData = [
        {value:"0", text:"用户定义"},
        {value:"1", text:"系统同步"}
    ];

    $.parseDone(function() {
        // tab页切换
        $("#tabsId_handle_process").tabs({
            onActivate: function(event, ui) {
                var index = $("#tabsId_handle_process").tabs("option", "active");
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
        // 初始化应急处置记录
        initHandleRecord();

        // 初始化报警记录信息
        initAlarmRecord();

        // 展示处置流程切换页
        $("#tabsId_handle_process").tabs("option", "active", "tabsId_handle_process_2");
    });

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
        width: "70",
        align: "left"
    }, {
        label: "号码",
        name: "callNo",
        width: "70",
        align: "left"
    }, {
        label: "呼叫状态",
        name: "callStatus",
        width: "70",
        align: "center",
        formatter : "convertCode",
        revertCode : true,
        formatoptions : {'data': callStatusData}
    }, {
        label: "呼叫结果",
        name: "callResult",
        width: "100",
        align: "left"
    }, {
        label: "更新时间",
        align: "center",
        name: "updateTime",
        width: "100"
    }];

    /**
     * 打开对话框
     * @param event
     * @param ui
     */
    function openNearbyDailog() {
        // 应急处置记录编号
        var recordId = $("#formId_preplan_view").find("input[id='emerHandleRecordId']").val();
        // 应急处置记录的当前处置工作组ID
        var recordGroupId = $("div[id='divId_handle_process_hidden']").find("#currentRecordGroupId").textbox("getValue");

        var url = "${ctx}/emergency/handle/recordMemberManage/openNearbyDialog?recordId=" + recordId + "&recordGroupId=" + recordGroupId;
        $("#dialogId_groupMember_nearby").dialog({
            width: 1065,
            height: 600,
            title: "编辑工作组民警",
            url: url,
            onClose: function() {//回调事件
                // 关联工作组li点击事件
                $("div[id='tabsId_handle_process_2']").find("div[class='middle_side']").find("div[class='content1']").find("ul:eq(0)").find("li[id='" + recordGroupId + "']").click();
            }
        });
        $("#dialogId_groupMember_nearby").dialog("open");
    }

    /**
     * 初始化应急预案信息
     */
    function initHandleRecord() {
        // 应急处置记录编号
        var emerHandleRecordId = $("#formId_preplan_view").find("input[id='emerHandleRecordId']").val();

        var url = "${ctx}/emergency/handle/recordManage/queryById.json";
        var params = {};
        if(emerHandleRecordId) {
            params["id"] = emerHandleRecordId;
        }

        var callBack = function(data) {
            if (data.success) {
                // 填充应急预案信息
                fillHandleRecord(data.obj);
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
        // 应急处置记录编号
        var emerHandleRecordId = $("#formId_preplan_view").find("input[id='emerHandleRecordId']").val();

        var url = "${ctx}/emergency/handle/recordStepManage/queryByRecordId.json";
        var params = {};
        if(preplanId) {
            params["recordId"] = emerHandleRecordId;
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
        var stepContainer = $("div[id='tabsId_handle_process_2']").find("div[class='left_side']").find("div[class='content']").find("ul:eq(0)");
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
                    var liObj = this;
                    if(timers[index]) {
                        clearTimeout(timers[index]);
                    }

                    // 应急工作组名称单击事件
                    timers[index] = setTimeout(function () {
                        beforeInitGroup(liObj);
                    }, 500);
                });

                // 显示名称赋值
                stepTemplateClone.html(obj.stepName);

                stepContainer.append(stepTemplateClone);
            });
            // 默认选选中第一步
            stepContainer.find("li:eq(0)").click();
        }
    }

    /**
     * 初始化应急处置记录的处置步骤关联的工作组列表
     */
    function beforeInitGroup(obj) {
        // 应急处置记录的处置步骤编号
        var recordStepId = $(obj).attr("value");

        // 查询应急处置记录的当前处置步骤之前的处置步骤中，是否有未处置或者过程记录为空的情况
        var url = "${ctx}/emergency/handle/recordStepManage/queryUnhandledPrevStepById.json";
        var params = {};
        if(recordStepId) {
            params["id"] = recordStepId;
        }

        var callBack = function(data) {
            if (data.success) {
                if(data.obj != null && data.obj.length > 0) {
                    var unhandledStepNames = "";
                    $.each(data.obj, function (index, val) {
                        unhandledStepNames += val.stepName + ",";
                    });
                    if(unhandledStepNames != null && unhandledStepNames != '') {
                        unhandledStepNames = unhandledStepNames.substring(0, unhandledStepNames.lastIndexOf(","));
                    }
                    alert("[" + unhandledStepNames + "]尚未被处置，请返回处置！");
                } else {
                    initGroup(obj);
                }
            } else {
                $.messageQueue({ message : data.msg, cls : "warning", iframePanel : true, type : "info" });
            }
        };
        ajaxTodo(url, params, callBack);
    }

    /**
     * 初始化应急工作组
     */
    function initGroup(obj) {
        // 应急处置记录的处置步骤编号 TODO
        var recordStepId = $(obj).attr("value");
        // 处置步骤容器内的元素数量
        var stepCount = $(obj).parent().find("li").length;
        // 应急处置记录的当前处置步骤索引号
        var currentStepIndex = $(obj).index();

        // 处置步骤选中后，样式的改变
        $(obj).addClass("li-select").siblings().removeClass("li-select");

        // 应急处置记录的当前处置步骤ID赋值
        $("div[id='divId_handle_process_hidden']").find("#currentRecordStepId").textbox("setValue", recordStepId);

        // 按钮状态设置
        if(currentStepIndex <= 0) {
            $("#btnId_prevStep").button({disabled: true});
            if(stepCount == 1) {
                $("#btnId_nextStep").button("hide");
                $("#btnId_finished").button("show");
            } else {
                $("#btnId_finished").button("hide");
                $("#btnId_nextStep").button("show");
            }
        } else {
            $("#btnId_prevStep").button({disabled: false});
            if((currentStepIndex + 1) < stepCount) {
                $("#btnId_finished").button("hide");
                $("#btnId_nextStep").button("show");
            } else {
                $("#btnId_nextStep").button("hide");
                $("#btnId_finished").button("show");
            }
        }

        //1 查询应急处置记录的处置步骤信息
        var url1 = "${ctx}/emergency/handle/recordStepManage/queryById.json";
        var params1 = {};
        if(recordStepId) {
            params1["id"] = recordStepId;
        }

        var callBack1 = function(data) {
            if (data.success) {
                if(data.obj != null) {
                    // 处置过程描述赋值
                    $("div[id='tabsId_handle_process_2']").find("div[class='right_side']").find("div[class='content2']").find("#handleContent").textbox("setValue", data.obj.handleContent);
                }
            } else {
                $.messageQueue({ message : data.msg, cls : "warning", iframePanel : true, type : "info" });
            }
        };
        ajaxTodo(url1, params1, callBack1);

        //2 查询处置步骤关联的工作组信息
        var url2 = "${ctx}/emergency/handle/recordGroupManage/queryByRecordStepId.json";
        var params2 = {};
        if(recordStepId) {
            params2["recordStepId"] = recordStepId;
        }

        var callBack2 = function(data) {
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
        ajaxTodo(url2, params2, callBack2);
    }

    /**
     * 填充处置步骤
     */
    function fillGroup(groupList) {
        // 工作组容器
        var groupContainer = $("div[id='tabsId_handle_process_2']").find("div[class='middle_side']").find("div[class='content1']").find("ul:eq(0)");
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
                    notice : obj.groupNotice,
                    getMemberWay : obj.getMemberWay
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
                groupTemplateClone.html(obj.groupName);

                groupContainer.append(groupTemplateClone);
            });
            groupContainer.find("li:eq(0)").click();
        }
    }

    /**
     * 加载工作组成员数据
     * @param obj
     */
    function loadGroupMemberInfo(liObj) {
        var recordGroupId = $(liObj).attr("value");       // 应急处置记录的工作组ID

        // 应急处置记录的当前处置工作组ID赋值
        $("div[id='divId_handle_process_hidden']").find("#currentRecordGroupId").textbox("setValue", recordGroupId);

        var url = "${ctx}/emergency/handle/recordGroupManage/queryById.json";
        var params = {};
        if(recordGroupId) {
            params["id"] = recordGroupId;
        }

        var callBack = function(data) {
            if (data.success) {
                var groupNotice = data.obj.groupNotice;             // 应急处置记录的工作组任务
                var getMemberWay = data.obj.getMemberWay;           // 应急处置记录的工作组获取成员方式(0:预定义; 1:即时获取，从人脸识别相机、狱内干警数据中获取.)
                var groupNoticeStatus = data.obj.groupNoticeStatus; // 应急处置记录的工作组通知状态(0:未通知; 1:已通知.)

                // 工作组任务赋值
                $("div[id='tabsId_handle_process_2']").find("div[class='middle_side']").find("div[class='content2']").find("#groupNotice").textbox("setValue", "");
                $("div[id='tabsId_handle_process_2']").find("div[class='middle_side']").find("div[class='content2']").find("#groupNotice").textbox("setValue", groupNotice);

                // 应急处置记录的当前处置工作组通知状态赋值
                $("div[id='divId_handle_process_hidden']").find("#currentRecordGroupNoticeStatus").textbox("setValue", groupNoticeStatus);

                // 根据工作组获取成员分方式，做不同的处理
                if(getMemberWay == "1" || getMemberWay == "2") {
                    $("div[id='tabsId_handle_process_2']").find("div[class='right_side']").find("div[class='title1']").find("#nearbyPolice").button("show");
                } else {
                    $("div[id='tabsId_handle_process_2']").find("div[class='right_side']").find("div[class='title1']").find("#nearbyPolice").button("hide");
                }

                // 根据工作组通知状态，做不同的处理
                if(groupNoticeStatus == "1") {
                    $("div[id='tabsId_handle_process_2']").find("div[class='right_side']").find("div[class='title1']").find("#restartCallMember").button("show");
                } else {
                    $("div[id='tabsId_handle_process_2']").find("div[class='right_side']").find("div[class='title1']").find("#restartCallMember").button("hide");
                }

                // 呼叫工作组成员
                startCallMember(false);

                // 刷新工作组成员
                refreshCallResult();
            } else {
                // console.error(data.msg);
                $.messageQueue({ message : data.msg, cls : "warning", iframePanel : true, type : "info" });
            }
        };
        ajaxTodo(url, params, callBack);
    }

    /**
     * 填充预案信息
     * @param preplan
     */
    function fillHandleRecord(handleRecord) {
        if(handleRecord) {
            $("#formId_preplan_view").find("#preplanName").textbox("setValue", handleRecord.preplanName);
            $("#formId_preplan_view").find("#preplanSource").combobox("setValue", handleRecord.preplanSource);
            $("#formId_preplan_view").find("#preplanRemarks").textbox("setValue", handleRecord.preplanRemarks);
            $("#formId_preplan_view").find("#alarmAreaName").textbox("setValue", handleRecord.alarmAreaName);
            $("#formId_preplan_view").find("#alarmPlanName").textbox("setValue", handleRecord.alarmPlanName);
            $("#formId_preplan_view").find("#startTime").textbox("setValue", handleRecord.startTime);
        }
    }

    /**
     * 填充报警记录信息
     */
    function fillAlarmRecord(alarmRecord) {
        if(alarmRecord) {
            $("#formId_preplan_view").find("#alarmTime").textbox("setValue", alarmRecord.ARD_ALERT_TIME);
        }
    }

    /**
     * 指挥调度系统呼叫工作组成员
     * @param restartCallFlag 重新呼叫标志位
     */
    function startCallMember(restartCallFlag) {
        // 应急处置记录的当前处置工作组ID
        var recordGroupId = $("div[id='divId_handle_process_hidden']").find("#currentRecordGroupId").textbox("getValue");
        // 通知状态
        var noticeStatus = $("div[id='divId_handle_process_hidden']").find("#currentRecordGroupNoticeStatus").textbox("getValue");
        // 通知内容
        var noticeContent = $("div[id='tabsId_handle_process_2']").find("div[class='middle_side']").find("div[class='content2']").find("#groupNotice").textbox("getValue");

        // 非重新呼叫且通知状态为已通知时执行
        if(!restartCallFlag && noticeStatus == "1") {
            console.log("呼叫未执行，原因：工作组通知状态为已通知，且并非重新呼叫");
            return;
        }

        var url = "${ctx}/emergency/handle/recordMemberManage/startCallByRecordGroupId.json";
        var params = {};
        if(recordGroupId) {
            params["recordGroupId"] = recordGroupId;
        }
        if(noticeContent) {
            params["noticeContent"] = noticeContent;
        }
        params["restartCall"] = restartCallFlag;

        var callBack = function(data) {
            if (data.success) {
                console.log("呼叫用户指令发送成功");
                if(restartCallFlag) {
                    $.messageQueue({ message : "重新呼叫工作组成员执行完成", cls : "success", iframePanel : true, type : "info" });
                } else {
                    // 更新当前工作组的通知状态
                    $("div[id='divId_handle_process_hidden']").find("#currentRecordGroupNoticeStatus").textbox("setValue", "1");
                    // 显示重新呼叫按钮
                    $("div[id='tabsId_handle_process_2']").find("div[class='right_side']").find("div[class='title1']").find("#restartCallMember").button("show");
                }
            } else {
                console.warn(data.msg);
                $.messageQueue({ message : data.msg, cls : "warning", iframePanel : true, type : "info" });
            }
        };
        ajaxTodo(url, params, callBack);
    }

    /**
     * 重新呼叫
     */
    function restartCallMember() {
        $.confirm("是否重新呼叫工作组成员？", "信息确认", function(confirm) {
            if (confirm) {
                startCallMember(true);
            }
        });
    }

    /**
     * 定时器固定格式，参数为(函数,设置的定时毫秒数)，每过定时器中设置的毫秒数，就运行一下函数
     * 清空计时器：clearInterval(si);
     */
    var handleProcessSi = setInterval(function() {
        // 刷新工作组成员呼叫结果
        refreshCallResult();
    }, 5000);

    /**
     * 刷新工作组成员呼叫结果
     */
    function refreshCallResult() {
        // 应急处置记录的当前处置工作组ID
        var recordGroupId = $("div[id='divId_handle_process_hidden']").find("#currentRecordGroupId").textbox("getValue");

        if(recordGroupId) {
            // 分页查询
            var url = "${ctx}/emergency/handle/recordMemberManage/queryWithPage";
            var params = {};

            // 初始化查询条件
            params["recordGroupId"] = recordGroupId;

            $('#gridId_groupMember').grid('option', 'postData', params);
            $("#gridId_groupMember").grid("reload", url);
        }
    }

    /**
     * 上一步
     */
    function prevStep() {
        // 处置步骤容器
        var stepContainer = $("div[id='tabsId_handle_process_2']").find("div[class='left_side']").find("div[class='content']").find("ul:eq(0)");
        // 应急处置记录的当前处置步骤ID
        var recordStepId = $("div[id='divId_handle_process_hidden']").find("#currentRecordStepId").textbox("getValue");
        // 应急处置记录的当前处置步骤索引号
        var currentStepIndex = stepContainer.find("li[id='" + recordStepId + "']").index();

        // 触发上一步骤点击事件
        stepContainer.find("li:eq(" + (currentStepIndex - 1) + ")").click();
    }

    /**
     * 下一步
     */
    function nextStep() {
        // 处置步骤容器
        var stepContainer = $("div[id='tabsId_handle_process_2']").find("div[class='left_side']").find("div[class='content']").find("ul:eq(0)");
        // 应急处置记录的当前处置步骤ID
        var recordStepId = $("div[id='divId_handle_process_hidden']").find("#currentRecordStepId").textbox("getValue");
        // 应急处置记录的当前处置步骤索引号
        var currentStepIndex = stepContainer.find("li[id='" + recordStepId + "']").index();
        // 处置过程描述
        var stepHandleContent = $("div[id='tabsId_handle_process_2']").find("div[class='right_side']").find("div[class='content2']").find("#handleContent").textbox("getValue");

        if(!stepHandleContent || $.trim(stepHandleContent) == "") {
            alert("请填写过程记录");
            return;
        }

        //1 更新过程记录
        var url = "${ctx}/emergency/handle/recordStepManage/updateHandleContent.json";
        var params = {};
        if(recordStepId) {
            params["id"] = recordStepId;
        }
        if(stepHandleContent) {
            params["handleContent"] = stepHandleContent;
        }

        var callBack = function(data) {
            if (data.success) {
                $.messageQueue({ message : "更新成功", cls : "success", iframePanel : true, type : "info" });
                // 触发下一步骤点击事件
                stepContainer.find("li:eq(" + (currentStepIndex + 1) + ")").click();
            } else {
                $.messageQueue({ message : data.msg, cls : "warning", iframePanel : true, type : "info" });
            }
        };
        ajaxTodo(url, params, callBack);
    }

    /**
     * 完成
     */
    function finished() {
        // 应急处置记录的当前处置步骤ID
        var recordStepId = $("div[id='divId_handle_process_hidden']").find("#currentRecordStepId").textbox("getValue");
        // 处置过程描述
        var stepHandleContent = $("div[id='tabsId_handle_process_2']").find("div[class='right_side']").find("div[class='content2']").find("#handleContent").textbox("getValue");

        if(!stepHandleContent || $.trim(stepHandleContent) == "") {
            alert("请填写过程记录");
            return;
        }
        //1 更新过程记录
        var url = "${ctx}/emergency/handle/recordStepManage/updateHandleContent.json";
        var params = {};
        if(recordStepId) {
            params["id"] = recordStepId;
        }
        if(stepHandleContent) {
            params["handleContent"] = stepHandleContent;
        }

        var callBack = function(data) {
            if (data.success) {
                //2 更新应急处置记录状态为已完成
                doAjaxEmerHandleRecordToFinish();

                $.messageQueue({ message : "更新成功", cls : "success", iframePanel : true, type : "info" });

                //3 关闭处置窗口
                $("#dialogId_handle_process").dialog("close");
            } else {
                $.messageQueue({ message : data.msg, cls : "warning", iframePanel : true, type : "info" });
            }
        };
        ajaxTodo(url, params, callBack);
    }
</script>
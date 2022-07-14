<%@page import="com.cesgroup.prison.common.bean.user.UserBean" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.cesgroup.prison.common.facade.AuthSystemFacade" %>
<%@ page import="com.cesgroup.prison.code.utils.CodeFacade" %>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<script src="${ctx}/static/system/common.js"></script>
<style>
    #container{
        width: 700px;
        margin: 10px 0px 10px 0px;
        position:relative;
        width: 100%;
        height: 480px;
        /*border:solid 1px #00FF00;*/
    }
    #container .left_side{
        position:absolute;
        top: 0px;
        left: 10px;
        width:180px;
        height: 478px;
        /*border:solid 1px #0000FF;*/
    }
    #container .middle_side{
        margin: 0px 10px 0px 210px;
        width: 180px;
        height: 478px;
        /*border:solid 1px #0000FF;*/
    }
    #container .right_side{
        position:absolute;
        top: 0px;
        right: 10px;
        width:380px;
        height: 478px;
        /*border:solid 1px #0000FF;*/
    }
    #container .left_side .title {
        height: 20px;
    }
    #container .left_side .content {
        width: 100%;
        height: 454px;
        border: 1px solid #4692f0;
        overflow-y: auto;
        overflow-x: hidden;
    }
    #container .middle_side .title {
        height: 20px;
    }
    #container .middle_side .content {
        width: 100%;
        height: 454px;
        border: 1px solid #4692f0;
        overflow-y: auto;
        overflow-x: hidden;
    }
    #container .right_side .title {
        height: 20px;
    }
    #container .right_side .content {
        height: 454px;
        /*border: 1px solid #4692f0;*/
        overflow-y: auto;
        overflow-x: hidden;
    }
    .selectHid {
        text-align: center;
        width: 160px;
        height: 30px;
        font-size: 11.5px;
        cursor: pointer;
        margin-top: 5px;
    }

    #container .right_side .content table {
        width: 540px;
        border: 1px solid #ededed;
        margin-top: 20px;
        margin-bottom: 20px;
    }

    #container .right_side .content th {
        background: #4692f0;
        text-align: center;
        border: 1px solid #ededed;
    }

    #container .right_side .content td {
        border: 1px solid #ededed;
    }
</style>
<div id="container">
    <div id="divId_stepGroup_left" class="left_side">
        <div class="title"><span>待关联工作组</span></div>
        <div class="content">
            <ul></ul>
        </div>
    </div>
    <div id="divId_stepGroup_middle" class="middle_side">
        <div class="title"><span>已关联工作组</span></div>
        <div class="content">
            <ul></ul>
        </div>
    </div>
    <div id="divId_stepGroup_right" class="right_side">
        <div class="title"></div>
        <div class="content">
            <table></table>
        </div>
    </div>
</div>
<!-- 隐藏域 -->
<div style="display: none;">
    <cui:form id="formId_stepGroup_maintain">
        <cui:input type='hidden' id="preplanId" name="preplanId" value="${preplanId}" />
        <cui:input type='hidden' id="stepId" name="stepId" value="${stepId}" />
    </cui:form>
</div>
<script>
    $.parseDone(function() {
        // 初始化待关联的工作组数据
        initUncheckedGroup();

        // 初始化已关联的工作组数据
        initCheckedGroup();
    });

    /**
     * 初始化待关联工作组
     */
    function initUncheckedGroup() {
        var preplanId = $("#formId_stepGroup_maintain").find("#preplanId").textbox("getValue");//预案编号
        var stepId = $("#formId_stepGroup_maintain").find("#stepId").textbox("getValue");//处置步骤编号

        var url = "${ctx}/emergency/stepGroupManage/queryUncheckedGroup.json";
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
                    fillUncheckedGroup(data.obj);
                }
            } else {
                $.messageQueue({ message : data.msg, cls : "warning", iframePanel : true, type : "info" });
            }
        };
        ajaxTodo(url, params, callBack);
    }

    /**
     * 初始化已关联工作组
     */
    function initCheckedGroup() {
        var preplanId = $("#formId_stepGroup_maintain").find("#preplanId").textbox("getValue");//预案编号
        var stepId = $("#formId_stepGroup_maintain").find("#stepId").textbox("getValue");//处置步骤编号

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
                // 将已关联工作组数据存入checkedGroupList
                if(data.obj != null && data.obj.length > 0) {
                    // 填充已关联工作组
                    fillCheckedGroup(data.obj);
                }
            } else {
                $.messageQueue({ message : data.msg, cls : "warning", iframePanel : true, type : "info" });
            }
        };
        ajaxTodo(url, params, callBack);
    }

    /**
     * 填充待关联的工作组
     * @param uncheckedGroupList
     */
    function fillUncheckedGroup(groupList) {
        // 工作组容器
        var groupContainer = $("div[id='divId_stepGroup_left']").find("div[class='content']").find("ul:eq(0)");
        // 工作组模板
        var groupTemplate = '<li class="selectHid"></li>'

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
                    value : obj.id
                }).unbind('dblclick').bind('dblclick', function() {
                    event.stopPropagation();
                    if(timers[index]) {
                        clearTimeout(timers[index]);
                    }

                    // 应急工作组名称双击事件
                    moveGroup("divId_stepGroup_left", "divId_stepGroup_middle", this);
                }).unbind('click').bind('click', function() {
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
        }
    }

    /**
     * 填充已关联的工作组
     * @param uncheckedGroupList
     */
    function fillCheckedGroup(groupList) {
        // 工作组容器
        var groupContainer = $("div[id='divId_stepGroup_middle']").find("div[class='content']").find("ul:eq(0)");
        // 工作组模板
        var groupTemplate = '<li class="selectHid"></li>'

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
                    value : obj.id
                }).unbind('dblclick').bind('dblclick', function() {
                    event.stopPropagation();
                    if(timers[index]) {
                        clearTimeout(timers[index]);
                    }

                    // 应急工作组名称双击事件
                    moveGroup("divId_stepGroup_middle", "divId_stepGroup_left", this);
                }).unbind('click').bind('click', function() {
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
        }
    }

    /**
     * 工作组转移
     */
    function moveGroup(source, target, obj) {
        // 目标工作组容器
        var targetContainer = $("div[id='" + target + "']").find("div[class='content']").find("ul:eq(0)");
        // 工作组模板
        var groupTemplate = '<li class="selectHid"></li>';

        var groupId = $(obj).attr("value");
        var groupName = $(obj).html();

        var groupTemplateClone = $(groupTemplate).clone();
        var timer = null;
        groupTemplateClone.attr({
            id : groupId,
            name : groupId,
            value : groupId
        }).unbind('dblclick').bind('dblclick', function() {
            event.stopPropagation();
            if(timer) {
                clearTimeout(timer);
            }

            moveGroup(target, source, this);
        }).unbind('click').bind('click', function() {
            var liObj = this;
            if(timer) {
                clearTimeout(timer);
            }

            // 应急预案名称单击事件
            timer = setTimeout(function () {
                loadGroupMemberInfo(liObj);
            }, 500);
        });

        // checkedGroup显示名称赋值
        groupTemplateClone.html(groupName);

        targetContainer.append(groupTemplateClone);

        $(obj).remove();
    }

    /**
     * 加载工作组成员数据
     * @param obj
     */
    function loadGroupMemberInfo(obj) {
        var groupId = $(obj).attr("value");

        var url = "${ctx}/emergency/groupMemberManage/queryGroupMemberByGroupId.json";
        var params = {};
        if(groupId) {
            params["groupId"] = groupId;
        }

        var callBack = function(data) {
            if (data.success) {
                // 填充已关联工作组
                fillGroupMember(data.obj);
            } else {
                $.messageQueue({ message : data.msg, cls : "warning", iframePanel : true, type : "info" });
            }
        };
        ajaxTodo(url, params, callBack);
    }

    /**
     * 填充工作组成员数据
     * @param groupMemberList
     */
    function fillGroupMember(groupMemberList) {
        console.log("length = " + groupMemberList.length);
        var groupMemberContainer = $("div[id='divId_stepGroup_right']").find("div[class='content']").find("table:eq(0)");
        groupMemberContainer.empty();
        var groupMemberHeaderTemplate = '<tr>' +
            '<th width="100" nowrap="nowrap">姓名</th>' +
            '<th width="100" nowrap="nowrap">通联号码</th>' +
            '<th width="170" nowrap="nowrap">所属部门</th>' +
            '</tr>';

        var groupMemberBodyTemplate = '<tr>' +
            '<td id="memberName" width="100" nowrap="nowrap"></td>' +
            '<td id="callNo" width="100" nowrap="nowrap"></td>' +
            '<td id="deptName" width="170" nowrap="nowrap"></td>' +
            '</tr>';
        groupMemberContainer.append($(groupMemberHeaderTemplate).clone());
        if(groupMemberList != null && groupMemberList.length > 0) {
            $.each(groupMemberList, function(index, groupMember) {
                var groupMemberBodyTemplateClone = $(groupMemberBodyTemplate).clone();
                groupMemberBodyTemplateClone.find("td[id='memberName']").text(groupMember.memberName);
                groupMemberBodyTemplateClone.find("td[id='callNo']").text(groupMember.callNo);
                groupMemberBodyTemplateClone.find("td[id='deptName']").text(groupMember.deptName);
                groupMemberContainer.append(groupMemberBodyTemplateClone);
            });
        }
    }
</script>
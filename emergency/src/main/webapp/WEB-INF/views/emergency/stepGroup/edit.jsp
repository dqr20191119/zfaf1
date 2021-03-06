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
        <div class="title"><span>??????????????????</span></div>
        <div class="content">
            <ul></ul>
        </div>
    </div>
    <div id="divId_stepGroup_middle" class="middle_side">
        <div class="title"><span>??????????????????</span></div>
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
<!-- ????????? -->
<div style="display: none;">
    <cui:form id="formId_stepGroup_maintain">
        <cui:input type='hidden' id="preplanId" name="preplanId" value="${preplanId}" />
        <cui:input type='hidden' id="stepId" name="stepId" value="${stepId}" />
    </cui:form>
</div>
<script>
    $.parseDone(function() {
        // ????????????????????????????????????
        initUncheckedGroup();

        // ????????????????????????????????????
        initCheckedGroup();
    });

    /**
     * ???????????????????????????
     */
    function initUncheckedGroup() {
        var preplanId = $("#formId_stepGroup_maintain").find("#preplanId").textbox("getValue");//????????????
        var stepId = $("#formId_stepGroup_maintain").find("#stepId").textbox("getValue");//??????????????????

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
                // ?????????????????????????????????uncheckedGroupList
                if(data.obj != null && data.obj.length > 0) {
                    // ????????????????????????
                    fillUncheckedGroup(data.obj);
                }
            } else {
                $.messageQueue({ message : data.msg, cls : "warning", iframePanel : true, type : "info" });
            }
        };
        ajaxTodo(url, params, callBack);
    }

    /**
     * ???????????????????????????
     */
    function initCheckedGroup() {
        var preplanId = $("#formId_stepGroup_maintain").find("#preplanId").textbox("getValue");//????????????
        var stepId = $("#formId_stepGroup_maintain").find("#stepId").textbox("getValue");//??????????????????

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
                // ?????????????????????????????????checkedGroupList
                if(data.obj != null && data.obj.length > 0) {
                    // ????????????????????????
                    fillCheckedGroup(data.obj);
                }
            } else {
                $.messageQueue({ message : data.msg, cls : "warning", iframePanel : true, type : "info" });
            }
        };
        ajaxTodo(url, params, callBack);
    }

    /**
     * ???????????????????????????
     * @param uncheckedGroupList
     */
    function fillUncheckedGroup(groupList) {
        // ???????????????
        var groupContainer = $("div[id='divId_stepGroup_left']").find("div[class='content']").find("ul:eq(0)");
        // ???????????????
        var groupTemplate = '<li class="selectHid"></li>'

        // ???????????????
        groupContainer.empty();

        // ??????????????????????????????
        if(groupList != null && groupList.length > 0) {
            var timers = [];
            $.each(groupList, function (index, obj) {
                // ???????????????
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

                    // ?????????????????????????????????
                    moveGroup("divId_stepGroup_left", "divId_stepGroup_middle", this);
                }).unbind('click').bind('click', function() {
                    var liObj = this;
                    if(timers[index]) {
                        clearTimeout(timers[index]);
                    }

                    // ?????????????????????????????????
                    timers[index] = setTimeout(function () {
                        loadGroupMemberInfo(liObj);
                    }, 500);
                });

                // ??????????????????
                groupTemplateClone.html(obj.name);

                groupContainer.append(groupTemplateClone);
            });
        }
    }

    /**
     * ???????????????????????????
     * @param uncheckedGroupList
     */
    function fillCheckedGroup(groupList) {
        // ???????????????
        var groupContainer = $("div[id='divId_stepGroup_middle']").find("div[class='content']").find("ul:eq(0)");
        // ???????????????
        var groupTemplate = '<li class="selectHid"></li>'

        // ???????????????
        groupContainer.empty();

        // ??????????????????????????????
        if(groupList != null && groupList.length > 0) {
            var timers = [];
            $.each(groupList, function (index, obj) {
                // ???????????????
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

                    // ?????????????????????????????????
                    moveGroup("divId_stepGroup_middle", "divId_stepGroup_left", this);
                }).unbind('click').bind('click', function() {
                    var liObj = this;
                    if(timers[index]) {
                        clearTimeout(timers[index]);
                    }

                    // ?????????????????????????????????
                    timers[index] = setTimeout(function () {
                        loadGroupMemberInfo(liObj);
                    }, 500);
                });

                // ??????????????????
                groupTemplateClone.html(obj.name);

                groupContainer.append(groupTemplateClone);
            });
        }
    }

    /**
     * ???????????????
     */
    function moveGroup(source, target, obj) {
        // ?????????????????????
        var targetContainer = $("div[id='" + target + "']").find("div[class='content']").find("ul:eq(0)");
        // ???????????????
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

            // ??????????????????????????????
            timer = setTimeout(function () {
                loadGroupMemberInfo(liObj);
            }, 500);
        });

        // checkedGroup??????????????????
        groupTemplateClone.html(groupName);

        targetContainer.append(groupTemplateClone);

        $(obj).remove();
    }

    /**
     * ???????????????????????????
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
                // ????????????????????????
                fillGroupMember(data.obj);
            } else {
                $.messageQueue({ message : data.msg, cls : "warning", iframePanel : true, type : "info" });
            }
        };
        ajaxTodo(url, params, callBack);
    }

    /**
     * ???????????????????????????
     * @param groupMemberList
     */
    function fillGroupMember(groupMemberList) {
        console.log("length = " + groupMemberList.length);
        var groupMemberContainer = $("div[id='divId_stepGroup_right']").find("div[class='content']").find("table:eq(0)");
        groupMemberContainer.empty();
        var groupMemberHeaderTemplate = '<tr>' +
            '<th width="100" nowrap="nowrap">??????</th>' +
            '<th width="100" nowrap="nowrap">????????????</th>' +
            '<th width="170" nowrap="nowrap">????????????</th>' +
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
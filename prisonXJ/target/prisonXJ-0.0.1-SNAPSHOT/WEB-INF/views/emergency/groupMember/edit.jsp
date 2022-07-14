<%@page import="com.cesgroup.prison.common.bean.user.UserBean" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.cesgroup.prison.common.facade.AuthSystemFacade" %>
<%@ page import="com.cesgroup.prison.code.utils.CodeFacade" %>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<script src="${ctx}/static/system/common.js"></script>
<div>
    <cui:form id="formId_groupMember_edit" heightStyle="fill">
        <cui:input type='hidden' id="groupId" name="groupId" />
        <cui:input type='hidden' id="id" name="id" />
        <table class="table" style="width: 98%;">
            <tr>
                <th>呼叫人员</th>
                <td>
                    <div id="divId_memberCombotree">
                        <cui:combotree id="memberCombotree" url="${ctx}/common/authsystem/findDeptPoliceForCombotree" onNodeClick="onCallNameClick" required="true" componentCls="form-control"></cui:combotree>
                    </div>
                    <div id="divId_memberName">
                        <cui:input id="memberName" name="memberName" type="text" componentCls="form-control"/>
                    </div>
                    <cui:input id="memberId" name="memberId" type="hidden"/>
                    <cui:input id="memberJobNo" name="memberJobNo" type="hidden"/>
                </td>
            </tr>
            <tr>
                <th>呼叫号码</th>
                <td>
                    <cui:input id="callNo" name="callNo" type="text" required="true" componentCls="form-control"/>
                </td>
            </tr>
            <tr>
                <th>所属单位</th>
                <td>
                    <cui:input id="unitName" name="unitName" type="text" componentCls="form-control"/>
                    <cui:input id="unitCode" name="unitCode" type="hidden"/>
                </td>
            </tr>
            <tr>
                <th>所属部门</th>
                <td>
                    <cui:input id="deptName" name="deptName" type="text" componentCls="form-control"/>
                    <cui:input id="deptCode" name="deptCode" type="hidden"/>
                </td>
            </tr>
            <tr>
                <th>数据来源</th>
                <td>
                    <cui:combobox id="source" name="source" data="groupMemberSourceData" required="true" componentCls="form-control"></cui:combobox>
                </td>
            </tr>
        </table>
    </cui:form>
</div>
<script>
    $.parseDone(function() {
        // 梯队成员初始化
        initEmerGroupMember();
    });

    /**
     * 表单数据填充
     */
    function initEmerGroupMember() {
        var id = '${id}';// 工作组与成员关联关系编号
        var groupId = '${groupId}';// 工作组编号
        var memberId = '${memberId}';// 成员编号

        $("form[id='formId_groupMember_edit']").find("#groupId").textbox("setValue", groupId);
        if(id == null || id == '') {
            // $("form[id='formId_groupMember_edit']").find("#memberCombotree").combotree("clear");
            $("form[id='formId_groupMember_edit']").find("div[id='divId_memberName']").hide();
            $("form[id='formId_groupMember_edit']").find("div[id='divId_memberCombotree']").show();
            $("form[id='formId_groupMember_edit']").find("#memberCombotree").combotree({required: true});
            // $("form[id='formId_groupMember_edit']").find("#memberCombotree").combotree("reload", "${ctx}/common/authsystem/findDeptPoliceForCombotree");

            $("form[id='formId_groupMember_edit']").find("#id").textbox("setValue", "");
            $("form[id='formId_groupMember_edit']").find("#memberId").textbox("setValue", "");
            $("form[id='formId_groupMember_edit']").find("#memberName").textbox({disabled: false});
            $("form[id='formId_groupMember_edit']").find("#memberName").textbox("setValue", "");
            $("form[id='formId_groupMember_edit']").find("#memberJobNo").textbox("setValue", "");
            $("form[id='formId_groupMember_edit']").find("#callNo").textbox("setValue", "");
            $("form[id='formId_groupMember_edit']").find("#unitName").textbox({disabled: false});
            $("form[id='formId_groupMember_edit']").find("#unitName").textbox("setValue", "");
            $("form[id='formId_groupMember_edit']").find("#unitCode").textbox("setValue", "");
            $("form[id='formId_groupMember_edit']").find("#deptName").textbox({disabled: false});
            $("form[id='formId_groupMember_edit']").find("#deptName").textbox("setValue", "");
            $("form[id='formId_groupMember_edit']").find("#deptCode").textbox("setValue", "");
            $("form[id='formId_groupMember_edit']").find("#source").combobox("setValue", "");
        } else {
            // $("form[id='formId_groupMember_edit']").find("#memberCombotree").combotree("clear");
            $("form[id='formId_groupMember_edit']").find("#memberCombotree").combotree({required: false});
            $("form[id='formId_groupMember_edit']").find("div[id='divId_memberCombotree']").hide();
            $("form[id='formId_groupMember_edit']").find("div[id='divId_memberName']").show();

            $("form[id='formId_groupMember_edit']").find("#id").textbox("setValue", id);

            var url = "${ctx}/emergency/memberManage/queryById.json";
            var params = {};
            params['id'] = memberId;

            var callBack = function(data) {
                if (data.success) {
                    fillEmerGroupMember(data.obj);
                } else {
                    $.messageQueue({ message : data.msg, cls : "warning", iframePanel : true, type : "info" });
                }
            };
            ajaxTodo(url, params, callBack);
        }
    }

    /**
     * URL类型选择事件
     */
    function onCallNameClick(event, ui) {
        console.log("onCallNameClick node type = " + ui.node.type);
        var nodeId = ui.node.id;
        var nodeType = ui.node.type;

        //节点类型为机构
        if(nodeType == 'org') {
            var url = "${ctx}/common/authsystem/queryOrgByCode.json";
            var params = {};
            params['code'] = nodeId;

            var callBack = function(data) {
                if (data.success) {
                    fillEmerGroupMemberByOrg(data.obj);
                } else {
                    $.messageQueue({ message : data.msg, cls : "warning", iframePanel : true, type : "info" });
                }
            };
            ajaxTodo(url, params, callBack);
        }
        //节点类型为用户
        else if(nodeType == 'user') {
            var url = "${ctx}/common/authsystem/queryUserById.json";
            var params = {};
            params['id'] = nodeId;

            var callBack = function(data) {
                if (data.success) {
                    fillEmerGroupMemberByUser(data.obj);
                } else {
                    $.messageQueue({ message : data.msg, cls : "warning", iframePanel : true, type : "info" });
                }
            };
            ajaxTodo(url, params, callBack);
        }
        //呼叫人员赋值
        $("form[id='formId_groupMember_edit']").find("#memberName").textbox("setValue", ui.node.name);
        $("form[id='formId_groupMember_edit']").find("#memberJobNo").textbox("setValue", ui.node.code);
    }

    /**
     * 根据机构信息填充工作组成员数据
     * @param userInfo
     */
    function fillEmerGroupMemberByOrg(orgInfo) {
        var formObj = $("form[id='formId_groupMember_edit']");
        // formObj.find("#callNo").textbox({disabled: true});
        formObj.find("#callNo").textbox("setValue", "");
        formObj.find("#unitName").textbox("setValue", orgInfo.unitName);
        formObj.find("#unitCode").textbox("setValue", orgInfo.unitCode);
        formObj.find("#deptName").textbox("setValue", orgInfo.orgName);
        formObj.find("#deptCode").textbox("setValue", orgInfo.orgCode);
    }

    /**
     * 根据用户信息填充工作组成员数据
     * @param userInfo
     */
    function fillEmerGroupMemberByUser(userInfo) {
        var formObj = $("form[id='formId_groupMember_edit']");
        formObj.find("#callNo").textbox("setValue", userInfo.policeAffair);
        formObj.find("#unitName").textbox("setValue", userInfo.userUnitName);
        formObj.find("#unitCode").textbox("setValue", userInfo.userUnitCode);
        formObj.find("#deptName").textbox("setValue", userInfo.userOrgName);
        formObj.find("#deptCode").textbox("setValue", userInfo.userOrgCode);
    }
    /**
     * 填充数据
     * @param data
     */
    function fillEmerGroupMember(data) {
        console.log("fillEmerGroupMember data = " + JSON.stringify(data));
        var formObj = $("form[id='formId_groupMember_edit']");

        formObj.find("#memberId").textbox("setValue", data.id);
        formObj.find("#memberName").textbox("setValue", data.name);
        formObj.find("#memberName").textbox({disabled: true});
        formObj.find("#memberJobNo").textbox("setValue", data.jobNo);
        formObj.find("#callNo").textbox("setValue", data.callNo);
        formObj.find("#unitName").textbox("setValue", data.unitName);
        formObj.find("#unitName").textbox({disabled: true});
        formObj.find("#unitCode").textbox("setValue", data.unitCode);
        formObj.find("#deptName").textbox("setValue", data.deptName);
        formObj.find("#deptName").textbox({disabled: true});
        formObj.find("#deptCode").textbox("setValue", data.deptCode);
        formObj.find("#source").combobox("setValue", data.source);
    }
</script>
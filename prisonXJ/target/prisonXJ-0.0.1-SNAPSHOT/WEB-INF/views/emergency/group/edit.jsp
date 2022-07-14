<%@page import="com.cesgroup.prison.common.bean.user.UserBean" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.cesgroup.prison.common.facade.AuthSystemFacade" %>
<%@ page import="com.cesgroup.prison.code.utils.CodeFacade" %>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<script src="${ctx}/static/system/common.js"></script>
<div>
    <cui:form id="formId_group_edit" heightStyle="fill">
        <cui:input type='hidden' id="preplanId" name="preplanId" />
        <cui:input type='hidden' id="id" name="id" />
        <table class="table" style="width: 98%;">
            <tr>
                <th>工作组名称</th>
                <td>
                    <cui:input id="name" name="name" type="text" required="true" componentCls="form-control"/>
                </td>
                <th>数据来源</th>
                <td>
                    <cui:combobox id="source" name="source" data="groupSourceData" required="true" componentCls="form-control"></cui:combobox>
                </td>
            </tr>
            <tr>
                <th>通知内容：</th>
                <td colspan="3">
                    <cui:textarea id="notice" name="notice" componentCls="form-control" height="130"></cui:textarea>
                </td>
            </tr>
        </table>
    </cui:form>
</div>
<script>
    $.parseDone(function() {
        // 应急预案工作组数据初始化
        initEmerGroup();
    });

    /**
     * 表单数据填充
     */
    function initEmerGroup() {
        var preplanId = '${preplanId}';// 应急预案编号
        var id = '${id}';// 应急预案工作组编号

        if(id == null || id == '') {
            $("form[id='formId_group_edit']").find("#preplanId").textbox("setValue", preplanId);
            $("form[id='formId_group_edit']").find("#id").textbox("setValue", "");
        } else {
            var url = "${ctx}/emergency/groupManage/queryById.json";
            var params = {};
            params['id'] = id;

            var callBack = function(data) {
                if (data.success) {
                    fillEmerGroup(data.obj);
                } else {
                    $.messageQueue({ message : data.msg, cls : "warning", iframePanel : true, type : "info" });
                }
            };
            ajaxTodo(url, params, callBack);
        }
    }

    /**
     * 填充数据
     * @param emerGroup
     */
    function fillEmerGroup(emerGroup) {
        var formObj = $("form[id='formId_group_edit']");
        formObj.find("#id").textbox("setValue", emerGroup.id);
        formObj.find("#preplanId").textbox("setValue", emerGroup.preplanId);
        formObj.find("#name").textbox("setValue", emerGroup.name);
        formObj.find("#source").combobox("setValue", emerGroup.source);
        formObj.find("#notice").textbox("setValue", emerGroup.notice);
    }
</script>
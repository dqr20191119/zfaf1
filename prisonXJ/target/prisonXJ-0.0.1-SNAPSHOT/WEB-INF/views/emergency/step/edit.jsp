<%@page import="com.cesgroup.prison.common.bean.user.UserBean" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.cesgroup.prison.common.facade.AuthSystemFacade" %>
<%@ page import="com.cesgroup.prison.code.utils.CodeFacade" %>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<script src="${ctx}/static/system/common.js"></script>
<div>
    <cui:form id="formId_step_edit" heightStyle="fill">
        <cui:input type='hidden' id="preplanId" name="preplanId" />
        <cui:input type='hidden' id="id" name="id" />
        <table class="table" style="width: 98%;">
            <tr>
                <th>名称</th>
                <td>
                    <cui:input id="name" name="name" type="text" required="true" componentCls="form-control"/>
                </td>
            </tr>
            <tr>
                <th>描述：</th>
                <td>
                    <cui:textarea id="remarks" name="remarks" componentCls="form-control" height="130"></cui:textarea>
                </td>
            </tr>
        </table>
    </cui:form>
</div>
<script>
    $.parseDone(function() {
        // 应急预案工作组数据初始化
        initEmerStep();
    });

    /**
     * 表单数据填充
     */
    function initEmerStep() {
        var preplanId = '${preplanId}';// 应急预案编号
        var id = '${id}';// 应急预案工作组编号

        if(id == null || id == '') {
            $("form[id='formId_step_edit']").find("#preplanId").textbox("setValue", preplanId);
            $("form[id='formId_step_edit']").find("#id").textbox("setValue", "");
        } else {
            var url = "${ctx}/emergency/stepManage/queryById.json";
            var params = {};
            params['id'] = id;

            var callBack = function(data) {
                if (data.success) {
                    fillEmerStep(data.obj);
                } else {
                    $.messageQueue({ message : data.msg, cls : "warning", iframePanel : true, type : "info" });
                }
            };
            ajaxTodo(url, params, callBack);
        }
    }

    /**
     * 填充数据
     * @param emerStep
     */
    function fillEmerStep(emerStep) {
        var formObj = $("form[id='formId_step_edit']");
        formObj.find("#id").textbox("setValue", emerStep.id);
        formObj.find("#preplanId").textbox("setValue", emerStep.preplanId);
        formObj.find("#name").textbox("setValue", emerStep.name);
        /* formObj.find("#groupId").combobox("setValue", emerStep.groupId); */
        formObj.find("#remarks").textbox("setValue", emerStep.remarks);
    }
</script>
<%@page import="com.cesgroup.prison.common.bean.user.UserBean" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.cesgroup.prison.common.facade.AuthSystemFacade" %>
<%@ page import="com.cesgroup.prison.code.utils.CodeFacade" %>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<script src="${ctx}/static/system/common.js"></script>
<style>

    th{
        text-align:right;
    }
</style>
<div style="height: 100%">
    <cui:form id="formId_preplan_edit" heightStyle="fill">
        <cui:input type='hidden' id="id" name="id" />
        <table class="table" style="width: 98%;">
            <tr>
                <th>预案名称</th>
                <td>
                    <cui:input id="name" name="name" type="text" required="true" componentCls="form-control"/>
                </td>
            </tr>
            <tr>
                <th>数据来源</th>
                <td>
                    <cui:combobox id="source" name="source" data="preplanSourceData" required="true" componentCls="form-control"></cui:combobox>
                </td>
            </tr>
            <tr>
                <th>备注</th>
                <td>
                    <cui:textarea id="remarks" name="remarks" height="150" componentCls="form-control"></cui:textarea>
                </td>
            </tr>
        </table>
    </cui:form>
    <div class="dialog-buttons" style="margin-bottom: 0px; text-align: center;">
        <cui:button label="保存"  onClick="saveOrUpdate" componentCls="btn-primary"></cui:button>
        <cui:button label="重置"  onClick="reset"></cui:button>
    </div>
</div>
<script>
    $.parseDone(function() {
        // 应急预案数据初始化
        initEmerPreplan();
    });

    /**
     * 表单数据填充
     */
    function initEmerPreplan() {
        var id = '${id}';// 应急预案编号

        if(id == null || id == '') {
            $("form[id='formId_preplan_edit']").find("#id").textbox("setValue", "");
        } else {
            var url = "${ctx}/emergency/preplanManage/queryById.json";
            var params = {};
            params['id'] = id;

            var callBack = function(data) {
                if (data.success) {
                    fillEmerPreplan(data.obj);
                } else {
                    $.messageQueue({ message : data.msg, cls : "warning", iframePanel : true, type : "info" });
                }
            };
            ajaxTodo(url, params, callBack);
        }
    }

    /**
     * 填充应急预案数据
     * @param emerPreplan
     */
    function fillEmerPreplan(emerPreplan) {
        var formObj = $("form[id='formId_preplan_edit']");
        formObj.find("#id").textbox("setValue", emerPreplan.id);
        formObj.find("#name").textbox("setValue", emerPreplan.name);
        formObj.find("#source").combobox("setValue", emerPreplan.source);
        formObj.find("#remarks").textbox("setValue", emerPreplan.remarks);
    }
    function reset() {
        $("#formId_preplan_edit").form("reset");
    }
</script>
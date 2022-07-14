<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@page import="com.cesgroup.prison.code.utils.CodeFacade" %>
<%@taglib prefix="cui" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<body style="height: 100%">
<div style="height: 98%; margin: 5px">
    <div style="height:100%;">
        <div style="float: right">
            罪犯上报类型:
            <cui:input id="reportTypeName" name="reportTypeName"></cui:input>
            <cui:button label="查 询" onClick="searchInfo" cls="cyanbtn"></cui:button>
            <cui:button label="重 置" onClick="reset" cls="btn-danger"></cui:button>
        </div>
        <div style="margin: 5px;">
            <cui:button label="新 增" onClick="addIn" cls="greenbtn"></cui:button>
            <cui:button label="修 改" onClick="updateInAndOut" cls="cyanbtn"></cui:button>
            <cui:button label="删 除" onClick="remove" cls="deleteBtn"></cui:button>
        </div>
        <div style="height: 90%;">
            <cui:grid id="gridId_sblx" fitStyle="fill" multiselect="true" colModel="gridId_sblx_colModel" onDblClickRow="showInfo">
                <cui:gridPager gridId="gridId_sblx"/>
            </cui:grid>
        </div>
    </div>
</div>
<cui:dialog id="dialogId_sblx" iframePanel="true" autoOpen="false" resizable="false" reLoadOnOpen="true"
            modal="true"></cui:dialog>
</body>
<script>

    var jsConst = window.top.jsConst;
    var cusNumber = jsConst.CUS_NUMBER;
    var userId = jsConst.USER_ID;   //当前登陆者
    var realName = jsConst.REAL_NAME;
    var gridId_sblx_colModel = [{label: "id", name: "id", width: 70, key: true, hidden: true},
        {name: "prtTypeName", label: "罪犯类型名称", align: "center", width: 80},
        {name: "prtRemark", label: "备注", align: "center", width: 80}
    ];

    $.parseDone(function () {
        searchInfo();
    });

    function searchInfo() {
        var reportTypeName = $("#reportTypeName").textbox("getValue");
        var url = "${ctx}/xxhj/zfsjsb/sblx/listAll.json?prtCusNumber="+ jsConst.CUS_NUMBER+ "&prtTypeName=" + reportTypeName ;
        $("#gridId_sblx").grid("reload", url);
    }

    function reset() {
        $("#reportTypeName").textbox("setValue", "");
    }

    function addIn() {
        open(1000, 680, "${ctx}/xxhj/zfsjsb/sblx/save", "新增罪犯上报类型");
    }

    function updateInAndOut() {
        var sel = $('#gridId_sblx').grid("option", "selarrrow");
        if (sel.length < 1) {
            $.message({
                message: "请先勾选需要编辑的记录！",
                cls: "warning"
            });
            return false;
        }
        if (sel.length > 1) {
            $.message({
                message: "请只勾选一条需要编辑的记录！",
                cls: "warning"
            });
            return false;
        }
        var rowData = $('#gridId_sblx').grid("getRowData", sel[0]);
        open(1000, 680, "${ctx}/xxhj/zfsjsb/sblx/save?id="+rowData.id, "修改罪犯上报类型");
    }

    function open(dialog_width, dialog_height, url, label) {
        if (dialog_width != 0 && dialog_height != 0) {
            $("#dialogId_sblx").dialog({
                width: dialog_width,
                height: dialog_height,
                url: url,
                title: label
            });
            $("#dialogId_sblx").dialog("open");
        }
    }

    function remove() {
        var ids = [];
        var sel = $("#gridId_sblx").grid("option", "selarrrow");
        for (var i = 0; i < sel.length; i++) {
            var rowData = $('#gridId_sblx').grid("getRowData", sel[i]);
            ids.push(rowData.id);
        }
        if (ids.length > 0) {
            $.confirm("是否删除选中的记录", "信息确认", function (confirm) {
                if (confirm) {
                    $.ajax({
                        url: "${ctx}/xxhj/zfsjsb/sblx/removeInfo",
                        type: "post",
                        data: {"ids": JSON.stringify(ids)},
                        dataType: 'json',
                        traditional: true,
                        success: function (data) {
                            if (data.success == true) {
                                $("#gridId_sblx").grid("reload");
                                $.message({message: data.msg, cls: "success"});
                            } else {
                                $.message({message: data.msg, cls: "warning"});
                            }
                        }
                    });
                }
            });
        } else {
            $.message({
                message: "请选择记录",
                cls: "warning"
            });
        }
    }

</script>
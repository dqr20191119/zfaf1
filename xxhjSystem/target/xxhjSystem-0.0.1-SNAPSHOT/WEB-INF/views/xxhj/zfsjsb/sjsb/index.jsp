<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@page import="com.cesgroup.prison.code.utils.CodeFacade" %>
<%@taglib prefix="cui" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<body style="height: 100%">
<div style="height: 98%; margin: 5px">
    <div style="height:100%;">
        <div style="float: right">
            汇报期数:
            <cui:datepicker id="cprTimesq" name="cprTime" dateFormat="yyyyMMdd"
                            width="150"></cui:datepicker>
            <cui:button label="查 询" onClick="searchInfo" cls="cyanbtn"></cui:button>
            <cui:button label="重 置" onClick="reset" cls="btn-danger"></cui:button>
        </div>
        <div style="margin: 5px;">
            <cui:button label="新 增" onClick="addIn" cls="greenbtn"></cui:button>
            <cui:button label="修 改" onClick="updateInAndOut" cls="cyanbtn"></cui:button>
            <cui:button label="删 除" onClick="remove" cls="deleteBtn"></cui:button>
        </div>
        <div style="height: 90%;">
            <div id="gridId_sjsb"></div>
        </div>
    </div>
</div>
<cui:dialog id="dialogId_sjsb" iframePanel="true" autoOpen="false" resizable="false" reLoadOnOpen="true"
            modal="true"></cui:dialog>
</body>

<script>

    var jsConst = window.top.jsConst;
    var cusNumber = jsConst.CUS_NUMBER;
    var userId = jsConst.USER_ID;   //当前登陆者
    var realName = jsConst.REAL_NAME;

    $.parseDone(function () {
        $.ajax({
            type: 'post',
            url: '${ctx}/common/authsystem/findAllChildrenOrgByJyKeyForCombobox?cusNumber=' + cusNumber,
            data: '',
            dataType: 'json',
            success: function (data) {
                departS = data;
                var gridId_sjsb_colModel = [
                    {name: "cprCusNumber", label: "监狱名称",  align: "center", width: 80,hidden:true },
                    {name: "cprTime", label: "汇报期数", align: "center", width: 80 },
                    {name: "cprDprtmntId", label: "部门名称", align: "center", width: 80,hidden:true},
                    {name: "cprDprtmntName", label: "部门名称", align: "center", width: 80,formatter:function (cellValue, options, rowObject) {
                        for(obj in departS){
                            if(departS[obj].value == rowObject.cprDprtmntId) {
                                return departS[obj].text;
                            }
                        }
                    }},
                    {name: "cprRemark", label: "备注", align: "center", width: 80}
                ];
                var _setting={
                    colModel: gridId_sjsb_colModel,
                    multiselect: true,
                    fitStyle: "fill"
                };
                $('#gridId_sjsb').grid(_setting);
                searchInfo();
            }
        });
    });

    function searchInfo() {
        var cprTime = $("#cprTimesq").datepicker("getValue");
        var url = "${ctx}/xxhj/zfsjsb/sjsb/listAll.json?cprCusNumber="+ jsConst.CUS_NUMBER+ "&cprTime=" + cprTime ;
        if(jsConst.USER_LEVEL == 3){
            url = url + "&cprDprtmntId=" + jsConst.DEPARTMENT_ID;
        }
        $("#gridId_sjsb").grid("reload", url);
    }

    function reset() {
        $("#cprTimesq").datepicker("setValue", "");
    }

    function addIn() {
        open(1000, 680, "${ctx}/xxhj/zfsjsb/sjsb/save?flag=0", "数据上报");
    }

    function updateInAndOut() {
        var sel = $('#gridId_sjsb').grid("option", "selarrrow");
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
        var rowData = $('#gridId_sjsb').grid("getRowData", sel[0]);
        open(1000, 680, "${ctx}/xxhj/zfsjsb/sjsb/save?flag=1" +"&cprRemark=" + encodeURI(rowData.cprRemark) + "&cprTime=" + rowData.cprTime + "&cprDprtmntId=" + rowData.cprDprtmntId, "修改上报数据");
    }

    function open(dialog_width, dialog_height, url, label) {
        if (dialog_width != 0 && dialog_height != 0) {
            $("#dialogId_sjsb").dialog({
                width: dialog_width,
                height: dialog_height,
                url: url,
                title: label
            });
            $("#dialogId_sjsb").dialog("open");
        }
    }

    function remove() {
        var infos = [];
        var sel = $("#gridId_sjsb").grid("option", "selarrrow");
        for (var i = 0; i < sel.length; i++) {
            var rowData = $('#gridId_sjsb').grid("getRowData", sel[i]);
            infos.push({
                cprCusNumber : rowData.cprCusNumber,
                cprTime : rowData.cprTime,
                cprDprtmntId : rowData.cprDprtmntId
            });
        }
        if (infos.length > 0) {
            $.confirm("是否删除选中的记录", "信息确认", function (confirm) {
                if (confirm) {
                    $.ajax({
                        url: "${ctx}/xxhj/zfsjsb/sjsb/removeInfo",
                        type: "post",
                        data: {"infos": JSON.stringify(infos)},
                        dataType: 'json',
                        traditional: true,
                        success: function (data) {
                            if (data.success == true) {
                                $("#gridId_sjsb").grid("reload");
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
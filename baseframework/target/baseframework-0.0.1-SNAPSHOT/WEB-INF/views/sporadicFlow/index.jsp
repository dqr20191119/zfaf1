<%@page import="com.cesgroup.prison.code.utils.CodeFacade" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="cui" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<div style="height: 98%; margin: 5px">
<cui:toolbar id="toolbarId" data="toolbar_localDate"></cui:toolbar>
        <cui:grid
                id="gridId_sporadicflow" singleselect="true" multiselect="true" fitStyle="fill"
                colModel="gridId_sporadicflow_colModelDate"> <cui:gridPager
                gridId="gridId_sporadicflow"/> </cui:grid>
        <cui:dialog id="dialogId_sporadicflow" autoOpen="false" iframePanel="true" resizable="false" reLoadOnOpen="true"
                    modal="true"></cui:dialog>
</div>
<script> var jsConst = window.top.jsConst;
var cusNumber = jsConst.CUS_NUMBER;
/*监狱号*/
var userId = jsConst.USER_ID;
//部门
var departmentId = jsConst.DEPARTMENT_ID;

//等级
var userLevel = jsConst.USER_LEVEL;

/*当前登陆者*/
//var flowStts =  <%=CodeFacade.loadCode2Json("4.20.31")%>;
var flowStts = [{
    "hidden": false,
    "isValid": "1",
    "jianpinLower": "dcf",
    "jianpinUpper": "DCF",
    "text": "待出发",
    "value": "1"
},
    {"hidden": false, "isValid": "1", "jianpinLower": "tz", "jianpinUpper": "TZ", "text": "途中", "value": "2"},
    {"hidden": false, "isValid": "1", "jianpinLower": "dd", "jianpinUpper": "DD", "text": "已到达", "value": "3"},
    {"hidden": false, "isValid": "1", "jianpinLower": "fh", "jianpinUpper": "DD", "text": "已返回", "value": "4"}
];
/*审核状态*/
var checkStts = <%=CodeFacade.loadCode2Json("4.0.5")%>;
/*登记类型*/
var flowType = <%=CodeFacade.loadCode2Json("4.20.30")%>;
$.parseDone(function () {
    $("#gridId_sporadicflow").grid("reload", "${ctx}/sporadicFlow/listAll.json?sflCusNumber=" + cusNumber + "&sflCrteDprtmntId=" + departmentId);
});
var gridId_sporadicflow_colModelDate = [{label: "ID", name: "ID", key: true, hidden: true}, {
    name: "SFL_CRTE_TIME",
    label: "发起时间",
}, {name: "SFL_CRTE_USER", label: "登记人",}, {name: "SFL_DPRTMNT", label: "监区",}, {
    name: "SFL_START_TIME",
    label: "出发时间",
}, {name: "SFL_START_ADDRS", label: "出发区域",}, {name: "SFL_END_TIME", label: "到达时间",}, {
    name: "SFL_END_ADDRS",
    label: "到达区域",
}, {
    name: "SFL_BACK_TIME",
    label: "返回时间",
}, {name: "SFL_OFFENDER_NUM", label: "罪犯人数",}, {name: "SFL_POLICE_NUM", label: "民警人数",}, {
    name: "SFL_FLOW_TYPE",
    label: "登记类型",
    formatter: "convertCode",
    revertCode: true,
    formatoptions: {'data': flowType}
}, {
    name: "SFL_FLOW_STTS",
    label: "流动状态",
    formatter: "convertCode",
    revertCode: true,
    formatoptions: {'data': flowStts}
}, {
    name: "SFL_FLOW_CHECK_STTS",
    label: "审核状态",
    formatter: "convertCode",
    revertCode: true,
    formatoptions: {'data': checkStts}
}, {name: "SFL_CHECK_USER", label: "审核人",}, {name: "SFL_CHECK_TIME", label: "审核时间",}];
toolbar_localDate = [
    {
        "type": "button",
        "id": "btnId_add",
        "label": "发起",
        "onClick": "openDailog",
        "componentCls": "btn-primary"
    }, {
        "type": "button",
        "id": "btnId_update",
        "label": "编辑",
        "onClick": "openDailog",
        "componentCls": "btn-primary"
    }, /*{
     "type": "button",
     "id": "btnId_del",
     "label": "删除",
     "onClick": "remove",
     "componentCls": "btn-primary"
     },*/ {
        "type": "button",
        "id": "btnId_depart",
        "label": "出发",
        "onClick": "updateStts",
        "componentCls": "btn-primary"
    }, {
        "type": "button",
        "id": "btnId_arrive",
        "label": "已到达",
        "onClick": "updateStts",
        "componentCls": "btn-primary"
    },
    {
        "type": "button",
        "id": "btnId_back",
        "label": "已返回",
        "onClick": "openDailog",
        "componentCls": "btn-primary"
    }, {
        "type": "button",
        "id": "btnId_check",
        "label": "审核",
        "onClick": "openDailog",
        "componentCls": "btn-primary"
    }, {
        "type": "button",
        "id": "btnId_show",
        "label": "查看",
        "onClick": "openDailog",
        "componentCls": "btn-primary"
    }];
function openDailog(event, ui) {
    var dialog_width = 0;
    var dialog_height;
    var url;
    var title;
    switch (ui.id) {
        case "btnId_add":
            dialog_width = "1030";
            dialog_height = "635";
            title = "发起";
            url = "${ctx}/sporadicFlow/openDialog/register";
            break;
        case "btnId_update":
            var selected = $("#gridId_sporadicflow").grid("option", "selarrrow");
            if (selected.length == 1) {
                var rowData = $("#gridId_sporadicflow").grid("getRowData", selected[0]);
                if (rowData.SFL_FLOW_CHECK_STTS != "" || rowData.SFL_FLOW_STTS != "1") {
                    $.message({
                        message: "此记录不能做编辑操作",
                        cls: "warning"
                    });
                    return;
                }


                var rowData = $("#gridId_sporadicflow").grid("getRowData", selected[0]);
                dialog_width = "1030";
                dialog_height = "635";
                title = "编辑";
                url = "${ctx}/sporadicFlow/openDialog/edit?id=" + rowData.ID;
            } else {
                $.messageQueue({
                    message: "请选择一条要修改的记录",
                    cls: "warning",
                    iframePanel: true,
                    type: "info"
                });
            }

            break;

        case "btnId_check":
            var selected = $("#gridId_sporadicflow")
                    .grid("option", "selarrrow");
            if (selected.length == 1) {
                var rowData = $("#gridId_sporadicflow").grid("getRowData", selected[0]);

                if (rowData.SFL_FLOW_CHECK_STTS != "") {
                    //审核状态为通过，不可审核
                    $.message({
                        message: "请选择一条要审核的记录",
                        cls: "warning"
                    });
                    return;
                }
                debugger;
                dialog_width = "520";
                dialog_height = "620";
                title = "审核";
                url = "${ctx}/sporadicFlow/openDialog/check?id=" + rowData.ID;
            } else {
                $.message({
                    message: "请选择一条要审核的记录",
                    cls: "warning"
                });
            }

            break;
        case "btnId_back":
            var selected = $("#gridId_sporadicflow")
                    .grid("option", "selarrrow");
            if (selected.length == 1) {
                var rowData = $("#gridId_sporadicflow").grid("getRowData", selected[0]);

                if (rowData.SFL_FLOW_STTS != "3") {
                    $.message({
                        message: "请选择一条已到达的记录",
                        cls: "warning"
                    });
                    return;
                }
                debugger;
                dialog_width = "520";
                dialog_height = "620";
                title = "已返回";
                url = "${ctx}/sporadicFlow/openDialog/back?id=" + rowData.ID;
            } else {
                $.message({
                    message: "请选择一条要审核的记录",
                    cls: "warning"
                });
            }

            break;
        case "btnId_show":
            var selected = $("#gridId_sporadicflow")
                    .grid("option", "selarrrow");
            if (selected.length == 1) {
                var rowData = $("#gridId_sporadicflow").grid("getRowData", selected[0]);
                dialog_width = "520";
                dialog_height = "600";
                title = "查看";
                url = "${ctx}/sporadicFlow/openDialog/show?id=" + rowData.ID;
            } else {
                $.message({
                    message: "请选择一条要查看的记录",
                    cls: "warning"
                });
            }

            break;

        default:
            break;
    }

    if (dialog_width != 0) {
        $("#dialogId_sporadicflow").dialog({
            width: dialog_width,
            height: dialog_height,
            title: title,
            url: url,
            onClose: function () {
                //回调事件
            }
        });
        $("#dialogId_sporadicflow").dialog("open");
    }
}

function remove() {
    var selected = $("#gridId_sporadicflow").grid("option", "selarrrow");
    var selectedIf = selected;
    debugger;
    for (var i = 0; i < selectedIf.length; i++) {
        var rowData = $("#gridId_sporadicflow").grid("getRowData", selectedIf[i]);
        if (rowData.SFL_FLOW_CHECK_STTS != "" || rowData.SFL_FLOW_STTS != "1") {

            selected.splice(i, 1);
        }
    }

    if (selected.length > 0) {
        $.confirm("是否删除选中的记录", "信息确认", function (confirm) {
            if (confirm) {


                $.ajax({
                    url: "${ctx}/sporadicFlow/delete.json",
                    dataType: 'json',
                    type: 'post',
                    data: JSON.stringify(selected),
                    contentType: "application/json",
                    success: function (data) {
                        if (data.code == 1) {
                            $("#gridId_sporadicflow").grid("reload");
                            $.message({
                                message: "删除记录成功",
                                cls: "success"
                            });
                        } else {
                            $.message({
                                message: "删除记录失败",
                                cls: "warning"
                            });
                        }
                    }
                });
            }
        });
    } else {
        $.message({
            message: "请选择可删除的的记录",
            cls: "warning"
        });
    }
}

//修改流动状态 ：出发、 到达
function updateStts(event, ui) {
    var selected = $("#gridId_sporadicflow").grid("option", "selarrrow");
    if (selected.length == 1) {
        var updDate = {};
        var rowData = $("#gridId_sporadicflow").grid("getRowData", selected[0]);
        if (rowData.SFL_FLOW_CHECK_STTS == "" || rowData.SFL_FLOW_CHECK_STTS == "0") {
            $.message({
                message: "请选择已通过审核的记录",
                cls: "warning"
            });
            return;
        }
        updDate["id"] = rowData.ID;
        if (ui.id == "btnId_depart") {
            if (rowData.SFL_FLOW_STTS == "1") {
                updDate["sflFlowStts"] = 2;//出发
            } else {
                $.message({
                    message: "请选择【待出发】状态记录",
                    cls: "warning"
                });
                return;
            }
        }
        else if (ui.id == "btnId_arrive") {

            if (rowData.SFL_FLOW_STTS == "2") {
                updDate["sflFlowStts"] = 3;//达到
            } else {
                $.message({
                    message: "请选择【途中】状态记录",
                    cls: "warning"
                });
                return;
            }
        }
        var url = "${ctx}/sporadicFlow/updateStts.json";
        $.ajax({
            type: "post",
            url: url,
            data: updDate,
            dataType: "json",
            success: function (data) {
                if (data.code == 1) {
                    $.message("操作成功");
                    $("#gridId_sporadicflow").grid("reload");
                } else {
                    $.message({
                        message: data.message,
                        cls: "warning"
                    });
                }
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                alert(textStatus);
            }
        });

    } else {
        $.message({
            message: "请选择一条要操作的记录",
            cls: "warning"
        });
    }
}
</script>
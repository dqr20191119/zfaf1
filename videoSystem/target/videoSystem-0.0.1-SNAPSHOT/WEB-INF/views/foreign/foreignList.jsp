<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="cui" tagdir="/WEB-INF/tags" %>
<%@ page import="com.cesgroup.prison.common.facade.AuthSystemFacade"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<body style="height: 100%">
<div style="height: 98%; margin: 5px">
    <div style="height:100%;">
        <div style="float: right">
            进出状态：
            <cui:combobox id="frType" name="frType" data="frTypeData" width="150"></cui:combobox>
            开始时间：
            <cui:datepicker id="frTimeStart" name="frTimeStart" dateFormat="yyyy-MM-dd HH:mm:ss"
                            width="150"></cui:datepicker>
            至：
            <cui:datepicker id="frTimeEnd" name="frTimeEnd" dateFormat="yyyy-MM-dd HH:mm:ss"
                            width="150"></cui:datepicker>
            <cui:button label="查 询" onClick="searchInfo" cls="cyanbtn"></cui:button>
            <cui:button label="重 置" onClick="reset" cls="btn-danger"></cui:button>
        </div>
        <div style="margin: 5px;">
            <cui:button label="进入登记" onClick="addIn" cls="greenbtn"></cui:button>
            <cui:button label="修 改" onClick="updateInAndOut" cls="cyanbtn"></cui:button>
            <cui:button label="审 批" onClick="checkInfo" cls="cyanbtn"></cui:button>
            <cui:button label="删 除" onClick="remove" cls="deleteBtn"></cui:button>
            <cui:button label="离开" onClick="outInfo" cls="greenbtn"></cui:button>
        </div>
        <div style="height: 90%;">
            <cui:grid id="gridId_foreign" fitStyle="fill" multiselect="true" colModel="gridId_foreign_colModelDate" onDblClickRow="showInfo">
                <cui:gridPager gridId="gridId_foreign"/>
            </cui:grid>
        </div>
    </div>
</div>
<cui:dialog id="dialogId_foreign" iframePanel="true" autoOpen="false" resizable="false" reLoadOnOpen="true"
            modal="true"></cui:dialog>
</body>
<script>
    var jsConst = window.top.jsConst;
    var cusNumber = jsConst.CUS_NUMBER;
    var userId = jsConst.USER_ID;   //当前登陆者
    var realName = jsConst.REAL_NAME;

    var frTypeData = [{value: "0", text: "待进入","selected": "selected"}, {value: "1", text: "已进入"},{value: "2", text: "已离开"}];
    var combobox_bm = <%=AuthSystemFacade.getAllChildrenOrgJsonInfoByOrgKey(null)%>;
    var gridId_foreign_colModelDate = [{label: "id", name: "ID", width: 70, key: true, hidden: true},
        {name: "FR_TIME", label: "进入时间 ", align: "center", width: 80},
        {name: "reportPoliceName", label: "部门", align: "center", width: 60},
        {name: "policeName", label: "带队民警", align: "center", width: 90},
        {name: "FR_RECORD_PEOPLE", label: "检查民警", align: "center", width: 80},
        {name: "FR_CHECK_PEOPLE", label: "审批领导", align: "center", width: 80},
        {name: "FR_REASON", label: "事由", align: "center", width: 80},
        // {name: "FR_DIRECTION", label: "前往区域", align: "center", width: 80},
        {name: "FR_CARS_INFO", label: "车辆", align: "center", width: 80},
        //{name: "FR_PEOPLES_INFO", label: "人员", align: "center", width: 80},
        {name: "FR_OUT_TIME", label: "离开时间 ", align: "center", width: 80},
        {name: "FR_OUT_PEOPLE", label: "离开确认人 ", align: "center", width: 80},
        {name: "FR_TYPE", label: "进出状态", align: "center", width: 50, formatter: typeFormatter},
        {name: "FR_CHECK_STTS_INDC", label: "审批状态", align: "center", width: 80, formatter: typeCheckFormatter},
        {name: "", label: "详情", align: "center", width: 90,formatter:"formatterDetail"}
    ];

    $.parseDone(function () {
        var dt=new Date();
        dt.setHours(0);
        dt.setMinutes(0);
        dt.setSeconds(0);
        $('#frTimeStart').datepicker("setDate", dt);
        searchInfo();

    });
    function formatterDetail(cellValue,options,rowObject){
        var result="<span><a href='#' style='color: #4692f0;' onclick='showDetails(\""+rowObject.ID+"\");return false;'>详细</a></span>";
        return result;
    }
    function typeFormatter(cellvalue, options, rawObject) {
        if (cellvalue == '0') {
            return "待进入";
        } else if (cellvalue == '1') {
            return "已进入";
        } else if (cellvalue == '2') {
            return "已离开";
        } else {
            return "";
        }
    }

    function typeCheckFormatter(cellvalue, options, rawObject){
        if (cellvalue == '0' || cellvalue == '' || cellvalue == null) {
            return "<font color='#ff9900'>未审批</font>";
        } else if (cellvalue == '1') {
            return "<font color='green'>已同意</font>";
        } else if (cellvalue == '2') {
            return "<font color='red'>不同意</font>";
        }
        else {
            return "";
        }
    }

    function open(dialog_width, dialog_height, url, label) {
        if (dialog_width != 0 && dialog_height != 0) {
            $("#dialogId_foreign").dialog({
                width: dialog_width,
                height: dialog_height,
                url: url,
                title: label
            });
            $("#dialogId_foreign").dialog("open");
        }
    }

    function addIn() {
        open(800, 680, "${ctx}/foreign/save?flag=0", "进入人员车辆登记");
    }

    function addOut() {
        var sel = $('#gridId_foreign').grid("option", "selarrrow");
        if(sel.length>0){
            var rowData = $('#gridId_foreign').grid("getRowData", sel[0]);
            if(rowData.FR_TYPE=="已进入"){
                open(800, 680, "${ctx}/foreign/save?flag=0&id=" + rowData.ID, "离开人员车辆登记");
            }else{
                $.message({
                    message: "请选择已进入人车信息！",
                    cls: "warning"
                });
            }
        }
        else{
            $.message({
                message: "请选择已进入人车信息！",
                cls: "warning"
            });
            //open(800, 680, "${ctx}/foreign/save?flag=1", "离开人员车辆登记");
        }
    }

    function updateInAndOut() {
        var sel = $('#gridId_foreign').grid("option", "selarrrow");
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
        var rowData = $('#gridId_foreign').grid("getRowData", sel[0]);
        if (rowData.FR_CHECK_STTS_INDC=='<font color="#ff9900">未审批</font>') {
            var url = "${ctx}/foreign/save?id=" + rowData.ID;
            open(800, 680, url, "外来人员车辆修改");
        }else{
            $.message({
                message: "已审批信息，不能修改",
                cls: "warning"
            });
        }
    }

    function checkInfo() {
        var sel = $("#gridId_foreign").grid("option", "selarrrow");
        if (sel.length < 1) {
            $.message({
                message: "请先勾选需要审核的记录",
                cls: "warning"
            });
            return false;
        }
        if (sel.length > 1) {
            $.message({
                message: "请只勾选一条需要审核的记录！",
                cls: "warning"
            });
            return false;
        }
        var rowData = $('#gridId_foreign').grid("getRowData", sel[0]);
        if (rowData.FR_CHECK_STTS_INDC == '<font color="#ff9900">未审批</font>') {
            var url = "${ctx}/foreign/save?flag=1&id=" + rowData.ID;
            open(800, 680, url, "外来人员车辆审批");
        }else{
            $.message({
                message: "已审批信息，不能再次审批",
                cls: "warning"
            });
        }
    }

    function outInfo() {
        var sel = $("#gridId_foreign").grid("option", "selarrrow");
        if (sel.length < 1) {
            $.message({
                message: "请先勾选已进入的记录",
                cls: "warning"
            });
            return false;
        }
        if (sel.length > 1) {
            $.message({
                message: "请只勾选一条已进入的记录！",
                cls: "warning"
            });
            return false;
        }
        var rowData = $('#gridId_foreign').grid("getRowData", sel[0]);
        if (rowData.FR_TYPE == "已进入") {
            $.confirm("是否确认离开！", "信息确认", function (confirm) {
                if (confirm) {
                    $.ajax({
                        type: "post",
                        url: "${ctx}/foreign/outInfo",
                        data: {id: rowData.ID},
                        dataType: "json",
                        success: function (data) {
                            if (data.success) {
                                $.messageQueue({message: data.msg, cls: "success", iframePanel: true, type: "info"});
                                $("#gridId_foreign").grid("reload");
                            } else {
                                $.messageQueue({message: data.msg, cls: "warning", iframePanel: true, type: "info"});
                            }
                        }
                    });
                }
            });

        }else{
            $.message({
                message: "请只勾选一条已进入的记录!",
                cls: "warning"
            });
        }
    }
    
    function showInfo(e, ui) {
        console.log(ui.rowId);
        var url = "${ctx}/foreign/openDialog/show?id=" + ui.rowId;
        open(800, 680, url, "外来人员车辆审批");
    }
    function showDetails (id) {
        var url = "${ctx}/foreign/openDialog/show?id=" + id;
        open(800, 680, url, "外来人员车辆审批");
    }

    function remove() {
        var ids = [];
        var sel = $("#gridId_foreign").grid("option", "selarrrow");
        for (var i = 0; i < sel.length; i++) {
            var rowData = $('#gridId_foreign').grid("getRowData", sel[i]);
            console.log(rowData.FR_CHECK_STTS_INDC);
            if (rowData.FR_CHECK_STTS_INDC == '<font color="#ff9900">未审批</font>') {
                ids.push(rowData.ID);
            }
        }
        console.log(JSON.stringify(ids));
        if (ids.length > 0) {
            $.confirm("是否删除选中的记录", "信息确认", function (confirm) {
                if (confirm) {
                    $.ajax({
                        url: "${ctx}/foreign/removeInfo",
                        type: "post",
                        data: {"ids": JSON.stringify(ids)},
                        dataType: 'json',
                        traditional: true,
                        success: function (data) {
                            if (data.success == true) {
                                $("#gridId_foreign").grid("reload");
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
                message: "请选择未审批的记录",
                cls: "warning"
            });
        }
    }


    function reset() {
        $("#frType").combobox("setValue", null);
        $("#frTimeStart").datepicker("setValue", "");
        $("#frTimeEnd").datepicker("setValue", "");
    }


    function searchInfo() {
        var frType = $("#frType").combobox("getValue");
        console.log(frType);
        var frTimeStart = $("#frTimeStart").datepicker("getValue");
        var frTimeEnd = $("#frTimeEnd").datepicker("getValue");
        var url = "${ctx}/foreign/listAll.json?frCusNumber="+ jsConst.CUS_NUMBER+ "&frType=" + frType +"&frTimeStart="+frTimeStart+"&frTimeEnd="+frTimeEnd;
        $("#gridId_foreign").grid("reload", url);
    }


    //双击详情
    function detail(e, ui) {
        var url = "${ctx}/foreign/detail?id=" + ui.rowId;
        open(800, 680, url, "外来车辆详情");
    }

</script>
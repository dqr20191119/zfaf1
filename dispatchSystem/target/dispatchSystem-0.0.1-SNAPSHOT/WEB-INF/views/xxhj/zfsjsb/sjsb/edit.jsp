<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="cut" uri="http://www.springframework.org/tags/form" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <style>
        td, tr {
            padding-top: 3px;
        }
    </style>
</head>
<body>
<div id="save" class="dialog">
    <div style="text-align: left; margin-left: 5px; margin-top: 1px;margin-bottom: 1px">
        <cui:button label="保  存" onClick="saveOrUpdateInfo" id="saveInfo" width="55"
                    cls="greenbtn"></cui:button>
    </div>
    <fieldset id="zfsjsbdj">
        <legend>数据上报登记</legend>
        <cui:form id="formId_zfsjsb" style="padding:0;margin-bottom: 10px;margin-right:10px">
            <table style="width: 100%;">
                <tr>
                    <td align="right" style="width:170px; height: 25px;">汇报期数：</td>
                    <td align="left" ><cui:datepicker id="cprTime" name="cprTime" dateFormat="yyyyMMdd"
                                                      width="150" required="true" readonly="true"></cui:datepicker></td>

                    <td align="right" style="width:170px; height: 25px;">部门名称：</td>
                    <td align="left" ><cui:combobox id="cprDprtmntId"
                                                    name="cprDprtmntId" required="true"
                                                    width="160" onChange="cprDprtmntIdSelect"></cui:combobox></td>
                </tr>
                <tr>
                    <td align="right" style="height: 25px">备注：</td>
                    <td align="left"><cui:textarea id="cprRemark" componentCls="form-control" name="cprRemark"
                                                   height="120"></cui:textarea></td>
                </tr>
            </table>
        </cui:form>
    </fieldset>
    <fieldset id="sjsbdj">
        <legend>数据上报登记</legend>
        <div id="gridId_zfsjsb"></div>
    </fieldset>
</div>
<script>
    var jsConst = window.top.jsConst;
    var cusNumber = jsConst.CUS_NUMBER;
    var cprTime ;
    var flag = "${flag}";
    var cprDprtmntId;

    var gridId_zfsjsb_colModel = [{
        name: "cprTypeCode",
        label: "数据类型",
        align: "center",
        width: 120,
        hidden: true
    }, {
        name: "cprTypeName",
        label: "数据类型名称",
        align: "center",
        width: 80
    },{
        name: "cprNumber",
        label: "数值",
        sortable:false,
        formatter: "text",
        formatoptions: {
            'required': true
        },
        align: "center",
        width: 50
    }];
    var _setting={
        colModel:gridId_zfsjsb_colModel,
        fitStyle:'width',
        height: 320,
        editableRows: false,        //此处是行编辑。不要和单元格编辑混用
        onLoad: function(e, ui) {
        }
    };
    $('#gridId_zfsjsb').grid(_setting);

    $.parseDone(function () {
        if (flag == "0") {
            $('#cprTime').datepicker("setDate", new Date());
            cprTime = $('#cprTime').datepicker("getValue");
            cprDprtmntId = jsConst.DEPARTMENT_ID;
        } else {
            cprTime = "${cprTime}";
            $('#cprTime').datepicker("setValue", cprTime);
            cprDprtmntId = "${cprDprtmntId}";
            $('#cprRemark').textbox("setValue", "${cprRemark}");
            $('#cprDprtmntId').combobox("option", 'readonly', true);
        }
        $("#gridId_zfsjsb").grid("reload", "${ctx}/xxhj/zfsjsb/sjsb/listDetail.json?cprTime="+cprTime+"&cprDprtmntId="+cprDprtmntId);
        $.ajax({
            type: 'post',
            url: '${ctx}/common/authsystem/findAllChildrenOrgByJyKeyForCombobox?cusNumber=' + cusNumber,
            data: '',
            dataType: 'json',
            success: function (data) {
                $('#cprDprtmntId').combobox("reload",
                    {
                        data: data,
                        onLoad: function () {
                            if (flag == "0") {
                                $("#cprDprtmntId").combobox("setValue", jsConst.DEPARTMENT_ID);
                            }else{
                                $("#cprDprtmntId").combobox("setValue", cprDprtmntId);
                            }
                        }
                    });
            }
        });
    });

    function saveOrUpdateInfo() {
        var zfsjsbGridData = $("#gridId_zfsjsb").grid("getRowData");
        var registerData = $("#formId_zfsjsb").form("formData");
        if(zfsjsbGridData.length <= 0 ) {
            return ;
        }
        registerData.sbsjList = zfsjsbGridData;
        console.log(registerData);
        $.loading({text:"正在处理中，请稍后..."});
        $.ajax({
            type: "post",
            url: "${ctx}/xxhj/zfsjsb/sjsb/register.json",
            contentType: "application/json",
            data: JSON.stringify(registerData),
            dataType: "json",
            success: function (data) {
                $.loading("hide");
                if (data.success) {
                    $.messageQueue({message: data.msg, cls: "success", iframePanel: true, type: "info"});
                    $("#gridId_sjsb").grid("reload");
                    $("#dialogId_sjsb").dialog("close");
                } else {
                    $.messageQueue({message: data.msg, cls: "warning", iframePanel: true, type: "info"});
                }
            },
            error:function () {
                $.loading("hide");
            }
        });
    }

    function cprDprtmntIdSelect(event, ui) {
        $("#gridId_zfsjsb").grid("clearGridData");
        $("#gridId_zfsjsb").grid("reload", "${ctx}/xxhj/zfsjsb/sjsb/listDetail.json?cprTime="+cprTime+"&cprDprtmntId="+ui.value);
    }

</script>
</body>
</html>
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
    <fieldset id="zfsblxdj">
        <legend>上报类型登记</legend>
        <cui:form id="formId_sblxxx" style="padding:0;margin-bottom: 10px;margin-right:10px">
            <table style="width: 100%;">
                <input type="hidden" id="id" name="id"/>
                <tr>
                    <td align="right" style="width:170px; height: 25px;">上报类型名称：</td>
                    <td align="left" ><cui:input id="prtTypeName" name="prtTypeName"
                                                                    width="280" required="true"></cui:input></td>
                </tr>
                <tr>
                    <td align="right" style="height: 25px">备注：</td>
                    <td align="left"><cui:textarea id="prtRemark" componentCls="form-control" name="prtRemark"
                                                               height="120"></cui:textarea></td>
                </tr>
            </table>
        </cui:form>
    </fieldset>
    <fieldset id="sybmdj">
        <legend>使用部门登记</legend>
        <div id="gridId_depart"></div>
    </fieldset>
</div>
<script>

    var jsConst = window.top.jsConst;
    var cusNumber = jsConst.CUS_NUMBER;
    var rowDepartLength = 1;
    var departS;
    var gridId_depart_colModel ;

    $.parseDone(function () {
        if ("${id}" != "") {
            $("#formId_sblxxx").form("load", "${ctx}/xxhj/zfsjsb/sblx/SblxInfoById.json?id=${id}");
        }
        $.ajax({
            type: 'post',
            url: '${ctx}/common/authsystem/findAllChildrenOrgByJyKeyForCombobox?cusNumber=' + cusNumber,
            data: '',
            dataType: 'json',
            success: function (data) {
                departS = data;
                gridId_depart_colModel = [{
                    name: "prtdDprtmntId",
                    label: "使用部门",
                    align: "center",
                    width: 120,
                    formatter: "combobox",
                    formatoptions: {
                        'data': departS,
                        'onChange' : function(event, ui ) {
                            var newDepartId = ui.value;
                            var gridDepartIds = $('#gridId_depart').grid('getCol','prtdDprtmntId');
                            var newDepartCnt = 0;
                            for(var i in gridDepartIds) {
                                if(gridDepartIds[i] === newDepartId) {
                                    newDepartCnt++;
                                }
                            }
                            if(newDepartCnt > 1) {
                                $.message({
                                    message: ui.text + " 已配置",
                                    iframePanel: true
                                });
                                $(this).combobox('setValue', '');
                                $(this).combobox('setText', '');
                                return;
                            }
                        }
                    }
                }, {
                    name: "prtdIsCalc",
                    label: "是否参与数据统计",
                    align: "center",
                    width: 80,
                    formatter: "combobox",
                    formatoptions: {
                        'data': [{
                            'value': 1,
                            'text': '是'
                        }, {
                            'value': 0,
                            'text': '否'
                        }]
                    }
                },{
                    name: "cz",
                    sortable:false,
                    label: "<span style=color:red onclick=addDepartRow()>添加</span>",
                    align: "center",
                    width: 50,
                    formatter: function (value,cell) {
                        return "<a class='cui-icon-bin' href='#' onclick='delDepartRow(\""+cell.rowId+"\")'></a>"
                    }
                }];
               var _setting={
                    colModel:gridId_depart_colModel,
                    fitStyle:'width',
                    height: 320,
                    editableRows: false,        //此处是行编辑。不要和单元格编辑混用
                    onLoad: function(e, ui) {
                    }
                };
                $('#gridId_depart').grid(_setting);
                if ("${id}" != "") {
                    $("#gridId_depart").grid("reload", "${ctx}/xxhj/zfsjsb/sblxsz/listByTypeId.json?prtdTypeCode=${id}");
                }
            }
        });
    });

    function addDepartRow() {
        $("#gridId_depart").grid("addRowData", rowDepartLength, {
            "prtdIsCalc": 0
        });
        rowDepartLength++;
    }

    function delDepartRow(rowid) {
        $("#gridId_depart").grid("delRowData", rowid);
    }

    function saveOrUpdateInfo() {
        var departGridData = $("#gridId_depart").grid("getRowData");
        var registerData = $("#formId_sblxxx").form("formData");
        departGridData = departGridData.filter(function (value) {
            if (value.prtdDprtmntId == "") {
                return false;
            }
            return true;
        });
        registerData.sblxszList = departGridData;
        $.loading({text:"正在处理中，请稍后..."});
        $.ajax({
            type: "post",
            url: "${ctx}/xxhj/zfsjsb/sblx/register.json",
            contentType: "application/json",
            data: JSON.stringify(registerData),
            dataType: "json",
            success: function (data) {
                $.loading("hide");
                if (data.success) {
                    $.messageQueue({message: data.msg, cls: "success", iframePanel: true, type: "info"});
                    $("#gridId_sblx").grid("reload");
                    $("#dialogId_sblx").dialog("close");
                } else {
                    $.messageQueue({message: data.msg, cls: "warning", iframePanel: true, type: "info"});
                }
            },
            error:function () {
                $.loading("hide");
            }
        });
    }

</script>
</body>
</html>
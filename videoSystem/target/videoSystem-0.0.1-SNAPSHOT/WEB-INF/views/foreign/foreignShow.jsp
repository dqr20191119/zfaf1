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

    <%--shirinkToFit--%>
    <fieldset id="jxxdj">
        <legend>进出信息登记</legend>
        <cui:form id="formId_jcxx" style="padding:0px;">
            <table style="width: 100%; margin-left:-10px; margin-top:-25px; margin-right:3px;">
                <input type="hidden" id="id" name="id"/>
                <input type="hidden" id="frType" name="frType"/>
                <tr class="class_jccl" >
                    <td class="class_jccl" align="right" style="width:200px; height: 25px">出入时间：</td>
                    <td class="class_jccl" align="left" style="width:160px"><cui:datepicker id="frTime"
                                                                                            name="frTime"
                                                                                            dateFormat="yyyy-MM-dd HH:mm:ss"
                                                                                            required="true"
                                                                                            width="160"></cui:datepicker></td>
                    <td class="class_jccl" align="right" style="width: 120px">部门：</td>
                    <td class="class_jccl" align="left"><cui:combobox id="frReportPeople"
                                                                      name="frReportPeople"
                                                                      width="160"
                                                                      data="combobox_bm"
                                                                      required="true"
                                                                      onChange="dprtmnSelect"></cui:combobox>
                    </td>
                    <td class="class_jccl" align="right">带队民警：</td>
                    <td class="class_jccl" align="left">
                            <%--<cui:autocomplete id="frOprtnPolice" name="frOprtnPolice"--%>
                            <%--width="160" multiple="true"--%>
                            <%--onChange="fcdOprtnInPoliceIdSelect"></cui:autocomplete>--%>
                        <cui:autocomplete id="frOprtnPolice" name="frOprtnPolice" valueField="id"
                                          textField="name" multiple="true" postMode="value"
                                          multiLineLabel="true" height="30" width="160" required="true"></cui:autocomplete>
                    </td>
                </tr>
                <tr class="class_jccl">
                    <td class="class_jccl" align="right" style="width:100px; height: 25px">事由：</td>
                    <td class="class_jccl" align="left" style="width:100px">
                        <cui:combobox  id="frReason" name="frReason" enableSearch="true"
                                       width="160" required="true" data="reasonInfo"></cui:combobox></td>
                    <td class="class_jccl" align="right" style="width: 120px">检查民警：</td>
                    <td class="class_jccl" align="left">
                        <cui:input id="frRecordPeople" name="frRecordPeople"
                                   width="160" required="true"></cui:input>
                    </td>
                    <td class="class_jccl" align="right" style="width:200px; height: 25px">审批领导：</td>
                    <td class="class_jccl" align="left" style="width:100px"><cui:input id="frCheckPeople" name="frCheckPeople"
                                                                                       width="160" required="true"></cui:input></td>
                </tr>
                <tr class="class_jccl" style="display: none">
                    <td class="class_jccl" align="right" style="width:200px; height: 25px">前往区域：</td>
                    <td class="class_jccl" align="left" style="width:100px"><cui:input id="frDirection" name="frDirection"
                                                                                       width="160"></cui:input></td>
                    <td class="class_jccl" align="right" style="width:100px; height: 25px">路线选择：</td>
                    <td class="class_jccl" align="left"><cui:combobox id="frPathId" name="frPathId" width="160"
                                                                      onChange="pathSelect"></cui:combobox></td>
                    <td class="class_jccl" align="right" style="width:100px; height: 25px">进出备注：</td>
                    <td class="class_jccl" align="left"><cui:textarea id="frRemark" componentCls="form-control" name="frRemark"
                                                                      height="30"></cui:textarea></td>
                    <cui:input id="frType" name="frType" type="hidden"></cui:input>
                </tr>
            </table>
        </cui:form>
    </fieldset>
    <fieldset id="ryxxdj" style="display: none">
        <legend>人员信息登记</legend>
        <cui:grid id="gridId_foreignPeopel" fitStyle="width" height="190"
                  singleselect="false" rowEditButtons="false"
                  colModel="gridId_foreignPeopel_colModelDate">
        </cui:grid>
    </fieldset>
    <fieldset id="clxxdj">
        <legend>车辆信息登记</legend>
        <cui:grid id="gridId_foreignCar" fitStyle="width" height="380"
                  singleselect="false" rowEditButtons="false"
                  colModel="gridId_foreignCar_colModelDate">
        </cui:grid>
    </fieldset>
</div>
</body>

<script type="text/javascript">
    var jsConst = window.top.jsConst;
    var cusNumber = jsConst.CUS_NUMBER;

    var checkStta = [{
        'value': '1',
        'text': '同意',
        "selected": "selected"
    }, {
        'value': '2',
        'text': '不同意'
    }];

    var gridId_foreignPeopel_colModelDate = [{
        name: "fpdIdCardTypeIndc",
        label: "证件类型",
        align: "center",
        editable: true,
        edittype: "combobox",
        width: 100,
        editoptions: {
            'data': [{
                'value': 1,
                'text': '身份证'
            }, {
                'value': 2,
                'text': '驾驶证'
            },]
        },

    }, {
        name: "fpdIdCardCode",
        label: "证件号",
        align: "center",
        width: 180,
        editable: true,
        edittype: "text"
    }, {
        name: "fpdPeopleName",
        label: "姓名",
        align: "center",
        width: 80,
        editable: true,
        edittype: "text"

    }, {
        name: "fpdSexIndc",
        label: "性别",
        align: "center",
        width: 80,
        editable: true,
        edittype: "combobox",
        editoptions: {
            'data': [{
                'value': 1,
                'text': '男'
            }, {
                'value': 2,
                'text': '女'
            }]
        }
    }, {
        name: "fpdAge",
        label: "年龄",
        align: "center",
        width: 60,
        editable: true,
        edittype: "text"
    }, {
        name: "fpdPhone",
        label: "电话",
        align: "center",
        width: 80,
        editable: true,
        edittype: "text"
    }, {
        name: "fpdFamilyAddrs",
        label: "住址",
        align: "center",
        width: 80,
        editable: true,
        edittype: "text"
    },/* {
        name: "cktp",
        label: "图片",
        align: "center",
        width: 80,
        formatter: function () {
            return "<a href='javascript:void(0)' style='font-color:red'>查看</a>"
        }
    },*/ {
        name: "cz",
        label: "操作",
        align: "center",
        width: 50,
        formatter: function () {
            return "<label class='cui-icon-bin'></label>"
        }
    }];

    var gridId_foreignCar_colModelDate = [{
        name: "fcdCarLcnsIdnty",
        label: "车牌号",
        align: "center",
        width: 120,
        editable: true,
        edittype: "text"

    }, {
        name: "fcdCarCmpny",
        label: "所属单位",
        align: "center",
        width: 120,
        editable: true,
        edittype: "text"
    }, {
        name: "fcdCarTypeIndc",
        label: "类型",
        align: "center",
        width: 80,
        editable: true,
        edittype: "combobox",
        editoptions: {
            'data': [{
                'value': 1,
                'text': '小型车'
            }, {
                'value': 2,
                'text': '中型车'
            }, {
                'value': 3,
                'text': '大型车'
            }]
        },


    }, {
        name: "fcdCarColor",
        label: "颜色",
        align: "center",
        width: 80,
        editable: true,
        edittype: "text"

    }, {
        name: "fcdPeopleCount",
        label: "人数",
        align: "center",
        width: 80,
        editable: true,
        edittype: "text"
    }, {
        name: "fcdCargo",
        label: "所载货物",
        align: "center",
        width: 80,
        editable: true,
        edittype: "text"
    }, /*{
        name: "cktp",
        label: "图片",
        align: "center",
        width: 80,
        formatter: function () {
            return "<a href='javascript:void(0)' style='font-color:blue'>查看</a>"
        }
    },*/ {
        name: "cz",
        label: "操作",
        align: "center",
        width: 50,
        formatter: function () {
            return "<label class='cui-icon-bin'></label>"
        }
    }];

    $.parseDone(function () {
        $("#frTime").datepicker("option", "readonlyInput", true);
        $("#frReason").combobox("option", "readonlyInput", true);
        $("#frDirection").textbox("option", "readonlyInput", true);
        $("#frOprtnPolice").autocomplete("option", "readonly", true);
        $("#frReportPeople").combobox("option", "readonly", true);
        $("#frRecordPeople").textbox("option", "readonly", true);
        $("#frRemark").textbox("option", "readonlyInput", true);

        $.ajax({
            type: 'post',
            url: '${ctx}/common/authsystem/findSyncDeptPoliceForCombotree?cusNumber=' + cusNumber,
            data: '',
            dataType: 'json',
            success: function (data) {
                $('#frRecordPeople').combotree("reload", {data: data});
            }
        });

        /*$.ajax({
            type: 'post',
            url: '${ctx}/common/authsystem/findAllJqByJyKeyForCombobox?cusNumber=' + cusNumber,
            data: '',
            dataType: 'json',
            success: function (data) {
                $('#frReportPeople').combobox("reload", {data: data});
            }
        });*/

        $.ajax({
            type: 'post',
            url: '${ctx}/common/authsystem/findAllPoliceForAutocomplete.json?cusNumber=' + cusNumber,
            data: '',
            dataType: 'json',
            success: function (data) {
                $("#frOprtnPolice").autocomplete({"source": data});
                loadForm("formId_jcxx","${ctx}/foreign/registerInfoByRegisterId.json?id=${id}",function (data) {
                   if(data.frPathId!="")
                   {
                       showCamera(data.frPathId);
                   }
                });
                //$("#formId_jcxx").form("load", "${ctx}/foreign/registerInfoByRegisterId.json?id=${id}");


            }
        });

        $("#gridId_foreignPeopel").grid("reload", "${ctx}/foreign/listPeopleByRegisterId.json?id=${id}");
        $("#gridId_foreignCar").grid("reload", "${ctx}/foreign/listCarByRegisterId.json?id=${id}");

    });
    function showCamera(path) {
        debugger;
        var url = "${ctx}/prisonPath/listAllForPrisonPathCamera?pcrPathId=" + path;
        $.ajax({
            type: "post",
            url: url,
            dataType: "json",
            success: function (data) {
                console.log(data);
                info = data.data;
                var deviceIds = [];
                for (var i = 0; i < info.length; i++) {
                    deviceIds.push(info[i].PCR_CAMERA_ID);
                }
                if (deviceIds.length > 0) {
                    videoClient.playVideoHandle({
                        'subType': 2,
                        'layout': deviceIds.length,
                        'data': deviceIds,
                        'callback': function (data) {
                        }
                    });
                }
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                $.messageQueue({message: textStatus, cls: "warning", iframePanel: true, type: "info"});
            }
        });


    }
</script>
</html>
<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="cut" uri="http://www.springframework.org/tags/form" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <style>
        .class_jccl {
            padding-top: 10px;
        }

    </style>
</head>
<body>
<div id="save" class="dialog">

    <div style="text-align: left; margin-left: 5px; margin-top: 1px;margin-bottom: 1px">
        <cui:button label="保  存" onClick="saveOrUpdateInfo" id="saveInfo" width="55"
                    cls="greenbtn"></cui:button>
        <cui:button label="修  改" onClick="saveOrUpdateInfo" id="updateInfo" width="55"
                    cls="greenbtn"></cui:button>
        <cui:button label="审  批" onClick="checkAllInfo" id="checkInfo" width="55" cls="greenbtn"></cui:button>
        <cui:button label="离  开" onClick="outInfo" id="outInfo" width="55"
                    cls="greenbtn"></cui:button>
        <span id="sp">
            <cui:combobox id="frCheckSttsIndc" name="frCheckSttsIndc" data="checkStta" width="90"
                      onChange="checkRemarkInfo"></cui:combobox>
            <span id="spyj">审批意见：<cui:input id="frCheckRemark" name="frCheckRemark"></cui:input></span>
        </span>
    </div>
    <%--shirinkToFit--%>
    <fieldset id="jxxdj" style="padding-bottom:10px;margin: 10px">
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
    <fieldset id="ryxxdj"  style="display: none;">
        <legend>人员信息登记</legend>
        <cui:grid id="gridId_foreignPeopel" fitStyle="width" height="190"
                  singleselect="false"
                  colModel="gridId_foreignPeopel_colModelDate">
        </cui:grid>
    </fieldset>
    <fieldset id="clxxdj" class="class_jccl" style="margin: 10px">
        <legend>车辆信息登记</legend>
        <cui:grid id="gridId_foreignCar"  fitStyle="width" height="380"
                  singleselect="false"
                  colModel="gridId_foreignCar_colModelDate">
        </cui:grid>
    </fieldset>
</div>
</body>

<script type="text/javascript">
    var jsConst = window.top.jsConst;
    var cusNumber = jsConst.CUS_NUMBER;
    var rowPeopleLength = 10;
    var rowCarLength = 10;

    var checkStta = [{
        'value': '1',
        'text': '同意',
        "selected": "selected"
    }, {
        'value': '2',
        'text': '不同意'
    }];

    var reasonInfo = [{
        'value': '送货',
        'text': '送货'
    }, {
        'value': '事由2',
        'text': '事由2'
    }, {
        'value': '事由3',
        'text': '事由3'
    }, {
        'value': '事由4',
        'text': '事由4'
    }, {
        'value': '事由5',
        'text': '事由5'
    }];


    var gridId_foreignPeopel_colModelDate = [{
        name: "fpdIdCardTypeIndc",
        sortable:false,
        label: "证件类型",
        align: "center",
        formatter : "combobox",
        width: 100,
        formatoptions　: {
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
        sortable:false,
        label: "证件号",
        align: "center",
        width: 180,
        revertCode:true,
        formatter: function (value,cell) {
            return "<input type='text' style='height: 30px;border-color: #57BEFC;' value='"+value+"' onchange='loadPeopleRow(this.value,"+cell.rowId+")'/>"
        },
        formatoptions: {
            'required': true,
        }
    }, {
        name: "fpdIdCardCode",
        sortable:false,
        label: "证件号",
        hidden: true,
        formatter: "text",
        formatoptions: {
            'required': true,
        }
    }, {
        name: "fpdPeopleName",
        sortable:false,
        label: "姓名",
        align: "center",
        width: 80,
        formatter: "text",

        formatoptions: {
            'required': true,
        }

    }, {
        name: "fpdSexIndc",
        sortable:false,
        label: "性别",
        align: "center",
        width: 80,
        formatter: "combobox",
        formatoptions: {
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
        sortable:false,
        label: "年龄",
        align: "center",
        width: 60,
        formatter: "text"
    }, {
        name: "fpdPhone",
        label: "电话",
        align: "center",
        width: 80,
        formatter: "text"
    }, {
        name: "fpdFamilyAddrs",
        sortable:false,
        label: "住址",
        align: "center",
        width: 80,
        formatter: "text"
    }, /*{
        name: "cktp",
        label: "图片",
        align: "center",
        width: 80,
        type:hide,
        formatter: function () {
            return "<a href='javascript:void(0)' style='font-color:red'>查看</a>"
        }
    },*/ {
        name: "cz",
        sortable:false,
        label: "<span style=color:red onclick=addPeopleRow()>添加</span>",
        align: "center",
        width: 50,
        formatter: function (value,cell) {
            return "<a class='cui-icon-bin' href='#' onclick='delPeopleRow("+cell.rowId+")'></a>"
        }
    }];

    var gridId_foreignCar_colModelDate = [{
        name: "fcdCarLcnsIdnty",
        label: "车牌号",
        sortable:false,
        align: "center",
        width: 120,
        revertCode:true,
        formatter: "text",
        formatter: function (value,cell) {
            return "<input type='text' style='height: 30px;border-color: #57BEFC;' value='"+value+"' onchange='loadCarRow(this.value,"+cell.rowId+")'/><font color='red'>*</font>"
        },
        formatoptions: {
            'required': true,
        }

    }, {
        name: "fcdCarLcnsIdnty",
        sortable:false,
        label: "车牌号",
        hidden: true,
        formatter: "text",
        formatoptions: {
            'required': true,
        }
    },{
        name: "fcdCarTypeIndc",
        sortable:false,
        label: "类型",
        align: "center",
        width: 80,
        formatter: "combobox",
        formatoptions: {
            'data': [{
                'value': 1,
                'text': '货车'
            }, {
                'value': 2,
                'text': '皮卡'
            }, {
                'value': 3,
                'text': '面包车'
            }, {
                'value': 4,
                'text': '厢式货车'
            }, {
                'value': 5,
                'text': '工程车'
            }, {
                'value': 6,
                'text': '拖车'
            }]
        }
    }, {
        name: "fcdCarColor",
        label: "颜色",
        sortable:false,
        align: "center",
        width: 80,
        formatter: "combobox",
        formatoptions: {
            'data': [{
                'value': '蓝色',
                'text': '蓝色'
            }, {
                'value': '黄色',
                'text': '黄色'
            }, {
                'value': '白色',
                'text': '白色'
            }, {
                'value': '黑色',
                'text': '黑色'
            }, {
                'value': '红色',
                'text': '红色'
            }]
        }

    }, {
        name: "fcdPeopleCount",
        sortable:false,
        label: "人数",
        align: "center",
        width: 80,
        formatter: "text"
    }, {
        name: "fcdCargo",
        label: "所载货物",
        align: "center",
        width: 80,
        formatter: "text"
    }, {
        name: "fcdCarCmpny",
        sortable:false,
        label: "所属单位",
        align: "center",
        width: 120,
        formatter: "text"
    }, /*{
        name: "cktp",
        label: "图片",
        align: "center",
        width: 80,
        formatter: function () {
            return "<a href='javascript:void(0)' style='font-color:blue'>查看</a>"
        }
    }, */{
        name: "cz",
        sortable:false,
        label: "<span style=color:red onclick=addCarRow()>添加</span>",
        align: "center",
        width: 50,
        formatter: function (value,cell) {
            return "<a class='cui-icon-bin' href='#' onclick='delCarRow("+cell.rowId+")'></a>"
        }
    }];

    $.parseDone(function () {

        if("${id}" == ""){
            $.ajax({
                type: 'post',
                url: '${ctx}/foreign/findLastRow.json?frCusNumber=' + cusNumber,
                data: '',
                dataType: 'json',
                success: function (data) {
                    if(data.obj != null){
                        $("#frRecordPeople").textbox("setValue",data.obj.FR_RECORD_PEOPLE);
                        $("#frCheckPeople").textbox("setValue",data.obj.FR_CHECK_PEOPLE);
                    }
                }
            });
        }

        $.ajax({
            type: 'post',
            url: '${ctx}/common/authsystem/findAllPoliceForAutocomplete.json?cusNumber=' + cusNumber,
            data: '',
            dataType: 'json',
            success: function (data) {
                $("#frOprtnPolice").autocomplete({"source": data});
                if ("${id}" == "") {
                    $("#frOprtnPolice").autocomplete("setValue", jsConst.USER_ID);

                    addCarRow();
                }
                else{
                    $("#frPathId").combobox("reload", "${ctx}/prisonPath/findPathInfoForCombobox.json?cusNumber=" + cusNumber);
                    $("#formId_jcxx").form("load", "${ctx}/foreign/registerInfoByRegisterId.json?id=${id}");
                }
            }
        });

        /*$.ajax({
            type: 'post',
            url: '${ctx}/common/authsystem/findAllJqByJyKeyForCombobox?cusNumber=' + cusNumber,
            data: '',
            dataType: 'json',
            success: function (data) {
                $('#frReportPeople').combobox("reload",
                    {
                        data: data,
                        onLoad: function () {
                            if ("${id}" == "") {
                                // $("#frReportPeople").combobox("setValue", jsConst.DEPARTMENT_ID);
                            }
                        }
                    });
            }
        });*/
        if("${id}" == ""){
            $("#updateInfo").attr("style", "display:none");
            $("#checkInfo").attr("style", "display:none");
            $("#outInfo").attr("style", "display:none");
            $('#frTime').datepicker("setDate", new Date());
            $('#sp').hide();
        } else {
            switch("${flag}"){
                case "":
                    $("#saveInfo").attr("style", "display:none");
                    $("#checkInfo").attr("style", "display:none");
                    $("#outInfo").attr("style", "display:none");
                    $('#sp').hide();
                    break;
                case "1":
                    $("#updateInfo").attr("style", "display:none");
                    $("#checkInfo").attr("style", "display:none");
                    $('#frTime').datepicker("setDate", new Date());
                    $("#saveInfo").attr("style", "display:none");
                    $('#sp').hide();
                    break;
                case "2":
                    $("#saveInfo").attr("style", "display:none");
                    $("#outInfo").attr("style", "display:none");
                    $("#updateInfo").attr("style", "display:none");
                    /*$("#gridId_foreignPeopel").grid("option", "editableRows", false);
                    $("#gridId_foreignCar").grid("option", "editableRows", false);*/
                    $("#frTime").datepicker("option", "readonlyInput", true);
                    $("#frReason").combobox("option", "readonlyInput", true);
                    $("#frDirection").textbox("option", "readonlyInput", true);
                    $("#frOprtnPolice").autocomplete("option", "readonly", true);
                    $("#frReportPeople").combobox("option", "readonly", true);
                    $("#frRecordPeople").textbox("option", "readonly", true);
                    $("#frCheckPeople").textbox("option", "readonly", true);
                    $("#frRemark").textbox("option", "readonlyInput", true);
                    $('#frCheckRemark').textbox("setValue", '同意');
                    break;
            }

            //$("#gridId_foreignPeopel").grid("reload", "${ctx}/foreign/listPeopleByRegisterId.json?id=${id}");
            $("#gridId_foreignCar").grid("reload", "${ctx}/foreign/listCarByRegisterId.json?id=${id}");
        }
    });

    function dprtmnSelect(e, ui) {
        if (ui.value != undefined || ui.value != "") {
            $("#frPathId").combobox("reload", "${ctx}/prisonPath/findPathInfoForCombobox.json?cusNumber=" + cusNumber+"&sflDprtmntId="+ui.value);
        }
    }
    function addPeopleRow() {
        $("#gridId_foreignPeopel").grid("addRowData", rowPeopleLength, {
            "fpdPeopleName": "",
            "fpdSexIndc": 1,
            "fpdIdCardCode": "",
            "fpdIdCardTypeIndc": 1
        });
        rowPeopleLength++;
    }

    function loadPeopleRow(value,rowId) {
        debugger;

        var cardNumber = value;
        var rowData=$("#gridId_foreignPeopel").grid("getRowData", rowId);
        if(cardNumber!=null && cardNumber!=""){
            $.ajax({
                type : 'post',
                url : "${ctx}/foreign/findPeopleByCardCode.json",
                data : {"cardNumber": cardNumber},
                dataType : 'json',
                success : function(data) {
                    if(data.obj != null){
                        console.log(data.obj);

                        if(rowData.fpdAge=="")
                        {
                            $("#gridId_foreignPeopel").grid("setRowData", rowId, {

                                "fpdAge": data.obj.FPD_AGE,
                            });
                        }
                        if(rowData.fpdPhone=="")
                        {
                            $("#gridId_foreignPeopel").grid("setRowData", rowId, {

                                "fpdPhone": data.obj.FPD_PHONE,
                            });
                        }
                        if(rowData.fpdFamilyAddrs=="")
                        {
                            $("#gridId_foreignPeopel").grid("setRowData", rowId, {

                                "fpdFamilyAddrs": data.obj.FPD_FAMILY_ADDRS,
                            });
                        }
                        if(rowData.fpdPeopleName=="")
                        {
                            $("#gridId_foreignPeopel").grid("setRowData", rowId, {

                                "fpdPeopleName": data.obj.FPD_PEOPLE_NAME,
                            });
                        }
                        if(rowData.fpdSexIndc=="")
                        {
                            $("#gridId_foreignPeopel").grid("setRowData", rowId, {

                                "fpdSexIndc": data.obj.FPD_SEX_INDC,
                            });
                        }
                    }
                }
            });
        }

        $("#gridId_foreignPeopel").grid("setRowData", rowId, {

            "fpdIdCardCode": value,
        });



    }

    function addCarRow() {
        $("#gridId_foreignCar").grid("addRowData", rowCarLength, {
            "fcdCarLcnsIdnty": "新A",
            //"fcdCarCmpny": "",
            "fcdCarTypeIndc": 1,
//            "fcdCarColor": "",
//            "fcdPeopleCount": "",
//            "fcdCargo": ""
        });
        rowCarLength++;
    }

    function loadCarRow(value,rowId) {
        var carNumber = value;
        var rowData=$("#gridId_foreignCar").grid("getRowData", rowId);
        if(carNumber!=null && carNumber!=""){
            $.ajax({
                type : 'post',
                url : "${ctx}/foreign/findCarByCarIdnty.json",
                data : {"fcdCarLcnsIdnty": carNumber},
                dataType : 'json',
                success : function(data) {
                    if(data.obj != null){
                        console.log(data.obj);
                        if(rowData.fcdCarCmpny=="")
                        {
                            $("#gridId_foreignCar").grid("setRowData", rowId, {

                                "fcdCarCmpny": data.obj.FCD_CAR_CMPNY,
                            });
                        }
                        if(rowData.fcdCarColor=="")
                        {
                            $("#gridId_foreignCar").grid("setRowData", rowId, {

                                "fcdCarColor": data.obj.FCD_CAR_COLOR,
                            });
                        }
                        if(rowData.fcdCarTypeIndc=="")
                        {
                            $("#gridId_foreignCar").grid("setRowData", rowId, {

                                "fcdCarTypeIndc": data.obj.FCD_CAR_TYPE_INDC,
                            });
                        }
                    }
                }
            });
        }
        $("#gridId_foreignCar").grid("setRowData", rowId, {

            "fcdCarLcnsIdnty": value,
        });
    }


    function delPeopleRow(rowid) {
        if ("${flag}" != "1") {
            debugger;
            $("#gridId_foreignPeopel").grid("delRowData", rowid);
        }
    }

    function delCarRow(rowid) {
        if ("${flag}" != "1") {
            debugger;
            $("#gridId_foreignCar").grid("delRowData", rowid);
        }
    }

    function saveOrUpdateInfo() {
        var peopleGridData = $("#gridId_foreignPeopel").grid("getRowData");
        var carGridData = $("#gridId_foreignCar").grid("getRowData");
        var registerData = $("#formId_jcxx").form("formData");

        if ("${requestScope.flag}" != "" && "${requestScope.flag}" != "1") {
            registerData.frType = "${requestScope.flag}";
            registerData.id="";
        }

        peopleGridData = peopleGridData.filter(function (value) {
            if (value.fpdIdCardCode == "" && value.fpdPeopleName == "") {
                return false;
            }
            return true;
        });

        carGridData = carGridData.filter(function (value) {
            if ((value.fcdCarLcnsIdnty == "" || value.fcdCarLcnsIdnty=="新A") && value.fcdCarCmpny == "") {
                return false;
            }
            return true;
        });

        if (peopleGridData.length > 0) {
            for (var i = 0; i < peopleGridData.length; i++) {
                if (peopleGridData[i].fpdIdCardCode == "") {
                    $.message({
                        message: "人员信息-证件号不能为空",
                        iframePanel: true
                    });
                    return;
                } else if (!/^[1-9]\d{5}(18|19|([23]\d))\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{3}[0-9Xx]$/i.test(peopleGridData[i].fpdIdCardCode) &&　!/^[1-9]\d{5}\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{2}$/i.test(peopleGridData[i].fpdIdCardCode) ) {
                    $.message({
                        message: "输入的证件号格式错误",
                        iframePanel: true
                    });
                    return;
                }


                if (peopleGridData[i].fpdPeopleName == "") {
                    $.message({
                        message: "人员信息-姓名不能为空",
                        iframePanel: true
                    });
                    return;
                }
            }

            $.each(peopleGridData, function (key, object) {
                if (object != undefined && object != "undefined") {
                    delete object.cz;
                    delete object.cktp;
                }
            });

            registerData.foreignPeopleDtlsList = peopleGridData;

        }

        if (carGridData.length > 0) {
            for (var i = 0; i < carGridData.length; i++) {
                if (carGridData[i].fcdCarLcnsIdnty == "" || carGridData[i].fcdCarLcnsIdnty == "新A") {
                    $.message({
                        message: "车辆信息-车牌号不能为空",
                        iframePanel: true
                    });
                    return;
                }

                /*if (carGridData[i].fcdPeopleCount == "") {
                    $.message({
                        message: "车辆信息-人数不能为空",
                        iframePanel: true
                    });
                    return;
                }*/

            }

            $.each(carGridData, function (key, object) {
                if (object != undefined && object != "undefined") {
                    delete object.cz;
                    delete object.cktp;
                }
            });

            registerData.foreignCarDtlsList = carGridData;
        }


        if (registerData.frTime == "") {
            $.message({
                message: "出入信息-出入时间不能为空",
                iframePanel: true
            });
            return;
        }
        if (registerData.frReportPeople == "") {
            $.message({
                message: "出入信息-部门不能为空",
                iframePanel: true
            });
            return;
        }
        if (registerData.frOprtnPolice == "") {
            $.message({
                message: "出入信息-带队民警不能为空",
                iframePanel: true
            });
            return;
        }
        if (registerData.frRecordPeople == "") {
            $.message({
                message: "出入信息-检查民警不能为空",
                iframePanel: true
            });
            return;
        }
        if (registerData.frCheckPeople == "") {
            $.message({
                message: "出入信息-审批领导不能为空",
                iframePanel: true
            });
            return;
        }

        if (registerData.frReason == "") {
            $.message({
                message: "出入信息-事由不能为空",
                iframePanel: true
            });
            return;
        }

        console.log(registerData);
        console.log(JSON.stringify(registerData));

        if (registerData.foreignCarDtlsList != null) {
            $.loading({text:"正在处理中，请稍后..."});
            $.ajax({
                type: "post",
                url: "${ctx}/foreign/register.json",
                contentType: "application/json",
                data: JSON.stringify(registerData),
                dataType: "json",
                success: function (data) {
                    $.loading("hide");
                    if (data.success) {
                        $.messageQueue({message: data.msg, cls: "success", iframePanel: true, type: "info"});
                        $("#gridId_foreign").grid("reload");
                        $("#dialogId_foreign").dialog("close");
                        rowPeopleLength = 10;
                        rowCarLength=10;

                    } else {
                        $.messageQueue({message: data.msg, cls: "warning", iframePanel: true, type: "info"});

                    }
                }
            });
        } else {
            $.loading("hide");
            $.message({
                message: "请填写车辆信息",
                iframePanel: true
            });
        }
    }

    function checkAllInfo() {
        var registerData = $("#formId_jcxx").form("formData");
        registerData.frCheckSttsIndc = $('#frCheckSttsIndc').combobox("getValue");
        registerData.frCheckRemark = $('#frCheckRemark').textbox("getValue");
        console.log(registerData.frCheckSttsIndc);
        console.log(registerData.frCheckRemark);
        $.ajax({
            type: "post",
            url: "${ctx}/foreign/checkInfo.json",
            contentType: "application/json",
            data: JSON.stringify(registerData),
            dataType: "json",
            success: function (data) {
                if (data.success) {
                    $.messageQueue({message: data.msg, cls: "success", iframePanel: true, type: "info"});
                    $("#gridId_foreign").grid("reload");
                    $("#dialogId_foreign").dialog("close");
                } else {
                    $.messageQueue({message: data.msg, cls: "warning", iframePanel: true, type: "info"});
                }
            }
        });
    }
    
    function checkRemarkInfo(e, ui) {
        $('#frCheckRemark').textbox("setValue", ui.text);
    }

</script>
</html>
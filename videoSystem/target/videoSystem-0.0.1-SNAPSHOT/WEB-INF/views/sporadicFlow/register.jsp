<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@page import="com.cesgroup.prison.code.utils.CodeFacade" %>
<%@taglib prefix="cui" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<style>
    .form-control {
        width: 100%;
    }
</style>
<div>
    <cui:form id="formId_addInfo" heightStyle="fill">
        <table class="table" style="width:1000px; font-size:14px">
            <tr>
                <td style="width:450px">
                    <div>
                        <div style="float:left;">
                            监区：<cui:combobox id="sflDprtmntId" name="sflDprtmntId" componentCls="form-control"
                                             width="145" required="true" onChange="dprtmnSelect"></cui:combobox>
                            <cui:input type="hidden" required="true" name="sflDprtmnt" id="sflDprtmnt"></cui:input>
                        </div>
                        <div>
                            &nbsp;路线选择：<cui:combobox id="sflPrisonPathId" name="sflPrisonPathId" width="145"
                                                     required="true" onChange="pathSelect"></cui:combobox>
                        </div>
                    </div>
                    <div style="margin-top: 15px">
                        <div style="float:left;">出发时间：<cui:datepicker id="startTime" name="sflStartTime" width="140"
                                                                      dateFormat="yyyy-MM-dd HH:mm:ss"
                                                                      required="true"></cui:datepicker>
                        </div>
                        <div>到达时间：<cui:datepicker id="endTime" name="sflEndTime" width="140"
                                                  dateFormat="yyyy-MM-dd HH:mm:ss" startDateId="startTime"
                                                  required="true"></cui:datepicker>
                        </div>
                    </div>
                    <div style="margin-top: 15px">
                        <div style="float:left;">
                            <fieldset>
                                <cui:grid id="gridPoliceLeftData" colModel="gridPoliceColModelLeft" width="195" height="395"
                                          onDblClickRow="toPoliceRight" rowNum="500">
                                </cui:grid>
                            </fieldset>
                        </div>
                        <div>
                            <fieldset>
                                <cui:grid id="gridPoliceRightData" colModel="gridPoliceColModelRight" width="195"
                                          height="395" onDblClickRow="removePolice" rowNum="500">
                                </cui:grid>
                            </fieldset>
                        </div>
                    </div>
                </td>
                <td>
                    <div>
                        <div style="float:left;">
                            出发区域：<cui:input id="sflStartAddrs" name="sflStartAddrs" width="120"
                                            required="true"></cui:input>
                        </div>
                        <div style="float:left;">
                            &nbsp;到达区域：<cui:input id="sflEndAddrs" name="sflEndAddrs" width="120"
                                                  required="true"></cui:input>
                        </div>
                        <div>
                            &nbsp;罪犯人数：<cui:input id="sflOffenderNum" value="0" name="sflOffenderNum" width="60" required="true" pattern="//^\-?[0-9]+(\.[0-9]+)?$//"></cui:input>
                        </div>
                    </div>
                    <div style="margin-top: 15px">
                        <div style="float:left;">登记类型：<cui:combobox name="sflFlowType" width="100" data="flowType"
                                                                    required="true"></cui:combobox>
                        </div>
                        <div>外出原因：<cui:textarea name="sflReason" width="280" height="27"></cui:textarea></div>
                    </div>
                    <div style="margin-top: 15px">
                        <div style="float:left">
                            <fieldset>
                                <cui:grid id="gridOffenderLeftData" colModel="gridOffenderColModelLeft" width="250"
                                          height="395" onDblClickRow="toOffenderRight" rowNum="500">
                                </cui:grid>
                            </fieldset>
                        </div>
                        <div>
                            <fieldset>
                                <cui:grid id="gridOffenderRightData" colModel="gridOffenderColModelRight" width="250"
                                          height="395" onDblClickRow="removeOffender" rowNum="500">
                                </cui:grid>
                            </fieldset>
                        </div>
                    </div>
                </td>
            </tr>
        </table>

    </cui:form>
</div>

<div class="dialog-buttons">
    <cui:button label="提交" text="false" onClick="submit" style="font-size:14px"></cui:button>
    <cui:button label="重置" text="false" onClick="reset" style="font-size:14px"></cui:button>
</div>


<script>
    $.parseDone(function () {
        var time = new Date();
        $('#startTime').datepicker("setDate", new Date());
        $("#sflDprtmntId").combobox("reload", "${ctx}/common/authsystem/findAllJqByJyKeyForCombobox.json?cusNumber=" + cusNumber);

        if("${ID}"!= "")
        {
            $.ajax({
                type : "post",
                url : "${ctx}/sporadicFlow/findById.json?id=${ID}",
                dataType : "json",
                success : function(data) {
                    if (data.success) {
                        info = data.obj;
                        $("#formId_addInfo").form("load", info);

                        $("#sflPrisonPathId").combobox("reload", "${ctx}/prisonPath/findPathInfoForCombobox.json?cusNumber=" + cusNumber+"&sflDprtmntId="+info.sflDprtmntId);

                        $("#gridPoliceLeftData").grid("reload", "${ctx}/common/authsystem/findDeptPoliceForGrid.json?cusNumber=" + cusNumber+"&id=" +info.sflDprtmntId);

                        loadOffender(info.sflPrisonPathId);
                        policeSelect("${ID}");
                        offenderSelect("${ID}");

                    } else {
                        $.messageQueue({ message : data.msg, cls : "warning", iframePanel : true, type : "info" });
                    }
                },
                error : function(XMLHttpRequest, textStatus, errorThrown) {
                    $.messageQueue({ message : textStatus, cls : "warning", iframePanel : true, type : "info" });
                }
            });




        }
    });
    ///修改检索民警列表
    function policeSelect(id) {
        var url = "${ctx}/sporadicFlow/findDeptPoliceForGridByItem.json?id=" + id;

        $("#gridPoliceRightData").grid("reload", url);
    }
    ///修改检索罪犯列表
    function offenderSelect(id) {
        var url = "${ctx}/sporadicFlow/findDeptOffenderForGridByItem.json?id=" + id;

        $("#gridOffenderRightData").grid("reload", url);
    }
    //监区部门选择
    function dprtmnSelect(e, ui) {
        if (ui.value != undefined || ui.value != "") {
            $("#sflDprtmnt").textbox('setValue',ui.text);
            $("#gridPoliceLeftData").grid("reload", "${ctx}/common/authsystem/findDeptPoliceForGrid.json?cusNumber=" + cusNumber + "&id=" + ui.value);

            $("#sflPrisonPathId").combobox("reload", "${ctx}/prisonPath/findPathInfoForCombobox.json?cusNumber=" + cusNumber+"&sflDprtmntId="+ui.value);
        }
    }
    //路线选择
    function pathSelect(e, ui) {
        loadOffender(ui.value);
    }

    function loadOffender(ui) {
        if (ui != undefined || ui != "") {
            $.ajax({
                type: "post",
                url: "${ctx}/prisonPath/findById.json?id=" + ui,
                dataType: "json",
                success: function (data) {
                    if (data.success) {
                        $("#sflStartAddrs").textbox("setValue", data.obj.ppiStartArea);
                        $("#sflEndAddrs").textbox("setValue", data.obj.ppiEndArea);

                        var url = "${ctx}/xxhj/zfjbxx/queryJSPrisonerInfo.json?cusNumber=" + cusNumber;
                        if (data.obj.ppiStartAreaId != undefined || data.obj.ppiStartAreaId != "") {
                            url = url + "&lch=" + data.obj.ppiStartAreaId;
                        }
                        $("#gridOffenderLeftData").grid("reload", url);
                    } else {
                        $.messageQueue({message: data.msg, cls: "warning", iframePanel: true, type: "info"});
                    }
                },
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    $.messageQueue({message: textStatus, cls: "warning", iframePanel: true, type: "info"});
                }
            });
        }
    }

    //重置按钮事件
    function reset() {
        $("#formId_addInfo").form("clear");
        $("#gridPoliceLeftData").grid("clearGridData",false);
        $("#gridOffenderLeftData").grid("clearGridData",false);
    }

    function submit() {
        var gridDataPolice = $("#gridPoliceRightData").grid("getRowData"), gridDataOffender = $("#gridOffenderRightData").grid("getRowData");
        if (validationPolice(gridDataPolice)) {
            return;
        }
        if ($("#formId_addInfo").form("valid")) {
            var url = '${ctx}/sporadicFlow/register.json';
            var formData = $("#formId_addInfo").form("formData");

            if ("${ID}" != ""){
                url = '${ctx}/sporadicFlow/editInfo.json';
                formData["id"] = "${ID}";
            }

            $.ajax({
                type: "post",
                url: url,
                data: {"formDataJson":JSON.stringify(formData),"gridDataPoliceJson":JSON.stringify(gridDataPolice),"gridDataOffenderJson":JSON.stringify(gridDataOffender)},
                dataType: "json",
                success: function (data) {
                    if (data.code == 1) {
                        $.message("保存成功");
                        $("#dialogId_sporadicflow").dialog("close");
                        $("#gridId_sporadicflow").grid("reload");
                    } else {
                        $.message({
                            message: data.message,
                            cls: "success"
                        });
                    }

                },
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    alert(textStatus);
                }
            });

        } else {
            alert("请确认输入是否正确！");
        }
    }

    var gridPoliceColModelLeft = [{
        name: "id",
        label: "id",
        hidden: true
    }, {
        name: "name",
        label: "待选择民警"
    }];
    var gridPoliceColModelRight = [{
        name: "id",
        label: "id",
        hidden: true
    }, {
        name: "name",
        label: "已选择民警"
    }];
    var gridOffenderColModelLeft = [{
        name: "PRSNR_IDNTY",
        label: "id",
        hidden: true
    }, {
        name: "NAME",
        label: "待选择罪犯"
    }, {
        name: "JSH",
        label: "监舍号"
    }];
    var gridOffenderColModelRight = [{
        name: "PRSNR_IDNTY",
        label: "id",
        hidden: true
    }, {
        name: "NAME",
        label: "已选择罪犯"
    }, {
        name: "JSH",
        label: "监舍号"
    }];
    //待关联民警列表双击列加入关联表
    function toPoliceRight(e, ui) {
        var $gridLeft = $("#gridPoliceLeftData"), $gridRight = $("#gridPoliceRightData"), sel = $gridLeft.grid("option", "selrow");
        var rowData = $gridLeft.grid("getRowData", ui.rowId);
        var rightData = $gridRight.grid("getRowData");
        for (var i = 0; i < rightData.length; i++) {
            if (rightData[i].id == rowData.id) {
                $.messageQueue({
                    message: $("#comId_items").combobox("getText") + "民警【 " + rowData.name + " 】不可重复添加",
                    cls: "warning",
                    iframePanel: true,
                    type: "info"
                });
                return;
            }
        }
        var obj = new Object();
        obj.id = rowData.id;
        obj.name = rowData.name;
        $gridRight.grid("addRowData", rowData.id, obj);
        $gridLeft.grid("delRowData", ui.rowId);
    }

    //待关联罪犯列表双击列加入关联表
    function toOffenderRight(e, ui) {
        var $gridLeft = $("#gridOffenderLeftData"), $gridRight = $("#gridOffenderRightData"), sel = $gridLeft.grid("option", "selrow");
        var rowData = $gridLeft.grid("getRowData", ui.rowId);
        var rightData = $gridRight.grid("getRowData");
        for (var i = 0; i < rightData.length; i++) {
            if (rightData[i].PRSNR_IDNTY == rowData.PRSNR_IDNTY) {
                $.messageQueue({
                    message: $("#comId_items").combobox("getText") + "罪犯【 " + rowData.name + " 】不可重复添加",
                    cls: "warning",
                    iframePanel: true,
                    type: "info"
                });
                return;
            }
        }
        var obj = new Object();
        obj.PRSNR_IDNTY = rowData.PRSNR_IDNTY;
        obj.NAME = rowData.NAME;
        obj.JSH = rowData.JSH;
        $gridRight.grid("addRowData", rowData.PRSNR_IDNTY, obj);
        $gridLeft.grid("delRowData", ui.rowId);

        $("#sflOffenderNum").textbox('setValue',rightData.length+1);
    }

    //已关联民警列表的双击删除事件
    function removePolice(e, ui) {
        var $gridRight = $("#gridPoliceRightData"), $gridLeft = $("#gridPoliceLeftData");
        var rowData = $gridRight.grid("getRowData", ui.rowId);
        $gridRight.grid("delRowData", ui.rowId);
        var leftData = $gridLeft.grid("getRowData");
        for (var i = 0; i < leftData.length; i++) {
            if (leftData[i].id == rowData.id) {
                return;
            }
        }
        $gridLeft.grid("addRowData", rowData.id, rowData);
    }

    //已关联联罪列表的双击删除事件
    function removeOffender(e, ui) {
        var $gridRight = $("#gridOffenderRightData"), $gridLeft = $("#gridOffenderLeftData");
        var rowData = $gridRight.grid("getRowData", ui.rowId);
        $gridRight.grid("delRowData", ui.rowId);
        var leftData = $gridLeft.grid("getRowData");
        for (var i = 0; i < leftData.length; i++) {
            if (leftData[i].PRSNR_IDNTY == rowData.PRSNR_IDNTY) {
                return;
            }
        }

        $gridLeft.grid("addRowData", rowData.id, rowData);

        $("#sflOffenderNum").textbox('setValue',$gridRight.grid("getRowData").length);
    }

    function validationPolice(GridData) {
        if (GridData.length == 0) {
            $.messageQueue({message: "已关联民警列表不能为空", cls: "warning", iframePanel: true, type: "info"});
            return true;
        }
        return false;
    }

    function validationOffender(GridData) {
        if (GridData.length == 0) {
            $.messageQueue({message: "已关联罪犯列表不能为空", cls: "warning", iframePanel: true, type: "info"});
            return true;
        }
        return false;
    }
</script>
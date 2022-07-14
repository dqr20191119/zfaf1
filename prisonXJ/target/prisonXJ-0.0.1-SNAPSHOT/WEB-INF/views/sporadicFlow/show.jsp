<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="cui" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<style>
    .form-control {
        width: 100%;
    }
</style>
<div>
    <cui:form id="formId_addInfo">
        <table class="table" style="width:500px; font-size:14px">
            <tr>
                <td>
                    <div>
                        <div style="float:left;">
                            监区：<cui:combobox id="sflDprtmntId" name="sflDprtmntId"
                                             width="150" isLabel="true"></cui:combobox>
                        </div>
                        <div>
                            &nbsp;路线名称：<cui:combobox id="sflPrisonPathId" name="sflPrisonPathId" width="150"
                                                     isLabel="true"></cui:combobox>
                        </div>
                    </div>
                    <div>
                        <div style="float:left;">
                            出发区域：<cui:combobox id="sflStartAddrs" name="sflStartAddrs" width="120"
                                               isLabel="true"></cui:combobox>
                        </div>
                        <div>
                            到达区域：<cui:combobox id="sflEndAddrs" name="sflEndAddrs" width="150"
                                               isLabel="true"></cui:combobox>
                        </div>
                    </div>
                    <div>
                        <div style="float:left;">
                            <fieldset>
                                <legend>带队民警</legend>
                                <cui:grid id="gridPoliceRightData" colModel="gridPoliceColModel" width="195"
                                          height="250" rowNum="500">
                                </cui:grid>
                            </fieldset>
                        </div>
                        <div>
                            <fieldset>
                                <legend>罪犯列表</legend>
                                <cui:grid id="gridOffenderRightData" colModel="gridOffenderColModelRight" width="195"
                                          height="250" rowNum="500">
                                </cui:grid>
                            </fieldset>
                        </div>
                    </div>
                </td>
            </tr>
            <tr>
                <td>
                    <div>
                        <div style="float:left;">出发时间：<cui:datepicker id="startTime" name="sflStartTime" width="150"
                                                                      dateFormat="yyyy-MM-dd HH:mm:ss"
                                                                      isLabel="true"></cui:datepicker>
                        </div>
                        <div>到达时间：<cui:datepicker id="endTime" name="sflEndTime" width="150"
                                                  dateFormat="yyyy-MM-dd HH:mm:ss"
                                                  isLabel="true"></cui:datepicker>
                        </div>
                    </div>
                    <div>
                        <div style="float:left">登记类型：<cui:combobox name="sflFlowType" width="100" data="flowType"
                                                                   isLabel="true"></cui:combobox>
                        </div>
                        <div>外出原因：<cui:textarea name="sflReason" width="180" height="27"
                                                isLabel="true"></cui:textarea></div>
                    </div>
                </td>
            </tr>
        </table>
    </cui:form>
</div>

<div class="dialog-buttons">
    <cui:button label="返回" text="false" onClick="back"></cui:button>
</div>

<script>
    $.parseDone(function () {
        $("#sflDprtmntId").combobox("reload", "${ctx}/common/authsystem/findAllJqByJyKeyForCombobox.json?cusNumber=" + cusNumber);
        $("#sflPrisonPathId").combobox("reload", "${ctx}/prisonPath/findPathInfoForCombobox.json?cusNumber=" + cusNumber);
        var url = "${ctx}/sporadicFlow/findById.json?id=${ID}";
        $.ajax({
            type: "post",
            url: url,
            dataType: "json",
            success: function (data) {
                if (data.success) {
                    $("#formId_addInfo").form("load", data.obj);

                    policeSelect("${ID}");
                    offenderSelect("${ID}");

                    showCamera(data.obj.sflPrisonPathId)
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

    });
    var gridPoliceColModel = [{
        name: "id",
        label: "id",
        hidden: true
    }, {
        name: "name",
        label: "姓名"
    }];
    var gridOffenderColModelRight = [{
        name: "PRSNR_IDNTY",
        label: "id",
        hidden: true
    }, {
        name: "NAME",
        label: "姓名"
    },{
        name: "JSH",
        label: "监舍号"
    }];
    function policeSelect(id) {
        var url = "${ctx}/sporadicFlow/findDeptPoliceForGridByItem.json?id=" + id;

        $("#gridPoliceRightData").grid("reload", url);
    }
    ///修改检索罪犯列表
    function offenderSelect(id) {
        var url = "${ctx}/sporadicFlow/findDeptOffenderForGridByItem.json?id=" + id;

        $("#gridOffenderRightData").grid("reload", url);
    }


    function back() {
        $("#dialogId_sporadicflow").dialog("close");
    }

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
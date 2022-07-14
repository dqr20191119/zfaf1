<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<style>
    .formCell {
        margin-top: 10px;
        margin-left: 20px;
        display: inline-block;
    }

    .uploadButtonClass {
        width: 100px;
    }
</style>
<head>
</head>
<body>
<div id="save" class="dialog" style="height:100%;">
    <cui:tabs id="tabs" heightStyle="fill" beforeActivate="beforeClick">
        <ul>
            <li><a href="#fragment-1" style="font-size:14px">外来车辆</a></li>
            <li><a href="#fragment-2" style="font-size:14px">外来人员</a></li>
        </ul>
        <div id="fragment-1">
            <div style="margin: 5px;">
                <cui:form id="formId_save" style="padding:0px;">
                    <fieldset>
                        <legend>基本信息登记</legend>
                        <table style="width: 68%; margin-left:8px; font-size:14px; float:left">
                            <input type="hidden" id="id" name="id"/>
                            <input type="hidden" id="picPicturesSave" name="picPicturesSave"/>
                            <input type="hidden" id="frType" name="foreignRegister.frType" value="${flag}" />
                            <tr>
                                <td align="right" style="width:100px; height: 25px">车牌号：</td>
                                <td align="left" colspan="3">
                                	<cui:input id="fcdCarLcnsIdnty" name="foreignCarDtls.fcdCarLcnsIdnty" required="true" onEnter="findByCarLcnsIdnty" width="160"></cui:input>
                                </td>
                            </tr>
                            <tr>
                                <td align="right" style="height: 40px">车辆类型：</td>
                                <td align="left">
                                	<cui:combobox id="fcdCarTypeIndc" name="foreignCarDtls.fcdCarTypeIndc" data="carTypeData"  width="160"></cui:combobox>
                                </td>
                                <td align="right" style="height: 40px">货物情况：</td>
                                <td align="left"><cui:input id="fcdCargo" name="foreignCarDtls.fcdCargo" width="160"></cui:input></td>
                            </tr>
                            <tr>
                                <td align="right" style="height: 40px">事由：</td>
                                <td align="left">
                                	<cui:input id="frReason" name="foreignRegister.frReason" width="160"></cui:input>
                                </td>
                                <td align="right">所到地点：</td>
                                <td align="left">
                                	<cui:input id="frDirection" name="foreignRegister.frDirection" width="160"></cui:input>
                                </td>
                            </tr>
                            <tr>
                                <td align="right">车身颜色：</td>
                                <td align="left">
                                	<cui:input id="fcdCarColor" name="foreignCarDtls.fcdCarColor" width="160"></cui:input>
                                </td>
                                <td align="right" style="height: 40px">车内人数：</td>
                                <td align="left">
                                	<cui:input id="fcdPeopleCount" name="foreignCarDtls.fcdPeopleCount" valid="valid" width="160"></cui:input>
                                </td>
                            </tr>
                            <%-- <tr>

                                <td align="right">出入证号：</td>
                                <td align="left">
                                	<cui:input id="fcdOutsideIdnty" name="fcdOutsideIdnty" width="160"></cui:input>
                                </td>
                                <td align="right" style="height: 40px">办证时间：</td>
                                <td align="left">
                                	<cui:datepicker id="fcdOprtnTime" name="fcdOprtnTime" dateFormat="yyyy-MM-dd HH:mm:ss" width="160"></cui:datepicker>
                                </td>
                            </tr> --%>
                            <tr>
                                <td align="right" style="height: 40px">所属单位：</td>
                                <td align="left">
                                	<cui:input id="fcdCarCmpny" name="foreignCarDtls.fcdCarCmpny" width="160"></cui:input>
                                </td>
                                <td align="right" style="height: 40px">备注：</td>
                                <td align="left" colspan="3">
                                	<cui:input id="frRemark" name="foreignRegister.frRemark" width="160"></cui:input>
                                </td>
                                </td>
                            </tr>
                        </table>
                        <table style="width: 28%; border: 1px solid #C0C0C0; margin-left:30px">
                            <tr>
                                <td>
                                    <div style="height: 50px; line-height: 50px; text-align: center; font-size:14px">
										车辆图片
                                    </div>
                                    <div style="text-align:center; overflow-y:scroll;height:170px">
                                        <cui:uploader id="carPictures" fileObjName="uploadFile"
                                                      uploadLimit="4"
                                                      uploader="${ctx}/common/affix/upload"
                                                      onUploadStart="common.onUploadStart"
                                                      onUploadSuccess="common.onUploadSuccess"
                                                      onInit="common.onInit"
                                                      swf="${ctx}/static/resource/swf/uploadify.swf">
                                        </cui:uploader>
                                    </div>
                                </td>
                            </tr>
                        </table>
                    </fieldset>

                    <fieldset id="jrxxdj">
                        <legend>进入信息登记</legend>
                        <table style="width: 68%; margin-left:8px; font-size:14px; float:left">
                            <tr>
                                <td align="right" style="width:100px; height: 25px">进入时间：</td>
                                <td align="left" style="width:160px">
                                	<cui:datepicker id="frEnterTime"
                                                    name="foreignRegister.frEnterTime"
                                                    dateFormat="yyyy-MM-dd HH:mm:ss"
                                                    required="true"
                                                    width="160"></cui:datepicker>
                                </td>
                                <td align="right" style="height: 40px">审批人：</td>
                                <td align="left">
                                	<cui:combotree id="frCheckPeopleId"
                                                   name="foreignRegister.frCheckPeopleId" allowPushParent="false"
                                                   width="160"
                                                   onChange="frCheckPeopleIdSelect"></cui:combotree>
                                    <cui:input id="frCheckPeople" name="foreignRegister.frCheckPeople" type="hidden"></cui:input>
                                </td>
                            </tr>
                            <tr>
                                <td align="right">审批情况：</td>
                                <td align="left">
                                	<cui:combobox id="frCheckSttsIndc"
                                                  name="foreignRegister.frCheckSttsIndc" data="checkStatesData"
                                                  width="160"></cui:combobox>
                                </td>
                                <td align="right" style="height: 40px">带队民警：</td>
                                <td align="left">
                                	<cui:combotree id="carfrOprtnInPoliceId" name="foreignRegister.frOprtnInPoliceId"
                                                   allowPushParent="false" width="160"
                                                   onChange="frOprtnInPoliceIdSelect"></cui:combotree>
                                    <cui:input id="frOprtnInPolice" name="foreignRegister.frOprtnInPolice"
                                               type="hidden"></cui:input>
                                </td>
                            </tr>
                            <tr>
                                <td align="right">报告人：</td>
                                <td align="left"><cui:combotree id="frReportInPeopleId"
                                                                name="foreignRegister.frReportInPeopleId" allowPushParent="false"
                                                                width="160"
                                                                onChange="frReportInPeopleIdSelect"></cui:combotree>
                                    <cui:input id="frReportInPeople" name="foreignRegister.frReportInPeople"
                                               type="hidden"></cui:input></td>
                                <td align="right" style="height: 40px">记录人：</td>
                                <td align="left"><cui:combotree id="frRecordInPeopleId"
                                                                name="foreignRegister.frRecordInPeopleId"
                                                                allowPushParent="false" width="160"
                                                                onChange="frRecordInPeopleIdSelect"></cui:combotree>
                                    <cui:input id="frRecordInPeople" name="foreignRegister.frRecordInPeople"
                                               type="hidden"></cui:input></td>
                                </td>
                            </tr>
                        </table>
                    </fieldset>
                    <fieldset id="lkxxdj">
                        <legend>离开信息登记</legend>
                        <table style="width: 98%; margin-left:8px; font-size:14px;">
                            <tr>
                                <td align="right" style="width:100px; height: 25px">离开时间：</td>
                                <td align="left" style="width:160px"><cui:datepicker id="frLeaveTime"
                                                                                     name="foreignRegister.frLeaveTime"
                                                                                     dateFormat="yyyy-MM-dd HH:mm:ss"
                                                                                     width="160"></cui:datepicker>
                                </td>
                                <td align="right" style="width:160px">带队民警：</td>
                                <td align="left">
                                	<cui:combotree id="carfrOprtnOutPoliceId" name="foreignRegister.frOprtnOutPoliceId"
                                                   allowPushParent="false" width="160"
                                                   onChange="frOprtnOutPoliceIdSelect"></cui:combotree>
                                    <cui:input id="frOprtnOutPolice" name="foreignRegister.frOprtnOutPolice" type="hidden"></cui:input>
                                 </td>
                            </tr>
                            <tr>
                                <td align="right" style="height: 40px">报告人：</td>
                                <td align="left">
                                	<cui:combotree id="frReportOutPeopleId"
                                                   name="foreignRegister.frReportOutPeopleId" allowPushParent="false"
                                                   width="160"
                                                   onChange="frReportOutPeopleIdSelect"></cui:combotree>
                                    <cui:input id="frReportOutPeople" name="foreignRegister.frReportOutPeople" type="hidden"></cui:input>
                                </td>
                                <td align="right">记录人：</td>
                                <td align="left"><cui:combotree id="frRecordOutPeopleId"
                                                                name="foreignRegister.frRecordOutPeopleId" allowPushParent="false"
                                                                width="160"
                                                                onChange="frRecordOutPeopleIdSelect"></cui:combotree>
                                    <cui:input id="frRecordOutPeople" name="foreignRegister.frRecordOutPeople" type="hidden"></cui:input>
                                </td>
                            </tr>
                        </table>
                    </fieldset>
                </cui:form>
                <div style="text-align: right; margin-right: 50px;">
                    <cui:button label="保  存" onClick="save" id="saveCar" width="55" cls="greenbtn"></cui:button>
                </div>
            </div>
        </div>
        <div id="fragment-2">
            <div style="margin-left: 2%; height: 100%;">
                <div>
                    <cui:form id="foreignPeopelSearchData">
                        <cui:button label="添加" onClick="foreignPeopel.openDialog('add')"
                                    cls="greenlight"></cui:button>
                        <cui:button label="编辑"
                                    onClick="foreignPeopel.openDialog('update')" cls="greenbtn"></cui:button>
                        <cui:button label="删除" onClick="foreignPeopel.del"
                                    cls="deleteBtn"></cui:button>
                    </cui:form>
                </div>
                <cui:grid id="gridId_foreignPeopel" fitStyle="fill" multiselect="true"
                          colModel="foreignPeopel.gridId_foreignPeopel_colModelDate" url="str"
                          onDblClickRow="foreignPeopel.onDblClickRow">
                    <cui:gridPager gridId="gridId_foreignPeopel"/>
                </cui:grid>
            </div>
        </div>
    </cui:tabs>
</div>


<cui:dialog id="dialogId_foreignPeopel" autoOpen="false" resizable="false" reLoadOnOpen="true" modal="true">
    <div>
        <cui:form id="formId_foreignPeopel">
            <!-- 基本信息 begin-->
            <input type="hidden" id="fpdForeighCarId" name="fpdForeighCarId"/>
            <div style="display: inline-block; width: 650px;height:200px;margin-left: 20px">
                <fieldset>
                    <legend>基本信息</legend>
                    <div class="formCell">
                        <label style="width: 80px">证件类型：</label>
                        <cui:combobox id="fpdIdCardTypeIndc" name="fpdIdCardTypeIndc" emptyText="请选择证件类型"
                                      data="foreignPeopel.cardType" required="true"
                                      onChange="foreignPeopel.cardTypeSelect"></cui:combobox>
                    </div>

                    <div class="formCell">
                        <label style="width: 80px">证件号：</label>
                        <cui:input id="fpdIdCardCode" name="fpdIdCardCode" placeholder="请输入证件号" required="true"
                                   onEnter="foreignPeopel.findDataBefore" validType="idno"></cui:input>
                    </div>
                    <div class="formCell">
                        <label style="width: 80px">人员姓名：</label>
                        <cui:input id="foreginPeopelId" name="id" type="hidden"></cui:input>
                        <cui:input id="fpd_username" name="fpdPeopleName" type="text" placeholder="请输入姓名"
                                   required="true"></cui:input>
                    </div>

                    <div class="formCell">
                        <label style="width: 80px">性别：</label>
                        <cui:combobox id="fpdSexIndc" name="fpdSexIndc" data="foreignPeopel.sexType" required="true"
                                      value="男"></cui:combobox>
                    </div>

                    <div class="formCell">
                        <label style="width: 80px">年龄：</label>
                        <cui:input id="fpdAge" name="fpdAge" placeholder="请输入年龄" required="true"
                                   pattern="/^[0-9]*$/"></cui:input>
                    </div>

                    <div class="formCell">
                        <label style="width: 80px">家庭住址：</label>
                        <cui:input id="fpdFamilyAddrs" name="fpdFamilyAddrs" placeholder="请输入家庭住址"></cui:input>
                    </div>

                    <div class="formCell">
                        <label style="width: 80px">电话：</label>
                        <cui:input id="fpdPhone" name="fpdPhone" placeholder="请输入号码" required="true"
                                   pattern="/^[0-9]*$/"></cui:input>
                    </div>

                    <div class="formCell">
                        <label style="width: 80px">人员类别：</label>
                        <cui:combobox id="fpdPeopleType" name="fpdPeopleType" data="foreignPeopel.peopleType"
                                      required="fasle"></cui:combobox>
                    </div>
                </fieldset>
            </div>
            <!-- 基本信息 end-->
            <div style="width: 280px ;height:450px ;float: right;margin-right: 20px">
                <fieldset>
                    <legend>照片</legend>
                    <div style="width: 100%;height:auto">
                        <div style="margin-left: 20px">
                            <cui:uploader id="carPictures_affixIds" fileObjName="uploadFile" uploadLimit="2"
                                          buttonText="请选择照片..." uploader="${ctx}/common/affix/upload"
                                          onUploadStart="common.onUploadStart"
                                          onUploadSuccess="common.onUploadSuccess" onInit="common.onInit"
                                          onSWFReady="foreignPeopel.onSWFReady"
                                          swf="${ctx}/static/resource/swf/uploadify.swf">
                            </cui:uploader>
                        </div>
                        <cui:input id="foreignPeopelPicIds" name="foreignPeopelPicIds" type="hidden"></cui:input>
                    </div>
                </fieldset>
            </div>

            <div style="width: 650px;height:200px;margin-top: 40px;margin-left: 20px">
                <fieldset>
                    <legend>出入信息</legend>
                    <div class="formCell">
                        <label style="width: 80px">办证民警：</label>
                        <cui:combotree id="fpdOprtnPoliceIdnty" name="fpdOprtnPoliceIdnty" required="true"
                                       onChange="foreignPeopel.onMJSelect" allowPushParent="false"></cui:combotree>
                        <cui:input id="fpdOprtnPoliceName" name="fpdOprtnPoliceName" type="hidden"></cui:input>
                    </div>
                    <div class="formCell">
                        <label style="width: 80px">陪同民警：</label>
                        <cui:combotree id="fpdAccmpnyPoliceIdnty" name="fpdAccmpnyPoliceIdnty" required="true"
                                       onChange="foreignPeopel.onMJSelect" allowPushParent="false"></cui:combotree>
                        <cui:input id="fpdAccmpnyPoliceName" name="fpdAccmpnyPoliceName" type="hidden"></cui:input>
                    </div>
                    <div class="formCell">
                        <label style="width: 80px">驾驶标识：</label>
                        <cui:combobox id="fpdDriverIndc" name="fpdDriverIndc" emptyText="是否驾车"
                                      data="foreignPeopel.haveCar" required="true"></cui:combobox>
                    </div>
                    <div class="formCell">
                        <label style="width: 80px">所到地点：</label>
                        <cui:input id="fpdDirection" name="fpdDirection" placeholder="请输入去向"
                                   required="true"></cui:input>
                    </div>
                    <div class="formCell">
                        <label style="width: 80px">进出事由：</label>
                        <cui:textarea id="fpdReason" name="fpdReason" required="true" height="100"></cui:textarea>
                    </div>

                    <div class="formCell">
                        <label style="width: 80px">备注：</label>
                        <cui:textarea id="fpdRemark" name="fpdRemark" height="100"></cui:textarea>
                    </div>
                </fieldset>
            </div>

        </cui:form>
        <div id="optionButtons" style="height:30px;width:120px;margin:0px auto;border:0px solid red;margin-top: 80px">
            <cui:button label="提交" text="false" onClick="foreignPeopel.addOrUpdate"></cui:button>
            <cui:button label="重置" text="false" onClick="foreignPeopel.reset"></cui:button>
        </div>
    </div>
</cui:dialog>
</body>

<script type="text/javascript">
    var jsConst = window.top.jsConst;
    var cusNumber = jsConst.CUS_NUMBER;//监狱号

    var carTypeData = [
        {'text': '小型车', 'value': '1'},
        {'text': '中型车', 'value': '2'},
        {'text': '大型车', 'value': '3'}
    ]
    var checkStatesData = [
        {'text': '通过', 'value': '1', "selected": "selected"},
        {'text': '未通过', 'value': '0'}

    ]
    function valid() {
        var text = $("#fcdPeopleCount").textbox('getValue');
        var isValid;
        var transValue = "" + parseInt(text) == text;
        if (transValue) {
            isValid = true;
        } else {
            isValid = false;
        }
        return {isValid: isValid, errMsg: "请输入正确的人数！"};
    }

    $.parseDone(function () {
        if("${flag}" != "0")
        {
            $("#jrxxdj").attr("style","display:none");
        }
        if("${flag}" != "1")
        {
            $("#lkxxdj").attr("style","display:none");
        }
        if ("${id}" == "") {
            $('#frEnterTime').datepicker("setDate", new Date());
        }

        $.ajax({
            type: 'post',
            url: '${ctx}/common/authsystem/findSyncDeptPoliceForCombotree?cusNumber=' + cusNumber,
            data: '',
            dataType: 'json',
            success: function (data) {
                $('#carfrOprtnInPoliceId').combotree("reload", {
                    data: data,
                    onLoad: function () {
                        if ("${id}" == "") {
                            $("#carfrOprtnInPoliceId").combotree("setValue", userId);
                            $("#frOprtnInPolice").textbox("setValue", realName);
                        }
                    }
                });

                $('#frCheckPeopleId').combotree("reload", {
                    data: data,
                    onLoad: function () {
                        if ("${id}" == "") {
                            $("#frCheckPeopleId").combotree("setValue", userId);
                            $("#frCheckPeople").textbox("setValue", realName);
                        }
                    }
                });

                $('#frReportInPeopleId').combotree("reload", {
                    data: data,
                    onLoad: function () {
                        if ("${id}" == "") {
                            $("#frReportInPeopleId").combotree("setValue", userId);
                            $("#frReportInPeople").textbox("setValue", realName);

                        }
                    }
                });

                $('#frRecordInPeopleId').combotree("reload", {
                    data: data,
                    onLoad: function () {
                        if ("${id}" == "") {
                            $("#frRecordInPeopleId").combotree("setValue", userId);
                            $("#frRecordInPeople").textbox("setValue", realName);
                        }
                    }
                });


                if("${flag}"=="1")
                {
                    $('#carfrOprtnOutPoliceId').combotree("reload", {
                        data: data,
                        onLoad: function () {
                            if ("${id}" == "") {
                                $("#carfrOprtnOutPoliceId").combotree("setValue", userId);
                                $("#frOprtnOutPolice").textbox("setValue", realName);
                            }
                        }
                    });
                    $('#frReportOutPeopleId').combotree("reload", {
                        data: data,
                        onLoad: function () {
                            if ("${id}" == "") {
                                $("#frReportOutPeopleId").combotree("setValue", userId);
                                $("#frReportOutPeople").textbox("setValue", realName);

                            }
                        }
                    });
                    $('#frRecordOutPeopleId').combotree("reload", {
                        data: data,
                        onLoad: function () {
                            if ("${id}" == "") {
                                $("#frRecordOutPeopleId").combotree("setValue", userId);
                                $("#frRecordOutPeople").textbox("setValue", realName);
                            }
                        }
                    });
                }

            }, error: function (XMLHttpRequest, textStatus, errorThrown) {
                $.alert({
                    message: textStatus,
                    title: "提示信息",
                    iframePanel: true
                });
            }
        });
        //判断是否更新
        if ("${id}" != "") {
	        $.ajax({
	            type: 'post',
	            url: '${ctx}/foreignCar/findById',
	            data: {'id': '${id}'},
	            dataType: 'json',
	            success: function (data) {
	                if (data.success = true) {
	                    data = data.obj;
	
	                    $('#id').val(data.ID);
	
	                    $("#fcdOutsideIdnty").textbox('setValue', data.FCD_OUTSIDE_IDNTY);
	                    $("#fcdCarLcnsIdnty").textbox('setValue', data.FCD_CAR_LCNS_IDNTY);
	                    $("#fcdCarTypeIndc").combobox('setValue', data.FCD_CAR_TYPE_INDC);
	                    $("#fcdPeopleCount").textbox('setValue', data.FCD_PEOPLE_COUNT);
	                    $("#fcdCarColor").textbox('setValue', data.FCD_CAR_COLOR);
	                    $("#fcdCarCmpny").textbox('setValue', data.FCD_CAR_CMPNY);
	                    $("#frReason").textbox('setValue', data.FCD_REASON);
	                    $("#frDirection").textbox('setValue', data.FCD_DIRECTION);
	                    $("#fcdCargo").textbox('setValue', data.FCD_CARGO);
	                    $("#frRemark").textbox('setValue', data.FCD_REMARK);
	                    $("#fcdOprtnTime").datepicker('setValue', data.FCD_OPRTN_TIME);
	
	                    $("#frEnterTime").datepicker('setValue', data.FCD_ENTER_TIME);
	                    $("#frCheckSttsIndc").combobox('setValue', data.FCD_CHECK_STTS_INDC);
	                    $("#frCheckPeopleId").combotree('setValue', data.FCD_CHECK_PEOPLE_ID);
	                    $("#frCheckPeople").textbox('setValue', data.FCD_CHECK_PEOPLE);
	                    $("#carfrOprtnInPoliceId").combotree('setValue', data.FCD_OPRTN_IN_POLICE_ID);
	                    $("#frOprtnInPolice").textbox('setValue', data.FCD_OPRTN_IN_POLICE);
	                    $("#frReportInPeopleId").combotree('setValue', data.FCD_REPORT_IN_PEOPLE_ID);
	                    $("#frReportInPeople").textbox('setValue', data.FCD_REPORT_IN_PEOPLE);
	                    $("#frRecordInPeopleId").combotree('setValue', data.FCD_RECORD_IN_PEOPLE_ID);
	                    $("#frRecordInPeople").textbox('setValue', data.FCD_RECORD_IN_PEOPLE);
	
	
	
	                    if("${flag}"=="1")
	                    {
	                        $("#frLeaveTime").datepicker('setValue', data.FCD_LEAVE_TIME);
	                        $("#carfrOprtnOutPoliceId").combotree('setValue', data.FCD_OPRTN_OUT_POLICE_ID);
	                        $("#carfrOprtnOutPolice").textbox('setValue', data.FCD_OPRTN_OUT_POLICE);
	                        $("#frReportOutPeopleId").combotree('setValue', data.FCD_REPORT_OUT_PEOPLE_ID);
	                        $("#frReportOutPeople").textbox('setValue', data.FCD_REPORT_OUT_PEOPLE);
	                        $("#frRecordOutPeopleId").combotree('setValue', data.FCD_RECORD_OUT_PEOPLE_ID);
	                        $("#frRecordOutPeople").textbox('setValue', data.FCD_RECORD_OUT_PEOPLE);
	                    }
	
	                    common.loadAffix("carPictures", data.carPictureList, false);
	                    $('#fpdForeighCarId').val(data.ID);
	                    var dataPer = {};
	                    dataPer['fpdForeighCarId'] = data.ID;
	                    $("#gridId_foreignPeopel").grid("option", {
	                        postData: dataPer,
	                        url: "${ctx}/foreignPeopel/pageList.json"
	                    });
	                    $("#gridId_foreignPeopel").grid("reload");
	                }
	            }, error: function (XMLHttpRequest, textStatus, errorThrown) {
	                $.alert({
	                    message: textStatus,
	                    title: "提示信息",
	                    iframePanel: true
	                });
	            }
	        });
        }
    });

    function frOprtnInPoliceIdSelect(e, ui) {
        if (ui.value != undefined || ui.value != "") {
            $("#frOprtnInPolice").textbox('setValue', ui.text);
        }
    }

    function frRecordInPeopleIdSelect(e, ui) {
        if (ui.value != undefined || ui.value != "") {
            $("#frRecordInPeople").textbox('setValue', ui.text);
        }
    }
    function frReportInPeopleIdSelect(e, ui) {
        if (ui.value != undefined || ui.value != "") {
            $("#frReportInPeople").textbox('setValue', ui.text);
        }
    }

    function frCheckPeopleIdSelect(e, ui) {
        if (ui.value != undefined || ui.value != "") {
            $("#frCheckPeople").textbox('setValue', ui.text);
        }
    }

    function frOprtnOutPoliceIdSelect(e, ui) {
        if (ui.value != undefined || ui.value != "") {
            $("#frOprtnOutPolice").textbox('setValue', ui.text);
        }
    }

    function frReportOutPeopleIdSelect(e, ui) {
        if (ui.value != undefined || ui.value != "") {
            $("#frReportOutPeople").textbox('setValue', ui.text);
        }
    }

    function frRecordOutPeopleIdSelect(e, ui) {
        if (ui.value != undefined || ui.value != "") {
            $("#frRecordOutPeople").textbox('setValue', ui.text);
        }
    }

    var isUploaderDisable = false;
    function findByCarLcnsIdnty(event, ui) {
        $.ajax({
            type: 'post',
            url: '${ctx}/foreignCar/findByCarLcnsIdnty',
            data: {'carLcnsIdnty': ui.value},
            dataType: 'json',
            success: function (data) {
                if (data.success = true) {
                    data = data.obj;
                    if (data != null) {
                        $('#id').val(data.ID);
                        $("#fcdOutsideIdnty").textbox('setValue', data.FCD_OUTSIDE_IDNTY);
                        $("#fcdCarLcnsIdnty").textbox('setValue', data.FCD_CAR_LCNS_IDNTY);
                        $("#fcdCarTypeIndc").combobox('setValue', data.FCD_CAR_TYPE_INDC);
                        $("#fcdCarColor").textbox('setValue', data.FCD_CAR_COLOR);
                        $("#fcdCarCmpny").textbox('setValue', data.FCD_CAR_CMPNY);
                        $("#frReason").textbox('setValue', data.FCD_REASON);
                        $("#fcdCargo").textbox('setValue', data.FCD_CARGO);

                        $("#frDirection").textbox('setValue', data.FCD_DIRECTION);
                        $("#fcdPeopleCount").textbox('setValue', data.FCD_PEOPLE_COUNT);
                        $("#fcdOprtnTime").datepicker('setValue', data.FCD_OPRTN_TIME);
                        $("#frRemark").textbox('setValue', data.FCD_REMARK);

                        $("#frEnterTime").datepicker('setValue', data.FCD_ENTER_TIME);
                        $("#frCheckSttsIndc").combobox('setValue', data.FCD_CHECK_STTS_INDC);
                        $("#frCheckPeopleId").combotree('setValue', data.FCD_CHECK_PEOPLE_ID);
                        $("#frCheckPeople").textbox('setValue', data.FCD_CHECK_PEOPLE);
                        $("#carfrOprtnInPoliceId").combotree('setValue', data.FCD_OPRTN_IN_POLICE_ID);
                        $("#frOprtnInPolice").textbox('setValue', data.FCD_OPRTN_IN_POLICE);
                        $("#frReportInPeopleId").combotree('setValue', data.FCD_REPORT_IN_PEOPLE_ID);
                        $("#frReportInPeople").textbox('setValue', data.FCD_REPORT_IN_PEOPLE);
                        $("#frRecordInPeopleId").combotree('setValue', data.FCD_RECORD_IN_PEOPLE_ID);
                        $("#frRecordInPeople").textbox('setValue', data.FCD_RECORD_IN_PEOPLE);
                    }
                }
            }, error: function (XMLHttpRequest, textStatus, errorThrown) {
                $.alert({
                    message: textStatus,
                    title: "提示信息",
                    iframePanel: true
                });
            }
        });
    }

    function save() {
        var result = $("#formId_save").form("valid");
        if (result == false) {
            return;
        }
        debugger;
        var ids = $('#carPictures_affixIds').val();
        if ($('#carPictures_affixIds').val() == undefined) {
            ids = '';
        }
        $('#picPicturesSave').val(ids);
        var formData = $("#formId_save").form("formData");
        $.ajax({
            type: 'post',
            url: '${ctx}/foreignCar/saveForeignCarDtls',
            data: formData,
            dataType: 'json',
            success: function (data) {
            	alert(1);
                if (data.success) {
                	alert(2);
                    $.message({
                        message: data.msg,
                        iframePanel: true
                    });
                    $('#gridId_foreign').grid("reload");
                    $('#fpdForeighCarId').val(data.obj.id);
                    $('#id').val(data.obj.id);
                    var dataPer = {};
                    dataPer['fpdForeighCarId'] = data.obj.id;
                    $("#gridId_foreignPeopel").grid("option", {
                        postData: dataPer,
                        url: "${ctx}/foreignPeopel/pageList.json"
                    });
                    $("#gridId_foreignPeopel").grid("reload");
                } else {
                	alert(4);
                    $.message({
                        message: data.msg,
                        iframePanel: true
                    });
                }
            }, error: function (XMLHttpRequest, textStatus, errorThrown) {
            	console.log("XMLHttpRequest = " + XMLHttpRequest);
            	console.log("textStatus = " + textStatus);
            	console.log("errorThrown = " + errorThrown);
            	alert(4);
                $.alert({
                    message: textStatus,
                    title: "提示信息",
                    iframePanel: true
                });
            }
        });
    }

    function beforeClick(event, ui) {
        <c:if test="${id == null}">
        if ($('#fpdForeighCarId').val() == '') {
            $.message({
                message: "请先保存外来车辆！",
                iframePanel: true
            });
            return false;
        }
        return true;
        </c:if>
    }
    var foreignPeopel = null;

    $(function () {
        foreignPeopel = {
            sexType: [{text: "男", value: "男"}, {text: "女", value: "女"}],
            cardType: [{text: "身份证", value: "1"}, {text: "驾驶证", value: "2"}],
            haveCar: [{text: "驾车", value: "1"}, {text: "不驾车", value: "0"}],
            gridId_foreignPeopel_colModelDate: [{
                label: "ID",
                name: "ID",
                hidden: true,
                key: true
            }, {
                name: "FPD_PEOPLE_NAME",
                label: "姓名",
                align: "center",
                width: 80
            }, {
                name: "FPD_ID_CARD_CODE",
                label: "证件号",
                align: "center",
                width: 120
            }, {
                name: "FPD_SEX_INDC",
                label: "性别",
                align: "center",
                width: 80
            }, {
                name: "FPD_AGE",
                label: "年龄",
                align: "center",
                width: 80
            }, {
                name: "FPD_DIRECTION",
                label: "去向",
                align: "center",
                width: 120
            }, {
                name: "FPD_ACCMPNY_POLICE_NAME",
                label: "陪同民警",
                align: "center",
                width: 80
            }, {
                name: "FPD_ENTER_TIME",
                label: "进入时间",
                align: "center",
                width: 120,
            }, {
                name: "FPD_LEAVE_TIME",
                label: "离开时间",
                align: "center",
                width: 120,
            }, {
                name: "FPD_REASON",
                label: "进入原因",
                align: "center",
                width: 180
            }, {
                name: "FPD_REMARK",
                label: "备注",
                align: "center",
                width: 120
            }, {
                name: "FPD_FAMILY_ADDRS",//住址家庭
                hidden: true
            }, {
                name: "FPD_PHONE",//电话
                hidden: true
            }, {

                name: "FPD_ID_CARD_TYPE_INDC",//证件类型
                hidden: true
            }, {
                name: "FPD_ACCMPNY_POLICE_IDNTY",//陪同民警 id
                hidden: true
            }, {
                name: "FPD_DRIVER_INDC",//是否驾车
                hidden: true
            }, {
                name: "FPD_OPRTN_POLICE_NAME",//办证民警姓名
                hidden: true
            }, {
                name: "FPD_OPRTN_POLICE_IDNTY",//办证民警id
                hidden: true
            }, {
                name: "FPD_PEOPLE_TYPE",//外来人员类型
                hidden: true
            }],
            //打开新增或修改dialog
            openDialog: function (str) {
                $("#formId_foreignPeopel").form("setIsLabel", false);
                $("#formId_foreignPeopel").form("clear");
                $("#optionButtons").show();
                if (isUploaderDisable) {
                    $("#foreignPeopelPic").uploader("disable", false);
                    isUploaderDisable = false;
                }
                var title = "";
                if (str == "add") {
                    title = "新增";
                    foreignPeopel.clearPics();
                    $("#fpdSexIndc").combobox("setValue", "男");
                    $("#fpdDriverIndc").combobox("setValue", "0");
                    $("#fpdIdCardTypeIndc").combobox("setValue", "1");
                } else if (str == "update") {
                    var selarrrow = $("#gridId_foreignPeopel").grid("option", "selarrrow");
                    if (selarrrow.length == 1) {
                        var rowData = $('#gridId_foreignPeopel').grid("getRowData", selarrrow[0]);
                        foreignPeopel.setDateToForm(rowData);
                        foreignPeopel.doPicJob(selarrrow[0], "");
                        title = "修改";
                    } else {
                        $.message({
                            message: "请先勾选一条需要修改的记录！",
                            cls: "warning"
                        });
                        return;
                    }
                }
                $("#dialogId_foreignPeopel").dialog({
                    width: 1000,
                    height: 680,
                    title: title
                })
                $("#dialogId_foreignPeopel").dialog("open");
            },
            //填充表格数据
            setDateToForm: function (data) {
                if (data.ID) {
                    $("#foreginPeopelId").textbox("setValue", data.ID);
                }
                $("#fpd_username").textbox("setValue", data.FPD_PEOPLE_NAME);
                $("#fpdAge").textbox("setValue", data.FPD_AGE);
                $("#fpdIdCardCode").textbox("setValue", data.FPD_ID_CARD_CODE);
                $("#fpdSexIndc").combobox("setValue", data.FPD_SEX_INDC);
                $("#fpdFamilyAddrs").textbox("setValue", data.FPD_FAMILY_ADDRS);
                $("#fpdIdCardTypeIndc").combobox("setValue", data.FPD_ID_CARD_TYPE_INDC);
                $("#fpdPhone").textbox("setValue", data.FPD_PHONE);
                $("#fpdDirection").textbox("setValue", data.FPD_DIRECTION);
                if (data.FPD_OPRTN_POLICE_IDNTY) {
                    $("#fpdOprtnPoliceIdnty").combotree("setValue", data.FPD_OPRTN_POLICE_IDNTY);
                }
                if (data.FPD_OPRTN_POLICE_NAME) {
                    $("#fpdOprtnPoliceName").textbox("setValue", data.FPD_OPRTN_POLICE_NAME);
                }
                if (data.FPD_ACCMPNY_POLICE_IDNTY) {
                    $("#fpdAccmpnyPoliceIdnty").combotree("setValue", data.FPD_ACCMPNY_POLICE_IDNTY);
                }
                if (data.FPD_ACCMPNY_POLICE_NAME) {
                    $("#fpdAccmpnyPoliceName").textbox("setValue", data.FPD_ACCMPNY_POLICE_NAME);
                }
                if (data.FPD_ACCMPNY_POLICE_NAME) {
                    $("#fpdAccmpnyPoliceName").textbox("setValue", data.FPD_ACCMPNY_POLICE_NAME);
                }
                if (data.FPD_DRIVER_INDC) {
                    $("#fpdDriverIndc").combobox("setValue", data.FPD_DRIVER_INDC);
                }
                if (data.FPD_REASON) {
                    $("#fpdReason").textbox("setValue", data.FPD_REASON);
                }
                $("#fpdRemark").textbox("setValue", data.FPD_REMARK);
            },
            addOrUpdate: function () {
                var data = null;
                var url = "";
                var picIds = $("#foreignPeopelPic_affixIds").val() == null ? "" : $("#foreignPeopelPic_affixIds").val();
                $("#foreignPeopelPicIds").textbox("setValue", picIds);
                if ($("#formId_foreignPeopel").form("valid")) {
                    data = $("#formId_foreignPeopel").form("formData");
                    if (data.id == null || data.id == "") {
                        url = "${ctx}/foreignPeopel/insertForeignPeopel";
                    } else {
                        url = "${ctx}/foreignPeopel/updateForeignPeopel";
                    }
                    $.ajax({
                        type: "post",
                        url: url,
                        data: data,
                        dataType: "json",
                        success: function (data) {
                            if (data.success) {
                                $.message({
                                    message: "操作成功",
                                    cls: "success"
                                });
                                $("#gridId_foreignPeopel").grid("reload");
                                $("#dialogId_foreignPeopel").dialog("close");
                                foreignPeopel.clearPics();
                            } else {
                                $.message({message: data.message, cls: "warning"});
                            }
                        },
                        error: function (XMLHttpRequest, textStatus,
                                         errorThrown) {
                            alert(textStatus);
                        }
                    });
                } else {
                    $.message({message: "请检查表单内容", cls: "warning"});
                }
            },
            //选择民警id的同时，对民警姓名进行保存
            onMJSelect: function (event, ui) {
                if (event.target.id == "fpdOprtnPoliceIdnty") {
                    $("#fpdOprtnPoliceName").textbox("setValue", ui.newText);
                } else if (event.target.id == "fpdAccmpnyPoliceIdnty") {
                    $("#fpdAccmpnyPoliceName").textbox("setValue", ui.newText);
                }
            },
            del: function () {
                var selrow = $("#gridId_foreignPeopel").grid("option", "selarrrow");
                if (selrow.length > 0) {
                    $.confirm("确认是否删除？", function (r) {
                        if (r) {
                            var j = 0;//成功条数
                            var k = 0;//失败条数
                            for (var i = 0; i < selrow.length; i++) {
                                $.ajax({
                                    async: false,
                                    type: "post",
                                    data: {"id": selrow[i]},
                                    url: "${ctx}/foreignPeopel/delForeignPeopel.json",
                                    success: function (data) {
                                        if (data.success) {
                                            j++;
                                        } else {
                                            k++;
                                        }
                                    }
                                });
                            }
                            $("#gridId_foreignPeopel").grid("reload");
                            $.message({
                                message: "删除成功" + j + "条,删除失败" + k + "条.",
                                cls: "success"
                            });
                        }
                    });
                } else {
                    $.message({
                        message: "请先勾选需要删除记录！",
                        cls: "warning"
                    });
                }
            },
            findDataBefore: function (event, ui) {
                foreignPeopel.clearPics();
                $.ajax({
                    type: "post",
                    url: "${ctx}/foreignPeopel/findRecord.json",
                    data: {fpdIdCardCode: ui.value, cusNumber: cusNumber},
                    dataType: "json",
                    success: function (data) {
                        if (data.success) {
                            foreignPeopel.setDateToForm(data.obj);
                            common.loadAffix("foreignPeopelPic", data.obj.pics, false);
                        } else {
                            $.message({message: data.msg, cls: "warning"});
                        }
                    },
                })
            },
            reset: function () {
                var id = $("#foreginPeopelId").textbox("getValue");
                if (id == null || id == "") {
                    $("#formId_foreignPeopel").form("clear");
                } else {
                    var selarrrow = $("#gridId_foreignPeopel").grid("option", "selarrrow");
                    var rowData = $('#gridId_foreignPeopel').grid("getRowData", selarrrow[0]);
                    foreignPeopel.setDateToForm(rowData);
                }
            },
            doPicJob: function (id) {
                if (isUploaderDisable) {
                    $("#foreignPeopelPic").uploader("disable", false);
                    isUploaderDisable = false;
                }
                foreignPeopel.clearPics();
                $.ajax({
                    type: "post",
                    url: "${ctx}/foreignPeopel/findPic.json",
                    data: {id: id},
                    dataType: "json",
                    success: function (data) {
                        if (data.success) {
                            common.loadAffix("foreignPeopelPic", data.obj, false);
                            if (funName == "onDblClickRow") {
                                $("#foreignPeopelPic").uploader("disable", true);
                                isUploaderDisable = true;
                            }
                        } else {
                            $.message({
                                message: "加载图片失败！",
                                cls: "warning"
                            });
                        }
                    },
                })
            },
            clearPics: function () {
                $("#foreignPeopelPic").prev(".wrapper").html("");
                $("#foreignPeopelPic_affixIds").val("");
                $("#foreignPeopelPicIds").textbox("setValue", "");
            },
            search: function () {
                $("#cusNumber_fdp").textbox("setValue", cusNumber);
                var data = $("#foreignPeopelSearchData").form("formData");
                $("#gridId_foreignPeopel").grid("option", {
                    postData: data,
                    url: "${ctx}/foreignPeopel/pageList.json"
                });
                $("#gridId_foreignPeopel").grid("reload");
            },
            reset: function () {
                $("#foreignPeopelSearchData").form("clear");
            },
            //双击显示详情
            onDblClickRow: function (e, ui) {
                $("#formId_foreignPeopel").form("setIsLabel", true);
                var rowData = $('#gridId_foreignPeopel').grid("getRowData", ui.rowId);
                foreignPeopel.setDateToForm(rowData);
                foreignPeopel.doPicJob(ui.rowId, "onDblClickRow");
                $("#dialogId_foreignPeopel").dialog({
                    width: 1000,
                    height: 680,
                    title: "外来人员信息"
                })
                $("#optionButtons").hide();
                $("#dialogId_foreignPeopel").dialog("open");
            },
            //改变证件类型时更换验证规则
            cardTypeSelect: function (event, ui) {
                if (ui.newValue == "1") {
                    $("#fpdIdCardCode").textbox("option", {validType: 'idno'});
                } else {
                    $("#fpdIdCardCode").textbox("option", {validType: ''});
                }
            },
            onSWFReady: function () {
                $("#foreignPeopelPic").uploader("disable", false);
            }
        }
        $("#fpdAccmpnyPoliceIdnty").combotree({url: "${ctx}/common/authsystem/findDeptPoliceForCombotree?cusNumber=" + cusNumber});

        $("#fpdOprtnPoliceIdnty").combotree({url: "${ctx}/common/authsystem/findDeptPoliceForCombotree?cusNumber=" + cusNumber});
        $("#gridId_foreignPeopel").grid({url: ""});
    });
</script>
</html>
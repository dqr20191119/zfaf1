<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags" %>
<%@page import="com.cesgroup.prison.code.utils.CodeFacade" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta http-equiv="cache-control" content="no-store, no-cache, must-revalidate">
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="expires" content="0">
    <title>标签管理</title>
</head>
<body>
<cui:dialog id="labelDialog" width="60%" height="60%" title="" iframePanel="true" autoOpen="false"></cui:dialog>
<div class="rightDivStyle right-zb">
    <h4>标签管理</h4>
    <a href="javascript:label.showLabelManagers();" class="modelUrl">标签列表</a>
    <div class="model_div">
        <cui:form id="labelForm">
            <input type="hidden" id="id" name="id" value=""/>
            <input type="hidden" id="cusNumber" name="vfuCusNumber" value=""/>
            <input type="hidden" id="vf3dId" name="vfu3dIdnty" value=""/>
            <input type="hidden" id="parentArreaId" value=""/>
            <div class="model_form">
                <div class="model-input">
                    <label>关联区域：</label>
                    <cui:combotree name="mliAreaId" id="labelBox" url="str" required="true"
                                   onChange="label.changePosition"/>
                </div>
                <div class="model-input">
                    <label>标签类型：</label>
                    <cui:combobox name="mliLabelType" id="labelTypeId" data="label.labelType"
                                  onChange="label.updateLabelStta"/>
                </div>
                <div class="model-input">
                    <label>监舍序号：</label>
                    <cui:combobox id="dormInfo" textField="name" valueField="id"
                                  onChange="label.dormAndAreaChange"></cui:combobox>
                <%--    <cui:input type="hidden" name="mliLabelCode" id="labelCode"/>
                    <cui:input type="hidden" name="id" id="labelId"/>--%>
                </div>
                <div class="model-input">
                    <label>标签名称：</label>
                    <cui:input placeholder="请输入标签名称" name="mliLabelCode" id="labelCode" required="true"
                               errMsg="名称不能为空"/>
                    <cui:input type="hidden" name="id" id="labelId"/>
                </div>
                <div class="model-input">
                    <label>标签宽度：</label>
                    <cui:input placeholder="请输入标签宽度" name="mliWidth" id="mliWidth" pattern="/^[0-9]+\.{0,1}[0-9]{0,5}$/"
                               errMsg="请输入正确数字" required="true" onChange="label.change"/>
                </div>
                <div class="model-input">
                    <label>标签高度：</label>
                    <cui:input placeholder="请输入标签高度" name="mliHeight" id="mliHeight"
                               pattern="/^[0-9]+\.{0,1}[0-9]{0,5}$/" required="true" errMsg="请输入正确数字"
                               onChange="label.change"/>
                </div>
                <div class="title-box">
                	<h4>标签坐标</h4>
                </div>
                <div class="model-input">
                    <label>X坐标：</label>
                    <cui:input id="view_x" name="mliX" readonly="true"/>
                </div>
                <div class="model-input">
                    <label>Y坐标：</label>
                    <cui:input id="view_y" name="mliY" readonly="true"/>
                </div>
                <div class="model-input">
                    <label>Z坐标：</label>
                    <cui:input id="view_z" name="mliZ" readonly="true"/>
                </div>
                <div class="model_buttons_save">
                    <cui:button label="保存" onClick="label.save"></cui:button>
                    <cui:button label="重置" onClick="label.reset"></cui:button>
                    <cui:button label="清除标签" onClick="label.cleanLabels"></cui:button>
                </div>
            </div>
        </cui:form>
    </div>
</div>

<script type="text/javascript">
    var position = map_3d.__g.new_Vector3;
    var tableLabel = null;
    var label = null;
    var tableLabels = [];
    var crteStta = false;
    $(function () {
        label = {
            <%--labelType: <%=CodeFacade.loadCode2Json("4.20.49")%>,--%>
            labelType: [{text: "民警、罪犯、设备数量信息", value: 5}, {text: "监舍罪犯信息", value: 2},],
            url: "${ctx}/common/areadevice/findForCombotree.json?cusNumber=" + jsConst.CUS_NUMBER + "&deviceType=0",
            setViewPosition: function () {
                map_3d.cameraInfo = map_3d.__g.camera.getCamera();
                $('#view_x').textbox("setValue", map_3d.cameraInfo.position.x);
                $('#view_y').textbox("setValue", map_3d.cameraInfo.position.y);
                $('#view_z').textbox("setValue", map_3d.cameraInfo.position.z);
                $('#view_heading').textbox("setValue", map_3d.cameraInfo.angle.heading);
                $('#view_tilt').textbox("setValue", map_3d.cameraInfo.angle.tilt);
            },
            showLabelManagers: function () {
                $("#labelDialog").dialog({
                    width: 1000,                  //属性
                    height: 530,                 //属性
                    subTitle: '标签信息列表',
                    modal: false,                 //弹出窗口底层可操作
                    position: {my: "left", at: "left+30px top+300px ", of: window}, //弹出窗口 位置
                    autoOpen: true,
                    url: '${ctx}/labels/list',
                });
            },
            save: function () {
                if ($("#labelForm").form("valid")) {
                    var data = $("#labelForm").form("formData");
                    var url = "";
                    if (data.id == null || data.id == "") {
                        url = "${ctx}/labels/addLabel.json";
                    } else {
                        url = "${ctx}/labels/updLabel.json";
                    }
                    $.ajax({
                        type: "post",
                        data: data,
                        url: url,
                        success: function (data) {
                            if (data.message == "保存成功") {
//                                $("#labelForm").form("clear");
                                $("#labelGrid").grid("reload");
                                crteStta = false;
                                tableLabels = [];
                                $.message({
                                    message: data.message,
                                    cls: "success",
                                    iframePanel: true
                                });
                            }else {
                                $.message({
                                    message: data.message,
                                    cls: "warning",
                                    iframePanel: true
                                });
                            }


                        },
                        error: function (XMLHttpRequest, textStatus, errorThrown) {
                            $.alert({
                                message: textStatus,
                                title: "信息提示",
                                iframePanel: true
                            });
                        }
                    });
                }
            },
            reset: function () {
                if ($("#labelId").val() != "") {
                    labelList.openUpdate();
                } else {
                    $("#labelForm").form("clear");
                }
            },
            change: function () {
                tableLabel.textSymbol.marginWidth = $("#mliWidth").textbox("getValue");
                tableLabel.textSymbol.verticalOffset = $("#mliHeight").textbox("getValue");
            },
            dormAndAreaChange: function () {
                var areaName = $("#labelBox").combotree("getText");
                var areaId = $("#labelBox").combotree("getValue");
                var dormName = $("#dormInfo").combobox("getText");
                var dormId = $("#dormInfo").combobox("getValue");
                switch ($("#labelTypeId").combobox('getValue')) {
                    case '2':
                        if (areaId.length>8 && areaName != null && "" != areaName && dormName != null && "" != dormName) {
                            $("#labelCode").textbox("setValue", dormId); //dormName + "|" +
                        }else{
                            $("#labelCode").textbox("setValue", areaName);
                        };
                        break;
                    case '5':
                        $("#labelCode").textbox("setValue", "");
                        if (areaName != null && "" != areaName) {
                            $("#labelCode").textbox("setValue", areaName);
                        }
                        break;
                    default:
                        $("#labelCode").textbox("setValue", areaName);
                        break;
                }
            },
            //清除label
            cleanLabels: function () {
                for (var i = 0; i < tableLabels.length; i++) {
                    map_3d.__g.objectManager.deleteObject(tableLabels[i]);
                }
                tableLabels = [];
                crteStta = false;
            },
            changePosition: function () {
                var areaId = $("#labelBox").combotree("getValue");
                $("#dormInfo").combobox("reload", {url: "${ctx}/common/all/findLcjsh.json?cusNumber=" + jsConst.CUS_NUMBER + "&areaId=" + areaId});

                var dataPara = {'alpGrandId': areaId, 'alpCusNumber': jsConst.CUS_NUMBER};
                var data = _DOCUMENT_EVENT.request_data("${ctx}/point/getViewByGrandId", dataPara, false);
                if (data != null && data.length > 0) {
                    map.viewPosition(data[0].vfuXCrdnt, data[0].vfuYCrdnt, data[0].vfuZCrdnt, data[0].vfuHeadingCrdnt, data[0].vfuTiltCrdnt, data[0].vfuParentViewId, areaId, 1);
                }
                label.dormAndAreaChange();

            },
            updateLabelStta: function () {
                switch ($(this).combobox('getValue')) {
                    case '2':
                        $("#dormInfo").combobox("option", "disabled", false);
                        $("#labelCode").textbox("option","readonly", true);
                        break;
                    case '5':
                        $("#dormInfo").combobox("option", "disabled", true);
                        $("#labelCode").textbox("option","readonly", false);
                        break;
                }
            },


            init: function () {
                $("#labelBox").combotree({url: label.url});
                map_3d.__g.interactMode = gviInteractMode.gviInteractSelect;
                map_3d.__g.mouseSelectObjectMask = gviMouseSelectObjectMask.gviSelectAll;
                map_3d.__g.mouseSelectMode = gviMouseSelectMode.gviMouseSelectClick;
                if (typeof (label.fnMouseClickSelectForLabel) == "function") ____events["onMouseClickSelect"] = label.fnMouseClickSelectForLabel;
            },

            fnMouseClickSelectForLabel: function (pickResult, intersectPoint, mask, eventSender) {
                if (!crteStta) {
                    var areaId = $("#labelBox").combotree("getValue");
                    var width = $("#mliWidth").textbox("getValue");
                    var height = $("#mliHeight").textbox("getValue");

                    switch ($("#labelTypeId").combobox('getValue')) {
                        case '2':
                            if (height == null || height == "") {
                                height = 1;
                                $("#mliHeight").textbox("setValue", 1);
                            }
                            //标签内容主体
                            var content = $("#dormInfo").combobox("getText");
                            if (width == null || width == "") {
                                width = 1;
                                $("#mliWidth").textbox("setValue", 1);
                            }
//                            map.createLabels(areaId, intersectPoint, width, content, height);
                            label.crteLabel(intersectPoint, width, content, height);
                            break;
                        case '5':
                            if (height == null || height == "") {
                                height = 5;
                                $("#mliHeight").textbox("setValue", 5);
                            }
                            //标签内容主体
                            var content = "楼内民警：20人\n楼内罪犯：100人\n待维修设备：05台";
                            if (width == null || width == "") {
                                width = 2;
                                $("#mliWidth").textbox("setValue", 2);
                            }
                            label.crteLabel(intersectPoint, width, content, height);
                            break;
                    }

                    $("#view_x").textbox("setValue", intersectPoint.x);
                    $("#view_y").textbox("setValue", intersectPoint.y);
                    $("#view_z").textbox("setValue", intersectPoint.z);
                    crteStta = true;
                } else {
                    _DOCUMENT_EVENT.messge("请先保存，勿重复创建");
                }
            },

            crteLabel: function (point, width, content, height) {
                tableLabel = map_3d.__g.objectManager.createLabel(__rootId);
                tableLabels.push(tableLabel.guid);

                tableLabel.text = content;
                tableLabel.position = point;
                var textSymbol = map_3d.__g.new_TextSymbol;
                textSymbol.drawLine = true;
                textSymbol.verticalOffset = height;
                textSymbol.marginWidth = width;
                textSymbol.maxVisualDistance = 800;
                var textAttribute = map_3d.__g.new_TextAttribute;
                textAttribute.textColor = 0xffffff00;
                textAttribute.textSize = 12;
                textAttribute.underline = false;
                textAttribute.font = "黑体";
                textSymbol.textAttribute = textAttribute;
                tableLabel.textSymbol = textSymbol;
            }
        }
        label.init();
    });


    /* 	  function createLabel(point,title,width,content,rows){
     var ang = __g.new_EulerAngle;
     ang.heading = 0;
     ang.tilt = -20;
     tableLabel = __g.objectManager.createTableLabel(rows, 1, __rootId);
     tableLabels.push(tableLabel.guid);
     //身设置标题
     tableLabel.titleText = title;
     //设置标签内容
     for(var i=0;i<rows;i++){
     tableLabel.setRecord(i, 0, "测试信息"+i);
     }
     //设置位置
     tableLabel.position = point;
     //设置列宽度
     tableLabel.setColumnWidth(0, width);
     // 表的边框颜色
     tableLabel.borderColor = 0xffffffff;
     // 表的边框的宽度
     tableLabel.borderWidth = 2;
     // 表的背景色
     tableLabel.tableBackgroundColor = 4290707456;
     // 标题背景色
     tableLabel.titleBackgroundColor = 0xff000000;
     //设置第一列字体
     var headerTextAttribute = __g.new_TextAttribute;
     headerTextAttribute.textColor = 0xffffffff;
     headerTextAttribute.outlineColor = 0xff000000;
     headerTextAttribute.font = "黑体";
     headerTextAttribute.textSize = 10;
     headerTextAttribute.bold = true;
     headerTextAttribute.multilineJustification = gviMultilineJustification.gviMultilineCenter;
     tableLabel.setColumnTextAttribute(0, headerTextAttribute);
     // 标题文本样式
     var capitalTextAttribute = __g.new_TextAttribute;
     capitalTextAttribute.textColor = 0xffffffff;
     capitalTextAttribute.outlineColor = 4279834905;
     capitalTextAttribute.font = "华文新魏";
     capitalTextAttribute.textSize = 12;
     capitalTextAttribute.multilineJustification = gviMultilineJustification.gviMultilineCenter;
     capitalTextAttribute.bold = true;
     tableLabel.titleTextAttribute = capitalTextAttribute;
     } */
</script>
</body>
</html>


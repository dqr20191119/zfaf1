<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags" %>
<%@page import="com.cesgroup.prison.code.utils.CodeFacade" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<div class="PanelCon">
    <div class="PanelList">
        <div class="clearfix panelTop" id="userToolBarArea">
            <!-- 操作按钮开始 -->
            <div>
                <cui:form id="searchData">
                    <cui:button label="添加" onClick="routeList.openAdd" cls="greenlight"></cui:button>
                    <cui:button label="编辑" onClick="routeList.openUpdate" cls="greenbtn"></cui:button>
                    <cui:button label="删除" onClick="routeList.delRoute" cls="deleteBtn"></cui:button>
                    <cui:button label="路线预览" onClick="routeList.createLine" cls="btn-info"></cui:button>
                    <cui:button label="路线巡视" onClick="routeList.see" cls="btn-info"></cui:button>
                    <cui:button label="路线模型清除" onClick="routeList.clear" cls="btn-info"></cui:button>

                    <div style="float: right;">
                        <cui:combobox id="departTree1" width="120" name="rpiDepartCode" url="str"/>
                            <%-- <cui:combobox  id="routeTypeBox1" name="rpiRouteType" data="routeList.routeTypeBox" width="120" placeholder="请选择路线类型"/> --%>
                        <cui:input type="text" id="search" width="120" placeholder="请输入路线名称" name="rpiName"/>
                        <cui:input type="hidden" id="cusnumber" width="120" name="rpiCusNumber"/>
                        <cui:button label="搜索" onClick="routeList.search" cls="cyanbtn"></cui:button>
                        <cui:button label="重置" onClick="routeList.reset" cls="btn-danger"></cui:button>
                    </div>
                </cui:form>
            </div>
            <!-- 操作按钮结束 -->
            <!-- 列表展示开始 -->
        </div>
        <cui:grid id="routeGrid" rownumbers="false" multiselect="true" url="str" fitStyle="fill" width="auto"
                  height="400" rowNum="10">
            <cui:gridCols>
                <cui:gridCol name="id" hidden="true" align="center">id</cui:gridCol>
                <cui:gridCol name="rpiName" width="40" align="center">路线名称</cui:gridCol>
                <cui:gridCol name="rpiSpeed" width="40" align="center">时间(s)</cui:gridCol>
                <cui:gridCol name="rpiRouteType" width="40" align="center" formatter="routeType" unformat="unformat">路线类型</cui:gridCol>
                <cui:gridCol name="rpiHorizonHeight" width="40" align="center">水平高度</cui:gridCol>
                <cui:gridCol name="rpiShowArrow" width="40" align="center" formatter="convertCode" revertCode="true" formatoptions="{'data': rpiShowArrowData}">是否显示箭头</cui:gridCol>
                <%-- <cui:gridCol name="rpiDepartName" width="40" align="center">部门名称</cui:gridCol> --%>
                <cui:gridCol name="rpiRemark" width="40" align="center">备注</cui:gridCol>
                <cui:gridCol name="rpiDepartCode" width="40" hidden="true">部门id</cui:gridCol>
            </cui:gridCols>
            <cui:gridPager gridId="routeGrid"/>
        </cui:grid>
        <!-- 列表展示结束 -->
    </div>
</div>
<cui:dialog id="routeDialog1" reLoadOnOpen="true" modal="true" iframePanel="true"
            autoOpen="false" width="750" height="350" maximizable="false">
    <cui:form id="routeDialogForm">
        <div style="margin-top: 20px;margin-left: 30px;display: inline-block;">
            <label style="width: 80px">路线名称：</label>
            <cui:input required="true" errMsg="必须输入路线名称！" type="text" id="routeName" name="rpiName"
                       value=""></cui:input>
            <cui:input type="hidden" id="routeId" name="id" value=""></cui:input>
        </div>
        <div style="margin-top: 20px;margin-left: 30px;display: inline-block;">
            <label style="width: 80px">时间(s)：</label>
            <cui:input required="true" errMsg="必须输入时间！" type="text" id="routeSpeed" name="rpiSpeed"
                       pattern="/^[0-9]+\.{0,1}[0-9]{0,5}$/" value=""></cui:input>
        </div>
        <%--  <div style="margin-top: 20px;margin-left: 30px;display: inline-block;">
          <label style="width: 60px">路线类型：</label>
          <cui:combobox  id="routeTypeBox" name="rpiRouteType" data="routeList.routeTypeBox" required="true"/>
          </div> --%>
        
        <%--   <div style="margin-top: 20px;margin-left: 30px;display: inline-block;">
          <label style="width: 60px">所属部门：</label>
          <cui:combobox  id="departTree" width="150" name="rpiDepartCode" url="str"/>
         </div> --%>  <!-- 后台添加部门id -->
        <div style="margin-top: 20px;margin-left: 30px;display: inline-block;">
			<label style="width: 80px">水平高度：</label>
            <cui:input required="false" type="text" id="horizonHeight" name="rpiHorizonHeight" value=""></cui:input>
        </div>
        <div style="margin-top: 20px;margin-left: 30px;display: inline-block;">
			<label style="width: 80px">显示箭头：</label>
			<cui:combobox id="showArrow" name="rpiShowArrow" data="rpiShowArrowData"></cui:combobox>
        </div>
        <div style="margin-top: 20px;margin-left: 30px;display: inline-block;">
            <label style="width: 80px">备注：</label>
            <cui:input type="text" id="remark" name="rpiRemark" value=""></cui:input>
        </div>
        <div style="height:30px;width:140px;margin:30px auto;border:0px solid red;">
            <cui:button label="保存" width="55" onClick="routeList.saveRoute"/>
            <cui:button label="重置" width="55" onClick="routeList.resetRoute"/>
        </div>
    </cui:form>
</cui:dialog>
<script type="text/javascript">
    var routeList = null;
    var __cameraTour = null;
    var rpolyline = null;
    var renderPoints = [];
    var indexNow = null;
    var pointData = null;
	var rpiShowArrowData = <%=CodeFacade.loadCode2Json("4.0.1")%>;//是否显示箭头
	
    $(function () {
        routeList = {
            url: "${ctx}/common/authsystem/findAllJqByJyKeyForCombobox?cusNumber=" + jsConst.CUS_NUMBER,
            routeTypeBox:<%=CodeFacade.loadCode2Json("4.20.48")%>,
            openAdd: function () {
                $("#routeDialogForm").form("clear");
                $("#routeDialog1").dialog("option", {
                    title: "添加路线"
                });
                $("#routeDialog1").dialog("open");
            },
            saveRoute: function () {
                if ($("#routeDialogForm").form("valid")) {
                    var datas = $("#routeDialogForm").form("formData");
                    var url = "";
                    if ($("#routeDialog1").dialog("option", "title") == "添加路线") {
                        url = "${ctx}/route/addRoute.json";
                    } else {
                        url = "${ctx}/route/updateRoute.json";
                    }
                    $.ajax({
                        type: 'post',
                        url: url,
                        data: datas,
                        dataType: 'json',
                        success: function (data) {
                            $("#routeDialog1").dialog("close");
                            $("#routeDialogForm").form("clear");
                            $("#routeGrid").grid("reload");
                            $("#routeBox").combobox("reload");
                            $("#routeBox1").combobox("reload");
                            $.alert({
                                message: data.message,
                                title: "提示信息",
                                iframePanel: true
                            });
                        },
                        error: function (XMLHttpRequest, textStatus, errorThrown) {
                            $.alert({
                                message: textStatus,
                                title: "信息提示",
                                iframePanel: true
                            });
                        }
                    })
                }
            },
            resetRoute: function () {
                if ($("#routeDialog1").dialog("option", "title") == "添加路线") {
                    $("#routeDialogForm").form("clear");
                } else {
                    var selrow = $("#routeGrid").grid("option", "selrow");
                    var rowData = $('#routeGrid').grid("getRowData", selrow);
                    $("#routeName").val(rowData.rpiName);
                    $("#routeSpeed").val(rowData.rpiSpeed);
                    $("#remark").val(rowData.rpiRemark);
                }
            },
            openUpdate: function () {
                var selrow = $("#routeGrid").grid("option", "selarrrow");
                if (selrow.length == 1) {
                    var rowData = $('#routeGrid').grid("getRowData", selrow[0]);
                    $("#routeId").textbox("setValue", rowData.id);
                    $("#routeName").textbox("setValue", rowData.rpiName);
                    $("#routeSpeed").textbox("setValue", rowData.rpiSpeed);
                    $("#remark").textbox("setValue", rowData.rpiRemark);
                    $("#routeTypeBox").combobox("setValue", rowData.rpiRouteType);
                    $("#departTree").combobox("setValue", rowData.rpiDepartCode);
                    $("#horizonHeight").textbox("setValue", rowData.rpiHorizonHeight);
                    $("#showArrow").combobox("setValue", rowData.rpiShowArrow);
                    $("#routeDialog1").dialog("option", {
                        title: "修改路线"
                    });
                    $("#routeDialog1").dialog("open");
                } else {
                    $.message({
                        message: "请先勾选一条需要修改的记录！",
                        cls: "warning"
                    });
                }

            },
            delRoute: function () {
                var selrow = $("#routeGrid").grid("option", "selarrrow");
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
                                    url: "${ctx}/route/delRoute.json",
                                    success: function (data) {
                                        if (data.message == "删除成功") {
                                            j++;
                                        } else {
                                            k++;
                                        }
                                    }
                                });
                            }
                            $("#routeGrid").grid("reload");
                            $("#routeBox").combobox("reload");
                            $("#routeBox1").combobox("reload");
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
            search: function () {
                $("#cusnumber").textbox("setValue", jsConst.CUS_NUMBER);
                var data = $("#searchData").form("formData");

                if (jsConst.USER_LEVEL == 3) {
                    data.rpiDepartCode = jsConst.DEPARTMENT_ID;
                }

                $("#routeGrid").grid("option", {
                    postData: data,
                    url: '${ctx}/route/routePage',
                });
                $("#routeGrid").grid("reload");
            },
            see: function () {
                var selrow = $("#routeGrid").grid("option", "selarrrow");
                if (selrow.length == 1) {
                    $("#routeDialog").dialog("close");
                    map.showTour(selrow[0], 5000);
                } else {
                    $.message({
                        message: "请先勾选一条需要展示的路线！",
                        cls: "warning"
                    });
                }
            },
            //在三维地图上画出巡航路线
            createLine: function () {
                var url = "${ctx}/routePoint/selectPointsByRouteID.json";
                var reqParams = {};
                var creatLineAndPoint = function (data) {
                    var __gfc = map_3d.__g.geometryFactory;
                    var __geo = __gfc.createGeometry(gviGeometryType.gviGeometryPolyline, gviVertexAttribute.gviVertexAttributeZ);
                    for (var i = 0; i < data.length; i++) {
                        /* 标签
                        var dynamicTableLabel = __g.objectManager.createTableLabel(4, 2, __rootId);
                        dynamicTableLabel.titleText = "巡视点信息";	 */
                        var point = __gfc.createPoint(gviVertexAttribute.gviVertexAttributeZ);
                        point.x = data[i].rprPositionX;
                        point.y = data[i].rprPositionY;
                        //
                        point.z = data[i].rprPositionZ;
                        __geo.appendPoint(point);
                        var renderPoint = map_3d.__g.objectManager.createRenderPoint(point, null, "");
                        renderPoints.push(renderPoint.guid);
                    }
                    //设置线样式
                    var cur = map_3d.__g.new_CurveSymbol;
                    cur.color = colorFromARGB(255, 255, 0, 255);
                    cur.width = -3;
                    rpolyline = map_3d.__g.objectManager.createRenderPolyline(__geo, cur, __rootId);
                    //闪烁
                    rpolyline.glow(0);
                    //高亮
                    rpolyline.Highlight = 0xffffff00;
                    $("#routeDialog").dialog("close");
                }
                var selrow = $("#routeGrid").grid("option", "selarrrow");
                routeList.clear();
                if (selrow.length == 1) {
                    reqParams = {"rprRoamIdnty": selrow[0]};
                    _DOCUMENT_EVENT.request_data_callback(url, creatLineAndPoint, reqParams, null);
                } else {
                    $.message({
                        message: "请先勾选一条需要预览的路线！",
                        cls: "warning"
                    });
                }
            },
            clear: function () {
                //清除路线模型
                if (rpolyline) {
                    map_3d.__g.objectManager.deleteObject(rpolyline.guid);
                    rpolyline = null;
                }
                //清除点模型
                if (renderPoints) {
                    for (var i = 0; i < renderPoints.length; i++) {
                        map_3d.__g.objectManager.deleteObject(renderPoints[i]);
                    }
                    renderPoints = [];
                }
                $("#routeDialog").dialog("close");
            },
            reset: function () {
                $("#searchData").form("reset");
            }
        }
        //绑定飞行时节点变化事件
        if (typeof (map.onWaypointchanged) == "function") ____events["onCameraTourWaypointChanged"] = map.onWaypointchanged;
        if (jsConst.USER_LEVEL != 3) {
            $("#departTree").combobox({url: routeList.url});
            $("#departTree1").combobox({url: routeList.url});
        }
        $("#routeGrid").grid({url: "${ctx}/route/routePage?rpiCusNumber=" + jsConst.CUS_NUMBER + "&rpiDepartCode=" + jsConst.DEPARTMENT_ID});
    });

    function routeType(cellvalue, options, rawObject) {
        if (cellvalue == 1) {
            return "公共路线";
        } else if (cellvalue == 2) {
            return "私人路线";
        } else if (cellvalue == null) {
            return "";
        }
    }

    function unformat(value) {
        if (value == "公共路线") {
            return "1";
        } else if (value == "私人路线") {
            return "2";
        } else if (value == "") {
            return null;
        }
    }
</script>

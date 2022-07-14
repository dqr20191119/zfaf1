<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<div class="PanelCon">
    <div class="PanelList">
        <div class="clearfix panelTop" id="userToolBarArea">
            <!-- 操作按钮开始 -->
            <div>
                <cui:form id="pointSearchData">
                    <cui:button label="编辑" onClick="routePointList.updatePoint" cls="greenbtn"></cui:button>
                    <cui:button label="删除" onClick="routePointList.delPoint" cls="deleteBtn"></cui:button>
                    <div style="float: right">
                        <cui:combobox id="routeBox1" url="str" emptyText="请选择路线" width="150" name="rprRoamIdnty"/>
                        <cui:combotree url="" id="equipmentTree1" width="150" name="rprEquipmentId"/>
                        <cui:input type="text" id="searchPoint" width="150" placeholder="请输入路线点名称" name="rprPointName"/>
                        <cui:input type="hidden" id="cusN" width="150" name="rprCusNumber"/>
                        <cui:button label="搜索" onClick="routePointList.search" cls="cyanbtn"></cui:button>
                        <cui:button label="重置" onClick="routePointList.reset" cls="btn-danger"></cui:button>
                    </div>
                </cui:form>
            </div>
            <!-- 操作按钮结束 -->
            <!-- 列表展示开始 -->
        </div>
        <cui:grid id="routePointGrid" rownumbers="false" multiselect="true"
                  url="str" fitStyle="fill" width="auto" height="400" rowNum="10">
            <cui:gridCols>
                <cui:gridCol name="id" hidden="true" align="center">id</cui:gridCol>
                <cui:gridCol name="rprPointName" width="40" align="center">路线点名称</cui:gridCol>
                <cui:gridCol name="rprOrder" width="20" align="center">次序</cui:gridCol>
                <cui:gridCol name="rprRoamName" width="40" align="center">路线名称</cui:gridCol>
                <cui:gridCol name="rprRoamIdnty" width="40" hidden="true">路线id</cui:gridCol>
                <cui:gridCol name="rprEquipmentName" width="40" align="center">关联设备名称</cui:gridCol>
                <cui:gridCol name="rprEquipmentId" width="40" hidden="true">关联设备id</cui:gridCol>
                <cui:gridCol name="rprRouteSpeed" width="40" align="center">漫游时间(s)</cui:gridCol>
                <cui:gridCol name="rprPositionX" width="30" align="center">X轴坐标</cui:gridCol>
                <cui:gridCol name="rprPositionY" width="30" align="center">Y轴坐标</cui:gridCol>
                <cui:gridCol name="rprPositionZ" width="30" align="center">Z轴坐标</cui:gridCol>
                <cui:gridCol name="rprAngleHead" width="30" align="center">方位角</cui:gridCol>
                <cui:gridCol name="rprAngleTilt" width="30" align="center">俯仰角</cui:gridCol>
            </cui:gridCols>
            <cui:gridPager gridId="routePointGrid"/>
        </cui:grid>
        <!-- 列表展示结束 -->
    </div>
</div>

<script type="text/javascript">
    var routePointList = null;
    $(function () {
        routePointList = {
            url: "${ctx}/common/areadevice/findForCombotree?deviceType=1&cusNumber=" + jsConst.CUS_NUMBER,
            delPoint: function () {
                var selrow = $("#routePointGrid").grid("option", "selarrrow");
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
                                    url: "${ctx}/routePoint/delPoint.json",
                                    success: function (data) {
                                        if (data.message == "删除成功") {
                                            j++;
                                        } else {
                                            k++;
                                        }
                                    }
                                });
                            }
                            $("#routePointGrid").grid("reload");
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
                $("#cusN").textbox("setValue", jsConst.CUS_NUMBER);
                var data = $("#pointSearchData").form("formData");

                if (jsConst.USER_LEVEL == 3) {
                    var lxName = $("#routeBox1").combobox("getValue");
                    if (lxName == null || lxName == "") {
                        $.message({
                            message: "请选择巡视路线",
                            iframePanel: true
                        });
                        return;
                    }
                }

                $("#routePointGrid").grid("option", {
                    postData: data,
                    url: "${ctx}/routePoint/Page",
                });

                $("#routePointGrid").grid("reload");
            },
            reset: function () {
                $("#pointSearchData").form("clear");
            },
            updatePoint: function () {
                var selrow = $("#routePointGrid").grid("option", "selarrrow");
                if (selrow.length == 1) {
                    setData(selrow);
                } else {
                    $.message({
                        message: "请先勾选一条需要修改的记录！",
                        cls: "warning"
                    });
                }

            },
        }
        $("#equipmentTree1").combotree({url: routePointList.url});
        if (jsConst.USER_LEVEL != 3) {
            $("#routePointGrid").grid({url: "${ctx}/routePoint/Page?rprCusNumber=" + jsConst.CUS_NUMBER});
        }
        $("#routeBox1").combobox({url: "${ctx}/route/findByPrisonCode?cusNumber=" + jsConst.CUS_NUMBER + "&departCode=" + jsConst.DEPARTMENT_ID});
    })

    function setData(selrow) {
        var rowData = $('#routePointGrid').grid("getRowData", selrow);
        $("#rprPointId").textbox("setValue", rowData.id);
        $("#routeBox").combobox("setValue", rowData.rprRoamIdnty);
        $("#rprRouteSpeed").textbox("setValue", rowData.rprRouteSpeed);
        $("#rprPointName").textbox("setValue", rowData.rprPointName);
        $("#equipmentTree").combotree("setValue", rowData.rprEquipmentId);
        $("#equipmentTree").combotree("setText", rowData.rprEquipmentName);
        $("#rprOrder").textbox("setValue", rowData.rprOrder);
        $("#view_x").textbox("setValue", rowData.rprPositionX);
        $("#view_y").textbox("setValue", rowData.rprPositionY);
        $("#view_z").textbox("setValue", rowData.rprPositionZ);
        $("#view_heading").textbox("setValue", rowData.rprAngleHead);
        $("#view_tilt").textbox("setValue", rowData.rprAngleTilt);
        $("#routeDialog").dialog("close");

        //重新定位摄像机
        var p = {x: rowData.rprPositionX, y: rowData.rprPositionY, z: rowData.rprPositionZ}
        var a = {heading: rowData.rprAngleHead, tilt: rowData.rprAngleTilt};
        map_3d.setCameraInfo(p, a)
    }


</script>

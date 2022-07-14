<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta http-equiv="cache-control" content="no-store, no-cache, must-revalidate">
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="expires" content="0">
    <title>巡视维护</title>
</head>
<body>
<cui:dialog id="routeDialog" width="60%" height="60%" title="" iframePanel="true" autoOpen="false">

</cui:dialog>
<div class="rightDivStyle right-zb">
    <h4>巡视路径管理</h4>
    <a href="javascript:cameraTour.showRouteManagers();" class="modelUrl">巡视管理</a>
    <div class="model_div">
        <cui:form id="routePointForm">
            <input type="hidden" id="id" name="id" value=""/>
            <input type="hidden" id="cusNumber" name="vfuCusNumber" value=""/>
            <input type="hidden" id="vf3dId" name="vfu3dIdnty" value=""/>
            <input type="hidden" id="parentArreaId" value=""/>
            <div class="model_form">
                <div class="model-input">
                    <label>巡视路线：</label>
                    <cui:combobox name="rprRoamIdnty" id="routeBox" url="str" required="true"
                                  onChange="cameraTour.routeChange"/>
                </div>
                <div class="model-input">
                    <label>漫游时间(s)：</label>
                    <cui:input placeholder="请输入巡视点速度" name="rprRouteSpeed" id="rprRouteSpeed"
                               pattern="/^[0-9]+\.{0,1}[0-9]{0,5}$/" errMsg="请输入数字" required="true"/>
                </div>
                <div class="model-input">
                    <label>巡视点次序：</label>
                    <cui:input placeholder="请输入巡视点次序" name="rprOrder" id="rprOrder" pattern="/^[0-9]*$/" required="true"
                               errMsg="请输入数字" onBlur="cameraTour.onblurEven()"/>
                </div>
                <div class="model-input">
                    <label>巡视点名称：</label>
                    <cui:input placeholder="请输入巡视点名称" name="rprPointName" id="rprPointName" required="true"
                               errMsg="名称不能为空"/>
                    <cui:input type="hidden" name="id" id="rprPointId"/>
                </div>
                <div class="model-input">
                    <label style="width: 80px">区域摄像头：</label>
                    <cui:combotree url="str" id="equipmentTree" name="rprEquipmentId"/>
                </div>
                <div class="title-box">
                	<h4>巡视点位坐标</h4>
                </div>
                <div class="model-input">
                    <label>X坐标：</label>
                    <cui:input id="view_x" name="rprPositionX" readonly="true"/>
                </div>
                <div class="model-input">
                    <label>Y坐标：</label>
                    <cui:input id="view_y" name="rprPositionY" readonly="true"/>
                </div>
                <div class="model-input">
                    <label>Z坐标：</label>
                    <cui:input id="view_z" name="rprPositionZ" readonly="true"/>
                </div>
                <div class="model-input">
                    <label>俯仰角：</label>
                    <cui:input id="view_tilt" name="rprAngleTilt" readonly="true"/>
                </div>
                <div class="model-input">
                    <label>旋转角：</label>
                    <cui:input id="view_heading" name="rprAngleHead" readonly="true"/>
                </div>
                <div class="model_buttons_save">
                    <cui:button label="获取点位信息" onClick="cameraTour.setViewPosition" width="90"></cui:button>
                    <cui:button label="保存" onClick="cameraTour.save"></cui:button>
                    <cui:button label="重置" onClick="cameraTour.reset"></cui:button>
                </div>
            </div>
        </cui:form>
    </div>
</div>
<%-- <div style="height:570px;">
   <div style="margin:5px 0px;font-size: 16px;font-weight: bold;width:280px;text-align:center;">巡视路径管理
       <a href="javascript:cameraTour.showRouteManagers();" style="font-size: 12px;float:left;position:absolute;margin-left:40px;margin-top:8px;">巡视管理</a>
   </div>
   <cui:form id="routePointForm">
   <div style="margin-top: 10px">
   <label style="width: 80px">巡视路线：</label>
   <cui:combobox  name="rprRoamIdnty" id="routeBox" url="str" required="true"  onChange="cameraTour.routeChange"/></div>
   <div style="margin-top: 10px">
   <label style="width: 80px">巡视点名称：</label>
   <cui:input placeholder="请输入巡视点名称" name="rprPointName" id="rprPointName" required="true" errMsg="名称不能为空"/>
   <cui:input type="hidden" name="id" id="rprPointId" />
   </div>
   <div style="margin-top: 10px">
   <label style="width: 80px">漫游时间(s)：</label>
   <cui:input placeholder="请输入巡视点速度" name="rprRouteSpeed" id="rprRouteSpeed" pattern="/^[0-9]+\.{0,1}[0-9]{0,5}$/" errMsg="请输入数字" required="true"/></div>
   <div style="margin-top: 10px">
   <label style="width: 80px">巡视点次序：</label>
   <cui:input placeholder="请输入巡视点次序" name="rprOrder" id="rprOrder" pattern="/^[0-9]*$/" required="true"  errMsg="请输入数字"/></div>
   <div style="margin-top: 10px">
   <label style="width: 80px">区域摄像头：</label>
   <cui:combotree url="str" id="equipmentTree" name="rprEquipmentId" /></div>
   <table class="mp_table" style="margin-top: 30px">
               <tr >
                   <th colspan="2">巡视点位坐标</th>
               </tr>
               <tr>
                   <td width="80px">X坐标：</td>
                   <td class="mp_td"><cui:input id="view_x" name="rprPositionX" readonly="true" /></td>
               </tr>
               <tr>
                   <td width="80px">Y坐标：</td>
                   <td class="mp_td"><cui:input id="view_y" name="rprPositionY"  readonly="true"/></td>
               </tr>
               <tr>
                   <td width="80px">Z坐标：</td>
                   <td class="mp_td"><cui:input id="view_z" name="rprPositionZ" readonly="true" /></td>
               </tr>
               <tr>
                   <td width="80px">俯仰角：</td>
                   <td class="mp_td"><cui:input id="view_tilt" name="rprAngleTilt"  readonly="true"/></td>
               </tr>
               <tr>
                   <td width="80px">旋转角：</td>
                   <td class="mp_td"><cui:input id="view_heading" name="rprAngleHead" readonly="true"/></td>
               </tr>
           </table>
           </cui:form>

           <div style="height:30px;width:300px;margin:10px 20px;border:0px solid red;">
               <cui:button label="获取点位信息" onClick="cameraTour.setViewPosition" width="100" ></cui:button>
               <cui:button label="保存" onClick="cameraTour.save" width="55" ></cui:button>
               <cui:button label="重置" onClick="cameraTour.reset" width="55"></cui:button>
           </div>
</div>		 --%>

<script type="text/javascript">
    var initSpeed;
    var initRouteId;
    var route = null;
    var cameraTour = null;
    $(function () {
        cameraTour = {
            url: "${ctx}/common/areadevice/findForCombotree?deviceType=1&cusNumber=" + jsConst.CUS_NUMBER,
            routeurl: "${ctx}/route/findByPrisonCode?cusNumber=" + jsConst.CUS_NUMBER + "&departCode=" + jsConst.DEPARTMENT_ID,
            videoPostion: {
                my: "center",//中间
                at: "center",
                of: window
            },
            init: function () {
                //设置为飞行模式
                map_3d.setMouseFlyMode();
                //map_3d.__g.interactMode = gviInteractMode.gviInteractNormal;
            },
            setViewPosition: function () {
                map_3d.cameraInfo = map_3d.__g.camera.getCamera();
                $('#view_x').textbox("setValue", map_3d.cameraInfo.position.x);
                $('#view_y').textbox("setValue", map_3d.cameraInfo.position.y);
                $('#view_z').textbox("setValue", map_3d.cameraInfo.position.z);
                $('#view_heading').textbox("setValue", map_3d.cameraInfo.angle.heading);
                $('#view_tilt').textbox("setValue", map_3d.cameraInfo.angle.tilt);
            },

            showRouteManagers: function () {
                $("#routeDialog").dialog({
                    width: 1000,                  //属性
                    height: 530,                 //属性
                    subTitle: '巡视管理',
                    modal: false,                 //弹出窗口底层可操作
                    position: {my: "left", at: "left+30px top+300px ", of: window}, //弹出窗口 位置
                    autoOpen: true,
                    url: '${ctx}/route/manager',
                });
            },
            save: function () {
                if ($("#routePointForm").form("valid")) {
                    var data = $("#routePointForm").form("formData");
                    $.ajax({
                        type: "post",
                        data: data,
                        url: "${ctx}/routePoint/addOrUpdatePoint.json",
                        success: function (data) {
                            if (data.message == "操作成功") {
                                $("#routePointForm").form("clear");
                                $("#routeBox").combobox("setValue", initRouteId);
                                $("#rprRouteSpeed").textbox("setValue", initSpeed);
                            }
                            $.message({
                                message: data.message,
                                cls: "success",
                                iframePanel: true
                            });
                        }
                    });

                }
            },
            reset: function () {
                if ($("#rprPointId").val() != "") {
                    var selrow = $("#routePointGrid").grid("option", "selrow");
                    setData(selrow);
                } else {
                    $("#routePointForm").form("clear");
                }

            },
            routeChange: function () {
                var routeId = $("#routeBox").combobox("getValue");
                initRouteId = routeId;
                $.ajax({
                    type: "post",
                    data: {id: routeId},
                    url: "${ctx}/route/findByRouteId.json",
                    success: function (data) {
                        $("#rprRouteSpeed").textbox("setValue", data.rpiSpeed);
                        initSpeed = data.rpiSpeed;
                    }
                })
            },
            onblurEven: function () {
                var rprOrder = $("#rprOrder").textbox("getValue");
                $("#rprPointName").textbox("setValue",rprOrder);
            }

        }
        $("#equipmentTree").combotree({url: cameraTour.url});
        $("#routeBox").combobox({url: cameraTour.routeurl});
        //初始化
        cameraTour.init();
    })
</script>
</body>
</html>
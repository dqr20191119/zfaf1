<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<body>
<cui:dialog id="pointDorm_dialog" width="60%" height="60%" title="监舍点位信息" iframePanel="true" autoOpen="false"></cui:dialog>
<div class="rightDivStyle right-zb">
    <h4>监舍信息点位维护</h4>
    <a href="javascript:pointDormManager.allPointManage();" class="modelUrl">点位管理</a>
    <div class="model_div">
        <cui:form  id="pointDorm_pointForm" method="post" action="">
            <input type="hidden" id="pointDorm_alpCusNumber" name="alpCusNumber"  />
            <div class="model_form">
                <div class="model-input" id="pointDorm_regionDiv">
                    <label>区域名称：</label>
                    <cui:combotree id="pointDorm_region" name="alpGrandId" onChange="pointDormManager.changeGrand"></cui:combotree>
                </div>
                <div class="model-input">
                    <label>监舍序号：</label>
                    <cui:combobox id="pointDorm_dormInfo" textField="jsh" valueField="jsId"></cui:combobox>
                </div>
                <div class="model-input">
                    <cui:button label="创建" onClick="pointDormManager.creButtonEvent" width="55" ></cui:button>
                    <cui:button label="销毁" onClick="pointDormManager.delButtonEvent" width="55" ></cui:button>
                        <%--<cui:input id="pointDorm_deviceName" name="alpDeviceName" value="" />--%>
                        <%--<cui:checkbox id="pointDorm_create" name="checkboxCreate" label="创建" onChange="pointDormManager.checkBoxEvent"></cui:checkbox>--%>
                </div>

                <div class="title-box">
                	<h4>点位坐标</h4>
                </div>
                <div class="model-input">
                    <label>X轴坐标：</label>
                    <cui:input id="pointDorm_point_x" name="alpXPointIdnty"  placeholder="暂无"/>
                </div>
                <div class="model-input">
                    <label>Y轴坐标：</label>
                    <cui:input id="pointDorm_point_y" name="alpYPointIdnty"  placeholder="暂无"/>
                </div>
                <div class="model-input">
                    <label>Z轴坐标：</label>
                    <cui:input id="pointDorm_point_z" name="alpZPointIdnty"  placeholder="暂无"/>
                </div>
                <div class="model_buttons_save">
                    <cui:button label="保 存" onClick="pointDormManager.save" width="55" ></cui:button>
                    <cui:button label="重置" onClick="pointDormManager.reSetData" id="pointDorm_reSet" width="55"></cui:button>
                    <cui:button label="取消" onClick="pointDormManager.canel" id="pointDorm_canel" width="55"></cui:button>
                </div>
            </div>
        </cui:form>
    </div>
</div>
<script type="text/javascript">
    /**
     * 1、编辑状态时 隐藏所有已有的点位  始终保持显示在编辑的点位
     * 2、有一个按钮能够显示所有已有的点位
     * */
    var pointDormManager;
    var deviceIdForEdit="";
    var modelCurrent=null;//当前添加模型的对象
    var flagIfCheck=false;//是否已经勾选
    var flagIfHave=false;//模型一次只能添加一个
    $(function(){
        pointDormManager = {
            cusNumber:jsConst.CUS_NUMBER,//监狱编号
            url:null,
            mapName:null,

            init:function(){
                $('#pointDorm_region').combotree({url:"${ctx}/common/areadevice/findForCombotree.json?cusNumber="+pointDormManager.cusNumber+"&deviceType=0"});
                <%--$('#pointDorm_canel').hide();--%>

            },
            changeGrand:function(){
                pointDormManager.changeGrandView($('#pointDorm_region').combotree('getValue'));
            },
            //区域改变时切换当前视角
            changeGrandView:function(grandId){
                //通过接口获取监舍号和监舍id
                $("#pointDorm_dormInfo").combobox("reload",{url:"${ctx}/common/all/findAllJsxz.json?cusNumber="+pointDormManager.cusNumber+"&areaId="+grandId});
                var dataPara={'alpGrandId':grandId,'alpCusNumber':pointDormManager.cusNumber};
                var data=_DOCUMENT_EVENT.request_data("${ctx}/point/getViewByGrandId",dataPara,false);
                if(data!=null&&data.length>0)
                    map.viewPosition(data[0].vfuXCrdnt,data[0].vfuYCrdnt,data[0].vfuZCrdnt,data[0].vfuHeadingCrdnt,data[0].vfuTiltCrdnt,data[0].vfuParentViewId,grandId,1);
            },

            //创建按钮点击事件
            creButtonEvent:function(){
                var id=$(this).attr("id");
                if(flagIfCheck == false) {
                    flagIfCheck = true;
                    pointDormManager.attachMouse();
                }else{
                    _DOCUMENT_EVENT.messge("已处于创建状态，勿重复点击")
                }
            },

            //销毁按钮点击事件
            delButtonEvent:function () {
                if(flagIfCheck && modelCurrent!=null && modelCurrent!="") {
                    map_3d.__g.objectManager.delayDelete(modelCurrent.guid, 0);
                    pointDormManager.reSetCommon();
                    $('#pointDorm_point_x').textbox('setValue',"");
                    $('#pointDorm_point_y').textbox('setValue',"");
                    $('#pointDorm_point_z').textbox('setValue',"");
                }else {
                    _DOCUMENT_EVENT.messge("点位未创建或未在编辑模式！")
                }
            },
            //点击添加模型按钮 进入添加状态
            attachMouse:function(){
                map_3d.__g.mouseSelectMode =  gviMouseSelectMode.gviMouseSelectClick ;//鼠标左键点选模式（up的时候触发）
                //鼠标点击事件添加模型
                if (typeof (pointDormManager.createOnMouseClickSelect) == "function") ____events["onMouseClickSelect"] = pointDormManager.createOnMouseClickSelect;
                //转为编辑模式
                map_3d.__g.interactMode = gviInteractMode.gviInteractEdit;
                if (typeof (pointDormManager.fnonobjectediting) == "function") ____events["onObjectEditing"] = pointDormManager.fnonobjectediting;
            },
            createOnMouseClickSelect:function(pickResult, intersectPoint, mask, eventSender){
                if(flagIfCheck==true&&flagIfHave==false){//判断已经勾选过复选框
                    //创建模型或者图片
                    pointDormManager.creatModelOrImg(intersectPoint.x,intersectPoint.y,intersectPoint.z);
                    modelCurrent.glow(-1);//闪烁
                    //以下编辑状态下动态获取坐标值
                    if (typeof (pointDormManager.fnonobjectediting) == "function") ____events["onObjectEditing"] = pointDormManager.fnonobjectediting;
                    map_3d.__g.objectEditor.startEditRenderGeometry(modelCurrent, gviGeoEditType.gviGeoEdit3DMove);
                    //以下为坐标赋值 modelCurrent
                    var posiotion={alpXPointIdnty:intersectPoint.x,alpYPointIdnty:intersectPoint.y,alpZPointIdnty:intersectPoint.z};
                    pointDormManager.showPlaceValue(posiotion);//为坐标赋值
                    flagIfHave=true;
                }
            },
            fnonobjectediting:function(){
                var position={'alpXPointIdnty':modelCurrent.getFdeGeometry().position.x,
                    'alpYPointIdnty':modelCurrent.getFdeGeometry().position.y,
                    'alpZPointIdnty':modelCurrent.getFdeGeometry().position.z};
                pointDormManager.showPlaceValue(position);
            },

            //为坐标赋值
            showPlaceValue:function(data){
                $('#pointDorm_point_x').textbox('setValue',data.alpXPointIdnty);
                $('#pointDorm_point_y').textbox('setValue',data.alpYPointIdnty);
                $('#pointDorm_point_z').textbox('setValue',data.alpZPointIdnty);
            },

            //创建模型或者图片
            creatModelOrImg:function(x,y,z){
                modelCurrent = map.createModelImm(mapDevice["dt_8"]["model"]["d_8"],x,y,z);
            },

            //添加的保存
            save:function(){
                if(modelCurrent!=""&&modelCurrent!=null){
                    if($("#pointDorm_pointForm").form("valid")){
                        map_3d.__g.objectEditor.finishEdit();
                        $('#pointDorm_alpCusNumber').val(jsConst.CUS_NUMBER);
                        var formData = $("#pointDorm_pointForm").form("formData");
                        formData.alpStatuIdnc = 0;
                        formData.alpDeviceType = "8";

                        //update wq 2018-4-14
                        //绑定接口回传数据
                        formData.alpDeviceName= $('#pointDorm_dormInfo').combobox('getText');
                        if(deviceIdForEdit!=''&&deviceIdForEdit!=null)
                            formData.alpDeviceIdnty=deviceIdForEdit;
                        else
                            formData.alpDeviceIdnty=$('#pointDorm_dormInfo').combobox('getValue');
                        _DOCUMENT_EVENT.request_data_callback('${ctx}/point/savePoint',pointDormManager.callBackSave,formData,null);
                    }else{
                        _DOCUMENT_EVENT.alert_msg("表单验证失败！！！");
                    }
                }else{
                    _DOCUMENT_EVENT.alert_msg("请先在地图上点击添加的模型！！！");
                }
            },
            //保存回调函数
            callBackSave:function(result) {
                if(result.success){
                    _DOCUMENT_EVENT.messge("操作成功！");
                    map_3d.__g.interactMode = gviInteractMode.gviInteractSelect; //设置当前模式为拾取模式
                    pointDormManager.saveReSet(); //调用清空方法
                }else
                    _DOCUMENT_EVENT.messge("操作失败，请检查！");
            },
            //重置
            reSetData:function(){
                $("#pointDorm_pointForm").form("clear");
                pointDormManager.reSetCommon();
            },
            //保存后的数据重置
            saveReSet:function(){
                $('#pointDorm_point_x').textbox('setValue',"");
                $('#pointDorm_point_y').textbox('setValue',"");
                $('#pointDorm_point_z').textbox('setValue',"");
                pointDormManager.reSetCommon();
            },
            reSetCommon:function(){
                if(modelCurrent != null){
                    modelCurrent.glow(0);//停止闪烁
                }
                deviceIdForEdit="";
                modelCurrent=null;
                flagIfCheck=false;
                flagIfHave=false;
            },
            //所有点位信息dialog
            allPointManage:function(){
                $("#pointDorm_dialog").dialog({
                    width:1000,
                    height:520,
                    subTitle :'点位信息列表',
                    modal:false,                 //弹出窗口底层可操作
                    position: { my: "left",at:"left+30px top+300px ",of: window} , //弹出窗口 位置
                    autoOpen:true,
                    url:'${ctx}/point/list2'
                });
            },
            canel:function(){
                map.setMouseFlyMode();
                pointDormManager.reSetData();
            }
        }
        pointDormManager.init();
    });
</script>
</body>
</html>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<c:set var="document_id"  value="point_managementInfo"/> <!-- 页面元素id前缀 -->
<c:set var="parent_document_id"  value="point_managementList"/> <!-- 父页面页面元素id前缀 -->
<c:set var="fun_name"  value="pointInfo"/> <!-- js对象方法的名称 -->
<div class="PanelCon">
	<div class="PanelList">
		<div class="clearfix panelTop">
				<div style="margin-left: 20px;float: left">
					<label>查询区域：</label>
					<cui:combotree id="${document_id}_region" name="alpGrandId"></cui:combotree>
					<label>设备类型：</label>
					<cui:combobox id="${document_id}_deviceType" data="${fun_name}.deviceType" ></cui:combobox>
					<label>设备名称：</label>
                    <cui:input id="${document_id}_device"  name="alpDeviceIdnty" ></cui:input>
				</div>
			<div style="float: right;margin-right: 50px">
				<cui:button label="查询" onClick="${fun_name}.search" cls="cyanbtn" 	></cui:button>
				<cui:button label="编辑" onClick="${fun_name}.update" cls="greenbtn" 	></cui:button>
				<cui:button label="删除" onClick="${fun_name}.delPoint" cls="deleteBtn" ></cui:button>
			</div>
		</div>
		<cui:grid id="${document_id}_grid" rownumbers="false" multiselect="true" singleselect="false"
				  fitStyle="fill" onDblClickRow="${fun_name}.onDblClickRow" width="auto" height="400" rowNum="10">
			<cui:gridCols>
				<cui:gridCol name="id" hidden="true" >id</cui:gridCol>
				<cui:gridCol name="alpCusNumber" hidden="true">监狱编号</cui:gridCol>
				<cui:gridCol name="alpDeviceIdnty"   hidden="true">设备id</cui:gridCol>
				<cui:gridCol name="alpDeviceType"   hidden="true">设备类型</cui:gridCol>
				<cui:gridCol name="alpDeviceModel"   hidden="true">设备具体类型</cui:gridCol>
				<cui:gridCol name="alpGrandId"   hidden="true">区域id</cui:gridCol>
				<cui:gridCol name="alpGrandName"  width="40" align="center">区域名称</cui:gridCol>
				<cui:gridCol name="deviceTypeName" width="40" align="center">设备类型</cui:gridCol>
				<cui:gridCol name="alpDeviceName"  align="center" width="40">设备名称</cui:gridCol>
				<cui:gridCol name="alpXPointIdnty" width="40" align="center">X坐标</cui:gridCol>
				<cui:gridCol name="alpYPointIdnty" width="40" align="center">Y坐标</cui:gridCol>
				<cui:gridCol name="alpZPointIdnty" width="40" align="center">Z坐标</cui:gridCol>
				<cui:gridCol name="alpStatuIdnc" width="40" align="center" formatter="${fun_name}.forStatus">状态</cui:gridCol>
			</cui:gridCols>
			<cui:gridPager gridId="${document_id}_grid" />
		</cui:grid>
	<!-- 列表展示结束 -->
</div>
</div>
<cui:dialog id="adddialog" reLoadOnOpen="true" modal="true" autoOpen="false" width="1000" height="500" maximizable="false"></cui:dialog>
<cui:dialog id="resdialog" reLoadOnOpen="true" modal="true" autoOpen="false" width="80%" height="80%" maximizable="false"></cui:dialog>
<script>
var pointInfo;
$(function(){
    pointInfo = {
		cusNumber:jsConst.CUS_NUMBER,//监狱编号
        //设备类型 (由于点位维护中，只包括以下6种设备，所以未用统一编码接口 4.20.57)
        deviceType: [
            {"text":"摄像机","value":"1"},
            {"text":"报警器","value":"3"},
            {"text":"门禁","value":"4"},
            {"text":"广播","value":"5"},
            {"text":"对讲分机","value":"2"},
            {"text":"对讲主机","value":"6"}
        ],
		init:function(){
            $('#${document_id}_region').combotree({url:"${ctx}/common/areadevice/findForCombotree.json?cusNumber="+pointInfo.cusNumber+"&deviceType=0"});
            $('#${document_id}_grid').grid({url:"${ctx}/point/findByPage?alpCusNumber="+pointInfo.cusNumber});
		},
        forStatus:function(value,row,index){
		    if(value==0)return '有效';
			else if(value==1)return '无效';
		},
		//查询
		search :function(){
            var postData = {
                "alpGrandId":$('#${document_id}_region').combotree('getValue'),
                "alpDeviceType":$('#${document_id}_deviceType').combobox('getValue'),
                "deviceName":$('#${document_id}_device').textbox('getValue'),
            }
            $('#${document_id}_grid').grid('option', 'postData', postData);
            $('#${document_id}_grid').grid('reload');
		},
		//编辑　修改
		update :function(){
			var selrow = $('#${document_id}_grid').grid("option", "selrow");
			if (selrow != null) {
				var rowData = $('#${document_id}_grid').grid("getRowData", selrow);
				//先关闭dialog 再调用赋值方法
                $("#${parent_document_id}_dialog").dialog("close");
                $("#${parent_document_id}_pointForm").form("clear");
                $('#${parent_document_id}_region').combotree('setValue',rowData.alpGrandId);
                $('#${parent_document_id}_region').combotree('setText',rowData.alpGrandName);
                $('#${parent_document_id}_deviceType').combobox('setText',rowData.deviceTypeName);
                $('#${parent_document_id}_deviceType').combobox('setValue',rowData.alpDeviceType);
                deviceIdForEdit=rowData.alpDeviceIdnty;
                deviceModelType=rowData.alpDeviceModel;
                creatDeviceType=rowData.alpDeviceType;
                pointMana.changeDeviceTypeView();//设备列表展示正在编辑的设备
                $('#${parent_document_id}_point_x').textbox('setValue',rowData.alpXPointIdnty);
                $('#${parent_document_id}_point_y').textbox('setValue',rowData.alpYPointIdnty);
                $('#${parent_document_id}_point_z').textbox('setValue',rowData.alpZPointIdnty);
                //隐藏创建按钮 重置按钮  添加取消按钮
                $('#${parent_document_id}_canel').show();
                $('#${parent_document_id}_reSet').hide();
                pointMana.changeGrandView(rowData.alpGrandId);//传当前区域id获取当前视角
                map_3d.__g.interactMode = gviInteractMode.gviInteractEdit;//转为编辑模式
                pointMana.creatModelOrImg(rowData.alpXPointIdnty,rowData.alpYPointIdnty,rowData.alpZPointIdnty);//创建模型或者图片
                modelCurrent.glow(-1);//闪烁
                /*** 以下开始编辑状态 动态获取坐标值*/
                if (typeof (pointMana.fnonobjectediting) == "function") ____events["onObjectEditing"] = pointMana.fnonobjectediting;
                map_3d.__g.objectEditor.startEditRenderGeometry(modelCurrent, gviGeoEditType.gviGeoEdit3DMove);
            } else {
                _DOCUMENT_EVENT.messge("请先选择一条记录！");
			}
		},
		//按取消后重现之前的功能
		canelSet:function(){
            $('#${parent_document_id}_reSet').show();
            $('#${parent_document_id}_modelCreat').show();
            pointMana.reSetData();
		},
		//删除
		delPoint1 :function(){
            var selrow = $('#${document_id}_grid').grid("option", "selrow");
            if (selrow != ""&&selrow != null) {
                $.confirm("确认是否删除？", function(r) {
                    if (r) {
                        var rowData = $('#${document_id}_grid').grid("getRowData", selrow);
                        var url ="${ctx}/point/destoryPoint/" + rowData.id;
                        _DOCUMENT_EVENT.request_data_callback(url,pointInfo.callBackDelete,null,null);
                    }
                });
            } else {
                _DOCUMENT_EVENT.messge("请先选择要删除的记录！！！");
            }
		},

        delPoint:function(){
            var selrow = $('#${document_id}_grid').grid("option", "selarrrow");
            if (selrow.length>0) {
                $.confirm("确认是否删除？", function(r) {
                    if (r) {
                        var j =0;
                        var k =0;
                        for(var i=0;i<selrow.length;i++){
                            var rowData = $('#${document_id}_grid').grid("getRowData", selrow[i]);
                            $.ajax({
                                async :false,
                                type : "post",
                                data : {"id":rowData.id},
                                url : "${ctx}/point/destoryPoint.json",
                                success : function(data) {
                                    if(data.msg=="操作成功"){
                                        j++;
                                    }else{
                                        k++;
                                    }
                                }
                            });
                        }
                        $('#${document_id}_grid').grid("reload");
                        $.message({
                            message : "删除成功"+j+"条,删除失败"+k+"条.",
                            cls : "success"
                        });
                    }
                });
            } else {
                $.message({
                    message : "请先勾选需要删除记录！",
                    cls : "warning"
                });
            }
        },

        callBackDelete:function(result){
            if (result.success){
                _DOCUMENT_EVENT.messge("删除成功！");
                $('#${document_id}_deviceType').combobox('setValue','');
                $('#${document_id}_device').textbox('setValue','');
                $('#${document_id}_grid').grid("reload");
			}else{
                _DOCUMENT_EVENT.messge("删除失败，请检查！");
			}
		}
	}
    pointInfo.init();
});
</script>

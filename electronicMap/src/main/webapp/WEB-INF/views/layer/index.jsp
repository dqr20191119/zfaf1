<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta http-equiv="cache-control" content="no-store, no-cache, must-revalidate"> 
<meta http-equiv="pragma" content="no-cache"> 
<meta http-equiv="expires" content="0">
<title>模型管理</title>
</head>
<body>
	<div class="rightDivStyle right-zb">
		<h4>报警图层管理</h4>
		<div class="model_div">
			<cui:form id="layerForm" method="post" action="" >
				<input type="hidden" id="id" name="id"/>
				<input type="hidden" id="linCusNumber" name="linCusNumber">
				<input type="hidden" id="linPoints" name="linPoints"/>
				<div class="model_form">
<%--					<div class="model-input">
						<label>设备类型：</label>
						<cui:combobox id="linDeviceType" name="linDeviceType" data="layer3d.deviceType" onChange="layer3d.updateDevice"/>
					</div>--%>
					<div class="model-input">
						<label>设备区域：</label>
						<cui:combotree id="linAreaId" name="linAreaId" onChange="layer3d.updateDevice"></cui:combotree>
					</div>
					<div class="model-input">
						<label>设备：</label>
						<cui:combotree id="linDeviceId" name="linDeviceId" onChange="layer3d.findLayer" onNodeClick="layer3d.linDeviceClick"></cui:combotree>
					</div>
					<div class="model-input">
						<label>图层名称：</label>
						<cui:input id="linLayerName" name="linLayerName" value=""/>
					</div>
					<div class="model-input">
						<label>备注：</label>
						<cui:input id="linRemark" name="linRemark" value=""/>
					</div>
					<div class="model_buttons_oper">
						<cui:button label="创建路线" onClick="map_3d.createRenderPolyline" width="90" ></cui:button>
						<cui:button label="路线转图层" onClick="map_3d.createRenderPolygon" width="90" ></cui:button>	
					</div>
					<div class="model_buttons_save">
						<cui:button label="保存" onClick="layer3d.save" ></cui:button>
						<cui:button label="重置" onClick="layer3d.reset" ></cui:button>
					    <cui:button label="清除标签" onClick="layer3d.deleteLayer" ></cui:button>	
					</div>
				</div>
			</cui:form>
		</div>
	</div>


<script type="text/javascript">
var layer3d;
$(function(){
	layer3d = {
		cusNumber:jsConst.CUS_NUMBER,
		url:null,
		mapName:null,
		//1-摄像头，2-对讲，3-报警器，4-门禁，5-广播，0-只查询区域
		deviceType: <%=CodeFacade.loadCode2Json("4.20.57")%>,
		//保存模型
		save:function (){
			if($("#layerForm").form("valid")){
					if($('#id').val()!=''){
					$.message({
						message:"当前设备已经存在一个图层，无法继续保存！",
						iframePanel:true
					});
					return;
				}
				if($('#linPoints').val()==''){
					$.message({
						message:"请先创建一个图层！",
						iframePanel:true
					});
					return;
				}
				$('#linCusNumber').val(layer3d.cusNumber);
				var formData = $("#layerForm").form("formData");
				formData.linDeviceType = "3";
/*				var linDeviceType = $('#linDeviceType').combobox('getValue');
				if(linDeviceType == ""){
					$.message({
						message:"请选择设备类型！",
						iframePanel:true
					});
					return;
				}*/
				var linAreaId = $('#linAreaId').combotree('getValue');
				if(linAreaId == ''){
					$.message({
						message:"请选择区域！",
						iframePanel:true
					});
					return;
				}
				var linDeviceId = $('#linDeviceId').combotree('getValue');
				if(linDeviceId == ''){
					$.message({
						message:"请选择一个设备！",
						iframePanel:true
					});
					return;
				}
				var linLayerName = $('#linLayerName').textbox('getValue');
				if(linLayerName == ''){
					$.message({
						message:"请输入图层名称！",
						iframePanel:true
					});
					return;
				}
				$.ajax({
					type : 'post',
					url : '${ctx}/layer/saveLayer',
					data : formData,
					dataType : 'json',
					success : function(data) {
						if(data != null){
							$.message({
								message:"操作成功",
								iframePanel:true
							});
							$('#id').val(data.id);
						}else{
							$.message({
								message:"操作失败",
								iframePanel:true
							});
						}
					},error : function(XMLHttpRequest, textStatus, errorThrown) {
						$.alert({
							message:textStatus,
							title:"提示信息",
							iframePanel:true
						});
					}
				}); 
			}
		},
		//重置输入内容
		reset:function(){
			//map.createRenderPolygonByPointsByDevice('3044',true,'3203');
			$('#id').val('');
			//$('#linDeviceType').combobox('setValue','');
			//$('#linAreaId').combotree('setValue','');
			//$('#linDeviceId').combotree('setValue','');
			$('#linLayerName').textbox('setValue','');
			$('#linRemark').textbox('setValue','');
			$('#linPoints').val('');
			map_3d.deleteGeometry();
		},
		//删除
		deleteLayer:function(){
			if($('#id').val()==''){
				$.message({
					message:"当前设备已经没有图层，无法删除！",
					iframePanel:true
				});
				return;
			}
			$.ajax({
				type : 'post',
				url : '${ctx}/layer/deleteLayer',
				data : {'id':$('#id').val()},
				dataType : 'json',
				success : function(data) {
					$.message({
						message:"操作成功",
						iframePanel:true
					});
					$('#id').val('');
					$('#linPoints').val('');
					layer3d.reset();
				},error : function(XMLHttpRequest, textStatus, errorThrown) {
					$.alert({
						message:textStatus,
						title:"提示信息",
						iframePanel:true
					});
				}
			}); 
		},
		//更新设备类型
		updateDevice:function(){
			// var deviceType = $('#linDeviceType').combobox('getValue');
			var deviceType = "3";
			var id = $('#linAreaId').combotree('getValue');
			//区域选中
			if(id != ''){
				var selectRegions = $('#linAreaId').combotree('tree').tree('getSelectedNodes');
				if(selectRegions.length>0){
					var parentId = selectRegions[0].pid;
				}
				//显示隐藏模型
				map.showHideByAreaId(parentId,id,1);
				//设置管理员视角
				map.setAdminCameraByAreaId(id,layer3d.cusNumber);
				//设备类型选中
				if(deviceType != ''){
					$('#linDeviceId').combotree({url:"${ctx}/common/areadevice/findForCombotree.json?cusNumber="+layer3d.cusNumber+"&deviceType="+deviceType+"&id="+id});
					$('#linDeviceId').combotree('reload');
				}
			}
		},
		//根据设备类型和设备编号查找图层
	    findLayer:function(){
			// var linDeviceType = $('#linDeviceType').combobox('getValue');
			var linDeviceType = "3";
			var linDeviceId = $('#linDeviceId').combotree('getValue');
			layer3d.reset();
		    $.ajax({
				type : 'post',
				url :"${ctx}/layer/findByLayer",
				data : {"linDeviceType":linDeviceType,'linDeviceId':linDeviceId,'linCusNumber':layer3d.cusNumber},
				dataType : 'json',
				success : function(data) {
					if(data!=null){
						$('#id').val(data.id);
						$('#linLayerName').textbox('setValue',data.linLayerName);
						$('#linRemark').textbox('setValue',data.linRemark);
						var points = data.layerPoints;
				        var linPoints = '';
				        var newPoints = [];
						for(var i=0;i<points.length;i++){
							var point = points[i];
							var newPoint = {};
							newPoint.x = parseFloat(point.lpoPointX);
							newPoint.y = parseFloat(point.lpoPointY);
							newPoint.z = parseFloat(point.lpoPointZ);
							newPoints.push(newPoint);
				            linPoints += newPoint.x+","+newPoint.y+","+newPoint.z+";";
						}
						$('#linPoints').val(linPoints);
						var guidKey = "custom_"+linDeviceId;
						map_3d.deleteGeometry();	
						map_3d.createRenderPolygonByPoints(newPoints,false,guidKey);
					}
				}
			});
	    },
	    
	    linDeviceClick:function(){
	    	var linDeviceName =  $('#linDeviceId').combotree('getText');
	    	var tcName = linDeviceName + '-报警图层';
	    	$('#linLayerName').textbox('setText', tcName);
	    	$('#linLayerName').textbox('setValue', tcName);
	    },
	    
	    //区域初始化
	    init:function(){
	    	$('#linAreaId').combotree({url:"${ctx}/common/areadevice/findForCombotree.json?cusNumber="+layer3d.cusNumber+"&deviceType=0"});
	    }
	}
	layer3d.init();
});
    
</script>
</body>
</html>
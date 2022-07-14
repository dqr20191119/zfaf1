<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<link href="${ctx}/static/module/sign/css/signStyle.css" rel="stylesheet"> 
<link href="${ctx}/static/module/sign/css/icon-font/iconfont.css" rel="stylesheet">

<style>
.pointBorder{
	border:#FF0000 3px solid;
}
.gxdw_button {
	width: 80%;
}
.form-control {
	width: 100%;
}
#ewdw-layout .coral-panel .coral-tree li a{
	color:#333;
}
h3{width:800px;margin:80px auto 40px auto;text-align:center;font-size:20px;}
	
.imagebox{
	top:10px;
	margin:0 auto;
	position:relative;
	}
.imagebox img{
	width:100%;
	height:100%;
	}
.bottom{
	position: absolute;
    left: 0;
    bottom: 0;
    right: 0;
}
.sureSign{
	display: inline-block;
    width: 40%;
    background: none repeat scroll 0 0 #3686e8;
}
.imagebox .coral-dialog{
	z-index: 200;
}
#signDialog{
	display: none;
}

#signDialog .dialog-buttons {
    line-height: 28px;
}
#signDialog .coral-form-default {
    position: relative;
    padding-top: 0px;
    padding-bottom: 0px;
}
</style>

<div style="height: 100%;">
	<cui:layout id="ewdw-layout" fit="true">
		<cui:layoutRegion region='west' title="区域树" split="true" showSplitIcon="true" style="width: 220px"
			maxWidth="250">
			<div class="F-left" style="overflow-y:auto; overflow-x:auto;width: 100%;background-color: #EDEEEE;">
				<!-- 区域树 -->
				<cui:tree id="areaTree" asyncEnable="true" keepParent="true"
					 asyncType="post" asyncUrl="" simpleDataEnable="true"
					asyncAutoParam="id,name" onClick="areaTreeClick" onDblClick="areaTreeDblClick" rootNode="true" showRootNode="true" >
				</cui:tree>	
			</div>
		</cui:layoutRegion>
		<cui:layoutRegion region='center' split="false" onLoad="" onResize="">
			<div id="layerImgDivEdit" style="overflow-y:auto; overflow-x:auto; width:100%; height:100%;">
			<cui:input type="hidden" id="areaId" ></cui:input>
				<div class="imagebox" id="signx_edit">
					<!-- <h3>鼠标在图片上右键添加标记。</h3> -->
					<img id="planeLayer" onmousedown="$.sign_edit.stopImgDrag(event)" ondrop="$.sign_edit.deviceDrop(event)" ondragover="allowDrop(event)">
					 <%-- <div id="signDialog">
					 	<center>
						 	<cui:form id="formId_AddPoint">
								<table class="table table-fixed" style="width:100%">
									<tr>
										<td><cui:combobox required="true" data="comboData" id="deviceType" emptyText="请选择设备类型" componentCls="form-control" onChange="deviceTypeOnChange"></cui:combobox></td>
									</tr>
									<tr>
										<td><cui:combotree required="true" id="deviceId" url="${ctx}/common/areadevice/nullJson.json" componentCls="form-control"></cui:combotree></td>
									</tr>
								</table>
							</cui:form>
						</center>						
						<div class="dialog-buttons" >
							<input id="sureSign" ></input>
						</div>
			 		</div>  --%>
				</div>
			</div>
		</cui:layoutRegion>
		<cui:layoutRegion region='east' split="true" style="width:250px;" maxWidth="300">
			<div id="refreshLoad" class="F-left" style="overflow-y:auto; overflow-x:auto;width: 100%;background-color: #EDEEEE;">
				<center style="margin-top:10px;">
					<cui:button componentCls="btn-info gxdw_button" label="刷新点位" onClick="refreshPoint"></cui:button>
				</center>
				<!-- 旋转按钮 start -->
				<center style="margin-top:10px;">
					<img style="width:50px;height:50px;left:10px;top:100px" src='${ctx}/static/module/sign/png/anti-clockwise.png' onclick="$.sign_edit.anticlockwise()"/>
					<img style="width:50px;height:50px;left:100px;top:100px" src='${ctx}/static/module/sign/png/clockwise.png' onclick="$.sign_edit.clockwise()"/>
				</center>
				<!-- 旋转按钮 end -->
				<div style="margin-top:10px;">
					<cui:combobox required="true" data="comboData" id="deviceType" 
					emptyText="请选择设备类型" componentCls="form-control" 
					onChange="deviceTypeOnChange_new"></cui:combobox>
				</div>
				<div style="margin-top:20px;">
					<!-- 设备树 -->
					<cui:tree id="deviceTree" asyncEnable="true" keepParent="true"
						 asyncType="post" asyncUrl="" simpleDataEnable="true" 
						 formatter="deviceFormatter" nameIsHTML="true"
						asyncAutoParam="id,name" rootNode="true" showRootNode="true" >
					</cui:tree>	
				</div>
			</div>
		</cui:layoutRegion>
	</cui:layout>
</div>
<cui:dialog id="dialogId_ewtcwh" iframePanel="true" autoOpen="false" resizable="false" reLoadOnOpen="true"
            modal="true"></cui:dialog>
<script type="text/javascript" src="${ctx}/static/module/sign/jquery-sign.js"></script>
<script type="text/javascript" src="${ctx}/static/module/sign/jquery.rotate.min.js"></script>
<script type="text/javascript" src="${ctx}/static/module/sign/jquery-mouseSelect.js"></script>
<script>

var jsConst=window.top.jsConst;
var cusNumber=jsConst.ORG_CODE;							//监狱编号
var userId=jsConst.USER_ID;								//登录人
var dprtmntIdnty=jsConst.DEPARTMENT_ID;					//部门ID

// 1-摄像头，2-对讲分机，3-报警器，4-门禁，5-广播，6-对讲主机，7-高压电网，8-监舍标签，20-地图标签，0-只查询区域
//图层id
var layerId="";
//摄像头类型值
var cameraTypeValue="1";
//对讲分机类型值
var talkBackTypeValue="2";
//报警器类型值
var alarmTypeValue="3";
//门禁类型值
var doorTypeValue="4";
//广播类型值
var broadcastTypeValue="5";
//监舍标签类型值
var jsbqTypeValue="8";

var comboData =[{
    "value":cameraTypeValue,
    "text":"摄像头"
},{            
    "value":alarmTypeValue,
    "text":"报警器"
},{            
    "value":doorTypeValue,
    "text":"门禁"
},{            
    "value":talkBackTypeValue,
    "text":"对讲机"
},{            
    "value":broadcastTypeValue,
    "text":"广播"
},{            
    "value":jsbqTypeValue,
    "text":"监舍标签"
}];

$.parseDone(function(){
	$("#areaTree").tree("reload",
			"${ctx}/common/areadevice/findForCombotree.json?cusNumber="
					+ cusNumber + "&deviceType=0");
	
	$.sign_edit.bindSign('#layerImgDivEdit #signx_edit');//初始化
	//$.sign_edit.setSignColor('#3498DB'); //设置标记框颜色
	//$.sign.setBodyColor('rgba(255,255,255,0.5)'); 设置提示背景颜色
	//$.sign.setFontColor('#000');//设置字体颜色
});

function deviceFormatter(e,node){
	var deviceDom="<label id='label_"+node.id+"' data-type='planePoint_add' data-deviceId="+node.id+" data-cbdTypeIndc="+node.CBD_TYPE_INDC+" draggable='true' ondragstart='$.sign_edit.deviceDrag(event)'>"+node.name+"</label>";
    return deviceDom;
}
function allowDrop(ev)
{
    ev.preventDefault();
}

var clickFlag_;		//单击延时触发
//单击区域展示关联设备
function areaTreeClick(event, id, node){
	if(clickFlag_){
		clearTimeout(clickFlag_);
	}
	clickFlag_=setTimeout(function(){
		//设置区域id
		$("#areaId").textbox("setValue",node.id);
		//更新右侧设备信息
		var deviceType=$("#deviceType").combobox("getValue");
		deviceTypeChange(deviceType);
	},500);
}
//显示点位
function showPoints(){
	//删除所有点位标记
	$("#layerImgDivEdit [id*=Ts]").remove();
	
	$.ajax({
		type : 'post',
		url : '${ctx}/xtgl/planeLayerPoint/searchPlaneLayerPoint.json',
		data : {
			"plpLayerIdnty":layerId
		},
		dataType : 'json',
		success : function(data) {
			if (data.success) {
				if(data.obj.length>0){
					console.log(data.obj);
					$.sign_edit.loadingSign(data.obj);//载入标记数据
				}else{
					console.log("图层未关联点位");
				}
				
			} else {
				$.message({
					iframePanel:true,
					message : data.msg,
					cls : "warning"
				});
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			$.alert({
				message:textStatus,
				title:"信息提示",
				iframePanel:true
			});
		}
	});
}
//双击区域展示关联图层
function areaTreeDblClick(event, id, node){
	if(clickFlag_){
		clearTimeout(clickFlag_);
	}
	//设置区域id
	$("#areaId").textbox("setValue",node.id);
	
	$("#layerImgDivEdit #signx_edit").hide();
	
	$("#layerImgDivEdit #planeLayer").attr("src","");
	
	//删除所有点位标记
	$("#layerImgDivEdit [id*=Ts]").remove();
	
	//更新右侧设备信息
	var deviceType=$("#deviceType").combobox("getValue");
	deviceTypeChange(deviceType);
	
	$.ajax({
		type : 'post',
		url : '${ctx}/xtgl/planeLayer/findByPliAreaId.json',
		data : {'pliAreaId':node.id},
		dataType : 'json',
		success : function(data) {
			console.log(data);	
			if(data.exception==undefined){
				//说明存在关联图层
				if(data.length>0){
					//一个区域关联一个图层，如果存在多个取第一个
					var obj=data[0];
					console.log("存在关联图层");
					//图层宽高
					var pliWidth=obj.pliWidth;
					var pliHeight=obj.pliHeight;
					//图层id
					layerId=obj.id;
					var paramData = {};
					paramData.id = layerId;
					$.ajax({
						type : 'post',
						url : '${ctx}/xtgl/planeLayer/findFile.json',
						data : paramData,
						dataType : 'json',
						success : function(data) {
							if (data.success) {
								if(data.obj.length>0){
									var layerImgId=data.obj[0].id;
									console.log(layerImgId);
									$("#layerImgDivEdit #planeLayer").attr("src","${ctx}/common/affix/download?id="+layerImgId);
				
									$("#layerImgDivEdit .imagebox").width(pliWidth);
									$("#layerImgDivEdit .imagebox").height(pliHeight);
									//显示
									$("#layerImgDivEdit #signx_edit").show();
									//显示点位
									showPoints();
								}else{
									console.log("图层未关联图片");
									$.alert({
										message:"图层未关联图片！",
										title:"信息提示",
										iframePanel:true
									});
								}
								
							} else {
								$.message({
									iframePanel:true,
									message : data.msg,
									cls : "warning"
								});
							}
						},
						error : function(XMLHttpRequest, textStatus, errorThrown) {
							$.alert({
								message:textStatus,
								title:"信息提示",
								iframePanel:true
							});
						}
					});
				}
				//不存在关联图层
				else{
					console.log("不存在关联图层");
					$.confirm( {
						message:"该区域没有关联图层，是否现在关联？",
						iframePanel:true,
						callback: function(sure) {
							if (sure == true) {

								$('#dialogId_ewtcwh').dialog({
									iframePanel : true,
									width : 900,
									height : 600,
									title : "二维图层维护",
									url : '${ctx}/xtgl/planeLayer/index'
								});
                                $("#dialogId_ewtcwh").dialog("open");
								return;
							}
							if (sure == false) {
								console.log('cancel');
							}
						}
					});
				}
			}else{
				$.message({
					iframePanel:true,
			        message:data.exception.cause.message,
			        type:"danger"
			    });
			}
		},error : function(XMLHttpRequest, textStatus, errorThrown) {
			$.alert({
				message:textStatus,
				title:"信息提示",
				iframePanel:true
			});
		}
	});
}

function f_add_new(pointObj){
	if(layerId==""){
		$.message({
			iframePanel:true,
			message : "请选择图层！",
			cls : "waring"
		});
		return;
	}
	
	pointObj.plpCusNumber=jsConst.ORG_CODE;
	pointObj.plpLayerIdnty=layerId;
	var deviceLabelId="label_"+pointObj.plpDeviceIdnty;	
	pointObj.plpPointName = $("#"+deviceLabelId).html(); 
	pointObj.plpDeviceModel = $("#"+deviceLabelId).attr("data-cbdTypeIndc") || "";

	f_add(pointObj);
}

function f_add(pointObj){
	var url = '${ctx}/xtgl/planeLayerPoint/insert.json';
	$.ajax({
		type : 'post',
		url : url,
		data : pointObj,
		dataType : 'json',
		success : function(data) {
			if(data.success){
				//点位插入数据库后返回的id
				pointObj.id=data.obj;
				var pointArray=new Array(pointObj);
				$.sign_edit.loadingSign(pointArray);//载入标记数据
				/* //重置表单数据
				resetForm();
				//关闭添加点位dialog
				$('#signDialog').dialog("destroy"); */
			}else{
				$.message( {
					iframePanel:true,
			        message:data.msg,
			        type:"danger"
			    });
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			$.alert({
				message:textStatus,
				title:"信息提示",
				iframePanel:true
			});
		}
	});
}

function f_delete(pointId){
	$.confirm( {
		message:"确认删除？",
		iframePanel:true,
		callback: function(sure) {
			if (sure == true) {
				$.ajax({
					type : "post",
					url : "${ctx}/xtgl/planeLayerPoint/delete.json?id=" + pointId,
					success : function(data) {
						if (data.success) {
							//删除点位标记
							$('#layerImgDivEdit #signx_edit #Ts'+pointId).remove();
							$.message({
								message : "操作成功",
								cls : "success",
								iframePanel:true
							});		
						} else {
							$.message({
								iframePanel:true,
								message : data.msg,
								cls : "warning"
							});
						}
					},
					error : function(XMLHttpRequest, textStatus, errorThrown) {
						$.alert({
							message:textStatus,
							title:"信息提示",
							iframePanel:true
						});
					}
					
				});  
			}
			if (sure == false) {
				console.log('cancel');
			}
		}
	});
}
/* //设备类型改变
function deviceTypeOnChange(e,ui){
	var areaId=$("#areaId").textbox("getValue");
	$("#deviceId").combotree("clear");
	
	// 是否室外 0-室内，1-室外		
	var outSide='';
	//如果是监狱，则列出监狱中所有室外设备
	if(areaId==cusNumber){
		outSide='1'
	}
	if(ui.value==alarmTypeValue){
		$("#deviceId").combotree("tree").tree("reload","${ctx}/common/areadevice/findDeviceList.json?cusNumber="+cusNumber+"&deviceType=3&areaId="+areaId+"&outSide="+outSide);
	}else if(ui.value==cameraTypeValue){
		$("#deviceId").combotree("tree").tree("reload","${ctx}/jfsb/camera/searchByCbdAreaIdAndCbdCusNumber.json?cbdCusNumber="+cusNumber+"&cbdAreaId="+areaId+"&cbdOutSide="+outSide);
	}else if(ui.value==doorTypeValue){
		$("#deviceId").combotree("tree").tree("reload","${ctx}/common/areadevice/findDeviceList.json?cusNumber="+cusNumber+"&deviceType=4&areaId="+areaId+"&outSide="+outSide);
	}
	else if(ui.value==talkBackTypeValue){
		//对讲用的是编码
		$("#deviceId").combotree("tree").tree("reload","${ctx}/talkBackBase/seachComboData.json?cusNumber="+cusNumber+"&areaId="+areaId);
	}

	$("#deviceId").combotree("tree").tree("option","onLoad",function(){
		$.parser.parse();
	});
} */
//设备类型改变 add by zk 20180518
function deviceTypeChange(deviceType){
	var areaId=$("#areaId").textbox("getValue");
	if(!areaId){
		$.alert({
			message:"请先选择左侧区域",
			title:"信息提示",
			iframePanel:true
		});
		return;
	}
	
	// 是否室外 0-室内，1-室外		
	var outSide='';
	//如果是监狱，则列出监狱中所有室外设备
	if(areaId==cusNumber){
		outSide='1'
	}
	//报警
	if(deviceType==alarmTypeValue){
		$("#deviceTree").tree("reload","${ctx}/common/areadevice/findDeviceList.json?cusNumber="+cusNumber+"&deviceType=3&areaId="+areaId+"&outSide="+outSide);
	}
	//摄像机
	else if(deviceType==cameraTypeValue){
		$("#deviceTree").tree("reload","${ctx}/jfsb/camera/searchByCbdAreaIdAndCbdCusNumber.json?cbdCusNumber="+cusNumber+"&cbdAreaId="+areaId+"&cbdOutSide="+outSide);
	}
	//门禁
	else if(deviceType==doorTypeValue){
		$("#deviceTree").tree("reload","${ctx}/common/areadevice/findDeviceList.json?cusNumber="+cusNumber+"&deviceType=4&areaId="+areaId+"&outSide="+outSide);
	}
	//对讲
	else if(deviceType==talkBackTypeValue){
		//对讲用的是编码
		$("#deviceTree").tree("reload","${ctx}/talkBackBase/seachComboData.json?cusNumber="+cusNumber+"&areaId="+areaId);
	}
	//广播
	else if(deviceType==broadcastTypeValue){
		$("#deviceTree").tree("reload","${ctx}/common/areadevice/findDeviceList.json?cusNumber="+cusNumber+"&deviceType=5&areaId="+areaId+"&outSide="+outSide);
	}
	//监舍标签
	else if(deviceType==jsbqTypeValue){
		$("#deviceTree").tree("reload","${ctx}/common/all/findLcjsh.json?cusNumber="+cusNumber+"&areaId="+areaId);
	}
	//其他
	else{
		$("#deviceTree").tree("reload","${ctx}/common/areadevice/nullJson.json");
	}
}
function deviceTypeOnChange_new(e,ui){
	deviceTypeChange(ui.value);
}
function refreshPoint(){
	 $("#refreshLoad").loading({
	        text : "正在刷新。。。。",
	        loadingIcon : "coral-icon-loading"
	});
	
	//如果图层ID（layerId）为空，则刷新整个监狱的点位信息
	$.ajax({
		type : "post",
		url : "${ctx}/xtgl/planeLayerPoint/refreshPoint.json",
		data:{
			"plpLayerIdnty":layerId,
			"cusNumber":cusNumber
		},
		success : function(data) {
			if (data.success) {
				//显示点位
				showPoints();
				$("#refreshLoad").loading("hide");
				$.message({
					message : "操作成功",
					cls : "success",
					iframePanel:true
				});		
			} else {
				$.message({
					iframePanel:true,
					message : data.msg,
					cls : "warning"
				});
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			$.alert({
				message:textStatus,
				title:"信息提示",
				iframePanel:true
			});
		}
		
	});  
}
/* //重置
function resetForm(){
	$("#formId_AddPoint").form("reset");
} */
</script>
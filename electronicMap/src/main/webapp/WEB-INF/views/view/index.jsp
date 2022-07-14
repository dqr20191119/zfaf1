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
<title>视角管理</title>
</head>
<body>
	<cui:dialog id="viewDialog" width="60%" height="60%" title="" iframePanel="true" autoOpen="false">
	</cui:dialog>
		<div class="rightDivStyle current-sj">
			<h4>视角维护</h4>
			<a href="javascript:view3d.showViewList();" class="modelUrl">视角管理</a>
			<div class="model-box">
				<cui:form id="viewForm" method="post" action="">
				<input type="hidden" id="id" name="id" value="" />
				<input type="hidden" id="cusNumber" name="vfuCusNumber" value="" />
				<input type="hidden" id="parentArreaId" value="" />
				<div class="model-content-sj">
					<div class="model-input">
						<label>视角名称：</label>
						<cui:input id="viewName" name="vfuViewName" value="" style="width:100px;" />
					</div>
					<div class="model-input">
						<label>视角类型：</label>
						<cui:combobox id="viewType" data="view3d.viewTypeData" />
					</div>
					<div class="model-input">
						<label>关联区域：</label> 
						<cui:combotree id="viewRegion" name="vfuAreaId" url="${ctx}/common/areadevice/nullJson.json"
						simpleDataEnable="true" simpleDataIdKey="id" simpleDataPIdKey="pid" keyName="name" onChange="view3d.regionOrSttsChange">
						</cui:combotree> 
					</div>
					<div class="model-input">
						<label>视角属性：</label>
						<cui:combobox id="viewConfine" name="vfuConfine" data="view3d.confineData"/>
					</div>
					<div class="model-input">
						<label>垂直视角：</label>
						<cui:combobox id="viewStts" name="vfuStts" data="view3d.sttsData" onChange="view3d.regionOrSttsChange" />
					</div>
					<div class="model-input">
						<label>默认视角：</label>
						<cui:checkbox id="defIndc" name="vfuDefIndc" value="0" label="默认视角" checked="true"></cui:checkbox>
					</div>
					<%-- <div class="model_buttons">
						<cui:button label="拾 取 模 型" onClick="view3d.selectModel" ></cui:button>
						<cui:button label="隐 藏 模 型" onClick="view3d.hideModel" ></cui:button>
					</div> --%>
				</div>
				<h4>视角点位坐标</h4>
					<div class="model-content-sj second">
					<div class="model-input">
						<label> X坐标：</label>
						<cui:input id="view_x" name="vfuXCrdnt" value="" />
					</div>
					<div class="model-input">
						<label> Y坐标：</label>
						<cui:input id="view_y" name="vfuYCrdnt" value="" />
					</div>
					<div class="model-input">
						<label> Z坐标：</label>
						<cui:input id="view_z" name="vfuZCrdnt" value="" />
					</div>
					<div class="model-input">
						<label> 俯仰角：</label>
						<cui:input id="view_tilt" name="vfuTiltCrdnt" value="" />
					</div>
					<div class="model-input">
						<label> 旋转角：</label>
						<cui:input id="view_heading" name="vfuHeadingCrdnt" value="" />
					</div>
					<div class="model_buttons_save">
						<cui:button label="保存" onClick="view3d.save" width="51" ></cui:button>
						<cui:button label="重置" onClick="view3d.reset" width="51"></cui:button>
						<cui:button label="删除" onClick="view3d.delView" width="51"></cui:button>
					</div>
					<div class="model-content-sj">
			</cui:form>
			</div>
	</div>
<script type="text/javascript">
var view3d;
$(function(){
	view3d = {
		cusNumber:jsConst.CUS_NUMBER,
		regionUrl:"${ctx}/common/areadevice/findForCombotree.json?cusNumber="+jsConst.CUS_NUMBER+"&deviceType=0",
		mapName:null,
		viewTypeData: <%=CodeFacade.loadCode2Json("4.20.9")%>,
		sttsData: <%=CodeFacade.loadCode2Json("4.20.11")%>,
		confineData: <%=CodeFacade.loadCode2Json("4.20.10")%>,

		init:function(){
			$.parseDone(function(){
				//初始化区域下拉树
				$("#viewRegion").combotree("tree").tree("reload",view3d.regionUrl);
				//调用三维地图摄像机视角改变事件
				map_3d.onCameraChange();
			})
		},
		//设置视角信息 
		setViewPosition:function(x,y,z,heading,tilt){//x,y,z,选择角,俯仰角
			$('#view_x').textbox('setValue',x);
			$('#view_y').textbox('setValue',y);
			$('#view_z').textbox('setValue',z);
			$('#view_heading').textbox('setValue',heading);
			$('#view_tilt').textbox('setValue',tilt);
		},
		//保存视角
		save:function (){
			if(view3d.formvalid()){
				$('#cusNumber').val(jsConst.CUS_NUMBER);
				var formData = $("#viewForm").form("formData");
				formData.vfuViewType = $('#viewType').combobox('getValue');
				formData.vfuDefIndc = $('#defIndc').is(':checked')?1:0;
				formData.vfuConfine = $('#viewConfine').combobox('getValue');
				$.ajax({
					type : 'post',
					url : '${ctx}/view/saveView',
					data : formData,
					dataType : 'json',
					success : function(data) {
						$.message({
							message:"操作成功",
							iframePanel:true
						});
						$('#gridView').grid('reload');
					},
					error : function(XMLHttpRequest, textStatus, errorThrown) {
						$.alert({
							message:textStatus,
							title:"提示信息",
							iframePanel:true
						});
					}
				}); 
			}
		},
		//显示视角列表窗口
		showViewList:function(){
			$("#viewDialog").dialog({
			    width:1000,                  //属性
			    height:520,                 //属性
			    subTitle :'视角信息列表', 
			    modal:true,                 //弹出窗口底层可操作
			    position: { my: "left",at:"left+30px top+300px ",of: window} , //弹出窗口 位置
			    autoOpen:true,  
			    url:'${ctx}/view/list',//editRegion regionTreePage',
			});
		},
		//拾取模型
		selectModel:function(){
			//调用三维地图拾取事件
			map_3d.setMouseClickSelect();
		},
		//隐藏拾取模型
		hideModel:function(){
			map_3d.hideModel();
		},
		//普通视角/垂直视角切换
		regionOrSttsChange:function(){
			var selectRegions = $('#viewRegion').combotree("tree").tree("getSelectedNodes");
			console.log(selectRegions);
			if(selectRegions.length>0){
				$('#parentArreaId').val(selectRegions[0].pid);
			}
			var stts = $('#viewStts').combobox('getValue');
			var areaId = $("#viewRegion").combotree('getValue');
			var parentAreaId = $('#parentArreaId').val();
			if(stts!=null && areaId!=null && areaId!=""){	//垂直视角隐藏当前区域（楼层）以上的模型
				//根据楼层区域编号隐藏/显示模型,
				map.showHideByAreaId(parentAreaId,areaId,stts);
			}
		},
		//重置
		reset:function(){
			$('#id').val('');
			$('#cusNumber').val('');
			$('#viewName').val('');//.textbox('setValue','');
			//视角权限
			$('#viewConfine').combobox('setValue','');
			$('#viewConfine').combobox('setText','');
			//视角类型
			$('#viewType').combobox('setValue','');
			$('#viewType').combobox('setText','');
			//隐藏状态
			$('#viewStts').combobox('setValue','0');
			$('#viewStts').combobox('setText','普通');
			//三维模型显示
			view3d.regionOrSttsChange();
			//关联区域
			$('#viewRegion').combotree('setValue','');
			$('#viewRegion').combotree('setText','');
			//默认视角
			$('#defIndc').val('0');
			$('#view_x').textbox('setValue','');
			$('#view_y').textbox('setValue','');
			$('#view_z').textbox('setValue','');
			$('#view_heading').textbox('setValue','');
			$('#view_tilt').textbox('setValue','');
		},
		//表单验证
		formvalid:function(){
			var viewName = $('#viewName').textbox("getValue");
			var viewRegion = $('#viewRegion').combotree('getValue');
			var viewStts = $('#viewStts').combobox('getValue');
			var viewType = $('#viewType').combobox('getValue');
			var viewConfine = $('#viewConfine').combobox('getValue');
			if(viewName==null || viewName==''){
				$.alert({
					message:'请填写视角名称',
					title:"提示信息",
					iframePanel:true
				});
				return false;
			}
			if(viewType==null || viewType==''){
				$.alert({
					message:'请选视角类型',
					title:"提示信息",
					iframePanel:true
				});
				return false;
			}
			if(viewRegion==null || viewRegion==''){
				$.alert({
					message:'请选择关联区域',
					title:"提示信息",
					iframePanel:true
				});
				return false;
			}
			if(viewConfine==null || viewConfine==''){
				$.alert({
					message:'请选视角属性',
					title:"提示信息",
					iframePanel:true
				});
				return false;
			}
			if(viewStts==null || viewStts==''){
				$.alert({
					message:'请选垂直属性',
					title:"提示信息",
					iframePanel:true
				});
				return false;
			}
			return true;
		}
	}
	view3d.init();
});



</script>
</body>
</html>
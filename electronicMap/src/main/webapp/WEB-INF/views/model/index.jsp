<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<body>
	<cui:dialog id="modelDialog" width="60%" height="60%" title="" iframePanel="true" autoOpen="false">
	</cui:dialog>
	<div class="rightDivStyle right-zb">
		<h4>当前模型</h4>
		<a href="javascript:model3d.showModelList();" class="modelUrl">模型管理</a>
		<div class="model_div first">
			<div class="model-content">
				<label>模型编号：</label>
				<cui:input id="modelNo" name="ModelNo" value="" style="width:100px;" readonly="true"/>
			</div>
			<div class="model-content">
				<label>隐藏状态：</label>
				<cui:combobox id="minModelState" name="minModelState" data="model3d.propertyData"  readonly="true"/>
			</div>
			<div>
				<cui:button label="显 示 模 型" onClick="model3d.showModel" width="100" ></cui:button>
				<cui:button label="隐 藏 模 型" onClick="model3d.hideModel" width="100" ></cui:button>
			</div>
		</div>
	</div>
	<div class="rightDivStyle right-zb">
		<h4 class="formTitle">编辑模型</h4>
		<div class="model_div">
			<cui:form id="modelForm" method="post" action="">
				<input type="hidden" id="id" name="id" value="" />
				<input type="hidden" id="minCusNumber" name="minCusNumber" value="" />
				<div class="model_form">
					<div class="model-input">
						<label>模型编号：</label>
						<cui:input id="minModelNo" name="minModelNo" value="" style="width:100px;" readonly="true"/>
					</div>
					<div class="model-input">
						<label>模型集名称：</label>
						<cui:input id="minModelFcname" name="minModelFcname" value="" style="width:100px;"  readonly="true"/>
					</div>
					<div class="model-input">
						<label>模型数据源：</label>
						<cui:input id="minModelFdsname" name="minModelFdsname" value="" style="width:100px;" readonly="true"/>
					</div>
					<div class="model-input">
						<label>模型名称：</label>
						<cui:input id="minModelName" name="minModelName" value="" style="width:100px;" />
					</div>
					<div class="model-input">
						<label>关联区域：</label>
						<cui:combotree id="minAreaId" name="minAreaId">
						</cui:combotree>
					</div>
				 	<div class="model-chk"> 
						<cui:checkbox id="minIsTop" name="minIsTop" value="1" label="是否为屋顶"></cui:checkbox>
						<cui:checkbox id="autoHide" name="" value="0" label="保存后隐藏"></cui:checkbox>
					 </div>
					<cui:button label="保  存" onClick="model3d.save" width="55" ></cui:button>
					<cui:button label="重  置" onClick="model3d.reset" width="55"></cui:button>
				</div>
			</cui:form>
		</div>
	</div>

<script type="text/javascript">
var model3d;
$(function(){
	model3d = {
		cusNumber:jsConst.CUS_NUMBER,
		url:null,
		mapName:null,
		propertyData: [
		    {"text":"显示","value":"1","selected":"selected"},
		    {"text":"隐藏","value":"0"}
		],
		//保存模型
		save:function (){
			if($("#modelForm").form("valid")){
				$('#minCusNumber').val(model3d.cusNumber);
				var modelNo = $('#minModelNo').textbox('getValue');
				var formData = $("#modelForm").form("formData");
				if(modelNo == ""){
					$.message({
						message:"请选择一个模型！",
						iframePanel:true
					});
					return;
				}
				var minModelName = $('#minModelName').textbox('getValue');
				if(minModelName == ''){
					$.message({
						message:"请输入模型名称！",
						iframePanel:true
					});
					return;
				}
				var minAreaId = $('#minAreaId').combotree('getValue');
				if(minAreaId == ''){
					$.message({
						message:"请选择关联区域！",
						iframePanel:true
					});
					return;
				}

                $.loading({text:"正在处理中，请稍后..."});
				$.ajax({
					type : 'post',
					url : '${ctx}/model/saveModel',
					data : formData,
					dataType : 'json',
					success : function(data) {
                        $.loading("hide");
						$.message({
							message:"操作成功",
							iframePanel:true
						});
						$('#id').val(data.id);
						if($('#autoHide').is(':checked')){
							model3d.hideModel();
						}
						if(modelList!=null){
							//$('#${document_id}_grid').grid({url:"${ctx}/point/findByPage?alpCusNumber="+pointInfo.cusNumber});
							//modelList.$grid.grid("reload","${ctx}/model/findByPage?obj={'minCusNumber':'"+model3d.cusNumber+"'}");
							//modelList.$grid.grid({url:"${ctx}/model/findByPage?obj={'minCusNumber':'"+model3d.cusNumber+"'}"});
							var obj={'minCusNumber':model3d.cusNumber};
							modelList.$grid.grid("option","postData",obj);
							modelList.$grid.grid("reload","${ctx}/model/findByPage");
						}
					},error : function(XMLHttpRequest, textStatus, errorThrown) {
                        $.loading("hide");
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
			$('#id').val('');
			$('#minCusNumber').val('');
			$('#minModelName').textbox('setValue','');
			$('#minAreaId').combotree('setValue','');
			$('#minAreaId').combotree('setText','');
		    $('#minModelFcname').textbox('setValue','');
		    $('#minModelFdsname').textbox('setValue','');
		},
		//显示模型窗口
		showModelList:function(){
			$("#modelDialog").dialog({
			    width:1000,                  //属性
			    height:520,                 //属性
			    subTitle :'模型列表', 
			    modal:false,                 //弹出窗口底层可操作
			    position: { my: "left",at:"left+30px top+300px ",of: window} , //弹出窗口 位置
			    autoOpen:true,  
			    url:'${ctx}/model/list',//editRegion regionTreePage',
			});
		},
		//显示模型
		showModel:function(){
			map_3d.showModel(map_3d.selectFeatureIds);
			$('#minModelState').combobox('setValue','1')
		},
		//隐藏拾取模型
		hideModel:function(){
			map_3d.hideModel(map_3d.selectFeatureIds);
			$('#minModelState').combobox('setValue','0')
		},
		//初始化
		init:function(){
			model3d.setMouseClickSelectForOne();
			$('#minAreaId').combotree({url:"${ctx}/common/areadevice/findForCombotree.json?cusNumber="+model3d.cusNumber+"&deviceType=0"});
		},
		//定义事件
		setMouseClickSelectForOne:function (){
			map_3d.cameraInfo = map_3d.__g.camera.getCamera();
			//设置当前模式为拾取模式
			map_3d.__g.interactMode = gviInteractMode.gviInteractSelect;
			//设置当前拾取模式为场景模式
			map_3d.__g.mouseSelectObjectMask = gviMouseSelectObjectMask.gviSelectFeatureLayer;
		    //设置鼠标触发方式为点选
			map_3d.__g.mouseSelectMode = gviMouseSelectMode.gviMouseSelectClick;
		    //绑定鼠标点击事件
            if (typeof (model3d.fnMouseClickSelectForOne) == "function") ____events["onMouseClickSelect"] = model3d.fnMouseClickSelectForOne;
		},
		//鼠标点击事件，更新模型编号
        fnMouseClickSelectForOne:function (pickResult, intersectPoint, mask, eventSender) {
		    //cep拾取
		    var featureId = pickResult.featureId;
		    var fl = pickResult.featureLayer;
		    var fc = map_3d.fnGetFcByLayer(fl);
		    var fcname = fl.featureClassInfo.featureClassName;
		    var fdsname = fl.featureClassInfo.dataSetName;
			//模型集名称
		    $('#minModelFcname').textbox('setValue',fcname);
			//模型数据源
		    $('#minModelFdsname').textbox('setValue',fdsname);
	    	map_3d.selectFeatureIds = [];
		    map_3d.selectFeatureIds.push(featureId);
			//取消当前所有高亮
	        map_3d.__g.featureManager.unhighlightAll();
		    //高亮
		    map_3d.__g.featureManager.highlightFeature(fc, featureId, 0xffffff00);
		    //隐藏
		    //__g.featureManager.setFeatureVisibleMask(fc,featureId,gviViewportMask.gviViewNone);
		    //给全局变量重新赋值
		    /*map_3d.selectFeatureIds = [];
		    map_3d.selectFeatureIds.push(featureId);*/
		    map_3d.featureLayer = pickResult.featureLayer;
		    map_3d.mousePosition = intersectPoint;
	    	$('#modelNo').textbox('setValue',featureId);
			$('#minModelState').combobox('setValue','1');
			//重置不重置区域
			$('#id').val('');
			$('#minCusNumber').val('');
			$('#minModelName').val('');
			$('#minModelNo').textbox('setValue',featureId);
		    $.ajax({
				type : 'post',
				url :"${ctx}/model/findByModel",
				data : {"minModelNo":featureId,'minModelFcname':fcname,'minModelFdsname':fdsname,'minCusNumber':model3d.cusNumber},
				dataType : 'json',
				success : function(data) {
					$('#id').val(data.ID);
					$('#minCusNumber').val(data.MIN_CUS_NUMBER);
					$('#minModelName').textbox('setValue',data.MIN_MODEL_NAME);
					$('#minAreaId').combotree('setValue',data.ABD_AREA_ID);
					$('#minAreaId').combotree('setText',data.ABD_AREA_NAME);
					$('#minModelNo').textbox('setValue',data.MIN_MODEL_NO);
					$('#modelNo').textbox('setValue',data.MIN_MODEL_NO);
					if(data.MIN_IS_TOP != null && data.MIN_IS_TOP == "1"){
						$('#minIsTop').checkbox('check');
					}else{
						$('#minIsTop').checkbox('uncheck');
					}
				}
			});
		},
	}
	model3d.init();
});



</script>
</body>
</html>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<div class="PanelCon">
	<div class="PanelList">
		<div class="clearfix panelTop" id="userToolBarArea">
			<!-- 操作按钮开始 -->
			 <div>
			 	<cui:toolbar id="modelToolbar" data="modelList.modelToolBarData" onClick="modelList.toolbarOnClick"></cui:toolbar>
			</div>
			<!-- 操作按钮结束 -->
			<!-- 列表展示开始 -->
		</div>
		 <cui:layout style="height:440px;">
	        <cui:layoutRegion region="west" style="width:150px;">
				<li style="margin:5px auto;">
					<label>模型名称：</label>
					<cui:input id="searchModelName" width="130"/>
				</li>
	            <li style="margin:5px auto; ">
					<label>区域名称：</label>
					<div style="height:350px;overflow:auto;background-color:rgba(189, 208, 230, 1);">
					<cui:tree id="searchRegion" asyncEnable="true" keepParent="true"  rootNode="true" showRootNode="true"
					asyncType="post" asyncAutoParam="id,name" onClick="modelList.clickEvent">
					</cui:tree>	
					</div>
				</li>
			</cui:layoutRegion>
	        <cui:layoutRegion region="center">
				<cui:grid id="gridModel" rownumbers="false" multiselect="true"
					sortname="MIN_UPDT_TIME" sortorder="desc" url="${ctx}/view/findByPage?{'minCusNumber':'','minModelName':''}"
					fitStyle="fill" onDblClickRow="onDblClickRow" width="auto" height="400"
					 rowNum="10">
					<cui:gridCols>
						<cui:gridCol name="ID" hidden="true" align="center">id</cui:gridCol>
						<cui:gridCol name="ABD_AREA_NAME" width="40" align="center">区域名称</cui:gridCol>
						<cui:gridCol name="MIN_MODEL_NAME" width="40" align="center">模型名称</cui:gridCol>
						<cui:gridCol name="MIN_MODEL_NO" width="40" align="center">模型编号</cui:gridCol>
						<cui:gridCol name="MIN_MODEL_FCNAME" width="40" align="center">模型集名称</cui:gridCol>
						<cui:gridCol name="MIN_MODEL_FDSNAME" width="40" align="center">模型数据源</cui:gridCol>
						<cui:gridCol name="ABD_AREA_ID"  hidden="true" >区域ID</cui:gridCol>
						<cui:gridCol name="MIN_IS_TOP" hidden="true" align="center">IS_TOP</cui:gridCol>
					</cui:gridCols>
					<cui:gridPager gridId="gridModel" />
				</cui:grid>
				<!-- 列表展示结束 -->
       		</cui:layoutRegion>
        </cui:layout>
	<cui:dialog id="searchDialog" reLoadOnOpen="true" modal="true"
		autoOpen="false" width="500" height="300" maximizable="false">
	</cui:dialog>
</div>
</div>
<cui:dialog id="adddialog" reLoadOnOpen="true" modal="true"
	autoOpen="false" width="1000" height="500" maximizable="false">
</cui:dialog>
<cui:dialog id="resdialog" reLoadOnOpen="true" modal="true"
	autoOpen="false" width="80%" height="80%" maximizable="false">
</cui:dialog>
<script>
var modelList;
$(function(){
	modelList = {
		cusNumber:jsConst.CUS_NUMBER,
		$grid : $("#gridModel"),
		modelToolBarData: [
			{
				"id"		: "search",
				"label"		: "查询",
				"disabled"	: "false",
				"type"		: "button",
				"cls":"cyanbtn",
				"icon":"icon-pencil7"
			} ,
			{
				"id"		: "update",
				"label"		: "编辑",
				"disabled"	: "disable",
				"type"		: "button",
				"cls":"greenbtn",
				"icon":"icon-plus3"	
			},
			{
				"id"		: "delete",
				"label"		: "删除",
				"disabled"	: "false",
				"type"		: "button",
				"cls":"deleteBtn",
				"icon":"icon-switch"	
					
			}
		],
		//工具栏按钮点击事件调用
		toolbarOnClick:function(e,ui){
			var id = ui.id;
			if(id=='search'){
				modelList.search();
			}else if(id=='update'){
				modelList.update();
			}else if(id=='delete'){
				modelList.delModel();
			}
		},
		//查询
		search :function(){
			var obj={'minCusNumber':modelList.cusNumber,'minModelName':encodeURI($('#searchModelName').textbox('getValue'))};
			modelList.$grid.grid("option","postData",obj);
			modelList.$grid.grid("reload","${ctx}/model/findByPage");
			//vao obj={'minCusNumber':modelList.cusNumber,'minModelName':encodeURI(encodeURI($('#searchModelName').textbox('getValue')))};
			//modelList.$grid.grid("option","obj",obj);
			//modelList.$grid.grid("reload","${ctx}/model/findByPage");
			
			//modelList.$grid.grid("reload","${ctx}/model/findByPage?obj={'minCusNumber':'"+modelList.cusNumber+"','minModelName':'"+encodeURI(encodeURI($('#searchModelName').textbox('getValue')))+"'}");
			/* 
			 $("#searchDialog").dialog({
				id:'searchDialogDiv',
			    width:300,     //属性
			    height:200,                 //属性
			    subTitle :'查询', 
			    modal:false,                 //弹出窗口底层可操作
			    position: { my: "left",at:"left+400px top+300px ",of: window} , //弹出窗口 位置
			    autoOpen:true,  
			    url:'${ctx}/model/searchPage',
			}); */
		},
		//修改
		update :function(){
			var sel = modelList.$grid.grid("option","selarrrow");
			if(sel.length<1){
				$.message({
					message : "请先勾选需要编辑的记录！",
					cls : "warning"
				});
				return false;	
			}
			if(sel.length>1){
				$.message({
					message : "请只勾选一条需要编辑的记录！",
					cls : "warning"
				});
				return false;
			}
			var rowData = modelList.$grid.grid("getRowData", sel[0]);
			$('#id').val(rowData.ID);
			$('#minCusNumber').val(rowData.MIN_CUS_NUMBER);
			$('#minModelName').textbox('setValue',rowData.MIN_MODEL_NAME);
			$('#minAreaId').combotree('setValue',rowData.ABD_AREA_ID);
			$('#minAreaId').combotree('setText',rowData.ABD_AREA_NAME);
			$('#minModelNo').textbox('setValue',rowData.MIN_MODEL_NO);
			$('#minModelFcname').textbox('setValue',rowData.MIN_MODEL_FCNAME);
			$('#minModelFdsname').textbox('setValue',rowData.MIN_MODEL_FDSNAME);
			$('#modelNo').textbox('setValue',rowData.MIN_MODEL_NO);
			if(rowData.MIN_IS_TOP != null && rowData.MIN_IS_TOP == "1"){
				$('#minIsTop').checkbox('check');
			}else{
				$('#minIsTop').checkbox('uncheck');
			}
			$("#modelDialog").dialog('close');
			
			//设置默认选中
	    	map_3d.selectFeatureIds = [];
		    map_3d.selectFeatureIds.push(rowData.MIN_MODEL_NO);

			//定位到选中设备关联区域管理员视角
			
		},
		//删除
		delModel :function(){
			var ids = [];
		    var sel = modelList.$grid.grid("option","selarrrow");
			if(sel.length<1){
				$.message({
					message : "请先勾选需要删除的记录！",
					cls : "warning"
				});
				return false;
			}
		    $.each(sel, function(i){
		        var rowData = modelList.$grid.grid("getRowData", sel[i]);
		        ids.push(rowData.ID);
		    });
			$.confirm({
				message:"确认删除？",
				iconCls:"icon-question",
				title:"提示信息",
				iframePanel:true,
				callback: function(sure) {
					if (sure == true) {
					    $.ajax({
							type : 'post',
							url :"${ctx}/model/deleteModel",
							data : {"obj":JSON.stringify(ids)},
							dataType : 'json',
							success : function(data) {
								console.log(data);
								$.message({
									message : "操作成功",
									cls : "warning"
								});
								modelList.$grid.grid("reload");
							},error : function(XMLHttpRequest, textStatus, errorThrown) {
								$.alert({
									message : textStatus,
									title:"提示信息",
									cls : "warning"
								});
							}
						});
					}
				}
			});
		},
		//点击区域树事件
		clickEvent:function(event, id, node){
//			var obj={'minCusNumber':modelList.cusNumber,'minAreaId':node.id,'minModelName':encodeURI($('#searchModelName').textbox('getValue'))};
			var obj={'minCusNumber':modelList.cusNumber,'minAreaId':node.id,'minModelName':encodeURI($('#searchModelName').textbox('getValue'))};
			$("#gridModel").grid("option","postData",obj);
			$("#gridModel").grid("reload","${ctx}/model/findByPage");
		},
		//初始化
		init:function(){
			$("#searchRegion").tree({asyncUrl:"${ctx}/common/areadevice/findForCombotree.json?cusNumber="+modelList.cusNumber+"&deviceType=0"});
			$.parseDone(function(){
				//$("#gridModel").grid({url:"${ctx}/model/findByPage?{'minCusNumber':'"+modelList.cusNumber+"','minModelName':''}"});
				var obj={'minCusNumber':modelList.cusNumber};
				$("#gridModel").grid("option","postData",obj);
				$("#gridModel").grid("reload","${ctx}/model/findByPage");
			})
		}
	}
	modelList.init();
});

</script>

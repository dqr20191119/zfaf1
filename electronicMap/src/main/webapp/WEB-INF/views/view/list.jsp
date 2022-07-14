<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<div class="PanelCon" style="height:100%;">
	<div class="PanelList">
		<div class="clearfix panelTop" id="userToolBarArea">
			<!-- 操作按钮开始 -->
			 <div>
			 	<cui:toolbar id="viewToolbar" data="viewList.viewToolBarData" onClick="viewList.toolbarOnClick"></cui:toolbar>
			</div>
			<!-- 操作按钮结束 -->
			<!-- 列表展示开始 -->
		</div>
		<cui:grid id="gridView" rownumbers="false" multiselect="true"
			url="${ctx}/view/findByPage?obj={'cusNumber':''}"
			fitStyle="fill" onDblClickRow="onDblClickRow" width="auto" height="400" rowNum="10">
			<cui:gridCols>
				<cui:gridCol name="ID" hidden="true" align="center">id</cui:gridCol>
				<cui:gridCol name="ABD_AREA_NAME" width="40" align="center">区域名称</cui:gridCol>
				<cui:gridCol name="VFU_VIEW_NAME" width="40" align="center">视角名称</cui:gridCol>
				<cui:gridCol name="VFU_VIEW_TYPE" width="40" formatter="convertCode" align="center"
					formatoptions="{'data':view3d.viewTypeData}">视角类型</cui:gridCol>
				<cui:gridCol name="VFU_CONFINE" width="40" formatter="convertCode" align="center"
					formatoptions="{'data':view3d.confineData}">视角权限</cui:gridCol>
				<cui:gridCol name="VFU_STTS" width="40" formatter="convertCode" align="center"
					formatoptions="{'data':view3d.sttsData}">视角属性</cui:gridCol>
				<cui:gridCol name="VFU_X_CRDNT" width="40" align="center">X坐标</cui:gridCol>
				<cui:gridCol name="VFU_Y_CRDNT" width="40" align="center">Y坐标</cui:gridCol>
				<cui:gridCol name="VFU_Z_CRDNT" width="40" align="center">Z坐标</cui:gridCol>
				<cui:gridCol name="VFU_TILT_CRDNT" width="40" align="center">俯仰角</cui:gridCol>
				<cui:gridCol name="VFU_HEADING_CRDNT" width="40" align="center">旋转角</cui:gridCol>
				<cui:gridCol name="VFU_DEF_INDC" hidden="true">默认视角</cui:gridCol>
				<cui:gridCol name="VFU_CUS_NUMBER" hidden="true">监狱编号</cui:gridCol>
				<cui:gridCol name="VFU_PARENT_VIEW_ID" hidden="true">父级视角编号</cui:gridCol>
				<cui:gridCol name="VFU_AREA_ID" hidden="true">区域编号</cui:gridCol>
				<cui:gridCol name="ABD_PARENT_AREA_ID" hidden="true">父级视角关联区域编号</cui:gridCol>
				<%-- <cui:gridCol name="VFU_CONFINE" >视角权限</cui:gridCol> --%>
			</cui:gridCols>
			<cui:gridPager gridId="gridView" />
		</cui:grid>
	<!-- 列表展示结束 -->
</div>
</div>
<cui:dialog id="searchDialog" reLoadOnOpen="true" modal="true"
	autoOpen="false" width="500" height="300" maximizable="false">
</cui:dialog>
<%-- <cui:dialog id="resdialog" reLoadOnOpen="true" modal="true"
	autoOpen="false" width="80%" height="80%" maximizable="false">
</cui:dialog> --%>
<script>
var viewList;
$(function(){
	viewList = {
		viewListUrl:"${ctx}/view/findByPage?obj={'cusNumber':'"+jsConst.CUS_NUMBER+"'}",
		$grid: $("#gridView"),
		$search:$("#searchDialog"),
		cusNumber:jsConst.CUS_NUMBER,
		viewToolBarData: [
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
					
			}/* ,
			{
				"id"		: "export",
				"label"		: "导出",
				"disabled"	: "false",
				"type"		: "button",
				"cls":"greenlight",
				"icon":"icon-pencil7"	
			} */
		],
		init:function(){
			$.parseDone(function(){
				//初始化区域下拉树
				//$("#gridView").grid("reload",jsConst.basePath+"view/findByPage?obj={'cusNumber':'"+jsConst.CUS_NUMBER+"'}");
				var obj={'cusNumber':jsConst.CUS_NUMBER};
				$("#gridView").grid("option","postData",obj);
				$("#gridView").grid("reload",jsConst.basePath+"view/findByPage");
			})
		},
		//工具栏按钮点击事件调用
		toolbarOnClick:function(e,ui){
			var id = ui.id;
			if(id=='search'){
				viewList.search();
			}else if(id=='update'){
				viewList.update();
			}else if(id=='delete'){
				viewList.delView();
			}else if(id=='export'){
				viewList.search();
			}
		},
		//查询
		search :function(){
			 var map = {'cusNumber':jsConst.CUS_NUMBER};
			 viewList.$search.dialog({
				    width:300,                  //属性
				    height:220,                 //属性
				    subTitle :'视角信息列表', 
				    modal:false,                 //弹出窗口底层可操作
				    position: { my: "left",at:"left+400px top+300px ",of: window} , //弹出窗口 位置
				    autoOpen:true,  
				    url:'${ctx}/view/searchView',
				});
		},
		//修改
		update :function(){
			var sel = viewList.$grid.grid("option","selarrrow");
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
			if (sel.length==1) {
				//关闭弹出窗口
				$("#viewDialog").dialog('close');
				var rowData = viewList.$grid.grid("getRowData", sel[0]);
				$('#id').val(rowData.ID);
				$('#cusNumber').val(rowData.VFU_CUS_NUMBER);
				$('#viewName').textbox('setValue',rowData.VFU_VIEW_NAME);
				//视角类型
				var viewTypeVal = viewList.unFormatViewType(rowData.VFU_VIEW_TYPE);
				$('#viewType').combobox('setValue',viewTypeVal);
				$('#viewType').combobox('setText',rowData.VFU_VIEW_TYPE);
				//管理员视角
				$('#viewConfine').combobox('setValue',rowData.VFU_CONFINE=="管理视角"?"1":"0");
				$('#viewConfine').combobox('setText',rowData.VFU_CONFINE);
				//关联区域
				$('#viewRegion').combotree('setValue',rowData.VFU_AREA_ID);
				$('#viewRegion').combotree('setText',rowData.ABD_AREA_NAME);
				//视角属性
				$('#viewStts').combobox('setValue',rowData.VFU_STTS=="垂直"?'1':'0');
				$('#viewStts').combobox('setText',rowData.VFU_STTS);
				//默认视角
				$('#defIndc').val(rowData.VFU_DEF_INDC);
				if(rowData.VFU_DEF_INDC==1){
					$('#defIndc').checkbox('check');
				}
				//父级区域
				$('#parentArreaId').val(rowData.ABD_PARENT_AREA_ID);
				$('#id').val(rowData.ID);
				$('#cusNumber').val(rowData.VFU_CUS_NUMBER);
				//视角定位
				var position = {};	//坐标
				var angle = {};	//角度
				position.x = rowData.VFU_X_CRDNT;
				position.y = rowData.VFU_Y_CRDNT;
				position.z = rowData.VFU_Z_CRDNT;
				angle.heading = rowData.VFU_HEADING_CRDNT;
				angle.tilt = rowData.VFU_TILT_CRDNT;
				map_3d.setCameraInfo(position,angle);
				//隐藏/显示
				view3d.regionOrSttsChange();
			}
		},
		//删除
		delView :function(){
			var ids = [];
		    var sel = viewList.$grid.grid("option","selarrrow");
			if(sel.length<1){
				$.message({
					message : "请先勾选需要删除的记录！",
					cls : "warning"
				});
				return false;
			}
		    $.each(sel, function(i){
		        var rowData = viewList.$grid.grid("getRowData", sel[i])
		        ids.push(rowData.ID);
		    }); 
		    $.confirm( {
				message:"确认是否删除？",
				iconCls:"icon-question",
				title:"提示信息",
				iframePanel:true,
				callback: function(sure) {
					if (sure == true) {
					    $.ajax({
							type : 'post',
							url :"${ctx}/view/deleteView",
							data : {"ids":JSON.stringify(ids)},
							dataType : 'json',
							success : function(data) {
								console.log(data);
								$.message({
									message : "操作成功",
									cls : "warning"
								});
								viewList.$grid.grid("reload");
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
		//获取
		unFormatViewType:function(viewTypeText){
			console.log(viewTypeText);
			var viewTypeVal = '';
			if(viewTypeText!=null){
				/* if(viewTypeText=='公共视角'){
					viewTypeVal = 0;
				}else */ 
				if(viewTypeText=='楼房视角'){
					viewTypeVal = '1';
				}else if(viewTypeText=='楼层视角'){
					viewTypeVal = '2';
				}else if(viewTypeText=='房间视角'){
					viewTypeVal = '3';
				}
			}
			return viewTypeVal;
		}
	}
	viewList.init();
});

</script>

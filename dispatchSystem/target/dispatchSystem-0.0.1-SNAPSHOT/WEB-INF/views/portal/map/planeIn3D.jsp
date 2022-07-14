<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<link href="${ctx}/static/module/sign/css/signStyle.css" rel="stylesheet"> 

<style>	
.imagebox{
	top:10px;
	margin:0 auto;
	position:relative;
	}
.imagebox img{
	width:100%;
	height:100%;
	}

</style>

<cui:dialog id="dialog_planeIn3D" autoOpen="false" iframePanel="true" title="二维地图"
	 reLoadOnOpen="true" modal="true" resizable="true" width="1200" height="600">
	<div id="layerImgDivShow" style=" width:100%; height:100%;">
	 	<div class="imagebox" id="signx_show">
			<img id="planeLayer" >
		</div>
	</div>
</cui:dialog>
<script type="text/javascript" src="${ctx}/static/module/sign/jquery-sign-show.js"></script>
<script type="text/javascript" src="${ctx}/static/module/sign/jquery.rotate.min.js"></script>
<script type="text/javascript" src="${ctx}/static/module/sign/jquery-mouseSelect.js"></script>

<script type="text/javascript">
	$.parseDone(function(){
		$.sign_show.bindSign('#layerImgDivShow #signx_show');//初始化
	});
	
	//双击区域展示关联图层
	function planeIn3DViewPosition(id){

		//隐藏#signx_show显示
		$("#layerImgDivShow #signx_show").hide();
		//初始化图片显示
		$("#layerImgDivShow #planeLayer").attr("src","");
		
		//删除所有点位标记
		$("#layerImgDivShow [id*=Ts]").remove();
		
		$.ajax({
			type : 'post',
			url : '${ctx}/xtgl/planeLayer/findByPliAreaId.json',
			data : {'pliAreaId':id},
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
										$("#layerImgDivShow #planeLayer").attr("src","${ctx}/common/affix/download?id="+layerImgId);
					
										$("#layerImgDivShow .imagebox").width(pliWidth);
										$("#layerImgDivShow .imagebox").height(pliHeight);
										//显示
										$("#layerImgDivShow #signx_show").show();
										
										//打开二维地图
										$("#dialog_planeIn3D").dialog("open");
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
														$.sign_show.loadingSign(data.obj);//载入标记数据
																
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
			                		$('#dialog').dialog("destroy");
									$('#dialog').dialog({
										iframePanel : true,
										width : 900,
										height : 600,
										title : "二维图层维护",
										url : '${ctx}/xtgl/planeLayer/index'
									});
									$("#dialog").dialog("open");
									return;
								}
								if (sure == false) {
									console.log("cancel")
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
	
</script>
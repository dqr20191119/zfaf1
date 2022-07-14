<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<link href="${ctx}/static/module/video/css/video-plugin-layout.css" rel="stylesheet">
<link href="${ctx}/static/module/sign/css/signStyle.css" rel="stylesheet"> 

<style>
.form-control {
	width: 100%;
}
#ewdw-layout .coral-panel .coral-tree li a{
	color:#333;
}	
</style>
<div class="map">
	<div id="layerImgDivShow" style="overflow-y:auto; overflow-x:auto; width:100%; height:100%;">
		<h2 class="title-position"><span class="title-logo"></span><span class="title-content">当前位置:<span id="currentPosition"></span></span></h2>
		<div class="imageboxall_abs" id="signx_show" >
			<img id="planeLayer">
		</div>
	</div>
</div>
<div class="map-footer">
	<div class="l-span">
		<cui:input id="mapAreaId" type="hidden" />
		<span><a href="javascript:logoutX();"><i class="iconfont icon-tuichu"></i>退出</a></span>
		<span><a href="javascript:void(0)" id="viewMessageId"><i class="iconfont icon-daibanshixiang"></i>待办</a></span>
		<span id="plane_glob_dep"></span>
		<span>位置：<a href="javascript:void(0)" id="plane_locationName"></a></span>
		<!-- <span>监控点：<a href="javascript:void(0)" id="plane_monitorPoint" class="color-red" onclick="common.areaCountCameraList()"></a></span> -->
		<!-- <span>监控点：<a href="javascript:void(0)" id="plane_monitorPoint" class="color-red" onclick="common.areaCountCameraReadList()"></a></span> -->
		<!-- <span>报警点：<a href="javascript:void(0)" id="plane_alarmPoint" class="color-red" onclick="common.areaCountAlertorList()"></a></span> -->
		<!-- <span>报警点：<a href="javascript:void(0)" id="plane_alarmPoint" class="color-red" onclick="common.areaCountAlertorReadList()"></a></span> -->
		<span style="display:none">正在截图...<a href="javascript:void(0)" id="plane_alarmPoint"><i class="iconfont icon-jietu"></i></a></span>
		<span id="message" style="display: none"></span>
	</div>	
	<div class="r-but">
		<%@ include file="toolbar.jsp"%>
	</div>
</div>

<script src="${ctx}/static/module/video/js/video-plugin-layout.js"></script>
<script type="text/javascript" src="${ctx}/static/module/sign/jquery-sign-show.js"></script>
<script type="text/javascript" src="${ctx}/static/module/sign/jquery.rotate.min.js"></script>
<script type="text/javascript" src="${ctx}/static/module/sign/jquery-mouseSelect.js"></script>
<script src="${ctx}/static/js/scripts/common.js"></script>
			
<script type="text/javascript">

	$.parseDone(function(){
		initToolbar();
		$.sign_show.bindSign('#layerImgDivShow #signx_show');//初始化
	});

	//初始化工具条
	function initToolbar(){
		//$("#layerImgDivShow #signx_show").hide();
		
		$("#plane_glob_dep").html(jsConst.CUS_NAME +"：<a href='javascript:void(0);'>" + jsConst.REAL_NAME+"</a>");
		if(jsConst.USER_LEVEL==1 && jsConst.ROOT_ORGA_CODE!=null && jsConst.ROOT_ORGA_CODE!=''){//省局登录
			$('#plane_locationName').text(jsConst.ROOT_ORGA_NAME);
		}else if(jsConst.USER_LEVEL==2  && jsConst.CUS_NUMBER!=null && jsConst.CUS_NUMBER!=''){//监狱登录
			$('#plane_locationName').text(jsConst.CUS_NAME).unbind('click').bind('click', function() {
				planeViewPosition(jsConst.CUS_NUMBER, jsConst.CUS_NAME);
			});
			/*var currentPosition = $('<a href="javascript:void(0);"></a>').clone().html(name).unbind('click').bind('click', function() {
				planeViewPosition(id, name);
			});*/
            showViewMenu();
            planeViewPosition(jsConst.CUS_NUMBER, jsConst.CUS_NAME);
		}else if(jsConst.USER_LEVEL==3){//监区登录
		   $.ajax({
                type : 'post',
                url : jsConst.basePath+'view/findRegionView_2d.json',
                data : {'cusNumber':jsConst.CUS_NUMBER},
                dataType : 'json',
                success : function(data) {
                    for(var i=0;i<data.length;i++) {
                        //父节点
                        if(data[i].isParent){
                            for(var z = 0; z<data.length; z++){
                                if(!data[z].isParent && data[z].pId == data[i].id){
                                    planeViewPosition(data[z].id, data[z].name);
                                    break;
                                }
                            }
                        }
                    }

                }
            });
           /* $('#plane_locationName').text(jsConst.CUS_NAME).unbind('click').bind('click', function() {
                planeViewPosition(jsConst.CUS_NUMBER, jsConst.CUS_NAME);
            });*/
            showViewMenu();
        }

	}
	
	
	//监狱登录初始化左上角视角定位下拉菜单
    	function showViewMenu_bak(){
            $('#viewPositionMenu').html('');//清空视角定位信息
            var cusNumber = jsConst.USER_LEVEL=='1'?jsConst.ROOT_ORGA_CODE:jsConst.CUS_NUMBER;
            var parentAreaId = '';
             $.ajax({
                type : 'post',
                url : jsConst.basePath+'view/findRegionView.json',
                data : {'cusNumber':cusNumber,'parentAreaId':parentAreaId,'confine':0},
                dataType : 'json',
                success : function(data) {
                    var menuHtml = '';
                    for(var i=0;i<data.length;i++){
                        //父节点
                        var li = '<li><a href="javascript:void(0);" onclick="planeViewPosition('
                            +data[i].VFU_AREA_ID+',\''+data[i].ABD_AREA_NAME+'\',\''+data[i].ABD_LEVEL_INDC+'\')">'+data[i].ABD_AREA_NAME;
                        if(data[i].childens && data[i].childens.length>0){
                            li = li + '<b class="leftArrow"></b>';
                        }
                        li = li + '</a>';
                        //子节点
                        var childenLi = '';
                        //子节点
                        if(data[i].childens && data[i].childens.length>0){
                            childens = data[i].childens;
                            childenLi = '<iframe class="sub-iframe"></iframe><ul>';
                            for(var j=0;j<childens.length;j++){
                                childenLi = childenLi + '<li><a href="javascript:void(0);" onclick="planeViewPosition('
                                +childens[j].VFU_AREA_ID+',\''+childens[j].ABD_AREA_NAME+'\',\''+childens[j].ABD_LEVEL_INDC+'\')">'+childens[j].ABD_AREA_NAME+'</a></li>';
                            }
                            childenLi = childenLi + '</ul>';
                            li = li + childenLi;
                        }
                        li = li + '</li>';
                        menuHtml = menuHtml + li;
                    }
                    $('#viewPositionMenu').html(menuHtml);

                    //初始化滚动条
                    $("#viewPositionMenu").mCustomScrollbar({
                        theme:"minimal-dark",
                        autoExpandScrollbar:true,
                        scrollInertia:0,
                        mouseWheelPixels:100//鼠标滚动一下滑动多少像素
                    });
                    //调用显示隐藏子节点
                    map.showOrHideMenu();
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

    //二维视角定位，取消和三维视角关联
    function showViewMenu(){
    	// debugger;
        $('#viewPositionMenu').html('');//清空视角定位信息
        var cusNumber = jsConst.USER_LEVEL=='1'?jsConst.ROOT_ORGA_CODE:jsConst.CUS_NUMBER;
        $.ajax({
            type : 'post',
            url : jsConst.basePath+'view/findRegionView_2d.json',
            data : {'cusNumber':cusNumber},
            dataType : 'json',
            success : function(data) {
            	// debugger;
                var menuHtml = '';
				var coun = 0;
				var parentCount = 0;
                // console.profile("XX");
				for(var x=0;x<data.length;x++) {
                    if(data[x].isParent){
                        parentCount++;
                    }
                }

                for(var i=0;i<data.length;i++) {
                    //父节点
                    if(data[i].isParent){
                        coun++;
                        var li = '<li><a id="sjdw_a_' + data[i].name + '" href="javascript:void(0);" onclick="planeViewPosition('
                            +data[i].id+',\''+data[i].name+'\')">'+data[i].name;

                        if(data[i].childrenNum != 0 && data[i].pId != null ){
                            li = li + '<b class="leftArrow"></b>';
                        }
                        li = li + '</a>';

                        //子节点
                        var childenLi = '';
                        childenLi = '<iframe class="sub-iframe"></iframe><ul>';
                        for(var z=0; z<data.length; z++){
                            if(!data[z].isParent && data[z].pId == data[i].id){
                                childenLi = childenLi + '<li><a href="javascript:void(0);" onclick="planeViewPosition('
                                    +data[z].id+',\''+data[z].name+'\')">'+data[z].name+'</a></li>';
                            }
                        }
                        childenLi = childenLi + '</ul>';
                        li = li + childenLi;
                        if(parentCount == coun && coun <= 8) {
                            li = li + '<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>';
                        }
                        li = li + '</li>';
                        menuHtml = menuHtml + li;
                    }
                }
                // alert("planMap.jsp 1= " + menuHtml);
                $('#viewPositionMenu').html(menuHtml);

                //初始化滚动条
                $("#viewPositionMenu").mCustomScrollbar({
                    theme:"minimal-dark",
                    autoExpandScrollbar:true,
                    scrollInertia:0,
                    mouseWheelPixels:100//鼠标滚动一下滑动多少像素
                });
                //调用显示隐藏子节点
                map.showOrHideMenu();
                
                // 临时代码，2019年1月14号演示所用：触发女子监狱点击事件，自动跳转到女子监狱
            	// document.getElementById("sjdw_a_女子监狱").click();
            },
            error : function(XMLHttpRequest, textStatus, errorThrown) {
                /*$.alert({
                    message:textStatus,
                    title:"提示信息",
                    iframePanel:true
                });*/
                console.log("视角定位加载失败....exception="+textStatus);
            }
        });
    }

	//双击区域展示关联图层
	function planeViewPosition(id,name,level,callbackFunction){
		//alert("1");
		if(!id){
			$.alert({
				message:"请选择视角定位",
				title:"提示信息",
				iframePanel:true
			});
			return;
		}
		//加载右侧民警分布信息
		//window.top.mjfbRightView(jsConst.ORG_CODE,id,name,level);

		// 为当前位置赋予点击事件
		var currentPosition = $('<a href="javascript:void(0);"></a>').clone().html(name).unbind('click').bind('click', function() {
			planeViewPosition(id, name);
		});
		$('#currentPosition').html('');
		$('#currentPosition').append(currentPosition);

		// $('#plane_locationName').text(name);
		$('#plane_locationName').text(name).unbind('click').bind('click', function() {
			planeViewPosition(id, name);
		});

		//初始化tool显示
		$('#plane_monitorPoint').text("0个");
		$('#plane_alarmPoint').text("0个");

		//清空signx_show内的视频播放器
		videoPlugin.videoPlayerClear();

		//隐藏#signx_show显示
		$("#layerImgDivShow #signx_show").hide();

		//清空#signx_show元素
		$("#layerImgDivShow #signx_show").empty();
		var planeLayerImg = $('<img id="planeLayer">').clone();
		$("#layerImgDivShow #signx_show").append(planeLayerImg);

		//初始化图片显示
		$("#layerImgDivShow #planeLayer").attr("src","");

		//删除所有点位标记
		$("#layerImgDivShow [id*=Ts]").remove();

		$.ajax({
			type : 'post',
			url : '${ctx}/xtgl/planeLayer/findByPliAreaId.json',
			data : {'pliAreaId':id},
			dataType : 'json',
			async:false,
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
								/* debugger; */
								if (data.success) {
									if(data.obj.length>0){
										var layerImgId=data.obj[0].id;
										console.log(layerImgId);
										$("#layerImgDivShow #planeLayer").attr("src","${ctx}/common/affix/download?id="+layerImgId);
										var comWidth = $("body").outerWidth(true);
										var rightWidth = $(".coral-layout-panel-east").outerWidth(true);

										var lessWidth  = comWidth  - rightWidth - (40+20); //  left 20 center right 20 + padding20

										var pliWidth_new = pliWidth;
										var pliHeight_new =pliHeight;
										var mapShowPercent_=1;
										if(lessWidth < pliWidth){// center < pic
											mapShowPercent_ = lessWidth/pliWidth;
											pliWidth_new = lessWidth;
											pliHeight_new = pliHeight_new * mapShowPercent_;
										}


										//$("#layerImgDivShow .imageboxall_abs").width(pliWidth);
										//$("#layerImgDivShow .imageboxall_abs").height(pliHeight);
										$("#layerImgDivShow .imageboxall_abs").width(pliWidth_new);
										$("#layerImgDivShow .imageboxall_abs").height(pliHeight_new);

										//显示
										$("#planeMapHome").hide();
										$("#layerImgDivShow #signx_show").show();
										$("#layerImgDivShow .title-position").show();


										//var comWidth = $("body").outerWidth(true) - $(".coral-layout-panel-east").outerWidth(true);
										//if(comWidth < pliWidth){
										//	$(".map").addClass("scroll-box");
										//}else{
										//	$(".map").removeClass("scroll-box");
										//}



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
														//删除所有点位标记
														$("#layerImgDivShow [id*=Ts]").remove();
														$.sign_show.loadingSign(data.obj, mapShowPercent_,mapShowPercent_,callbackFunction);//载入标记数据
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
						$.confirm(
						        "该区域没有关联图层，是否现在关联？",
						        "消息确认",
						        function (test) {
						            if (test == true) {
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
						            if (test == false) {
						                console.log("cancel")
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

		// 加载监控点、报警点数量
		var areaInfoCountData = common.getAreaInfoCount(jsConst.ROOT_ORGA_CODE, id);
		$('#plane_monitorPoint').text(areaInfoCountData.sxjsl + "个");
		$('#plane_alarmPoint').text(areaInfoCountData.bjqsl + "个");

		$("#mapAreaId").textbox("setValue", id);
	}
</script>





























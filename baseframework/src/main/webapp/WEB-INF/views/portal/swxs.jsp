<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<div class="rightDivStyle right-zb xslb-div">
	<h4>巡视列表</h4>
	<div class="xslb-box">
		<div id="roamPathPanel1" style="margin-top: 10px;display: none;"></div>
		<div id="roamPathPanel2" class="tree-panel" style="display: none;">
			<ul id="ulCameraTree" class="my-ul-tree" oncontextmenu="return false"></ul>					
		</div>
		<!-- Begin add by linhe for patrol list 2018-01-05 -->
		<div class="radio-box">
			<!-- 大屏预案下拉框选择 -->
			<div class="combo-box"><span>大屏预案： </span><cui:combobox id="dpyaList" emptyText="请选择大屏预案"></cui:combobox></div>
			
			<!-- 巡视路线单选框选择 -->
	       	<cui:radiolist id="patrolList" componentCls="radio-lg" repeatLayout="flow"
	       	readonly="false" column="1" name="radiolist" url=""></cui:radiolist>
       	</div>

	</div>
</div>
<div class="rightDivStyle right-zb">
	<div class="btn-box">
		<div class="play1 btnCls" onclick="patrolList.play()"><span>开始巡视</span></div>
		<div class="play2 btnCls" onclick="patrolList.goPlay()"><span>继续巡视</span></div>
		<div class="pause btnCls" onclick="patrolList.pause()"><span>暂停巡视</span></div>
		<div class="stop btnCls" onclick="patrolList.stop()"><span>停止巡视</span></div>
	</div>
</div>
<!-- 监控视频弹窗 -->
<cui:dialog id="showRouteVideoDialog" resizable="false" subTitle ="监控视频"  width="400" height="400"  iframePanel="true" autoOpen="false" onOpen="map.stopPlay" onClose="map.contiunePlay" position="map.videoPostion">
  <div id="videoPlay_map">视频</div>
</cui:dialog>
<script type="text/javascript">
	var patrolList ;
    var jsConst = window.top.jsConst;
	$(function(){
		patrolList = {
			//cusNumber:jsConst.CUS_NUMBER,
			url:jsConst.basePath+"/route/findByPrisonCode?cusNumber="+jsConst.CUS_NUMBER + "&departCode=" +jsConst.DEPARTMENT_ID ,
			init:function(){
				$.parseDone(function(){
					//巡视路线信息
					$("#patrolList").radiolist("reload",patrolList.url);
					if(jsConst.USER_LEVEL != 3){
                        $("#dpyaList").combobox("reload", {url: "${ctx}/screenPlan/seachComboData.json?cusNumber"+ jsConst.CUS_NUMBER + "&isDynamic=1"});
                    }
					//绑定事件
                    // if (typeof (map.onWaypointchanged) == "function") ____events["onCameraTourWaypointChanged"] = map.onWaypointchanged;
                    // if (typeof (stopControlMenu) == "function") ____events["onCameraFlyFinished"] = stopControlMenu;
				})
				initControlMenu();//zxz 2018-04-25
			},
			//开始巡视
			play:function(){
				if(jsConst.MAP_TYPE != "0"){
					// 巡视路线编号
					var routeId = $("#patrolList").radiolist("getValue");
					if(routeId==null || routeId==''){
						$.alert({
							message:'请选择巡视路线！',
							title:"提示信息",
							iframePanel:true
						});
					}else{
						// 保存到三维巡视记录表
						doAjaxSaveRoamRecord();
						
                        if (typeof (map.onWaypointchanged) == "function") ____events["onCameraTourWaypointChanged"] = map.onWaypointchanged;
                        if (typeof (stopControlMenu) == "function") ____events["onCameraFlyFinished"] = stopControlMenu;
                        map.setMouseFlyMode();
						map.showTour(routeId,'2000');
						playControlMenu();//zxz 2018-04-25
					}
                } else {
				    $.message({
						message: "请先切换至三维地图",
						cls: "warning"
					});
                }
			},
			//继续巡视
			goPlay:function(){
				map.setMouseFlyMode();
				map.contiunePlay();
				goPlayControlMenu();//zxz 2018-04-25
			},
			//暂停
			pause:function(){
				map.cameraTour.pause();
				pauseControlMenu();//zxz 2018-04-25
			},
			//停止
			stop:function(){
				map.stopCameraTour();
				stopControlMenu();//zxz 2018-04-25
			}
		}
		patrolList.init();
	})
	//初始化巡视按钮控制 //zxz 2018-04-25
	function initControlMenu() {
		$('div.play1').show();
		$('div.play2').hide();
		$('div.pause').hide();
		$('div.stop').hide();
	}

	//开始巡视按钮控制 //zxz 2018-04-25
	function playControlMenu() {
		$('div.play1').hide();
		$('div.play2').hide();
		$('div.pause').show();
		$('div.stop').show();
		$("#patrolList").radiolist("disable");

	}
	//继续巡视按钮控制 //zxz 2018-04-25
	function goPlayControlMenu() {
		$('div.play1').hide();
		$('div.play2').hide();
		$('div.pause').show();
		$('div.stop').show();
		$("#patrolList").radiolist("disable");
	}
	//暂停巡视按钮控制 //zxz 2018-04-25
	function pauseControlMenu() {
		$('div.play1').hide();
		$('div.play2').show();
		$('div.pause').hide();
		$('div.stop').show();
	}
	//停止巡视按钮控制 //zxz 2018-04-25
	function stopControlMenu() {
		$('div.play1').show();
		$('div.play2').hide();
		$('div.pause').hide();
		$('div.stop').hide();
		$("#patrolList").radiolist("enable");
	}
	
	/**
	 * 保存三维巡视记录
	 */
	function doAjaxSaveRoamRecord() {
		// 巡视路线编号
		var roamPathId = $("#patrolList").radiolist("getValue");
		// 巡视路线名称
		var roamPathName = $("#patrolList").radiolist("getText");
		// 大屏预案编号
		var screenPlanId = $("#dpyaList").combobox("getValue");
		// 大屏预案名称
		var screenPlanName = $("#dpyaList").combobox("getText");
		
		var urlPath = "${ctx}/roamRecord/save";
		$.ajax({
			type : "post",
	        url : urlPath,
	        data: {
	        	roamPathId: roamPathId,
	        	roamPathName: roamPathName,
	        	screenPlanId: screenPlanId,
	        	screenPlanName: screenPlanName
	        },
			dataType : "json",
			success : function(data) {
	            if(data.code == 200){
	            	
	            }else if(data.code == 500){
	    			$.messageQueue({ message : data.message, cls : "warning", iframePanel : true, type : "info" });
	            }
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$.messageQueue({ message : textStatus, cls : "warning", iframePanel : true, type : "info" });
			}
		});
	}
</script>
<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<link href="${ctx}/static/module/calendar/css/reset.css" rel="stylesheet">
<link href="${ctx}/static/module/calendar/css/css.css" rel="stylesheet">
<style>
.divInline {
	height: 100%;overflow:hidden;
	display：inline-block
}
</style>	
	<!--日历-->
	<div id="rili">
		<div id='calendar'></div>
	</div>
	
	<!--报警-->
	<div class="bjxx-box box">
		<div class="rightDivStyle right-zb" id="prisonDeviceCount" >
			<h4 align="right">出工信息</h4>
				<div class="info_list wfsb">
				<dl>
					<dd style='width:13%;'>单位</dd>
					<dd style='width:17%;'>车间负责人</dd>
					<dd style='width:10%;'>警力</dd>
					<dd style='width:20%;'>出工时间</dd>
					<dd style='width:10%;'>刑事犯</dd>
					<dd style='width:10%;'>危安犯</dd>
					<dd style='width:10%;'>罪犯人数</dd>
					<dd style='width:10%;'>警囚比</dd>
				</dl>
				<div class="maquee wfsb-list" id="bjxxList">
					<div id="bjxx_notData" style="display: none;" align="center" >暂无数据!</div>
				</div>
			</div>
		</div>
	</div>
	
	<!-- 事务督办 -->
	<div class="swdb-box box">
		<div class="rightDivStyle right-zb" id="prisonDeviceCount" >
			<h4 align="right">巡查通报</h4>
				<div class="info_list wfsb">
				<dl>
<%--				<dd style='width:25%;'>填报单位</dd>
					<dd style='width:25%;'>填报时间</dd>
					<dd style='width:25%;'>故障类型</dd>
					<dd style='width:25%;'>故障内容</dd>--%>
					<dd style='width:13%;'>被督办单位</dd>
					<dd style='width:22%;'>填报时间</dd>
					<dd style='width:30%;'>事件内容</dd>
					<dd style='width:35%;'>事件地点</dd>
				</dl>
				<div class="maquee wfsb-list" id="swdbList">
					<div id="swdb_notData" style="display: none;" align="center" >暂无数据!</div>
				</div>
			</div>
		</div>
	</div>
	
	<!-- 外来人车 -->
	<div class="bjxx-box box">
		<div class="rightDivStyle right-zb" id="prisonDeviceCount" >
			<h4 align="right">放风信息</h4>
				<div class="info_list wfsb">
				<dl>
<%--					<dd style='width:25%;'>进入时间</dd>
					<dd style='width:25%;'>带队民警</dd>
					<dd style='width:25%;'>车辆</dd>
					<dd style='width:25%;'>人员</dd>--%>
					<dd style='width:13%;'>单位</dd>
					<dd style='width:17%;'>负责人</dd>
					<dd style='width:10%;'>警力</dd>
					<dd style='width:20%;'>放风时间</dd>
					<dd style='width:10%;'>刑事犯</dd>
					<dd style='width:10%;'>危安犯</dd>
					<dd style='width:10%;'>罪犯人数</dd>
					<dd style='width:10%;'>警囚比</dd>
				</dl>
				<div class="maquee wfsb-list" id="wlrcList">
					 <div id="wlrc_notData" style="display: none;" align="center" >暂无数据!</div>
				</div>
			</div>
		</div>
	</div>
	
	<!-- 摄像头故障情况 -->
	<div class="bjxx-box box">
		<div class="rightDivStyle right-zb" id="prisonDeviceCount" >
			<h4 align="right">摄像头故障情况</h4>
			<div id="echarts_camera" style="height:270px; width:300px; margin:0 auto"></div>
		</div>
	</div>
	

<script src="${ctx}/static/module/calendar/js/calendar.js"></script>

<script type="text/javascript">
	/**
	 * 返回当前时间
	 * type = 0 返回 YYYY-MM-DD 
	 * type = 1 返回YYYY-MM-DD HH:MM:SS
	 * type = 2 返回YYYY-MM-DD 00:00:00
	 */
	function getCurTime(type){
		var nowDate = new Date();
		var day = nowDate.getDate().toString().length == 2?nowDate.getDate():'0'+nowDate.getDate();
		var month = (nowDate.getMonth()+1).toString().length == 2?(nowDate.getMonth()+1):'0'+(nowDate.getMonth()+1);
		if(type == 0){
			return nowDate.getFullYear() +'-'+ month +'-'+ day;
		}else if(type == 1){
			return nowDate.getFullYear() +'-'+ month +'-'+ day +
			' '+nowDate.getHours()+':'+nowDate.getMinutes()+':'+ nowDate.getSeconds();
		}else if(type == 2){
			return nowDate.getFullYear() +'-'+ month +'-'+ day +' 00:00:00';
		}
	}

	//加载工作日程安排
	function loadGZRCAP(){
		Rili("#calendar");
		
	}
	//加载收工信息
	function loadBJXX(){
		$("#bjxxList").mCustomScrollbar({
			theme:"minimal-light",
			autoExpandScrollbar:true
		});
		
		var url = "${ctx}/xxhj/cgsgxx/searchData.json";
        <%--var url = "${ctx}/alarm/alarmRecordList.json";--%>
		if(jsConst.USER_LEVEL == 1){
			// url = url + "?P_orders=ard_oprtn_stts_indc,asc,ard_alert_level_indc,asc,ard_crte_time,desc";
			url = url; //+ "?P_orders=ard_oprtn_stts_indc,asc,ard_alert_level_indc,asc,ard_crte_time,desc";
		} else if(jsConst.USER_LEVEL == 2){
			// url = url + "?ardCusNumber=" + cusNumber + "&P_orders=ard_oprtn_stts_indc,asc,ard_alert_level_indc,asc,ard_crte_time,desc";
			url = url + "?pwrCusNumber=" + jsConst.CUS_NUMBER+ "&pwrTimeStart=" + getCurTime(2) + "&pwrStatus=" + 1;
		} else if(jsConst.USER_LEVEL == 3){
			// url = url + "?ardCusNumber=" + cusNumber + "&dprtmntId=" + jsConst.DEPARTMENT_ID + "&P_orders=ard_oprtn_stts_indc,asc,ard_alert_level_indc,asc,ard_crte_time,desc";
			url = url + "?pwrCusNumber=" + jsConst.CUS_NUMBER + "&pwrDprtmntId=" + jsConst.DEPARTMENT_ID + "&pwrTimeStart=" + getCurTime(2) + "&pwrStatus=1";
		}
		
		$.ajax({
			type : 'post',
			url : url,
			data : {
				// "pwrTimeStart" : getCurTime(0)
			},
			dataType : 'json',
			success : function(data) {
				if(data.exception==undefined){
					var resultData = data.data;
					if(resultData && resultData.length > 0) {
						$("#bjxx_notData").hide();
						
						var arryHtml = [];
						arryHtml.push("<ul class = 'police-box'>");
						for(var i = 0; i < resultData.length; i++) {
/*							arryHtml.push("<li><div class='divInline' style='width:25%;'>"+(resultData[i].ALARM_DATE?resultData[i].ALARM_DATE:"无")+"</div>");
							arryHtml.push("<div class='divInline' style='width:25%;'>"+(resultData[i].ALARM_TIME?resultData[i].ALARM_TIME:"无")+"</div>");
							arryHtml.push("<div class='divInline' style='width:45%;'>"+(resultData[i].ALARM_ADDRESS?resultData[i].ALARM_ADDRESS:"无")+"</div></li>");*/
							arryHtml.push("<li><div class='divInline' style='width:13%;'>"+(resultData[i].pwrDprtmntName?resultData[i].pwrDprtmntName:"无")+"</div>");
							arryHtml.push("<div class='divInline' style='width:17%;'>"+(resultData[i].pwrLeadPoliceName?resultData[i].pwrLeadPoliceName:"无")+"</div>");
							arryHtml.push("<div class='divInline' style='width:10%;'>"+(resultData[i].pwrPoliceCount?resultData[i].pwrPoliceCount:"无")+"</div>");
							arryHtml.push("<div class='divInline' style='width:20%;'>"+(resultData[i].pwrStartTime?resultData[i].pwrStartTime:"无")+"</div>");
							arryHtml.push("<div class='divInline' style='width:10%;'>"+(resultData[i].pwrXsfCount?resultData[i].pwrXsfCount:"无")+"</div>");
							arryHtml.push("<div class='divInline' style='width:10%;'>"+(resultData[i].pwrWafCount?resultData[i].pwrWafCount:"无")+"</div>");
							arryHtml.push("<div class='divInline' style='width:10%;'>"+(resultData[i].pwrPrisonerCount?resultData[i].pwrPrisonerCount:"无")+"</div>");
							arryHtml.push("<div class='divInline' style='width:10%;'>"+((resultData[i].pwrPrisonerCount)?((resultData[i].pwrPoliceCount/resultData[i].pwrPrisonerCount).toFixed(2)):"无")+"</div></li>");
						}
						arryHtml.push("</ul>"); 
						$("#bjxxList").html(arryHtml.join(""));
					} else {
						$("#bjxx_notData").show();
					}
				}else{
					$("#bjxx_notData").show();
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
	//加载巡查通报
	function loadSWDB(){
		$("#swdbList").mCustomScrollbar({
			theme:"minimal-light",
			autoExpandScrollbar:true
		});

        var url = "${ctx}/monitor/searchMonitor.json";
        if(jsConst.USER_LEVEL == 1){
            url = url;
        } else if(jsConst.USER_LEVEL == 2){
            url = url + "?mdoCusNumber=" + jsConst.CUS_NUMBER + "&modSttsIndc=1&mdoConsultStatus=0";
        } else if(jsConst.USER_LEVEL == 3){
            url = url + "?mdoCusNumber=" + jsConst.CUS_NUMBER + "&modSttsIndc=1&mdoConsultStatus=0&mdoNoticeDepartment=" + jsConst.DEPARTMENT_ID;
        }
		
		<%--var url = "${ctx}/deviceMaintain/listAll.json?dmaCusNumber=" + jsConst.CUS_NUMBER + "&P_orders=dma_fault_submit_time,desc";--%>
		var url = "${ctx}/monitor/searchMonitor.json?mdoCusNumber=" + jsConst.CUS_NUMBER + "&modSttsIndc=1&mdoConsultStatus=0";
		$.ajax({
			type : 'post',
			url : url,
			dataType : 'json',
			success : function(data) {
				if(data.exception==undefined){
					var resultData = data.data;
					if(resultData && resultData.length > 0) {
						$("#swdb_notData").hide();
						
						var arryHtml = [];
						arryHtml.push("<ul class = 'police-box'>");
						for(var i = 0; i < resultData.length; i++) {
/*							arryHtml.push("<li><div class='divInline' style='width:23%;'>"+(resultData[i].DMA_DPRMNT_NAME?resultData[i].DMA_DPRMNT_NAME:"无")+"</div>");
							arryHtml.push("<div class='divInline' style='width:23%;'>"+(resultData[i].DMA_FAULT_SUBMIT_TIME?resultData[i].DMA_FAULT_SUBMIT_TIME:"无")+"</div>");
							arryHtml.push("<div class='divInline' style='width:23%;'>"+(resultData[i].DMA_FAULT_TYPE_CH?resultData[i].DMA_FAULT_TYPE_CH:"无")+"</div>");
							arryHtml.push("<div class='divInline' style='width:30%;'>"+(resultData[i].DMA_FAULT_CONTENT_CH?resultData[i].DMA_FAULT_CONTENT_CH:"无")+"</div></li>");*/
							arryHtml.push("<li><div class='divInline' style='width:13%;'>"+(resultData[i].MDO_NOTICE_DEPARTMENT_NAME?resultData[i].MDO_NOTICE_DEPARTMENT_NAME:"无")+"</div>");
							arryHtml.push("<div class='divInline' style='width:22%;'>"+(resultData[i].MDO_CRTE_TIME?resultData[i].MDO_CRTE_TIME:"无")+"</div>");
							arryHtml.push("<div class='divInline' style='width:30%;'>"+(resultData[i].MDO_PROBLEM?resultData[i].MDO_PROBLEM:"无")+"</div>");
							arryHtml.push("<div class='divInline' style='width:35%;'>"+(resultData[i].MDO_ADDR?resultData[i].MDO_ADDR:"无")+"</div></li>");
						}
						arryHtml.push("</ul>"); 
						$("#swdbList").html(arryHtml.join(""));
					} else {
						$("#swdb_notData").show();
					}
				}else{
					$("#swdb_notData").show();
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
	//加载放风信息
	function loadWLRC(){
		
        <%--var url = "${ctx}/foreign/listAll.json?frCusNumber="+ jsConst.CUS_NUMBER;--%>
        var url = "${ctx}/xxhj/jndt/searchData.json?parCusNumber="+ jsConst.CUS_NUMBER + "&parStartTime=" + getCurTime(2) + "&parStatus=1&parOutCategory=FF";

		$.ajax({
			type : 'post',
			url : url,
			data : {
				// "frTimeStart" : getCurTime(2)
			}, 
			dataType : 'json',
			success : function(data) {
				if(data.exception==undefined){
					var resultData = data.data;
					if(resultData && resultData.length > 0) {
						$("#wlrc_notData").hide();
						
						var arryHtml = [];
						arryHtml.push("<ul class = 'police-box'>");
						for(var i = 0; i < resultData.length; i++) {
/*							arryHtml.push("<li><div class='divInline' style='width:25%;'>"+(resultData[i].FR_TIME?resultData[i].FR_TIME:"无")+"</div>");
							arryHtml.push("<div class='divInline' style='width:25%;'>"+(resultData[i].policeName?resultData[i].policeName:"无")+"</div>");
							arryHtml.push("<div class='divInline' style='width:25%;'>"+(resultData[i].FR_CARS_INFO?resultData[i].FR_CARS_INFO:"无")+"</div>");
							arryHtml.push("<div class='divInline' style='width:25%;'>"+(resultData[i].FR_PEOPLES_INFO?resultData[i].FR_PEOPLES_INFO:"无")+"</div></li>");*/
                            arryHtml.push("<li><div class='divInline' style='width:13%;'>"+(resultData[i].parDprtmntName?resultData[i].parDprtmntName:"无")+"</div>");
                            arryHtml.push("<div class='divInline' style='width:17%;'>"+(resultData[i].parPoliceName?resultData[i].parPoliceName:"无")+"</div>");
                            arryHtml.push("<div class='divInline' style='width:10%;'>"+(resultData[i].parPoliceCount?resultData[i].parPrisonerCount:"无")+"</div>");
                            arryHtml.push("<div class='divInline' style='width:20%;'>"+(resultData[i].parStartTime?resultData[i].parStartTime:"无")+"</div>");
                            arryHtml.push("<div class='divInline' style='width:10%;'>"+(resultData[i].parXsfCount?resultData[i].parXsfCount:"无")+"</div>");
                            arryHtml.push("<div class='divInline' style='width:10%;'>"+(resultData[i].parWafCount?resultData[i].parWafCount:"无")+"</div>");
                            arryHtml.push("<div class='divInline' style='width:10%;'>"+(resultData[i].parPrisonerCount?resultData[i].parPrisonerCount:"无")+"</div>");
                            arryHtml.push("<div class='divInline' style='width:10%;'>"+((resultData[i].parPrisonerCount)?((resultData[i].parPoliceCount/resultData[i].parPrisonerCount).toFixed(2)):"无")+"</div></li>");
						}
						arryHtml.push("</ul>"); 
						$("#wlrcList").html(arryHtml.join(""));
					} else {
						$("#wlrc_notData").show();
					}
				}else{
					$("#wlrc_notData").show();
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
	//加载摄像头故障情况
	function loadSXTGZ(){	
		var url = "${ctx}/jfsb/camera/searchCameraCount.json?cusNumber="+ jsConst.CUS_NUMBER;
		$.ajax({
			type : 'post',
			url : url,
			dataType : 'json',
			success : function(data) {
				if(data.exception==undefined){
					var resultData = data;
					if(resultData){
						var cameraChart = echarts.init(document.getElementById('echarts_camera'));
						
						var cameraChartOption = {
							    
							    tooltip : {
							        trigger: 'item',
							        formatter: "{b} : {c} ({d}%)"
							    },
							  	calculable : true,
							  	color:['green', 'red'],
							    series : [
							        {
							            type:'pie',
							            radius : '40%',
							            center: ['60%', '30%'],
							            data:resultData
							        }
							    ]
							};
					 
					 cameraChart.setOption(cameraChartOption);	
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
	$.parseDone(function(){
		
		//加载工作日程安排
		loadGZRCAP();
		//加载报警信息
		loadBJXX();
		//加载事务督办
		loadSWDB();
		//加载外来人车
		loadWLRC();
		//加载摄像头故障情况
		loadSXTGZ();
		 	                    
	});
	

</script>
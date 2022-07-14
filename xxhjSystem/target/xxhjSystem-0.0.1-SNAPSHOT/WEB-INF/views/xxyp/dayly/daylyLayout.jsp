<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<link rel="stylesheet" type="text/css"
	href="${ctx}/static/module/dayly/dayly.css" />
<cui:layout id="layout_dayly" fit="true">
	<cui:layoutRegion region="north" split="false" style="height:75px;">
		<div style="float: left;font-size: 14px;width: 100%;margin-bottom: 5px;">
			<div style="float: left;">
				<div class="date_div">
					当前时间：<span class="spanTime">
							 	<span id="ymd"></span>
							 	&nbsp;&nbsp;00:00:00 ~ <span id="dateTime"></span>
							 </span>&nbsp;&nbsp;&nbsp;
				</div>
				<a href="javascript:void(0);" onclick="refurbish();" style="color: rgb(4,181,113);">刷新</a>
			</div>
			<div style="float: right;">
				日期：<cui:datepicker id="date" dateFormat="yyyy-MM-dd" />&nbsp;&nbsp;&nbsp;
				<!--时点：<select id="hour" style="width: 60px">
					    <option value="0">全天</option>
					  	<option value="16">16点</option>
					  	<option value="22">22点</option>
					  	<option value="24">24点</option>
					  </select>&nbsp;&nbsp;&nbsp;-->
				监狱列表：<cui:combobox  id="prisonList" emptyText="全省" url="${ctx}/common/authsystem/findAllJyForCombobox.json"></cui:combobox>&nbsp;&nbsp;&nbsp;
				<button class="submit" onclick="queryData();">查询</button>
				<!-- <button class="downLoad" onclick="toExportDayly();">导出</button> -->
			</div>
		</div>
		<div style="text-align: center;">
			<label id="cusName" style="font-size:25px;font-weight:bold;"></label>
		</div>
	</cui:layoutRegion>
	<cui:layoutRegion region="center">
	</cui:layoutRegion>
</cui:layout>
<script type="text/javascript"
	src="${ctx}/static/module/dayly/common.js"></script>
<script type="text/javascript">

var daylyPanel;
$.parseDone(function(){
	setTime();
	
	daylyPanel = $("#layout_dayly").layout("panel", "center");
	if(jsConst.USER_LEVEL==1){
		$("#cusName").html("全省");
		daylyPanel.panel("refresh", jsConst.basePath+"/xxyp/provDayly/provinceDayly");
	}else{
		$("#cusName").html(jsConst.CUS_NAME);
		$("#prisonList").combobox("setValue",jsConst.ORG_CODE);
		$("#prisonList").combobox("option","readonly",true);
		daylyPanel.panel("refresh", jsConst.basePath+"/xxyp/dayly/prisonDayly");
	}
});

/**
 * 当前时间
 */
function setTime(){
	$("#ymd").html(formatterDate(new Date()));
	$("#dateTime").html(formatterDate(new Date(),"hms"));
	setTimeout(setTime,1000);
}

/**
 * 刷新
 */
function refurbish(){
	$("#dateTime").html(formatterDate(new Date(),"hms"));
	$("#date").datepicker('setDate',new Date());
	loadData(formatterDate(new Date()));
}

/**
 * 查询
 */
function queryData(){
	var combo_cusNumber=$("#prisonList").combobox("getValue");
	var cusName=$("#prisonList").combobox("getText");
	if(combo_cusNumber){
		$("#cusName").html(cusName);
		daylyPanel.panel("refresh", jsConst.basePath+"/xxyp/dayly/prisonDayly?cusNumber="+combo_cusNumber);
	}else{
		$("#cusName").html("全省");
		daylyPanel.panel("refresh", jsConst.basePath+"/xxyp/provDayly/provinceDayly");
	}
	var date = $("#date").datepicker('getDateValue');
	var today = window.top.getCurTime(0);
	if(date > today){
		$.alert({
			message:'查询日期不能大于当天',
			title:"信息提示",
			iframePanel:true
		});
		return;
	}
	if(date < formatterDate(new Date())){
		//loadPrisonInformationDayly(date,24);
		loadData(date);
	}else{
		loadData(date);
	}
}
</script>
<!DOCTYPE HTML>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.cesgroup.prison.common.facade.AuthSystemFacade"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<html lang="en">
<head>
<meta content="IE=11.0000" http-equiv="X-UA-Compatible" />
<meta name="viewport" content="width=device-width, initial-scale=1" /> 
<meta charset="utf-8" /> 
<meta name="keywords" content="" />
<meta name="GENERATOR" content="MSHTML 11.00.9600.17037" />
<title>监内动态</title> 
<link rel="stylesheet" type="text/css" href="${ctx}/static/jndt/easy-responsive-tabs.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/static/jndt/bootstrap.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/static/jndt/style.css" />
<script type="text/javascript" src="${ctx}/static/jndt/jquery-2.1.4.min.js"></script>
<script type="text/javascript" src="${ctx}/static/jndt/easy-responsive-tabs.js"></script>
<script type="text/javascript" src="${ctx}/static/js/scripts/common.js"></script>
</head>

<body>
	<section class="demo" id="coaches">
		<div class="container">
			<h3 class="heading"><span>监内动态</span></h3>
			<div id="verticalTab">
				<ul id="jndtList" class="resp-tabs-list">
	  			</ul>
	  			<div id="jndtDetail" class="resp-tabs-container">
	  			</div>
			</div>
		</div>
	</section>
</body>

<script>

	var combobox_bm = <%=AuthSystemFacade.getAllChildrenOrgJsonInfoByOrgKey(String.valueOf(request.getAttribute("cusNumber")))%>;
	var comboboxData_jcsy = [
	    {value:"JJ",text:"接见"},                   
	    {value:"CFCG",text:"厂房出工"},   
		{value:"LSCG",text:"临时出工"},
		{value:"JNJY",text:"监内就医"},
		{value:"FF",text:"放风"},
		{value:"WC",text:"外出"},
		{value:"SY",text:"收押"},
		{value:"SF",text:"释放"},
		{value:"QT",text:"其他"}];
	                 	
	$(function() {
	 	setInterval(function(){
				queryJndtList(1);
			}, 5000); 	
	});

	function queryJndtList(param) {		
		
		$.ajax({
			type: "post",
			data: {
				cusNumber: '${cusNumber}'
			},
			url: "${ctx}/guest/jndt/searchAllData",
			dataType: "json",
			success: function(data) {
				
				if(data) {
					parseJndtList(data,param);
				}
			}
		});
	} 
	
	function parseJndtList(data,param) {

		var arryHtml = [];
		for(var i = 0; i < data.length; i++) {
			
			var ardOprtnSttsIndc = data[i].ardOprtnSttsIndc;
			if(ardOprtnSttsIndc && ardOprtnSttsIndc != "3") {
				window.location.href = "${ctx}/alarm/guest/showAlarmMsg?id=" + data[i].parRemark;
			}
			
			var dprtmntName = formatDrptmntName(data[i].parDprtmntId);
			var OutCategory = formatOutCategory(data[i].parOutCategory);
			var parStartTime = (data[i].parStartTime).substring(11,16);
			var parBackStatus;
			if(data[i].parBackTime) {
				parBackStatus = "<span style='font-size:25px;font-weight:bold;color:blue;float:right;margin: -30px 0px 30px 0px;'>已返回</span>";
		
			} else {
				parBackStatus = "<span style='font-size:25px;font-weight:bold;color:red;float:right;margin: -30px 0px 30px 0px;'>未返回</span>";
			}
			
			if(data[i].parOutCategory == "BJ") {
				
				/* arryHtml.push("<li>");
				arryHtml.push("<div class=\"col-md-8 tab1\"><h4 style=\"color: red;\">"+ data[i].parOutReason + "处置完成！</h4></div>");
				arryHtml.push("<div class=\"clearfix\"></div>");
				arryHtml.push("<input type=\"hidden\" value=\"" + i + "\">");
				arryHtml.push("</li>"); */
			} else {
/* 				arryHtml.push("<li><div class=\"col-md-4 tab1\"><img src=\"${ctx}/common/authsystem/findMjPicInfo?loginName=\"" + data[i].pbdLoginName + "&demptId="+data[i].parDprtmntId+"\" width=\"12%\" height=\"12%\"></div>");
 */				arryHtml.push("<li><div class='col-md-4 tab1'><img src='${ctx}/common/authsystem/findMjPicInfo?loginName=" + data[i].pbdLoginName + "&demptId="+data[i].pbdDrptmntId+"' width='12%' height='12%'></div>");

				arryHtml.push("<div class=\"col-md-8 tab1\"><h3>" + dprtmntName + "&nbsp;&nbsp;" + data[i].parStartReporterName + "</h3><h4><label style=\"float:left;\">时间：" + parStartTime + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>" + parBackStatus + "</h4><h4>带" + data[i].parPrisonerCount + "名罪犯" +OutCategory+"("+data[i].parOutReason +")</h4></div>");
				arryHtml.push("<div class=\"clearfix\"></div>");
				arryHtml.push("<input type=\"hidden\" value=\"" + i + "\">");
				arryHtml.push("</li>");
			}
		}
		
		$("#jndtList").html(arryHtml.join(""));
		
		if(param) {
			parseJndtDetail(data, 0);
		}
		
		$('#verticalTab').easyResponsiveTabs({
			type: 'vertical',
			width: 'auto',
			fit: true,
			activate: function() {
				
				var i = $(this).find("input").val();
				parseJndtDetail(data, i);
			}
		});
	}

	function parseJndtDetail(data, i) {
		
		var html;
		
		if(data[i].parOutCategory == "BJ") {
			
			html = "<div>" + 
			"<div class=\"col-md-7 tabs-right1\">" + 
			"<p style=\"font-size: 30px;\">事由：" + data[i].parOutReason + "</p>" +
			"</div>" +
			"<div class=\"clearfix\"></div>" +
			"</div>";
		} else {
			var parStartTime = (data[i].parStartTime).substring(11,16);
			var OutCategory = formatOutCategory(data[i].parOutCategory);
			var parBackStatus;
			if(data[i].parBackTime) {
				parBackStatus = "<label style='font-size:38px;color:blue;float:right;'>已返回</label>";
			} else {
				parBackStatus = "<label style='font-size:38px;color:red;float:right;'>未返回</label>";
			}
			
			html = "<div>" + 
				"<div class=\"col-md-7 tabs-right1\">" + 
				"<h3 style=\"font-size: 58px;\">" + formatDrptmntName(data[i].parDprtmntId) + "&nbsp;&nbsp;&nbsp;&nbsp;" + data[i].parStartReporterName + "</h3>" +
				"<h4 style=\"font-size: 50px;\"><label style=\"\">时间：" + parStartTime + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label><label style=\"float: right; font-size: 38px; color: red;\">" + parBackStatus + "</label></h4>" +
				"<div class=\"tab-bottom\">" +
				// "<p style=\"font-size: 38px;\"><i class=\"fa fa-map-marker\" aria-hidden=\"true\"></i>当前位置：操场</p>" +
				"<p style=\"font-size: 38px;\"><i class=\"fa fa-map-marker\" aria-hidden=\"true\"></i><span font-weight='bold'> 负责民警：</span>" + data[i].parPoliceName + "</p>" + 
				"<p style=\"font-size: 38px;\"><i class=\"fa fa-envelope\" aria-hidden=\"true\"></i>警务通：" + data[i].pbdPoliceIdnty + "</p>" + 
				"<p style=\"font-size: 38px;\"><i class=\"fa fa-phone\" aria-hidden=\"true\"></i>对讲机：" + (data[i].pbdTalkNum == null ? "" : data[i].pbdTalkNum) + "</p>" +
				"<p style=\"font-size: 38px;\"><i class=\"fa fa-phone\" aria-hidden=\"true\"></i>罪犯人数：" + data[i].parPrisonerCount + "人</p>" +
				"<p style=\"font-size: 38px;\"><i class=\"fa fa-phone\" aria-hidden=\"true\"></i>事由：带" + data[i].parPrisonerCount + "名罪犯" +OutCategory+"("+ data[i].parOutReason + ")</p>" +
				"</div>" +
				"</div>" +
				"<div class=\"col-md-5 tabs-right2\"><img src='${ctx}/common/authsystem/findMjPicInfo?loginName=" + data[i].pbdLoginName + "&demptId="+data[i].pbdDrptmntId+"' width='20%' height='30%'></div>" +
				"<div class=\"clearfix\"></div>" +
				"</div>";
		}
		
		$("#jndtDetail").html(html);
	}
	
	function formatDrptmntName(parDprtmntId) {
		
		return convertColVal(combobox_bm, parDprtmntId);
	}
	
	function formatOutCategory(parOutCategory) {
		
		return convertColVal(comboboxData_jcsy, parOutCategory);
	}
</script>
</html>

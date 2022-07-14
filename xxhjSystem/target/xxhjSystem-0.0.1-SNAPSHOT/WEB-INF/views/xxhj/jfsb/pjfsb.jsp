<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<style>

	#technicalDeviceCount.rightTable td .title {
	    text-align: center;
	    font-weight: bold;
	    padding: 5px;
	    width: 93%;
	    margin: 10px 10px 10px 10px;
	}

   #jfsbPrisonList table td {
		padding-top: 6px;
		padding-left: 30px;
		color: #3b89d1;
	}
	
	#jfsbPrisonListLoading {
		height: 500px;
		overflow: auto;
	}

</style>

<input type="hidden" id="url" />
<input type="hidden" id="deviceName" />

<div id="rightSideHome_jfsb" class="scorllBar">

	<div class="rightDivStyle right-zb" id="technicalDeviceCount">
		<h4 align="center">全疆技防设备统计</h4>
		<table class="rightTable" align="center">
		</table>
	</div>
	
	<div class="rightDivStyle right-zb" id="jfsbPrisonList">
		<h4 align="center"></h4>
		<div id= "jfsbPrisonListLoading">
			<table></table>
		</div>
	</div>
</div>
<script type="text/javascript">

	var USER_LEVEL = jsConst.USER_LEVEL;
	var thisCusNumber = jsConst.CUS_NUMBER;
	
	$(function() {
		
		$("#rightSide_Pwfsb").mCustomScrollbar({
			theme:"minimal-light",
			autoExpandScrollbar:true
		});
		
		loadProvMethod();
	});
	
	function loadProvMethod() {
		
		$("#technicalDeviceCount").show(); 	//数量显示
		$("#jfsbPrisonList").show(); 			//各监所
		
		queryTechnicalDeviceCount();
	}
	/**
	 * 查询技防设备统计信息
	 */
	function queryTechnicalDeviceCount() {
		
		var url = "${ctx}/xxhj/jfsb/listDeviceMasterInfo?dmaTypeIndc=1";
		$.ajax({
			type : "post",
			url : url,
			dataType : "json",
			success : function(data) {
				
				if (data) {
					loadTechnicalDeviceCount(data);
				}
			}
		});
	}
	
	/**
	 * 加载技防设备统计信息
	 */
	function loadTechnicalDeviceCount(data) {
		
		var table = $("#technicalDeviceCount table");
		table.empty();
		var tr = $("<tr id='title'></tr>");
		tr.append('<td width="150" class="title">设备名称</td>');
		tr.append('<td width="100" class="title">单位</td>');
		tr.append('<td width="100" class="title">总数</td>');
		tr.append('<td width="100" class="title">故障</td>');
		table.append(tr);
		
		var unit = "";
		for (var i = 0; i < data.length; i++) {
			
			var tr = $("<tr></tr>");
			tr.append("<td>" + data[i].DMA_DEVICE_NAME + "</td>");
		
			if (data[i].DMA_DEVICE_IDNTY == 1) {
				unit = "个";
			} else if (data[i].DMA_DEVICE_IDNTY == 2) {
				unit = "个";
			} else if (data[i].DMA_DEVICE_IDNTY == 3) { //  3代表数字电网
				unit = "对";
			} else if (data[i].DMA_DEVICE_IDNTY == 4) {
				unit = "段";
			} else if (data[i].DMA_DEVICE_IDNTY == 5) {
				unit = "个";
			} else if (data[i].DMA_DEVICE_IDNTY == 6) {
				unit = "点";
			} else if (data[i].DMA_DEVICE_IDNTY == 7) {
				unit = "区";
			}
			tr.append("<td>" + unit + "</td>");
			
			if(parseInt(data[i].DMA_TOTAL_COUNT) > 0) {
				tr.append("<td><a href='javascript:void(0)'; onclick='methodControler("+ data[i].DMA_DEVICE_IDNTY
				          +",0,this)'; id='one_"+data[i].DMA_DEVICE_IDNTY+"'>"+ data[i].DMA_TOTAL_COUNT
				          +"</a></td>");
			} else {
				tr.append("<td>"+ data[i].DMA_TOTAL_COUNT+"</td>");
			}
			if(parseInt(data[i].DMA_ABNORMAL_COUNT) > 0) {
				tr.append("<td><a href='javascript:void(0)'; onclick='methodControler("+ data[i].DMA_DEVICE_IDNTY
				          +",0,this)'; id='one_"+data[i].DMA_DEVICE_IDNTY+"'>"+ data[i].DMA_ABNORMAL_COUNT
				          +"</a></td>");
			} else {
				tr.append("<td>"+ data[i].DMA_ABNORMAL_COUNT+"</td>");
			}
			table.append(tr);
			}
			
		$("#one_1").click(); 
	}
	/**
	 * 省局、监狱加载数据方法控制   deviceType 是设备编号   deviceStatus0是正常 1是故障的
	 */
	function methodControler(deviceType, deviceStatus, aId) {
		
		var obj = $(aId);
		var level = $("#level").val(); 

		$("#deviceType").val(deviceType);
		$("#deviceStatus").val(deviceStatus);
		var deviceName = obj.parent().parent().children().eq(0).html(); 

		if(deviceStatus == 0) {
		
			$("#jfsbPrisonList h4").html("各监所" + deviceName + "统计");
			queryPrisonDeviceinfo(deviceType, deviceStatus, aId);
		} else if(deviceStatus == 1) {
			
			$("#jfsbPrisonList h4").html("各监所" + deviceName + "故障统计");
			queryPrisonDeviceinfo(deviceType, deviceStatus, aId);
		}
	}
	
	/**
	 * 查询所有监狱技防设备信息
	 */
	function queryPrisonDeviceinfo(deviceType, deviceStatus, aId) {
		$("#jfsbPrisonList table").empty();
		var url = null;
		deviceStatus = deviceStatus == 0 ? "" : deviceStatus; 	
		var list = new Array();
		
		if(deviceType == 1 && deviceStatus == 1) { 	
			
			list.push(0);
		} else {
			
			list.push(deviceStatus);
		}
		var url = "${ctx}/xxhj/jfsb/listTechnicalDevicePrisonList?type="+deviceType+"&sttsIndc="+list[0];	
		
		$("#prisonListLoading").loading({ text : "正在加载中，请稍后..."})
		$.ajax({
			type : "post",
			url : url,
			dataType : "json",
			success : function(data) {
				$("#prisonListLoading").loading("hide");
				if (data) {
					loadPrisonList(data,aId,deviceType,deviceStatus);
				}
			}
		});
	}
	/**
	 * 加载全疆监狱列表
	 */
	function loadPrisonList(data, aId,type,status) {

		var unit = $(aId).parent().parent().children().eq(1).html();
		var table = $("#jfsbPrisonList table");
		table.empty(); 
		/* var arryHtml = [];
		arryHtml.push("<ul class = 'police-box'>");
		for (var i = 0; i < data.length; i++) {			
			if (data[i].OBD_ORGA_NAME != null && data[i].COUNT != null) {
				arryHtml.push("<li><div style='width:100px;text-align:left;white-space:nowrap;'>"+ data[i].OBD_ORGA_NAME + "：</div>");
				arryHtml.push("<div style='padding-left:50px;width:50px;text-align:right;'><a href='javascript:void(0);'  onClick='toPrisonPage("+ data[i].OBD_ORGA_IDNTY+ ",\""+ data[i].OBD_ORGA_NAME+ "\");'>"
						+ data[i].COUNT+ "</a> " + unit + "</div>");
				arryHtml.push("</li>"); 
			}
		}
		arryHtml.push("</ul>");
		$("#listMaquee").html(arryHtml.join("")); */
		
		for (var i = 0; i < data.length; i++) {
			
			var tr = $("<tr></tr>");
			tr.append("<td>" + data[i].OBD_ORGA_NAME + "：</td>");
			tr.append("<td><a href='javascript:void(0);' class='spTwo' onclick='toPrisonPage("
							+ data[i].OBD_ORGA_IDNTY+ ",\""+ data[i].OBD_ORGA_NAME+ "\");'>"
							+ data[i].COUNT+ "</a> " + unit + "</td>");
			table.append(tr);
		} 
	}

	/* 显示监狱信息页面 */
    function toPrisonPage(prisonId,prisonName) {
    	
    	var panel = $("#layout1").layout("panel", "east");
    	prisonName= encodeURIComponent(encodeURIComponent(prisonName));
    	
     	panel.panel("refresh", "${ctx}/xxhj/jfsb/jfsb?prisonId="+prisonId+"&prisonName="+prisonName);

    }
</script>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<style>

	#defenseDeviceCount.rightTable .title {
	    text-align: center;
	    font-weight: bold;
	    padding: 5px;
	    width: 93%;
	    margin: 10px 10px 10px 10px;
	}
	
	#wfsbPrisonList table td {
		padding-top: 6px;
		padding-left: 30px;
		color: #3b89d1; 
	}
	
	#wfsbPrisonListLoading {
		height: 450px;
		overflow: auto;
	}
	
</style>

<div id="rightSideHome_jfsb" class="scorllBar">

	<div class="rightDivStyle right-zb" id="defenseDeviceCount">
		<h4 align="center" >全疆物防设备统计</h4>
			<table class="rightTable" align="center">
			</table>
		<div class="notData">暂无数据!</div>
	</div>
	
	<div class="rightDivStyle right-zb" id="wfsbPrisonList">
		<h4 align="center" >各监所物防设备统计</h4>
			<div id= "wfsbPrisonListLoading">
				<table></table>
			</div>
	</div>
</div>

<input type="hidden" id="thisCusNumber" value="" />

<script type="text/javascript">

	var prov = false;
	
	$.parseDone(function() {
		
		$("#rightSide_Pwfsb").mCustomScrollbar({
			theme:"minimal-light",
			autoExpandScrollbar:true
		});
		
		loadProvMethod();
	});
	
	/**
	 * 加载省局用户所展示的模块
	 */
	function loadProvMethod() {
		
		$(".notData").hide();
		$("#wfsbPrisonList").show();
		queryDenfenseDeviceCount();
		prov = true;
	}
	
	/**
	 * 查询物防设备统计信息
	 */
	function queryDenfenseDeviceCount() {
		
		var cusNumber = $("#thisCusNumber").val();
		var list = new Array();
		list.push(cusNumber);
		
		var url = "${ctx}/xxhj/wfsb/listPhysicalDeviceCount?pdeCusNumber=" + list[0]; 
		
		$.ajax({
			type : "post",
			url : url,
			dataType : "json",
			success : function(data) {
				
 				if (data) {
					loadDenfenseDeviceCount(data);
				} else {
					$.alert("获取数据失败！");
				}
			}
		});
	}
	
	/**
	 * 加载物防设备统计信息
	 */
	function loadDenfenseDeviceCount(data) {
		
		if(data.length == 0) {
			$(".notData").show();
		} else {
			$(".notData").hide();
		}
		
		var table = $("#defenseDeviceCount table");
		table.empty();
		
		var tr = $("<tr></tr>");
		tr.append('<td width="200" class="title">设备名称</td>');
		tr.append('<td width="150" class="title">单位</td>');
		tr.append('<td width="150" class="title">正常</td>');
		tr.append('<td width="150" class="title">故障</td>');
		table.append(tr);
		
		for(var i = 0; i < data.length; i++) {
			var deviceName = data[i].PDN_DEVICE_NAME;
			var flag = true;
			
			//如果全部不相等 就flag=false 就不显示 if(flag)  
			//只显示以下几个类型的数据
			if(prov) {
				if (deviceName != "监门哨" && deviceName != "监狱大门") {
					if (deviceName != "外部蛇腹网" && deviceName != "内部蛇腹网") {
						if (deviceName != "围墙" && deviceName != "武警岗哨") {
							flag = false;
						}
					}
				}
			}
			
			if(flag) {
				var unit = data[i].PDE_UNIT == null ? "" : data[i].PDE_UNIT;
				tr = $("<tr align='center'></tr>");
				tr.append("<td>" + deviceName + "</td>");
				tr.append("<td>" + unit + "</td>");
				
				if (prov) {
					tr.append("<td><a href='javascript:void(0);' onclick='prisDeviceCount(\""
									+ data[i].PDN_DEVICE_NAME+ "\",0,this);'>"+ data[i].PDE_NORMAL_COUNT + "</a></td>");
					
					if (data[i].PDE_ABNORMAL_COUNT != 0) {
						tr.append("<td><a href='javascript:void(0);' onclick='prisDeviceCount(\""
										+ data[i].PDN_DEVICE_NAME+ "\",1,this);'>"+ data[i].PDE_ABNORMAL_COUNT+ "</a></td>");
					} else {
						tr.append("<td>" + data[i].PDE_ABNORMAL_COUNT + "</td>");
					}
				} else {
					tr.append("<td><span class='spTwo' style='font-size: 16px;'>"
									+ data[i].PDE_NORMAL_COUNT + "</span></td>");
					
					if (data[i].PDE_ABNORMAL_COUNT != 0) {
						tr.append("<td><span class='spTwo' style='font-size: 16px;'>"
										+ data[i].PDE_ABNORMAL_COUNT
										+ "</span></td>");
					} else {
						tr.append("<td>" + data[i].PDE_ABNORMAL_COUNT
										+ "</td>");
					}
				}
				
				table.append(tr);
			}
		}
		
		//只有第一次默认进来的时候会走这里 自动点击 监门哨 正常个数
		if(prov) {
			var deviceName = table.children().eq(0).children().eq(1).children().eq(0).html();
			var aId =table.children().eq(0).children().eq(1).find("a");
			prisDeviceCount(deviceName, 0, aId);
		}
	}
	
	/**
	 * 查询全疆监狱物防设备正常、故障数量
	 */
	function prisDeviceCount(deviceName, type, aId) {
		
        var divId = $("#defenseDeviceCount a");
		var obj = $(aId);  
		var deviceName = obj.parent().parent().children().eq(0).html();
		var list = new Array();
		list.push(deviceName);
		list.push(-1);
		
		if(type == 0) {
			$("#wfsbPrisonList h4").html("各监所" + deviceName + "统计");
		} else if(type == 1) {
			$("#wfsbPrisonList h4").html("各监所" + deviceName + "故障统计");
		}
		
		var url = "${ctx}/xxhj/wfsb/listPhysicalDeviceCountPrisonList";
		
		$.ajax({
			type : "post",    
			url : url,
			dataType : "json",
			data:{
				pdeDeviceName : list[0],
		        obdParentId : list[1]
			},
			success : function(data) {
				
				if(data) {
					
					loadPrisonList(data, type);
				}
			}
		});
	}
	
	/**
	 * 加载全疆监狱物防设备正常、故障数量
	 */
	function loadPrisonList(data, type) {

		var pde_unit = "个";
		var table = $("#wfsbPrisonList table");
		table.empty();
		
		for (var i = 0; i < data.length; i++) {
			
			var tr = $("<tr margin-top='2px'></tr>");
			tr.append("<td>" + data[i].OBD_ORGA_NAME + "：</td>");
			
			if(type == 0) {
				
				if(data[i].PDE_NORMAL_COUNT != null && data[i].PDE_NORMAL_COUNT > 0) {
					pde_unit = data[i].PDE_UNIT;
					tr.append("<td><a href='javascript:void(0);' class='Two' onclick='toPrisonList("
							+ data[i].PDE_CUS_NUMBER +",\" "+ data[i].OBD_ORGA_NAME+ 
							"\");' >"+ data[i].PDE_NORMAL_COUNT+ "</a> <span class='pde_unit'></span></td>");
				} else {
					tr.append("<td><span class='Two'>0</span> <span class='pde_unit'></span></td>");
				}
			} else {
				
				if(data[i].PDE_ABNORMAL_COUNT != null && data[i].PDE_ABNORMAL_COUNT > 0) {
					pde_unit = data[i].PDE_UNIT;
					tr.append("<td><a href='javascript:void(0);' class='spTwo' onclick='toPrisonList("
							+ data[i].PDE_CUS_NUMBER +",\" "+ data[i].OBD_ORGA_NAME+"\");' >"+ data[i].PDE_ABNORMAL_COUNT+ "</a> <span class='pde_unit'></span></td>");
				} else {
					tr.append("<td><span class='spTwo'>0</span> <span class='pde_unit'></span></td>");
				}
			}
			
			table.append(tr);
		}
		
		table.find(".pde_unit").html(pde_unit);
	}
	
	/* 显示监狱信息页面 */
    function toPrisonList(prisonId,prisonName) {
    	
    	var panel = $("#layout1").layout("panel", "east");
    	prisonName= encodeURIComponent(encodeURIComponent(prisonName));
    	panel.panel("refresh", "${ctx}/xxhj/wfsb/wfsb?prisonId=" + prisonId + "&prisonName=" + prisonName);
    }
</script>

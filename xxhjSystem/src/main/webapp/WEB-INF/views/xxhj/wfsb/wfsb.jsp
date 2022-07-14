<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<style>
	.rightTable {
		text-align: center;
		font-size: 13px;
		border-collapse: collapse;
		border: solid #4592f0;
		border-width: 1px;
		margin-top: 10px;
		margin-left: 5px;
	}
	.rightTable td {
		border: solid #4592f0;
		border-width: 1px;
		height: 30px;
		text-align:center;
	}
</style>
	<div id="rightSide_Pwfsb">
		<div class="rightDivStyle right-zb" id="prisonDeviceCount" >
			<h4 align="right">当前监内物防设备统计</h4>
			<h5 align="right"><a href="javascript:void(0);" id="callBack" onclick="callBack();" title="返回">返回>></a></h5>
				<div class="info_list wfsb">
				<dl>
					<dd>设备名称</dd>
					<dd>单位</dd>
					<dd>正常</dd>
					<dd>故障</dd>
				</dl>
				<div class="maquee wfsb-list" id="maquee">
					 <ul class="police-box">
						<!-- <li>
							<div>6507100</div>
							<div>中心信息</div>
							<div>张三</div>
							<div>查看</div>
						</li>
						<li>
							<div>6507100</div>
							<div>中心信息</div>
							<div>张三</div>
							<div>查看</div>
						</li> -->
					</ul> 
				</div>
			</div>
		</div>
	</div>

<script type="text/javascript">

	var USER_LEVEL = jsConst.USER_LEVEL; 
	var thisCusNumber = jsConst.CUS_NUMBER;  
	var prisonId = '${prisonId}';          //省局传来监狱编号
	var prisonName = '${prisonName}';      //省局传来监狱名称
	var prov = false;
	
	$.parseDone(function() {
		
		$("#maquee").mCustomScrollbar({
			theme:"minimal-light",
			autoExpandScrollbar:true
		});
		
		if(prisonName != null && USER_LEVEL==1) {
			
			$("#callBack").show();
			cusNumber = prisonId ;
			$("#prisonDeviceCount h4").html(prisonName+'物防设备统计')

		} else if (USER_LEVEL != 1) {
			
			cusNumber = thisCusNumber;
			$("#callBack").hide();
			$("#prisonDeviceCount h4").show();
		}
		
		queryDenfenseDeviceCount();
		prov = true;
	});
	
	/**
	 * 查询物防设备统计信息
	 */
	function queryDenfenseDeviceCount() {
		
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
		
		var arryHtml = [];
		arryHtml.push("<ul class = 'police-box'>");
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
				arryHtml.push("<li><div>"+deviceName+"</div>");
				arryHtml.push("<div>"+unit+"</div>");
				arryHtml.push("<div>"+data[i].PDE_NORMAL_COUNT+"</div>");
				arryHtml.push("<div>"+data[i].PDE_ABNORMAL_COUNT+"</div></li>");
			}
		}
		arryHtml.push("</ul>");
		$("#maquee").html(arryHtml.join(""));
	}
	
	function callBack() {
    	
    	var panel = $("#layout1").layout("panel", "east");
    	panel.panel("refresh", "${ctx}/xxhj/wfsb/pwfsb");
    }
</script>

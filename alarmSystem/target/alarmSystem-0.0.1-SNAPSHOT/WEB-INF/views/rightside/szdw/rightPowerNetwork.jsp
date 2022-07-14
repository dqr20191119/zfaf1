<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<link rel="stylesheet" href="${ctx}/static/css/rightSide.css" />
<!-- <style type="text/css">
.model_div{
	height:100%;
}
#topNetWork{
	margin-top: 4px;
	height:364px;
}
#centerNetWork{
	height:364px;
}
#netWork{
	margin-top: 4px;
	height:90%;
}
#netWork table{
	width: 250px;
	border-top: 1px solid #ccc;
}
#netWork table tr{
	height: 30px;
}
#netWork table tr td{
	padding-left: 15px;
	font-size: 15px;
}
#netWork table .name{
	font-weight: bold;
}
#netWork table tr td span{
	font-size: 17px;
	color: #ffb76e;
}
#bottomNetWork{
	height:　45px;
}
#bottomNetWork div{
 	width: 100%; 
 	height: 100%; 
	margin-top: 14px;
}
.rightTable td{
	height: 34px;
}
.netWork_btn{
	border: none;
	background-color: #ff6c00;
	width: 80px;
	height: 35px;
	font-size: 16px;
	font-family: "微软雅黑";
	color: #fff;
	cursor: pointer;
}
.overflow_div{
	width: 100%;
	height: 90%;
	margin-top: 25px;
	overflow: auto;
}
#prisonList{
	margin-top: 4px;
	height: 90%;
}
#prisonList .overflow_div{
	margin-top: 15px;
	height: 90%;
}
#prisonList .overflow_div ul{
	margin-top: 0px;
}
#prisonList .overflow_div ul li{
	text-align:center;
	margin-right:25px;
	height: 28px;
}
</style> -->
<!-- <div class="scorllBar">
	<div class="rightDivStyle right-zb hideStyle model-mainten" id="prisonList">
		<h4>各监所数字电网</h4>
		<div class="model_electron coral-noscroll mscroll" style="width:100%;height:100%;">
			<div class="model_box">
				<ul></ul>
			</div>
		</div>	
	</div>
	<div class="rightDivStyle right-zb hideStyle model-mainten" id="netWork">
		<h4>数字电网信息<a id="callBack" href="javascript:callBack();" class="modelUrl">返回</a></h4>
		<div class="model_electron coral-noscroll mscroll" style="width:100%;height:100%;">
			<div class="model_box"></div>
		</div>
	</div>
</div> -->


<link rel="stylesheet" type="text/css" href="/prison/static/module/video/css/rightVideoPlan.css">
<div id="prisonList" class="cameraGroup hideStyle" style="height:96%;width:98%">
	<cui:accordion  heightStyle="fill" icons="accordionIcon">
	<h3>各监所数字电网</h3>
	<div class="menuDiv" >
		<div class="panel" style="float: right;width: 100%; height: 100%">
			<div class="tree-box">
				<div style="width:100%;height:100%;" class="mscroll">
					<div class="model_box">
						<ul></ul>
					</div>
				</div>
			</div>
		</div>
	</div>
	</cui:accordion>
</div>
<div id="netWork" class="cameraGroup hideStyle" style="height:96%;width:98%">
	<cui:accordion  heightStyle="fill" icons="accordionIcon">
	<h3>数字电网信息<a id="callBack" href="javascript:void(0);" onclick='callBack()' class="modelUrl">返回</a></h3>
	<div class="menuDiv" >
		<div class="panel" style="float: right;width: 100%; height: 100%">
			<div class="tree-box">
				<div style="width:100%;height:100%;" class="mscroll">
					<div class="model_box"></div>
				</div>
			</div>
		</div>
	</div>
	</cui:accordion>
</div>
<script type="text/javascript">
	var jsConst = window.top.jsConst;
	var thisCusNumber = jsConst.CUS_NUMBER;
	var intervalID = "";
	$(function(){
		debugger
		if(jsConst.USER_LEVEL==1){
			loadProvMethod();
		}else if(jsConst.USER_LEVEL==2){
			loadPrisMethod();
		}else if(jsConst.USER_LEVEL==3){
			loadAreaMethod();
		}
		
		$(".mscroll").mCustomScrollbar({
			theme:"minimal-dark",
			autoExpandScrollbar:true,
			mouseWheelPixels:130//鼠标滚动一下滑动多少像素
		});   
		
	});
	
	/**
	 * 加载省局用户所展示的模块
	 */
	function loadProvMethod(){
		debugger;
		$(".hideStyle").hide();
		$("#prisonList").show();
		loadPrisonList();
	}
	/**
	 * 加载监狱用户所展示的模块
	 */
	function loadPrisMethod(cusNumber,cusName){
		$("#callBack").hide();
		if(cusNumber != null){
			thisCusNumber = cusNumber;
			$("#topNetWork p").html(cusName);
			$("#callBack").show();
		}
		$(".hideStyle").hide();
		$("#netWork").show();
		$("#bottomNetWork").show();
		queryNetWorkCount();
		//timer = setInterval(queryNetWorkCount, 3000);
		showMapData();
	}
	/**
	 * 加载监区用户所展示的模块
	 */
	function loadAreaMethod(){
		$("#callBack").hide();
		if(cusNumber != null){
			thisCusNumber = cusNumber;
			$("#callBack").show();
		}
		$(".hideStyle").hide();
		$("#netWork").show();
		$("#bottomNetWork").show();
		queryNetWorkCount();
		//timer = setInterval(queryNetWorkCount, 3000);
		showMapData();
	}   
	/**
	 * 返回全省监狱列表模块
	 */
	function callBack(){
		clearInterval(timer);
		thisCusNumber = "";
		$("#callBack").hide();
		$(".hideStyle").hide();
		$("#prisonList").show();
	}
	/**
	 * 加载全省监狱列表
	 */
	function loadPrisonList(){
		$.ajax({
			type : 'post',
			url : jsConst.basePath+"//common/authsystem/findAllJyForCombobox.json",
			dataType : 'json',
			success : function(data) {
				if(data.exception==undefined){
					console.log(data);
					
					var ul = $("#prisonList ul");
					for(var i=0;i<data.length;i++){
						ul.append("<li><a href='javascript:void(0);' onclick='loadPrisMethod(" + data[i].value + ",\"" + data[i].text + "\");'>" + data[i].text + "</a></li>");
					}
				}else{
					$.message( {
						iframePanel:true,
				        message:data.exception.cause.message,
				        type:"danger"
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
	/**
	 * 查询数字电网统计数据findPowerNetworkInfo
	 */
	function queryNetWorkCount(){
		$.ajax({
			type : 'post',
			url : jsConst.basePath+"/jfsb/powerNetwork/findPowerNetworkInfo.json?pnbCusNumber="+thisCusNumber,
			dataType : 'json',
			success : function(data) {
				if(data.exception==undefined){
					console.log(data);
					parseNetWorkCount(data);
				}else{
					$.message( {
						iframePanel:true,
				        message:data.exception.cause.message,
				        type:"danger"
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
	/**
	 * 查询数字电网统计数据
	 */
	function queryNetWorkCount_bak(){
		var options = {
			 dataType : 1,
			 sqlId : "cds_power_network_info",
			 wid : "0",
			 oid : "0",
			 data : [thisCusNumber],
			 success : function(json){
				 if(json.success){
					 var data = json.obj.data;
					 parseNetWorkCount(data);
				}
			}
		}
		window.top.queryCtrl(options);
		/*var url = window.top.jsConst.basePath + "powernetworkCtrl/powerNetworkForList";
		var args = {"args":JSON.stringify({
			 sqlid : "dvc_queryPowerNetworkInfo",
			 whereId : "0",
			 orderId : "0",
			 parasList : [thisCusNumber]
		})};
		window.top.ajaxTodo(url, args, function(json){
			if (json.success){
				parseNetWorkCount(json.obj.data);
			}
		});*/
	}

	/**
	 * 解析数字电网统计数据
	 */
	var timer = null;
	function parseNetWorkCount(data){
		debugger;
		//alert(JSON.stringify(data));
		var netWorkDiv = $("#netWork .model_box");
		netWorkDiv.empty();
		for(var i=0;i<data.length;i++){
			var brand = data[i].PNB_BRAND;
			var name = data[i].PNB_NAME;
			var sourceVoltage = null;
			var sttsIndc = data[i].PNB_STTS_INDC;
			sttsIndc = (sttsIndc == ""||sttsIndc == undefined) ? "正常" : sttsIndc;
			sourceVoltage = sttsIndc == "正常" && data[i].PNB_POWER_SOURCE_VOLTAGE ? data[i].PNB_POWER_SOURCE_VOLTAGE : 0;
			var sourceFlow = sttsIndc == "正常" && data[i].PNB_POWER_SOURCE_VOLTAGE ? data[i].PNB_POWER_SOURCE_FLOW : 0;	
			var table = $("<table></table>");
			if(i > 0){
				table = $("<table style='margin-top: 20px;'></table>");
			}
			if(brand == 2){
				var tr = $("<tr></tr>");
				tr.append("<td align='center' colspan='2' class='name'>" + name + "</td>");
				table.append(tr);
				tr = $("<tr></tr>");
				tr.append("<td width='150'>电源电压(V)</td>");
				tr.append("<td width='130'><span>" + sourceVoltage + "</span></td>");
				table.append(tr);
				tr = $("<tr></tr>");
				tr.append("<td>电源电流(mA)</td>");
				tr.append("<td><span>" + sourceFlow + "</span></td>");
				table.append(tr);
			}else{
				var aBoxVoltage = sttsIndc == "正常" ? (data[i].PNB_A_BOX_VOLTAGE).toFixed(2) : 0;
				var bBoxVoltage = sttsIndc == "正常" ? data[i].PNB_B_BOX_VOLTAGE : 0;			
				if(data[i].PNB_A_BOX_POWER_FLOW==0){
					var aBoxPowerFlow = sttsIndc == "正常" ? data[i].PNB_A_BOX_POWER_FLOW : 0;
				}else{
					var aBoxPowerFlow = sttsIndc == "正常" ? (data[i].PNB_A_BOX_POWER_FLOW).toFixed(3) : 0;
				}					
				var bBoxPowerFlow = sttsIndc == "正常" ? data[i].PNB_B_BOX_POWER_FLOW : 0;
				var tr = $("<tr></tr>");
				tr.append("<td align='center' colspan='2' class='name'>" + name + "</td>");
				table.append(tr);
				tr = $("<tr></tr>");
				tr.append("<td width='130'>A 相高压(KV)</td>");
				tr.append("<td width='130'><span>" + aBoxVoltage + "</span></td>");
				table.append(tr);
				tr = $("<tr></tr>");
				tr.append("<td>A 相电流(mA)</td>");
				tr.append("<td><span>" + aBoxPowerFlow + "</span></td>");
				table.append(tr);
				tr = $("<tr></tr>");
				tr.append("<td>B 相高压(KV)</td>");
				tr.append("<td><span>" + bBoxVoltage + "</span></td>");
				table.append(tr);
				tr = $("<tr></tr>");
				tr.append("<td>B 相电流(mA)</td>");
				tr.append("<td><span>" + bBoxPowerFlow + "</span></td>");
				table.append(tr);
				tr = $("<tr></tr>");
				tr.append("<td>电源电压(V)</td>");
				tr.append("<td><span>" + sourceVoltage + "</span></td>");
				table.append(tr);
				tr = $("<tr></tr>");
				tr.append("<td>电源电流(mA)</td>");
				tr.append("<td><span>" + sourceFlow + "</span></td>");
				table.append(tr);
			}
			tr = $("<tr></tr>");
			tr.append("<td>电网状态</td>");
			if(sttsIndc == "正常"){
				tr.append("<td><span style='color: #00FF00;font-size: 15px;'>" + sttsIndc + "</span></td>");
			}else{
				tr.append("<td><span style='color: #FF0000;font-size: 15px;'>" + sttsIndc + "</span></td>");
			}
			table.append(tr);
			netWorkDiv.append(table);
		}
		/*var topNetWorkTab = $("#topNetWorkTab");
		topNetWorkTab.empty();
		topNetWorkTab.append("<tr><td width='109'></td><td width='80'>东</td><td width='80'>南</td></tr>");
		for(var i=0;i<array.length;i++){
			topNetWorkTab.append("<tr><td>" + window.top.getValue(array[i].name) + "</td>"
							   + "<td>" + window.top.getValue(array[i].east) + "</td>"
							   + "<td>" + window.top.getValue(array[i].south) + "</td>"
							   + "</tr>");
		}
		var centerNetWorkTab = $("#centerNetWorkTab");
		centerNetWorkTab.empty();
		centerNetWorkTab.append("<tr><td width='109'></td><td width='80'>西</td><td width='80'>北</td></tr>");
		for(var i=0;i<array.length;i++){
			centerNetWorkTab.append("<tr><td>" + window.top.getValue(array[i].name) + "</td><td>" + window.top.getValue(array[i].west) + "</td><td>" + window.top.getValue(array[i].north) + "</td></tr>");
		}
		setTimeout(queryNetWorkCount, 3000);*/
	}
	/**
	 * 3D地图上显示数字电网情况
	 */
	function showMapData(){
		return;
		
		var labelType = 10;

		mapModel = window.top.jsConst.MAP_MODEL;

		if( mapModel ){
			map3d = window.top.jsMap['map3d'];
			map3d.hideAreaLabel();
			window.top.winMap["mapModelBtn"].toggleModel("3D");
			window.top.queryCtrl({
				sqlId: "cds_query_lable_base",
				wid: 0,
				data: [thisCusNumber, labelType],
				callback: function(data){
					if( data.success ){  
						var list = data.obj.data;
						var i = 0;
						while( list.length > 0 ){
							var stts = queryPowerNetworkStts(list[i].LBD_DEVICE_IDNTY);
							stts = stts.split(":");
							list[i].LBD_LABEL_NAME = list[i].LBD_LABEL_NAME + stts[0];
							list[i].LBD_FONT_COLOR = stts[1] == 1 ? "#FF0000" : list[i].LBD_FONT_COLOR;
							list[i].LBD_FONT_BKCOLOR = stts[1] == 1 ? "#FF0000" : list[i].LBD_FONT_BKCOLOR;
							map3d.addLabel( list.shift());
						}
					}
				}
			});
		}
	}
	/**
	 * 查询数字电网状态
	 */
	function queryPowerNetworkStts(pnb_idnty){
		var stts = 0;
		var options = {
			 dataType : 1,
			 sync : false,
			 sqlId : "dvc_queryPowerNetworkInfo",
			 wid : "1",
			 data : [thisCusNumber, pnb_idnty],
			 success : function(json){
				 if(json.success){
					 var data = json.obj.data;
					 if(data != "" && data != null)
						 stts = data[0].PNB_STTS_INDC_CH + ":" + data[0].PNB_STTS_INDC;
				}
			}
		}
		queryCtrl(options);
		console.log(stts);
		return stts;
	}
	function parseNetWorkCount1(data){
		debugger;
		//alert(JSON.stringify(data));
		var netWorkDiv = $("#netWork .model_box");
		netWorkDiv.empty();
		var sttsIndc = "正常";
		// var bBoxPowerFlow = sttsIndc == "正常" ? data[i].PNB_B_BOX_POWER_FLOW/10 : 0;
		var table = $("<table></table>");
		var tr = $("<tr></tr>");
		tr.append("<td align='center' colspan='2' class='name'>周界电网</td>");
		table.append(tr);
		// 1段
		tr = $("<tr></tr>");
		tr.append("<td width='130'>1段 高压电压(KV)</td>");
		tr.append("<td width='130'><span>7.1</span></td>");
		table.append(tr);
		tr = $("<tr></tr>");
		tr.append("<td width='130'>1段 低压电压(KV)</td>");
		tr.append("<td width='130'><span>3.5</span></td>");
		table.append(tr);
		tr = $("<tr></tr>");
		tr.append("<td>1段 高压电流(mA)</td>");
		tr.append("<td><span>0</span></td>");
		table.append(tr);
		tr = $("<tr></tr>");
		tr.append("<td>1段 低压电流(mA)</td>");
		tr.append("<td><span>0</span></td>");
		table.append(tr);
		// 2段
		tr = $("<tr></tr>");
		tr.append("<td width='130'>2段 高压电压(KV)</td>");
		tr.append("<td width='130'><span>7.1</span></td>");
		table.append(tr);
		tr = $("<tr></tr>");
		tr.append("<td width='130'>2段 低压电压(KV)</td>");
		tr.append("<td width='130'><span>3.5</span></td>");
		table.append(tr);
		tr = $("<tr></tr>");
		tr.append("<td>2段 高压电流(mA)</td>");
		tr.append("<td><span>0</span></td>");
		table.append(tr);
		tr = $("<tr></tr>");
		tr.append("<td>2段 低压电流(mA)</td>");
		tr.append("<td><span>0</span></td>");
		table.append(tr);
		// 3段
		tr = $("<tr></tr>");
		tr.append("<td width='130'>3段 高压电压(KV)</td>");
		tr.append("<td width='130'><span>6.6</span></td>");
		table.append(tr);
		tr = $("<tr></tr>");
		tr.append("<td width='130'>3段 低压电压(KV)</td>");
		tr.append("<td width='130'><span>3.5</span></td>");
		table.append(tr);
		tr = $("<tr></tr>");
		tr.append("<td>3段 高压电流(mA)</td>");
		tr.append("<td><span>0</span></td>");
		table.append(tr);
		tr = $("<tr></tr>");
		tr.append("<td>3段 低压电流(mA)</td>");
		tr.append("<td><span>0</span></td>");
		table.append(tr);
		// 4段
		tr = $("<tr></tr>");
		tr.append("<td width='130'>4段 高压电压(KV)</td>");
		tr.append("<td width='130'><span>6.6</span></td>");
		table.append(tr);
		tr = $("<tr></tr>");
		tr.append("<td width='130'>4段 低压电压(KV)</td>");
		tr.append("<td width='130'><span>3.5</span></td>");
		table.append(tr);
		tr = $("<tr></tr>");
		tr.append("<td>4段 高压电流(mA)</td>");
		tr.append("<td><span>0</span></td>");
		table.append(tr);
		tr = $("<tr></tr>");
		tr.append("<td>4段 低压电流(mA)</td>");
		tr.append("<td><span>0</span></td>");
		table.append(tr);
		// 状态
		tr = $("<tr></tr>");
		tr.append("<td>电网状态</td>");
		if(sttsIndc == "正常"){
			tr.append("<td><span style='color: #00FF00;font-size: 15px;'>" + sttsIndc + "</span></td>");
		}else{
			tr.append("<td><span style='color: #FF0000;font-size: 15px;'>" + sttsIndc + "</span></td>");
		}
		table.append(tr);
		netWorkDiv.append(table);
	}
</script>
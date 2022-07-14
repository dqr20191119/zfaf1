<%@page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!-- 自定义查询开始 -->
<style>
#divId_existJS table {
	font-size:13px;
	border-bottom-width: 1px;
	border-bottom-style: solid;
	border-bottom-color: #F5FAFA;
	width: 100%;
	height: 100%;
}

#divId_existJS table tr:nth-child(even) {
	text-align: center;
	background: #FFF
}

#divId_existJS table tr:nth-child(odd) {
	text-align: center;
	background: #F5FAFA
}

#subTd table {
	font-size:13px;
	border-bottom-width: 1px;
	border-bottom-style: solid;
	border-bottom-color: #F5FAFA;
	width: 100%;
	height: 100%;
}

#subTd table tr{
	text-align: center;
	background: #F5FAFA
}
#subTr table tr{
	text-align: center;
	background: #F5FAFA
}

</style>

<div style="width: 100%; height: 100%;">
	<div style="width: 100%; height: 100%;">
		<cui:form id="formId_jswh_add">
			<div style="width: 40%; float:left; height: 700px;padding:5px;">
				<fieldset>
				<legend>已有监舍 </legend>
					<table class="table" style="margin-bottom:10px;">
						<tr>
							<td style="font-size:15px;">请选择区域:</td>
							<td><cui:combotree id="jswh_regionTree" name="abdAreaId"  componentCls="form-control" url="${ctx}" simpleDataEnable="true" simpleDataIdKey="id" simpleDataPIdKey="pid" keyName="name" allowPushParent = "false" required="true" onChange="search"></cui:combotree>
						</tr>
					</table>
				<div id="divId_existJS" style ="height: 560px;overflow :auto;">
				</div>
				</fieldset>
		 	</div>
		 	<div style="height:700px; float:right; width:60%; padding:5px">
				<fieldset>
				<legend>新增 </legend>
				<table class="table" style="margin-left:30px;">
					<tr>
						<td style="font-size:15px;">批量增加:</td>
						<td style="font-size:15px;"><cui:input id="cpjAddCount" name ="cpjAddCount" componentCls="form-control" placeholder="请输入添加个数" width="180" validType = "naturalnumber"  required="true" onChange="ensure"></cui:input></td>
						<td style="font-size:13px;color: #4692f0">（建议一次添加量不超过30）</td>
					</tr>
				</table>
				<div id="addJSList" style="margin:10px 0px 0px 10px;padding:-5px;height: 560px;overflow :auto;">
					<table>
						<tr id="subTr">
							<td id="subTd">
							</td>
						</tr>
					</table>
				</div>
				</fieldset>
		 	</div>
	 	</cui:form>
	</div>
</div>
<script>
	var jsConst = window.top.jsConst;
	var cusNumber = jsConst.ORG_CODE; //监狱编号
	var userId = jsConst.USER_ID; //登录人

	$.parseDone(function() {
		$("#jswh_regionTree").combotree("tree").tree( "reload", "${ctx}/common/areadevice/findForCombotree.json?cusNumber=" + cusNumber + "&deviceType=0");
	});
	
	
	function search() {
		
		var regionId = $("#jswh_regionTree").combotree("getValue");
		var url = "${ctx}/xxhj/jswh/searchAllData.json";
		$("#subTd").empty();
		$("#cpjAddCount").textbox("setText","");
		$("#divId_existJS").empty();
		$("#divId_existJS").loading({ text : "正在加载中，请稍后..."});
		
		$.ajax({
			type : "post",
			data : {
				"cpjLch" : regionId,
			},
			url : url,
			dataType : "json",
			success : function(data) {
				
				$("#divId_existJS").loading("hide");
				creatTable(data, $("#divId_existJS"));
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$.messageQueue({
					message : textStatus,
					cls : "warning",
					iframePanel : true,
					type : "info"
				});
			}
		});
	}

	function ensure() {
		
		$("#addJSList table").loading({ text : "正在加载中，请稍后..."});
		$("#subTd").empty();
		var addCount = $("#cpjAddCount").val();
		var cameraList = new Array();
		
		if(addCount) {
			
			var tab = $("<table class='table'></table>");
			$("#subTd").append(tab);
			var item = null;
			
			for(var i = 0; i < addCount; i++) {
				
				if(i == 0) {
					item =  "<table><tr><td style='padding-bottom:10px;font-size:15px;font-weight:bold;'><span>监舍名称</span></td><td style='padding-bottom:10px;font-size:15px;font-weight:bold;'><span>主摄像头</span></td><td style='padding-bottom:10px;font-size:15px;font-weight:bold;'><span>副摄像头</span></td></tr>"+
					"<tr><td style='padding-right:10px'><input class='coralui-textbox' id='jsTab_"+(i+1)+"'  data-options='\"width\":100,\"required\": true'/></td>"+
					"<td style='padding-right:10px'><input class='coralui-combobox' id='cameraTab_"+(i+1)+"'  data-options='\"width\":200,\"required\": true,\"enablePinyin\": true,\"readonlyInput\":false'/></td>"+
					"<td style='padding-right:10px'><input class='coralui-combobox' id='fCameraTab_"+(i+1)+"'  data-options='\"multiple\":true,\"width\":200,\"enablePinyin\": true,\"readonlyInput\":false'/></td></tr></table>"
				} else {
					item = "<table><tr><td style='padding-right:10px'><input class='coralui-textbox' id='jsTab_"+(i+1)+"'  data-options='\"onChange\":\"onChange\",\"width\":100'/></td>"+
					"<td style='padding-right:10px'><input class='coralui-combobox' id='cameraTab_"+(i+1)+"'  data-options='\"onChange\":\"onChange\",\"width\":200,\"enablePinyin\": true,\"readonlyInput\":false'/></td>"+
					"<td style='padding-right:10px'><input class='coralui-combobox' id='fCameraTab_"+(i+1)+"'  data-options='\"onChange\":\"onChange\",\"multiple\":true,\"width\":200,\"enablePinyin\": true,\"readonlyInput\":false'/></td></tr></table>";
				}
				
				tab.append("<tr><td>" +item+ "</td></tr>");
				$.parser.parse(tab);
				cameraList.push({id:"cameraTab_"+(i+1),value:""},{id:"fCameraTab_"+(i+1),value:""})
			}
			$("#addJSList").loading("hide");
			$("#addJSList").mCustomScrollbar({
				theme : "minimal-light",
				autoExpandScrollbar : true
			});
			$("#subTd").show();
			setCameraSelect(addCount,cameraList);
		}
	}
	
	/**
	  * 查询并加载摄像头信息
	  */
	 function setCameraSelect(addCount,cameraList) {
		
		var areaId = $("#jswh_regionTree").combotree("getValue");
		var dataList;
		var url = "${ctx}/common/areadevice/findDeviceList.json?deviceType=1&cusNumber=" + cusNumber+ "&areaId=" + areaId;
		$.ajax({
			type : "post",
			url : url,
			dataType : "json",
			success : function(data) {
				$("#addJSList table").loading("hide");
				dataList = data;
				if(data.length > 0){
					for(var i = 0; i < cameraList.length; i++) {
				 		var id = cameraList[i].id;
				 		$("#" + id).combobox( "reload", dataList);
				 	}
				}
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$.messageQueue({
					message : textStatus,
					cls : "warning",
					iframePanel : true,
					type : "info"
				});
			}
		});
	}
	
	function save() {
		
		var validFlag = $("#formId_jswh_add").form("valid");
		if(!validFlag) {
			return;
		}
		
		var list = new Array();
		var LCName = $("#jswh_regionTree").combotree("getText");        //楼层名称
		var addCount = parseInt($("#cpjAddCount").val());
		var nowData = [];
		var existData = [];
		for (var key in existList) {
			existData.push(existList[key]);
		}
		var lc = $("#jswh_regionTree").combotree("getText");
		var lch =$("#jswh_regionTree").combotree("getValue");
		nowData.push(
				{key : lch, value : lc},{key : "id", value : ""});
		
		for(var i = 0; i < addCount; i++) { 
			
			var addData = [];
			var jsTab = $("#jsTab_"+(i+1)).val();
			var cameraId = $("#cameraTab_"+(i+1)).combobox("getValue");
			var cameraName = $("#cameraTab_"+(i+1)).combobox("getText");
			var fCameraId = $("#fCameraTab_"+(i+1)).combobox("getValues");
			var fCameraName = $("#fCameraTab_"+(i+1)).combobox("getText");
			if (jsTab){
				existData.push(jsTab);
				addData.push(
						{key : "jsTab", value : jsTab},
						{key : cameraName, value : cameraId},
						{key : fCameraName, value : fCameraId});
				nowData.push({key : i, value : addData});
			}
		}  
		list.push(nowData);
		list.push("");
		var flag = list;
		var nary = existData.sort();
		for(var i = 0; i < nary.length; i++) {
			
			if (nary[i] == nary[i+1]) {
				flag = false;
				$.message({message:"注意！监舍名称不可重复！", cls:"waring"});
				break;
			} 
		}
		return flag;
	}
	
	var existList;
	function creatTable(list, $div) {
		
		var $div_1 = $("<div style='height: 100%;'></div>")
		var $tableTemp = $("<table class='table'></table>");
			existList = [];
			
		if (list.length > 0) {
			var $trTemp = "<tr style='font-size:15px;font-weight:bold;background-color:#EDEDED'><td><span >监舍名称</span></td><tr>";
			for (var i = 0; i < list.length; i++) {
				
				$trTemp = $trTemp + "<tr><td><span>" + list[i].cpjJs + "</span></td></tr>";
				existList.push(list[i].cpjJs);
			}
			$tableTemp.append($trTemp);
		} else{
			var $trTemp = $("<tr style='font-size:15px;font-weight:bold;background-color:#EDEDED'><td><span>该区域暂无监舍信息 </span></td></tr>");
			$tableTemp.append($trTemp);
		}
		$div_1.append($tableTemp);
		$div.append($div_1);

		$div_1.mCustomScrollbar({
			theme : "minimal-light",
			autoExpandScrollbar : true
		});
	}
	
	function onChange(e, ui) {
		
		var i =	(e.target.id).split("_")[1];
		var js = $("#jsTab_"+i).textbox('getValue');
		var cameraTab = $("#cameraTab_"+i).combobox('getValue');
		var fCameraTab = $("#fCameraTab_"+i).combobox('getValue');
		
		if(!js && !cameraTab && !fCameraTab) {
			$("#jsTab_"+i).textbox({
				required : false
			});
			$("#cameraTab_"+i).combobox("option","required",false);
			$("#fCameraTab_"+i).combobox("option","required",false);
		} else {
			$("#jsTab_"+i).textbox({
				required : true
			});
			$("#cameraTab_"+i).combobox("option","required",true);
		} 
	}
</script>
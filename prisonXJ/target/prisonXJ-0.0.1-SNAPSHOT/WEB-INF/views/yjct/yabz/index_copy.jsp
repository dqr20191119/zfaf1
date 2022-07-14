<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<body style="height: 100%">
	<cui:form id="formId_yabz_query">
		<table class="table">			
			<tr>
				<td>预案名称：</td>
				<td><cui:input name="epiPlanName"></cui:input></td>
				<td>预案类别：</td>
				<td><cui:combobox name="epiPlanType" data="combobox_yjct_planType"></cui:combobox></td>
				<td>状态：</td>
				<td><cui:combobox name="epiPlanStatus" data="combobox_yjct_planStatus"></cui:combobox></td>
				<td>
					<cui:button label="查询" componentCls="coral-btn-blue" onClick="search"/>
					<cui:button label="重置" onClick="reset"></cui:button>
				</td>
			</tr>
		</table>
	</cui:form>
	
	<div style="height: 490px; margin: 0px 10px;">
		<cui:toolbar id="toolbarId_yabz" data="toolbar_yabz"></cui:toolbar>			
		<cui:grid id="gridId_yabz" rownumbers="true" multiselect="true" width="auto" fitStyle="fill" 
			url="${ctx}/yjct/yabz/searchData"
			sortname="epiCrteTime, desc" rowNum="10">
			<cui:gridCols>
				<cui:gridCol name="id" hidden="true">id</cui:gridCol>
				<cui:gridCol name="epiPlanName" width="100">预案名称</cui:gridCol>
				<cui:gridCol name="epiPlanType" width="100" formatter="formatPlanType">预案类别</cui:gridCol>
				<cui:gridCol name="id" width="100">工作组</cui:gridCol>
				<cui:gridCol name="id" width="100">处置流程</cui:gridCol>				 
				<cui:gridCol name="epiPlanStatus" width="100" align="center" formatter="formatPlanStatus">状态</cui:gridCol>
			</cui:gridCols>
			<cui:gridPager gridId="gridId_yabz" />
		</cui:grid>
	</div>
	
	<cui:dialog id="dialogId_yabz" autoOpen="false" resizable="false" reLoadOnOpen="true" modal="true">
	</cui:dialog>
</body>

<script>
	
	var gzzDatas = [];
	var gzzLeftDatas = [];
	var startIndex = 0;
	var endIndex = 4;
	
	var czlcDatas = [];
	var czlcLeftDatas = [];
	var czlcStartIndex = 0;
	var czlcEndIndex = 4;
	
	var czlcGzzDatas = [];
	var czlcGzzCheckDatas = [];
	
	var czlcExpertDatas = [];
	var czlcExpertCheckDatas = [];
	
	var pos;
	var lpos;
	
	var updateFlag = false;
	
	var toolbar_yabz = [{
		"type" : "button",
		"id" : "btnId_add",
		"label" : "新增",
		"onClick" : "openDailog",
		"componentCls" : "btn-primary"
	}, {
		"type" : "button",
		"id" : "btnId_edit",
		"label" : "修改",
		"onClick" : "openDailog",
		"componentCls" : "btn-primary"
	}, {
		"type" : "button",
		"id" : "btnId_remove",
		"label" : "删除",
		"onClick" : "deleteByIds",
		"componentCls" : "btn-primary"
	}];

	$.parseDone(function() {
		
	});
	
	function openDailog(event, ui) {
		 
		var url;		 
		if(ui.id == "btnId_add") {
			
			url = "${ctx}/yjct/yabz/toEdit";
		} else if(ui.id == "btnId_edit") {	
			
			var selarrrow = $("#gridId_yabz").grid("option", "selarrrow");			
			if(selarrrow && selarrrow.length == 1) {
				url = "${ctx}/yjct/yabz/toEdit?id=" + selarrrow[0];
			} else {
				$.alert("请选择一条记录！");
				return;
			}			
		}

		$("#dialogId_yabz").dialog({
			width : 800,
			height : 600,
			title : ui.label,
			url : url				 
		});
		$("#dialogId_yabz").dialog("open");
	}
	
	function closeDialog() {
		
		$("#dialogId_yabz").dialog("close");
	}
	
	function search() {
		
		var formData = $("#formId_yabz_query").form("formData");
		$("#gridId_yabz").grid("option", "postData", formData);
		$("#gridId_yabz").grid("reload");
	}
	
	function reset() {
		
		$("#formId_yabz_query").form("reset");
	}
		
	function formatPlanType(cellvalue, options, rawObject) {
		
		return convertColVal(combobox_yjct_planType, cellvalue);
	}
	
	function formatPlanStatus(cellvalue, options, rawObject) {
		
		return convertColVal(combobox_yjct_planStatus, cellvalue);
	}
	
	function loadInfo(id) {
		
		$.ajax({
			type : 'post',
			url : '${ctx}/yjct/yabz/getById',
			data: {
				id : id
			},
			dataType : 'json',
			success : function(data) {
				if(data.code == "1") {
					$("#formId_yabz_edit").form("load", data.data);	
					
					if(updateFlag) {
						loadGzzInfo();
						loadGzzlsInfo();
						
						loadCzlcInfo();
						loadCzlclsInfo();
					}
				} else {
					$.alert("获取数据失败！");
				}				
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$.alert("获取数据失败！");
			}
		});
	}
	 
	// 预案基本信息			start
	function saveOrUpdatePlanInfo() {
		
		var formData = $("#formId_yabz_edit").form("formData");
			
		$.ajax({
			type : 'post',
			url : '${ctx}/yjct/yabz/saveOrUpdate',
			data: formData,
			dataType : 'json',
			success : function(data) {
				if(data.code == "1") {
					$.alert("操作成功！");
					$("#gridId_yabz").grid("reload");
					$("#tabsId_yabz_edit").tabs("option", "active", "tabsId_yabz_edit_2");
					$("#formId_yabz_edit").form("load", data.data);	
					
					// 加载工作组
					loadGzzInfo();
				} else {
					$.alert("操作失败！");
				}				
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$.alert("操作失败！");
			}
		});
	}
	// 预案基本信息			end
	
	
	// 预案工作组信息		start
	function loadGzzInfo() {
		
		var data = {};
		data.wgiStatus = "2";
		data.wgiHandleType = "1";
		data.wgiHandleId = $("#inputId_yabz_planId").val();
		
		$.ajax({
			type : 'post',
			url : '${ctx}/yjct/gzzgl/searchAllData',
			data : data,
			dataType : 'json',
			success : function(data) {
				if(data.code == "1") {					
					gzzDatas = data.data;
					initGzzList();
				} else {
					$.alert("获取数据失败！");
				}				
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$.alert("获取数据失败！");
			}
		});
	}
	
	function loadGzzlsInfo() {
		
		var data = {};
		data.epcConfigType = "2";
		data.wgiHandleType = "1";		
		data.wgiHandleId = $("#inputId_yabz_planId").val();
		
		$.ajax({
			type : 'post',
			url : '${ctx}/yjct/gzzls/searchAllData',
			data : data,
			dataType : 'json',
			success : function(data) {
				if(data.code == "1") {					
					gzzLeftDatas = data.data;
					initGzzLeftList();
				} else {
					$.alert("获取数据失败！");
				}				
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$.alert("获取数据失败！");
			}
		});
	}
	
	function loadGzzcyInfo(btn) {
		
		$("#tableId_yabz_gzzright_desc").html("");
		
		var gzzId = $(btn).attr("id");
		var gzzTask = $(btn).attr("name");
		var data = {};
		data.wgmWorkgroupId = gzzId;
		
		$.ajax({
			type : 'post',
			url : '${ctx}/yjct/gzzcy/searchAllData',
			data : data,
			dataType : 'json',
			success : function(data) {
				if(data.code == "1") {	
					
					initGzzcyInfo(gzzTask, data.data);
				} else {
					$.alert("获取数据失败！");
				}				
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$.alert("获取数据失败！");
			}
		});
	}
	
	function moveLeft() {
		
		if(startIndex == 0) {
			return;
		}
		
		startIndex--;
		endIndex--;
		
		initGzzList();
	}
	
	function moveRight() {
		
		if(endIndex >= gzzDatas.length) {
			return;
		}
		
		startIndex++;
		endIndex++;
		
		initGzzList();
	}
	
	function gzzMoveToLeft(btn) {
		
		  var sortNum = $(btn).attr("sort-num");
		  var element = gzzDatas[sortNum];	
		  
		  gzzDatas.splice(sortNum, 1);		  
		  gzzLeftDatas.push(element);
		  		  
		  startIndex = 0;
		  endIndex = 4;
		  initGzzList();
		  initGzzLeftList(); 
	}
	
	function gzzMoveToTop(btn) {

		var flag = false;
	   	var id = $(btn).attr("id");
	 	var sortNum = $(btn).attr('sort-num');
	   	
	 	var element = gzzLeftDatas[sortNum];	 	 
	 	gzzLeftDatas.splice(sortNum, 1); 
	 	initGzzLeftList();
	   	
	 	if(gzzDatas.length == 0) {
	 		gzzDatas.push(element);
	 		startIndex = 0;
			endIndex = 4;
	    	initGzzList();
	    	return;
	 	}
	 	
	    $("#divId_yabz_gzzall input").each(function() {
	   		 var obj = $(this);
	   		 var _id =  obj.attr('id');
	   		 
	   		 if(id * 1 > _id * 1) {
	   			var _sortNum = obj.attr('sort-num');	    			
	   			gzzDatas.splice(_sortNum, 0, element);
	   			startIndex = 0;
	 			endIndex = 4;
	 	    	initGzzList();
	 	    	
	   			flag = true;
	 	    	return false;
	   		 }
	    });
	    
	    if(!flag) {
	    	gzzDatas.push(element);
	    	startIndex = 0;
			endIndex = 4;
	    	initGzzList();
	    }
	}
	
	function initGzzList() {
		
		var gzzTop = $("#divId_yabz_gzzall");
		gzzTop.empty();	
		
		if(endIndex > gzzDatas.length) {
			endIndex = gzzDatas.length;
		}

		for(var i = startIndex; i < endIndex; i++) {
			var timers = [];
			var gzzTd = $("<input class='hid' type='button' />");
			gzzTd.attr({
				id : gzzDatas[i].wgiWorkgroupId,
				name : gzzDatas[i].wgiWorkgroupTask,
				value : gzzDatas[i].wgiWorkgroupName,
				'sort-num' : i
			}).unbind('dblclick').bind('dblclick', function(event) {
				
				event.stopPropagation();
				clearTimeout(timers[i]);
				
				gzzMoveToLeft(this);
				
				$("#divId_yabz_gzzall").find("input").css("color", "white");
				$("#divId_yabz_gzzleft ul").find("li").css("color", "white");
				$("#divId_yabz_gzzleft ul").find("li").last().css("color", "red");
				$("#divId_yabz_gzzleft ul").find("li").last().click();
			}).unbind('click').bind('click', function() {
				
				var btnObj = this;
				clearTimeout(timers[i]);
				timers[i] = setTimeout(function() {
					
					loadGzzcyInfo(btnObj);
					$("#divId_yabz_gzzall").find("input").css("color", "white");
					$("#divId_yabz_gzzleft ul").find("li").css("color", "white");
					$(btnObj).css("color", "red");
			  	}, 300);				
			});
			
			gzzTd.appendTo(gzzTop);
		}
	}
	
	function initGzzLeftList() {
		
	    var gzzLeft = $("#divId_yabz_gzzleft ul");		
	    gzzLeft.empty();
	    
	    for(var i = 0; i < gzzLeftDatas.length; i++) {
	    	var timers = [];
			var gzzLeftTd = $("<li class='selectHid'>" + gzzLeftDatas[i].wgiWorkgroupName + "</li>");
			gzzLeftTd.attr({
				id : gzzLeftDatas[i].wgiWorkgroupId,
				name : gzzLeftDatas[i].wgiWorkgroupTask,
				value : gzzLeftDatas[i].wgiWorkgroupName,
				'sort-num': i,
			}).unbind('dblclick').bind('dblclick', function() {

				event.stopPropagation();
				clearTimeout(timers[i]);
				
				gzzMoveToTop(this);
				
				$("#divId_yabz_gzzall").find("input").css("color", "white");
				$("#divId_yabz_gzzleft ul").find("li").css("color", "white");
				
				var id = $(this).attr("id");
				$("#divId_yabz_gzzall").find("input[id='" + id + "']").css("color", "red");
				$("#divId_yabz_gzzall").find("input[id='" + id + "']").click();
			}).unbind('click').bind('click', function() {
				
				var btnObj = this;
				clearTimeout(timers[i]);
				timers[i] = setTimeout(function() {
					loadGzzcyInfo(btnObj);
					$("#divId_yabz_gzzall").find("input").css("color", "white");
					$("#divId_yabz_gzzleft ul").find("li").css("color", "white");
					$(btnObj).css("color", "red");
				}, 300);
			});
			
			gzzLeftTd.appendTo(gzzLeft);
		}	
 	}
	
	function initGzzcyInfo(gzzTask, gzzcyDatas) {
		
		var headerTr = "<tr>" + 
				"<td width='90' style='background: #aeeafd' nowrap='nowrap'></td>" + 
		   		"<td width='90' class='tabhead' nowrap='nowrap'>姓名</td>" +
			   	"<td width='110' class='tabhead' nowrap='nowrap'>通联号码</td>" + 
			   	"<td width='330' class='tabhead' align='center' nowrap='nowrap'>任务分工</td>" + 
			   	"</tr>" ;
			   	
		var tailTr =  "<tr><td class='tabhead' nowrap='nowrap'>组任务</td>" + 
	       "<td colspan='3' class='tabContent'>" + gzzTask + "</td></tr>" ;
	       
	   	var zzTr = "";
	   	var fzzTr = "";
	   	var cyTr = "";
	   	var zzPos = 1;
	   	var fzzPos = 1;
	   	var cyPos = 1;
	   	for(var i = 0; i < gzzcyDatas.length; i++) {
	   		var gzzcy = gzzcyDatas[i];
	   		var role = gzzcy.wgmUserRole;

	   		if(role == "1") {
	   			
	   			zzTr += "<tr>"; 
	   			if(zzPos == 1) {	   				
	   				zzTr += "<td id='tableId_yabz_gzzright_desc_1' class='tabhead' nowrap='nowrap' rowspan='" + zzPos + "'>组长</td>"; 
	   			}
				
	   			zzTr += "<td class='tabContent' nowrap='nowrap'>" + gzzcy.wgmPoliceName + "</td>" +
			   	"<td class='tabContent' nowrap='nowrap'>" + gzzcy.pbdPhone + "</td>" + 
			   	"<td class='tabContent' align='center' nowrap='nowrap'>" + gzzcy.wgmRoleTask + "</td>" + 
			   	"</tr>";
	   			zzPos++;
	   		} else if(role == "2") {
	   			
	   			fzzTr += "<tr>"; 
	   			if(fzzPos == 1) {
	   				fzzTr += "<td id='tableId_yabz_gzzright_desc_2' class='tabhead' nowrap='nowrap' rowspan='" + fzzPos + "'>副组长</td>"; 
	   			}
				
	   			fzzTr += "<td class='tabContent' nowrap='nowrap'>" + gzzcy.wgmPoliceName + "</td>" +
			   	"<td class='tabContent' nowrap='nowrap'>" + gzzcy.pbdPhone + "</td>" + 
			   	"<td class='tabContent' align='center' nowrap='nowrap'>" + gzzcy.wgmRoleTask + "</td>" + 
			   	"</tr>";
	   			fzzPos++;	
	   		} else if(role == "3") {
	   			
	   			cyTr += "<tr>"; 
	   			if(cyPos == 1) {
	   				cyTr += "<td id='tableId_yabz_gzzright_desc_3' class='tabhead' nowrap='nowrap' rowspan='" + cyPos + "'>成员</td>"; 
	   			}
				
	   			cyTr += "<td class='tabContent' nowrap='nowrap'>" + gzzcy.wgmPoliceName + "</td>" +
			   	"<td class='tabContent' nowrap='nowrap'>" + gzzcy.pbdPhone + "</td>" + 
			   	"<td class='tabContent' align='center' nowrap='nowrap'>" + gzzcy.wgmRoleTask + "</td>" + 
			   	"</tr>";
	   			cyPos++;
	   		}
	   	}
	   		  
	   	$("#tableId_yabz_gzzright_desc").append(headerTr + zzTr + fzzTr + cyTr + tailTr);	  
	 	$("#tableId_yabz_gzzright_desc_1").attr("rowspan", zzPos - 1);
	   	$("#tableId_yabz_gzzright_desc_2").attr("rowspan", fzzPos - 1);
	   	$("#tableId_yabz_gzzright_desc_3").attr("rowspan", cyPos - 1);
	}
	
	function saveOrUpdateGzzInfo() {
		
		var planId = $("#inputId_yabz_planId").val();
		if(planId == null || planId == "") {
			$.alert("请先填写基本信息！");
			return;
		}
		
		var data = {};
		data.epiPlanId = planId;
	    $('#divId_yabz_gzzleft ul li').each(function(i) {
			var id = $(this).attr("id");
			var name = $(this).attr("name");
			var value = $(this).attr("value");
			data["yapzList[" + i + "].epcPlanId"] = planId;
			data["yapzList[" + i + "].epcConfigType"] = "2";
			data["yapzList[" + i + "].epcConfigItemId"] = id;
			data["yapzList[" + i + "].epcOrderSeq"] = i;			
			data["yapzList[" + i + "].gzzName"] = value;
			data["yapzList[" + i + "].gzzTask"] = name;
	    });
		
		$.ajax({
			type : 'post',
			url : '${ctx}/yjct/yabz/saveOrUpdateGzzInfo',
			data: data,
			dataType : 'json',
			success : function(data) {
				if(data.code == "1") {
					$.alert("操作成功！");
					$("#gridId_yabz").grid("reload");
					$("#tabsId_yabz_edit").tabs("option", "active", "tabsId_yabz_edit_3");
					
					// 加载处置方法
					loadCzlcInfo();
				} else {
					$.alert("操作失败！");
				}				
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$.alert("操作失败！");
			}
		});
	}
	// 预案工作组信息		end
	
	// 预案处置流程信息		start
	function loadCzlcInfo() {
		
		var data = {};
		data.dmiPlanType = $("#comboboxId_yabz_planType").combobox("getValue");
		data.dmiStatus = "2";
		data.dmiHandleType = "1";
		data.dmiHandleId = $("#inputId_yabz_planId").val();
		
		$.ajax({
			type : 'post',
			url : '${ctx}/yjct/czffgl/searchAllData',
			data : data,
			dataType : 'json',
			success : function(data) {
				if(data.code == "1") {					
					czlcDatas = data.data;
					initCzlcList();
				} else {
					$.alert("获取数据失败！");
				}				
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$.alert("获取数据失败！");
			}
		});
	}
	
	function loadCzlclsInfo() {
		
		var data = {};	
		data.epcConfigType = "1";
		data.wgiHandleType = "1";		
		data.wgiHandleId = $("#inputId_yabz_planId").val();
		
		$.ajax({
			type : 'post',
			url : '${ctx}/yjct/czffls/searchAllData',
			data : data,
			dataType : 'json',
			success : function(data) {
				if(data.code == "1") {					
					czlcLeftDatas = data.data;
					initCzlcLeftList();
				} else {
					$.alert("获取数据失败！");
				}				
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$.alert("获取数据失败！");
			}
		});
	}
	
	function loadCzlcInfoDetail(btn) {
		
		var id = $(btn).attr("id");
		var desc = $(btn).attr("name");
		$("#tdId_yabz_methodDesc").html(desc);
	 	$("#inputId_yabz_czlcMethodId").val(id);
	 	
		$.ajax({
			type : 'post',
			url : '${ctx}/yjct/czffczx/searchAllData',
			data: {
				mraMethodId : id
			},
			dataType : 'json',
			success : function(data) {
				if(data.code == "1") {
					
					var actionData = data.data;
					var html = "";
					for(var i = 0; i < actionData.length; i++) {						
						for(var j = 0; j < combobox_yjct_actionType.length; j++) {
							if(actionData[i].mraRelActionType == combobox_yjct_actionType[j].value) {
								html += '<div style="float:left"><a href="javascript:void(0);" class="disDescBtn" onclick="showActionDetail(' + actionData[i].mraRelActionType + ')">' + combobox_yjct_actionType[j].text + '</a></div>';								
							}
						}						
					}
					
					$("#tdId_yabz_relAction").html(html);
				} else {
					$.alert("获取数据失败！");
				}				
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$.alert("获取数据失败！");
			}
		});
	}
	
	function moveLeftCzlc() {
		
		if(czlcStartIndex == 0) {
			return;
		}
		
		czlcStartIndex--;
		czlcEndIndex--;
		
		initCzlcList();
	}
	
	function moveRightCzlc() {
		
		if(czlcEndIndex >= czlcDatas.length) {
			return;
		}
		
		czlcStartIndex++;
		czlcEndIndex++;
		
		initCzlcList();
	}
		
	function czlcMoveToLeft(btn) {
		
		  var sortNum = $(btn).attr("sort-num");
		  var element = czlcDatas[sortNum];	
		  
		  czlcDatas.splice(sortNum, 1);		  
		  czlcLeftDatas.push(element);
		  		  
		  czlcStartIndex = 0;
		  czlcEndIndex = 4;
		  initCzlcList();
		  initCzlcLeftList(); 
	}
	
	function czlcMoveToTop(btn) {

		var flag = false;
	   	var id = $(btn).attr("id");
	 	var sortNum = $(btn).attr('sort-num');
	   	
	 	var element = czlcLeftDatas[sortNum];	 	 
	 	czlcLeftDatas.splice(sortNum, 1); 
	 	initCzlcLeftList();
	   	
	 	if(czlcDatas.length == 0) {
	 		czlcDatas.push(element);
	 		czlcStartIndex = 0;
	 		czlcEndIndex = 4;
 	    	initCzlcList();
 	    	return;
	 	}
	 	
	    $("#divId_yabz_czlcall input").each(function() {
	   		 var obj = $(this);
	   		 var _id =  obj.attr('id');
	   		 
	   		 if(id * 1 > _id * 1) {
	   			var _sortNum = obj.attr('sort-num');	    			
	   			czlcDatas.splice(_sortNum, 0, element);
	   			czlcStartIndex = 0;
	   			czlcEndIndex = 4;
	 	    	initCzlcList();
	 	    	
	   			flag = true;
	 	    	return false;
	   		 }
	    });
	    
	    if(!flag) {
	    	czlcDatas.push(element);
	    	czlcStartIndex = 0;
	    	czlcEndIndex = 4;
 	    	initCzlcList();
	    }
	}
	
	function initCzlcList() {
		
		var czlcTop = $("#divId_yabz_czlcall");
		czlcTop.empty();	
		
		if(czlcEndIndex > czlcDatas.length) {
			czlcEndIndex = czlcDatas.length;
		}

		for(var i = czlcStartIndex; i < czlcEndIndex; i++) {
			var timers = [];
			var czlcTd = $("<input class='hid' type='button' />");
			czlcTd.attr({
				id : czlcDatas[i].dmiMethodId,
				name : czlcDatas[i].dmiMethodDesc,
				value : czlcDatas[i].dmiMethodName,
				planType : czlcDatas[i].dmiPlanType,
				'sort-num' : i
			}).unbind('dblclick').bind('dblclick', function(event) {
				
				event.stopPropagation();
				clearTimeout(timers[i]);
				
				czlcMoveToLeft(this);
				
				$("#divId_yabz_czlcall").find("input").css("color", "white");
				$("#divId_yabz_czlcleft ul").find("li").css("color", "white");
				$("#divId_yabz_czlcleft ul").find("li").last().css("color", "red");
				$("#divId_yabz_czlcleft ul").find("li").last().click();
			}).unbind('click').bind('click', function() {
				
				var btnObj = this;
				clearTimeout(timers[i]);
				timers[i] = setTimeout(function() {
					loadCzlcInfoDetail(btnObj);
					$("#divId_yabz_czlcall").find("input").css("color", "white");
					$("#divId_yabz_czlcleft ul").find("li").css("color", "white");
					$(btnObj).css("color", "red");
			  	}, 300);				
			});
			
			czlcTd.appendTo(czlcTop);
		}
	}
	
	function initCzlcLeftList() {
		
	    var czlcLeft = $("#divId_yabz_czlcleft ul");		
	    czlcLeft.empty();
	    
	    for(var i = 0; i < czlcLeftDatas.length; i++) {
	    	var timers = [];
			var czlcLeftTd = $("<li class='disSelectHid'><div class='disSlectFont'>" + czlcLeftDatas[i].dmiMethodName + "</div></li>");
			czlcLeftTd.attr({
				id : czlcLeftDatas[i].dmiMethodId,
				name : czlcLeftDatas[i].dmiMethodDesc,
				value : czlcLeftDatas[i].dmiMethodName,
				planType : czlcLeftDatas[i].dmiPlanType,
				'sort-num': i,
			}).unbind('dblclick').bind('dblclick', function() {

				event.stopPropagation();
				clearTimeout(timers[i]);
				
				czlcMoveToTop(this);
				
				$("#divId_yabz_czlcall").find("input").css("color", "white");
				$("#divId_yabz_czlcleft ul").find("li").css("color", "white");
				
				var id = $(this).attr("id");
				$("#divId_yabz_czlcall").find("input[id='" + id + "']").css("color", "red");
				$("#divId_yabz_czlcall").find("input[id='" + id + "']").click();
			}).unbind('click').bind('click', function() {
				
				var btnObj = this;
				clearTimeout(timers[i]);
				timers[i] = setTimeout(function() {
					loadCzlcInfoDetail(btnObj);
					$("#divId_yabz_czlcall").find("input").css("color", "white");
					$("#divId_yabz_czlcleft ul").find("li").css("color", "white");
					$(btnObj).css("color", "red");
				}, 300);
			});
			
			czlcLeftTd.appendTo(czlcLeft);
		}	
 	}
	
	function saveOrUpdateCzffInfo() {
		
		var planId = $("#inputId_yabz_planId").val();
		if(planId == null || planId == "") {
			$.alert("请先填写基本信息！");
			return;
		}
		
		var data = {};
		data.epiPlanId = planId;
	    $('#divId_yabz_czlcleft ul li').each(function(i) {
			var id = $(this).attr("id");
			var name = $(this).attr("name");
			var value = $(this).attr("value");
			var planType = $(this).attr("planType");
			data["yapzList[" + i + "].epcPlanId"] = planId;
			data["yapzList[" + i + "].epcConfigType"] = "1";
			data["yapzList[" + i + "].epcConfigItemId"] = id;
			data["yapzList[" + i + "].epcOrderSeq"] = i;			
			data["yapzList[" + i + "].methodName"] = value;
			data["yapzList[" + i + "].methodDesc"] = name;
			data["yapzList[" + i + "].planType"] = planType;			
	    });
		
		$.ajax({
			type : 'post',
			url : '${ctx}/yjct/yabz/saveOrUpdateCzffInfo',
			data: data,
			dataType : 'json',
			success : function(data) {
				if(data.code == "1") {
					$.alert("操作成功！");
					// $("#gridId_yabz").grid("reload");					
				} else {
					$.alert("操作失败！");
				}				
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$.alert("操作失败！");
			}
		});
	}
	// 预案处置流程信息		end
	
	// 预案处置流程-关联操作项			start
	function showActionDetail(relActionType) {
		
		for(var i = 1; i <= 5; i++) {
			if(relActionType == i+"") {
				$("#divId_yabz_edit_relAction_" + i).show();
			} else {
				$("#divId_yabz_edit_relAction_" + i).hide();
			}			
		}
		
		if(relActionType == "2") {			
			// 加载工作组
			loadCzlcGzzUncheckInfo();
			
			if(updateFlag) {
				loadCzlcGzzCheckInfo();
			}			
		} else if(relActionType == "3") {
			// 加载专家
			loadCzlcExpertUncheckInfo();
			
			if(updateFlag) {
				loadCzlcExpertCheckInfo();
			}
		} else if(relActionType == "4") {
			// 加载物资
			loadCzlcMaterialInfo();
		} else if(relActionType == "5") {
			// 加载物资
			loadCzlcLawInfo();
		}
	}
	
	// 关联操作项-事件记录					start
	function saveOrUpdateEventRecord() {
		
		var data = {};
		data.epiPlanId = $("#inputId_yabz_planId").val();
		data["yaczList[0].epaPlanId"] = $("#inputId_yabz_planId").val();
		data["yaczList[0].epaMethodId"] = $("#inputId_yabz_czlcMethodId").val();
		data["yaczList[0].epaRelActionType"] = 1;
		data["yaczList[0].epaRelActionId"] = 0;
		data["yaczList[0].epaHandleType"] = 1;
		
		$.ajax({
			type : 'post',
			url : '${ctx}/yjct/yabz/saveOrUpdatePlanAction',
			data: data,
			dataType : 'json',
			success : function(data) {
				if(data.code == "1") {
					$.alert("操作成功！");
					// $("#gridId_yabz").grid("reload");
				} else {
					$.alert("操作失败！");
				}				
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$.alert("操作失败！");
			}
		});
	}
	// 关联操作项-事件记录					end
	
	// 关联操作项-工作组					start
	function loadCzlcGzzUncheckInfo() {
		
		var data = {};
		data.wgiHandleId = $("#inputId_yabz_planId").val();
		data.wgiHandleType = "1";
		data.epcConfigType = "2";
		data.epaMethodId = $("#inputId_yabz_czlcMethodId").val();
		data.epaRelActionType = "2";
		data.actionExist = "0";
				
		$.ajax({
			type : 'post',
			url : '${ctx}/yjct/gzzls/searchAllData',
			data: data,
			dataType : 'json',
			success : function(data) {
				if(data.code == "1") {
					
					czlcGzzDatas = data.data;
					initCzlcGzzUncheckInfo();
				} else {
					$.alert("获取数据失败！");
				}				
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$.alert("获取数据失败！");
			}
		});
	}
	
	function loadCzlcGzzCheckInfo() {
		
		var data = {};
		data.wgiHandleId = $("#inputId_yabz_planId").val();
		data.wgiHandleType = "1";
		data.epcConfigType = "2";
		data.epaMethodId = $("#inputId_yabz_czlcMethodId").val();
		data.epaRelActionType = "2";
		data.actionExist = "1";
				
		$.ajax({
			type : 'post',
			url : '${ctx}/yjct/gzzls/searchAllData',
			data: data,
			dataType : 'json',
			success : function(data) {
				if(data.code == "1") {
					
					czlcGzzCheckDatas = data.data;
					initCzlcGzzCheckInfo();
				} else {
					$.alert("获取数据失败！");
				}				
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$.alert("获取数据失败！");
			}
		});
	}
	
	function czlcGzzMoveToChecked(btn){
	   	  
		var sortNum = $(btn).attr("sort-num");
		var element = czlcGzzDatas[sortNum];	
		
		czlcGzzDatas.splice(sortNum, 1);		  
		czlcGzzCheckDatas.push(element);
		  		  		   
		initCzlcGzzUncheckInfo();
		initCzlcGzzCheckInfo(); 
	}
	
	function czlcGzzMoveToUnChecked(btn){
	   	  
		var sortNum = $(btn).attr("sort-num");
		var element = czlcGzzCheckDatas[sortNum];	
		
		czlcGzzCheckDatas.splice(sortNum, 1);		  
		czlcGzzDatas.push(element);
		  		  		   
		initCzlcGzzUncheckInfo();
		initCzlcGzzCheckInfo(); 
	}
	
	function initCzlcGzzUncheckInfo() {
		
		var workgroupUnChecked = $("#divId_yabz_edit_workgroup_unChecked");
		workgroupUnChecked.empty();
		
		for(var i = 0; i < czlcGzzDatas.length; i++) {
			
		    unCheckTd = $("<input style='width: 248px; margin-top: 2px;' type='button' />");
		    unCheckTd.attr({
				id: czlcGzzDatas[i].wgiWorkgroupId,
				value: czlcGzzDatas[i].wgiWorkgroupName,
				'sort-num' : i
			}).bind('dblclick', function() {
				
				czlcGzzMoveToChecked(this);
			});
		    
		    unCheckTd.appendTo(workgroupUnChecked);
		}
	}
	
	function initCzlcGzzCheckInfo() {
		
		var workgroupChecked = $("#divId_yabz_edit_workgroup_checked");
		workgroupChecked.empty();
		
		for(var i = 0; i < czlcGzzCheckDatas.length; i++) {
			
		    checkTd = $("<input style='width: 248px; margin-top: 2px;' type='button' />");
		    checkTd.attr({
				id: czlcGzzCheckDatas[i].wgiWorkgroupId,
				value: czlcGzzCheckDatas[i].wgiWorkgroupName,
				'sort-num' : i
			}).bind('dblclick', function() {
				
				czlcGzzMoveToUnChecked(this);
			});
		    
		    checkTd.appendTo(workgroupChecked);
		}
	}
	
	function saveOrUpdateWorkGroup() {
		
		var data = {};		
		data.epiPlanId = $("#inputId_yabz_planId").val();
		for(var i = 0; i < czlcGzzCheckDatas.length; i++) {
			data["yaczList[" + i +"].epaPlanId"] = $("#inputId_yabz_planId").val();
			data["yaczList[" + i +"].epaMethodId"] = $("#inputId_yabz_czlcMethodId").val();
			data["yaczList[" + i +"].epaRelActionType"] = 2;
			data["yaczList[" + i +"].epaRelActionId"] = czlcGzzCheckDatas[i].wgiWorkgroupId;
			data["yaczList[" + i +"].epaHandleType"] = 1;
		}
				
		$.ajax({
			type : 'post',
			url : '${ctx}/yjct/yabz/saveOrUpdatePlanAction',
			data: data,
			dataType : 'json',
			success : function(data) {
				if(data.code == "1") {
					$.alert("操作成功！");
					// $("#gridId_yabz").grid("reload");
				} else {
					$.alert("操作失败！");
				}				
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$.alert("操作失败！");
			}
		});
	}
	// 关联操作项-工作组					end
	
	// 关联操作项-专家					start
	function loadCzlcExpertUncheckInfo() {
		
		var data = {};
		data.epiSttsIndc = "2";
		data.epaPlanId = $("#inputId_yabz_planId").val();
		data.epaHandleType = "1";
		data.epaMethodId = $("#inputId_yabz_czlcMethodId").val();
		data.epaRelActionType = "3";
		data.actionExist = "0";
		
		$.ajax({
			type : 'post',
			url : '${ctx}/yjct/zjgl/searchAllData',
			data : data,
			dataType : 'json',
			success : function(data) {
				if(data.code == "1") {
					
					czlcExpertDatas = data.data;
					initCzlcExpertUncheckInfo();
				} else {
					$.alert("获取数据失败！");
				}				
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$.alert("获取数据失败！");
			}
		});
	}
	
	function loadCzlcExpertCheckInfo() {
		
		var data = {};
 		data.epiHandleId = $("#inputId_yabz_planId").val();
		data.epiHandleType = "1";
		data.epaMethodId = $("#inputId_yabz_czlcMethodId").val();
		data.epaRelActionType = "3";
		data.actionExist = "1";
		
		$.ajax({
			type : 'post',
			url : '${ctx}/yjct/zjls/searchAllData',
			data : data,
			dataType : 'json',
			success : function(data) {
				if(data.code == "1") {
					
					czlcExpertCheckDatas = data.data;
					initCzlcExpertCheckInfo();
				} else {
					$.alert("获取数据失败！");
				}				
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$.alert("获取数据失败！");
			}
		});
	}
	
	function czlcExpertMoveToChecked(btn){
	   	
		var sortNum = $(btn).attr("sort-num");
		var element = czlcExpertDatas[sortNum];	
		
		czlcExpertDatas.splice(sortNum, 1);		  
		czlcExpertCheckDatas.push(element);
		  		  		   
		initCzlcExpertUncheckInfo();
		initCzlcExpertCheckInfo(); 
	}
	
	function czlcExpertMoveToUnChecked(btn){
	   	  
		var sortNum = $(btn).attr("sort-num");
		var element = czlcExpertCheckDatas[sortNum];	
		
		czlcExpertCheckDatas.splice(sortNum, 1);		  
		czlcExpertDatas.push(element);
		  		  		   
		initCzlcExpertUncheckInfo();
		initCzlcExpertCheckInfo(); 
	}
		
	function initCzlcExpertUncheckInfo() {
		
		var expertUnChecked = $("#divId_yabz_edit_expert_unChecked");
		expertUnChecked.empty();
		
		for(var i = 0; i < czlcExpertDatas.length; i++) {
			
		    unCheckTd = $("<input style='width:210px;' type='button' />");
		    unCheckTd.attr({
				id: czlcExpertDatas[i].epiExpertId,
				value: czlcExpertDatas[i].epiExpertName,
				'sort-num' : i
			}).bind('dblclick', function() {
				
				czlcExpertMoveToChecked(this);
			});
		    
		    unCheckTd.appendTo(expertUnChecked);
		}
	}
		
	function initCzlcExpertCheckInfo() {
		
		var expertChecked = $("#divId_yabz_edit_expert_checked");
		expertChecked.empty();
		
		for(var i = 0; i < czlcExpertCheckDatas.length; i++) {
			
		    checkTd = $("<input style='width:210px;' type='button' />");
		    checkTd.attr({
				id: czlcExpertCheckDatas[i].epiExpertId,
				value: czlcExpertCheckDatas[i].epiExpertName,
				'sort-num' : i
			}).bind('dblclick', function() {
				
				czlcExpertMoveToUnChecked(this);
			});
		    
		    checkTd.appendTo(expertChecked);
		}
	}
	
	function saveOrUpdateExpert() {
		
		var data = {};
		data.epiPlanId = $("#inputId_yabz_planId").val();
		for(var i = 0; i < czlcExpertCheckDatas.length; i++) {
			data["yaczList[" + i +"].epaPlanId"] = $("#inputId_yabz_planId").val();
			data["yaczList[" + i +"].epaMethodId"] = $("#inputId_yabz_czlcMethodId").val();
			data["yaczList[" + i +"].epaRelActionType"] = 3;
			data["yaczList[" + i +"].epaRelActionId"] = czlcExpertCheckDatas[i].epiExpertId;
			data["yaczList[" + i +"].epaHandleType"] = 1;
		}
				
		$.ajax({
			type : 'post',
			url : '${ctx}/yjct/yabz/saveOrUpdatePlanAction',
			data: data,
			dataType : 'json',
			success : function(data) {
				if(data.code == "1") {
					$.alert("操作成功！");
					// $("#gridId_yabz").grid("reload");
				} else {
					$.alert("操作失败！");
				}				
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$.alert("操作失败！");
			}
		});
	}
	// 关联操作项-专家					end
	
	// 关联操作项-物资					start
	function loadCzlcMaterialInfo() {
		
		var data = {};
		data.mriStatus = "2";
		data.epaPlanId = $("#inputId_yabz_planId").val();
		data.epaHandleType = "1";
		data.epaMethodId = $("#inputId_yabz_czlcMethodId").val();
		data.epaRelActionType = "4";
		data.actionExist = "0";
		
		$.ajax({
			type : 'post',
			url : '${ctx}/yjct/wzgl/searchAllData',
			data : data,
			dataType : 'json',
			success : function(data) {
				if(data.code == "1") {
					
					var materialDatas = data.data;
					var dataArr = [];
					for(var i = 0; i < materialDatas.length; i++) {
						var data = {};
						data.value = materialDatas[i].mriMaterialId + "_" + materialDatas[i].mriCount;
						data.text = materialDatas[i].mriMaterialName;
						dataArr[i] = data;
					}
					
					$("#comboboxId_yabz_edit_materialName").combobox("reload", dataArr);
				} else {
					$.alert("获取数据失败！");
				}				
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$.alert("获取数据失败！");
			}
		});
	}
	
	function loadCzlcMaterialCheckInfo() {
		
		var data = {};
		data.epiHandleId = $("#inputId_yabz_planId").val();
		data.epiHandleType = "1";
		data.epaMethodId = $("#inputId_yabz_czlcMethodId").val();
		data.epaRelActionType = "4";
		data.actionExist = "1";
		
		$.ajax({
			type : 'post',
			url : '${ctx}/yjct/wzls/searchAllData',
			data : data,
			dataType : 'json',
			success : function(data) {
				if(data.code == "1") {
					
					var materialDatas = data.data;
					var dataArr = [];
					for(var i = 0; i < materialDatas.length; i++) {
						var data = {};
						data.value = materialDatas[i].mriMaterialId + "_" + materialDatas[i].mriCount;
						data.text = materialDatas[i].mriMaterialName;
						dataArr[i] = data;
					}
					
					$("#comboboxId_yabz_edit_materialName").combobox("reload", dataArr);
				} else {
					$.alert("获取数据失败！");
				}				
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$.alert("获取数据失败！");
			}
		});
	}
	
	function updateMaterialTotal(event, ui) {
				
		$("#comboboxId_yabz_edit_materialTotal").val(ui.newValue.split("_")[1]);
	}
	
	function selectMaterial() {
	
		var id = $("#comboboxId_yabz_edit_materialName").combobox("getValue").split("_")[0];
		var name = $("#comboboxId_yabz_edit_materialName").combobox("getText");
		var total = $("#comboboxId_yabz_edit_materialTotal").val();
		var count =  $("#comboboxId_yabz_edit_materialNum").val();
				
		var trId = "trId_yabz_edit_material_info_" + pos;
		var html = "<tr id='" + trId + "'>" +
			"<td>物资名称：</td>" +
			"<td><input readonly='readonly' id='inputId_yabz_edit_material_name_" + pos + "' value='" + name + "' style='width: 100px;' />" +
			"<input readonly='readonly' type='hidden' id='inputId_yabz_edit_material_id_" + pos + "' value='" + id + "' />" +	
 			"</td>" + 
			"<td>总数：</td>" +
			"<td><input readonly='readonly' id='inputId_yabz_edit_material_total_" + pos + "' value='" + total + "' style='width: 100px;' /></td>" +
			"<td>所需数量：</td>" +
			"<td><input id='inputId_yabz_edit_material_count_" + pos + "' value='" + count + "' style='width: 100px;' /></td>" +			
			"<td>&nbsp;&nbsp;&nbsp;<a onclick='deleteMaterial(\"#" + trId + "\", null)'>删除</a></td>" +
			"</tr>";
			
		$("#tableId_yabz_edit_materialSelect").append(html);
 			
		$("#comboboxId_yabz_edit_materialName").combobox('setValue', '');
		$("#comboboxId_yabz_edit_materialTotal").val('');
		$("#comboboxId_yabz_edit_materialNum").val('');
		pos++;
	}
	
	function deleteMaterial(trId, materialId) {
		
		$(trId).remove();					 
	}
	
	function saveOrUpdateMaterial() {
		
		var data = {};
		data.epiPlanId = $("#inputId_yabz_planId").val();
		for(var i = 0; i < pos; i++) {
			if($("#inputId_yabz_edit_material_id_" + i).length > 0) {
				data["yaczList[" + i +"].epaPlanId"] = $("#inputId_yabz_planId").val();
				data["yaczList[" + i +"].epaMethodId"] = $("#inputId_yabz_czlcMethodId").val();
				data["yaczList[" + i +"].epaRelActionType"] = 4;
				data["yaczList[" + i +"].epaRelActionId"] = $("#inputId_yabz_edit_material_id_" + i).val();
				data["yaczList[" + i +"].epaRemark"] = $("#inputId_yabz_edit_material_count_" + i).val();
				data["yaczList[" + i +"].epaHandleType"] = 1;
			}			
		}
		
		$.ajax({
			type : 'post',
			url : '${ctx}/yjct/yabz/saveOrUpdatePlanAction',
			data: data,
			dataType : 'json',
			success : function(data) {
				if(data.code == "1") {
					$.alert("操作成功！");
					// $("#gridId_yabz").grid("reload");
				} else {
					$.alert("操作失败！");
				}				
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$.alert("操作失败！");
			}
		});
	}
	// 关联操作项-物资					end
	
	// 关联操作项-法规					start
	function loadCzlcLawInfo() {
		
		var data = {};
		data.lpiSttsIndc = "2";
		
		$.ajax({
			type : 'post',
			url : '${ctx}/yjct/fggl/searchAllData',
			data : data,
			dataType : 'json',
			success : function(data) {
				if(data.code == "1") {
					
					var lawDatas = data.data;
					var dataArr = [];
					for(var i = 0; i < lawDatas.length; i++) {
						var data = {};
						data.value = lawDatas[i].lriLawsId + "_" + lawDatas[i].lriLawsContent;
						data.text = lawDatas[i].lriLawsName;
						dataArr[i] = data;
					}
					
					$("#comboboxId_yabz_edit_lawName").combobox("reload", dataArr);
				} else {
					$.alert("获取数据失败！");
				}				
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$.alert("获取数据失败！");
			}
		});
	}
	
	function updateLawRemark(event, ui) {
		
		$("#comboboxId_yabz_edit_lawRemark").val(ui.newValue.split("_")[1]);
	}
	
	function selectLaw() {
		
		var id = $("#comboboxId_yabz_edit_lawName").combobox("getValue").split("_")[0];
		var name = $("#comboboxId_yabz_edit_lawName").combobox("getText");
 		var remark =  $("#comboboxId_yabz_edit_lawRemark").val();
				
		var trId = "trId_yabz_edit_law_info_" + lpos;
		var html = "<tr id='" + trId + "'>" +
			"<td>法规名称：</td>" +
			"<td><input readonly='readonly' id='inputId_yabz_edit_law_name_" + lpos + "' value='" + name + "' style='width: 100px;' />" +
			"<input readonly='readonly' type='hidden' id='inputId_yabz_edit_law_id_" + lpos + "' value='" + id + "' />" +	
 			"</td>" + 
			"<td>备注：</td>" +
			"<td><input readonly='readonly' id='inputId_yabz_edit_law_remark_" + lpos + "' value='" + remark + "' style='width: 200px;' /></td>" +			 
			"<td>&nbsp;&nbsp;&nbsp;<a onclick='deleteLaw(\"#" + trId + "\", null)'>删除</a></td>" +
			"</tr>";
		
		$("#tableId_yabz_edit_lawSelect").append(html);
 			
		$("#comboboxId_yabz_edit_lawName").combobox('setValue', '');
		$("#comboboxId_yabz_edit_lawRemark").val('');
		lpos++;
	}
	
	function deleteLaw(trId, lawId) {
		
		$(trId).remove();					
	}
	
	function saveOrUpdateLaw() {
		
		var data = {};
		data.epiPlanId = $("#inputId_yabz_planId").val();
		for(var i = 0; i < pos; i++) {
			if($("#inputId_yabz_edit_law_id_" + i).length > 0) {
				data["yaczList[" + i +"].epaPlanId"] = $("#inputId_yabz_planId").val();
				data["yaczList[" + i +"].epaMethodId"] = $("#inputId_yabz_czlcMethodId").val();
				data["yaczList[" + i +"].epaRelActionType"] = 5;
				data["yaczList[" + i +"].epaRelActionId"] = $("#inputId_yabz_edit_law_id_" + i).val();
				data["yaczList[" + i +"].epaRemark"] = $("#inputId_yabz_edit_law_remark_" + i).val();
				data["yaczList[" + i +"].epaHandleType"] = 1;
			}			
		}
		
		$.ajax({
			type : 'post',
			url : '${ctx}/yjct/yabz/saveOrUpdatePlanAction',
			data: data,
			dataType : 'json',
			success : function(data) {
				if(data.code == "1") {
					$.alert("操作成功！");
					// $("#gridId_yabz").grid("reload");
				} else {
					$.alert("操作失败！");
				}				
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$.alert("操作失败！");
			}
		});
	}
	// 关联操作项-法规					end
	// 预案处置流程-关联操作项			end
	
	function updateYafbsp() {
		
		var data = {};
		data.epiPlanId = $("#inputId_yabz_planId").val();
		
		$.ajax({
			type : 'post',
			url : '${ctx}/yjct/yabz/updateYafbsp',
			data: data,
			dataType : 'json',
			success : function(data) {
				if(data.code == "1") {
					$.alert("操作成功！");
					$("#gridId_yabz").grid("reload");
					closeDialog();
				} else {
					$.alert("操作失败！");
				}				
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$.alert("操作失败！");
			}
		});
	}
	
	function deleteByIds() {
		
		var selarrrow = $("#gridId_yabz").grid("option", "selarrrow");			
		if(selarrrow && selarrrow.length > 0) {
			$.confirm({
				message : "确认删除？",
				iconCls : "icon-question",
				callback : function(sure) {
					if(sure == true) {
						
						var ids = "";
						for(var i = 0; i < selarrrow.length; i++) {
							ids += selarrrow[i] + ",";
						}
						
						$.ajax({
							type : 'post',
							url : '${ctx}/yjct/yabz/deleteByIds',
							data: {
								ids : ids
							},
							dataType : 'json',
							success : function(data) {
								if(data.code == "1") {
									$.alert("操作成功！");
									$("#gridId_yabz").grid("reload");
								} else {
									$.alert("操作失败！");
								}				
							},
							error : function(XMLHttpRequest, textStatus, errorThrown) {
								$.alert("操作失败！");
							}
						});
					}
				}
			}); 		
		} else {
			$.alert("请选择记录！");
			return;
		}
	}
</script>
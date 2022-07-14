<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<div style="height: 100%; margin: 0px 10px;">
	<cui:form id="formId_yabz_query">
		<table class="table">			
			<tr>
				<td>预案名称：</td>
				<td><cui:input name="epiPlanName"></cui:input></td>
				<td>预案类别：</td>
				<td><cui:combobox name="epiPlanType" data="combobox_yjct_planType"></cui:combobox></td>
				<td>
					<cui:button label="查询" componentCls="btn-primary" onClick="search"/>
					<cui:button label="重置" onClick="reset"></cui:button>
				</td>
			</tr>
		</table>
	</cui:form>
	
	<div style="height: 480px;">
		<cui:toolbar id="toolbarId_yabz" data="toolbar_yabz"></cui:toolbar>			
		<cui:grid id="gridId_yabz" rownumbers="true" multiselect="true" width="auto" fitStyle="fill" 
			url="${ctx}/yjct/yabz/searchData"
			sortname="epiCrteTime, desc" rowNum="10">
			<cui:gridCols>
				<cui:gridCol name="id" hidden="true">id</cui:gridCol>
				<cui:gridCol name="epiPlanName" width="100">预案名称</cui:gridCol>
				<cui:gridCol name="epiPlanType" width="100" formatter="formatPlanType">预案类别</cui:gridCol>
 				<cui:gridCol name="epiPlanStatus" width="100" align="center" formatter="formatPlanStatus" revertCode="true">状态</cui:gridCol>
			</cui:gridCols>
			<cui:gridPager gridId="gridId_yabz" />
		</cui:grid>
	</div>
	
	<cui:dialog id="dialogId_yabz" autoOpen="false" resizable="false" iframePanel="true" reLoadOnOpen="true" modal="true" autoDestroy="true">
	</cui:dialog>
</div>

<script>
	
	var combobox_yjct_planType = <%=CodeFacade.loadCode2Json("4.20.4")%>;
	var combobox_yjct_planStatus = <%=CodeFacade.loadCode2Json("4.20.2")%>;	
	var combobox_yjct_actionType = <%=CodeFacade.loadCode2Json("4.20.5")%>;
	var combobox_yjct_sex = <%=CodeFacade.loadCode2Json("4.3.3")%>;
	
	var gzzUncheckDatas = [];
	var gzzCheckDatas = [];
	
	var czlcUncheckDatas = [];
	var czlcCheckDatas = [];
	
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
	}, {
		"type" : "button",
		"id" : "btnId_view",
		"label" : "查看",
		"onClick" : "openDailog",
		"componentCls" : "btn-primary"
	}, {
		"type" : "button",
		"id" : "btnId_copy",
		"label" : "复制",
		"onClick" : "copyYaxx",
		"componentCls" : "btn-primary"
	}, {
		"type" : "button",
		"id" : "btnId_sp",
		"label" : "发布审批",
		"onClick" : "updateYafbsp",
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
				
				var rowData = $("#gridId_yabz").grid("getRowData", selarrrow[0]);
				if(rowData.epiPlanStatus != "0") {
					$.message({message:"请选择状态为新建中的记录！", cls:"waring"});
					return;
				}
				
				url = "${ctx}/yjct/yabz/toEdit?id=" + selarrrow[0];
			} else {
				$.message({message:"请选择一条记录！", cls:"waring"});
				return;
			}			
		} else if(ui.id == "btnId_view") {
			
			var selarrrow = $("#gridId_yabz").grid("option", "selarrrow");			
			if(selarrrow && selarrrow.length == 1) {
				
				url = "${ctx}/yjct/yabz/toView?id=" + selarrrow[0];
			} else {
				$.message({message:"请选择一条记录！", cls:"waring"});
				return;
			}
		}

		$("#dialogId_yabz").dialog({
			width : 1000,
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
	 
	// 预案基本信息			start
	function saveOrUpdatePlanInfo() {
		
		var validFlag = $("#formId_yabz_edit").form("valid");
		if(!validFlag) {
			return;
		}
		
		var formData = $("#formId_yabz_edit").form("formData");
			
		$.loading({text:"正在处理中，请稍后..."});
		
		$.ajax({
			type : 'post',
			url : '${ctx}/yjct/yabz/saveOrUpdate',
			data: formData,
			dataType : 'json',
			success : function(data) {
				$.loading("hide");
				
				if(data.code == "1") {
					$.message({message:"操作成功！", cls:"success"});
					
					$("#gridId_yabz").grid("reload");
					$("#tabsId_yabz_edit").tabs("option", "active", "tabsId_yabz_edit_2");
					$("#formId_yabz_edit").form("load", data.data);	
					
					// 加载工作组
					if(!updateFlag) {
						loadGzzUncheckInfo();
					}					
				} else {
					$.message({message:"操作失败！", cls:"error"});
				}				
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$.loading("hide");
				$.message({message:"操作失败！", cls:"error"});
			}
		});
	}
	// 预案基本信息			end
	
	// 预案工作组信息		start
	function loadGzzUncheckInfo() {
		
		var data = {};
		data.wgiStatus = "1";
		data.configExist = "0";
		data.epcPlanFid = $("#inputId_yabz_planId").val();
		
		$.loading({text:"正在读取中，请稍后..."});
		
		$.ajax({
			type : 'post',
			url : '${ctx}/yjct/gzzgl/searchAllData',
			data : data,
			dataType : 'json',
			success : function(data) {
				$.loading("hide");
				
				gzzUncheckDatas = data;
				initGzzUncheckList();			
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$.loading("hide");
				$.message({message:"获取数据失败！", cls:"error"});
			}
		});
	}
	
	function loadGzzCheckInfo() {
		
		var data = {};
		data.epcConfigType = "2";
		data.epcPlanFid = $("#inputId_yabz_planId").val();		
		
		$.ajax({
			type : 'post',
			url : '${ctx}/yjct/yapz/searchAllData',
			data : data,
			dataType : 'json',
			success : function(data) {
				gzzCheckDatas = data;
				initGzzCheckList();				 				
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$.message({message:"获取数据失败！", cls:"error"});
			}
		});
	}
	
	function loadGzzcyInfo(btn) {
		
		$("#divId_yabz_gzzright_1").html("");
		$("#divId_yabz_gzzright_2").html("");
		$("#divId_yabz_gzzright_3").html("");
		$("#divId_yabz_gzzright_4").html("");
		
		var gzzId = $(btn).attr("id");
		var gzzTask = $(btn).attr("name");
		var data = {};
		data.wgmWorkgroupFid = gzzId;
		
		$.loading({text:"正在读取中，请稍后..."});
		
		$.ajax({
			type : 'post',
			url : '${ctx}/yjct/gzzcy/searchAllData',
			data : data,
			dataType : 'json',
			success : function(data) {
				$.loading("hide");
 				initGzzcyInfo(gzzTask, data);				 				
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$.loading("hide");
				$.message({message:"获取数据失败！", cls:"error"});
			}
		});
	}
	
	function gzzMoveToChecked(btn){
	   	  
		var sortNum = $(btn).attr("sort-num");
		var element = gzzUncheckDatas[sortNum];	
		
		gzzUncheckDatas.splice(sortNum, 1);		  
		gzzCheckDatas.push(element);
		  		  		   
		initGzzUncheckList();
		initGzzCheckList(); 
	}
	
	function gzzMoveToUnChecked(btn){
	   	  
		var sortNum = $(btn).attr("sort-num");
		var element = gzzCheckDatas[sortNum];	
		
		gzzCheckDatas.splice(sortNum, 1);		  
		gzzUncheckDatas.push(element);
		  		  		   
		initGzzUncheckList();
		initGzzCheckList(); 
	}
	
	function initGzzUncheckList() {
		
		var gzzLeft = $("#divId_yabz_gzzleft ul");		
	    gzzLeft.empty();
	    
	    for(var i = 0; i < gzzUncheckDatas.length; i++) {
	    	var timers = [];
			var gzzLeftTd = $("<li class='selectHid'>" + gzzUncheckDatas[i].wgiWorkgroupName + "</li>");
			gzzLeftTd.attr({
				id : gzzUncheckDatas[i].id,
				name : gzzUncheckDatas[i].wgiWorkgroupTask,
				value : gzzUncheckDatas[i].wgiWorkgroupName,
 				'sort-num': i,
			}).unbind('dblclick').bind('dblclick', function() {

				event.stopPropagation();
				clearTimeout(timers[i]);
				
				gzzMoveToChecked(this);
				
				$("#divId_yabz_gzzleft ul").find("li").css("color", "white");
				$("#divId_yabz_gzzmiddle ul").find("li").css("color", "white");
				$("#divId_yabz_gzzmiddle ul").find("li").last().css("color", "red");
				$("#divId_yabz_gzzmiddle ul").find("li").last().click();			
			}).unbind('click').bind('click', function() {
				
				var btnObj = this;
				clearTimeout(timers[i]);
				timers[i] = setTimeout(function() {
					loadGzzcyInfo(btnObj);
					$("#divId_yabz_gzzleft ul").find("li").css("color", "black");
					$("#divId_yabz_gzzmiddle ul").find("li").css("color", "black");
					$(btnObj).css("color", "red");
				}, 500);
			});
			
			gzzLeftTd.appendTo(gzzLeft);
		}	
	}
	
	function initGzzCheckList() {
		
	    var gzzMiddle = $("#divId_yabz_gzzmiddle ul");		
	    gzzMiddle.empty();
	    
	    for(var i = 0; i < gzzCheckDatas.length; i++) {
	    	var timers = [];
			var gzzMiddleTd = $("<li class='selectHid'>" + gzzCheckDatas[i].wgiWorkgroupName + "</li>");
			gzzMiddleTd.attr({
				id : gzzCheckDatas[i].id,
				name : gzzCheckDatas[i].wgiWorkgroupTask,
				value : gzzCheckDatas[i].wgiWorkgroupName,
				'sort-num': i,
			}).unbind('dblclick').bind('dblclick', function() {

				event.stopPropagation();
				clearTimeout(timers[i]);
				
				gzzMoveToUnChecked(this);
				
				$("#divId_yabz_gzzleft ul").find("li").css("color", "white");
				$("#divId_yabz_gzzmiddle ul").find("li").css("color", "white");
				$("#divId_yabz_gzzleft ul").find("li").last().css("color", "red");
				$("#divId_yabz_gzzleft ul").find("li").last().click();	
			}).unbind('click').bind('click', function() {
				
				var btnObj = this;
				clearTimeout(timers[i]);
				timers[i] = setTimeout(function() {
					loadGzzcyInfo(btnObj);
					$("#divId_yabz_gzzleft ul").find("li").css("color", "black");
					$("#divId_yabz_gzzmiddle ul").find("li").css("color", "black");
					$(btnObj).css("color", "red");
				}, 500);
			});
			
			gzzMiddleTd.appendTo(gzzMiddle);
		}	
 	}
	
	function initGzzcyInfo(gzzTask, gzzcyDatas) {
		
		var headerTr = "<table><tr>" + 
		   		"<th width='100' nowrap='nowrap'>姓名</th>" +
			   	"<th width='100' nowrap='nowrap'>通联号码</th>" + 
			   	"<th width='340' nowrap='nowrap'>任务分工</th>" + 
			   	"</tr>";
  
	   	var zzTr = "";
	   	var fzzTr = "";
	   	var cyTr = "";
	   	for(var i = 0; i < gzzcyDatas.length; i++) {
	   		var gzzcy = gzzcyDatas[i];
	   		var role = gzzcy.wgmUserRole;
			
	   		if(gzzcy.pbdPhone == null) {
	   			gzzcy.pbdPhone = "";
	   		}
	   		
	   		if(role == "1") {
	   			
	   			zzTr += "<tr>" + 
	   			"<td nowrap='nowrap'>" + gzzcy.wgmPoliceName + "</td>" +
			   	"<td nowrap='nowrap'>" + gzzcy.pbdPhone + "</td>" + 
			   	"<td nowrap='nowrap'>" + gzzcy.wgmRoleTask + "</td>" + 
			   	"</tr>";
	   		} else if(role == "2") {
	   			
	   			fzzTr += "<tr>" + 
	   			"<td nowrap='nowrap'>" + gzzcy.wgmPoliceName + "</td>" +
			   	"<td nowrap='nowrap'>" + gzzcy.pbdPhone + "</td>" + 
			   	"<td nowrap='nowrap'>" + gzzcy.wgmRoleTask + "</td>" + 
			   	"</tr>";
	   		} else if(role == "3") {
	   			
	   			cyTr += "<tr>" + 
	   			"<td nowrap='nowrap'>" + gzzcy.wgmPoliceName + "</td>" +
			   	"<td nowrap='nowrap'>" + gzzcy.pbdPhone + "</td>" + 
			   	"<td nowrap='nowrap'>" + gzzcy.wgmRoleTask + "</td>" + 
			   	"</tr>";
	   		}
	   	}
	   		 
	   	$("#divId_yabz_gzzright_1").append('<div style="border-bottom: 1px solid #4692f0; width: 540px;">组长</div>' + headerTr + zzTr + '</table>');
	   	$("#divId_yabz_gzzright_2").append('<div style="border-bottom: 1px solid #4692f0; width: 540px;">副组长</div>' + headerTr + fzzTr + '</table>');
	   	$("#divId_yabz_gzzright_3").append('<div style="border-bottom: 1px solid #4692f0; width: 540px;">成员</div>' + headerTr + cyTr + '</table>');
		$("#divId_yabz_gzzright_4").append("<b>组任务：</b>" + gzzTask);
	}
	
	function saveOrUpdateGzzInfo() {
		
		var planId = $("#inputId_yabz_planId").val();
		if(planId == null || planId == "") {
			$.message({message:"请先填写基本信息！", cls:"waring"});
			return;
		}
		
		var data = {};
		data.id = planId;
	    $('#divId_yabz_gzzmiddle ul li').each(function(i) {
			var id = $(this).attr("id");
			var name = $(this).attr("name");
			var value = $(this).attr("value");
			data["yapzList[" + i + "].epcPlanFid"] = planId;
			data["yapzList[" + i + "].epcConfigType"] = "2";
			data["yapzList[" + i + "].epcConfigItemFid"] = id;
			data["yapzList[" + i + "].epcOrderSeq"] = i;			
	    });
		
	    if(!data["yapzList[0].epcPlanFid"]) {
	    	$.message({message:"请选择工作组！", cls:"waring"});
			return;
	    }

	    $.loading({text:"正在处理中，请稍后..."});
	    
	    $.ajax({
			type : 'post',
			url : '${ctx}/yjct/yabz/saveOrUpdateGzzInfo',
			data: data,
			dataType : 'json',
			success : function(data) {
				$.loading("hide");
				
				if(data.code == "1") {
					$.message({message:"操作成功！", cls:"success"});
					$("#gridId_yabz").grid("reload");
					$("#tabsId_yabz_edit").tabs("option", "active", "tabsId_yabz_edit_3");
					
					// 加载处置方法
					if(!updateFlag) {
						loadCzlcUncheckInfo();
					}					
				} else {
					$.message({message:"操作失败！", cls:"error"}); 
				}				
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$.loading("hide");
				$.message({message:"操作失败！", cls:"error"}); 
			}
		});
	}
	// 预案工作组信息		end
	
	// 预案处置流程信息		start
	function loadCzlcUncheckInfo() {
		
		var data = {};
		data.dmiStatus = "1";
		data.configExist = "0";
		data.epcPlanFid = $("#inputId_yabz_planId").val();
		data.dmiPlanType = $("#comboboxId_yabz_planType").combobox("getValue");
		
		$.loading({text:"正在读取中，请稍后..."});
		
		$.ajax({
			type : 'post',
			url : '${ctx}/yjct/czffgl/searchAllData',
			data : data,
			dataType : 'json',
			success : function(data) {
				$.loading("hide");
				
 				czlcUncheckDatas = data;
				initCzlcUncheckList();				 			
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$.loading("hide");
				$.message({message:"获取数据失败！", cls:"error"}); 
			}
		});
	}
	
	function loadCzlcCheckInfo() {
		
		var data = {};
		data.epcConfigType = "1";
		data.epcPlanFid = $("#inputId_yabz_planId").val();	
		
		$.ajax({
			type : 'post',
			url : '${ctx}/yjct/yapz/searchAllData',
			data : data,
			dataType : 'json',
			success : function(data) {
 				czlcCheckDatas = data;
				initCzlcCheckList();				 			
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$.message({message:"获取数据失败！", cls:"error"}); 
			}
		});
	}
	
	function loadCzlcInfoDetail(btn) {
		
		var id = $(btn).attr("id");
		var desc = $(btn).attr("name");
		$("#tdId_yabz_methodDesc").html(desc);
	 	$("#inputId_yabz_czlcMethodId").val(id);
	 	$("#tdId_yabz_relAction").html("");
	 	
	 	$.loading({text:"正在读取中，请稍后..."});
	 	
		$.ajax({
			type : 'post',
			url : '${ctx}/yjct/czffczx/searchAllData',
			data: {
				mraMethodFid : id
			},
			dataType : 'json',
			success : function(data) {
				$.loading("hide");
				
				var actionData = data;
				var html = "";
				for(var i = 0; i < actionData.length; i++) {						
					for(var j = 0; j < combobox_yjct_actionType.length; j++) {
						if(actionData[i].mraRelActionType == combobox_yjct_actionType[j].value) {
							html += '<div style="float:left"><a href="javascript:void(0);" class="disDescBtn" onclick="showActionDetail(' + actionData[i].mraRelActionType + ')">' + combobox_yjct_actionType[j].text + '</a></div>';								
						}
					}						
				}
				
				$("#tdId_yabz_relAction").html(html);	
				
				for(var i = 1; i <= 5; i++) {
					$("#divId_yabz_edit_relAction_" + i).hide();			
				}
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$.loading("hide");
				$.message({message:"获取数据失败！", cls:"error"}); 
			}
		});
	}
	
	function czlcMoveToChecked(btn){
	   	  
		var sortNum = $(btn).attr("sort-num");
		var element = czlcUncheckDatas[sortNum];	
		
		czlcUncheckDatas.splice(sortNum, 1);		  
		czlcCheckDatas.push(element);
		  		  		   
		initCzlcUncheckList();
		initCzlcCheckList(); 
	}
	
	function czlcMoveToUnChecked(btn){
	   	  
		var sortNum = $(btn).attr("sort-num");
		var element = czlcCheckDatas[sortNum];	
		
		czlcCheckDatas.splice(sortNum, 1);		  
		czlcUncheckDatas.push(element);
		  		  		   
		initCzlcUncheckList();
		initCzlcCheckList(); 
	}
	
	function initCzlcUncheckList() {
		
		var czlcLeft = $("#divId_yabz_czlcleft ul");		
		czlcLeft.empty();
	    
	    for(var i = 0; i < czlcUncheckDatas.length; i++) {
	    	var timers = [];
			var czlcLeftTd = $("<li class='selectHid'>" + czlcUncheckDatas[i].dmiMethodName + "</li>");
			czlcLeftTd.attr({
				id : czlcUncheckDatas[i].id,
				name : czlcUncheckDatas[i].dmiMethodDesc,
				value : czlcUncheckDatas[i].dmiMethodName,
				'planType' : czlcUncheckDatas[i].dmiPlanType,
 				'sort-num': i,
			}).unbind('dblclick').bind('dblclick', function() {

				event.stopPropagation();
				clearTimeout(timers[i]);
				
				czlcMoveToChecked(this);
				
				$("#divId_yabz_czlcleft ul").find("li").css("color", "black");
				$("#divId_yabz_czlcmiddle ul").find("li").css("color", "black");
				$("#divId_yabz_czlcmiddle ul").find("li").last().css("color", "white");
				$("#divId_yabz_czlcmiddle ul").find("li").last().click();			
			}).unbind('click').bind('click', function() {
				
				var btnObj = this;
				clearTimeout(timers[i]);
				timers[i] = setTimeout(function() {
					loadCzlcInfoDetail(btnObj);
					$("#divId_yabz_czlcleft ul").find("li").css("color", "black");
					$("#divId_yabz_czlcmiddle ul").find("li").css("color", "black");
					$(btnObj).css("color", "red");
				}, 500);
			});
			
			czlcLeftTd.appendTo(czlcLeft);
		}
	}
	
	function initCzlcCheckList() {
		
		var czlcMiddle = $("#divId_yabz_czlcmiddle ul");		
		czlcMiddle.empty();
	    
	    for(var i = 0; i < czlcCheckDatas.length; i++) {
	    	var timers = [];
			var czlcMiddleTd = $("<li class='hid'>" + czlcCheckDatas[i].dmiMethodName + "</li>");
			czlcMiddleTd.attr({
				id : czlcCheckDatas[i].id,
				name : czlcCheckDatas[i].dmiMethodDesc,
				value : czlcCheckDatas[i].dmiMethodName,
				'planType' : czlcCheckDatas[i].dmiPlanType,
 				'sort-num': i,
			}).unbind('dblclick').bind('dblclick', function() {

				event.stopPropagation();
				clearTimeout(timers[i]);
				
				czlcMoveToUnChecked(this);
				
				$("#divId_yabz_czlcleft ul").find("li").css("color", "black");
				$("#divId_yabz_czlcmiddle ul").find("li").css("color", "black");
				$("#divId_yabz_czlcleft ul").find("li").last().css("color", "red");
				$("#divId_yabz_czlcleft ul").find("li").last().click();			
			}).unbind('click').bind('click', function() {
				
				var btnObj = this;
				clearTimeout(timers[i]);
				timers[i] = setTimeout(function() {
					loadCzlcInfoDetail(btnObj);
					$("#divId_yabz_czlcleft ul").find("li").css("color", "black");
					$("#divId_yabz_czlcmiddle ul").find("li").css("color", "black");
					$(btnObj).css("color", "white");
				}, 500);
			});
			
			czlcMiddleTd.appendTo(czlcMiddle);
		}
 	}
	
	function saveOrUpdateCzffInfo() {
		
		var planId = $("#inputId_yabz_planId").val();
		if(planId == null || planId == "") {
			$.message({message:"请先填写基本信息！", cls:"waring"});
			return;
		}
		
		var data = {};
		data.id = planId;
	    $('#divId_yabz_czlcmiddle ul li').each(function(i) {
			var id = $(this).attr("id");
			var name = $(this).attr("name");
			var value = $(this).attr("value");
			var planType = $(this).attr("planType");
			data["yapzList[" + i + "].epcPlanFid"] = planId;
			data["yapzList[" + i + "].epcConfigType"] = "1";
			data["yapzList[" + i + "].epcConfigItemFid"] = id;
			data["yapzList[" + i + "].epcOrderSeq"] = i;			
	    });
		
	    if(!data["yapzList[0].epcPlanFid"]) {
	    	$.message({message:"请选择处置方法！", cls:"waring"});
			return;
	    }
	    
	    $.loading({text:"正在处理中，请稍后..."});
	    
		$.ajax({
			type : 'post',
			url : '${ctx}/yjct/yabz/saveOrUpdateCzffInfo',
			data: data,
			dataType : 'json',
			success : function(data) {
				$.loading("hide");
				
				if(data.code == "1") {
					if(data.data != "") {
						$.message({message:data.data, cls:"waring"});
					} else {
						$.message({message:"操作成功！", cls:"success"});
						$("#gridId_yabz").grid("reload");
						closeDialog();
					}	
				} else {
					$.message({message:"操作失败！", cls:"error"});
				}				
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$.loading("hide");
				$.message({message:"操作失败！", cls:"error"});
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
		
		if(relActionType == "1"){
			loadSjjlInfo();
			
		} else if(relActionType == "2") {
			$("#divId_yabz_edit_workgroup_unChecked ul").html("");	
			$("#divId_yabz_edit_workgroup_checked ul").html("");

			// 初始化处置流程-工作组
			czlcGzzDatas = [];
			czlcGzzCheckDatas = [];
					
			// 加载工作组
			loadCzlcGzzCheckInfo();
			loadCzlcGzzUncheckInfo();					
		} else if(relActionType == "3") {
			$("#divId_yabz_edit_expert_unChecked ul").html("");	
			$("#divId_yabz_edit_expert_checked ul").html("");

			// 初始化处置流程-专家
			czlcExpertDatas = [];
			czlcExpertCheckDatas = [];
			
			// 加载专家
			loadCzlcExpertCheckInfo();
			loadCzlcExpertUncheckInfo();			
		} else if(relActionType == "4") {				
			$("#tableId_yabz_edit_materialSelect").html("");
			
			// 初始化处置流程-物资
			pos = 0;
			
			// 加载物资
			loadCzlcMaterialInfo();
			loadCzlcMaterialCheckInfo();
		} else if(relActionType == "5") {
			$("#tableId_yabz_edit_lawSelect").html("");
			
			// 初始化处置流程-法规
			lpos = 0;
			
			// 加载法规
			loadCzlcLawInfo();			
			loadCzlcLawCheckInfo();
		}
	}
	
	//事件记录
	function loadSjjlInfo() {
		$("#divId_yabz_edit_relAction_1 input").val("");
		$("#divId_yabz_edit_relAction_1 textarea").val("");
		
		var data = {};
		data.epaPlanFid = $("#inputId_yabz_planId").val();
 		data.epaMethodFid = $("#inputId_yabz_czlcMethodId").val();
		data.epaRelActionType = "1";
		
		$.loading({text:"正在读取中，请稍后..."});
		
		$.ajax({
			type : 'post',
			url : '${ctx}/yjct/yacz/searchAllData',
			data : data,
			dataType : 'json',
			success : function(data) {
				$.loading("hide");
				debugger;
				if(data.length > 0 ) {
					var v_str = data[0].epaRemark;
					var arry = v_str.split("~");
					for(var i = 0; i < arry.length; i++) {
						$("#edit_sjlj"+(i+1)).val(arry[i]);
					}
				}
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$.loading("hide");
				$.message({message:"获取数据失败！", cls:"error"});
			}
		});
	}
	
	
	
	// 关联操作项-事件记录					start
	function saveOrUpdateEventRecord() {

		var flag = checkSelectCzff();
		if(!flag) {
			$.message({message:"请选择处置方法！", cls:"waring"});
			return;
		}
		
		
		var edit_sjlj1 = $("#edit_sjlj1").val();
		var edit_sjlj2 = $("#edit_sjlj2").val();
		var edit_sjlj3 = $("#edit_sjlj3").val();
		var edit_sjlj4 = $("#edit_sjlj4").val();
		var edit_sjlj5 = $("#edit_sjlj5").val();
		var edit_sjlj6 = $("#edit_sjlj6").val();
		var v_str = edit_sjlj1 + "~" + edit_sjlj2 +  "~" + edit_sjlj3 +  "~" + edit_sjlj4 +  "~" + edit_sjlj5 +  "~" + edit_sjlj6;
		
		var data = {};
		data.id = $("#inputId_yabz_planId").val();
		data["yaczList[0].epaPlanFid"] = $("#inputId_yabz_planId").val();
		data["yaczList[0].epaMethodFid"] = $("#inputId_yabz_czlcMethodId").val();
		data["yaczList[0].epaRelActionType"] = 1;
		data["yaczList[0].epaRelActionFid"] = 0;
		data["yaczList[0].epaRemark"] = v_str;
		
		$.loading({text:"正在处理中，请稍后..."});
		
		$.ajax({
			type : 'post',
			url : '${ctx}/yjct/yabz/saveOrUpdatePlanAction',
			data: data,
			dataType : 'json',
			success : function(data) {
				$.loading("hide");
				
				if(data.code == "1") {
					if(data.data != "") {
						$.message({message:data.data, cls:"waring"});
					} else {
						$.message({message:"操作成功！", cls:"success"});
					}					
				} else {
					$.message({message:"操作失败！", cls:"error"});
				}				
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$.loading("hide");
				$.message({message:"操作失败！", cls:"error"});
			}
		});
	}
	// 关联操作项-事件记录					end
	
	// 关联操作项-工作组					start
	function loadCzlcGzzUncheckInfo() {
				
		var data = {};
		data.epcPlanFid = $("#inputId_yabz_planId").val();
 		data.epcConfigType = "2";
		data.epaMethodFid = $("#inputId_yabz_czlcMethodId").val();		
		data.epaRelActionType = "2";
		data.actionExist = "0";
			
		$.loading({text:"正在读取中，请稍后..."});
		
		$.ajax({
			type : 'post',
			url : '${ctx}/yjct/yapz/searchAllData',
			data: data,
			dataType : 'json',
			success : function(data) {
				$.loading("hide");
				
				czlcGzzDatas = data;
				initCzlcGzzUncheckInfo();			
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$.loading("hide");
				$.message({message:"获取数据失败！", cls:"waring"});
			}
		});
	}
	
	function loadCzlcGzzCheckInfo() {
		
		var data = {};
		data.epcPlanFid = $("#inputId_yabz_planId").val();
 		data.epcConfigType = "2";
		data.epaMethodFid = $("#inputId_yabz_czlcMethodId").val();		
		data.epaRelActionType = "2";
		data.actionExist = "1";
		
		$.ajax({
			type : 'post',
			url : '${ctx}/yjct/yapz/searchAllData',
			data: data,
			dataType : 'json',
			success : function(data) {
 					
				czlcGzzCheckDatas = data;
				initCzlcGzzCheckInfo();				 			
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$.message({message:"获取数据失败！", cls:"waring"});
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
		
		var workgroupUnChecked = $("#divId_yabz_edit_workgroup_unChecked ul");
		workgroupUnChecked.empty();
		
		for(var i = 0; i < czlcGzzDatas.length; i++) {
			
		    unCheckTd = $("<li class='selectHid'>" + czlcGzzDatas[i].wgiWorkgroupName + "</li>");
		    unCheckTd.attr({
				id: czlcGzzDatas[i].id,
				value: czlcGzzDatas[i].wgiWorkgroupName,
				'sort-num' : i
			}).bind('dblclick', function() {
				
				czlcGzzMoveToChecked(this);
			});
		    
		    unCheckTd.appendTo(workgroupUnChecked);
		}
	}
	
	function initCzlcGzzCheckInfo() {
		
		var workgroupChecked = $("#divId_yabz_edit_workgroup_checked ul");
		workgroupChecked.empty();
		
		for(var i = 0; i < czlcGzzCheckDatas.length; i++) {
			
 		    checkTd = $("<li class='selectHid'>" + czlcGzzCheckDatas[i].wgiWorkgroupName + "</li>");
		    checkTd.attr({
				id: czlcGzzCheckDatas[i].id,
				value: czlcGzzCheckDatas[i].wgiWorkgroupName,
				'sort-num' : i
			}).bind('dblclick', function() {
				
				czlcGzzMoveToUnChecked(this);
			});
		    
		    checkTd.appendTo(workgroupChecked);
		}
	}
	
	function saveOrUpdateWorkGroup() {
		
		var flag = checkSelectCzff();
		if(!flag) {
			$.message({message:"请选择处置方法！", cls:"waring"});
			return;
		}
		
		if(czlcGzzCheckDatas.length == 0) {
			$.message({message:"请选择工作组！", cls:"waring"});
			return;
		}
		
		var data = {};		
		data.id = $("#inputId_yabz_planId").val();
		for(var i = 0; i < czlcGzzCheckDatas.length; i++) {
			data["yaczList[" + i +"].epaPlanFid"] = $("#inputId_yabz_planId").val();
			data["yaczList[" + i +"].epaMethodFid"] = $("#inputId_yabz_czlcMethodId").val();
			data["yaczList[" + i +"].epaRelActionType"] = 2;
			data["yaczList[" + i +"].epaRelActionFid"] = czlcGzzCheckDatas[i].id;
 		}
		
		$.loading({text:"正在处理中，请稍后..."});
		
		$.ajax({
			type : 'post',
			url : '${ctx}/yjct/yabz/saveOrUpdatePlanAction',
			data: data,
			dataType : 'json',
			success : function(data) {
				$.loading("hide");
				
				if(data.code == "1") {
					if(data.data != "") {
						$.message({message:data.data, cls:"waring"});
					} else {
						$.message({message:"操作成功！", cls:"success"});
					}
				} else {
					$.message({message:"操作失败！", cls:"error"});
				}				
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$.loading("hide");
				$.message({message:"操作失败！", cls:"error"});
			}
		});
	}
	// 关联操作项-工作组					end
	
	// 关联操作项-专家					start
	function loadCzlcExpertUncheckInfo(id) {
				
		var data = {};
		data.epiSttsIndc = "1";
		data.epaPlanFid = $("#inputId_yabz_planId").val();
		data.epaMethodFid = $("#inputId_yabz_czlcMethodId").val();
		data.actionExist = "0";
		
		$.loading({text:"正在读取中，请稍后..."});
		
		$.ajax({
			type : 'post',
			url : '${ctx}/yjct/zjgl/searchAllData',
			data : data,
			dataType : 'json',
			success : function(data) {
				$.loading("hide");
				
				czlcExpertDatas = data;
				initCzlcExpertUncheckInfo();				 				
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$.loading("hide");
				$.message({message:"获取数据失败！", cls:"error"});
			}
		});
	}
	
	function loadCzlcExpertCheckInfo(id) {
		
		var data = {};
		data.epaPlanFid = $("#inputId_yabz_planId").val();
 		data.epaMethodFid = $("#inputId_yabz_czlcMethodId").val();
		data.epaRelActionType = "3";
		
		$.ajax({
			type : 'post',
			url : '${ctx}/yjct/yacz/searchAllData',
			data : data,
			dataType : 'json',
			success : function(data) {
 					
				czlcExpertCheckDatas = data;
				initCzlcExpertCheckInfo();				
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$.message({message:"获取数据失败！", cls:"error"});
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
			
		    unCheckTd = $("<li class='selectHid'>" + czlcExpertDatas[i].epiExpertName + "</li>");
		    unCheckTd.attr({
				id: czlcExpertDatas[i].id,
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
			
		    checkTd = $("<li class='selectHid'>" + czlcExpertCheckDatas[i].epiExpertName + "</li>");
		    checkTd.attr({
				id: czlcExpertCheckDatas[i].id,
				value: czlcExpertCheckDatas[i].epiExpertName,
				'sort-num' : i
			}).bind('dblclick', function() {
				
				czlcExpertMoveToUnChecked(this);
			});
		    
		    checkTd.appendTo(expertChecked);
		}
	}
	
	function saveOrUpdateExpert() {
		
		var flag = checkSelectCzff();
		if(!flag) {
			$.message({message:"请选择处置方法！", cls:"waring"});
			return;
		}
		
		if(czlcExpertCheckDatas.length == 0) {
			$.message({message:"请选择专家！", cls:"waring"});
			return;
		}
		
		var data = {};
		data.id = $("#inputId_yabz_planId").val();
		for(var i = 0; i < czlcExpertCheckDatas.length; i++) {
			data["yaczList[" + i +"].epaPlanFid"] = $("#inputId_yabz_planId").val();
			data["yaczList[" + i +"].epaMethodFid"] = $("#inputId_yabz_czlcMethodId").val();
			data["yaczList[" + i +"].epaRelActionType"] = 3;
			data["yaczList[" + i +"].epaRelActionFid"] = czlcExpertCheckDatas[i].id;
		}
	
		$.loading({text:"正在处理中，请稍后..."});
		
		$.ajax({
			type : 'post',
			url : '${ctx}/yjct/yabz/saveOrUpdatePlanAction',
			data: data,
			dataType : 'json',
			success : function(data) {
				$.loading("hide");
				
				if(data.code == "1") {
					if(data.data != "") {
						$.message({message:data.data, cls:"waring"});
					} else {
						$.message({message:"操作成功！", cls:"success"});
					}
				} else {
					$.message({message:"操作失败！", cls:"error"});
				}				
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$.loading("hide");
				$.message({message:"操作失败！", cls:"error"});
			}
		});
	}
	// 关联操作项-专家					end
	
	// 关联操作项-物资					start
	function loadCzlcMaterialInfo() {
		
		var data = {};
		data.mriStatus = "1";
		data.epaPlanFid = $("#inputId_yabz_planId").val();
		data.epaMethodFid = $("#inputId_yabz_czlcMethodId").val();
		data.actionExist = "0";
		
		$.ajax({
			type : 'post',
			url : '${ctx}/yjct/wzgl/searchAllData',
			data : data,
			dataType : 'json',
			success : function(data) {
 					
				var materialDatas = data;
				var dataArr = [];
				for(var i = 0; i < materialDatas.length; i++) {
					var data = {};
					data.value = materialDatas[i].id + "_" + materialDatas[i].mriCount;
					data.text = materialDatas[i].mriMaterialName;
					dataArr[i] = data;
				}
				
				$("#comboboxId_yabz_edit_materialName").combobox("reload", dataArr);				 				
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$.message({message:"获取数据失败！", cls:"error"});
			}
		});
	}
	
	function loadCzlcMaterialCheckInfo() {
		
		$("#tableId_yabz_edit_materialSelect").html("");
		
		var data = {};
		data.epaPlanFid = $("#inputId_yabz_planId").val();
 		data.epaMethodFid = $("#inputId_yabz_czlcMethodId").val();
		data.epaRelActionType = "4";
		
		$.loading({text:"正在读取中，请稍后..."});
		
		$.ajax({
			type : 'post',
			url : '${ctx}/yjct/yacz/searchAllData',
			data : data,
			dataType : 'json',
			success : function(data) {
				$.loading("hide");
				
				var materialDatas = data;
				var dataArr = [];
				for(var i = 0; i < materialDatas.length; i++) {
					var data = {};						 
					data.id = materialDatas[i].id;
					data.name = materialDatas[i].mriMaterialName;
					data.total = materialDatas[i].mriCount;
					data.count =  materialDatas[i].epaRemark;						
					dataArr[i] = data;
				}
				
				initMaterialInfo(dataArr);				 			
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$.loading("hide");
				$.message({message:"获取数据失败！", cls:"error"});
			}
		});
	}
	
	function updateMaterialTotal(event, ui) {
				
		$("#comboboxId_yabz_edit_materialTotal").val(ui.newValue.split("_")[1]);
	}
	
	function selectMaterial() {
			
		var dataArr = [];
		var data = {};
		data.id = $("#comboboxId_yabz_edit_materialName").combobox("getValue").split("_")[0];
		data.name = $("#comboboxId_yabz_edit_materialName").combobox("getText");
		data.total = $("#comboboxId_yabz_edit_materialTotal").val();
		data.count =  $("#comboboxId_yabz_edit_materialNum").val();
		dataArr.push(data);

		if(data.id == "") {
			$.message({message:"请选择物资！", cls:"waring"});
			return;
		}
		
		if(data.count == "") {
			$.message({message:"请填写所需数量！", cls:"waring"});
			return;
		}
		
		if(Number(data.count) > Number(data.total)) {
			$.message({message:"所需数量不能大于总数！", cls:"waring"});
			return;
		}
		
		initMaterialInfo(dataArr);
		
		$("#comboboxId_yabz_edit_materialName").combobox('setValue', '');
		$("#comboboxId_yabz_edit_materialTotal").val('');
		$("#comboboxId_yabz_edit_materialNum").val('');
	}
	
	function deleteMaterial(trId, materialId) {
		
		$(trId).remove();					 
	}
	
	function initMaterialInfo(data) {
		
		for(var i = 0; i < data.length; i++) {
			var trId = "trId_yabz_edit_material_info_" + pos;
			var html = "<tr id='" + trId + "'>" +
				"<td width='80'>物资名称：</td>" +
				"<td><input readonly='readonly' id='inputId_yabz_edit_material_name_" + pos + "' value='" + data[i].name + "' style='width: 130px; height: 22px;' />" +
				"<input readonly='readonly' type='hidden' id='inputId_yabz_edit_material_id_" + pos + "' value='" + data[i].id + "' />" +	
	 			"</td>" + 
				"<td width='80'>总数：</td>" +
				"<td><input readonly='readonly' id='inputId_yabz_edit_material_total_" + pos + "' value='" + data[i].total + "' style='width: 50px; height: 22px;' /></td>" +
				"<td width='80'>所需数量：</td>" +
				"<td><input id='inputId_yabz_edit_material_count_" + pos + "' value='" + data[i].count + "' style='width: 50px; height: 22px;' /></td>" +			
				"<td>&nbsp;&nbsp;&nbsp;<a onclick='deleteMaterial(\"#" + trId + "\", null)'>删除</a></td>" +
				"</tr>";
				
			$("#tableId_yabz_edit_materialSelect").append(html);			
			pos++;
		} 		
	}
	
	function saveOrUpdateMaterial() {
		
		var flag = checkSelectCzff();
		if(!flag) {
			$.message({message:"请选择处置方法！", cls:"waring"});
			return;
		}
		
		var data = {};
		data.id = $("#inputId_yabz_planId").val();
		
		var index = 0;
		for(var i = 0; i < pos; i++) {
			if($("#inputId_yabz_edit_material_id_" + i).length > 0) {
				data["yaczList[" + index +"].epaPlanFid"] = $("#inputId_yabz_planId").val();
				data["yaczList[" + index +"].epaMethodFid"] = $("#inputId_yabz_czlcMethodId").val();
				data["yaczList[" + index +"].epaRelActionType"] = 4;
				data["yaczList[" + index +"].epaRelActionFid"] = $("#inputId_yabz_edit_material_id_" + i).val();
				data["yaczList[" + index +"].epaRemark"] = $("#inputId_yabz_edit_material_count_" + i).val();
				index++;
			}			
		}
		
		if(index == 0) {
			$.message({message:"请选择物资！", cls:"waring"});
			return;
		}
		
		$.loading({text:"正在处理中，请稍后..."});
		
		$.ajax({
			type : 'post',
			url : '${ctx}/yjct/yabz/saveOrUpdatePlanAction',
			data: data,
			dataType : 'json',
			success : function(data) {
				$.loading("hide");
				
				if(data.code == "1") {
					if(data.data != "") {
						$.message({message:data.data, cls:"waring"});
					} else {
						$.message({message:"操作成功！", cls:"success"});
					}		
				} else {
					$.message({message:"操作失败！", cls:"error"});
				}				
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$.loading("hide");
				$.message({message:"操作失败！", cls:"error"});
			}
		});
	}
	// 关联操作项-物资					end
	
	// 关联操作项-法规					start
	function loadCzlcLawInfo() {
		
		var data = {};
		data.lpiSttsIndc = "1";
		data.epaPlanFid = $("#inputId_yabz_planId").val();
		data.epaMethodFid = $("#inputId_yabz_czlcMethodId").val();
		data.actionExist = "0";
		
		$.ajax({
			type : 'post',
			url : '${ctx}/yjct/fggl/searchAllData',
			data : data,
			dataType : 'json',
			success : function(data) {
 					
				var lawDatas = data;
				var dataArr = [];
				for(var i = 0; i < lawDatas.length; i++) {
					var data = {};
					data.value = lawDatas[i].id + "_" + lawDatas[i].lriLawsContent;
					data.text = lawDatas[i].lriLawsName;
					dataArr[i] = data;
				}
				
				$("#comboboxId_yabz_edit_lawName").combobox("reload", dataArr);				 			
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$.message({message:"获取数据失败！", cls:"error"});
			}
		});
	}
	
	function loadCzlcLawCheckInfo() {
		
		$("#tableId_yabz_edit_lawSelect").html("");
		
		var data = {};
		data.epaPlanFid = $("#inputId_yabz_planId").val();
 		data.epaMethodFid = $("#inputId_yabz_czlcMethodId").val();
		data.epaRelActionType = "5";
		
		$.loading({text:"正在读取中，请稍后..."});
		
		$.ajax({
			type : 'post',
			url : '${ctx}/yjct/yacz/searchAllData',
			data : data,
			dataType : 'json',
			success : function(data) {
				$.loading("hide");
				
				var lawDatas = data;
				var dataArr = [];
				for(var i = 0; i < lawDatas.length; i++) {
					var data = {};						 
					data.id = lawDatas[i].id;
					data.name = lawDatas[i].lriLawsName;
					data.remark =  lawDatas[i].lriLawsContent;						
					dataArr[i] = data;
				}
				
				initLawInfo(dataArr);				 			
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$.loading("hide");
				$.message({message:"获取数据失败！", cls:"error"});
			}
		});
	}
	
	function updateLawRemark(event, ui) {
		
		$("#comboboxId_yabz_edit_lawRemark").val(ui.newValue.split("_")[1]);
	}
	
	function selectLaw() {
		
		var dataArr = [];
		var data = {};
		data.id = $("#comboboxId_yabz_edit_lawName").combobox("getValue").split("_")[0];
		data.name = $("#comboboxId_yabz_edit_lawName").combobox("getText");
		data.remark =  $("#comboboxId_yabz_edit_lawRemark").val();
		dataArr.push(data);
		
		if(data.id == "") {
			$.message({message:"请选择法规！", cls:"waring"});
			return;
		}
		
 		initLawInfo(dataArr);
 			
		$("#comboboxId_yabz_edit_lawName").combobox('setValue', '');
		$("#comboboxId_yabz_edit_lawRemark").val('');
		lpos++;
	}
	
	function deleteLaw(trId, lawId) {
		
		$(trId).remove();					
	}
	
	function initLawInfo(data) {
		
		for(var i = 0; i < data.length; i++) {
			var trId = "trId_yabz_edit_law_info_" + lpos;
			var html = "<tr id='" + trId + "'>" +
				"<td width='80'>法规名称：</td>" +
				"<td><input readonly='readonly' id='inputId_yabz_edit_law_name_" + lpos + "' value='" + data[i].name + "' style='width: 100px; height: 22px;' />" +
				"<input readonly='readonly' type='hidden' id='inputId_yabz_edit_law_id_" + lpos + "' value='" + data[i].id + "' />" +	
	 			"</td>" + 
				"<td width='80'>备注：</td>" +
				"<td><input readonly='readonly' id='inputId_yabz_edit_law_remark_" + lpos + "' value='" + data[i].remark + "' style='width: 220px; height: 22px;' /></td>" +			 
				"<td>&nbsp;&nbsp;&nbsp;<a onclick='deleteLaw(\"#" + trId + "\", null)'>删除</a></td>" +
				"</tr>";
			
			$("#tableId_yabz_edit_lawSelect").append(html);
			lpos++;
		}		
	}
	//法律法规
	function saveOrUpdateLaw() {
		
		var flag = checkSelectCzff();
		if(!flag) {
			$.message({message:"请选择处置方法！", cls:"waring"});
			return;
		}
		
		var data = {};
		data.id = $("#inputId_yabz_planId").val();
		
		var index = 0;
		for(var i = 0; i < lpos; i++) {
			if($("#inputId_yabz_edit_law_id_" + i).length > 0) {
				data["yaczList[" + index +"].epaPlanFid"] = $("#inputId_yabz_planId").val();
				data["yaczList[" + index +"].epaMethodFid"] = $("#inputId_yabz_czlcMethodId").val();
				data["yaczList[" + index +"].epaRelActionType"] = 5;
				data["yaczList[" + index +"].epaRelActionFid"] = $("#inputId_yabz_edit_law_id_" + i).val();
				data["yaczList[" + index +"].epaRemark"] = $("#inputId_yabz_edit_law_remark_" + i).val();
				index++;
			}			
		}
		
		if(index == 0) {
			$.message({message:"请选择法规！", cls:"waring"});
			return;
		}
		
		$.loading({text:"正在处理中，请稍后..."});
		
		$.ajax({
			type : 'post',
			url : '${ctx}/yjct/yabz/saveOrUpdatePlanAction',
			data: data,
			dataType : 'json',
			success : function(data) {
				$.loading("hide");
				
				if(data.code == "1") {
					if(data.data != "") {
						$.message({message:data.data, cls:"waring"});
					} else {
						$.message({message:"操作成功！", cls:"success"});
					}
				} else {
					$.message({message:"操作失败！", cls:"error"});
				}				
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$.loading("hide");
				$.message({message:"操作失败！", cls:"error"});
			}
		});
	}
	// 关联操作项-法规					end
	
	function checkSelectCzff() {
		
		var czlcMethodId = $("#inputId_yabz_czlcMethodId").val();
		var flag = false;
		for(var i = 0; i < czlcCheckDatas.length; i++) {
			if(czlcCheckDatas[i].id == czlcMethodId) {
				flag = true;
				break;
			}
		}
		
		return flag;
	}
	// 预案处置流程-关联操作项			end
	
	function updateYafbsp() {
		
		var selarrrow = $("#gridId_yabz").grid("option", "selarrrow");			
		if(selarrrow && selarrrow.length == 1) {
			var data = {};
			data.id = selarrrow[0];
			data.epiPlanStatus = "1";
			
			var rowData = $("#gridId_yabz").grid("getRowData", selarrrow[0]);
			if(rowData.epiPlanStatus != "0") {
				$.message({message:"请选择状态为新建中的记录！", cls:"waring"});
				return;
			}
			
			$.loading({text:"正在处理中，请稍后..."});
			
			$.ajax({
				type : 'post',
				url : '${ctx}/yjct/yabz/updatePlanStatus',
				data: data,
				dataType : 'json',
				success : function(data) {
					$.loading("hide");
					
					if(data.code == "1") {
						$.message({message:"操作成功！", cls:"success"});
						$("#gridId_yabz").grid("reload");
					} else {
						$.message({message:"操作失败！", cls:"error"});
					}				
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					$.loading("hide");
					$.message({message:"操作失败！", cls:"error"});
				}
			});
		} else {
			$.message({message:"请选择一条记录！", cls:"waring"});
			return;
		}		
	}
	
	function deleteByIds() {
		
		var selarrrow = $("#gridId_yabz").grid("option", "selarrrow");			
		if(selarrrow && selarrrow.length > 0) {
			$.confirm("确认删除？", function(r) {
				if(r) {
					
					var ids = "";
					for(var i = 0; i < selarrrow.length; i++) {
						ids += selarrrow[i] + ",";
						
						var rowData = $("#gridId_yabz").grid("getRowData", selarrrow[i]);
						if(rowData.epiPlanStatus != "0") {
							$.message({message:"请选择状态为新建中的记录！", cls:"waring"});
							return;
						}
					}
					
					$.loading({text:"正在处理中，请稍后..."});
					
					$.ajax({
						type : 'post',
						url : '${ctx}/yjct/yabz/deleteByIds',
						data: {
							ids : ids
						},
						dataType : 'json',
						success : function(data) {
							$.loading("hide");
							
							if(data.code == "1") {
								$.message({message:"操作成功！", cls:"success"});
								$("#gridId_yabz").grid("reload");
							} else {
								$.message({message:"操作失败！", cls:"error"});
							}				
						},
						error : function(XMLHttpRequest, textStatus, errorThrown) {
							$.loading("hide");
							$.message({message:"操作失败！", cls:"error"});
						}
					});
				}
			}); 		
		} else {
			$.message({message:"请选择记录！", cls:"waring"});
			return;
		}
	}
	
	function copyYaxx() {
		
		var selarrrow = $("#gridId_yabz").grid("option", "selarrrow");			
		if(selarrrow && selarrrow.length == 1) {
			$.confirm("确认复制此预案信息？", function(r) {
				if(r) {
					
					var rowData = $("#gridId_yabz").grid("getRowData", selarrrow[0]);
					if(rowData.epiPlanStatus != "6") {
						$.message({message:"请选择状态为修订中的记录！", cls:"waring"});
						return;
					}
					
					$.loading({text:"正在处理中，请稍后..."});
					
					$.ajax({
						type : 'post',
						url : '${ctx}/yjct/yabz/copyYaxx',
						data: {
							id : selarrrow[0]
						},
						dataType : 'json',
						success : function(data) {
							$.loading("hide");
							
							if(data.code == "1") {
								$.message({message:"操作成功！", cls:"success"});
								$("#gridId_yabz").grid("reload");
							} else {
								$.message({message:"操作失败！", cls:"error"});
							}				
						},
						error : function(XMLHttpRequest, textStatus, errorThrown) {
							$.loading("hide");
							$.message({message:"操作失败！", cls:"error"});
						}
					});
				}
			}); 		
		} else {
			$.message({message:"请选择一条记录！", cls:"waring"});
			return;
		}
	}
</script>

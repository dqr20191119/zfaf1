<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<%@taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<link href="${ctx}/static/css/callNames.css" type="text/css" rel="stylesheet" />
<body>
	<div class="layout-wrap-dianming">
		<div class="layout-container clearfix">
			<div class="layout-main">
				<div class="layout-left">
					<cui:tree id="callNames_regionTree" simpleDataEnable="true" asyncAutoParam="id,name" asyncType="get" asyncEnable="true" onDblClick="findCellByRegion">
					</cui:tree>
				</div>
				<div class="callNames-right">
					<div id="callNames-content_1" class="callNames-content" style="display: none;">
						<h3 id="left_title_1" class="left_title">&nbsp;</h3>
						<ul id="box_1" class="js_box specialbox1">
						</ul>
					</div>
					<div id="callNames-content_2" class="callNames-content" style="display: none;">
						<h3 id="left_title_2" class="left_title">&nbsp;</h3>
						<ul id="box_2" class="js_box">
						</ul>
					</div>
					<div id="callNames-content_3" class="callNames-content" style="display: none;">
						<h3 id="left_title_3" class="left_title">&nbsp;</h3>
						<ul id="box_3" class="js_box">
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>

<cui:dialog id="dialogId_rollcall" iframePanel="true" autoOpen="false" resizable="false" reLoadOnOpen="true" modal="true"></cui:dialog>
<script>
	var jsConst = window.top.jsConst;
	var userLev = jsConst.USER_LEVEL;
	var cusNumber = jsConst.CUS_NUMBER;//监狱号
	var rollcallSatus = null;//点名状态  0-未结束   1-已结束
	$.parseDone(function() {
		$("#callNames_regionTree").tree( "reload", "${ctx}/callNames/findForTree?cusNumber=" + cusNumber + "&userLev=" + userLev);
	});

	function isEmpty(_v) {
		if (_v == undefined)
			return true;
		if (_v == null)
			return true;
		if (_v == "undefined")
			return true;
		if (_v == "")
			return true;
		return false;
	}

	function findCellByRegion(event, id, node) {
		$(".callNames-content").hide();

		$(".callNames-right").loading({
			text : "加载中。。。。",
			loadingIcon : "coral-icon-loading"
		});

		var dempt = null;
		var area = null;
		var cusNumber_ = null;
		var para = node.node_lev;
		if (!isEmpty(para)) {
			switch (para) {
			case "1":
				cusNumber_ = node.id;
				break;
			case "2":
				cusNumber_ = node.pId;
				dempt = node.id;
				break;
			case "3":
				dempt = node.pId;
				area = node.id;
				break;
			default:
				return;
			}

			var data = {};
			if (!isEmpty(area)) {
				data['area'] = area;
			}
			if (!isEmpty(dempt)) {
				data['dempt'] = dempt;
			}
			if (!isEmpty(cusNumber_)) {
				data['cusNumber'] = cusNumber_;
			}
			if (!isEmpty(para)) {
				data['para'] = para;
			}
 
			$.ajax({
				type : "post",
				url : "${ctx}/callNames/findPrisonerNum.json",
				data : data,
				dataType : "json",
				success : function(data) {
					creatHtmlForShow(para, node.name, data);
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					$.messageQueue({ message : textStatus, cls : "warning", iframePanel : true, type : "info" });
				}
			});

		}

	}

	function creatHtmlForShow(para, areaName, data) {
		$("#callNames-content_" + para).show();
		if (para == "1") {
			$("#left_title_" + para).text(areaName);
			$("#box_1").empty()
			if (data != undefined && data != "undefined" && data.length > 0) {
				for (var i = 0; i < data.length; i++) {
					var jqData = data[i];
					var div = " <li> <div class=\"zt_title\"> <p>"
							+ jqData.DEMPT
							+ "</p>  </div> <div class=\"zt_h\"> <a href=\"#\"> \r\n"
							+ "<div> <p> <span>0</span> /" + jqData.PRISONERNUM
							+ "</p> </div> </a> <p>状态 : 未完成</p> </div> </li>";
					$("#box_1").append(div);
				}
			} else {
				$("#box_1").append("<span> 暂无数据</span>");
			}

		}
		if (para == "2") {
			$("#box_2").empty()
			if (data != undefined && data != "undefined" && data.length > 0) {
				for (var i = 0; i < data.length; i++) {
					var jqData = data[i];
					var div = " <li> <div class=\"zt_title\"> <p>"
							+ areaName
							+ "</p>  </div> <div class=\"zt_h\"> <a href=\"#\"> \r\n"
							+ "<div> <p> <span>0</span> /" + jqData.PRISONERNUM
							+ "</p> </div> </a> <p>状态 : 未完成</p> </div> </li>";
					$("#box_2").append(div);
				}
			} else {
				$("#box_2").append("<span> 暂无数据</span>");
			}
		}

		if (para == "3") {
			$("#left_title_" + para).text(areaName);
			$("#box_3").empty()
			if (data != undefined && data != "undefined" && data.length > 0) {
				for (var i = 0; i < data.length; i++) {
					var cell = data[i];
					peopleNum = 0;
					var div = " <li class=\""+ cell.AREAID +"\"> <div class=\"zt_title\"> <p>"
							+ cell.CELLNO
							+ "监舍</p>  </div> <div id=\"" + cell.DEMPTID + "-" + cell.AREAID + "-" + cell.CELLNO 
							+"\" class=\"zt_h\"> <a href=\"#\" onclick=\"openRollcallDailog('" + cell.DEMPTID + "','" + cell.AREAID + "','" + cell.CELLNO + "')\">"
							+ "<div> <p> <span class =\"peopleTotal\">0</span> /" + cell.PRISONERNUM
							+ "</p> </div> </a> <p class=\"rollcallSatus\">状态 : 未完成</p> </div> </li>";
					$("#box_3").append(div);
				}

				getNumber(data[0].DEMPTID,data[0].AREAID);//获取监舍信息
			} else {
				$("#box_3").append("<span> 暂无数据</span>");
			}
		}

		$(".callNames-right").loading("hide");
	}

	function getNumber(demptId,areaId) {
		if (!isEmpty(demptId)) {
			$.ajax({
				type : "post",
				url : "${ctx}/callNames/getNumber.json?rollcallId=${ID}&demptId=" + demptId,
				dataType : "json",
				success : function(data) {
					if (data.success) {
						rollcallSatus = data.obj.rollcallSatus;
						var demptId = data.obj.demptId;//部门id
						//var demptName = data.obj.demptName;//部门name
						//var peopleTotal = data.obj.peopleTotal;//总人数
						//var arrivedTotal = data.obj.arrivedTotal;//已点名人数
						var floorId = data.obj.floorData[0].floorId;//楼层id
						//var floorName = data.obj.floorData.floorData.floorName;//楼层name
						var houseData = data.obj.floorData[0].houseData;//楼层所有监舍信息
						
						for (var i = 0; i < houseData.length; i++) {
							var cellDate = houseData[i];
							$("#"+demptId+"-"+floorId+"-"+cellDate.cellId + " .peopleTotal").text(cellDate.arrivedTotal);
							
							if(cellDate.arrivedTotal == cellDate.peopleTotal && cellDate.arrivedTotal != 0){
								if($("#"+demptId+"-"+floorId+"-"+cellDate.cellId).hasClass('zt_h')){
									$("#"+demptId+"-"+floorId+"-"+cellDate.cellId).attr('class', 'zt_done' ); 
									$("#"+demptId+"-"+floorId+"-"+cellDate.cellId + " .rollcallSatus").text("状态 : 已完成");	
								}
							}
						}
					 
						if(rollcallSatus == "0" || rollcallSatus == 0){
						 
							if($("#callNames-content_3 #box_3 li:first").hasClass(floorId)){
								setTimeout(function(){
									getNumber(demptId,areaId);
								},3 * 1000);
							 } else {
								 rollcallSatus = null;
							 }
						} else {
							saveEndRollcallList("${ID}");
						}
					} else {
						$.messageQueue({ message : data.msg, cls : "warning", iframePanel : true, type : "info" });
					}
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					$.messageQueue({ message : textStatus, cls : "warning", iframePanel : true, type : "info" });
				}
			});
		}
	}
	
	
	function openRollcallDailog(demptId,floorId,cellId) {
		$("#dialogId_rollcall").dialog({
			width : 1200,
			height : 800,
			url : "${ctx}/callNames/openDialog/rollcall?rollcallId=${ID}&demptId="+demptId+"&floorId="+floorId+"&cellId="+cellId,
			title : '人员列表',
		});
		$("#dialogId_rollcall").dialog("open");
	}
	
</script>
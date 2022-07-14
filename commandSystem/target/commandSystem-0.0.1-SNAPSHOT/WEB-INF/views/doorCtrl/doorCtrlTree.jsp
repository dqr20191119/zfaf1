<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.cesgroup.prison.common.facade.AuthSystemFacade"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<link rel="stylesheet" type="text/css"
	href="${ctx}/static/module/video/css/rightVideoPlan.css" />

<style type="text/css">
    .gridtable {
        font-family: verdana,arial,sans-serif;
        font-size:11px;
        color:chartreuse;
        border-width: 1px;
        border-color: #666666;
        border-collapse: collapse;
        margin: auto;
        white-space: nowrap;
    }
    .gridtable th {
        border-width: 1px;
        padding: 8px;
        border-style: solid;
        border-color: #666666;
        color: #4de3e2;
        text-align: center;
    }
    .gridtable td {
        border-width: 1px;
        padding: 8px;
        border-style: solid;
        border-color: #666666;
        text-align: center;
    }
    .search_div {
        text-align: center;
    }
    .search_div input{
        color: white;
    }

</style>

<%if(AuthSystemFacade.whatLevelForLoginUser()==2) {%>
<div class="control-door-box">
	<cui:tabs id="control-door-tabs" >
		<ul >
            <li>
                <a href="#fragment-2">门禁状态</a>
            </li>
			<li>
				<a href="#fragment-1" >门禁控制</a>
			</li>
			<%--<li>
				<a href="#fragment-3">待启用预案</a>
			</li>--%>
		</ul>
		<div id="fragment-1" style="height: 100%; background: none;">
            <div class="search_div">
                <cui:input  texticons="" placeholder="搜索"   onKeyPress="regiondoorCtrlSearch"></cui:input>
            </div>
			<div class="tree-tabs-box1">
				<cui:tree id="searchRegion" asyncEnable="true" keepParent="true" rootNode="true" showRootNode="true" asyncType="post" asyncAutoParam="id,name" checkable="true" onLoad="treeOnLoad"></cui:tree>
			</div>
			<div class="button-tabs-box">
				<cui:button label="开门" onClick="ctrlDoor('searchRegion','1')"></cui:button>
				<cui:button label="关门" onClick="ctrlDoor('searchRegion','4')"></cui:button>
				<cui:button label="摄像" onClick="playVideo('searchRegion')"></cui:button>
				<cui:button label="常闭" onClick="openDialog_door"></cui:button>
				<cui:button label="恢复" onClick="ctrlDoor('3')"></cui:button>
			</div>
		</div>
		<div id="fragment-2" style="height: 100%; background: none;overflow: auto;">
			<%--<div class="tree-tabs-box1">
				<cui:tree id="planTree_1" asyncEnable="true" keepParent="true" rootNode="true" showRootNode="true" asyncType="post" asyncAutoParam="id,name" checkable="true" onLoad="treeOnLoad"></cui:tree>
			</div>--%>
			<%--<div class="button-tabs-box">
				<cui:button label="停用" onClick="ctrlDoorPlan('planTree_1','0')"></cui:button>
				<cui:button label="摄像" onClick="playVideo('planTree_1')"></cui:button>
				<cui:button label="常闭" onClick="ctrlDoor('planTree_1','2',254)"></cui:button>
				<cui:button label="恢复" onClick="ctrlDoor('planTree_1','3')"></cui:button>
			</div>--%>
                <div class="search_div">
                    <cui:input  texticons="" placeholder="搜索"   onEnter="regiondoorStatusSearch"></cui:input>
                </div>
                <table class="gridtable"  id="doorStatus">
                    <tr>
                        <th>门禁名称</th><th>状态</th>
                    </tr>
                    <%--<tr>
                        <td>1</td><td>2号监舍操场右</td><td>开</td>
                    </tr>
                    <tr>
                        <td>2</td><td>3号监舍3F通道左</td><td>关</td>
                    </tr>--%>
                </table>
		</div>
		<div id="fragment-3" style="height: 100%; background: none;">
			<div class="tree-tabs-box1">
				<cui:tree id="planTree_0" asyncEnable="true" keepParent="true" rootNode="true" showRootNode="true" asyncType="post" asyncAutoParam="id,name" checkable="true" onLoad="treeOnLoad"></cui:tree>
			</div>
	
			<div class="button-tabs-box">
				<cui:button label="启用" onClick="ctrlDoorPlan('planTree_0','1')"></cui:button>
			</div>
		</div>
	</cui:tabs>
</div>
<%} else {%> 
	 <div class="rightDivStyle right-zb mjkz-box2">
		<h4>门禁控制</h4>
         <div class="search_div">
             <cui:input  texticons="" placeholder="搜索"   onKeyPress="regiondoorCtrlSearch"></cui:input>
         </div>
		<div class="tree-box">
			<cui:tree id="searchRegion" asyncEnable="true" keepParent="true" rootNode="true" showRootNode="true" asyncType="post" asyncAutoParam="id,name" checkable="true" onLoad="treeOnLoad"></cui:tree>
		</div>
		<div class="button-box">
			<cui:button label="开门" onClick="ctrlDoor('searchRegion','1')"></cui:button>
			<cui:button label="关门" onClick="ctrlDoor('searchRegion','4')"></cui:button>
			<cui:button label="摄像" onClick="playVideo('searchRegion')"></cui:button>
			<cui:button label="常闭" onClick="openDialog_door"></cui:button>
			<cui:button label="恢复" onClick="ctrlDoor('3')"></cui:button>
		</div>
	</div>
<%}%>

<cui:dialog id="dialogId_door" iframePanel="true" autoOpen="false" resizable="false" autoDestroy="true" modal="true" buttons="dialogId_door_button">
	<table class="table" style="width: 98%">
		<tr>
			<th>常闭有效时间（分钟）：</th>
			<td>
				<cui:input id="input_time" validType="integer" componentCls="form-control"></cui:input>
			</td>
		</tr>
	</table>
</cui:dialog>

<cui:dialog id="dialogId_door_open" iframePanel="true" autoOpen="false" resizable="false" autoDestroy="true" modal="true" buttons="dialogId_door__open_button">
	<table class="table" style="width: 98%">
		<tr>
			<th>请输入门禁开启密码：</th>
			<td>
				<cui:input id="input_password" componentCls="form-control" type="password" ></cui:input>
			</td>
		</tr>
	</table>
</cui:dialog>




<script type="text/javascript">
	var cusNumber = jsConst.USER_LEVEL == 1 ? "" : jsConst.CUS_NUMBER;
	$.parseDone(function() {
		//$("#searchRegion").tree( "reload", "${ctx}/common/areadevice/findForCombotree.json?cusNumber=" + cusNumber + "&deviceType=4");
		$("#searchRegion").tree( "reload", "${ctx}/door/findForTree.json?cusNumber=" + cusNumber);
		if(jsConst.USER_LEVEL == 2){
			$("#planTree_0").tree( "reload", "${ctx}/door/plan/findForTree.json?cusNumber=" + cusNumber + "&stts=0");
			$("#planTree_1").tree( "reload", "${ctx}/door/plan/findForTree.json?cusNumber=" + cusNumber + "&stts=1");	
		}

		//加载门禁状态列表
        $.ajax({
            type : "post",
            url : "${ctx}/doorstate/all/listAllByDoorStateEntity",
            data : {dsdCusNumber:cusNumber
                    },
            dataType : "json",
            success : function(data) {
                if (data.code == 200) {
                    var doorList = data.data;
                    $("#doorStatus").html("");
                    if (doorList.length > 0) {
                        for (var i = 0; i < doorList.length; i++) {
                            $("#doorStatus").append("<tr>" +
                                "<td>" + doorList[i].dsdDoorName + "</td><td>" + doorList[i].dsdDoorState + "</td>" +
                                "</tr>");
                        }
                    } else {
                        $.messageQueue({message: data.msg, cls: "warning", iframePanel: true, type: "info"});
                    }
                }
            },
            error : function(XMLHttpRequest, textStatus, errorThrown) {
                $.messageQueue({ message : textStatus, cls : "warning", iframePanel : true, type : "info" });
            }
        });

	});

	function playVideo(tree) {
		var list = $("#" + tree).tree("getCheckedNodes");
		var camera = [];
		var msg = "";
		for (var i = 0; i < list.length; i++) {
			var row = list[i];
			if (row.isParent == false) {
				var camera_id = row.cameraId;
				if(camera_id != undefined && camera_id != null && camera_id != ""){
					camera = camera.concat(camera_id.split(','));//add by long ge 
				} else {
					msg = msg + row.name + " ";
				}
			}
		}
		// console.log("camera array is " + JSON.stringify(camera));
		if(camera.length > 0){
			videoClient.playVideoHandle({ 'subType' : 2, 'layout' : camera.length, 'data' : camera });
		}
		if(msg != ""){
			$.messageQueue({ message : "门禁【 " + msg +"】未关联摄像头", cls : "warning", iframePanel : true, type : "info" });
		}
	}
	
	function ctrlDoor(tree,action, time) {
		if(action==1){
			//var doorPassword =prompt("请输入本监狱门禁开启密码");
			dialogId_door_open(tree,action, time);
			
		}else{
			 playVideo(tree);
				var list = $("#" + tree).tree("getCheckedNodes");
				var doorIds = [];
				for (var i = 0; i < list.length; i++) {
					var row = list[i];
					if (row.isParent == false) {
						doorIds.push(row.id);
					}
				}
				if (doorIds.length > 0) {
					controlDoor(doorIds, action, time);
				} else {
					$.messageQueue({ message : "请选择要操作的门禁设备", cls : "warning", iframePanel : true, type : "info" });
				}
		}
		
       
	}

	//后台请求操作  
	function controlDoor(doorIds, action, time) {
		var data = {};
		data["doorIds"] = JSON.stringify(doorIds);
		data["action"] = action;//1 开门 2禁止开门 3恢复开门 4关门
		data["time"] = time;
		var ur = "${ctx}/doorlinkage/controlDoor.json";``
		$.ajax({
			type : "post",
			url : ur,
			data : data,
			dataType : "json",
			success : function(data) {
				if (data.success) {
					$.messageQueue({ message : data.msg, cls : "success", iframePanel : true, type : "info" });

				} else {
					$.messageQueue({ message : data.msg, cls : "warning", iframePanel : true, type : "info" });
				}
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$.messageQueue({ message : textStatus, cls : "warning", iframePanel : true, type : "info" });
			}
		});
	}
	
	function dialogId_door_open(tree,action, time) {
		$("#dialogId_door_open").dialog({
			width : 360,
			height : 150,
			title : '门禁开启操作',
		});
		$("#dialogId_door_open").dialog("open");
	}
	
	function closeDoorOpen(tree,action, time) {
		var password = $('#input_password').textbox("getText");
		var url = "${ctx}/dooropen/getById.json?jyid=" + cusNumber;
		$.ajax({
			type : "post",
			url : url,
			dataType : "json",
			success : function(data) {
				if(data.success){
					if(password==data.obj.openPassword){
						$("#dialogId_door_open").dialog("close");
						debugger;
						 playVideo(tree);
							var list = $("#" + tree).tree("getCheckedNodes");
							var doorIds = [];
							for (var i = 0; i < list.length; i++) {
								var row = list[i];
								if (row.isParent == false) {
									doorIds.push(row.id);
								}
							}
							if (doorIds.length > 0) {
								controlDoor(doorIds, action, time);
							} else {
								$.messageQueue({ message : "请选择要操作的门禁设备", cls : "warning", iframePanel : true, type : "info" });
							}
						}else{
							$.alert("密码不对,请重新输入!")
						}
					}else{
						$.alert("本监狱未设置门禁开启密码,请到系统管理员设置门禁开启密码!")
					}
				}
		});
		
	}
	
	function openDialog_door() {
		$("#dialogId_door").dialog({
			width : 360,
			height : 150,
			title : '常闭操作',
		});
		$("#dialogId_door").dialog("open");
	}

	
	function closeDoor() {
		var time = $('#input_time').textbox("getText");
		if (time > 0 && time < 255) {
			ctrlDoor('searchRegion',"2", time);
			$("#dialogId_door").dialog("close");
		} else {
			$.messageQueue({ message : "请输入1-254之间的整数", cls : "warning", iframePanel : true, type : "info" });
		}
	}

	var dialogId_door_button = [ {
		text : "确认",
		click : function() {
			closeDoor();
		}
	}, {
		text : "关闭",
		click : function() {
			$("#dialogId_door").dialog("close");
		}
	} ];
	
	
	var dialogId_door__open_button=[ {
		text : "确认",
		click : function(tree,action, time) {
			var action = 1;
			var tree= "searchRegion";
			closeDoorOpen(tree,action, time);
		}
	}, {
		text : "关闭",
		click : function() {
			$("#dialogId_door_open").dialog("close");
		}
	} ];
	
	/* 门禁预案控制  stts 0 未生效  1 已生效*/
	function ctrlDoorPlan(tree,stts) {
		var list = $("#" + tree).tree("getCheckedNodes");
		var doorPlanIds = [];
		for (var i = 0; i < list.length; i++) {
			var row = list[i];
			if (row.isParent == true) {
				doorPlanIds.push(row.id);
			}
		}
		if(doorPlanIds.length > 0){
			$.ajax({
				url : '${ctx}/door/plan/ctrlDoorPlan.json?stts=' + stts,
				dataType : 'json',
				type : 'post',
				data : JSON.stringify(doorPlanIds),
				contentType : "application/json",
				success : function(data) {
					if (data.success) {
						$.messageQueue({ message : data.msg, cls : "success", iframePanel : true, type : "info" });
						debugger;
						$("#planTree_0").tree( "reload");
						$("#planTree_1").tree( "reload");
					} else {
						$.messageQueue({ message : data.msg, cls : "warning", iframePanel : true, type : "info" });
					}
				}
			});
		} else {
			$.messageQueue({ message : '请选择要操作的预案', cls : "warning", iframePanel : true, type : "info" });
		}
	}
	
	function treeOnLoad(event, id, data) {
		var $tree = $("#" + id);
		var parentOpenImg = "${ctx}/static/resource/images/tree/node_1.png";
		var parentCloseImg = "${ctx}/static/resource/images/tree/node_2.png";
		var doorImg = "${ctx}/static/resource/images/tree/door.png";
		var doorLockImg = "${ctx}/static/resource/images/tree/locked.png";
		if ( data == null){
			var list = $("#" + id).tree("getNodes");
			if(list.length == 0){
				return;
			}
			for ( var i = 0; i < list.length; i++) {
				var node = list[i];
				if ( !node.isParent && node.childrenNum == '0'){
					$tree.tree("removeNode", node);

				}
				/* node.iconOpen = parentOpenImg;
				node.iconClose = parentCloseImg;
				$tree.tree("updateNode",node); */
			}
		}else{
			var list = data.children;
			if (list != null && list != undefined && list.length > 0){
				for ( var i = 0; i < list.length; i++ ) {
					var node = list[i];
					if (node.isParent){
						/* node.iconOpen = parentOpenImg;
						node.iconClose = parentCloseImg; */
					} else {
						if (node.sttsIndc == '2') {
							node.icon = doorLockImg;
						}else{
							node.icon = doorImg;
						}
					}
					$tree.tree("updateNode",node); 
				}
			}
		}
	}


    //区域门禁树查询
    function regiondoorCtrlSearch(e, data) {
        //回车时触发
        if(e.keyCode == 13)
        {
            $("#searchRegion").tree("filterNodesByParam", {
                name : data.value
            });
        }
    }
    //搜索门禁状态
    function regiondoorStatusSearch(e,ui) {
        console.log("ui.value="+ui.value);
       // var dsdDoorName =ui.value;
        //加载门禁状态列表
        $("#doorStatus").html("");
        $.ajax({
            type : "post",
            url : "${ctx}/doorstate/all/listAllByDoorStateEntity",
            data : {dsdCusNumber:cusNumber,
                dsdDoorName:ui.value
            },
            dataType : "json",
            success : function(data) {
                if (data.code == 200) {
                    var doorList = data.data;
                    if (doorList.length > 0) {
                        for (var i = 0; i < doorList.length; i++) {
                            $("#doorStatus").append("<tr>" +
                                "<td>" + doorList[i].dsdDoorName + "</td><td>" + doorList[i].dsdDoorState + "</td>" +
                                "</tr>");
                        }
                    }
                }
            },
            error : function(XMLHttpRequest, textStatus, errorThrown) {
                $.messageQueue({ message : textStatus, cls : "warning", iframePanel : true, type : "info" });
            }
        });

    }

</script>

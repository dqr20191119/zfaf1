<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="sec" tagdir="/WEB-INF/tags/security"%>
<%@ page import="com.cesgroup.prison.common.facade.AuthSystemFacade"%>
<%@ page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta http-equiv="cache-control" content="no-store, no-cache, must-revalidate">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="expires" content="0">
<title>指挥调度</title>

<!-- coral4 css start  -->
<link rel="stylesheet" type="text/css" href="${ctx}/static/bj-cui/css/iconfont/iconfont.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/static/bj-cui/cui/cui.min.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/static/bj-cui/css/jquery.mCustomScrollbar.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/static/bj-cui/css/style.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/static/bj-cui/css/dialog.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/static/bj-cui/css/moxing.css" />
<!-- coral4 css  end  -->

<!-- app css define start -->
<link href="${ctx}/static/resource/style/css/common.css" type="text/css" rel="stylesheet" />
<link href="${ctx}/static/css/style.css" type="text/css" rel="stylesheet" />
<link href="${ctx}/static/resource/style/css/jquery.pagewalkthrough.css" type="text/css" rel="stylesheet" />
<link href="${ctx}/static/resource/style/css/forcoraltheme.css" type="text/css" rel="stylesheet" />
<link href="${ctx}/static/resource/style/css/body.css" type="text/css" rel="stylesheet" />
<link href="${ctx}/static/resource/style/css/inforGlobal.css" type="text/css" rel="stylesheet" />
<link href="${ctx}/static/resource/style/css/prettify.css" type="text/css" rel="stylesheet" />
<link href="${ctx}/static/font/iconfont.css" type="text/css" rel="stylesheet" />
<link href="${ctx}/static/extraFont/iconfont.css" type="text/css" rel="stylesheet" />
<link rel="stylesheet" href="${ctx}/static/css/rightSide.css" />
<link rel="stylesheet" href="${ctx}/static/css/talkbackControl.css" />
<!-- app css define end -->

<!-- coral4 js start -->
<script src="${ctx}/static/jquery-cui/js/jquery-1.11.3.min.js"></script>
<script src="${ctx}/static/bj-cui/cui/cui.js"></script>
<script src="${ctx}/static/bj-cui/js/jquery.mCustomScrollbar.js"></script>
<!-- coral4 js end -->

<!-- app js define start  -->
<script src="${ctx}/static/js/scripts/common.js"></script>
<script src="${ctx}/static/resource/style/js/function.js"></script>
<script src="${ctx}/static/js/scripts/prettify.js"></script>
<!-- app js define  end  -->

<script src="${ctx}/static/system/jsConst.js"></script>
<!-- add by tao callbackfunction 回调函数移至 callback.js -->
<script src="${ctx}/static/js/callback/callback.js"></script>

<script>
	jsConst.basePath = "${ctx}/";
</script>
<script src="${ctx}/static/system/common.js"></script>
<script src="${ctx}/static/js/echarts.min.js"></script>
<script type="text/javascript" src="${ctx}/static/module/video/js/videoPlanTimer.js"></script>
<!-- Begin add by linhe 2018-01-09 for request ajax and 3d map -->
<script src = "${ctx}/static/js/common/ajaxCommon.js"></script>
<script src="${ctx}/static/js/map/prisonmap.js"></script>
<!-- End add by linhe 2018-01-09 for request ajax and 3d map -->

<!-- 4g执法 start-->
<script src="${ctx}/static/js/sgzf/base64.js"></script>
<script src="${ctx}/static/js/sgzf/es6-promise.min.js"></script>
<script src="${ctx}/static/js/sgzf/stomp.js"></script>
<script src="${ctx}/static/js/sgzf/tonmx_lib.min.js"></script>
<!-- 4g执法 end-->

<!-- add by zk start -->
<jsp:include page="../include/videoInclude.jsp"></jsp:include>
<!-- add by zk end -->
<jsp:include page="../include/messageInclude.jsp"></jsp:include>

</head>
<%--onbeforeunload="unloadEvent();"--%>
<body id="warp-main">
	<cui:dialog id="dialogId_common_relEvidence" autoOpen="false" resizable="false" iframePanel="true" reLoadOnOpen="true" modal="false" autoDestroy="true">
	</cui:dialog>
	
	<!-- 编辑图片证据dialog -->
	<cui:dialog id="editImgEvidenceDialog" autoOpen="false" iframePanel="true" reLoadOnOpen="true" modal="true" resizable="false" autoDestroy="true"></cui:dialog>
	
	<cui:dialog id="dialog" autoOpen="false" iframePanel="true" reLoadOnOpen="true" modal="true" resizable="false" autoDestroy="true"></cui:dialog>
	
	<cui:dialog id="logoutDialog"  title="提示"  autoOpen="false" iframePanel="true" reLoadOnOpen="true" modal="true" resizable="false" componentCls="exitDialog" buttons="buttons">
		<h2>确认要退出当前用户吗？</h2>
	</cui:dialog>
	
	<!-- 监舍罪犯dialog -->
	<cui:dialog id="jszfDialog" autoOpen="false" iframePanel="true" reLoadOnOpen="true" modal="true" resizable="false"></cui:dialog>
	
	<!-- 消息确认dialog -->
	<cui:dialog id="confirmDialog" autoOpen="false" iframePanel="true" reLoadOnOpen="true" modal="true" resizable="false"></cui:dialog>
	
	<cui:layout id="layout1" fit="true">
		<cui:layoutRegion region="north" split="false" style="height:67px;">
		<%@ include file="header.jsp"%>
		</cui:layoutRegion>
		
		<!-- <div id="warp-main"> -->
		<cui:layoutRegion region="east" split="true" collapsed="false" style="width:280px;" onLoad="alarmLeave">
			<div class="wrapper">
	          <!-- <div class="query-wrapper">
	            <h2 class="title">设备查询</h2>
	            <input id="query" type="text">
	          </div> -->
	          <div class="device-wrapper">
	            <h2 class="title">设备统计</h2>
	            <ul class="device-nav" id="sbtj">
	              <!-- <li class="device-item">
	                <span class="iconfont icon-alarm">
	                </span>
	                <div class="content">
	                   	报警
	                  <span class="all">
	                    1
	                  </span>
	                  <span class="split">/</span>
	                  <span class="done">0</span>
	                </div>
	              </li>
	              <li class="device-item">
	                <span class="iconfont icon-terminal">
	                </span>
	                <div class="content">
	                 	 物联网终端
	                  <span class="all">
	                    0
	                  </span>
	                  <span class="split">/</span>
	                  <span class="done">0</span>
	                </div>
	              </li>
	              <li class="device-item">
	                <span class="iconfont icon-environment">
	                </span>
	                <div class="content">
	                	  机房环境
	                  <span class="all">
	                    0
	                  </span>
	                  <span class="split">/</span>
	                  <span class="done">0</span>
	                </div>
	              </li>
	              <li class="device-item">
	                <span class="iconfont icon-broadcast">
	                </span>
	                <div class="content">
	                  	广播
	                  <span class="all">
	                    7
	                  </span>
	                  <span class="split">/</span>
	                  <span class="done">0</span>
	                </div>
	              </li>
	              <li class="device-item">
	                <span class="iconfont icon-talk1">
	                </span>
	                <div class="content">
	                	对讲
	                  <span class="all">
	                    16
	                  </span>
	                  <span class="split">/</span>
	                  <span class="done">0</span>
	                </div>
	              </li>
	              <li class="device-item">
	                <span class="iconfont icon-camera">
	                </span>
	                <div class="content">
	                  	摄像
	                  <span class="all">
	                    162
	                  </span>
	                  <span class="split">/</span>
	                  <span class="done">5</span>
	                </div>
	              </li>
	              <li class="device-item">
	                <span class="iconfont icon-phone">
	                </span>
	                <div class="content">
	                  	亲情电话
	                  <span class="all">
	                    11
	                  </span>
	                  <span class="split">/</span>
	                  <span class="done">1</span>
	                </div>
	              </li>
	              <li class="device-item">
	                <span class="iconfont icon-battery">
	                </span>
	                <div class="content">
	                  	电源/电池
	                  <span class="all">
	                    2
	                  </span>
	                  <span class="split">/</span>
	                  <span class="done">0</span>
	                </div>
	              </li>
	              <li class="device-item">
	                <span class="iconfont icon-server">
	                </span>
	                <div class="content">
	                  	服务器
	                  <span class="all">
	                    10
	                  </span>
	                  <span class="split">/</span>
	                  <span class="done">0</span>
	                </div>
	              </li>
	              <li class="device-item">
	                <span class="iconfont icon-exchange">
	                </span>
	                <div class="content">
	                  	交换机
	                  <span class="all">
	                    12
	                  </span>
	                  <span class="split">/</span>
	                  <span class="done">1</span>
	                </div>
	              </li>
	              <li class="device-item">
	                <span class="iconfont icon-electron">
	                </span>
	                <div class="content">
	                  	电网
	                  <span class="all">
	                    4
	                  </span>
	                  <span class="split">/</span>
	                  <span class="done">0</span>
	                </div>
	              </li>
	              <li class="device-item">
	                <span class="iconfont icon-limit">
	                </span>
	                <div class="content">
	                  	门禁
	                  <span class="all">
	                    41
	                  </span>
	                  <span class="split">/</span>
	                  <span class="done">0</span>
	                </div>
	              </li>
	              <li class="device-item">
	                <span class="iconfont icon-pcm">
	                </span>
	                <div class="content">
	                  	PCM
	                  <span class="all">
	                    0
	                  </span>
	                  <span class="split">/</span>
	                  <span class="done">0</span>
	                </div>
	              </li> -->
	            </ul>
	          </div>
	        </div>
		</cui:layoutRegion>
		
		<cui:layoutRegion region="center">
		</cui:layoutRegion>
	</cui:layout>
	
	<!-- 弹出框 -->
  	<div id="dialog1">
	    <!-- <div class="form-wrapper">
	      <form id="form1">
	        <div class="row">
	          <div id="radiolist1"></div>
	        </div>
	        <div class="row">
	          <div class="col-md-3">
	            	发送信息
	          </div>
	          <div class="col-md-9">
	            <textarea id="textarea1" name="textbox"></textarea>
	          </div>
	        </div>
	      </form>
	      <div class="button-wrapper">
	        <button id="cancel">取消</button>
	        <button id="send">发送</button>
	      </div>
	    </div> -->
  	</div>
</body>
<script>
	var messageBox = null;
	
	var combobox_index_msgType = <%=CodeFacade.loadCode2Json("4.20.37")%>;
	
	var buttons=[{ text: "确定",  id: "saveId",
	    click: function () { logout(); }        
	},{ text: "取消", id: "cancelId",
	    click: function () { loginCancel();  }            
	}];
	$(function() {
		//debugger;
		$('li').has('ul').mouseenter(function(e){
			$(this).addClass("active");
			$(this).children('ul').css('visibility','visible');
			var targetObj = $(this).children('ul');
			var width = $(this).children('ul').find("li").eq(0).outerWidth(true) -2;
			var height = $(this).children('ul').height()-2;
	 		$(this).children('iframe').width(width);
			$(this).children('iframe').height(height);
			$(this).children('iframe').css('visibility','visible');
			return false;
		}).mouseleave(function(){
			$(this).removeClass("active");
			$(this).children('ul').css('visibility','hidden');
 			$(this).children('iframe').css('visibility','hidden');
		});
		/* $('.tolist li').has('ul').mouseover(function(){
	      $(this).children('ul').css('visibility','visible');
	      var width = $(this).children('ul').width();
	      var height = $(this).children('ul').height();
	      $(this).children('iframe').width(width);
	      $(this).children('iframe').height(height);
	      $(this).children('iframe').css('visibility','visible');
	    }).mouseout(function(){
	      $(this).children('ul').css('visibility','hidden');
	      $(this).children('iframe').css('visibility','hidden');
	    }); */

		initUserInfo();
		initSbSearch();
		//initPage();//初始化
		videoPlanTimer.initVideoPlanTimer();
		// initMessage();
		window.setTimeout(initMessage, 1000);
		//sjchang();
	});
	function initSbSearch(){
		 $("#query").textbox({
		      placeholder: "输入编号",
		      componentCls: "form-control",
		      buttons: [{
		        text: false,
		        icons: "iconfont icon-sousuo",
		        click: function (e, data) {
		          //点击查询按钮触发的事件
		        }
		      }]
		    })
	}

	// 消息通知框
	function initMessage() {
		var url = jsConst.basePath + '/common/message/toMessageIndex';
		messageBox = $.messageBox({
			timeOut:60000,//一分钟
			componentCls:"workmessage",
			message: "",
			url: url,
			width:526,
			height:262,
			title:"消息提醒",
			reLoadOnOpen: true,
			autoDestroy: true,
			onOpen:function(e,ui){
				$("#viewMessageId").unbind("click");
			},
			onClose:function(e,ui){
				messageBox = null;
				$("#viewMessageId").bind("click",initMessage);
			}
		});
	}

	/*function initMessage() {
		$.getJSON("${ctx}/common/message/findByMsgType?tt=" + getRandomId(), function(data) {
			var msg = "没有消息提醒工作！";
			var list = data;
			if(list && list.length>0) {
				msg = '<ul>';
				for(var i = 0; i < list.length; i++) {				
					msg += '<li>';
					var content = convertColVal(combobox_index_msgType, list[i].MSG_TYPE) + "，查看";
					msg += '<a title="'+content+'" href="javascript:void(0);" data-href="'+list[i].URL+'" onclick="clickContent(this);">'+common.abbreviate(content,30)+'</a>';
					msg += '<div style="float: right; margin-right: 10px;" ><a data-type="'+list[i].MSG_TYPE+'" onClick="updateRead(this);" style="cursor: pointer; color: red;">X</a></div>';
					msg += '</li>';
				}
				msg += '</ul>';
				
				messageBox = $.messageBox({
					timeOut:60000,//一分钟
					componentCls:"workmessage",
			        message:msg,
			        width:300,
			        height:170,
			        onOpen:function(e,ui){
			        	$("#viewMessageId").unbind("click");
			        },
			        onClose:function(e,ui){
			        	$("#viewMessageId").bind("click",initMessage);
			        	messageBox = null;
			        }
			    }); 
			} else {
				$("#viewMessageId").unbind("click");
				$("#viewMessageId").bind("click",initMessage);
				//$.message({message:msg, cls:"waring"});
				//toastMsg(msg,"danger","custom");
			}
		});
	}*/
	
	//报警处置 红色悬浮窗口
	function toastMsg(msg,type,special,height,width,position){
		var options = {
			 message:msg,
			 special:special||"",
             iconCls:false,
             height:height||"auto",
             width: width || "auto",
             type:type||"info",
             position:position,
             iframePanel:true,//覆盖object对象
		}
		if(special == "custom" && type=="danger"){
			 options.componentCls = "coral-closable";
			 options.timeOut = 0;
			 options.width = "560";
			 options.position = {
					my: "top",
					at: "top top+50",
					of: window
			}
		}
		$.message(options);
	}
	function clickContent(obj) {
		var url = $(obj).attr("data-href");
		if(url != null && url != "" && url != "null" && url != "undefined") {
			$("#dialog").dialog("option", {
				title:"办理窗口",
				width:1000,
				height:600,
				url:url,
				onLoad:function(e,ui){
				},
				onLoadError:function(e,ui){
				}
			});
			$("#dialog").dialog("open");
		}
	}
	
	function updateRead(obj) {
		var msgType = $(obj).attr("data-type");
		$.ajax({
			type : 'post',
			url : '${ctx}/common/message/updateReadByYwId',
			data: {
				msgType: msgType,
				noticeUserId: jsConst.USER_ID
			},
			dataType : 'json',
			success : function(data) {
				if(data.code == "1") {
					$(obj).parent().parent().remove();
				}			
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
			}
		});
	}
	
	
	function initPage(){
		//填充右侧面板
		//var panel = $("#layout1").layout("panel", "east");
		//panel.panel("refresh", jsConst.basePath+"/xxhj/sy/index");
		if(jsConst.USER_LEVEL==1){
			//省局视频工具栏显示
			window.top.videoClient.setConfig(1,1,1);
			//toDisplay('homeMenu');
		}else if(jsConst.USER_LEVEL==2){
			//监狱视频工具栏显示
			window.top.videoClient.setConfig(1,1,1);
			//toDisplay('homeMenu');
		}else if(jsConst.USER_LEVEL==3){
			//监区视频工具栏隐藏
			window.top.videoClient.setConfig(1,1,1);
			// toXxhjDisplay('jqzfjbxx');
		}else{
			;
		}
		
		$.ajax({
			type : 'post',
			url : jsConst.basePath+'/xtgl/userConfig/findByUcUserId.json',
			data : { "ucUserId":jsConst.USER_ID },
			dataType : 'json',
			success : function(data) {
				if (data.success) {
					if(data.obj){
						var userConfigId=data.obj;
						//设置地图类型全局变量
						jsConst.MAP_TYPE=userConfigId.ucMapType;
					}
					//2D
					else{
						console.log("用户未进行2D/3D地图设置,默认显示2D");
						jsConst.MAP_TYPE='0';
					}
					toDisplay('homeMenu');
				} else {
					$.message({ iframePanel:true, message : data.msg, cls : "warning" });
				}
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$.alert({ message:textStatus, title:"信息提示", iframePanel:true });
			}
		}); 
		
	}
	function init2DPage(){
		//二维地图 add by zk start
		
		var panel = $("#layout1").layout("panel", "center");
		if(jsConst.USER_LEVEL==1){
			jsConst.ROOT_ORGA_CODE = '';
			panel.panel("refresh", "${ctx}/portal/provMap");
		}else if(jsConst.USER_LEVEL==2){
			panel.panel("refresh", "${ctx}/portal/planeMap");
		}else if(jsConst.USER_LEVEL==3){
			panel.panel("refresh", "${ctx}/portal/planeMap");
		}
		
		return;  
		//二维地图 add by zk end
	}
	function init3DPage(){
		if(jsConst.USER_LEVEL==1){
			/** 
			 * Begin modify by lincoln.cheng 2019-03-10
			 * centerDisplay('provMap');
			 */
			centerDisplay('provMap', '1');
			/** End modify by lincoln.cheng 2019-03-10 */
		}else if(jsConst.USER_LEVEL==2 || jsConst.USER_LEVEL==3){
			/** 
			 * Begin modify by lincoln.cheng 2019-03-10
			 * centerDisplay('provMap');
			 */
			centerDisplay('prisMap', '1');
			/** End modify by lincoln.cheng 2019-03-10 */
		}
		return;
	}
	//视角定位修改位置
	//add by linhe 2018-1-13
	function initToolsText(text){
		$('#locationName').text(text);
	}
	
	function openDialog(event,name) {
		  var event = window.event || event;
			//event.stopPropagation();
			  if ( event && event.stopPropagation ) {
				  event.stopPropagation();
	    		} else {
	           window.event.cancelBubble = true;
	    	}
			//event.preventDefault();
		if (name == 'dayly') {
			var url_='${ctx}/xxyp/dayly/daylyLayout';
			$("#dialog").dialog({
				width : 800,
				height :700,
				title : '信息日报',
				url : url_
			});
			$("#dialog").dialog("open");
	    }
		var url = "";
		/**
		if (name == 'wxjl') {
			$("#dialog").dialog({
				width : 900,
				height : 680,
				subTitle : '维修记录',
				url : '${ctx}/policeInPrison/jnmj_police_comego_record',
			});
			$("#dialog").dialog("open");
		}
		**/
		return;
	}  
	
	//切换center
	function centerDisplay(name, mapType) {
		var panel = $("#layout1").layout("panel", "center");
		if (name == "provMap") {
			jsConst.ROOT_ORGA_CODE = '';//add by linhe 2018-1-10
			panel.panel("refresh", "${ctx}/portal/provMap");
		}else if (name == "prisMap") {
			if(mapType=='0'){
				panel.panel("refresh", "${ctx}/portal/planeMap");
			} else {
				panel.panel("refresh", "${ctx}/portal/prisMap");
			}			
		}
	}

	function toDisplay(name) {
		// alert("toDisplay = " + name);
		// alert("toDisplay Const.USER_LEVEL = " + jsConst.USER_LEVEL);
		// alert("toDisplay Const.MAP_TYPE = " + jsConst.MAP_TYPE);
		if (name == "homeMenu") {
			if (jsConst.USER_LEVEL==3) {
				// toXxhjDisplay('jqzfjbxx');
			} else {
				//var panel = $("#layout1").layout("panel", "east");
				//panel.panel("refresh", "${ctx}/xxhj/sy/index");
				//add wq 2018-05-25
				/* if(jsConst.USER_LEVEL==1 && jsConst.ROOT_ORGA_CODE!=null && ''!=jsConst.ROOT_ORGA_CODE){
					centerDisplay("provMap");
                } */
			}
			//如果当前为2D则展示首页
			if(jsConst.MAP_TYPE=='0'){
				init2DPage();
			}else{
				init3DPage();
			}
		
		}
	}
	
	//携带参数打开右侧视图 add by zk
	function openViewToRightAddParam(viewName, params) {
		var panel = $("#layout1").layout("panel", "east");
		panel.panel("refresh", '${ctx}/menubar/displayRightAddParam?viewName=' + viewName + '&params=' + encodeURI(JSON.stringify(params)));
	}
	
	//民警分布右侧视图 add by zk
	function mjfbRightView(cusNumber,areaId,areaName,areaLevel){
		var viewName="mjfb/mjfb";
		var params={
			"cusNumber":cusNumber,
			"areaId":areaId,
			"areaName":areaName,
			"areaLevel":areaLevel	//1 楼房、 2 楼层、 3 监狱
		};
		openViewToRightAddParam(viewName, params);
	}
	
	// 监督单提醒消息回调
	function callbackProvinceMonitorNotice1(data) {
		// data为调消息接口的content
		data = JSON.parse(data);
		// 处理后续业务
	}
	
	// 故障维修消息回调
	function callbackMsgFaultMaintain1(data) {
		// data为调消息接口的content
		data = JSON.parse(data);
		// 处理后续业务
	}
	
	//打开编辑图片dialog
	function openEditImgDialog(evidenceId) {	
		$('#editImgEvidenceDialog').html("");
		$('#editImgEvidenceDialog').dialog({
			width : 1000,
			height : 600,
			title : "编辑图片证据",
			url : jsConst.basePath+'/evidence/editImgEvidenceDialog?id='+evidenceId
		});
		$("#editImgEvidenceDialog").dialog("open");
	}
	
	//报警页面离开时间 面板创建 时 重置报警记录id为 null
	function alarmLeave() {
		if(jsConst.ALARM_ID != null){
			jsConst.ALARM_ID = null;
		} 
	}
	
	//获取监狱名称，报警处置回调中 跳转地图需要
	function getCusName(cusNumber) {
		var jyData =  <%=AuthSystemFacade.getAllJyJsonInfo()%>; //所有监狱信息json  
		for (var i = 0; i < jyData.length; i++) {
			if (cusNumber == jyData[i].value){
				 return jyData[i].text;
			}
		}
	}
	
	$("#query").textbox({
      placeholder: "输入编号",
      componentCls: "form-control",
      buttons: [{
        text: false,
        icons: "iconfont icon-sousuo",
        click: function (e, data) {
          //点击查询按钮触发的事件
        }
      }]
    })
    $("#dialog1").dialog({
      width: 800,
      height: 460,
      autoOpen: false,
      componentCls: "custom" //对话框风格，引入dialog.css后需要加上此属性
    })
    $("#form1").form();
    $('#radiolist1').radiolist({
      name: "telephone",
      value: "tel",
      data: [{
          value: "tel",
          text: "固定电话：134567890"
        },
        {
          value: "police",
          text: "警务通：134567890"
        },
        {
          value: "interphone",
          text: "对讲机：134567890"
        }
      ],
      column: 1 //每行放一个
    });
    $('#textarea1').textbox({
      componentCls: "form-control"
    });
    $("#cancel").button({
      onClick: function (e, ui) {
        //点击事件
      },
      componentCls: "custom-button"
    })
    $("#send").button({
      onClick: function (e, ui) {
        //点击事件
      },
      componentCls: "custom-button"
    })
    //打开弹出框方法
    function openDialog() {
      $("#dialog1").dialog("open");
    }
	function sjchang(){
		 
	 	 $.ajax({
			type : "post",
			url : '${ctx}/wghgl/yrzq/getsjzs?type=1',
			dataType : "json",
			success : function(data) {
				 console.log(data);
				 //sbtj
				 $("#sbtj").html("");
				 var jg = "";
				  for(var i = 0;i<data.length;i++){
					 var zc = data[i].zc;
					 var yc = data[i].yc;
					 var tb = data[i].tb;
					 var name = data[i].name;
					 jg = jg+"<li class=\"device-item\">"+
		                "<span class=\"iconfont "+tb+"\">"+
		               " </span>"+
		                "<div class=\"content\">"+name+"&nbsp;<span class=\"all\">"+zc+
		               "   </span>"+
		                "  <span class=\"split\">/</span>"+
		               "   <span class=\"done\">"+yc+"</span>"+
		              "  </div>"+
		             " </li>";
				 } 
				  $("#sbtj").html(jg);
			},	error : function(XMLHttpRequest, textStatus, errorThrown) {
				console.log(textStatus);
				console.log(errorThrown);
				console.log("获取异常");
			} 
	 	}); 
	} 
</script>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<%@taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<c:set var="document_id" value="talkNow" />
<!-- 页面元素id前缀 -->
<body style="height: 100%">
	<div class="TC-content">
		<div id="${document_id}_controlTabs">
			<ul class="left-nav">
				<li>
					<a href="#TC-fragment-extension" class="left-nav-title1">呼叫对讲</a>
				</li>
				<!-- <li>
					<a href="#TC-fragment-host" class="left-nav-title2">呼叫主机</a>
				</li> -->
			</ul>
			<!-- 分机内容 -->
			<div id="TC-fragment-extension">
				<!-- 提示信息 -->
				<p class="TC-remindMessage">提示:直接选择备注下的主机号名称或分机号的名称再按确定即可呼叫对应的对讲,"如果提示视屏客户端未打开,请重新打开后请点击刷新按钮"</p>
				<!-- 主机号与分机号 -->
				<div class="TC-main-left fl">
					<div class="TC-hostNumber TC-number fl">
						<span class="TC-hostNumberTitle1">主机号</span>
						<span class="TC-hostNumberTitle2">备注</span>
						<div class="TC-hostNumberContent">
							<ul id="${document_id}_server"></ul>
						</div>
					</div>
					<div class="TC-extensionNumber TC-number fr">
						<span class="TC-hostNumberTitle1">分机号</span>
						<span class="TC-hostNumberTitle2">备注</span>
						<div class="TC-extensionNumberContent">
							<ul id="${document_id}_partServer">
							</ul>
						</div>
					</div>
				</div>
				<!-- 拨号部分 -->
				<div class="TC-main-right fr">
					<div class="TC-main-right-top">呼叫对讲</div>
					<div class="TC-main-right-btm">
						<input class="displayInp" type="text" value="" id="${document_id}_phoneNumber">
						<div class="numberButton clearfix">
							<ul>
								<li onclick="talkControl.addNumber(1)">1</li>
								<li onclick="talkControl.addNumber(2)">2</li>
								<li onclick="talkControl.addNumber(3)">3</li>
								<li onclick="talkControl.addNumber(4)">4</li>
								<li onclick="talkControl.addNumber(5)">5</li>
								<li onclick="talkControl.addNumber(6)">6</li>
								<li onclick="talkControl.addNumber(7)">7</li>
								<li onclick="talkControl.addNumber(8)">8</li>
								<li onclick="talkControl.addNumber(9)">9</li>
								<li onclick="talkControl.goBack()">
									<span class="detleBtn"></span>
								</li>
								<li onclick="talkControl.addNumber(0)">0</li>
								<li onclick="talkControl.callNumber()">确定</li>
								<li onclick="talkControl.refreshTalkMult()">刷新</li>
								<li onclick="talkControl.stopCallNumber()">挂断</li>
							</ul>
						</div>
					</div>
				</div>
			</div>
			<!-- 主机号内容 -->
			<div id="TC-fragment-host">
				<!-- 主机号选择按钮 -->
				<div id="TC-host-tabs" class="clearfix">
					<ul class="TC-host-btn">
						<li>
							<a href="#TC-host-1">1-60</a>
						</li>
						<li>
							<a href="#TC-host-2">61-120</a>
						</li>
						<li>
							<a href="#TC-host-3">121-180</a>
						</li>
						<li>
							<a href="#TC-host-4">181-240</a>
						</li>
						<li>
							<a href="#TC-host-5">241-300</a>
						</li>
						<li>
							<a href="#TC-host-6">301-360</a>
						</li>
						<li>
							<a href="#TC-host-7">361-420</a>
						</li>
						<li>
							<a href="#TC-host-8">421-480</a>
						</li>
						<li>
							<a href="#TC-host-9">481-540</a>
						</li>
						<li>
							<a href="#TC-host-10">541-600</a>
						</li>
					</ul>
					<div id="TC-host-1">
						<ul>
							<li>1*</li>
							<li>2*</li>
							<li>3*</li>
							<li>4*</li>
							<li>5*</li>
							<li>6*</li>
							<li>7*</li>
							<li>8*</li>
							<li>9*</li>
							<li>10*</li>
							<li>1*</li>
							<li>2*</li>
							<li>3*</li>
							<li>4*</li>
							<li>5*</li>
							<li>6*</li>
							<li>7*</li>
							<li>8*</li>
							<li>9*</li>
							<li>10*</li>
							<li>1*</li>
							<li>2*</li>
							<li>3*</li>
							<li>4*</li>
							<li>5*</li>
							<li>6*</li>
							<li>7*</li>
							<li>8*</li>
							<li>9*</li>
							<li>10*</li>
							<li>1*</li>
							<li>2*</li>
							<li>3*</li>
							<li>4*</li>
							<li>5*</li>
							<li>6*</li>
							<li>7*</li>
							<li>8*</li>
							<li>9*</li>
							<li>10*</li>
							<li>1*</li>
							<li>2*</li>
							<li>3*</li>
							<li>4*</li>
							<li>5*</li>
							<li>6*</li>
							<li>7*</li>
							<li>8*</li>
							<li>9*</li>
							<li>10*</li>
							<li>1*</li>
							<li>2*</li>
							<li>3*</li>
							<li>4*</li>
							<li>5*</li>
							<li>6*</li>
							<li>7*</li>
							<li>8*</li>
							<li>9*</li>
							<li>10*</li>
						</ul>
					</div>
					<div id="TC-host-2">内容2</div>
					<div id="TC-host-3">内容3</div>
					<div id="TC-host-4">内容4</div>
					<div id="TC-host-5">内容5</div>
					<div id="TC-host-6">内容6</div>
					<div id="TC-host-7">内容7</div>
					<div id="TC-host-8">内容8</div>
					<div id="TC-host-9">内容9</div>
					<div id="TC-host-10">内容10</div>
				</div>

			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	var jsConst = window.top.jsConst;
	var talkControl = {
		init : function() {
			talkControl.showMainTalk();
			$('#${document_id}_controlTabs').tabs({
				heightStyle : 'fill',
				cls : "coral-tabs-left", //属性: 值
				onActivate : function() { //回调事件: 方法
				}
			});
			$('#TC-host-tabs').tabs({
				heightStyle : 'fill',
				cls : "coral-tabs-top",
				onActivate : function() { //回调事件: 方法
				}
			});
			if(jsConst.CUS_NUMBER =="4303"){
                talkControl.iniTalkMult();
            }
		},
		//对讲主机数据初始化
		showMainTalk : function() {
			var data = _DOCUMENT_EVENT.request_data( "${ctx}/talkBackServer/findByCusNumber.json", { "cusNumber" : jsConst.CUS_NUMBER }, false);
			var str = "";
			$('#${document_id}_server').html("");
			for (var i = 0; i < data.length; i++) {
				str += ("<li><span>" + data[i]["number"] + "</span><a href='javascript:void(0);' title='"
                    + data[i]["name"]
                    + "' onclick='talkControl.showOtherTalk(\""
                    + data[i]["id"]
                    + "\",\""+ data[i]["number"]+"\")'>"
                    + data[i]["name"] + "</a></li>");
			}
			$('#${document_id}_server').append(str);
		},
		//对讲分机数据显示
		showOtherTalk : function(tbdMainIdnty,tbdNumber) {
            talkControl.setNumber(tbdNumber);
			var data = _DOCUMENT_EVENT.request_data( "${ctx}/talkBackBase/findBaseByMain.json", { "tbdMainIdnty" : tbdMainIdnty }, false);
			var str = "";
			$('#${document_id}_partServer').html("");
			for (var i = 0; i < data.length; i++) {
				str += ("<li><span>" + data[i]["number"] + "</span><a href='javascript:void(0);' onclick='talkControl.setNumber(\"" + data[i]["number"] + "\")'>" + data[i]["text"] + "</a></li>");
			}
			$('#${document_id}_partServer').append(str);
		},
		addNumber : function(number) {
			$("#${document_id}_phoneNumber").val( "" + $("#${document_id}_phoneNumber").val() + number);
		},
		goBack : function() {
			$("#${document_id}_phoneNumber").val( $("#${document_id}_phoneNumber").val().substr(0, $("#${document_id}_phoneNumber").val().length - 1));
		},
		callNumber : function() {
			//开始呼叫
            if(jsConst.CUS_NUMBER !="4303"){
                var talkIdntys = [];
                talkIdntys.push($("#${document_id}_phoneNumber").val());
                var params = {};
                params["cusNumber"] = jsConst.CUS_NUMBER;
                params["talkIdntys"] = JSON.stringify(talkIdntys);
                $.ajax({
                    type : 'post',
                    url : '${ctx}/realTimeTalk/startTalk.json',
                    data : params,
                    dataType : 'json',
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

               /* $.ajax({
                    type : 'post',
                    url : '${ctx}/jfsb/camera/getCameraIdByTalkbackId.json',
                    data : params,
                    dataType : 'json',
                    success : function(data_cameraIds) {
                        if (data_cameraIds != null) {
                            console.log(data_cameraIds);
                            videoClient.playVideoHandle({
                                'subType': 2,
                                'layout': data_cameraIds.length,
                                'data': data_cameraIds,
                                'callback': function (data_) {
                                }
                            });
                        }
                    }
                });*/
            }else{
                var params = {};
                params["cusNumber"] = jsConst.CUS_NUMBER;
                var slaveCode = {"slaveCode":$("#${document_id}_phoneNumber").val()};
                params["args"] =JSON.stringify(slaveCode);
                $.ajax({
                    type : 'post',
                    url : '${ctx}/videoClient/startTalkMult.json',
                    data : params,
                    dataType : 'json',
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
		},
		iniTalkMult: function(){
			//初始化对讲模型
			var pam ={
					"Sn":"1902",
					"SipAcc":"1119999",
					"SipPwd":"222242",
					"BoxAddress":"34.67.79.253",
					"TalkType":"801",
					"UdpPort":":5062"
			};
			$.ajax({
                type : 'post',
                url : '${ctx}/videoClient/iniTalkMult',
                data:"args="+JSON.stringify(pam)	,	
              // dataType : 'json',
                success : function(data) {
                    if(data.success==true){
                    	
                    }else{
                    	$.messageQueue({ message : "请确认是否打开视屏客户端!", cls : "warning", iframePanel : true, type : "info" }); 
                    }
                }
            });
		},
		refreshTalkMult: function(){
			talkControl.iniTalkMult();
		},
		setNumber : function(number) {
			$("#${document_id}_phoneNumber").val(number);
		},
		stopCallNumber : function(){
			//停止对讲
            console.log("停止对讲号码:"+$("#${document_id}_phoneNumber").val());
			//var talkIdntys = [];
			//talkIdntys.push($("#${document_id}_phoneNumber").val());
			var params = {};
			var slaveCode = {"slaveCode":$("#${document_id}_phoneNumber").val()};
            params["cusNumber"] = jsConst.CUS_NUMBER;
            params["args"] = JSON.stringify(slaveCode);
			$.ajax({
				type : 'post',
				url : '${ctx}/videoClient/stopTalk.json',
				data : params,
				dataType : 'json',
				success : function(data) {
					if (data.success) {
						//$.messageQueue({ message : data.msg, cls : "success", iframePanel : true, type : "info" });
					} else {
						$.messageQueue({ message : data.msg, cls : "warning", iframePanel : true, type : "info" }); 
					}
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					$.messageQueue({ message : textStatus, cls : "warning", iframePanel : true, type : "info" }); 
				}
			});
		}
	};
	talkControl.init();
</script>
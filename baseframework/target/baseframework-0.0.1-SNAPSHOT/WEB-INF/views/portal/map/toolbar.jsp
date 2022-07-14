<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
		<!--2D/3D切换 add by zk -->
		<link href="${ctx}/static/module/toggle-switch/toggle-switch.css" rel="stylesheet">

		<a id="mapType" style="float:left;" href="javascript:void(0)" class="toggle toggle--on"></a>

		<cui:dialog id="dialogId_DormInfo" autoOpen="false" iframePanel="true" resizable="false"></cui:dialog>

		<!--
		<a  href="javascript: void(0)" onclick="navEvent('expand',event)"><i class="iconfont icon-weibiaoti-6"></i></a>
		<a onclick="navEvent('collpse',event)"><i class="iconfont icon-weibiaoti-7"></i></a>
		<a><i class="iconfont icon-weibiaoti-5"></i></a>
		<a><i class="iconfont icon-weibiaoti-2"></i></a>
		<a><i class="iconfont icon-weibiaoti-1"></i></a>
		<a><i class="iconfont icon-weibiaoti-1"></i></a>
		-->
		<a  href="javascript: void(0)" onclick="mapClick(this)" title="地图鼠标" ><i class="iconfont icon-weibiaoti-8"></i></a>
		<a  href="javascript: void(0)" onclick="mapChoose(this)" title="地图框选" ><i class="iconfont icon-weibiaoti-"></i></a>
		<a  href="javascript: void(0)" onclick="mapPointChoose(this)" title="地图点选"><i class="iconfont icon-icon-dian"></i></a>
		<!-- <a  href="javascript: void(0)" onclick="mapEvent('videoImgCut',event)" title="视频截图"><i class="iconfont icon-weibiaoti-4"></i></a>
		<a  href="javascript: void(0)" onclick="mapEvent('videoRecord',event)" title="视频录像"><i class="iconfont icon-weibiaoti-3"></i></a>
		<a  href="javascript: void(0)" onclick="mapEvent('videoPlayBack',event)" title="切换回上次监控"><i class="iconfont icon-weibiaoti-2"></i></a> -->
		<a class="video-goback" onclick="videoClient.goback()" title="切换回上次监控" >
			<i class="iconfont icon-weibiaoti-2"></i>
		</a>
		<a class="video-cut" onclick="_snap()" title="视频截图" >
			<i id="icon_cut" class="iconfont icon-weibiaoti-4"></i>
		</a>
		<a class="video-record" onclick="_record()" title="视频录像">
			<i class="iconfont icon-weibiaoti-3"></i>
		</a>
		<a class="video-stop" onclick="_stop()" title="停止录像" style="display: none;">
			<i class="iconfont highlight icon-weibiaoti-3"></i>
		</a>
		<a href="javascript: void(0)" onclick="videoPlayBack()" title="视频回放 "><i class="iconfont icon-shipinhuifang"></i></a>
		<a href="javascript: void(0)" onclick="_talk()" title="对讲通话 " ><i class="iconfont icon-tonghua"></i></a>
		<cui:dialog id="dialog_toggleMapType" autoOpen="false" iframePanel="true"  modal="true" resizable="false" title="地图模式设置"  buttons="buttons_toggleMapType">
			<center>
				<h1>请选择地图类型</h1>
				<cui:radio id="radioMapType_0" name="radioMapType" componentCls="radio-md" value="0" label="仅二维"></cui:radio>
				<cui:radio id="radioMapType_1" name="radioMapType" componentCls="radio-md" value="1" label="仅三维" ></cui:radio>
				<cui:radio id="radioMapType_2" name="radioMapType" componentCls="radio-md" value="2" label="三维加二维"></cui:radio>
			</center>
		</cui:dialog>
		<!-- 回放时间弹出框 -->
		<cui:dialog id="dialog_choicePlayBackTime" autoOpen="false" iframePanel="true"  modal="true" resizable="false" position="{ my: 'right center', at: 'right center', of: window }">
			<center>
				<table class="table" width="90%">
					<tr>
						<td>			
							<cui:datepicker id="playBack_startTime" dateFormat="yyyy-MM-dd HH:mm:ss" placeholder="开始时间" componentCls="form-control"></cui:datepicker>
						</td>
					</tr>
					<tr>
						<td>			
							<cui:datepicker id="playBack_endTime" startDateId="playBack_startTime" dateFormat="yyyy-MM-dd HH:mm:ss" placeholder="结束时间" componentCls="form-control"></cui:datepicker>
						</td>
					</tr>
				</table>
			</center>
		</cui:dialog>
			
		<script>
		var buttons_toggleMapType = [{
		    text: "确定",
		    id: "saveId",
		    click: function () {
		    	
		    	var radioMapType_= $( "#radioMapType_0" ).radio( "getValue" );
		    	//设置的值不同于全局变量
		    	if(radioMapType_!=jsConst.MAP_TYPE){
		    		
		    		//更改数据库地图类型
		    		updateMapType(radioMapType_,function(radioMapType){
		    			debugger;
		    			if(radioMapType==mapType_2D){
		    				jsConst.MAP_TYPE=mapType_2D;
			    			//初始化2D地图
			    			//init2DPage();
			    			//初始化2D地图(在init2Dpage的时候会出现问题，所以用reload)
							window.location.reload();
			    			
				        }else if(radioMapType==mapType_3D){
				        	//如果是从2D切换过来需要重置地图
				        	if(jsConst.MAP_TYPE==mapType_2D){
				        		//初始化3D地图
				        		init3DPage();
				        	}
				        	jsConst.MAP_TYPE=mapType_3D;

				        }else if(radioMapType==mapType_2Din3D){
				        	//如果是从2D切换过来需要重置地图
				        	if(jsConst.MAP_TYPE==mapType_2D){
				        		//初始化3D地图
				        		init3DPage();
				        	}  
				        	jsConst.MAP_TYPE=mapType_2Din3D;

				        }
			    		
			    		//切换样式
			    		toggleClass(radioMapType);
			    		//更改radio
			    		$("#radioMapType_"+jsConst.MAP_TYPE).radio( "check" );
		    		});
		    	}
		    	
		    	$("#dialog_toggleMapType").dialog("close");
		        
		    }        
		},{
		    text: "取消",
		    id: "cancelId",
		    click: function () {
		        $("#dialog_toggleMapType").dialog("close");
		    }            
		}];
		
		//仅二维
		var mapType_2D='0';
		//仅三维
		var mapType_3D='1';
		//三维二维同时使用，且2D附在3D上面
		var mapType_2Din3D='2';
		$.parseDone(function(){
			initMapType();
			
			$('#mapType').click(function(e) {
				  var toggle = this;			  
				  e.preventDefault();
				  $("#dialog_toggleMapType").dialog("open");
			});
		});
		function initMapType(){
			toggleClass(jsConst.MAP_TYPE);
			$("#radioMapType_"+jsConst.MAP_TYPE).radio( "check" );
			
		}
		//切换二维、三维样式
		function toggleClass(mapType){
			/* debugger; */
			//2D
			if(mapType==mapType_2D){
				$("#mapType").removeClass("toggle--3-2");
				$("#mapType").removeClass("toggle--off");
				$("#mapType").addClass("toggle--on");
				
			}
			//3D
			else if(mapType==mapType_3D){
				$("#mapType").removeClass("toggle--3-2");
				$("#mapType").removeClass("toggle--on");
				$("#mapType").addClass("toggle--off");
			}
			//3D+2D
			else if(mapType==mapType_2Din3D){
				$("#mapType").removeClass("toggle--off");
				$("#mapType").removeClass("toggle--on");
				$("#mapType").addClass("toggle--3-2");
			}
			
			$("#mapType").addClass('toggle--moving');
	  
		  setTimeout(function() {
		    $("#mapType").removeClass('toggle--moving');
		  }, 200);
		} 
		//切换地图类型
		function updateMapType(mapType,callback){
				console.log(mapType);
			  //修改用户地图配置
			  $.ajax({
					type : 'post',
					url : '${ctx}/xtgl/userConfig/findByUcUserId.json',
					data : {
						"ucUserId":jsConst.USER_ID
					},
					dataType : 'json',
					success : function(data) {
						if (data.success) {
							if(data.obj){
								console.log(data.obj);
								var userConfigId=data.obj;
								//当设置的地图类型和数据库中不一致时才更改
								if(userConfigId.ucMapType!=mapType){
									$.ajax({
										type : 'post',
										url : '${ctx}/xtgl/userConfig/updatePart.json',
										data : {
											"id":userConfigId.id,
											"ucMapType":mapType
										},
										dataType : 'json',
										success : function(data) {
											if (data.success) {
												if( typeof callback == 'function' ){
													callback.call(this, mapType);
												}
												console.log("update success");
												
											} else {
												$.message({
													iframePanel:true,
													message : data.msg,
													cls : "warning"
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
								
							}else{
								console.log("用户未进行2D/3D地图设置");
								$.ajax({
									type : 'post',
									url : '${ctx}/xtgl/userConfig/insert.json',
									data : {
										"ucUserId":jsConst.USER_ID,
										"ucMapType":mapType,
										"ucCusNumber":jsConst.ORG_CODE
									},
									dataType : 'json',
									success : function(data) {
										if (data.success) {
											if( typeof callback == 'function' ){
												callback.call(this, mapType);
											}
											console.log(data.obj);
											
										} else {
											$.message({
												iframePanel:true,
												message : data.msg,
												cls : "warning"
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
							
						} else {
							$.message({
								iframePanel:true,
								message : data.msg,
								cls : "warning"
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
		function mapEvent(name,event){
			var event=window.event||event; 
			event.stopPropagation(); 
			event.stopPropagation();
			$(event.currentTarget).parents(".r-but").children("a").removeClass("highlight");
			$(event.currentTarget).addClass("highlight");
			if(name!=''){
				eval(name+"('"+name+"')");
			}
		}
		function mapClick(obj){
			$(obj).parents(".r-but").children("a").removeClass("highlight");
			$(obj).addClass("highlight");
			map.setMouseFlyMode();//add by linhe 2018-1-10
			//alert("地图导航条事件:"+name);
		}
		function mapChoose(obj){
			$(obj).parents(".r-but").children("a").removeClass("highlight");
			$(obj).addClass("highlight");
			map.setMouseDragModel();//add by linhe 2018-1-10
			//alert("地图导航条事件:"+name)
		}

		//add by wq 2018-4-13
		function mapPointChoose(obj) {
			$(obj).parents(".r-but").children("a").removeClass("highlight");
			$(obj).addClass("highlight");
			map.setMouseDormPointSelect();
		}
		//视频回放
		function videoPlayBack(){
			var zeroTime = new Date(new Date().setHours(0,0,0,0));
			$("#playBack_startTime").datepicker("setDate",zeroTime);
	    	$("#playBack_endTime").datepicker("setDate",new Date());
			$("#dialog_choicePlayBackTime").dialog({   
				 title : "选择回放时间",
				 modal : true,
				 buttons: [{
		          	text: "确定",
		          	click:function() {
		          		var stime = $("#playBack_startTime").datepicker("getValue");
				    	var etime = $("#playBack_endTime").datepicker("getValue");
				    	if( stime.length == 0 ){
				    		_alert('请输入回放开始时间！');
				    		return false;
				    	}

				    	if( etime.length == 0 ){
				    		_alert('请输入回放结束时间！');
				    		return false;
				    	}
				    	_playback(stime,etime);
				    	$("#dialog_choicePlayBackTime").dialog("close");
		            }
		         },{
		 		    text: "取消",
				    click: function () {
				        $("#dialog_choicePlayBackTime").dialog("close");
				    }      
				}]
			});
			$("#dialog_choicePlayBackTime").dialog("open");
		}
		function videoImgCut(name,event){
			alert("地图导航条事件:"+name)
		}
		function videoRecord(name,event){
			alert("地图导航条事件:"+name)
		}
		</script>
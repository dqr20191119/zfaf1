// JavaScript Document
(function($){
	//1-摄像头，2-对讲分机，3-报警器，4-门禁，5-广播，6-对讲主机，7-高压电网，8-监舍标签，20-地图标签
	//摄像头类型值
	var cameraTypeValue="1";
	//对讲分机类型值
	var talkBackTypeValue="2";
	//报警器类型值
	var alarmTypeValue="3";
	//门禁类型值
	var doorTypeValue="4";
	//广播类型值
	var broadcastTypeValue="5";
	//监舍标签类型值
	var jsbqTypeValue="8";
	
	var cX,cY,indexId=0,removeId,Data=[],DOM,changeSignColor=false,signColor;
	var changeBodyColor=false,bodyColor,changeFontColor=false,fontColor;
	var Rleft,Rtop;//需要删除的坐标
	jQuery.sign_show={
		bindSign:function(dom){
			DOM=dom;
			defined(dom);
		},//初始化，绑定元素
		setSignColor:function(color){
			changeSignColor=true;
			signColor=color;
		},//设定标记框的颜色
		setBodyColor:function(color){
			changeBodyColor=true;
			bodyColor=color;
		},//设定提示框颜色
		// setFontColor:function(color){
		// 	changeFontColor=true;
		// 	fontColor=color;
		// 	},//设定字体颜色
		getSignMessage:function(){
			return Data;
		},//获得标记位置数据
		//加载标记 percent_x：横坐标压缩百分比，percent_y：纵坐标压缩百分比
		loadingSign:function(data,percent_x,percent_y,callback){
			loading(data,percent_x,percent_y,callback);
			Data=Data.concat(data);
		},
		//开始报警
		startAlarm:function(deviceId){
			$(DOM+ " [data-plpDeviceType='3'][data-plpDeviceIdnty='"+deviceId+"'] img").attr("src",jsConst.basePath+"/static/module/sign/png/baojingqi.gif");
			
			//var alarmPoint_tmp = $(DOM+ " [data-plpDeviceType='3'][data-plpDeviceIdnty='"+deviceId+"'] img").addClass("imageboxall_abs img .alarm_point_max");
			$(DOM+ " [data-plpDeviceType='3'][data-plpDeviceIdnty='"+deviceId+"'] img")[0].style.width="60px";
			$(DOM+ " [data-plpDeviceType='3'][data-plpDeviceIdnty='"+deviceId+"'] img")[0].style.height="60px";
		},
		//停止报警
		stopAlarm:function(deviceId){
			$(DOM+ " [data-plpDeviceType='3'][data-plpDeviceIdnty='"+deviceId+"'] img").attr("src",jsConst.basePath+"/static/module/sign/png/baojingqi.png");
			
			//var alarmPoint_tmp = $(DOM+ " [data-plpDeviceType='3'][data-plpDeviceIdnty='"+deviceId+"'] img").removeClass("imageboxall_abs img .alarm_point_max");
			$(DOM+ " [data-plpDeviceType='3'][data-plpDeviceIdnty='"+deviceId+"'] img")[0].style.width="35px";
			$(DOM+ " [data-plpDeviceType='3'][data-plpDeviceIdnty='"+deviceId+"'] img")[0].style.height="35px";
		},
		signOnClick:function(deviceCusNumber,deviceType,deviceId,deviceName){
			//图标点击事件
			//摄像机
			if(deviceType==cameraTypeValue){
				/*videoClient.playVideoHandle({
					'subType': 1, //1 手动 2 自动
					'layout': 1,
					'data': [{
						'index': 0,
						'cameraId': deviceId
					}],
					'callback': function (data) {
					}
				});*/
				if(jsConst.VIDEO_PLAYER_TYPE=='1'){
					$("#dialogId_videoPluginDemo").dialog("close");
					$("#dialogId_videoPluginDemo").dialog({
						width: 800,
						height: 600,
						title: '视频调阅',
						onOpen: function() {
							// 播放选中的摄像头实时视频
							videoPlugin.multiVideoPlayHandle({
								'container': $("div[id='dialogId_videoPluginDemo']"),
								'subType': 1,
								'layout': 1,
								'data': [{
									'index': 0,
									'cameraId': deviceId
								}],
								'callback': function (data) {
								}
							});
						},
						onClose: function () {
							videoPlugin.videoPlayerClear();
						}
					});
					$("#dialogId_videoPluginDemo").dialog("open");
				}else if(jsConst.VIDEO_PLAYER_TYPE=='0'||!jsConst.VIDEO_PLAYER_TYPE){
					if(isClickOpenVideo == '0'){
						videoClient.playVideoHandle({
							'subType': 1, //1 手动 2 自动
							'layout': 1,
							'data': [{
								'index': 0,
								'cameraId': deviceId
							}],
							'callback': function (data) {
							}
						});
					}
				}
	 		}
			//报警器
			else if(deviceType==alarmTypeValue){
				//var data = {};
				//data["deviceId"] = deviceId;
				//data["talkIdntys"] = JSON.stringify(talkIdntys);
				//var data={};
				//dubgger;
	 			if(deviceCusNumber=="4303"){//雁南监狱周界报警
	 				$.ajax({
	 					type : "get",
	 					url : jsConst.basePath+'/alertor/findById?id='+deviceId,
	 					//data : data,
	 					dataType : "json",
	 					success : function(data) {
	 						if (data.success) {
	 						var abdTypeIndc=data.obj.abdTypeIndc;
	 						if(abdTypeIndc=="3"){//周界红外
	 						//	alert(abdTypeIndc);
	 							$("#confirmDialog").dialog("option", {
	 								title: deviceName+"操作窗口",
	 								width: 900,
	 								height: 700,
	 				 				url:jsConst.basePath+"/bfcf/controlMultiDialog?bjqId="+deviceId
	 							});
	 							$("#confirmDialog").dialog("open");
	 							
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
	 		}
			//门禁
			else if(deviceType==doorTypeValue){
	 			// 
	 			$("#confirmDialog").dialog("option", {
					title: deviceName+"门禁操作窗口",
					width: 400,
					height: 150,
	 				url:jsConst.basePath+"/doorlinkage/controlDoorDialog",
					buttons: [{
					    text: "开门",
					    id: "openDoorId",
					    click: function () {
					        // alert("开门");
	 						console.log("开门");
					        openDoor(deviceId, "1");
					        $("#confirmDialog").dialog("close");
					    }        
					},{
					    text: "关门",
					    id: "closeDoorId",
					    click: function () {
					        // alert("关门");
	 						console.log("关门");
					    	openDoor(deviceId, "4");
					        $("#confirmDialog").dialog("close");
					    }        
					},{
					    text: "取消",
					    id: "cancelId",
					    click: function () {
					        // alert("cancel");
	 						console.log("取消");
					        $("#confirmDialog").dialog("close");
					    }            
					}]
				});
				$("#confirmDialog").dialog("open");
	 		}
			//对讲
			else if(deviceType==talkBackTypeValue){
	 			$.confirm( {
	 				message:"确认呼叫"+deviceName+"吗？",
	 				iframePanel:true,
	 				callback: function(sure) {
	 					if (sure == true) {
	 						startTalk(deviceCusNumber,deviceId);
	 					}
	 					if (sure == false) {
	 						console.log('cancel');
	 					}
	 				}
	 			});
	 		}
			//广播
			else if(deviceType==broadcastTypeValue){
	 			$("#confirmDialog").dialog("option", {
					title: deviceName+"广播操作窗口",
					width: 800,
					height: 600,
	 				url:jsConst.basePath+"/broadcastLinkage/controlBroadcastDialog?broadcastId="+deviceId
				});
				$("#confirmDialog").dialog("open");
	 		}
			//监舍标签
	 		else if(deviceType==jsbqTypeValue){
	 			var jsId = deviceId.split("_");
		 		var cusNumber = jsId[0];
		 		var lch = jsId[1];
		 		var jsh = jsId[2];
	 			//监舍标签
	 			$("#jszfDialog").dialog("option", {
					title:deviceName+"监舍罪犯信息",
					width:1000,
					height:600,
					url:jsConst.basePath+"xxhj/zfjbxx/jsPrisonerInfo?cusNumber="+cusNumber+"&lch="+lch+"&jsh="+jsh,
					onClose : function() { //回调事件
					}
				});
				$("#jszfDialog").dialog("open");
	 		}
		}
	};
	
	//开门
	function openDoor(deviceId, action){
		var doorIds = [];
		doorIds.push(deviceId);
		
		var data = {};
		data["doorIds"] = JSON.stringify(doorIds);
		data["action"] = action;//1 开门 2禁止开门 3恢复开门 4关门
		var ur = jsConst.basePath+"/doorlinkage/controlDoor.json";
		$.ajax({
			type : "post",
			url : ur,
			data : data,
			dataType : "json",
			success : function(data) {
				if (data.success) {
					$.messageQueue({
						message : data.msg,
						cls : "success",
						iframePanel : true,
						type : "info"
					});
					if (data.obj) {
						if(jsConst.VIDEO_PLAYER_TYPE=='1'){
							$("#dialogId_videoPluginDemo").dialog("close");
							$("#dialogId_videoPluginDemo").dialog({
								width: 800,
								height: 600,
								title: '视频调阅',
								onOpen: function() {
									// 播放选中的摄像头实时视频
									videoPlugin.multiVideoPlayHandle({
										'container': $("div[id='dialogId_videoPluginDemo']"),
										'subType': 2,
										'layout': data.obj.length,
										'data': data.obj,
										'callback': function (data) {
										}
									});
								},
								onClose: function () {
									videoPlugin.videoPlayerClear();
								}
							});
							$("#dialogId_videoPluginDemo").dialog("open");
						}else if(jsConst.VIDEO_PLAYER_TYPE=='0' || !jsConst.VIDEO_PLAYER_TYPE){
							videoClient.playVideoHandle({
								'subType' : 2,
								'layout' : data.obj.length,
								'data' : data.obj
							});
						}
					}
				} else {
					$.messageQueue({
						message : data.msg,
						cls : "warning",
						iframePanel : true,
						type : "info"
					});
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
	//呼叫
	function startTalk(deviceCusNumber,deviceId){
		var talkIdntys = [];
		talkIdntys.push(deviceId);
		var data = {};
		data["cusNumber"] = deviceCusNumber;
		data["talkIdntys"] = JSON.stringify(talkIdntys);
		$.ajax({
			type : 'post',
			url : jsConst.basePath+'/realTimeTalk/startTalk.json',
			data : data,
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

        //临时添加打开对讲联动摄像头 -wq
        console.log(data.cusNumber + "" + data.talkIdntys);
        $.ajax({
            type : 'post',
            url : jsConst.basePath+'/jfsb/camera/getCameraIdByTalkbackId.json',
            data : data,
            dataType : 'json',
            success : function(data_cameraIds) {
                console.log(data_cameraIds+""+1);
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
        });
	}
		/*
		********定义插件
		*/

	/*document.oncontextmenu = function(e){
         e.preventDefault();
         }*/;//阻止鼠标右键默认事件
	function defined(dom){		
			$(document).on('mouseover','[id*=Ts]',function(){
			var l=$(this).offset().left,T=$(this).offset().top;
			$('.hintBox').remove();
			var t=$(this).attr("theSign");
			$('body').append("<div class='hintBox'>"+t+"</div>");
			var Hw=$('.hintBox').width(),Hh=$('.hintBox').height();
			if(Hh>35){
				$('.hintBox').css({"text-align":"left"});
			}
			$('.hintBox').append("<div class='triangle-down'></div>");
			$('.triangle-down').css({"left":Hw/2-10,"top":Hh});
			$('.hintBox').css({"left":l-Hw/2+20,"top":T-Hh-10});
			$('.hintBox').css({"z-index":5000});
			if(changeBodyColor){
				$('.hintBox').css("background",bodyColor);
				$('.triangle-down').css("border-top","10px solid "+bodyColor);
				}
			if(changeFontColor){
				$('.hintBox').css("color",fontColor);
				}
			});//显示提示
		$(document).on('mouseleave','[id*=Ts]',function(){
			 $('.hintBox').remove();
			});//
		$(document).click(function(){
			$('.chooseBox').remove();
			});
		//点击消失
		}
		/*
		********定义事件
		*/


		/**************************************************
		*****************add by zk start******************
		**************************************************/
		
		function loading(data,percent_x,percent_y,callback){
					
			//初始化摄像头、报警器数量
			var cameraNum=0;
			var alarmNum=0;
			
			console.log(data);
			for(var i=0;i<data.length;i++){
			 	var innerHtml = "<div class='signIndex' id='Ts"+data[i].id+"' theSign="+data[i].plpPointName+" " +
			 			" data-plpCusNumber="+data[i].plpCusNumber+" data-plpPointName="+data[i].plpPointName+" " +
			 			" data-plpDeviceType="+data[i].plpDeviceType+" data-plpDeviceIdnty="+data[i].plpDeviceIdnty+" " +
			 			" data-plpLayerIdnty="+data[i].plpLayerIdnty+
			 			" ondblclick='$.sign_show.signOnClick(\""+data[i].plpCusNumber+"\",\""+data[i].plpDeviceType+"\",\""+data[i].plpDeviceIdnty+"\",\""+data[i].plpPointName+"\")'>";
			 	if(data[i].plpDeviceType != null){
			 		if(data[i].plpDeviceType==cameraTypeValue){
			 			cameraNum++;
			 			if(data[i].plpDeviceModel=='1'){
			 				if(data[i].status && data[i].status=='1'){
			 					innerHtml += "<span><img src='"+jsConst.basePath+"/static/module/video/css/img/camera_spherical_1.png'/></span>";
			 				}else if(data[i].status && data[i].status=='2'){
			 					innerHtml += "<span><img src='"+jsConst.basePath+"/static/module/video/css/img/camera_spherical_2.png'/></span>";
			 				}else{
			 					innerHtml += "<span><img src='"+jsConst.basePath+"/static/module/video/css/img/camera_spherical_0.png'/></span>";
			 				}	
			 			}else if(data[i].plpDeviceModel=='2'){
			 				if(data[i].status && data[i].status=='1'){
			 					innerHtml += "<span><img src='"+jsConst.basePath+"/static/module/video/css/img/camera_gun_1.png'/></span>";
			 				}else if(data[i].status && data[i].status=='2'){
			 					innerHtml += "<span><img src='"+jsConst.basePath+"/static/module/video/css/img/camera_gun_2.png'/></span>";
			 				}else{
			 					innerHtml += "<span><img src='"+jsConst.basePath+"/static/module/video/css/img/camera_gun_0.png'/></span>";
			 				}	
			 			}else if(data[i].plpDeviceModel=='3'){
			 				if(data[i].status && data[i].status=='1'){
			 					innerHtml += "<span><img src='"+jsConst.basePath+"/static/module/video/css/img/camera_hemisphere_1.png'/></span>";
			 				}else if(data[i].status && data[i].status=='2'){
			 					innerHtml += "<span><img src='"+jsConst.basePath+"/static/module/video/css/img/camera_hemisphere_2.png'/></span>";
			 				}else{
			 					innerHtml += "<span><img src='"+jsConst.basePath+"/static/module/video/css/img/camera_hemisphere_0.png'/></span>";
			 				}	
			 			}
			 		}else if(data[i].plpDeviceType==alarmTypeValue){
			 			alarmNum++;
			 			innerHtml += "<span><img src='"+jsConst.basePath+"/static/module/sign/png/baojingqi.png'/></span>";
			 		}else if(data[i].plpDeviceType==doorTypeValue){
			 			innerHtml += "<span><img src='"+jsConst.basePath+"/static/module/sign/png/door.png'/></span>";
			 		}else if(data[i].plpDeviceType==talkBackTypeValue){
			 			innerHtml += "<span><img src='"+jsConst.basePath+"/static/module/sign/png/interphone.png'/></span>";
			 		}else if(data[i].plpDeviceType==broadcastTypeValue){
			 			innerHtml += "<span><img src='"+jsConst.basePath+"/static/module/sign/png/broadcast.png'/></span>";
			 		}
			 		else if(data[i].plpDeviceType==jsbqTypeValue){
			 			innerHtml += "<label style='color: #FFD105;font-size: 15px;font-weight: bold;'>"+data[i].plpPointName+"</label>";
			 		}
			 	}
			 	innerHtml += "</div>";
				$(DOM).append(innerHtml);
				if(!percent_x){
					percent_x = 1;
				}else if(!percent_y){
					percent_y = 1;
				}
				//点位图标长宽也进行缩放（监舍标签除外，因为会出现字体换行）add by zk 20180801
				if(data[i].plpDeviceType!=jsbqTypeValue){
					var original_width=$(DOM+' #Ts'+data[i].id).css("width");
					var original_height=$(DOM+' #Ts'+data[i].id).css("height");
					$(DOM+' #Ts'+data[i].id).css("width",parseInt(original_width)*percent_x+"px");
					$(DOM+' #Ts'+data[i].id).css("height",parseInt(original_height)*percent_y+"px");
				}
				$(DOM+' #Ts'+data[i].id).css({"left":parseInt(data[i].plpPointX)*percent_x,"top":parseInt(data[i].plpPointY)*percent_y});
				$(DOM+' #Ts'+data[i].id).rotate(data[i].plpPointAngle);
				if(changeSignColor){
					$(DOM+' #Ts'+data[i].id).css("border",signColor+" 3px solid");
				}//改变了颜色
			}
			//执行回调
			if(typeof(callback)=="function"){
				callback();
			}
			/*$('#plane_monitorPoint').text(cameraNum+"个");
			$('#plane_alarmPoint').text(alarmNum+"个");*/
		}//载入数据

		/**************************************************
		*****************add by zk end******************
		**************************************************/
	})(jQuery);
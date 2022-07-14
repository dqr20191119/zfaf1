
	/*******************************************************************设备控制面板**************************************************/
	var videoClient = window.top.videoClient;
	var jsConst = window.top.jsConst;
	
	/**
	 * 查询报警预案信息
	 * @param cusNumber_ 监狱号
	 * @param dvcIdnty_ 报警器编号
	 * @returns
	 */
	function loadAlarmPlan(cusNumber_, dvcIdnty_, alarmPlanId_) {
		// debugger;
		if ($("#device_show").is(":visible")) {
			//如果预案信息已经展示了，就不需要再请求数据，渲染界面
			return;
		}
		
		var data = {};
		data["cusNumber"] = cusNumber_; 
		data["dvcIdnty"] = dvcIdnty_;
		data["alarmPlanId"] = alarmPlanId_;
		console.log("loadAlarmPlan data is " + JSON.stringify(data));
		
		var url = jsConst.basePath + "/alarm/handle/queryAlarmPlanDtls.json";
		var callBack = function(resDate) {
			 console.log("loadAlarmPlan resDate = " + JSON.stringify(resDate));
			if (resDate.success) {
				alarmPlanId = resDate.obj.planId;//预案id
				addContentForDiv(resDate.obj);//生成关联设备html
				if(isEmpty(alarmPlanId_)){
					//查询报警预案是否关联广播预案plan/findByPlanId  bprPlanId
					var url2 = jsConst.basePath + "/plan/findByPlanId.json?bprPlanId"+alarmPlanId;
					var data2 ={};
					data2["bprPlanId"] =alarmPlanId;
					var callBack2 =function(resdata){
						if(resdata.success && resdata.obj.bprBroadcastPlanId!=null){
							var broadcastPlan = resdata.obj.bprBroadcastPlanId;
							console.log("自动播放广播的广播预案id为:"+broadcastPlan);
							startBroadPlan(broadcastPlan);
						}
					}
					ajaxTodo(url2, data2, callBack2);
					callDevice(resDate.obj);//自动处理的设备列表
				}
			} else {
				$.messageQueue({ message : resDate.msg, cls : "warning", iframePanel : true, type : "info" });
			}
		};
		ajaxTodo(url, data, callBack);
		 
	}
	
	function addContentForDiv(map) {
		var device = map.deviceName; // 1、门禁 2、摄像机 3、 广播 4、大屏  5、对讲 6.广播预案
		if (device == null || device == undefined) {
			return;
		}
		var htmlH3 = "";
		var htmlDiv = [];
		var htmlContent = "";
		
		var titleHmtl = [];
		$("#device_show").show();
		for ( var key in device) {
			if(jsConst.USER_LEVEL == 1 && (key == "4" || key == "5")){
				//省局用户不操作大屏，对讲
				continue;
			}
			if(jsConst.USER_LEVEL == 3 &&  key == "4" ){
				//监区用户不操作大屏
				continue;
			}
			
			var htmlDevice = "";
			var tabName = device[key];
			var arr = map.device[key];
			var itemId = "tabName-"+key,frameId = "#"+itemId;
			//htmlH3 = "<h3>【" + tabName + "】</h3>";
			titleHmtl.push("<li><a href=\"" + frameId + "\">"+tabName+"</a></li>");
			var checkClass = "checkClass_" + key;
			if(key !="6"){
				for (var j = 0; j < arr.length; j++) {
					var deviceId = arr[j].PDR_DVC_IDNTY;
					var deviceName = arr[j].PDR_DVC_NAME;
					var outo = arr[j].PDR_OUTO_INDC == "1" ? "【自动】":"";
					
					if(key == "4"){
						htmlDevice = htmlDevice + "<input id=\"" + deviceId + "\"class=\""+checkClass+"\" name=\"radio_\" type=\"radio\" value =\""+deviceId+"\"/>  <span>" + deviceName + outo +"</span></br>";
					}else{
						htmlDevice = htmlDevice + "<input id=\"" + deviceId + "\"class=\""+checkClass+"\" type=\"checkbox\" value =\""+deviceId+"\"/>  <span>" + deviceName + outo +"</span></br>";
					}
				}
			}
			
			if(key=="6"){
				//广播预案
				var broadcastPlanName =map.alarmPlanBroadcastPlan.bprBroadcastPlanName;
				var broadcastPlanId =map.alarmPlanBroadcastPlan.bprBroadcastPlanId;
				htmlDevice =htmlDevice + "<input id=\"" + broadcastPlanId + "\"class=\""+ checkClass +"\" type=\"checkbox\" value =\""+broadcastPlanId+"\"/>  <span>" + broadcastPlanName +"</span></br>"
			}
			
			
				htmlDevice = "<div style=\"width: 285px;\">" + htmlDevice + "</div>"	
			
			switch (key) {
			case "1":
				//门禁
				htmlDiv.push("<div id=\""+itemId+"\" class=\"itemclass\"> <p> <div id=\"intro1\" class=\"scrollCls\" style=\"text-align: left;\"> "
					+ htmlDevice 
					+ "<div class=\"toolbarbtn\"><button style=\"margin :5px;\" class=\"button_all\" onClick= \"checkAll('"
					+ checkClass + "')\">全选</button>"
					+ "<button style=\"margin :5px;\" class=\"button_open\" onClick= \"ctrl('" + checkClass + "','" + key + "','1')\">开门</button>" 
					+ "<button style=\"margin :5px;\" class=\"button_open\" onClick= \"ctrl('" + checkClass + "','" + key + "','2')\">禁止开门</button>" 
					+ "<button style=\"margin :5px;\" class=\"button_open\" onClick= \"ctrl('" + checkClass + "','" + key + "','3')\">恢复开门</button></div>" 
					+ "</div> </p> </div>");
				break;
			case "2":
				//摄像机
				htmlDiv.push("<div id=\""+itemId+"\" class=\"itemclass\"> <p> <div id=\"intro2\" class=\"scrollCls\" style=\" text-align: left;\"> "
					+ htmlDevice 
					+ "<div class=\"toolbarbtn\"><button style=\"margin :5px;\" class=\"button_all\" onClick= \"checkAll('"
					+ checkClass + "')\">全选</button>"
					+ "<button style=\"margin :5px;\" class=\"button_open\" onClick= \"ctrl('" + checkClass + "','" + key + "')\">打开</button></div>" 
					+ "</div> </p> </div>");		
				break;
			case "6":
				//广播预案
				htmlDiv.push("<div id=\""+itemId+"\" class=\"itemclass\"> <p> <div id=\"intro3\" class=\"scrollCls\" style=\"text-align: left;\"> "
					+ htmlDevice 
					+ "<div class=\"toolbarbtn\"><button style=\"margin :5px;\" class=\"button_all\" onClick= \"checkAll('"
					+ checkClass + "')\">全选</button>"
					+ "<button style=\"margin :5px;\" class=\"button_open\" onClick= \"ctrl('" + checkClass + "','" + key + "','1')\">开始播放</button>" 
					+ "<button style=\"margin :5px;\" class=\"button_open\" onClick= \"ctrl('" + checkClass + "','" + key + "','2')\">停止播放</button></div>" 
					+ "</div> </p> </div>");		
				break;
			case "4":
					//大屏 省局用户不操作大屏 
					htmlDiv.push("<div id=\""+itemId+"\" class=\"itemclass\"> <p> <div id=\"intro4\" class=\"scrollCls\" style=\"text-align: left;\"> "
						+ htmlDevice 
						+ "<div class=\"toolbarbtn\"><button style=\"margin :5px;\" class=\"button_open\" onClick= \"ctrl('" + checkClass + "','" + key + "')\">切换</button></div>" 
						+ "</div> </p> </div>");	
				break;
			case "5":
					//对讲  省局用户不操作 对讲
					htmlDiv.push("<div id=\""+itemId+"\" class=\"itemclass\"> <p> <div id=\"intro5\" class=\"scrollCls\" style=\"text-align: left;\"> "
						+ htmlDevice 
						+ "<div class=\"toolbarbtn\"><button style=\"margin :5px;\" class=\"button_all\" onClick= \"checkAll('"
						+ checkClass + "')\">全选</button>"
						+ "<button style=\"margin :5px;\" class=\"button_open\" onClick= \"ctrl('" + checkClass + "','" + key + "')\">拨通</button></div>" 
						/* + "<button style=\"margin :5px;\" class=\"button_open\" onClick= \"ctrl('" + checkClass + "')\">挂断</button>"  */
						+ "</div> </p> </div>");
				break;
			default:
				break;
			}	
			htmlH3 = '<ul>'+titleHmtl.join('')+'</ul>';
			htmlContent = htmlH3 + htmlDiv.join('');
		}
		var praDiv = "<div id=\"tabsId\" >" + htmlContent + "</div>";
		$("#divId_device").append(praDiv);
		initDiv();//控件初始化
	
	}

	
	function initDiv() {
		$("#tabsId").tabs({
			heightStyle:"fill"
		})
		//按钮js初始化
		$('.button_all').button({ componentCls : "btn-primary" });
		$('.button_cancel').button({ componentCls : "btn-primary" });
		$('.button_open').button({ componentCls : "btn-primary" });
		/*$("#tabName-2").mCustomScrollbar({
			  theme:"minimal-dark",
		});
		$("#tabName-1").mCustomScrollbar({
			  theme:"minimal-dark",
		});*/
	}
	
 
	/** 
	 * 设备自动列表设备调用
	 * @param map
	 * @returns
	 */
	function callDevice(map) {
		var autoName = map.autoName;
		var auto = map.auto;
		console.log("callDevice autoName = " + JSON.stringify(autoName) + ", auto = " + JSON.stringify(auto));
		if (autoName == null || autoName == undefined) {
			return;
		}
		for ( var key in auto) {
			var devices = auto[key];
			// 1、门禁 2、摄像机 3、 广播 4、大屏  5、对讲
			// console.log("callDevice key = " + key + ", devices = " + JSON.stringify(devices));
			if(key == "2"){
				//调用视频设备	 
				if(jsConst.VIDEO_PLAYER_TYPE=='1'){
					$("#dialogId_videoPluginDemo").dialog("close");
					$("#dialogId_videoPluginDemo").dialog({
						width: 800,
						height: 600,
						title: '视频调阅',
						onOpen: function() {
							// 播放选中的摄像头实时视频
							videoPlugin.multiVideoPlayHandle({
								'container': $("#dialogId_videoPluginDemo"),
								'subType': 2,
								'layout': devices.length,
								'data': devices,
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
					videoClient.playVideoHandle({ 'subType' : 2, 'layout' : devices.length, 'data' : devices, 'callback' : function(data) {  /* _record(); 开始录像 */ } });
				}
			} else if(key == "4"){
				if(jsConst.USER_LEVEL == 2){
					ctrlDevice(key, devices, "2", alarmAddress);	//省局和监区用户不操作大屏
				}
			} else if(key == "5"){
				if(jsConst.USER_LEVEL != 1){
					ctrlDevice(key, devices, "2");	//省局用户不操作对讲
				}
			} else {
				ctrlDevice(key, devices, "2");
			}
			$.messageQueue({ message : "【自动】" + autoName[key], cls : "success", iframePanel : true, type : "info" });
		}
	}
	
	 
	/**
	 *  ajax 请求设备操作
	 * @param deviceType 设备类型
	 * @param deviceIds 设备ids
	 * @param action 门禁操作动作
	 * @returns
	 */
	function ctrlDevice(deviceType ,deviceIds ,action) {
		var data = {};
		data["deviceType"] = deviceType; 
		var cameraIds = [];
		if( deviceType == '4'){
			$(".checkClass_2").each(function() {
				cameraIds.push(this.value);
			});
		}
		data["cameraIds"] = JSON.stringify(cameraIds); 
		data["deviceIds"] = JSON.stringify(deviceIds); 
		data["action"] = action;//1 开门 2禁止开门 3恢复开门
		data["cusNumber"] = cusNumber; 
		data["alarmAddress"] = alarmAddress; //报警地点，大屏切换时，投屏文字
		var ur = jsConst.basePath + "/alarm/handle/ctrlDevice.json";
		var callBack = function(resDate) {
			if (resDate.success) {
				$.messageQueue({ message : resDate.msg, cls : "success", iframePanel : true, type : "info" });
			} else {
				$.messageQueue({ message : resDate.msg, cls : "warning", iframePanel : true, type : "info" });
			}
		};
		this.ajaxTodo(ur, data, callBack);
	}
	
	//全选
	function checkAll(checkclass) {
		$("." + checkclass).each(function() {
			this.checked = !this.checked;
		});

	}
	 
	/**
	 * 设备操作按钮点击事件
	 * @param checkclass 
	 * @param deviceType 设备类型
	 * @param action 开关门动作指令
	 * @returns
	 */
	function ctrl(checkclass,deviceType,action) {
		var deviceIds = [];
		$("." + checkclass).each(function() {
			if (this.checked) {
				deviceIds.push(this.value);
			}
		});
		if (deviceIds.length > 0 ) {
			if (deviceType == "2"){
				//调用视频设备
				if(jsConst.VIDEO_PLAYER_TYPE=='1'){
					$("#dialogId_videoPluginDemo").dialog("close");
					$("#dialogId_videoPluginDemo").dialog({
						width: 800,
						height: 600,
						title: '视频调阅',
						onOpen: function() {
							// 播放选中的摄像头实时视频
							videoPlugin.multiVideoPlayHandle({
								'container': $("#dialogId_videoPluginDemo"),
								'subType': 2,
								'layout': deviceIds.length,
								'data': deviceIds,
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
					videoClient.playVideoHandle({ 'subType' : 2, 'layout' : deviceIds.length, 'data' : deviceIds, 'callback' : function(data) {} });
				}
			} else if (deviceType == "6"){
				var broadPlanId = deviceIds;
				if(action=="1"){
					//开始广播预案
					startBroadPlan(broadPlanId);
				}else if(action=="2"){
					//关闭广播预案
					stopBroadPlan(broadPlanId);
				}
				
			} else if (deviceType == "4"){
				 //大屏
				 if(deviceIds.length > 1 ){
					$.messageQueue({ message : "大屏预案不可多选", cls : "warning", iframePanel : true, type : "info" });
				 } else {
					ctrlDevice(deviceType, deviceIds, action); 
				 }
			} else {
				ctrlDevice(deviceType, deviceIds, action);
			}
		} else {
			$.messageQueue({ message : "请选择要操作的设备！", cls : "warning", iframePanel : true, type : "info" });
		}
	}
	/**
	 * 开始广播预案
	 * @param broadPlanId 广播预案id
	 * @returns
	 */
	function startBroadPlan(broadPlanId){
		//查询关联的广播和曲目
		var data = {};
		//data["id"] = broadPlanId;
		var url = jsConst.basePath + "/broadcastPlan/queryById.json?id="+broadPlanId;
		var callBack = function(resDate) {
			if (resDate.success) {
				var infos =resDate.obj;
				//多个广播
				var broadcastIds =infos.broadcasts;
				//broadcasts 
				//曲目id
				var content =infos.dbpBroadcastFileDtlsId;
				for(var i =0;i<broadcastIds.length;i++){
					//开始播放广播
					var broadcastId =broadcastIds[i].ID;
					var ur = jsConst.basePath +"broadcast/queryBroadcastDtoById.json?id="+broadcastId;
					$.ajax({
						type : "post",
						url : ur,
						dataType : "json",
						success : function(data) {
							if (data.success) {
								var info = data.obj;
								if(info.bbdSttsIndc != '0' ) {
									if(info.bbdSttsIndc == '1') {
										$.messageQueue({ message : "广播"+info.bbdName+"状态为离线，请联系管理员", cls : "warning", iframePanel : true, type : "info" });
									} else if(info.bbdSttsIndc == '2') {
										$.messageQueue({ message : "广播"+info.bbdName+"状态为使用中，请检查后再试", cls : "warning", iframePanel : true, type : "info" });
									} else {
										$.messageQueue({ message : "广播"+info.bbdName+"状态未知，请联系管理员", cls : "warning", iframePanel : true, type : "info" });
									}
									return;
								}
								//发送开始广播指令
								 // 参数封装
					            var paramData = {};
					            paramData["broadcastId"] = broadcastId;
					            paramData["contentType"] = "1";
					            paramData["content"] = content;
					            console.log("开始广播参数paramData = " + JSON.stringify(paramData));
					            
								$.ajax({
									type : 'post',
									url : jsConst.basePath+"broadcastRecord/startPlay.json",
									data : paramData,
									dataType : 'json',
									success : function(data) {
							            if(data.code == 200) {
											$.messageQueue({ message : data.message, cls : "success", iframePanel : true, type : "info" });
							            } else if(data.code == 500) {
											$.messageQueue({ message : data.message, cls : "warning", iframePanel : true, type : "info" });
							            }
									},
									error : function(XMLHttpRequest, textStatus, errorThrown) {
										$.messageQueue({ message : textStatus, cls : "warning", iframePanel : true, type : "info" });
									}
								});
								
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
			} else {
				$.messageQueue({ message : resDate.msg, cls : "warning", iframePanel : true, type : "info" });
			}
		};
		this.ajaxTodo(url, data, callBack);
	}
	
	
	
	
	/**
	 * 停止广播播放
	 * @param broadPlanId 广播预案id
	 * 
	 * @returns
	 */
	function stopBroadPlan(broadPlanId){
		//查询关联的广播和曲目
		var data = {};
		var url = jsConst.basePath + "/broadcastPlan/queryById.json?id="+broadPlanId;
		var callBack = function(resDate) {
			if (resDate.success) {
				var infos =resDate.obj;
				//多个广播
				var broadcastIds =infos.broadcasts;
				for(var i =0;i<broadcastIds.length;i++){
					var broadcastId = broadcastIds[i].ID;
					var ur =  jsConst.basePath+"broadcast/queryBroadcastDtoById.json?id="+broadcastId;
					$.ajax({
						type : "post",
						url : ur,
						dataType : "json",
						success : function(data) {
							if (data.success) {
								var info = data.obj;
								//info.bbdLatestRecord.id
								//var bbdLatestRecord = info.bbdLatestRecord.id;
								var paramData = {};
								//最近一次广播播放记录id
								paramData["id"] = info.bbdLatestRecord.id;
								paramData["broadcastId"] = broadcastId;
								
								console.log("停止广播播放参数:paramData = " + JSON.stringify(paramData));
								$.ajax({
									type : 'post',
									url :  jsConst.basePath+"broadcastRecord/stopPlay.json",
									data : paramData,
									dataType : 'json',
									success : function(data) {
										if(data.code == 200) {
											$.messageQueue({ message : data.message, cls : "success", iframePanel : true, type : "info" });
										} else if(data.code == 500) {
											$.messageQueue({ message : data.message, cls : "warning", iframePanel : true, type : "info" });
										}
									},
									error : function(XMLHttpRequest, textStatus, errorThrown) {
										$.messageQueue({ message : textStatus, cls : "warning", iframePanel : true, type : "info" });
									}
								});
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
			} else {
				$.messageQueue({ message : resDate.msg, cls : "warning", iframePanel : true, type : "info" });
			}
		};
		this.ajaxTodo(url, data, callBack);
		}
	
	
	
	
	
	/*******************************************************************重点罪犯**************************************************/
	
	/**
	 * 查询监舍罪犯信息列表
	 * @param cusNumber_ 监狱号
	 * @param lch 楼层号
	 * @param jsh 监舍号
	 * @returns
	 */
	function queryCellPrisoner(cusNumber_, lch, jsh) {
		 
		var data = {};
		data["cusNumber"] = cusNumber_;
		data["lch"] = lch; 
		data["jsh"] = jsh; 
		
		var url = jsConst.basePath + "/xxhj/zfjbxx/queryJSPrisonerInfo.json";
		
		var callBack = function(resDate) {
			 if( !isNull(resDate.data) && resDate.data.length > 1){
				 $("#switch-criminal").show();
				 addContentForPrisonerDiv(resDate.data);
			 }
		};
		ajaxTodo(url, data, callBack);
	}
	
	function addContentForPrisonerDiv(data) {
		var content = "";
		var index = data.length;
		if (data.length > 10){
			index = 10;//控件超过10个，ie下失灵
		}
		
		for (var i = 0; i < index; i++) {
			var img = "<img id=\""+data[i].PRSNR_IDNTY+"\" class=\"img_prisoner\" src=\""+jsConst.basePath + "/common/all/findZfPicInfo?zfBh="+data[i].PRSNR_IDNTY+"&mtNrFl=3\"/>";
			content = content + "<li class=\"caroSwitchEnd1\"> <a href=\"javascript:void(0)\" onclick=\"prisonerClick("+data[i].PRSNR_IDNTY+")\"> " + img 
			+ "<p>" + data[i].NAME + "</p><p>" + data[i].PRSNR_IDNTY + "</p> </a> </li>";	
			
		}
		$(".zxx_test_list_list #caroBoxEnd1").append(content);
		$("#caroEndless-cri a").powerSwitch({
			container: $("#caroBoxEnd1"),
			number: 2
		});  

	}
	
	function prisonerClick(PRSNR_IDNTY) {
		$("#dialogId_jszfxx").dialog({
			width : 1000,       
			height : 800, 
			subTitle : '罪犯信息',
			url : jsConst.basePath + "/xxhj/zfjbxx/criminalArchivesFile?prisonerId=" + PRSNR_IDNTY,
		});

		$("#dialogId_jszfxx").dialog("open");
	}
	
	
	/*******************************************************************民警**************************************************/
	
	/**
	 * 查询民警信息
	 * @param cusNumber_  监狱号
	 * @param drptmntId_ 部门号
	 * @returns
	 */
	function queryPoliceList(cusNumber_, drptmntId_) {
		var data = {};
		data["pbdcusNumber"] = cusNumber_;
		data["pbdDrptmntId"] = drptmntId_; 
		
		var url = jsConst.basePath + "/xxhj/jnmj/queryPoliceByDid.json";
	 
		
		var callBack = function(resDate) {
			 if( !isNull(resDate.data) && resDate.data.length > 1){
				 $("#switch").show();
				 addContentForPoliceDiv(resDate.data);
			 }
		};
		ajaxTodo(url, data, callBack);
	}
	
	function addContentForPoliceDiv(data) {
		var content = "";
		var index = data.length;
		if (data.length > 10){
			index = 10;//控件超过10个，ie下失灵
		}
		for (var i = 0; i < index; i++) {

            //E:\workspace\IdeaProjects\idea_anfang\xxhjSystem\src\main\webapp\WEB-INF\static\rygl\img\icon-police.png
			//var img = "<img id=\""+data[i].PBD_POLICE_IDNTY+"\" class=\"img_police\" src=\""+jsConst.basePath + "/common/authsystem/findMjPicInfo?loginName="+data[i].PBD_LOGIN_NAME+"&demptId="+data[i].PBD_DRPTMNT_ID+"\" />";
            var img = "<img id=\""+data[i].PBD_POLICE_IDNTY+"\" class=\"img_police\" src=\""+jsConst.basePath + "/common/authsystem/findMjPicInfo?loginName="+data[i].PBD_LOGIN_NAME+"&demptId="+data[i].PBD_DRPTMNT_ID+"\" onerror= \"this.src='"+jsConst.basePath+"/static/rygl/img/icon-police.png'\" />";
           // var img = "<img id=\""+data[i].PBD_POLICE_IDNTY+"\" class=\"img_police\" src=\""+jsConst.basePath + "/common/authsystem/findMjPicInfo?loginName="+data[i].PBD_LOGIN_NAME+"&demptId="+data[i].PBD_DRPTMNT_ID+"\" onerror=\"javascript:this.src="+jsConst.basePath+"static/rygl/img/icon-police.png"+"\" />";
			content = content + "<li class=\"caroSwitchEnd\"> <a href=\"javascript:void(0)\" onclick=\"policeClick("+data[i].PBD_USER_ID+")\"> "+img+"<p>" + data[i].PBD_POLICE_NAME + "</p><p>" + data[i].PBD_POLICE_IDNTY + "</p> </a> </li>";	
		}
		
		$(".zxx_test_list_list #caroBoxEnd").append(content);
		$("#caroEndless a").powerSwitch({
			container: $("#caroBoxEnd"),
			number: 2
		});


		
	}
	
	function policeClick(USER_ID) {
		$("#dialogId_zbmjxx").dialog({
			width : 640,
			height : 550,
			subTitle : '民警信息',
			url : jsConst.basePath + "/xxhj/jnmj/openPoliceInfo?pbdcusNumber=" + cusNumber + "&pbdUserId=" + USER_ID,
		});
		$("#dialogId_zbmjxx").dialog("open");
	}
	
	////////////////////////////////////工具//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	/**
	 * 异步请求
	 * 
	 * @param url
	 *            相对路径
	 * @param args
	 *            参数
	 * @param success
	 *            请求成功后的回调函数
	 * @param error
	 *            请求失败后的回调函数
	 */
	function ajaxTodo(url, args, success) {
		_ajax(url, args, success,  true);
	}

	/**
	 * 同步请求
	 * 
	 * @param url
	 *            相对路径
	 * @param args
	 *            参数
	 * @param success
	 *            请求成功后的回调函数
	 * @param error
	 *            请求失败后的回调函
	 */
	function syncTodo(url, args, success) {
		_ajax(url, args, success, false);
	}

	/**
	 * 判断是否为空
	 * 
	 * @param obj
	 * @returns {Boolean}
	 */
	function isNull(obj) {
		return obj == undefined || obj == null || obj == 'undefined';
	};

	/**
	 * 判断是否不为空
	 * 
	 * @param obj
	 * @returns {Boolean}
	 */
	function isNotNull(obj) {
		return !window.isNull(obj);
	};

	function isFunction(obj) {
		return typeof obj == 'function';
	}

	/**
	 * 封装的ajax请求方法
	 * 
	 * @param url
	 *            相对路径
	 * @param args
	 *            参数
	 * @param success
	 *            请求成功后的回调函数
	 * @param async
	 *            是否异步ture/false
	 */
	function _ajax(url, args, success, async) {
		// 检查请求地址是否缺省根目录
		if (url.indexOf(jsConst.basePath) != 0) {
			url = jsConst.basePath + url;
		}
		$.ajax({
			'url' : url,
			'data' : args,
			'async' : async,
			'timeout' : 120 * 1000,
			'type' : 'POST',
			'dataType' : "json",
			'contentType' : "application/x-www-form-urlencoded; charset=utf-8",
			'cache' : false,
			'success' : function(result) {
				if(success && isFunction(success)){
					 success(result);
				}
			},
			'error' : function(request, status, e) {
				$.messageQueue({ message : status, cls : "warning", iframePanel : true, type : "info" });
			}
		});
	}

	/**
	 * 判断是否为空值
	 * 
	 * @param _v
	 *            待验证的值
	 */
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

	/**
	 * 判断是否不为空值
	 * 
	 * @param _v
	 *            待验证的值
	 */
	function isNotEmpty(_v) {
		return !isEmpty(_v);
	}
 
	
	
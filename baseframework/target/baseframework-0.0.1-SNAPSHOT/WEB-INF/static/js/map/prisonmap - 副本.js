/**
 * Add by linhe for 3dmap public function
 * 2018-01-03 
 */
/*(function (global) {
	global.prisonmap = 
}(this));*/
var map = {
		cusNumber:jsConst.CUS_NUMBER,
		//模型显示隐藏
		hideAreaId:null,
		hideParentAreaId:null,
		//视频巡视
		cameraTour:null,
		pointData:null,
		nowIndex:null,
		videoPlayTime:null,
		//省局登录初始化视角定位
		showPrisonMenu:function(){
			$.ajax({
				type : 'post',
				url : jsConst.basePath+'common/authsystem/findAllJyForCombobox',
				data : {},
				dataType : 'json',
				success : function(data) {
					console.log(data);
					var menuHtml = '';
					for(var i=0;i<data.length;i++){
						var li = '<li><a href="javascript:void(0);" onclick="map.loadMapInfo('+data[i].value+')">'
							+data[i].text;
						li = li + '</a></li>';
						menuHtml = menuHtml + li;
					}
					$('#viewPositionMenu').html(menuHtml);
					//调用显示隐藏子节点
					//map.showOrHideMenu();
				},
				error:function(e){
					console.log(e);
				}
			})
		},
		loadMapInfo:function(cusNumber){//省局加载监狱三维地图
			$('#__g').show();
			$('#map_2d').hide();
			jsConst.ROOT_ORGA_CODE = cusNumber;
			map_3d.openCep(cusNumber);
		},
		//监狱登录初始化左上角视角定位下拉菜单
		showViewMenu:function(){
			//debugger;
			//if(jsConst.USER_LEVEL!=null && jsConst.USER_LEVEL=='1'){//省局登录 
			var cusNumber = jsConst.USER_LEVEL=='1'?jsConst.PRISON_ORG_CODE:jsConst.CUS_NUMBER;
	        var parentAreaId = '';
	         $.ajax({
				type : 'post',
				url : jsConst.basePath+'view/findRegionView.json',
				data : {'cusNumber':cusNumber,'parentAreaId':parentAreaId,'confine':0},
				dataType : 'json',
				success : function(data) {
					console.log(data);
					/*<li> <a href="javascript:alert();centerDisplay('provMap');">1111111ces<b class="leftArrow"></b></a>
					<iframe class="sub-iframe"></iframe>
					<ul>
						<li onclick="centerDisplay('provMap')"><a href="javascript:void(0);">省局</a></li>
						<li onclick="centerDisplay('prisMap')"><a href="javascript:void(0);">监狱</a></li>
						<li><a onclick="openDialog(event,'css')" href="javascript:void(0);">CSS</a></li>
						<li><a href="javascript:void(0);">Photoshop</a></li>
					</ul>
				</li>*/ 
					
					var menuHtml = '';
					for(var i=0;i<data.length;i++){
						//父节点
						var li = '<li><a href="javascript:void(0);" onclick="map.viewPosition('
							+data[i].VFU_X_CRDNT+","+data[i].VFU_Y_CRDNT+","+data[i].VFU_Z_CRDNT +","+data[i].VFU_HEADING_CRDNT
							+","+data[i].VFU_TILT_CRDNT+",'"+data[i].ABD_PARENT_AREA_ID+"','"+data[i].VFU_AREA_ID
							+"',"+data[i].VFU_STTS+')">'+data[i].ABD_AREA_NAME;
						if(data[i].childens && data[i].childens.length>0){
							li = li + '<b class="leftArrow"></b>';
						}
						li = li + '</a>';
						//子节点
						var childenLi = '';
						//子节点
						if(data[i].childens && data[i].childens.length>0){
							childens = data[i].childens;
							childenLi = '<iframe class="sub-iframe"></iframe><ul>';
							for(var j=0;j<childens.length;j++){
								childenLi = childenLi + '<li><a href="javascript:void(0);" onclick="map.viewPosition('
								+childens[j].VFU_X_CRDNT+","+childens[j].VFU_Y_CRDNT+","+childens[j].VFU_Z_CRDNT
								+","+childens[j].VFU_HEADING_CRDNT+","+childens[j].VFU_TILT_CRDNT
								+",'"+childens[j].ABD_PARENT_AREA_ID+"','"+childens[j].VFU_AREA_ID+"',"+childens[j].VFU_STTS
								+')">'+childens[j].ABD_AREA_NAME+'</a></li>';
							}
							childenLi = childenLi + '</ul>';
							li = li + childenLi;
						}
						li = li + '</li>';
						menuHtml = menuHtml + li;
					}
					$('#viewPositionMenu').html(menuHtml);
					//调用显示隐藏子节点
					map.showOrHideMenu();
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					$.alert({
						message:textStatus,
						title:"提示信息",
						iframePanel:true
					});
				}
			}); 
		},
		showOrHideMenu:function(parentMenuId){
			$('li').has('ul').mouseenter(function(){
				$(this).children('ul').css('visibility','visible');
				var width = $(this).children('ul').width()-3;
				var height = $(this).children('ul').height() -2;
		 		$(this).children('iframe').width(width);
				$(this).children('iframe').height(height);
				$(this).children('iframe').css('visibility','visible'); 
				return false;
			}).mouseleave(function(){
				 $(this).children('ul').css('visibility','hidden');
	 			$(this).children('iframe').css('visibility','hidden'); 
			}); 
		},
		//视角定位
		viewPosition:function(x, y, z, heading, tilt, parentAreaId, areaId, isHide){
			var cusNumber = jsConst.USER_LEVEL=='1'?jsConst.PRISON_ORG_CODE:jsConst.CUS_NUMBER;
			map.setCameraInfo(x, y, z, heading, tilt);//摄像头定位
			/** 隐藏显示设备  */
			/** 
			参数 ：1、区域id（string），2、设备类型（string 为空为全部类型 指定类型：eg:('1,2,3')） 3、监狱编号（string）4、是否显示子节点设备（int 0、不显示1、显示）
			设备类：1-摄像头，2-对讲分机，3-报警器，4-门禁，5-广播，6-对讲主机，0-只查询区域
			 */
			/** 隐藏上个区域的设备 */
			if(map.hideAreaId!=null && map.hideAreaId!=''){
				for(var i=0;i<map_3d.allModelObj.length;i++){
					map_3d.__g.objectManager.delayDelete(map_3d.allModelObj[i].guid, 0);
				}
			}
			/** 显示当前区域设备 */
			if(areaId!=null && areaId!=''){
				map.getAllPointByGrandAndType(cusNumber,areaId,'',1);//显示当前区域以及其子节点的设备
				//map.getAllPointByGrandAndType(jsConst.CUS_NUMBER,areaId,'',0);//当前区域的设备
				//map.getAllPointByGrandAndType(map.cusNumber,areaId,'1,4',1);//摄像机 门禁
			}
			map.showHideByAreaId(parentAreaId,areaId,isHide);//模型显示隐藏
		},
		setCameraInfo :function(x, y, z, heading, tilt){
			//视角定位
			var position = {};	//坐标
			var angle = {};	//角度
			position.x = x;
			position.y = y;
			position.z = z;
			angle.heading = heading;
			angle.tilt = tilt;
			map_3d.setCameraInfo(position,angle);
		},
		//撤销视角定位
		setCameraUndo:function(){
			map_3d.setCameraUndo();
			$("#dropdownMenu2").mouseover(function() {
			})
		},
		//根据区域编号显示隐藏模型
		showHideByAreaId:function(parentAreaId,areaId,isHide){
			if(map.hideParentAreaId!=null && map.hideParentAreaId!=parentAreaId){
				//显示已隐藏模型
				map.showOrHideModel(parentAreaId,map.hideAreaId,0);
			}
			map.showOrHideModel(parentAreaId,areaId,isHide);
		},
		//显示或隐藏模型方法
		showOrHideModel:function(parentAreaId,areaId,isHide){
			var cusNumber = jsConst.USER_LEVEL=='1'?jsConst.PRISON_ORG_CODE:jsConst.CUS_NUMBER;
			$.ajax({
				type : 'post',
				url : jsConst.basePath+'common/all/findAreaEqualLevelModel.json',
				data : {"cusNumber":cusNumber,"areaId":areaId},
				dataType : 'json',
				success : function(data) {
					console.log(data);
					//隐藏模型集合
					var hideModels = data.hideModel;
					//显示模型集合
					var showModels = data.showModel;
					if(isHide!=null && areaId!=null){
						/**	隐藏显示模型 */
						//隐藏
						var hideModels = data.hideModel;
						if(hideModels!=null){
							for(var i=0;i<hideModels.length;i++){
								var model = hideModels[i];
								if(model.MIN_MODEL_FCNAME!=null && model.MIN_MODEL_FCNAME!=null
									&& model.MIN_MODEL_FDSNAME!=null && model.MIN_MODEL_FDSNAME!=""
									&& model.MIN_MODEL_NO!=null && model.MIN_MODEL_NO!=""){
									//隐藏属性
									if(isHide==1 || isHide=="1"){//隐藏
										map.hideAreaId = areaId;
										map.hideParentAreaId = parentAreaId;
										map_3d.hideByNameAndId(model.MIN_MODEL_FCNAME,model.MIN_MODEL_FDSNAME,model.MIN_MODEL_NO);
									}else{//显示
										map.hideAreaId = null;
										map.hideParentAreaId = null;
										map_3d.showByNameAndId(model.MIN_MODEL_FCNAME,model.MIN_MODEL_FDSNAME,model.MIN_MODEL_NO);
									}
								}
							}
						}
						//显示
						if(showModels!=null){
							for(var i=0;i<showModels.length;i++){
								var model = showModels[i];
								if(model.MIN_MODEL_FCNAME!=null && model.MIN_MODEL_FCNAME!=null
									&& model.MIN_MODEL_FDSNAME!=null && model.MIN_MODEL_FDSNAME!=""
									&& model.MIN_MODEL_NO!=null && model.MIN_MODEL_NO!=""){
									map_3d.showByNameAndId(model.MIN_MODEL_FCNAME,model.MIN_MODEL_FDSNAME,model.MIN_MODEL_NO);
								}
							}
						}
					}
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					$.alert({
						message:textStatus,
						title:"提示信息",
						iframePanel:true
					});
				}
			});
		},
		/** 1、根据区域id，设备类型（空位全部类型，指定：eg:'1,2'),监狱编号 获取此区域下的所有点位
         *  2、是否显示子区域下的所有点位 0、不显示1、显示
         *  3、返回所有点位模型对象
         */
        getAllPointByGrandAndType:function(curName,grandId,deviceType,ifShowChild){
            var dataPara={'alpCusNumber':curName,'alpGrandId':grandId,'alpDeviceType':deviceType,'ifShowChild':ifShowChild};
            var data=_DOCUMENT_EVENT.request_data(jsConst.basePath+"point/getAllPointByObj",dataPara,false);
        	map_3d.allModelObj=[];
        	if(data.length>0){
        		for(var i=0;i<data.length;i++){
        			var modelName = getMediaRelatePath(data[i].alpImgPath);
        			var geoFactory = map_3d.__g.geometryFactory;
        			var modePoint = geoFactory.createGeometry(gviGeometryType.gviGeometryModelPoint, gviVertexAttribute.gviVertexAttributeZ);
        			modePoint.modelName = modelName;
        			modePoint.setCoords(data[i].alpXPointIdnty,data[i].alpYPointIdnty,data[i].alpZPointIdnty, 0, 0);
        			var nowObj = map_3d.__g.objectManager.createRenderModelPoint(modePoint, null, __rootId);
        			nowObj.maxVisibleDistance=100000000.0;
        			map_3d.allModelObj.push(nowObj);
        		}
        	}
        },
		//根据区域ID跳转到管理员视角
        setAdminCameraByAreaId:function(areaId,cusNumber){
	    	$.ajax({
				type : 'post',
				url :"${ctx}/view/findViewByAreaId",
				data : {"cusNumber":cusNumber,"areaId":areaId,"confine":'1'},
				dataType : 'json',
				success : function(data) {
					if(data!=null){
						map.setCameraInfo(data.vfuXCrdnt,data.vfuYCrdnt,data.vfuZCrdnt,
								data.vfuHeadingCrdnt,data.vfuTiltCrdnt); 
					}
				},
				error:function(e){
					console.log(e);
				}
			});
		},
		//根据cusNumber,deviceType,deviceId,isGlow图层闪烁:仅当true时闪烁,画出图层
	    createRenderPolygonByPointsByDevice:function(deviceId,isGlow,cusNumber){
	    	//垂直视角
	    	$.ajax({
				type : 'post',
				url :"${ctx}/layer/findByLayer",
				data : {'linDeviceId':deviceId,'linCusNumber':cusNumber},
				dataType : 'json',
				success : function(data) {
					var points = data.layerPoints;
					if(data!=null){
				        var newPoints = [];
						for(var i=0;i<points.length;i++){
							var point = points[i];
							var newPoint = {};
							newPoint.x = parseFloat(point.lpoPointX);
							newPoint.y = parseFloat(point.lpoPointY);
							newPoint.z = parseFloat(point.lpoPointZ);
							newPoints.push(newPoint);
						}
				        //保存到geometryGuids，key为custom_开头
						var guidKey = "custom_"+deviceId;	
						map_3d.createRenderPolygonByPoints(newPoints,isGlow,guidKey);
					}
				}
			});
	    },
	    //显示图层，定位到垂直视角，deviceType设备类型 ,deviceId设备ID,isGlow图层闪烁:仅当true时闪烁,areaId,跳转镜头区域id，cusNumber监狱编号
	    createRenderPolygonAndSetAdminCamera:function(deviceId,isGlow,areaId,cusNumber){
	    	//显示图层
	    	map.createRenderPolygonByPointsByDevice(deviceId,isGlow,cusNumber);
	    	//定位垂直视角
	    	map.setAdminCameraByAreaId(areaId,cusNumber);
	    },	
	    // ------------预警图层管理模块调用结束------------
	    //-------------巡视模块开始--------------
	    /* 根据路线id巡视路线 */
	    showTour:function(routeId,time){//路线id，节点视频播放时间
	    	map.videoPlayTime = time;
	    	map.nowIndex = 0; 
	    	 //先清除cameraTour
	    	 if (map.cameraTour) {
	    		  map.cameraTour.stop();
	    		  map_3d.__g.objectManager.deleteObject(map.cameraTour.guid);
	              map.cameraTour = null;
	         }
	    	 map.cameraTour = map_3d.__g.objectManager.createCameraTour(__rootId);    	         
	         $.ajax({
	        	 type : "post",
	        	 data : {"rprRoamIdnty":routeId},
	        	 url : jsConst.basePath+"routePoint/selectPointsByRouteID.json",
	        	 success:function(data){
	 	    		 map.pointData =data;
	 	    		 for(var i=0;i<data.length;i++){
	 	    			 var position = map_3d.__g.new_Vector3;
	 	    			 var angle = map_3d.__g.new_EulerAngle;              			  
	 	    			 position.set(data[i].rprPositionX, data[i].rprPositionY, data[i].rprPositionZ);
	 	    			 angle.heading = data[i].rprAngleHead==null?0: data[i].rprAngleHead;
	 	    			 angle.tilt = data[i].rprAngleTilt==null?0:data[i].rprAngleTilt;
	 	    			 angle.roll = data[i].roll==null?0:data[i].roll;  
	 	    			 map.cameraTour.addWaypoint(position, angle, data[i].rprRouteSpeed,gviCameraTourMode.gviCameraTourSmooth );              
	 	    		 }
	 	    		 map.cameraTour.play();             	    		   
		    		 //$("#showRouteVideoDialog").dialog("open");
		         }
	         })
		},
		stopPlay:function(){
			map.cameraTour.pause();
			/*if(map.pointData[map.nowIndex].rprEquipmentId==null||map.pointData[map.nowIndex].rprEquipmentId==""){
				 $("#videoPlay_map").text("暂无摄像头")
			}else{
				 $("#videoPlay_map").text(map.pointData[map.nowIndex].rprEquipmentId)
			}	*/
		    setTimeout(function(){
		    	//$("#showRouteVideoDialog").dialog("close");
		    	//videoClient.closeAllMult();
		    },map.videoPlayTime);
		},
		contiunePlay:function (){
			if(map.nowIndex<map.cameraTour.waypointsNumber-1){
				map.cameraTour.play();
			}else{
				stop();
				$.message({
					message : "演示完毕！",
					cls : "success",
					iframePanel:true
				});
			}					
		},
		onWaypointchanged:function(index){
			//保存当前index
			map.nowIndex =index;
			var x = map.pointData[index];
		   	//$("#showRouteVideoDialog").dialog("open");
		   	debugger;
			var cameraId=map.pointData[map.nowIndex].rprEquipmentId;
			
		   	if(cameraId==null||cameraId==""){
				 //$("#videoPlay_map").text("暂无摄像头")
		   		console.log("暂无摄像头");
			}else{
				 //$("#videoPlay_map").text(map.pointData[map.nowIndex].rprEquipmentId)
				// 打开视频
				videoClient.playVideoHandle({
					'subType': 1,
					'layout': 1,
					'data': [{
						'index': 0,
						'cameraId': cameraId
					}],
					'callback': function (data) {
						//__toggleVideo(treeNode, true);
					}
				});
			}	
		   	
		   	
		},
		stopCameraTour:function(){
			if(map.cameraTour){
				map.cameraTour.stop();
			}		
		}
	}
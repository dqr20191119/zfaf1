// JavaScript Document
(function($){
	var cX,cY,indexId=0,removeId,Data=[],DOM,changeSignColor=false,signColor;
	var changeBodyColor=false,bodyColor,changeFontColor=false,fontColor;
	var Rleft,Rtop;//需要删除的坐标
	jQuery.sign_edit={
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
		loadingSign:function(data){
			loading(data);
			Data=Data.concat(data);
		},
		//链接和图片默认是可拖动的，不需要 draggable 属性。此方法禁止图片拖拽,在img标签中加入onmousedown='$.sign_edit.stopImgDrag(event)
		stopImgDrag:function (e){
			e.preventDefault();
		},
		deviceDrag:function(ev)
		{
			console.log(ev.target.id);
			ev.dataTransfer.setData("Text",ev.target.id);
		},
		deviceDrop:function(e){
			e.preventDefault();
			if(e.which==1){
				var l=e.clientX-$(DOM).offset().left;
				var t=e.clientY-$(DOM).offset().top;
				cX=l;
				cY=t;
			}
			
			var pointObj={};
			pointObj.plpPointX=cX-15;
			pointObj.plpPointY=cY-15;
			var deviceLabelId=e.dataTransfer.getData("Text");
			var deviceSelecter="";
			if(deviceLabelId){
				if(deviceLabelId.indexOf("Ts") == -1){
					deviceSelecter = "#"+deviceLabelId;
				}
				else{
					deviceSelecter = DOM+" #"+deviceLabelId;
				}
			}else{
				$.message({
					iframePanel:true,
					message : "设备ID不存在！",
					cls : "waring"
				});
				return;
			}
			var data_type = $(deviceSelecter).attr("data-type");
			if(data_type && data_type =="planePoint_add"){
				pointObj.plpDeviceIdnty=$(deviceSelecter).attr("data-deviceId");
				pointObj.plpDeviceType=$("#deviceType").combobox("getValue");
				var pointDom=$(DOM + " [data-plpDeviceIdnty='"+pointObj.plpDeviceIdnty+"'][data-plpDeviceType='"+pointObj.plpDeviceType+"']");
				if(pointDom && pointDom.length>0){
					$.message({
						iframePanel:true,
						message : "该设备已存在！",
						cls : "waring"
					});
					$.sign_edit.signOnClick(pointObj.plpDeviceType,pointObj.plpDeviceIdnty,pointDom.attr("data-pointId"));
					return;
				}
				f_add_new(pointObj);
			}else if(data_type && data_type =="planePoint_edit"){
				pointObj.id=$(deviceSelecter).attr("data-pointId");
				$.sign_edit.updatePosition(pointObj);
			}	
		},
		updatePosition:function(pointObj){
			$.ajax({
				type : 'post',
				url : jsConst.basePath+"/xtgl/planeLayerPoint/updatePart.json",
				data : {
					"id":pointObj.id,
					"plpPointX":pointObj.plpPointX,
					"plpPointY":pointObj.plpPointY
				},
				dataType : 'json',
				success : function(data) {
					if(data.success){
						$(DOM+' #Ts'+pointObj.id).css({"left":parseInt(pointObj.plpPointX),"top":parseInt(pointObj.plpPointY)});
					}else{
						$.message( {
							iframePanel:true,
					        message:data.msg,
					        type:"danger"
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
		},
		updateAngle:function(pointId,angle){
			$.ajax({
				type : 'post',
				url : jsConst.basePath+"/xtgl/planeLayerPoint/updatePart.json",
				data : {
					"id":pointId,
					"plpPointAngle":angle
				},
				dataType : 'json',
				success : function(data) {
					if(data.success){
						$(DOM+' #Ts'+pointId).attr("data-plpPointAngle",angle);
						$(DOM+' #Ts'+pointId).rotate(angle);
					}else{
						$.message( {
							iframePanel:true,
					        message:data.msg,
					        type:"danger"
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
		},
		//逆时针
		anticlockwise:function(){
			console.log("逆时针旋转45度");
			if($(DOM+' .pointBorder') && $(DOM+' .pointBorder')!="undefined"){
				var angle=0;
				var data_plpPointAngle = $(DOM+' .pointBorder').attr("data-plpPointAngle");
				var pointId=$(DOM+' .pointBorder').attr("data-pointId");
				if(pointId && pointId!="undefined"){
				}else{
					$.alert({
						message:"请先选择一个设备",
						title:"信息提示",
						iframePanel:true
					});
					return;
				}
				if(data_plpPointAngle && data_plpPointAngle!="undefined"){
					angle=data_plpPointAngle;
				}
				if(angle<=0){
					angle+=360;
				}
				angle-=45;
				$.sign_edit.updateAngle(pointId,angle);
			}else{
				$.alert({
					message:"请先选择一个设备",
					title:"信息提示",
					iframePanel:true
				});
			}		
		},
		//顺时针
		clockwise:function(){
			console.log("顺时针旋转45度");
			if($(DOM+' .pointBorder') && $(DOM+' .pointBorder')!="undefined"){
				var angle=0;
				var data_plpPointAngle = $(DOM+' .pointBorder').attr("data-plpPointAngle");
				var pointId=$(DOM+' .pointBorder').attr("data-pointId");
				if(pointId && pointId!="undefined"){
				}else{
					$.alert({
						message:"请先选择一个设备",
						title:"信息提示",
						iframePanel:true
					});
					return;
				}
				if(data_plpPointAngle && data_plpPointAngle!="undefined"){
					angle=data_plpPointAngle;
				}
				if(angle>=360){
					angle-=360;
				}
				angle+=45;
				$.sign_edit.updateAngle(pointId,angle);
			}else{
				$.alert({
					message:"请先选择一个设备",
					title:"信息提示",
					iframePanel:true
				});
			}		
		},
		signOnClick:function(deviceType,deviceId,pointId){
			$(DOM+' [id*=Ts]').removeClass("pointBorder");
			$(DOM+' #Ts'+pointId).addClass("pointBorder");
		},
		signOnDblClick:function(deviceType,deviceId,deviceName){
			if(deviceType==cameraTypeValue){
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
	 		}else if(deviceType==alarmTypeValue){
	 			
	 		}else if(deviceType==doorTypeValue){
	 			$.confirm( {
	 				message:"确认打开"+deviceName+"吗？",
	 				iframePanel:true,
	 				callback: function(sure) {
	 					if (sure == true) {
	 						openDoor(deviceId);
	 					}
	 					if (sure == false) {
	 						console.log('cancel');
	 					}
	 				}
	 			});
	 			
	 		}else if(deviceType==talkBackTypeValue){
	 			$.confirm( {
	 				message:"确认呼叫"+deviceName+"吗？",
	 				iframePanel:true,
	 				callback: function(sure) {
	 					if (sure == true) {
	 						startTalk(deviceId);
	 					}
	 					if (sure == false) {
	 						console.log('cancel');
	 					}
	 				}
	 			});
	 		}
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
	function openDoor(deviceId){
		var doorIds = [];
		doorIds.push(deviceId);
		
		var data = {};
		data["doorIds"] = JSON.stringify(doorIds);
		data["action"] = '1';//1 开门 2禁止开门 3恢复开门
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
						videoClient.playVideoHandle({
							'subType' : 2,
							'layout' : data.obj.length,
							'data' : data.obj
						});
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
	function startTalk(deviceId){
		var talkIdntys = [];
		talkIdntys.push(deviceId);
		var data = {};
		data["cusNumber"] = jsConst.CUS_NUMBER;
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
	}
		/*
		********定义插件
		*/

	
	function defined(dom){
		$(document).on("contextmenu",dom,function(e){
			e.preventDefault();
			});//阻止鼠标右键默认事件
		
		/*$(document).on("mousedown",dom,function(e){
		e.preventDefault();
		if(e.which==3){
			$('.chooseBox').remove();
			$(dom).append("<div class='chooseBox'><ul><li id='addsign'>添加设备</li></ul></div>");
			var l=e.clientX-$(dom).offset().left;
			var t=e.clientY-$(dom).offset().top;
			$('.chooseBox').css({"left":l,"top":t});
			cX=l;
			cY=t;
			}//鼠标右键
		});//注册鼠标右键点击事件*/	
		
		/*$(document).on("click","#addsign",function(e){
			e.preventDefault();
			$("#signDialog").dialog({
				iframePanel:true,
				title:"关联设备",
				width:300,
				height:160,
				draggable:true,
				appendTo:"#imagebox",
				autoOpen:false,
				onClose: function(e,ui) {            //回调事件: 方法
					$("#formId_AddPoint").form("reset");
			    }
			});
			
			$("#sureSign").button({
				label:"确定",
				componentCls:"sureSign"
			}) 
			$("#signDialog").dialog("open");
			$('#signDialog').parent().css({"left":cX,"top":cY,"position":"absolute"})
		});//添加编辑*/		
		
		/**************************************************
		*****************add by zk start******************
		**************************************************/
		/*$(document).on('click','#sureSign',function(){	
			//添加点位
			var pointObj={};
			pointObj.plpPointX=cX-15;
			pointObj.plpPointY=cY-15;
			
			f_add(pointObj);
		});//确认编辑	*/	
		
		$(document).on('mousedown','[id*=Ts]',function(e){
			var m=$(this).attr('id').substring(2);
			if(e.which==3){
				e.stopPropagation();
				removeId=m;
				$('.chooseBox').remove();
				Rleft=$(this).css("left").replace(/[^0-9]/ig, "");
				Rtop=$(this).css("top").replace(/[^0-9]/ig, "");
				var l=e.clientX-$(dom).offset().left,t=e.clientY-$(dom).offset().top;
				$(dom).append("<div class='chooseBox'><ul><li id='deleteSign'>删除设备</li></ul></div>");
				$('.chooseBox').css({"left":l,"top":t});
			}
		});//弹出取消标记
		
		$(document).off("click").on("click","#deleteSign",function(){
			f_delete(removeId);
		});//删除标记
		
		/**************************************************
		*****************add by zk end******************
		**************************************************/
		
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
		});//点击消失
	}
	/*
	********定义事件
	*/
	function deleteData(left,top){
		for(var i=0;i<Data.length;i++){
			if(Data[i].left==left&&Data[i].top==top){
				Data.splice(i,1);
				break;
			}else{
				continue;
			}
		}
	}//删除数据
				
	/**************************************************
	*****************add by zk start******************
	**************************************************/
	
	function loading(data){
		console.log(data);
		for(var i=0;i<data.length;i++){
		 	var innerHtml = "<div class='signIndex' id='Ts"+data[i].id+"' theSign="+data[i].plpPointName+" " +
		 			" data-plpCusNumber="+data[i].plpCusNumber+" data-plpPointName="+data[i].plpPointName+" " +
		 			" data-plpDeviceType="+data[i].plpDeviceType+" data-plpDeviceIdnty="+data[i].plpDeviceIdnty+" " +
		 			" data-plpLayerIdnty="+data[i].plpLayerIdnty+" data-plpPointAngle="+data[i].plpPointAngle+" " +" data-pointId="+data[i].id+" " +
		 			" onclick='$.sign_edit.signOnClick(\""+data[i].plpDeviceType+"\",\""+data[i].plpDeviceIdnty+"\",\""+data[i].id+"\")'"+
		 			" data-type='planePoint_edit' draggable='true' ondragstart='$.sign_edit.deviceDrag(event)' "+
		 			" ondblclick='$.sign_edit.signOnDblClick(\""+data[i].plpDeviceType+"\",\""+data[i].plpDeviceIdnty+"\",\""+data[i].plpPointName+"\")' ";
		 	if(data[i].plpDeviceType != null){
		 		if(data[i].plpDeviceType==cameraTypeValue){
		 			if(data[i].plpDeviceModel=='1'){
		 				if(data[i].status && data[i].status=='1'){
		 					innerHtml += " style='background:url(\""+jsConst.basePath+"/static/module/video/css/img/camera_spherical_1.png\");background-size:100%;'";
		 				}else if(data[i].status && data[i].status=='2'){
		 					innerHtml += " style='background:url(\""+jsConst.basePath+"/static/module/video/css/img/camera_spherical_2.png\");background-size:100%;'";
		 				}else{
		 					innerHtml += " style='background:url(\""+jsConst.basePath+"/static/module/video/css/img/camera_spherical_0.png\");background-size:100%;'";
		 				}	
		 			}else if(data[i].plpDeviceModel=='2'){
		 				if(data[i].status && data[i].status=='1'){
		 					innerHtml += " style='background:url(\""+jsConst.basePath+"/static/module/video/css/img/camera_gun_1.png\");background-size:100%;'";
		 				}else if(data[i].status && data[i].status=='2'){
		 					innerHtml += " style='background:url(\""+jsConst.basePath+"/static/module/video/css/img/camera_gun_2.png\");background-size:100%;'";
		 				}else{
		 					innerHtml += " style='background:url(\""+jsConst.basePath+"/static/module/video/css/img/camera_gun_0.png\");background-size:100%;'";
		 				}	
		 			}else if(data[i].plpDeviceModel=='3'){
		 				if(data[i].status && data[i].status=='1'){
		 					innerHtml += " style='background:url(\""+jsConst.basePath+"/static/module/video/css/img/camera_hemisphere_1.png\");background-size:100%;'";
		 				}else if(data[i].status && data[i].status=='2'){
		 					innerHtml += " style='background:url(\""+jsConst.basePath+"/static/module/video/css/img/camera_hemisphere_2.png\");background-size:100%;'";
		 				}else{
		 					innerHtml += " style='background:url(\""+jsConst.basePath+"/static/module/video/css/img/camera_hemisphere_0.png\");background-size:100%;'";
		 				}	
		 			}
		 		}else if(data[i].plpDeviceType==alarmTypeValue){
		 			innerHtml += " style='background:url(\""+jsConst.basePath+"/static/module/sign/png/baojingqi.png\");background-size:100%;'";
		 		}else if(data[i].plpDeviceType==doorTypeValue){
		 			innerHtml += " style='background:url(\""+jsConst.basePath+"/static/module/sign/png/door.png\");background-size:100%;'";
		 		}else if(data[i].plpDeviceType==talkBackTypeValue){
		 			innerHtml += " style='background:url(\""+jsConst.basePath+"/static/module/sign/png/interphone.png\");background-size:100%;'";
		 		}else if(data[i].plpDeviceType==broadcastTypeValue){
		 			innerHtml += " style='background:url(\""+jsConst.basePath+"/static/module/sign/png/broadcast.png\");background-size:100%;'";
		 		}
		 		else if(data[i].plpDeviceType==jsbqTypeValue){
		 			innerHtml += "><span style='color: #FFD105;font-size: 14px;font-weight: bold;'>"+data[i].plpPointName+"</span";
		 		}
		 		
		 	}
		 	innerHtml += "></div>";
			$(DOM).append(innerHtml);
			$(DOM+' #Ts'+data[i].id).css({"left":parseInt(data[i].plpPointX),"top":parseInt(data[i].plpPointY)});
			$(DOM+' #Ts'+data[i].id).rotate(data[i].plpPointAngle);
			if(changeSignColor){
				$(DOM+' #Ts'+data[i].id).css("border",signColor+" 3px solid");
			}//改变了颜色
		}
	}//载入数据

	/**************************************************
	*****************add by zk end******************
	**************************************************/
})(jQuery);
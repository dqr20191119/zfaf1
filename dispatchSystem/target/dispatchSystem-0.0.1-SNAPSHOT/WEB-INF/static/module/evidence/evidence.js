var jsConst= window.top.jsConst;
var vc = window.top.videoClient;
 
function dateFormatter(date){
	var year = date.getFullYear();       //年
	var month = date.getMonth() + 1;     //月
	var day = date.getDate();            //日
    var hh = date.getHours();            //时
    var mm = date.getMinutes();          //分
    var ss = date.getSeconds();          //秒
    var clock = year + "-";
    if(month < 10)
        clock += "0";
    clock += month + "-";
    if(day < 10)
        clock += "0";
    clock += day + " ";
    if(hh < 10)
        clock += "0";
    clock += hh + ":";
    if (mm < 10) 
    	clock += '0'; 
    clock += mm + ":"; 
     if (ss < 10) 
    	clock += '0'; 
    clock += ss; 
    return clock;
}

function showEvidence(fileType,ID){
	//图片
	if(fileType==1){
		showImg(ID);
	}
	//视频
	else if(fileType==2){
		showVideo(ID);
	}
}

/**
 * 设置视频默认图片 add by zk
 */
function setVideoImg(id){
	$(id).attr('src',jsConst.basePath+"/static/module/evidence/img/video_bg.png");
}

/**
 * 加载图片
 */
function getImg(id, sqno){
	 $.ajax({
			type : 'post',
			url : jsConst.basePath+'/evidence/searchImage',
			data : {
				"id":sqno
			},
			dataType : 'json',
			success : function(data) {
				if(data.exception==undefined){
					fileName = data.EIN_FILE_NAME;
					ftpPath = data.EIN_FTP_PATH;
					ftpPrefix= data.EIN_FTP_PREFIX;
					$(id).attr('src',jsConst.STATIC_RESOURCE_ADDR+"/"+ftpPrefix+"/"+ftpPath+"/"+fileName+"?temp=" + Math.random());
					$('div.play').hide();
					
				}else{
					$.message( {
						iframePanel:true,
				        message:data.exception.cause.message,
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

}
/**
 * 加载视频
 */
 function filePlay(sqno){
	 $('div .play').show();
	 	 
	 $.ajax({
		type : 'post',
		url : jsConst.basePath+'/evidence/searchVideo',
		data : {
			"id":sqno
		},
		dataType : 'json',
		success : function(data) {
			if(data.exception==undefined){
				fileName = data.EIN_FILE_NAME;
				filePath = data.EIN_FILE_PATH;
				ftpPath = data.EIN_FTP_PATH;
				$('.play').unbind('click').bind('click', function(){
					var file = filePath + '\\' + fileName;
					vc.setLayout(1, function (result) {
						if (result && result.success)
							vc.playVideoFile(0, file,ftpPath, null, null);
					});
				});
			}else{
				$.message( {
					iframePanel:true,
			        message:data.exception.cause.message,
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
	
}

/*
 * 显示图片信息
 */
function showImg(seNo){
	getImg("#imgEvi", seNo);
	$('#imgView').show();
	$('#imgView').dialog({ 
		 width: 510,
		 height: 405, 
		 title: "查看图片信息",
	     modal:true,
	     maximizable:true
	});
}

/**
 *  显示视频信息
 */
function showVideo(seNo){
	//getImg('#videoEvi',seNo);
	setVideoImg('#videoEvi');
	filePlay(seNo);
	$('#videoView').show();
	$('#videoView').dialog({
		 width: 510,
		 height: 405, 
		 title: "查看视频信息",
	     modal: true
	});
} 

/*************************************************/
/*********************创建监督单*******************/
/*************************************************/

/*-------------------update by zk start-------------------*/
function Evidence(){
	
}
var evidence=new Evidence();
/**
 * 已使用的证据编号集合
 */
Evidence.prototype.evidenceSqnoList = new Array();

/**
 * 发送对象集合
 */
Evidence.prototype.sendToUserList = new Array();
/**
 * 修改已建监督单时是否编辑过证据
 */
Evidence.prototype.isEditEvidence = false;
/**
 * 修改已建监督单时是否编辑过接收人
 */
Evidence.prototype.isEditRecipient = false;


function lastNextClick(evidenceSqnoList_p,eviFileTypeList,type){
	var clickNumobj = null;
	var clickNum = null;
	var eviImgobj = null;
	var btnLast = null;
	var btnNext = null;
	
	switch(type){
		case 1:
			clickNumobj = $("#imgClickNum_edit");
			clickNum = Number(clickNumobj.val());
			eviImgobj = "#imgEvidence_edit";
			btnLast = $("#LastImg_edit");
			btnNext = $("#NextImg_edit");
			break;
		case 2:
			clickNumobj = $("#imgClickNum_new");
			clickNum = Number(clickNumobj.val());
			eviImgobj = "#imgEvidence_new";
			btnLast = $("#LastImg_new");
			btnNext = $("#NextImg_new");
			break;
	}
	btnLast.show();
	btnNext.show();
	//图片滚动查看
	//只有一张图片 两个按钮都不可用
	if(1 == evidenceSqnoList_p.length){
		btnNext.hide();
	}
	btnLast.hide();
	//<<上一张按钮点击事件
	btnLast.click(function(){
		btnLast.hide();
		clickNum = clickNum -1;
		if(eviFileTypeList[clickNum] == 1){
			getImg(eviImgobj,evidenceSqnoList_p[clickNum]);
			if(type==2){//创建监督单的页面才做图片编辑
                getImgName(evidenceSqnoList_p[clickNum],false,initImage);
            }
		}
		//文件类型为视频时
		else if(eviFileTypeList[clickNum] == 2){
			setVideoImg(eviImgobj);
			//显示视频播放按钮并可播放视频
			filePlay(evidenceSqnoList_p[clickNum]);

		}
		clickNumobj.val(clickNum);
        id = messenger.evidenceSqnoList[clickNum];
		//防止用户多次重复点击 .隔0.1s后按钮置为可用
		setTimeout(function last(){
			if(clickNum != 0){btnLast.show();}
			btnNext.show();
		},100);
	});
	//>>下一张按钮点击事件
	btnNext.click(function(){
		//按钮置为不可点击，防止用户重复点击
		btnNext.hide();
		clickNum = clickNum +1;
		if(eviFileTypeList[clickNum] == 1){
			getImg(eviImgobj,evidenceSqnoList_p[clickNum]);
            if(type==2){//创建监督单的页面才做图片编辑
                getImgName(evidenceSqnoList_p[clickNum],false,initImage);
            }
		}
		//文件类型为视频时
		else if(eviFileTypeList[clickNum] == 2){
			setVideoImg(eviImgobj);
			//显示视频播放按钮并可播放视频
			filePlay(evidenceSqnoList_p[clickNum]);
		}
		clickNumobj.val(clickNum);
        id = messenger.evidenceSqnoList[clickNum];
		//0.1秒后按钮可点击
		setTimeout(function next(){
			//最后一张时下一张按钮不可用
			if(clickNum != evidenceSqnoList_p.length-1){btnNext.show();}
			btnLast.show();
		},100);
	});
} 
/*
 * 监督单证据关联信息保存
 * */
function savaRelationMonEvi(monitorDocId){
	var monitorAndEvidenceList=new Array();
	var merCrteTime = dateFormatter(new Date());
	if(evidence.evidenceSqnoList && evidence.evidenceSqnoList.length>0){
		for(var i=0;i<evidence.evidenceSqnoList.length;i++){
			monitorAndEvidenceList.push({
				"merCusNumber":jsConst.ORG_CODE,
				"merMonitorSqno":monitorDocId,
				"merEvidenceSqno":evidence.evidenceSqnoList[i],
				"merCrteTime":merCrteTime,
				"merCrteUserId":jsConst.USER_ID
			});
		}
	}else{
		return;
	}
	
	$.ajax({
		type : 'post',
		url : jsConst.basePath+'/monitor/batchInsertMonitorAndEvidence.json',
		contentType: "application/json; charset=utf-8",
		data : JSON.stringify(monitorAndEvidenceList),
		dataType : 'json',
		success : function(data) {
			
			if(data.success){
				//重置证据信息使用状态
				var list ={
	        			"einSttsIndc":"1",					 // 1 - 使用状态
	        			"monitorId":monitorDocId			//监督单ID
	        		 };
	
				 updateEviStatus(list);
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
}

/*
 * 监督单接收人关联信息保存
 * */
function savaRelationMonRec(monitorDocId,callback){
	var monitorAndRecipientList=new Array();
	var crteTime = dateFormatter(new Date());
	if(evidence.sendToUserList.length>0){
		for(var key in evidence.sendToUserList){
			monitorAndRecipientList.push({
				"mrrCusNumber":jsConst.ORG_CODE,			//机构编号
				"mrrMonitorSqno":monitorDocId,				//监督单号
				"mrrRcpntIdnty":key,						//接受人编号
				"mrrRcpntName":evidence.sendToUserList[key],			//接受人姓名
				"modCrteTime":crteTime,
				"modCrteUserId":jsConst.USER_ID,		//创建人
				"modUpdtTime":crteTime,
				"modUpdtUserId":jsConst.USER_ID,		//更新人
				"mrrStatus":"0"								//状态
			});
		}
		
		$.ajax({
			type : 'post',
			url : jsConst.basePath+'/monitor/batchInsertMonitorAndRecipient',
			contentType: "application/json; charset=utf-8",
			data : JSON.stringify(monitorAndRecipientList),
			dataType : 'json',
			success : function(data) {
				if(data.success){
					if( typeof callback == 'function' ){
						callback.call(this);
					}
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
	}

}

/**
 * 保存监督单
 */
function saveSuperviseOrder(type) {
	
	if ($("#formId_jdd_add").form("valid")) {
		var formData = $("#formId_jdd_add").form("formData");
        formData.mdoNoticeDepartmentName = $("#mdoNoticeDepartment").combobox("getText");
        formData.mdoConsultStatus = "0";
		$.ajax({
			type : 'post',
			url : jsConst.basePath+'/monitor/insert',
			data : formData,
			dataType : 'json',
			success : function(data) {
				
				if(data.success){
					//监督单ID
					var monitorDocId=data.obj;
					
					/*
					 * 监督单证据关联信息保存
					 * */
					savaRelationMonEvi(monitorDocId);
					
					/*
					 * 监督单接收人关联信息保存
					 * */
					savaRelationMonRec(monitorDocId);
					
					
					//保存
					if(type == 1){
						$.message({
							message : "保存成功",
							cls : "success",
							iframePanel:true
						});
						$("#gridId_yjjdd").grid("reload");
						$.confirm("是否进入风险采集？", function(r) {
							
				        	if(r){
				        		$('#dialog1').html("");
				        		$('#dialog1').dialog({
				        			width : 1000,
				        			height : 800,
				        			title : '风险采集',
				        			url : jsConst.basePath + '/aqfxyp/fxcj/toIndex?type=1'
				        		});
				        		$("#dialog1").dialog("open");
				        	}
				        });
		        	 }else{
		        		 //推送
		        		 $.confirm( {
		        			 //message:'是否确认向警员['+$("#txtSendToUser_new").textbox("getValue")+']推送名为['+$("#mdoMonitorName").textbox("getValue")+']的监督单信息?',
		        			 message:'是否推送名为['+$("#mdoMonitorName").textbox("getValue")+']的监督单信息?',
		        			 iframePanel:true,
		        				callback: function(sure) {
		        					if (sure == true) {
		        						var list ={
		        			        			"modSttsIndc":"1",		//已推送
		        			        			"id":monitorDocId
		        			        		 };
		        							 updateMdoStatus(list);
		        					}
		        					if (sure == false) {
		        						console.log('cancel');
		        					}
		        				}
		        			}); 
		        		
		        	}
					$("#dialogId_jdjc").dialog("close");
					
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
	} else {
		$.alert({
			message:"请确认输入是否正确！",
			title:"信息提示",
			iframePanel:true
		});
	}
}
/**
 * 更新证据使用状态
 */
function updateEviStatus(list){
	//后台更新数据
	$.ajax({
		type : 'post',
		url : jsConst.basePath+'/monitor/updateEviStatusByMonId.json',
		contentType: "application/json; charset=utf-8",
		data : JSON.stringify(list),
		dataType : 'json',
		success : function(data) {
			if(data.success){
				$("#gridId_zjxx").grid("reload",jsConst.basePath+"/evidence/searchEvidence.json?einSttsIndc=0&einCusNumber="+jsConst.ORG_CODE);
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
}

/**
 * 推送监督单信息
 */
function updateMdoStatus(list){
	//后台更新数据
	$.ajax({
		type : 'post',
		url : jsConst.basePath+'monitor/updatePart.json',
		data : list,
		dataType : 'json',
		success : function(data) {
			if(data.success){
				//部门已收监督单
				$("#gridId_deptysjdd").grid("reload",jsConst.basePath+"/monitor/searchDeptReceivedMonitor.json");
				$.message({
					message : "操作成功",
					cls : "success",
					iframePanel:true
				});
				$("#gridId_yjjdd").grid("reload");
				$("#gridId_yfjdd").grid("reload");
				$("#gridId_ysjdd").grid("reload",jsConst.basePath+"/monitor/searchReceivedMonitor.json");
				
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
}
	/**
	 * 更新证据
	 */
	 function updateEvidence(){
		
		//监督单号
		var seqNo = $("#yjjdd_id").textbox("getValue");
		/***************更新证据信息***************/
		 //修改已建监督单时编辑过证据
		 if(evidence.isEditEvidence){
			//重置证据信息使用状态
			 var eviStatusParam ={
				"einSttsIndc":"0",					 // 0 - 未使用状态
				"monitorId":seqNo					 //监督单ID
			 };
			 $.ajax({
				type : 'post',
				url : jsConst.basePath+'/monitor/updateEviStatusByMonId.json',
				contentType: "application/json; charset=utf-8",
				data : JSON.stringify(eviStatusParam),
				dataType : 'json',
				success : function(data) {
					if(data.success){
						$("#gridId_zjxx").grid("reload",jsConst.basePath+"/evidence/searchEvidence.json?einSttsIndc=0&einCusNumber="+jsConst.ORG_CODE);
						
						//删除监督单已使用的证据信息	 
						 $.ajax({
								type : 'post',
								url : jsConst.basePath+'/monitor/delRelationMonEvi.json?cusNumber='+jsConst.ORG_CODE+'&monitorSqno='+seqNo,
								dataType : 'json',
								success : function(data) {
									if(data.success){
										 /*
										 * 监督单证据关联信息保存
										 * */
										savaRelationMonEvi(seqNo);
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
		 }
	 }
/**
 * 保存修改
 */
 function saveEdit(){
	  
	//监督单号
	var seqNo = $("#yjjdd_id").textbox("getValue");

	 /***************更新接收人信息***************/
	 //修改已建监督单时编辑过接收人
	 if(evidence.isEditRecipient){
		 
		//删除已关联接收人	 
		 $.ajax({
				type : 'post',
				url : jsConst.basePath+'/monitor/delRelationMonRec.json?cusNumber='+jsConst.ORG_CODE+'&monitorSqno='+seqNo,
				dataType : 'json',
				success : function(data) {
					if(data.success){					
						//重新插入数据到监督单发送人关联表信息
						savaRelationMonRec(seqNo);
						console.log("success");
                        $("#gridId_yjjdd").grid("reload");
						$.message({
							message : "修改成功",
							cls : "success",
							iframePanel:true
						});
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
	 }
	 if ($("#formId_jdd_edit").form("valid")) {
			var formData = $("#formId_jdd_edit").form("formData");
			if(jsConst.USER_LEVEL == 3 && (formData.mdoFeedbackmessage == null || formData.mdoFeedbackmessage == "")){
                $.alert({
                    message:"保存失败，反馈结果未填写！",
                    title:"信息提示",
                    iframePanel:true
                });
                return;
            }
			// 监督单状态：0:未查阅; 1:已查阅反馈; 2:未反馈;
			formData["mdoConsultStatus"] = '2';
			$.ajax({
				type : 'post',
				url : jsConst.basePath+'/monitor/updatePart',
				data : formData,
				dataType : 'json',
				success : function(data) {
					if(data.success){
						console.log("success");
						$("#gridId_yjjdd").grid("reload");
						$.message({
							message : "修改成功",
							cls : "success",
							iframePanel:true
						});
                        $("#dialogId_jdjc").dialog("close");
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
		} else {
			$.alert({
				message:"请确认输入是否正确！",
				title:"信息提示",
				iframePanel:true
			});
			return;
		}
	
 }


/**
 * 推送按钮点击事件
 */
function BtnSendClick(){
	$.confirm( {
		//message:'是否确认向警员['+$("#txtSendToUser_edit").textbox("getValue")+']推送名为['+$("#mdoMonitorName").textbox("getValue")+']的监督单信息?',
		message:'是否推送名为['+$("#mdoMonitorName").textbox("getValue")+']的监督单信息?',
		iframePanel:true,
		callback: function(sure) {
			if (sure == true) {
				var list ={
		    			"modSttsIndc":"1",					//已推送
		    			"id":$("#yjjdd_id").textbox("getValue")
		    		 };
					 updateMdoStatus(list);
			}
			if (sure == false) {
				console.log('cancel');
			}
		}
	}); 
}



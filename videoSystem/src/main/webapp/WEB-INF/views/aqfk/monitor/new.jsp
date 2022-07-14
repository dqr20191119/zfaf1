<%@page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<style>
.form-control {
	width: 100%;
}

.zjxxys{
	position: absolute;
	top: 42px;
	right: 24px;
	width: 400px;
	height: 250px;
	border: 2px dotted #AAAAAA;
	background-color: #FFF;
	box-shadow: 0px 2px 5px 0px rgba(0,0,0,0.40);
}
.zjxxys img{
	margin: 1px;
	width: 100%;
	height: 100%;
}
.zjxxys .play {
	width: 100%;
	height: 100%;
}
</style>
<%--<center>--%>
<cui:form id="formId_jdd_add">
<cui:input  type="hidden" required="true" id="mdoCusNumber" name="mdoCusNumber" value="" componentCls="form-control"></cui:input>
<cui:input  type="hidden" required="true" id="mdoUpdtUserId" name="mdoUpdtUserId" value="" componentCls="form-control"></cui:input>
<cui:input  type="hidden" required="true" name="modSttsIndc" value="0" componentCls="form-control"></cui:input>
<cui:input  type="hidden" required="true" id="mdoIsFromProv" name="mdoIsFromProv" value="0" componentCls="form-control"></cui:input>
<div style="display:none;" ><cui:datepicker  required="true" id="mdoCrteTime"  name="mdoCrteTime"  dateFormat="yyyy-MM-dd HH:mm:ss" componentCls="form-control"></cui:datepicker>
<cui:datepicker  required="true" id="mdoUpdtTime"  name="mdoUpdtTime"  dateFormat="yyyy-MM-dd HH:mm:ss" componentCls="form-control"></cui:datepicker>
	</div>
<table class="table" width="90%">
		<tr>
			<td width="15%" ><label>监督单名：</label></td>
			<td width="35%" >			
				<cui:input id="mdoMonitorName" name="mdoMonitorName" required="true" componentCls="form-control"></cui:input>
			</td>
			<td width="50%" rowspan="6">
				<div class="zjxxys">
					<div class="play" title="点击播放录像文件..."></div>
					<img id="imgEvidence_new" alt="未找到图片.."  src="">
					<!-- 图片点击次数计数 -->
					<input id="imgClickNum_new" value="0" type="hidden"/>
					<div align="center">
					<div style="float:left"><img id="LastImg_new" title="上一张" style="margin-top:5px;width:10px;height:15px;" src="${ctx }/static/module/evidence/img/left_blueArrowhead.png" /></div>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<div style="float:right;"><img id="NextImg_new" title="下一张" style="margin-top:5px;width:10px;height:15px;" src="${ctx }/static/module/evidence/img/blueArrowhead.png"/></div>
					</div>
				</div>
			</td>
		</tr>
		<tr>
			<td><label>推送监狱:</label></td>
			<td>			
				<cui:combobox required="true" id="mdoNoticeCusNumber" name="mdoNoticeCusNumber" url="${ctx}/common/authsystem/findAllJyForCombobox.json" componentCls="form-control" ></cui:combobox>
			</td>
		</tr>
		<tr id="trSendToDepartment_new">
			<%-- <td><label>推送对象:</label></td>
			<td>			
				<cui:input id="txtSendToUser_new" required="true" componentCls="form-control" onClick="openUserDialog()"></cui:input>
			</td> --%>
			<td><label>推送部门:</label></td>
			<td>			
				<cui:combobox  id="mdoNoticeDepartment" name="mdoNoticeDepartment" url="" componentCls="form-control" ></cui:combobox>
			</td>
		</tr>
		<tr>
			<td><label>记录地点：</label></td>
			<td><cui:input id="mdoAddr" name="mdoAddr" componentCls="form-control"></cui:input></td>
		</tr>
		<tr>
			<td ><label>记录时间:</label></td>
			<td><cui:datepicker id="mdoTime" name="mdoTime"
					componentCls="form-control" dateFormat="yyyy-MM-dd HH:mm:ss"></cui:datepicker>
			</td>
		</tr>
		<tr>
			<td><label>问题描述：</label></td>
			<td><cui:textarea id="mdoProblem" required="true"  name="mdoProblem"  componentCls="form-control"></cui:textarea></td>
		</tr>
		<tr style="display:none">
			<td><label>记录人：</label></td>
			<td><cui:input required="true" id="mdoCrteUserId" name="mdoCrteUserId" value="" componentCls="form-control"></cui:input></td>
		</tr>
	</table>
	</cui:form>
	<div class="dialog-buttons" >
		<cui:button label="保存监督单"  onClick="saveJdd(1)"></cui:button>
		<%-- <cui:button label="保存并推送"  onClick="saveJdd(2)"></cui:button> --%>
		<cui:button label="取消"  onClick="closeDialog"></cui:button>
	</div>
    <div style="text-align: center;">
        <table style="margin-top:10px;" width ="100%">
            <tr>
                <td>
                    <div class="btn-group btn-info btn-xs">
                        <cui:button label="直线" onClick = "setDrawType(1)" ></cui:button>
                        <cui:button label="矩形" onClick = "setDrawType(2)" ></cui:button>
                        <cui:button label="椭圆" onClick = "setDrawType(3)"></cui:button>
                        <cui:button label="正圆"  onClick = "setDrawType(4)"></cui:button>
                    </div>
                </td>
                <td>
                    <div>
                        <cui:button label="清除"  onClick = "editImgEvidence.clear()" ></cui:button>
                        <cui:button label="保存编辑图片并提交"  onClick = "editImgEvidence.submit()"  ></cui:button>
                        <cui:button label="下载"  onClick = "editImgEvidence.download()"  ></cui:button>
                    </div>
                </td>
            </tr>
        </table>
        <canvas id="imgEvidenceCanvas" width="900" height="500"></canvas>
    </div>
<%--</center>--%>

<cui:dialog id="dialogId_sendToUser" title="选择推送对象" autoOpen="false" iframePanel="true"  reLoadOnOpen="true"  modal="true" resizable="false" width="300" height="auto">

	<cui:input  texticons="" placeholder="Search" onKeyPress="userSearch"></cui:input>
	<div style="height:350px;overflow-y:scroll;">
		<cui:tree id="sendToUserTree" checkable="true" chkboxType="chkboxType" chkStyle="checkbox" >
		</cui:tree>
	</div>
	<div class="dialog-buttons" style="margin-bottom: 0px; text-align: center;" >
		<cui:button label="确定"  onClick="f_save"></cui:button>
		<cui:button label="取消"  onClick="closeDialog"></cui:button>
	</div>
</cui:dialog>
<cui:dialog id="fxcjDialog" autoOpen="false" iframePanel="true" reLoadOnOpen="true" modal="true" resizable="false" autoDestroy="true"></cui:dialog>
<script>
var chkboxType = {
	'Y' : 'ps',
	'N' : 'ps'
}
var jsConst=window.top.jsConst;
var cusNumber=jsConst.ORG_CODE							//监狱编号
var userId=jsConst.USER_ID					//登录人
var messenger=window.top.messenger;
var evidence=window.top.evidence;
/*编辑图片 start                      */
var einCusNumber="";	//证据监狱编号
var originalFtpFileName="";	//ftp原始文件名
var id=null;
/*if (messenger.evidenceSqnoList.length >0){
     id=messenger.evidenceSqnoList[0];
}*/

var canvasDom = document.getElementById('imgEvidenceCanvas'); // 得到画布
var ctx = canvasDom.getContext('2d'); // 得到画布的上下文对象
var lx = "${lx}";
var flag = false; //鼠标移动前，判断鼠标是否按下
var startX = 0; // 鼠标开始移动的位置X
var startY = 0; // 鼠标开始移动的位置Y
var url = ''; // canvas图片的二进制格式转为dataURL格式
var color = '#FF0000'; //画笔颜色，默认为红色
var lineWidth = 2; 		//画笔粗细
var drawType = 2;		//绘制类型，默认为矩形
/* 编辑图片 end           */
function initSendToUserTree(){
	$.ajax({
		type : 'post',
		url : "${ctx }/common/authsystem/findSyncDeptPoliceForCombotree.json?cusNumber="+cusNumber,
		dataType : 'json',
		success : function(data) {
			
			if(data.exception==undefined){
				$("#sendToUserTree").tree("reload",data);
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
$.parseDone(function(){
	//初始化页面数据
	$("#mdoUpdtUserId").textbox("setValue",userId);
	$("#mdoCrteUserId").textbox("setValue",userId);
	$('#mdoCrteTime').datepicker('setDate',new Date());
	$('#mdoUpdtTime').datepicker('setDate',new Date());
	$("#imgEvidence_new").attr('src','');
	$("#imgClickNum_new").val(0); //重置图片点击次数计数
    id = messenger.evidenceSqnoList[0];
	$('#LastImg_new').unbind('click');
	$('#NextImg_new').unbind('click');
	
	//初始化接收人树（同步树）
	//initSendToUserTree();
	$("#mdoNoticeDepartment").combobox("reload","${ctx}/common/authsystem/findAllChildrenOrgByJyKeyForCombobox.json?cusNumber="+cusNumber);
		
	$("#mdoCusNumber").textbox("setValue",cusNumber);
	
	//若非省局登录则设置为本监狱
	if(jsConst.USER_LEVEL!=1){
		$("#mdoNoticeCusNumber").combobox("setValue",cusNumber);
		$("#mdoNoticeCusNumber").combobox("option","readonly",true);
	}else{
		$("#trSendToDepartment_new").hide();
		//$("#txtSendToUser_new").textbox("option","required",false);
		$("#mdoIsFromProv").textbox("setValue","1");	//来自省局
	}
	
	//选择证据信息的ID(evidence.js)
	evidence.evidenceSqnoList=messenger.evidenceSqnoList;
	//选择证据信息的类型
	var fileTypeList=messenger.fileTypeList;
	if(lx=="1"){
        var zjxx_data=messenger.getzjxxData(evidence.evidenceSqnoList[0]);
        debugger;
        console.log("zjxx_data  SNAP截屏"+JSON.stringify(zjxx_data));
        $("#mdoMonitorName").textbox("setValue","监督单_"+zjxx_data.einAddrs);
        $("#mdoAddr").textbox("setValue",zjxx_data.einAddrs);
        $("#mdoTime").datepicker("setValue",zjxx_data.einCrteTime);
        // 类型1图片则显示图片，类型2视频则显示视频播放按钮d
        if (zjxx_data.einFileTypeIndc == 1){
            getImg("#imgEvidence_new", evidence.evidenceSqnoList[0]);
        }
        else if (zjxx_data.einFileTypeIndc == 2){
            setVideoImg("#imgEvidence_new");
            filePlay(evidence.evidenceSqnoList[0]);
        }
    }else{
        var zjxx_data=messenger.zjxx_getRowData(evidence.evidenceSqnoList[0]);
        console.log(zjxx_data);
        $("#mdoMonitorName").textbox("setValue","监督单_"+zjxx_data.EIN_ADDRS);
        $("#mdoAddr").textbox("setValue",zjxx_data.EIN_ADDRS);
        console.log(zjxx_data.EIN_CRTE_TIME);
        $("#mdoTime").datepicker("setValue",zjxx_data.EIN_CRTE_TIME);
        // 类型1图片则显示图片，类型2视频则显示视频播放按钮d
        if (zjxx_data.EIN_FILE_TYPE_INDC == 1){
            getImg("#imgEvidence_new", evidence.evidenceSqnoList[0]);
        }
        else if (zjxx_data.EIN_FILE_TYPE_INDC == 2){
            setVideoImg("#imgEvidence_new");
            filePlay(evidence.evidenceSqnoList[0]);
        }
    }

	//图片滚动查看点击事件
	lastNextClick(evidence.evidenceSqnoList,fileTypeList,2);

    //加载编辑的图片
        if(id) {
            getImgName(id,false,initImage);
        }
})

function openUserDialog(){
	$("#dialogId_sendToUser").dialog("open");
}
//发送对象查询
function userSearch(e, data) {
	//回车时触发
	if(e.keyCode == 13)      
    {
		$("#sendToUserTree").tree("filterNodesByParam", {
			name : data.value
		});
    }	
}
//保存发送对象
function f_save(){
	var nodes=$("#sendToUserTree").tree("getCheckedNodes");
	//input显示的值
	var sendUserText="";
	for(var i=0;i<nodes.length;i++){
		if(!nodes[i].isParent){
			var number = nodes[i].id;
	    	var name = nodes[i].name;
	    	evidence.sendToUserList[number] = name;
	    	if(sendUserText==""){
	    		sendUserText=name;
	    	}else{
	    		sendUserText=sendUserText+","+name;
	    	}
	    	
		}
	}
	$("#txtSendToUser_new").textbox("setValue",sendUserText);
	
	$("#dialogId_sendToUser").dialog("close");
}



/**
 * 保存监督单
 */
function saveJdd(type) {
	
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
				        		$('#fxcjDialog').html("");
				        		$('#fxcjDialog').dialog({
				        			width : 1000,
				        			height : 800,
				        			title : '风险采集',
				        			url : jsConst.basePath + '/aqfxyp/fxcj/toIndex?type=1'
				        		});
				        		$("#fxcjDialog").dialog("open");
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
};


//设置绘制类型
function setDrawType(type){
    drawType=type;
}

//画笔工具
function drawPencil(endX,endY){
    if(flag){
        ctx.lineTo(endX,endY);
        ctx.stroke(); // 调用绘制方法
    }else{
        ctx.beginPath();
        ctx.moveTo(startX,startY);
    }
}

//画矩形
function drawRect(endX,endY){
    if(flag){
        //ctx.clearRect(0,0,canvasDom.width,canvasDom.height);
        ctx.beginPath();
        ctx.strokeRect(startX,startY,endX-startX,endY-startY);
    }
}

//画直线
function drawLine(endX,endY){
    if(flag){
        //ctx.clearRect(0,0,canvasDom.width,canvasDom.height);
        ctx.beginPath();
        ctx.moveTo(startX,startY);
        ctx.lineTo(endX,endY);
        ctx.stroke();
    }
}

//画正圆
function drawCircle(endX,endY){
    if(flag){
        //ctx.clearRect(0,0,canvasDom.width,canvasDom.height);
        ctx.beginPath();
        var rx = (endX-startX)/2;
        var ry = (endY-startY)/2;
        //var r = Math.sqrt(rx*rx+ry*ry);
        var r = Math.min(rx,ry);
        ctx.arc(rx+startX,ry+startY,r,0,Math.PI*2); // 第6个参数默认是false-顺时针
        ctx.stroke();
    }
}

//画椭圆
function drawEllipse(endX,endY){
    if(flag){
        if (CanvasRenderingContext2D.prototype.ellipse == undefined) {
            CanvasRenderingContext2D.prototype.ellipse = function(x, y, radiusX, radiusY, rotation, startAngle, endAngle, antiClockwise) {
                this.save();
                this.translate(x, y);
                this.rotate(rotation);
                this.scale(radiusX, radiusY);
                this.arc(0, 0, 1, startAngle, endAngle, antiClockwise);
                this.restore();
            }
        }

        //ctx.clearRect(0,0,canvasDom.width,canvasDom.height);
        ctx.beginPath();
        var rx = (endX-startX)/2;
        var ry = (endY-startY)/2;

        ctx.ellipse(rx+startX, ry+startY, rx, ry, 0, 0, Math.PI*2, false);
        ctx.stroke();
    }
}

//解决画布上只能同时有一个图形
function loadImage(){
    var imgTmp = new Image();
    imgTmp.src = url;
    ctx.drawImage(imgTmp,0,0,canvasDom.width,canvasDom.height);
}

// 保存文件函数(加强版)
var saveFile = function(data, filename){
    //将mime-type改为image/octet-stream，强制让浏览器直接download
    //data = data.replace("image/png", "image/octet-stream");

    if (canvasDom.msToBlob) {//IE9+浏览器
        var blob = canvasDom.msToBlob();
        window.navigator.msSaveBlob(blob, filename);
    }
    else{//firefox,chrome
        var save_link = document.createElementNS('http://www.w3.org/1999/xhtml', 'a');
        save_link.href = data;
        save_link.download = filename;

        var event = document.createEvent('MouseEvents');
        event.initMouseEvent('click', true, false, window, 0, 0, 0, 0, 0, false, false, false, false, 0, null);
        save_link.dispatchEvent(event);
    }

};


/*图片编辑 -------------------------------------start------------------------*/

/* 为canvas绑定mouse事件 */
canvasDom.onmousedown=function(e){
    if(canvasDom.setCapture)
    {
        canvasDom.setCapture();
    }
    flag = true;
    startX = e.offsetX; // 鼠标落下时的X
    startY = e.offsetY; // 鼠标落下时的Y
    ctx.strokeStyle = color;	//设置画笔颜色
    ctx.lineWidth = lineWidth	//设置画笔粗细

    document.onmousemove=function(e)
    {
        console.log(canvasDom.offsetLeft+"-"+canvasDom.offsetTop+"-"+canvasDom.width+"-"+canvasDom.height);
        var endX = e.offsetX;
        var endY = e.offsetY;
        if(endX<canvasDom.offsetLeft)
        {
            endX=canvasDom.offsetLeft;
        }
        else if(endX>canvasDom.offsetLeft+canvasDom.width)
        {
            endX=canvasDom.offsetLeft+canvasDom.width;
        }

        if(endY<canvasDom.offsetTop)
        {
            endY=canvasDom.offsetTop;
        }
        else if(endY>canvasDom.offsetTop+canvasDom.height)
        {
            endY=canvasDom.offsetTop+canvasDom.height;
        }
        endX = endX - canvasDom.offsetLeft;
        endY = endY - canvasDom.offsetTop;

        //清空画布（避免绘制多个图片,虽然此处不清空界面上的效果不会有影响）
        ctx.clearRect(0,0,canvasDom.width,canvasDom.height);
        //画之前执行，否则绘制的图形会被背景覆盖
        loadImage();

        //进行绘制
        switch (drawType)
        {
            case 0:
                //drawPencil(endX,endY);
                break;
            case 1:
                drawLine(endX,endY);
                break;
            case 2:
                drawRect(endX,endY);
                break;
            case 3:
                drawEllipse(endX,endY);
                break;
            case 4:
                drawCircle(endX,endY);
                break;
            default:
                drawRect(endX,endY);
        }

    }
    document.onmouseup=function()
    {
        document.onmousemove=null;
        document.onmouseout=null;
        if(canvasDom.releaseCapture)
        {
            canvasDom.releaseCapture();
        }
        flag = false;
        url = canvasDom.toDataURL(); // 每次 mouseup 都保存一次画布状态
    }
    return false;
};

/**
 * 加载图片
 isOriginal 是否是原始的，如果为true，则还原到最初的图片
 */
function getImgName(sqno,isOriginal,f_callBack){
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

                einCusNumber = data.EIN_CUS_NUMBER;
                originalFtpFileName = "/"+ftpPath+"/"+fileName;
                var defaultImgSrc=jsConst.STATIC_RESOURCE_ADDR+"/"+ftpPrefix+"/"+ftpPath+"/"+fileName;
                var imgSrc=jsConst.STATIC_RESOURCE_ADDR+"/"+ftpPrefix+"/"+ftpPath+"/"+fileName;
                if(isOriginal){
                    var exts = fileName.substring(fileName.lastIndexOf("."));
                    var newFileName = fileName.substring(0,fileName.lastIndexOf(".")) + "_org" + exts;
                    imgSrc=jsConst.STATIC_RESOURCE_ADDR+"/"+ftpPrefix+"/"+ftpPath+"/"+newFileName;
                }
                if( typeof f_callBack == 'function' ){
                    f_callBack.call(this, imgSrc,defaultImgSrc);
                }

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
function initCanvasImg(img){
    debugger;
    /*var ptrn = ctx.createPattern(img,'no-repeat');

    ctx.fillStyle = ptrn;

    ctx.fillRect(0, 0, canvasDom.width, canvasDom.height);*/

    //创建一个临时的canvas，用canvas 的drawImg()方法，对图片进行缩放，
    //然后在再把canvas 传到createPattern里面。以此达到canvas路径的大小和图片的大小一致的效果。
    var canvasTemp = document.createElement('canvas');
    var contextTemp = canvasTemp.getContext('2d');
    canvasTemp.width = canvasDom.width;
    canvasTemp.height = canvasDom.height;

    console.log(canvasDom.width+"--"+canvasDom.height);
    contextTemp.drawImage(img,0,0,canvasTemp.width, canvasTemp.height);
    var ptn = ctx.createPattern(canvasTemp,'no-repeat')
    ctx.fillStyle = ptn;

    ctx.fillRect(0, 0, canvasDom.width, canvasDom.height);
    //ctx.fill();

    url = canvasDom.toDataURL(); //保存一次画布状态
}

//初始化图片
function initImage(imgSrc,defaultImgSrc){
    var img = new Image();
    //img.width = canvasDom.width;
    //img.height = canvasDom.height;
    if (img.complete) {
        initCanvasImg(img);
    } else {
        img.onload = function () {
            initCanvasImg(img);
            img.onload = null;
        };
    };
    img.onerror = function () {
        img.src = defaultImgSrc+"?temp=" + Math.random();
    };

    debugger;
    img.src = imgSrc+"?temp=" + Math.random();
}

/*$.parseDone(function(){

    if(id) {
        getImgName(id,false,initImage);
    }
});*/

editImgEvidence = {
    clear: function(e,ui){
        //canvasDom.width = canvasDom.width;
        //清空画布
        ctx.clearRect(0,0,canvasDom.width,canvasDom.height);
        //初始化图片
        if(id) {
            getImgName(id,true,initImage);
           // getImg("#imgEvidence_new",id);
        }
    },
    submit:function(){

        $.ajax({
            type : 'post',
            url : '${ctx}/evidence/updateFtpImg.json',
            data : {
                "cusNumber":cusNumber,
                "fileName":originalFtpFileName,
                "imgBase64":canvasDom.toDataURL("image/jpeg")
            },
            dataType : 'json',
            success : function(data) {
                if (data.success) {
                 getImg("#imgEvidence_new",id);
                    $.message({
                        iframePanel:true,
                        message:"操作成功！",
                        cls:"success"});
                } else {
                    $.message({
                        iframePanel:true,
                        message : data.msg,
                        type : "danger"
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
    download: function(){
        var imgData = canvasDom.toDataURL("image/jpeg");
        saveFile(imgData,originalFtpFileName);
    }
}

/*  图片编辑--------------------------------end-------------------------------*/

</script>
<%@page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<style>

</style>

<div style="text-align: center; height: 100%; width: 100%">
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
	    			<cui:button label="保存并提交"  onClick = "editImgEvidence.submit()"  ></cui:button>
	    			<cui:button label="下载"  onClick = "editImgEvidence.download()"  ></cui:button>
				</div>
			</td>
		</tr>
	</table>
     <canvas id="imgEvidenceCanvas" width="800" height="500"></canvas>
</div>
<script>
	var einCusNumber="";	//证据监狱编号
	var originalFtpFileName="";	//ftp原始文件名
	var id='${id}';
	
	var canvasDom = document.getElementById('imgEvidenceCanvas'); // 得到画布
	var ctx = canvasDom.getContext('2d'); // 得到画布的上下文对象
			
	var flag = false; //鼠标移动前，判断鼠标是否按下 
	var startX = 0; // 鼠标开始移动的位置X
	var startY = 0; // 鼠标开始移动的位置Y
	var url = ''; // canvas图片的二进制格式转为dataURL格式
	var color = '#FF0000'; //画笔颜色，默认为红色
	var lineWidth = 2; 		//画笔粗细
	var drawType = 2;		//绘制类型，默认为矩形
	
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
	
	$.parseDone(function(){
		
		if(id) {
			getImgName(id,false,initImage);
		}
	});
	
	editImgEvidence = {
		clear: function(e,ui){
			//canvasDom.width = canvasDom.width;
			//清空画布
			ctx.clearRect(0,0,canvasDom.width,canvasDom.height);
			//初始化图片
			if(id) {
				getImgName(id,true,initImage);
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
</script>
<%@page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<link rel="stylesheet" type="text/css" href="${ctx}/static/module/evidence/evidence.css" />


<div style="height: 100%;">
	<cui:button label="关联证据" onClick="relevanceZJ"></cui:button>
	<cui:grid id="gridId_zjxx" multiselect="true" colModel="gridColModel_zjxx" fitStyle="fill" datatype="json" url="" pager="true"></cui:grid>
</div>


<cui:dialog id="dialogId_jdjc" autoOpen="false" iframePanel="true" reLoadOnOpen="true" modal="true" resizable="false"></cui:dialog>



<!-- 单独显示图片或视频信息 -->
<div id="imgView" class="video-file" style="display: none; overflow: hidden">
	<img id="imgEvi" src="">
</div>
<div id="videoView" class="video-file" style="display: none; overflow: hidden">
	<div class="play" title="点击播放录像文件..."></div>
	<img id="videoEvi" src="">
</div>

<script>
	var jsConst = window.top.jsConst;
	var vc = window.top.videoClient;

	$.parseDone(function() {
		//证据信息
		$("#gridId_zjxx").grid( "reload", "${ctx }/evidence/searchEvidence.json?einSttsIndc=0&einCusNumber=" + jsConst.ORG_CODE + "&startTime=${startingDate}&endTime=${endingDate}");
	})
	 
	var zjwjlxJson = <%=CodeFacade.loadCode2Json("4.20.32")%>;

	var gridColModel_zjxx = [ {
		label : "id",
		name : "ID",
		hidden : true,
		key : true
	}, {
		label : "证据标题",
		name : "EIN_TITLE"
	}, {
		label : "监控地点",
		name : "EIN_ADDRS"
	}, {
		label : "记录时间",
		name : "EIN_CRTE_TIME"
	}, {
		label : "文件类型",
		name : "EIN_FILE_TYPE_INDC",
		formatter : "convertCode",
		revertCode : true,
		formatoptions : {
			'dataStructure' : 'list',
			'data' : zjwjlxJson
		}

	}, {
		label : "操作",
		formatter : "formatterA",
	} ];
	
	function formatterA(cellValue,options,rowObject){
		var result="<span><a href='#' style='color: #4692f0;' onclick='showEvidence(\""+rowObject.EIN_FILE_TYPE_INDC+"\",\""+rowObject.ID+"\");return false;'>查看</a></span>";
		return result;
	}

	function showEvidence(fileType, ID) {
		//图片
		if (fileType == 1) {
			showImg(ID);
		}
		//视频
		else if (fileType == 2) {
			showVideo(ID);
		}
	}
	/*
	 * 显示图片信息
	 */
	function showImg(seNo) {
		getImg("#imgEvi", seNo);
		$('#imgView').show();
		$('#imgView').dialog({
			width : 510,
			height : 405,
			title : "查看图片信息",
			modal : true
		});
	}

	/**
	 *  显示视频信息
	 */
	function showVideo(seNo) {
		//getImg('#videoEvi',seNo);
		setVideoImg('#videoEvi');
		filePlay(seNo);
		$('#videoView').show();
		$('#videoView').dialog({
			width : 510,
			height : 405,
			title : "查看视频信息",
			modal : true
		});
	}

	/**
	 * 设置视频默认图片 add by zk
	 */
	function setVideoImg(id) {
		$(id).attr('src', jsConst.basePath + "/static/module/evidence/img/video_bg.png");
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
					$.messageQueue({ message : data.exception.cause.message, cls : "warning", iframePanel : true, type : "info" });
				}
				
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$.messageQueue({ message : textStatus, cls : "warning", iframePanel : true, type : "info" });
			}
		});
		
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
						$(id).attr('src',jsConst.STATIC_RESOURCE_ADDR+"/"+ftpPrefix+"/"+ftpPath+"/"+fileName);
						$('div.play').hide();
						
					}else{
						$.messageQueue({ message : data.exception.cause.message, cls : "warning", iframePanel : true, type : "info" });
					}
					
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					$.messageQueue({ message : textStatus, cls : "warning", iframePanel : true, type : "info" });
				}
			});

	}

	function relevanceZJ() {
		var selected = $("#gridId_zjxx").grid("option", "selarrrow");
		console.info(JSON.stringify(selected));

		if (selected.length > 0) {
			//alert(JSON.stringify(selected));
			var data = {};
			data["ids"] = JSON.stringify(selected);//证据信息ids
			data["ywId"] = "${ywId}";//报警记录id
			data["fjfl"] = "uploaderId_zjxx";//上传控件的id
			//alert(JSON.stringify(data));
			$.ajax({
				type : "post",
				url : "${ctx}/alarm/glzjxx.json",
				data : data,
				dataType : 'json',
				//contentType : "application/json",
				success : function(data) {
					if (data.success) {
						$.messageQueue({ message : data.msg, cls : "success", iframePanel : true, type : "info" });
						$("#dialogId_zjxx").dialog("close");
						findFiles();//查询附件信息
					} else {
						$.messageQueue({ message : data.msg, cls : "success", iframePanel : true, type : "info" });
					}
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					$.messageQueue({ message : textStatus, cls : "success", iframePanel : true, type : "info" });
				}
			});

		} else {
			$.messageQueue({ message : "请先勾选记录！", cls : "success", iframePanel : true, type : "info" });
		}
	}
</script>
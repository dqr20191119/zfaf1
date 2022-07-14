<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<%@page import="com.ces.prison.common.constants.GroupKeyConst"%>
<%@page import="com.ces.prison.common.constants.SystemConst"%>
<%@taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<style>
.rollcallList .detailImgs {
	vertical-align: middle;
	border-style: none;
}

.rollcallList {
	height: 100%;
	width: 100%;
	border: 1px solid;
	border-bottom-left-radius: 6px;
	border-bottom-right-radius: 6px;
	background: rgba(1, 25, 69, 1);
	overflow: auto;
	box-sizing: border-box;
	scrollbar-arrow-color: rgba(1, 25, 69, 1);
	/*滚动条滑块按钮的颜色*/
	scrollbar-face-color: rgba(1, 25, 69, 1);
	/*滚动条整体颜色*/
	scrollbar-highlight-color: rgba(1, 25, 69, 1);
	/*滚动条阴影*/
	scrollbar-shadow-color: rgba(1, 25, 69, 1);
	/*滚动条轨道颜色*/
	scrollbar-track-color: rgba(1, 25, 69, 1);
}

.rollcallList .card {
	position: relative;
	height: 100%;
	width: 100%;
	background: #33444e;
	border: 1px solid #151a20;
	border-radius: 4px;
	cursor: pointer;
	transition: .3s;
	overflow: hidden;
}

.rollcallList .detailContent {
	height: 180px;
	position: relative;
}

.rollcallList .detailName_h {
	width: 100%;
	height: 48px;
	text-align: center;
	color: #abc;
	border-top: 1px solid #1e343e;
	transition: .3s;
	background: rgba(168, 99, 21, 1);
}

.rollcallList .detailName_done {
	width: 100%;
	height: 48px;
	text-align: center;
	color: #abc;
	border-top: 1px solid #1e343e;
	transition: .3s;
	background: rgba(1, 163, 36, 1);
}

.rollcallList .detailItems {
	float: left;
	width: 210px;
	height: 250px;
	box-sizing: border-box;
	padding: 10px 15px 10px;
	margin-left: 23px;
}

.rollcallList .names {
	text-align: center;
	font-size: 16px;
	line-height: 24px;
	width: 100%;
	overflow: hidden;
	text-overflow: ellipsis;
	white-space: nowrap;
	color: white;
}

.rollcallList .detailImgWrapper {
	width: 170px;
	height: 170px;
	position: absolute;
	left: 50%;
	top: 50%;
	-ms-transform: translate(-50%, -50%);
	transform: translate(-50%, -50%);
}

.rollcallList .detailImgs {
	width: 100%;
	height: 100%;
}

.fd {
    width: 0px;
    height: 0px;
    position: fixed;
    z-index: 9999;
    animation: big 2s ;
    /*Safari and Chrome*/
    -webkit-animation: big 2s ;
}

.fd .detailImgWrapper  {
    width: 100%;
    height: 100%;
    position: absolute;
    left: 50%;
    top: 50%;
    -ms-transform: translate(-50%, -50%);
    transform: translate(-50%, -50%);
}

.fd  .detailContent {
    height: 90%;
    position: relative;
}

.fd  .names {
    text-align: center;
    font-size: 20px;
    line-height: 20px;
    width: 100%;
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
    color: white;
}

@keyframes big {
    from {
        width: 210px;
        height: 250px;
    }
    to {
        width: 300px;
        height: 300px;
        z-index: 9999;
        transform: translate3d(-70px,-10px,9999px);
    }
}
</style>


<div id="rollcallList" class="rollcallList"></div>
<script>
	var videoClient = window.top.videoClient;
	var isShowCamera = false;
	$.parseDone(function() {
		getPrisonerInfo();
	});


	function getPrisonerInfo() {
		var data = {};
		data["lch"] = "${floorId}";
		data["jsh"] = "${cellId}";
		var url = jsConst.basePath + "/callNames/getJSPrisonerInfo.json";
		$.ajax({
			type : 'post',
			url : url,
			data : data,
			dataType : 'json',
			success : function(data) {
				if (data.success) {
					var personList = data.obj;
					$("#rollcallList").empty()
					for (var i = 0; i < personList.length; i++) {
						var persioner = personList[i];
						var div = "<div id=\"detailItems-"+persioner.PRSNR_IDNTY+"\" class=\"detailItems\"> <div class=\"card\"> <div class=\"detailContent\"> <div class=\"detailImgWrapper\">"
								+ "<img id=\"img-" + persioner.PRSNR_IDNTY + "\" class=\"detailImgs\" alt=\"\" src=\"" + jsConst.basePath
								+ "/common/all/findZfPicInfo?zfBh=" + persioner.PRSNR_IDNTY + "&mtNrFl=3\"/>"
								+ "</div></div><div id=\"title-"+persioner.PRSNR_IDNTY+"\" title=\""+persioner.NAME+"\" class=\"detailName_h\" >"
								+ "<div class=\"names\"></div><div class=\"names\">" + persioner.NAME + "</div></div></div></div>";
						$("#rollcallList").append(div);
					}
					isShowCamera = true;
					getEndRollcallList();
				} else {
					$.messageQueue({ message : data.msg, cls : "warning", iframePanel : true, type : "info" });
				}
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$.messageQueue({ message : textStatus, cls : "warning", iframePanel : true, type : "info" });
			}
		});

	}

	function getEndRollcallList() {
		$.ajax({
			type : "post",
			url : "${ctx}/callNames/getEndRollcallList.json?rollcallId=${rollcallId}&demptId=${demptId}&floorId=${floorId}&cellId=${cellId}",
			dataType : "json",
			success : function(data) {
				if (data.success) {
					var cameraIds = data.obj.cameraIds;
					if(!isEmpty(cameraIds) && cameraIds.length > 0 && isShowCamera){
						//调用视频设备	 
						videoClient.playVideoHandle({ 'subType' : 2, 'layout' : cameraIds.length, 'data' : cameraIds, 'callback' : function(data) {} });
						isShowCamera = false;
					}
					var personList = data.obj.floorData[0].houseData[0].personList;//监舍罪犯集合

					for (var i = 0; i < personList.length; i++) {
						var personer = personList[i];
						if (personer.isRollCall == "1" || personer.isRollCall == 1) {
							if($("#title-" + personer.criminalNumber).hasClass('detailName_h')){
								$("#title-" + personer.criminalNumber).attr('class', 'detailName_done' );
                                $("#rollcallList").scrollTop($("#detailItems-" + personer.criminalNumber).offset().top - $("#rollcallList").offset().top + $("#rollcallList").scrollTop());
                                var $that = $("#detailItems-" + personer.criminalNumber).clone(true)
                                    .width(0)
                                    .height(0);
                                $that.css({
                                    "top" :   $("#detailItems-" + personer.criminalNumber).offset().top+ "px",
                                    "left" :    $("#detailItems-" + personer.criminalNumber).offset().left+ "px"
                                });
                                $that.insertAfter($("#detailItems-" + personer.criminalNumber));
                                $that.addClass("fd");
                                (function (that) {
                                    setTimeout(function () {
                                        that.remove();
                                    }, 2000);
                                }($that));
							}
						}
					}
					if(!isEmpty(rollcallSatus) && rollcallSatus == "0" || rollcallSatus == 0){
						setTimeout(function(){
							getEndRollcallList();
						},3 * 1000);
					}
					
				} else {
					$.messageQueue({ message : data.msg, cls : "warning", iframePanel : true, type : "info" });
				}

			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$.messageQueue({ message : textStatus, cls : "warning", iframePanel : true, type : "info" });
			}
		});

	}
</script>
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
	box-sizing: border-box;
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
	$.parseDone(function() {
		getPrisonerInfo();
	});


	function getPrisonerInfo() {
		var data = {};
		data["jyh"] = "${jyh}";
		data["lch"] = "${lch}";
		data["jsh"] = "${jsh}";
		var url = jsConst.basePath + "/callNames/master/getPrisonerInfoByJsFromCache.json";
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
						var div = "<div id=\"detailItems-" + persioner.ZFBH + "\" class=\"detailItems\"> <div class=\"card\"> <div class=\"detailContent\"> <div class=\"detailImgWrapper\">"
								+ "<img id=\"img-" + persioner.ZFBH + "\" class=\"detailImgs\" alt=\"\" src=\"" + jsConst.basePath
								+ "/callNames/register/getZfPicInfo?imgUrl=" + persioner.IMG_URL + "&imgName=" + persioner.IMG_NAME + "&imgSize=" + persioner.IMG_SIZE +"\"/>"
								//+ "/callNames/register/getZfPicInfo?imgUrl=" + persioner.IMG_URL + "&imgName=" + persioner.IMG_NAME + "&imgSize=9999999\"/>"
								+ "</div></div><div id=\"title-" + persioner.ZFBH+"\" title=\"" + persioner.ZF + "\" class=\"detailName_h\" >"
								+ "<div class=\"names\"></div><div class=\"names\">" + persioner.ZF + "</div></div></div></div>";
						$("#rollcallList").append(div);
					}
					$("#rollcallList").mCustomScrollbar({
						theme : "minimal-light",
						autoExpandScrollbar : true
				}); 
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
		si_getEndRollcallList = setInterval(function(){
		var data = {};
		data["jyh"] = "${jyh}";
		data["lch"] = "${lch}";
		data["jsh"] = "${jsh}";
		$.ajax({
			type : "post",
			url : "${ctx}/callNames/master/getCallNamesPrisonerDtlsByJs.json",
			data : data,
			dataType : "json",
			success : function(data) {
				if (data.success) {
					var zfbhs = data.obj.zfbhs; //监舍罪犯集合
					/* debugger; */
					for(var key in zfbhs){
	 					console.log("zfbh：" + key + ",值：" + zfbhs[key]);
	 					if (data.obj.state == "1" || data.obj.state == 1 ) {
							if($("#title-" + key).hasClass('detailName_h') &&  zfbhs[key] == '1'){
								$("#title-" + key).attr('class', 'detailName_done' );
							}
						}
					}
				 
					if(data.obj.state == "2" || data.obj.state == 2){
						clearInterval(si_getEndRollcallList); 
					} 
				} else {
					$.messageQueue({ message : data.msg, cls : "warning", iframePanel : true, type : "info" });
				}

			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$.messageQueue({ message : textStatus, cls : "warning", iframePanel : true, type : "info" });
			}
		});
		},3 * 1000);
	}
</script>
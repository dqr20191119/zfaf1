<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sitemesh" uri="http://www.opensymphony.com/sitemesh/decorator"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta http-equiv="cache-control" content="no-store, no-cache, must-revalidate"> 
<meta http-equiv="pragma" content="no-cache"> 
<meta http-equiv="expires" content="0">
<title>系统授权默认页面</title>

<sitemesh:head />
</head>
<body>
<!-- 进入这个页面是不正常的，新增的模块请自觉添加过滤规则 -->
<!-- 伟大的工程师，您好：进入这个页面是不正常的，请自觉过滤 -->
	<sitemesh:body />
	
安全通道创建中:
<label id="timer" style="color:blue;"></label> 
	
</body>
<script type="text/javascript">


//单纯分钟和秒倒计时
function resetTime(time){
  var timer=null;
  var t=time;
  var m=0;
  var s=0;
  m=Math.floor(t/60%60);
  m<10&&(m='0'+m);
  s=Math.floor(t%60);
  function countDown(){
   s--;
   
   s<10&&(s='0'+s);
   if(s.length>=3){
    s=59;
    m="0"+(Number(m)-1);
   }
   if(m.length>=3){
    m='00';
    s='00';
    clearInterval(timer);
   }
   //console.log(m+"分钟"+s+"秒");
   if(s=="01"){
	   window.location.href="${ctx}/portal/shouye";
   }
   
   document.getElementById("timer").innerHTML = s+"秒";
  }
  timer=setInterval(countDown,1000);
}

resetTime(4);





//$(function(){
//	var	panel = $("#coralui-layout").layout("panel","center");
//	panel.panel("refresh", "${ctx}/work/index");
//});
</script>
</html>
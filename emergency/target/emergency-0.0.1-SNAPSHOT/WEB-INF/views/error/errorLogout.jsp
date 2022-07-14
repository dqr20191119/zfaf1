<!DOCTYPE HTML>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<html lang="en">
<head>
<title>监内动态</title> 
<script src="${ctx}/static/jquery-cui/js/jquery-1.11.3.min.js"></script>
</head>

<body>
	<div id="tipDivId" style="font-size: 28px; color: red;"></div>
</body>

<script>

var count = 3;
var tempInterval;

$(function() {
	
	tempInterval = setInterval(closeWin, 1000);
});

function closeWin() {
	
	$("#tipDivId").html("用户已退出......" + count);
	count--;
    if(count == -1) {
        clearInterval(tempInterval);
        
     	// 关闭当前窗口
		window.opener = null;
		window.open('', '_self');
		window.close();
    }
}

</script>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.cesgroup.framework.util.ConfigUtil"%>
<%@ page import="com.cesgroup.prison.common.bean.user.UserBean"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta http-equiv="cache-control" content="no-store, no-cache, must-revalidate">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="expires" content="0">
<title>test演示1</title>
<!-- coral4 css start  -->
<link rel="stylesheet" href="${ctx}/static/cui/cui.min.css" />
<!-- coral4 css  end  -->



<!-- coral4 js start -->
<script src="${ctx}/static/jquery-cui/js/jquery-1.11.3.min.js"></script>
<script src="${ctx}/static/cui/cui.js"></script>
<!-- coral4 js end -->

<!-- app js define start  -->
<script src="${ctx}/static/js/scripts/common.js"></script>
<script src="${ctx}/static/resource/style/js/function.js"></script>
<script src="${ctx}/static/js/scripts/prettify.js"></script>
<!-- app js define  end  -->

<script src="${ctx}/static/system/common.js"></script>
<script src="${ctx}/static/system/jsConst.js"></script>
<script type="text/javascript" src="${ctx}/static/websocket/ces.date.js"></script>
<script type="text/javascript" src="${ctx}/static/websocket/ces.seq.js"></script>
<script type="text/javascript" src="${ctx}/static/websocket/ces.array.js"></script>
<script type="text/javascript" src="${ctx}/static/websocket/ces.map.js"></script>
<script src="${ctx}/static/websocket/websocket.js"></script>

<script type="text/javascript" src="${ctx}/static/websocket/jsMessage.js"></script>
<script type="text/javascript" src="${ctx}/static/websocket/messageConst.js"></script>
<script type="text/javascript" src="${ctx}/static/websocket/messageCallBack.js"></script>
<script type="text/javascript" src="${ctx}/static/websocket/messagerDialog.js"></script>


<!-- script src="${ctx}/static/websocket/webmessage.js"--> 

<script>
	jsConst.basePath = "${ctx}/";
	
	
	messageConst.SOCKET_URL = '<%=ConfigUtil.get("SOCKET_URL")%>';
	messageConst.WEBSOCKET_INTERVAL = <%=ConfigUtil.get("WEBSOCKET_INTERVAL")%>;
	messageConst.WEBSOCKET_RELOGINTIME = <%=ConfigUtil.get("WEBSOCKET_RELOGINTIME")%>;
	messageConst.WEBSOCKET_REEXCUTETIME = <%=ConfigUtil.get("WEBSOCKET_REEXCUTETIME")%>;
	
	<%
	UserBean userBean = com.cesgroup.prison.common.facade.AuthSystemFacade.getLoginUserInfo();
	%>
	// 初始消息
	try{window.jsMessage.INIT(<%=userBean.getCusNumber()%>, <%=userBean.getPoliceNo()%>);}catch(e){};
	
	
	initUserInfo();
	//webMessage = new WebMessage();
	//webMessage.init("ws://127.0.0.1:8087/websocket");
	
	//function loadDutyPolice(data){
	//	alert(data);
	//	console.log(data);	
	//}
	
	// 订阅消息：当前监内民警
	//webMessage.on('8000', 'policeInfo.1003', function(data){
	//	loadDutyPolice(data);
	//});
	
	/**
 * 加载当前在监民警人数
 */
function parseInPoliceCount1(data){
	if ((typeof data) == "string") {
		data = JSON.parse(data).obj.data;
	}
	var count = data.INSIDE_POLICE_COUNT;
	alert("num:"+count);
	//if(count == 0){
	//	$("#current_insidePoliceCount").html(count);
	//}else{
	//	$("#current_insidePoliceCount").empty();
	//	$("#current_insidePoliceCount").append("<a href='javascript:void(0);'  onclick='toPoliceList()'>" + count + "</a>");
	//}
	
}
	
	
	
</script>




</head>
<body>
	
</body>
</html>





























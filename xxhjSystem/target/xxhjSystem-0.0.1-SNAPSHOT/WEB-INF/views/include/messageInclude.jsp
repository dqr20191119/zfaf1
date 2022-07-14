<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.cesgroup.framework.util.ConfigUtil"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<script type="text/javascript" src="${ctx}/static/websocket/ces.date.js"></script>
<script type="text/javascript" src="${ctx}/static/websocket/ces.seq.js"></script>
<script type="text/javascript" src="${ctx}/static/websocket/ces.array.js"></script>
<script type="text/javascript" src="${ctx}/static/websocket/ces.map.js"></script>
<script type="text/javascript" src="${ctx}/static/websocket/websocket.js"></script>
<script type="text/javascript" src="${ctx}/static/websocket/jsMessage.js"></script>
<script type="text/javascript" src="${ctx}/static/websocket/messageConst.js"></script>
<script type="text/javascript" src="${ctx}/static/websocket/messageCallBack.js"></script>
<script type="text/javascript" src="${ctx}/static/websocket/messagerDialog.js"></script>
<script type="text/javascript">
	messageConst.SOCKET_URL = '<%=ConfigUtil.get("SOCKET_URL")%>';
	messageConst.WEBSOCKET_INTERVAL = <%=ConfigUtil.get("WEBSOCKET_INTERVAL")%>;
	messageConst.WEBSOCKET_RELOGINTIME = <%=ConfigUtil.get("WEBSOCKET_RELOGINTIME")%>;
	messageConst.WEBSOCKET_REEXCUTETIME = <%=ConfigUtil.get("WEBSOCKET_REEXCUTETIME")%>;
</script>

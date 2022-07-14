<%@ attribute name="id" %><%@ attribute name="cls" %><%@ attribute name="componentCls" %><%@ attribute name="title" %><%@ attribute name="iconCls" %><%@ attribute name="width" %><%@ attribute name="height" %><%@ attribute name="headerCls"%><%@ attribute name="bodyCls" %><%@ attribute name="style" %><%@ attribute name="href"%><%@ attribute name="cache" type="java.lang.Boolean" %><%@ attribute name="fit" type="java.lang.Boolean" %><%@ attribute name="noheader" type="java.lang.Boolean" %><%@ attribute name="content" %><%@ attribute name="collapsible" type="java.lang.Boolean" %><%@ attribute name="minimizable" type="java.lang.Boolean" %><%@ attribute name="maximizable" type="java.lang.Boolean" %><%@ attribute name="closable" type="java.lang.Boolean" %><%@ attribute name="collapsed" type="java.lang.Boolean" %><%@ attribute name="minimized" type="java.lang.Boolean" %><%@ attribute name="maximized" type="java.lang.Boolean" %><%@ attribute name="closed" type="java.lang.Boolean" %><%@ attribute name="tools"  %><%@ attribute name="url" %><%@ attribute name="loadtext" %><%@attribute name="beforeOpen"%><%@attribute name="beforeClose"%><%@attribute name="beforeDestroy"%><%@attribute name="beforeCollapse"%><%@attribute name="beforeExpand"%><%@attribute name="onLoad"%><%@attribute name="onOpen"%><%@attribute name="onClose"%><%@attribute name="onDestroy"%><%@attribute name="onResize"%><%@attribute name="onMove"%><%@attribute name="onMaximize"%><%@attribute name="onRestore"%><%@attribute name="onMinimize"%><%@attribute name="onCollapse"%><%@attribute name="onExpand"%><%@ include file="TagUtil.jsp" %><% 
String dataOption = tagUtil
.add("id", id)
.add("cls", cls)
.add("style", style)
.add("componentCls",componentCls)
.add("title", title)
.add("iconCls", iconCls)
.add("width", width)
.add("height", height)
.add("headerCls", headerCls)
.add("bodyCls",bodyCls)
.add("style",style)
.add("href",href)
.add("cache",cache)
.add("fit",fit)
.add("noheader",noheader)
.add("content",content)
.add("collapsible", collapsible)
.add("minimizable", minimizable)
.add("maximizable", maximizable)
.add("closable",closable)
.add("collapsed",collapsed)
.add("minimized",minimized)
.add("maximized",maximized)
.add("closed",closed)
.add("tools",tools)
.add("url",url)
.add("loadtext",loadtext)

.add("beforeOpen", beforeOpen)
.add("beforeClose", beforeClose)
.add("beforeDestroy", beforeDestroy)
.add("beforeCollapse", beforeCollapse)
.add("beforeExpand", beforeExpand)
.add("onLoad", onLoad)
.add("onOpen", onOpen)
.add("onClose", onClose)
.add("onDestroy", onDestroy)
.add("onResize", onResize)
.add("onMove", onMove)
.add("onMaximize", onMaximize)
.add("onRestore", onRestore)
.add("onMinimize", onMinimize)
.add("onCollapse", onCollapse)
.add("controllerName", request.getAttribute("controllerName"))
.add("onExpand", onExpand).toString();
String clsStr = "",
idStr = "",
styleStr = "";
idStr = id==null?"":"id="+id;
clsStr = cls == null ? "class='coralui-panel'" : "class='coralui-panel "+cls+"'";
styleStr = style == null ? "" : "style='"+style+"'";
 %><div <%=idStr %> <%=clsStr %> <%=styleStr %> data-options="<%=dataOption %>"><jsp:doBody /></div>
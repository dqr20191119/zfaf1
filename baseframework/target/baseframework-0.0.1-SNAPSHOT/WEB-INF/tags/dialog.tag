<%@attribute name="id"%><%@ attribute name="cls" %><%@ attribute name="contentType" %><%@ attribute name="componentCls" %><%@ attribute name="rendered" type="java.lang.Boolean" %><%@ attribute name="authorized" %><%@attribute name="style"%><%@attribute name="buttons"%><%@attribute name="closable" type="java.lang.Boolean"%><%@attribute name="resizable" type="java.lang.Boolean"%><%@attribute name="reLoadOnOpen" type="java.lang.Boolean"%><%@attribute name="autoDestroy" type="java.lang.Boolean"%><%@attribute name="destroyOnClose" type="java.lang.Boolean"%><%@attribute name="closeButtonClass"%><%@attribute name="loadtext"%><%@attribute name="url"%><%@attribute name="name"%><%@attribute name="title"%><%@attribute name="iframePanel" type="java.lang.Boolean"%><%@attribute name="subTitle"%><%@attribute name="appendTo"%><%@attribute name="maximizable" type="java.lang.Boolean"%><%@attribute name="minimizable" type="java.lang.Boolean"%><%@attribute name="draggable" type="java.lang.Boolean"%><%@attribute name="maximized" type="java.lang.Boolean"%><%@attribute name="autoOpen" type="java.lang.Boolean"%><%@attribute name="focusInput" type="java.lang.Boolean"%><%@attribute name="height"%><%@attribute name="width"%><%@attribute name="modal" type="java.lang.Boolean"%><%@attribute name="timeOut" type="java.lang.Integer"%><%@attribute name="position" fragment="false" rtexprvalue="true"%><%@attribute name="animateType"%><%@attribute name="type"%><%@attribute name="message"%><%@attribute name="onCreate" type="java.lang.String" %><%@attribute name="onLoad"%><%@attribute name="onLoadError"%><%@attribute name="onMaximize" type="java.lang.String"%><%@attribute name="onOpen" %><%@attribute name="onClose" %><%@attribute name="beforeClose" %><%@attribute name="onConfirm" type="java.lang.String" %><%@attribute name="onCancel" type="java.lang.String" %><%@include file="TagUtil.jsp" %><% 
String dataOption = tagUtil
.add("id", id)
.add("contentType", contentType)
.add("cls", cls)
.add("componentCls", componentCls)
.add("rendered", rendered).add("authorized", authorized)
.add("style", style)
.add("buttons", buttons)
.add("closable", closable)
.add("reLoadOnOpen", reLoadOnOpen)
.add("autoDestroy", autoDestroy)
.add("destroyOnClose", destroyOnClose)
.add("closeButtonClass", closeButtonClass)
.add("name", name)
.add("resizable", resizable)
.add("loadtext", loadtext)
.add("url", url)
.add("title", title)
.add("iframePanel", iframePanel)
.add("subTitle",subTitle)
.add("appendTo", appendTo)
.add("autoOpen", autoOpen)
.add("focusInput", focusInput)
.add("maximizable", maximizable)
.add("maximized", maximized)
.add("minimizable", minimizable)
.add("draggable", draggable)
.add("height", height)
.add("width", width)
.add("modal", modal)
.add("timeOut", timeOut)
.add("position", position)
.add("animateType", animateType)
.add("onCreate", onCreate)
.add("onLoad", onLoad)
.add("onLoadError", onLoadError)
.add("onMaximize", onMaximize)
.add("onOpen", onOpen)
.add("onClose", onClose)
.add("beforeClose", beforeClose)
.add("onConfirm", onConfirm)
.add("controllerName", request.getAttribute("controllerName"))
.add("onCancel", onCancel).toString();
String clsStr = "",
idStr = "",
styleStr = "";
idStr = id==null?"":"id="+id;
clsStr = cls == null ? "class='coralui-dialog'" : "class='coralui-dialog "+cls+"'";
styleStr = style == null ? "style='display:none;'" : "style='display:none;"+style+"'";
%><div <%=idStr %> <%=clsStr %> <%=styleStr %> data-options="<%=dataOption %>" ><jsp:doBody /></div>
<%@attribute name="id"%><%@attribute name="onMinMaxMenu"%><%@ attribute name="firstIsactive" type="java.lang.Boolean" %><%@attribute name="style"%><%@attribute name="simpleDataIdKey"%><%@attribute name="isactiveField"%><%@attribute name="simpleDataPIdKey"%><%@attribute name="nameField"%><%@ attribute name="autoDisplay" type="java.lang.Boolean" %><%@ attribute name="openFirst" type="java.lang.Boolean" %><%@attribute name="collapseIcon"%><%@attribute name="expandIcon"%><%@ attribute name="collapseButton" type="java.lang.Boolean" %><%@ attribute name="singleOpen" type="java.lang.Boolean" %><%@ attribute name="simpleDataEnable" type="java.lang.Boolean" %><%@ attribute name="minmenu" type="java.lang.Boolean" %><%@ attribute name="responsive" type="java.lang.Boolean" %><%@ attribute name="vertical" type="java.lang.Boolean" %><%@ attribute name="cls" %><%@ attribute name="componentCls" %><%@ attribute name="rendered" type="java.lang.Boolean" %><%@ attribute name="authorized" %><%@attribute name="name"%><%@attribute name="data"%><%@attribute name="disabled"%><%@attribute name="url"%><%@attribute name="method"%><%@attribute name="onclick"%><%@attribute name="onItemClick"%><%@attribute name="onClick"%><%@attribute name="onCreate"%><%@include file="TagUtil.jsp" %><% 
String dataOption = tagUtil.add("disabled", disabled)
.add("id", id)
.add("onMinMaxMenu", onMinMaxMenu)
.add("simpleDataIdKey", simpleDataIdKey)
.add("simpleDataPIdKey", simpleDataPIdKey)
.add("nameField", nameField)
.add("firstIsactive", firstIsactive)
.add("cls", cls)
.add("style", style)
.add("componentCls", componentCls)
.add("isactiveField", isactiveField)
.add("autoDisplay", autoDisplay)
.add("openFirst", openFirst)
.add("collapseIcon", collapseIcon)
.add("expandIcon", expandIcon)
.add("collapseButton", collapseButton)
.add("responsive",responsive)
.add("singleOpen",singleOpen)
.add("simpleDataEnable",simpleDataEnable)
.add("rendered", rendered).add("authorized", authorized)
.add("minmenu",minmenu)
.add("method", method)
.add("url", url)
.add("data", data)
.add("vertical", vertical)
.add("name", name)
.add("onClick", onClick)
.add("onItemClick", onItemClick)
.add("controllerName", request.getAttribute("controllerName"))
.add("onCreate", onCreate).toString();
String clsStr = "",
idStr = "",
styleStr = "";
idStr = id==null?"":"id="+id;
clsStr = cls == null ? "class='coralui-menubar'" : "class='coralui-menubar "+cls+"'";
styleStr = style == null ? "" : "style='"+style+"'";
%><ul <%=idStr %> <%=clsStr %> <%=styleStr %> data-options="<%=dataOption %>"><jsp:doBody /></ul>
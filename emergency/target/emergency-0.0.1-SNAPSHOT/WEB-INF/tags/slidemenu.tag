<%@attribute name="id"%><%@ attribute name="cls" %><%@ attribute name="style" %><%@ attribute name="componentCls" %><%@ attribute name="rendered" type="java.lang.Boolean" %><%@ attribute name="authorized" %><%@attribute name="name"%><%@attribute name="data"%><%@attribute name="disabled"%><%@attribute name="url"%><%@attribute name="trigger"%><%@attribute name="method"%><%@attribute name="popup"%><%@attribute name="onClick"%><%@attribute name="onCreate"%><%@attribute name="onCheck"%><%@include file="TagUtil.jsp" %><% 
String dataOption = tagUtil.add("disabled", disabled)
.add("id", id)
.add("cls", cls)
.add("style", style)
.add("componentCls", componentCls)
.add("rendered", rendered).add("authorized", authorized)
.add("url", url)
.add("id", id)
.add("data", data)
.add("name", name)
.add("trigger", trigger)
.add("popup", popup)
.add("onClick", onClick)
.add("onCheck", onCheck)
.add("controllerName", request.getAttribute("controllerName"))
.add("onCreate", onCreate).toString();
String	clsStr = "",
		idStr = "",
		styleStr = "";
idStr = id==null?"":"id="+id;
clsStr = cls == null ? "class='coralui-slidermenu'" : "class='coralui-slidermenu "+cls+"'";
styleStr = style == null ? "" : "style='"+style+"'";
%><ul <%=idStr %> <%=clsStr %> <%=styleStr %> data-options="<%=dataOption %>">
<jsp:doBody />
</ul>
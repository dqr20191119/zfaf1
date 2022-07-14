<%@attribute name="id"%><%@attribute name="style"%><%@attribute name="name"%><%@ attribute name="cls" %><%@ attribute name="componentCls" %><%@ attribute name="rendered" type="java.lang.Boolean" %><%@ attribute name="authorized" %><%@attribute name="data"%><%@attribute name="disabled"%><%@attribute name="url"%><%@attribute name="target"%><%@attribute name="method"%><%@attribute name="onClick"%><%@attribute name="onCreate"%><%@attribute name="onCheck"%><%@include file="TagUtil.jsp" %><% 
String dataOption = tagUtil.add("disabled", disabled)
.add("id", id)
.add("cls", cls)
.add("style", style)
.add("componentCls", componentCls)
.add("rendered", rendered).add("authorized", authorized)
.add("method", method)
.add("url", url)
.add("id", id)
.add("data", data)
.add("name", name)
.add("target", target)
.add("onCheck", onCheck)
.add("onClick", onClick)
.add("controllerName", request.getAttribute("controllerName"))
.add("onCreate", onCreate).toString();
String clsStr = "",
idStr = "",
styleStr = "";
idStr = id==null?"":"id="+id;
clsStr = cls == null ? "class='coralui-contextmenu'" : "class='coralui-contextmenu "+cls+"'";
styleStr = style == null ? "" : "style='"+style+"'";
%><ul <%=idStr %> <%=clsStr %> <%=styleStr %> data-options="<%=dataOption %>"><jsp:doBody /></ul>
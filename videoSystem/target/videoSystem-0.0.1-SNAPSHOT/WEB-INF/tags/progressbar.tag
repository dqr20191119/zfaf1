<%@attribute name="id"%><%@attribute name="style"%><%@ attribute name="cls" %><%@ attribute name="componentCls" %><%@ attribute name="rendered" type="java.lang.Boolean" %><%@ attribute name="authorized" %><%@attribute name="name"%><%@attribute name="disable" type="java.lang.Boolean"%><%@attribute name="max" type="java.lang.Integer"%><%@attribute name="value" type="java.lang.Integer"%><%@attribute name="onCreate" type="java.lang.String" %><%@attribute name="onChange" type="java.lang.String" %><%@attribute name="onComplete" type="java.lang.String" %><%@include file="TagUtil.jsp" %><% 
String dataOption = tagUtil.add("name", name)
.add("id", id)
.add("cls", cls)
.add("style", style)
.add("componentCls", componentCls)
.add("rendered", rendered).add("authorized", authorized)
.add("disable", disable)
.add("max", max)
.add("value", value)

.add("onCreate", onCreate)
.add("onChange", onChange)
.add("controllerName", request.getAttribute("controllerName"))
.add("onComplete", onComplete).toString();
String clsStr = "",
idStr = "",
styleStr = "";
idStr = id==null?"":"id="+id;
clsStr = cls == null ? "class='coralui-progressbar'" : "class='coralui-progressbar "+cls+"'";
styleStr = style == null ? "" : "style='"+style+"'";
%>
<div <%=idStr %> <%=clsStr %> <%=styleStr %> data-options="<%=dataOption %>" ></div>
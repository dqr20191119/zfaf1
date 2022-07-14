<%@attribute name="id"%><%@ attribute name="cls" %><%@ attribute name="style" %><%@ attribute name="componentCls" %><%@ attribute name="rendered" type="java.lang.Boolean" %><%@ attribute name="authorized" %><%@attribute name="name"%><%@attribute name="active"%><%@attribute name="disabled"%><%@attribute name="collapsible"%><%@attribute name="url"%><%@attribute name="method"%><%@attribute name="onCreate"%><%@attribute name="onCheck"%><%@include file="TagUtil.jsp" %><% 
String dataOption = tagUtil.add("disabled", disabled)
.add("id", id)
.add("cls", cls)
.add("style", style)
.add("componentCls", componentCls)
.add("rendered", rendered).add("authorized", authorized)
.add("method", method)
.add("url", url)
.add("id", id)
.add("ulid", id)
.add("name", name)
.add("onCheck", onCheck)
.add("controllerName", request.getAttribute("controllerName"))
.add("onCreate", onCreate).toString();
if(dataOption.length()>0)
	dataOption = dataOption.substring(0, dataOption.length()-1);
String clsStr = "",
idStr = "",
styleStr = "";
idStr = id==null?"":"id="+id;
clsStr = cls == null ? "class='coralui-menu'" : "class='coralui-menu "+cls+"'";
styleStr = style == null ? "" : "style='"+style+"'";
%><ul <%=idStr %> <%=clsStr %> <%=styleStr %> data-options="<%=dataOption %>"><jsp:doBody /></ul>
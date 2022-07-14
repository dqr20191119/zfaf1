<%@attribute name="id"%><%@attribute name="style"%><%@attribute name="cls"%><%@attribute name="componentCls" %><%@ attribute name="rendered" type="java.lang.Boolean" %><%@ attribute name="authorized" %><%@attribute name="active" type="java.lang.Boolean"%><%@attribute name="icons"%><%@attribute name="name"%><%@attribute name="disabled"%><%@attribute name="collapsible" type="java.lang.Boolean"%><%@attribute name="heightStyle"%><%@attribute name="onActivate"%><%@attribute name="beforeActivate"%><%@include file="TagUtil.jsp" %>
<% 
String dataOption = tagUtil.add("disabled", disabled)
.add("id", id)
.add("cls", cls)
.add("style", style)
.add("componentCls", componentCls)
.add("rendered", rendered).add("authorized", authorized)
.add("icons", icons)
.add("active", active)
.add("name", name)
.add("collapsible", collapsible)
.add("heightStyle", heightStyle)
.add("onActivate", onActivate)
.add("controllerName", request.getAttribute("controllerName"))
.add("beforeActivate", beforeActivate).toString();
String clsStr = "",
	idStr = "",
	styleStr = "";
idStr = id==null?"":"id="+id;
clsStr = cls == null ? "class='coralui-accordion'" : "class='coralui-accordion "+cls+"'";
styleStr = style == null ? "" : "style='"+style+"'";
%><div <%=idStr %> <%=clsStr %> <%=styleStr %> data-options="<%=dataOption %>" ><jsp:doBody /></div>
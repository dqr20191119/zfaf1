<%@attribute name="id"%><%@ attribute name="cls" %><%@ attribute name="componentCls" %><%@ attribute name="rendered" type="java.lang.Boolean" %><%@ attribute name="authorized" %><%@ attribute name="strictLayout" type="java.lang.Boolean" %><%@attribute name="onCreate"%><%@attribute name="onSplitIconClick"%><%@attribute name="name"%><%@attribute name="fit" type="java.lang.Boolean"%><%@attribute name="style"%><%@include file="TagUtil.jsp" %><% 
String dataOption = tagUtil.add("id", id)
.add("cls", cls)
.add("style", style)
.add("componentCls", componentCls)
.add("rendered", rendered).add("authorized", authorized)
.add("strictLayout", strictLayout)
.add("onCreate", onCreate)
.add("name", name)
.add("onSplitIconClick", onSplitIconClick)
.add("controllerName", request.getAttribute("controllerName"))
.add("fit", fit).toString();
String clsStr = "",
idStr = "",
styleStr = "";
idStr = id==null?"":"id="+id;
clsStr = cls == null ? "class='coralui-layout'" : "class='coralui-layout "+cls+"'";
styleStr = style == null ? "" : "style='"+style+"'";
%><div <%=idStr %> <%=clsStr %> <%=styleStr %> data-options="<%=dataOption %>"><jsp:doBody /></div>
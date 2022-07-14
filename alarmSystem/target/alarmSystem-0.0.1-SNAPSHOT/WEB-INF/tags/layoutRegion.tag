<%@attribute name="region"%><%@attribute name="split" type="java.lang.Boolean"%><%@attribute name="showSplitIcon" type="java.lang.Boolean"%><%@attribute name="collapsedSize" type="java.lang.Integer"%><%@attribute name="onLoad"%><%@attribute name="collapsed" type="java.lang.Boolean"%><%@attribute name="title"%><%@attribute name="style"%><%@attribute name="minWidth" type="java.lang.Integer"%><%@attribute name="maxWidth" type="java.lang.Integer"%><%@attribute name="minHeight" type="java.lang.Integer"%><%@attribute name="maxHeight" type="java.lang.Integer"%><%@attribute name="cls"%><%@attribute name="onResize"%><%@attribute name="beforeCollapse"%><%@attribute name="url"%><%@attribute name="beforeExpand"%><%@include file="TagUtil.jsp" %>
<% 
String dataOption = tagUtil.add("region", region)
.add("split", split)
.add("showSplitIcon", showSplitIcon)
.add("collapsedSize", collapsedSize)
.add("url", url)
.add("collapsed", collapsed)
.add("title", title)
.add("minWidth", minWidth)
.add("maxWidth", maxWidth)
.add("minHeight", minHeight)
.add("maxHeight", maxHeight)
.add("onResize", onResize)
.add("onLoad", onLoad)
.add("beforeCollapse", beforeCollapse)
.add("controllerName", request.getAttribute("controllerName"))
.add("beforeExpand", beforeExpand).toString();
String styleStr = "";
styleStr = style == null ? "" : "style='"+style+"'";
%>
<div <%=styleStr %> data-options="<%=dataOption %>"><jsp:doBody /></div>


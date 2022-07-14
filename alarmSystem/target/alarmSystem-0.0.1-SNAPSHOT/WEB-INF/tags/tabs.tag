<%@attribute name="id"%><%@ attribute name="cls" %><%@ attribute name="style" %><%@ attribute name="componentCls" %><%@ attribute name="rendered" type="java.lang.Boolean" %><%@ attribute name="authorized" %><%@ attribute name="active" type="java.lang.Integer"%><%@attribute name="loadonce" type="java.lang.Boolean"%><%@attribute name="name"%><%@attribute name="renderPanelOnActivate" type="java.lang.Boolean"%><%@attribute name="destroyAllonActive" type="java.lang.Boolean"%><%@attribute name="disabled" type="java.lang.Boolean"%><%@attribute name="collapsible" type="java.lang.Boolean"%><%@attribute name="url"%><%@attribute name="method"%><%@attribute name="newbtn" type="java.lang.Boolean"%><%@attribute name="overflowContent" type="java.lang.Boolean"%><%@attribute name="heightStyle"%><%@attribute name="onActivate"%><%@attribute name="beforeActivate"%><%@attribute name="beforeLoad"%><%@attribute name="onCreate"%><%@attribute name="onLoad"%><%@attribute name="onTabNew"%><%@attribute name="beforeTabClose"%><%@attribute name="onTabClose"%><%@include file="TagUtil.jsp" %><%
String dataOption = tagUtil.add("collapsible", collapsible)
.add("id", id)
.add("cls", cls)
.add("style", style)
.add("componentCls", componentCls)
.add("rendered", rendered).add("authorized", authorized)
.add("active", active)
.add("loadonce", loadonce)
.add("renderPanelOnActivate", renderPanelOnActivate)
.add("destroyAllonActive", destroyAllonActive)
.add("disabled", disabled)
.add("method", method)
.add("newbtn", newbtn)
.add("overflowContent", overflowContent)
.add("disabled", disabled)
.add("heightStyle", heightStyle)
.add("id", id)
.add("url", url)
.add("name", name)
.add("onActivate", onActivate)
.add("beforeActivate", beforeActivate)
.add("beforeLoad", beforeLoad)
.add("onCreate", onCreate)
.add("onTabNew", onTabNew)
.add("onLoad", onLoad)
.add("beforeTabClose", beforeTabClose)
.add("controllerName", request.getAttribute("controllerName"))
.add("onTabClose", onTabClose).toString();
String	clsStr = "",
		idStr = "",
		styleStr = "";
idStr = id==null?"":"id="+id;
clsStr = cls == null ? "class='coralui-tabs'" : "class='coralui-tabs "+cls+"'";
styleStr = style == null ? "" : "style='"+style+"'";
%><div <%=idStr %> <%=clsStr %> <%=styleStr %> data-options="<%=dataOption %>">
<jsp:doBody />
</div>

<%@ attribute name="id" %><%@ attribute name="style" %><%@ attribute name="name" %><%@ attribute name="controllerName" %><%@ attribute name="cls" %><%@ attribute name="componentCls" %><%@ attribute name="rendered" type="java.lang.Boolean" %><%@ attribute name="authorized" %><%@ attribute name="onCreate" type="java.lang.String" %><%@ attribute name="onClick" type="java.lang.String" %><%@ include file="TagUtil.jsp" %><% 
String dataOption = tagUtil.add("name", name)
.add("id", id)
.add("controllerName", controllerName)
.add("componentCls", componentCls)
.add("style", style)
.add("rendered", rendered).add("authorized", authorized)
.add("onCreate", onCreate).toString();
request.setAttribute("controllerName", controllerName);
String	clsStr = "",
		idStr = "",
		styleStr = "";
idStr = id==null?"":"id="+id;
clsStr = cls == null ? "class='coralui-view'" : "class='coralui-view "+cls+"'";
styleStr = style == null ? "" : "style='"+style+"'";
%><div <%=idStr %> <%=clsStr %> <%=styleStr %> data-options="<%=dataOption %>" ><jsp:doBody /></div>

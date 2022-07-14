<%@ attribute name="id" %><%@ attribute name="cls" %><%@ attribute name="style" %><%@ attribute name="componentCls" %><%@ attribute name="minWidth" %><%@ attribute name="onDetailNavigate" %>
<%@ include file="TagUtil.jsp" %><% 
String dataOption = tagUtil
.add("id", id)
.add("cls",cls)
.add("style",style)
.add("minWidth",minWidth)
.add("componentCls",componentCls)
.add("controllerName", request.getAttribute("controllerName"))
.add("onDetailNavigate", onDetailNavigate).toString();
String	clsStr = "",
		idStr = "",
		styleStr = "";
idStr = id==null?"":"id="+id;
clsStr = cls == null ? "class='coralui-splitcontainer'" : "class='coralui-splitcontainer "+cls+"'";
styleStr = style == null ? "" : "style='"+style+"'";
%><div <%=idStr %> <%=clsStr %> <%=styleStr %>  data-options="<%=dataOption %>"><jsp:doBody /></div>

<%@attribute name="id"%><%@attribute name="cls"%><%@attribute name="style"%><%@attribute name="componentCls"%><%@include file="TagUtil.jsp" %>
<% 
String dataOption = tagUtil.add("id", id)
.add("cls",cls)
.add("style",style)
.add("componentCls",componentCls).toString();
String	clsStr = "",
		idStr = "",
		styleStr = "";
idStr = id==null?"":"id="+id;
clsStr = cls == null ? "class='coralui-splitDetail'" : "class='coralui-splitDetail "+cls+"'";
styleStr = style == null ? "" : "style='"+style+"'";
%>
<div <%=idStr %> <%=clsStr %> <%=styleStr %> data-options="<%=dataOption %>" ><jsp:doBody /></div>
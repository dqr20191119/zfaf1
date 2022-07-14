<%@attribute name="title"%><%@attribute name="id"%><%@attribute name="cls"%><%@attribute name="componentCls"%><%@attribute name="style"%><%@attribute name="showNavButton" type="java.lang.Boolean"%><%@include file="TagUtil.jsp" %>
<% 
String dataOption = tagUtil.add("title", title)
.add("id",id)
.add("cls",cls)
.add("componentCls", componentCls)
.add("style",style)
.add("showNavButton",showNavButton).toString();
String	clsStr = "",
		idStr = "",
		styleStr = "";
idStr = id==null?"":"id="+id;
clsStr = cls == null ? "class='coralui-splitPanel'" : "class='coralui-splitPanel "+cls+"'";
styleStr = style == null ? "" : "style='"+style+"'";
%><div <%=idStr %> <%=clsStr %> <%=styleStr %> data-options="<%=dataOption %>" ><jsp:doBody /></div>
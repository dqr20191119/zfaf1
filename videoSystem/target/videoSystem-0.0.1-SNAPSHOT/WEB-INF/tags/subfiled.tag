<%@attribute name="id"%><%@ attribute name="cls" %><%@ attribute name="style" %><%@ attribute name="componentCls" %><%@ attribute name="rendered" type="java.lang.Boolean" %><%@ attribute name="authorized" %><%@attribute name="title" required="true"%><%@attribute name="lineCls"%><%@attribute name="textCls"%><%@include file="TagUtil.jsp"%>
<% 
String dataOption = tagUtil.add("title", title)
.add("id", id)
.add("cls", cls)
.add("style", style)
.add("componentCls", componentCls)
.add("rendered", rendered).add("authorized", authorized)
.add("lineCls", lineCls)
.add("controllerName", request.getAttribute("controllerName"))
.add("textCls", textCls).toString();
System.out.println(dataOption);
String	clsStr = "",
		idStr = "",
		styleStr = "";
idStr = id==null?"":"id="+id;
clsStr = cls == null ? "class='coralui-subfiled'" : "class='coralui-subfiled "+cls+"'";
styleStr = style == null ? "" : "style='"+style+"'";
%><div <%=idStr %> <%=clsStr %> <%=styleStr %> data-options="<%=dataOption %>"></div>

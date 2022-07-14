<%@attribute name="id"%><%@ attribute name="style" %><%@ attribute name="cls" %><%@ attribute name="componentCls" %><%@ attribute name="rendered" type="java.lang.Boolean" %><%@ attribute name="authorized" %><%@attribute name="name"%><%@attribute name="data"%><%@attribute name="disabled"%><%@attribute name="url"%><%@attribute name="method"%><%@attribute name="onClick"%><%@attribute name="checkable"%><%@attribute name="fixmenu"%><%@attribute name="onCreate"%><%@variable name-given="menu_checkable" scope="NESTED"%><%@variable name-given="menu_id" scope="NESTED"%><%@include file="TagUtil.jsp" %><%
jspContext.setAttribute("menu_id",id);
jspContext.setAttribute("menu_checkable",checkable);

String dataOption = tagUtil.add("disabled", disabled)
.add("id", id)
.add("style", style)
.add("cls", cls)
.add("componentCls", componentCls)
.add("rendered", rendered).add("authorized", authorized)
.add("method", method)
.add("url", url)
.add("id", id)
.add("data", data)
.add("name", name)
.add("onClick", onClick)
.add("checkable", checkable)
.add("fixmenu", fixmenu)
.add("controllerName", request.getAttribute("controllerName"))
.add("onCreate", onCreate).toString();
String clsStr = "",
idStr = "",
styleStr = "";
idStr = id==null?"":"id="+id;
clsStr = cls == null ? "class='coralui-navigatemenu'" : "class='coralui-navigatemenu "+cls+"'";
styleStr = style == null ? "" : "style='"+style+"'";
%><ul <%=idStr %> <%=clsStr %> <%=styleStr %> data-options="<%=dataOption %>"><jsp:doBody /></ul>
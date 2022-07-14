<%@ tag language="java" pageEncoding="UTF-8"%><%@ attribute name="id" %><%@ attribute name="cls" %><%@ attribute name="componentCls" %><%@ attribute name="style" %><%@ attribute name="rendered" type="java.lang.Boolean" %><%@ attribute name="authorized" %><%@ attribute name="label" %><%@ attribute name="width" type="java.lang.Integer" %><%@ attribute name="text" type="java.lang.Boolean" %><%@ attribute name="disabled" type="java.lang.Boolean" %><%@ attribute name="icons"  %><%@ attribute name="treeOptions" %><%@ attribute name="onCreate" %><%@ attribute name="onClick" %><%@ attribute name="onSelect" %><%@ attribute name="onCheck" %><%@ include file="TagUtil.jsp" %><% 
String dataOption = tagUtil.add("id", id)
.add("cls", cls)
.add("componentCls", componentCls)
.add("style", style)
.add("rendered", rendered).add("authorized", authorized)
.add("treeOptions", treeOptions)
.add("label", label)
.add("width", width)
.add("disabled", disabled)
.add("text", text)
.add("icons", icons)
.add("onCreate", onCreate)
.add("controllerName", request.getAttribute("controllerName"))
.add("onClick", onClick).toString();
String	clsStr = "",
		idStr = "",
		styleStr = "";
idStr = id==null?"":"id="+id;
clsStr = cls == null ? "class='coralui-treebutton'" : "class='coralui-treebutton "+cls+"'";
styleStr = style == null ? "" : "style='"+style+"'";
%><button <%=idStr %> <%=clsStr %> <%=styleStr %> data-options="<%=dataOption %>" type="button" ><jsp:doBody /></button>

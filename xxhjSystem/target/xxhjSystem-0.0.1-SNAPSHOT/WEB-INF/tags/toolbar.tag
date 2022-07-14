<%@ tag language="java" pageEncoding="UTF-8"%><%@ attribute name="clickToDisplay" %><%@ attribute name="align" %><%@ attribute name="title" type="java.lang.Boolean"%><%@ attribute name="isOverflow" type="java.lang.Boolean"%><%@ attribute name="disabled" type="java.lang.Boolean"%><%@ attribute name="id" %><%@ attribute name="dataCustom" %><%@ attribute name="style" %><%@ attribute name="name" %><%@ attribute name="cls" %><%@ attribute name="componentCls" %><%@ attribute name="autoDisplay" type="java.lang.Boolean" %><%@ attribute name="rendered" type="java.lang.Boolean" %><%@ attribute name="authorized" %><%@ attribute name="url" %><%@ attribute name="data" %><%@ attribute name="width" %><%@ attribute name="height" %><%@ attribute name="onCreate" %><%@ attribute name="onclick" %><%@ attribute name="dropdownOptions" %><%@ attribute name="onClick" %><%@ include file="TagUtil.jsp" %><%
String dataOption = tagUtil.add("id", id)
.add("name", name)
.add("isOverflow", isOverflow)
.add("disabled", disabled)
.add("autoDisplay", autoDisplay)
.add("dropdownOptions", dropdownOptions)
.add("style", style)
.add("title",title)
.add("cls", cls)
.add("componentCls", componentCls)
.add("rendered", rendered).add("authorized", authorized)
.add("url", url)
.add("dataCustom", dataCustom)
.add("data", data)		
.add("width", width)
.add("height", height)
.add("align", align)
.add("clickToDisplay", clickToDisplay)
.add("onCreate", onCreate)
.add("controllerName", request.getAttribute("controllerName"))
.add("onClick", onClick).toString();
String	clsStr = "",
		idStr = "",
		styleStr = "";
idStr = id==null?"":"id="+id;
clsStr = cls == null ? "class='coralui-toolbar'" : "class='coralui-toolbar "+cls+"'";
styleStr = style == null ? "" : "style='"+style+"'";
%><div <%=idStr %> <%=clsStr %> <%=styleStr %> data-options="<%=dataOption %>"><jsp:doBody /></div>

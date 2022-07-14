<%@attribute name="id"%><%@ attribute name="style" %><%@ attribute name="labelField" %><%@ attribute name="starBefore" type="java.lang.Boolean" %><%@attribute name="maxLabelWidth"%><%@attribute name="name"%><%@ attribute name="cls" %><%@ attribute name="componentCls" %><%@ attribute name="rendered" type="java.lang.Boolean" %><%@ attribute name="authorized" %><%@ attribute name="onKeyDown" %><%@attribute name="repeatLayout" %><%@attribute name="itemWidth" %><%@attribute name="valueField" %><%@attribute name="textField" %><%@attribute name="required" type="java.lang.Boolean"%><%@ attribute name="showStar" type="java.lang.Boolean"%><%@ attribute name="showRequiredMark"%><%@ attribute name="hideRequiredMark"%><%@attribute name="disabled" type="java.lang.Boolean"%><%@attribute name="readonly" type="java.lang.Boolean"%><%@attribute name="value" %><%@attribute name="data" %><%@attribute name="column" type="java.lang.Integer" %><%@attribute name="url"%><%@attribute name="shortCut"%><%@attribute name="termSplit" %><%@attribute name="itemSplit" %><%@ attribute name="errMsg" %><%@ attribute name="errMsgPosition" %><%@ attribute name="onValidError" %><%@ attribute name="onLoad" %><%@ attribute name="onValidSuccess" %><%@attribute name="onChange" type="java.lang.String" %><%@ attribute name="triggers"%><%@ attribute name="excluded" type="java.lang.Boolean"%><%@include file="TagUtil.jsp" %><% 
String dataOption = tagUtil.add("name", name)
.add("id", id)
.add("style", style)
.add("labelField", labelField)
.add("starBefore", starBefore)
.add("cls", cls)
.add("maxLabelWidth",maxLabelWidth)
.add("triggers", triggers)
.add("excluded", excluded)
.add("componentCls", componentCls)
.add("rendered", rendered).add("authorized", authorized)
.add("valueField", valueField)
.add("textField", textField)
.add("value", value)
.add("required", required)
.add("showStar", showStar).add("showRequiredMark", showRequiredMark).add("hideRequiredMark", hideRequiredMark)
.add("disabled", disabled)
.add("readonly", readonly)
.add("data", data)
.add("column", column)
.add("url", url)
.add("shortCut", shortCut)
.add("termSplit", termSplit)
.add("itemSplit", itemSplit)
.add("errMsg", errMsg)
.add("errMsgPosition", errMsgPosition)
.add("repeatLayout", repeatLayout)
.add("itemWidth", itemWidth)
.add("onLoad", onLoad)
.add("onValidSuccess", onValidSuccess)
.add("onKeyDown", onKeyDown)
.add("onValidError", onValidError)
.add("controllerName", request.getAttribute("controllerName"))
.add("onChange", onChange).toString();
String clsStr = "",
idStr = "",
styleStr = "";
idStr = id==null?"":"id="+id;
clsStr = cls == null ? "class='coralui-checkboxlist'" : "class='coralui-checkboxlist "+cls+"'";
styleStr = style == null ? "" : "style='"+style+"'";
%><div <%=idStr %> <%=clsStr %> <%=styleStr %> data-options="<%=dataOption %>"></div>
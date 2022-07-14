<%@attribute name="id"%><%@ attribute name="labelField" %><%@ attribute name="starBefore" type="java.lang.Boolean" %><%@ attribute name="cls" %><%@ attribute name="style" %><%@ attribute name="componentCls" %><%@ attribute name="rendered" type="java.lang.Boolean" %><%@ attribute name="authorized" %><%@ attribute name="allowCancel" type="java.lang.Boolean" %><%@attribute name="onLoad" %><%@ attribute name="onKeyDown" %><%@attribute name="name"%><%@attribute name="repeatLayout" %><%@attribute name="itemWidth" %><%@attribute name="valueField" %><%@attribute name="textField" %><%@attribute name="required" type="java.lang.Boolean"%><%@ attribute name="showStar" type="java.lang.Boolean"%><%@ attribute name="showRequiredMark"%><%@ attribute name="hideRequiredMark"%><%@attribute name="disabled" type="java.lang.Boolean"%><%@attribute name="readonly" type="java.lang.Boolean"%><%@attribute name="value" %><%@attribute name="data" %><%@attribute name="column" type="java.lang.Integer" %><%@attribute name="url"%><%@attribute name="shortCut"%><%@attribute name="termSplit" %><%@attribute name="itemSplit" %><%@ attribute name="errMsg" %><%@ attribute name="errMsgPosition" %><%@ attribute name="onValidError" %><%@ attribute name="onCreate" %><%@ attribute name="onValidSuccess" %><%@attribute name="onChange" type="java.lang.String" %><%@ attribute name="triggers"%><%@ attribute name="excluded" type="java.lang.Boolean"%><%@include file="TagUtil.jsp" %><% 
String dataOption = tagUtil.add("name", name)
.add("id", id)
.add("cls", cls)
.add("style", style)
.add("triggers", triggers)
.add("excluded", excluded)
.add("labelField", labelField)
.add("starBefore", starBefore)
.add("componentCls", componentCls)
.add("rendered", rendered).add("authorized", authorized)
.add("allowCancel", allowCancel)
.add("valueField", valueField)
.add("textField", textField)
.add("value", value)
.add("onLoad", onLoad)
.add("column", column)
.add("required", required)
.add("showStar", showStar).add("showRequiredMark", showRequiredMark).add("hideRequiredMark", hideRequiredMark)
.add("disabled", disabled)
.add("readonly", readonly)
.add("data", data)
.add("url", url)
.add("shortCut", shortCut)
.add("termSplit", termSplit)
.add("itemSplit", itemSplit)
.add("errMsg", errMsg)
.add("errMsgPosition", errMsgPosition)
.add("repeatLayout", repeatLayout)
.add("itemWidth", itemWidth)

.add("onCreate", onCreate)
.add("onValidSuccess", onValidSuccess)
.add("onKeyDown", onKeyDown)
.add("onValidError", onValidError)
.add("controllerName", request.getAttribute("controllerName"))
.add("onChange", onChange).toString();
String	clsStr = "",
		idStr = "",
		styleStr = "";
idStr = id==null?"":"id="+id;
clsStr = cls == null ? "class='coralui-radiolist'" : "class='coralui-radiolist "+cls+"'";
styleStr = style == null ? "" : "style='"+style+"'";
%><div <%=idStr %> <%=clsStr %> <%=styleStr %> data-options="<%=dataOption %>"></div>

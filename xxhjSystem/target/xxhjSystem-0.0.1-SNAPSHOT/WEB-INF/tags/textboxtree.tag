<%@ tag language="java" pageEncoding="UTF-8"%><%@ attribute name="cls" %><%@ attribute name="style" %><%@ attribute name="componentCls" %><%@ attribute name="rendered" type="java.lang.Boolean" %><%@ attribute name="authorized" %><%@ attribute name="requiredMsg"%><%@ attribute name="valid"%><%@ attribute name="validType"%><%@ attribute name="readonly" type="java.lang.Boolean"%><%@ attribute name="isLabel" type="java.lang.Boolean"%><%@ attribute name="defaultValue"%><%@ attribute name="onValidSuccess"%><%@ attribute name="onValidError"%><%@ attribute name="postMode"%><%@ attribute name="errMsg" %><%@ attribute name="errMsgPosition" %><%@ attribute name="required" type="java.lang.Boolean"%><%@ attribute name="showStar" type="java.lang.Boolean"%><%@ attribute name="showRequiredMark"%><%@ attribute name="hideRequiredMark"%><%@ attribute name="id" %><%@ attribute name="name" %><%@ attribute name="value" %><%@ attribute name="text" %><%@ attribute name="appendTo" %><%@ attribute name="delay" type="java.lang.Integer" %><%@ attribute name="autoFocus" type="java.lang.Boolean" %><%@ attribute name="minLength" type="java.lang.Integer" %><%@ attribute name="source" %><%@ attribute name="valueField" %><%@ attribute name="textField" %><%@ attribute name="searchField" %><%@ attribute name="buttons" %><%@ attribute name="onBlur" %><%@ attribute name="onChange" %><%@ attribute name="onClose" %><%@ attribute name="onFocus" %><%@ attribute name="onOpen" %><%@ attribute name="onResponse" %><%@ attribute name="onSearch" %><%@ attribute name="onNodeClick" %><%@ include file="TagUtil.jsp" %><% 	
String dataOption = tagUtil.add("id", id)
.add("name", name)
.add("cls", cls)
.add("style", style)
.add("componentCls", componentCls)
.add("rendered", rendered).add("authorized", authorized)
.add("postMode", postMode)
.add("requiredMsg", requiredMsg)
.add("valid", valid)
.add("validType", validType)
.add("value", value)
.add("text", text)
.add("isLabel", isLabel)
.add("readonly", readonly)
.add("defaultValue", defaultValue)
.add("required", required)
.add("showStar", showStar).add("showRequiredMark", showRequiredMark).add("hideRequiredMark", hideRequiredMark)
.add("errMsg", errMsg)
.add("errMsgPosition", errMsgPosition)
.add("appendTo", appendTo)
.add("delay", delay)
.add("autoFocus", autoFocus)
.add("minLength", minLength)
.add("source", source)
.add("valueField", valueField)
.add("textField", textField)
.add("searchField", searchField)
.add("buttons", buttons)
.add("onValidError", onValidError)
.add("onValidSuccess", onValidSuccess)
.add("onChange", onChange)
.add("onBlur", onBlur)
.add("onClose", onClose)
.add("onFocus", onFocus)
.add("onOpen", onOpen)
.add("onResponse", onResponse)
.add("onSearch", onSearch)
.add("controllerName", request.getAttribute("controllerName"))
.add("onNodeClick", onNodeClick).toString();
String	clsStr = "",
		idStr = "",
		styleStr = "";
idStr = id==null?"":"id="+id;
clsStr = cls == null ? "class='coralui-textboxtree'" : "class='coralui-textboxtree "+cls+"'";
styleStr = style == null ? "" : "style='"+style+"'";
%><input <%=idStr %> <%=clsStr %> <%=styleStr %> data-options="<%=dataOption %>"/>

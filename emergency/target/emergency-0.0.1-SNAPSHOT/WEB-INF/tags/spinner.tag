<%@ tag language="java" pageEncoding="UTF-8"%><%@ attribute name="id" %><%@ attribute name="cls" %><%@ attribute name="style" %><%@ attribute name="componentCls" %><%@ attribute name="rendered" type="java.lang.Boolean" %><%@ attribute name="authorized" %><%@ attribute name="name" %><%@ attribute name="value" %><%@ attribute name="max" %><%@ attribute name="min" %><%@ attribute name="step" %><%@ attribute name="disabled" type="java.lang.Boolean" %><%@ attribute name="pattern" %><%@ attribute name="maxlength" type="java.lang.Integer"%><%@ attribute name="required" type="java.lang.Boolean"%><%@ attribute name="minlength" type="java.lang.Integer"%><%@ attribute name="errMsg" %><%@ attribute name="valid" %><%@ attribute name="validType" %><%@ attribute name="onCreate" %><%@ attribute name="onChange" %><%@ attribute name="onStart" %><%@ attribute name="onStop" %><%@ attribute name="triggers"%><%@ attribute name="excluded" type="java.lang.Boolean"%><%@ include file="TagUtil.jsp" %><% 
id = tagUtil.getClientId( id );
String dataOption = tagUtil.add("id", id)
.add("name", name)
.add("cls", cls)
.add("cls", style)
.add("triggers", triggers)
.add("excluded", excluded)
.add("componentCls", componentCls)
.add("rendered", rendered).add("authorized", authorized)
.add("value", value)
.add("max", max)
.add("min", min)
.add("step", step)
.add("disabled", disabled)
.add("required", required)
.add("pattern", pattern)
.add("valid", valid)
.add("validType", validType)
.add("errMsg", errMsg)
.add("maxlength", maxlength)
.add("minlength", minlength)

.add("onCreate", onCreate)
.add("onChange", onChange)
.add("onStart", onStart)
.add("controllerName", request.getAttribute("controllerName"))
.add("onStop", onStop).toString();
String	clsStr = "",
		idStr = "",
		styleStr = "";
idStr = id==null?"":"id="+id;
clsStr = cls == null ? "class='coralui-spinner'" : "class='coralui-spinner "+cls+"'";
styleStr = style == null ? "" : "style='"+style+"'";
 %><input type="text" <%=idStr %> <%=clsStr %> <%=styleStr %> data-options="<%=dataOption %>"/>

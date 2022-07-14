<%@ attribute name="id" %><%@ attribute name="title" type="java.lang.Boolean" %><%@ attribute name="style" %><%@ attribute name="dataCustom" %><%@ attribute name="shortCut" %><%@ attribute name="name" %><%@ attribute name="cls" %><%@ attribute name="componentCls" %><%@ attribute name="rendered" type="java.lang.Boolean" %><%@ attribute name="authorized" %><%@ attribute name="label" %><%@ attribute name="once" type="java.lang.Boolean" %><%@ attribute name="text" type="java.lang.Boolean" %><%@ attribute name="disabled" type="java.lang.Boolean" %><%@ attribute name="showCountdown" type="java.lang.Boolean" %><%@ attribute name="countdown" type="java.lang.Boolean" %><%@ attribute name="icons" %><%@ attribute name="type" %><%@ attribute name="countdownTime" %><%@ attribute name="width" type="java.lang.Integer" %><%@ attribute name="onCreate" type="java.lang.String" %><%@ attribute name="onClick" type="java.lang.String" %><%@ attribute name="onDblClick" type="java.lang.String" %><%@ attribute name="onMouseEnter" %><%@ attribute name="onMouseLeave" %><%@ include file="TagUtil.jsp" %><% 
String dataOption = tagUtil.add("name", name)
.add("id", id)
.add("cls", cls)
.add("style", style)
.add("title", title)
.add("componentCls", componentCls)
.add("rendered", rendered).add("authorized", authorized)
.add("label", label)
.add("width", width)
.add("once", once)
.add("text", text)
.add("disabled", disabled)
.add("showCountdown", showCountdown)
.add("countdown", countdown)
.add("dataCustom", dataCustom)
.add("countdownTime", countdownTime)
.add("icons", icons)
.add("shortCut", shortCut)
.add("type", type)
.add("onCreate", onCreate)
.add("onClick", onClick)
.add("onMouseEnter", onMouseEnter)
.add("onMouseLeave", onMouseLeave)
.add("controllerName", request.getAttribute("controllerName"))
.add("onDblClick", onDblClick).toString();
String clsStr = "",
	typeStr = "",
	idStr = "",
	styleStr = "";
typeStr = type==null?"type='button'":"type="+type;
idStr = id==null?"":"id="+id;
clsStr = cls == null ? "class='coralui-button'" : "class='coralui-button "+cls+"'";
styleStr = style == null ? "" : "style='"+style+"'";
%><button <%=typeStr %> <%=idStr %> <%=clsStr %> <%=styleStr %> data-options="<%=dataOption %>"></button>

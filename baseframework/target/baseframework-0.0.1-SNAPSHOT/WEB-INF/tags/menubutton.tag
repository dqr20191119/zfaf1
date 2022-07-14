<%@ attribute name="id" %><%@ attribute name="style" %><%@ attribute name="cls" %><%@ attribute name="componentCls" %><%@ attribute name="rendered" type="java.lang.Boolean" %><%@ attribute name="authorized" %><%@ attribute name="name" %><%@ attribute name="label" %><%@ attribute name="width" type="java.lang.Integer" %><%@ attribute name="text" type="java.lang.Boolean" %><%@ attribute name="disabled" type="java.lang.Boolean" %><%@ attribute name="icons"  %><%@ attribute name="menuOptions" %><%@ attribute name="onCreate" %><%@ attribute name="onClick" %><%@ attribute name="onDblClick" %><%@ include file="TagUtil.jsp" %><% 
String dataOption = tagUtil.add("id", id)
.add("name", name)
.add("cls", cls)
.add("style", style)
.add("componentCls", componentCls)
.add("rendered", rendered).add("authorized", authorized)
.add("menuOptions", menuOptions)
.add("label", label)
.add("width", width)
.add("disabled", disabled)
.add("text", text)
.add("icons", icons)

.add("onCreate", onCreate)
.add("onClick", onClick)
.add("controllerName", request.getAttribute("controllerName"))
.add("onDblClick", onDblClick).toString();
String clsStr = "",
idStr = "",
styleStr = "";
idStr = id==null?"":"id="+id;
clsStr = cls == null ? "class='coralui-menubutton'" : "class='coralui-menubutton "+cls+"'";
styleStr = style == null ? "" : "style='"+style+"'";
 %><button type="button" <%=idStr %> <%=clsStr %> <%=styleStr %> <%=(name == null ? "" : "name="+name) %> data-options="<%=dataOption %>"></button>
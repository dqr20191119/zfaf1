<%@attribute name="id"%><%@attribute name="disabledMessage"%><%@attribute name="keyName"%><%@attribute name="url"%><%@attribute name="fontCss"%><%@ attribute name="cls" %><%@ attribute name="style" %><%@ attribute name="clickExpand" type="java.lang.Boolean"%><%@attribute name="disabled" type="java.lang.Boolean"%><%@attribute name="showRootNode" type="java.lang.Boolean"%><%@ attribute name="componentCls" %><%@ attribute name="rendered" type="java.lang.Boolean" %><%@ attribute name="authorized" %><%@attribute name="data"%><%@attribute name="rootNode" type="java.lang.Object"%><%@attribute name="simpleDataEnable"  type="java.lang.Boolean"%><%@attribute name="simpleDataIdKey"%><%@attribute name="simpleDataPIdKey"%><%@attribute name="asyncType"%><%@attribute name="asyncUrl"%><%@attribute name="asyncAutoParam"%><%@attribute name="checkable" type="java.lang.Boolean"%><%@attribute name="chkStyle"%><%@attribute name="editable" type="java.lang.Boolean"%><%@attribute name="showRemoveBtn" type="java.lang.Boolean"%><%@attribute name="showRenameBtn" type="java.lang.Boolean"%><%@attribute name="asyncEnable" type="java.lang.Boolean"%><%@attribute name="showLine" type="java.lang.Boolean"%><%@attribute name="showIcon"%><%@attribute name="nameIsHTML" type="java.lang.Boolean"%><%@attribute name="formatter"%><%@attribute name="keepParent" type="java.lang.Boolean"%><%@attribute name="addDiyDom" %><%@attribute name="selectedMulti" type="java.lang.Boolean"%><%@attribute name="dblClickExpand" type="java.lang.Boolean"%><%@attribute name="chkboxType" %><%@attribute name="radioType" %><%@attribute name="onClick"%><%@attribute name="beforeClick"%><%@attribute name="onDblClick"%><%@attribute name="onRightClick"%><%@attribute name="beforeCheck"%><%@attribute name="onCheck"%><%@attribute name="onExpand"%><%@attribute name="beforeExpand"%><%@attribute name="onCollapse"%><%@attribute name="onDrag"%><%@attribute name="onDrop"%><%@attribute name="beforeDrag"%><%@attribute name="beforeDrop"%><%@attribute name="onReName"%><%@attribute name="onLoad"%><%@attribute name="onLoadError"%><%@include file="TagUtil.jsp" %><% 
String dataOption = "";
String setting,viewoptions,checkoptions,asyncoptions,callbackoptions,editoptions = "";
dataOption += id==null?"":"id:'"+id+"',";
dataOption += data==null?"":tagUtil.clear().add("data", data)+",";
viewoptions = tagUtil.clear()
.add("id", id)
.add("url", url)
.add("disabledMessage", disabledMessage)
.add("style", style)
.add("cls", cls)
.add("keyName", keyName)
.add("disabled",disabled)
.add("fontCss",fontCss)
.add("componentCls", componentCls)
.add("rendered", rendered).add("authorized", authorized)
.add("showRootNode", showRootNode)
.add("rootNode", rootNode)
.add("simpleDataEnable", simpleDataEnable)
.add("simpleDataIdKey", simpleDataIdKey)
.add("simpleDataPIdKey", simpleDataPIdKey)
.add("showLine", showLine)
.add("showIcon", showIcon)
.add("nameIsHTML", nameIsHTML)
.add("formatter",formatter)
.add("keepParent", keepParent)
.add("addDiyDom", addDiyDom)
.add("selectedMulti", selectedMulti)
.add("clickExpand", clickExpand)
.add("controllerName", request.getAttribute("controllerName"))
.add("dblClickExpand", dblClickExpand).toString();
if(viewoptions.length()>0)
	viewoptions = viewoptions+",";

checkoptions = tagUtil.clear()
	.add("checkable", checkable)
	.add("chkStyle", chkStyle)
	.add("chkboxType", chkboxType)
	.add("radioType", radioType).toString();
if(checkoptions.length()>0)
	checkoptions = checkoptions + ",";

asyncoptions = tagUtil.clear()
	.add("asyncEnable", asyncEnable)
	.add("asyncType", asyncType)
	.add("asyncUrl", asyncUrl)
	.add("asyncAutoParam", asyncAutoParam).toString();
if(asyncoptions.length()>0)
	asyncoptions = asyncoptions+",";

editoptions = tagUtil.clear()
					.add("editable", editable)
					.add("showRemoveBtn", showRemoveBtn)
					.add("showRenameBtn", showRenameBtn)
					.toString();
if(editoptions.length()>0)
	editoptions = editoptions + ",";

callbackoptions = tagUtil.clear()
.add("onClick", onClick)
.add("beforeClick", beforeClick)
.add("onDblClick", onDblClick)
.add("onRightClick", onRightClick)
.add("beforeCheck", beforeCheck)
.add("onCheck", onCheck)
.add("onExpand", onExpand)
.add("beforeExpand", beforeExpand)
.add("onCollapse", onCollapse)
.add("onDrag", onDrag)
.add("onDrop", onDrop)
.add("beforeDrag", beforeDrag)
.add("beforeDrop", beforeDrop)
.add("onReName", onReName)

.add("onLoad", onLoad)
.add("onLoadError", onLoadError).toString();
if(callbackoptions.length()>0)
	callbackoptions = callbackoptions + ",";

setting = viewoptions + checkoptions + asyncoptions + callbackoptions + editoptions;
if(setting.length()>0)
	setting = setting.substring(0,setting.length()-1);
//compile option
dataOption += "" + setting +""; 
String	clsStr = "",
		idStr = "",
		styleStr = "";
idStr = id==null?"":"id="+id;
clsStr = cls == null ? "class='coralui-tree'" : "class='coralui-tree "+cls+"'";
styleStr = style == null ? "" : "style='"+style+"'";
%><ul <%=idStr %> <%=clsStr %> <%=styleStr %> data-options="<%=dataOption %>"><jsp:doBody /></ul>

<%@ tag language="java" pageEncoding="UTF-8"%><%@ attribute name="generalLevel" type="java.lang.Boolean"%><%@ attribute name="pagerContent"%><%@attribute name="connectGridId"%><%@attribute name="onSortableLoad"%><%@attribute name="onSortableStart"%><%@attribute name="onSortableStop"%><%@attribute name="containment"%><%@attribute name="id"%><%@attribute name="style"%><%@attribute name="onSortCol"%><%@ attribute name="cls" %><%@ attribute name="componentCls" %><%@ attribute name="subGridRowExpanded"%><%@ attribute name="subGridBeforeExpand"%><%@ attribute name="subGridRowColapsed"%><%@ attribute name="expandColumn"%><%@ attribute name="subGrid" type="java.lang.Boolean"%><%@ attribute name="rendered" type="java.lang.Boolean" %><%@ attribute name="authorized" %><%@ attribute name="loadui" %><%@ attribute name="sortname" %><%@ attribute name="sortorder" %><%@ attribute name="allowSaveOnError" type="java.lang.Boolean"%><%@ attribute name="rowEditButtons"%><%@ attribute name="treeGrid" type="java.lang.Boolean"%><%@ attribute name="deselectAfterSort" type="java.lang.Boolean"%><%@ attribute name="minWidth" type="java.lang.Boolean"%><%@ attribute name="clicksToEdit"%><%@attribute name="rowattr"%><%@attribute name="cellattr"%><%@attribute name="userData"%><%@attribute name="footerrow" type="java.lang.Boolean" %><%@attribute name="showGridHeader" type="java.lang.Boolean" %><%@attribute name="enableHighlight" type="java.lang.Boolean" %><%@attribute name="userDataOnFooter" type="java.lang.Boolean" %><%@attribute name="frozenColumns" type="java.lang.Boolean" %><%@attribute name="emptyrecords"%><%@attribute name="autoencode" type="java.lang.Boolean" %><%@attribute name="jsonReader"%><%@attribute name="groupingView"%><%@attribute name="grouping" type="java.lang.Boolean" %><%@attribute name="loadtext"%><%@attribute name="loadonce" type="java.lang.Boolean" %><%@attribute name="autoWrap" type="java.lang.Boolean" %><%@attribute name="rowList"%><%@attribute name="multikey"%><%@attribute name="multiboxonly" type="java.lang.Boolean" %><%@attribute name="clickRowToSelect" type="java.lang.Boolean" %><%@attribute name="afterInlineSaveRow"%><%@attribute name="beforePopulate"%><%@attribute name="afterInsertRow"%><%@attribute name="beforeInlineEditRow"%><%@attribute name="afterInlineSave"%><%@attribute name="afterInlineCancelRow"%><%@attribute name="pagerStyle"%><%@attribute name="pager"%><%@attribute name="pagerTemplate"%><%@attribute name="colModel"%><%@attribute name="recordtext"%><%@attribute name="rninput"%><%@attribute name="altRows" type="java.lang.Boolean"%><%@attribute name="altclass"%><%@attribute name="shortCut"%><%@attribute name="beforeEditCell"%><%@attribute name="afterEditCell"%><%@attribute name="afterSaveCell"%><%@attribute name="onInlineEditRow"%><%@attribute name="afterFilter"%><%@attribute name="width"%><%@attribute name="height"%><%@attribute name="url"%><%@attribute name="caption"%><%@attribute name="data"%><%@attribute name="initData"%><%@attribute name="onLoad"%><%@attribute name="onComplete"%><%@attribute name="allowCellSelect"%><%@attribute name="shrinkToFit" type="java.lang.Boolean"%><%@attribute name="singleselect" type="java.lang.Boolean"%><%@attribute name="forceFit" type="java.lang.Boolean"%><%@attribute name="onSortableColumns" %><%@attribute name="beforeSortableRows" %><%@attribute name="onSortableRows" %><%@attribute name="afterSortableRows" %><%@attribute name="beforeDraggableRows" %><%@attribute name="onDraggableRows" %><%@attribute name="afterDraggableRows" %><%@attribute name="editableRows" type="java.lang.Boolean" %><%@attribute name="onPaging"%><%@attribute name="onMouseEnter"%><%@attribute name="onMouseLeave"%><%@attribute name="onDblClickRow"%><%@attribute name="onResizeStop"%><%@attribute name="onResizeStart"%><%@attribute name="onSelectRow"%><%@attribute name="beforeSelectRow"%><%@attribute name="onSelectCell"%><%@attribute name="onSelectAll"%><%@attribute name="rownumbers" type="java.lang.Boolean"%><%@ attribute name="rownumName" %><%@attribute name="rowNum"%><%@attribute name="rownumWidth"%><%@attribute name="groupHeader" type="java.lang.Boolean"%><%@attribute name="model"%><%@attribute name="picTemplate"%><%@attribute name="multiselect" type="java.lang.Boolean"%><%@attribute name="repeatitems" type="java.lang.Boolean"%><%@attribute name="postData"%><%@attribute name="fitStyle"%><%@attribute name="datatype"%><%@attribute name="asyncType"%><%@attribute name="viewrecords" type="java.lang.Boolean"%><%@attribute name="navbarOptions"%><%@attribute name="cellEdit" type="java.lang.Boolean"%><%@include file="TagUtil.jsp" %><% 
String option = tagUtil.add("id", id)
.add("cls", cls)
.add("generalLevel",generalLevel)
.add("pagerContent",pagerContent)
.add("style", style)
.add("onSortCol", onSortCol)
.add("subGrid", subGrid)
.add("subGridRowExpanded", subGridRowExpanded)
.add("subGridRowColapsed", subGridRowColapsed)
.add("subGridBeforeExpand", subGridBeforeExpand)
.add("expandColumn", expandColumn)
.add("componentCls", componentCls)
.add("rendered", rendered).add("authorized", authorized)
.add("treeGrid", treeGrid)
.add("deselectAfterSort", deselectAfterSort)
.add("minWidth", minWidth)
.add("allowSaveOnError", allowSaveOnError)
.add("clicksToEdit", clicksToEdit)
.add("sortname", sortname)
.add("sortorder", sortorder)
.add("loadui", loadui)
.add("rowattr", rowattr)
.add("cellattr", cellattr)
.add("userData", userData)
.add("allowCellSelect", allowCellSelect)
.add("footerrow", footerrow)
.add("showGridHeader", showGridHeader)
.add("enableHighlight", enableHighlight)
.add("userDataOnFooter", userDataOnFooter)
.add("frozenColumns", frozenColumns)
.add("autoencode", autoencode)
.add("emptyrecords", emptyrecords)

.add("jsonReader", jsonReader, "options")
.add("groupingView", groupingView, "options")
.add("grouping", grouping)
.add("loadtext", loadtext)
.add("loadonce", loadonce)
.add("autoWrap", autoWrap)
.add("shortCut", shortCut)
.add("rowList", rowList)
.add("multikey", multikey)
.add("multiboxonly", multiboxonly)
.add("clickRowToSelect", clickRowToSelect)
.add("rowEditButtons", rowEditButtons)
.add("afterInlineSave", afterInlineSave)
.add("afterInsertRow", afterInsertRow)
.add("afterInlineSaveRow", afterInlineSaveRow)
.add("beforePopulate", beforePopulate)
.add("beforeInlineEditRow", beforeInlineEditRow)
.add("afterInlineCancelRow", afterInlineCancelRow)
.add("pager", pager)
.add("pagerStyle", pagerStyle)
.add("pagerTemplate", pagerTemplate)
.add("colModel", colModel)
.add("recordtext", recordtext)
.add("rninput", rninput)
.add("altRows", altRows)
.add("altclass", altclass)
.add("beforeEditCell", beforeEditCell)
.add("afterEditCell", afterEditCell)
.add("afterSaveCell", afterSaveCell)
.add("onInlineEditRow", onInlineEditRow)
.add("afterFilter", afterFilter)
.add("width", width)
.add("height", height)	
.add("url", url)
.add("caption", caption)
.add("data", data)
.add("initData", initData)
.add("onLoad", onLoad)	
.add("onComplete", onComplete)
.add("shrinkToFit", shrinkToFit)	
.add("singleselect", singleselect)	
.add("forceFit", forceFit)
.add("onSortableColumns", onSortableColumns)	
.add("beforeSortableRows", beforeSortableRows)
.add("afterSortableRows", afterSortableRows)
.add("onSortableStart", onSortableStart)	
.add("onSortableStop", onSortableStop)
.add("onSortableLoad", onSortableLoad)
.add("onSortableRows", onSortableRows)	
.add("connectGridId", connectGridId)
.add("containment", containment)
.add("beforeDraggableRows", beforeDraggableRows)
.add("onDraggableRows", onDraggableRows)
.add("afterDraggableRows", afterDraggableRows)
.add("editableRows", editableRows)
.add("onPaging", onPaging)
.add("onMouseEnter", onMouseEnter)	
.add("onMouseLeave", onMouseLeave)
.add("onDblClickRow", onDblClickRow)	
.add("onResizeStop", onResizeStop)
.add("onSelectRow", onSelectRow)	
.add("beforeSelectRow", beforeSelectRow)	
.add("onSelectCell", onSelectCell)	
.add("onSelectAll", onSelectAll)	
.add("rownumbers", rownumbers)
.add("rownumName", rownumName)
.add("rownumWidth", rownumWidth)
.add("rowNum", rowNum)	
.add("groupHeader", groupHeader)
.add("model", model)	
.add("picTemplate", picTemplate)
.add("multiselect", multiselect)	
.add("repeatitems", repeatitems)
.add("postData", postData)	
.add("fitStyle", fitStyle)
.add("datatype", datatype)
.add("asyncType", asyncType)
.add("viewrecords", viewrecords)
.add("controllerName", request.getAttribute("controllerName"))
.add("navbarOptions", navbarOptions, "options")
.add("cellEdit", cellEdit).toString();
String clsStr = "",
idStr = "",
styleStr = "";
idStr = id==null?"":"id="+id;
clsStr = cls == null ? "class='coralui-grid'" : "class='coralui-grid "+cls+"'";
styleStr = style == null ? "" : "style='"+style+"'";
%><div <%=idStr %> <%=clsStr %> <%=styleStr %> data-options="<%=option %>" ><jsp:doBody /></div>
<%@ tag language="java" pageEncoding="UTF-8"%><%@attribute name="gridId"%><%@attribute name="pagerCls"%><%@attribute name="toolbarOptions"%><%@attribute name="layout"%><%@attribute name="description"%><%@include file="TagUtil.jsp" %>
<%
String option = tagUtil.add("toolbarOptions", toolbarOptions, "options")
.add("layout", layout)
.add("controllerName", request.getAttribute("controllerName"))
.add("description", description)
.toString();
%>
<div class="<%=(pagerCls == null ? " " : pagerCls+" ") %> <%=(gridId == null ? " " : gridId+" ") %>" data-options="<%= option %>"></div>
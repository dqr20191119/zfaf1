<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<div class="PanelCon">
 <cui:tabs id="routeTab" heightStyle="content" >
        <ul>
            <li><a href="${ctx}/route/routeList">巡视路线管理</a></li>
            <li><a href="${ctx}/route/routePointList">巡视点位管理</a></li>
        </ul>
    </cui:tabs>
</div>


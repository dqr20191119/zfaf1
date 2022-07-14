<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<link rel="stylesheet" type="text/css" href="${ctx}/static/module/video/css/rightVideoPlan.css" />

<style>
.form-control {
	width: 100%;
}
.yhsd{
	display:none;
}
.transfer_splitDiv {
    float:left;
    margin-top:40%;
    margin-left:10px;
}
.transfer_btn {
    padding-bottom:25px
}
</style>
<div style="height: 100%;">
	<cui:layout id="fxcj-layout" fit="true">
		<!-- 风险要素树形结构 -->
		<cui:layoutRegion region="west" split="false" style="width: 300px;" maxWidth="300" url="${ctx}/aqfxyp/fxcj/fxysTreePage?type=${type}" onResize="initTreebox">
		</cui:layoutRegion>
		<cui:layoutRegion region="center" split="false" onLoad="layoutCenterResize" onResize="layoutCenterResize">
		</cui:layoutRegion>
	</cui:layout>
</div>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.cesgroup.prison.common.facade.AuthSystemFacade"%>
<%@ page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<%@ page import="com.cesgroup.framework.utils.Util"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<div style="height: 100%; margin: 0px 10px;">
	<img src="" padding-left="50px" id="facepicUrl" width="360" height="420">
	<img src="" padding-left="50px" id="capSmallpicUrl" width="360" height="420">
</div>
<script>
	$.parseDone(function() {
		var facepic = '${facepic}';
		var capSmallpic = '${capSmallpic}';
		var id = '${id}';
		$("#facepicUrl").attr("src","${ctx}/rlsb/downloadImage?type="+facepic+"&id="+id);
		$("#capSmallpicUrl").attr("src","${ctx}/rlsb/downloadImage?type="+capSmallpic+"&id="+id);
	});
	
	</script>
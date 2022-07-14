<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%-- <%@ include file="/WEB-INF/layouts/base.jsp"%> --%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta http-equiv="cache-control" content="no-store, no-cache, must-revalidate"> 
<meta http-equiv="pragma" content="no-cache"> 
<meta http-equiv="expires" content="0">
<title>组织部门树</title>

</head>
<body>
<!-- 部门信息 -->
<div style="width:100%;height:380px;" class="mscroll">
	<cui:tree id="departTree" asyncEnable="true" keepParent="true" asyncType="post" asyncUrl="departTree.url"
		asyncAutoParam="id,name" rootNode="departTree.rootNode" showRootNode="true" checkable="true" 
		chkboxType="departTree.chkboxType" chkStyle="checkbox">
	</cui:tree> 
</div>
<script>
	$(".mscroll").mCustomScrollbar({
		theme:"minimal-dark",
		autoExpandScrollbar:true,
		scrollInertia:0,
		mouseWheelPixels:130//鼠标滚动一下滑动多少像素 
	}); 
	$(function(){
		departTree = {
			url:"${ctx}/common/authsystem/findAllDeptForCombotree?cusNumber="+jsConst.CUS_NUMBER,//"${ctx}/common/authsystem/findDeptPoliceForCombotree?cusNumber=3203",
			chkboxType: {'Y':'ps', 'N':'ps'},
			rootNode :{ name:jsConst.CUS_NAME+"组织部门", id:"", isParent:true, open:true },
			//$tree:$('#departTree'),
			init:function(){
				//初始化树
				$('#departTree').tree({
				    asyncEnable: true,                     //属性，值是boolean型，true/false不要引号
				    asyncType: 'post',                     //属性，值是字符串型，需要引号
				    asyncUrl: departTree.url,//属性，值是字符串，需要引号
				    onExpand: function() {                 //回调事件
				    }
				}); 
			}
		}
		//初始化区域树
		departTree.init();
	});
</script>
</body>
</html>
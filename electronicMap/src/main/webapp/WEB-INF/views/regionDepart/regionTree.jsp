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
<title>区域树</title>

</head>
<body>
<!-- 区域信息 -->
<div style="width:100%;height:380px;" class="mscroll">
	<cui:tree id="regionTree" asyncEnable="true" keepParent="true" asyncType="post" asyncUrl="regionTree.url"
		asyncAutoParam="id,name,areaId" onClick="regionTree.als" showLine="true" showRootNode="true">
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
		regionTree = {
			url:"${ctx}/common/areadevice/findForCombotree?cusNumber="+jsConst.CUS_NUMBER+"&deviceType=0",//"${ctx}/region/getRegionTree?cusNumber=3203",
			$tree:$('#regionTree'),
			//rootNode :{ name:jsConst.CUS_NAME+"区域", id:"", isParent:true, open:true },
			init:function(){
				//初始化树
				$('#regionTree').tree({
				    asyncEnable: true,                     //属性，值是boolean型，true/false不要引号
				    asyncType: 'post',                     //属性，值是字符串型，需要引号
				    asyncUrl: regionTree.url,//属性，值是字符串，需要引号
				    onExpand: function() {                 //回调事件
				    }
				});
			},
			//单击事件，显示单击的区域父区域名称
			als: function (event, id, node) {
				var nodes = $('#departTree').tree("getNodeByParam",'监狱领导').children;
				//取消选中
				for(var i=0;i<nodes.length;i++){
					$('#departTree').tree("checkNode", nodes[i], false);
				}
				//console.log(nodes);
			    $.ajax({
					type : 'post',
					url : '${ctx}/regionDepart/getDepartInfo.json',
					data : {'cusNumber':jsConst.CUS_NUMBER,'areaId':node.id},
					dataType : 'json',
					success : function(data) {
						for(var i=0;i<data.length;i++){
							for(var j=0;j<nodes.length;j++){
								if(data[i].adrDprtmntId == nodes[j].id){
									$('#departTree').tree("checkNode", nodes[j], true);
								}
							}
						}
					},error : function(XMLHttpRequest, textStatus, errorThrown) {
						alert(textStatus);
					}
				});
			}
		}
		//初始化区域树
		regionTree.init();
	});
</script>
</body>
</html>
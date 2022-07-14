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
<title>test演示1</title>
<!-- coral4 css start  -->
<link rel="stylesheet" href="${ctx}/static/cui/cui.min.css"/>
<!-- coral4 css  end  -->
<!-- app css define start -->
<link href="${ctx}/static/resource/style/css/common.css" type="text/css" rel="stylesheet" />
<link href="${ctx}/static/resource/style/css/jquery.pagewalkthrough.css" type="text/css" rel="stylesheet" />
<link href="${ctx}/static/resource/style/css/forcoraltheme.css" type="text/css" rel="stylesheet" />
<link href="${ctx}/static/resource/style/css/body.css" type="text/css" rel="stylesheet" />
<link href="${ctx}/static/resource/style/css/inforGlobal.css" type="text/css" rel="stylesheet" />
<link href="${ctx}/static/resource/style/css/prettify.css" type="text/css" rel="stylesheet" />
<!-- app css define end -->

<!-- coral4 js start -->
<script src="${ctx}/static/jquery-cui/js/jquery-1.11.3.min.js"></script>
<script src="${ctx}/static/cui/cui.js"></script>
<!-- coral4 js end -->

<!-- app js define start  -->
<script src="${ctx}/static/js/scripts/common.js"></script>
<script src="${ctx}/static/resource/style/js/function.js"></script>
<script src="${ctx}/static/js/scripts/prettify.js"></script>
<!-- app js define  end  -->

</head>
<body>
<div class="F-left" style="width: 100%;">
	<!-- 区域信息 -->
	<cui:tree id="regionTree" asyncEnable="true" keepParent="true" style="height:150px;"
		 asyncType="post" asyncUrl="${ctx}/region/getRegionTree?cusNumber=3201"
		asyncAutoParam="id,name,areaId" onClick="regionTree.als" onDblClick="regionTree.dbls" rootNode="true" showRootNode="true" >
	</cui:tree>
</div>
<script>
	$(function(){
		regionTree = {
			tree:$('#regionTree'),
			id:null,
			abdAreaId:null,
			abdAreaName:null,
			abdShortName:null,
			areaType:null,
			areaProperty:null,
			abdMapName:null,
			init:function(){
				//初始化加载数据
				var panel = $('#region-layout').layout('panel', 'center');
			},
			//单击事件，显示单击的区域父区域名称
			als: function (event, id, node) {
				console.log(node);
				//重置表单
				regionTree.resetForm();
				var parent = node.getParentNode();
				var id = node.id;
				var isParent = node.isParent;
				var parentId = node.parentTId;
				if(parentId != null){
					/* $('#abdParentAreaName').val(parent==null?'':parent.name);
					$('#abdParentAreaId').val(parent==null?'':parent.id); */
				}
				/* $('#areaId').val(id);
				$('#id').val(node.level); */
			},
			//双击事件，获取双击的区域信息
			dbls: function (event, id, node){
				$.ajax({
					type : 'post',
					url : '${ctx}/region/getRegionInfo.json',
					data : {'id':node.id},
					dataType : 'json',
					success : function(data) {
						var model = data[0];
						/* $('#id').val(model.id);
						$('#abdAreaId').val(model.abdAreaId);
						$('#abdAreaName').val(model.abdAreaName);
						$('#abdShortName').val((model.abdShortName==null || model.abdShortName=='')?model.abdAreaName:model.abdShortName);
						$("#areaType").combobox("select",model.abdTypeIndc);
						$("#areaProperty").combobox("select",model.abdLevelIndc);
						$('#abdMapName').val(model.abdMapName);
						$('#abdOrder').val(model.adbOrder); */
					},error : function(XMLHttpRequest, textStatus, errorThrown) {
						alert(textStatus);
					}
				});
			},
			//重置表单
			resetForm: function (){
				/* $('#id').val('');
				$('#abdAreaId').val('');
				$('#abdAreaName').val('');
				$('#abdShortName').val('');
				$("#areaType").combobox("select",'');
				$("#areaProperty").combobox("select",'');
				$('#abdMapName').val('');
				$('#abdParentAreaName').val('');
				$('#abdParentAreaId').val('');
				$('#abdOrder').val(''); */
				
			}
		}
		
		//初始化区域树
		regionTree.init();
	});

	
</script>
</body>
</html>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags" %>
<%-- <%@ include file="/WEB-INF/layouts/base.jsp"%> --%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta http-equiv="cache-control" content="no-store, no-cache, must-revalidate">
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="expires" content="0">
    <title>区域树</title>
    <!-- coral4 css start  -->
    <link rel="stylesheet" href="${ctx}/static/cui/cui.min.css"/>
    <!-- coral4 css  end  -->
    <!-- app css define start -->
    <link href="${ctx}/static/resource/style/css/common.css" type="text/css" rel="stylesheet"/>
    <link href="${ctx}/static/resource/style/css/jquery.pagewalkthrough.css" type="text/css" rel="stylesheet"/>
    <link href="${ctx}/static/resource/style/css/forcoraltheme.css" type="text/css" rel="stylesheet"/>
    <link href="${ctx}/static/resource/style/css/body.css" type="text/css" rel="stylesheet"/>
    <link href="${ctx}/static/resource/style/css/inforGlobal.css" type="text/css" rel="stylesheet"/>
    <link href="${ctx}/static/resource/style/css/prettify.css" type="text/css" rel="stylesheet"/>
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
    <div style="width:100%;height:100%;" class="mscroll">
        <cui:tree id="regionTree" asyncEnable="true" keepParent="true" asyncType="post" asyncUrl="regionTree.url"
                  asyncAutoParam="id,name,areaId,nodeCusNumber" onClick="regionTree.als" onDblClick="regionTree.dbls"
                  rootNode="true" showRootNode="true">
        </cui:tree>
    </div>
</div>
<script>
    $(".mscroll").mCustomScrollbar({
        theme: "minimal-dark",
        autoExpandScrollbar: true,
        scrollInertia: 0,
        mouseWheelPixels: 130//鼠标滚动一下滑动多少像素
    });

    $(function () {
        regionTree = {
            url: "${ctx}/region/getRegionTree?cusNumber=" + jsConst.CUS_NUMBER,
            $tree: $('#regionTree'),
            init: function () {
                //初始化加载数据
                var panel = $('#region-layout').layout('panel', 'center');
                //初始化树
                $('#regionTree').tree({
                    asyncEnable: true,                     //属性，值是boolean型，true/false不要引号
                    asyncType: 'post',                     //属性，值是字符串型，需要引号
                    asyncUrl: regionTree.url,//属性，值是字符串，需要引号
                    onExpand: function () {                 //回调事件
                    }
                });
            },
            //单击事件，显示单击的区域父区域名称
            als: function (event, id, node) {
                //重置表单
                regionTree.resetForm();

                /*				var parent = node.getParentNode();
                                var parentId = node.parentTId;

                                if(parentId != null) {
                                    $('#abdParentAreaName').val(parent==null?'':parent.name);
                                }*/


                var parent = node.getParentNode();
                var id = node.id;
                var isParent = node.isParent;
                var parentId = node.parentTId;
                if (parentId != null) {
                    $('#abdParentAreaName').val(parent == null ? '' : parent.name);
                    $('#abdParentAreaId').val(parent == null ? '' : parent.areaId);
                }
                $('#areaId').val(node.areaId);
                $('#id').val(node.id);
                $('#selectCusNumber').val(typeof(node.nodeCusNumber) == 'undefined' ? '' : node.nodeCusNumber);
                /* //按钮状态
                if((node.areaId==null || node.areaId=='') && node.level==0 || node.level==1){
                    $('#btn_addBrother').button("disable");
                    $('#btn_addChild').button("disable");
                    $('#btn_updRegion').button("disable");
                    $('#btn_delRegion').button("disable");
                    return false;
                }else  */
                if ((node.areaId == null || node.areaId == '') && node.level == 0) {
                    $('#btn_addBrother').button("disable");
                    $('#btn_addChild').button("enable");
                    $('#btn_updRegion').button("disable");
                    $('#btn_delRegion').button("disable");
                    return false;
                } else {
                    $('#btn_addBrother').button("enable");
                    $('#btn_addChild').button("enable");
                    $('#btn_updRegion').button("enable");
                    $('#btn_delRegion').button("enable");
                }
            },
            //双击事件，获取双击的区域信息
            dbls: function (event, id, node) {
                if ((node.areaId == null || node.areaId == '') && node.level == 0) {
                    console.log('省局');
                    return false;
                } else if ((node.areaId == null || node.areaId == '') && node.level == 1) {
                    console.log('监区');
                    return false;
                }
                $.ajax({
                    type: 'post',
                    url: '${ctx}/region/getRegionInfo.json',
                    data: {'cusNumber': node.nodeCusNumber, 'id': node.areaId},
                    dataType: 'json',
                    success: function (data) {
                        var model = data[0];
                        $('#id').val(model.id);
                        $('#abdAreaId').val(model.abdAreaId);
                        $('#abdAreaName').val(model.abdAreaName);
                        $('#abdShortName').val((model.abdShortName == null || model.abdShortName == '') ? model.abdAreaName : model.abdShortName);
                        if (model.abdTypeIndc != 0) {
                            $("#areaType").combobox("select", model.abdTypeIndc);
                        }
                        //$("#areaProperty").combobox("select",model.abdLevelIndc);
                        $("#abdLevelIndc").combobox("setValue", model.abdLevelIndc == "0" ? '' : model.abdLevelIndc + '');
                        $('#abdMapName').val(model.abdMapName);
                        $('#abdOrder').val(model.abdOrder);
                        $("#abdJqId").combobox('setValue', model.abdJqId);
                        $("#abdJqId").combobox('setText', model.abdJqName);
                    }, error: function (XMLHttpRequest, textStatus, errorThrown) {
                        alert(textStatus);
                    }
                });
            },
            //重置表单
            resetForm: function () {
                $('#id').val('');
                $('#abdAreaId').val('');
                $('#abdAreaName').val('');
                $('#abdShortName').val('');
                $("#areaType").combobox("select", '');
                $("#abdLevelIndc").combobox("select", '');
                $('#abdMapName').val('');
                $('#abdParentAreaName').val('');
                $('#abdParentAreaId').val('');
                $('#abdOrder').val('');
                $("#abdJqId").combobox('setValue', '');
                $("#abdJqId").combobox('setText', '');
            }
        }
        //初始化区域树
        regionTree.init();
    });
</script>
</body>
</html>
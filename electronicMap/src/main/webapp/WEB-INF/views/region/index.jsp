<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags" %>
<%@page import="com.cesgroup.prison.code.utils.CodeFacade" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta http-equiv="cache-control" content="no-store, no-cache, must-revalidate">
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="expires" content="0">
    <title>区域管理</title>
</head>
<body>
<div class="model-mainten add-region">
    <h3 class="formTitle">区域维护</h3>
    <div class="model_div">
        <cui:form id="regionForm" method="post" action="">
            <input type="hidden" id="id" name="id" value=""/>
            <input type="hidden" id="abdParentAreaId" name="abdParentAreaId" value=""/>
            <input type="hidden" id="areaId" value=""/>
            <input type="hidden" id="abdTypeIndc" name="abdTypeIndc" value=""/>
            <!-- <input type="hidden" id="abdLevelIndc" name="abdLevelIndc" value="" /> -->
            <input type="hidden" id="abd3dIdnty" name="abd3dIdnty" value=""/>
            <input type="hidden" id="selectCusNumber" value=""/>
        </cui:form>
        <cui:panel width="100%" height="260" url="${ctx}/region/regionTreePage" onResize="initTreebox"></cui:panel>
    </div>
    <div class="model_div region_model">
        <div class="region_table">
            <label>父级区域：</label>
            <cui:input id="abdParentAreaName" readonly="true" value=""/>
            <label>区域编号：</label>
            <cui:input id="abdAreaId" name="abdAreaId" value=""/>
            <label>区域名称：</label>
            <cui:input componentCls="form-control" id="abdAreaName" name="abdAreaName" value=""
                       onBlur="region3d.onblurEven()"/>
            <label>区域简称：</label>
            <cui:input id="abdShortName" name="abdShortName" value=""/>
            <label>区域类型：</label>
            <cui:combobox id="areaType" data="region3d.typeData" emptyText="空"/>
            <label>区域属性：</label>
            <cui:combobox id="abdLevelIndc" name="abdLevelIndc" data="region3d.propertyData"/>
            <label>排 序 号：</label>
            <cui:input id="abdOrder" name="abdOrder"/>
            <%--			<label >所属部门：</label>
                        <cui:combobox id="abdJqId" name="abdJqId" url=""/>--%>
            <cui:button label="同级增加" id="btn_addBrother" onClick="region3d.addBrotherNode"></cui:button>
            <cui:button label="下级增加" id="btn_addChild" onClick="region3d.addChildNode"></cui:button>
            <cui:button label=" 修  改  " id="btn_updRegion" onClick="region3d.updateRegion"></cui:button>
            <cui:button label=" 删  除  " id="btn_delRegion" onClick="region3d.delRegion"></cui:button>
        </div>
    </div>
</div>
<%-- <cui:layout id="region-layout" fit="true">
    <cui:layoutRegion region='north' split="false" style="width:240px;height:150px;" maxHeight="190" maxWidth="240" url="${ctx}/region/regionTreePage" onResize="initTreebox">
    </cui:layoutRegion>
    <cui:layoutRegion region='center' split="false" style="width: 220px;height:390px;top:0px;"  onLoad="" onResize="">
        <div class="region_div">
            <table class="region_table">
                <tr >
                    <td class="tdLabel"><label>父级区域：</label></td>
                    <td class="tdElement"><cui:input componentCls="form-control" id="abdParentAreaName"
                        readonly="true" value=""/></td>
                </tr>
                <tr>
                    <td class="tdLabel"><label>区域编号：</label></td>
                    <td class="tdElement"><cui:input componentCls="form-control"
                        id="abdAreaId" name="abdAreaId" value=""/></td>
                </tr>
                <tr>
                    <td class="tdLabel"><label>区域名称：</label></td>
                    <td class="tdElement"><cui:input componentCls="form-control"
                        id="abdAreaName" name="abdAreaName" value=""/></td>
                </tr>
                <tr>
                    <td class="tdLabel"><label>区域简称：</label></td>
                    <td class="tdElement"><cui:input componentCls="form-control"
                        id="abdShortName" name="abdShortName" value=""/></td>
                </tr>
                <tr>
                    <td class="tdLabel"><label>区域类型：</label></td>
                    <td class="tdElement"><cui:combobox id="areaType" data="region3d.typeData" /></td>
                </tr>
                <tr>
                    <td class="tdLabel"><label>区域属性：</label></td>
                    <td class="tdElement"><cui:combobox id="abdLevelIndc" name="abdLevelIndc" data="region3d.propertyData" /></td>
                </tr>
                <tr>
                    <td class="tdLabel"><label>排 序 号：</label></td>
                    <td class="tdElement"><cui:input id="abdOrder" name="abdOrder" value="" /></td>
                </tr>

                <tr>
                    <td class="tdLabel"><label>图层名称：</label></td>
                    <td class="tdElement"><cui:input componentCls="form-control" readonly="true"
                        id="abdMapName" name="abdMapName" value=""/></td>
                </tr>
                <tr>

                <tr>

                    <td class="tdLabel"><label>所属部门：</label></td>

                    <td class="tdElement"><cui:input componentCls="form-control"
                        name="departmentName" value="${model.departmentName }"/></td>
                </tr>
                <tr>

                    <td class="tdElement"><cui:combobox id="abdJqId" name="abdJqId" url="" /></td>
                </tr>

                <tr>
                    <td colspan="2" class="reg_btns" style="padding-top:10px;">
                        <center>
                            <span>
                                <cui:button label="同级增加" id="btn_addBrother" onClick="region3d.addBrotherNode" ></cui:button>
                                <cui:button label="下级增加" id="btn_addChild" onClick="region3d.addChildNode"></cui:button>
                            </span><br/>
                            <span>
                                <cui:button label=" 修  改  " id="btn_updRegion" onClick="region3d.updateRegion"></cui:button>
                                <cui:button label=" 删  除  " id="btn_delRegion"  onClick="region3d.delRegion"></cui:button>
                            </span>
                        </center>
                    </td>
                </tr>
            </table>
        </div>
    </cui:layoutRegion>
</cui:layout>  --%>


<script type="text/javascript">
    var region3d;
    $(function () {
        region3d = {
            cusNumber: jsConst.CUS_NUMBER,
            selectCusNumber: null,
            typeData: <%=CodeFacade.loadCode2Json("4.20.7")%>,
            propertyData: <%=CodeFacade.loadCode2Json("4.20.8")%>,
            init: function () {
                /*			$.parseDone(function(){
                                //初始化所属部门下拉列表
                                $("#abdJqId").combobox("reload", jsConst.basePath+"common/authsystem/findAllChildrenOrgByJyKeyForCombobox.json?cusNumber="+jsConst.CUS_NUMBER);
                            });*/
            },
            //添加同级区域
            addBrotherNode: function () {
                $('#id').val('');
                region3d.save();
            },
            //添加下级区域
            addChildNode: function () {
                $('#id').val('');
                $('#abdParentAreaId').val($('#areaId').val());
                region3d.save();
            },
            //保存添加
            save: function () {
                if (region3d.isVild()) {
                    var selectCusNumber = $('#selectCusNumber').val();
                    var cusNumber = (selectCusNumber == null || selectCusNumber == '') ? jsConst.CUS_NUMBER : selectCusNumber;
                    var areaId = $('#abdAreaId').val();
                    $.ajax({
                        type: 'post',
                        url: '${ctx}/region/getRegionInfo.json',
                        data: {'cusNumber': cusNumber, 'id': areaId},
                        dataType: 'json',
                        success: function (data) {
                            if (data.length < 1) {
                                var model = data[0];
                                var formData = region3d.getFormData();
                                if (formData.abdCusNumber == null || formData.abdCusNumber == '') {
                                    formData.abdCusNumber = cusNumber;
                                }
                                console.log(formData);
                                var ur = '${ctx}/region/createRegion';
                                $.ajax({
                                    type: 'post',
                                    url: ur,
                                    data: formData,
                                    dataType: 'json',
                                    success: function (data) {
                                        var model = data.region;
                                        $.message({
                                            message: "添加成功",
                                            iframePanel: true
                                        });
                                        region3d.reloadTree();
                                    },
                                    error: function (XMLHttpRequest, textStatus, errorThrown) {
                                        $.alert({
                                            message: textStatus,
                                            title: "信息提示",
                                            iframePanel: true
                                        });
                                    }
                                });
                            } else {
                                $.alert({
                                    message: "区域编号重复，请重新输入!",
                                    title: "信息提示",
                                    iframePanel: true
                                });
                            }
                        }, error: function (XMLHttpRequest, textStatus, errorThrown) {
                            $.alert({
                                message: textStatus,
                                title: "信息提示",
                                iframePanel: true
                            });
                        }
                    });
                }
            },
            //删除区域
            delRegion: function () {
                var cusNumber = $('#selectCusNumber').val();
                var id = $('#areaId').val();
                if (id == '') {
                    $.alert({
                        message: '请点击选择一个层级功能名称',
                        title: "信息提示",
                        iframePanel: true
                    });
                    return false;
                }
                $.confirm({
                    message: '确认是否删除?',
                    title: "提示信息",
                    iframePanel: true,
                    callback: function (r) {
                        if (r) {
                            $.ajax({
                                type: 'post',
                                url: "${ctx}/region/delRegion",
                                data: {"cusNumber": cusNumber, "id": id},
                                dataType: 'json',
                                success: function (data) {
                                    $.message({
                                        message: "操作成功",
                                        iframePanel: true
                                    });
                                    region3d.reloadTree();
                                    //$("#regionTree").tree("reload");
                                },
                                error: function (XMLHttpRequest, textStatus, errorThrown) {
                                    $.alert({
                                        message: '请点击选择一个层级功能名称',
                                        title: "信息提示",
                                        iframePanel: true
                                    });
                                }
                            });
                        }
                    }
                });
            },
            //修改区域
            updateRegion: function () {
                if (region3d.isVild()) {
                    var cusNumber = $('#selectCusNumber').val();
                    var areaId = $('#abdAreaId').val();
                    $.ajax({
                        type: 'post',
                        url: '${ctx}/region/getRegionInfo.json',
                        data: {'cusNumber': cusNumber, 'id': areaId},
                        dataType: 'json',
                        success: function (data) {
                            if (data.length <= 1 && (data.length == 1 ? data[0].id == $('#id').val() : true)) {
                                var formData = region3d.getFormData();//$("#regionForm").form("formData");
                                var ur = '${ctx}/region/updateRegion';
                                $.ajax({
                                    type: 'post',
                                    url: ur,
                                    data: formData,
                                    dataType: 'json',
                                    success: function (data, textStatus, errorThrown) {
                                        if(data.success){
                                            // var model = data.region;
                                            $.message({
                                                message: data.msg,
                                                iframePanel: true
                                            });
                                            region3d.reloadTree();
                                            $('#abdParentAreaId').val('');
                                            $('#id').val('');
                                        }
                                    },
                                    error: function (XMLHttpRequest, textStatus, errorThrown) {
                                        $.alert({
                                            message: data.msg,
                                            title: "信息提示",
                                            iframePanel: true
                                        });
                                    }
                                });
                            } else {
                                $.alert({
                                    message: "区域编号重复，请重新输入!",
                                    title: "信息提示",
                                    iframePanel: true
                                });
                            }

                        }, error: function (XMLHttpRequest, textStatus, errorThrown) {
                            $.alert(textStatus);
                        }
                    });
                }
            },
            //表单输入验证
            isVild: function () {
                //$('#abdLevelIndc').val($('#areaProperty').combobox('getValue'));
                $('#abdTypeIndc').val($('#areaType').combobox('getValue'));
                var selectAeaId = $('#areaId').val();
                var areaId = $('#abdAreaId').val();
                var areaName = $('#abdAreaName').val();
                var abdLevelIndc = $('#abdLevelIndc').combobox("getValue");
                var areaType = $("#abdTypeIndc").val();
                //var areaProperty = $("#abdLevelIndc").val();
//			var jqId = $('#abdJqId').combobox('getValue');
//			var jqName = $('#abdJqId').combobox('getText');
                var selectCusNumber = $('#selectCusNumber').val();
                var allNodes = $('#regionTree').tree('getNodes');
                if (selectAeaId == '' && selectCusNumber == '' && allNodes.length > 0) {
                    $.alert({
                        message: "请点击选择一个层级功能名称",
                        title: "信息提示",
                        iframePanel: true
                    });
                    return false;
                }
                if (areaId == '') {
                    $.alert({
                        message: "请点填写区域编号",
                        title: "信息提示",
                        iframePanel: true
                    });
                    return false;
                }
                if (areaName == '') {
                    $.alert({
                        message: "请点填写区域名称",
                        title: "信息提示",
                        iframePanel: true
                    });
                    return false;
                }
                if (areaType == '' && abdLevelIndc != 1) {
                    $.alert({
                        message: "请点选择区域类型",
                        title: "信息提示",
                        iframePanel: true
                    });
                    return false;
                }
                /*			if(jqId=='' || jqName==''){
                                $.alert({
                                    message:"请点选择所属部门",
                                    title:"信息提示",
                                    iframePanel:true
                                });
                                return false;
                            }*/
                return true;
            },
            getFormData: function () {
                var formData = new Object();
                formData.id = $('#id').val();
                formData.abdAreaId = $('#abdAreaId').val();
                formData.abdAreaName = $('#abdAreaName').val();
                formData.abdShortName = $('#abdShortName').val();
                //formData.abdMapName = $('#abdMapName').val();
                formData.abdParentAreaId = $('#abdParentAreaId').val();
                formData.abdLevelIndc = $('#abdLevelIndc').combobox("getValue");
                formData.abdTypeIndc = $('#abdTypeIndc').val();
                formData.abd3dIdnty = $('#abd3dIdnty').val();
                formData.abdOrder = $('#abdOrder').val();
                formData.abdCusNumber = $('#selectCusNumber').val();
//			formData.abdJqId = $('#abdJqId').combobox('getValue');
//			formData.abdJqName = $('#abdJqId').combobox('getText');
                return formData;
            },
            reloadTree: function () {
                $("#regionTree").tree("reload");
                $('#selectCusNumber').val('');
                $('#id').val('');
                $('#areaId').val('');
            },
            onblurEven: function () {
                var shortName = $("#abdAreaName").textbox("getValue");
                $("#abdShortName").textbox("setValue", shortName);
            }
        }
        region3d.init();
    });
</script>
</body>
</html>
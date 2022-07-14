<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<div class="PanelCon">
	<div class="PanelList">
		<div class="clearfix panelTop">
				<div style="margin-left: 150px;float: left">
					<label>区域选择：</label>
                    <cui:combotree id="pointDormInfo_region" name="alpGrandId" onChange="pointDormManagerInfo.changeGrand"></cui:combotree>
                    <label>查询楼层：</label>
                    <cui:input id="pointDormInfo_floor" name="floor" value=""/>
				</div>
			<div style="float: right;margin-right: 200px">
				<cui:button label="查询" onClick="pointDormManagerInfo.search" cls="cyanbtn"></cui:button>
				<cui:button label="编辑" onClick="pointDormManagerInfo.update" cls="greenbtn" ></cui:button>
				<cui:button label="删除" onClick="pointDormManagerInfo.delPoint" cls="deleteBtn" ></cui:button>
			</div>
		</div>
		<cui:grid id="pointDormInfo_grid" rownumbers="false" singleselect="true"
				  fitStyle="fill" onDblClickRow="pointDormManagerInfo.onDblClickRow" width="auto" height="400" rowNum="10">
			<cui:gridCols>
				<cui:gridCol name="id" hidden="true" >id</cui:gridCol>
				<cui:gridCol name="alpCusNumber" hidden="true">监狱编号</cui:gridCol>
				<cui:gridCol name="alpDeviceIdnty"   hidden="true">监舍点位ID</cui:gridCol>
				<cui:gridCol name="alpDeviceType"   hidden="true">监舍点位类型</cui:gridCol>
				<cui:gridCol name="alpGrandId"   hidden="true">区域id</cui:gridCol>
				<cui:gridCol name="alpGrandName"  width="40" align="center">区域名称</cui:gridCol>
				<cui:gridCol name="alpDeviceName"  width="40" align="center">监舍名称</cui:gridCol>
				<cui:gridCol name="alpXPointIdnty" width="40" align="center">X坐标</cui:gridCol>
				<cui:gridCol name="alpYPointIdnty" width="40" align="center">Y坐标</cui:gridCol>
				<cui:gridCol name="alpZPointIdnty" width="40" align="center">Z坐标</cui:gridCol>
				<cui:gridCol name="alpStatuIdnc" width="40" hidden="true">状态</cui:gridCol>
            </cui:gridCols>
			<cui:gridPager gridId="pointDormInfo_grid"/>
		</cui:grid>
</div>
</div>
<cui:dialog id="adddialog" reLoadOnOpen="true" modal="true" autoOpen="false" width="1000" height="500" maximizable="false"></cui:dialog>
<cui:dialog id="resdialog" reLoadOnOpen="true" modal="true" autoOpen="false" width="80%" height="80%" maximizable="false"></cui:dialog>

<script>
var pointDormManagerInfo;
$(function(){
    pointDormManagerInfo = {
		cusNumber:jsConst.CUS_NUMBER,
		init:function(){
            $('#pointDormInfo_region').combotree({url:"${ctx}/common/areadevice/findForCombotree.json?cusNumber="+pointDormManagerInfo.cusNumber+"&deviceType=0"});
            $('#pointDormInfo_grid').grid({url:"${ctx}/point/findByDormPage?alpCusNumber="+pointDormManagerInfo.cusNumber});

//            var obj={'cusNumber':pointDormManagerInfo.cusNumber};
//            $("#gridView").grid("option","postData",obj);
//            $("#gridView").grid("reload",jsConst.basePath+"point/findByDormPage");
/*            $.parseDone(function () {
                var obj={'alpCusNumber':pointDormManagerInfo.cusNumber};
                $("#gridView").grid("option","postData",obj);
                $("#gridView").grid("reload",jsConst.basePath+"point/findByDormPage");
            })*/
        },


		//搜索查询
		search :function(){
            var postData = {
                "alpGrandId":$('#pointDormInfo_region').combobox('getValue'),
                "alpDeviceName":$('#pointDormInfo_floor').value,
                "alpCusNumber":pointDormManagerInfo.cusNumber
            }
            $('#pointDormInfo_grid').grid('option', 'postData', postData);
            $('#pointDormInfo_grid').grid('reload');
		},

		//编辑修改
		update :function(){
			var selrow = $('#pointDormInfo_grid').grid("option", "selrow");
			if (selrow != null) {
				var rowData = $('#pointDormInfo_grid').grid("getRowData", selrow);
                $("#pointDorm_dialog").dialog("close");
                $("#pointDorm_pointForm").form("clear");
                $('#pointDorm_region').combotree('setValue',rowData.alpGrandId);
                $('#pointDorm_region').combotree('setText',rowData.alpGrandName);
                $('#pointDorm_dormInfo').combobox('setText',rowData.alpDeviceName);
                $('#pointDorm_dormInfo').combobox('setValue',rowData.alpDeviceType);
                deviceIdForEdit=rowData.alpDeviceIdnty;
                $('#pointDorm_point_x').textbox('setValue',rowData.alpXPointIdnty);
                $('#pointDorm_point_y').textbox('setValue',rowData.alpYPointIdnty);
                $('#pointDorm_point_z').textbox('setValue',rowData.alpZPointIdnty);

/*                $('#pointDorm_canel').show();
                $('#pointDorm_reSet').hide();*/

                //通过区域id，切换定位视角
                pointDormManager.changeGrandView(rowData.alpGrandId);
                //切换为编辑状态
                map_3d.__g.interactMode = gviInteractMode.gviInteractEdit;
                if (typeof (pointDormManager.fnonobjectediting) == "function") ____events["onObjectEditing"] = pointDormManager.fnonobjectediting;

                pointDormManager.creatModelOrImg(rowData.alpXPointIdnty,rowData.alpYPointIdnty,rowData.alpZPointIdnty);//创建模型或者图片
                modelCurrent.glow(-1);//闪烁
                //编辑状态，动态获取xyz坐标
                if (typeof (pointDormManager.fnonobjectediting) == "function") ____events["onObjectEditing"] = pointDormManager.fnonobjectediting;
                map_3d.__g.objectEditor.startEditRenderGeometry(modelCurrent, gviGeoEditType.gviGeoEdit3DMove);
            } else {
                _DOCUMENT_EVENT.messge("请先选择一条记录！");
			}
		},


		//删除
		delPoint :function(){
            var selrow = $('#pointDormInfo_grid').grid("option", "selrow");
            if (selrow != ""&&selrow != null) {
                $.confirm("确认是否删除？", function(r) {
                    if (r) {
                        var rowData = $('#pointDormInfo_grid').grid("getRowData", selrow);
                        var url ="${ctx}/point/destoryPoint/" + rowData.id;
                        _DOCUMENT_EVENT.request_data_callback(url,pointDormManagerInfo.callBackDelete,null,null);
                    }
                });
            } else {
                _DOCUMENT_EVENT.messge("请先选择要删除的记录！！！");
            }
		},
        callBackDelete:function(result){
            if (result.success){
                _DOCUMENT_EVENT.messge("删除成功！");
                $('#pointDormInfo_grid').grid("reload");
			}else{
                _DOCUMENT_EVENT.messge("删除失败，请检查！");
			}
		}
	}
    pointDormManagerInfo.init();
});
</script>

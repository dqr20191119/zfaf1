<%@page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<style>
.form-control{
    width: 100%;
}
</style>

    <div style="text-align: center; height: 100%; width: 100%">
        <cui:form id="formId_cameraAdd" heightStyle="fill">
        <cui:input  type="hidden" required="true" id="cbdCusNumber" name="cbdCusNumber" value="" componentCls="form-control"></cui:input>
        <cui:input  type="hidden" required="true" name="cbdActionIndc" value="1" componentCls="form-control"></cui:input>
        <cui:input  type="hidden" required="true" id="cbdCrteUserId" name="cbdCrteUserId" value="" componentCls="form-control"></cui:input>
        <cui:input  type="hidden" required="true" id="cbdUpdtUserId" name="cbdUpdtUserId" value="" componentCls="form-control"></cui:input>
        <div style="display:none;" >
            <cui:datepicker  required="true" id="cbdCrteTime"  name="cbdCrteTime"  dateFormat="yyyy-MM-dd HH:mm:ss" componentCls="form-control"></cui:datepicker>
            <cui:datepicker  required="true" id="cbdUpdtTime"  name="cbdUpdtTime"  dateFormat="yyyy-MM-dd HH:mm:ss" componentCls="form-control"></cui:datepicker>
        </div>
        <table class="table" style="width: 100%;">
            <tr>
                <td width="20%"><label>摄像机名称：</label></td>
                <td width="30%"><cui:input required="true" name="cbdName" componentCls="form-control"></cui:input></td>
                <td width="20%"><label>摄像机所在的DVR：</label></td>
                <td width="30%"><cui:combobox id="cbdDvrIdnty" name="cbdDvrIdnty"  emptyText="请选择"  url="" componentCls="form-control"></cui:combobox></td>
            </tr>
            <tr>
                <td><label>摄像机品牌：</label></td>
                <td><cui:combobox name="cbdBrandName" emptyText="请选择"  data="cbdBrandName_json" componentCls="form-control"></cui:combobox></td>
                <td><label>摄像机所在的DVR的通道号：</label></td>
                <td><cui:input name="cbdDvrChnnlIdnty" componentCls="form-control"></cui:input></td>
            </tr>
            <tr>
                <td><label>摄像机类型：</label></td>
                <td><cui:combobox required="true" name="cbdTypeIndc" emptyText="请选择"  data="cbdTypeIndc_json" componentCls="form-control"></cui:combobox></td>
                <td><label>摄像机所在的流媒体：</label></td>
                <td><cui:combobox id="cbdStreamMediaIdnty" name="cbdStreamMediaIdnty" emptyText="请选择"  url="" componentCls="form-control"></cui:combobox></td>
            </tr>
            <tr>
                <td><label>摄像机状态：</label></td>
                <td><cui:combobox  emptyText="请选择" required="true" name="cbdSttsIndc"  data="cbdSttsIndc_json" componentCls="form-control"></cui:combobox></td>
                <td><label>摄像机所在平台：</label></td>
                <td><cui:combobox name="cbdPlatform"  emptyText="请选择"  data="" componentCls="form-control"></cui:combobox></td>
            </tr>
            <tr>
                <td><label>摄像机所属区域：</label></td>
                <td><cui:combotree required="true" id="cbdAreaId_new" name="cbdAreaId"  url="${ctx}" simpleDataEnable="true" componentCls="form-control"  onChange="onChangeArea" onNodeClick="onAreaTreeClick_sxt"></cui:combotree>
                <cui:input  id="cbdArea" name="cbdArea" type="hidden" value="" componentCls="form-control"></cui:input></td>
                <td><label>关联对讲设备：</label></td>
                <td><cui:combobox id="cbdTalkbackId_new" name="cbdTalkbackId" componentCls="form-control" onChange="onChangeTalk"></cui:combobox>
                    <cui:input  id="cbdTalkbackName" name="cbdTalkbackName" type="hidden" value="" componentCls="form-control"></cui:input></td>
            </tr>
            <tr>
                <td><label>监舍：</label></td>
                <td><cui:autocomplete id="cbdAddrs" name="cbdAddrs" componentCls="form-control" postMode="text" ></cui:autocomplete></td>
                <td><label>摄像机所在平台索引编号：</label></td>
                <td><cui:input name="cbdPlatformIdnty" componentCls="form-control"></cui:input></td>
                <%--<td><label>摄像机所属部门：</label></td>
                <td><cui:combobox id="cbdDprtmntSrno" name="cbdDprtmntSrno"  url="${ctx}"  componentCls="form-control" onChange="onChangeDprtmnt"></cui:combobox>
                <cui:input  id="cbdDprtmnt" name="cbdDprtmnt" type="hidden" value="" componentCls="form-control"></cui:input></td>--%>
            </tr>
            <tr>
                <td><label>实时视频播放方式：</label></td>
                <td><cui:combobox  required="true" name="cbdVideoPlayIndc" emptyText="请选择"  data="dbdVideoPlayIndc_json" componentCls="form-control"></cui:combobox></td>
                <td><label>视频回放方式：</label></td>
                <td><cui:combobox name="cbdVideoPlaybackIndc" emptyText="请选择"  data="dbdVideoPlayIndc_json" componentCls="form-control"></cui:combobox></td>
            </tr>
            <tr>
                <td><label>使用限制：</label></td>
                <td><cui:combobox name="cbdUseLimit" emptyText="请选择"  data="cbdUseLimit_json" componentCls="form-control"></cui:combobox></td>
                <td><label>摄像机IP：</label></td>
                <td><cui:input name="cbdIpAddrs" componentCls="form-control"></cui:input></td>
            </tr>
            <tr>
                <td><label>摄像机端口号：</label></td>
                <td><cui:input name="cbdPort" componentCls="form-control"></cui:input></td>
                <td><label>摄像机用户名：</label></td>
                <td><cui:input name="cbdUserName" componentCls="form-control"></cui:input></td>
            </tr>
            <tr>
                <td><label>摄像机密码：</label></td>
                <td><cui:input name="cbdUserPassword" type="password" componentCls="form-control"></cui:input></td>
                <td><label>摄像机通道号：</label></td>
                <td><cui:input name="cbdChnnlIdnty" componentCls="form-control"></cui:input></td>
            </tr>
            <tr>
                <td><label>摄像头位置：</label></td>
                <td><cui:combobox name="cbdOutSide" emptyText="请选择" value="0" data="cbdOutSide_json" componentCls="form-control"></cui:combobox></td>
                <td><label>排序：</label></td>
                <td><cui:input name="cbdOrder" validType = "integer" componentCls="form-control"></cui:input></td>
            </tr>
            <%--<tr>
                <td><label>报警有效开始时间：</label></td>
                <td style="column-span: 3"><cui:datepicker  model="timepicker" id="cbdValidStarttime"  name="cbdValidStarttime"  timeFormat="HH:mm:ss"></cui:datepicker></td>
                <td><label>报警有效结束时间：</label></td>
                <td style="column-span: 3"><cui:datepicker  model="timepicker" id="cbdValidEndtime"  name="cbdValidEndtime"  timeFormat="HH:mm:ss"></cui:datepicker></td>
            </tr>--%>
        </table>
        </cui:form>
        <div class="dialog-buttons" style="margin-bottom: 0px; text-align: center;">
            <cui:button label="重置"  onClick="reset"></cui:button>
            <cui:button label="保存"  onClick="save"></cui:button>
        </div>
</div>
<script>
	var jsConst=window.top.jsConst;
	var cusNumber=jsConst.ORG_CODE;	//监狱编号
	var userId=jsConst.USER_ID;	//登录人

	$.parseDone(function(){
		initFormData();
	});
	var cbdOutSide_json=[{'value':'0','text':'室内'},{'value':'1','text':'室外'}];
	var cbdUseLimit_json=[{'value':'0','text':'无限制'},{'value':'1100','text':'省局'}];
	var cbdTypeIndc_json =<%=CodeFacade.loadCode2Json("4.20.33")%>; 
	var cbdSttsIndc_json =<%=CodeFacade.loadCode2Json("4.20.13")%>; 
	var cbdBrandName_json =<%=CodeFacade.loadCode2Json("4.20.16")%>;
	var dbdVideoPlayIndc_json =<%=CodeFacade.loadCode2Json("4.20.14")%>;
    var cbdTimeValid_json = <%=CodeFacade.loadCode2Json("4.0.1")%>;

	function initFormData(){
		$("#cbdAreaId_new").combotree("tree").tree("reload","${ctx}/common/areadevice/findForCombotree.json?cusNumber="+cusNumber+"&deviceType=0");
		
		$("#cbdDprtmntSrno").combobox("reload","${ctx}/common/authsystem/findAllChildrenOrgByJyKeyForCombobox.json?cusNumber="+cusNumber);
		
		$("#cbdCusNumber").textbox("setValue",cusNumber);
		$("#cbdCrteUserId").textbox("setValue",userId);
		$("#cbdUpdtUserId").textbox("setValue",userId);
		$("#cbdDvrIdnty").combobox("option","url","${ctx}/sppz/videoDevice/simpleVideoDeviceList.json?vdiCusNumber="+cusNumber);
		$("#cbdDvrIdnty").combobox("reload");
		$("#cbdStreamMediaIdnty").combobox("option","url","${ctx}/sppz/streamServer/simpleStreamServerList.json?ssiCusNumber="+cusNumber);
		$("#cbdStreamMediaIdnty").combobox("reload");
		
		$('#cbdCrteTime').datepicker('setDate',new Date());
		$('#cbdUpdtTime').datepicker('setDate',new Date());
	}
	
	function reset(){
		$("#formId_cameraAdd").form("reset");
		initFormData
	}
	function onTimeValidChange(e,ui){
        if(ui.value == '1'){
            $('#cbdValidStarttime').datepicker({ required:true});
            $('#cbdValidEndtime').datepicker({ required:true});
        }else{
            $('#cbdValidStarttime').datepicker({ required:false});
            $('#cbdValidEndtime').datepicker({ required:false});
        }
    }
	function onChangeArea(e,ui){
		console.log(ui.text);
		$("#cbdArea").textbox('setValue',ui.text);
	}
	function onChangeDprtmnt(e,ui){
		console.log(ui.text);
		$("#cbdDprtmnt").textbox('setValue',ui.text);
	}
	/* 区域树下拉点击事件 */
	function onAreaTreeClick_sxt(event, ui) {
		$("#cbdTalkbackId_new").combobox( "reload", "${ctx}/talkBackBase/seachComboData.json?cusNumber=" + cusNumber + "&areaId=" + ui.node.id + "&deviceType=2");
        //监舍控件请求数据
        $("#cbdAddrs").autocomplete("option","source","${ctx}/common/all/findLcjsh.json?cusNumber=" + cusNumber + "&areaId=" + ui.node.id);
	}
	function onChangeTalk(e,ui){
		$("#cbdTalkbackName").textbox('setValue',ui.text);
	}
	function save(){
		if ($("#formId_cameraAdd").form("valid")) {
            $.loading({text:"正在处理中，请稍后..."});
			var formData = $("#formId_cameraAdd").form("formData");
			var ur = '${ctx}/jfsb/camera/create.json';
			$.ajax({
				type : 'post',
				url : ur,
				data : formData,
				dataType : 'json',
				success : function(data) {
                    $.loading("hide");
					if(data.exception==undefined){
						$.message({
							message : "保存成功",
							cls : "success",
							iframePanel:true
						});
						$("#gridId_camera").grid("reload","${ctx}/jfsb/camera/searchCamera.json?cbdCusNumber="+cusNumber);
						$("#dialogId_cameraManage").dialog("close");
					}else{
						$.message( {
							iframePanel:true,
					        message:data.exception.cause.message,
					        type:"danger"
					    });
					}
					
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
                    $.loading("hide");
					$.alert({
						message:textStatus,
						title:"信息提示",
						iframePanel:true
					});
				}
			});
	
		} else {
			$.alert({
				message:"请确认输入是否正确！",
				title:"信息提示",
				iframePanel:true
			});
		}
	}
</script>
<%@page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<style>
	.form-control {
		width: 100%;
	}
</style>
<div style="text-align: center; height: 100%; width: 100%">

	<cui:form id="formId_cameraEdit" heightStyle="fill">
		<cui:input type="hidden" required="true" name="id" value="${model.id }" componentCls="form-control"></cui:input>
		<cui:input type="hidden" required="true" id="cbdUpdtUserId" name="cbdUpdtUserId" value="" componentCls="form-control"></cui:input>
		<div style="display: none;">
			<cui:datepicker required="true" id="cbdUpdtTime" name="cbdUpdtTime" dateFormat="yyyy-MM-dd HH:mm:ss" componentCls="form-control"></cui:datepicker>
		</div>
		<table class="table table-fixed" style="width: 100%;">
			<tr>
				<td width="20%"><label>摄像机名称：</label></td>
				<td width="30%"><cui:input required="true" name="cbdName"
										   value="${model.cbdName }" componentCls="form-control"></cui:input></td>
				<td width="20%"><label>摄像机所在的DVR：</label></td>
				<td width="30%"><cui:combobox id="cbdDvrIdnty" name="cbdDvrIdnty"
											  value="${model.cbdDvrIdnty }" emptyText="请选择"
											  url="" componentCls="form-control"></cui:combobox></td>
			</tr>
			<tr>
				<td><label>摄像机品牌：</label></td>
				<td><cui:combobox name="cbdBrandName"
								  value="${model.cbdBrandName }" emptyText="请选择"
								  data="cbdBrandName_json" componentCls="form-control"></cui:combobox></td>
				<td><label>摄像机所在的DVR的通道号：</label></td>
				<td><cui:input name="cbdDvrChnnlIdnty"
							   value="${model.cbdDvrChnnlIdnty }" componentCls="form-control"></cui:input></td>
			</tr>
			<tr>
				<td><label>摄像机类型：</label></td>
				<td><cui:combobox required="true" name="cbdTypeIndc"
								  value="${model.cbdTypeIndc }" emptyText="请选择"
								  data="cbdTypeIndc_json" componentCls="form-control"></cui:combobox></td>
				<td><label>摄像机所在的流媒体：</label></td>
				<td><cui:combobox id="cbdStreamMediaIdnty" name="cbdStreamMediaIdnty"
								  value="${model.cbdStreamMediaIdnty }" emptyText="请选择"
								  url="" componentCls="form-control"></cui:combobox></td>
			</tr>
			<tr>
				<td><label>摄像机状态：</label></td>
				<td><cui:combobox emptyText="请选择" required="true"
								  name="cbdSttsIndc" value="${model.cbdSttsIndc}"
								  data="cbdSttsIndc_json" componentCls="form-control"></cui:combobox></td>
				<td><label>摄像机所在平台：</label></td>
				<td><cui:combobox name="cbdPlatform"
								  value="${model.cbdPlatform }" emptyText="请选择" data="" componentCls="form-control"></cui:combobox></td>
			</tr>
			<tr>
				<td><label>摄像机所属区域：</label></td>
				<td><cui:combotree required="true" id="cbdAreaId_edit" name="cbdAreaId" value="${model.cbdAreaId }"  simpleDataEnable="true"
								   url="${ctx}/common/areadevice/nullJson.json" onChange="onChangeArea" componentCls="form-control" onNodeClick="onAreaTreeClick_sxt"></cui:combotree>
					<cui:input  id="cbdArea" name="cbdArea" type="hidden" value="${model.cbdArea}" componentCls="form-control"></cui:input></td></td>
				<td><label>关联对讲设备：</label></td>
				<td><cui:combobox id="cbdTalkbackId_new" name="cbdTalkbackId" componentCls="form-control" value="${model.cbdTalkbackId}" onChange="onChangeTalk"></cui:combobox>
					<cui:input  id="cbdTalkbackName" name="cbdTalkbackName" type="hidden" value="${model.cbdTalkbackName}" componentCls="form-control"></cui:input></td>
			</tr>
			<tr>
				<td><label>监舍：</label></td>
				<td><cui:autocomplete id="cbdAddrs" name="cbdAddrs" componentCls="form-control"  postMode="text" ></cui:autocomplete></td>
				<td><label>摄像机所在平台索引编号：</label></td>
				<td><cui:input name="cbdPlatformIdnty"
							   value="${model.cbdPlatformIdnty }" componentCls="form-control"></cui:input></td>
				<%--
				<td><label>摄像机所属部门：</label></td>
				<td><cui:combobox id="cbdDprtmntSrno" name="cbdDprtmntSrno" value="${model.cbdDprtmntSrno }"  url="${ctx}"  componentCls="form-control" onChange="onChangeDprtmnt"></cui:combobox>
					<cui:input  id="cbdDprtmnt" name="cbdDprtmnt" value="${model.cbdDprtmnt }" type="hidden"  componentCls="form-control"></cui:input></td>
				--%>
			</tr>
			<tr>
				<td><label>实时视频播放方式：</label></td>
				<td><cui:combobox name="cbdVideoPlayIndc"
								  value="${model.cbdVideoPlayIndc }" emptyText="请选择"
								  data="dbdVideoPlayIndc_json" componentCls="form-control"></cui:combobox></td>
				<td><label>视频回放方式：</label></td>
				<td><cui:combobox name="cbdVideoPlaybackIndc"
								  value="${model.cbdVideoPlaybackIndc }" emptyText="请选择"
								  data="dbdVideoPlayIndc_json" componentCls="form-control"></cui:combobox></td>
			</tr>
			<tr>
				<td><label>使用限制：</label></td>
				<td><cui:combobox name="cbdUseLimit"
								  value="${model.cbdUseLimit }" emptyText="请选择"
								  data="cbdUseLimit_json" componentCls="form-control"></cui:combobox></td>
				<td><label>摄像机IP：</label></td>
				<td><cui:input name="cbdIpAddrs" value="${model.cbdIpAddrs }" componentCls="form-control"></cui:input></td>
			</tr>
			<tr>
				<td><label>摄像机端口号：</label></td>
				<td><cui:input name="cbdPort" value="${model.cbdPort }" componentCls="form-control"></cui:input></td>
				<td><label>摄像机用户名：</label></td>
				<td><cui:input name="cbdUserName" value="${model.cbdUserName }" componentCls="form-control"></cui:input></td>
			</tr>
			<tr>
				<td><label>摄像机密码：</label></td>
				<td><cui:input name="cbdUserPassword" value="${model.cbdUserPassword }" type="password" componentCls="form-control"></cui:input></td>
				<td><label>摄像机通道号：</label></td>
				<td><cui:input name="cbdChnnlIdnty" value="${model.cbdChnnlIdnty }" componentCls="form-control"></cui:input></td>
			</tr>
			<tr>
				<td><label>摄像头位置：</label></td>
				<td><cui:combobox name="cbdOutSide" value="${model.cbdOutSide }" emptyText="请选择"  data="cbdOutSide_json" componentCls="form-control"></cui:combobox></td>
                <td><label>排序：</label></td>
                <td><cui:input name="cbdOrder" value="${model.cbdOrder }" validType = "integer" componentCls="form-control"></cui:input></td>
			</tr>
            <%--<tr>
                <td><label>报警有效开始时间：</label></td>
                <td style="column-span: 3"><cui:datepicker  model="timepicker" id="cbdValidStarttime"   value="${model.cbdValidStarttime }" name="cbdValidStarttime"  timeFormat="HH:mm:ss"></cui:datepicker></td>
                <td><label>报警有效结束时间：</label></td>
                <td style="column-span: 3"><cui:datepicker  model="timepicker" id="cbdValidEndtime"   name="cbdValidEndtime"  timeFormat="HH:mm:ss"></cui:datepicker></td>
            </tr>--%>
		</table>
	</cui:form>
<div class="dialog-buttons" style="margin-bottom: 0px; text-align: center;">
	<cui:button label="重置" onClick="reset"></cui:button>
	<cui:button label="修改" onClick="f_edit"></cui:button>
</div>
</div>
<script>
	var jsConst=window.top.jsConst;
	var cusNumber=jsConst.ORG_CODE;							//监狱编号
	var userId=jsConst.USER_ID;					//登录人
	$.parseDone(function() {
		initFormData();
	});
	var cbdOutSide_json=[{'value':'0','text':'室内'},{'value':'1','text':'室外'}];
	var cbdUseLimit_json = [ {
		'value' : '0',
		'text' : '无限制'
	}, {
		'value' : '1100',
		'text' : '省局'
	} ];
	var cbdTypeIndc_json =<%=CodeFacade.loadCode2Json("4.20.33")%>;
	var cbdSttsIndc_json =<%=CodeFacade.loadCode2Json("4.20.13")%>;
	var cbdBrandName_json =<%=CodeFacade.loadCode2Json("4.20.16")%>;
	var dbdVideoPlayIndc_json =<%=CodeFacade.loadCode2Json("4.20.14")%>;
    var cbdTimeValid_json = <%=CodeFacade.loadCode2Json("4.0.1")%>;

	function initFormData(){
		//$("#cbdAreaId_edit").combotree("tree").tree("reload","${ctx}/common/areadevice/findForCombotree.json?cusNumber="+cusNumber+"&deviceType=0");
		$("#cbdAreaId_edit").combotree("tree").tree("reload",{
			asyncUrl: "${ctx}/common/areadevice/findForCombotree.json?cusNumber="+cusNumber+"&deviceType=0",
			onLoad: function(){
				$("#cbdAreaId_edit").combotree("setValue",('${model.cbdAreaId}').toString());
				$("#cbdAreaId_edit").combotree("setText",('${model.cbdArea}').toString());
			}
		} );

		$("#cbdDprtmntSrno").combobox("reload",{
			url:"${ctx}/common/authsystem/findAllChildrenOrgByJyKeyForCombobox.json?cusNumber="+cusNumber,
			onLoad: function(){
				$("#cbdDprtmntSrno").combobox("setValue",('${model.cbdDprtmntSrno }').toString());
				$("#cbdDprtmntSrno").combotree("setText",('${model.cbdDprtmnt}').toString());
			}
		});

        onAreaTreeClick();

        $("#cbdAddrs").autocomplete("option","source","${ctx}/common/all/findLcjsh.json?cusNumber=" + cusNumber + "&areaId=" + '${model.cbdAreaId}');

        setTimeout(function () {
            $("#cbdTalkbackId_new").combobox("setValue",('${model.cbdTalkbackId}').toString());
            $("#cbdTalkbackName").textbox("setValue",('${model.cbdTalkbackName}').toString());
            $("#cbdAddrs").autocomplete("setValue",('${model.cbdAddrs}').toString());
            $("#cbdAddrs").autocomplete("setText",('${model.cbdAddrs}').toString());
        });

		$("#cbdUpdtUserId").textbox("setValue",userId);

		$("#cbdDvrIdnty").combobox("reload",{
			url:"${ctx}/sppz/videoDevice/simpleVideoDeviceList.json?vdiCusNumber="+cusNumber,
			onLoad: function(){
				$("#cbdDvrIdnty").combobox("setValue",('${model.cbdDvrIdnty}').toString());
			}
		});

		$("#cbdStreamMediaIdnty").combobox("reload",{
			url:"${ctx}/sppz/streamServer/simpleStreamServerList.json?ssiCusNumber="+cusNumber,
			onLoad: function(){
				$("#cbdStreamMediaIdnty").combobox("setValue",('${model.cbdStreamMediaIdnty}').toString());
			}
		});

		$('#cbdUpdtTime').datepicker('setDate', new Date());
	}
	function reset() {
		$("#formId_cameraEdit").form("reset");
		initFormData();
	}
    function onTimeValidChange(e,ui){
        if(ui.value == '1'){
            $('#cbdValidStarttime').datepicker({ required:true});
            $('#cbdValidEndtime').datepicker({ required:true});
        }else{
            $('#cbdValidStarttime').datepicker({ required:false});
            $('#cbdValidEndtime').datepicker({ required:false});
            $('#cbdValidStarttime').datepicker("clear");
            $('#cbdValidEndtime').datepicker("clear");
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
        $("#cbdAddrs").autocomplete("setValue","");
        $("#cbdAddrs").autocomplete( "option","source", "${ctx}/common/all/findLcjsh.json?cusNumber=" + cusNumber + "&areaId=" + ui.node.id);
        $("#cbdTalkbackId_new").combobox( "reload", "${ctx}/talkBackBase/seachComboData.json?cusNumber=" + cusNumber + "&areaId=" + ui.node.id + "&deviceType=2");
    }
	/* 区域树下拉点击事件 */
	function onAreaTreeClick() {
		$("#cbdTalkbackId_new").combobox( "reload", "${ctx}/talkBackBase/seachComboData.json?cusNumber=" + cusNumber + "&areaId=" +'${model.cbdAreaId}' + "&deviceType=2");
	}
	function onChangeTalk(e,ui){
		$("#cbdTalkbackName").textbox('setValue',ui.text);
	}
	function f_edit() {
		if ($("#formId_cameraEdit").form("valid")) {
			var formData = $("#formId_cameraEdit").form("formData");
			var ur = '${ctx}/jfsb/camera/updatePart';
			$.ajax({
				type : 'post',
				url : ur,
				data : formData,
				dataType : 'json',
				success : function(data) {
					if (data.success) {
						$.message({
							message : "修改成功",
							cls : "success",
							iframePanel:true
						});
						$("#gridId_camera").grid("reload","${ctx}/jfsb/camera/searchCamera.json?cbdCusNumber="+cusNumber);
						$("#dialogId_cameraManage").dialog("close");
					} else {
						$.message({
							iframePanel:true,
							message : data.msg,
							type : "danger"
						});
					}

				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
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
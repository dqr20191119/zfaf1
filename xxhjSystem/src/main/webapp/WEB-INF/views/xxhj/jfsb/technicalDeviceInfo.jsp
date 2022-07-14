<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<style>
	th {
		padding: 15px 0px 15px 30px;
	    vertical-align: middle;
	}
</style>

<cui:tabs heightStyle="fill">
	<ul>
		<li><a href="#DeviceInfo">详细信息</a></li>
		<li><a href="#faultDeviceList">维修记录</a></li>
	</ul>
	

	<div id="DeviceInfo">
	    <cui:form id="listDeviceInfo">
			<div id="cameraTable"   style="display: none">
               	<table>						
               		<tr>
	                 <th>摄像机名称：</th>
					 <td><cui:input id="cbdName" name="CBD_NAME" width="160"></cui:input></td>
					 <th>摄像机名字前缀：</th>
					 <td><cui:input id="cbdPreNeme" name="ABD_PORT" width="160"></cui:input></td>
					 <th>摄像机类型：</th>
					 <td><cui:combobox id="cbdTypeIndc" name="CBD_TYPE_INDC_CH" data="combobox_cbdTypeIndc" width="160"></cui:combobox></td>
					<tr>
					<tr>
	                 <th>摄像机品牌：</th>
					 <td><cui:combobox id="cbdBrandName" data="combobox_cbdBrandIndc" name="CBD_BRAND_NAME" width="160"></cui:combobox></td>
					 <th>智能分析通道号：</th>
					 <td><cui:input id="cbdBrandName" name="ABD_PORT" width="160"></cui:input></td>
					 <th>摄像机状态:</th>
					 <td><cui:combobox id="cbdSttsIndc" name="CBD_STTS_INDC" data="combobox_cbdSttsIndc" width="160"></cui:combobox></td>
					</tr>	
					<tr>
	                 <th>摄像机所在矩阵编号：</th>
					 <td><cui:input id="cbdMtrxIdnty" name="CBD_MTRX_IDNTY" width="160"></cui:input></td>
					 <th>摄像机所在矩阵输入通道：</th>
					 <td><cui:input id="cbdMtrxChnnlIdnty" name="CBD_MTRX_CHNNL_IDNTY" width="160"></cui:input></td>
					 <th>摄像机在单位中序号：</th>
					 <td><cui:input id="cbdDprtmntSrno" name="CBD_DPRTMNT_SRNO" width="160"></cui:input></td>
					</tr>	
					<tr>
	                 <th>所属部门：</th>
					 <td><cui:input id="cbdDprtmnt" name="CBD_DPRTMNT" width="160"></cui:input></td>
					 <th>摄像机所在DVR编号：</th>
					 <td><cui:input id="cbdDvrIdnty" name="CBD_DVR_IDNTY" width="160"></cui:input></td>
					 <th>所属区域：</th>
					 <td><cui:input id="cbdArea" name="CBD_AREA" width="160"></cui:input></td>
					</tr>	
					<tr>
	                 <th>摄像机所在DVR通道号：</th>
					 <td><cui:input id="cbdDvrChnnlIdnty" name="CBD_DVR_CHNNL_IDNTY" width="160"></cui:input></td>
					 <th>摄像机地址码：</th>
					 <td><cui:input id="cbdAddrsCode" name="CBD_ADDRS_CODE" width="160"></cui:input></td>
					</tr>	
				</table>
			</div>
			
			<div id="alertorTable"  style="display: none">
				<table>
					<tr>
						<th>报警器名称:</th>
						<td><cui:input id="abdName" name="ABD_NAME"></cui:input></td>
						<th>端口:</th>
						<td><cui:input id="abdPort" name="ABD_PORT"></cui:input></td>
						<th>报警器IP:</th>
						<td><cui:input id="abdIp" name="ABD_IP"></cui:input></td>
					</tr>
					<tr>
					    <th>报警器名称前缀:</th>
					    <td><cui:input id="abdPreName"  name="ABD_PRE_NAME"></cui:input></td>
						<th>所属区域：</th>
						<td><cui:input id="abdArea"  name="ABD_AREA"></cui:input></td>
						<th>报警器地址:</th>
						<td><cui:input id="abdAddrs"  name="ABD_ADDRS"> </cui:input></td>
					</tr>
					<tr>
						<th>报警器品牌:</th>
						<td><cui:combobox id="abdBrandIndc" name="ABD_BRAND_INDC" data="combobox_abdBrandIndc"></cui:combobox></td>
						<th>报警器主机编号:</th>
						<td><cui:input id="abdHostIdnty" name="ABD_HOST_IDNTY"></cui:input></td>
						<th>报警器类型:</th>
						<td><cui:combobox id="abdTypeIndc" name="ABD_TYPE_INDC" data="combobox_abdTypeIndc"></cui:combobox></td>
					</tr>
					<tr>
						<th>报警器状态:</th>
						<td><cui:combobox id="abdSttsIndc" name="ABD_STTS_INDC" data="combobox_deviceSttsIndc"></cui:combobox></td>
						<th>备注:</th>
						<td><cui:input id="abdRemark"  name="ABD_REMARK"></cui:input></td>
					</tr>
				</table>
			</div>
			
			<div id="infraredTable" style="display: none">
				<table>
					<tr>
						<th>红外名称：</th>
						<td><cui:input id="IBI_NAME" name="IBI_NAME" width="160"></cui:input></td>
						<th>红外类型：</th>
						<td><cui:combobox id="IBI_TYPE_INDC" name="IBI_TYPE_INDC" width="160"></cui:combobox></td>
						<th>布防状态：</th>
						<td><cui:input id="IBI_WORK_STTS_INDC" name="IBI_WORK_STTS_INDC" width="160"></cui:input></td>
					</tr>
					
					<tr>
						<th>设备状态：</th>
						<td><cui:combobox id="IBI_DVC_STTS_INDC" name="IBI_DVC_STTS_INDC" width="160"></cui:combobox></td>
						<th>安装地址：</th>
						<td><cui:input id="IBI_ADDRS" name="IBI_ADDRS" width="160"></cui:input></td>
						<th>三维视角-俯仰角：</th>
						<td><cui:input id="IBI_POS_PITCH" name="IBI_POS_PITCH" width="160"></cui:input></td>
						
					</tr>
					<tr>
					    <th>三维视角-旋转角：</th>
						<td><cui:input id="IBI_POS_ROTATE" name="IBI_POS_ROTATE" width="160"></cui:input></td>
					    <th>三维视角-X坐标：</th>
						<td><cui:input id="IBI_POS_X" name="IBI_POS_X" width="160"></cui:input></td>
						<th>三维视角-Y坐标：</th>
						<td><cui:input id="IBI_POS_Y" name="IBI_POS_Y" width="160"></cui:input></td>
					</tr>
					<tr>
						<th>三维视角-Z坐标：</th>
						<td><cui:input id="IBI_POS_Z" name="IBI_POS_Z" width="160"></cui:input></td>
					</tr>
				</table>
			</div>

			<div id="doorTable" style="display: none">
				<table>
					<tr>
						<th>门禁名称：</th>
						<td><cui:input id="dcbName" name="DCB_NAME" width="160"></cui:input></td>
						<th>门禁端口：</th>
						<td><cui:input id="dcbPort" name="DCB_PORT" width="160"></cui:input></td>
						<th>门禁名称前缀：</th>
						<td><cui:input id="dcbPreName" name="DCB_PRE_NAME" width="160"></cui:input></td>
					</tr>
					<tr>
					    <th>门禁IP：</th>
						<td><cui:input id="dcbIpAddrs" name="DCB_IP_ADDRS" width="160"></cui:input></td>
					    <th>门禁类型：</th>
						<td><cui:combobox id="dcbTypeIndc" data="combobox_dcbTypeIndc" name="DCB_TYPE_INDC_CH" width="160"></cui:combobox></td>
						<th>所属区域：</th>
						<td><cui:input id="dcbArea" name="DCB_AREA" width="160"></cui:input></td>
					</tr>
					<tr>
					    <th>所属部门：</th>
						<td><cui:input id="dcbDprtmnt" name="DCB_IP_ADDRS" width="160"></cui:input></td>
					    <th>门禁状态：</th>
						<td><cui:combobox id="dcbSttsIndc" data="combobox_dcbSttsIndc" name="DCB_STTS_INDC_CH" width="160"></cui:combobox></td>
						<th>门禁地址：</th>
						<td><cui:input id="dcbAddrs" name="DCB_ADDRS" width="160"></cui:input></td>
					</tr>
			        <tr>
					    <th>门禁频道：</th>
						<td><cui:input id="dcbDprtmnt" name="DCB_IP_ADDRS" width="160"></cui:input></td>
					    <th>门禁序列号：</th>
						<td><cui:input id="dcbSttsIndc" name="DCB_STTS_INDC_CH" width="160"></cui:input></td>
						<th>门禁点图层：</th>
						<td><cui:input id="dcbAddrs" name="DCB_ADDRS" width="160"></cui:input></td>
					</tr>
				    <tr>
					    <th>备注：</th>
						<td><cui:input id="dcbRemark" name="DCB_REMARK" width="160"></cui:input></td>
					</tr>
				</table>
			</div>

			<div id="powerNetworkTable" style="display: none">
				<table>
					<tr> 
						<th>电网名称：</th>
						<td><cui:input id="pnbName" name="PNB_NAME" width="160" ></cui:input></td>
						<th>电网状态：</th>
						<td><cui:combobox id="pnbSttsIndc" data="combobox_deviceSttsIndc" name="PNB_STTS_INDC_CH" width="160"></cui:combobox></td>
						<th>A相电压：</th>
						<td><cui:input id="pnbABoxVoltage" name="PNB_A_BOX_VOLTAGE" width="160"></cui:input></td>
					</tr>
					<tr>
						<th>A相电流：</th>
						<td><cui:input id="pnbABoxPowerFlow" name="PNB_A_BOX_POWER_FLOW" width="160"></cui:input></td>
						<th>B相电压：</th>
						<td><cui:combobox id="pnbBBoxVoltage" name="PNB_B_BOX_VOLTAGE" width="160"></cui:combobox></td>
						<th>B相电流：</th>
						<td><cui:input id="pnbBBoxPowerFlow" name="PNB_B_BOX_POWER_FLOW" width="160"></cui:input></td>
					</tr>
					<tr>
						<th>电源电压：</th>
						<td><cui:input id="pnbPowerSourceVoltage" name="PNB_POWER_SOURCE_VOLTAGE" width="160"></cui:input></td>
						<th>电源电流：</th>
						<td><cui:combobox id="pnbPowerSourceFlow" name="PNB_POWER_SOURCE_FLOW" width="160"></cui:combobox></td>
						<th>IP：</th>
						<td><cui:input id="pnbIp" name="PNB_IP" width="160"></cui:input></td>
					</tr>
					<tr>
						<th>变动时间：</th>
						<td><cui:input id="pnbTime" name="PNB_TIME" width="160"></cui:input></td>
						
					</tr>
				</table>
			</div>
		
			<div id="snakeTable" style="display: none">
				<table>
					<tr>
						<th>防区名称：</th>
						<td><cui:input id="swiDefenseName" name="SWI_DEFENSE_NAME" width="160"></cui:input></td>
						<th>防区编号：</th>
						<td><cui:combobox id="swiDefenseIdnty" name="SWI_DEFENSE_IDNTY" width="160"></cui:combobox></td>
						<th>探测器编号：</th>
						<td><cui:input id="swiDetectorIdnty" name="SWI_DETECTOR_IDNTY" width="160"></cui:input></td>
					</tr>
					<tr>
						<th>探测主机编号：</th>
						<td><cui:input id="swiDetectorHostIdnty" name="SWI_DETECTOR_HOST_IDNTY" width="160"></cui:input></td>
						<th>安装地址：</th>
						<td><cui:combobox id="swiAddrs" name="SWI_ADDRS" width="160"></cui:combobox></td>
						<th>设备状态：</th>
						<td><cui:combobox id="swiSttsIndc" data="combobox_deviceSttsIndc" name="SWI_STTS_INDC_CH" width="160"></cui:combobox></td>
					</tr>
				</table>
			</div>
				
		</div>
	</cui:form>
	<div id="faultDeviceList">
		<cui:grid id="gridId_faultDeviceList" singleselect="true" height="800px" colModel="gridColModel_faultDeviceList" fitStyle="fill" pager="true"
			url="${ctx}/technicalDevice/listdeviceMaintainRecord?dmrCusNumber=${cusNumber}&dmrDeviceType=${deviceType}&&dmrDeviceIdnty=${deviceId}"></cui:grid>
	</div>
</cui:tabs>

<script type="text/javascript">

	//一般设备状态
	var combobox_deviceSttsIndc = <%=CodeFacade.loadCode4ComboJson("4.20.23", 4, "0,1")%>;
	//摄像机状态有多个
	var combobox_cbdSttsIndc = <%=CodeFacade.loadCode4ComboJson("4.20.13", 4, "0,1,2,3")%>;
	//摄像机品牌
	var combobox_cbdBrandIndc = <%=CodeFacade.loadCode2Json("4.20.16")%>;
	//摄像机类型
	var combobox_cbdTypeIndc = <%=CodeFacade.loadCode2Json("4.20.33")%>;
	//报警器类型
	var combobox_abdTypeIndc = <%=CodeFacade.loadCode2Json("4.20.27")%>;
	//报警器品牌
	var combobox_abdBrandIndc = <%=CodeFacade.loadCode2Json("4.20.36")%>;
	//门禁类型
	var combobox_dcbTypeIndc = <%=CodeFacade.loadCode2Json("4.20.21")%>;
	//门禁状态
	var combobox_dcbSttsIndc = <%=CodeFacade.loadCode2Json("4.20.23")%>;
	
	$.parseDone(function() {
	
		var cusNumber =<%=request.getParameter("cusNumber")%>;
		var deviceType =<%=request.getParameter("deviceType")%>;
		var deviceId ="<%=request.getParameter("deviceId")%>";
       
		queryOneDeviceInfo(cusNumber, deviceType, deviceId)
	});
	
	/**
	 * 查询技防设备信息
	 */
	function queryOneDeviceInfo(cusNumber, deviceType, deviceId) {
	
		var url;
		var list = new Array();
		list.push(cusNumber);
		list.push(deviceId);
		var url = "${ctx}/xxhj/jfsb/listSysDeviceInfo?type="+deviceType+"&cusNumber=" + list[0] + "&id=" + list[1];
		
		$.ajax({
			type : "post",
			url : url,
			dataType : "json",
			success : function(data) {
				
				if (data[0]) {
					checkDeviceType(deviceType, data[0]);
				}
			}
		});
	}
	
	/**
	 * 根据设备类型加载设备信息
	 */
	function checkDeviceType(deviceType, data) {
		
		switch (deviceType) {
			case 1:
				loadCameraInfo(data);
				break;
			case 2:
				loadAlertorInfo(data);
				break;
			case 3:
				loadPowerNetworkInfo(data);
				break;
			case 4:
				loadSnakeWireInfo(data);
				break;
			case 5:
				loadInfraredInfo(data);
				break;
			case 6:
				loadDoorInfo(data);
				break;
			case 7:
				loadTalkbackInfo(data);
				break;
			case 11:
				loadSnakeWireInfo(data);
				break;
		}
	}
	
	/**
	 * 加载摄像机信息
	 */
	function loadCameraInfo(data) {
	
		$("#listDeviceInfo").form("setReadOnly",true);
		$("#listDeviceInfo").form("loadData",data);
	
		$("#cameraTable").show();
	}
	
	/**
	 * 加载报警器信息
	 */
	function loadAlertorInfo(data) {
		
		$("#listDeviceInfo").form("setReadOnly",true);
		$("#listDeviceInfo").form("loadData",data);

		$("#alertorTable").show(); 
	}
	
	/**
	 * 加载红外报警器信息
	 */
	function loadInfraredInfo(data) {
		
		$("#listDeviceInfo").form("setReadOnly",true);
		$("#listDeviceInfo").form("loadData",data);

		$("#infraredTable").show(); 
	}
	
	/**
	 * 加载门禁信息
	 */
	function loadDoorInfo(data) {
		
		$("#listDeviceInfo").form("setReadOnly",true);
		$("#listDeviceInfo").form("loadData",data);
		
		$("#doorTable").show();
	}
	
	/**
	 * 加载电网信息
	 */
	function loadPowerNetworkInfo(data) {
		
		$("#listDeviceInfo").form("setReadOnly",true);
		$("#listDeviceInfo").form("loadData",data);
		
		$("#powerNetworkTable").show();
	}

	/*
	 * 加载蛇腹网信息
	 */
	function loadSnakeWireInfo(data) {
		
		$("#listDeviceInfo").form("setReadOnly",true);
		$("#listDeviceInfo").form("loadData",data);
		
		$("#snakeTable").show();
	}

	/**
	 * 列表加载设备维修信息
	 */
	var gridColModel_faultDeviceList = [{
		label : "设备类型",
		name : "DMR_DEVICE_TYPE_CH"
	}, {
		label : "设备名称",
		name : "DMR_DEVICE_NAME"
	}, {
		label : "故障时间",
		name : "DMR_CRTD_DATE"
	}, {
		label : "维修情况",
		name : "DMR_FAULT_CONTENT"
	}, {
		label : "维修人员",
		name : "DMR_FAULT_MAINTAINER"
	}, {
		label : "维修时间",
		name : "DMR_FAULT_MAINTAIN_TIME"
	}, {
		label : "记录人",
		name : "DMR_CRTD_USER_ID"
	}, {
		label : "记录时间",
		name : "DMR_CRTD_DATE"
	}]
</script>
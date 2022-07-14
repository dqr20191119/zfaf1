<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<%@ page import="com.cesgroup.framework.util.ConfigUtil"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<OBJECT id="sgzfOcx" WIDTH="0" HEIGHT="0" CLASSID="CLSID:E60F65AF-6BA5-4015-8070-77A134D5845E" TYPE="application/oleobject"></OBJECT>

<table class="table">
	<tr>
	 	<td>
	 		<div style="width: 200px; height: 500px; padding: 0px 2px; border: 1px solid #4692f0; overflow-x: hidden; overflow-y: auto;">
				<cui:tree id="sszkDepartmentTreeId" onDblClick="sszkQueryTerminal"></cui:tree>		 				
	 		</div>
	 	</td>
	 	<td>
	 		<div style="width: 740px; height: 500px;">
				<cui:toolbar id="toolbarId_sszk" data="toolbar_sszk"></cui:toolbar>			
				<cui:grid id="gridId_sszk" rownumbers="true" multiselect="true" width="auto" fitStyle="fill" datatype="local">
					<cui:gridCols>
						<cui:gridCol name="pid" hidden="true">人员id</cui:gridCol>
						<cui:gridCol name="deptid" hidden="true">部门id</cui:gridCol>
						<cui:gridCol name="dkey" hidden="true">部门key</cui:gridCol>
						<cui:gridCol name="deptName" hidden="true">部门名称</cui:gridCol>
						<cui:gridCol name="id" hidden="true">终端id</cui:gridCol>
						<cui:gridCol name="la" hidden="true">实时纬度</cui:gridCol>
						<cui:gridCol name="lon" hidden="true">实时经度</cui:gridCol>
						<cui:gridCol name="storage" hidden="true">内存</cui:gridCol>
						<cui:gridCol name="battery" hidden="true">电量</cui:gridCol>
						<cui:gridCol name="uid" hidden="true">终端uid</cui:gridCol>
						<cui:gridCol name="sn" hidden="true">sn</cui:gridCol>
						<cui:gridCol name="channelid" hidden="true">固定对讲频道</cui:gridCol>
						<cui:gridCol name="channelidT" hidden="true">临时对讲频道</cui:gridCol>
						<cui:gridCol name="channelName" hidden="true">临时对讲频道名称</cui:gridCol>
						<cui:gridCol name="pno" width="100">人员编号</cui:gridCol>
						<cui:gridCol name="name" width="100">人员名称</cui:gridCol>
						<cui:gridCol name="status" align="center" width="100" formatter="convertCode" revertCode="true" formatoptions="{
						'data': combobox_sszk_status
						}">在线状态</cui:gridCol>
					</cui:gridCols>
				</cui:grid>
			</div>
	 	</td>									 	
	</tr> 
</table>

<script>
	
	var combobox_sszk_status = [{'value':'0', 'text':'不在线'}, {'value':'1', 'text':'在线'}];
	var sgzf = {
		httpApi: new httpApi('${configMap.CSC_URL}'),
		ocxService: new ocxService(),
		userName: '${configMap.CSC_USER_NAME}',
		password: '${configMap.CSC_PASSWORD}'
	};
	
	var toolbar_sszk = [{
		"type" : "button",
		"id" : "btnId_sphj",
		"label" : "视频呼叫",
		"onClick" : "sszkCallVideo",
		"componentCls" : "btn-primary"
	}, {
		"type" : "button",
		"id" : "btnId_yphj",
		"label" : "音频呼叫",
		"onClick" : "sszkCallAudio",
		"componentCls" : "btn-primary"
	}];
	
	$.parseDone(function() {
		
		sgzf.httpApi.login(sgzf.userName, sgzf.password).then(function (data) {
			
	        return sgzf.httpApi.loadOcxInfo(sgzf.userName);
	    }, function (error) {
	    	
	    	$.message({message:"登录失败！", cls:"error"});
	    }).then(function (data) {
	    	
	    	sgzf.ocxService.registOcx(document.getElementById("sgzfOcx"), data.uid, data.pname, data.server, data.ac);
	    }, function (error) {
	    	
	    	$.message({message:"加载ocx信息失败！", cls:"error"});
	    }).then(function (data) {
	    	
	    	sgzf.ocxService.writeConfig("video_path", "<%=ConfigUtil.get("SYSTEM.SGZF.BASE.PATH")%>");
	    }, function (error) {
	    	
	    	$.message({message:"注册ocx失败！", cls:"error"});
	    }).then(function (data) {
	    	
	    	return sgzf.httpApi.fuzzyQueryDepartment(null, null, null);
	    }, function (error) {
	    	
	    	$.message({message:"设置ocx信息失败！", cls:"error"});
	    }).then(function(data) {
	    	
			var departmentList = data.data;
			initDepartmentTree(departmentList);		// 初始化部门树
	    	$("#sszkDepartmentTreeId").tree("reload", departmentList);
	    }, function (error) {
	    	$.message({message:"获取部门信息失败！", cls:"error"});
	    });
	});
	
	/**
	 * 初始化部门树结构
	 */
	function initDepartmentTree(departmentList) {
		
    	for(var i in departmentList) {
    		if(departmentList[i].children && departmentList[i].children.length > 0) {
    			initDepartmentTree(departmentList[i].children);
    		}
    		
   			departmentList[i].name = departmentList[i].dept_name;
   			departmentList[i].pid = departmentList[i].parentid;
    	}
	}
	
	/**
	 * 查询终端
	 */
	function sszkQueryTerminal() {
		
		var data = $("#sszkDepartmentTreeId").tree("getSelectedNodes");
		sgzf.httpApi.queryTerminal(data[0].id).then(function (data) {
			
			$("#gridId_sszk").grid("reload", data);
	    }, function (error) {
	    	$.message({message:"获取终端信息失败！", cls:"error"});
	    });
	}
	
	/**
	 * 音视频呼叫
	 */
	function sszkCallVideo() {
		
		var selarrrow = $("#gridId_sszk").grid("option", "selarrrow");			
		if(selarrrow && selarrrow.length >= 1) {
			
			for(var i = 0; i < selarrrow.length; i++) {
				var callData = $("#gridId_sszk").grid("getRowData", selarrrow[i]);
				sgzf.ocxService.callStream(1, callData);				
			}
		} else {
			$.message({message:"请选择记录！", cls:"waring"});
			return;
		}
	}
	
	/**
	 * 音频呼叫
	 */
	function sszkCallAudio() {
	    
		var selarrrow = $("#gridId_sszk").grid("option", "selarrrow");			
		if(selarrrow && selarrrow.length >= 1) {
			
			for(var i = 0; i < selarrrow.length; i++) {
				var callData = $("#gridId_sszk").grid("getRowData", selarrrow[i]);
				sgzf.ocxService.callStream(2, callData);				
			}
		} else {
			$.message({message:"请选择记录！", cls:"waring"});
			return;
		}
	}
</script>

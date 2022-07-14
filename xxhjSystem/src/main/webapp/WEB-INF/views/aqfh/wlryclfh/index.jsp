<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.cesgroup.prison.common.facade.AuthSystemFacade"%>
<%@page import="com.cesgroup.prison.common.bean.user.UserBean"%>
<%@ page import="com.cesgroup.prison.zbgl.rygl.util.RyCommon"%>
<%@ page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <%
UserBean userInfo = AuthSystemFacade.getLoginUserInfo();
request.setAttribute("cusNumber", userInfo.getCusNumber());
%> 
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<div style="height:100%;">
	<cui:form id="formId_wlryclfh_query">
		<table class="table">
			<tr>	
				<th>复核状态：</th>
				<td>
					<cui:combobox id ="combobox_zt" name ="zt" data="combobox_zt" ></cui:combobox>
				</td>
			</tr>
			<tr>	
				<td>
	 				<cui:button label="查询" componentCls="btn-primary" onClick="search"/>
					<cui:button label="重置" onClick="clear"></cui:button>
				</td>
			</tr>
		</table>
	</cui:form>
	<div style="height: 40px;">
		<cui:toolbar id="toolbarId_wlryclfh_query" data="toolbar_wlryclfh_query"></cui:toolbar>	
	</div>	
	
	<cui:grid id="gridId_wlryclfh_query" rownumbers="true" multiselect="true" width="auto" fitStyle="fill" rowNum="20">
		<cui:gridCols>
			<cui:gridCol name="id" hidden="true">id</cui:gridCol>
			<cui:gridCol name="cusNumber" align="center" formatter="convertCode" revertCode="true" formatoptions="{
			'data': combox_dw
			}">监狱</cui:gridCol>
			<cui:gridCol name="dlmjName" hidden="true">带领民警</cui:gridCol>
			<cui:gridCol name="jjsy" align="center">进监事由</cui:gridCol>
			<cui:gridCol name="jjsj" align="center">进监时间</cui:gridCol>
			<cui:gridCol name="lx" align="center" formatter="convertCode" revertCode="true" formatoptions="{
			'data': combobox_lx
			}">类型</cui:gridCol>
			<cui:gridCol name="zt" align="center" formatter="convertCode" revertCode="true" formatoptions="{
			'data': combobox_zt
			}">状态</cui:gridCol>
		</cui:gridCols>
		<cui:gridPager gridId="gridId_wlryclfh_query"/>
	</cui:grid>

    <cui:dialog id="dialogId_wlryclfh_view" autoDestroy="true" autoOpen="false" resizable="false" iframePanel="true" reLoadOnOpen="true" modal="true" buttons="buttons_wlryclfh_view">
    </cui:dialog>
</div>

<script>
	var combobox_bm = <%=AuthSystemFacade.getAllChildrenOrgJsonInfoByOrgKey(null)%>;
	var combox_dw =<%= AuthSystemFacade.getAllJyJsonInfo()%>;
	var commonbox_ry =<%= RyCommon.getCommonbox3(request.getAttribute("cusNumber").toString())%>;
	var cusNumber = jsConst.CUS_NUMBER;
	var drpmntId = jsConst.DEPARTMENT_ID;
	var USER_LEVEL = jsConst.USER_LEVEL;
	var combobox_zt = [{"value":"0","text":"未复核"},
					   {"value":"1","text":"已复核"},
						];

    var combobox_lx = [{"value":"1","text":"外来人员"},
        {"value":"2","text":"外来车辆"},
    ];
	$.parseDone(function() {
		inItDutyData();
		search(); 
	});

		var combobox_xb =[{"value":"0","text":"男"},
            {"value":"1","text":"女"},
        ];


    var buttons_wlryclfh_view = [{
        text: "关闭",
        id: "btnId_cancel",
        click: function () {

            $("#dialogId_wlryclfh_view").dialog("close");
        }
    }];
	
	var toolbar_wlryclfh_query = [{
		"type" : "button",
		"id" : "btnId_zb",
		"label" : "复核",
		"onClick" : "startFh",
		"componentCls" : "btn-primary"
	},{
        "type" : "button",
        "id" : "btnId_view",
        "label" : "查看",
        "onClick" : "openDailog",
        "componentCls" : "btn-primary"
    }];

    function openDailog(event, ui) {

        var url;
        var selarrrow = $("#gridId_wlryclfh_query").grid("option","selarrrow");
        var rowData = $("#gridId_wlryclfh_query").grid("getRowData", selarrrow[0]);

        if(selarrrow && selarrrow.length == 1 ) {

             if(ui.id == "btnId_view") {
                url = "${ctx}/aqfh/wlryclfh/toView?id="+rowData.id+"&lx="+rowData.lx;
                $("#dialogId_wlryclfh_view").dialog({
                    width :1200,
                    height : 800,
                    title : ui.label,
                    url : url
                });
                $("#dialogId_wlryclfh_view").dialog("open");
            }

        } else {
            $.message({message:"请选择一条记录！", cls:"waring"});
            return;
        }
    }
    
    
    
	function inItDutyData() {
		$.ajax({
			type : 'post',
			url : '${ctx}/aqfh/wlryclfh/inItDutyData',
			dataType : 'json',
			async: false,
			success : function(data) {
					if(data.code==200){
						/* if(data.message!='true'){
							$.message({message:data.message, cls:"waring"});
						} */
					}
			},
		});
	}
	
	
	function search() {
		var formData = {};
		formData.cusNumber = cusNumber;
		formData.zt = $("#combobox_zt").combobox("getValue");
		$("#gridId_wlryclfh_query").grid("option", "postData", formData);
		$("#gridId_wlryclfh_query").grid("reload","${ctx}/aqfh/wlryclfh/searchData"); 
	}
	
	function clear() {
			$("#formId_wlryclfh_query").form("reset");
	}
	
	
	
	
	
	
	
	
	function startFh() {
		var selarrrow = $("#gridId_wlryclfh_query").grid("option","selarrrow");
		var rowData = $("#gridId_wlryclfh_query").grid("getRowData", selarrrow[0]);
		var zt = rowData.zt;
		if(selarrrow && selarrrow.length == 1 ) {
			var params = {};
			params.id = rowData.id;
			params.zt = "1";
			$.ajax({
				type : 'post',
				url : '${ctx}/aqfh/wlryclfh/updateById',
				data: params,
				dataType : 'json',
				async: false,
				success : function(data) {
						if(data.code==200){
							$.message({message:"审核成功！", cls:"success"});
							$("#gridId_wlryclfh_query").grid("reload");
						}
				}
			});
			
		}else{
			$.message({message:"请选择一条记录！", cls:"waring"});
			return;
		}	
		
	}
	
		
	
	
</script>
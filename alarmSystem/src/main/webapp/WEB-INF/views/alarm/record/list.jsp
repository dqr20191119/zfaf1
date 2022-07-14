<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<%@page import="com.ces.prison.common.constants.GroupKeyConst"%>
<%@page import="com.ces.prison.common.constants.SystemConst"%>
<%@taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<style>
.form-control {
	width: 100%;
}






.flowHandleList {
    text-align: center;
    font-size: 23px;
    font-weight: bold;
}
.flowHandleList span{
    font-size: 23px;
    font-weight: bold;
}
#flowDiv table{
    width: 1000px;
    height: 500px;
    margin: auto;
}

#flowDiv table textarea{
    height: 100px;
    width: 400px;
    text-align: left;
    line-height: 100px;

}



#flowDiv table input{
    text-align: center;
    height: 50px;
    width: 300px;

}

</style>


<cui:dialog id="dialogId_alarm_detail" iframePanel="true" autoOpen="false" resizable="false" autoDestroy="true" modal="true" buttons="dia_button">
	<cui:form id="formId_info">
		<table class="table" style="width: 98%">
			<tr>
				<th>报警日期：</th>
				<td>
					<cui:input componentCls="form-control" name="ALARM_DATE" readonly="true"></cui:input>
				</td>
				<th>报警时间：</th>
				<td>
					<cui:input componentCls="form-control" name="ALARM_TIME" readonly="true"></cui:input>
				</td>
				<th>报警地点：</th>
				<td>
					<cui:input componentCls="form-control" name="ALARM_ADDRESS" readonly="true"></cui:input>
				</td>
				<th>报警人：</th>
				<td>
					<cui:input componentCls="form-control" name="ARD_ALARM_POLICE" readonly="true"></cui:input>
				</td>
			</tr>

			<tr>
				<th>所属区域：</th>
				<td>
					<cui:input componentCls="form-control" name="AREA" readonly="true"></cui:input>
				</td>
				<th>报警等级：</th>
				<td>
					<cui:combobox name="ARD_ALERT_LEVEL_INDC" data="bjdj_data" componentCls="form-control" readonly="true"></cui:combobox>
				</td>
				<th>报警类型：</th>
				<td>
					<cui:combobox name="ARD_TYPE_INDC" data="bjlx_data" componentCls="form-control" readonly="true"></cui:combobox>
				</td>
				<th>事件性质：</th>
				<td>
					<cui:input componentCls="form-control" name="ARD_EVENT_TYPE" readonly="true"></cui:input>
				</td>
			</tr>

			<tr>
				<th>接警人：</th>
				<td>
					<cui:input componentCls="form-control" name="ARD_RECEIVE_ALARM_POLICE" readonly="true"></cui:input>
				</td>
				<th>接警时间：</th>
				<td>
					<cui:input componentCls="form-control" name="ARD_RECEIVE_TIME" readonly="true"></cui:input>
				</td>
				<%--<th>处置人：</th>
				<td>
					<cui:input componentCls="form-control" name="ARD_OPRTR" readonly="true"></cui:input>
				</td>--%>
				<%--<th>处置时间：</th>
				<td>
					<cui:input componentCls="form-control" name="ARD_OPRTN_TIME" readonly="true"></cui:input>
				</td>--%>
                <th>接警状态：</th>
                <td>
                    <cui:combobox name="ARD_RECEIVE_STTS" data="jjzt_data" componentCls="form-control" readonly="true"></cui:combobox>
                </td>
			</tr>

			<tr>

				<th>处置状态：</th>
				<td>
					<cui:combobox name="ARD_OPRTN_STTS_INDC" data="czzt_data" componentCls="form-control" readonly="true"></cui:combobox>
				</td>
				<th>完成时间：</th>
				<td>
					<cui:input componentCls="form-control" name="ARD_OPRTN_TIME" readonly="true"></cui:input>
				</td>
				<%--<th>确认人：</th>
				<td>
					<cui:input componentCls="form-control" name="ARD_FINISH_SURE_POLICE" readonly="true"></cui:input>
				</td>--%>
			</tr>
            <%--<tr>
                <th>现场情况：</th>
                <td colspan="3">
                    <cui:textarea componentCls="form-control" id="xcqk" readonly="true"></cui:textarea>
                </td>
                <th>处置情况：</th>
                <td colspan="3">
                    <cui:textarea componentCls="form-control" id="czqk" readonly="true"></cui:textarea>
                </td>
            </tr>
            <tr>
                <th>处置结果：</th>
                <td colspan="3">
                    <cui:textarea componentCls="form-control" id="czjg" readonly="true"></cui:textarea>
                </td>
                <th>备注：</th>
                <td colspan="3">
                    <cui:textarea componentCls="form-control" id="bz" readonly="true"></cui:textarea>
                </td>
            </tr>--%>
		</table>
	</cui:form>
    <div id = "flowDiv">
        <%--<div>
            <ul id="flowHandleList">
            </ul>
        </div>
        <div  style="width: 700px;margin: 40px 0px 20px 30px" >
            <table>
                <tr>
                    <th>现场情况：</th>
                    <td style="width: 600px">
                        <cui:textarea componentCls="form-control" id="xcqk" readonly="true"></cui:textarea>
                    </td>
                </tr>
                <tr>
                    <th>处置情况：</th>
                    <td style="width: 600px">
                        <cui:textarea componentCls="form-control" id="czqk" readonly="true"></cui:textarea>
                    </td>
                </tr>
                <tr>
                    <th>处置结果：</th>
                    <td style="width: 600px">
                        <cui:textarea componentCls="form-control" id="czjg" readonly="true"></cui:textarea>
                    </td>

                </tr>
                <tr>
                    <th>备注：</th>
                    <td style="width: 500px">
                        <cui:textarea componentCls="form-control" id="bz" readonly="true"></cui:textarea>
                    </td>
                </tr>
            </table>
        </div>--%>
    </div>

</cui:dialog>


<div style="height: 100%;">
	<cui:form id="formId_query">
		<table class="table">
			<tr>
				<th>报警等级：</th>
				<td>
					<cui:combobox name="ardAlertLevelIndc" id="ardAlertLevelIndc" data="bjdj_data" componentCls="form-control"></cui:combobox>
				</td>

				<th>报警类型：</th>
				<td>
					<cui:combobox name="ardTypeIndc" id="ardTypeIndc" data="bjlx_data" componentCls="form-control"></cui:combobox>
				</td>

				<th>处置状态：</th>
				<td>
					<cui:combobox name="ardOprtnSttsIndc" id="ardOprtnSttsIndc" componentCls="form-control" data="czzt_data"></cui:combobox>
				</td>

			</tr>
			<tr>
				<th>报警日期：</th>
				<td>
					<cui:datepicker componentCls="form-control" id="startTime" name="startTime" dateFormat="yyyy-MM-dd HH:mm:ss"></cui:datepicker>
				</td>
				<th align="center">至</th>
				<td>
					<cui:datepicker componentCls="form-control" name="endTime" id ="endTime" startDateId="startTime" dateFormat="yyyy-MM-dd HH:mm:ss"></cui:datepicker>
				</td>
                <th>监所名称：</th>
                <td>
                    <cui:combobox name="ardCusNumber" id="ardCusNumber" componentCls="form-control" data="combobox_jy"></cui:combobox>
                </td>
				<td>
					<cui:button label="查询" onClick="query()"></cui:button>
					<cui:button label="重置" onClick="reset"></cui:button>
				</td>
			</tr>
		</table>
	</cui:form>

	<cui:toolbar data="toolbar_localDate"></cui:toolbar>
	<cui:grid id="gridId_alarmRecord" rownumbers="true" singleselect="true" fitStyle="fill" width="auto" colModel="gridId_alarmRecord_colModelDate">
		<cui:gridPager gridId="gridId_alarmRecord" />
	</cui:grid>
</div>

<cui:dialog id="dialogId_alarm" autoOpen="false" resizable="false" reLoadOnOpen="true" modal="true"></cui:dialog>
<script>
	var jsConst = window.top.jsConst;

	var cusNumber = jsConst.CUS_NUMBER;//监狱号

	var userId = jsConst.USER_ID;//当前登陆者

	var czzt_data = <%=CodeFacade.loadCode2Json("4.20.26")%>;//处置状态

	var bjdj_data = <%=CodeFacade.loadCode2Json("4.20.25")%>;//报警等级

	var bjlx_data = <%=CodeFacade.loadCode2Json("4.20.27")%>;//报警类型
	
	var jjzt_data = <%=CodeFacade.loadCode2Json("4.20.39")%>;//接警状态
	//从编码表中取监狱 
	var combobox_jy = <%=CodeFacade.loadCode2Json(GroupKeyConst.GROUP_CODE_KEY_JY)%>;
	
	$.parseDone(function() {
		var url = "${ctx}/alarm/alarmRecordList.json";
		var type ='${type}';
        $("#ardCusNumber").combobox( "setValue",cusNumber );
		if(jsConst.USER_LEVEL == 1){
			url = url + "?type="+type+"&P_orders=ard_oprtn_stts_indc,asc,ard_alert_level_indc,asc,ard_crte_time,desc";
		} else{
			url = url + "?type="+type+"&ardCusNumber=" + cusNumber + "&P_orders=ard_oprtn_stts_indc,asc,ard_alert_level_indc,asc,ard_crte_time,desc";
            $("#ardCusNumber").combobox( "option","disabled",true);
		} /*else if(jsConst.USER_LEVEL == 3){
			url = url + "?type="+type+"&ardCusNumber=" + cusNumber + "&dprtmntId=" + jsConst.DEPARTMENT_ID + "&P_orders=ard_oprtn_stts_indc,asc,ard_alert_level_indc,asc,ard_crte_time,desc";
            $("#ardCusNumber").combobox( "option","disabled",true);
		}*/
		$("#gridId_alarmRecord").grid("reload", url);

	});

	function query() {
		var formData = $("#formId_query").form("formData");
		var STime =$("#startTime").datepicker("getValue");
		var ETime =$("#endTime").datepicker("getValue");
		$('#gridId_alarmRecord').grid('option', 'postData', formData);
		$("#gridId_alarmRecord").grid("reload","${ctx}/alarm/searchRecordList.json?DpName="+jsConst.PRISON_ORG_CODE+"&STime="+STime+"&ETime="+ETime+"&P_orders=ard_crte_time,desc,ard_oprtn_stts_indc,asc,ard_alert_level_indc,asc");
	}

	//重置按钮事件
	var reset = function() {
		$("#formId_query").form("reset");
	}
	var gridId_alarmRecord_colModelDate = [ {
		label : "id",
		name : "ID",
		width : "70",
		hidden : true
	}, {
		label : "监所名称",
		name : "ARD_CUS_NUMBER",
		width : "95", 
		align : "center",
		formatter : "convertCode",  revertCode : true, formatoptions : { 'data':combobox_jy } 
	}, {
		label : "报警等级",
		align : "center",
		name : "ARD_ALERT_LEVEL_INDC",
		width : "75",
		cellattr : function(o) {
		    if ( o.rawObject.ARD_ALERT_LEVEL_INDC == "1"  ) {
	        	return 'style="color: #FF3030"';
		    }
		    if ( o.rawObject.ARD_ALERT_LEVEL_INDC == "2"  ) {
	        	return 'style="color: #FFC125"';
		    }
		    if ( o.rawObject.ARD_ALERT_LEVEL_INDC == "3"  ) {
	        	return 'style="color: #6495ED"';
		    }
		},
		formatter : "convertCode",
	    revertCode : true,
	    formatoptions : { 'data':bjdj_data } 
	}, {
		label : "报警日期",
		align : "center",
		name : "ALARM_DATE",
		width : "85"
	}, {
		label : "报警时间",
		align : "center",
		name : "ALARM_TIME",
		width : "75"
	}, {
		label : "报警类型",
		name : "ARD_TYPE_INDC",
		width : "75",
		align : "center",
        formatter : "convertCode",
        revertCode : true,
        formatoptions : { 'data':bjlx_data } 
	}, {
		label : "报警地点",
		align : "center",
		name : "ALARM_ADDRESS",
		width : "200"
	}/* , {
		label : "报警设备",
		name : "ALERTOR_NAME",
		width : "130"
	} */, {
		label : "处置状态",
		name : "ARD_OPRTN_STTS_INDC",
		width : "75",
		cellattr : function(o) {
		    if ( o.rawObject.ARD_OPRTN_STTS_INDC != "3"  ) {
	        	return 'style="color: #FF3030"';
		    }
		},
		align : "center",
		formatter : "convertCode",
	    revertCode : true,
	    formatoptions : { 'data':czzt_data } 
	}, {
		label : "接警状态",
		name : "ARD_RECEIVE_STTS",
		width : "75",
		align : "center",
		formatter : "convertCode",
	    revertCode : true,
	    formatoptions : { 'data':jjzt_data } 
	}, {
		label : "归档状态",
		name : "ARD_FILE_STTS",
		width : "80",
		align : "center",
		formatter : function(cellvalue, options, rawObject) {
			if(cellvalue == "1"){
				return "已归档";	
			}else {
				return "未归档";
			}
		}
	}, {
		label : "接警人",
		align : "center",
		name : "ARD_RECEIVE_ALARM_POLICE",
	}, {
		label : "操作",
		name : "check",
		align : "center",
		formatter : "Formatter"
	}  ];

	toolbar_localDate = [ {
		"type" : "button",
		"id" : "toolbarIcon",
		"label" : "处置",
		"onClick" : "handle",
	}, {
		"type" : "button",
		"id" : "toolbarIcon",
		"label" : "信息归档",
		"onClick" : "openEventDailog",
	} ];
	
	
	function Formatter(cellValue, options, rowObject) {
		var param1 = rowObject.ID;
		var param2 = rowObject.ARD_RECEIVE_STTS;
		//var result = '<a href="" style="color: #4692f0;" onclick="getAlarmInfo(\''+param1.toString()+'\');return false;">查看</a>';
		var result = "<button class=\"ctrl-init ctrl-init-button coral-button coral-component coral-state-default coral-corner-all coral-button-text-only\" onClick= \"getAlarmInfo('" + param1 + "')\">查看</button>" ;
		
		if(rowObject.ARD_OPRTN_STTS_INDC != "3"){
			result = result + "<button style=\"margin :3px;\" class=\"ctrl-init ctrl-init-button coral-button coral-component coral-state-default coral-corner-all coral-button-text-only\" onClick= \"alarmReceive('" + param1 + "','" + param2 + "')\">快速处置</button>" ;
		}
		return result;
	}

	//后台请求数据，根据id获取报警记录信息
	function getAlarmInfo(recordId) {
		$('#formId_info').form("clear");
		$("#dialogId_alarm_detail").dialog({
			width : 1200,
			height : 800,
			title : '记录详情',
		});
		$("#dialogId_alarm_detail").dialog("open");
		$("#dialogId_alarm_detail").loading({ text : "正在请求报警信息详情信息，请稍后..."});
		var url = "${ctx}/alarm/findById.json?id=" + recordId;
		$.ajax({
			type : 'post', //方法类型  
			url : url,//请求地址     
			dataType : 'json', //数据类型  
			success : callback //请求成功处理函数  
		});
		function callback(res) { //返回函数
			if (res.success) {
				$('#formId_info').form("load", res.obj);//刷新数据
                //加载流程记录
                loadFlowDtlsRecord(recordId);
			} else {
				$.messageQueue({ message : res.msg, cls : "warning", iframePanel : true, type : "info" });
			}
			$("#dialogId_alarm_detail").loading("hide");
		}
	}
    /**
     * 加载流程记录
     *
     * */
	function loadFlowDtlsRecord(recordId) {
        $.ajax({
            type : 'post', //方法类型
            url : '${ctx}/flow/ListByAlarmRecordId',//请求地址
            data:{recordId:recordId},
            dataType : 'json', //数据类型
            success : function (data) {
                if(data.code ==200){
                    $("#flowDiv").show();
                    loadFlowDtls(data.data,recordId);
                }else{
                    $("#flowDiv").hide();
                }
            }
        });
    }

    /**
     * 加载流程步骤详情
     * @param msg
     * @param recordId
     */
    function loadFlowDtls(msg,recordId) {
        var flowHtml="";
        for (var i =0;i<msg.length ; i++){
          //  flowHtml+="<li> <span style='color: red'> 第"+ (i + 1) +"步:</span><span>"+msg[i].HFD_FLOW_ITEM_NAME +"</span><button id ='flowDtlsId_" + i + "' style='margin-left: 30px'  class=\"ant-btn ant-btn-red\" onClick= \"searchFlowDtls('" + msg[i].HFD_FLOW_ID+"','"+recordId+"','"+msg[i].ID+ "')\">" +"查看处置流程记录" + "</button></li>";
         // var flowsDtlsData =  searchFlowDtls(msg[i].HFD_FLOW_ID,recordId,msg[i].ID);

            var params = {};
            params["hfdaAlertRecordDtlsId"] =recordId;
            params["hfdaFlowId"] = msg[i].HFD_FLOW_ID;
            params["hfdaFlowDtlsId"] = msg[i].ID;
            $.ajax({
                type : 'post',
                url : '${ctx}/flow/getByFlowDtlsAlarmRecord',
                data : params,
                dataType : 'json',
                async:false,
                success : function(data) {
                    if (data.code ==200 && data.data !=null) {
                        var obj = data.data;
                        flowHtml +="<div class='flowHandleList' style='color: red'>第" +(i+1)+ "步:<span>"+msg[i].HFD_FLOW_ITEM_NAME+"</span></div>";
                        flowHtml +="<div  style='width: 100%;'><table>" +
                            "<tr><th>处置人：</th><td ><input  readonly='true' value='"+obj.hfdaUpdateName+"'></input></td><th>处置时间：</th><td ><input  readonly='true' value='"+obj.hfdaUpdateTime+"'></input></td></tr>" +
                            "<tr><th>现场情况：</th><td ><textarea  readonly='true'>"+(obj.hfdaXcqk==null?'':obj.hfdaXcqk)+"</textarea></td><th>处置情况：</th><td ><textarea  readonly='true'>"+(obj.hfdaCzqk==null?'':obj.hfdaCzqk)+"</textarea></td></tr>" +
                            "<tr><th>处置结果：</th><td ><textarea readonly='true'>"+(obj.hfdaCzjg==null?'':obj.hfdaCzjg)+"</textarea></td><th>备注：</th><td ><textarea  readonly='true'>"+(obj.hfdaBz==null?'':obj.hfdaBz)+"</textarea></td></tr>" +
                            "</table></div>";
                    }
                }
            });
        }
        $("#flowDiv").html(flowHtml);
    }

    function searchFlowDtls(flowId,recordId,flowDtlsId) {
	    console.log("flowId="+flowId+"........recordId"+recordId+".......flowDtlsId="+flowDtlsId);
        var params = {};
        var result;
        params["hfdaAlertRecordDtlsId"] =recordId;
        params["hfdaFlowId"] = flowId;
        params["hfdaFlowDtlsId"] = flowDtlsId;
        $.ajax({
            type : 'post',
            url : '${ctx}/flow/getByFlowDtlsAlarmRecord',
            data : params,
            dataType : 'json',
            success : function(data) {
                if (data.code ==200 && data.data !=null) {
                    debugger
                    var obj = data.data;
                    result = obj;
                }
            }
        });
        return result;
    }




	function openEventDailog(event, ui) {
		var selrow = $("#gridId_alarmRecord").grid("option", "selrow");
		if (selrow != null) {
			var rowData = $("#gridId_alarmRecord").grid("getRowData", selrow.toString());
			if (rowData.ARD_OPRTN_STTS_INDC == "3") {
				var type = rowData.ARD_FILE_STTS == "已归档" ? "1" : "0";
				$("#dialogId_alarm").dialog( {
					width : "800",
					height : "735",
					url : "${ctx}/alarm/openDialog/files?id=" + rowData.ID + "&type=" + type,
					title : "归档",
				});
				$("#dialogId_alarm").dialog("open");
			} else {
				$.messageQueue({ message : "请选择处置完成的记录", cls : "warning", iframePanel : true, type : "info" });
			}
		} else {
			$.messageQueue({ message : "请选择要归档的记录", cls : "warning", iframePanel : true, type : "info" });
		}
	}

	function handle() {
		var selrow = $("#gridId_alarmRecord").grid("option", "selrow");
		if (selrow != null) {
			var rowData = $("#gridId_alarmRecord").grid("getRowData", selrow.toString());
			/* if (getHour(rowData.ALARM_DATE + " " + rowData.ALARM_TIME) > 24){ //允许报警处置的时间，24小时
				$.messageQueue({ message : "已超过允许处置的最大时限 24 小时", cls : "warning", iframePanel : true, type : "info" });
				return;
			} */
			if (rowData.ARD_OPRTN_STTS_INDC != "3") {
				if (jsConst.USER_LEVEL == 1){
					//请求查询报警记录 临时
					$.ajax({
						type : "post",
						url : "${ctx}/alarm/findById.json?id=" + rowData.ID,
						dataType : "json",
						success : function(data) {
							if (data.success) {
								if(data.obj.ARD_RECEIVE_STTS == null || data.obj.ARD_RECEIVE_STTS == "0"){//接警状态  0、未接警
									var is3D = jsConst.MAP_TYPE == "0" ? false : true;
									if (is3D) {
										jsConst.ALARM_INFO = {'areaId' : data.obj.AREA_ID, 'alarmDeviceId' : data.obj.ALERTOR_ID, 'cusNumber': cusNumber}
										map.loadMapInfo(data.obj.ARD_CUS_NUMBER, getCusName(data.obj.ARD_CUS_NUMBER));//3维地图切换
									}
									$("#layout1").layout("panel", "east").panel("refresh", "${ctx}/alarm/handle/index?isList=1&type=0&id=" + rowData.ID);
									$("#dialog").dialog("close");
								 } else {
									$.messageQueue({ message : "该报警已被接警", cls : "warning", iframePanel : true, type : "info" });
								 }
							} else {
								$.messageQueue({ message : data.msg, cls : "warning", iframePanel : true, type : "info" });
							}
						},
						error : function(XMLHttpRequest, textStatus, errorThrown) {
							$.messageQueue({ message : textStatus, cls : "warning", iframePanel : true, type : "info" });
						}
					});
				} else {
					toDisplay('homeMenu');
					$("#layout1").layout("panel", "east").panel("refresh", "${ctx}/alarm/handle/index?isList=1&type=0&id=" + rowData.ID);
					$("#dialog").dialog("close");
				}
			} else {
				$.messageQueue({ message : "请选择未处置完成的记录", cls : "warning", iframePanel : true, type : "info" });
			}

		} else {
			$.messageQueue({ message : "请选择要处置的记录", cls : "warning", iframePanel : true, type : "info" });
		}
	}

	var dia_button = [ {
		text : "关闭",
		width : 80,
		click : function() {
			$("#dialogId_alarm_detail").dialog("close");
		}
	} ];
	
	
	//日期比较，获取时间差（小时）
	 function getHour(time) {
	      var  s1 = new Date(time.replace(/-/g, '/'));
	      var  s2 = new Date();
	      var ms = Math.abs(s1.getTime() - s2.getTime());
	      return ms/1000/60/60;
	 }
	
	//改变接警的状态
	function changereceiveFlag(recordID) {
		var data = {};
		data["id"] = recordID;
		data["ardReceiveStts"] = "1";//接警
		var url = '${ctx}/alarm/update/record.json';
		$.ajax({
			type : 'post', //方法类型  
			url : url,//请求地址     
			data : data,
			dataType : 'json', //数据类型  
			success : callBack //请求成功处理函数  
		});
		var callBack = function(resDate) {
			if (resDate.success) {
				receiveFlag = true;
			} else {
				$.messageQueue({ message : resDate.msg, cls : "warning", iframePanel : true, type : "info" });
			}
		};
	}
	
	
	 function alarmReceive(recordID,receiveStts) {
		if(receiveStts != '1'){
			changereceiveFlag(recordID);//改变接警的状态
		}
		$("#dialogId_alarm").dialog({
			width : 800,
			height : 800,
			url : "${ctx}/alarm/openDialog/receive?id=" + recordID,
			title : "接警处置",
		});
		$("#dialogId_alarm").dialog("open");

	}
	  
</script>
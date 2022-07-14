<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<%@taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import=" com.cesgroup.prison.common.constant.AuthSystemConst"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<style type="text/css">
 .form-control {
	width: 100%;
}
 

.alarm_level {
	width: 100%;
	
	display: none;
}

.notData {
	display: none;
	color: #666666;
	margin-top: 190px;
}

.form-control_hide {
	display: none;
}

 .ant-btn {
     line-height: 1.499;
     position: relative;
     display: inline-block;
     font-weight: 400;
     white-space: nowrap;
     text-align: center;
     background-image: none;
     border: 1px solid transparent;
     -webkit-box-shadow: 0 2px 0 rgba(0,0,0,0.015);
     box-shadow: 0 2px 0 rgba(0,0,0,0.015);
     cursor: pointer;
     -webkit-transition: all .3s cubic-bezier(.645, .045, .355, 1);
     transition: all .3s cubic-bezier(.645, .045, .355, 1);
     -webkit-user-select: none;
     -moz-user-select: none;
     -ms-user-select: none;
     user-select: none;
     -ms-touch-action: manipulation;
     touch-action: manipulation;
     height: 32px;
     padding: 0 15px;
     font-size: 14px;
     border-radius: 4px;
     color: rgba(0,0,0,0.65);
     background-color: #fff;
     border-color: #d9d9d9;
 }

 .ant-btn-red {
     color: #fff;
     background-color: #FF5A44;
     border-color: #FF5A44;
     text-shadow: 0 -1px 0 rgba(0,0,0,0.12);
     -webkit-box-shadow: 0 2px 0 rgba(0,0,0,0.045);
     box-shadow: 0 2px 0 rgba(0,0,0,0.045);
 }

 .ant-btn-gray{
     color: #fff;
     background-color: #CCCCCC;
     border-color: #CCCCCC;
     text-shadow: 0 -1px 0 rgba(0,0,0,0.12);
     -webkit-box-shadow: 0 2px 0 rgba(0,0,0,0.045);
     box-shadow: 0 2px 0 rgba(0,0,0,0.045);
 }

</style>

<cui:input id="flag" componentCls="form-control_hide" value="0"></cui:input>
<!-- 0代表没有接警人，1代表有接警人 -->
<cui:input id="flowId" componentCls="form-control_hide"></cui:input>
<!-- 流程id -->

<div class="receiveDiv" style="display: none">
	<cui:form id="formId_alarmInfo">
		<table class="table" style="width: 100%;">
			<tr>
				<th>报警时间：</th>
				<td>
					<cui:datepicker name="ARD_ALERT_TIME" componentCls="form-control" readonly="true"></cui:datepicker>
				</td>
				<th>报警地点：</th>
				<td>
					<cui:input name="ALARM_ADDRESS" componentCls="form-control" readonly="true"></cui:input>
				</td>
			</tr>
			<tr>
				<th>报警类型：</th>
				<td>
					<cui:combobox name="ARD_TYPE_INDC" componentCls="form-control" data="bjlx_data" readonly="true"></cui:combobox>
				</td>
				<th>报警人：</th>
				<td>
					<cui:input name="ARD_ALARM_POLICE" componentCls="form-control" readonly="true"></cui:input>
				</td>
			</tr>
			<tr>
                <input id="flowDtlsAlarmRecordId"  type="hidden"><%--报警流程记录表的id--%>
                <input id="orderNumber"  type="hidden"><%--当前步骤--%>
                <input id="flowMax"   type="hidden"><%--流程总数--%>
                <input id="alarmFlowId"   type="hidden">
                <input id="flowDtsId"   type="hidden">
				<th>事件性质：</th>
				<td colspan="3">
					<cui:input id="flowName" name="ARD_EVENT_TYPE" componentCls="form-control" readonly="true"></cui:input>
				</td>
			</tr>
            <tr>
                <th>现场情况：</th>
                <td colspan="3">
                    <cui:textarea id="hfdaXcqk" componentCls="form-control" name="hfdaXcqk"></cui:textarea>
                </td>
            </tr>
            <tr>
                <th>处置情况：</th>
                <td colspan="3">
                    <cui:textarea id="hfdaCzqk" componentCls="form-control" name="hfdaCzqk"></cui:textarea>
                </td>
            </tr>
            <tr>
                <th>处置结果：</th>
                <td colspan="3">
                    <cui:textarea id="hfdaCzjg" componentCls="form-control" name="hfdaCzjg"></cui:textarea>
                </td>
            </tr>
            <tr>
                <th>备注：</th>
                <td colspan="3">
                    <cui:textarea id="hfdaBz" componentCls="form-control" name="hfdaBz" ></cui:textarea>
                </td>
            </tr>
		</table>
	</cui:form>
	<div class="alarm_button" align="center">
		<cui:button onClick="back(2)" label="返回"></cui:button>
		<%--<cui:button id="resetButton" onClick="resetData()" label="重置"></cui:button>--%>
        <cui:button id="nextButton" onClick="nextData(1)" label="下一步"></cui:button>
		<cui:button id="subButton" onClick="subData(0)" label="完成提交"></cui:button>
		<cui:button id="leaderHandle" onClick="subData(1)" label="上级处置" style="display:none;"></cui:button>
		<cui:button id="alarmDone" onClick="alarmDone()" label="报警已处置,返回首页" style="display:none;"></cui:button>
	</div>
</div>
<div class="oprtrDiv">
	<div class="alarm_level">
		<div class="level_title"><span class="baojing1-logo logo"></span>一级报警</div>
		<div class="level_div" id="alarmLevel_1"></div>
	</div>
	<div class="alarm_level" >
		<div class="level_title"><span class="baojing2-logo logo"></span>二级报警</div>
		<div class="level_div" id="alarmLevel_2"></div>
	</div>
	<div class="alarm_level" >
		<div class="level_title"><span class="baojing3-logo logo"></span>三级报警</div>
		<div class="level_div" id="alarmLevel_3"></div>
	</div>
	<div class="alarm_flow" >
	   <ul id="flowHandle">
	   
	   </ul>
	</div>
	<div class="notData">无报警处置流程步骤!</div>
	<div class="alarm_button" align="center">
		<cui:button id="back_1" onClick="back(1)" label="上一步"></cui:button>
		<%--<cui:button onClick="register()" label="报警处置登记"></cui:button>--%>
	</div>
</div>
<script>
	var jsConst = window.top.jsConst;
	var alarmRecordId = "${ID}";
	var oprtnStts = null; //处置状态

	$.parseDone(function() {
		getAlarmMsg();//请求查询报警记录
	});

	function init(alarmMsg) {
		var alarmEventId = alarmMsg.ARD_EVENT_ID;
		var alarmEvent = alarmMsg.ARD_EVENT_TYPE;
		oprtnStts = alarmMsg.ARD_OPRTN_STTS_INDC;

		if (alarmEventId == null || alarmEventId == "") {
			loadEventType(alarmMsg.ARD_CUS_NUMBER); //如果报警记录没有关联流程，进入流程选择页
		} else {
			$("#back_1").hide();
			changeEventType(alarmEventId, alarmEvent);
		}

		var alarmReceive = alarmMsg.ARD_RECEIVE_ALARM_POLICE;//接警人
		if (alarmReceive == null || alarmReceive == "") {
			$("#leaderHandle").show();//没有接警人 ，提交上级可用
		} else {
			$("#flag").textbox("setValue", "1");//代表有接警人
		}
		if (oprtnStts == "3") {//处置状态 3 完成
			$("#resetButton").hide();
			$("#subButton").hide();
			$("#leaderHandle").hide();
			$("#alarmDone").show();
			//$("#alarmPolice").textbox("option","readonly",true);
			$("#localCase").textbox("option", "readonly", true);
			$("#oprtnDesc").textbox("option", "readonly", true);
		}

		if (oprtnStts == null || oprtnStts == "1") {
			//如果处置状态为 1 或者 null ，修改处置状态
			changeOprtnStts(alarmRecordId);

		}

	}
	
	function alarmDone() {
		if(is3D){
			map.removeAlarm();//删除图层
		}else{
			$.sign_show.stopAlarm(alarmDeviceId);
		}
		alermMusic(false);
		toDisplay('homeMenu');//人工报警界面转到主页
		$("#dialogId_bjcz").dialog("close");
	}

	//修改处理状态为 2 处理中
	function changeOprtnStts(id) {
		var data = {};
		data["id"] = id;
		data["ardOprtnSttsIndc"] = "2";//处理中
		$.ajax({
			type : 'post',
			url : '${ctx}/alarm/update/record.json',
			data : data,
			dataType : 'json',
			success : function(data) {
				if (data.success) {
				} else {
					$.messageQueue({ message : data.msg, cls : "warning", iframePanel : true, type : "info" });
				}
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$.messageQueue({ message : textStatus, cls : "warning", iframePanel : true, type : "info" });
			}
		});
	}

	//获得报警信息
	function getAlarmMsg() {
		$.ajax({
			type : "post",
			url : "${ctx}/alarm/findById.json?id=${ID}",
			dataType : "json",
			success : function(data) {
				if (data.success) {
					$("#formId_alarmInfo").form("load", data.obj);
					init(data.obj);
				} else {
					$.messageQueue({ message : data.msg, cls : "warning", iframePanel : true, type : "info" });
				}
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$.messageQueue({ message : textStatus, cls : "warning", iframePanel : true, type : "info" });
			}
		});
	}

	/**
	 * 查询处置流程
	 */
	function loadEventType(cus) {

		var data = {};
		data["cusNumber"] = cus;
		data["cusNum"] = "1100";
		 
		$(".alarm_level").show();
		$.ajax({
			type : 'post',
			url : '${ctx}/flow/findMaster.json',
			data : data,
			dataType : 'json',
			success : function(data) {
				if (data.success) {
					initEventType(data.obj)
				} else {
					$.messageQueue({ message : data.msg, cls : "warning", iframePanel : true, type : "info" });
				}
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$.messageQueue({ message : textStatus, cls : "warning", iframePanel : true, type : "info" });
			}
		});
	}
	/**
	 * 加载处置流程
	 */
	function initEventType(msg) {
		if(msg.length != 0){
			for (var i = 0; i < msg.length; i++) {
				var alarmLevel = msg[i].HFM_ALARM_LEVEL;
				var flowId = msg[i].ID;
				var flowName = msg[i].HFM_FLOW_NAME;
				$("#alarmLevel_" + alarmLevel).append( "<button id ='flowId_" + flowId + "' class=\"level_type\" onClick= \"changeEventType('" + flowId + "')\">" + flowName + "</button>");
			}
			$('.level_type').button({ componentCls : "btn-lg" });
		}else{
			$(".alarm_level").hide();
			$(".notData").show();
			$(".alarm_button").show(); 
		}
	}
	/**
	 * 查询处置流程详情
	 */
	function changeEventType(flowId, flowName) {
		$("#flowId").textbox("setValue", flowId);

		if (flowName ==  undefined || flowName ==  "" ) {
			flowName = $("#flowId_" + flowId).text();
			$("#flowName").textbox("setText", flowName);
		}
		var data = {};
		data["cusNumber"] = jsConst.CUS_NUMBER;
		data["cusNum"] = "1100";
		if (flowId != null && flowId != "") {
			data["flowId"] = flowId;
		}
		$(".alarm_level").hide();
		$.ajax({
			type : 'post',
			url : '${ctx}/flow/findFlowDtls.json',
			data : data,
			dataType : 'json',
			success : function(data) {
				if (data.success) {
					loadFlowHandle(data.obj, flowName)
				} else {
					$.messageQueue({ message : data.msg, cls : "warning", iframePanel : true, type : "info" });
				}
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$.messageQueue({ message : textStatus, cls : "warning", iframePanel : true, type : "info" });
			}
		});
	}
    /**
     * 加载处置流程详情
     */
    var $flowDtlsId ="#flowDtlsId_";
    function loadFlowHandle(msg, flowName) {
        if (window.top.isNotNull(msg) && msg != "") {
            $(".alarm_flow").show();
            $(".alarm_button").show();
            var flowHtml="";
            var flowMax =msg.length; //表示共有几个流程
            $("#flowMax").val("");
            $("#flowMax").val(flowMax);
            for (var i = 0; i < msg.length; i++) {

                //  parmaData["alarmRecordId"] = alarmRecordId;
                //  parmaData["flowId"] = msg[i].HFD_FLOW_ID;
                // parmaData["flowDtlsId"] = msg[i].ID;
                //flowHtml+="<li> <span class='iconfont icon-xiaoyuanquan'>"+ (i + 1) +"</span><span>"+msg[i].HFD_FLOW_ITEM_NAME +"</span></li>";
                var orderNumber  =i+1;//表示当前步骤序号从1开始
                flowHtml+="<li> <span style='color: red'> 第"+ (i + 1) +"步:</span><span>"+msg[i].HFD_FLOW_ITEM_NAME +"</span><button id ='flowDtlsId_" + i + "' style='margin-left: 30px'  class=\"ant-btn ant-btn-red\" onClick= \"insertFlowDtls('" + msg[i].HFD_FLOW_ID+"','"+msg[i].ID+"','"+orderNumber+"','"+flowMax+ "')\">" +"填写处置流程记录" + "</button></li>";
            }
            $('#flowHandle').html(flowHtml);
            for (var j = 0; j < msg.length; j++) {
                if(j==0){//只显示第一步的流程填写按钮 其他隐藏
                    $($flowDtlsId+j).show();
                }else{
                    $($flowDtlsId+j).hide();
                }
            }
        } else {
            $(".notData").show();
            $(".alarm_button").show();
        }
    }
    /**
     *
     * 填写报警流程记录
     * */
    function insertFlowDtls(flowId,flowDtsId,orderNumber,flowMax) {
        $(".receiveDiv").show();
        $(".oprtrDiv").hide();
        console.log("flowId="+flowId+"--------flowDtsId="+flowDtsId+"----------orderNumber="+orderNumber+"---------flowMax="+flowMax);
        //1.首先清空流程记录,然后查询该流程是否有流程记录,有显示,没有从第一步开始填写,点击下一步,显示下一步的流程按钮,当填写到最后一步即为 orderNumber = flowMax 才可以提交
        // 现场情况： hfdaXcqk   处置情况： hfdaCzqk  处置结果：hfdaCzjg 备注： hfdaBz
        //2.查询是否有报警流程记录 有就赋值
        if(orderNumber==flowMax){//表示最后一步
            //显示提交按钮  下一步隐藏
            $("#nextButton").hide();
            $("#subButton").show();
        }else{
            $("#nextButton").show();
            $("#subButton").hide();
        }
        $("#orderNumber").val("");
        $("#orderNumber").val(orderNumber);
        $("#hfdaXcqk").val("");
        $("#hfdaCzqk").val("");
        $("#hfdaCzjg").val("");
        $("#hfdaBz").val("");
        $("#flowDtlsAlarmRecordId").val("");
        $("#alarmFlowId").val(flowId);
        $("#flowDtsId").val(flowDtsId);
        var params = {};
        params["hfdaAlertRecordDtlsId"] =alarmRecordId;
        params["hfdaFlowId"] = flowId;
        params["hfdaFlowDtlsId"] = flowDtsId;
        $.ajax({
            type : 'post',
            url : '${ctx}/flow/getByFlowDtlsAlarmRecord',
            data : params,
            dataType : 'json',
            success : function(data) {
                if (data.code ==200 && data.data !=null) {
                    var obj = data.data;
                    SetFlowAlarmRecord(obj);
                }
            }
        });
    }
    /**
     * 赋值报警流程记录
     * */
    function SetFlowAlarmRecord(obj) {
        $("#hfdaXcqk").val(obj.hfdaXcqk);
        $("#hfdaCzqk").val(obj.hfdaCzqk);
        $("#hfdaCzjg").val(obj.hfdaCzjg);
        $("#hfdaBz").val(obj.hfdaBz);
        $("#flowDtlsAlarmRecordId").val(obj.id);
    }
    /**
     *
     * 流程下一步 type = 1 表示下一步  type = 2 表示最后一步需要提交
     * */
    function nextData(type) {
        var hfdaXcqk =  $("#hfdaXcqk").val();
        var hfdaCzqk =  $("#hfdaCzqk").val();
        var hfdaCzjg =  $("#hfdaCzjg").val();
        var hfdaBz = $("#hfdaBz").val();
        var flowDtlsAlarmRecordId = $("#flowDtlsAlarmRecordId").val();//流程记录表的id
        var hfdaFlowDtlsId = $("#flowDtsId").val();
        var hfdaFlowId = $("#alarmFlowId").val();
        var hfdaAlertRecordDtlsId = alarmRecordId;
        var parmsData = {};
        if(hfdaXcqk !=null && hfdaXcqk !="" && hfdaXcqk != undefined){
            parmsData["hfdaXcqk"] = hfdaXcqk;
        }
        if(hfdaCzqk !=null && hfdaCzqk !="" && hfdaCzqk != undefined){
            parmsData["hfdaCzqk"] = hfdaCzqk;
        }
        if(hfdaCzjg !=null && hfdaCzjg !="" && hfdaCzjg != undefined){
            parmsData["hfdaCzjg"] = hfdaCzjg;
        }
        if(hfdaBz !=null && hfdaBz !="" && hfdaBz != undefined){
            parmsData["hfdaBz"] = hfdaBz;
        }
        if(flowDtlsAlarmRecordId !=null && flowDtlsAlarmRecordId !="" && flowDtlsAlarmRecordId != undefined){
            parmsData["id"] = flowDtlsAlarmRecordId;
        }
        if(hfdaFlowDtlsId !=null && hfdaFlowDtlsId !="" && hfdaFlowDtlsId != undefined){
            parmsData["hfdaFlowDtlsId"] = hfdaFlowDtlsId;
        }
        if(hfdaFlowId !=null && hfdaFlowId !="" && hfdaFlowId != undefined){
            parmsData["hfdaFlowId"] = hfdaFlowId;
        }
        if(hfdaAlertRecordDtlsId !=null && hfdaAlertRecordDtlsId !="" && hfdaAlertRecordDtlsId != undefined){
            parmsData["hfdaAlertRecordDtlsId"] = hfdaAlertRecordDtlsId;
        }
        $.ajax({
            type : 'post',
            url : '${ctx}/flow/saveOrUpdate',
            data : parmsData,
            dataType : 'json',
            success : function(data) {
                if (data.code ==200 ) {
                    //清除记录表的详情id 的值
                    //根据当前步骤  隐藏$(".receiveDiv").hide(); 显示下一步的填写记录按钮
                    if(type !=2){
                        $(".receiveDiv").hide();
                        $(".oprtrDiv").show();
                        var orderNumber =$("#orderNumber").val();
                        //debugger;
                        var beforeOrderNumber = Number(orderNumber)-1;
                        $($flowDtlsId+beforeOrderNumber).addClass("ant-btn-gray");
                        $($flowDtlsId+orderNumber).show();
                    }
                }
            }
        });
    }
	/**
	 * 返回上一步
	 */
	function back(type) {
		if (type == 1) {
			$(".notData").hide();
			$(".alarm_flow").hide();
			$("table.table_one").html("");
			$(".alarm_button").hide();
			$(".alarm_level").show();
		} else if (type == 2) {
			$(".receiveDiv").hide();
			$(".oprtrDiv").show();
		}
	}
	/**
	 * 报警登记
	 */
	function register() {
		$(".oprtrDiv").hide();
		$(".receiveDiv").show();
	}

	/**
	 * 提交处置  type:0提交,1上级处置
	 */
	function subData(type) {
		if ($("#formId_addInfo").form("valid")) {
			nextData(2)
			var data = {};
			data["id"] = alarmRecordId;
			data["userId"] = jsConst.USER_ID;
			data["userName"] = jsConst.REAL_NAME;
			data["cusNumber"] = jsConst.CUS_NUMBER;
			data["subType"] = type + "";
			data["ardEventId"] = $("#flowId").textbox("getValue");
			data["ardEventType"] = $("#flowName").val();
			data["ardLocalCase"] = $("#localCase").val();
			data["ardOprtnDesc"] = $("#oprtnDesc").val();
			data["flag"] = $("#flag").textbox("getValue");
		  	data["ardOprtnResult"] = $("#oprtnResult").val();
		 
			$.ajax({
				type : 'post',
				url : '${ctx}/alarm/handle/receive.json',
				data : data,
				dataType : 'json',
				success : function(data) {
					if (data.success) {
						$("#dialogId_bjcz").dialog("close");
						$.messageQueue({ message : data.msg, cls : "success", iframePanel : true, type : "info" });
						stopAlarm(type);
						//stopVideo(type);
					} else {
						$.messageQueue({ message : data.msg, cls : "warning", iframePanel : true, type : "info" });
					}
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					$.messageQueue({ message : textStatus, cls : "warning", iframePanel : true, type : "info" });
				}
			});
		} else {
			alert("请确认输入是否正确！");
		}

	}
	/**
	 * 停止录像并添加报警事件报告信息
	 */
	/* function stopVideo(type) {
		 if (type != 0) {//如果是提交上级，就不会保存证据信息
			return;
		}  

		addEventRep();
		 var evidenceId = _stop();//"证据记录id";
		if (evidenceId != null || evidenceId != "") {
			//evidenceId 停止录像后返一个证据记录记录id
			addAlertEvidenceRelation(evidenceId);
		} 
	} */

	//添加报警事件报告
	/* function addEventRep() {
		var data = {};
		data["aevRecordId"] = alarmRecordId;
		$.ajax({
			type : 'post',
			url : '${ctx}/alert/event/save/rep.json',
			data : data,
			dataType : 'json',
			success : function(data) {
				if (data.success) {

				} else {
					$.messageQueue({ message : data.msg, cls : "warning", iframePanel : true, type : "info" });
				}
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$.messageQueue({ message : textStatus, cls : "warning", iframePanel : true, type : "info" });
			}
		});
	} */

	/**
	 * 添加报警证据信息
	 */
	/* function addAlertEvidenceRelation(evidenceId) {
		var data = {};
		data["aerRecordId"] = alarmRecordId;
		data["aerEvidenceId"] = evidenceId;
		$.ajax({
			type : 'post',
			url : '${ctx}/alert/event/save/evidence.json',
			data : data,
			dataType : 'json',
			success : function(data) {
				if (data.success) {

				} else {
					$.messageQueue({ message : data.msg, cls : "warning", iframePanel : true, type : "info" });
				}
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$.messageQueue({ message : textStatus, cls : "warning", iframePanel : true, type : "info" });
			}
		});
	}
  */
	/**
	 * 表单重置
	 */
	function resetData() {
		$("#localCase").val("");
		$("#oprtnDesc").val("");
	}
</script>
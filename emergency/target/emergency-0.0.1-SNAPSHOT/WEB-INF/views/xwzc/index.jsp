<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.cesgroup.prison.common.facade.AuthSystemFacade" %>
<%@ page import="com.cesgroup.prison.code.utils.CodeFacade" %>
<%@ page import="com.cesgroup.framework.utils.Util" %>
<%@ page import="java.util.*" %>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<div style="height: 100%; margin: 0px 10px;">

    <cui:form id="formId_xwzc">
        <table class="table">
            <tr>
                <td width="110">事件源名称 ：</td>
                <td><cui:input id="sourceName" name="sourceName"></cui:input></td>
                <td width="150">事件开始时间晚于：</td>
                <td><cui:datepicker id="startTime" name="startTime"
                                    dateFormat="yyyy-MM-dd HH:mm:ss"></cui:datepicker></td>
                <td width="120">早于 ：</td>
                <td><cui:datepicker id="stopTime" name="stopTime"
                                    dateFormat="yyyy-MM-dd HH:mm:ss"></cui:datepicker></td>
                <td>
                    <cui:button label="查询" componentCls="btn-primary" onClick="searchxwzc"></cui:button>
                </td>
            </tr>
            <tr>
                <td>事件状态 ：</td>
                <td><cui:combobox id="eventState" name="eventState" data="comboboxData_sjzt"></cui:combobox></td>
                <td>事件类型名称 ：</td>
                <td><cui:input id="eventTypeName" name="eventTypeName"></cui:input></td>
                <td>事件等级 ：</td>
                <td><cui:combobox id="eventLevel" name="eventLevel" data="comboboxData_sjdj"></cui:combobox></td>
                <td>
                    <cui:button label="重置" onClick="clear"></cui:button>
                </td>
            </tr>
        </table>
    </cui:form>

    <div style="height: 30px;">
        <cui:toolbar id="toolbarId_xwzc" data="toolbar_xwzc"></cui:toolbar>
    </div>
    <cui:grid id="gridId_xwzc" rownumbers="true" singleselect="true" width="auto" fitStyle="fill" rowNum="15">
        <cui:gridCols>
            <cui:gridCol name="id" hidden="true">id</cui:gridCol>
            <cui:gridCol name="sourceIdx">事件源编号</cui:gridCol>
            <cui:gridCol name="cusNumber" hidden="true">监狱编号</cui:gridCol>
            <cui:gridCol name="dataSource" hidden="true">数据来源</cui:gridCol>
            <cui:gridCol name="eventState" align="center" formatter="convertCode" revertCode="true"
                         formatoptions="{'data': comboboxData_sjzt}">事件状态</cui:gridCol>
            <cui:gridCol name="eventLevel" align="center" formatter="convertCode" revertCode="true"
                         formatoptions="{'data': comboboxData_sjdj}">事件等级</cui:gridCol>
            <cui:gridCol name="eventTypeName" align="center">事件类型名称</cui:gridCol>
            <cui:gridCol name="startTime" align="center">事件开始时间</cui:gridCol>
            <cui:gridCol name="stopTime" align="center">事件结束时间</cui:gridCol>
            <cui:gridCol name="sourceName" align="center">事件源名称</cui:gridCol>
        </cui:gridCols>
        <cui:gridPager gridId="gridId_xwzc"/>
    </cui:grid>
</div>
<cui:dialog id="dialogId1" autoOpen="false" iframePanel="true" reLoadOnOpen="true" modal="true" resizable="false" autoDestroy="true"></cui:dialog>
 
<script>
    var comboboxData_sjzt = [{value: '0', text: '瞬时事件'}, {value: '1', text: '事件开始'}, {
        value: '2',
        text: '事件结束'
    }, {value: '3', text: '事件脉冲'}, {value: '4', text: '事件更新'}];
    var comboboxData_sjdj = [{value: '0', text: '普通事件'}, {value: '1', text: '低级报警'}, {
        value: '2',
        text: '中级报警'
    }, {value: '3', text: '高级报警'}];
    $.parseDone(function () {
        //searchxwzc();
        var type = ${type}
            $("#gridId_xwzc").grid("reload", "${ctx}/xwzc/searchXwzcList?type=" + type);
    });

    var toolbar_xwzc = [{
        "type": "button",
        "id": "btnId_view",
        "label": "查看",
        "onClick": "openView",
        "componentCls": "btn-primary"
    }];
	function openView(event, ui) {
		 var selrow = $("#gridId_xwzc").grid("option", "selrow");
	     var rowData = $("#gridId_xwzc").grid("getRowData", selrow.toString());
	     var id = rowData.id; 
	     if(rowData.dataSource == '海康'){
	    	 openView_BAK(event, ui);
	     } else {
	    	 $("#dialogId1").dialog({
	 			width : 840, //属性
	 			height : 440, //属性
	 			title : "查看",
	 			modal : true, //属性
	 			autoOpen : false,
	 			url : "${ctx}/xwzc/openImage?id="+id
	 		});
	 		$("#dialogId1").dialog("open");
	     }
	}
    
    
    
    function openView_BAK(event, ui) {
        var selrow = $("#gridId_xwzc").grid("option", "selrow");
        var rowData = $("#gridId_xwzc").grid("getRowData", selrow.toString());
        var layout = {
            'layout': 1,
            'last': 1
        };
        if(rowData.startTime == null || rowData.startTime == '') {
            $.messageQueue({message: "事件开始时间为空，无法回放视频！", cls: "warning", iframePanel: true, type: "info"});
            return false;
        }
        console.log("=============================== xwzc index.jsp openView start ===============================")
        var stime_ = formatToDate(rowData.startTime);
        var etime_ = (rowData.stopTime == null || rowData.stopTime == '') ? formatToDate(rowData.startTime) : formatToDate(rowData.stopTime);
        console.log("xwzc index.jsp openView stime_ = " + stime_);
        console.log("xwzc index.jsp openView etime_ = " + etime_);
        /**
         * Begin modify by lincoln.cheng 2019-03-11
         * var stime = getMyDate(time.setTime(time.getTime() - 1000 * 60 * 5));
         * var etime = getMyDate(time.setTime(time.getTime() + 1000 * 60 * 10));
         */
        var stime = new Date(stime_.getTime() - 1000 * 30).Format("yyyy-MM-dd hh:mm:ss");
        var etime = new Date(etime_.getTime() + 1000 * 30).Format("yyyy-MM-dd hh:mm:ss");
        console.log("xwzc index.jsp openView stime = " + stime);
        console.log("xwzc index.jsp openView etime = " + etime);
        /** End modify by lincoln.cheng 2019-03-11 */

        var index = 0;
        var cbdCusNumber = rowData.cusNumber;// 行为侦测事件所属监狱编号
        var cbdPlatformIdnty = rowData.sourceIdx;// 行为侦测事件摄像头在平台的索引ID
        var dataSource = rowData.dataSource;// 行为侦测事件的数据来源
        // alert("cbdCusNumber = " + cbdCusNumber);
        if(cbdCusNumber == '') {
            $.messageQueue({message: "监狱编号为空，无法回放视频！", cls: "warning", iframePanel: true, type: "info"});
            return false;
        }
        var videoList = new Array();
        $(".layout_table table #date_" + (index + 1)).html(stime.split(" ")[1] + " ~ " + etime.split(" ")[1]);

        /**
         * Modify By lincoln.cheng 2019/04/18 Start
         *
         * 根据cbdPlatformIdnty查询DVC_CAMERA_BASE_DTLS数据，获取camera的id与name
         */
        var urlPath = "${ctx}/jfsb/camera/getCameraByCbdCusNumberAndCbdPlatformIdnty";
        $.ajax({
            type: "post",
            url: urlPath,
            data: {
                cbdCusNumber: cbdCusNumber,
                cbdPlatformIdnty: cbdPlatformIdnty
            },
            dataType: "json",
            success: function (data) {
                if (data.code == 200) {
                    var camera = data.data;
                    videoList.push({
                        index: index,
                        cameraId: camera.id,
                        cameraName: camera.cbdName,
                        startTime: stime,
                        endTime: etime
                    });
                    if(jsConst.VIDEO_PLAYER_TYPE=='0'){
                        videoClient.playbackHandle(videoList, layout);
                    }else if(jsConst.VIDEO_PLAYER_TYPE=='1'){
                        $("#dialogId_videoPluginDemo").dialog("close");
                        $("#dialogId_videoPluginDemo").dialog({
                            width: 800,
                            height: 600,
                            title: '视频调阅',
                            onOpen: function() {
                                // 播放选中的摄像头实时视频
                                videoPlugin.multiVideoPlaybackHandle({
                                    'container': $("div[id='dialogId_videoPluginDemo']"),
                                    'subType': 2,
                                    'layout': layout,
                                    'data': videoList,
                                    'callback': function (data) {
                                        console.log("xwzc/index.jsp openView data = " + JSON.stringify(data));
                                    }
                                });
                            },
                            onClose: function () {
                                videoPlugin.videoPlayerClear();
                            }
                        });
                        $("#dialogId_videoPluginDemo").dialog("open");
                    }
                } else if (data.code == 500) {
                    $.messageQueue({message: data.message, cls: "warning", iframePanel: true, type: "info"});
                }
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                $.messageQueue({message: textStatus, cls: "warning", iframePanel: true, type: "info"});
            }
        });
        console.log("=============================== xwzc index.jsp openView end ===============================")
        /** Modify By lincoln.cheng 2019/04/18 End */
    }

    function searchxwzc() {
        var formData = $("#formId_xwzc").form("formData");
        $("#gridId_xwzc").grid("option", "postData", formData);
        //$("#gridId_xwzc").grid("reload");
        $("#gridId_xwzc").grid("reload", "${ctx}/xwzc/searchXwzcList");
    }

    function clear() {
        $("#formId_xwzc").form("reset");
    }


    /**
     * 根据formatter字符串转换时间字符串
     * 月(M)、日(d)、小时(h)、分(m)、秒(s) 占位符个数需要和时间字符串匹配
     * @param  {string} dateStr 时间字符串 如:"2015-11-12"
     * @param  {string} fmt     格式化字符串 如:"yyyy-MM-dd"
     * @return {Date}           标准时间
     * author: shaojiasong 2015-12-29
     */
    function parseDate(dateStr, fmt) {
        if (!dateStr || !fmt || (dateStr.length != fmt.length)) {
            throw new Error("转换时间时发生错误,时间字符串与格式字符串不匹配!");
        }
        //排除特殊字符,避免正则发生错误
        fmt = fmt.replace(/([\^\$\.\*\+\?\=\!\:\|\\\/\(\)\[\]\{\}])/ig, "\\$1");

        function getReg(str) {
            var cfmt = fmt;
            cfmt = cfmt.replace(new RegExp(str + "+", "g"), function (full) {
                return "(" + full + ")";
            }) || "";
            return cfmt.replace(/[yMdhmsS]/g, "\\d");
        }

        var year = parseInt((new RegExp(getReg("y")).exec(dateStr)[1])) || 0;
        var month = parseInt((new RegExp(getReg("M")).exec(dateStr)[1])) - 1 || 0;
        var day = parseInt((new RegExp(getReg("d")).exec(dateStr)[1])) || 0;
        var hour = parseInt((new RegExp(getReg("h")).exec(dateStr)[1])) || 0;
        var minute = parseInt((new RegExp(getReg("m")).exec(dateStr)[1])) || 0;
        var second = parseInt((new RegExp(getReg("s")).exec(dateStr)[1])) || 0;

        return new Date(year, month, day, hour, minute, second);
    }

    function getMyDate(str) {
        var oDate = new Date(str),
            oYear = oDate.getFullYear(),
            oMonth = oDate.getMonth() + 1,
            oDay = oDate.getDate(),
            oHour = oDate.getHours(),
            oMin = oDate.getMinutes(),
            oSen = oDate.getSeconds(),
            oTime = oYear + '-' + getzf(oMonth) + '-' + getzf(oDay) + ' ' + getzf(oHour) + ':' + getzf(oMin) + ':' + getzf(oSen);//最后拼接时间
        return oTime;
    };

    //补0操作
    function getzf(num) {
        if (parseInt(num) < 10) {
            num = '0' + num;
        }
        return num;
    }


</script>
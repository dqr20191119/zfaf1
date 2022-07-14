<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@page import="com.cesgroup.prison.code.utils.CodeFacade" %>
<%@taglib prefix="cui" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<body style="height: 100%">
<div style="height: 98%; margin: 5px">
    <div style="height:100%;">
        <div style="padding:0;margin-bottom: 10px;margin-left:120px">
            汇报期数:
            <cui:datepicker id="cprTimesq" name="cprTime" dateFormat="yyyyMMdd"
                            width="150"></cui:datepicker>
            <cui:button label="查 询" onClick="searchInfo" cls="cyanbtn"></cui:button>
            <cui:button label="重 置" onClick="reset" cls="btn-danger"></cui:button>
        </div>
        <div>
            <cui:form  style="padding:0;margin-bottom: 10px;margin-right:10px">
                <table style="width: 100%;">
                    <tr>
                        <td align="right" style="width:170px; height: 25px;">已上报部门：</td>
                        <td align="left" ><cui:textarea id="reportedDprt" componentCls="form-control" name="reportedDprt" readonly="true"
                                                        height="120"></cui:textarea></td>
                    </tr>
                    <tr>
                        <td align="right" style="width:170px; height: 25px;">未上报部门：</td>
                        <td align="left" ><cui:textarea id="unreportDprt" componentCls="form-control" name="unreportDprt" readonly="true"
                                                        height="120"></cui:textarea></td>
                    </tr>
                </table>
            </cui:form>
        </div>
        <div style="height: 90%;">
            <div id="gridId_sjhz"></div>
        </div>
    </div>
</div>
</body>

<script>

    var jsConst = window.top.jsConst;
    var cusNumber = jsConst.CUS_NUMBER;
    var userId = jsConst.USER_ID;   //当前登陆者
    var realName = jsConst.REAL_NAME;

    $.parseDone(function () {
                var gridId_sjsb_colModel = [
                    {name: "prtTypeName", label: "上报类型名称",  align: "center", width: 80 },
                    {name: "cprNumber", label: "总数", align: "center", width: 80 }
                ];
                var _setting={
                    colModel: gridId_sjsb_colModel,
                    fitStyle: "fill"
                };
                $('#gridId_sjhz').grid(_setting);
                $('#cprTimesq').datepicker("setDate", new Date());
                searchInfo();
    });

    function searchInfo() {
        var cprTime = $("#cprTimesq").datepicker("getValue");
        var url = "${ctx}/xxhj/zfsjsb/sjsb/getSjhz.json?cprCusNumber="+ jsConst.CUS_NUMBER+ "&cprTime=" + cprTime ;
        $("#gridId_sjhz").grid("reload", url);
        $.ajax({
            type: 'post',
            url: '${ctx}/xxhj/zfsjsb/sjsb/getReportDprt?cprCusNumber=' + cusNumber+ "&cprTime=" + cprTime,
            data: '',
            dataType: 'json',
            success: function (data) {
                $('#reportedDprt').textbox('setValue', data.obj.reportedDprt.toString());
                $('#unreportDprt').textbox('setValue', data.obj.unreportedDprt.toString());
            }
        });
    }

    function reset() {
        $("#cprTimesq").datepicker("setValue", "");
    }

</script>
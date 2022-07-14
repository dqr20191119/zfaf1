<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<html>
<body>
<div>
    <cui:form id="zfjcj_form">
        <table class="table">
            <tr>
                <td style="padding-right:20px;"><cui:button label="审   核" onClick="shInfo"
                                                            componentCls="cyanbtn" width="60"></cui:button></td>
                <td>离监日期：</td>
                <td><cui:datepicker id="pirStartTime" name="pirStartTime" componentCls="form-control"
                                    dateFormat="yyyy-MM-dd HH:mm:ss" showOn="button"></cui:datepicker></td>
                <td>至</td>
                <td><cui:datepicker id="pirEndTime" name="pirEndTime" componentCls="form-control"
                                    startDateId="pirStartTime" dateFormat="yyyy-MM-dd HH:mm:ss"
                                    showOn="button"></cui:datepicker></td>
                <td><cui:button label="查询" onClick="searchInfo"></cui:button></td>
                <td><cui:button label="重置" onClick="reSet"></cui:button></td>
            </tr>
        </table>
    </cui:form>

    <cui:grid id="zfjcj_grid" rowNum="20" rownumbers="false" fitStyle="fill" height="480" pager="true" onSelectRow="haha"
              multiselect="true">
        <cui:gridCols>
            <cui:gridCol name="jyid" width="150" align="center" hidden="true"> 监狱编码 </cui:gridCol>
            <cui:gridCol name="zfbh" width="150" align="center"> 罪犯编号 </cui:gridCol>
            <cui:gridCol name="zfxm" width="150" align="center"> 罪犯姓名 </cui:gridCol>
            <cui:gridCol name="ljlx" width="150" align="center"> 离监类型 </cui:gridCol>
            <cui:gridCol name="ljrq" width="150" align="center"> 离监时间 </cui:gridCol>
            <cui:gridCol name="bgmj" width="150" align="center"> 报告人 </cui:gridCol>
            <cui:gridCol name="pirApprovalPoliceName" width="150" align="center"> 审核人 </cui:gridCol>
            <cui:gridCol name="pirApprovalTime" width="150" align="center"> 审核时间 </cui:gridCol>
            <cui:gridCol name="pirApprovalStts" width="80" align="center" formatter="shStatus">审核状态</cui:gridCol>
        </cui:gridCols>
        <cui:gridPager gridId="zfjcj_grid"/>
    </cui:grid>
</div>
<script type="text/javascript">

    $.parseDone(function () {
        $("#zfjcj_grid").grid("reload", "${ctx}/zfjcj/listAll.json?pirCusNumber=" + jsConst.CUS_NUMBER);

    });

    function searchInfo() {
        var formData = $("#zfjcj_form").form("formData");
        formData["jyid"] = jsConst.CUS_NUMBER;

        $("#zfjcj_grid").grid("option", "postData", formData);
        $("#zfjcj_grid").grid("reload");

    }
    
    function reSet() {
        $("#zfjcj_form").form("clear");
    }

    function shStatus(cellvalue, options, rawObject) {
        if (cellvalue == '0' || cellvalue == '' || cellvalue == null) {
            return "<font color='#ff9900'>未审核</font>";
        } else if (cellvalue == '1') {
            return "<font color='green'>已审核</font>";
        }
        else {
            return "";
        }
    }

    function shInfo() {
        $.loading({text:"正在处理中，请稍后..."});
        var selected = $("#zfjcj_grid").grid("option", "selarrrow");
        if(selected && selected.length > 0) {
            for(var i = 0; i < selected.length; i++) {
                var rowData = $("#zfjcj_grid").grid("getRowData", selected[i]);
                if(rowData.pirApprovalStts != "已审核"){
                    $.ajax({
                        async :false,
                        type : "post",
                        data : JSON.stringify(rowData),
                        url : "${ctx}/zfjcj/checkInfo.json",
                        contentType:"application/json",
                        success : function(data) {
                            $.loading("hide");
                            if(data != null){
                                $("#zfjcj_grid").grid("reload");
                                $.messageQueue({ message : "操作成功", cls : "success", iframePanel : true, type : "info" });
                            }else{

                            }
                        },
                        error : function(XMLHttpRequest, textStatus, errorThrown) {
                            $.loading("hide");
                            $.messageQueue({ message : "操作失败", cls : "error", iframePanel : true, type : "info" });
                        }
                    });
                }
/*                else{
                    $.loading("hide");
                    $.message({message:"注意！无法审核已完成审核记录！", cls:"waring"});
                    return;
                }*/
            }
        }else{
            $.loading("hide");
            $.message({message:"请选择记录！", cls:"waring"});
            return;
        }
    }
</script>
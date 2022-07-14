<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.cesgroup.prison.common.facade.AuthSystemFacade"%>
<%@ page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<script src="${ctx}/static/js/scripts/common.js"></script>
<style>
    #zbbpPoliceList span{
        display:inline-block;
        border:2px solid #B3D0F4;
        line-height:25px;
        width:100px;
        white-space:pre-wrap;
        text-align: left;
        padding-left: 10px;
    }
</style>
<div>
    <div style="margin: 5px 5px 5px 5px;">
        <table border="0" >
            <tr>
                <td>
                    <cui:form id="formId_rlwh_edit">
                        <cui:input type='hidden' name="id" id="id" />
                        <cui:input type='hidden' name="cjrId" value="${cjrId }" />
                        <fieldset>
                            <table class="table" style="width:600px; height:auto">
                                <tr>
                                    <th>开始日期：</th>
                                    <td><cui:datepicker id="startDate"  name="dmdStartDate" required="true" dateFormat="yyyy-MM-dd"></cui:datepicker>
                                    <th>结束日期：</th>
                                    <td><cui:datepicker id="endDate"  name="dmdStartDate" required="true" dateFormat="yyyy-MM-dd"></cui:datepicker>
                                </tr>
                                <tr>
                                    <td colspan="2">
                                        <cui:button label="确认" id="ensure" componentCls="btn-primary" onClick="ensure"/>
                                    </td>
                                </tr>
                            </table>
                        </fieldset>
                    </cui:form>
                </td>
            </tr>
        </table>
    </div>

    <cui:grid id="grid_rlwhDetail" colModel="gridColModel"  data="grid_localData"   cellEdit="true" rownumbers="true"
              fitStyle="width" height="800"   rowNum="400"  >
    </cui:grid>
</div>

<script>
    var cusNumber = jsConst.CUS_NUMBER;
    var USER_LEVEL = jsConst.USER_LEVEL;
    var drpmntId = jsConst.DEPARTMENT_ID;

    $.parseDone(function() {
        getNextMothDate();
        $("#grid_rlwhDetail").hide();
        //debugger;
       /* $("#edit_cusNumber").combobox("option","readonly",true);

        if(id) {//修改

            loadForm("formId_rlwh_edit", "${ctx}/zbgl/jyzbhz/getJyzbBaseById?id="+id, function(data) {

            });
            $("#edit_zbYf").datepicker("option","readonly",true);
            $("#ensure").hide();
            ensure('1');
        }else{
            //新增
            $("#edit_cusNumber").combobox("setValue",cusNumber);
            $("#grid_rlwhDetail").hide();
        }*/
    });

    var gridColModel = [{
        label : "日期",
        name : "dutyDate",
        align : "center"
    },{
        label : "星期",
        name : "weekDay",
        align : "center"
    },{
        label : "节假日标志",
        name : "flag",
        editable : true,
        revertCode: true,
        align : "center",
        edittype : "combobox",
        editoptions : {
            'required' : true,
            'data' : combobox_flag
        }
    }
    ];


    function ensure(){
            var validFlag  = $("#formId_rlwh_edit").form("valid");
            if(!validFlag) {
                return;
            }
            //新增
           // debugger;
            initModel();
        $("#grid_rlwhDetail").show();
    }



    function initModel(){
        var params = {};
          var startDate =  $("#startDate").datepicker("getValue");
          var endDate = $("#endDate").datepicker("getValue");
            params["startDate"] = startDate;
            params["endDate"] = endDate;
            //检查是否有数据
      //  debugger;
            if(checkIsBetweenStartDateAndEndDate(params)){
                $.messageQueue({ message : "在这个日期内已经维护了日历数据,请重新操作!", cls : "warning", iframePanel : true, type : "info" });
                return;
            }
        $.ajax({
            type : 'post',
            url : '${ctx}/zbgl/zbrlwh/getInitRlwhData',
            data: params,
            dataType : 'json',
            async:false,
            success : function(data) {
                $("#grid_rlwhDetail").grid("reload",data);
            }
        });
    }


    function checkIsBetweenStartDateAndEndDate(params) {
        var flag = false;
        $.ajax({
            type : 'post',
            url : '${ctx}/zbgl/zbrlwh/checkIsBetweenStartDateAndEndDate',
            data: params,
            dataType : 'json',
            async:false,
            success : function(data) {
                if(data.code==200){
                    if(data.message=="true"){
                        debugger;
                        //说明有数据
                        flag = true;
                    }
                }
            }
        });

        return flag;
    }



    function getNextMothDate(){
        var curDate = new Date();
        var y = curDate.getFullYear();
        var m = curDate.getMonth() + 2;//本身就得+1才等于当前月份，然而我要计算下一个月，所以直接+2
        if( m > 12 ){
            m = 1;
            y++
        }
        var monthLastDay = new Date(y,m,0).getDate();
        var syqxks = y + '-' + (m < 10 ? '0'+m : m) + '-' + '01';
        var syqxjs = y + '-' + (m < 10 ? '0'+m : m) + '-' + (monthLastDay < 10 ? '0'+monthLastDay : monthLastDay);
        console.log("syqxks="+syqxks+"syqxjs="+syqxjs)
        $("#startDate").datepicker("setValue",syqxks);
        $("#endDate").datepicker("setValue",syqxjs);
    }



</script>
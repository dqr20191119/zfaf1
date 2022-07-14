<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.ces.prison.common.constants.SystemConst"%>

<%@ page import="com.cesgroup.prison.common.facade.AuthSystemFacade"%>
<%@ page import="com.ces.prison.common.constants.GroupKeyConst"%>
<%@ page import="com.cesgroup.prison.code.utils.CodeFacade" %>
<%@ page import="com.ces.authsystem.server.AuthSystemServer" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<script src="${ctx}/static/js/scripts/cell/cellCortrol.js" type="text/javascript"></script>
<script src="${ctx}/static/js/scripts/cell/cellMan.js" type="text/javascript"></script>
<script src="${ctx}/static/js/scripts/cell/common.js" type="text/javascript"></script>
<%
String cellClassid=SystemConst.cellClassid;
String[] cellLogin=SystemConst.cellLogin;
/*String jyId = AuthSystemFacade.getLoginUserInfo().getCusNumber();
String jyName = CodeFacade.getCodeNameByCodeKey(GroupKeyConst.GROUP_CODE_KEY_JY,jyId);
request.setAttribute("jynName",jyName);*/
%>
<table style="width: 100%; height: 100% " cellpadding="0" cellspacing="8">
	<tr>
		<td>
			<table>
				<tr>
					<td>
						<object id="CellWeb" style="left: 0px; width: 1100px; top: 0px; height: 700px" codebase="${ctx}/static/cell/CellWeb5.CAB#version=5,3,9,14" classid="<%=cellClassid%>" >
							<param name="_Version" value="65536" />
							<param name="_ExtentX" value="10266" />
							<param name="_ExtentY" value="7011" />
							<param name="_StockProps" value="0" />
						</object>
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>
<%--<div class="dialog-buttons">
<cui:button id="printYl" label="打印预览" icons="iconView" onClick="printYl"></cui:button>
<cui:button id="print" label="打印" icons="iconPrint" onClick="print"></cui:button>
<cui:button label="关闭" icons="iconClose" onClick="closeDialog"></cui:button>
</div>--%>
<script type="text/javascript">
    var id='${id}';                 //模板部门表的主键
    var dmdDprtmntId = '${dmdDprtmntId}';
    var categoryId = '${categoryId}';
    var dmdModeId = '${dmdModeId}'    //模板的主键
    var dmdStartDate ='${dmdStartDate}';
    var dmdEndDate = '${dmdEndDate}';
    var print='${print}'
    var jyName =jsConst.CUS_NAME;
    $.parseDone(function(){
        var cellM = new CELL();// 实例化Cell管理类对象（必须，不允许修改）   
	    var cellObj = document.getElementById("CellWeb");//（必须，不允许修改）
	    cellObj.Login("<%=cellLogin[0]%>","<%=cellLogin[1]%>",<%=cellLogin[2]%>,"<%=cellLogin[3]%>");//注册cell（必须，不允许修改）
	  	cellM.open(cellObj,"${ctx}/static/cell/form/zbgl_zbbp_sjzbb.cll");//打开cell文件（必须修改）
		cellM.setReadOnly(cellObj) ;//页面设置为只读（必须，默认设置）
	  	cellObj.ShowGridLine(1,0);//显示 or 隐藏背景网格线（必须，默认设置）
		cellObj.ShowSheetLabel(0,0);//默认显示第1个sheet页,隐藏下方sheet页栏（必须，默认设置）
        if(print=="1"){
            insertCell(cellObj);//（必须，默认方法）
        }else{
            insertCell2(cellObj);//（必须，默认方法）
        }
	});
 
	function insertCell(cellObj){
        $.ajax({
            type : 'post',
            url : '${ctx}/zbgl/mbsz/getById',
            data: {
                mojCusNumber: cusNumber,
                modelId : dmdModeId,  //模板主键
                id : id,              //模板部门表主键
                mojStartDate : dmdStartDate,
                mojEndDate : dmdEndDate,
                param : "5",
            },
            dataType : 'json',
            async:false,
            success : function(data) {
                if(cusNumber=="4300"){
                    //局机关指挥中心值班安排表
                    var year = dmdStartDate.split("-")[0];
                    var moth = dmdStartDate.split("-")[1];
                    cellObj.S(2,1,0,"局机关"+year+"年"+moth+"月排班表");
                }else{
                    cellObj.S(2,1,0,jyName+"值班安排表");
                }
                var orderPolices =data.orderPolices;
                var row = 4;
                var col = 2;
                var temp =col;
                for(var i = 0;i <orderPolices.length;i++){
                    cellObj.S(temp++,row,0,formatDateYearAndMoth(orderPolices[i].dutyDate));
                    cellObj.S(temp++,row,0,orderPolices[i].week);
                    cellObj.S(temp++,row,0,orderPolices[i].zhz);
                    cellObj.S(temp++,row,0,orderPolices[i].zzbz);
                    cellObj.S(temp++,row,0,orderPolices[i].zzby);
                    cellObj.S(temp++,row,0,orderPolices[i].zhzbz);
                    cellObj.S(temp++,row,0,orderPolices[i].zhzby);
                    cellObj.S(temp++,row,0,orderPolices[i].wzbz);
                    cellObj.S(temp++,row,0,orderPolices[i].wzby);
                    row++;
                    temp=col;
                }
            }
        });

    }


    function insertCell2(cellObj){
       var data = createPrint();
       console.log(JSON.stringify(data));
        putData(data,cellObj);
    }

    function putData(data,cellObj) {
        if(cusNumber=="4300"){
            //局机关指挥中心值班安排表
            var year = data[0].dutyDate.split("-")[0];
            var moth = data[0].dutyDate.split("-")[1];
            cellObj.S(2,1,0,"局机关"+year+"年"+moth+"月排班表");
           // cellObj.S(2,1,0,"局机关指挥中心值班安排表");
        }else{
            cellObj.S(2,1,0,jyName+"值班安排表");
        }
        var row = 4;
        var col = 2;
        var temp =col;
        for(var i = 0;i <data.length;i++){
            cellObj.S(temp++,row,0,formatDateYearAndMoth(data[i].dutyDate));
            cellObj.S(temp++,row,0,data[i].week);
            cellObj.S(temp++,row,0,data[i].zhz);
            cellObj.S(temp++,row,0,data[i].zzbz);
            cellObj.S(temp++,row,0,data[i].zzby);
            cellObj.S(temp++,row,0,data[i].zhzbz);
            cellObj.S(temp++,row,0,data[i].zhzby);
            cellObj.S(temp++,row,0,data[i].wzbz);
            cellObj.S(temp++,row,0,data[i].wzby);
            row++;
            temp=col;
        }
    }

    function  formatDateYearAndMoth(date){
	    var m = date.split("-")[1];
        if(m.charAt(0)=="0"){
            m = m.substr(1)
        }
	    var d = date.split("-")[2];
	   if(d.charAt(0)=="0"){
	       d = d.substr(1)
       }
	    return m+"月"+d+"日"

    }


    function zj_print() {
        var cellObj = document.getElementById("CellWeb");
        cellObj.PrintSheet(1, 0);
    }

    function printYl() {
        var cellObj = document.getElementById("CellWeb");
        cell_Preview(cellObj);
    }

	

	
    
</script>
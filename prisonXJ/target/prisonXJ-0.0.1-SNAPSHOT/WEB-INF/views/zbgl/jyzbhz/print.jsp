<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.ces.prison.common.constants.SystemConst"%>
<%@ page import="com.ces.authsystem.server.facade.AuthSystemFacade"%>
<%@ page import="com.ces.prison.common.constants.GroupKeyConst"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<script src="${ctx}/static/js/scripts/cell/cellCortrol.js" type="text/javascript"></script>
<script src="${ctx}/static/js/scripts/cell/cellMan.js" type="text/javascript"></script>
<script src="${ctx}/static/js/scripts/cell/common.js" type="text/javascript"></script>
<%
String cellClassid=SystemConst.cellClassid;
String[] cellLogin=SystemConst.cellLogin;
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

    $.parseDone(function(){
        var cellM = new CELL();// 实例化Cell管理类对象（必须，不允许修改）   
	    var cellObj = document.getElementById("CellWeb");//（必须，不允许修改）
	    cellObj.Login("<%=cellLogin[0]%>","<%=cellLogin[1]%>",<%=cellLogin[2]%>,"<%=cellLogin[3]%>");//注册cell（必须，不允许修改）
	  	cellM.open(cellObj,"${ctx}/static/cell/form/zbgl_jyzbhz.cll");//打开cell文件（必须修改）
		cellM.setReadOnly(cellObj) ;//页面设置为只读（必须，默认设置）
	  	cellObj.ShowGridLine(1,0);//显示 or 隐藏背景网格线（必须，默认设置）
		cellObj.ShowSheetLabel(0,0);//默认显示第1个sheet页,隐藏下方sheet页栏（必须，默认设置）
        insertCell(cellObj);
	});
 
	function insertCell(cellObj){
        cellObj.S(0,1,0,1);
    }










	function print() {
	   var cellObj = document.getElementById("CellWeb");
	   cellObj.PrintSheet(1, 0);
	}
	
	function printYl() {
	   var cellObj = document.getElementById("CellWeb");
	   cell_Preview(cellObj);
	}
	

	
    
</script>
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
					<cui:form id="formId_zbbp_view">
						<cui:input type='hidden' name="id" id="id" />
						<cui:input type='hidden' name="cjrId" value="${cjrId }" />
						 <fieldset> 
							<table class="table" style="width:1120px; height:auto">
								<tr>
									<th>监狱名称：</th>
									<td><cui:combobox id="view_cusNumber" readonly="true"  data="combox_dw" name="cusNumber" ></cui:combobox></td>
									<th>月份：</th>
									<td><cui:datepicker id ="view_zbYf" readonly="true"   value="${zbYf }" name ="zbYf" required="true" dateFormat="yyyy-MM" ></cui:datepicker></td>
									<th>值班电话：</th>
									<td><cui:input id="zbDh" readonly="true"  name= "zbDh" required="true" ></cui:input></td>
								</tr>
								<tr>
									<th>填报人：</th>
									<td><cui:input id="cjrName"  readonly="true"  name="cjrName" value="${cjrName }"  ></cui:input></td>
									<th>填报日期：</th>
									<td><cui:input  name = "cjrq" readonly="true"  value="${cjrq }" ></cui:input></td>
								</tr>
							</table>
						</fieldset>
					</cui:form>
				</td>
			</tr>
			<!-- <tr>
				<td>
					<table>
						<tr>
							<td id="dutyTable" style="vertical-align: top; padding-left: 20px;">
								<div id="dutyList" style="position:absolute; width:980px; height:500px; overflow: auto;margin:5px 5px 5px 5px">
									<table border="1"  id="dutyform" style="table-layout:fixed;width:850px;">
									</table>
								</div>
							</td>
						</tr>
					</table>
				</td>
			</tr> -->
		</table>
	</div>
	
	<cui:grid id="grid_jyzbDetail" colModel="gridColModel"    rownumbers="true"
        fitStyle="width" height="800"   rowNum="31"  >
        </cui:grid>
</div>
	
<script>
	var cusNumber = jsConst.CUS_NUMBER;
	var USER_LEVEL = jsConst.USER_LEVEL;
	var drpmntId = jsConst.DEPARTMENT_ID;
	
	var id = '${id}';                      //模板部门表的主键
	//var zbYf = '${zbYf}';  
	//var zbDh = '${zbDh}';
	$.parseDone(function() {
		if(id) {
			
			loadForm("formId_zbbp_view", "${ctx}/zbgl/jyzbhz/getJyzbBaseById?id="+id, function(data) {
               
			});
			$.ajax({
				type : 'post',
				url : '${ctx}/zbgl/jyzbhz/getJyzbDetailByYwID',
				data: {id:id},
				dataType : 'json',
				success : function(data) {
					$("#grid_jyzbDetail").grid("reload",data); 
				}
			});
		}
	});
	
	
	
	 var gridColModel = [{
        label : "id",
        name : "id",
        width : 100,
        hidden : true
       // formatter : "text"//设置formatter后不能再使用editable为true
    },{
        label : "值班日期",
        name : "dutyDate",
        align : "center"
    },{
        label : "指挥长",
        name : "zhz",
        align : "center"
    },{
        label : "值班长",
        name : "zbz",
        align : "center"
    },{
        label : "值班员",
        name : "zby",
        align : "center"
    }];
	
	
	

	
	
	

</script>
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
					<cui:form id="formId_zbbp_edit">
						<cui:input type='hidden' name="id" id="id" />
						<cui:input type='hidden' name="cjrId" value="${cjrId }" />
						 <fieldset> 
							<table class="table" style="width:1120px; height:auto">
								<tr>
									<th>监狱名称：</th>
									<td><cui:combobox id="edit_cusNumber" data="combox_dw" name="cusNumber" ></cui:combobox></td>
									<th>月份：</th>
									<td><cui:datepicker id ="edit_zbYf"  value="${zbYf }" name ="zbYf" required="true" dateFormat="yyyy-MM" ></cui:datepicker></td>
									<th>值班电话：</th>
									<td><cui:input id="zbDh"  name= "zbDh" required="true" ></cui:input></td>
								</tr>
								<tr>
									<th>填报人：</th>
									<td><cui:input id="cjrName"  readonly="true"  name="cjrName" value="${cjrName }"  ></cui:input></td>
									<th>填报日期：</th>
									<td><cui:input  name = "cjrq" readonly="true"  value="${cjrq }" ></cui:input></td>
								</tr>
								<tr>
								<%-- <th class = "cloneType">复制方式：</th>
									<td class = "cloneType" ><cui:combobox id="cloneType"  name="cloneType" data= "com_cloneType"></cui:combobox>
									<cui:button label="复制" id="cloneBtn" componentCls="btn-primary" onClick="cloneData"/>
									</td> --%>
									<td colspan="2"> 
										&nbsp;
						 				<cui:button label="继续" id="ensure" componentCls="btn-primary" onClick="ensure('2')"/>	
						 				<%--  <cui:button label="自动排班" id="autoDuty" componentCls="btn-primary" onClick="ensure('4')" />	 --%>		
									</td>
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
	
	<cui:grid id="grid_jyzbDetail" colModel="gridColModel"    cellEdit="true" rownumbers="true"
        fitStyle="width" height="800"   rowNum="31"  >
       <%--  <cui:gridCols>
			<cui:gridCol name="id" hidden="true">id</cui:gridCol>
			<cui:gridCol name="dutyDate"   align="center"  >值班日期</cui:gridCol>
			<cui:gridCol name="zhz" editable="true" width="90" edittype="autocomplete"
			 editoptions="postMode:true,required:true,valueField:'value', multiple:'true',multiLineLabel:'true',textField:'label',source:'${ctx}/zbgl/rygl/getCommonbox?cusNumber=4300'">指挥长</cui:gridCol>
		 	 <cui:gridCol name="zbz" editable="true" width="90" edittype="autocomplete" editoptions="postMode:true,required:true,valueField:'value', multiple:'true',textField:'label',source:'${ctx}/zbgl/rygl/getCommonbox?cusNumber=4300',onSelect:gridRowJbxx">值班长</cui:gridCol>
			<cui:gridCol name="zby" editable="true" width="90" edittype="autocomplete" editoptions="postMode:true,required:true,valueField:'value', multiple:'true',textField:'label',source:'${ctx}/zbgl/rygl/getCommonbox?cusNumber=4300',onSelect:gridRowJbxx">值班员</cui:gridCol>	
	 	</cui:gridCols>  --%>
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
		//debugger;
		$("#edit_cusNumber").combobox("option","readonly",true);
		
		if(id) {//修改
			
			loadForm("formId_zbbp_edit", "${ctx}/zbgl/jyzbhz/getJyzbBaseById?id="+id, function(data) {
               
			});
			$("#edit_zbYf").datepicker("option","readonly",true);
			$("#ensure").hide();
			ensure('1');
		}else{
			//新增
			$("#edit_cusNumber").combobox("setValue",cusNumber);
			$("#grid_jyzbDetail").hide();
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
        formatter : "autocomplete",
        formatoptions : {
        	'multiple':true,
            'required' : true,
            'multiLineLabel':true,
           // 'valueField' : 'value',
          // 'textField' : 'label',
           'postMode':'value',
           'source' : commonbox_ry,
        }
    },{
        label : "值班长",
        name : "zbz",
        formatter : "autocomplete",
        formatoptions : {
        	'multiple':true,
        	 'multiLineLabel':true,
            'required' : true,
           // 'valueField' : 'value',
            //'textField' : 'label',
            'postMode':'value',
            'source' : commonbox_ry
        }
    },{
        label : "值班人员",
        name : "zby",
        formatter : "autocomplete",
        formatoptions : {
        	'multiple':true,
            'required' : true,
            'multiLineLabel':true,
           // 'valueField' : 'value',
           // 'textField' : 'label',
            'postMode':'value',
            'source' : commonbox_ry
        }
    }];
	
	
	function ensure(param){
		if(param=="1"){
			//修改
			initModel(id);
		}
		if(param=="2"){
			var validFlag  = $("#formId_zbbp_edit").form("valid");
			if(!validFlag) {
				return;
			}
			//新增
			debugger;
		var month =	$("#edit_zbYf").datepicker("getValue");
			initModel("",month);
			$("#grid_jyzbDetail").show();
		}
		
	}
	
	
	
	function initModel(id,month){
		var params = {};
		if(id!="" && id !=null){
			params["id"] = id;
		}
		if(month!="" && month!=0 && month!=undefined){
			params["zbYf"] = month+"-01";
		}
	//	params["param"] =param;
		$.ajax({
			type : 'post',
			url : '${ctx}/zbgl/jyzbhz/getJyzbDetailByYwID',
			data: params,
			dataType : 'json',
			success : function(data) {
				$("#grid_jyzbDetail").grid("reload",data); 
			}
		});
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
	    $("#dmdStartDate").datepicker("setValue",syqxks);
	    $("#dmdEndDate").datepicker("setValue",syqxjs);
	}
	
	

</script>